package org.bss.brihaspatisync.tools.audio;

/**
 * AudioCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, 2013 ETRG,IIT Kanpur.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import org.bss.brihaspatisync.util.AudioUtilObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Modified on 04-04-2013. JSpeex codec integration to encode audio data.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioCapture implements Runnable {
	
	private int bufferSize=0;
	private boolean flag=false;
	private Thread runner=null;	
	private TargetDataLine targetDataLine=null;
	private AudioFormat audioFormat=AudioUtilObject.getAudioFormat();
	private java.util.LinkedList<byte[]> audioVector=new java.util.LinkedList<byte[]>();
	private org.xiph.speex.SpeexEncoder encoder=org.bss.brihaspatisync.util.AudioUtilObject.getSpeexEncoder();

	
	/**
 	 * Open and start input Line (TargetDataLine) from selected Mixer.
 	 */  
    	public void getTargetLine() {
		try {
			if(targetDataLine ==null){
				targetDataLine =AudioUtilObject.getTargetLine();
			} else System.out.println("targetDataLine could not initialized.");
		} catch(Exception e){System.out.println("Error in open targetdataline "+e.getMessage());}
    	}

	/**
 	 * Stop TargetDataLine
 	 */  
	public void stopCapture() {
		if(runner !=null){
			runner.stop();
			runner=null;
			targetDataLine=null;
			flag=false;
			System.out.println("stopping audio capture successfull");
		}
        }
	
	private void startCapture(){
		if(runner ==null) {
			bufferSize=2 *  encoder.getChannels() * encoder.getFrameSize();
                	getTargetLine();
			runner=new Thread(this);
			flag=true;
			runner.start();
			System.out.println("capture Audio start successfully .");
		}
		
        }
	
	protected void setflag(boolean flag1){
		if((org.bss.brihaspatisync.util.ClientObject.getUserRole()).equals("instructor")) {
			if(flag1)
        	       		startCapture();
			else 
                                stopCapture();
		} else {
			if(flag1) 
				startCapture();
			else  
				stopCapture();
		}
	}

	/**
 	 * Local thread for record audio from microphone and save in file named as filename variable.
 	 */  
	public void run() { 
                try {
			while(flag && org.bss.brihaspatisync.util.ThreadController.getThreadFlag()) {	
				try {
					if(targetDataLine != null) {
						byte audio_data[] = new byte[bufferSize];
                	               		int cnt = targetDataLine.read(audio_data,0,audio_data.length);
						audioVector.addLast(audio_data);
					} else 
                         	       		targetDataLine = AudioUtilObject.getTargetLine();
					runner.yield();
				} catch(Exception ex){ System.out.println("Eception in capture Audio class "+ex.getCause()); }
			}
			stopCapture();
                } catch(Exception e){ System.out.println("Eception in capture Audio class "+e.getCause()); }
        }
		
	protected synchronized java.util.LinkedList  getAudioData() {
		return audioVector;
	}
}
