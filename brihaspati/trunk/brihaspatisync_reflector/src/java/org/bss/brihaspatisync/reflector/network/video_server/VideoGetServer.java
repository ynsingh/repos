package org.bss.brihaspatisync.reflector.network.video_server;

/**
 * VideoGetServer.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, Kanpur.
 **/

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
import java.net.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a>
 */

public class VideoGetServer {
	
	private static VideoGetServer httppostserver=null;

        private HttpServer server =null;
	
	private boolean flag=false;

	private int server_port =8092;
	
	public static VideoGetServer getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new VideoGetServer();
                return httppostserver;
        }
	
	public VideoGetServer() throws Exception{
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyVideoHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
		
	protected boolean isRunning() throws Exception {
                return flag;
        }
	
	public void start() throws Exception {
                try {
                	flag=true;
                        System.out.println(" VideoGetServer start successfully !! ");
                        server.start();
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        flag=false;
                        server.stop(0);
			System.out.println(" VideoGetServer stop successfully !! ");
                }
        }
	
}

class MyVideoHandler implements HttpHandler {
  	public void handle(HttpExchange exchange) throws IOException {
		while(true){
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("GET")) {
		              	Headers responseHeaders = exchange.getResponseHeaders();
                                responseHeaders.set("Content-Type", "text/plain");
                                exchange.sendResponseHeaders(200, 0);
                                OutputStream responseBody = exchange.getResponseBody();
				try {
					if(VideoBufferImage.getController().bufferSize()>0) {	
						BufferedImage image=VideoBufferImage.getController().get(0);
						VideoBufferImage.getController().remove();	
						ImageIO.write(image, "jpeg", responseBody);
					}
				}catch(Exception e){}
				responseBody.close();
    			}
		}
	}
}
