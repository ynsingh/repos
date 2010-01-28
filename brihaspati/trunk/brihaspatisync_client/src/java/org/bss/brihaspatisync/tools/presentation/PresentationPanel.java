package org.bss.brihaspatisync.tools.presentation;
	
/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT kanpur.
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.io.File;
import javax.swing.JProgressBar;
import org.bss.brihaspatisync.network.ftp.FTPClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.html.*;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a> 
 */
 
public class PresentationPanel extends JPanel implements ActionListener {
	private int i=0;	
	private Timer timer=null;
	private JLabel label=null;
	private Container con=null;
	private JButton browse=null;
        private JProgressBar pb=null;
	private JPanel mainPanel=null;
	private JButton slideShow=null;
	final static int interval = 10000;
	final static int stoptimer = 10000;	
	private JFileChooser instcspanel=null;
	private static PresentationPanel prePanel=null;
	private UtilObject utilObject=UtilObject.getController();
	
	public static PresentationPanel getController(){
		if (prePanel==null){
			prePanel=new PresentationPanel();
		}
		return prePanel;
	}
	
	public PresentationPanel(){
		
		timer = new Timer(interval, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                if (stoptimer < i){
                                        Toolkit.getDefaultToolkit().beep();
					slideShow.setEnabled(true);
					browse.setEnabled(false);
                                        timer.stop();
                                        pb.setValue(0);
                                        String str = "<html>" + "<font color=\"#FF0000\">" + "Uploading completed."  + "</font>" + "</html>";
                                        label.setText(str);
                                }
                                i = i + 1;
                                pb.setValue(i);
                        }
                });
		
	}
		
  	public JPanel createGUI(){
  		mainPanel=new JPanel();
  		mainPanel.setLayout(new BorderLayout());

  		JPanel labelPane=new JPanel();
		JPanel bttnPane=new JPanel();

		if((ClientObject.getController().getUserRole()).equals("instructor")){
	               	browse=new JButton("Upload");
               		browse.addActionListener(this);
			
			pb = new JProgressBar(0, 20);
	                pb.setValue(0);
        	        pb.setStringPainted(true);
			label = new JLabel("Upload .ppt File ");
			labelPane.add(label);
			labelPane.add(pb);			

			browse.setEnabled(true);
			slideShow=new JButton("Slide Show");
        	        slideShow.addActionListener(this);
			slideShow.setEnabled(false);
			bttnPane.add(browse);
                	bttnPane.add(slideShow);

		}
		mainPanel.add(labelPane,BorderLayout.CENTER);
		mainPanel.add(bttnPane,BorderLayout.SOUTH);
		return mainPanel;
	}
	
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==browse){	
			if (instcspanel == null) {
                                instcspanel = new JFileChooser();
                                FTPClient.getController().checkDirectory();
                        }else {
		                int returnVal = instcspanel.showDialog(PresentationPanel.this,"Attach");
				this.revalidate();
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                        File b=instcspanel.getSelectedFile();
					File src=new File(b.getAbsolutePath().toString());
                                        File dst=new File("temp/");
					if(dst.exists()) {
						timer.start();
						try {
							FileChooser.getController().start(src,dst);
							label.setText("Uploading process ---");
							browse.setEnabled(false);
						}catch(Exception e){}
                                        }else {
                                                File f=new File("temp/");
                                                f=new File(f.getAbsolutePath().toString());
                                                if(!f.exists())
                                                        f.mkdir();

                                        }
                                        dst=null;
                                        src=null;
					
                                } else {
                                        label.setText("Attachment cancelled by user.");
                                }
                        }
                        instcspanel.setSelectedFile(null);

                }
		
		if(ae.getSource() == slideShow){
			SlideShowWindow.getController().setUPGUI();
			StringBuffer sb=new StringBuffer(100);
			sb=sb.append("ppt");
                        sb=sb.append("$");
                        sb=sb.append("0");
			String send_msg=sb.toString();
                        utilObject.setSendQueue(send_msg);
			return ;
		}
	}
	
	protected void stopTimer(int i){
		this.i=i;
	}
	
	protected void setlabelText(String str){
                label.setText(str);
        }
}


