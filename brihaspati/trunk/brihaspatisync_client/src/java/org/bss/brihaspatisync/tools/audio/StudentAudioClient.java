package org.bss.brihaspatisync.tools.audio;

/**
 * StudentAudioClient.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2015 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar Pal </a>
 */

public class StudentAudioClient implements Runnable {

	private Thread runner=null;
	private boolean flag=false;
	private boolean audiostartstopFlag=false;
	private static StudentAudioClient audio=null;
	private AudioCapture au_cap=new AudioCapture();	
	private UtilObject utilobject=UtilObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private org.xiph.speex.SpeexEncoder encoder=org.bss.brihaspatisync.util.AudioUtilObject.getSpeexEncoder();
	
	/**
 	 * Controller for the class.
 	 */
	public static StudentAudioClient getController() {
		if(audio==null)
			audio=new StudentAudioClient();
		return audio;
	}

	/**
 	 * Start Thread
 	 */
	public void startThread(){
        	if (runner == null) {
			flag=true;
            		runner = new Thread(this);
            		runner.start();
			StatusPanel.getController().setstudentaudioClient("yes");
			utilobject.addType("Student_Audio_Data");
			System.out.println("StudentAudioClient start successfully !!!!!!");
		}
       }

       /**
        * Stop Thread.
        */
	public void stopThread() {
        	if (runner != null) {
			flag=false;
            		runner = null;	
			StatusPanel.getController().setstudentaudioClient("no");
			postAudio(false);	
			utilobject.removeType("Student_Audio_Data");
			utilobject.removeReceiveQueue("Audio_Data");
                        utilobject.removeSendQueue("Student_Audio_Data");	
			System.out.println("StudentAudioClient stop successfully !!!!!!");
      		}
   	}

	/**
	 * This flag is used to AudioCapture start/stop thread .
	 */   
	public void postAudio(boolean startstopflag) {
		audiostartstopFlag=startstopflag;
		au_cap.setflag(startstopflag);	
	}
	/*	
	 public boolean getpostAudio(){
                return audiostartstopFlag;
        }
	*/
	/**
 	 * Transmit audioInputStream to reflector by using HTTP post method.
 	 */
		
  	public void run() {
		while(flag && ThreadController.getThreadFlag()) {
			try {
				if(ThreadController.getReflectorStatusThreadFlag()) {
					try {
					/****   send audio data to reflector ****/
					if(audiostartstopFlag) {
						java.util.LinkedList audio_buffer=au_cap.getAudioData();
						int size=audio_buffer.size();
						byte[] audiodata=null;
						int currentOffset = 0;
						if(size>10) {
							for(int i=0;i<size;i++) {
								byte[] audioBytes=(byte[])audio_buffer.remove();
								byte[] currentArray=getEncoder(audioBytes);
								if(currentArray != null) {
									if( audiodata == null )
										audiodata=new byte[((currentArray.length)*size)];
                                	        			System.arraycopy(currentArray, 0,audiodata, currentOffset,currentArray.length);
                                        				currentOffset += currentArray.length;
                                					}
								}
							}
							if(audiodata != null) {
                        		        	LinkedList send_queue=utilobject.getSendQueue("Student_Audio_Data");
	                			        send_queue.addLast(audiodata);
							} 
					}else{
						utilobject.removeSendQueue("Student_Audio_Data");	
					}
					
					LinkedList audio_rechive_data=utilobject.getReceiveQueue("Audio_Data");

                                        if(audio_rechive_data.size()>0) 
						AudioPlayer.getController().startThread();
					else
						utilobject.removeReceiveQueue("Audio_Data");
					StatusPanel.getController().setstudentaudioClient("yes");
					} catch(Exception e) { }
				} else
					StatusPanel.getController().setstudentaudioClient("no");
				runner.yield();
				//runner.sleep(100);
			} catch(Exception epe) {StatusPanel.getController().setstudentaudioClient("no"); 	}
        	}
		try {
                        stopThread();
			StatusPanel.getController().setstudentaudioClient("no");
                }catch(Exception e){}
	}
	
	/**
	 * This method is used to encode raw audio data.  
	 */ 
	private byte [] getEncoder(byte [] audiodata) {
		byte[] encoded_data= null;
		try {
			if(encoder.processData(audiodata, 0, audiodata.length)) {
				encoded_data= new byte[encoder.getProcessedDataByteSize()];
				encoder.getProcessedData(encoded_data, 0);
			}
		} catch(Exception e) { System.out.println("Exception in AudioClient class in getEncoder method ! "+e.getMessage());}
		return encoded_data;
	} 
}
