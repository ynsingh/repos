package org.bss.brihaspatisync.network.ppt_sharing;

/**
 * PostPPTScreen.java
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

import org.bss.brihaspatisync.tools.presentation.PresentationViewPanel;

import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.apache.commons.httpclient.auth.AuthScope;

import org.bss.brihaspatisync.util.ClientObject;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.UsernamePasswordCredentials;


/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: ashish.knp@gmail.com" > Ashish Yadav</a>
 */

public class PostPPTScreen {
	
	private String reflectorIP ="";
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private static PostPPTScreen post_ppt=null;
	private int port=runtime_object.getPPTPort();
	/**
 	 * Controller for the class.
 	 */ 
	public static PostPPTScreen getController(){
		if(post_ppt==null)
			post_ppt=new PostPPTScreen();
		return post_ppt;
	}

	public PostPPTScreen(){}

	public void start_to_sendppt(int pptnumber) throws Exception {
		try {
			HttpClient client = new HttpClient();
		        PostMethod postMethod = new PostMethod("http://"+ClientObject.getReflectorIP()+":"+port);//5271");
			client.setConnectionTimeout(8000);
                       	postMethod.setRequestBody(new FileInputStream("temp/"+"image"+(pptnumber)+".jpeg"));
               		postMethod.setRequestHeader("Content-type","image/jpeg; charset=ISO-8859-1");
			
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
			try {
				PresentationViewPanel.getController().runPresentationPanel(ImageIO.read(new File("temp/"+"image"+(pptnumber)+".jpeg")));
				org.bss.brihaspatisync.gui.StatusPanel.getController().setpptClient("yes");
			}catch(Exception e){}
		}catch(Exception e){
			System.out.println("Error in PostMethod of PostSharedScreen : "+e.getMessage());
			org.bss.brihaspatisync.gui.StatusPanel.getController().setpptClient("no");
		}
	}
}
