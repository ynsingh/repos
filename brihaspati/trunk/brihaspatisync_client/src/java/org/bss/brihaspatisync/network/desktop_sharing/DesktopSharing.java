package org.bss.brihaspatisync.network.desktop_sharing;

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
	
	private boolean flag=false;

	private Socket socket =null;

	private Thread runner = null;
	
	private int server_port = 8884;
	
	private ServerSocket server =null;
	
	private org.bss.brihaspatisync.network.Log log=org.bss.brihaspatisync.network.Log.getController();

	private static DesktopSharing httpserver=null;

	public static DesktopSharing getController(){
              	if(httpserver==null)
                       	httpserver=new DesktopSharing();
                return httpserver;
        }

	/**
         * This method is used to start DesktopSharing.
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
			log.setLog("IOException in DesktopSharing Server in start method !!! "+e.getCause());
		}
        }

        /**
         * This method is used to stop DesktopSharing.
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
			log.setLog("IOException in DesktopSharing Server in stop method !!! "+e.getCause());
              	}
        }

	public void run() {
		try{
                        log.setLog(" DesktopSharing Server Start !! ");
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
			log.setLog("IOException in DesktopSharing Server !!! "+e.getCause());
		}
	}
}
