package org.bss.brihaspatisync.util;

/**
 * HttpsUtil.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.util.Vector;

import java.io.IOException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


import java.security.Security;
import java.security.cert.Certificate;
import javax.security.cert.X509Certificate;

import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

//import java.util.Properties;
import javax.swing.JOptionPane;
import javax.net.ssl.SSLSession;
import java.util.StringTokenizer;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */

public class HttpsUtil{
	
	private int netType=0;
	
	private URL indexurl=null;
   	
	private String proxyhost=null;
   	
	private String proxyport=null;
 	
	private static HttpsUtil httpsUtil=null;
	
	private HttpsURLConnection connection=null;
	
	private Log log=Log.getController();

		
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
		/*try{
                	Properties prop=new Properties();
                        prop.load(new FileInputStream("./conf/conn.ini"));
                        netType=Integer.parseInt(prop.getProperty("Type"));
                        if(netType!=1){
                                proxyhost=prop.getProperty("ProxyHost");
                                proxyport=prop.getProperty("ProxyPort");
                                proxyuser=prop.getProperty("ProxyUser");
                                proxypass=prop.getProperty("ProxyPass");
                        }
        	}catch (Exception e){
                        log.setLog("Error in reading of conn.ini file=====>"+e);
        	}*/
		
		//Properties sysprops = System.getProperties();
                //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                //sysprops.put("java.protocol.handler.pkgs","javax.net.ssl.internal.www.protocol");

        	//if(netType==1){
                	connection = (HttpsURLConnection) url.openConnection();
        	/*}else{
                	sysprops.put("http.proxyHost",proxyhost);
                	sysprops.put("http.proxyPort", proxyport);
                	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
                	String proxyAuth=proxyuser+":"+proxypass;
                	String encodedUserPwd =encoder.encode(proxyAuth.getBytes());
                	connection = (HttpsURLConnection) url.openConnection();
                	connection.setRequestProperty("Proxy-Authorization", "Basic " + encodedUserPwd);
        	}*/
	      	
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
   				}catch(Exception e){log.setLog("Error On Line 61"+e.getMessage());}
				return false;
			}		
		});
		if(connection==null)
			JOptionPane.showMessageDialog(null,"Check your Network Connection");
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
				JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
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
                        log.setLog("Error on getIndexingMessage(connection) HttpsUtil.java "+e.getMessage());
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
                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                        }else{
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String str="";
                                try{
                                        while((str=in.readLine())!=null){
						log.setLog("str  -> "+str);
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
							ClientObject.getController().setParentReflectorIP(parent_ref_ip);	
						}					
						else{
							JOptionPane.showMessageDialog(null,str);
							return null;
						}
					}
                                }finally {
                                        if(in != null) in.close();
                                }
                        }
                }catch(Exception e){
                        log.setLog("Error on getReflectorAddress() in HttpsUtil.java "+e.getMessage());
                }
                return ref_ip;
        }

	
	public synchronized Vector getvectorMessage(String sendurl,String message){
        	Vector msgList=new Vector();
		try {
                	URL url = new URL(sendurl);
                        connection=createHTTPConnection(url);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
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
                        log.setLog("Error on getvectorMessage(connection) HttpsUtil.java "+e.getMessage());
                        msgList.clear();
                        return msgList;
                }
                return msgList;
        }
	
	public Vector getSessionForCourse(Vector courseList, String indexServerName){
		Vector userlist=new Vector();
                for(int i=0;i<courseList.size();i++){
                        try{
                                String courseName="cn="+java.net.URLEncoder.encode((String)courseList.get(i),"UTF-8");
                                String indexServer=indexServerName+"/ProcessRequest?req=getSession&"+courseName;
                        	URL indexurl = new URL(indexServer);
	                        connection=createHTTPConnection(indexurl);	
				if(connection==null){
	                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
        	                }else{
                	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        	        String str="";
                                	try{
                                        	while((str=in.readLine())!=null){
                                                	if(!(str.equals("noLecture"))){
                                                        	userlist.addElement(str);
                                                	}
                                        	}
                                	}finally {
                                        	if(in != null) in.close();
                                	}
                        	}

			}catch(Exception e){
                                log.setLog("Error at getSessionList()in HttpsConnection : "+e.getMessage());
                        }
                }
                return userlist;
        }
    	
	/**
     	 * Verify the Organisation Name and Issuer Common Name 
     	 */
   	private Boolean verifyCertificate(String Subject_OrgName, String IssuerCN_Name){
		String orgName=Client.getController().getCertOrgName();
		String issuerName=Client.getController().getcertIssuerName();
   	
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
		
	public String getProxyHost(){
		return proxyhost;
	}
	
	public String getProxyPort(){
		return proxyport;
        }
}
