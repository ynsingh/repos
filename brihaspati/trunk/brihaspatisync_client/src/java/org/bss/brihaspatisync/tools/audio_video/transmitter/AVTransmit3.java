package org.bss.brihaspatisync.tools.audio_video.transmitter;

/**
 * AVTransmit3.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.awt.Frame;
import java.awt.Dimension;

import java.io.IOException;
import java.net.InetAddress;

import javax.media.Codec;
import javax.media.Format;
import javax.media.Owned;
import javax.media.Player;
import javax.media.Control;
import javax.media.Manager;
import javax.media.Processor;
import javax.media.Controller;
import javax.media.MediaLocator;
import javax.media.ControllerEvent;
import javax.media.CaptureDeviceInfo;
import javax.media.ControllerListener;
import javax.media.NoProcessorException;
import javax.media.CaptureDeviceManager;
import javax.media.ControllerClosedEvent;

import javax.media.protocol.DataSource;
import javax.media.protocol.SourceCloneable;
import javax.media.protocol.PushBufferStream;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.PushBufferDataSource;

import javax.media.format.VideoFormat;
import javax.media.format. AudioFormat;


import javax.media.control.TrackControl;
import javax.media.control.FormatControl;
import javax.media.control.QualityControl;

import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;

import javax.media.rtp.rtcp.SourceDescription;

import java.util.Vector;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>      
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class AVTransmit3 {

	private MediaLocator locator;
	private AudioFormat format=null;
	private CaptureDeviceInfo di= null;
	private boolean encodingOk = false;
	private String ipAddress=ClientObject.getController().getReflectorIP();
	private int portBase=RuntimeDataObject.getController().getAudioPort();//ClientObject.getController().getAVPort();
	private Processor processor = null;
	private RTPManager rtpMgrs[];
	private DataSource dataOutput = null;
	private CaptureDeviceInfo audioCDI = null;
	private CaptureDeviceInfo videoCDI = null;
	private String audioDeviceName = null;
	private String videoDeviceName = null;
    	private Format videoFormat;
  	private Format audioFormat;
  	static DataSource captured_ds=null;
//	private Log log=Log.getController();
  
  	
  	private static AVTransmit3 av_trans=null;
  	
  	public  static AVTransmit3 getController(){
  		if(av_trans==null){
  			av_trans=new AVTransmit3();
  		}
  		return av_trans;
  	}
  	
  	public static DataSource getDataSource(){
  		if(captured_ds!=null)
  			return captured_ds;
  		return null;
  	}
  	
  	
    	public AudioFormat getAudioFormat(){
         	if (format==null){
         		format= new AudioFormat(AudioFormat.LINEAR,
                        	                      8000,
                                	              8,
                                        	      1);
		}
         	return format;
        }
        
  	public CaptureDeviceInfo getCaptureDevice(){
      		Vector devices= CaptureDeviceManager.getDeviceList( getAudioFormat());
        	if (devices.size() > 0) {
        		di = (CaptureDeviceInfo) devices.elementAt(0);
                  
        	}else {
            		System.out.println("Sorry!! No audio device found");
       		}
        	return di;
  	}
  	
  	void registerDevices() {
    		CaptureDeviceDialog cdDialog = new CaptureDeviceDialog(new Frame(), "Capture Device", true);
    		cdDialog.show();
    		if (!cdDialog.hasConfigurationChanged())
      			return;

    		//configuration has changed, update variables.
    		audioCDI = cdDialog.getAudioDevice();
    		if (audioCDI!=null) {
      			audioDeviceName = audioCDI.getName();
      			System.out.println("Audio Device Name: " + audioDeviceName);
    		}

    		videoCDI = cdDialog.getVideoDevice();
    		if (videoCDI!=null) {
      			videoDeviceName = videoCDI.getName();
      			System.out.println("Video Device Name: " + videoDeviceName);
    		}

    		//Get formats selected, to be used for creating DataSource
    		videoFormat = cdDialog.getVideoFormat();
    		audioFormat = cdDialog.getAudioFormat();
  	}


    	/**
     	 * Starts the transmission. Returns null if transmission started ok.
     	 * Otherwise it returns a string with the reason why the setup failed.
     	 */
    	public synchronized String start() {
        	String result;

        	// Create a processor for the specified media locator
        	// and program it to output JPEG/RTP
        	result = createProcessor();
        	if (result != null)
            		return result;

        	// Create an RTP session to transmit the output of the
        	// processor to the specified IP address and port no.
        	result = createTransmitter();
        	if (result != null) {
            		processor.close();
            		processor = null;
            		return result;
        	}

        	// Start the transmission
        	processor.start();
        	return null;
    	}

    	/**
     	 * Stops the transmission if already started
     	 */
    	public void stop() {
        	synchronized (this) {
            		if (processor != null) {
                		processor.stop();
                		processor.close();
                		processor = null;
                		for (int i = 0; i < rtpMgrs.length; i++) {
                    			rtpMgrs[i].removeTargets( "Session ended.");
                    			rtpMgrs[i].dispose();
                		}
            		}
        	}
    	}
   
	private String createProcessor() {
        	DataSource ds=null;
        	DataSource clone;
        
		if (audioCDI==null && videoCDI==null)
      			registerDevices();

    		try {
      			if (!(audioCDI==null && videoCDI==null)) {

     				DataSource[] dataSources = new DataSource[2];
	        		System.out.println("Creating data sources.");

	        		dataSources[0] = Manager.createDataSource(audioCDI.getLocator());
        			dataSources[1] = Manager.createDataSource(videoCDI.getLocator());
        			ds = Manager.createMergingDataSource(dataSources);        
      			}
      			else
        			System.out.println("CDI not found.");
    		}catch (Exception e) {
      			System.out.println(e.toString());
    		}
    	 	captured_ds = Manager.createCloneableDataSource(ds);//capture.getVideoDS());
		ds = ( ( SourceCloneable )captured_ds).createClone();
	
        // Try to create a processor to handle the input media locator
        try {
            processor = javax.media.Manager.createProcessor(ds);
        } catch (NoProcessorException npe) {
            return "Couldn't create processor";
        } catch (IOException ioe) {
            return "IOException creating processor";
        }

        // Wait for it to configure
        boolean result = waitForState(processor, Processor.Configured);
        if (result == false)
            return "Couldn't configure processor";

        // Get the tracks from the processor
        TrackControl [] tracks = processor.getTrackControls();

        // Do we have atleast one track?
        if (tracks == null || tracks.length < 1)
            return "Couldn't find tracks in processor";

        // Set the output content descriptor to RAW_RTP
        // This will limit the supported formats reported from
        // Track.getSupportedFormats to only valid RTP formats.
        ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
        processor.setContentDescriptor(cd);

        Format supported[];
        //Format chosen;
        boolean encoding = false;

        // Program the tracks.
        for (int i = 0; i < tracks.length; i++) {
            Format format = tracks[i].getFormat();
            if (  tracks[i].isEnabled() && format instanceof AudioFormat ) {
				AudioFormat afmt = (AudioFormat)tracks[i].getFormat();
                AudioFormat newFormat =   new AudioFormat(AudioFormat.MPEG_RTP);
				tracks[i].setFormat (newFormat);
				System.err.println("Audio transmitted as:");
				System.err.println("  " + newFormat);
				// Assume succesful
				encoding = true;
				
            }else if (  tracks[i].isEnabled() && format instanceof VideoFormat ) {
				 Dimension size = ((VideoFormat)format).getSize();
                float frameRate = ((VideoFormat)format).getFrameRate();
                int w = (size.width % 8 == 0 ? size.width : (int)(size.width / 8) * 8);
                int h = (size.height % 8 == 0 ? size.height :(int)(size.height / 8) * 8);
              VideoFormat newFormat = new VideoFormat(VideoFormat.H263_RTP,
                                                       new Dimension(w, h),
                                                       Format.NOT_SPECIFIED,
                                                       Format.byteArray,
                                                       frameRate);

		
//				VideoFormat newFormat=new VideoFormat(VideoFormat.H263_RTP);
				tracks[i].setFormat(newFormat);
				System.err.println("Video transmitted as:");
				System.err.println("  " + newFormat);
				// Assume succesful
				encoding = true;
          } else
                tracks[i].setEnabled(false);
        }
        if (!encoding)
            return "Couldn't set any of the tracks to a valid RTP format";

        // Realize the processor. This will internally create a flow
        // graph and attempt to create an output datasource for JPEG/RTP
        // audio frames.
        result = waitForState(processor, Controller.Realized);
        if (result == false)
            return "Couldn't realize processor";

        // Set the JPEG quality to .5.
        //setJPEGQuality(processor, 0.5f);

        // Get the output data source of the processor
        dataOutput = processor.getDataOutput();
        
        

        return null;
    }

    /**
     * Use the RTPManager API to create sessions for each media
     * track of the processor.
     */
	private String createTransmitter() {

        	// Cheated.  Should have checked the type.
	        PushBufferDataSource pbds = (PushBufferDataSource)dataOutput;
        	PushBufferStream pbss[] = pbds.getStreams();

	        rtpMgrs = new RTPManager[pbss.length];
        	SendStream sendStream;
		int port=0;
	        SessionAddress localAddr, destAddr;
		InetAddress ipAddr;

	        SourceDescription srcDesList[];
	
        	for (int i = 0; i < pbss.length; i++) {
	        	try {
		                rtpMgrs[i] = RTPManager.newInstance();

                		port = portBase + 2*i;

              			ipAddr = InetAddress.getByName(ipAddress);
				localAddr = new SessionAddress( InetAddress.getLocalHost(),port);
				destAddr = new SessionAddress( ipAddr, port);
				rtpMgrs[i].initialize( localAddr);
				rtpMgrs[i].addTarget( destAddr);
				//log.setLog( "Created RTP session: " + ipAddress + " " + port);

                		sendStream = rtpMgrs[i].createSendStream(dataOutput, i);
                		sendStream.start();
                		System.out.println("Audio transmission is start");
            		} catch (Exception  e) {return e.getMessage();}
        	}

        	return null;
    	}

/**
     * For JPEG and H263, we know that they only work for particular
     * sizes.  So we'll perform extra checking here to make sure they
     * are of the right sizes.
     */
    Format checkForVideoSizes(Format original, Format supported) {

        int width, height;
        Dimension size = ((VideoFormat)original).getSize();
        Format jpegFmt = new Format(VideoFormat.JPEG_RTP);
        Format h263Fmt = new Format(VideoFormat.H263_RTP);

        if (supported.matches(jpegFmt)) {
            // For JPEG, make sure width and height are divisible by 8.
            width = (size.width % 8 == 0 ? size.width :
                                (int)(size.width / 8) * 8);
            height = (size.height % 8 == 0 ? size.height :
                                (int)(size.height / 8) * 8);
        } else if (supported.matches(h263Fmt)) {
            // For H.263, we only support some specific sizes.
            if (size.width < 128) {
                width = 128;
                height = 96;
            } else if (size.width < 176) {
                width = 176;
                height = 144;
            } else {
                width = 352;
                height = 288;
            }
        } else {
            // We don't know this particular format.  We'll just
            // leave it alone then.
            return supported;
        }

        return (new VideoFormat(null,
                                new Dimension(width, height),
                                Format.NOT_SPECIFIED,
                                null,
                                Format.NOT_SPECIFIED)).intersects(supported);
    }

/**
     * Setting the encoding quality to the specified value on the JPEG encoder.
     * 0.5 is a good default.
     */
    void setJPEGQuality(Player p, float val) {

        Control cs[] = p.getControls();
        QualityControl qc = null;
        VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

        // Loop through the controls to find the Quality control for
        // the JPEG encoder.
        for (int i = 0; i < cs.length; i++) {

            if (cs[i] instanceof QualityControl &&
                cs[i] instanceof Owned) {
                Object owner = ((Owned)cs[i]).getOwner();

                // Check to see if the owner is a Codec.
                // Then check for the output format.
                if (owner instanceof Codec) {
                    Format fmts[] = ((Codec)owner).getSupportedOutputFormats(null);
                    for (int j = 0; j < fmts.length; j++) {
                        if (fmts[j].matches(jpegFmt)) {
                            qc = (QualityControl)cs[i];
                            qc.setQuality(val);
                            System.out.println("- Setting quality to " +val + " on " + qc);
                            break;
                        }
                    }
                }
                if (qc != null)
                    break;
            }
        }
    }
    
    /****************************************************************
     * Convenience methods to handle processor's state changes.
     ****************************************************************/

    private Integer stateLock = new Integer(0);
    private boolean failed = false;

    Integer getStateLock() {
        return stateLock;
    }

    void setFailed() {
        failed = true;
    }

    private synchronized boolean waitForState(Processor p, int state) {
        p.addControllerListener(new StateListener());
        failed = false;

        // Call the required method on the processor
        if (state == Processor.Configured) {
            p.configure();
        } else if (state == Processor.Realized) {
            p.realize();
        }

        // Wait until we get an event that confirms the
        // success of the method, or a failure event.
        // See StateListener inner class
        while (p.getState() < state && !failed) {
            synchronized (getStateLock()) {
                try {
                    getStateLock().wait();
                } catch (InterruptedException ie) {
                    return false;
                }
            }
        }

        if (failed)
            return false;
        else
            return true;
    }

    /****************************************************************
     * Inner Classes
     ****************************************************************/

    class StateListener implements ControllerListener {

        public void controllerUpdate(ControllerEvent ce) {

            // If there was an error during configure or
            // realize, the processor will be closed
            if (ce instanceof ControllerClosedEvent)
                setFailed();

            // All controller events, send a notification
            // to the waiting thread in waitForState method.
            if (ce instanceof ControllerEvent) {
                synchronized (getStateLock()) {
                    getStateLock().notifyAll();
                }
            }
        }
    }
    
}
