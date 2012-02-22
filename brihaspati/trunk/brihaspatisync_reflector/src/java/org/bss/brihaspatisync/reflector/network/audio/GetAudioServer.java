package org.bss.brihaspatisync.reflector.network.audio;
/**
 * GetAudioServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
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

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2011
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modify the transmit thread.
 */

public class GetAudioServer {

	private static GetAudioServer getAudio=null;
   	private HttpServer server =null;
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

	public void startThread() throws Exception {
    		try {
          		server.start();
          		System.out.println(" GetAudioServer start successfully !! ");
       		} catch (Exception e) { }
  	}

  	public void stopThread() throws Exception {
    		if (server != null) {
          		server.stop(0);
          		System.out.println(" GetAudioServer stop successfully !! ");
       		}
   	}
}

class GetRequestHandler implements HttpHandler {
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	
  	public synchronized void handle(HttpExchange exchange) throws IOException {
  		try{
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("GET")) {
		      		Headers responseHeaders = exchange.getResponseHeaders();
            			responseHeaders.set("Content-Type", "text/plain");
            			exchange.sendResponseHeaders(200, 0);
				Headers responseHeader = exchange.getRequestHeaders();
                                String lecture_id=responseHeader.get("session").toString();
				String client_ip=exchange.getRemoteAddress().getAddress().getHostAddress();
            			OutputStream responseBody = exchange.getResponseBody();
				try {
					MyHashTable temp_ht=runtimeObject.getAudioServerMyHashTable();
	                                BufferMgt buffer_mgt=temp_ht.getValues("Audio_Post"+lecture_id);
        	                        String input=(String)(buffer_mgt.sendData(client_ip,"Audio_Post"+lecture_id));
                	                if(input!=null) {	
						File f=new File(lecture_id+"/"+input+".wav");	
						AudioInputStream ais =AudioSystem.getAudioInputStream(new FileInputStream(f.getAbsolutePath()));
						AudioSystem.write(ais,AudioFileFormat.Type.WAVE,responseBody);
					}
                                }catch(Exception e){}
				responseBody.flush();
				responseBody.close();
				exchange.notify();
           		}
		}catch(Exception exe){}
	}
}

