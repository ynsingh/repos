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
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.gui.HandRaiseAction;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class ShareScreenAndPPT extends JPanel implements MouseListener{

        private JPanel mainPanel;
        private static ShareScreenAndPPT wbPanel=null;
	
	private JLabel desk_share=null;
	private JLabel desk_ppt=null;
	private ClassLoader clr;
	private String role=ClientObject.getController().getUserRole();
	
        public static ShareScreenAndPPT getController(){
                if (wbPanel==null){
                        wbPanel=new ShareScreenAndPPT();
                }
                return wbPanel;
        }

	public ShareScreenAndPPT(){
		clr= this.getClass().getClassLoader();

	}

       	public JPanel createGUI(){
                
                mainPanel=new JPanel();
                mainPanel.setLayout(new java.awt.FlowLayout());
		
		desk_share=new JLabel("<html><blink><Font size=3 color=white><b> Share Screen &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
		desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
                desk_share.setCursor(new Cursor(Cursor.HAND_CURSOR));
                desk_share.setName("Share-Screen");
		desk_share.setEnabled(false);

		desk_ppt=new JLabel("<html><blink><Font size=3 color=white><b> Share PPT &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                desk_ppt.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
                desk_ppt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                desk_ppt.setName("Instructor_Allow-PPT");
		desk_ppt.setEnabled(false);
		
		mainPanel.add(desk_share);
		mainPanel.add(desk_ppt);
		return mainPanel;
	}
	
	public void setEnable_Decable(){
		desk_share.addMouseListener(this);
		desk_share.setEnabled(true);	
		desk_share.setText("<html><blink><Font size=3 color=blue><b> Share Screen &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
	
		desk_ppt.addMouseListener(this);
		desk_ppt.setEnabled(true);
		desk_ppt.setText("<html><blink><Font size=3 color=blue><b> Share PPT &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
	}

	public void mouseClicked(MouseEvent e) {
		String cmd=e.getComponent().getName();
	 	if(cmd.equals("Share-Screen")) {
                        HandRaiseAction.getController().actionONRequest("Share-Screen");
			desk_share.setText("<html><blink><Font size=3 color=blue><b> Stop Share Screen &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
			desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopDesktopSharing"));
			desk_share.setName("Stop-Share-Screen");
                }

		if(cmd.equals("Stop-Share-Screen")){
                        HandRaiseAction.getController().actionONRequest("Instructor_Stop_Allow");
                        desk_share.setText("<html><blink><Font size=3 color=blue><b> Share Screen &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
			desk_share.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StartDesktopSharing"));
                        desk_share.setName("Share-Screen");

		}

		if(cmd.equals("Instructor_Allow-PPT")) {
                        HandRaiseAction.getController().actionONRequest("Instructor_Allow-PPT");
                        desk_ppt.setText("<html><blink><Font size=3 color=blue><b> Stop Share PPT &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>") ;
                        desk_ppt.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StopPPTSharing"));
                        desk_ppt.setName("Stop-Allow-PPT");
                }

                if(cmd.equals("Stop-Allow-PPT")) {
                        HandRaiseAction.getController().actionONRequest("Instructor_Stop_Allow");//available");
                        desk_ppt.setText("<html><blink><Font size=3 color=blue><b> Share PPT &nbsp;  &nbsp;  &nbsp;</b></font></blink></html>");
                        desk_ppt.setToolTipText(Language.getController().getLangValue("WhiteBoardPanel.StartPPTSharing"));
                        desk_ppt.setName("Instructor_Allow-PPT");
                }
		
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
	
}

