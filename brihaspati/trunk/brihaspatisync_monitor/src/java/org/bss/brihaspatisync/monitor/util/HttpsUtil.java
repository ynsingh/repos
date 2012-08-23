package org.bss.brihaspatisync.monitor.util;

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
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.InetSocketAddress;
import java.net.URI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import java.net.ProxySelector;
import java.net.Proxy;

import javax.swing.JOptionPane;
import javax.net.ssl.SSLSession;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;



import org.bss.brihaspatisync.monitor.util.RuntimeDataObject;
import org.bss.brihaspatisync.monitor.gui.ProxyAuthenticator;



/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 */

public class HttpsUtil{
	
	private int netType=0;
	
	private URL indexurl=null;
   	
	private String proxyhost=null;
   	
	private String proxyport=null;
 	
	private static HttpsUtil httpsUtil=null;
	private InetSocketAddress proxy_addr;
	private Proxy proxy;
        private List list;


	private HttpsURLConnection connection=null;
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();
	

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
   				}catch(Exception e){}
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
		
	public String getProxyHost(){
		return proxyhost;
	}
	
	public String getProxyPort(){
		return proxyport;
        }
}
