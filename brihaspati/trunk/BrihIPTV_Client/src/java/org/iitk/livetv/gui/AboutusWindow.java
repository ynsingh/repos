package org.iitk.livetv.gui;

/**
 * AboutusWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.awt.Container;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */
 
public class AboutusWindow extends JFrame {
	
	private Container con=null;
	private JPanel mainPanel;
	private boolean connFlag=false;
	private static AboutusWindow about=null;
	protected JPanel window_mainPanel;

	public static AboutusWindow getController(){
		if (about==null){
			about=new AboutusWindow();
		}
		return about;
	}

	public AboutusWindow(){
		createWindow();
	}
  	
	protected void createWindow(){
    	   	setTitle("About LiveTV");
    		con=this.getContentPane();
		window_mainPanel=new JPanel();
		window_mainPanel.setLayout(new BorderLayout());
		con.add(window_mainPanel); 
    		setSize(420, 300);
    		setLocation(515,100);
    		setVisible(true);
    		setResizable(true);
    	}
}
