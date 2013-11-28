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
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;


import java.awt.Font;
import java.awt.Color;

public class VideoPanel {
	
	private int W =200;
        private int H =150;
	
	private int W1 =200;
        private int H1 =150;
	
	private int kk=0;
	private int kk1=0;
	private int half=0;
		
	private JPanel av_Pane=null;
	private JScrollPane js=null;
	private JScrollPane js1=null;
        private JLayeredPane mainPanel=null;
        private JPanel leftPanel=null;
        private JPanel centerPanel=null;
	
	private JLabel selfimageDisplay = null;
        private JLabel otherimageDisplay = null;
        private static VideoPanel videoPanel=null;
	
	
	public static VideoPanel getController(){
                if (videoPanel==null){
                        videoPanel=new VideoPanel();
                }
                return videoPanel;
        }
	
	public void resetController() {
		videoPanel=null;
	}
	
	/**
 	 * Create JscrollPane in which images display label is added to show capture images.
 	 */ 
	public JPanel createGUI() {  
		try {
		av_Pane=new JPanel();
                av_Pane.setLayout(new BorderLayout());
                av_Pane.setBackground(Color.BLACK);
                
                JPanel labelPane=new JPanel();
                JLabel welcome=new JLabel(Language.getController().getLangValue("UserListPanel.WelcomeLabel"));
                welcome.setFont(new Font("Arial", Font.PLAIN, 12));

                JLabel userLogin=new JLabel(java.net.URLDecoder.decode(org.bss.brihaspatisync.util.ClientObject.getController().getwelcomeUserName(),"UTF-8")) ;
                userLogin.setForeground(new Color(0,0,0));
                userLogin.setFont(new Font("Arial", Font.BOLD, 12));
                labelPane.add(welcome);
                labelPane.add(userLogin);
                        
                av_Pane.add(labelPane,BorderLayout.NORTH);
		
		mainPanel=new JLayeredPane();
		//mainPanel.setBackground(new Color(1, 0, 0, 0.5f));
	
		leftPanel=new JPanel();
		leftPanel.setLayout(new BorderLayout());
	        //leftPanel.setBackground(new Color(1, 0, 0, 0.5f));
		
		selfimageDisplay = new JLabel();	
		js=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js.getViewport().add(selfimageDisplay);
		leftPanel.add(js,BorderLayout.CENTER);
		
		centerPanel=new JPanel();
		centerPanel.setLayout(new BorderLayout());
		//centerPanel.setBackground(new Color(1, 0, 0, 0.5f));
		centerPanel.setLocation(0,0);
		centerPanel.setSize(0,0);
		
		otherimageDisplay= new JLabel();	
                js1=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                js1.getViewport().add(otherimageDisplay);
                centerPanel.add(js1,BorderLayout.CENTER);
        	
        	mainPanel.add(leftPanel, new Integer(0), 0);
	        mainPanel.add(centerPanel, new Integer(1), 0);
                av_Pane.add(mainPanel,BorderLayout.CENTER);
		} catch(Exception e ){System.out.println("Exception in VideoPanel class  "+e.getMessage());}
		return av_Pane; 	
	}
	
	
	
	public void removeStudentPanel(){
		try {
			half=0;
			centerPanel.setSize(0,0);
			centerPanel.updateUI();
			mainPanel.updateUI();
	                mainPanel.revalidate();				
		} catch(Exception e){ System.out.println("Error in VideoPanel.java !! "+e.getMessage());}
	}
	public void addStudentPanel(){
        	try {
			half=1;
		} catch(Exception e){ System.out.println("Error in VideoPanel.java !! "+e.getMessage());}
        }
	public void runInstructorVidio(BufferedImage originalImage){
                try {
			if(originalImage != null){
                        	int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	                        originalImage=resizeImage(originalImage,type);
        	                selfimageDisplay.setIcon(new ImageIcon(originalImage));
			}
		} catch(Exception e){ System.out.println("Error in VideoPanel.java 111!! "+e.getMessage());}
        }
	
	public void runStudentVidio(BufferedImage originalImage){
                try {
                        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                        originalImage=resizeImageforstudent(originalImage,type);
                        otherimageDisplay.setIcon(new ImageIcon(originalImage));
                } catch(Exception e){ System.out.println("Error in VideoPanel.java 22222!! "+e.getMessage());}
        }

	private BufferedImage resizeImageforstudent(BufferedImage originalImage, int type) {
		W=av_Pane.getSize().width;
                H=av_Pane.getSize().height;
                BufferedImage resizedImage = new BufferedImage(((W*40)/100),((H*40)/100), type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0,((W*40)/100),((H*40)/100), null);
		g.dispose();
		centerPanel.setLocation((W-((W*40)/100)),(H-((H*40)/100)));//setLocation((W-150),(H-120));
                centerPanel.setSize(((W*40)/100),((H*40)/100));
                centerPanel.updateUI();
                return resizedImage;
        }
	
        private BufferedImage resizeImage(BufferedImage originalImage, int type) {
		W=av_Pane.getSize().width;
                H=av_Pane.getSize().height;
                BufferedImage resizedImage = new BufferedImage(W,H, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0,W,H, null);
		g.dispose();
		if(half==1){
			centerPanel.updateUI();
		}
       		leftPanel.setSize(W,H);
		leftPanel.updateUI();
		mainPanel.updateUI();
                mainPanel.revalidate();	
		
                return resizedImage;
        }

	/*	
	public void setIMG_HIEGHT(int value) {
		if(H1<value) {
	                H1=value;
			if((W1-W)>=(H1-H)){
				W=W+(H1-H);
				H=H+(H1-H);
			}
		}else if(H1>value) {
			H1=value;
			if(H1<H){
				W=W-(H-H1);
                	        H=H-(H-H1);
			}
		}
			
		if(kk==0){
			H=H1;
			kk++;
                }
        }	

        public void setIMG_WIDTH(int value) {
		if(W1<value) {
                	W1=value;
			if((W1-W)<=(H1-H)){
				H=H+(W1-W);
                                W=W+(W1-W);	
			}
                }else if(W1>value){
			W1=value;
			if(W1<W){
				H=H-(W-W1);
                	      	W=W-(W-W1);
			}
                }
		if(kk1==0){
			W=W1;
			kk1++;
                }
        }*/	
}

