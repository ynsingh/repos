package org.bss.brihaspatisync.gui;

/**
 * JoinSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013 ETRG, IIT Kanpur.
 */

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationViewPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 */

public class JoinSessionPanel extends JPanel { 

	/**
         * Cretae GUI for JoinSessionPanel.
         */
	protected JoinSessionPanel() {
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
                int screen_width= (int)dim.getWidth();
                int screen_height= (int)dim.getHeight();
		setSize(screen_width, screen_height-113);
		VideoPanel.getController().resetController();
		UserListPanel.getController().resetController();	
		
		// left panel ************************************
		final JSplitPane av_userlist_split=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		final JSplitPane userlist_chat_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		final JSplitPane chat_pp_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			
		av_userlist_split.setTopComponent(VideoPanel.getController().createGUI());
		av_userlist_split.setBottomComponent(userlist_chat_Split);
		av_userlist_split.addAncestorListener(new BaseAncestorListener() {
        		public void ancestorAdded(AncestorEvent event) {
	            		av_userlist_split.setDividerLocation(getSize().height/4);
        		}
    		});
		av_userlist_split.setDividerSize(2);
		userlist_chat_Split.setTopComponent(UserListPanel.getController().createGUI());
		userlist_chat_Split.setBottomComponent(chat_pp_Split);
		userlist_chat_Split.addAncestorListener(new BaseAncestorListener() {
                        public void ancestorAdded(AncestorEvent event) {
                                userlist_chat_Split.setDividerLocation((getSize().height/4));
                        }
                });
		userlist_chat_Split.setDividerSize(2);
		chat_pp_Split.setTopComponent(ChatPanel.getController().createGUI());
		chat_pp_Split.setBottomComponent(PresentationPanel.getController().createGUI());	
		chat_pp_Split.addAncestorListener(new BaseAncestorListener() {
                        public void ancestorAdded(AncestorEvent event) {
                                chat_pp_Split.setDividerLocation((getSize().height/4));
                        }
                });
		chat_pp_Split.setDividerSize(2);
		// right panel ****************************************
		JTabbedPane desktop_whiteBoard_ppt_panel = new JTabbedPane();
		desktop_whiteBoard_ppt_panel.addTab(Language.getController().getLangValue("JoinSessionPanel.DesktopSharing"),Desktop_Sharing.getController().createGUI());
                desktop_whiteBoard_ppt_panel.addTab(Language.getController().getLangValue("JoinSessionPanel.Whiteboard"),WhiteBoardPanel.getController().createGUI());
                desktop_whiteBoard_ppt_panel.addTab(Language.getController().getLangValue("JoinSessionPanel.PptPresentation"),PresentationViewPanel.getController().createGUI());	
				
		JSplitPane left_right_Pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,av_userlist_split,desktop_whiteBoard_ppt_panel);
		left_right_Pane.setDividerLocation((((int)(getSize()).getWidth())/7));	
		left_right_Pane.setContinuousLayout(left_right_Pane.isContinuousLayout());
                add(left_right_Pane,BorderLayout.CENTER);
		setLocation(0,0);	
		setVisible(true);
	}
	/**
	 * this method is used to auto rearenge left panel 
	 */  
	public static class BaseAncestorListener 
		implements AncestorListener {
		public void ancestorAdded(AncestorEvent event) { }
		public void ancestorRemoved(AncestorEvent event) {  }
		public void ancestorMoved(AncestorEvent event) { }
	}
}//end of class
