package org.bss.brihaspatisync.reflector.audio_video;


/*
 * TransmitHandlerThread.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;

import org.bss.brihaspatisync.reflector.network.util.RuntimeObject;
	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class TransmitHandlerThread implements Runnable {
	
	private Thread audio_video=null;
	
	private Vector ipVectorforClient =new Vector();
	
  	private static TransmitHandlerThread trHandler=null;

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
		int j=0;
                while(!(audio_video.isInterrupted())){
                        try {
				Vector courseid =RuntimeObject.getController().getCourseID();
				for(int k=0;k<courseid.size();k++) {
	                      		Vector VectorforClient =RuntimeObject.getController().getCourseid_IP(courseid.get(k).toString());
					for(int i=0;i<VectorforClient.size();i++){
						String ip=VectorforClient.get(i).toString();
						if(!ipVectorforClient.contains(ip)) {
							ipVectorforClient.add(ip);
							//System.out.println("your transmit IP is : "+ip+"\n\n\n\n\n\n\n\n");
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
							//System.out.println("new address is added to transmit audio/video ==>"+ip);
        	                                	TransmitReceiveHandler.getControllerofHandler().startSendStream();
						}
					}
				}
				audio_video.yield();
                                audio_video.sleep(1000);
			}catch(Exception e) {
                               // System.out.println("Error in TransmitHandlerThread.java  "+e.getMessage());
                        }
                }
        }
}	   		
