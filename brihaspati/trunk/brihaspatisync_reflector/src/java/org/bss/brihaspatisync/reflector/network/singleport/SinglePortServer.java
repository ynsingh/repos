package org.bss.brihaspatisync.reflector.network.singleport;

/**
 * DesktopPostSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013,ETRG, IIT Kanpur.
 **/

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class SinglePortServer {
	
	private HttpServer server =null;
	private static SinglePortServer singleportserver=null;
	private int server_port = RuntimeDataObject.getController().getSinglePortServer();//8080;
	
	public static SinglePortServer getController() throws Exception {
                if(singleportserver==null)
                        singleportserver=new SinglePortServer();
                return singleportserver;
        }
	
	public SinglePortServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
	
	public void start() throws Exception {
                try {
                        server.start();
                        System.out.println(" Single Port Server start successfully !! ");
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        server.stop(0);
                        System.out.println(" Single Port Server stop successfully !! ");
                }
        }
	
}

class MyHandler implements HttpHandler {
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
  	public void handle(HttpExchange exchange) throws IOException {
		try {
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("POST")) {
      				Headers responseHeaders = exchange.getResponseHeaders();
      				responseHeaders.set("Content-Type", "text/plain");
      				exchange.sendResponseHeaders(200, 0);
				Headers responseHeader = exchange.getRequestHeaders();
				String lecture_id_username=responseHeader.get("session").toString();
                                String lecture_id_usernamearray[]=lecture_id_username.split(",");
                                String lecture_id=lecture_id_usernamearray[0];
				String username=lecture_id_usernamearray[1];
                                String type=lecture_id_usernamearray[2];
				type=type.replaceAll("]","");
				OutputStream responseBody = exchange.getResponseBody();
			        byte[] bytes =org.apache.commons.io.IOUtils.toByteArray(exchange.getRequestBody()); 
		              	try {
					if(type.equals("Desktop_Data")) {		
						MyHashTable temp_ht=runtimeObject.getDesktopServerMyHashTable();
				                if(!temp_ht.getStatus(type+lecture_id)){
                        				BufferMgt buffer_mgt= new BufferMgt();
			                	        temp_ht.setValues(type+lecture_id,buffer_mgt);
	                        		}
						
						BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
						if( (bytes.length>0) && (bytes !=null)) {
						        buffer_mgt.putByte(bytes,username,type+lecture_id);		
							buffer_mgt.sendData(username,type+lecture_id);
						}
						
						byte[] image_new=buffer_mgt.sendData(username,type+lecture_id);
                        	                if((image_new.length>0) && (image_new !=null) ){
							responseBody.write(image_new);	
						}
					}else if(type.equals("Audio_Data")) {
						MyHashTable temp_ht=runtimeObject.getAudioServerMyHashTable();
	                                        if(!temp_ht.getStatus(type+lecture_id)){
        	                                        BufferMgt buffer_mgt= new BufferMgt();
                	                                temp_ht.setValues(type+lecture_id,buffer_mgt);
                        	                }
                                	        BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
                                        	if((bytes.length) > 3000 && (bytes !=null) ) {
                                                	buffer_mgt.putByte(bytes,username,type+lecture_id);
	                                        }
	
        	                                byte[] sendbytes=buffer_mgt.sendData(username,type+lecture_id);
                	                        if(sendbytes != null){
                        	                        responseBody.write(sendbytes);
                                	        }
					}else if(type.equals("ch_wb_Data")) {
						String returndata=HttpGetPost.getController().putValue_CH_WB(new String(bytes),lecture_id,username);
						if(returndata != null) {
							responseBody.write(returndata.getBytes());
						}
					}else if(type.equals("ins_video")){
						MyHashTable temp_ht=runtimeObject.getInstructorVideoMyHashTable();
                	                        if(!temp_ht.getStatus(type+lecture_id)){
                        	                        BufferMgt buffer_mgt= new BufferMgt();
                                	                temp_ht.setValues(type+lecture_id,buffer_mgt);
                                        	}
	                                        BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
        	                                if((bytes.length>0) && (bytes !=null)) {
                	                                buffer_mgt.putByte(bytes,username,type+lecture_id);
                        	                        buffer_mgt.sendData(username,type+lecture_id);
                                	        }
                                        	byte[] sendbytes=buffer_mgt.sendData(username,type+lecture_id);
	                                        if((sendbytes.length>0) && (sendbytes !=null)){
							responseBody.write(sendbytes);
                                        	}
					} else if(type.equals("stud_video")){
						MyHashTable temp_ht=runtimeObject.getStudentVideoMyHashTable();
	                                        if(!temp_ht.getStatus(type+lecture_id)){
        	                                        BufferMgt buffer_mgt= new BufferMgt();
                	                                temp_ht.setValues(type+lecture_id,buffer_mgt);
                        	                }
                                	        BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
                                        	if((bytes.length>0) && (bytes !=null)) {
                                                	buffer_mgt.putByte(bytes,username,type+lecture_id);
	                                                buffer_mgt.sendData(username,type+lecture_id);
        	                                }
                	                        byte[] sendbytes = buffer_mgt.sendData(username,type+lecture_id);
                        	                if((sendbytes.length>0) && (sendbytes !=null)) {
                                	                responseBody.write(sendbytes);
                                        	}
					}
                	     	} catch(Exception e){} 
				responseBody.flush();
		        	responseBody.close();
    			}
		}catch(Exception ex){}
	}
}
