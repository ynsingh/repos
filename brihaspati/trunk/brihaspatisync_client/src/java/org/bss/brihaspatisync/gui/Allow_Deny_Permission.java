package org.bss.brihaspatisync.gui;

/**
 * Allow_Deny_Permission.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2013 ETRG, IIT Kanpur.
 */

import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class Allow_Deny_Permission {

        private JPanel mainPanel= null;
	private JLabel desk_share=null;
	private JLabel ins_desk_share=null;
	private JLabel denie_permission=null;

	private HandRaiseAction handRaiseAction=new HandRaiseAction();
	private InstructorHandRaiseAction inshandRaiseAction=new InstructorHandRaiseAction();
	
       	protected JPanel createGUI(){
                mainPanel=new JPanel();
		
		desk_share=new JLabel();
		desk_share.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/login.png")));
		
		if((ClientObject.getUserRole()).equals("instructor")) {
			desk_share.setToolTipText(Language.getController().getLangValue("UserListPanel.allowPermission"));
			desk_share.setName("Allow-Permission");
			
			ins_desk_share=new JLabel();
                        ins_desk_share.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/user/allowscreen.jpeg")));                                                                                                                             
                        ins_desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StartDesktopSharing"));
                        ins_desk_share.setName("Permission");
			mainPanel.add(ins_desk_share);
			
		} else {
			desk_share.setToolTipText(Language.getController().getLangValue("UserListPanel.getPermission"));	
			desk_share.setName("Get-Permission");		
		}
		
		denie_permission=new JLabel();
		denie_permission.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/user/denie.png")));
		denie_permission.setToolTipText(Language.getController().getLangValue("UserListPanel.denyPermission"));
                denie_permission.setName("Denie-Permission");
		
		setEnable_Decable_Permission(true);
		
		mainPanel.add(desk_share);
		mainPanel.add(denie_permission);
		return mainPanel;
	}
	
	protected void setEnable_Decable_Permission(boolean flag) {
		try {
			if(flag) { 
				desk_share.addMouseListener(handRaiseAction);
				desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
				denie_permission.removeMouseListener(handRaiseAction);
				denie_permission.setCursor(null);
				if((ClientObject.getUserRole()).equals("instructor")) {
					ins_desk_share.addMouseListener(inshandRaiseAction);
					ins_desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			} else {
				desk_share.removeMouseListener(handRaiseAction);
				desk_share.setCursor(null);
				denie_permission.addMouseListener(handRaiseAction);
				denie_permission.setCursor(new Cursor(Cursor.HAND_CURSOR));
				if((ClientObject.getUserRole()).equals("instructor")) {
					ins_desk_share.removeMouseListener(inshandRaiseAction);
					ins_desk_share.setCursor(null);
				}
			}
                        desk_share.setEnabled(flag);
			denie_permission.setEnabled(!flag);
			if((ClientObject.getUserRole()).equals("instructor"))
	                	ins_desk_share.setEnabled(flag);
			mainPanel.revalidate();
			mainPanel.repaint();
		} catch(Exception e) { System.out.println("Exception at Allow_Deny_Permission class in setEnable_Decable method  "+e.getMessage()); 			
		}
	}
}
