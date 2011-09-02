package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)HttpServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import java.io.IOException;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

//import org.bss.brihaspatisync.reflector.network.tcp.TCPClient;

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;
import org.bss.brihaspatisync.reflector.network.serverdata.HandraiseAction;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created a basic multithreaded HttpServer on 05Feb2009
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>Code review on 10April2009
 */

public class HttpGetPost {
	
	private static HttpGetPost httpserver=null;

	private HttpServer server =null;
		
	private boolean flag=false;
	
	private int server_port = RuntimeDataObject.getController().getHttpPort();
	
	public static HttpGetPost getController() throws Exception {
              	if(httpserver==null)
                       	httpserver=new HttpGetPost();
                return httpserver;
        }
	
	private HttpGetPost() throws Exception {
		InetSocketAddress addr = new InetSocketAddress(server_port);
                server = HttpServer.create(addr, 0);
                server.createContext("/", new MyPostGetHandler());
                server.setExecutor(Executors.newCachedThreadPool());
	}


	/**
         * This method is used to start HttpServer.
         */
	
        public void start() throws Exception {
		try {
			flag=true;
			server.start();
			System.out.println("HttpServer start successfully !! ");
		} catch( Exception e ) {
			System.out.println("Error in start HttpServer !! "+e.getCause());
		}
        }

        /**
         * This method is used to stop HttpServer.
         */
        public void stop() {
		if (server != null) {
                        flag=false;
                        server.stop(0);
	        }
        }
}

class MyPostGetHandler implements HttpHandler {
	private String client_ip="";
	private HandraiseAction handraiseAction=new HandraiseAction();
        public void handle(HttpExchange exchange) throws IOException {
                while(true){
                        String requestMethod = exchange.getRequestMethod();
			client_ip=exchange.getRemoteAddress().getHostName();
                        if (requestMethod.equalsIgnoreCase("POST")) {
				Headers responseHeaders = exchange.getResponseHeaders();
                                responseHeaders.set("Content-Type", "text/plain");
                                exchange.sendResponseHeaders(200, 0);
                                OutputStream responseBody = exchange.getResponseBody();

                                java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(exchange.getRequestBody()));
				String req="";
                                String line;
                                if ((line = rd.readLine()) != null) {
					req=line;
                                }
					
				/**
 				 ** split() is used to get lecture_id and the data from this request , 
				 ** where data_value[0] contains lecture_id and data_value[1] contains data.
                                 */
				
                                String data_value[]=req.split("req");
				if(data_value[1].startsWith("HandRaiseAction")){
                                        data_value[1]=java.net.URLDecoder.decode(data_value[1]).replaceAll("HandRaiseAction","");
                                        handraiseAction.setValue(data_value[1]);
					responseBody.close();
                                }else {
                                        String s=data_value[0];
                                        String strarray[]=s.split(",");
                                        data_value[0]=strarray[1];
                                        RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
                                        if(strarray[0].equals("instructor")){
                                                /** ip for master reflector */
                                                runtimeObject.setMastrerReflecterCourseid(strarray[1]);
                                        }
                                        MyHashTable temp_ht=runtimeObject.getMyHashTable();
                                        /** set All course id */
                                        runtimeObject.setCourseID(data_value[0]);
                                        if(runtimeObject.getStatusCourseidIP(data_value[0]+"#"+client_ip)){
                                                /** set course id and ip */
                                                runtimeObject.setCourseid_IP(data_value[0]+"#"+client_ip);
                                        }
                                        if(data_value[2].startsWith("parent")) {
                                                data_value[2]=data_value[2].replace("parent","");
						/*
                                                if(Util.getController().getStatus(data_value[2])){
                                                        TCPClient.getController().setcourseID(data_value[0]);
							TCPClient.getController().setparentIp(data_value[2]);
                                                        TCPClient.getController().start();
                                                }else{
                                                        TCPClient.getController().setcourseID(data_value[0]);
                                                }
						*/
                                        } 
					if(!temp_ht.getStatus(data_value[0])) {
						BufferMgt buffer_mgt= new BufferMgt();
						temp_ht.setValues(data_value[0],buffer_mgt);
						buffer_mgt.putByte(data_value[1],client_ip);
						responseBody.close();
                                        }else if(temp_ht.getStatus(data_value[0])) {
                                                BufferMgt buffer_mgt=temp_ht.getValues(data_value[0]);
						buffer_mgt.putByte(data_value[1],client_ip);
						String str=buffer_mgt.sendData(client_ip);
						/**  get Lecture id ***************/
						String data=UserListUtil.getContriller().getDataForVector(data_value[0]);
						/**  get Lecture id ***************/
						if(data.equals("")) data="nodata";
						if(str == null) str="nodata";
						data=data+"  "+str;
						responseBody.write(data.getBytes("UTF-8"));
						responseBody.flush();
                                                responseBody.close();
                                        } else {
                                             responseBody.close();
					}
                                }
                        }
                }
        }
}

