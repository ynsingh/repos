package org.bss.brihaspatisync.tools.audio_video.receiver;

/**
 * HandraiseAudioReceive.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import javax.media.Manager;
import javax.media.rtp.RTPManager;
import javax.media.rtp.RTPControl;
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
import javax.media.rtp.event.NewReceiveStreamEvent;
import javax.media.rtp.event.RemotePayloadChangeEvent;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.media.protocol.DataSource;

import java.net.InetAddress;

import org.bss.brihaspatisync.network.Log;
import javax.media.format.FormatChangeEvent;
import javax.media.control.BufferControl;
import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>      
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class HandraiseAudioReceive implements ReceiveStreamListener, SessionListener {

    	private RTPManager mgrs[] = null;
    	private boolean dataReceived = false;
    	private Object dataSync = new Object();
    	private static HandraiseAudioReceive hav=null;
    	private static DataSource ds=null;
    	private int port=RuntimeDataObject.getController().getAudioHandraisePort();//(ClientObject.getController().getAVPort())+4;
    	private String IPAddress=ClientObject.getController().getReflectorIP();                                               
    	private boolean value;
    	private int initialize=0;

	/** getting the clone datasource for the Audio */  
	
	
    	public static DataSource getAudioDataSource() {
       		if(ds!=null) {
          		ds=Manager.createCloneableDataSource(ds);
         	}
              	return ds;
    	}

	/** geting the controller of AudioReceive */
	/*
    	public static HandraiseAudioReceive getController() {
       		if(hav==null)
       			hav=new HandraiseAudioReceive();
       		return hav; 
     	}
	*/
    	public HandraiseAudioReceive() {
		
	}


	/**Initialise the RTPSession for the Receving of unicast audio from the same or the remote machine */
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
                		ipAddr = InetAddress.getByName(IPAddress);
                		localAddr= new SessionAddress(InetAddress.getLocalHost(),port);
                		destAddr = new SessionAddress(ipAddr,port);
				mgrs[i].initialize(localAddr);
                		BufferControl bc = (BufferControl)mgrs[i].getControl("javax.media.control.BufferControl");
				if (bc != null)
		    			bc.setBufferLength(350);
    				mgrs[i].addTarget(destAddr);
	   		}
       		} catch (Exception e){
                	System.out.println("Cannot create the RTP Session for handraise: " + e.getMessage());
                	return value;
        	}

		long then = System.currentTimeMillis();
		long waitingPeriod = 20000;  

		try{
   	    		synchronized (dataSync) {
				while (!dataReceived){// && System.currentTimeMillis() - then < waitingPeriod) {
					MainWindow.getController().getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					System.out.println("  - Waiting for Handraise RTP data to arrive...");
		       			dataSync.wait(5000);
              			}
	    		}
			MainWindow.getController().getContainer().setCursor(Cursor.getDefaultCursor());
			
	  	} catch (Exception e) {}

		if (!dataReceived) {
	    		System.out.println("No handraise RTP data was received.");
            		JOptionPane.showMessageDialog(null,"Sorry You do not get the handraise unicast Audio");
            
           		/**If data does not receive then transmitAudio button become disabled */
           		close();
	    		return false;
		}

        	JOptionPane.showMessageDialog(null,"You get the Handraised unicast Audio");
        	return true;
    	}
		
    	public boolean isDone() {
        	return value;
    	}

    	public void windowclose() {
    		value=true;
    	} 

    	public void close() { 
        	System.out.println("Stopping StudentAudioReceive & close all Session for receive Audio"); 
		value=true;

       		/**If session is Initialise then close all of the Session */

    		if(initialize==1) {  
        		for (int i = 0; i < mgrs.length; i++) {
            			if (mgrs[i] != null) {
                 			mgrs[i].removeTargets( "Closing session from Student Audio Receive");
                 			mgrs[i].dispose();
                 			mgrs[i] = null;
					ds=null;
					initialize=0;
               			}
           		}
      		}
    	}

    	/** SessionListener. */
     
    	public synchronized void update(SessionEvent evt) {
		if (evt instanceof NewParticipantEvent) {
	    		Participant p = ((NewParticipantEvent)evt).getParticipant();
	    		System.out.println("  - A new participant had just joined: " + p.getCNAME());
		}
    	}

    	/** ReceiveStreamListener */
    	public synchronized void update( ReceiveStreamEvent evt) {

		RTPManager mgr = (RTPManager)evt.getSource();
		Participant participant = evt.getParticipant();	
		ReceiveStream stream = evt.getReceiveStream();  

		if (evt instanceof RemotePayloadChangeEvent) {
     
	    		System.out.println("  - Received an RTP PayloadChangeEvent.");
	    		System.out.println("Sorry, cannot handle payload change.");
		}
    
		else if (evt instanceof NewReceiveStreamEvent) {
	    		try {
				stream = ((NewReceiveStreamEvent)evt).getReceiveStream();
		 		ds = stream.getDataSource();

	     			/** Find out the formats of the Audio */
 
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				if (ctl != null){
		    			System.out.println("  - Recevied format of the new RTP stream if first: " + ctl.getFormat());
				} else
		    			System.out.println("  - here we do not get a format of stream  and Recevied new RTP stream");

             			/**Find out is there any new user in this session */

				if (participant == null)
		    			System.out.println("      The sender of this stream had yet to be identified.");
				else {
		    			System.out.println("      The stream comes from: " + participant.getCNAME()); 
				}

				synchronized (dataSync) {
		    			dataReceived = true;
		    			dataSync.notifyAll();
				}

	    		} catch (Exception e) {
				System.out.println("NewReceiveStreamEvent exception " + e.getMessage());
				return;
	    		}
        
		}else if (evt instanceof StreamMappedEvent) {
	     		if (stream != null && stream.getDataSource() != null) {
		 		ds = stream.getDataSource(); 

 				/** Find out the formats. */

				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				System.out.println("  - The previously unidentified stream ");
				if (ctl != null)
		    			System.out.println("Received the format of the stream if 2nd==>" + ctl.getFormat());
				System.out.println("      had now been identified as sent by: " + participant.getCNAME());
	     		}
		}

		else if (evt instanceof ByeEvent) {
	     		System.out.println("  - Got \"bye\" from: " + participant.getCNAME());
		
		}
    	}
}

