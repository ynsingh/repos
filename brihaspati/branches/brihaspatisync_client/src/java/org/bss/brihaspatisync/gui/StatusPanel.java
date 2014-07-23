package org.bss.brihaspatisync.gui;

/**
 * StatusPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.util.Vector;	
import org.bss.brihaspatisync.util.ThreadController;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind pal </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a> Modified on 10 Dec 2012, Add two method getStatusLabel(), and getAppLabel().
 *
 */
public class StatusPanel extends JPanel implements Runnable {
	
	private Thread runner=null;
	private boolean flag=false;

	private JPanel east_panel=new JPanel();
	private JPanel west_panel=null;
	
	private JLabel statusLabel=null;
	private JLabel appLabel=null;
	private JLabel label1=null;
	
	private JLabel ppt =new JLabel();
	private JLabel destop =new JLabel();
	private JLabel httpclient =new JLabel();
	private JLabel audioclient =new JLabel();
	private JLabel processbarlabel =new JLabel();

	private Vector mess=new Vector();
	private Vector pptmess=new Vector();
	private Vector destopmess=new Vector();
	private Vector httpclientmess=new Vector();
	private Vector audioclientmess=new Vector();
	private Vector processbarmess=new Vector();

	private static StatusPanel labe =null;
	private ClassLoader clr= this.getClass().getClassLoader();
	private FlowLayout flowLayout = new FlowLayout();

	private String httpmessage="";
	private String pptmessage="";
	private String destmessage="";
	private String audiomessage="";
	private String processbarpmessage="";
	
	private JPanel desktop_panel=null;
	private JPanel ppt_panel=null;
	private JPanel chatwb_panel=null;
	private JPanel audio_panel=null;
	private JPanel process_panel=null;

	public StatusPanel() {
		try {
			setLayout(new BorderLayout());
			west_panel=new JPanel();
			east_panel=new JPanel();
			west_panel.setBackground(new Color(24,116,205));
			east_panel.setBackground(new Color(24,116,205));	
			statusLabel = new JLabel();
			statusLabel.setText("<html><Font size=3 color=white><b> "+Language.getController().getLangValue("StatusPanel.loginStatus")+"&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b></font></html>");
			label1 = new JLabel();	
			west_panel.add(statusLabel,flowLayout);
			west_panel.add(label1,flowLayout);
			
			appLabel=new JLabel();
			appLabel.setText("<html><Font size=3 color=white><b>" +Language.getController().getLangValue("StatusPanel.applicationStatus")+"&nbsp;:&nbsp</b></font></html>");
			east_panel.add(appLabel);
			setBackground(new Color(24,116,205));
			
			desktop_panel=new JPanel();
			desktop_panel.setBackground(new Color(24,116,205));
			destop.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.desktopShareStatus")+"</b></font></html>");
			desktop_panel.add(destop,flowLayout);

			ppt_panel=new JPanel();
			ppt_panel.setBackground(new Color(24,116,205));
			ppt.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.pptStatus")+"</b></font></html>");
			ppt_panel.add(ppt,flowLayout);
			
			process_panel=new JPanel();
                        process_panel.setBackground(new Color(24,116,205));
                        process_panel.add(processbarlabel,flowLayout);
			
			chatwb_panel=new JPanel();
                        chatwb_panel.setBackground(new Color(24,116,205));		
			httpclient.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.reflectorStatus")+"</b></font></html>");
                        chatwb_panel.add(httpclient,flowLayout);
			
	
			audio_panel=new JPanel();
			audio_panel.setBackground(new Color(24,116,205));
			audioclient.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("UpdateSessionPanel.AudioCheck")+" </b></font></html>");
                        audio_panel.add(audioclient,flowLayout);

			east_panel.add(ppt_panel,flowLayout);
			east_panel.add(desktop_panel,flowLayout);
			east_panel.add(audio_panel,flowLayout);
			east_panel.add(chatwb_panel,flowLayout);
			add(east_panel,BorderLayout.EAST);
			add(west_panel,BorderLayout.WEST);
			add(process_panel,BorderLayout.CENTER);
			
		}catch(Exception e){}
	}
	
	protected void updateGUI() {	
		statusLabel.setText("<html><Font size=3 color=white><b> "+Language.getController().getLangValue("StatusPanel.loginStatus")+"&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;</b></font></html>");
		appLabel.setText("<html><Font size=3 color=white><b>" +Language.getController().getLangValue("StatusPanel.applicationStatus")+"&nbsp;:&nbsp</b></font></html>");
		destop.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.desktopShareStatus")+"</b></font></html>");
		ppt.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.pptStatus")+"</b></font></html>");
		httpclient.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("StatusPanel.reflectorStatus")+"</b></font></html>");
		audioclient.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("UpdateSessionPanel.AudioCheck")+" </b></font></html>");
		validate();
                repaint();
	}

	public static StatusPanel  getController(){
		if(labe==null)
			labe=new StatusPanel();
		return labe;
	}

	public void startStatusPanel(){
                if (runner == null) {
			flag=true;
                        runner = new Thread(this);
                        runner.start();
                }
        }
	
	public void stopStatusPanel() {
                if (runner != null) {
			flag=true;
                        runner = null;
                }
        }

	public void run() {
                while(flag) {
                        try {
				if(audioclientmess.size()>0) {
					try {
						String message=audioclientmess.get(0).toString();
						audioclientmess.remove(0);
                			        if(message.equals("yes"))
                                			audioclient.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
		                        	else
                		                	audioclient.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
					} catch(Exception e){System.out.println("Exception in StatusPanel in method  setdestopClient");}
				}
				if(destopmess.size()>0 ) {
					String message=destopmess.get(0).toString();
                                        destopmess.remove(0);
	                       		try {
        	                       		if(message.equals("yes"))
                	                       		destop.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
                        	       		else
                                	       		destop.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
					} catch(Exception e) { System.out.println("Exception in StatusPanel in method  setdestopClient"); }
				} if(httpclientmess.size()>0) {
					String message=httpclientmess.get(0).toString();
                                        httpclientmess.remove(0);
		                        if(message.equals("yes"))
                				httpclient.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
		                        else
                		                httpclient.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
				} if(pptmess.size()>0) {
					String message=pptmess.get(0).toString();
                                        pptmess.remove(0);
		                        if(message.equals("yes"))
                		                ppt.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/clock-green-blink.gif")));
		                        else
                		                ppt.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/red.png")));
				}
				if(mess.size()>0) {
					String message=mess.get(0).toString();
                                        mess.remove(0);
					label1.setText("<html><blink><Font size=3 color=white><b>"+message+"</b></font></blink></html>");
				}
				if(processbarmess.size()>0) {
                                        String message=processbarmess.get(0).toString();
                                        processbarmess.remove(0);
                                        if(message.equals("yes")) {
                                                processbarlabel.setIcon(new javax.swing.ImageIcon(clr.getResource("resources/images/loading.gif")));
						processbarlabel.setText("<html><Font size=3 color=white><b>"+Language.getController().getLangValue("Load.panel")+"</b></font></html>");
                                        } else {
                                                processbarlabel.setIcon(null);	
						processbarlabel.setText("");
					}
                                }
				
				try {
					int sum = 0;	
					java.util.Hashtable ht=org.bss.brihaspatisync.network.singleport.NetworkController.getHashtable();
					java.util.Set<String> keys = ht.keySet();
        				for(String key_type : keys) {
						Vector value = (Vector)ht.get(key_type);
						for (int i=0;i<value.size();i++) {
							int temp=Integer.parseInt((String)value.get(i));
							sum=sum +temp;
						}
						sum =sum / (value.size());
						if((sum> 12) && (key_type.equals("ins_video"))) {
							org.bss.brihaspatisync.util.ClientObject.setInsImageQuality(0.2f);
						}
						if((sum> 12) && (key_type.equals("stud_video"))) {
							org.bss.brihaspatisync.util.ClientObject.setStdImageQuality(0.2f);
						}
						if((sum> 12) && (key_type.equals("Desktop_Data"))) {
							org.bss.brihaspatisync.util.ClientObject.setDesktopImageQuality(0.2f);
						}
							
        				}
				} catch(Exception e) {	System.out.println("Exception in StatusPanel in method  network slow "); }
				runner.sleep(500);
                		runner.yield();
                        } catch(Exception ep) {  
				try {  
					runner.sleep(1000); runner.yield(); 
					System.out.println("Exception in StatusPanel class  "+ep.getMessage());
				} catch(Exception e) { }
			}
		}
	}	
	
	public void setStatus(String message) {
		mess.add(message);
		startStatusPanel();
	}
	
	public void setaudioClient(String message) {
		if(!audiomessage.equals(message)) {
                        audiomessage=message;
			audioclientmess.add(message);
		}
        }

	public void sethttpClient(String message){
		if(!httpmessage.equals(message)) {
			httpmessage=message;
			httpclientmess.add(message.trim());
		}
	}
	
	public void setdestopClient(String message) {
		if(!destmessage.equals(message)) {
                        destmessage=message;
			destopmess.add(message);
		}
        }
	
	public void setpptClient(String message) {
		if(!pptmessage.equals(message)) {
                        httpmessage=message;
			pptmess.add(message);
		}
        }
	
	public void setProcessBar(String message) {
                if(!processbarpmessage.equals(message)) {
                        processbarpmessage=message;
                        processbarmess.add(message);
                }
        }
}

