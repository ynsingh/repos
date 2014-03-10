package org.bss.brihaspatisync.reflector.network.ppt;

/**
 * PPTGetAndPostServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.StoreBufferMgnObject;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class PPTGetAndPostServer {
	
	private boolean flag=false;
        private HttpServer server =null;
        private static PPTGetAndPostServer pptserver=null;
	private int ppt_port=0;//RuntimeDataObject.getController().getPPtServerPort();

	public static PPTGetAndPostServer getController() throws Exception {
                if(pptserver==null)
                        pptserver=new PPTGetAndPostServer();
                return pptserver;
        }

        private PPTGetAndPostServer() throws Exception {
		InetSocketAddress addr = new InetSocketAddress(ppt_port);
                server = HttpServer.create(addr, 0);
                server.createContext("/", new PPTHandler());
                server.setExecutor(Executors.newCachedThreadPool());
        }

        public void startThread() throws Exception {
		flag=true;
		System.out.println(" Presentation GET and POST Server start successfully !! ");
               	server.start();
        }

        public void stopThread() throws Exception {
		if (server != null) {
                        flag=false;
                        server.stop(0);
                }
        }
	
	public boolean getFlag(){
		return flag;
	}
	
}
	
class PPTHandler implements HttpHandler {
	private RuntimeObject runtimeObject=RuntimeObject.getController();
        public void handle(HttpExchange exchange) throws IOException {
		try {
			/*
                	while(PPTGetAndPostServer.getController().getFlag()){
				String client_ip=exchange.getRemoteAddress().getAddress().getHostAddress();
				String requestMethod = exchange.getRequestMethod();
                        	if (requestMethod.equalsIgnoreCase("POST")) {
					MyHashTable temp_ht=runtimeObject.getPPTServerMyHashTable();
                                	if(!temp_ht.getStatus("ppt_server")){
                                        	BufferMgt buffer_mgt= new BufferMgt();
                                        	temp_ht.setValues("ppt_server",buffer_mgt);
                                	}
					Headers responseHeaders = exchange.getResponseHeaders();
                                        responseHeaders.set("Content-Type", "text/plain");
                                        exchange.sendResponseHeaders(200, 0);
                                        OutputStream responseBody = exchange.getResponseBody();
                                        InputStream in = exchange.getRequestBody();
                                        byte[] bytes = new byte[1024*1024];
                                        int count = 0;
                                        do {
                                                count+= in.read(bytes,count,bytes.length-count);
                                        } while(!(count>4&&bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));
                                        java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(bytes));
                                        try {
						if(image !=null) {
                                                        BufferMgt buffer_mgt=temp_ht.getValues("ppt_server");
                                                        buffer_mgt.putByte(image,client_ip,"ppt_server");
                                                }	
					} catch(Exception e){}
                                        responseBody.close();
        	                }
				
			  	if (requestMethod.equalsIgnoreCase("GET")) {
					Headers responseHeaders = exchange.getResponseHeaders();
	                                responseHeaders.set("Content-Type", "text/plain");
        	                        exchange.sendResponseHeaders(200, 0);
                	                OutputStream responseBody = exchange.getResponseBody();
                        	        try {
	        				MyHashTable temp_ht=runtimeObject.getPPTServerMyHashTable();
                                        	BufferMgt buffer_mgt=temp_ht.getValues("ppt_server");
                                        	java.awt.image.BufferedImage image=(java.awt.image.BufferedImage)(buffer_mgt.sendData(client_ip,"ppt_server"));
						if(image!=null)
							javax.imageio.ImageIO.write(image, "jpeg", responseBody);
        	                        }catch(Exception e){}
                	                responseBody.close();
				}
                	}
			*/
		}catch(Exception ep){}
        }
}

