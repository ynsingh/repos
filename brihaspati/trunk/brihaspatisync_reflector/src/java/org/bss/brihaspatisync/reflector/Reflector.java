package org.bss.brihaspatisync.reflector;

/**
 * Reflector.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */
import javax.swing.JOptionPane;

import java.util.Vector;
import java.util.Properties;
import java.io.InputStream;
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> created on 28Jan2009.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> modify on 10Feb2009.
 */

public class Reflector {

	private int udp_port=0;
        private int tcp_port=0;
        private int http_port=0;
        private int video_port=0;
        private int audio_port=0;
        private String master_url="";
	private String cert_ORG_Name="";
        private String cert_Issuer_Name="";
	private static Reflector reflector=null;
	private MaintainLog log=MaintainLog.getController();
	private JButton button=null;
	private JLabel label1 = new JLabel();
    	
	/**
	 * Controller for this calss
	 */
	public static Reflector getController(){
		if(reflector==null)
			reflector=new Reflector();
		return reflector;
	}

	public boolean loadPrtopertiesFile(String fileName){
                Properties prop=new Properties();
                try{
                        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/"+fileName);
                        prop.load(inputStream);
                }catch(Exception e){
			log.setString("Error on loading properties file "+e.getCause());
                        return false;
                }
                master_url=prop.getProperty("MasterServerURL");
                cert_ORG_Name=prop.getProperty("cert_ORG_Name");
                cert_Issuer_Name=prop.getProperty("cert_Issuer_Name");
                udp_port=Integer.parseInt(prop.getProperty("ref_udp_port"));
                tcp_port=Integer.parseInt(prop.getProperty("ref_tcp_port"));
                http_port=Integer.parseInt(prop.getProperty("ref_http_port"));
                audio_port=Integer.parseInt(prop.getProperty("ref_audio_port"));
                video_port=Integer.parseInt(prop.getProperty("ref_video_port"));
                return true;
        }


	/**
         * Constructor, call RegisterToIndesServer for registering this reflector 
	 * to an index server from index server list (index server list would be 
         * found from master server )with it's IP Address and status.On successfull 
	 * registration it will return index serverlist 
	 */
	private void startReflector(){
        	if(loadPrtopertiesFile("Reflector.properties")){
        		log.setString("Starting Reflector !!!!!!!!");
                	try{
				JFrame frame = new JFrame("Reflector Popup");
		        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                		button = new JButton("Ask !!");
                                button.addActionListener(actionListener);
				Container contentPane = frame.getContentPane();
				contentPane.add(label1,BorderLayout.CENTER);
                		contentPane.add(button, BorderLayout.SOUTH);
                		frame.setSize(150, 120);
                		frame.setVisible(true);
			}catch(Exception e){
                              log.setString("Error on starting reflector :"+e.getCause());
                              JOptionPane.showMessageDialog(null,"Error on starting reflector :"+e.getCause());
                      }
              	}else{
                	log.setString("Error on loding properties file");
         	}
	}      	
	 
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
        		Component source = (Component) actionEvent.getSource();
                	Object response = JOptionPane.showInputDialog(source,"Chose options !!","Select a Destination", JOptionPane.QUESTION_MESSAGE,null, new String[] {"Start Reflector", "Stop Reflector"},"Start Reflector");
			String response1=response.toString();
			if(response1.startsWith("Start Reflector")) {
				String masterurl=master_url.trim()+"req=getISList";
				Vector indexServerList=RegisterToIndexServer.getController().connectToMasterServer(masterurl);
				if(indexServerList.size() > 0){
					RegisterToIndexServer.getController().connectToIndexServer(indexServerList);
					label1.setText("Reflector started successfully !! ");
				}else{
					log.setString("Oop!!!! There is some problem in starting Reflector");
					label1.setText("There is some problem in starting Reflector");
					//return;
				}
			}else if(response1.startsWith("Stop Reflector")) {
				LogoutReflector.getController().stopReflector();
                                label1.setText("Reflector stoped successfully !! ");
			}
		}
	};
	
	public String getMasterUrl(){
		return master_url;
        }

        public String getCertOrgName(){
             	return cert_ORG_Name;
        }

        public String getcertIssuerName(){
              	return cert_Issuer_Name;
        }

	public int getHttpPort(){
                return http_port;
        }

        public int getTcpPort(){
                return tcp_port;
        }

        public int getUdpPort(){
                return udp_port;
        }

        public int getAudioPort(){
                return audio_port;
        }
        public int getVedioPort(){
                return video_port;
        }

	public void setMasterUrl(String val){
                master_url=val;
        }

        public void setCertOrgName(String val){
                cert_ORG_Name=val;
        }

        public void setcertIssuerName(String val){
                cert_Issuer_Name=val;
        }

        public void setRefHttpPort(int val){
                http_port=val;
        }

        public void setRefTcpPort(int val){
                tcp_port=val;
        }

        public void setRefUdpPort(int val){
                udp_port=val;
        }

        public void setRefAudioPort(int val){
                audio_port=val;
        }
        public void setRefVedioPort(int val){
                video_port=val;
	}


	/**
	 * Main Method to start reflector.
	 */
	
	public static void main(String args[]){
		Reflector.getController().startReflector();
	}
}
