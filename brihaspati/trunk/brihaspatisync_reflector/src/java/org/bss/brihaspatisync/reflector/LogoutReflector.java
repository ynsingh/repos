package org.bss.brihaspatisync.reflector;

/**
 * LogoutReflector.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Timer;
import java.net.URL;
import java.net.URLEncoder;
import java.net.InetAddress;

import javax.swing.JOptionPane;

import javax.net.ssl.HttpsURLConnection;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.bss.brihaspatisync.reflector.util.HttpsUtil;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */

public class LogoutReflector {

	public static void stopReflector(){
		try {	
			System.out.println("stop ref 1");	
			String indexServer=org.bss.brihaspatisync.reflector.util.RuntimeDataObject.getController().getindexServerAddr();
			System.out.println("indexServer "+indexServer);	
			if(!indexServer.equals("")) {	
				String req_url=indexServer+"req=reflector_logout";
				URL indexurl = new URL(req_url);
                               	HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                               	if(connection != null){
					BufferedReader in=null;
                               		try{
		                		in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                				String str=in.readLine();
						if(str.equals("successfull")){
						  	System.out.println("xml delete !Logout Reflector Successfully !! ");	
						}
                			}finally {
                       				if(in != null){
                               				in.close();
                               			}
					}
                       		}
				Timer UL_Timer =RegisterToIndexServer.getTimer();
				if(UL_Timer != null) {
					UL_Timer.cancel();
					System.out.println("Logout Reflector Successfully !! ");			
				}	
				org.bss.brihaspatisync.reflector.network.singleport.SinglePortServer.getController().stop();
			}
		}catch(Exception e){ 	
			try {
				org.bss.brihaspatisync.reflector.network.singleport.SinglePortServer.getController().stop();
			}catch(Exception ex){}
			System.out.println("Error on Logout Reflector ");
		}
	}
}
