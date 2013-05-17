package org.bss.brihaspatisync.tools.audio;

/**
 * AudioCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, 2013 ETRG,IIT Kanpur.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import org.bss.brihaspatisync.util.ClientObject;

import org.xiph.speex.SpeexEncoder;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Modified on 04-04-2013. JSpeex codec integration to encode audio data.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioCapture implements Runnable {

	private boolean flag=false;
	private Thread runner=null;	
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
	
	protected void startCapture(){
		if(runner ==null) {
	                //bufferSize = ((int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize()))/4;
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
        	       		startCapture();
		} else {
			if(flag) {
				startCapture();
			} else { 
				stopCapture();
			}
		}
	}

	/**
 	 * Local thread for record audio from microphone and save in file named as filename variable.
 	 */  
	public void run() { 
                try {
			SpeexEncoder encoder = new SpeexEncoder();
                        encoder.init(1, 10, (int)audioFormat.getSampleRate(), audioFormat.getChannels());
                        final int raw_block_size = encoder.getFrameSize() * audioFormat.getChannels()  * (audioFormat.getSampleSizeInBits() / 8);
			byte audio_data[] = new byte[raw_block_size];
			while(flag && org.bss.brihaspatisync.util.ThreadController.getController().getThreadFlag()) {	
				if(targetDataLine !=null) {
                               		int cnt = targetDataLine.read(audio_data,0,audio_data.length);
					if( encoder.processData(audio_data, 0, audio_data.length) ) {
						byte[] encoded_data= new byte[encoder.getProcessedDataByteSize()];
                        	        	encoder.getProcessedData(encoded_data, 0);
        	                        	audioVector.addLast(encoded_data);
					} else 
        	                                System.out.println("Could not encode data!");
				} else 
                         	       targetDataLine = ClientObject.getController().getTargetLine();
			}
                } catch(Exception e){System.out.println("Eception in capture Audio class "+e.getCause());}
        }
		
	protected byte [] getAudioData() {
		if(audioVector.size()>0) {
			if(audioVector.size()>10) {
				audioVector.subList(2,7).clear();	
                        }
			byte[] data=audioVector.get(0);
			audioVector.remove(0);
			return data;
		}
		return null;
	}
}
