package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * PostSharedScreen.java
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

import org.bss.brihaspatisync.gui.Desktop_Sharing;
import org.bss.brihaspatisync.gui.StatusPanel;

import org.bss.brihaspatisync.util.ClientObject;

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
 */

public class PostSharedScreen implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private Robot robot=null;
	private String reflectorIP ="";
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static PostSharedScreen post_screen=null;
	
	/**
 	 * Controller for the class.
 	 */ 
	public static PostSharedScreen getController(){
		if(post_screen==null)
			post_screen=new PostSharedScreen();
		return post_screen;
	}

	public PostSharedScreen(){}

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

	public BufferedImage captureScreen() {
		BufferedImage image=null;
		try{
        	      	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
             		Rectangle size = new Rectangle(0, 0, screenSize.width, screenSize.height);
                
			Point mousePosition = MouseInfo.getPointerInfo().getLocation();
			image = robot.createScreenCapture(size);
                	Polygon pointer = new Polygon(new int[]{0,16,10,8},new int[]{0,8,10,16},4);
	                Polygon pointerShadow = new Polygon(new int[]{6,21,16,14},new int[]{1,9,11,17},4);
        	        Graphics2D gr12D = image.createGraphics();
                	gr12D.translate(mousePosition.x,mousePosition.y);
	                gr12D.setColor(new Color(100,100,100,100) );
        	        gr12D.fillPolygon(pointerShadow);
                	gr12D.setColor(new Color(100,100,255,200) );
	                gr12D.fillPolygon(pointer);
        	        gr12D.setColor(Color.red);
                	gr12D.drawPolygon(pointer);
	                gr12D.dispose();
		}catch(Exception e){System.out.println("Error in capture image"+e.getCause());}
                return image;
        }


	public void run() {
		try {
			robot = new Robot();
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                        h.setName("session");
                        h.setValue(clientObject.getLectureID());
			while(flag) {
				try {
					HttpClient client = new HttpClient();
			        	PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":8884");
					client.setConnectionTimeout(8000);
					try {
						BufferedImage bimg=captureScreen();
	                                	java.io.FileOutputStream fout = new java.io.FileOutputStream("image.jpeg");
						JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(fout);
                	        	        JPEGEncodeParam enParam = jencoder.getDefaultJPEGEncodeParam(bimg);
                        	        	enParam.setQuality(0.25F, true);
	                                	jencoder.setJPEGEncodeParam(enParam);
        	                	        jencoder.encode(bimg);
	                	                fout.close();
					}catch(Exception ew){}
       	                	        postMethod.setRequestBody(new FileInputStream("image.jpeg"));
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
                	       		postMethod.getStatusLine();
                       			postMethod.releaseConnection();
                       			try {	runner.sleep(100); runner.yield(); }catch(Exception ex){}
					StatusPanel.getController().setdestopClient("yes");
				}catch(Exception e){    StatusPanel.getController().setdestopClient("no"); }
			}
		}catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
			StatusPanel.getController().setdestopClient("no");
		}
	}
}
