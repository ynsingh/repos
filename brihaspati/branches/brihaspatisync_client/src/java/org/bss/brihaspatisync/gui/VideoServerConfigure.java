package org.bss.brihaspatisync.gui;

/**
 * VideoServerConfigure.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
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

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Arvind Pal </a> 
 */

public class VideoServerConfigure implements ActionListener {

	private JFrame frame;
        private JPanel main_panel;
        private JPanel center_panel;
        private JPanel south_panel;
        private JLabel name_label, name_labelPort,vlc_server_path_label,vlc_device_name_label;
        private JTextField usr_text ,usr_textPort,vlc_server_path_text,vlc_device_name_text;
        private JButton ok_bttn,cancel_bttn;

	public VideoServerConfigure(){
		createGUI();
	}	

	public void createGUI(){
		frame=new JFrame();
                frame.setTitle(Language.getController().getLangValue("VideoServerConfigure.Title"));
                main_panel=new JPanel();
                main_panel.setLayout(new BorderLayout());

                center_panel=new JPanel();
	
			
                name_label=new JLabel(Language.getController().getLangValue("VideoServerConfigure.lable"));
                usr_text=new JTextField(15);
		usr_text.setText(RuntimeDataObject.getController().getVideoServer());
                center_panel.add(name_label);
                center_panel.add(usr_text);

		/*** port ******/
		
		name_labelPort=new JLabel(Language.getController().getLangValue("VideoServerConfigurePort.lable"));
                usr_textPort=new JTextField(3);
                usr_textPort.setText(RuntimeDataObject.getController().getVideoServerPort());
                center_panel.add(name_labelPort);
                center_panel.add(usr_textPort);
		/*** port ******/

		/*** set video server path *****/
		vlc_server_path_label=new JLabel(" VlC Installer Path ");//Language.getController().getLangValue("VideoServerConfigurePort.lable"));
                vlc_server_path_text=new JTextField(20);
                vlc_server_path_text.setText(RuntimeDataObject.getController().getVLCServer());
                center_panel.add(vlc_server_path_label);
                center_panel.add(vlc_server_path_text);

		vlc_device_name_label=new JLabel(" VlC Device Name ");//Language.getController().getLangValue("VideoServerConfigurePort.lable"));
                vlc_device_name_text=new JTextField(20);
                vlc_device_name_text.setText(RuntimeDataObject.getController().getVLCdeviceName());
                center_panel.add(vlc_device_name_label);
                center_panel.add(vlc_device_name_text);
		
		
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
                frame.setSize(450,300);
                frame.setLocation((((int)dim.getWidth()/2)-185),(((int)dim.getHeight()/2)-220));
                frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
       	
		if(e.getActionCommand().equals("OK_Bttn")){
			if(!((usr_text.getText()).equals(""))){
                        	RuntimeDataObject.getController().setVideoServer(usr_text.getText());
                        	RuntimeDataObject.getController().setVideoServerPort(usr_textPort.getText());
				RuntimeDataObject.getController().setVLCServer(vlc_server_path_text.getText());
				RuntimeDataObject.getController().setVLCdeviceName(vlc_device_name_text.getText());
                             	frame.dispose();
                      	}else{
                        	JOptionPane.showMessageDialog(null,Language.getController().getLangValue("VideoServerConfigure.Message"));
                  	}
		}else if(e.getActionCommand().equals("Cancel_Bttn")){
			frame.dispose();
		}
	}

	

}

