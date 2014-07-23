package org.bss.brihaspatisync.gui;

/**
 * CourseSessionWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012.2013 ETRG,IIT Kanpur
 */

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> Modify for GUI class.
 */

public class CourseSessionWindow extends JInternalFrame {
	
	/**
	 * Creating GUI for CourseSessionWindow.
	 */
  	protected CourseSessionWindow() { 
		super(Language.getController().getLangValue("CourseSessionWindow.title"),true,false,true,true);

  		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	    	splitPane.setTopComponent(new StudentCSPanel().createGUI());
	    	splitPane.setBottomComponent(new InstructorCSPanel().createGUI());
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                int screen_width= (int)dim.getWidth();
                int screen_height= (int)dim.getHeight();
		
		setSize(screen_width/2+100, screen_height/2+80);
		
                splitPane.setDividerLocation((((int)(getSize()).getHeight())/2)-50);
                splitPane.setContinuousLayout(splitPane.isContinuousLayout());

                add(splitPane,BorderLayout.CENTER);
                setLocation((screen_width/4)-120, (screen_height/4)-120);
                setVisible(true);
                setResizable(true);
	  }
}
