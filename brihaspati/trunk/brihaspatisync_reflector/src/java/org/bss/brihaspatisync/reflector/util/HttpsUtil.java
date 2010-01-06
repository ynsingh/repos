package org.bss.brihaspatisync.reflector.util;

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

import javax.swing.JOptionPane;
import javax.net.ssl.SSLSession;
import java.util.StringTokenizer;
import org.bss.brihaspatisync.reflector.Reflector;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> created on 28Jan2009.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> created on 28Jan2009.
 */

public class HttpsUtil{
	
	private int netType=0;
	
	private URL indexurl=null;
   	
	private String proxyhost=null;
   	
	private String proxyport=null;
 	
	private static HttpsUtil httpsUtil=null;
	
	private HttpsURLConnection connection=null;

	/**
	 * Constructor for this class.
	 */		
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
                        System.out.println("Error in reading of conn.ini file=====>"+e);
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
   				}catch(Exception e){
				//	System.out.println("Error On Line 61"+e.getMessage());
				}
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
   		StringTokenizer st_Subject_O=new StringTokenizer(Subject_O,"=");
   		String Subject_Org=st_Subject_O.nextToken();
   		String Subject_OrgName=st_Subject_O.nextToken();
   		return Subject_OrgName;
   		
   	}

	/**
         * Verify the Organisation Name and Issuer Common Name
         */
        private Boolean verifyCertificate(String Subject_OrgName, String IssuerCN_Name){
                String orgName=Reflector.getController().getCertOrgName();
                String issuerName=Reflector.getController().getcertIssuerName();

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
