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
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:esha2008@gmail.com">Esha Srivastava </a>Implement method for dynamic selection of mixer.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Implement method for get audio stream from reflector.
 */

public class GetAudioStream implements Runnable {

	private Thread runner=null;
	private Thread playThread=null;
	private boolean flag=false;
	private static GetAudioStream get_audio=null;
	private SourceDataLine sourceDataLine;
	private InputStream is;
	private AudioFormat audioFormat;
	private byte audioBytes[]=null;
	private AudioInputStream ais=null;
	private ClientObject clientObject=ClientObject.getController();
        private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private Vector audioVector=new Vector();

	
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
		 	while(flag) {
				try {
                                	HttpClient client = new HttpClient();
	                                GetMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":2001");
        	                        client.setConnectionTimeout(20000);
					method.setRequestHeader(h);
                        	        int statusCode = client.executeMethod(method);
        		        	byte audioBytes[]=method.getResponseBody();
        	        		method.releaseConnection();
					
					AudioInputStream ais=javax.sound.sampled.AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioBytes));
					if((ais != null) && (audioBytes.length > 70))
                              			AudioPlayer.getController().putAudioStream(ais);
					ais.close();
				}catch(Exception we){}
				
               			try { runner.sleep(5000); runner.yield(); }catch(Exception ex){}
                        }
		}catch(Exception exe){try { runner.sleep(100); runner.yield(); }catch(Exception ex){}System.out.println("Error on get stream in GetAudioStream  "+exe.getMessage());}
	}

}
