package org.bss.brihaspatisync.gui;
	
/**
 * HandRaisePanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010-2011 ETRG,Kanpur.
 */

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;
import org.bss.brihaspatisync.tools.audio_video.AVTransmitReceiveHandler;
import org.bss.brihaspatisync.tools.audio_video.AudioVideoPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class HandRaisePanel {

	private JPanel mainPanel=null;
	private JButton wbBttn=null;;
	private JButton denieWB=null;
	private JButton micBttn=null;
	private JButton denieMic=null;
	private JButton screenBttn=null;
	private JButton denieScreen=null;
	
	private JButton pptBttn=null;
	private JButton denieppt=null;
	
	private static HandRaisePanel hr_panel=null;
	private ClientObject object=ClientObject.getController();
	private String role=object.getUserRole();	
	
	/**
	 * Controller for class.
	 */	
	public static HandRaisePanel getController() {
		if(hr_panel==null){
			hr_panel=new HandRaisePanel();
		}
		return hr_panel;
	}

	/**
	 * Creating GUI for UserListPanel.
	 */
	protected JPanel createGUI() {
		
		ClassLoader clr= this.getClass().getClassLoader();
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridLayout(8,1,0,4)); 
		// Buttons for student handraise panel.
	 	if(role.equals("student")){
			wbBttn=new JButton("Get-WB",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
			wbBttn.setToolTipText("Request to draw on Whiteboard");
                	wbBttn.setActionCommand("Get-WB");
                	wbBttn.addActionListener(HandRaiseAction.getController());

			
			denieWB=new JButton("Cancel-WB",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
			denieWB.setToolTipText("Withdraw from Whiteboard request");
                	denieWB.setActionCommand("Denie-WB");
                	denieWB.addActionListener(HandRaiseAction.getController());	
			
			micBttn=new JButton("Get-Mic",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
			micBttn.setToolTipText("Request mic from instructotr");
                	micBttn.setActionCommand("Get-Mic");
                	micBttn.addActionListener(HandRaiseAction.getController());

			denieMic=new JButton("Cancel-Mic",new ImageIcon(clr.getResource("resources/images/user/denie.png")));				
			denieMic.setToolTipText("Withdraw from Mic Request");
                	denieMic.setActionCommand("Denie-Mic");
                	denieMic.addActionListener(HandRaiseAction.getController());
			
			screenBttn=new JButton("Get-Screen",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        screenBttn.setToolTipText("Request to screen share from instructotr");
                        screenBttn.setActionCommand("Get-Screen");
                        screenBttn.addActionListener(HandRaiseAction.getController());

                        denieScreen=new JButton("Cancel-Screen",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denieScreen.setToolTipText("Withdraw from screen share request");
                        denieScreen.setActionCommand("Denie-Screen");
                        denieScreen.addActionListener(HandRaiseAction.getController());

			pptBttn=new JButton("Get-PPT",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        pptBttn.setToolTipText("Request to PPT from instructotr");
                        pptBttn.setActionCommand("Get-PPT");
                        pptBttn.addActionListener(HandRaiseAction.getController());

                        denieppt=new JButton("Cancel-PPT",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denieppt.setToolTipText("Withdraw from PPT request");
                        denieppt.setActionCommand("Denie-PPT");
                        denieppt.addActionListener(HandRaiseAction.getController());

		// Buttons for instructor Handraise panel.
		}else if(role.equals("instructor")){
			wbBttn=new JButton("Allow-WB",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        wbBttn.setToolTipText("Allow to draw on whiteboard");
                        wbBttn.setActionCommand("Allow-WB");
                        wbBttn.addActionListener(HandRaiseAction.getController());


                        denieWB=new JButton("Deny-WB",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denieWB.setToolTipText("Denie student to draw on whiteboard");
                        denieWB.setActionCommand("Denie-WB");
                        denieWB.addActionListener(HandRaiseAction.getController());
                        
                        micBttn=new JButton("Allow-Mic",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        micBttn.setToolTipText("Allow mic to student");
                        micBttn.setActionCommand("Allow-Mic");
                        micBttn.addActionListener(HandRaiseAction.getController());

                        denieMic=new JButton("Deny-Mic",new ImageIcon(clr.getResource("resources/images/user/denie.png")));                           
                        denieMic.setToolTipText("Denie student for mic request");
                        denieMic.setActionCommand("Denie-Mic");
                        denieMic.addActionListener(HandRaiseAction.getController());

			screenBttn=new JButton("Allow-Screen",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        screenBttn.setToolTipText("Allow screen share to student");
                        screenBttn.setActionCommand("Allow-Screen");
                        screenBttn.addActionListener(HandRaiseAction.getController());

                        denieScreen=new JButton("Deny-Screen",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denieScreen.setToolTipText("Denie student for share screen");
                        denieScreen.setActionCommand("Denie-Screen");
                        denieScreen.addActionListener(HandRaiseAction.getController());
			pptBttn=new JButton("Allow-PPT",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        pptBttn.setToolTipText("Allow PPT to student");
                        pptBttn.setActionCommand("Allow-PPT");
                        pptBttn.addActionListener(HandRaiseAction.getController());

                        denieppt=new JButton("Deny-PPT",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denieppt.setToolTipText("Denie student for PPT");
                        denieppt.setActionCommand("Denie-PPT");
                        denieppt.addActionListener(HandRaiseAction.getController());
		}
		wbBttn.setEnabled(false);
		denieWB.setEnabled(false);
		micBttn.setEnabled(false);
		denieMic.setEnabled(false);
		screenBttn.setEnabled(false);
		denieScreen.setEnabled(false);
	
		pptBttn.setEnabled(false);
                denieppt.setEnabled(false);

		mainPanel.add(wbBttn);
		mainPanel.add(denieWB);
		mainPanel.add(micBttn);
		mainPanel.add(denieMic);
		mainPanel.add(screenBttn);
                mainPanel.add(denieScreen);
		mainPanel.add(pptBttn);
                mainPanel.add(denieppt);

		return mainPanel;
	}

	/**
 	 * Enable or disable buttons for handraise panel according to status available in userlist.
 	 */ 
	protected void setEnableORDisable(String str){

		// Controll for student
		if(role.equals("student")) {
			try {
				if(str.equals("available")){
        	                        wbBttn.setEnabled(true);
                	                micBttn.setEnabled(true);
					screenBttn.setEnabled(true);
                        	        denieWB.setEnabled(false);
                                	denieMic.setEnabled(false);
                                	denieScreen.setEnabled(false);
					pptBttn.setEnabled(true);
					denieppt.setEnabled(false);
	                               	try{
                                        	WhiteBoardDraw.getController().denieDrawforStudent();
	                                }catch(Exception e){System.out.println("Error in Stop White Board");}
                	        }

				if(str.equals("Get-WB")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(true);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
                                }
	
		                if(str.equals("Allow-WB")){
					wbBttn.setEnabled(false);
	        		       	denieWB.setEnabled(true);
					micBttn.setEnabled(false);
					denieMic.setEnabled(false);
					screenBttn.setEnabled(false);
					denieScreen.setEnabled(false);
					try{
                                                WhiteBoardDraw.getController().allowDrawforStudent();
                                        }catch(Exception e){System.out.println("Error in Start Audio or White Board");}
				}	

				if(str.equals("Get-Mic")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(true);
                                        screenBttn.setEnabled(true);
                                        denieScreen.setEnabled(false);
                                }

				if(str.equals("Allow-Mic")){
					wbBttn.setEnabled(false);
                	                denieWB.setEnabled(false);
					micBttn.setEnabled(false);
                                	denieMic.setEnabled(true);
					screenBttn.setEnabled(true);
					denieScreen.setEnabled(false);
				}

				if(str.equals("Get-Screen")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(true);
                                }

				if(str.equals("Allow-Screen")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(true);
				}
				if(str.equals("Get-PPT")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
					pptBttn.setEnabled(false);
                                        denieppt.setEnabled(true);
                                }

                                if(str.equals("Allow-PPT")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
					pptBttn.setEnabled(false);
                                        denieppt.setEnabled(true);
                                }


			} catch(Exception e){}

		}else  { // controll for instructor.
			try {
				if(str.equals("available")){
                        	        wbBttn.setEnabled(false);
                                	micBttn.setEnabled(false);
	                                denieWB.setEnabled(false);
        	                        denieMic.setEnabled(false);
					screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
                	        }

				if(str.equals("Get-WB")){
                                        wbBttn.setEnabled(true);
                                        denieWB.setEnabled(true);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);

                                }
				if(str.equals("Allow-WB")){
                	                wbBttn.setEnabled(false);
                        	        denieWB.setEnabled(true);
                                	micBttn.setEnabled(false);
	                                denieMic.setEnabled(false);
					screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);

        	                }

				if(str.equals("Get-Mic")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(true);
                                        denieMic.setEnabled(true);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);

                                }
                	        if(str.equals("Allow-Mic")){
                        	        wbBttn.setEnabled(false);
                                	denieWB.setEnabled(false);
	                                micBttn.setEnabled(false);
        	                        denieMic.setEnabled(true);
					screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);

                	        }

				if(str.equals("Get-Screen")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(true);
                                        denieScreen.setEnabled(true);
                                }
				if(str.equals("Allow-Screen")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(true);
                                }
				if(str.equals("Get-PPT")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
					pptBttn.setEnabled(true);
                                        denieppt.setEnabled(true);
                                }
                                if(str.equals("Allow-PPT")){
                                        wbBttn.setEnabled(false);
                                        denieWB.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        denieMic.setEnabled(false);
                                        screenBttn.setEnabled(false);
                                        denieScreen.setEnabled(false);
					pptBttn.setEnabled(false);
                                        denieppt.setEnabled(true);

                                }
	
			} catch(Exception e){}
		}
        }
}

