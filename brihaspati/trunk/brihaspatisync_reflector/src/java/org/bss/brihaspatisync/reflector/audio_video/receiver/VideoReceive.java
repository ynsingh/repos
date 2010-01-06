package org.bss.brihaspatisync.reflector.audio_video.receiver;


/*
 * VideoReceive.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.net.InetAddress;
import javax.swing.JOptionPane;

import javax.media.Manager;
import javax.media.protocol.DataSource;
import javax.media.control.BufferControl;

import javax.media.rtp.RTPControl;
import javax.media.rtp.RTPManager;
import javax.media.rtp.Participant;
import javax.media.rtp.ReceiveStream;
import javax.media.rtp.SessionAddress;
import javax.media.rtp.SessionListener;
import javax.media.rtp.ReceiveStreamListener;

import javax.media.rtp.event.ByeEvent;
import javax.media.rtp.event.SessionEvent;
import javax.media.rtp.event.StreamMappedEvent;
import javax.media.rtp.event.ReceiveStreamEvent;
import javax.media.rtp.event.NewParticipantEvent;
import javax.media.rtp.event.NewReceiveStreamEvent;
import javax.media.rtp.event.RemotePayloadChangeEvent;

import org.bss.brihaspatisync.reflector.Reflector;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class VideoReceive implements ReceiveStreamListener, SessionListener {

	private static boolean value;

    	private static int initialize=0;

    	private RTPManager mgrs[] = null;

    	private static DataSource ds=null;

    	private static VideoReceive av=null;

    	private static RTPControl rtpc=null;

    	private boolean dataReceived = false;

    	private Object dataSync = new Object();

    	private int port=Reflector.getController().getVedioPort();

	public static VideoReceive getVideoReceiveController(){
    		if(av==null)
       			av=new VideoReceive();
       		return av; 
   	}


	/** getting the clone datasource for the Audio */

    	public static DataSource getDataSource() {
       		if(ds!=null) {
          		ds=Manager.createCloneableDataSource(ds);
         	}
		return ds;
    	}

    	public VideoReceive() { }

	/**Initialise the RTPSession for the Unicast Video */

    	public boolean initialize() {
    		try {
	    		InetAddress ipAddr;
            		initialize=1;                                                  
	    		SessionAddress localAddr = new SessionAddress();
	    		SessionAddress destAddr;
	    		mgrs = new RTPManager[2];
            		value=false;

	    		for (int i = 0; i < 1; i++) {
    				mgrs[i] = (RTPManager) RTPManager.newInstance();
				mgrs[i].addSessionListener(this);
				mgrs[i].addReceiveStreamListener(this);
                		ipAddr = InetAddress.getByName("172.28.44.86");
                		localAddr= new SessionAddress(InetAddress.getLocalHost(),port);
                		destAddr = new SessionAddress(ipAddr,port);
				mgrs[i].initialize(localAddr);
                		BufferControl bc = (BufferControl)mgrs[i].getControl("javax.media.control.BufferControl");
				if (bc != null)
		    			bc.setBufferLength(350);
    				mgrs[i].addTarget(destAddr);
	   		}
       		} catch (Exception e){
                	System.err.println("Cannot create the RTP Session: " + e.getMessage());
                	return value;
        	}

		long then = System.currentTimeMillis();
		long waitingPeriod = 10000;  

		try{
	    		synchronized (dataSync) {
				while (!dataReceived) {
		    			if (!dataReceived)
						System.err.println("  - Waiting for RTP data to arrive...");
		    			dataSync.wait(1000);
				}
	    		}
	  	} catch (Exception e) {}


		if (!dataReceived) {
 	    		System.err.println("No RTP data was received.");
            		JOptionPane.showMessageDialog(null,"Sorry You do not get the unicast Video");
            
	     		close();
	    		return false;
		}
       		JOptionPane.showMessageDialog(null,"You get the unicast Video");
        	return true;
    	}


    	public boolean isDone() {
        	return value;
    	}


   	public void close() { 
    		value=true;

       		/** Close all of the RTP Session for the Video */
       		if(initialize==1) {
          		for(int i = 0; i < mgrs.length; i++) {
             			if (mgrs[i] != null) {
                 			mgrs[i].removeTargets( "Closing session from AVReceive2");
                 			mgrs[i].dispose();
                 			mgrs[i] = null;
             			}
          		}
      		}   
  	}


    	/**SessionListener */
     
    	public synchronized void update(SessionEvent evt) {
		if (evt instanceof NewParticipantEvent) {
	    		Participant p = ((NewParticipantEvent)evt).getParticipant();
	    		System.err.println("  - A new participant had just joined: " + p.getCNAME());
		}
   	}


    	/** ReceiveStreamListener */

    	public synchronized void update( ReceiveStreamEvent evt) {

		RTPManager mgr = (RTPManager)evt.getSource();
		Participant participant = evt.getParticipant();	
		ReceiveStream stream = evt.getReceiveStream();  

		if (evt instanceof RemotePayloadChangeEvent) {
         		System.err.println("  - Received an RTP PayloadChangeEvent.");
	    		System.err.println("Sorry, cannot handle payload change.");

		}
    
		else if (evt instanceof NewReceiveStreamEvent) {

	    		try {
				stream = ((NewReceiveStreamEvent)evt).getReceiveStream();
		 		ds = stream.getDataSource();
				
				/** Find out the formats of Video */
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				if (ctl != null){
		    			System.err.println("  - Recevied format of the new RTP stream if first: " + ctl.getFormat());
				} else
		    			System.err.println("  - here we do not get a format of stream  and Recevied new RTP stream");

				if (participant == null)
		    			System.err.println("      The sender of this stream had yet to be identified.");
				else {
		    			System.err.println("      The stream comes from: " + participant.getCNAME()); 
				}

				synchronized (dataSync) {
		    			dataReceived = true;
		    			dataSync.notifyAll();
				}

	    		} catch (Exception e) {
				System.err.println("NewReceiveStreamEvent exception " + e.getMessage());
				return;
	    		}
        
		}else if (evt instanceof StreamMappedEvent) {

	     		if (stream != null && stream.getDataSource() != null) {
		 		ds = stream.getDataSource();

				/** Find out the formats of Video */
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				System.err.println("  - The previously unidentified stream ");
				if (ctl != null)
		    			System.err.println("Received the format of the stream if 2nd==>" + ctl.getFormat());
				System.err.println("      had now been identified as sent by: " + participant.getCNAME());
	     		}

		}else if (evt instanceof ByeEvent) {

		     	System.err.println("  - Got \"bye\" from: " + participant.getCNAME());
		}
    	}
}    

