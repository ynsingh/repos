package org.bss.brihaspatisync.gui;

/**
 * WhiteBoardPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.HandRaiseAction;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class Allow_Deny_Permission implements MouseListener{

        private JPanel mainPanel;
        private static Allow_Deny_Permission allow_deny_permission=null;
	
	private JLabel desk_share=null;
	private String selectedUsername="";
	private String denieselectedUsername="";
	private JLabel denie_permission=null;
	private ClientObject client_obj=ClientObject.getController();
	private boolean flag=false;
        protected static Allow_Deny_Permission getController(){
                if (allow_deny_permission==null){
                        allow_deny_permission=new Allow_Deny_Permission();
                }
                return allow_deny_permission;
        }

	

       	protected JPanel createGUI(){
                
                mainPanel=new JPanel();
		desk_share=new JLabel("<html><blink><Font size=2 color=white><b> "+Language.getController().getLangValue("UserListPanel.permission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
		desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
                desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
		desk_share.setName("Permission");
		desk_share.setEnabled(false);
		desk_share.addMouseListener(this);		

		denie_permission=new JLabel("<html><blink><Font size=2 color=white><b> "+Language.getController().getLangValue("UserListPanel.denyPermission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                denie_permission.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
                denie_permission.setCursor(new Cursor(Cursor.HAND_CURSOR));
                denie_permission.setName("Denie-Permission");
		denie_permission.setEnabled(false);
		denie_permission.addMouseListener(this);

		mainPanel.add(desk_share);
		mainPanel.add(denie_permission);
		return mainPanel;
	}
	
	protected void setSelectedUsername(String str){
                this.selectedUsername=str;
        }

	protected void setEnable_Decable(){
		try{
		if((client_obj.getUserRole()).equals("instructor")){
                        desk_share.setText("<html><blink><Font size=2 color=blue><b>"+ Language.getController().getLangValue("UserListPanel.allowPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                        desk_share.setName("Allow-Permission");
			desk_share.setEnabled(true);
                }else {
                        desk_share.setText("<html><blink><Font size=2 color=blue><b>"+Language.getController().getLangValue("UserListPanel.getPermission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                        desk_share.setName("Get-Permission");
			desk_share.setEnabled(true);
		}
		mainPanel.revalidate();
		mainPanel.repaint();
		}catch(Exception e){System.out.println("Error in Sha "+e.getMessage());}
	}

	public void mouseClicked(MouseEvent e) {
		String cmd=e.getComponent().getName();
		if((client_obj.getUserRole()).equals("instructor")) {
                        if(selectedUsername.equals(client_obj.getUserName())) {
				try {
				if(cmd.equals("Allow-Permission")) {
					if(!flag){
					if(selectedUsername.equals("")  ) {
                 				javax.swing.JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                			}else {
						flag=true;
	                        		HandRaiseAction.getController().actionONRequest("Share-Screen",selectedUsername);
						denieselectedUsername=selectedUsername;
						desk_share.setText("<html><blink><Font size=2 color=white><b>"+Language.getController().getLangValue("UserListPanel.allowPermission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
						denie_permission.setText("<html><blink><Font size=2 color=red><b>"+Language.getController().getLangValue("UserListPanel.denyPermission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
						desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
						desk_share.setEnabled(false);
						denie_permission.setEnabled(true);
						desk_share.removeMouseListener(this);
						denie_permission.addMouseListener(this); 
						selectedUsername="";
						StatusPanel.getController().setStatus(selectedUsername +" "+" is Allowed to use all tools ");
					}}
                		}
				}catch(Exception ex){System.out.println("Error in Allow-Permission in line no 124 ");}
				if(cmd.equals("Denie-Permission")) {
                        		HandRaiseAction.getController().actionONRequest("available",denieselectedUsername);
                        		denie_permission.setText("<html><blink><Font size=2 color=white><b> "+Language.getController().getLangValue("UserListPanel.denyPermission")+" &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
					desk_share.setText("<html><blink><Font size=2 color=blue><b>"+Language.getController().getLangValue("UserListPanel.allowPermission") +"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                        		denie_permission.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
					denie_permission.setEnabled(false);
                                        desk_share.setEnabled(true);
					desk_share.addMouseListener(this);
                                        denie_permission.removeMouseListener(this);
					StatusPanel.getController().setStatus(selectedUsername +" "+" is Denied to use all tools ");
					flag =false;
                		}
			}else {
				try {
				if(cmd.equals("Allow-Permission")) {
					if(!flag){
					if(selectedUsername.equals("")) {
                                        	javax.swing.JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                               		}else {
					flag =true;
					HandRaiseAction.getController().actionONRequest("Allow-Permission",selectedUsername);
					denieselectedUsername=selectedUsername;
			              	desk_share.setText("<html><blink><Font size=2 color=white><b>"+Language.getController().getLangValue("UserListPanel.allowPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
			       		denie_permission.setText("<html><blink><Font size=2 color=red><b>"+Language.getController().getLangValue("UserListPanel.denyPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
                                	desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
                                	denie_permission.setEnabled(true);
                                	desk_share.setEnabled(false);
					desk_share.removeMouseListener(this);
	                                denie_permission.addMouseListener(this);
					selectedUsername="";
					StatusPanel.getController().setStatus(selectedUsername +" "+" is Allowed to use all tools ");
					}}
        	             	}
				}catch(Exception ex){System.out.println("Error in Allow-Permission in line no 151 ");}
                	        if(cmd.equals("Denie-Permission")) {
					flag =false;
                        		HandRaiseAction.getController().actionONRequest("available",denieselectedUsername);
	                                denie_permission.setText("<html><blink><Font size=2 color=white><b>"+Language.getController().getLangValue("UserListPanel.denyPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
        	                        desk_share.setText("<html><blink><Font size=2 color=blue><b>"+Language.getController().getLangValue("UserListPanel.allowPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                	                denie_permission.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
                        	        denie_permission.setEnabled(false);
                                	desk_share.setEnabled(true);
					desk_share.addMouseListener(this);
        	                        denie_permission.removeMouseListener(this);
					StatusPanel.getController().setStatus(selectedUsername +" "+" is Denied to use all tools ");
                	      	}
			}
		}else {
			
			if(cmd.equals("Get-Permission")) {
                           	selectedUsername=client_obj.getUserName();
                                HandRaiseAction.getController().actionONRequest("Get-Permission",selectedUsername);
                                denieselectedUsername=selectedUsername;
                                desk_share.setText("<html><blink><Font size=2 color=white><b>"+Language.getController().getLangValue("UserListPanel.getPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                                denie_permission.setText("<html><blink><Font size=2 color=blue><b>"+Language.getController().getLangValue("UserListPanel.denyPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
                                desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
                                denie_permission.setEnabled(true);
                                desk_share.setEnabled(false);
                                desk_share.removeMouseListener(this);
                                denie_permission.addMouseListener(this);
                   	}
                       	if(cmd.equals("Denie-Permission")) {
                        	HandRaiseAction.getController().actionONRequest("available",denieselectedUsername);
                                denie_permission.setText("<html><blink><Font size=2 color=white><b>"+Language.getController().getLangValue("UserListPanel.denyPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
                                desk_share.setText("<html><blink><Font size=2 color=blue><b>"+Language.getController().getLangValue("UserListPanel.getPermission")+"&nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                                denie_permission.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
                                denie_permission.setEnabled(false);
                                desk_share.setEnabled(true);
                                desk_share.addMouseListener(this);
                                denie_permission.removeMouseListener(this);
                 	}		
		}
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
	
}

