package org.bss.brihaspatisync.util;

/**
 * AudioUtilObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 */

import javax.sound.sampled.Mixer;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.SourceDataLine;
import org.bss.brihaspatisync.http.HttpCommManager;
//import org.xiph.speex.SpeexEncoder;
//import org.xiph.speex.SpeexDecoder;
/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * This class is used to store objects which are needed in runtime by this client.
 */

public class AudioUtilObject {

	private static String a_status="";
	private static String v_status="";
	//private static SpeexEncoder encoder=null;
	//private static SpeexDecoder decoder=null;
	private static SourceDataLine sourceDataLine=null;
	private static TargetDataLine targetDataLine=null;
	
	public static void setVideoStatus(String str){
                v_status=str;
        }

        public static void setAudioStatus(String str){
                a_status=str;
        }

	public static String getVideoStatus(){
                return v_status;
        }

        public static String getAudioStatus(){
                return a_status;
        } 
	
	/**
	 * Define audio format
 	 */
	
        public static javax.sound.sampled.AudioFormat getAudioFormat() {
		float sampleRate = 8000.0f;    //8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;  //8,16
                int channels = 1;           //1,2
		boolean bigEndian =false;    //true,false
		return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,sampleRate,sampleSizeInBits,channels,channels*2,sampleRate,bigEndian);
    	}

	/**
 	 * Getting a available mixer in local system which support selected audio format.
 	 **/

        private static void getMixer() {
                try {
			boolean targetDataLineflag=false;	
			boolean sourceDataLineflag=false;	
                	
			DataLine.Info targetdataLineInfo = new DataLine.Info(TargetDataLine.class, getAudioFormat());
                        DataLine.Info soursedataLineInfo = new DataLine.Info(SourceDataLine.class, getAudioFormat());
                        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
                        System.out.println("Available mixers:");
                        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
                        	System.out.println(mixerInfo[cnt].getName());
                                Mixer currentMixer_local = AudioSystem.getMixer(mixerInfo[cnt]);
				
				if( currentMixer_local.isLineSupported(soursedataLineInfo) && (!sourceDataLineflag)) {
                                	System.out.println("mixer name: " + mixerInfo[cnt].getName() + " index:" + cnt);
					Mixer currentMixer=currentMixer_local;
                                	try {
                                        	sourceDataLine =(SourceDataLine)currentMixer.getLine(soursedataLineInfo);
                                                sourceDataLine.open(getAudioFormat());
                                                sourceDataLine.start();
						sourceDataLineflag=true;
                                                System.out.println("opening sourceDataLine.");
                                      	} catch(Exception ex){System.out.println("Error in get Miser and start sourceDataLine "+ex.getMessage());}
				}

                                if( currentMixer_local.isLineSupported(targetdataLineInfo) && (!targetDataLineflag) ) {
                                	System.out.println("mixer name: " + mixerInfo[cnt].getName() + " index:" + cnt);
                                        Mixer currentMixer=currentMixer_local;
					try {
						targetDataLine =(TargetDataLine)currentMixer.getLine(targetdataLineInfo);
						targetDataLine.open(getAudioFormat());
		                		targetDataLine.start();
						targetDataLineflag=true;
                		        	System.out.println("opening targetDataLine.");
					} catch(Exception ex){System.out.println("Error in get Miser and start targetDataLine "+ex.getMessage());}
                             	}

				if((sourceDataLineflag) && (targetDataLineflag))
					break;
               		}
             	}catch(Exception e){  System.out.println("Exception in get Miser and start sourceDataLine and targetDataLine "+e.getMessage());}
        }
	
	public static TargetDataLine getTargetLine() {
		if(targetDataLine == null)
			getMixer();
		return 	targetDataLine;
	}
	
	public static SourceDataLine getSourceLine() {
		if(sourceDataLine == null)
                        getMixer();
		return sourceDataLine;
	}

	/**	
	public static SpeexEncoder getSpeexEncoder() {
		try {
			if(encoder == null) {
				encoder = new SpeexEncoder();
	                        encoder.init(1, 10, (int)getAudioFormat().getSampleRate(), getAudioFormat().getChannels());
				
			}
		} catch(Exception e){System.out.println("Exception in ClientObject class in getSpeexEncoder method  "+e.getMessage());}	
		return encoder;
	}
		
	public static SpeexDecoder getSpeexDecoder() {
		try {
			if(decoder == null) {
                        	decoder = new SpeexDecoder();
		                decoder.init(1, (int) getAudioFormat().getSampleRate(), getAudioFormat().getChannels(), false);
                        }	
		}catch(Exception e){ System.out.println("Exception in ClientObject class in getSpeexDecoder method  "+e.getMessage()); }
		return decoder;
	}**/
}

