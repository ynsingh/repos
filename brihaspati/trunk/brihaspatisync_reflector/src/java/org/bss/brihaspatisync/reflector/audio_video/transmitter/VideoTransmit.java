package org.bss.brihaspatisync.reflector.audio_video.transmitter;

/**
 * VideoTransmit.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010-2011
 */

import java.awt.Dimension;
import java.net.InetAddress;

import javax.media.Codec;
import javax.media.Owned;
import javax.media.Format;
import javax.media.Player;
import javax.media.Control;
import javax.media.Processor;

import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.ControllerClosedEvent;

import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;

import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;

import javax.media.control.TrackControl;
import javax.media.control.QualityControl;

import javax.media.protocol.ContentDescriptor;

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.audio_video.receiver.VideoReceive;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class VideoTransmit {

	private SendStream stream=null;
    	private static VideoTransmit av=null;    
    	private Processor processor = null;
    	private RTPManager rtpvideo;
    	private DataSource dataOutput = null;
    	private int port=RuntimeDataObject.getController().getVedioPort();
		
    	public static VideoTransmit getVideoTransmitController() {
    		if(av==null)
      			av=new VideoTransmit();
      		return av;
    	}
	
    	public VideoTransmit() { 
		try {
		rtpvideo=RTPManager.newInstance();
		SessionAddress localAddr1=new SessionAddress();
                rtpvideo.initialize(localAddr1);
		}catch(Exception e){System.out.println("Error in dispose of in Transmission of Video==> "+e);}
	}
	
    	private String createProcessor() {
		DataSource ds;
		DataSource clone;
	    	try {
	          	ds=VideoReceive.getVideoReceiveController().getDataSource();
	    	} catch (Exception e) {
	    		return "Couldn't create DataSource";
		}
		try {
	    		processor = javax.media.Manager.createProcessor(ds);
		} catch (Exception e) {
			System.out.println("Error in createProcessor in VideoTransmit.java "+e.getMessage());
		} 

		boolean result = waitForState(processor, Processor.Configured);
		if (result == false)
	    		return "Couldn't configure processor";

		TrackControl [] tracks = processor.getTrackControls();

		if (tracks == null || tracks.length < 1)
	    		return "Couldn't find tracks in processor";

	  	ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW_RTP);
	  	processor.setContentDescriptor(cd);

		result = waitForState(processor, Controller.Realized);
		if (result == false)
    			return "Couldn't realize processor";
		setJPEGQuality(processor, 0.5f);
		dataOutput = processor.getDataOutput();
		return null;
    	}

    	private Format checkForVideoSizes(Format original, Format supported) {

		int width, height;
		Dimension size = ((VideoFormat)original).getSize();
		Format jpegFmt = new Format(VideoFormat.JPEG_RTP);
		Format h263Fmt = new Format(VideoFormat.H263_RTP);

		if (supported.matches(jpegFmt)) {
	    		width = (size.width % 8 == 0 ? size.width :(int)(size.width / 8) * 8);
	    		height = (size.height % 8 == 0 ? size.height :(int)(size.height / 8) * 8);
		} else if (supported.matches(h263Fmt)) {
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
	        	return supported;
	  	}

		return (new VideoFormat(null, 
				new Dimension(width, height), 
				Format.NOT_SPECIFIED,
				null,
				Format.NOT_SPECIFIED)).intersects(supported);
    	}


    	private void setJPEGQuality(Player p, float val) {

		Control cs[] = p.getControls();
		QualityControl qc = null;
		VideoFormat jpegFmt = new VideoFormat(VideoFormat.JPEG);

		for (int i = 0; i < cs.length; i++) {

	    		if (cs[i] instanceof QualityControl && cs[i] instanceof Owned) {
				Object owner = ((Owned)cs[i]).getOwner();

				if (owner instanceof Codec) {
			    		Format fmts[] = ((Codec)owner).getSupportedOutputFormats(null);
			    		for (int j = 0; j < fmts.length; j++) {
						if (fmts[j].matches(jpegFmt)) {
			    				qc = (QualityControl)cs[i];
	    		    				qc.setQuality(val);
				    			System.out.println("- Setting quality to " + val + " on " + qc);
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
    
    	private Integer getStateLock() {
		return stateLock;
    	}

    	private void setFailed() {
		failed = true;
    	}
    
    	private synchronized boolean waitForState(Processor p, int state) {
		p.addControllerListener(new StateListener());
		failed = false;

		if (state == Processor.Configured) {
	    		p.configure();
		} else if (state == Processor.Realized) {
	    		p.realize();
		}
	
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

	    		if (ce instanceof ControllerClosedEvent)
				setFailed();

	    		if (ce instanceof ControllerEvent) {
				synchronized (getStateLock()) {
		    			getStateLock().notifyAll();
				}
	    		}
		}
    	}
        
	/** Initialize session to transmit stream*/
	public void createTransmitter(String str) {
                try{
                        InetAddress ipAddr=InetAddress.getByName(str);
                        SessionAddress destAddr=new SessionAddress(ipAddr,port);
                        rtpvideo.addTarget(destAddr);
			stream=rtpvideo.createSendStream(dataOutput,0);
                        stream.start();
                        destAddr=null;
                }catch(Exception e) { System.out.println("Error createTransmitter in VideoTransmit.java "+e);}
        }
	
	/**Strat stream sender thread*/
        public void streamTransmitterStart() {
                try{
                        stream=rtpvideo.createSendStream(dataOutput,0);
                        stream.start();
                } catch(Exception ex) { System.out.println("Error in transmission Stream for the Video===>"+ex); }
        }
	
	/**Stop strean sender thread*/
        public void streamTransmitterStop() {
                try{
                        stream.stop();
                        stream=null;
                } catch(Exception ex) { System.out.println("Error in transmission Stream for the Video===>"+ex); }
        }
	
	/**Start processor*/
	public synchronized String start() {
                String result=null;
                result = createProcessor();
                if (result == null)
                        System.out.println("createProcessor  already start");
                if (result == null) {
                        processor.start();
                        System.out.println("processor are in start ");
                }else {
                        System.out.println("processor already start ");
                        result=null;
                }
                return null;
        }

        /**
         * Stops the transmission if already started
         */
        public void stop() {
		try{
                	System.out.println("we are in stop all thread in VideoTransmit.java ");
                        if (processor != null) {
                        	rtpvideo.removeTargets("Session ended.");
                       	}
                        stream.stop();
                        rtpvideo.dispose();
                        processor.close();
                        processor = null;
               	}catch(Exception e){System.out.println("Error in dispose of in Transmission of Video==> "+e);}
        }
}

