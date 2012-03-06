package org.bss.brihaspatisync.network.video_capture;

/**
 * StudentGetVideo.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.gui.Desktop_Sharing;
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
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a>
 */

public class StudentGetVideo implements Runnable {
	
	private Thread runner=null;
	
	private boolean flag=false;

	private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static StudentGetVideo get_screen=null;

	public static StudentGetVideo getController(){
                if(get_screen==null)
                        get_screen=new StudentGetVideo();
                return get_screen;
        }

	public  StudentGetVideo(){ }

	/**
         * Start TCPSender Thread.
         */
        public void start(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
			//org.bss.brihaspatisync.gui.JoinSessionPanel.getController().getAV_Panel().add(org.bss.brihaspatisync.gui.VideoPanel.getController().createGUI());
			System.out.println("Student GetVideo start sucessfully !!");
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
			System.out.println("Student GetVideo stop Successfully !!");
                }
        }

	public void run() {
		org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
               	h.setName("session");
                h.setValue(clientObject.getLectureID());
		int port =runtime_object.client_stdgetvedio_port();
		while(flag && ThreadController.getController().getThreadFlag()) {
		        try {
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod("http://"+clientObject.getReflectorIP()+":"+port);//8094");
		                client.setConnectionTimeout(8000);
                                method.setRequestHeader(h);
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
                        			org.bss.brihaspatisync.gui.VideoPanel.getController().runStudentVidio(image);
				}catch(Exception e){ System.out.println("Error in loding image in desktop_sharing panel : "+e.getMessage()); }
				try {	runner.sleep(10);runner.yield(); }catch(Exception ep){}
			} catch(Exception e){ 
				System.out.println("Error in GetMethod of GetVideo : "+e.getMessage()); 
			}
			System.gc();
		}
	}
}
