package org.bss.brihaspatisync.tools.audio;

/**
 * AudioPlayer.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import javax.sound.sampled.Mixer;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import org.bss.brihaspatisync.util.ThreadController;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Jan2012.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified run() Method.
 */

public class AudioPlayer implements Runnable {

	private static AudioPlayer ap=null;
	private Thread runner=null;
        private boolean flag=false;
        private byte audioBytes[]=null;
	private SourceDataLine sourceDataLine;
	private LinkedList<byte[]> audioVector=new LinkedList<byte[]>();
        private AudioFormat audioFormat=org.bss.brihaspatisync.util.ClientObject.getController().getAudioFormat();;
        private DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

	
	protected static AudioPlayer getController(){
		if(ap==null)
			ap=new AudioPlayer();
		return ap;
	}
	
	protected void putAudioStream(byte[] audio_data){
		 if (runner == null) 
			startThread();
		audioVector.addLast(audio_data);
	}
	
	private void startThread(){
		 if (runner == null) {
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
			startSourceLine();
                }
	
	}

	private void stopThread(){
		if (runner != null) {
                        flag=false;
                        runner.stop();
                        runner = null;
			stopSourceLine();
                }
	
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
	private void startSourceLine(){
                try{
			int bufferSize =(int)(audioFormat.getSampleRate())*(audioFormat.getFrameSize());
			if(sourceDataLine!=null){
                       		sourceDataLine.open(audioFormat,bufferSize);
                        	sourceDataLine.start();
			}else {
				getMixer();
                                sourceDataLine.open(audioFormat,bufferSize);
                                sourceDataLine.start();
			}
                }catch(Exception e){System.out.println("Error in open sourceDataLine : "+e.getMessage());}

        }

	/**
 	 * Stop output line (SourceDataLine) to stop play audio.
 	 */  
        private void stopSourceLine(){
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
	public void run() {
		int bufferSize =(int)(audioFormat.getSampleRate())*(audioFormat.getFrameSize());
		int offset=0;
		int numRead = bufferSize;
                int size=bufferSize*25;
		while(flag && ThreadController.getController().getThreadFlag()){
			try {
				if(audioVector.size() > 0){
                              		// sourceDataLine.write(audioVector.get(0), 0, bufferSize);
					
                                	if(size >=numRead){
						offset=0;
						numRead = bufferSize;	
					}
					numRead=numRead + offset;
                                	offset += sourceDataLine.write(audioVector.get(0),offset, numRead);
					
					audioVector.remove(0);
				} //end of if
				runner.yield();	
			}catch(Exception ex){System.out.println("Error in AudioPlayer run() "+ex.getMessage());}
		}//end of while
	}
}


