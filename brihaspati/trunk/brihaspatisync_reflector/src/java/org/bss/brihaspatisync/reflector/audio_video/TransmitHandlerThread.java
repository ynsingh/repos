package org.bss.brihaspatisync.reflector.audio_video;

/**
 * TransmitHandlerThread.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.audio_video.receiver.StudentAudioReceive;	
import org.bss.brihaspatisync.reflector.audio_video.transmitter.StudentAudioTransmit;	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitHandlerThread implements Runnable {
	
	private Thread audio_video=null;
	private Vector ipVectorforClient =new Vector(); //for instructor audio/video
	private Vector ipVectorforHR =new Vector();     //for handraise audio
	
  	private static TransmitHandlerThread trHandler=null;
	
	private MaintainLog log=MaintainLog.getController();

	private int j=0; 
	private int k=1; 


  	/** Getting the handler of the main controller class */
	public static TransmitHandlerThread getControllerofHandler(){
		if(trHandler==null)
               		trHandler=new TransmitHandlerThread();
	       	return trHandler;
   	}
   
   	/**Constructor for the main controller */
   	public TransmitHandlerThread() {  }
	
	/** Start TransmitHandler Thread. */
        public void start() {//throws IOException {
		try {
	                if (audio_video == null) {
        	                audio_video = new Thread(this);
                	        audio_video.start();
                	}
			log.setString("Start Audio Video Thread Handeler : ");
		}catch(Exception e){
			log.setString("Error in Start Audio Video Thread Handeler : ");
		}
        }

        /**  Stop TransmitHandler Thread  */
        public void stop() {
                if(audio_video != null){
                        audio_video.stop();
                        audio_video=null;
                }
		log.setString("Stop Audio Video Thread Handeler : ");
        }
  	 
	public void run(){
		int l=0;
		while(!(audio_video.isInterrupted())){
                        try {
				Vector courseid =RuntimeDataObject.getController().getCourseID();
				for(int k=0;k<courseid.size();k++) {
	                      		Vector VectorforClient =RuntimeDataObject.getController().getCourseid_IP(courseid.get(k).toString());
					for(int i=0;i<VectorforClient.size();i++){
						String ip=VectorforClient.get(i).toString();

						//Checking ip address which peer is added to vector to receive instructor's audio and video stream
						if(!ipVectorforClient.contains(ip)) {
							ipVectorforClient.add(ip);
	        	                                if(j>0) {
		        					TransmitReceiveHandler.getControllerofHandler().stopSendStream();
								try {
                        	                                	Thread.sleep(500);
                                	                	}catch(Exception e){}
                                        		}
                                        		if(j==0) {
	                                                	TransmitReceiveHandler.getControllerofHandler().startReceiveAudio();
        	                                        	TransmitReceiveHandler.getControllerofHandler().startReceiveVideo();
						        	TransmitReceiveHandler.getControllerofHandler().startAVTransmit();
                                	                	j++;
                                        		}
                                        		TransmitReceiveHandler.getControllerofHandler().addTargetToTransmitter(ip);
        	                                	TransmitReceiveHandler.getControllerofHandler().startSendStream();
						}
						/** If there is handraise flag is true, checking which ip address are added to ipVectorforHR and which are not for current state.If 
 						 *  ip address is not found in vector it will added to vector as well as add Target to transmit handraise audio for this ip(peer).
						 */ 
						if(RuntimeDataObject.getController().getHandraiseFlag()){
							if(!ipVectorforHR.contains(ip)){
								ipVectorforHR.add(ip);
								if(l==0){
                                                        		try {
                                                                		TransmitReceiveHandler.getControllerofHandler().startStudentReceiveAudio();
                                                        		}catch(Exception e){System.out.println("Error on start Handraise receive audio "+e.getMessage());}
                                                        		try{
                                                                		Thread.sleep(500);
                                                        		}catch(Exception ex){System.out.println("Error in wait to start handraise audio");}
                                                        		StudentAudioTransmit.getAudioTransmitController().start();
                                                        		l++;
								}
								if(l>0){
									TransmitReceiveHandler.getControllerofHandler().stopHRSendStream();
                                                                	try {
                                                                        	Thread.sleep(500);
                                                        		}catch(Exception e){System.out.println("Error on stop Handraise send stream "+e.getMessage());}
								}
								TransmitReceiveHandler.getControllerofHandler().addTargetToHRTransmitter(ip);
        	                                                TransmitReceiveHandler.getControllerofHandler().startHRSendStream();
							}
						}
						// End of Handraise Audio
					}
				}
				audio_video.yield();
                                audio_video.sleep(1000);
			}catch(Exception e) {
                               log.setString("Error in TransmitHandlerThread.java  "+e.getMessage());
                        }
                }
        }
}	   		
