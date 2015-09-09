package org.bss.brihaspatisync.gui;

/**
 * HandRaiseThreadController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2015 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.network.desktop_sharing.Post_GetSharedScreen;

import org.bss.brihaspatisync.network.ppt_sharing.GetAndPostPPT;
import org.bss.brihaspatisync.network.ppt_sharing.GetPPTScreen;


/**
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>Created on feb2011	
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Modified on feb2011   
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep Kumar Pal </a>
 */


public class HandRaiseThreadController implements Runnable{

        private Thread runner = null;
        private boolean rec_Flag=false;
        private boolean stopallpermission=false;
        private boolean startgetpermission=false;
	private boolean startpostpermission=false;
		
	private boolean stopsharescreen=false;
	private boolean startgetsharescreen=false;
	private boolean startpostsharescreen=false;

        private static HandRaiseThreadController thread_controll=null;
	private String username=ClientObject.getUserName();;
        private String role=ClientObject.getUserRole();
	
        /**
         * Controller for the class
         */
	public static HandRaiseThreadController getController(){
                if(thread_controll==null){
                        thread_controll=new HandRaiseThreadController();
                }
                return thread_controll;
        }

	public HandRaiseThreadController(){ }

	/**
 	 * Start Thread
 	 */  
        public synchronized void startHandRaiseThread() throws Exception {
		if (runner == null) {
                        runner = new Thread(this);
			rec_Flag=true;
			runner.start();
			System.out.println("UserListThread is Start !!");
                }
        }

	/**
         * Stop Thread
         */ 
        private synchronized void stopHandRaiseThread() {
                if (runner != null) {
			rec_Flag=false;
			runner = null;
			thread_controll=null;
			stopallpermission=false;
        		startgetpermission=false;
        		startpostpermission=false;
			stopsharescreen=false;
        		startgetsharescreen=false;
        		startpostsharescreen=false;
			System.out.println("UserListThread is Stop !!");
             	}
        }

	public void run(){
		while(rec_Flag && ThreadController.getThreadFlag()){
			try{
				if(startpostpermission){ 
					try {
						startpostpermission=false;
						org.bss.brihaspatisync.tools.audio.AudioClient.getController().postAudio(true);	
						org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw.getController().allowDrawforStudent();
						
						Post_GetSharedScreen.getController().startSharedScreen(false);
						org.bss.brihaspatisync.network.video_capture.LocalServer.getController().startLocalServer();
						org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().startStdVideoCapture(false);
					}catch(Exception e){System.out.println("Error in startpostpermission ");}
				}
				if(startgetpermission){
					try {
						startgetpermission=false;
						Post_GetSharedScreen.getController().startSharedScreen(true);
						org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().startStdVideoCapture(true);
					}catch(Exception e){System.out.println("Error in startgetpermission ");}
                                }
				if(stopallpermission){
					try {
						stopallpermission=false;
						if(!(role.equals("instructor"))){
							org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw.getController().denieDrawforStudent();
							org.bss.brihaspatisync.tools.audio.AudioClient.getController().postAudio(false);
							org.bss.brihaspatisync.network.video_capture.LocalServer.getController().stopLocalServer();
						}
						
						Post_GetSharedScreen.getController().stopSharedScreen();		
						StatusPanel.getController().setdestopClient("no");
                        	                org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().stopStdVideoCapture();
						
					}catch(Exception e){System.out.println("Error in stopallpermission ");}
                                }
				
				if(startpostsharescreen){
					try {
						startpostsharescreen=false;
						Post_GetSharedScreen.getController().startSharedScreen(false);
					}catch(Exception e){System.out.println("Error in startpostsharescreen ");}
				}
				if(startgetsharescreen){
					try {
	                                        startgetsharescreen=false;
						Post_GetSharedScreen.getController().startSharedScreen(true);
					}catch(Exception e){System.out.println("Error in startgetsharescreen ");}
                                }
				if(stopsharescreen){
					try {
                                        	stopsharescreen=false;
						Post_GetSharedScreen.getController().stopSharedScreen();
						StatusPanel.getController().setdestopClient("no");
					}catch(Exception e){System.out.println("Error in stopsharescreen ");}
                                }
				runner.yield();
				runner.sleep(500);
			}catch(Exception e){}
		}
		stopHandRaiseThread();	
	}
		
	protected void startPostPermission(boolean flag) {
                startpostpermission=flag;
        }
	
	 public boolean getstartPostPermission() {
                return startpostpermission;
        }

	
	protected void startGetPermission(boolean flag) {
                startgetpermission=flag;
        }
	
	protected void stopAllPermission(boolean flag) {
		stopallpermission=flag;	
        }
	
	/***********  instructor ********/
	
	protected void startPostShareScreen(boolean flag) {
                startpostsharescreen=flag;
        }	

	protected void startGetShareScreen(boolean flag) {
                startgetsharescreen=flag;
        }

        protected void stopShareScreen(boolean flag) {
                stopsharescreen=flag;
        }	
	/************ end instructor ********************/
}
