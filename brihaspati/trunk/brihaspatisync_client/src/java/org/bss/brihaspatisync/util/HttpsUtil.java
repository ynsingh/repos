package org.bss.brihaspatisync.util;

/**
 * HttpsUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.IOException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.security.Security;
import java.security.cert.Certificate;
import javax.security.cert.X509Certificate;

import java.net.URL;
import java.net.ProxySelector;
import java.net.Proxy;
import java.net.PasswordAuthentication;
import java.net.InetSocketAddress;
import java.net.Authenticator;
import java.net.URI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import java.util.Vector;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;
import org.bss.brihaspatisync.gui.Language;

import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HttpsUtil{
	private static HttpsURLConnection connection=null;

	private static RuntimeDataObject runtime_object=RuntimeDataObject.getController();
		
	/**
   	 * The URL class is capable of handling http:// and https:// URLs
   	 */
   	public static HttpsURLConnection createHTTPConnection(URL url) throws IOException {
		try {
			InetSocketAddress proxy_addr=null;
			if(runtime_object.getProxyHost().equals("")) {
	                        System.setProperty("java.net.useSystemProxies","true");
        	                List list = ProxySelector.getDefault().select( new URI(url.toString()));
                	        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                        	        Proxy proxy = (Proxy) iter.next();
                                	proxy_addr = (InetSocketAddress)proxy.address();
				}
				/*
				if(!(list.get(0).toString()).equals("DIRECT")) {
					new org.bss.brihaspatisync.gui.PreferenceWindow().createWindow(url);
				}*/
			}


              		if(!runtime_object.getProxyHost().equals("")) {
                		runtime_object.setProxyHost(proxy_addr.getHostName());
	                      	runtime_object.setProxyPort(Integer.toString(proxy_addr.getPort()));
				if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
					Properties sysProps = System.getProperties();
	        		     	sysProps.put( "proxySet", "true" );
        	       			sysProps.put( "proxyHost", runtime_object.getProxyHost());
	              			sysProps.put( "proxyPort", runtime_object.getProxyPort());
		       			Authenticator authenticator = new Authenticator() {
        					public PasswordAuthentication getPasswordAuthentication() {
                					return (new PasswordAuthentication(runtime_object.getProxyUser(),runtime_object.getProxyPass().toCharArray()));
         					}
					};
		             		Authenticator.setDefault(authenticator);
				}
			}
		
	               	connection = (HttpsURLConnection) url.openConnection();
			//SSL Certificate
      			connection.setHostnameVerifier(new HostnameVerifier() {
	      			public boolean verify(String rserver, SSLSession sses) {
					try{
						Certificate[] certificates= getCertificates(connection);
   						X509Certificate cert = X509Certificate.getInstance(certificates[0].getEncoded());
						String subjectOrg_Name=getSubjectOrg_Name(cert);
						String issuerCN_Name=getIssuerCN_Name(cert);
						if(verifyCertificate(subjectOrg_Name,issuerCN_Name)==true)
							return true;
   						else
   							return false;
   					} catch(Exception e){System.out.println("Exception on createHTTPConnection in HttpsUtil class"+e.getMessage());}
					return false;
				}		
			});
			if(connection==null)
		        	System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog1"))				;
		}catch (Exception e) { e.printStackTrace();}
		return connection;
	}
	
	/**
   	 * Get Server Certifivate from HttpsURLConnection
    	 */
    	private static Certificate[] getCertificates(HttpsURLConnection conn) throws IOException {
   		return conn.getServerCertificates();
   	}
   	
   	/**
     	 * Get The Issuer CN Name to verify that certificate is comming from 
     	 * original Server.
     	 */
   	private static synchronized String getIssuerCN_Name(X509Certificate cert){
   	
   		String IssuerDN=cert.getIssuerDN().getName();
   		   		
   		StringTokenizer st_IssuerDN=new StringTokenizer(IssuerDN,",");
   		String Issuer_EMAIL=st_IssuerDN.nextToken();
   		String Issuer_CN=st_IssuerDN.nextToken();
   		StringTokenizer st_IssuerDN_CN=new StringTokenizer(Issuer_CN,"=");
   		String CN=st_IssuerDN_CN.nextToken();
   		String CN_Name=st_IssuerDN_CN.nextToken();
   		return CN_Name;	
   	}
   
    	/**
     	 * Get The Subject Organisation Name from certificate to verify that 
     	 * certificate is issued to valid server
     	 */
   	private static synchronized String getSubjectOrg_Name(X509Certificate cert){
   
   		String SubjectDN=cert.getSubjectDN().getName();
   		
   		StringTokenizer st_SubjectDN=new StringTokenizer(SubjectDN,",");
   		String Subject_CN=st_SubjectDN.nextToken();
   		String Subject_OU=st_SubjectDN.nextToken();
   		String Subject_O=st_SubjectDN.nextToken();
		StringTokenizer st_Subject_O=new StringTokenizer(Subject_O,"=");
   		String Subject_Org=st_Subject_O.nextToken();
   		String Subject_OrgName=st_Subject_O.nextToken();
   		return Subject_OrgName;
   		
   	}
	
	public static synchronized boolean getIndexingMessage(String indexServer) {
        	boolean flag=false;
               	try {
			URL indexurl = new URL(indexServer);
			connection=createHTTPConnection(indexurl);
			if(connection != null){
	                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	                String str="";
                	        try {
                        	        if((str=in.readLine())!=null) {
                                	        if(str.equals("Successfull")) {
                                        	        flag=true;
	                                        }
                        	        }
                               	} finally {
                        		if(in != null) in.close();
                           	}
			} else 
				System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
                }catch(Exception e) {
                        System.out.println("Exception on getIndexingMessage(connection) HttpsUtil.java "+e.getMessage());
                }
		return flag;
        }

	/**
	 * This method is used to get Reflector's Address from inexing server passed as parameter ,which is used to join session. 
	 */
	public static synchronized String getReflectorAddress(String indexServer){
		String ref_ip="";
		String parent_ref_ip="";
                try {
                        URL indexurl = new URL(indexServer);
                        connection=createHTTPConnection(indexurl);
                        if(connection != null){
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String str="";
                                try{
                                        while((str=in.readLine())!=null){
						if(str.startsWith("current")) {
							try {
								String str1[]=str.split(",");
								// store current reflector ipAddress
								ref_ip=str1[0].replaceAll("current","");
								// set current reflector ipaddress in clientObject for use in runtime.
								ClientObject.setReflectorIP(ref_ip);
								// store parent reflector ipaddress of current reflector 
								parent_ref_ip="parent"+str1[1].replaceAll("parent","");
								// set parent reflector ipaddress of current reflector in ClientObject 
								// for use in runtime
								ClientObject.setParentReflectorIP(parent_ref_ip);	
								String a_status=str1[2].replaceAll("A=","");
								AudioUtilObject.setAudioStatus(a_status);
								String v_status=str1[3].replaceAll("V=","");
								AudioUtilObject.setVideoStatus(v_status);
							}catch(Exception e){ System.out.println("Exception on getIndexingMessage(connection) HttpsUtil.java "+e.getMessage());}
						}else if(str.startsWith("date")) {					
							str=str.replaceAll("date","");
							return str;
						}else {
							org.bss.brihaspatisync.gui.StatusPanel.getController().setStatus(Language.getController().getLangValue("JoinSession.MessageDialog1"));	
							return null;
						}
					}
                                }finally {
                                        if(in != null) in.close();
                                }
                        } else
                                System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog1"));
                }catch(Exception e){
                        System.out.println("Exception on getReflectorAddress() in HttpsUtil.java "+e.getMessage());
                }
                return ref_ip;
        }
	
	public static synchronized String getStringMessage(String sendurl,String message){
                try {
                        URL url = new URL(sendurl);
                        connection=createHTTPConnection(url);
                        if(connection != null) {
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str="";
                                try{
                                        if((str=in.readLine())!=null){
                                                if(!(str.equals(message))){
                                                        return str;
                                                }
                                        }
                                }finally {
                                        if(in != null) in.close();
                                }
                        } else
                                System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
                }catch(Exception e){
                        System.out.println("Exception on getStringMessage(connection) HttpsUtil.java "+e.getMessage());
                }
                return null;
        }

	
	public static synchronized Vector getvectorMessage(String sendurl,String message){
        	Vector msgList=new Vector();
		try {
                	URL url = new URL(sendurl);
                        connection=createHTTPConnection(url);
                        if(connection != null) {
                        	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str="";
                                try{
					while((str=in.readLine())!=null){
	                                	if(!(str.equals(message))){
							if(str.matches("(.*)loginfailed(.*)")){
								return null;
							}else
								msgList.addElement(str);
                        	                }
                                	}
                                }finally {
                                	if(in != null) in.close();
                                }
                        }else 
                                System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
                }catch(Exception e){
                        System.out.println("Exception on getvectorMessage(connection) HttpsUtil.java "+e.getMessage());
                        msgList.clear();
                        return msgList;
                }
                return msgList;
        }
	/** get all the session in instruvtor or student ***/	

	public static synchronized Vector getSessionForCourse(Vector courseList, String indexServerName) {
		if(courseList !=null) {
			Vector sessionList=new Vector();
			for(int i=0;i<courseList.size();i++){
                        	try {
                                	String courseName="cn="+java.net.URLEncoder.encode((String)courseList.get(i),"UTF-8");
	                                String indexServer=indexServerName+"/ProcessRequest?req=getSession&"+courseName;
        	                	URL indexurl = new URL(indexServer);
	        	                connection=createHTTPConnection(indexurl);	
					if(connection != null) {
        	        	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                	        	        String str="";
                        	        	try{
                                	        	if((str=in.readLine())!=null){
                                        	        	if(!(str.equals("noLecture"))){
									java.util.StringTokenizer st = new java.util.StringTokenizer(str,"$$");
									while(st.hasMoreTokens()) {
										sessionList.addElement(st.nextElement().toString());
									}
        	                                        	}
                	                        	}
                        	        	}finally {
                                	        	if(in != null) in.close();
                                		}
	        	                } else
	                        	        System.out.println(Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
				} catch(Exception e) {
                	                System.out.println("Exception at getSessionList() in HttpsConnection : "+e.getMessage());
                        	}
			}
                	return sessionList;
		} else
			return null;
        }

	/**
     	 * Verify the Organisation Name and Issuer Common Name 
     	 */
   	private static Boolean verifyCertificate(String Subject_OrgName, String IssuerCN_Name) {
		String orgName=RuntimeDataObject.getController().getCertOrgName();
		String issuerName=RuntimeDataObject.getController().getcertIssuerName();
   		if (Subject_OrgName.equals(orgName)&&(IssuerCN_Name.equals(issuerName)))
			return true;
		else
		    return false;  	
   	}
}
