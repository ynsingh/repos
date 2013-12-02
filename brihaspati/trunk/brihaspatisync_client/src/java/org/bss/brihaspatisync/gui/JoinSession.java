package org.bss.brihaspatisync.gui;

/**
 * JoinSession.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur
 */

import java.io.File;
import java.awt.BorderLayout;
import java.net.URLEncoder;
import java.util.Vector;
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

	private ClientObject client_obj=ClientObject.getController();
	protected JoinSession(String Lecture_ID) {
                try{
			String lectid="lect_id="+URLEncoder.encode(Lecture_ID,"UTF-8");
			String usr_name=client_obj.getUserName();
			if(!(usr_name.equals(""))){
				if(usr_name.equals("guest")) {
					usr_name = javax.swing.JOptionPane.showInputDialog(null, "Please give nick name : ", "Nick name panel ", 1);
					if(!usr_name.equals("")) {
						usr_name=java.net.URLEncoder.encode(usr_name+" (guest)");
						client_obj.setUserName(usr_name);
					}else
						return;
				}
				String username="user="+URLEncoder.encode(usr_name,"UTF-8");
				//start GUI for this lecture id 
				if(!(client_obj.getUserRole().equals(""))) {
        	        	   String role="role="+URLEncoder.encode(client_obj.getUserRole(),"UTF-8");
                		   String st="status="+URLEncoder.encode("available","UTF-8");
				   String indexName=client_obj.getIndexServerName();
				   if(!(indexName.equals(""))) {
	                		String indexServer=indexName+"/ProcessRequest?req=join&"+lectid+"&"+username+"&"+role+"&"+st;
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
						startThread(Lecture_ID);
						StatusPanel.getController().setProcessBar("no");
					} else {
						StatusPanel.getController().setStatus(Language.getController().getLangValue("JoinSession.MessageDialog1"));	
					}
				   } else
                                	System.out.println("Insufficient index Server Name in goTOLecture() in joinSession Class :"+indexName);
				}
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
		}catch(Exception e){}
	}

	private void startThread(String lecture_id) {	
		// Timer to print user list in gui.
		// start thread controller which can handle send and receive thread of network.
		try {	
			ThreadController.setReflectorStatusThreadFlag(true);
                        org.bss.brihaspatisync.network.singleport.SinglePortClient.getController().start();
			WhiteBoardDraw.getController().start();
                        ReceiveQueueHandler.getController().start();
			HandRaiseThreadController.getController().startHandRaiseThread();
			//start audio thread
			String a_status=org.bss.brihaspatisync.util.AudioUtilObject.getAudioStatus();
                        if(a_status.equals("1")){
				org.bss.brihaspatisync.tools.audio.AudioClient.getController().startThread();
                                if((client_obj.getUserRole()).equals("instructor"))
                                        org.bss.brihaspatisync.tools.audio.AudioClient.getController().postAudio(true);
			}

			//start video thread
			String v_status=org.bss.brihaspatisync.util.AudioUtilObject.getVideoStatus();
			if((client_obj.getUserRole()).equals("instructor")) {
				if(v_status.equals("1")){	
                        		org.bss.brihaspatisync.network.video_capture.LocalServer.getController().startLocalServer();
					org.bss.brihaspatisync.network.video_capture.PostVideoCapture.getController().start(false);
				}
			} else{ 
				org.bss.brihaspatisync.network.video_capture.PostVideoCapture.getController().start(true);
			}
                }catch(Exception e){}
	}
}
