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

public class Post_Get_InsVideo implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;
	private String reflectorIP ="";
	private int port=RuntimeDataObject.getController().get_inspostVideoPort();
	private RuntimeDataObject runtimeObject=RuntimeDataObject.getController();
	
	private static Post_Get_InsVideo post_screen=null;
	
	private String lecture_id="";		
	
	public Post_Get_InsVideo(String lecture_id){
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
			System.out.println("PostSharedScreen start successfully !!");
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
			System.out.println("PostSharedScreen stop successfully !!");
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
					HttpClient client = new HttpClient();
			        	PostMethod postMethod = new PostMethod("http://"+CommonDataObject.getController().getReflectorParentIP(lecture_id1)+":"+port);
					client.setConnectionTimeout(8000);
					
	               			postMethod.setRequestHeader(h);
					
					MyHashTable temp_ht=runtimeObject.getInstructorVideoMyHashTable();
                                        if(!temp_ht.getStatus("ins_video"+lecture_id)){
                                        	BufferMgt buffer_mgt= new BufferMgt();
                                                temp_ht.setValues("ins_video"+lecture_id,buffer_mgt);
                                     	}
                                        BufferMgt buffer_mgt=temp_ht.getValues("ins_video"+lecture_id);
                                        BufferedImage image=(BufferedImage)(buffer_mgt.sendData(client_ip,"ins_video"+lecture_id));
					if(image != null) {
						try {
							java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
							ImageIO.write(image, "jpeg",os);
							os.flush();
							postMethod.setRequestBody(new java.io.ByteArrayInputStream(os.toByteArray()));
							os.close();
						}catch(Exception e){}
                                     	}
        	               		int statusCode1 = client.executeMethod(postMethod);
					
					byte[] bytes1=postMethod.getResponseBody();
		                        BufferedImage image_new = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
					try {
		                        	if(image_new!=null){
                                        		buffer_mgt.putByte(image_new,client_ip,"ins_video"+lecture_id);
	                                                buffer_mgt.sendData(client_ip,"ins_video"+lecture_id);
						}
                                	}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
					
                	       		postMethod.getStatusLine();
                       			postMethod.releaseConnection();
                       			try {	runner.sleep(40); runner.yield(); }catch(Exception ex){}
				}catch(Exception e){     }
				System.gc();
			}
		}catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
		}
	}
}
