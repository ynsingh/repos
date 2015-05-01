package org.bss.brihaspatisync.gui;

/**
 * VideoServerConfigure.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2015 ETRG, IIT Kanpur.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.FlowLayout;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Arvind Pal </a> 
 */

public class VideoServerConfigure implements ActionListener {

	private JFrame frame;
        private JPanel main_panel,center_toppanel;
        private JPanel center_panel;
        private JPanel south_panel;
        private JPanel west_panel;
        private JPanel East_panel;
        private JLabel name_label, name_labelPort,name_labelBit,vlc_server_path_label,vlc_device_name_label,message_label1,message_label2,message_label3,message_label4,message_label5;
        private JTextField usr_text ,usr_textPort, usr_textBit,vlc_server_path_text,vlc_device_name_text;
        private JButton ok_bttn,cancel_bttn,browse_bttn;
        private String os=System.getProperty("os.name");

	public VideoServerConfigure(){
		createGUI();
	}	

	public void createGUI(){
		
		frame=new JFrame();
                frame.setTitle(Language.getController().getLangValue("VideoServerConfigure.Title"));
                main_panel=new JPanel();
                main_panel.setLayout(new BorderLayout());

                center_panel=new JPanel();
	
		if(os.startsWith("Win")){
		
                name_label=new JLabel(Language.getController().getLangValue("VideoServerConfigure.lable"));
                usr_text=new JTextField(15);
		usr_text.setText(RuntimeDataObject.getController().getVideoServer());
                center_panel.add(name_label,BorderLayout.EAST);
                center_panel.add(usr_text);

		/*** port ******/
		
		name_labelPort=new JLabel(Language.getController().getLangValue("VideoServerConfigurePort.lable"));
                usr_textPort=new JTextField(3);
                usr_textPort.setText(RuntimeDataObject.getController().getVideoServerPort());
                center_panel.add(name_labelPort);
                center_panel.add(usr_textPort); 

		/*** port ******/
		
		/*** set video server path *****/
                
		center_toppanel = new JPanel(new FlowLayout());
		vlc_server_path_label=new JLabel("VlC Installer Path ");
                vlc_server_path_text=new JTextField(21);
		if(System.getenv("ProgramFiles(x86)") != null)
                	RuntimeDataObject.getController().setVLCServer((Language.getController().getLangValue("VideoServerArch64.lable")));
		else
			RuntimeDataObject.getController().setVLCServer((Language.getController().getLangValue("VideoServerArch32.lable")));
                vlc_server_path_text.setText(RuntimeDataObject.getController().getVLCServer());
                browse_bttn=new JButton("Browse");
		browse_bttn.setActionCommand("browse_Bttn");
                browse_bttn.addActionListener(this);
                center_toppanel.add(vlc_server_path_label);
                center_toppanel.add(vlc_server_path_text);
                center_toppanel.add(browse_bttn);
                
                center_panel.add(center_toppanel);

		}
		else{
		message_label1 =new JLabel(Language.getController().getLangValue("VideoServerConfigure.message1"));
		message_label2 =new JLabel(Language.getController().getLangValue("VideoServerConfigure.message2"));
		center_panel.add(message_label1);
		center_panel.add(message_label2);
		}
		
		/*** end video server path *****/
		

                main_panel.add(center_panel, BorderLayout.CENTER);
                

                south_panel=new JPanel();
                ok_bttn=new JButton(Language.getController().getLangValue("ProxyAuthenticator.OkButton"));
		ok_bttn.setActionCommand("OK_Bttn");
                ok_bttn.addActionListener(this);
		cancel_bttn=new JButton(Language.getController().getLangValue("PreferenceWindow.Cancelbttn"));
		cancel_bttn.setActionCommand("Cancel_Bttn");
                cancel_bttn.addActionListener(this);
                
		south_panel.add(ok_bttn);
                south_panel.add(cancel_bttn);
                main_panel.add(south_panel,BorderLayout.SOUTH);

                frame.getContentPane().add(main_panel);
		java.awt.Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frame.setSize(570,200);
                frame.setLocation((((int)dim.getWidth()/2)-350),(((int)dim.getHeight()/2)-220));
                frame.setVisible(true);

	}
	
	public String fileSelect(){
	String path = "";
        JFileChooser filedialog = new JFileChooser();
        filedialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnval = filedialog.showOpenDialog(MainWindow.getController().getDesktop());
        if (returnval == JFileChooser.APPROVE_OPTION){
        java.io.File file = filedialog.getSelectedFile();
         path  = file.getAbsolutePath();
        System.out.println("full path of the directory is "+path);
        }
        return path;
      }
	
	public void actionPerformed(ActionEvent e) {
       	
		if(e.getActionCommand().equals("OK_Bttn")){
		         if(os.startsWith("Linux")){
				frame.dispose();
				}
			else{
			
				if( !( ((usr_text.getText()).equals("")) || ((usr_textPort.getText()).equals("")) || ((vlc_server_path_text.getText()).equals("")))  ){
                        		RuntimeDataObject.getController().setVideoServer(usr_text.getText());
                        		RuntimeDataObject.getController().setVideoServerPort(usr_textPort.getText());
					RuntimeDataObject.getController().setVLCServer(vlc_server_path_text.getText());
                             		frame.dispose();
                      		}else{
                        		JOptionPane.showMessageDialog(null,Language.getController().getLangValue("VideoServerConfigure.Message"));
                  		}
                      
                          }
                                                 	
		}else if(e.getActionCommand().equals("Cancel_Bttn")){
			frame.dispose();
		}
		else if(e.getActionCommand().equals("browse_Bttn")){
		vlc_server_path_text.setText(fileSelect());
		}
	}

	

}

