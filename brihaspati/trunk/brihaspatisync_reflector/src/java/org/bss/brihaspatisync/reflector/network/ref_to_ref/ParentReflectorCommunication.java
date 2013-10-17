package org.bss.brihaspatisync.reflector.network.ref_to_ref;

/**
 * ParentReflectorCommunication.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 **/

import  java.util.Vector;
import  java.util.LinkedList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.util.CertificateVerify;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class ParentReflectorCommunication implements Runnable {
	
        private Thread runner=null;
        private boolean flag=false;
	private String lecture_id="";
	private String parentreflector_ip="";	
	private String username="parentreflector";
	private Vector type_vector=new Vector();
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	private int server_port = CertificateVerify.getController().getSinglePortServer();
        private org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();

        public ParentReflectorCommunication() { 
		type_vector.add("Audio_Data");type_vector.add("Desktop_Data");type_vector.add("ch_wb_Data");type_vector.add("ins_video");type_vector.add("stud_video");
	}

	public void start(String session_id,String parentreflector_ip){
                if (runner == null) {
                        flag=true;
			lecture_id=session_id;
			lecture_id=lecture_id.trim();
			this.parentreflector_ip=parentreflector_ip;
                        h.setName("session");
                        runner = new Thread(this);
                        runner.start();
                        System.out.println("Parent Reflector Communication Client start successfully !!");
                }
        }
	
	public void stop() {
                if (runner != null) {
                        flag=false;
                        runner.stop();
                        runner = null;
                        System.out.println("Parent Reflector Communication Client stop successfully !!");
                }
        }
	public void run() {
                while( flag ) {
                        try {
                                Vector v=new Vector();
                                v.addAll(type_vector);
                                while((v.size()) !=0) {
                                        String type=v.get(0).toString();
                                        v.remove(0);
					/*
                                        if(type.equals("Audio_Data")) {
                                                try {	
	                                                if(!MyHashTable.getStatusBufferMgtObject(type+lecture_id)) {
								MyHashTable.setBufferMgtObject(type+lecture_id,new BufferMgt());
                        	                        }
	                               	                BufferMgt buffer_mgt=MyHashTable.getBufferMgtObject(type+lecture_id);
        	                                        byte[] sendbytes=buffer_mgt.sendData_AudioIncreasePointer(username);
							buffer_mgt.sendData(username,type+lecture_id);
							byte[] rechive_bytes=sendDataToReflector(sendbytes,type);
							if((rechive_bytes.length>0) && (rechive_bytes !=null) ) {
                                                                buffer_mgt.putAudioBytes(bytes,username);
                                                        }
                                                } catch(Exception e){ System.out.println("Error in Audio_Data in reflector to reflector "+e.getMessage());}
                                        }else if(type.equals("Desktop_Data")) {
						try {
                                                        if(!MyHashTable.getStatusBufferMgtObject(type+lecture_id)){
								MyHashTable.setBufferMgtObject(type+lecture_id,new BufferMgt());
                                                        }
                                                        BufferMgt buffer_mgt=MyHashTable.getBufferMgtObject(type+lecture_id);
                                                        byte[] sendbytes=buffer_mgt.sendData_AudioIncreasePointer(username);
                                                        byte[] rechive_bytes=sendDataToReflector(sendbytes,type);
                                                        if((rechive_bytes.length>0) && (rechive_bytes !=null) ){
                                                                buffer_mgt.putByte(rechive_bytes,username);
                                                        }
						}catch(Exception e){ System.out.println("Error in Desktop_Data in reflector to reflector "+e.getMessage());}
					} else if(type.equals("ch_wb_Data")) {
						try {
                                                        if(!MyHashTable.getStatusBufferMgtObject(type+lecture_id)){
                                                                BufferMgt buffer_mgt= new BufferMgt();
                                                                temp_ht.setValues(type+lecture_id,buffer_mgt);
                                                        }

                                                        BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
                                                        byte[] sendbytes=buffer_mgt.sendData(username,type+lecture_id);
							String chat_wb_data=new String(sendbytes);
							if(sendbytes == null)
								chat_wb_data="nodata";
                                                        chat_wb_data=chat_wb_data+"req"+"null";
                                                        byte[] rechive_bytes=sendDataToReflector(chat_wb_data.getBytes(),type);
                                                        if((rechive_bytes.length>0) && (rechive_bytes !=null)) {
								java.util.StringTokenizer Tok = new java.util.StringTokenizer(new String(rechive_bytes));
                		                                if (Tok.hasMoreElements()) {
                                		                        String str1=(String)Tok.nextElement();
                                                		        str1=(String)Tok.nextElement();
		                                                        str1=java.net.URLDecoder.decode(str1);
									if(!(str1.equals("nodata"))){
										str1 = java.net.URLEncoder.encode(str1,"UTF-8");
	                                                                	buffer_mgt.putByte(str1.getBytes(),username,type+lecture_id);
									}
								}
                                                        }
						} catch(Exception e){ System.out.println("Error in chat and whiteboard in reflector to reflector "+e.getMessage());}
					}else if(type.equals("ins_video")) {
						try {
	                                                if(!MyHashTable.getStatusBufferMgtObject(type+lecture_id)){
        	                                                BufferMgt buffer_mgt= new BufferMgt();
                	                                        temp_ht.setValues(type+lecture_id,buffer_mgt);
                        	                        }
                                	                BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
        	                                        byte[] sendbytes=buffer_mgt.sendData(username,type+lecture_id);
							byte[] rechive_bytes=sendDataToReflector(sendbytes,type);
                                                        if((rechive_bytes.length>0) && (rechive_bytes !=null) ) {
                                                                buffer_mgt.putByte(rechive_bytes,username,type+lecture_id);
                                                        }
                                                }catch(Exception e){ System.out.println("Error in Instructor video in reflector to reflector "+e.getMessage());}
					}else if(type.equals("stud_video")) {
						try {
                                        	        if(!MyHashTable.getStatusBufferMgtObject(type+lecture_id)){
                                	                        BufferMgt buffer_mgt= new BufferMgt();
                        	                                temp_ht.setValues(type+lecture_id,buffer_mgt);
                	                                }
        	                                        BufferMgt buffer_mgt=temp_ht.getValues(type+lecture_id);
                        	                        byte[] sendbytes = buffer_mgt.sendData(username,type+lecture_id);
							byte[] rechive_bytes=sendDataToReflector(sendbytes,type);
							if((rechive_bytes.length>0) && (rechive_bytes !=null) ){
                                                                buffer_mgt.putByte(rechive_bytes,username,type+lecture_id);
                                                        }
                                                }catch(Exception e){ System.out.println("Error in student video in reflector to reflector "+e.getMessage());}
					}*/
                                        runner.sleep(10);runner.yield();
                                }
                        }catch(Exception ep){ System.out.println("Error in SinglePortClient class  "+ep.getMessage());}
                        System.gc();
                }
        }
	
	/**
	 * This method is used to send the data client to reflector .
	 **/
	private byte[] sendDataToReflector(byte[] send_data,String type){
                try {
                        HttpClient client = new HttpClient();
			System.out.println("http://"+parentreflector_ip+":"+server_port);
                        PostMethod postMethod = new PostMethod("http://"+parentreflector_ip+":"+server_port);
                        client.setConnectionTimeout(8000);
                        if(send_data != null)
                                postMethod.setRequestBody(new java.io.ByteArrayInputStream(send_data));
                        h.setValue(lecture_id+","+username+","+type);
                        postMethod.setRequestHeader(h);
                        int statusCode = client.executeMethod(postMethod);
                        byte[] receive_data_fromserver=postMethod.getResponseBody();
                        postMethod.releaseConnection();
			System.out.println("   receive_data_fromserver       "+receive_data_fromserver.length);
                        return receive_data_fromserver;
		}catch(Exception e) { }
                return null;
        }
}		
