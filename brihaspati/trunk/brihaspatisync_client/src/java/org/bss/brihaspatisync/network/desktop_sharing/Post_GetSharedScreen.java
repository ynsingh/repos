package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * PostSharedScreen.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.util.Iterator;
import java.util.LinkedList;

import java.io.ByteArrayOutputStream;

import java.awt.AWTException;
import java.awt.Color;
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
import javax.imageio.IIOImage;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.bss.brihaspatisync.gui.StatusPanel;
import org.bss.brihaspatisync.gui.Desktop_Sharing;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class Post_GetSharedScreen implements Runnable {
	
	private Robot robot=null;
	private Thread runner=null;
	private boolean flag=false;
	private boolean getflag=false;
	private boolean screen_mode=false;		
	private static Post_GetSharedScreen post_screen=null;
	private UtilObject utilObject=UtilObject.getController();		
	private ByteArrayOutputStream os = new ByteArrayOutputStream();
	
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
	public void startSharedScreen(boolean getscreen){
                if (runner == null) {
			flag=true;
			getflag=getscreen;
                        runner = new Thread(this);
                        runner.start();
			utilObject.addType("Desktop_Data");
			System.out.println("PostSharedScreen start successfully !!");
		}
        }

        /**
         * Stop Thread.
         */
        
	public void stopSharedScreen() {
                if (runner != null) {
			utilObject.removeType("Desktop_Data");	
			flag=false;
			getflag=false;
			screen_mode=false;
			robot  = null;
			runner=null;
			System.out.println("PostSharedScreen stop successfully !!");
                }
        }

	/**
 	 * This method is used to get screenshot .
 	 */  
	public BufferedImage captureScreen() {
		BufferedImage image=null;
		try{	
			if(robot == null)
				robot = new Robot();
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
		while(flag && ThreadController.getThreadFlag()) {
			try {
				if(ThreadController.getReflectorStatusThreadFlag()) {
					/****   send the image to reflector **********/
					if(!getflag) {
						BufferedImage image=captureScreen();
						encode(image);
						LinkedList send_queue=utilObject.getSendQueue("Desktop_Data");
						if(send_queue.size() ==0 ) {
							send_queue.addLast(os.toByteArray());
						}else {
							int k=compare(os.toByteArray(),(byte[])send_queue.get((send_queue.size())-1));
							if(k!=0)	
								send_queue.addLast(os.toByteArray());	
						}
						os.flush();
						os.reset();	
					}else {
						/****   receive the image from reflector **********/
						LinkedList desktop_queue=utilObject.getReceiveQueue("Desktop_Data");
                                                if(desktop_queue.size()>0) {
							byte[] bytes1=(byte[])desktop_queue.get(0);
							desktop_queue.remove(0);
			                                BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
		        	                       	if(image!=null){
								if(!screen_mode)
                		        	                       	Desktop_Sharing.getController().showImage(image);
								else
                		                                	org.bss.brihaspatisync.gui.FullScreen_Mode.getController().showImage(image);
							}
						}
					}
					StatusPanel.getController().setdestopClient("yes");
				}else 
					StatusPanel.getController().setdestopClient("no");
                       		runner.yield(); runner.sleep(2000); 
				System.gc();
			} catch(Exception e){  StatusPanel.getController().setdestopClient("no"); }
		}
		try {
			stopSharedScreen();	
			Desktop_Sharing.getController().resetshowImage();
			StatusPanel.getController().setdestopClient("no");
			utilObject.removeReceiveQueue("Desktop_Data");
			utilObject.removeSendQueue("Desktop_Data");
		}catch(Exception e){}
	}
	
	/**
 	 * This method is used to differeciate between two images .
 	 */  
	
	public int compare(byte[] left, byte[] right) {
        	for (int i = 0, j = 0; i < left.length && j < right.length; i++, j++) {
			int a = (left[i] & 0xff);
			int b = (right[j] & 0xff);
			if (a != b) {
                		return a - b;
            		}
        	}
        	return left.length - right.length;
    	}

	private void encode(BufferedImage image) {
		try {
			// get all image writers for JPG format
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();
			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);
			ImageWriteParam param = writer.getDefaultWriteParam();
			// compress to a given quality
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(ClientObject.getController().getDesktopImageQuality());
			// appends a complete image stream containing a single image and
		    	// associated stream and image metadata and thumbnails to the output
			writer.write(null, new IIOImage(image, null, null), param);
			// close all streams
			os.close();
			ios.close();
			writer.dispose();
		} catch(Exception e) { System.out.println("Exception in Post_GetSharedScreen class at encode method  "); }
	}

	public void setFlag(boolean f) {
		screen_mode=f;
	}
}
