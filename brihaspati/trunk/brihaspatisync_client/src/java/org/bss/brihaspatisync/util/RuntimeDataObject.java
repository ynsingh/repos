package org.bss.brihaspatisync.util;

/**
 * @(#)RuntimeObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.util.Properties;
import java.io.InputStream;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class RuntimeDataObject {

        private Properties prop=null;
        private static RuntimeDataObject obj=null;
        public static RuntimeDataObject getController(){
                if(obj==null) {
                        obj=new RuntimeDataObject();
                        obj.start();
                }
                return obj;
        }
 
	private void start(){
                prop=new Properties();
                try{
                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/brihaspatisync.properties");
                        prop.load(inputStream);
                }catch(Exception e){ }
        }

	public String getMasterUrl(){
		return prop.getProperty("MasterServerURL");
        }

        protected String getCertOrgName(){
		return prop.getProperty("cert_ORG_Name");
        }

        protected String getcertIssuerName(){
		return prop.getProperty("cert_Issuer_Name");
        }

        public String getRefHttpPort(){
		return prop.getProperty("client_http_port");
        }

        public int getRefAudioPort(){
		return Integer.parseInt(prop.getProperty("client_audio_port"));
        }

        public int getRefVedioPort(){
		return Integer.parseInt(prop.getProperty("client_video_port"));
        }
	
	public int getClientFTPPort(){
                return Integer.parseInt(prop.getProperty("client_ftp_port"));
        }
}	


