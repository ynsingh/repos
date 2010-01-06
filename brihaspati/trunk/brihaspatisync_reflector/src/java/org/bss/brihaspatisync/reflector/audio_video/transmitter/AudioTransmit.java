package org.bss.brihaspatisync.reflector.audio_video.transmitter;


/*
 * AudioTransmit.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.net.InetAddress;

import javax.media.Format;
import javax.media.Processor;

import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;
import javax.media.format.AudioFormat;

import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.NoProcessorException;
import javax.media.ControllerClosedEvent;

import javax.media.protocol.DataSource;
import javax.media.control.TrackControl;
import javax.media.control.FormatControl;
import javax.media.protocol.ContentDescriptor;

import org.bss.brihaspatisync.reflector.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.reflector.Reflector;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class AudioTransmit {

	private	int port=Reflector.getController().getAudioPort(); 
	private RTPManager rtpaudio;
	private SendStream stream=null;
	private InetAddress ipAddr=null;
	private Processor processor = null;
	private DataSource dataOutput = null;
    	private static AudioTransmit av=null;    
    	
	public static AudioTransmit getAudioTransmitController() {
    		if(av==null)
      			av=new AudioTransmit();
      		return av;
    	}
	
    	public AudioTransmit() { 
		try {
			rtpaudio=RTPManager.newInstance();
			SessionAddress localAddr=new SessionAddress();
                	rtpaudio.initialize(localAddr);
		}catch(Exception e){System.out.println("Error in dispose of in Audeo ==> "+e);}	
	}	    	
        

    	public synchronized String start() {
                String result = createProcessor();
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
			System.out.println("we are in stop audio transmit");
	     		if(processor != null) {
	        		rtpaudio.removeTargets("Session ended.");
				stream.stop();
				rtpaudio.dispose();
				processor.close();
	                        processor = null;
			}else 
				System.out.println("processor is already start ");
		}catch(Exception e){System.out.println("Error in dispose in transmission of Audio===>"+e);}
	} 
		
	
   	private String createProcessor() {
      		System.out.println("we are in createProcessor ");
		DataSource ds;
		DataSource clone;
        	/**get the clone of the datasource of the audio for the transmission of Audio from the Capture Device*/
	    	try {
	        	ds=AudioReceive.getAudioReceiveController().getDataSource();	          
		} catch (Exception e) {   return "Couldn't create DataSource";  }

		/** Try to create a processor to handle the input media locator */
		try {
	    		processor = javax.media.Manager.createProcessor(ds);
  	    	}catch (NoProcessorException npe) {
	        	return "Couldn't create processor";
	    	}catch (Exception ioe) {
	        	return "IOException creating processor";
	   	} 

		/** Wait for to configure the processor */

		boolean result = waitForState(processor, Processor.Configured);
		if (result == false)
	    		return "Couldn't configure processor";

		/** Get the tracks from the processor */
		TrackControl [] tracks = processor.getTrackControls();

		/** check do we have atleast one track? */
		if (tracks == null || tracks.length < 1)
	    		return "Couldn't find tracks in processor";
	
     		/**set the Content Descriptor */
	  	ContentDescriptor cd = new ContentDescriptor(ContentDescriptor.RAW);
	  	processor.setContentDescriptor(cd);

		Format supported[];
		Format chosen;
		boolean atLeastOneTrack = false;

		/** Program the tracks. */

       		System.out.println("Getting the tracks length==>"+tracks.length);

		for (int i = 0; i < 1; i++) {
	    		Format format = tracks[i].getFormat();
	    		if (tracks[i].isEnabled()) {
				supported = tracks[i].getSupportedFormats();
				if (supported.length > 0) {
		    			//if (supported[0] instanceof VideoFormat) {
					//	chosen = checkForVideoSizes(tracks[i].getFormat(), supported[0]);
		    			//} else
					
					chosen = supported[0];
		    			tracks[i].setFormat(chosen);
		    			System.err.println("Track " + i + " is set to transmit as:");
		    			System.err.println("Supported stream===>" + chosen);
		    			atLeastOneTrack = true;
				} else
		    			tracks[i].setEnabled(false);
	    		} else
				tracks[i].setEnabled(false);
		}
		
		if (!atLeastOneTrack)
	    		return "Couldn't set any of the tracks to a valid RTP format";

		result = waitForState(processor, Controller.Realized);
		if (result == false)
	    		return "Couldn't realize processor";

		/** Get the output data source of the processor */
		dataOutput = processor.getDataOutput();

		return null;
    	}


  	/**Transmit the stream of Audio*/
  	public String createTransmitter(String str) {
    		try{
			ipAddr=InetAddress.getByName(str);
			SessionAddress destAddr =new SessionAddress(ipAddr,port);
			rtpaudio.addTarget(destAddr);
			destAddr=null;
       		}catch(Exception e) { System.out.println("Error in initialize address to transmit Audio==>"+e); }
       		return null;
  	}					 
	public void streamTransmitterStart() {
                try{
			System.out.println("DataSource==============>"+dataOutput);
                        stream=rtpaudio.createSendStream(dataOutput,0);
                        stream.start();
                } catch(Exception ex) { System.out.println("Error in Start send stream for the audio===>"+ex); }
        }
        public void streamTransmitterStop() {
                try{
                        stream.stop();
                        stream=null;
                } catch(Exception ex) { System.out.println("Error in stop send stream for the audio===>"+ex); }
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

}//end of class

