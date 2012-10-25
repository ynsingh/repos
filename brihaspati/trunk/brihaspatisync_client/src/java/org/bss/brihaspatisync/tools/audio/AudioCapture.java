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
	private boolean flag1=false;
        private int bufferSize=0;	
	private Thread runner=null;	
	private byte audio_data[]=null;
   	private Mixer currentMixer=null;
	private TargetDataLine targetDataLine;
	private AudioFormat audioFormat=ClientObject.getController().getAudioFormat();
	private java.util.LinkedList<byte[]> audioVector=new java.util.LinkedList<byte[]>();
    	private DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
   	/**
 	 * Select a mixer from audio system which support audio format
 	 */  
	private Mixer getMixer(){
		Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
   		System.out.println("Available mixers:");
       		for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
         		System.out.println(mixerInfo[cnt].getName());
           		Mixer currentMixer = AudioSystem.getMixer(mixerInfo[cnt]);
			if( currentMixer.isLineSupported(dataLineInfo) ) {
				System.out.println("mixer name: " + mixerInfo[cnt].getName() + " index:" + cnt);
				return currentMixer;
			}
        	}
        	return null;
	}

	/**
 	 * Open and start input Line (TargetDataLine) from selected Mixer.
 	 */  
    	public TargetDataLine getTargetLine() {
		try{
			if(currentMixer==null)
				currentMixer = getMixer();
			if(currentMixer != null)
            			targetDataLine =(TargetDataLine)currentMixer.getLine(dataLineInfo);
            		else
            			System.out.println("Mixer not found!!");
			if(!targetDataLine.isOpen()){
            			targetDataLine.open(audioFormat, bufferSize);
				System.out.println("opening targetDataLine !!");
			}
            		targetDataLine.start();
		}catch(Exception e){System.out.println("Error in open targetdataline "+e.getMessage());}
		return targetDataLine;
    	}

	/**
 	 * Stop TargetDataLine
 	 */  
	public void stopCapture(){
		targetDataLine.flush();
		targetDataLine.stop();
		targetDataLine.drain();
	        targetDataLine.close();
		runner.stop();
		System.out.println("stopping audio capture successfull");
        }
	
	protected void start(){
		if(!flag1){
	                bufferSize = ((int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize()))/4;
                	getTargetLine();
			flag1=true;
			runner=new Thread(this);
			runner.start();
		}
		
        }
	
	protected void setflag(boolean f){
		flag=f;	
		if(f){
			start();
		}
		else {
			stopCapture();
			flag1=false;
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
