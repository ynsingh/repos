package org.bss.brihaspatisync.util;

/**
 * @(#)RuntimeObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010,2012 ETRG, IIT Kanpur.
 */

import java.util.Properties;
import java.io.InputStream;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class RuntimeDataObject {

        private Properties prop=null;
        private static RuntimeDataObject obj=null;
	private boolean transmit_mic_flag=false;
	private boolean presentation_mic_flag=false;
	private boolean wb_flag=false;
	private String userlist="";
	private String masterReflectorIP="172.26.82.18";
	
	private String proxy_host="";
	private String proxy_port="";
	private String proxy_user="";
	private String proxy_pass="";
	private String videoServer="127.0.0.1";
	private String videoServerPort="8090";
	
	private String vlcServer="C:\\Program Files\\VideoLAN\\VLC";
        private String vlcDeviceName="HP Webcam-50";//"Default";
	
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

	public void setVideoServer(String value){
                videoServer=value;
        }
	
        public String getVideoServer(){
                return videoServer;
        }
	//////////////////////////////////////
	public void setVLCdeviceName(String value){
                vlcDeviceName=value;
        }

        public String getVLCdeviceName(){
                return vlcDeviceName;
        }


        public void setVLCServer(String value){
                vlcServer=value;
        }

        public String getVLCServer(){
                return vlcServer;
        }
	//////////////////
	
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

        public int getAudioPort(){
		return Integer.parseInt(prop.getProperty("client_postaudio_port"));
        }
	
	public int client_inspostvedio_port(){
		return Integer.parseInt(prop.getProperty("client_inspostvedio_port"));
        }
	
	public int client_insgetvedio_port(){
		return Integer.parseInt(prop.getProperty("client_insgetvedio_port"));
      	}
	
	public int client_stdpostvedio_port(){
                return Integer.parseInt(prop.getProperty("client_stdpostvedio_port"));
        }
	
	public int client_postsharescreen_port(){
                return Integer.parseInt(prop.getProperty("client_postsharescreen_port"));
        }
	
	public int client_single_port(){
                return Integer.parseInt(prop.getProperty("single_port"));
        }	

	public int getPPTPort() {
                return Integer.parseInt(prop.getProperty("client_ppt_port"));
        }

	public void setMasterReflectorIP(String ip){
		masterReflectorIP=ip;
	}

	public String getMasterReflectorIP(){
		return masterReflectorIP;
	}
	
	public void setPresentationMicFlag(boolean value){
                presentation_mic_flag=value;
        }

        public boolean getPresentationMicFlag(){
                return presentation_mic_flag;
        }
	
	public void setTransmitMicFlag(boolean value){
		transmit_mic_flag=value;
	}

	public boolean getTransmitMicFlag(){
		return transmit_mic_flag;
	}
	
	public void setWBFlag(boolean value){
		wb_flag=value;
	}

	public boolean getWBFlag(){
		return wb_flag;
	}

	public String getUserList(){
                return userlist;
        }
	
	public void setUserList(String str){
                userlist=str;
        }


	public void setVideoServerPort(String value){
                videoServerPort=value;
        }
 
       	public String getVideoServerPort(){
               return videoServerPort;
       	}
	
}	
