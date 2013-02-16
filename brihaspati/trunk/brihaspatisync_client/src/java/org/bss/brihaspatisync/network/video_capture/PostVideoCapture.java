package org.bss.brihaspatisync.network.video_capture;

/**
 * PostVideoCapture.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013 ETRG, IIT Kanpur.
 */

import javax.imageio.ImageIO;

import  java.util.LinkedList;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.gui.VideoPanel;
import org.bss.brihaspatisync.gui.StatusPanel;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.bss.brihaspatisync.network.util.UtilObject;

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
	private static PostVideoCapture post_capture=null;
	private ClientObject clientObject=ClientObject.getController();	
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private java.io.ByteArrayOutputStream os=new java.io.ByteArrayOutputStream();

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
			org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().addType("ins_video");
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
			org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().removeType("ins_video");
			System.out.println("Post Video Capture  stop successfully !!");
                }
        }

	public void run() {
		while(flag && ThreadController.getController().getThreadFlag() ) {
			try {
				if(ThreadController.getController().getReflectorStatusThreadFlag()) {
					if(!getflag) {	
						/***  send data to reflector ********/
						if(BufferImage.getController().bufferSize()>0) {
							BufferedImage bimg=BufferImage.getController().get(0);
							BufferImage.getController().remove();
							
	                                	        JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(os);
        	                               		JPEGEncodeParam enParam = jencoder.getDefaultJPEGEncodeParam(bimg);
	        	                               	enParam.setQuality(0.25F, true);
	        		                        jencoder.setJPEGEncodeParam(enParam);
        	        		                jencoder.encode(bimg);
							LinkedList send_queue=UtilObject.getController().getSendQueue("ins_video");
							if(send_queue.size()==0 ){
                                                        	send_queue.addLast(os.toByteArray());
	                                                }else {
								int k=compare(os.toByteArray(),(byte[])send_queue.get((send_queue.size())-1));
                                	                        if(k!=0)
                                        	                        send_queue.addLast(os.toByteArray());
                                                	}
							os.flush();
							os.reset();
						}
					}else {
						LinkedList send_queue=UtilObject.getController().getSendQueue("ins_video");
                                                send_queue.addLast(null);
						LinkedList desktop_queue=UtilObject.getController().getQueue("ins_video");
                                       		if(desktop_queue.size()>0) {
                                        		byte[] bytes1=(byte[])desktop_queue.get(0);
	                                                desktop_queue.remove(0);
        	                                	BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes1));
                	                        	if(image != null)
                        	                       		org.bss.brihaspatisync.gui.VideoPanel.getController().runInstructorVidio(image);
						}
                              		}
					networkHandler();
				}
	                       	runner.sleep(3000); runner.yield();
			}catch(Exception e){System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());}
		}
	}

	/**
	 * This method is used to netwrok very slow . 
	 * then remove data from sending queue 
	 */

        private void networkHandler() {
                try {
                        LinkedList sendqueue=UtilObject.getController().getSendQueue("ins_video");
                        if(sendqueue.size()>10) {
                                for(int i=0;i<5;i++) {
                                        sendqueue.remove(0);
                                }
                        }
                }catch(Exception epe){System.out.println("Error in networkHandler class "); }
        }

	/**
	 * This method is used to differeciate between two images .
	 **/
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

}
