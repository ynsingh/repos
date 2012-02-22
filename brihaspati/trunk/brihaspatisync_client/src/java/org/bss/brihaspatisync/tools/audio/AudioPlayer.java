package org.bss.brihaspatisync.tools.audio;

/**
 * AudioPlayer.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import javax.sound.sampled.*;
import java.io.*;
import java.util.Vector;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Jan2012.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified run() Method.
 */

public class AudioPlayer implements Runnable {

	private static AudioPlayer ap=null;
	private Thread runner=null;
        private boolean flag=false;
	private SourceDataLine sourceDataLine;
        private AudioFormat audioFormat;
        private DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        private byte audioBytes[]=null;
	private Vector audioVector=new Vector();

	public static AudioPlayer getController(){
		if(ap==null)
			ap=new AudioPlayer();
		return ap;
	}
	
	protected void putAudioStream(AudioInputStream object){
		 if (runner == null) 
			startThread();
		audioVector.add(object);
	}
	
	public void startThread(){
		 if (runner == null) {
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
	
	}

	public void stopThread(){
		if (runner != null) {
                        flag=false;
                        runner.stop();
                        runner = null;
                }
	
	}
	
	 private AudioFormat getAudioFormat(){
                    float sampleRate = 8000;    //8000,11025,16000,22050,44100
                    int sampleSizeInBits = 16;  //8,16
                    int channels = 1;           //1,2
                    boolean signed = true;      //true,false
                    boolean bigEndian =true;    //true,false
                    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
        }

	/**
 	 * Getting a available mixer in local system which support selected audio format.
 	 */  
	private SourceDataLine getMixer(){
		try {
	                Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        	        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
                        	Mixer currentMixer = AudioSystem.getMixer(mixerInfo[cnt]);
	                        if( currentMixer.isLineSupported(dataLineInfo) ) {
					sourceDataLine =(SourceDataLine)currentMixer.getLine(dataLineInfo);
                        	        return sourceDataLine;
	                        }
        	        }
		}catch(Exception e){}
                return null;
        }

	/**
 	 * start output line (sourceDataLine) to play audio.
 	 */  	
	public void startSourceLine(){
                try{
			if(sourceDataLine!=null){
                        	audioFormat = getAudioFormat();
                       		sourceDataLine.open(audioFormat);
                        	sourceDataLine.start();
			}else {
				getMixer();
				audioFormat = getAudioFormat();
                                sourceDataLine.open(audioFormat);
                                sourceDataLine.start();
			}
                }catch(Exception e){System.out.println("Error in open sourceDataLine : "+e.getMessage());}

        }

	/**
 	 * Stop output line (SourceDataLine) to stop play audio.
 	 */  
        public void stopSourceLine(){
                try{
			sourceDataLine.flush();
                        sourceDataLine.stop();
                        sourceDataLine.drain();
                        sourceDataLine.close();
                }catch(Exception ex){System.out.println("Error in stop sourceDataLine : "+ex.getMessage());}
        }
	
	/**
 	 * Play audio thread which get audio stream from audioVector(local buffer for audio stream).
 	 */ 		 
	public void run(){
		startSourceLine();
		int bufferSize = (int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize());
		while(flag){
			try{
				if(audioVector.size() > 1){
					AudioInputStream input=(AudioInputStream)audioVector.get(0);
					audioVector.remove(0);
					startSourceLine();
					if(input != null ) {	
						byte buffer[] = new byte[bufferSize];
						try {
        			                        int count;
                			                while ((count = input.read(buffer, 0, buffer.length)) != -1) {
								System.out.println("count     "+count+" bufferSize "+bufferSize);
								if(count>0)
                                					sourceDataLine.write(buffer, 0, count);
			                                }
        			                }catch (Exception e) {  
							System.out.println("Error in play audio stream : "+e.getMessage());       
						}
					}
					try {
						stopSourceLine();
						input.reset();
						input.close();
						input=null;
						try {runner.sleep(50);runner.yield();}catch(Exception e){}	
					}catch(Exception ew){} 
				}//end of if
			}catch(Exception ex){System.out.println("Error in AudioPlayer run() "+ex.getMessage());}
		}//end of while
	}
}


