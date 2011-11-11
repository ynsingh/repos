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
	public void runDesktopSharing(BufferedImage originalImage){
                try {
			origanalimage=originalImage;
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

	public void setIMG_WIDTH(int value){
        	IMG_WIDTH=kk-value;
        }	
}

