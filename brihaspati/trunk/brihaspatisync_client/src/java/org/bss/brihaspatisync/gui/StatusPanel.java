package org.bss.brihaspatisync.gui;

/**
 * StatusPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */


import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class StatusPanel extends JPanel {
	private JLabel label=null;
	private JLabel label1=null;
	
	private JLabel httpclient=new JLabel();
	private JLabel destop=new JLabel();
	private JLabel ppt=new JLabel();
	private static StatusPanel labe =null;
	private ClassLoader clr= this.getClass().getClassLoader();
	private String mess1="";
	public StatusPanel() {
		setLayout(new BorderLayout());
		try {
			label = new JLabel("<html><Font size=5 color=black><b>Status : &nbsp;&nbsp;&nbsp;       </b></font></html>");
			add(label,BorderLayout.WEST);
			label1 = new JLabel("");
			add(label1,BorderLayout.WEST);

			add(destop,BorderLayout.EAST);	
			add(ppt,BorderLayout.EAST);	
		}catch(Exception e){}
	}

	public static StatusPanel  getController(){
		if(labe==null)
			labe=new StatusPanel();
		return labe;
	}


	public void setStatus(String message){
		label1.setText("<html><blink><Font size=3 color=blue><b>"+message+"</b></font></blink></html>");
		label1.updateUI();
	}
	public void sethttpClient(String message){
		httpclient.setText("Reflector Status");
		if(!mess1.equals(message)){
			mess1=message;	
			if(httpclient != null)
				remove(httpclient);
			if(message.equals("yes"))
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/green.png")));
			else 
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
			
			add(httpclient,BorderLayout.EAST);	
			httpclient.updateUI();
		}
	}
	
	public void setdestopClient(String message){
                if(message.equals("yes"))
                        destop.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/green.png")));
                else
                        destop.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
		destop.updateUI();
        }
	
	public void setpptClient(String message){
                if(message.equals("yes"))
                        ppt.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/green.png")));
                else
                        ppt.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
		ppt.updateUI();
        }	
	
}

