package org.bss.brihaspatisync.reflector.audio_video;

/**
 * TransmitReceiveHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011
 */

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.StudentAudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.VideoTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.AudioTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.StudentAudioTransmit;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitReceiveHandler {

  	private static TransmitReceiveHandler trHandler=null;
	private Thread stud_audio=null;
	private MaintainLog log=MaintainLog.getController();

  	/** Controller for this class */
	public static TransmitReceiveHandler getControllerofHandler(){
		if(trHandler==null)
               		trHandler=new TransmitReceiveHandler();
	       	return trHandler;
   	}
   
   	/**Constructor*/
   	public TransmitReceiveHandler() {  }
   
	/**
 	 * This is separate thread to start receive audio from instructor.
 	 */
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

	/**
 	 * This is separate thread to start receive audio from student. 
 	 */ 

	public void startStudentReceiveAudio(){
                if (!StudentAudioReceive.getAudioReceiveController().initialize()) {
                        System.out.println("Failed to initialize the sessions for handraise audio receive.");
                }

                (stud_audio=new Thread(){
                        public void run(){

                try {
                        while (!StudentAudioReceive.getAudioReceiveController().isDone()) {
                                Thread.sleep(1000);
                        }
                } catch (Exception e) { }


                        }
                } ).start();

        }

	/**
	 * Close session for the receive audio from student.
	 */ 	
	public void stopStudentReceiveAudio(){
		StudentAudioReceive.getAudioReceiveController().close();
		stud_audio.interrupt();
		stud_audio=null;
	}

   
	/**
         * This is separate thread to start receive video from student.
         */
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

	/**
         * Start Audio video transmission to other peer reflector or clients which is received from instructor.
         */
        public void startAVTransmit(){
                VideoTransmit.getVideoTransmitController().start();
                AudioTransmit.getAudioTransmitController().start();
        }

	
   	/**
	 * Stop Audio video transmission to other peer reflector or clients which is received from instructor. 
	 */
	public void stopAVTransmit(){
		AudioTransmit.getAudioTransmitController().stop();
                VideoTransmit.getVideoTransmitController().stop();
	}	   		
	
	/**
 	 * When new client arrive it will added to transmitter target
	 */
	public void addTargetToTransmitter(String ip){
		AudioTransmit.getAudioTransmitController().createTransmitter(ip.trim());
                VideoTransmit.getVideoTransmitController().createTransmitter(ip.trim());
		log.setString(ip+" : is added to transmit audio video");
	}
	
	/**
 	 * Start predefine sendStream thread to start transmit instructor's audio video. 
 	 */ 	 
	public void startSendStream(){
        	AudioTransmit.getAudioTransmitController().streamTransmitterStart();
	        VideoTransmit.getVideoTransmitController().streamTransmitterStart();
		System.out.println("Start send stream");
	}
	
	/**
         * Stop predefine sendStream thread to stop transmit instructor's audio video.
         */
	public void stopSendStream(){
                AudioTransmit.getAudioTransmitController().streamTransmitterStop();
                VideoTransmit.getVideoTransmitController().streamTransmitterStop();
		System.out.println("Stop send stream");
        }

	/**
         * When new client arrive it will added to handraise audio transmitter target
         */

	public void addTargetToHRTransmitter(String ip){
                StudentAudioTransmit.getAudioTransmitController().createTransmitter(ip.trim());
                System.out.println(ip+" : is added to transmit audio video");
        }

        /**
         * Start predefine sendStream thread to start transmit handraise audio.
         */
	public void startHRSendStream(){
                        StudentAudioTransmit.getAudioTransmitController().streamTransmitterStart();
                        System.out.println("handraise audio send stream start");
        }
	
        /**
         * Stop predefine sendStream thread to stop transmit handraise audio.
         */
	public void stopHRSendStream(){
                        StudentAudioTransmit.getAudioTransmitController().streamTransmitterStop();
                        System.out.println("handraise audio stream transmitter stop");
        }
}	   		
