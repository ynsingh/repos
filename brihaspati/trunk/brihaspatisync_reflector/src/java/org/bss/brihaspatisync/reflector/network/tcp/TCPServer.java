package org.bss.brihaspatisync.reflector.network.tcp;

/*
 * TCPServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;

import java.io.IOException;

import org.bss.brihaspatisync.reflector.Reflector;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.util.CertificateVerify;
//import org.bss.brihaspatisync.reflector.network.util.RuntimeObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class TCPServer implements Runnable{

	//stores data which is also forwarded to other peers which are connected with this client.
	
	private ServerSocket server=null;
        
	private Socket skt=null;
        
	private Thread runner = null;
       
	private MaintainLog log=MaintainLog.getController();
 
	private int TCP_Port=CertificateVerify.getController().getTcpPort();
	
	private static TCPServer tcpr=null;
	
	/**
        * create Class Controller.
      	* A single instance of TCPReceiver object is maintained. Every class can use this method to get
	* the reference to this instance.	
	*/
        
	public static TCPServer getController(){
                if(tcpr==null){
                        tcpr=new TCPServer();
                }
                return tcpr;
        }

	/**
         * Start TCPReceive Thread.
         */
        public void start() throws IOException {
		if(server==null){
                        try {
                                server = new ServerSocket(TCP_Port );
                        } catch( IOException e ) {
				log.setString("Error during TCPReceive server initialization"+e.getMessage());
                        }
                }
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
		}
		log.setString("TCPReceiver thread start");
	}

	/**
         * Stop TCPReceive Thread.
         */
        public void stop() {
                if (server != null) {
                        try {
                                server.close();
				server=null;
                        } catch (IOException ioe) {
				log.setString("Error during stoping TCPReceive"+ioe.getMessage());
			}
		}
		if(runner!=null){
			runner.stop();
			runner=null;
		}
		log.setString("TCPReceiver thread stop");
        }

        /**
         * This Accept all new incomming tcp connection.
         */
	public void run(){
        	while(true){	
                	try {
				skt = server.accept();
				String clientIP = skt.getInetAddress().getHostAddress();
				clientIP=clientIP.replaceAll("/","");
				String localIP = skt.getLocalAddress().getHostAddress();
				localIP=localIP.replaceAll("/","");
				if(!(clientIP.equals(localIP))){
					new MultiServerThread(skt,clientIP).start();
					log.setString("start new thread in count  MultiServerThread---->"+clientIP);
				}
			}catch(Exception e) {
				log.setString("Error in TCPServer.java "+e.getMessage());
			}
               	}
        }
}
