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
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Dimension;
import org.bss.brihaspatisync.util.ImageScaler;

public class Desktop_Sharing{

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

                mainPanel.add(imageDisplay,BorderLayout.CENTER);
		return mainPanel; 	
	}

	
	/*This method uses ImageScaler class to resize and rescale image to show on player panel.
 	 *@author : Ashish Yadav 
 	 */
	public void showImage(BufferedImage originalImage){
		try{

			Dimension mainPanel_dim=mainPanel.getSize();
			int mainPanelWidth = (int)mainPanel_dim.getWidth();
        		int mainPanelHeight = (int)mainPanel_dim.getHeight();
        		int imageWidth = originalImage.getWidth();
        		int imageHeight = originalImage.getHeight();
        		double xScale = (double)mainPanelWidth/imageWidth;
        		double yScale = (double)mainPanelHeight/imageHeight;
        		double scale = Math.min(xScale, yScale);// scale to fit
                       	Math.max(xScale, yScale); // scale to fill
                        int targetWidth = (int)(scale*imageWidth);
                        int targetHeight = (int)(scale*imageHeight);
			BufferedImage scaledImage= ImageScaler.resize(originalImage, ImageScaler.Method.QUALITY,ImageScaler.Mode.FIT_EXACT, targetWidth, targetHeight,ImageScaler.OP_ANTIALIAS);
			imageDisplay.setIcon(new ImageIcon(scaledImage));
			imageDisplay.setHorizontalAlignment(JLabel.CENTER);
			mainPanel.setBackground(Color.GRAY);
			scaledImage.flush(); 
		}catch(Exception ex){System.out.println("Error in Desktop_Sharing.java !!"+ex);}
	}
}

