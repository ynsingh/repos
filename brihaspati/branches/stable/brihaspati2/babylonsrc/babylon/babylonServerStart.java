package babylon;
/**
 * @(#)babylonServerStart.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.net.Socket;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;

import java.io.DataInputStream;
import java.io.DataOutputStream;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">  Arvind Pal  </a>
 * @author <a href="mailto:shaistashekh@gmail.com"> Shaista Bano</a>
 */

public class babylonServerStart implements Runnable {

        private static babylonServerStart httpserver=null;

        private Thread runner = null;

        private ServerSocket serverSoc =null;

        private Socket socket =null;

        private boolean flag=false;
        
	private String args[]=null;

        private int server_port = 9991;

	
	
        public static babylonServerStart getController(){
                if(httpserver==null)
                        httpserver=new babylonServerStart();
                return httpserver;
        }

        /**
         * This method is used to start HttpServer.
         */

        public void start(String strg[]) throws IOException {
                try {
                        if(serverSoc==null){
				//System.out.println("strg="+strg[0]);
				args=strg;
                                serverSoc = new ServerSocket(server_port);
				
                        }
                        if (runner == null) {
                                runner = new Thread(this);
                                flag=true;
                                runner.start();
				babylonServer bs=new babylonServer(args);
                                babylonServerObject.getController().babylonStoreObject(bs);

                        }
                } catch( IOException e ) {
                }
        }

        /**
         * This method is used to stop HttpServer.
         */
        public void stop() {
                try {
                        if (serverSoc != null) {
                                flag=false;
                                serverSoc.close();
                                serverSoc=null;
                        }
                        if(runner!=null){
                                runner.stop();
                                runner=null;
			                        }
                } catch (IOException e) {
                }
        }

        /**
         * This run() method is called by the start() to accept the all client's request.
         */
        public void run() {
                try{
			int temp=0;
                        //System.out.println(" babylonServerStart start !!! ");
			/**
			  * Server does wait for request. After socket gets open.
			  * As for as request receive to close Socket it goes in while loop
			**/

                        while(flag){
                                socket=serverSoc.accept();
                                synchronized(socket) {
					if(temp==0){
                                        	httpRequestHandler(socket);
						temp++;
                                        }else if(temp==1) {
                                                temp=0;socket.close();
                                        }
				}
                        }
                }catch(Exception e){
                }
        }
	
	public static void main(String strg[]) throws IOException{
			babylonServerStart.getController().start(strg);
	}

	private void httpRequestHandler(Socket socket) throws IOException {
		
             	DataInputStream in=new DataInputStream(socket.getInputStream());
                String request=in.readLine();
                StringTokenizer st=new StringTokenizer(request);
                String header=st.nextToken();
                if(header.equals("GET")){
                	String req=st.nextToken();
                        req=req.replaceAll("/","");
			if(req.equals("STOP")){
				try{
					babylonServerObject.getController().babylonServerObject();
				} catch(Exception e) {
					System.out.println( "Exception "+e.getMessage());
				}
				System.out.println("babylonServer Stop Successfully !!");
				socket.close();
			} else {
				socket.close();
			}	
		}
        }
}
	
