package org.bss.brihaspatisync.tools.audio;

/**
 * AudioClient.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.util.ClientObject;
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
	private static AudioClient audio=null;
	private AudioCapture au_cap=new AudioCapture();	
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();

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
			org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().addType("Audio_Data");
			System.out.println("AudioClient start successfully !!");
		}
       }

       /**
        * Stop Thread.
        */
	public void stopThread() {
        	if (runner != null) {
			flag=false;
            		runner.stop();
            		runner = null;	
			StatusPanel.getController().setaudioClient("no");
			org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().removeType("Audio_Data");	
			System.out.println("AudioClient stop successfully !!");
      		}
   	}

	public void postAudio(boolean startstopflag) {
		au_cap.setflag(startstopflag);	
	}

	/**
 	 *Transmit audioInputStream to reflector by using HTTP post method.
 	 */
		
  	public void run() {
		while(flag && ThreadController.getController().getThreadFlag()) {
			try {
				if(ThreadController.getController().getReflectorStatusThreadFlag()) {
					byte [] audiodata=au_cap.getAudioData();
					if(audiodata != null) {
						LinkedList send_queue=UtilObject.getController().getSendQueue("Audio_Data");
                	                        send_queue.addLast(audiodata);
					}else {
						LinkedList send_queue=UtilObject.getController().getSendQueue("Audio_Data");
                                        	send_queue.addLast(null);	
						runner.sleep(20);
					}
                        	        LinkedList audio_rechive_data=UtilObject.getController().getQueue("Audio_Data");
                                	if(audio_rechive_data.size()>0) {
                				byte[] audioBytes=(byte[])audio_rechive_data.get(0);
	                                        audio_rechive_data.remove(0);			
        	        	              	if((audioBytes.length) > 3000) {
                	        	              	AudioPlayer.getController().putAudioStream(audioBytes);
						}
					}
					StatusPanel.getController().setaudioClient("yes");
					networkHandler();
				}else
					StatusPanel.getController().setaudioClient("no");
				runner.yield();
			}catch(Exception epe){StatusPanel.getController().setaudioClient("no"); }
        	}
	}
	
	/**
 	 * This method is used to netwrok is very slow 
 	 * then remove data from sending queue 
 	 */
 
	private void networkHandler() {
		try {
			LinkedList sendqueue=UtilObject.getController().getSendQueue("Audio_Data");
			if(sendqueue.size()>20) {
	                        for(int i=0;i<5;i++) {
					sendqueue.remove(0);
				}
			}
		}catch(Exception epe){System.out.println("Error in networkHandler class "); }
	}	

}
