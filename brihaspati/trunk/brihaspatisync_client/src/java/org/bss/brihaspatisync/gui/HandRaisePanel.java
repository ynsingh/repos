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
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;
//import org.bss.brihaspatisync.tools.audio_video.AVTransmitReceiveHandler;
//import org.bss.brihaspatisync.tools.audio_video.AudioVideoPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> testing for gui.
 */

public class HandRaisePanel {

	private JPanel mainPanel=null;
	private JButton wbBttn=null;;
	//private JButton denieWB=null;
	private JButton micBttn=null;
	//private JButton denieMic=null;
	private JButton screenBttn=null;
	//private JButton denieScreen=null;
	
	private JButton pptBttn=null;
	private JButton denie=null;
	
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
			wbBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText1"));
                	wbBttn.setActionCommand("Get-WB");
                	wbBttn.addActionListener(HandRaiseAction.getController());

			micBttn=new JButton("Get-Mic",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
			micBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText3"));
                	micBttn.setActionCommand("Get-Mic");
                	micBttn.addActionListener(HandRaiseAction.getController());

			screenBttn=new JButton("Get-Screen",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        screenBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText5"));
                        screenBttn.setActionCommand("Get-Screen");
                        screenBttn.addActionListener(HandRaiseAction.getController());

			pptBttn=new JButton("Get-PPT",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        
			pptBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText7"));
                        pptBttn.setActionCommand("Get-PPT");
                        pptBttn.addActionListener(HandRaiseAction.getController());

		// Buttons for instructor Handraise panel.
		}else if(role.equals("instructor")){
			wbBttn=new JButton("Allow-WB",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        wbBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText9"));
                        wbBttn.setActionCommand("Allow-WB");
                        wbBttn.addActionListener(HandRaiseAction.getController());


                        micBttn=new JButton("Allow-Mic",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        micBttn.setToolTipText("Allow mic to student");
                        micBttn.setActionCommand("Allow-Mic");
                        micBttn.addActionListener(HandRaiseAction.getController());

			screenBttn=new JButton("Allow-Screen",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        screenBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText12"));
                        screenBttn.setActionCommand("Allow-Screen");
                        screenBttn.addActionListener(HandRaiseAction.getController());

			pptBttn=new JButton("Allow-PPT",new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        pptBttn.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText14"));
                        pptBttn.setActionCommand("Allow-PPT");
                        pptBttn.addActionListener(HandRaiseAction.getController());

		}
		{ /***  denie button **/
			denie=new JButton("Deny",new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                        denie.setToolTipText(Language.getController().getLangValue("HandRaisePanel.ToolTipText15"));
                        denie.setActionCommand("Denie-PPT");
                        denie.addActionListener(HandRaiseAction.getController());
		}
		wbBttn.setEnabled(false);
		micBttn.setEnabled(false);
		screenBttn.setEnabled(false);
	
		pptBttn.setEnabled(false);
                denie.setEnabled(false);

		mainPanel.add(wbBttn);
		mainPanel.add(micBttn);
		mainPanel.add(screenBttn);
		mainPanel.add(pptBttn);
                mainPanel.add(denie);

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
					pptBttn.setEnabled(true);
					denie.setEnabled(false);
	                               	try{
                                        	WhiteBoardDraw.getController().denieDrawforStudent();
	                                }catch(Exception e){System.out.println("Error in Stop White Board");}
                	        }

				if(str.equals("Get-WB")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);
                                        denie.setEnabled(true);
                                }
	
		                if(str.equals("Allow-WB")){
					wbBttn.setEnabled(false);
					micBttn.setEnabled(false);
					screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);	
					denie.setEnabled(true);	
					try{
                                                WhiteBoardDraw.getController().allowDrawforStudent();
                                        }catch(Exception e){System.out.println("Error in Start Audio or White Board");}
				}	

				if(str.equals("Get-Mic")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(true);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
                                }

				if(str.equals("Allow-Mic")){
					wbBttn.setEnabled(false);
					micBttn.setEnabled(false);
					screenBttn.setEnabled(true);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
				}

				if(str.equals("Get-Screen")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);	
					denie.setEnabled(true);
                                }

				if(str.equals("Allow-Screen")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
				}
				if(str.equals("Get-PPT")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
                                }

                                if(str.equals("Allow-PPT")){
                                        wbBttn.setEnabled(false);
                                        micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
                                }


			} catch(Exception e){}

		}else  { // controll for instructor.
			try {
				if(str.equals("available")){
                        	        wbBttn.setEnabled(false);
                                	micBttn.setEnabled(false);
					screenBttn.setEnabled(false);
					denie.setEnabled(false);
                	        }

				if(str.equals("Get-WB")){
                                        wbBttn.setEnabled(true);
                                        //micBttn.setEnabled(false);
                                        //screenBttn.setEnabled(false);
					denie.setEnabled(true);
                                }
				if(str.equals("Allow-WB")){
                	                wbBttn.setEnabled(false);
                                	//micBttn.setEnabled(false);
					//screenBttn.setEnabled(false);
					denie.setEnabled(true);
        	                }

				if(str.equals("Get-Mic")){
                                        //wbBttn.setEnabled(false);
                                        micBttn.setEnabled(true);
                                        //screenBttn.setEnabled(false);
					denie.setEnabled(true);	
                                }
                	        if(str.equals("Allow-Mic")){
                        	        //wbBttn.setEnabled(false);
	                                micBttn.setEnabled(false);
					//screenBttn.setEnabled(false);
					denie.setEnabled(true);
                	        }

				if(str.equals("Get-Screen")){
                                        //wbBttn.setEnabled(false);
                                        ///micBttn.setEnabled(false);
                                        screenBttn.setEnabled(true);
					denie.setEnabled(true);
                                }
				if(str.equals("Allow-Screen")){
                                        //wbBttn.setEnabled(false);
                                        //micBttn.setEnabled(false);
                                        screenBttn.setEnabled(false);
					denie.setEnabled(true);
                                }
				if(str.equals("Get-PPT")){
                                        //wbBttn.setEnabled(false);
                                        //micBttn.setEnabled(false);
                                        //screenBttn.setEnabled(false);
					pptBttn.setEnabled(true);
					denie.setEnabled(true);
                                }
                                if(str.equals("Allow-PPT")){
                                        //wbBttn.setEnabled(false);
                                        //micBttn.setEnabled(false);
                                        //screenBttn.setEnabled(false);
					pptBttn.setEnabled(false);
					denie.setEnabled(true);
                                }
	
			} catch(Exception e){}
		}
        }
}

