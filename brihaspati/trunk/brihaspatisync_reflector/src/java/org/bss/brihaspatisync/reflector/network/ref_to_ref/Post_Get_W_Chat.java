package org.bss.brihaspatisync.reflector.network.ref_to_ref;

/**
 * PostSharedScreen.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class Post_Get_W_Chat implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;
	private String reflectorIP ="";
	//private int port=RuntimeDataObject.getController().getHttpPort();	
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	private static Post_GetSharedScreen post_screen=null;
	private String lecture_id="";		
	
	public Post_Get_W_Chat(String lecture_id){
		this.lecture_id=lecture_id;
	}

	/**
 	 * Start Thread
 	 */  
	public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			System.out.println("Post Get Whiteboard_and Chat start successfully !!");
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
			System.out.println("Post Get Whiteboard_and Chat stop successfully !!");
                }
        }

	public void run() {
		try {
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                        h.setName("session");
			String lecture_id1=lecture_id;
			lecture_id="["+lecture_id+"]";	
                        h.setValue(lecture_id1);
			String client_ip="127.0.0.1";
			while(flag) {
				try {
					/*
					String datastr="nodata";
					String reg=null;
					MyHashTable temp_ht=runtimeObject.getMyHashTable();
					if(!temp_ht.getStatus("ch_wb"+lecture_id)){
                                        	BufferMgt buffer_mgt= new BufferMgt();
                                                temp_ht.setValues("ch_wb"+lecture_id,buffer_mgt);
                                     	}else if(temp_ht.getStatus("ch_wb"+lecture_id)) {
                                        	BufferMgt buffer_mgt=temp_ht.getValues("ch_wb"+lecture_id);
						datastr=(String)buffer_mgt.sendData(client_ip,"ch_wb"+lecture_id);
						if(datastr == null ){
							datastr="nodata";
						}
					}
					datastr=java.net.URLEncoder.encode(datastr);
					
					HttpClient client = new HttpClient();
			        	PostMethod postMethod = new PostMethod("http://"+CommonDataObject.getController().getReflectorParentIP(lecture_id1)+":"+port);
					client.setConnectionTimeout(8000);
					postMethod.setRequestBody("student"+","+lecture_id1+"req"+datastr+"req"+reg);
	               			postMethod.setRequestHeader(h);

        	               		int statusCode1 = client.executeMethod(postMethod);
					java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(postMethod.getResponseBodyAsStream()));
                                        String str;
                                        if((str = rd.readLine()) != null) {
						try {
							java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
							String str1=(String)Tok.nextElement();
                                                        org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil.getContriller().addDataForVector(lecture_id1,str1);
							String str2=(String)Tok.nextElement();
							if(!str2.equals("nodata")) {
								BufferMgt buffer_mgt=temp_ht.getValues("ch_wb"+lecture_id);
	                                        		buffer_mgt.putByte(str2,client_ip,"ch_wb"+lecture_id);
							}
                                		}catch(Exception e){ System.out.println(" Exception in rechive data to another reflector "+e.getMessage()); }
					}
                       			postMethod.releaseConnection();*/
				} catch(Exception e){   } 
				//System.gc();
			}
		} catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
		}
	}
}
