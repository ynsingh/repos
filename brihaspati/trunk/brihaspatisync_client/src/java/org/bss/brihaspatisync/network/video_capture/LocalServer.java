package org.bss.brihaspatisync.network.video_capture; 
/**
 * LocalServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;                                                                                                                            
import org.apache.commons.httpclient.methods.GetMethod;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.VideoPanel;
import org.bss.brihaspatisync.gui.JoinSessionPanel;

import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.util.ThreadController;

import org.apache.commons.httpclient.auth.AuthScope;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */

public class LocalServer implements Runnable {
	
	private Thread runner=null;
	private boolean flag=false;
	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static LocalServer get_capture=null;

	public static LocalServer getController(){
                if(get_capture==null)
                        get_capture=new LocalServer();
                return get_capture;
        }

	public  LocalServer(){ }

	/**
         * Start TCPSender Thread.
         */
        public void start(){
                if (runner == null) {	
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			//JoinSessionPanel.getController().getAV_Panel().add(VideoPanel.getController().createGUI());
			System.out.println("Video Captureing start sucessfully !!");
		}
        }

        /**
         * Stop TCPSender Thread.
         */
        public void stop() {
                if (runner != null) {
			flag=false;
                        runner = null;
			System.out.println("Video Captureing  stop Successfully !!");
                }
        }

	/**
 	 * This method is used to get video from localmachine .
 	 * and put the image in buffer .
 	 */
	public void run() {
		while(flag && ThreadController.getController().getThreadFlag()) {
		        try {
				if(ThreadController.getController().getReflectorStatusThreadFlag()) {
					
					HttpClient client = new HttpClient();
					HttpMethod method= new GetMethod("http://"+runtime_object.getVideoServer()+":"+runtime_object.getVideoServerPort());
			                client.setConnectionTimeout(80000);
                        	        method.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");
                                	int statusCode1 = client.executeMethod(method);
	                                byte[] bytes1=method.getResponseBody();
        	                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes1));
                	               	method.releaseConnection();
					if(image!=null) {
						BufferImage.getController().handleBuffer();
						BufferImage.getController().put(image);
						if((clientObject.getUserRole()).equals("instructor")){
							VideoPanel.getController().runInstructorVidio(image);
						}else {
                        	        		VideoPanel.getController().runStudentVidio(image);
						}
					}else {
						runner.sleep(4000);runner.yield();
					}
					
				}	
				runner.sleep(5000);runner.yield();
			} catch(Exception e){ }
		}
	}
}
