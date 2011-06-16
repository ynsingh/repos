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
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class PPTGetAndPostServer {
	
	private boolean flag=false;
        private HttpServer server =null;
        private static PPTGetAndPostServer pptserver=null;
	private int ppt_port=RuntimeDataObject.getController().getPPtServerPort();

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
        public void handle(HttpExchange exchange) throws IOException {
		try {
                	while(PPTGetAndPostServer.getController().getFlag()){
				String requestMethod = exchange.getRequestMethod();
                        	if (requestMethod.equalsIgnoreCase("POST")) {
                                	System.out.println(requestMethod+"        ppt Server Start         ");
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
					System.out.println("    image "+image);
                                        try {
						
                                                if((PPTUtil.getController().getBuffer().bufferSize()) < 50)
                                                        PPTUtil.getController().getBuffer().put(image);
                                                else
                                                        PPTUtil.getController().getBuffer().handleBuffer();
							
                                        } catch(Exception e){}
                                        responseBody.close();
        	                }
				
			  	if (requestMethod.equalsIgnoreCase("GET")) {
                      			System.out.println(requestMethod+"      ppt Server Start        ");
					Headers responseHeaders = exchange.getResponseHeaders();
	                                responseHeaders.set("Content-Type", "text/plain");
        	                        exchange.sendResponseHeaders(200, 0);
                	                OutputStream responseBody = exchange.getResponseBody();
                        	        try {
                                	        java.awt.image.BufferedImage image=PPTUtil.getController().getBuffer().get(0);
                                        	PPTUtil.getController().getBuffer().remove();
	                                        javax.imageio.ImageIO.write(image, "jpeg", responseBody);
        	                        }catch(Exception e){}
                	                responseBody.close();
				}
                	}
		}catch(Exception ep){}
        }
}

