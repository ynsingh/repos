package org.bss.brihaspatisync.tools.audio_video;

import java.util.*;
import java.io.*;
import org.bss.brihaspatisync.util.ClientObject;
import java.awt.Cursor;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.tools.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.tools.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.tools.audio_video.transmitter.AVTransmit3;

public class AVTransmitReceiveHandler extends Thread {

  	private static AVTransmitReceiveHandler trHandler=null;
	//private static Vector ipTabel=new Vector();
	
	private Log log=Log.getController();


  	/** Getting the handler of the main controller class */
	public static AVTransmitReceiveHandler getController(){
		if(trHandler==null)
               		trHandler=new AVTransmitReceiveHandler();
	       	return trHandler;
   	}
   
   	/**Constructor for the main controller */
   	public AVTransmitReceiveHandler() {
   		 String role=ClientObject.getController().getUserRole();
   		 log.setLog("role================="+role);
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
