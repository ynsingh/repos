package org.bss.brihaspatisync.network.video_capture; 
/**
 * LocalServer.java
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

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.VideoPanel;
import org.bss.brihaspatisync.gui.JoinSessionPanel;

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
			JoinSessionPanel.getController().getAV_Panel().add(VideoPanel.getController().createGUI());
			System.out.println("Video Captureing start sucessfully !!");
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
			System.out.println("Video Captureing  stop Successfully !!");
                }
        }

	public void run() {
		while(flag) {
		        try {
				HttpClient client = new HttpClient();
				HttpMethod method=null;
				if(runtime_object.getVideoServer().equals(""))
					method = new GetMethod("http://localhost:8090");
				else
					method = new GetMethod("http://"+runtime_object.getVideoServer()+":"+runtime_object.getVideoServerPort());
		                client.setConnectionTimeout(800);
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
					if(image!=null) {
						BufferImage.getController().handleBuffer();
						BufferImage.getController().put(image);
						if((clientObject.getUserRole()).equals("instructor")){
							VideoPanel.getController().runInstructorVidio(image);
						}else {
							//StudentBufferImage.getController().handleBuffer();
                                                        //StudentBufferImage.getController().put(image);
                                                        VideoPanel.getController().runStudentVidio(image);
						}
					}
				} catch(Exception e){ try { runner.sleep(1000); runner.yield(); System.out.println("Error in loding image in video_panel : "+e.getMessage());}catch(Exception ep){} }
				try {
                                	runner.sleep(100);runner.yield();
				}catch(Exception ex){}

			} catch(Exception e){ 
				try {   runner.sleep(1000); runner.yield();}catch(Exception ep){}
				System.out.println("Error in GetMethod of Video Captureing : "+e.getMessage()); 
			}
		}
	}
}
