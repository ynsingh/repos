package org.bss.brihaspatisync.gui;

/**
 * Desktop_Sharing.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Created on feb2011
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>Modified on feb2011
 */

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;

public class Desktop_Sharing {

	private JLabel imageDisplay = null;
	private JScrollPane js=null;
		
        private static Desktop_Sharing desktopSharing=null;
	
	public static Desktop_Sharing getController(){
                if (desktopSharing==null){
                        desktopSharing=new Desktop_Sharing();
                }
                return desktopSharing;
        }

	/**
 	 * Create JscrollPane in which images dislpay label is added to show screen share images.
 	 */ 
	public JScrollPane createGUI(){                 
		js=new JScrollPane(); 
		js.setBackground(java.awt.Color.black); 
		imageDisplay = new JLabel();
		js.getViewport().add( imageDisplay);
		return js; 	
	}
	/*
	public JLabel getImageDisplay(){
		return imageDisplay;
	}
	*/	
	public void runDesktopSharing(BufferedImage image){
                try {
			imageDisplay.setIcon(new ImageIcon(image));
                } catch(Exception e){ System.out.println("Error in Desktop_Sharing.java \n\n");}
	}
}

