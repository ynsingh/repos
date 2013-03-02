package org.iitk.livetv.util;

/**
 * @(#)RuntimeObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012-2013 ETRG, IIT Kanpur.
 */

import java.util.Properties;
import java.io.InputStream;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>
 */

public class RuntimeObject {

        private Properties prop=null;
        private static RuntimeObject obj=null;
	private String proxy_host="";
	private String proxy_port="";
	private String proxy_user="";
	private String proxy_pass="";
	
        public static RuntimeObject getController(){
                if(obj==null) {
                        obj=new RuntimeObject();
                        obj.start();
                }
                return obj;
        }
 
	private void start(){
                prop=new Properties();
                try{
                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/conf/livetv.properties");
                        prop.load(inputStream);
                }catch(Exception e){ }
        }

	public void setProxyHost(String value){
		proxy_host=value;
	}

	public String getProxyHost(){
		return proxy_host;
        }

	public void setProxyPort(String value){
		proxy_port=value;
        }

	public String getProxyPort(){
		return proxy_port;
        }

	public void setProxyUser(String value){
		proxy_user=value;
        }

	public String getProxyUser(){
		return proxy_user;
        }
	
	public void setProxyPass(String value){
		proxy_pass=value;
        }

	public String getProxyPass(){
		return proxy_pass;
        }

	public String getMasterServerUrl(){
		return prop.getProperty("MasterServerURL");
        }

        protected String getCertOrgName(){
		return prop.getProperty("cert_ORG_Name");
        }

        protected String getcertIssuerName(){
		return prop.getProperty("cert_Issuer_Name");
        }

	public String getStreamingPort(){
		return prop.getProperty("Streaming_Port");
	}
}	
