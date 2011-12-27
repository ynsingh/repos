package org.bss.brihaspatisync.tools.audio;

/**
 * GetAudioStream.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
 */

import java.io.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import javax.sound.sampled.*;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:esha2008@gmail.com">Esha Srivastava </a>Implement method for dynamic selection of mixer.
 */

public class GetAudioStream implements Runnable {

	private Thread runner=null;
	private boolean flag=false;
	private static GetAudioStream get_audio=null;
	private SourceDataLine sourceDataLine;
	private InputStream is;
	private AudioFormat audioFormat;
	private DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
	private byte audioBytes[]=null;
	private AudioInputStream ais=null;
	private ClientObject clientObject=ClientObject.getController();
        private RuntimeDataObject runtime_object=RuntimeDataObject.getController();

	
	/**
 	 * Controller for this class.
 	 */  

	public static GetAudioStream getController(){
    		if(get_audio==null)
        		get_audio=new GetAudioStream();
       		return get_audio;
   	}

	/**
     	 * Start get audio Thread.
     	 */
   	public void startThread(){
      		if (runner == null) {
			flag=true;
        		runner = new Thread(this);
            		runner.start();
			System.out.println("GetAudioStream start sucessfully !!");
		}
   	}

   	/**
   	 * Stop get audio Thread.
   	 */
  	public void stopThread(){
       		if (runner != null) {
			flag=false;
        		runner.stop();
        		runner = null;
			System.out.println("GetAudioStream stop Successfully !!");
       		}
  	}

	/**
 	 * Receive audio byte array from reflector by using HTTP Get method.
 	 */ 

  	public void run() {
		try {
			while(flag) {
				try {
					HttpClient client = new HttpClient();
					HttpMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":2001");
					client.setConnectionTimeout(200000);
        				method.setRequestHeader("Content-type","application/octet-stream");
					/*
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
        	                                HostConfiguration config = client.getHostConfiguration();
                	                        config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                        	                Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                	        AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                        	client.getState().setProxyCredentials(authScope, credentials);
	                                }
					*/	
                			System.out.println("Bytes length----------------------------------      \n\n\n\n\n\n\n\n");
					int statusCode = client.executeMethod(method);
                			System.out.println("Bytes length--------------------statusCode--------------"+statusCode+"      \n\n\n\n\n\n\n\n");
                			byte audioBytes[]=method.getResponseBody();
               				method.releaseConnection();
                			System.out.println("Bytes length----------------------------------"+audioBytes.length+"      \n\n\n\n\n\n\n\n");
	                		if(audioBytes.length > 0){
        	        			playAudio(audioBytes);
                			}
               				try {
                 				runner.sleep(100);
                 				runner.yield();
	               			}catch(Exception e){}
				}catch(Exception ex){ System.out.println("Error       \n\n\n\n\n\n\n\n"+ex.getMessage()); }
			}
		}catch(Exception exe){System.out.println("Error on open sourceDataLine "+exe.getMessage());}
	}

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
 	 * Play audio with output line(SourceDataLine).
 	 */  
	public void playAudio(byte audio[]){
		try{
			audioFormat = getAudioFormat();
			InputStream is=new ByteArrayInputStream(audio);
			ais = new AudioInputStream(is, audioFormat, audio.length / audioFormat.getFrameSize());

			Mixer currentMixer = getMixer();
			if(currentMixer!=null)
            			sourceDataLine =(SourceDataLine)currentMixer.getLine(dataLineInfo);
            		else
            			System.out.println("Mixer not found!!");

			sourceDataLine.open(audioFormat);
            		sourceDataLine.start();
			
			
            		int bufferSize = (int) (audioFormat.getSampleRate())*(audioFormat.getFrameSize());
			byte buffer[] = new byte[bufferSize];
			try {
            			int count;
        	    		while ((count = ais.read(buffer, 0, buffer.length)) != -1) {
        	    			if (count > 0) {
            					sourceDataLine.write(buffer, 0, count);
        	    			}
        	    		}
    	        		sourceDataLine.drain();
  				sourceDataLine.close();
			}catch (Exception e) {
				System.out.println("Error in play audio stream : "+e.getMessage());
			}
	  	}catch(Exception e){
	  		System.out.println("Error in play Audio"+e.getCause());
	  	}
	}
	
	/**
         * Define audio format
         */
	private AudioFormat getAudioFormat(){
		    float sampleRate = 8000;	//8000,11025,16000,22050,44100
		    int sampleSizeInBits = 16;	//8,16
		    int channels = 1;		//1,2
		    boolean signed = true;	//true,false
		    boolean bigEndian =true;	//true,false
		    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
 	}

	/*public static void main(String args[]){
		try{
			GetAudioStream.getController().startThread();
		}catch(Exception e){System.out.println("Error in main to start Thread"+e.getMessage());}
	}*/
}
