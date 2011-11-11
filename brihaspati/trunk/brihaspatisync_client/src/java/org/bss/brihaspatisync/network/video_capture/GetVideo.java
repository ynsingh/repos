package org.bss.brihaspatisync.network.video_capture;

/**
 * GetVideoCapture.java
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

import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.apache.commons.httpclient.auth.AuthScope;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a>
 */

public class GetVideo implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static GetVideo get_screen=null;

	public static GetVideo getController(){
                if(get_screen==null)
                        get_screen=new GetVideo();
                return get_screen;
        }

	public  GetVideo(){ }

	/**
         * Start TCPSender Thread.
         */
        public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			org.bss.brihaspatisync.gui.JoinSessionPanel.getController().getAV_Panel().add(org.bss.brihaspatisync.gui.VideoPanel.getController().createGUI());
			System.out.println("GetVideo start sucessfully !!");
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
			System.out.println("GetVideo stop Successfully !!");
                }
        }

	public void run() {
		while(flag) {
		        try {
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":8092");
		                client.setConnectionTimeout(8000);
                                method.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");
				// Http Proxy Handler
				if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
                                        HostConfiguration config = client.getHostConfiguration();
                                        config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
                                        Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
                                        AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
                                        client.getState().setProxyCredentials(authScope, credentials);
                                }

                                int statusCode1 = client.executeMethod(method);
                                byte[] bytes1=method.getResponseBody();
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes1));
                               	method.releaseConnection();
				try {
					if(image!=null)
                        			org.bss.brihaspatisync.gui.VideoPanel.getController().runInstructorVidio(image);
				}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
				try {	Thread.sleep(10);Thread.yield(); }catch(Exception ep){}
			} catch(Exception e){ 
				try {   Thread.sleep(1000);Thread.yield(); }catch(Exception ep){}
				System.out.println("Error in GetMethod of GetVideo : "+e.getMessage()); 
			}
		}
	}
}
