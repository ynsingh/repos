package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * DesktopPostSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,ETRG, IIT Kanpur.
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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

public class DesktopPostServer {
	
	private static DesktopPostServer httppostserver=null;

        private HttpServer server =null;
	
	private boolean flag=false;

	private int server_port = RuntimeDataObject.getController().getDesktopPostPort();//8884;
	
	public static DesktopPostServer getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new DesktopPostServer();
                return httppostserver;
        }
	
	public DesktopPostServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
		
	protected boolean isRunning() throws Exception {
                return flag;
        }
	
	public void start() throws Exception {
                try {
                	flag=true;
                        System.out.println(" DesktopPostServer start successfully !! ");
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

class MyHandler implements HttpHandler {
  	public void handle(HttpExchange exchange) throws IOException {
		while(true){
			String requestMethod = exchange.getRequestMethod();
			if (requestMethod.equalsIgnoreCase("POST")) {
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
		                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
		              	try {
					if((BufferImage.getController().size()) < 50)
                                        	BufferImage.getController().put(image);
					else
						BufferImage.getController().handleBuffer();
                                }catch(Exception e){}
		                responseBody.close();
    			}
		}
	}
}
