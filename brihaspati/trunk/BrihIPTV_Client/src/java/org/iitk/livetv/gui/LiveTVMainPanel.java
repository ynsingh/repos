package org.iitk.livetv.gui;

/**
 * LiveTVMainPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
 */

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 01Sep2012
 */

public class LiveTVMainPanel extends JPanel implements ActionListener, MouseListener{

	private static LiveTVMainPanel livetvPanel=null;
	private JPanel mainPanel=null;
	private JSplitPane splitPane=null;


	public static LiveTVMainPanel getController(){
		if(livetvPanel==null){
			livetvPanel=new LiveTVMainPanel();
		}
		return livetvPanel;
	}

	public JPanel createGUI(){
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LiveTVLeftPanel.getController().createGUI(),LiveTVRightPanel.getController().createGUI());
		splitPane.setContinuousLayout(true);
	        splitPane.setDividerLocation(200);
	        splitPane.setDividerSize(1);
		mainPanel.add(splitPane,BorderLayout.CENTER);

		return mainPanel;
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mouseClicked(MouseEvent ev) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e){}
}
