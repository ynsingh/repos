package org.bss.brihaspatisync.gui;
	
/**
 * HandRaisePanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,Kanpur.
 */


import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardDraw;
import org.bss.brihaspatisync.tools.audio_video.AVTransmitReceiveHandler;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class HandRaisePanel {

	private JPanel mainPanel=null;
	private JButton whiteBoard=null;;
	private JButton DenieWB=null;
	
	private static HandRaisePanel hr_panel=null;
	private ClientObject object=ClientObject.getController();
	private Log log=Log.getController();


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
		mainPanel.setLayout(new GridLayout(6,1,0,4)); 
		
		whiteBoard=new JButton(object.getAWBorHRbut(),new ImageIcon(clr.getResource("resources/images/user/accept.png")));
		whiteBoard.setToolTipText(object.gettooltipRWB());
                whiteBoard.setActionCommand(object.getAWBorHRbut());
		whiteBoard.setEnabled(object.getflag());
		whiteBoard.addActionListener(HandRaiseAction.getController());
		
		DenieWB=new JButton(object.getdenieWB(),new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                DenieWB.setToolTipText(object.gettooltipDWB());
                DenieWB.setActionCommand(object.getdenieWB());
		DenieWB.setEnabled(false);
                DenieWB.addActionListener(HandRaiseAction.getController());
		
		mainPanel.add(whiteBoard);
		mainPanel.add(DenieWB);

		return mainPanel;
	}

	protected void setEnableORDecable(String str){
		if(role.equals("student")){
	                if(str.equals("available")){
        	        	whiteBoard.setEnabled(true);
				DenieWB.setEnabled(false);	
				try{
					WhiteBoardDraw.getController().denieDrawforStudent();
					//AVTransmitReceiveHandler.getController().AVTransmitHandlerStop();
				}catch(Exception e){log.setLog("Error in Stop Audio  or White Board");}
			}
	                if(str.equals("Allow-WB")){
				whiteBoard.setEnabled(false);
	        	       	DenieWB.setEnabled(false);
				try{
					WhiteBoardDraw.getController().allowDrawforStudent();
					//AVTransmitReceiveHandler.getController().AVTransmitHandlerStart();
				}catch(Exception e){log.setLog("Error in Start Audio or White Board");}
			}
		}else{
                        if(str.equals("Hand-Raise")){
                        	whiteBoard.setEnabled(true);
                                DenieWB.setEnabled(true);
			}
			else if(str.equals("Allow-WB")){
				//AVTransmitReceiveHandler.getController().
                                whiteBoard.setEnabled(false);
                                DenieWB.setEnabled(true);
                        }else{
				whiteBoard.setEnabled(false);
                                DenieWB.setEnabled(false);
			}
		}
        }
}

