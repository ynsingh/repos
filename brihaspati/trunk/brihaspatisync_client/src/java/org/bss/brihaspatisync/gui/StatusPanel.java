package org.bss.brihaspatisync.gui;

/**
 * StatusPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import org.bss.brihaspatisync.util.Language;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind pal </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a> Modified on 10 Dec 2012, Add two method getStatusLabel(), and getAppLabel().
 *
 */
public class StatusPanel extends JPanel {
	
	private JPanel east_panel=new JPanel();
	private JPanel west_panel=null;
	
	private JLabel statusLabel=null;
	private JLabel appLabel=null;
	private JLabel label1=null;
	
	private JLabel ppt=new JLabel();
	private JLabel destop=new JLabel();
	private JLabel httpclient=new JLabel();
	private JLabel audioclient=new JLabel();
	
	private String pptmess="";
	private String destopmess="";
	private String httpclientmess="";
	private String audioclientmess="";
	private static StatusPanel labe =null;
	private ClassLoader clr= this.getClass().getClassLoader();
	private FlowLayout flowLayout = new FlowLayout();
	
	private JPanel desktop_panel=null;
	private JPanel ppt_panel=null;
	private JPanel reflector_panel=null;
	private JPanel audio_panel=null;
	
	public StatusPanel() {
		setLayout(new BorderLayout());
		try {
			west_panel=new JPanel();
			east_panel=new JPanel();
			west_panel.setBackground(new Color(24,116,205));
			east_panel.setBackground(new Color(24,116,205));	
			statusLabel = new JLabel("<html><Font size=3 color=white><b> "+Language.getController().getLangValue("StatusPanel.loginStatus")+"&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b></font></html>");
			label1 = new JLabel();	
			west_panel.add(statusLabel,flowLayout);
			west_panel.add(label1,flowLayout);
			add(west_panel,BorderLayout.WEST);
			
			appLabel=new JLabel("<html><Font size=3 color=white><b>" +Language.getController().getLangValue("StatusPanel.applicationStatus")+"&nbsp;:&nbsp</b></font></html>");
			east_panel.add(appLabel);
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

	public JLabel getStatusLabel(){
		return statusLabel;
	}

	public JLabel getAppLabel(){
		return appLabel;
	}


	public void setStatus(String message){
		label1.setText("<html><blink><Font size=3 color=white><b>"+message+"</b></font></blink></html>");
		statusLabel.updateUI();
	}
	
	public void setaudioClient(String message){
		message=message.trim();
                if(!(audioclientmess.equals(message))){
                        audioclientmess=message;
                        if(audioclient != null)
                                reflector_panel.remove(audioclient);
                        if(message.equals("yes"))
                                audioclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                        else
                                audioclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
                        audioclient.setText("<html><Font size=3 color=white><b> Audio </b></font></html>");
                        reflector_panel.add(audioclient,flowLayout);
                        audioclient.updateUI();
                }
        }


	public void sethttpClient(String message){
		message=message.trim();
		if(!(httpclientmess.equals(message))) {
			httpclientmess=message;	
			if(httpclient != null)
				reflector_panel.remove(httpclient);
			if(message.equals("yes"))
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
			else 
				httpclient=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
			httpclient.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.reflectorStatus")+"</b></font></html>");
			reflector_panel.add(httpclient,flowLayout);	
			httpclient.updateUI();
		}
	}
	
	public void setdestopClient(String message){
		message=message.trim();
        	if(!(destopmess.equals(message))) {
                        destopmess=message;
                        if(destop != null)
                                desktop_panel.remove(destop);
                       try {
                               if(message.equals("yes"))
                                       destop=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                               else
                                       destop=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
                       }catch(Exception e){System.out.println("Error in in StatusPanel in method  setdestopClient");}
                        destop.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.desktopShareStatus")+"</b></font></html>");
                        desktop_panel.add(destop,flowLayout);
                        destop.updateUI();
                }
        }
	
	public void setpptClient(String message){
		message=message.trim();
		if(!pptmess.equals(message)){
                        pptmess=message;
                        if(ppt != null)
                                ppt_panel.remove(ppt);
                        if(message.equals("yes"))
                                ppt=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                        else
                                ppt=new JLabel(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));

                        ppt.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.pptStatus")+"</b></font></html>");
                        ppt_panel.add(ppt,flowLayout);
                        ppt.updateUI();
			//ppt_panel.revalidate();
                }
        }
}

