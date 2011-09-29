package org.bss.brihaspatisync.gui;

/**
 * HandRaiseThreadController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.desktop_sharing.GetSharedScreen;
import org.bss.brihaspatisync.network.desktop_sharing.PostSharedScreen;
import org.bss.brihaspatisync.tools.audio_video.AVTransmitReceiveHandler;
import org.bss.brihaspatisync.tools.audio_video.AudioVideoPanel;
import org.bss.brihaspatisync.tools.audio_video.transmitter.PresentationAudioTransmit;
import org.bss.brihaspatisync.network.ppt_sharing.GetAndPostPPT;
import org.bss.brihaspatisync.network.ppt_sharing.GetPPTScreen;
import org.bss.brihaspatisync.tools.audio_video.transmitter.AudioFromStudent;

//import org.bss.brihaspatisync.tools.presentation.PresentationPanel;

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
	private AudioFromStudent stud_audio=null;
	private PresentationAudioTransmit pres_audio=null;
        private static HandRaiseThreadController thread_controll=null;
	private String username=ClientObject.getController().getUserName();;
        private String role=ClientObject.getController().getUserRole();
	
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
		while(rec_Flag){
			try{
				//Start audio handraise controll for student
				if(starthraudioflag){
					starthraudioflag=false;
					try{
                                	        AVTransmitReceiveHandler.getController().startReceiveHandraiseAudio();
                                                AudioVideoPanel.getController().stopPlayer();
                                                AudioVideoPanel.getController().startPlayer();
                                        } catch(Exception e){System.out.println("Error in start thread for get handraise audio "+e.getCause());}
				}
		
				//Stop audio handraise controll for student
				if(stophraudioflag){
					stophraudioflag=false;
					try{
                                	        AVTransmitReceiveHandler.getController().stopReceiveHandraiseAudio();
                                                AudioVideoPanel.getController().stopPlayer();
                                                AudioVideoPanel.getController().startPlayer();
                                        } catch(Exception e){System.out.println("Error in stop thread for get handraise audio "+e.getCause());}
				}
				
				//Start audio handraise controll for student
				if(start_pres_audio_rec){
                                        start_pres_audio_rec=false;
                                        try{
                                                AVTransmitReceiveHandler.getController().startReceivePresentationAudio();
                                                AudioVideoPanel.getController().stopPlayer();
                                                AudioVideoPanel.getController().startPlayer();
                                        } catch(Exception e){System.out.println("Error in start thread for get presentation audio "+e.getCause());}
                                }

				//Stop audio handraise controll for student
				if(stop_pres_audio_rec){
                                        stop_pres_audio_rec=false;
                                        try{
                                                AVTransmitReceiveHandler.getController().stopReceivePresentationAudio();
                                                AudioVideoPanel.getController().stopPlayer();
                                                AudioVideoPanel.getController().startPlayer();
                                        } catch(Exception e){System.out.println("Error in stop thread for get presentation audio "+e.getCause());}
                                }

				

				if(role.equals("student")) {
					// Start get share screen controll for student
					if(startgetscreeflag) {
						startgetscreeflag=false;
                                       		try {
                                                       	GetSharedScreen.getController().start();
                	                        }catch(Exception sp) {System.out.println("Error in starting GetSharedScreen"+sp.getMessage());}

					}
					
					// Stop get share screen controll for student				
					if(stopgetscreeflag) {
						stopgetscreeflag=false;
                                                try{
                                                        GetSharedScreen.getController().stop();
							StatusPanel.getController().setdestopClient("no");
                                                }catch(Exception sp){System.out.println("Error in stopping GetSharedScreen"+sp.getMessage());}
                                        }

					// Start post share screen controll for student
					if(startpostscreeflag) {	
						try {
                                                        PostSharedScreen.getController().start();
                	                        } catch(Exception sp) {System.out.println("Error in starting PostSharedScreen"+sp.getMessage());}

						startpostscreeflag=false;
					}

					// Start post share screen controll for student
					if(stoppostscreeflag) {          
						stoppostscreeflag=false;
						try{
	                                	        PostSharedScreen.getController().stop();
							StatusPanel.getController().setdestopClient("no");
	                                	}catch(Exception sp){System.out.println("Error in stopping PostSharedScreen"+sp.getMessage());}
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
						if(stud_audio==null){
			                                stud_audio=new AudioFromStudent();
                                                	String result=stud_audio.start();
                                               		if(result!=null)
        	                                        	System.out.println("Could not start audio transmit");
                                                	else 
                                                		System.out.println("Starting Audio from Student");
						}
					}
					//Stop Handraise Audio transmission.
					if(stophraudio){
						stophraudio=false;
						try{
                                                        stud_audio.stop();
                                                        stud_audio=null;
                                                } catch(Exception error){System.out.println("Error in stopping Handraise Audio Transmit");}
					}
					// Starting Presentation Audio mic capture for student to give presentation.
					if(start_pres_audio_transmit){
                                                start_pres_audio_transmit=false;
						if(pres_audio==null) {
                                                	pres_audio=new PresentationAudioTransmit();
                                                	String result=pres_audio.start();
                                                        if(result!=null)
                                                                System.out.println("Could not start presentation audio transmit");
                                                        else
                                                                System.out.println("Starting Audio from Student");
						}	
                                        }
					//Stop Presentation Audio transmission.
					if(stop_pres_audio_transmit){
                                                stop_pres_audio_transmit=false;
                                                try{
                                                        pres_audio.stop();
                                                        pres_audio=null;
                                                } catch(Exception error){System.out.println("Error in stopping Handraise Audio Transmit");}
                                        }

				}
				if(role.equals("instructor")) {	
					// Start post screen sharing controll for instructor.
					if(startpostscreeflag) {
						try{
        	                                	PostSharedScreen.getController().start();
							startpostscreeflag=false;
						}catch(Exception sp){System.out.println("  Error in start post screen for instructor :"+sp.getMessage());}
					}
					//Stop post screen sharing controll for instructor
					if(stoppostscreeflag) {
						PostSharedScreen.getController().stop();
						stoppostscreeflag=false;
						StatusPanel.getController().setdestopClient("no");	
					}
										
					//Start get screen sharing controll for instructor
					if(startgetscreeflag) {
						startgetscreeflag=false;
						try{
        	                                	GetSharedScreen.getController().start();
                                              	}catch(Exception sp){System.out.println("  Error in start get screen for instructor :"+sp.getMessage());}
					}
				
					//Stop get screen sharing controll for instructor
					if(stopgetscreeflag) {
						stopgetscreeflag=false;
                                        	GetSharedScreen.getController().stop();
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
                starthraudio=flag;
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
