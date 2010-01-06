package org.bss.brihaspatisync.gui;

/**
 * CourseSessionWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur
 */

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */

public class CourseSessionWindow extends JInternalFrame {
	
	private Container con=null;
	private JPanel panel2; 
	private JPanel mainPanel;
	private JPanel stuCSPanel;
	private JPanel instCSPanel;
	private JSplitPane splitPane=null;
	private boolean connFlag=false;
	private static CourseSessionWindow csWin=null;

	/**
	 * Controller for the class.
	 */
	
	protected static CourseSessionWindow getController(){
		if (csWin==null){
			csWin=new CourseSessionWindow();
		}
		return csWin;
	}

	/**
	 * Creating GUI for CourseSessionWindow.
	 */
  	public  CourseSessionWindow(){
    		super("Course & Session List",true,false,true,true);
    		mainPanel=new JPanel();
	  	mainPanel.setLayout(new BorderLayout());
  	
  		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    	splitPane.setTopComponent(StudentCSPanel.getController().createGUI());
	    	splitPane.setBottomComponent(InstructorCSPanel.getController().createGUI());
		
	  	splitPane.setPreferredSize(new Dimension(500, 200));
	        splitPane.setDividerLocation(270);
  		
	  	mainPanel.add(splitPane,BorderLayout.CENTER);
	  	add(mainPanel);
	  	setLocation(200,50);
	    	setVisible(true); 
	    	setSize(700, 600);
		setResizable(false);	
   	}
}
