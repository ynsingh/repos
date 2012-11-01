package org.bss.brihaspatisync.gui;

/**
 * HandRaiseThreadController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.network.desktop_sharing.Post_GetSharedScreen;

import org.bss.brihaspatisync.network.ppt_sharing.GetAndPostPPT;
import org.bss.brihaspatisync.network.ppt_sharing.GetPPTScreen;


/**
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>Created on feb2011	
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Modified on feb2011   
 */


public class HandRaiseThreadController implements Runnable{

        private Thread runner = null;
	private boolean startgetscreeflag=false;
        private boolean startpostscreeflag=false;
        private boolean starthraudioflag=false;
        private boolean stophraudioflag=false;

        private boolean stopgetscreeflag=false;
        private boolean stoppostscreeflag=false;
        private boolean startpostpptflag=false;
        private boolean startgetpptflag=false;
	private boolean rec_Flag = false;
	private boolean starthraudio = false;
	private boolean stophraudio = false;
	
	private boolean start_pres_audio_transmit = false;
	private boolean stop_pres_audio_transmit = false;

	private boolean start_pres_audio_rec = false;
        private boolean stop_pres_audio_rec = false;

	private boolean pres_mic_flag=false;
        private static HandRaiseThreadController thread_controll=null;
	private String username=ClientObject.getController().getUserName();;
        private String role=ClientObject.getController().getUserRole();
	
        /**
         * Controller for the class
         */
	protected static HandRaiseThreadController getController(){
                if(thread_controll==null){
                        thread_controll=new HandRaiseThreadController();
                }
                return thread_controll;
        }

	public HandRaiseThreadController(){ }

	/**
 	 * Start Thread
 	 */  
        public synchronized void start() throws Exception {
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
        public synchronized void stop() {
                if (runner != null) {
			rec_Flag=false;
                        runner.stop();
			runner = null;
			System.out.println("UserListThread is Stop !!");
             	}
        }

	public void run(){
		while(rec_Flag && ThreadController.getController().getThreadFlag()){
			try{
				//Start audio handraise controll for student
				if(starthraudioflag){
					starthraudioflag=false;
					{	
						VideoPanel.getController().addStudentPanel();
					}
					org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().start(true);
				}
		
				//Stop audio handraise controll for student
				if(stophraudioflag){
					stophraudioflag=false;
					org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().stop();
					{
						VideoPanel.getController().removeStudentPanel();
					}
				}
				
				if(role.equals("student")) {
					// Start get share screen controll for student
					if(startgetscreeflag) {
						startgetscreeflag=false;
                                       		try {
							Post_GetSharedScreen.getController().start(true);
                	                        }catch(Exception sp) {System.out.println("Error in starting GetSharedScreen"+sp.getMessage());}

					}
					
					// Stop get share screen controll for student				
					if(stopgetscreeflag) {
						stopgetscreeflag=false;
                                                try{
							Post_GetSharedScreen.getController().stop();
							StatusPanel.getController().setdestopClient("no");
                                                }catch(Exception sp){System.out.println("Error in stopping GetSharedScreen"+sp.getMessage());}
                                        }

					// Start post share screen controll for student
					if(startpostscreeflag) {	
						try {
                                                        Post_GetSharedScreen.getController().start(false);
                	                        } catch(Exception sp) {System.out.println("Error in starting Post_GetSharedScreen "+sp.getMessage());}

						startpostscreeflag=false;
					}

					// Start post share screen controll for student
					if(stoppostscreeflag) {          
						stoppostscreeflag=false;
						try{
	                                	        Post_GetSharedScreen.getController().stop();
							StatusPanel.getController().setdestopClient("no");
	                                	}catch(Exception sp){System.out.println("Error in stopping Post_GetSharedScreen"+sp.getMessage());}
					}

					//start post PPT Presentation controll for student
					if(startpostpptflag) {
						startpostpptflag=false;
						org.bss.brihaspatisync.tools.presentation.PresentationPanel.getController().setEnable_Decable(true);		
						GetAndPostPPT.getController().startFTPClient("POST");
					}

					//start get PPT Presentation controll for student
					if(startgetpptflag) {
						GetPPTScreen.getController().start();
						startgetpptflag=false;
					}
					// Starting Audio mic capture for student to ask question or clear doubts from instructor.
					if(starthraudio){
						starthraudio=false;
						org.bss.brihaspatisync.network.video_capture.LocalServer.getController().start();
						org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().start(false);
					}
					//Stop Handraise Audio transmission.
					if(stophraudio){
						stophraudio=false;
						org.bss.brihaspatisync.network.video_capture.LocalServer.getController().stop();
                                                org.bss.brihaspatisync.network.video_capture.StudentPostVideoCapture.getController().stop();
					}
					//Stop Presentation Audio transmission.
					if(stop_pres_audio_transmit){
                                                stop_pres_audio_transmit=false;
                                                try{
                                                        //pres_audio.stop();
                                                        //pres_audio=null;
                                                } catch(Exception error){System.out.println("Error in stopping Handraise Audio Transmit");}
                                        }

				}
				if(role.equals("instructor")) {	
					// Start post screen sharing controll for instructor.
					if(startpostscreeflag) {
						try{
        	                                	Post_GetSharedScreen.getController().start(false);
							startpostscreeflag=false;
						}catch(Exception sp){System.out.println("  Error in start post screen for instructor :"+sp.getMessage());}
					}
					//Stop post screen sharing controll for instructor
					if(stoppostscreeflag) {
						Post_GetSharedScreen.getController().stop();
						stoppostscreeflag=false;
						StatusPanel.getController().setdestopClient("no");	
					}
										
					//Start get screen sharing controll for instructor
					if(startgetscreeflag) {
						startgetscreeflag=false;
						try{
							Post_GetSharedScreen.getController().start(true);
                                              	}catch(Exception sp){System.out.println("  Error in start get screen for instructor :"+sp.getMessage());}
					}
				
					//Stop get screen sharing controll for instructor
					if(stopgetscreeflag) {
						stopgetscreeflag=false;
                                        	//GetSharedScreen.getController().stop();
						Post_GetSharedScreen.getController().stop();
						StatusPanel.getController().setdestopClient("no");
					}
					//Start post PPT Presentation controll for instructor.
					if(startpostpptflag) {
						startpostpptflag=false;
						org.bss.brihaspatisync.tools.presentation.PresentationPanel.getController().setEnable_Decable(true);	
                                                GetAndPostPPT.getController().startFTPClient("POST");
                                        }
					
					//Start get PPT Presentation controll for instructor.
					if(startgetpptflag) {
						GetPPTScreen.getController().start();
						startgetpptflag=false;
                                        }
				}	
				runner.yield();
				runner.sleep(100);
			}catch(Exception e){}
		}	
	}
	


	protected void startgetpptFlag(boolean flag) {
                startgetpptflag=flag;
        }

        protected void startpostpptFlag(boolean flag) {
                startpostpptflag=flag;
        }

	protected void startGetScreenFlag(boolean flag) {
		startgetscreeflag=flag;
	}
	
	protected void startPostScreenFlag(boolean flag) {
                startpostscreeflag=flag;
        }
	
	protected void stopGetScreenFlag(boolean flag) {
                stopgetscreeflag=flag;
        }

        protected void stopPostScreenFlag(boolean flag) {
                stoppostscreeflag=flag;
        }
	
	protected void starthraudioflag(boolean flag) {
                starthraudioflag=flag;
        }

	protected void stophraudioflag(boolean flag) {
                stophraudioflag=flag;
        }
	
	protected void starthandraiseraudioflag(boolean flag) {
                starthraudio=flag;
        }

        protected void stophandraiseraudioflag(boolean flag) {
                stophraudio=flag;
        }
	
	protected void startpresaudioflag(boolean flag) {
                start_pres_audio_transmit=flag;
        }

        protected void stoppresaudioflag(boolean flag) {
                stop_pres_audio_transmit=flag;
        }

	protected void startPresAudioRec(boolean flag) {
                start_pres_audio_rec=flag;
        }

        protected void stopPresAudioRec(boolean flag) {
                stop_pres_audio_rec=flag;
        }
	
}
