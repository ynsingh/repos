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
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Jan2012.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified run() Method.
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish yadav</a>JSpeex codec integration to decode audio bytes.
 */

public class AudioPlayer implements Runnable {

	private static AudioPlayer ap=null;
	private Thread runner=null;
	private boolean flag=false;
	private byte[] bigArray=null;
	private int currentOffset = 0;
	private Thread playerThread=null;
        private Thread mixed_byteThread=null;
	private SourceDataLine sourceDataLine=null;
	private UtilObject utilobject=UtilObject.getController();
	private LinkedList<byte[]> audioplayerVector=new LinkedList<byte[]>();
        private AudioFormat audioFormat=org.bss.brihaspatisync.util.AudioUtilObject.getAudioFormat();;
	private org.xiph.speex.SpeexDecoder decoder = org.bss.brihaspatisync.util.AudioUtilObject.getSpeexDecoder();

	protected static AudioPlayer getController(){
		if(ap==null)
			ap=new AudioPlayer();
		return ap;
	}
	
	protected void startThread(){
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
		(mixed_byteThread=new Thread() {
   		public void run(){
                	while(flag && org.bss.brihaspatisync.util.ThreadController.getThreadFlag()) {
                        	try {
					LinkedList audio_rechive_data=utilobject.getReceiveQueue("Audio_Data");
	                               	while(audio_rechive_data.size()>0) {
						byte[] audioBytes=(byte[])audio_rechive_data.remove();
						for(int i=0;i<audioBytes.length;i=i+74) {
							if( currentOffset==0 )
								bigArray=new byte[1280*10];
        	                                       	byte[] tempbyte=java.util.Arrays.copyOfRange(audioBytes,i,(i+74));
							byte[] decodebytearray=getDecoder(tempbyte);
							if(decodebytearray != null) {
								System.arraycopy(decodebytearray, 0,bigArray, currentOffset,decodebytearray.length);
								currentOffset += decodebytearray.length;
								if(currentOffset == 12800) {
									currentOffset=0;
									audioplayerVector.addLast(bigArray);	
								}
							}
						}	
                                	}
                                       	mixed_byteThread.yield();
                                       	//mixed_byteThread.sleep(10);
                                } catch(Exception ex) { System.out.println("Exception in AudioPlayer mixed_byteThread thread "+ex.getMessage());}
                   	}
              	}}).start();
		
                (playerThread=new Thread(){
                        public void run(){
                                while(flag && org.bss.brihaspatisync.util.ThreadController.getThreadFlag()) {
                                        try {
                                                while(audioplayerVector.size() > 0) {
                                                        byte[] original=audioplayerVector.remove();
                                                        if(sourceDataLine != null ) {
								if(original != null)
	                                                                sourceDataLine.write(original,0,original.length);
                                                        } else
                                                                sourceDataLine=org.bss.brihaspatisync.util.AudioUtilObject.getSourceLine();
                                                }
                                                playerThread.yield();
						//playerThread.sleep(5000);
						//stopThread();
                                        }catch(Exception e){ System.out.println("Exception in AudioPlayer in run() method "+e.getMessage());}
                                }stopThread();
                        }
                }).start();
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
