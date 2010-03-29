package org.bss.brihaspatisync.reflector.audio_video.receiver;


/*
 * AudioReceive.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */


import java.util.Vector;
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
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class AudioReceive implements ReceiveStreamListener, SessionListener {
	
    	private static boolean value;
    	
	private static int initialize=0;
    	
	private RTPManager mgrs[] = null;
    	
	private static DataSource ds=null;
    	
	private static RTPControl rtpc=null;
    	
	private static AudioReceive av=null;
    	
	private boolean dataReceived = false;
    	
	private Object dataSync = new Object();

	private MaintainLog log=MaintainLog.getController();
	
	private int port=RuntimeDataObject.getController().getAudioPort();                                              

	/** getting the clone datasource for the Audio */  

    	public static DataSource getDataSource() {
       		if(ds!=null) {
          		ds=Manager.createCloneableDataSource(ds);
         	}
              	return ds;
    	}


	/** geting the controller of AudioReceive */

    	public static AudioReceive getAudioReceiveController() {
       		if(av==null)
       			av=new AudioReceive();
       		return av; 
     	}
     

    	public AudioReceive() { }


	/**Initialise the RTPSession for the Receving of unicast audio from the same or the remote machine */
    	public boolean initialize() {
    		try {

			log.setString("audio Receive initialize");
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
                		ipAddr = InetAddress.getByName("172.26.82.21");
                		localAddr= new SessionAddress(InetAddress.getLocalHost(),port);
                		destAddr = new SessionAddress(InetAddress.getLocalHost(),port);//ipAddr,port);
				mgrs[i].initialize(localAddr);
                		BufferControl bc = (BufferControl)mgrs[i].getControl("javax.media.control.BufferControl");
				if (bc != null)
		    			bc.setBufferLength(350);
    				mgrs[i].addTarget(destAddr);
	   		}
       		} catch (Exception e){
                	log.setString("Cannot create the RTP Session: " + e.getMessage());
                	return value;
        	}

		long then = System.currentTimeMillis();
		long waitingPeriod = 10000;  

		try{
   	    		synchronized (dataSync) {
				while (!dataReceived) {
		    			if (!dataReceived)
						log.setString("  - Waiting for RTP data to arrive...");
		       			dataSync.wait(1000);
              			}
	    		}
	  	} catch (Exception e) {}

		if (!dataReceived) {
	    		log.setString("No RTP data was received.");
            		JOptionPane.showMessageDialog(null,"Sorry You do not get the unicast Audio");
            
           		/**If data does not receive then transmitAudio button become disabled */
         
	    		close();
	    		return false;
		}

        	JOptionPane.showMessageDialog(null,"You get the unicast Audio");
        	return true;
    	}

    	public boolean isDone() {
        	return value;
    	}

    	public void windowclose() {
    		value=true;
    	} 

    	public void close() { 
        	log.setString("Stopping AudioReceive & close all Session for receive Audio"); 
		value=true;

       		/**If session is Initialise then close all of the Session */
    		if(initialize==1) {  
        		for (int i = 0; i < mgrs.length; i++) {
            			if (mgrs[i] != null) {
                 			mgrs[i].removeTargets( "Closing session from Audio");
                 			mgrs[i].dispose();
                 			mgrs[i] = null;
               			}
           		}
      		}
    	}


    	/** SessionListener. */
     
    	public synchronized void update(SessionEvent evt) {
		if (evt instanceof NewParticipantEvent) {
	    		Participant p = ((NewParticipantEvent)evt).getParticipant();
	    		log.setString("  - A new participant had just joined: " + p.getCNAME());
		}
    	}


    	/** ReceiveStreamListener */
    	public synchronized void update( ReceiveStreamEvent evt) {

		RTPManager mgr = (RTPManager)evt.getSource();
		Participant participant = evt.getParticipant();	
		ReceiveStream stream = evt.getReceiveStream();  

		if (evt instanceof RemotePayloadChangeEvent) {
     
	    		log.setString("  - Received an RTP PayloadChangeEvent.");
	    		log.setString("Sorry, cannot handle payload change.");
		}
    
		else if (evt instanceof NewReceiveStreamEvent) {
	    		try {
				stream = ((NewReceiveStreamEvent)evt).getReceiveStream();
		 		ds = stream.getDataSource();

	     			/** Find out the formats of the Audio */
 
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				if (ctl != null){
		    			log.setString("  - Recevied format of the new RTP stream if first: " + ctl.getFormat());
				} else
		    			log.setString("  - here we do not get a format of stream  and Recevied new RTP stream");

             			/**Find out is there any new user in this session */

				if (participant == null)
		    			log.setString("      The sender of this stream had yet to be identified.");
				else {
		    			log.setString("      The stream comes from: " + participant.getCNAME()); 
				}

				synchronized (dataSync) {
		    			dataReceived = true;
		    			dataSync.notifyAll();
				}

	    		} catch (Exception e) {
				log.setString("NewReceiveStreamEvent exception " + e.getMessage());
				return;
	    		}
        
		}else if (evt instanceof StreamMappedEvent) {
	     		if (stream != null && stream.getDataSource() != null) {
		 		ds = stream.getDataSource(); 

 				/** Find out the formats. */

				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				log.setString("  - The previously unidentified stream ");
				if (ctl != null)
		    			log.setString("Received the format of the stream if 2nd==>" + ctl.getFormat());
				log.setString("      had now been identified as sent by: " + participant.getCNAME());
	     		}
		}

		else if (evt instanceof ByeEvent) {
	     		log.setString("  - Got \"bye\" from: " + participant.getCNAME());
		}
    	}
}

