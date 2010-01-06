package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)HttpServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;
import org.bss.brihaspatisync.reflector.Reflector;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;	


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created a basic multithreaded HttpServer on 05Feb2009
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>Code review on 10April2009
 */

public class HttpServer implements Runnable {
	
	private static HttpServer httpserver=null;
	
	private Thread runner = null;	
	
	private ServerSocket server =null;
	
	private Socket socket =null;
	
	private boolean flag=false;
	
	private int server_port = Reflector.getController().getHttpPort();
	
	private MaintainLog log=MaintainLog.getController();
	
	public static HttpServer getController(){
              	if(httpserver==null)
                       	httpserver=new HttpServer();
                return httpserver;
        }

	/**
         * This method is used to start HttpServer.
         */
	
        public void start() throws IOException {
		try {
                	if(server==null){
                                server = new ServerSocket(server_port);
		       	}
                	if (runner == null) {
                        	runner = new Thread(this);
				flag=true;
                        	runner.start();
                	}
		} catch( IOException e ) {
			log.setString("Error in start HttpServer !! "+e.getCause());
		}
        }

        /**
         * This method is used to stop HttpServer.
         */
        public void stop() {
                try {
          		if (server != null) {
				flag=false;
                		server.close();
                                server=null;
                	}
			if(runner!=null){
                        	runner.stop();
                        	runner=null;
                	}
           	} catch (IOException e) {
			log.setString("Error in stopping HttpServer !! "+e.getCause());
              	}
        }
	
	/**
	 * This run() method is called by the start() to accept the all client's request.
	 */
	public void run() {
		try{
			int temp=0;
			log.setString(" HttpServer start !!! ");
			while(flag){
				socket=server.accept();
				synchronized(socket) {
					if(temp==0){
						String clientIP = socket.getInetAddress().getHostAddress();
	                                	clientIP=clientIP.replaceAll("/","");
						String str=HttpRequestHandler.getController().ProcessClientRequest(socket,clientIP);
						if(str.equals("unsuccessfull")){
							socket.close();
						}else if(str.equals("successfull")){
							socket.close();
						}else {
							socket.close();
						}
						temp++;
					}else if(temp==1) {
						temp=0;socket.close();
					}
				}
			}
		}catch(Exception e){ 
			log.setString("Error in start HttpServer !!! "+e.getCause());
		}
	}
}
