package org.bss.brihaspatisync.tools.audio;

/**
 * AudioClient.java
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

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on Oct2011.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified transmit thread.
 */

public class AudioClient implements Runnable {

	private Thread runner=null;
	private boolean flag=false;

	private static AudioClient audio=null;
	private HttpClient client = new HttpClient();	
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private AudioCapture au_cap=new AudioCapture();	

	/**
 	 * Controller for the class.
 	 */
	public static AudioClient getController(){
		if(audio==null)
			audio=new AudioClient();
		return audio;
	}

	/**
 	 * Start Thread
 	 */
	public void startThread(){
        	if (runner == null) {
			flag=true;
            		runner = new Thread(this);
            		runner.start();
			System.out.println("AudioClient start successfully !!");
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
			System.out.println("AudioClient stop successfully !!");
      		}
   	}

	public void postAudio(boolean startstopflag) {
		au_cap.setflag(startstopflag);	
	}

	/**
 	 *Transmit audioInputStream to reflector by using HTTP post method.
 	 */
		
  	public void run() {
		try {
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                       	h.setName("session");
                        h.setValue(clientObject.getLectureID()+","+clientObject.getUserName());
			int port = runtime_object.getAudioPort();
			while(flag && ThreadController.getController().getThreadFlag()) {
				try {
                                        PostMethod method = new PostMethod("http://"+clientObject.getReflectorIP()+":"+port);
                                        client.setConnectionTimeout(8000);
					byte [] audiodata=au_cap.getAudioData();
					if(audiodata != null) {
						method.setRequestBody(new java.io.ByteArrayInputStream(audiodata));
					}
                                        method.setRequestHeader(h);	
					
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                                                HostConfiguration config = client.getHostConfiguration();
                                                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                                Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                                AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                                client.getState().setProxyCredentials(authScope, credentials);
                                        }

                                        int statusCode = client.executeMethod(method);
					byte audioBytes[]=method.getResponseBody();
					try {
						method.releaseConnection();
                	                        if((audioBytes.length) > 3000) {
                        	                        AudioPlayer.getController().putAudioStream(audioBytes);
						}
					}catch(Exception e){System.out.println(e.getMessage());}
					try { runner.yield(); }catch(Exception ex){}
					StatusPanel.getController().setaudioClient("yes");
				}catch(Exception epe){try { runner.sleep(500); runner.yield(); StatusPanel.getController().setaudioClient("no"); }catch(Exception ex){} }
                        }
		}catch(Exception e){ System.out.println("Error in PostMethod of Audio sender : "+e.getMessage()); StatusPanel.getController().setaudioClient("no"); }
	}
	
}
