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
import java.net.URLEncoder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
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
	private static PostAudioStream post_audio=null;
	private ClientObject clientObject=ClientObject.getController();

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
 	 *Transmit audioInputStream to reflector by using HTTP post method.
 	 */
  	public void run() {
		try {
			AudioCapture.getController().startCapture();
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                       	h.setName("session");
                        h.setValue(clientObject.getLectureID());
			while(flag) {
				try {
				String filename="audio.wav";
				AudioCapture.getController().stopCapture();
                                HttpClient client = new HttpClient();
                                PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":2000");
                                client.setConnectionTimeout(20000);
                                if((new File(filename)).exists()) {
                                        postMethod.setRequestBody(new java.io.FileInputStream(filename));
					postMethod.setRequestHeader(h);
                                	int statusCode1 = client.executeMethod(postMethod);
				}
                                postMethod.getStatusLine();
                                postMethod.releaseConnection();
				
                                try { 
					File audioFile= new File(filename); 
					if(audioFile.exists() ) 
						audioFile.delete(); 
					AudioCapture.getController().startCapture();
					runner.sleep(5000); 
					runner.yield();
				}catch(Exception ex){}
				}catch(Exception epe){}
                        }
		}catch(Exception e){
			try{ runner.sleep(1000); runner.yield(); }catch(Exception we){}
			System.out.println("Error in PostMethod of Audio sender : "+e.getMessage());
		}
	}
}
