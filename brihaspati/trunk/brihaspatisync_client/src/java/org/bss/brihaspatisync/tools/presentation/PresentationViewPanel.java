package org.bss.brihaspatisync.tools.presentation;

/**
 * PresentationViewPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */


import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.Graphics2D;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import org.bss.brihaspatisync.network.ppt_sharing.PostPPTScreen;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>
 */

public class PresentationViewPanel implements ActionListener {
	private int IMG_WIDTH =800;
        private int IMG_HEIGHT =700;
	private int temp=0;
	private JButton next;
        private JButton previous;
	private JScrollPane js=null;
        private JPanel northPanel=null;
        private JPanel mainPanel=null;
	private JLabel imageDisplay = null;
	private BufferedImage origanalimage=null;
	
        private static PresentationViewPanel pptPanel =null;
	
	public static PresentationViewPanel getController(){
                if (pptPanel==null){
                        pptPanel =new PresentationViewPanel();
                }
                return pptPanel;
        }
	
	/**
 	 * Create JscrollPane in which images dislpay label is added to show screen share images.
 	 */ 
	public JPanel createGUI(){                 
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		northPanel=new JPanel();
		previous=new JButton("previous");
                previous.addActionListener(this);
		previous.setEnabled(false);

                next=new JButton("next");
                next.addActionListener(this);
                next.setEnabled(false);

                northPanel.add(previous);
                northPanel.add(next);
		
		mainPanel.add(northPanel,BorderLayout.NORTH);

		imageDisplay = new JLabel();
                js=new JScrollPane();
                js.setBackground(java.awt.Color.black);
                js.getViewport().add(imageDisplay);

		mainPanel.add(js,BorderLayout.CENTER);
		mainPanel.add(JsliderListener.getController().createGUI(),BorderLayout.WEST);
		return mainPanel; 	
	}

	public void setEnable_Decable(boolean t1,boolean t2){
		previous.setEnabled(t1);
                next.setEnabled(t2);
        }

	public void runPresentationPanel(BufferedImage originalImage){
                try {
			origanalimage=originalImage;
			System.out.println(IMG_WIDTH +" , "+ IMG_HEIGHT);
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			imageDisplay.setIcon(new ImageIcon(resizeImageJpg));
                } catch(Exception e){ System.out.println("Error in Desktop_Sharing.java \n\n");}
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

	public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==next){
			File f=new File("temp/");
                        String str[]=f.list();
                        if(temp==(str.length-1)){
                                JOptionPane.showMessageDialog(null," This is last .ppt !!");
                        }else {
                                temp=temp+1;
                                try {
                                        PostPPTScreen.getController().start_to_sendppt(temp);
                                }catch(Exception e){System.out.println(" Error in ===========>  "+e.getMessage());}
				if(temp==(str.length-1)){
                                        next.setEnabled(false);
                                        previous.setEnabled(true);
                                        JOptionPane.showMessageDialog(null," This is last ppt Slide !!");
                                }else{
                                        next.setEnabled(true);
                                        previous.setEnabled(true);
                                }
			}		
                }
                if(ae.getSource()==previous){
			if(temp>0){
                                temp=temp-1;
                                try {
                                        PostPPTScreen.getController().start_to_sendppt(temp);
                                }catch(Exception e){
                                        System.out.println(" Error in ===========>  "+e.getMessage());
                                }
				if(temp==0) {
                                        next.setEnabled(true);
                                        previous.setEnabled(false);
                                        JOptionPane.showMessageDialog(null," This is start ppt Slide !!");
                                }else{
                                        next.setEnabled(true);
                                        previous.setEnabled(true);
                                }
                        }else{
                                JOptionPane.showMessageDialog(null," This is start .ppt !!");
                        }

                }
	}
}

