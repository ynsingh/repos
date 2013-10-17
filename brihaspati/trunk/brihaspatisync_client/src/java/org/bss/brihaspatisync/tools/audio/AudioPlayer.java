package org.bss.brihaspatisync.tools.audio;

/**
 * AudioPlayer.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, 2013 ETRG,IIT Kanpur.
 */

import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Jan2012.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified run() Method.
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish yadav</a>JSpeex codec integration to decode audio bytes.
 */

public class AudioPlayer implements Runnable {

	private static AudioPlayer ap=null;
	private Thread runner=null;
        private boolean flag=false;
	private byte[] bigArray=new byte[1280*10];
	private int currentOffset = 0;
	private SourceDataLine sourceDataLine=null;
	private LinkedList<byte[]> audioVector=new LinkedList<byte[]>();
        private AudioFormat audioFormat=org.bss.brihaspatisync.util.AudioUtilObject.getAudioFormat();;
	private org.xiph.speex.SpeexDecoder decoder = org.bss.brihaspatisync.util.AudioUtilObject.getSpeexDecoder();

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
				sourceDataLine=org.bss.brihaspatisync.util.AudioUtilObject.getSourceLine();
			}
                }catch(Exception e){System.out.println("Exception in open sourceDataLine : "+e.getMessage());}

        }

	/**
 	 * Play audio thread which get audio stream from audioVector(local buffer for audio stream).
 	 */ 		 

	public void run() {
		
		while(flag && org.bss.brihaspatisync.util.ThreadController.getThreadFlag()){
			try {
				if(audioVector.size() > 10) { 
					byte[] original=audioVector.get(0);audioVector.remove(0);
					for(int i=0;i<original.length;i=i+74) {	
						int k=i+74;
						byte[] tempbyte=java.util.Arrays.copyOfRange(original,i,k);
						byte[] decodebytearray=getDecoder(tempbyte); 
						System.arraycopy(decodebytearray, 0,bigArray, currentOffset,decodebytearray.length);
                                                currentOffset += decodebytearray.length;
						if(currentOffset == 12800) {
							if(sourceDataLine != null ) {
                                				sourceDataLine.write(bigArray,0,bigArray.length);
				                        } else
                                				sourceDataLine=org.bss.brihaspatisync.util.AudioUtilObject.getSourceLine();
							currentOffset = 0;
						}
					}
				        System.gc();
				} 
				runner.yield();	
			} catch(Exception ex) { System.out.println("Exception in AudioPlayer in run() method "+ex.getMessage());}
		} //end of while
	}
	
	public byte[] getDecoder(byte[] audio_data) {
		byte[] decoded_data=null;
		try {
			decoder.processData(audio_data, 0, audio_data.length);
        	        decoded_data= new byte[decoder.getProcessedDataByteSize()];
       	                decoder.getProcessedData(decoded_data, 0);
		} catch(Exception e){  System.out.println("Exception in AudioPlayer class in getDecoder method  "+e.getMessage());}
		return decoded_data;
	}
}
