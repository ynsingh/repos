package org.bss.brihaspatisync.tools.audio_video;

import java.io.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.rtp.rtcp.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.Format;
import javax.media.format.FormatChangeEvent;
import javax.media.control.BufferControl;
import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;


public class VideoReceive implements ReceiveStreamListener, SessionListener {

    	private RTPManager mgrs[] = null;
    	private boolean dataReceived = false;
    	private Object dataSync = new Object();
    	private static VideoReceive av=null;
    	private static DataSource ds=null;
    	private int port=(ClientObject.getController().getAVPort())+2;
    	private String IPAddress=ClientObject.getController().getReflectorIP(); 
	private static boolean value;
    	private static RTPControl rtpc=null;
    	private static int initialize=0;
	private Log log=Log.getController();


	public static VideoReceive getVideoReceiveController(){
    		if(av==null)
       			av=new VideoReceive();
       		return av; 
   	}


	/** getting the clone datasource for the Audio */

    	public static DataSource getVideoDataSource() {
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
                	log.setLog("Cannot create the RTP Session: " + e.getMessage());
                	return value;
        	}

		long then = System.currentTimeMillis();
		long waitingPeriod = 10000;  

		try{
	    		synchronized (dataSync) {
				while (!dataReceived && System.currentTimeMillis() - then < waitingPeriod) {
					MainWindow.getController().getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    			if (!dataReceived)
						log.setLog("  - Waiting for RTP data to arrive...");
		    			dataSync.wait(1000);
				}
	    		}
			MainWindow.getController().getContainer().setCursor(Cursor.getDefaultCursor());
	  	} catch (Exception e) {}
		

		if (!dataReceived) {
 	    		log.setLog("No RTP data was received.");
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
	    		log.setLog("  - A new participant had just joined: " + p.getCNAME());
		}
   	}


    	/** ReceiveStreamListener */

    	public synchronized void update( ReceiveStreamEvent evt) {

		RTPManager mgr = (RTPManager)evt.getSource();
		Participant participant = evt.getParticipant();	
		ReceiveStream stream = evt.getReceiveStream();  

		if (evt instanceof RemotePayloadChangeEvent) {
         		log.setLog("  - Received an RTP PayloadChangeEvent.");
	    		log.setLog("Sorry, cannot handle payload change.");

		}
    
		else if (evt instanceof NewReceiveStreamEvent) {

	    		try {
				stream = ((NewReceiveStreamEvent)evt).getReceiveStream();
		 		ds = stream.getDataSource();
				
				/** Find out the formats of Video */
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				if (ctl != null){
		    			log.setLog("  - Recevied format of the new RTP stream if first: " + ctl.getFormat());
				} else
		    			log.setLog("  - here we do not get a format of stream  and Recevied new RTP stream");

				if (participant == null)
		    			log.setLog("      The sender of this stream had yet to be identified.");
				else {
		    			log.setLog("      The stream comes from: " + participant.getCNAME()); 
				}

				synchronized (dataSync) {
		    			dataReceived = true;
		    			dataSync.notifyAll();
				}

	    		} catch (Exception e) {
				log.setLog("NewReceiveStreamEvent exception " + e.getMessage());
				return;
	    		}
        
		}else if (evt instanceof StreamMappedEvent) {

	     		if (stream != null && stream.getDataSource() != null) {
		 		ds = stream.getDataSource();

				/** Find out the formats of Video */
				RTPControl ctl = (RTPControl)ds.getControl("javax.media.rtp.RTPControl");
				log.setLog("  - The previously unidentified stream ");
				if (ctl != null)
		    			log.setLog("Received the format of the stream if 2nd==>" + ctl.getFormat());
				log.setLog("      had now been identified as sent by: " + participant.getCNAME());
	     		}

		}else if (evt instanceof ByeEvent) {
		     	log.setLog("  - Got \"bye\" from: " + participant.getCNAME());
		}
    	}
}    

