package org.bss.brihaspatisync.gui;

/**
 * JoinSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

import java.awt.Toolkit;
import java.awt.Dimension;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardPanel;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationViewPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class JoinSessionPanel extends JPanel { 

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
	
	/**
         * Cretae GUI for JoinSessionPanel.
         */
	protected JSplitPane createGUI(){

		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		
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
		JPanel labelPane=new JPanel();
                JLabel welcome=new JLabel(Language.getController().getLangValue("UserListPanel.WelcomeLabel"));
                welcome.setFont(new Font("Arial", Font.PLAIN, 20));
                JLabel userLogin=new JLabel(ClientObject.getController().getwelcomeUserName());
                userLogin.setForeground(new Color(24,116,205));
                userLogin.setFont(new Font("Arial", Font.BOLD, 20));
                labelPane.add(welcome);
                labelPane.add(userLogin);
		av_Pane.add(labelPane,BorderLayout.NORTH);
		//PPT Presentation Panel
                JPanel chat_slide_Pane=new JPanel();
                chat_slide_Pane.setLayout(new BorderLayout());

		JPanel slide_Pane=new JPanel();
		
		JSplitPane chat_pp_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,ChatPanel.getController().createGUI(),PresentationPanel.getController().createGUI());
               	chat_slide_Pane.add(chat_pp_Split);
                chat_pp_Split.setDividerLocation(((int)dim.getWidth())/6);
		BasicSplitPaneDivider chat_pp_divider = ( (BasicSplitPaneUI)  chat_pp_Split.getUI()).getDivider();
                chat_pp_Split.setOneTouchExpandable(true);
                chat_pp_divider.setDividerSize(5);
                chat_pp_divider.setBorder(BorderFactory.createTitledBorder(chat_pp_divider.getBorder(), ""));

		
		JSplitPane av_chat_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,UserListPanel.getController().createGUI(), chat_slide_Pane);
                new_Pane.add(av_chat_Split);
		av_chat_Split.setDividerLocation(((int)dim.getWidth()/7));
		BasicSplitPaneDivider av_chat_divider = ( (BasicSplitPaneUI)  av_chat_Split.getUI()).getDivider();
                av_chat_Split.setOneTouchExpandable(true);
                av_chat_divider.setDividerSize(5);
                av_chat_divider.setBorder(BorderFactory.createTitledBorder(av_chat_divider.getBorder(), ""));


		/*********************************/
		av_chat_Split.setContinuousLayout(true);
                av_chat_Split.setOneTouchExpandable(true);
                PropertyChangeListener propertyChangeListener1 = new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent changeEvent) {
                                JSplitPane sourceSplitPane = (JSplitPane) changeEvent.getSource();
                                String propertyName = changeEvent.getPropertyName();
                                if (propertyName.equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY)) {
                                        VideoPanel.getController().setIMG_HIEGHT(sourceSplitPane.getDividerLocation());
                                }
                        }
                };
                av_chat_Split.addPropertyChangeListener(propertyChangeListener1);

		/*********************************/
	        JSplitPane ul_av_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,av_Pane,new_Pane);
                left_Pane.add(ul_av_Split);
		ul_av_Split.setDividerLocation(((int)dim.getWidth()/8));
		BasicSplitPaneDivider divider = ( (BasicSplitPaneUI)  ul_av_Split.getUI()).getDivider();
    		 ul_av_Split.setOneTouchExpandable(true);
    		divider.setDividerSize(5);
    		divider.setBorder(BorderFactory.createTitledBorder(divider.getBorder(), ""));
   
			
		//TabbedPane for whiteboard, screen share and ppt presentation.
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.DesktopSharing"),Desktop_Sharing.getController().createGUI());
	        jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.Whiteboard"),WhiteBoardPanel.getController().createGUI());
		jtp.addTab(Language.getController().getLangValue("JoinSessionPanel.PptPresentation"),PresentationViewPanel.getController().createGUI());                
		
		//start for whiteboard,chat,remoteDesktop Panel

               	right_Pane=new JPanel();
		right_Pane.setLayout(new BorderLayout());
		right_Pane.add(jtp,BorderLayout.CENTER);
		right_Pane.setBackground(Color.WHITE);
        	JSplitPane splitPane1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left_Pane,right_Pane);
		splitPane1.setContinuousLayout(true);
                splitPane1.setOneTouchExpandable(true);
                PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent changeEvent) {
                                JSplitPane sourceSplitPane = (JSplitPane) changeEvent.getSource();
                                String propertyName = changeEvent.getPropertyName();
                                if (propertyName.equals(JSplitPane.LAST_DIVIDER_LOCATION_PROPERTY)) {
                                        int current = sourceSplitPane.getDividerLocation();
                                        VideoPanel.getController().setIMG_WIDTH(current);
                                }
                        }
                };
                splitPane1.addPropertyChangeListener(propertyChangeListener);
		
		splitPane1.setBackground(Color.WHITE);
                splitPane1.setDividerLocation(((int)dim.getWidth()/7)+20);
		BasicSplitPaneDivider splitPane1_divider = ( (BasicSplitPaneUI)  splitPane1.getUI()).getDivider();
                splitPane1.setOneTouchExpandable(true);
                splitPane1_divider.setDividerSize(5);
                splitPane1_divider.setBorder(BorderFactory.createTitledBorder(splitPane1_divider.getBorder(), ""));


		
		splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(splitPane1);
		splitPane.setBottomComponent(StatusPanel.getController());
		System.out.println((int)dim.getHeight());
		splitPane.setDividerLocation(((int)dim.getWidth()/2)-20);
		BasicSplitPaneDivider splitPane_divider = ( (BasicSplitPaneUI)  splitPane.getUI()).getDivider();
                splitPane.setOneTouchExpandable(false);
                splitPane_divider.setDividerSize(5);
                splitPane_divider.setBorder(BorderFactory.createTitledBorder(splitPane_divider.getBorder(), ""));

		return splitPane;
	}

}//end of class
