package org.bss.brihaspatisync.reflector.network.audio;
/**
 * GetAudioServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2011
 */

public class GetAudioServer {

	private static GetAudioServer getAudio=null;
   	private HttpServer server =null;
	private boolean flag=false;
	private int server_port = RuntimeDataObject.getController().getAudioGetPort();

	public static GetAudioServer getController() throws Exception {
    		if(getAudio==null)
        		getAudio=new GetAudioServer();
      		return getAudio;
   	}

	public GetAudioServer() throws Exception{
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new GetRequestHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}

	protected boolean isRunning() throws Exception {
      		return flag;
   	}

	public void startThread() throws Exception {
    		try {
        		flag=true;
          		System.out.println(" GetAudioServer start successfully !! ");
          		server.start();
			ManageAudioQueue.getController().startThread();
       		} catch (Exception e) { }
  	}

  	public void stopThread() throws Exception {
    		if (server != null) {
        		flag=false;
          		server.stop(0);
			ManageAudioQueue.getController().stopThread();
       		}
   	}

	/*public static void main(String args[]){
    		try{
        		GetAudioServer.getController().start();
      		}catch(Exception e){System.out.println("Error in main to start server thread"+e.getMessage());}
  	}*/
}

class GetRequestHandler implements HttpHandler {

	private Vector v=null;

  	public void handle(HttpExchange exchange) throws IOException {
  		try{
  			while(GetAudioServer.getController().isRunning()){
				String requestMethod = exchange.getRequestMethod();
				if (requestMethod.equalsIgnoreCase("GET")) {
		      			Headers responseHeaders = exchange.getResponseHeaders();
            				responseHeaders.set("Content-Type", "application/octet-stream");
            				exchange.sendResponseHeaders(200, 0);
            				OutputStream responseBody = exchange.getResponseBody();

					String ip=exchange.getRemoteAddress().getAddress().getHostAddress(); 
					System.out.println("Request from IP Address =====>"+ip);

					ManageAudioQueue.getController().createRequestQueue(ip);
					Vector v=new Vector();
					v= (Vector) ManageAudioQueue.getController().getHashtable().get(ip);
					System.out.println("get hastable vector object "+ v.size());
					/*if(v.size() > 0){
						AudioInputStream input=(AudioInputStream)v.elementAt(0);
                                                System.out.println(" input "+input);
                                                if(input!=null) {
                                                        AudioSystem.write(input,AudioFileFormat.Type.WAVE,responseBody);
                                                } else
                                                        System.out.println(" input is null "+input);
					*/
				
					
					
            			/*	if(AudioQueue.getController().size() > 0){
            					AudioInputStream input=(AudioInputStream)AudioQueue.getController().get();
						System.out.println(" input is null "+input);
        	    				if(input!=null) {
            						AudioSystem.write(input,AudioFileFormat.Type.WAVE,responseBody);
	       					} else
							System.out.println(" input is null "+input);
				*/
					//}else
					//	System.out.println("Audio Queue is empty "+AudioQueue.getController().size());
					//v.removeElementAt(0);
					responseBody.close();
           			}
			}
		}catch(Exception exe){}
	}
}

