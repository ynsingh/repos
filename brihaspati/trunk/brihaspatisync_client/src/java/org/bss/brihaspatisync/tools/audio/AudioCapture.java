package org.bss.brihaspatisync.tools.audio;

/**
 * AudioCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import java.net.URLEncoder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.io.File;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioCapture {

	private boolean flag=false;
        private int bufferSize=16000;	
   	private Mixer currentMixer=null;
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
    	private AudioFileFormat.Type fileType = null;
    	private DataLine.Info dataLineInfo = null; //new DataLine.Info(TargetDataLine.class, audioFormat);

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
			if(currentMixer==null){
				currentMixer = getMixer();
			}
    			if(currentMixer != null)
            			targetDataLine =(TargetDataLine)currentMixer.getLine(dataLineInfo);
            		else
            			System.out.println("Mixer not found!!");
			audioFormat = getAudioFormat();
			if(!targetDataLine.isOpen()){
            			targetDataLine.open(audioFormat);
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
		System.out.println("stopping audio capture successfull");
        }
	
	protected void start(){
                getTargetLine();
                bufferSize = (int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize());
        }
	
	protected void setflag(boolean f){
		flag=f;	
		if(f)
			start();
	}


	/**
 	 * Local thread for record audio from microphone and save in file named as filename variable.
 	 */  
	public java.io.ByteArrayOutputStream startCapture(){ 
                try {
			byte tempBuffer[] = new byte[bufferSize*10];
                        try {
				java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
				if(flag){	
                                	int cnt = targetDataLine.read(tempBuffer,0,tempBuffer.length);
                                	AudioInputStream ais = new AudioInputStream(new java.io.ByteArrayInputStream(tempBuffer),audioFormat, tempBuffer.length / getAudioFormat().getFrameSize());
                                	AudioSystem.write(ais,AudioFileFormat.Type.WAVE, os);
				}
				return os;	
                        } catch(Exception e){System.out.println("Error in capture Audio"+e.getCause());}
                }catch(Exception e){System.out.println("Error in capture Audio"+e.getCause());}
		return null;
        }

	/**
 	 * Define audio format
 	 */  
	private AudioFormat getAudioFormat(){
		    float sampleRate = 8000;	//8000,11025,16000,22050,44100
		    int sampleSizeInBits = 16;	//8,16
		    int channels = 1;		//1,2
		    boolean signed = true;	//true,false
		    boolean bigEndian =false;	//true,false
		    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
 	}
	
	/**
	private AudioFormat getAudioFormat(){
                AudioFormat.Encoding encoding = new AudioFormat.Encoding("PCM_SIGNED");
                float sampleRate = 8000;        //8000,11025,16000,22050,44100
                int sampleSizeInBits = 8;       //8,16
                int channels = 1;               //1,2
                boolean signedStat = true;      //true,false
                boolean bigEndian =false;       //true,false
                int frameSize = 2000; // number of octets in single frame. 
                int frameRate= 4; //number of frames played per second.
                return new AudioFormat(encoding, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);
        }*/
	
}
