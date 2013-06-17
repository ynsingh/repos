package org.bss.brihaspatisync.gui;

/**
 * ProxyAuthenticator.java
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
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class ProxyAuthenticator implements ActionListener {

	private static ProxyAuthenticator pa=null;
	private JFrame frame;
        private JPanel main_panel;
        private JPanel north_panel;
        private JPanel center_panel;
        private JPanel south_panel;
        private JLabel textLabel,name_label,pass_label;
        private JTextField usr_text;
        private JPasswordField usr_pass;
        private JButton ok_bttn,cancel_bttn;


	public static ProxyAuthenticator getController(){
		if(pa==null)
			pa=new ProxyAuthenticator();
		return pa;
	}

	public void createGUI(){
		frame=new JFrame();
                frame.setTitle(Language.getController().getLangValue("ProxyAuthenticator.Title"));
                main_panel=new JPanel();
                main_panel.setLayout(new BorderLayout());

                north_panel=new JPanel();
                textLabel=new JLabel(Language.getController().getLangValue("ProxyAuthenticator.JLabel11")+RuntimeDataObject.getController().getProxyHost()+":"+RuntimeDataObject.getController().getProxyPort()+Language.getController().getLangValue("ProxyAuthenticator.JLabel"));
                north_panel.add(textLabel);
                main_panel.add(north_panel,BorderLayout.NORTH);

                center_panel=new JPanel();
                name_label=new JLabel(Language.getController().getLangValue("ProxyAuthenticator.ProxyUser"));
                usr_text=new JTextField(30);
                pass_label=new JLabel(Language.getController().getLangValue("ProxyAuthenticator.ProxyPass"));
                usr_pass=new JPasswordField(30);
                center_panel.add(name_label);
                center_panel.add(usr_text);
                center_panel.add(pass_label);
                center_panel.add(usr_pass);
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
                frame.setSize(500,270);
		java.awt.Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((((int)dim.getWidth()/2)-250),(((int)dim.getHeight()/2)-76));
                frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
       	
		if(e.getActionCommand().equals("OK_Bttn")){
			if(!((usr_text.getText()).equals("")) && (!(usr_pass.getText()).equals(""))){
                        	RuntimeDataObject.getController().setProxyUser(usr_text.getText());
                              	RuntimeDataObject.getController().setProxyPass(usr_pass.getText());
                             	frame.dispose();
                      	}else{
                        	JOptionPane.showMessageDialog(null,Language.getController().getLangValue("ProxyAuthenticator.MessageDialog1"));
                  	}
		}else if(e.getActionCommand().equals("Cancel_Bttn")){
			frame.dispose();
			createGUI();
		}
	}

	

}

