package org.bss.brihaspatisync.network.singleport;

/**
 * SinglePortClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 */

import  java.util.Vector;
import  java.util.LinkedList;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.util.UtilObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class SinglePortClient implements Runnable {
	
	private Thread runner=null;
	private boolean flag=false;
	private Vector type_vector=new Vector();
	private static SinglePortClient post_screen=null;
	private int port=RuntimeDataObject.getController().client_single_port();
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
	
	/**
 	 * Controller for the class.
 	 */ 
	public static SinglePortClient getController(){
		if(post_screen==null)
			post_screen=new SinglePortClient();
		return post_screen;
	}

	public SinglePortClient(){ }
	
	public void addType(String type){
		if(!(type_vector.contains(type)))
			type_vector.add(type);
	}

	public void removeType(String type){
                type_vector.remove(type);
        }
	
	/**
 	 * Start Thread
 	 */  
	public void start(){
                if (runner == null) {
			h.setName("session");
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			ThreadController.getController().setReflectorStatusThreadFlag(true);
			System.out.println("Single Port Client start successfully !!");
		}
        }

        /**
         * Stop Thread.
         */
        
	public void stop() {
                if (runner != null) {
			flag=false;
                        runner.stop();
                        runner = null;
			System.out.println("Single Port Client stop successfully !!");
                }
        }

	/**
	 * This method is used to get data from Send Queue .
	 * and rechive data from reflector and put data to rechive queue .
	 */

	public void run() {
		while(flag && ThreadController.getController().getThreadFlag()) {
			try {
				Vector v=new Vector();
				v.addAll(type_vector);
				while((v.size()) !=0){
					String type=v.get(0).toString();
					v.remove(0);
					if(type.equals("Audio_Data")) {	
						try {	
							LinkedList sendqueue=UtilObject.getController().getSendQueue("Audio_Data");	
							for(int i=0;i<sendqueue.size();i++) {
								byte[] send_data=(byte[])sendqueue.get(0);
								sendqueue.remove(0);
								byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
								if(receive_data_fromserver.length>100) {	
	                               			                LinkedList audio_queue=UtilObject.getController().getQueue("Audio_Data");
        	                               			        audio_queue.addLast(receive_data_fromserver);		
								}
							}
						}catch(Exception e){ runner.sleep(100);runner.yield(); System.out.println("Error in Audio_Data "+e.getMessage());}
					}else if(type.equals("Desktop_Data")) {
						try {
							LinkedList sendqueue=UtilObject.getController().getSendQueue("Desktop_Data");	
							if(sendqueue.size() >0 ) {
	                                                        byte[] send_data=(byte[])sendqueue.get(0);
        	       	                                	sendqueue.remove(0);
                       		                                byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                               		                        LinkedList audio_queue=UtilObject.getController().getQueue("Desktop_Data");
                                       		                audio_queue.addLast(receive_data_fromserver);
							}
						}catch(Exception e){ System.out.println("Error in Desktop_Data "+e.getMessage());}
					} else if(type.equals("ch_wb_Data")) {
						try {
							LinkedList sendqueue=UtilObject.getController().getSendQueue("ch_wb_Data");
							if(sendqueue.size()> 0 ) {
	                                                        byte[] send_data=(byte[])sendqueue.get(0);
        	       	                                	sendqueue.remove(0);
                       		                                byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                               		                        LinkedList audio_queue=UtilObject.getController().getQueue("ch_wb_Data");
                                       		                audio_queue.addLast(receive_data_fromserver);
							}
						}catch(Exception e){ System.out.println("Error in ch_wb_Data "+e.getMessage());}
					}else if(type.equals("ins_video")) {
						try {
							LinkedList sendqueue=UtilObject.getController().getSendQueue("ins_video");
							if(sendqueue.size() >0 ) {
	                                                        byte[] send_data=(byte[])sendqueue.get(0);
        	       	                                        sendqueue.remove(0);
                        	                                byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                       	        	                        LinkedList audio_queue=UtilObject.getController().getQueue("ins_video");
                               	        	                audio_queue.addLast(receive_data_fromserver);
							}
						}catch(Exception e){ System.out.println("Error in instructor video "+e.getMessage());}
					}else if(type.equals("stud_video")) {
						try {
							LinkedList sendqueue=UtilObject.getController().getSendQueue("stud_video");
							if(sendqueue.size() >0 ) {
	                                                        byte[] send_data=(byte[])sendqueue.get(0);
        	                                                sendqueue.remove(0);
                                                                byte[] receive_data_fromserver=sendDataToReflector(send_data,type);
                       	                                        LinkedList audio_queue=UtilObject.getController().getQueue("stud_video");
                               	                                audio_queue.addLast(receive_data_fromserver);
							}
						}catch(Exception e){ System.out.println("Error in Student video "+e.getMessage());}
					}
					runner.sleep(10);runner.yield();
				}
			}catch(Exception ep){ System.out.println("Error in SinglePortClient class  "+ep.getMessage());}
			System.gc();
		}
	}
	
	/**
	 * This method is used to send the data client to reflector .
	 */
	private byte[] sendDataToReflector(byte[] send_data,String type){
		try {
			HttpClient client = new HttpClient();
                        PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":"+port);
                        client.setConnectionTimeout(8000);
			if(send_data != null)	
				postMethod.setRequestBody(new java.io.ByteArrayInputStream(send_data));
			h.setValue(clientObject.getLectureID()+","+clientObject.getUserName()+","+type);
                        postMethod.setRequestHeader(h);
			// Http Proxy Handler
			if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                        	HostConfiguration config = client.getHostConfiguration();
                                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                client.getState().setProxyCredentials(authScope, credentials);
                    	}
			int statusCode = client.executeMethod(postMethod);
			byte[] receive_data_fromserver=postMethod.getResponseBody();
                        postMethod.releaseConnection();	
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("yes");	
			ThreadController.getController().setReflectorStatusThreadFlag(true);
			return receive_data_fromserver;
		}catch(Exception e) { 
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
			ThreadController.getController().setReflectorStatusThreadFlag(false);	
		} 
		return null;
	}
}
