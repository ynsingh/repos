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
import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class DesktopPostServer {
	
	private static DesktopPostServer httppostserver=null;

        private HttpServer server =null;
	
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
	
	public void start() throws Exception {
                try {
                        server.start();
                        System.out.println(" DesktopPostServer start successfully !! ");
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        server.stop(0);
                        System.out.println(" DesktopPostServer stop successfully !! ");
                }
        }
	
}

class MyHandler implements HttpHandler {
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
  	public void handle(HttpExchange exchange) throws IOException {
		try {
			String requestMethod = exchange.getRequestMethod();
			String client_ip=exchange.getRemoteAddress().getAddress().getHostAddress();
			if (requestMethod.equalsIgnoreCase("POST")) {
      				Headers responseHeaders = exchange.getResponseHeaders();
      				responseHeaders.set("Content-Type", "text/plain");
      				exchange.sendResponseHeaders(200, 0);
				Headers responseHeader = exchange.getRequestHeaders();
				String lecture_id=responseHeader.get("session").toString();
				OutputStream responseBody = exchange.getResponseBody();
			        byte[] bytes =org.apache.commons.io.IOUtils.toByteArray(exchange.getRequestBody()); 
			        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
		              	try {
					MyHashTable temp_ht=runtimeObject.getDesktopServerMyHashTable();
			                if(!temp_ht.getStatus("Desktop_Post"+lecture_id)){
                        			BufferMgt buffer_mgt= new BufferMgt();
			                        temp_ht.setValues("Desktop_Post"+lecture_id,buffer_mgt);
                        		}
					
					BufferMgt buffer_mgt=temp_ht.getValues("Desktop_Post"+lecture_id);
					if(image != null) {
					        buffer_mgt.putByte(image,client_ip,"Desktop_Post"+lecture_id);		
						buffer_mgt.sendData(client_ip,"Desktop_Post"+lecture_id);
					}
					
					BufferedImage image_new=(BufferedImage)(buffer_mgt.sendData(client_ip,"Desktop_Post"+lecture_id));
                                        if(image_new != null){
						ImageIO.write(image_new, "jpeg", responseBody);
					}
				
                	     	} catch(Exception e){}
				responseBody.flush();
		        	responseBody.close();
    			}
		}catch(Exception ex){}
	}
}
