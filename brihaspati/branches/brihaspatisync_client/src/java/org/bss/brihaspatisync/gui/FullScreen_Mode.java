package org.bss.brihaspatisync.gui;

/**
 * FullScreen_Mode.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.util.ImageScaler;

/**
  *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Created on dec2012
  **/

public class FullScreen_Mode implements MouseListener{

	private JLayeredPane mainPanel=null;
        private JPanel leftPanel=null;
        private JPanel centerPanel=null;
	private JScrollPane js=null;
        private JScrollPane js1=null;
	private JLabel selfimageDisplay = null;
        private JLabel otherimageDisplay = null;
	
	private JLabel imageDisplay = null;
	private BufferedImage origanalimage=null;	
	private javax.swing.JButton button;
        private static FullScreen_Mode fullscreen_mode=null;

	
	public static FullScreen_Mode getController(){
                if (fullscreen_mode == null){
                        fullscreen_mode =new FullScreen_Mode();
                }
                return fullscreen_mode;
        }
	
	/**
 	 * Create JscrollPane in which images dislpay label is added to show screen share images.
 	 */ 

	public JLayeredPane createGUI() {

                mainPanel=new JLayeredPane();
                mainPanel.setBackground(Color.YELLOW);

                leftPanel=new JPanel();
                leftPanel.setLayout(new BorderLayout());
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		leftPanel.setSize(screensize.width,screensize.height);

                imageDisplay = new JLabel();
                js=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js.getViewport().add(imageDisplay);
                leftPanel.add(js,BorderLayout.CENTER);

                centerPanel=new JPanel();
                centerPanel.setLayout(new BorderLayout());
                centerPanel.setLocation(0,0);
                centerPanel.setSize(40,40);

                otherimageDisplay= new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("resources/images/icon_fullscreen_off.jpg")));
		otherimageDisplay.setName("click");
                otherimageDisplay.setEnabled(true);
                otherimageDisplay.addMouseListener(this);
		
                js1=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js1.getViewport().add(otherimageDisplay);
                centerPanel.add(js1,BorderLayout.CENTER);
		mainPanel.add(leftPanel, new Integer(0), 0);
                mainPanel.add(centerPanel, new Integer(1), 0);
                return mainPanel;
        }
		
	
	public void mouseClicked(MouseEvent e) {
                String cmd=e.getComponent().getName();
                if(cmd.equals("click")) {
                        try {
				org.bss.brihaspatisync.network.desktop_sharing.Post_GetSharedScreen.getController().setFlag(false);
                                FullScreen.getController().disposescreenshare();
                        }catch(Exception ex ){}
                }

        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
	
	/** This method uses ImageScaler class to resize and rescale image to show on player panel. */
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
			scaledImage.flush(); 
		}catch(Exception ex){System.out.println("Error in FullScreen_Mode.java !!"+ex);}
	}
}

