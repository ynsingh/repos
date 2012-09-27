package org.bss.brihaspatisync.gui;

/**
 * Desktop_Sharing.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Created on feb2011
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>Modified on feb2011
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation.
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> testing on aug 2011 for gui.
 */

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;

import java.awt.Graphics2D;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Dimension;
import org.bss.brihaspatisync.util.ImageScaler;

public class Desktop_Sharing {

	private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
	private int kk=(int)dim.getWidth()-5;
	private int IMG_WIDTH =(int)dim.getWidth();
        private int IMG_HEIGHT =((int)dim.getHeight()-200);

	private JScrollPane js=null;
        private JPanel mainPanel=null;
	private JPanel centerPanel=null;
	private JLabel imageDisplay = null;
	private BufferedImage origanalimage=null;	
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
	public JPanel createGUI(){  
                js=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel=new JPanel();
		centerPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new java.awt.Color(24,116,205));
                imageDisplay = new JLabel();
		imageDisplay.setBackground(new java.awt.Color(24,116,205));
                js.getViewport().add(imageDisplay);

                mainPanel.add(js,BorderLayout.CENTER);
		mainPanel.add(centerPanel,BorderLayout.EAST);
		mainPanel.add(centerPanel,BorderLayout.WEST);
		mainPanel.add(centerPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.SOUTH);
		return mainPanel; 	
	}
	
	/*This method uses ImageScaler class to resize and rescale image to show on player panel.
 	 *@author : Ashish Yadav 
 	 */
	public void showImage(BufferedImage originalImage){
		try{
			BufferedImage scaledImage= ImageScaler.resize(originalImage, ImageScaler.Method.QUALITY, ImageScaler.Mode.FIT_EXACT ,IMG_WIDTH,IMG_HEIGHT, ImageScaler.OP_ANTIALIAS);
			imageDisplay.setIcon(new ImageIcon(scaledImage));
			scaledImage.flush(); 
		}catch(Exception ex){System.out.println("Error in Desktop_Sharing.java !!");}
	}

	public void setIMG_WIDTH(int value){
        	IMG_WIDTH=kk-value;
        }	
}

