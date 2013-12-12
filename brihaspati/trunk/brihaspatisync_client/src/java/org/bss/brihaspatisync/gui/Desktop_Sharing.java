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
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.util.ImageScaler;

public class Desktop_Sharing implements MouseListener {

        private JLayeredPane videoPanel=null;
	private JPanel centerPanel=null;
	private JPanel leftPanel=null;	
	private JLabel imageDisplay = null;
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
	public JLayeredPane createGUI() { 
		 
		videoPanel=new JLayeredPane();

                leftPanel=new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
                imageDisplay = new JLabel();
                JScrollPane js=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js.getViewport().add(imageDisplay);
                leftPanel.add(js,BorderLayout.CENTER);
		
                centerPanel=new JPanel();
                centerPanel.setLayout(new BorderLayout());
		centerPanel.setLocation(0,0);
		centerPanel.setSize(0,0);

                JLabel otherimageDisplay= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("resources/images/icon_fullscreen_off.jpg")));
                otherimageDisplay.setName("click");
                otherimageDisplay.setEnabled(true);
                otherimageDisplay.addMouseListener(this);

                JScrollPane js1=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js1.getViewport().add(otherimageDisplay);
                centerPanel.add(js1,BorderLayout.CENTER);
                videoPanel.add(leftPanel, new Integer(0), 0);
                videoPanel.add(centerPanel, new Integer(1), 0);
		return videoPanel; 	
	}

	public void mouseClicked(MouseEvent e) {
                String cmd=e.getComponent().getName();
                if(cmd.equals("click")) {
                        try {
				org.bss.brihaspatisync.network.desktop_sharing.Post_GetSharedScreen.getController().setFlag(true);
                                FullScreen.getController().screenshare(FullScreen_Mode.getController().createGUI());
                        }catch(Exception ex ){}
                }

        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}


	/*This method uses ImageScaler class to resize and rescale image to show on player panel.
 	 *@author : Ashish Yadav 
 	 */
	public void showImage(BufferedImage originalImage){
		try{
			
			Dimension mainPanel_dim=videoPanel.getSize();
			int mainPanelWidth = (int)mainPanel_dim.getWidth();
        		int mainPanelHeight = (int)mainPanel_dim.getHeight();
			leftPanel.setSize(mainPanelWidth,mainPanelHeight);
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
			centerPanel.setLocation(targetWidth-40,4);
	                centerPanel.setSize(40,40);
			centerPanel.updateUI();
			//scaledImage.flush(); 
		}catch(Exception ex){System.out.println("Exception in showImage at Desktop_Sharing class "+ex);}
	}
	
	public void resetshowImage(){
                try{
			imageDisplay.setIcon(null);
                        centerPanel.updateUI();

                } catch(Exception ex){System.out.println("Error in resetshowImage method in Desktop_Sharing class "+ex);}
        }
}

