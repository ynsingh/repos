package org.bss.brihaspatisync.reflector.util;

/**
 * @(#)RuntimeObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
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
                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/Reflector.properties");
                        prop.load(inputStream);
                }catch(Exception e){ }
        }

        public String getMasterUrl(){
		return prop.getProperty("MasterServerURL");
        }


        public String getCertOrgName(){
                return prop.getProperty("cert_ORG_Name");
        }

        public String getcertIssuerName(){
                return prop.getProperty("cert_Issuer_Name");
        }

        public int getHttpPort(){
                return Integer.parseInt(prop.getProperty("ref_http_port"));
        }

        public int getTcpPort(){
                return Integer.parseInt(prop.getProperty("ref_tcp_port"));
        }

        public int getUdpPort(){
                return Integer.parseInt(prop.getProperty("ref_udp_port"));
        }

        public int getAudioPort(){
                return Integer.parseInt(prop.getProperty("ref_audio_port"));
        }

        public int getVedioPort(){
                return Integer.parseInt(prop.getProperty("ref_video_port"));
        }
}

