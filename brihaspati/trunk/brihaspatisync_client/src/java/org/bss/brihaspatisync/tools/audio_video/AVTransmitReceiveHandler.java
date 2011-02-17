package org.bss.brihaspatisync.tools.audio_video;

/**
 * AVTransmitReceiveHandler.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import javax.media.protocol.DataSource;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.tools.audio_video.receiver.AudioReceive;
//import org.bss.brihaspatisync.tools.audio_video.receiver.HandraiseAudioReceive;
import org.bss.brihaspatisync.tools.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.tools.audio_video.transmitter.AVTransmit3;
	
/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>      
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class AVTransmitReceiveHandler {

	private Thread hr_thread=null;
	
//	private HandraiseAudioReceive h_a_r=null;
		
  	private static AVTransmitReceiveHandler trHandler=null;

  	/** Getting the handler of the main controller class */
	public static AVTransmitReceiveHandler getController(){
		if(trHandler==null)
               		trHandler=new AVTransmitReceiveHandler();
	       	return trHandler;
   	}
   
   	/**Constructor for the main controller */
   	public AVTransmitReceiveHandler() {
   		 String role=ClientObject.getController().getUserRole();
   		 if(role.equals("student")){
   		 	System.out.println("start audio/vedio receive");
   		 	startReceiveAudio();
   		 	startReceiveVideo();
   		 }else{
   		  	System.out.println("start audio/video transmit");
   		  	String result = AVTransmit3.getController().start();
       	    		if (result != null) {
        	   		System.out.println("Error : " + result);
       			}
   		 }
   		 AudioVideoPanel.getController().startPlayer();
	}
   
   	public void startReceiveAudio(){
     		if (!AudioReceive.getAudioReceiveController().initialize()) {
        		System.out.println("Failed to initialize the sessions.");
       		}
		(new Thread(){
			public void run(){
				try {
                			while (!AudioReceive.getAudioReceiveController().isDone()) {
                    				Thread.sleep(1000);
                   			}
              			} catch (Exception e) { }
		  	}
	   	} ).start();
   	}
	
	public void startReceiveHandraiseAudio(){
		AudioVideoPanel.getController().startReceiveHandraiseAudio();	
	}
	
	public void stopReceiveHandraiseAudio(){
		AudioVideoPanel.getController().stopReceiveHandraiseAudio();
	}

	public void startReceivePresentationAudio(){
                AudioVideoPanel.getController().startReceivePresentationAudio();
        }

        public void stopReceivePresentationAudio(){
                AudioVideoPanel.getController().stopReceivePresentationAudio();
        }

   	
	public void startReceiveVideo(){
     		if (!VideoReceive.getVideoReceiveController().initialize()) {
               		System.out.println("Failed to initialize the sessions.");
      		}
                (new Thread(){
    			public void run(){
            			try {
                			while(!VideoReceive.getVideoReceiveController().isDone()) {
						
                    				Thread.sleep(1000);
                   			}
         			} catch (Exception e) { }
         		}
       		} ).start();   
   	}
   
}	   		
