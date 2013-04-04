package org.bss.brihaspatisync.tools.audio;

/**
 * AudioPlayer.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, 2013 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import javax.sound.sampled.Mixer;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import org.bss.brihaspatisync.util.ThreadController;

import org.xiph.speex.SpeexDecoder;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Jan2012.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified run() Method.
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish yadav</a>JSpeex codec integration to decode audio bytes.
 */

public class AudioPlayer implements Runnable {

	private static AudioPlayer ap=null;
	private Thread runner=null;
        private boolean flag=false;
	private SourceDataLine sourceDataLine=null;
	private LinkedList<byte[]> audioVector=new LinkedList<byte[]>();
        private AudioFormat audioFormat=org.bss.brihaspatisync.util.ClientObject.getController().getAudioFormat();;

	
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
			startSourceLine();
                        flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
	
	}

	private void stopThread(){
		if (runner != null) {
                        flag=false;
                        runner.stop();
                        runner = null;
			sourceDataLine=null;
                }
	
	}
	
	/**
 	 * start output line (sourceDataLine) to play audio.
 	 */  	

	private void startSourceLine(){
                try{
			if(sourceDataLine == null) {
				sourceDataLine=org.bss.brihaspatisync.util.ClientObject.getController().getSourceLine();
			}
                }catch(Exception e){System.out.println("Error in open sourceDataLine : "+e.getMessage());}

        }

	/**
 	 * Play audio thread which get audio stream from audioVector(local buffer for audio stream).
 	 */ 		 
	public void run() {
		SpeexDecoder decoder = new SpeexDecoder();
                decoder.init(1, (int) audioFormat.getSampleRate(), audioFormat.getChannels(), true);
		while(flag && ThreadController.getController().getThreadFlag()){
			try {
				if(audioVector.size() > 4){
					for (int i=0;i<4;i++) {
						if(sourceDataLine != null ) {
							byte[] audio_data=audioVector.get(0);
                                                        decoder.processData(audio_data, 0, audio_data.length);
                                                        byte[] decoded_data = new byte[decoder.getProcessedDataByteSize()];
                                                        int decoded = decoder.getProcessedData(decoded_data, 0);
                                                        System.out.println(decoded+ " bytes resulted as a result of decoding " + audio_data.length + " encoded bytes.");
                                                        sourceDataLine.write(decoded_data,0,decoded);
							audioVector.remove(0);
						}	
					}
				} 
				runner.yield();	
			}catch(Exception ex){System.out.println("Error in AudioPlayer run() "+ex.getMessage());}
		}//end of while
	}
}


