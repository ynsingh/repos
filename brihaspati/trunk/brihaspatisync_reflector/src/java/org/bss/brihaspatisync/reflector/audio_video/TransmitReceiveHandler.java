package org.bss.brihaspatisync.reflector.audio_video;


/*
 * TransmitReceiveHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.VideoTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.AudioTransmit;
	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitReceiveHandler {

  	private static TransmitReceiveHandler trHandler=null;
	
	private MaintainLog log=MaintainLog.getController();

  	/** Getting the handler of the main controller class */
	public static TransmitReceiveHandler getControllerofHandler(){
		if(trHandler==null)
               		trHandler=new TransmitReceiveHandler();
	       	return trHandler;
   	}
   
   	/**Constructor for the main controller */
   	public TransmitReceiveHandler() {  }
   
   	public void startReceiveAudio(){
     		if (!AudioReceive.getAudioReceiveController().initialize()) {
        		log.setString("Failed to initialize the sessions.");
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
               		log.setString("Failed to initialize the sessions.");
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
	
   	
	public void stopAVTransmit(){
		AudioTransmit.getAudioTransmitController().stop();
                VideoTransmit.getVideoTransmitController().stop();
	}	   		
	   		
	public void startAVTransmit(){
		VideoTransmit.getVideoTransmitController().start();
		AudioTransmit.getAudioTransmitController().start();
	}
	
	public void addTargetToTransmitter(String ip){
		AudioTransmit.getAudioTransmitController().createTransmitter(ip.trim());
                VideoTransmit.getVideoTransmitController().createTransmitter(ip.trim());
		log.setString(ip+" : is added to transmit audio video");
	}
	public void startSendStream(){
        	AudioTransmit.getAudioTransmitController().streamTransmitterStart();
	        VideoTransmit.getVideoTransmitController().streamTransmitterStart();
		log.setString("Start send stream");

		
	}
	
	public void stopSendStream(){
                AudioTransmit.getAudioTransmitController().streamTransmitterStop();
                VideoTransmit.getVideoTransmitController().streamTransmitterStop();
		log.setString("Stop send stream");

        }
	
}	   		
