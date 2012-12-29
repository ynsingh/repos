package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * PostSharedScreen.java
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

import org.bss.brihaspatisync.gui.Desktop_Sharing;
import org.bss.brihaspatisync.gui.StatusPanel;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.apache.commons.httpclient.auth.AuthScope;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class Post_GetSharedScreen implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;
	private Robot robot=null;
	private boolean getflag=false;
	private String reflectorIP ="";
		
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static Post_GetSharedScreen post_screen=null;
	private boolean screen_mode=false;		
	/**
 	 * Controller for the class.
 	 */ 
	public static Post_GetSharedScreen getController(){
		if(post_screen==null)
			post_screen=new Post_GetSharedScreen();
		return post_screen;
	}

	public Post_GetSharedScreen(){}

	/**
 	 * Start Thread
 	 */  
	public void start(boolean getscreen){
                if (runner == null) {
			flag=true;
			getflag=getscreen;
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
			getflag=false;
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
                        h.setValue(clientObject.getLectureID()+","+clientObject.getUserName());
			
			int port=runtime_object.client_postsharescreen_port(); 
			while(flag &&ThreadController.getController().getThreadFlag()) {
				try {
					HttpClient client = new HttpClient();
			        	PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":"+port);
					client.setConnectionTimeout(8000);
					/****   send the image to reflector **********/
					if(!getflag) {
						BufferedImage image=captureScreen();
						java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
						ImageIO.write(image, "jpeg", os);
       	                		        postMethod.setRequestBody(new java.io.ByteArrayInputStream(os.toByteArray()));
						os.flush();	
						os.close();	
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
					/****   receive the image from reflector **********/
					if(getflag) {
						byte[] bytes1=postMethod.getResponseBody();
		                                BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
						try {
		                                        if(image!=null){
								if(!screen_mode)
                		                                	Desktop_Sharing.getController().showImage(image);
								else
                		                                	org.bss.brihaspatisync.gui.FullScreen_Mode.getController().showImage(image);
							}
                                		}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
					}
                	       		postMethod.getStatusLine();
                       			postMethod.releaseConnection();
                       			try {	runner.yield(); }catch(Exception ex){}
					StatusPanel.getController().setdestopClient("yes");
				}catch(Exception e){    StatusPanel.getController().setdestopClient("no"); }
				System.gc();
			}
		}catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
			StatusPanel.getController().setdestopClient("no");
		}
	}
	
	public void setFlag(boolean f){
		screen_mode=f;
	}
}
