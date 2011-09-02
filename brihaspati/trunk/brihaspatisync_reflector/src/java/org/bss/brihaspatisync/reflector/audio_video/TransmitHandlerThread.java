package org.bss.brihaspatisync.reflector.audio_video;

/**
 * TransmitHandlerThread.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;

//import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.audio_video.transmitter.StudentAudioTransmit;	
import org.bss.brihaspatisync.reflector.audio_video.transmitter.PresentationAudioTransmit;	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitHandlerThread implements Runnable {
	
	private Thread audio_video=null;
	private Vector ipVectorforClient =new Vector(); //for instructor audio/video
	private Vector ipVectorforHR =new Vector();     //for handraise audio
	private Vector ipVectorforPres =new Vector();     //for presentation audio
	private boolean hr_flag=false;
	private boolean pres_flag=false;
	
  	private static TransmitHandlerThread trHandler=null;
	
	//private MaintainLog log=MaintainLog.getController();

	private int j=0; 
	private int m=0; 
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
			System.out.println("Start Audio Video Thread Handeler : ");
		}catch(Exception e){
			System.out.println("Error in Start Audio Video Thread Handeler : ");
		}
        }

        /**  Stop TransmitHandler Thread  */
        public void stop() {
                if(audio_video != null){
                        audio_video.stop();
                        audio_video=null;
                }
		System.out.println("Stop Audio Video Thread Handeler : ");
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
                                                                		Thread.sleep(500);
                                                        		}catch(Exception e){System.out.println("Error on start Handraise receive audio "+e.getMessage());}
                                                        		try{
										TransmitReceiveHandler.getControllerofHandler().startStudentAudioTransmit();
                                                        			l++;
                                                        		}catch(Exception ex){System.out.println("Error in wait to start handraise audio");}
								}
								if(l>0){
                                                                	try {
										TransmitReceiveHandler.getControllerofHandler().stopHRSendStream();
                                                                        	Thread.sleep(500);
                                                        		}catch(Exception e){System.out.println("Error on stop Handraise send stream "+e.getMessage());}
								}
								TransmitReceiveHandler.getControllerofHandler().addTargetToHRTransmitter(ip);
        	                                                TransmitReceiveHandler.getControllerofHandler().startHRSendStream();
								hr_flag=true;
							}
						}else {
							if(hr_flag){
								try {
									TransmitReceiveHandler.getControllerofHandler().stopStudentAudioTransmit();
									TransmitReceiveHandler.getControllerofHandler().stopStudentReceiveAudio();
									ipVectorforHR.clear();
									l=0;
									hr_flag=false;
								} catch(Exception e){ System.out.println("Error on stop Handraise receiver and transmit thread "+e.getMessage());  }
							}
						}// End of Handraise Audio

						if(RuntimeDataObject.getController().getPresentationFlag()){
                                                        if(!ipVectorforPres.contains(ip)){
                                                                ipVectorforPres.add(ip);
                                                                if(m==0){
                                                                        try {
                                                                                TransmitReceiveHandler.getControllerofHandler().startPresentationReceiveAudio();
                                                                                Thread.sleep(500);
                                                                        }catch(Exception e){System.out.println("Error on start presentation receive audio "+e.getMessage());}
                                                                        try{
                                                                                TransmitReceiveHandler.getControllerofHandler().startPresentationAudioTransmit();
                                                                                m++;
                                                                        }catch(Exception ex){System.out.println("Error in wait to start presentation audio");}
                                                                }
                                                                if(m>0){
                                                                        try {
                                                                                TransmitReceiveHandler.getControllerofHandler().stopPresentationSendStream();
                                                                                Thread.sleep(500);
                                                                        }catch(Exception e){System.out.println("Error on stop Handraise send stream "+e.getMessage());}
                                                                }
                                                                TransmitReceiveHandler.getControllerofHandler().addTargetToPresentationTransmitter(ip);
                                                                TransmitReceiveHandler.getControllerofHandler().startPresentationSendStream();
                                                                pres_flag=true;
                                                        }
						}else {
                                                        if(pres_flag){
                                                                try {
                                                                        TransmitReceiveHandler.getControllerofHandler().stopPresentationAudioTransmit();
                                                                        TransmitReceiveHandler.getControllerofHandler().stopPresentationReceiveAudio();
                                                                        ipVectorforPres.clear();
                                                                        m=0;
                                                                        pres_flag=false;
                                                                } catch(Exception e){ System.out.println("Error on stop presentation receiver and transmit thread "+e.getMessage());  }
                                                        }
                                                }//end of presentation audio

					}
				}
				audio_video.yield();
                                audio_video.sleep(1000);
			}catch(Exception e) {
                               System.out.println("Error in TransmitHandlerThread.java  "+e.getMessage());
                        }
                }
        }
}	   		
