package org.bss.brihaspatisync.tools.audio_video;

/**
 * AVTransmitReceiveHandler.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */


import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.tools.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.tools.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.tools.audio_video.transmitter.AVTransmit3;
	
/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>      
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class AVTransmitReceiveHandler extends Thread {

	private Log log=Log.getController();
	
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
   		 	log.setLog("start audio/vedio receive");
   		 	startReceiveAudio();
   		 	startReceiveVideo();
   		 	//AVFrame.getController();
   		 }else{
   		  	log.setLog("start audio/video transmit");
   		  	String result = AVTransmit3.getController().start();
       	    		if (result != null) {
        	   		log.setLog("Error : " + result);
       			}
   		 	//new AudioVideoPlayer();
   		 }
   		 AudioVideoPanel.getController();
   		
	}
   
   	public void startReceiveAudio(){
     		if (!AudioReceive.getAudioReceiveController().initialize()) {
        		log.setLog("Failed to initialize the sessions.");
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
   
   	public void startReceiveVideo(){
     		if (!VideoReceive.getVideoReceiveController().initialize()) {
               		log.setLog("Failed to initialize the sessions.");
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
