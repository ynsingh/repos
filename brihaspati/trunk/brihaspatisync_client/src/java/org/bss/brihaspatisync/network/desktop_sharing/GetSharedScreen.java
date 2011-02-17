package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * GetSharedScreen.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.gui.Desktop_Sharing;
import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class GetSharedScreen implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private ClientObject clientObject=ClientObject.getController();
	
	private static GetSharedScreen get_screen=null;

	public static GetSharedScreen getController(){
                if(get_screen==null)
                        get_screen=new GetSharedScreen();
                return get_screen;
        }

	public  GetSharedScreen(){ }

	/**
         * Start TCPSender Thread.
         */
        public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			System.out.println("GetSharedScreen start sucessfully !!");
		}
        }

        /**
         * Stop TCPSender Thread.
         */
        public void stop() {
                if (runner != null) {
			flag=false;
                        runner.stop();
                        runner = null;
			System.out.println("GetSharedScreen stop Successfully !!");
                }
        }

	public void run() {
		while(flag) {
		        try {
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":8883");
		                client.setConnectionTimeout(8000);
                                method.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");
                                int statusCode1 = client.executeMethod(method);
                                byte[] bytes1=method.getResponseBody();
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes1));
                               	method.releaseConnection();
				try {
					if(image!=null)
                        			Desktop_Sharing.getController().runDesktopSharing(image);
				}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
				try {	Thread.sleep(100); }catch(Exception ep){}
			} catch(Exception e){ 
				System.out.println("Error in GetMethod of GetSharedScreen : "+e.getMessage()); 
			}
		}
	}
}
