package org.bss.brihaspatisync.tools.audio;

/**
 * AudioCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioCapture implements Runnable {

	private boolean flag=false;
        private int bufferSize=0;	
	private Thread runner=null;	
	private byte audio_data[]=null;
   	private Mixer currentMixer=null;
	private TargetDataLine targetDataLine=null;
	private AudioFormat audioFormat=ClientObject.getController().getAudioFormat();
	private java.util.LinkedList<byte[]> audioVector=new java.util.LinkedList<byte[]>();

	/**
 	 * Open and start input Line (TargetDataLine) from selected Mixer.
 	 */  
    	public void getTargetLine() {
		try {
			if(targetDataLine ==null){
				targetDataLine =ClientObject.getController().getTargetLine();
				System.out.println("opening targetDataLine.");
			} else System.out.println("targetDataLine could not initialized.");
		} catch(Exception e){System.out.println("Error in open targetdataline "+e.getMessage());}
    	}

	/**
 	 * Stop TargetDataLine
 	 */  
	public void stopCapture(){
		if(runner !=null){
			runner.stop();
			runner=null;
			targetDataLine=null;
			flag=false;
			System.out.println("stopping audio capture successfull");
		}
        }
	
	protected void start(){
		if(runner ==null){
	                bufferSize = ((int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize()))/4;
                	getTargetLine();
			runner=new Thread(this);
			flag=true;
			runner.start();
			System.out.println("capture Audio start successfully .");
		}
		
        }
	
	protected void setflag(boolean flag){
		if((ClientObject.getController().getUserRole()).equals("instructor")) {
			if(flag)
        	       		start();
	                
		}else {
			if(flag){
				start();
			}else { 
				stopCapture();
			}
		}
	}

	/**
 	 * Local thread for record audio from microphone and save in file named as filename variable.
 	 */  
	public void run(){ 
                try {
			while(flag){	
			        audio_data = new byte[bufferSize];
                               	int cnt = targetDataLine.read(audio_data,0,audio_data.length);
				audioVector.addLast(audio_data);
			}
                }catch(Exception e){System.out.println("Error in capture Audio"+e.getCause());}
        }
		
	protected byte [] getAudioData(){
		if(audioVector.size()>0){
			if(audioVector.size()>10){
                                for(int removepacket=0;removepacket<5;removepacket++)
                                        audioVector.remove(0);
                        }
			byte[] data=audioVector.get(0);
			audioVector.remove(0);
			return data;
		}
		return null;
	}
}
