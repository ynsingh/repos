package org.bss.brihaspatisync.tools.presentation;

/**
 * SlideShowWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2008-2009 ETRG, IIT Kanpur
 */
 
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;

import java.io.File;

import org.bss.brihaspatisync.network.udp.UDPSender;
import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class SlideShowWindow extends JFrame implements ActionListener {
	
	public static SlideShowWindow ssw=null;
	private static Dimension newframeSize=null;
	
	private JButton privious;
	private JButton next;

	private JPanel mainPanel=null;
        private JPanel centerPanel=null;
        private JPanel southPanel=null;
	private JScrollPane imagePane=null;

	private int temp=0;
	
	private File f=null;
        private String str[]=null;
	//private JScrollPane scrollpane;

	public static SlideShowWindow  getController() {
		if(ssw==null)
			ssw=new SlideShowWindow();
		return ssw;
	}
	private  SlideShowWindow() {
		//scrollpane = new JScrollPane(centerPanel);
		//scrollpane.setAutoscrolls(true);
	}
	
	public void setUPGUI(){
		setTitle(" Image Canvas ");
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)d.getWidth(),((int)d.getHeight()));
		setLocation(0,0);
		setBackground(Color.gray);
		
		mainPanel=new JPanel();		
		mainPanel.setLayout(new BorderLayout());
		
		southPanel=new JPanel();
		
			

		centerPanel=new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(ImageLoadforStudent.getController(),BorderLayout.CENTER);		
		
		//scrollpane = new JScrollPane(ImageLoadforStudent.getController());
                //scrollpane.setAutoscrolls(true);


		ImageLoadforStudent.getController().runSlide(temp);	
		privious=new JButton("priv");
                privious.addActionListener(this);
		next=new JButton("next");
                next.addActionListener(this);
		
		southPanel.add(privious);
		southPanel.add(next);
		
		//mainPanel.add(scrollpane,BorderLayout.CENTER);
		mainPanel.add(centerPanel,BorderLayout.CENTER);

		if(ClientObject.getController().getUserRole().equals("instructor"))
                	mainPanel.add(southPanel,BorderLayout.SOUTH);			
		setContentPane(mainPanel);
		setSize(550,550);
                setResizable(true);
                setVisible(true);
		addWindowListener( new WindowAdapter (){
                        public void windowClosing (WindowEvent ev ){
                                UDPSender.getController().getSendQueue().putString("cancleppt");
                                getCanclePPT();
                        }
                });
	}
	
	public void getCanclePPT(){
                setVisible(false);
		ssw=null;
        }

	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==next){
			f=new File("temp/presentation");
			str=f.list();	
			if(temp <str.length){
				temp=temp+1;
				ImageLoadforStudent.getController().runSlide(temp);	
				UDPSender.getController().getSendQueue().putString(Integer.toString(temp));
			}else{
				JOptionPane.showMessageDialog(null," This last image !!");
			}
		}
		if(ae.getSource()==privious){
			if(temp>0){
				temp=temp-1;
				ImageLoadforStudent.getController().runSlide(temp);
				UDPSender.getController().getSendQueue().putString(Integer.toString(temp));
			}else{
				JOptionPane.showMessageDialog(null," This is start image !!");
			}
	
                }
	}

}
