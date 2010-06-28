package org.bss.brihaspatisync.gui;

/**
 * JoinSession.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur
 */

import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;

import org.bss.brihaspatisync.network.ReceiveQueueHandler;
import org.bss.brihaspatisync.network.http.HTTPClient;
import org.bss.brihaspatisync.network.Log;

import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;	
import org.bss.brihaspatisync.tools.audio_video.AVTransmitReceiveHandler;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class JoinSession {

        private Timer UL_Timer;
	private Timer ref_Timer;
        private String status="available";
	private boolean join_Flag=false;
	private static JoinSession js=null;
	private ClientObject client_obj=ClientObject.getController();
	private Log log=Log.getController();
	
	/**
	* Controller for class.
	*/
	
	protected static JoinSession getController(){
		if(js==null){
			js=new JoinSession();
		}
		return js;
	}

	/**
	 * goToLecture(Lecture_ID) takes Lecture ID, connects to indexing server and get the appropriate peer client and connects this
 	 * client system to it, to received the lecture transmission.The method does not return anything.
 	 */
	protected void goToLecture(String Lecture_ID){
		String lectid="";
		String role="";
		String username="";
                try{
			lectid="lect_id="+URLEncoder.encode(Lecture_ID,"UTF-8");
			String usr_name=client_obj.getUserName();
			if(!(usr_name.equals("")))
	                	username="user="+URLEncoder.encode(usr_name,"UTF-8");
			else
				log.setLog("Insufficient username in goTOLecture() in joinSession Class :"+usr_name);

			if(!(client_obj.getUserRole().equals("")))
        	        	role="role="+URLEncoder.encode(client_obj.getUserRole(),"UTF-8");
			else
				log.setLog("Insufficient role :"+client_obj.getUserRole());

                	String st="status="+URLEncoder.encode(status,"UTF-8");

			String indexName=client_obj.getIndexServerName();
			if(!(indexName.equals(""))){
                		String indexServer=indexName+"/ProcessRequest?req=join&"+lectid+"&"+username+"&"+role+"&"+st;

				//get reflector ip from indexing server.
				String ref_ip =HttpsUtil.getController().getReflectorAddress(indexServer);
				if(!(ref_ip.equals(""))){
					//make connection with is reflector to transmit or receive lecture.			
					ref_Timer=  new Timer();
                			synchronized(ref_Timer){
                        			ref_Timer.schedule(new HTTPClient(ref_ip,Lecture_ID),01,200);
                			}
				}else{
					JOptionPane.showMessageDialog(null,"Reflector ip can't find!!");
				}

				//start GUI for this lecture id 
				startGUIThread();
			}else
                                log.setLog("Insufficient index Server Name in goTOLecture() in joinSession Class :"+indexName);

          	}catch(Exception ex){log.setLog("Error on Join Session==> "+ex.getMessage());}
	}

	/**
	 * This method is responsible for load all media tool for goToLecture(Lecture_ID) 
	 * which start all transmit and receive (local client's Network_Controller).
	 */
	protected void startGUIThread(){
		if(!join_Flag)
			join_Flag=true;
		CourseSessionWindow.getController().setVisible(false);
                MainWindow.getController().getContainer().remove(MainWindow.getController().getDesktop());
                MainWindow.getController().getContainer().add(JoinSessionPanel.getController().createGUI(),BorderLayout.CENTER);
		MainWindow.getController().getContainer().validate();
		MainWindow.getController().getContainer().repaint();
           	try{
                	UL_Timer=new Timer(true);
			UL_Timer.schedule(UserListTimer.getController(),01,60*60*2);
               	}catch(Exception e){log.setLog("Error in user list timer"+e.getMessage());}
		
                try{
			// start thread controller which can handle send and receive thread of network.
			WhiteBoardDraw.getController().start();
                        ReceiveQueueHandler.getController().start();
			AVTransmitReceiveHandler.getController();
			//if(client_obj.getUserRole().equals("student"))
			//AVTransmitReceiveHandler.getController().AVReceiveHandler();
                        //else
                        //        AVTransmitReceiveHandler.getController().AVTransmitHandlerStart();
		}catch(Exception ex){log.setLog("Error in Starting GUIThreads"+ex.getMessage());}
		
	}

	protected Timer getUL_Timer(){
                return UL_Timer;
        }

        protected boolean getJoin_Flag(){
                return join_Flag;
        }
}
