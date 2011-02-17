package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * PostSharedScreen.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import java.io.File;
import java.io.FileInputStream;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.bss.brihaspatisync.gui.Desktop_Sharing;
import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class PostSharedScreen implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private String reflectorIP ="";
	
	private ClientObject clientObject=ClientObject.getController();

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

	public void run() {
		try {
			Robot robot = new Robot();
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        Rectangle size = new Rectangle(0, 0, screenSize.width, screenSize.height);
				
			while(flag) {
				HttpClient client = new HttpClient();
		        	PostMethod postMethod = new PostMethod("http://"+clientObject.getReflectorIP()+":8884");
				client.setConnectionTimeout(8000);
               			BufferedImage image = robot.createScreenCapture(size);
                       		ImageIO.write(image, "jpeg", new File("image.jpeg"));
                       		postMethod.setRequestBody(new FileInputStream("image.jpeg"));
               			postMethod.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");

                       		int statusCode1 = client.executeMethod(postMethod);
                       		postMethod.getStatusLine();
                       		postMethod.releaseConnection();
                       		try {
                               		Thread.sleep(100);
                               	}catch(Exception ex){}
			}
		}catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
		}
	}
}
