package org.bss.brihaspatisync.reflector.network.video_server;

/**
 * StudentVideoGetServer.java
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
import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a>
 */

public class StudentVideoGetServer {
	
	private static StudentVideoGetServer httppostserver=null;

        private HttpServer server =null;
	
	private boolean flag=false;

	private int server_port =8094;
	
	public static StudentVideoGetServer getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new StudentVideoGetServer();
                return httppostserver;
        }
	
	public StudentVideoGetServer() throws Exception{
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyStudentVideoHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
		
	protected boolean isRunning() throws Exception {
                return flag;
        }
	
	public void start() throws Exception {
                try {
                	flag=true;
                        server.start();
                        System.out.println(" StudentVideoGetServer start successfully !! ");
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        flag=false;
                        server.stop(0);
			System.out.println(" StudentVideoGetServer stop successfully !! ");
                }
        }
	
}

class MyStudentVideoHandler implements HttpHandler {
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
  	public void handle(HttpExchange exchange) throws IOException {
		while(true){
			String requestMethod = exchange.getRequestMethod();
			String client_ip=exchange.getRemoteAddress().getAddress().getHostAddress();
			if (requestMethod.equalsIgnoreCase("GET")) {
		              	Headers responseHeaders = exchange.getResponseHeaders();
                                responseHeaders.set("Content-Type", "text/plain");
                                exchange.sendResponseHeaders(200, 0);
                                OutputStream responseBody = exchange.getResponseBody();
				try {
                                	MyHashTable temp_ht=runtimeObject.getStudentVideoMyHashTable();
                                        BufferMgt buffer_mgt=temp_ht.getValues("stud_video");
                                        BufferedImage image=(BufferedImage)(buffer_mgt.sendData(client_ip,"stud_video"));
                                        ImageIO.write(image, "jpeg", responseBody);
				}catch(Exception e){}
				responseBody.close();
    			}
		}
	}
}
