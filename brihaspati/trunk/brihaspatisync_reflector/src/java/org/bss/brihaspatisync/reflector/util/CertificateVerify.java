package org.bss.brihaspatisync.reflector.util;

/**
 * CertificateVerify.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013
 */

import java.util.Properties;
import java.io.InputStream;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class CertificateVerify {

        private Properties prop=null;

        private static CertificateVerify obj=null;


	/**
 	 * Controller for this class
 	 */  
        public static CertificateVerify getController() {
                if(obj==null) {
                        obj=new CertificateVerify();
                        obj.start();
                }
                return obj;
        }

	/**
 	 * Loading properties file to get resource
 	 */  
        private void start(){
                prop=new Properties();
                try{
                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/Reflector.properties");
                        prop.load(inputStream);
                }catch(Exception e){ }
        }

	/**
 	 * Load Master Indexing server url from properties file
 	 */  
        public String getMasterUrl(){
		return prop.getProperty("MasterServerURL");
        }

	/**
         * Load Certificate issuer organization name from properties file
         */
        public String getCertOrgName(){
                return prop.getProperty("cert_ORG_Name");
        }

	/**
         * Load Certificate issuer Authority name from properties file
         */
        public String getcertIssuerName(){
                return prop.getProperty("cert_Issuer_Name");
        }

	/**
	 * Load Reflector HTTP server port from which clients will connect to receive media stream
	 */
        public int getSinglePortServer(){
                return Integer.parseInt(prop.getProperty("singleport_server"));
        }
	
	/**
         * Load Reflector TCP port to communicate with peer reflector
         **/  
	public int getTcpPort(){
        	return Integer.parseInt(prop.getProperty("ref_tcp_port"));
     	}	
}

