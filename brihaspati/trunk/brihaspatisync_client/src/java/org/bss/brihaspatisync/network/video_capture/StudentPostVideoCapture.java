package org.bss.brihaspatisync.network.video_capture;

/**
 * StudentPostVideoCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
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

import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.apache.commons.httpclient.auth.AuthScope;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */

public class StudentPostVideoCapture implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private String reflectorIP ="";
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static StudentPostVideoCapture post_capture=null;

	/**
 	 * Controller for the class.
 	 */ 
	public static StudentPostVideoCapture getController(){
		if(post_capture==null)
			post_capture=new StudentPostVideoCapture();
		return post_capture;
	}

	public StudentPostVideoCapture(){}

	/**
 	 * Start Thread
 	 */  
	public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			System.out.println("Student Post Video Capture  start successfully !!");
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
			System.out.println("Student Post Video Capture  stop successfully !!");
                }
        }

	public void run() {
		while(flag) {
			try {
				if(StudentBufferImage.getController().bufferSize()>0) {
					HttpClient client = new HttpClient();
			        	PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":8093");
					client.setConnectionTimeout(8000);
	                       		ImageIO.write(StudentBufferImage.getController().get(0),"jpeg", new File("image2.jpeg"));
        	               		postMethod.setRequestBody(new FileInputStream("image2.jpeg"));
               				postMethod.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");
					
					// Http Proxy Handler
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
        	                                HostConfiguration config = client.getHostConfiguration();
                	                        config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                        	                Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                	        AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                        	client.getState().setProxyCredentials(authScope, credentials);
	                                }
	
        	               		int statusCode1 = client.executeMethod(postMethod);
                	       		postMethod.getStatusLine();
                       			postMethod.releaseConnection();
					StudentBufferImage.getController().remove();
                       			try {
	                               		Thread.sleep(10);
        	                       	}catch(Exception ex){}
				} else {
					try {
                                                Thread.sleep(100);
                                        }catch(Exception ex){}
				}	
			}catch(Exception e){
				System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
			}
		}
	}
}
