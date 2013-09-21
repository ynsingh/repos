package org.bss.brihaspatisync.gui;

/**
 * WhiteBoardPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2013 ETRG, IIT Kanpur.
 */

import java.awt.Font;
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

	private ClientObject client_obj=ClientObject.getController();
	private HandRaiseAction handRaiseAction=new HandRaiseAction();
	
       	protected JPanel createGUI(){
                mainPanel=new JPanel();
		desk_share=new JLabel();
		if((client_obj.getUserRole()).equals("instructor")) {
			desk_share.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/login.png")));
			desk_share.setToolTipText(Language.getController().getLangValue("UserListPanel.allowPermission"));
			desk_share.setName("Allow-Permission");
		} else {
			desk_share.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/user/hr.jpeg")));
			desk_share.setToolTipText(Language.getController().getLangValue("UserListPanel.getPermission"));	
			desk_share.setName("Get-Permission");		
		}
               	desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
		desk_share.addMouseListener(handRaiseAction);
		desk_share.setEnabled(true);
		
		if((client_obj.getUserRole()).equals("instructor")) {
			ins_desk_share=new JLabel();
			ins_desk_share.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/user/allowscreen.jpeg")));
			ins_desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StartDesktopSharing"));
                	ins_desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
	                ins_desk_share.setName("Permission");
			ins_desk_share.addMouseListener(handRaiseAction);
                	ins_desk_share.setEnabled(true);
		}
		
		denie_permission=new JLabel();
		denie_permission.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/user/denie.png")));
		denie_permission.setToolTipText(Language.getController().getLangValue("UserListPanel.denyPermission"));
                denie_permission.setCursor(new Cursor(Cursor.HAND_CURSOR));
                denie_permission.setName("Denie-Permission");
		denie_permission.addMouseListener(handRaiseAction);
		denie_permission.setEnabled(false);
		if((client_obj.getUserRole()).equals("instructor"))
			mainPanel.add(ins_desk_share);
		mainPanel.add(desk_share);
		mainPanel.add(denie_permission);
		return mainPanel;
	}
	
	protected void setEnable_Decable_Allow_Get(boolean flag) {
		try{
			if((client_obj.getUserRole()).equals("instructor")) {
				if(flag) { 
					desk_share.addMouseListener(handRaiseAction);
					if((client_obj.getUserRole()).equals("instructor"))
						ins_desk_share.addMouseListener(handRaiseAction);
				} else {
					desk_share.removeMouseListener(handRaiseAction);
					if((client_obj.getUserRole()).equals("instructor"))
						ins_desk_share.removeMouseListener(handRaiseAction);
				}
                                desk_share.setEnabled(flag);
				if((client_obj.getUserRole()).equals("instructor"))
	                                ins_desk_share.setEnabled(flag);
	                } else {
				if(flag) {
					desk_share.addMouseListener(handRaiseAction);
				} else { 
					desk_share.removeMouseListener(handRaiseAction);
				}
				desk_share.setEnabled(flag);
			}
			mainPanel.revalidate();
			mainPanel.repaint();
		}catch(Exception e) { System.out.println("Exception at Allow_Deny_Permission class in setEnable_Decable method  "+e.getMessage()); }
	}

	protected void setEnable_Decable_Deny(boolean flag){
                try{
			if(flag) 
				denie_permission.addMouseListener(handRaiseAction);
			else 
				denie_permission.removeMouseListener(handRaiseAction);
                        denie_permission.setEnabled(flag);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                }catch(Exception e) { System.out.println("Exception at Allow_Deny_Permission class in setEnable_Decable_Deny method  "+e.getMessage());}
        }
}

