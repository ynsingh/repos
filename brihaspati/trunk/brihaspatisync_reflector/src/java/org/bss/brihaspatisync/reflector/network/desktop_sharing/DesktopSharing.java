package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * DesktopSharing.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;

public class DesktopSharing implements Runnable {

	private static DesktopSharing httpserver=null;

	private Thread runner = null;

	private ServerSocket server =null;

	private Socket socket =null;

	private boolean flag=false;

	private int server_port = 8883;


	public static DesktopSharing getController(){
              	if(httpserver==null)
                       	httpserver=new DesktopSharing();
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
              	}
        }

	public void run() {
		try{
			System.out.println(" DesktopSharing Start || ");
                        while(flag) {
                                socket=server.accept();
                                synchronized(socket) {
                               		String str=DesktopSharingRequestHandler.getController().ProcessClientRequest(socket);
                                        if(str.equals("unsuccessfull")){
                                        	socket.close();
                                        }else if(str.equals("successfull")){
                                        	socket.close();
                                     	}else {
                                        	socket.close();
                                       	}
                                }
                        }
		}catch(Exception e){
			System.out.println("Error in start HttpServer !!! "+e.getCause());
		}
	}
}
