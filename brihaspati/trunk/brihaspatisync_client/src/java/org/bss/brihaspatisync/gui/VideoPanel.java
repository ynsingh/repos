package org.bss.brihaspatisync.gui;

/**
 * VideoPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal</a> 
 */

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

import java.awt.Graphics2D;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;

public class VideoPanel {
	
	private int IMG_WIDTH =200;
        private int IMG_HEIGHT =200;

	private JScrollPane js=null;
        private JPanel mainPanel=null;
	private JPanel northPanel=null;
	private JLabel imageDisplay = null;
	private BufferedImage origanalimage=null;	
        private static VideoPanel videoPanel=null;
	
	public static VideoPanel getController(){
                if (videoPanel==null){
                        videoPanel=new VideoPanel();
                }
                return videoPanel;
        }

	/**
 	 * Create JscrollPane in which images display label is added to show capture images.
 	 */ 
	public JPanel createGUI(){  
                js=new JScrollPane();
		mainPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());

                northPanel=new JPanel();
                mainPanel.add(northPanel,BorderLayout.NORTH);
                imageDisplay = new JLabel();
                js.setBackground(java.awt.Color.black);
                js.getViewport().add(imageDisplay);

                mainPanel.add(js,BorderLayout.CENTER);
		
		return mainPanel; 	
	}

	public void runDesktopSharing(BufferedImage originalImage){
                try {
                        imageDisplay.setIcon(new ImageIcon(originalImage));
                } catch(Exception e){ System.out.println("Error in VideoPanel.java !! ");}
	}
	
}

