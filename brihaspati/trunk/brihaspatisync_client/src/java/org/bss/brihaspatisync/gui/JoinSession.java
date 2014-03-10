package org.bss.brihaspatisync.gui;

/**
 * JoinSession.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011.2014 ETRG, IIT Kanpur
 */

import java.io.File;
import java.awt.BorderLayout;
import java.net.URLEncoder;
import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.Vector;
import java.util.Enumeration;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;

import org.bss.brihaspatisync.network.ReceiveQueueHandler;
import org.bss.brihaspatisync.network.Log;

import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2008
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modified on jan2011
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class JoinSession {

	protected JoinSession(){}

	/**
	 * goToLecture(Lecture_ID) takes Lecture ID, connects to indexing server and get the appropriate peer client and connects this
 	 * client system to it, to received the lecture transmission.The method does not return anything.
 	 */

	protected JoinSession(String Lecture_ID) {
                try {
			String usr_name=ClientObject.getUserName();
			if(usr_name.equals("guest")) {
				usr_name = javax.swing.JOptionPane.showInputDialog(null, "Please give nick name : ", "Nick name panel ", 1);
				if(!usr_name.equals("")) {
					usr_name=java.net.URLEncoder.encode(usr_name+" (guest)");
					ClientObject.setUserName(usr_name);
				}else
					return;
			}
			/**
			 * send local ip to iserver
			 **/
			Enumeration ifaces = NetworkInterface.getNetworkInterfaces();
                	String ip="";
                	while (ifaces.hasMoreElements()) {
                        	NetworkInterface ni = (NetworkInterface)ifaces.nextElement();
        	                Enumeration addrs = ni.getInetAddresses();
                	        while (addrs.hasMoreElements()) {
                        	        InetAddress ia = (InetAddress)addrs.nextElement();
                                	if(!(ia.getHostAddress().equals("127.0.0.1")) ) {
                                        	String ip1=ia.getHostAddress();
						java.util.StringTokenizer Tok = new java.util.StringTokenizer(ip1,".");
						if(Tok.countTokens()==4) {	
							ip=ip1;
						}		
					}
                        	}
                	}
			
			String username="user="+URLEncoder.encode(usr_name,"UTF-8");
			//start GUI for this lecture id 
			String role="role="+URLEncoder.encode(ClientObject.getUserRole(),"UTF-8");
                	String st="status="+URLEncoder.encode("available","UTF-8");
			String indexName=ClientObject.getIndexServerName();
			String lectid="lect_id="+URLEncoder.encode(Lecture_ID,"UTF-8");
			String privateip="privateip="+URLEncoder.encode(ip,"UTF-8");
			
			String indexServer=indexName+"/ProcessRequest?req=join&"+lectid+"&"+username+"&"+role+"&"+st+"&"+privateip;
			//get reflector ip from indexing server.
			String ref_ip =HttpsUtil.getReflectorAddress(indexServer);
			if(!(ref_ip.equals(""))) {
				if(!(ThreadController.getThreadFlag()))
			       		ThreadController.setThreadFlag(true);	
				StatusPanel.getController().sethttpClient("no");
				StatusPanel.getController().setdestopClient("no");
				StatusPanel.getController().setpptClient("no");
				// Thread for get userlist and other media data from reflector.
				startGUIThread();
				ThreadController.setReflectorStatusThreadFlag(true);
	                        ReceiveQueueHandler.getController().start();
				StatusPanel.getController().setProcessBar("no");
				org.bss.brihaspatisync.reflector.Reflector.getController().init("client");
			} else {
				StatusPanel.getController().setStatus(Language.getController().getLangValue("JoinSession.MessageDialog1"));	
			}
          	}catch(Exception ex) {  System.out.println("Exception on Join Session !! "+ex.getMessage());}
	}

	/**
	 * This method is responsible for load all media tool for goToLecture(Lecture_ID) 
	 * which start all transmit and receive (local client's Network_Controller).
	 */
	private void startGUIThread() {
		// set flag for controle all threads of application.
		try {
			//remove CourseSessionWindow and add gui for view all tools activities.
			MainWindow mainWindow=MainWindow.getController();
			mainWindow.setMenuItemText();
			mainWindow.getDesktop().removeAll();
                        mainWindow.getDesktop().setBackground(new java.awt.Color(220,220,220));
                        mainWindow.getDesktop().add(new JoinSessionPanel(),BorderLayout.CENTER);
                        mainWindow.getContainer().add(mainWindow.getDesktop(),BorderLayout.CENTER);
                        mainWindow.getContainer().validate();
                        mainWindow.getContainer().repaint();
		} catch(Exception e) { System.out.println("Exception in start GUI Thread "+e.getMessage()); }
	}
}
