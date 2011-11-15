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
import org.bss.brihaspatisync.reflector.network.ppt.PPTGetAndPostServer;

//import org.bss.brihaspatisync.reflector.network.tcp.TCPServer;
//import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;
import org.bss.brihaspatisync.reflector.audio_video.TransmitHandlerThread;
import org.bss.brihaspatisync.reflector.network.audio.PostAudioServer;
import org.bss.brihaspatisync.reflector.network.audio.GetAudioServer;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */

public class LogoutReflector {

	private static LogoutReflector logoutref=null;
	//private MaintainLog log=MaintainLog.getController();
    	private RegisterToIndexServer riserver=RegisterToIndexServer.getController();
	/**
	 * Controller for this calss
	 */
	public static LogoutReflector getController(){
		if(logoutref==null)
			logoutref=new LogoutReflector();
		return logoutref;
	}

	protected void stopReflector(){
		try {	
			System.out.println("stop ref 1");	
			String indexServer=riserver.getIServerIP();
			System.out.println("indexServer "+indexServer);	
			if(!indexServer.equals("")) {	
				String req_url=indexServer+"/ProcessRequest?req=reflector_logout";
				System.out.println("req_url "+req_url);
				URL indexurl = new URL(req_url);
                               	HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
				System.out.println("connection "+connection);
                               	if(connection==null){
                                       	JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                       		}else{
					BufferedReader in=null;
                               		try{
		                		in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
                				String str=in.readLine();
						System.out.println("str "+str);
						if(str.equals("successfull")){
						  System.out.println("xml delete !Logout Reflector Successfully !! ");	
						}
                			}finally {
                       				if(in != null){
                               				in.close();
                               			}
					}
                       		}
                		HttpGetPost.getController().stop();     /** port 9999  */
       	        		//TCPServer.getController().stop();      /** port 8888  */
       	        		//Audio Threads
       	        		PostAudioServer.getController().stopThread();
                                GetAudioServer.getController().stopThread();
       	        		      	        		
				PPTGetAndPostServer.getController().stopThread();
              			//log.stop();
              			TransmitHandlerThread.getControllerofHandler().stop();
				Timer UL_Timer =riserver.getTimer();
				if(UL_Timer != null) {
					UL_Timer.cancel();
					System.out.println("Logout Reflector Successfully !! ");			
				}else {	
					System.out.println("Reflector is not start");
				}
			}else {
				System.out.println("Reflector is not start");
			}
		}catch(Exception e){
			System.out.println("Error on Logout Reflector ");
		}
	}
	
/*	protected void restartReflector(){
		try {
			HttpGetPost.getController().stop();     
                      	TCPServer.getController().stop();      
                        PPTGetAndPostServer.getController().stopThread();
                        //log.stop();
                        TransmitHandlerThread.getControllerofHandler().stop();
                        Timer UL_Timer =riserver.getTimer();
                        if(UL_Timer != null) {
                        	UL_Timer.cancel();
                             	log.setString("Logout Reflector Successfully !! ");
                      	}else {
                        	System.out.println("Reflector is not start");
                      	}
					
		}catch(Exception e){
                        log.setString("Error on Logout Reflector ");
                }
	}
	*/
	      	
	 
}
