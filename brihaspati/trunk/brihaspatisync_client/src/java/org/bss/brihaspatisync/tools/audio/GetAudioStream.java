package org.bss.brihaspatisync.tools.audio;

/**
 * GetAudioStream.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.io.*;
import java.util.Vector;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import javax.sound.sampled.*;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:esha2008@gmail.com">Esha Srivastava </a>Implement method for dynamic selection of mixer.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Implement method for get audio stream from reflector.
 */

public class GetAudioStream implements Runnable {

	private boolean flag=false;
	private Thread runner=null;
	private static GetAudioStream get_audio=null;
	private AudioFormat audioFormat;
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
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                        h.setName("session");
                        h.setValue(clientObject.getLectureID());
			audioFormat=getAudioFormat();
			int port =runtime_object.client_getaudio_port();
		 	while(flag && ThreadController.getController().getThreadFlag()) {
				try {
                                	HttpClient client = new HttpClient();
	                                GetMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":"+port);
        	                        client.setConnectionTimeout(20000);
					method.setRequestHeader(h);
					// Http Proxy Handler	
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                                                HostConfiguration config = client.getHostConfiguration();
                                                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                                Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                                AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                                client.getState().setProxyCredentials(authScope, credentials);
                                        }
									
                        	        int statusCode = client.executeMethod(method);
        		        	byte audioBytes[]=method.getResponseBody();
        	        		method.releaseConnection();
					AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(audioBytes),audioFormat, audioBytes.length / getAudioFormat().getFrameSize());
					if((ais != null) && (audioBytes.length > 1600))
                              			AudioPlayer.getController().putAudioStream(ais);
					ais.close();
				}catch(Exception we){}
               			try { runner.sleep(500); runner.yield(); }catch(Exception ex){}
				System.gc();
                        }
		}catch(Exception exe){try { runner.sleep(5000); runner.yield(); }catch(Exception ex){}System.out.println("Error on get stream in GetAudioStream  "+exe.getMessage());}
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
