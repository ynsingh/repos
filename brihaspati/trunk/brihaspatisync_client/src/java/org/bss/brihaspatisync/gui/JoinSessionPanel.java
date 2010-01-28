package org.bss.brihaspatisync.gui;

/**
 * JoinSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

import java.util.Vector;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardPanel;
import org.bss.brihaspatisync.tools.chat.ChatPanel;
import org.bss.brihaspatisync.tools.presentation.PresentationPanel;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class JoinSessionPanel extends JPanel implements ActionListener, MouseListener{

	private static JoinSessionPanel win=null; 
	private JPanel av_Pane=null;
	private JSplitPane splitPane=null;
	private JPanel left_Pane=null;
	private JPanel right_Pane=null;
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
		//Audio Vedio Panel
		
		av_Pane=new JPanel();
                av_Pane.setLayout(new BorderLayout());
		av_Pane.setBackground(Color.BLACK);
		//PPT Presentation Panel
                JPanel chat_slide_Pane=new JPanel();
                chat_slide_Pane.setLayout(new BorderLayout());
		//pp_Pane.add(ChatPanel.getController().createGUI());//,BorderLayout.CENTER);

		JPanel slide_Pane=new JPanel();
		
		JSplitPane chat_pp_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,ChatPanel.getController().createGUI(),PresentationPanel.getController().createGUI());
                chat_pp_Split.setDividerLocation(200);
		//pp_Pane.add(chat_pp_Split);
		chat_slide_Pane.add(chat_pp_Split);
		
		JSplitPane av_chat_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,av_Pane,chat_slide_Pane);
		av_chat_Split.setDividerLocation(250);

                new_Pane.add(av_chat_Split);

                JSplitPane ul_av_Split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,UserListPanel.getController().createGUI(),new_Pane);
		ul_av_Split.setDividerLocation(200);
                left_Pane.add(ul_av_Split);

                //start for whiteboard,chat,remoteDesktop Panel
               	right_Pane=new JPanel();
		right_Pane.setLayout(new BorderLayout());
		right_Pane.add(WhiteBoardPanel.getController().createGUI(),BorderLayout.CENTER);
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
