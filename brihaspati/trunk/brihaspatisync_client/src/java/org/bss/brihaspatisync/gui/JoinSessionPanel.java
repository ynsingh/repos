package org.bss.brihaspatisync.gui;

/**
 * JoinSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.Toolkit;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.util.Vector;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardPanel;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationViewPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class JoinSessionPanel extends JPanel implements ActionListener, MouseListener{

	private static JoinSessionPanel win=null; 
	private JPanel av_Pane=null;
	private JSplitPane splitPane=null;
	private JPanel left_Pane=null;
	private JPanel right_Pane=null;
	private JPanel av_Pane_new  =null;

	/**
         * Controller for Class.
	 */

	public static JoinSessionPanel getController(){
                if (win==null){
                        win=new JoinSessionPanel();
                }
                return win;
        }
   	public JPanel getAV_Panel(){
   		return av_Pane;
   	}
   	public JSplitPane getSplit_Panel(){
   		return splitPane;
   	}
   	public JPanel getLeft_Panel(){
   		return left_Pane;
   	}
   	public JPanel getRight_Panel(){
   		return right_Pane;
   	}
   	
	/**
         * Cretae GUI for JoinSessionPanel.
         */
	protected JSplitPane createGUI(){
		
		//start for userlist, Audio Vedio, and PPT Presentation Panel
		left_Pane=new JPanel();
                left_Pane.setLayout(new BorderLayout());
                
		JPanel new_Pane=new JPanel();
                new_Pane.setLayout(new BorderLayout());
		
		JPanel new_Pane1=new JPanel();
                new_Pane1.setLayout(new BorderLayout());

		//Audio Vedio Panel
		av_Pane=new JPanel();
                av_Pane.setLayout(new BorderLayout());
		av_Pane.setBackground(Color.BLACK);
	
		//PPT Presentation Panel
                JPanel chat_slide_Pane=new JPanel();
                chat_slide_Pane.setLayout(new BorderLayout());

		JPanel slide_Pane=new JPanel();
		
		JSplitPane chat_pp_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,ChatPanel.getController().createGUI(),PresentationPanel.getController().createGUI());
               	chat_slide_Pane.add(chat_pp_Split);
                Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                chat_pp_Split.setDividerLocation((((int)dim.getWidth()/5)-40));
		
		JSplitPane av_chat_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,av_Pane, chat_slide_Pane);
                new_Pane.add(av_chat_Split);
		av_chat_Split.setDividerLocation(160);

                JSplitPane ul_av_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,UserListPanel.getController().createGUI(),new_Pane);
                left_Pane.add(ul_av_Split);
		ul_av_Split.setDividerLocation(270);
	
		//TabbedPane for whiteboard, screen share and ppt presentation.
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.Whiteboard"), WhiteBoardPanel.getController().createGUI());
	        jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.DesktopSharing"),Desktop_Sharing.getController().createGUI());
		jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.PptPresentation"),PresentationViewPanel.getController().createGUI());                
		
		//start for whiteboard,chat,remoteDesktop Panel

               	right_Pane=new JPanel();
		right_Pane.setLayout(new BorderLayout());
		right_Pane.add(jtp,BorderLayout.CENTER);
		right_Pane.setBackground(Color.WHITE);
        	splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left_Pane,right_Pane);
		splitPane.setBackground(Color.WHITE);
                splitPane.setDividerLocation(275);
		return splitPane;
	}

	public void actionPerformed(ActionEvent e){}
		
 	/**
       	* Action for mouse click in Announce Session Panel.
        */

	public void mouseClicked(MouseEvent ev) {
        	if(ev.getComponent().getName().equals("")){
		}
	}
	
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}


}//end of class
