package org.bss.brihaspatisync.reflector.network.singleport;

/**
 * DesktopPostSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013,ETRG, IIT Kanpur.
 **/

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.CertificateVerify;
import org.bss.brihaspatisync.reflector.util.RuntimeObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.StoreBufferMgnObject;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class SinglePortServer {
	
	private HttpServer server =null;
	private static SinglePortServer singleportserver=null;
	private int server_port = CertificateVerify.getController().getSinglePortServer();//8080;
	
	public static SinglePortServer getController() throws Exception {
                if(singleportserver==null)
                        singleportserver=new SinglePortServer();
                return singleportserver;
        }
	
	public SinglePortServer() throws Exception {
    		InetSocketAddress addr = new InetSocketAddress(server_port);
    		server = HttpServer.create(addr, 0);
		server.createContext("/", new MyHandler());
    		server.setExecutor(Executors.newCachedThreadPool());
  	}
	
	public void start() throws Exception {
                try {
                        server.start();
                        System.out.println(" Single Port Server start successfully !! "+server_port);
                } catch (Exception e) { }
        }

        public void stop() throws Exception {
                if (server != null) {
                        server.stop(0);
                        System.out.println(" Single Port Server stoped successfully !! ");
                }
        }
	
}

class MyHandler implements HttpHandler {
	private RuntimeObject runtimeObject=RuntimeObject.getController();
	private UserConnectionController user_connection_cotroller=new UserConnectionController();
	
  	public synchronized void handle(HttpExchange exchange) throws IOException {
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
				lecture_id=lecture_id.replace("[","");
				lecture_id=lecture_id.trim();
				String username=(lecture_id_usernamearray[1]).trim();
                                String type=lecture_id_usernamearray[2];
				type=type.replaceAll("]","");
				OutputStream responseBody = exchange.getResponseBody();
			        byte[] bytes =org.apache.commons.io.IOUtils.toByteArray(exchange.getRequestBody()); 
		              	try {
					user_connection_cotroller.setLoginidAndTime(lecture_id,username);
					if(type.equals("Desktop_Data")) {		
				                if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id))
			                	        StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
						BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
						if( (bytes.length>0) && (bytes !=null)) 
						        buffer_mgt.putByte(bytes,username);		
						byte[] image_new=buffer_mgt.sendDataAndIncreasePointer(username);
                        	                if((image_new.length>0) && (image_new !=null) )
							responseBody.write(image_new);	
					} else if(type.equals("Audio_Data")) {
	                                        if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id))
                	                                StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
                                	        BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
                                        	if( ((bytes.length)>0 ) && (bytes !=null) ) 
                                                	buffer_mgt.putAudioBytes(bytes,username);
        	                                byte[] sendbytes=buffer_mgt.sendData_AudioIncreasePointer(username);
                	                        if((sendbytes.length>0) && (sendbytes !=null)){
                        	                        responseBody.write(sendbytes);
                                	        }
					} else if(type.equals("UserList_Data")) {
						if(runtimeObject.getReflectorRunning().equals("client")) {	
							if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id)){
	                                                        StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
        	                                        }
                	                                BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
                        	                        if((bytes.length>0) && (bytes !=null)) {
                                	                        buffer_mgt.putByte(bytes,username);
                                        	        }
                                                	byte[] sendbytes=buffer_mgt.sendDataAndIncreasePointer(username);
	                                                if((sendbytes.length>0) && (sendbytes !=null)){
        	                                                responseBody.write(sendbytes);
                	                                }
						} else {
							if( ((bytes.length) > 0) && (bytes !=null) ) {
                                                                HttpGetPost.getController().putValue_CH_WB(bytes,lecture_id,username);
                                                        }
                                                        String userlist_data=UserListUtil.getContriller().getDataForVector(lecture_id);
                                                        if(userlist_data.equals(""))
                                                                userlist_data="nodata";
                                                        responseBody.write(userlist_data.getBytes());
						} 
					} else if(type.equals("Chat_Wb_Data")) {
						if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id)) {
                                                        StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
                                                }
                                                BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
                                                if((bytes.length>0) && (bytes !=null)) {
                                                        buffer_mgt.putByte(bytes,username);
                                                }
                                                byte[] sendbytes=buffer_mgt.sendDataAndIncreasePointer(username);
                                                if((sendbytes.length>0) && (sendbytes !=null)){
                                                        responseBody.write(sendbytes);
                                                }
					} else if(type.equals("ins_video")) {
                	                        if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id)) {
                                	                StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
                                        	}
	                                        BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
        	                                if((bytes.length>0) && (bytes !=null)) {
                	                                buffer_mgt.putByte(bytes,username);
                                	        }
                                        	byte[] sendbytes=buffer_mgt.sendDataAndIncreasePointer(username);
	                                        if((sendbytes.length>0) && (sendbytes !=null)){
							responseBody.write(sendbytes);
                                        	}
					} else if(type.equals("stud_video")){
	                                        if(!StoreBufferMgnObject.getStatusBufferMgtObject(type+lecture_id)){
                	                                StoreBufferMgnObject.setBufferMgtObject(type+lecture_id,new BufferMgt());
                        	                }
                                	        BufferMgt buffer_mgt=StoreBufferMgnObject.getBufferMgtObject(type+lecture_id);
                                        	if((bytes.length>0) && (bytes !=null)) {
                                                	buffer_mgt.putByte(bytes,username);
        	                                }
                	                        byte[] sendbytes = buffer_mgt.sendDataAndIncreasePointer(username);
                        	                if((sendbytes.length>0) && (sendbytes !=null)) {
                                	                responseBody.write(sendbytes);
                                        	}
					} else if(type.equals("parent_ref")) {
						try {	
							if((bytes.length>0) && (bytes !=null)) 
								runtimeObject.setParentReflector(new String(bytes));
							String parent_ref=runtimeObject.getParentReflector();
							if(!parent_ref.equals(""))
								responseBody.write(parent_ref.getBytes("UTF-8"));	
						} catch(Exception e) {}
					} else if(type.equals("stop")) {
						org.bss.brihaspatisync.reflector.LogoutReflector.stopReflector();
						byte[] sendbytes="Reflector is Successfully Loggedout ".getBytes("UTF-8");
						responseBody.write(sendbytes);
						org.bss.brihaspatisync.reflector.network.singleport.SinglePortServer.getController().stop();
                		                System.exit(0);	
					}
					
                	     	} catch(Exception e){} 
				responseBody.flush();
		        	responseBody.close();
    			}
		}catch(Exception ex){}
	}
}
