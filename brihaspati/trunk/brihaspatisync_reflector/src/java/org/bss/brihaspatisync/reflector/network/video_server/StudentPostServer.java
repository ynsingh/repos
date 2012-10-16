package org.bss.brihaspatisync.reflector.network.video_server;

/**
 * StudentPostServer.java
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
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a>
 */

public class StudentPostServer {
	
	private static StudentPostServer httppostserver=null;

        private HttpServer server =null;
	
	private int server_port = RuntimeDataObject.getController().get_stdpostVideoPort();//8093;
	
	public static StudentPostServer getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new StudentPostServer();
                return httppostserver;
        }
	
	public StudentPostServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyStudentPostVideoHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
	
	public void start() throws Exception {
                try {
                        server.start();
                        System.out.println(" Student VideoPostServer start successfully !! ");
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        server.stop(0);
			System.out.println(" Student VideoPostServer stop successfully !! ");
                }
        }
	
}

class MyStudentPostVideoHandler implements HttpHandler {
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
			        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(exchange.getRequestBody());
				OutputStream responseBody = exchange.getResponseBody();
		              	try {
					MyHashTable temp_ht=runtimeObject.getStudentVideoMyHashTable();
                               		if(!temp_ht.getStatus("stud_video"+lecture_id)){
					        BufferMgt buffer_mgt= new BufferMgt();
                        			temp_ht.setValues("stud_video"+lecture_id,buffer_mgt);
                                	}
					BufferMgt buffer_mgt=temp_ht.getValues("stud_video"+lecture_id);
					if((bytes.length>0) && (bytes !=null)) {
                                                buffer_mgt.putByte(bytes,username,"stud_video"+lecture_id);	
						buffer_mgt.sendData(username,"stud_video"+lecture_id);
					}
					byte[] sendbytes = buffer_mgt.sendData(username,"stud_video"+lecture_id);
                                        if((sendbytes.length>0) && (sendbytes !=null)){
						responseBody.write(sendbytes);
                                        }
	               	        }catch(Exception e){}
				
				responseBody.flush();
			        responseBody.close();
    			}
		}catch(Exception ex){}
	}
}
