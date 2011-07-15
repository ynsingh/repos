package org.bss.brihaspatisync.tools.audio_video.transmitter;

/**
 *  PresentationAudioTransmit.java
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.InetAddress;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.format.*;
import javax.media.control.TrackControl;
import javax.media.control.FormatControl;
import javax.media.control.QualityControl;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;
import com.sun.media.rtp.*;
import java.util.Vector;

import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.util.Language;

public class PresentationAudioTransmit {
	private MediaLocator locator;
	private AudioFormat format=null;
	private CaptureDeviceInfo di= null;
	private boolean encodingOk = false;
	private String ipAddress=RuntimeDataObject.getController().getMasterReflectorIP();//"172.26.82.18";
	private int portBase=RuntimeDataObject.getController().getPresentationAudioPort();
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

  	public static DataSource getDataSource(){
  		if(captured_ds!=null)
  			return captured_ds;
  		return null;
  	}

    	public AudioFormat getAudioFormat(){
        	if (format==null)
         	format= new AudioFormat(AudioFormat.LINEAR,
                                              8000,
                                              8,
                                              1);
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
    		PresentationCaptureDeviceDailog cdDialog = new PresentationCaptureDeviceDailog(new Frame(),Language.getController().getLangValue("PresentationAudioTransmit.CaptureDialogTitle") , true);   		cdDialog.show();
    		if (!cdDialog.hasConfigurationChanged())
      			return;
		//configuration has changed, update variables.
    		audioCDI = cdDialog.getAudioDevice();
    		if (audioCDI!=null) {
      			audioDeviceName = audioCDI.getName();
      			System.out.println("Audio Device Name: " + audioDeviceName);
    		}
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
	System.out.println("PresentationAudioTransmit stopping");
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
	System.out.println("PresentationAudioTransmit stop successfully");
    }

   private String createProcessor() {
        DataSource ds=null;
        DataSource clone;

	System.out.println("Audio Device =========================================="+audioCDI);
		if (audioCDI==null)
			registerDevices();
   			try {
      			ds = Manager.createDataSource(audioCDI.getLocator());
			}catch (Exception e) {
      			System.out.println("Erron in device detecting "+e.toString());
    		}
System.out.println("=========================================="+ds);
		
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

                		sendStream = rtpMgrs[i].createSendStream(dataOutput, i);
                		sendStream.start();
                		System.out.println("Audio transmission is start");
            		} catch (Exception  e) {return e.getMessage();}
        	}

        	return null;
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
