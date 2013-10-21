package org.bss.brihaspatisync.tools.audio;

/**
 * AudioClient.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioClient implements Runnable {

	private Thread runner=null;
	private boolean flag=false;
	private boolean audiostartstopFlag=false;
	private static AudioClient audio=null;
	private AudioCapture au_cap=new AudioCapture();	
	private UtilObject utilobject=UtilObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private org.xiph.speex.SpeexEncoder encoder=org.bss.brihaspatisync.util.AudioUtilObject.getSpeexEncoder();
	
	/**
 	 * Controller for the class.
 	 */
	public static AudioClient getController(){
		if(audio==null)
			audio=new AudioClient();
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
			StatusPanel.getController().setaudioClient("yes");
			utilobject.addType("Audio_Data");
			System.out.println("AudioClient start successfully !!");
		}
       }

       /**
        * Stop Thread.
        */
	public void stopThread() {
        	if (runner != null) {
			flag=false;
            		runner = null;	
			StatusPanel.getController().setaudioClient("no");
			postAudio(false);	
			utilobject.removeType("Audio_Data");	
			System.out.println("AudioClient stop successfully !!");
      		}
   	}

	/**
	 * This flag is used to AudioCapture start/stop thread .
	 */   
	public void postAudio(boolean startstopflag) {
		audiostartstopFlag=startstopflag;
		au_cap.setflag(startstopflag);	
	}

	/**
 	 * Transmit audioInputStream to reflector by using HTTP post method.
 	 */
		
  	public void run() {
		while(flag && ThreadController.getThreadFlag()) {
			try {
				if(ThreadController.getReflectorStatusThreadFlag()) {
					/****   send audio data to reflector ****/
					if(audiostartstopFlag) {
						java.util.LinkedList audio_buffer=au_cap.getAudioData();
						int size=audio_buffer.size();
						byte[] audiodata=null;
						if(size>10) {
							int currentOffset = 0;
							for(int i=0;i<size;i++) {
								byte[] audioBytes=(byte[])audio_buffer.remove();
								byte[] currentArray=getEncoder(audioBytes);
								if(currentArray != null) {
									if( audiodata == null )
										audiodata=new byte[((currentArray.length)*size)];
                                	        			System.arraycopy(currentArray, 0,audiodata, currentOffset,currentArray.length);
                                        				currentOffset += currentArray.length;
                                				}else
									System.out.println("encode problem ========>>>>>>>>>>>>>");			
							}
						}
						if(audiodata != null) {
                        		        	LinkedList send_queue=utilobject.getSendQueue("Audio_Data");
	                			        send_queue.addLast(audiodata);
						} 
					}
					LinkedList audio_rechive_data=utilobject.getReceiveQueue("Audio_Data");
                                        if(audio_rechive_data.size()>0) 
						AudioPlayer.getController().startThread();
					StatusPanel.getController().setaudioClient("yes");
				} else
					StatusPanel.getController().setaudioClient("no");
				runner.yield();
				runner.sleep(1000);
			} catch(Exception epe) { 
				StatusPanel.getController().setaudioClient("no"); 	
				System.out.println("Exception in AudioClient class  "+epe.getMessage()); 
			}
        	}
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
