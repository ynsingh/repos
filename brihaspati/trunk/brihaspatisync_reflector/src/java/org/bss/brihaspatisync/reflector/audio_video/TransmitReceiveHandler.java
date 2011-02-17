package org.bss.brihaspatisync.reflector.audio_video;

/**
 * TransmitReceiveHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011
 */

import javax.media.protocol.DataSource;

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.StudentAudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.PresentationAudioReceive;
import org.bss.brihaspatisync.reflector.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.VideoTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.AudioTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.StudentAudioTransmit;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.PresentationAudioTransmit;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitReceiveHandler {

  	private static TransmitReceiveHandler trHandler=null;
	private Thread stud_audio=null;
	private Thread pres_audio=null;
	private MaintainLog log=MaintainLog.getController();
	private StudentAudioTransmit student_audio_tranmit=null;
	private PresentationAudioTransmit presentation_audio_tranmit=null;
	private StudentAudioReceive student_audio_receiver=null;
	private PresentationAudioReceive presentation_audio_receiver=null;
  	
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
		if(student_audio_receiver==null)
			student_audio_receiver=new StudentAudioReceive();	
                if (!student_audio_receiver.initialize()) {
                        System.out.println("Failed to initialize the sessions for handraise audio receive.");
                }

                (stud_audio=new Thread(){
                        public void run(){

                try {
                        while (!student_audio_receiver.isDone()) {
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
		
		try {
			student_audio_receiver.close();
			stud_audio.interrupt();
			stud_audio=null;
			student_audio_receiver=null;
		} catch(Exception e){ System.out.println("Failed to stopStudentReceiveAudio handraise audio receive ."); }
		
	}

	/**
         * This is separate thread to start receive presentation audio from student. 
         */
	public void startPresentationReceiveAudio(){
                if(presentation_audio_receiver==null)
                        presentation_audio_receiver=new PresentationAudioReceive();
                if (!presentation_audio_receiver.initialize()) {
                        System.out.println("Failed to initialize the sessions for presentation audio receive.");
                }

                (pres_audio=new Thread(){
                        public void run(){

                try {
                        while (!presentation_audio_receiver.isDone()) {
                                Thread.sleep(1000);
                        }
                } catch (Exception e) { }


                        }
                } ).start();

        }

	/**
         * Close session for the receive audio from student.
         */	
	 public void stopPresentationReceiveAudio(){

                try {
                        presentation_audio_receiver.close();
                        pres_audio.interrupt();
                        pres_audio=null;
                        presentation_audio_receiver=null;
                } catch(Exception e){ System.out.println("Failed to stopPresentationReceiveAudio handraise audio receive ."); }

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
	
	public void startStudentAudioTransmit(){
		try{
       	 		if(student_audio_tranmit==null) {
         			student_audio_tranmit=new StudentAudioTransmit();
         			student_audio_tranmit.start();
         		}
		}catch(Exception e){System.out.println("Error in start Student audio Transmission.");}

	}

	public void stopStudentAudioTransmit(){
		try {
			if(student_audio_tranmit != null){
				student_audio_tranmit.stop();
                      		student_audio_tranmit=null;
              		}
		}catch(Exception e){System.out.println("Error in stop student audio transmission.");}

	}

	public void startPresentationAudioTransmit(){
                try{
                        if(presentation_audio_tranmit==null) {
                                presentation_audio_tranmit=new PresentationAudioTransmit();
                                presentation_audio_tranmit.start();
                        }
                }catch(Exception e){System.out.println("Error in start presentation audio Transmission.");}

        }

        public void stopPresentationAudioTransmit(){
                try {
                        if(presentation_audio_tranmit != null){
                                presentation_audio_tranmit.stop();
                                presentation_audio_tranmit=null;
                        }
                }catch(Exception e){System.out.println("Error in stop presentation audio transmission.");}
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
		student_audio_tranmit.createTransmitter(ip.trim());
                System.out.println(ip+" : is added to transmit audio video");
        }

        /**
         * Start predefine sendStream thread to start transmit handraise audio.
         */
	public void startHRSendStream(){
                        student_audio_tranmit.streamTransmitterStart();
                        System.out.println("handraise audio send stream start");
        }
	
        /**
         * Stop predefine sendStream thread to stop transmit handraise audio.
         */
	public void stopHRSendStream(){
		student_audio_tranmit.streamTransmitterStop();
              	System.out.println("handraise audio stream transmitter stop");
        }

	 /**
          * When new client arrive it will added to presentation audio transmitter target
          */
	public void addTargetToPresentationTransmitter(String ip){
                presentation_audio_tranmit.createTransmitter(ip.trim());
                System.out.println(ip+" : is added to transmit presentation audio video");
        }

	 /**
          * Start predefine sendStream thread to start transmit presentation audio.
          */
	public void startPresentationSendStream(){
             	presentation_audio_tranmit.streamTransmitterStart();
             	System.out.println("presentation audio send stream start");
        }

	/**
         * Stop predefine sendStream thread to stop transmit presentation audio.
         */
	public void stopPresentationSendStream(){
             	presentation_audio_tranmit.streamTransmitterStop();
             	System.out.println("presentation audio stream transmitter stop");
        }


	public DataSource getHRDataSource(){
		return student_audio_receiver.getDataSource();
	}
	public DataSource getPresentationDataSource(){
                return presentation_audio_receiver.getDataSource();
        }
}	   		
