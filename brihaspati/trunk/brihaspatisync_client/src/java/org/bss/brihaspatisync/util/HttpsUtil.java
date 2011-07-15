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

import javax.swing.JOptionPane;

import java.util.Vector;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.gui.ProxyAuthenticator;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HttpsUtil{
	
	private int netType=0;
	
	private URL indexurl=null;
   	
	private InetSocketAddress proxy_addr;
        private Proxy proxy;
        private List list;

 	
	private static HttpsUtil httpsUtil=null;
	
	private HttpsURLConnection connection=null;

	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	
//	private Log log=Log.getController();

		
	public static HttpsUtil getController(){
		if (httpsUtil==null){
			httpsUtil=new HttpsUtil();
		}
		return httpsUtil;
	}
	
		
	/**
   	 * The URL class is capable of handling http:// and https:// URLs
   	 */
   	public HttpsURLConnection createHTTPConnection(URL url) throws IOException {

		try {
                        System.setProperty("java.net.useSystemProxies","true");
                        list = ProxySelector.getDefault().select( new URI(url.toString()));
                        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                                proxy = (Proxy) iter.next();
                                proxy_addr = (InetSocketAddress)proxy.address();
			}

		}catch (Exception e) { e.printStackTrace();}

              	if(proxy_addr != null) {
                	runtime_object.setProxyHost(proxy_addr.getHostName());
                      	runtime_object.setProxyPort(Integer.toString(proxy_addr.getPort()));
			ProxyAuthenticator.getController().createGUI();
		
			if((!(runtime_object.getProxyHost()).equals("")) && (!(runtime_object.getProxyPort()).equals(""))){
				Properties sysProps = System.getProperties();
	        	     	sysProps.put( "proxySet", "true" );
        	       		sysProps.put( "proxyHost", runtime_object.getProxyHost());
              			sysProps.put( "proxyPort", runtime_object.getProxyPort());
              			Authenticator authenticator = new Authenticator() {
                						public PasswordAuthentication getPasswordAuthentication() {
                                        				return (new PasswordAuthentication(runtime_object.getProxyUser(),runtime_object.getProxyPass().toCharArray()));
         							}};
	             		Authenticator.setDefault(authenticator);
			}
		}else{
			System.out.println("you are using DIRECT connection");
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
   				}catch(Exception e){System.out.println("Error On Line 61"+e.getMessage());}
				return false;
			}		
		});
		if(connection==null)
	        	JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HttpsUtil.MessageDialog1"))				;
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
   	private String getIssuerCN_Name(X509Certificate cert){
   	
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
   	private String getSubjectOrg_Name(X509Certificate cert){
   
   		String SubjectDN=cert.getSubjectDN().getName();
   		
   		StringTokenizer st_SubjectDN=new StringTokenizer(SubjectDN,",");
   		String Subject_CN=st_SubjectDN.nextToken();
   		String Subject_OU=st_SubjectDN.nextToken();
   		String Subject_O=st_SubjectDN.nextToken();
   		//StringTokenize st_Subject_O=new StringTokenizer(Subject_O,"=");
		StringTokenizer st_Subject_O=new StringTokenizer(Subject_O,"=");
   		String Subject_Org=st_Subject_O.nextToken();
   		String Subject_OrgName=st_Subject_O.nextToken();
   		return Subject_OrgName;
   		
   	}
	
	public synchronized boolean getIndexingMessage(String indexServer){
        	boolean flag=false;
               	try {
			URL indexurl = new URL(indexServer);
			connection=createHTTPConnection(indexurl);
			if(connection==null){
				JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
       	        	}else{
	                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	                String str="";
                	        try{
                        	        while((str=in.readLine())!=null){
                                	        if(str.equals("Successfull")){
                                        	        flag=true;
	                                        }
                        	        }
                               	}finally {
                        		if(in != null) in.close();
                           	}
			}
                }catch(Exception e){
                        System.out.println("Error on getIndexingMessage(connection) HttpsUtil.java "+e.getMessage());
                }
		return flag;
        }

	/**
	 * This method is used to get Reflector's Address from inexing server passed as parameter ,which is used to join session. 
	 */
	public synchronized String getReflectorAddress(String indexServer){
		String ref_ip="";
		String parent_ref_ip="";
                try {
                        URL indexurl = new URL(indexServer);
                        connection=createHTTPConnection(indexurl);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HttpsUtil.MessageDialog1"));
                        }else{
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String str="";
                                try{
                                        while((str=in.readLine())!=null){
						if(str.startsWith("current")){
							String str1[]=str.split(",");
							// store current reflector ipAddress
							ref_ip=str1[0].replaceAll("current/","");
							// set current reflector ipaddress in clientObject for use in runtime.
							ClientObject.getController().setReflectorIP(ref_ip);
							// store parent reflector ipaddress of current reflector 
							parent_ref_ip="parent"+str1[1].replaceAll("parent/","");
							// set parent reflector ipaddress of current reflector in ClientObject 
							// for use in runtime
							String handraise_port=str1[2].replaceAll("port=","");
							//RuntimeDataObject.getController().setAudioHandraisePort(handraise_port);
							// for use in runtime
							ClientObject.getController().setParentReflectorIP(parent_ref_ip);	
							String a_status=str1[3].replaceAll("A=","");
							ClientObject.getController().setAudioStatus(a_status);
							String v_status=str1[4].replaceAll("V=","");
							ClientObject.getController().setVideoStatus(v_status);
						}else if(str.startsWith("date")) {					
							str=str.replaceAll("date","");
							return str;
						}else {
							JOptionPane.showMessageDialog(null,str);
							return null;
						}
					}
                                }finally {
                                        if(in != null) in.close();
                                }
                        }
                }catch(Exception e){
                        System.out.println("Error on getReflectorAddress() in HttpsUtil.java "+e.getMessage());
                }
                return ref_ip;
        }

	
	public synchronized Vector getvectorMessage(String sendurl,String message){
        	Vector msgList=new Vector();
		try {
                	URL url = new URL(sendurl);
                        connection=createHTTPConnection(url);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
                        }else{
                        	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str="";
                                try{
					while((str=in.readLine())!=null){
	                                	if(!(str.equals(message))){
        	                                	msgList.addElement(str);
                        	                }
                                	}
                                }finally {
                                	if(in != null) in.close();
                                }
                        }
                }catch(Exception e){
                        System.out.println("Error on getvectorMessage(connection) HttpsUtil.java "+e.getMessage());
                        msgList.clear();
                        return msgList;
                }
                return msgList;
        }
	
	public Vector getSessionForCourse(Vector courseList, String indexServerName) {
		Vector userlist=new Vector();
                for(int i=0;i<courseList.size();i++){
                        try{
                                String courseName="cn="+java.net.URLEncoder.encode((String)courseList.get(i),"UTF-8");
                                String indexServer=indexServerName+"/ProcessRequest?req=getSession&"+courseName;
                        	URL indexurl = new URL(indexServer);
	                        connection=createHTTPConnection(indexurl);	
				if(connection==null){
	                                JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HttpsUtil.MessageDialog2"));
        	                }else{
                	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        	        String str="";
                                	try{
                                        	while((str=in.readLine())!=null){
                                                	if(!(str.equals("noLecture"))){
								java.util.StringTokenizer str1 = new java.util.StringTokenizer(str,"$$");
								while(str1.hasMoreTokens()) {
									userlist.addElement(str1.nextElement().toString());
								}
                                                	}
                                        	}
                                	}finally {
                                        	if(in != null) in.close();
                                	}
                        	}

			}catch(Exception e){
                                System.out.println("Error at getSessionList()in HttpsConnection : "+e.getMessage());
                        }
                }
                return userlist;
        }
    	
	/**
     	 * Verify the Organisation Name and Issuer Common Name 
     	 */
   	private Boolean verifyCertificate(String Subject_OrgName, String IssuerCN_Name){
		String orgName=RuntimeDataObject.getController().getCertOrgName();
		String issuerName=RuntimeDataObject.getController().getcertIssuerName();
   	
   		if (Subject_OrgName.equals(orgName)&&(IssuerCN_Name.equals(issuerName)))
			return true;
		else
		    return false;  	
		
   	}
	
		
	public int getNetType(){
		return netType;
	}
	
	public void setNetType(int type){
                netType=type;
        }
}
