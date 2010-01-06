package org.bss.brihaspatisync;

/**
 * Client.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.Properties;
import java.io.InputStream;
import org.bss.brihaspatisync.util.ClientObject;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.gui.LoginWindow;





/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class Client {
	
	private static Client client=null;
	private String masterURL="";
        private String cert_ORG_Name="";
        private String cert_Issuer_Name="";
        private int ref_udp_port=0;
        private int ref_tcp_port=0;
        private int ref_http_port=0;
        private int ref_video_port=0;
        private int ref_audio_port=0;
        private int ref_userlist_port=0;
	private Log log=Log.getController();
	public static Client getController(){
		if(client==null)
			client=new Client();
		return client;
	}
	
	/**
         * Main Method for client
         */
	public Client(){}

	/**
	 * Load properties file
	 */
	public boolean loadPrtopertiesFile(String fileName){
		Properties prop=new Properties();
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/"+fileName);
                	prop.load(inputStream);
		}catch(Exception e){
			log.setLog("Error on loading properties file "+e.getCause());
			return false;
		}
		masterURL=prop.getProperty("MasterServerURL");
		cert_ORG_Name=prop.getProperty("cert_ORG_Name");
		cert_Issuer_Name=prop.getProperty("cert_Issuer_Name");
		ref_udp_port=Integer.parseInt(prop.getProperty("ref_udp_port"));
		ref_tcp_port=Integer.parseInt(prop.getProperty("ref_tcp_port"));
		ref_http_port=Integer.parseInt(prop.getProperty("ref_http_port"));
		ref_audio_port=Integer.parseInt(prop.getProperty("ref_audio_port"));
		ref_video_port=Integer.parseInt(prop.getProperty("ref_video_port"));
		return true;				 
      	}

	public void startClient(){
		boolean value=loadPrtopertiesFile("brihaspatisync.properties");
                if(value==true){
                        try{
                                /* The look and feel is set */
                                //UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
                                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        }catch(Exception e){log.setLog("Error on Loading Look&Feel");}

                        if(((ClientObject.getController().getIndexServerList()).size()) > 1){
                                // initiate Main Window GUI.
                                MainWindow.getController().getDesktop().add(LoginWindow.getController());
				log.start();
				
                        }else{
                                JOptionPane.showMessageDialog(null,"Index server list not found. Please try again !!");
                        }
                }else{
                         JOptionPane.showMessageDialog(null,"Error on loading properties file !!");
                }
	}

	public String getMasterUrl(){
		if(!(masterURL.equals("")))
                	return masterURL;
		else
			JOptionPane.showMessageDialog(null,"Master URL not found !!");
		return null;
        }

        public String getCertOrgName(){
		if(!(cert_ORG_Name.equals("")))
                	return cert_ORG_Name;
		else
                        JOptionPane.showMessageDialog(null,"Certificate organization name not found !!");
                return null;

        }

        public String getcertIssuerName(){
		if(!(cert_Issuer_Name.equals("")))
                	return cert_Issuer_Name;
		else
                        JOptionPane.showMessageDialog(null,"Certificate issuer name not found !!");
                return null;

        }

        public int getRefHttpPort(){
		if(ref_http_port>0)
                	return ref_http_port;
		else
                        JOptionPane.showMessageDialog(null,"Http port for reflector not found !!");
                return 0;

        }

        public int getRefTcpPort(){
		if(ref_tcp_port>0)
                	return ref_tcp_port;
		else
                        JOptionPane.showMessageDialog(null,"Tcp port for reflector not found !!");
                return 0;

        }

        public int getRefUdpPort(){
		if(ref_udp_port>0)
                	return ref_udp_port;
		else
                        JOptionPane.showMessageDialog(null,"Udp port for reflector not found !!");
                return 0;

        }

        public int getRefAudioPort(){
		if(ref_audio_port>0)
               	 	return ref_audio_port;
		else
                        JOptionPane.showMessageDialog(null,"Audio port for reflector not found !!");
                return 0;

        }

        public int getRefVedioPort(){
		if(ref_video_port>0)
                	return ref_video_port;
		else
                        JOptionPane.showMessageDialog(null,"Video port for reflector not found !!");
                return 0;

        }

	public void setMasterUrl(String val){
                masterURL=val;
        }

        public void setCertOrgName(String val){
                cert_ORG_Name=val;
        }

        public void setcertIssuerName(String val){
                cert_Issuer_Name=val;
        }

        public void setRefHttpPort(int val){
                ref_http_port=val;
        }

        public void setRefUserPort(int val){
                ref_userlist_port=val;
        }

        public void setRefTcpPort(int val){
                ref_tcp_port=val;
        }

        public void setRefUdpPort(int val){
                ref_udp_port=val;
        }

        public void setRefAudioPort(int val){
                ref_audio_port=val;
        }
        public void setRefVedioPort(int val){
                ref_video_port=val;
        }

	public static void main(String args[]){
		Client.getController().startClient();
	}
}
