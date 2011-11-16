package org.bss.brihaspatisync.tools.audio;

/**
 * PostAudioStream.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:esha2008@gmail.com">Esha Srivastava </a>Implement method for dynamic selection of mixer.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class PostAudioStream implements Runnable {

	private Thread runner=null;
	private boolean flag=false;
	private TargetDataLine targetDataLine;
	private AudioFormat audioFormat;
	private InputStream is=null;
	private static PostAudioStream post_audio=null;
    	private DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
    	private AudioFileFormat.Type fileType = null;
   	private Thread captureThread=null;
   	private Mixer currentMixer=null;
	private ClientObject clientObject=ClientObject.getController();
	 private RuntimeDataObject runtime_object=RuntimeDataObject.getController();

	/**
 	 * Controller for the class.
 	 */
	public static PostAudioStream getController(){
		if(post_audio==null)
			post_audio=new PostAudioStream();
		return post_audio;
	}

	/**
 	 * Start Thread
 	 */
	public void startThread(){
        	if (runner == null) {
			flag=true;
            		runner = new Thread(this);
            		runner.start();
			System.out.println("PostAudioStream start successfully !!");
		}
       }

       /**
        * Stop Thread.
        */
	public void stopThread() {
        	if (runner != null) {
			flag=false;
            		runner.stop();
            		runner = null;
			System.out.println("PostAudioStream stop successfully !!");
      		}
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
 	 * Open and start input Line (TargetDataLine) from selected Mixer.
 	 */  
    	public TargetDataLine getTargetLine() {
		try{
			if(currentMixer==null){
				currentMixer = getMixer();
			}
    			if(currentMixer!=null)
            			targetDataLine =(TargetDataLine)currentMixer.getLine(dataLineInfo);
            		else
            			System.out.println("Mixer not found!!");

			audioFormat = getAudioFormat();
            		targetDataLine.open(audioFormat);
            		targetDataLine.start();
		}catch(Exception e){System.out.println("Error in open targetdataline "+e.getMessage());}
			return targetDataLine;
    	}

	/**
 	 * Stop TargetDataLine
 	 */  
	public void stopCapture(){
		targetDataLine.stop();
		targetDataLine.drain();
	        targetDataLine.close();
        }

	/**
 	 * Audio capture thread which record audio stream in a file named as audio.wav from TargetDataLine 
 	 */
  	public void startCapture(){
  		try{
			getTargetLine();
  		    	fileType = AudioFileFormat.Type.WAVE;
      			captureThread = new Thread(new Runnable() {
            					public void run() {
							try{
      								AudioSystem.write(new AudioInputStream(targetDataLine),	fileType, new File("audio.wav"));
    							}catch (Exception e){
      								e.printStackTrace();
    							}
						}
					}, "CaptureThread #1");
            		captureThread.start();
       		}catch(Exception e){System.out.println("Error in capture Audio"+e.getCause());}
  	}

	/**
 	 *Transmit audioInputStream to reflector by using HTTP post method.
 	 */  
  	public void run() {
		startCapture();
		try {
			while(flag) {
				stopCapture();
				HttpClient client = new HttpClient();
		        	PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":2000");
				client.setConnectionTimeout(20000);
				if((new File("audio.wav")).exists())
					postMethod.setRequestBody(AudioSystem.getAudioInputStream(new File("audio.wav")));//is);
               			postMethod.setRequestHeader("Content-type","application/octet-stream");
				// Http Proxy Handler		
				if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                                        HostConfiguration config = client.getHostConfiguration();
                                        config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                        Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                        AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                        client.getState().setProxyCredentials(authScope, credentials);
                                }
				
           			int statusCode1 = client.executeMethod(postMethod);
                		postMethod.getStatusLine();
                		postMethod.releaseConnection();

                		try {
                			File audioFile= new File("audio.wav");
					if(audioFile.exists()){
  		    				audioFile.delete();
					}
                			startCapture();
            				Thread.sleep(800);
        				Thread.yield();
        			}catch(Exception ex){}
			}
		}catch(Exception e){
			System.out.println("Error in PostMethod of Audio sender : "+e.getMessage());
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
		    boolean bigEndian =false;	//true,false
		    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
 	}

}
