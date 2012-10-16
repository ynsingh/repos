package org.bss.brihaspatisync.network.video_capture;

/**
 * PostVideoCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.io.File;
import java.io.FileInputStream;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.gui.VideoPanel;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;

import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.apache.commons.httpclient.auth.AuthScope;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGEncodeParam;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */

public class PostVideoCapture implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;
	private boolean getflag=false;
	private String reflectorIP ="";
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static PostVideoCapture post_capture=null;

	/**
 	 * Controller for the class.
 	 */ 
	public static PostVideoCapture getController(){
		if(post_capture==null)
			post_capture=new PostVideoCapture();
		return post_capture;
	}

	public PostVideoCapture(){}

	/**
 	 * Start Thread
 	 */  
	public void start(boolean getscreen){
                if (runner == null) {
			flag=true;
			
			getflag=getscreen;
			if(getflag)
				org.bss.brihaspatisync.gui.JoinSessionPanel.getController().getAV_Panel().add(org.bss.brihaspatisync.gui.VideoPanel.getController().createGUI());
                        runner = new Thread(this);
                        runner.start();
			System.out.println("Post Video Capture  start successfully !!");
		}
        }

        /**
         * Stop Thread.
         */
        
	public void stop() {
                if (runner != null) {
			flag=false;
			getflag=false;
                        runner.stop();
                        runner = null;
			System.out.println("Post Video Capture  stop successfully !!");
                }
        }

	public void run() {
		org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                h.setName("session");
                h.setValue(clientObject.getLectureID()+","+clientObject.getUserName());
		int port =runtime_object.client_inspostvedio_port();
		while(flag && ThreadController.getController().getThreadFlag()) {
			try {
				HttpClient client = new HttpClient();
			        PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":"+port);
				client.setConnectionTimeout(800000);
				if(!getflag) {	
					/***  send data to reflector ********/
					if(BufferImage.getController().bufferSize()>0) {
						BufferedImage bimg=BufferImage.getController().get(0);
						BufferImage.getController().remove();
						java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
                                	        JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(os);
                                       		JPEGEncodeParam enParam = jencoder.getDefaultJPEGEncodeParam(bimg);
	                                       	enParam.setQuality(0.25F, true);
	        	                        jencoder.setJPEGEncodeParam(enParam);
        	        	                jencoder.encode(bimg);
        	        			postMethod.setRequestBody(new java.io.ByteArrayInputStream(os.toByteArray()));
					}
				}
               			postMethod.setRequestHeader(h);
					
				// Http Proxy Handler
				if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
        	                	HostConfiguration config = client.getHostConfiguration();
                	                config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                        	        Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                	AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                       	client.getState().setProxyCredentials(authScope, credentials);
	                      	}
	
        	               	int statusCode1 = client.executeMethod(postMethod);
				if(getflag) {
                                	byte[] bytes1=postMethod.getResponseBody();
                                        BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
                                        try {
                                        	if(image != null)
                                                	org.bss.brihaspatisync.gui.VideoPanel.getController().runInstructorVidio(image);
                                     	}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
                              	}

                	       	postMethod.getStatusLine();
                       		postMethod.releaseConnection();
                       		try { runner.sleep(20); runner.yield(); }catch(Exception ex){}
			}catch(Exception e){	
				try { runner.sleep(1000);runner.yield();}catch(Exception ex){}
				System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
			}
			System.gc();	
		}
	}
}
