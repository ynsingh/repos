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
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
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

public class Desktop_Sharing {
	
	private int IMG_WIDTH =800;
        private int IMG_HEIGHT =700;

	private JScrollPane js=null;
        private JPanel mainPanel=null;
	private JPanel northPanel=null;
	private JLabel imageDisplay = null;
	private BufferedImage origanalimage=null;	
        private static Desktop_Sharing desktopSharing=null;
	private org.bss.brihaspatisync.tools.presentation.JsliderListener slider =new org.bss.brihaspatisync.tools.presentation.JsliderListener();
	
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
                js=new JScrollPane();
		mainPanel=new JPanel();
		slider.setTools("Desktop_Sharing");
                mainPanel.setLayout(new BorderLayout());

                northPanel=new JPanel();
                mainPanel.add(northPanel,BorderLayout.NORTH);
                imageDisplay = new JLabel();
                js.setBackground(java.awt.Color.black);
                js.getViewport().add(imageDisplay);

                mainPanel.add(js,BorderLayout.CENTER);
                mainPanel.add(slider.createGUI(),BorderLayout.WEST);
		
		return mainPanel; 	
	}

	public void setSclollEnable_Decable(boolean flag){
                slider.setEnable_Decable(flag);
        }
		
	public void runDesktopSharing(BufferedImage originalImage){
                try {
			origanalimage=originalImage;
                        System.out.println(IMG_WIDTH +" , "+ IMG_HEIGHT);
                        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                        imageDisplay.setIcon(new ImageIcon(resizeImageJpg));
                } catch(Exception e){ System.out.println("Error in Desktop_Sharing.java !! ");}
	}
	
	private BufferedImage resizeImage(BufferedImage originalImage, int type) {
                BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                g.dispose();
                return resizedImage;
        }

        public void revalidateImgWidth(int w){
                IMG_WIDTH=IMG_WIDTH+w;
                int type = origanalimage.getType() == 0? BufferedImage.TYPE_INT_ARGB : origanalimage.getType();
                BufferedImage resizeImageJpg = resizeImage(origanalimage, type);
                imageDisplay.setIcon(new ImageIcon(resizeImageJpg));
        }

        public void revalidateImgHeight(int h){
                IMG_HEIGHT=IMG_HEIGHT+h;
                int type = origanalimage.getType() == 0? BufferedImage.TYPE_INT_ARGB : origanalimage.getType();
                BufferedImage resizeImageJpg = resizeImage(origanalimage, type);
                imageDisplay.setIcon(new ImageIcon(resizeImageJpg));
        }
}

