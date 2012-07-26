package org.bss.brihaspatisync.tools.audio;

/**
 * PostAudioStream.java
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

import java.io.File;
import java.io.*;
import javax.sound.sampled.*;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class PostAudioStream implements Runnable {

	private Thread runner=null;
	private boolean flag=false;
	private boolean getflag=false;

	private AudioFormat audioFormat;	
	private static PostAudioStream post_audio=null;
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
	public void startThread(boolean getscreen){
        	if (runner == null) {
			if(getscreen)
				AudioPlayer.getController().startThread();
			else
				AudioCapture.getController().start();
			flag=true;
			getflag=getscreen;
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
			if(getflag)
				AudioPlayer.getController().stopThread();
			else
				AudioCapture.getController().stopCapture();
			flag=false;
			getflag=false;
            		runner.stop();
            		runner = null;
			System.out.println("PostAudioStream stop successfully !!");
      		}
   	}

	/**
 	 *Transmit audioInputStream to reflector by using HTTP post method.
 	 */
  	public void run() {
		try {
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                       	h.setName("session");
                        h.setValue(clientObject.getLectureID());
			int port =runtime_object.getAudioPort();
			while(flag && ThreadController.getController().getThreadFlag()) {
				try {
					java.io.ByteArrayOutputStream os=null;
					if(!getflag) {
						os=AudioCapture.getController().startCapture();
					}	
	                                HttpClient client = new HttpClient();
        	                        PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":"+port);
                	                client.setConnectionTimeout(20000);
                        	        if(os !=null ){
                                	        postMethod.setRequestBody(new java.io.ByteArrayInputStream(os.toByteArray()));
					}
					postMethod.setRequestHeader(h);
					// Http Proxy Handler	
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                	                	HostConfiguration config = client.getHostConfiguration();
                        	                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                	        Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                        	AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                                client.getState().setProxyCredentials(authScope, credentials);
	                              	}
        	                        int statusCode1 = client.executeMethod(postMethod);
					if(getflag) {					
						byte audioBytes[]=postMethod.getResponseBody();
	                                        AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(audioBytes),audioFormat, audioBytes.length / getAudioFormat().getFrameSize());
        	                                if((ais != null) && (audioBytes.length > 1600))
                	                                AudioPlayer.getController().putAudioStream(ais);
                        	                ais.close();
					}
                        	        postMethod.getStatusLine();
                                	postMethod.releaseConnection();
					os.flush();
					os.close();
					runner.yield();
				}catch(Exception epe){}
                        }
		}catch(Exception e){ System.out.println("Error in PostMethod of Audio sender : "+e.getMessage()); }
	}
	
	private AudioFormat getAudioFormat(){
                    float sampleRate = 8000;//8000,11025,16000,22050,44100
                    int sampleSizeInBits = 16;  //8,16
                    int channels = 1;   //1,2
                    boolean signed = true; //true,false
                    boolean bigEndian =true; //true,false
                    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
        }
}
