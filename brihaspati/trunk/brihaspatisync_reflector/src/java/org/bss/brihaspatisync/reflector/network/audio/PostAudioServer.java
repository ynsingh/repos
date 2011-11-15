package org.bss.brihaspatisync.reflector.network.audio;

/**
 * PostAudioServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import javax.sound.sampled.*;

import org.apache.commons.io.IOUtils;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2011
 */

public class PostAudioServer {

	private static PostAudioServer postserver=null;
    	private HttpServer server =null;
	private SourceDataLine line;
	private boolean flag=false;
	private int server_port=RuntimeDataObject.getController().getAudioPostPort();


	public static PostAudioServer getController() throws Exception {
    		if(postserver==null)
        		postserver=new PostAudioServer();
      		return postserver;
  	}

	public PostAudioServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyPostHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}

	protected boolean isRunning() throws Exception {
    		return flag;
   	}

	public void startThread() throws Exception {
    		try {
           		flag=true;
        		System.out.println(" PostAudioServer start successfully !! ");
          		server.start();
      		} catch (Exception e) { }
  	}

   	public void stopThread() throws Exception {
    		if (server != null) {
        		flag=false;
          		server.stop(0);
    		}
  	}

	/*public static void main(String args[]){
		try{
			PostAudioServer.getController().startThread();
			GetAudioServer.getController().start();
		}catch(Exception e){System.out.println("Error on start post audio server "+e.getMessage());}
	}*/

}

class MyPostHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		try{
			while(PostAudioServer.getController().isRunning()){
				String requestMethod = exchange.getRequestMethod();
				if (requestMethod.equalsIgnoreCase("POST")) {
					Headers responseHeaders = exchange.getResponseHeaders();
					responseHeaders.set("Content-Type", "application/octet-stream");
					exchange.sendResponseHeaders(200, 0);
					OutputStream responseBody = exchange.getResponseBody();
	           			InputStream input = exchange.getRequestBody();
					byte[] bytes=IOUtils.toByteArray(input);
					InputStream is=new ByteArrayInputStream(bytes);
					AudioInputStream ais = new AudioInputStream(is, getAudioFormat(), bytes.length / getAudioFormat().getFrameSize());
    	  	       			if(AudioQueue.getController().size()<50){
    		       				AudioQueue.getController().put(ais);
        		   		}else {
           					AudioQueue.getController().clearQueue();
           				}
           				responseBody.close();
				}//end of if
   			}//end of while
		}catch(Exception ex){}
	}

	private AudioFormat getAudioFormat(){
		    float sampleRate = 8000;	//8000,11025,16000,22050,44100
		    int sampleSizeInBits = 16;	//8,16
		    int channels = 1;			//1,2
		    boolean signed = true;		//true,false
		    boolean bigEndian =true;	//true,false
		    return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
 	}
}




