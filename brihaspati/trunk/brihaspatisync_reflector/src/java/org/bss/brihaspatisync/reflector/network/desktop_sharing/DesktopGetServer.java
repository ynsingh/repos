package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * DesktopPostSharing.java
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
 */

public class DesktopGetServer {
	
	private static DesktopGetServer httppostserver=null;

        private HttpServer server =null;
	
	private boolean flag=false;

	private int server_port =RuntimeDataObject.getController().getDesktopGetPort();//8883
	
	public static DesktopGetServer getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new DesktopGetServer();
                return httppostserver;
        }
	
	public DesktopGetServer() throws Exception{
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyGetHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
		
	protected boolean isRunning() throws Exception {
                return flag;
        }
	
	public void start() throws Exception {
                try {
                	flag=true;
                        System.out.println(" DesktopGetServer start successfully !! ");
                        server.start();
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        flag=false;
                        server.stop(0);
                }
        }
	
}

class MyGetHandler implements HttpHandler {
  	public void handle(HttpExchange exchange) throws IOException {
		while(true){
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("GET")) {
		              	Headers responseHeaders = exchange.getResponseHeaders();
                                responseHeaders.set("Content-Type", "text/plain");
                                exchange.sendResponseHeaders(200, 0);
                                OutputStream responseBody = exchange.getResponseBody();
				try {
					BufferedImage image=DestopSharingUtil.getController().getBuffer().get(0);
					DestopSharingUtil.getController().getBuffer().remove();	
					ImageIO.write(image, "jpeg", responseBody);
				}catch(Exception e){}
				responseBody.close();
    			}
		}
	}
}
