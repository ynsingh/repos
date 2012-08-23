package org.bss.brihaspatisync.monitor.network.http;

/**
 * HTTPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.awt.Frame;
import java.lang.Long;

import java.util.StringTokenizer;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import org.bss.brihaspatisync.monitor.util.RuntimeDataObject;
import org.bss.brihaspatisync.monitor.gui.PacketRategraph;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 */

public class HTTPClient extends Thread {

	private String lect_id="";
	Vector<Float> counter=new Vector<Float>();
	
	private String reflectorIP ="";
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	private final String refHttpPort=runtime_object.getRefHttpPort();

	public HTTPClient(){ }

	public HTTPClient(String reflectorIP){
		this.reflectorIP=reflectorIP;
        }
	private HttpClient client = new HttpClient();

	public void run() {
                try {
			org.apache.commons.httpclient.Header h=new org.apache.commons.httpclient.Header();
                        h.setName("session");
                        h.setValue(this.lect_id);
			while(true){
                        	try {
				
					PostMethod method = new PostMethod("http://"+reflectorIP+":"+refHttpPort);
                                        client.setConnectionTimeout(20000);
                                        method.setRequestBody("hi");
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
        	                	java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(method.getResponseBodyAsStream()));
                	        	String str;
					float i3=0;
                        		while((str = rd.readLine()) != null) {
						float f = Float.valueOf(str.trim()).floatValue();
						PacketRategraph d=new PacketRategraph("Data Transfer rate");
						counter.add(f);
						int size=counter.size();
						for(int j=0;j<counter.size();j++){
						  float i1=counter.lastElement();
						  float i2=counter.elementAt(size-2);
						  i3=(i1-i2)/5;
						}
						if(size==50)
						counter.clear();
						d.getDataValue(i3);
                        		}
                        		method.releaseConnection();
					try {
						this.sleep(5000);
						this.yield();
					}catch(Exception ww){
					}	
	                  	}catch(Exception ex) { 
					try {
                                                this.sleep(5000);
                                                this.yield();
                                        }catch(Exception ww){
                                        }
				}
			}
         	}catch(Exception e){ 
			try {
                                this.sleep(5000);
                        	this.yield();
                        }catch(Exception ww){
                    	}
		}
  	}
}
