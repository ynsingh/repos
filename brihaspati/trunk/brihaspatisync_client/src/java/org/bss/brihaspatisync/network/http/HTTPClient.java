package org.bss.brihaspatisync.network.http;

/**
 * HTTPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 20011, ETRG, IIT Kanpur.
 */

import java.awt.Frame;
import java.lang.Long;
import javax.swing.JOptionPane;

import java.util.Timer;
import java.util.TimerTask;
import java.util.StringTokenizer;

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.network.util.UtilObject;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 * @author <a href="mailto: ashish.knp@gmail.com"  >Ashish Yadav</a>
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HTTPClient extends Thread {

	private String lect_id="";
	
	private String reflectorIP ="";
	
	private UtilObject utilObject=UtilObject.getController();

        private ClientObject clientObject=ClientObject.getController();
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private final String refHttpPort=runtime_object.getRefHttpPort();

	public HTTPClient(){ }

	public HTTPClient(String reflectorIP,String lect_id){
		this.reflectorIP=reflectorIP;
		this.lect_id=lect_id;
        }
	private HttpClient client = new HttpClient();

	public void run() {
                try {
			while(true){
                        	try {
				
					String datastr="nodata";
					try {
						if(utilObject.getSendQueueSize() != 0) {
                        	        		datastr=utilObject.getSendQueue();
							datastr=java.net.URLEncoder.encode(datastr);
	
                                		}
					}catch(Exception e){System.out.println("Error in Send data "+datastr); }
				
					String reg="";
					if(clientObject.getParentReflectorIP()!= null ){
        	                                reg=clientObject.getParentReflectorIP();
                	                        clientObject.setParentReflectorIP("null");
                        	        }else{
                                	        reg="null";
                                	}
					PostMethod method = new PostMethod("http://"+reflectorIP+":"+refHttpPort);
                                        client.setConnectionTimeout(8000);
                                        method.setRequestBody(clientObject.getUserRole()+","+lect_id+"req"+datastr+"req"+reg);
                                        method.setRequestHeader("Content-type","text/plain; charset=ISO-8859-1");

					// Http Proxy Handler
					if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
					
						HostConfiguration config = client.getHostConfiguration();
        					config.setProxy(runtime_object.getProxyHost(),Integer.parseInt(runtime_object.getProxyPort()));
        					Credentials credentials = new UsernamePasswordCredentials(runtime_object.getProxyUser(), runtime_object.getProxyPass());
        					AuthScope authScope = new AuthScope(runtime_object.getProxyHost(), Integer.parseInt(runtime_object.getProxyPort()));
	        				client.getState().setProxyCredentials(authScope, credentials);
					}

	                        	int statusCode1 = client.executeMethod(method);
        	                	java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(method.getResponseBodyAsStream()));
                	        	String str;
                        		while((str = rd.readLine()) != null) {
						java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
						if (Tok.hasMoreElements()) {
							String str1=(String)Tok.nextElement();
							String str2=(String)Tok.nextElement();
							str2=java.net.URLDecoder.decode(str2);
							if(!str1.equals("nodata"))
								RuntimeDataObject.getController().setUserList(str1);
							if(!str1.equals("nodata"))
								utilObject.setRecQueue(str2);	
						}
                        		}
                        		method.releaseConnection();
					try {
						this.sleep(500);
						this.yield();
					}catch(Exception ww){
						System.out.println("=================>  "+ww.getMessage());
					}	
	                  	}catch(Exception ex) { 
					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HTTPClient.MessageDialog1"),"HTTPClient Message",JOptionPane.ERROR_MESSAGE);
					//JOptionPane.showMessageDialog(null,"Reflector connection failed !!","HTTPClient Message",JOptionPane.ERROR_MESSAGE);
					System.out.println(" HTTPClient could not connect  "+ex.getMessage()); 
				}
			}
         	}catch(Exception e){ 
			
		}
  	}
}
