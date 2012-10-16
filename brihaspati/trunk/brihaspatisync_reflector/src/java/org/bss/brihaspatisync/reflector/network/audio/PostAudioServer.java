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

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2011
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Created on 2012
 */

public class PostAudioServer {

	private static PostAudioServer postserver=null;
    	private HttpServer server =null;
	private SourceDataLine line;
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

	public void startThread() throws Exception {
    		try {
        		System.out.println(" PostAudioServer start successfully !! ");
          		server.start();
      		} catch (Exception e) { }
  	}

   	public void stopThread() throws Exception {
    		if (server != null) {
          		server.stop(0);
        		System.out.println(" PostAudioServer stop successfully !! ");
    		}
  	}
}

class MyPostHandler implements HttpHandler {
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	public void handle(HttpExchange exchange) throws IOException {
		try{
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("POST")) {
				Headers responseHeaders = exchange.getResponseHeaders();
                                responseHeaders.set("Content-Type", "application/octet-stream");
                                exchange.sendResponseHeaders(200, 0);
                                Headers responseHeader = exchange.getRequestHeaders();
                                String lecture_id_username=responseHeader.get("session").toString();
				String lecture_id_usernamearray[]=lecture_id_username.split(",");
				String lecture_id=lecture_id_usernamearray[0];
				String username=lecture_id_usernamearray[1];	
                                OutputStream responseBody = exchange.getResponseBody();
                                byte[] bytes=org.apache.commons.io.IOUtils.toByteArray(exchange.getRequestBody());
                                try {
                                  	MyHashTable temp_ht=runtimeObject.getAudioServerMyHashTable();
                                        if(!temp_ht.getStatus("Audio_Post"+lecture_id)){
                                        	BufferMgt buffer_mgt= new BufferMgt();
                                                temp_ht.setValues("Audio_Post"+lecture_id,buffer_mgt);
                                     	}
                                        BufferMgt buffer_mgt=temp_ht.getValues("Audio_Post"+lecture_id);
                                        if((bytes.length>16000) && (bytes !=null) ) {
						System.out.println(bytes.length);
						buffer_mgt.putByte(bytes,username,"Audio_Post"+lecture_id);
					}
					byte[] sendbytes=buffer_mgt.sendData(username,"Audio_Post"+lecture_id);
						
                                        if((sendbytes.length >16000) && (sendbytes != null) ) {
						responseBody.write(sendbytes);
        	                        }
                                        
                                }catch(Exception e){ /*System.out.println("Error in recive from client to ref PostAudioServer class  "+e.getMessage());*/ }
                                responseBody.flush();
                                responseBody.close();
			}
		}catch(Exception ex){}
	}
	
	private javax.sound.sampled.AudioFormat getAudioFormat(){
		float sampleRate = 8000;    //8000,11025,16000,22050,44100
                int sampleSizeInBits = 16;  //8,16
		int channels = 1;           //1,2
                boolean signed = true;      //true,false
		boolean bigEndian =true;    //true,false
		return new AudioFormat(sampleRate,sampleSizeInBits,channels,signed,bigEndian);
	}

}




