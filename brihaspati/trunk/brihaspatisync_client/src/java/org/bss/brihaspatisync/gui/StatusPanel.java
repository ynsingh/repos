package org.bss.brihaspatisync.gui;

/**
 * StatusPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */


import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

public class StatusPanel extends JPanel {
	
	private JPanel east_panel=new JPanel();
	private JPanel west_panel=null;
	
	private JLabel label=null;
	private JLabel label1=null;
	
	private JLabel ppt=new JLabel();
	private JLabel destop=new JLabel();
	private JLabel httpclient=new JLabel();
	
	private String pptmess="";
	private String destopmess="";
	private String httpclientmess="";
	private static StatusPanel labe =null;
	private ClassLoader clr= this.getClass().getClassLoader();
	private FlowLayout flowLayout = new FlowLayout();
	
	private JPanel desktop_panel=null;
	private JPanel ppt_panel=null;
	private JPanel reflector_panel=null;
	
	public StatusPanel() {
		setLayout(new BorderLayout());
		try {
			west_panel=new JPanel();
			east_panel=new JPanel();
			west_panel.setBackground(new Color(24,116,205));
			east_panel.setBackground(new Color(24,116,205));	
			label = new JLabel("<html><Font size=3 color=white><b> Status&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b></font></html>");
			label1 = new JLabel();	
			west_panel.add(label,flowLayout);
			west_panel.add(label1,flowLayout);
			add(west_panel,BorderLayout.WEST);
			
			east_panel.add(new JLabel("<html><Font size=3 color=white><b> Application Status&nbsp;:&nbsp</b></font></html>"),flowLayout);
			setBackground(new Color(24,116,205));
			
			desktop_panel=new JPanel();
			desktop_panel.setBackground(new Color(24,116,205));
			
			ppt_panel=new JPanel();
			ppt_panel.setBackground(new Color(24,116,205));
			
			reflector_panel=new JPanel();
			reflector_panel.setBackground(new Color(24,116,205));
			east_panel.add(desktop_panel,flowLayout);
			east_panel.add(ppt_panel,flowLayout);
			east_panel.add(reflector_panel,flowLayout);
			add(east_panel,BorderLayout.EAST);
			
		}catch(Exception e){}
	}

	public static StatusPanel  getController(){
		if(labe==null)
			labe=new StatusPanel();
		return labe;
	}


	public void setStatus(String message){
		label1.setText("<html><blink><Font size=3 color=white><b>"+message+"</b></font></blink></html>");
		label.updateUI();
	}
	public void sethttpClient(String message){
		if(!httpclientmess.equals(message)){
			httpclientmess=message;	
			if(httpclient != null)
				reflector_panel.remove(httpclient);
			if(message.equals("yes"))
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
			else 
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
			httpclient.setText("<html><Font size=3 color=white><b> Reflector</b></font></html>");
			reflector_panel.add(httpclient,flowLayout);	
			httpclient.updateUI();
		}
	}
	
	public void setdestopClient(String message){
		if(!destopmess.equals(message)){
                        destopmess=message;
                        if(httpclient != null)
                                desktop_panel.remove(destop);
			try {
                        	if(message.equals("yes"))
                                	destop=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                        	else
                                	destop=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
			}catch(Exception e){System.out.println("Error in in StatusPanel in method  setdestopClient");}
                        destop.setText("<html><Font size=3 color=white><b> DesktopShare</b></font></html>");
                        desktop_panel.add(destop,flowLayout);
                        destop.updateUI();
			desktop_panel.revalidate();
                }
		
        }
	
	public void setpptClient(String message){
		if(!pptmess.equals(message)){
                        pptmess=message;
                        if(httpclient != null)
                                ppt_panel.remove(ppt);
                        if(message.equals("yes"))
                                ppt=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                        else
                                ppt=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));

                        ppt.setText("<html><Font size=3 color=white><b> PPT </b></font></html>");
                        ppt_panel.add(ppt,flowLayout);
                        ppt.updateUI();
			ppt_panel.revalidate();
                }
        }
}

