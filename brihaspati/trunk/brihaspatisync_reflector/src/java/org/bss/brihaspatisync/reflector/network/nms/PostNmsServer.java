package org.bss.brihaspatisync.reflector.network.nms;

/**
 * PostNmsServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Created on june2012
 */

public class PostNmsServer {

	private static PostNmsServer postserver=null;
    	private HttpServer server =null;
	private int server_port=2773;


	public static PostNmsServer getController() throws Exception {
    		if(postserver==null)
        		postserver=new PostNmsServer();
      		return postserver;
  	}

	public PostNmsServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyNmsPostHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}

	public void startThread() throws Exception {
    		try {
        		System.out.println(" PostNmsServer start successfully !! ");
          		server.start();
      		} catch (Exception e) { }
  	}

   	public void stopThread() throws Exception {
    		if (server != null) {
          		server.stop(0);
        		System.out.println(" PostNmsServer stop successfully !! ");
    		}
  	}
}

class MyNmsPostHandler implements HttpHandler {
	public void handle(HttpExchange exchange) throws IOException {
		try{
			String requestMethod = exchange.getRequestMethod();
			String client_ip=exchange.getRemoteAddress().getAddress().getHostAddress();
			if (requestMethod.equalsIgnoreCase("POST")) {
				Headers responseHeaders = exchange.getResponseHeaders();
				responseHeaders.set("Content-Type", "application/octet-stream");
				exchange.sendResponseHeaders(200, 0);
				Headers responseHeader = exchange.getRequestHeaders();
				OutputStream responseBody = exchange.getResponseBody();
				BufferMgt buff=new BufferMgt();
				int size=buff.getBufferSize(); 
				String data=Integer.toString(size);
           			responseBody.write(data.getBytes("UTF-8"));
				responseBody.flush();
				responseBody.close();
			}//end of if
		}catch(Exception ex){}
	}

}




