package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)HttpRequestHandler.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.net.Socket;

import java.util.Vector;
import java.util.StringTokenizer;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

import org.bss.brihaspatisync.reflector.network.tcp.TCPClient;
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;

import org.bss.brihaspatisync.reflector.network.serverdata.HandraiseAction;	

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created this HttpRequestHandler on 01Feb2009
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>Code review on 15April2009
 */

public class HttpRequestHandler {

	private String client_ip="";
	private Socket client_Socket=null;
	private static HttpRequestHandler req_handler=null;

	private HttpRequestHandler(){}
	private MaintainLog log=MaintainLog.getController();
	
	public synchronized static HttpRequestHandler getController()  {  
		if( req_handler == null ) {  
       			req_handler = new HttpRequestHandler();  
     		}  
     		return req_handler;  
   	}  
	
	protected synchronized String ProcessClientRequest(Socket socket ,String ip) throws Exception
	{
		try{
			if((socket!=null)){
                        	this.client_Socket=socket;
                        	this.client_ip=ip;
			}else{
				log.setString("Insufficient parameter to process this http request");
				return "unsuccessfull";
			}
			DataInputStream in=new DataInputStream(client_Socket.getInputStream());
			DataOutputStream output = new DataOutputStream(client_Socket.getOutputStream());
			String request=in.readLine();
			StringTokenizer st=new StringTokenizer(request);
			String header=st.nextToken();
			if(header.equals("GET")){
				String req=st.nextToken();
				req=req.replaceAll("/","");
				
				/**
				 * split() is used to get lecture_id and the data from this request , 
				 * where data_value[0] contains lecture_id and data_value[1] contains data.
				 */
				String data_value[]=req.split("req");
				if(data_value[0].startsWith("lect_id=")){
					String course_id=data_value[0].replaceAll("lect_id=","");
					String data=UserListUtil.getContriller().getDataForVector(course_id);
					if(!data.equals("")) {
						output.writeUTF(data);
                                                output.flush();
                                                output.close();
                                                in.close();
                                                return "successfull";
					}else {
                                                output.flush();
                                                output.close();
                                                in.close();
                                                return "UnSuccessfull";

                                        }
				}else if(data_value[1].startsWith("HandRaiseAction")){
                                        data_value[1]=java.net.URLDecoder.decode(data_value[1]).replaceAll("HandRaiseAction","");
                                        HandraiseAction.getController().setValue(data_value[1]);
                                        output.flush();
                                        output.close();
                                        in.close();
                                        return "UnSuccessfull";
				}else {	
					String s=data_value[0];
					String strarray[]=s.split(",");
	                                data_value[0]=strarray[1];
					RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	                                if(strarray[0].equals("instructor")){  
						/** ip for master reflector */
        	                                runtimeObject.setMastrerReflecterCourseid(strarray[1],client_ip);
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
						if(Util.getController().getStatus(data_value[2])){
							TCPClient.getController().setcourseID(data_value[0]);
                                	        	TCPClient.getController().setparentIp(data_value[2]);
                                        		TCPClient.getController().start();
						}else{
							TCPClient.getController().setcourseID(data_value[0]);
						}
					} if(!temp_ht.getStatus(data_value[0])) {
						// creating a new buffer_mgt object for this lecture_id.
                        	        	BufferMgt buffer_mgt= new BufferMgt();
						//set this buffer_mgt and lecture_id to hashTable.
                                       		temp_ht.setValues(data_value[0],buffer_mgt);
						//put data value, received from this client to buffer_mgt. 
        	                             	buffer_mgt.putByte(data_value[1],client_ip);
						in.close();
                                	      	output.flush();
                                     		output.close();
						return "successfull";	
							
					}else if(temp_ht.getStatus(data_value[0])) {
						BufferMgt buffer_mgt=temp_ht.getValues(data_value[0]);
						// put data and ip ,received from this client.
						buffer_mgt.putByte(data_value[1],client_ip);
						// get data from it's buffer to sent this data to client. 
						String str=buffer_mgt.sendData(client_ip);
						if(str !=null ){
							log.setString("data on reflector !! "+str);
							output.writeUTF(str);
                                			output.flush();
						}
					       	output.close();
						in.close();
                		        	return "successfull";
					} else {
						log.setString("else in HttpRequestHandelerin.java !!");
						output.flush();
        	                       		in.close();
                	               		output.close();
						return "unsuccessfull";
					}
				}
			}
		}catch(Exception e){
			log.setString("Error in process Http Request !! "+e);
		}
		return "unsuccessfull";
	}
}
