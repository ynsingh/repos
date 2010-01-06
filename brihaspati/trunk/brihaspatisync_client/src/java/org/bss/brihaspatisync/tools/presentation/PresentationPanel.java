package org.bss.brihaspatisync.tools.presentation;
	
/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT kanpur.
 */

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.udp.UDPSender;
//import org.bss.brihaspatisync.tools.avtool.AudioReceiveAndPlay;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a> 
 */
 
public class PresentationPanel extends JPanel implements ActionListener {
	
	private Container con=null;
	private JPanel mainPanel;
	
	private JButton slideShow=null;
	private JButton browse=null;
	
	private static PresentationPanel prePanel=null;
	
	public static PresentationPanel getController(){
		if (prePanel==null){
			prePanel=new PresentationPanel();
		}
		return prePanel;
	}


  	public JPanel createGUI(){
  		mainPanel=new JPanel();
  		mainPanel.setLayout(new BorderLayout());
  		
		JPanel labelPane=new JPanel();
		
		
		if((ClientObject.getController().getUserRole()).equals("instructor")){
		
                	browse=new JButton("Browse");
               		browse.addActionListener(this);
			labelPane.add(browse);
			browse.setEnabled(false);
			slideShow=new JButton("Slide Show");
        	        slideShow.addActionListener(this);
			slideShow.setEnabled(false);
			labelPane.add(slideShow);
			
		}
		
		mainPanel.add(labelPane,BorderLayout.CENTER);
		return mainPanel;
	}
	
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==browse){
			new FileChooser();//.getController();
                }
		
		if(ae.getSource() == slideShow){
			SlideShowWindow.getController().setUPGUI();
			UDPSender.getController().getSendQueue().putString(Integer.toString(0));
			return ;
		}
	}
}


