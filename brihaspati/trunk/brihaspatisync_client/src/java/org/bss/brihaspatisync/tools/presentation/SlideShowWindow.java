package org.bss.brihaspatisync.tools.presentation;

/**
 * SlideShowWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2008-2009 ETRG, IIT Kanpur
 */
 
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.io.File;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class SlideShowWindow extends JFrame implements ActionListener {
	
	private int temp=0;
	private File f=null;
	private JButton next;
	private JButton privious;

	private JPanel mainPanel=null;
        private JPanel southPanel=null;
	public static SlideShowWindow ssw=null;
	private static Dimension newframeSize=null;
	private UtilObject utilObject=UtilObject.getController();

	public static SlideShowWindow  getController() {
		if(ssw==null)
			ssw=new SlideShowWindow();
		return ssw;
	}

	private  SlideShowWindow() { }
	
	public void setUPGUI(){
		setTitle(" Image Canvas ");
		setLocation(200,200);
		mainPanel=new JPanel();		
		mainPanel.setLayout(new BorderLayout());
		southPanel=new JPanel();
		
		JScrollPane scroller = new JScrollPane(ImageLoadforStudent.getController());
	        scroller.setPreferredSize(new Dimension(200,200));
		ImageLoadforStudent.getController().runSlide(temp);	
		privious=new JButton("priv");
                privious.addActionListener(this);
		next=new JButton("next");
                next.addActionListener(this);
		southPanel.add(privious);
		southPanel.add(next);
		mainPanel.add(scroller,BorderLayout.CENTER);
		if(ClientObject.getController().getUserRole().equals("instructor"))
                	mainPanel.add(southPanel,BorderLayout.SOUTH);			
		setContentPane(mainPanel);
		setSize(550,550);
                setResizable(true);
                setVisible(true);
		if(ClientObject.getController().getUserRole().equals("instructor")){
			addWindowListener( new WindowAdapter (){
                        	public void windowClosing (WindowEvent ev ){
					StringBuffer sb=new StringBuffer(100);
                                	sb=sb.append("ppt");
                                	sb=sb.append("$");
                                	sb=sb.append("cancleppt");
					String send_msg=sb.toString();
                               		utilObject.setSendQueue(send_msg);
                                	getCanclePPT();
                        	}
                	});
		}
	}
	
	public void getCanclePPT(){
                setVisible(false);
		ssw=null;
        }

	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==next){
			f=new File("temp/presentation");
			String str[]=f.list();	
			if(temp==(str.length-1)){
				JOptionPane.showMessageDialog(null," This is last .ppt !!");
			}else {
				temp=temp+1;
				ImageLoadforStudent.getController().runSlide(temp);	
				StringBuffer sb=new StringBuffer(100);
        	                sb=sb.append("ppt");
	                        sb=sb.append("$");
                	        sb=sb.append(Integer.toString(temp));
				String send_msg=sb.toString();
                        	utilObject.setSendQueue(send_msg);
			}
		}
		if(ae.getSource()==privious){
			if(temp>0){
				temp=temp-1;
                                ImageLoadforStudent.getController().runSlide(temp);
				StringBuffer sb=new StringBuffer(100);
                                sb=sb.append("ppt");
                                sb=sb.append("$");
                                sb=sb.append(Integer.toString(temp));
				String send_msg=sb.toString();
                                utilObject.setSendQueue(send_msg);
			}else{
				JOptionPane.showMessageDialog(null," This is start .ppt !!");
			}
	
                }
	}
}
