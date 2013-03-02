package org.iitk.livetv.gui;

/**
 * AddChannel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013 ETRG, IIT Kanpur
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.util.Collections;
import java.util.Vector;
import java.net.InetAddress;
import org.iitk.livetv.util.ClientObject;
import org.iitk.livetv.util.RuntimeObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 25Jan2013 
 */

public class AddChannel extends JFrame implements ActionListener {

	private static AddChannel channel=null;	
	private Container con=null;

	private JPanel mainPanel=null;
	private JPanel centerPanel=null;
	private JPanel bttnPanel=null;

	private JLabel choose_category=null;
	private JComboBox category_box=null;

	private JLabel ch_name=null;
	private JTextField ch_name_text=null;

	private JLabel ch_icon=null;
	private JTextField ch_icon_text=null;
	private JButton ch_icon_bttn=null;

	private JLabel ch_desc=null;
	private JTextArea ch_desc_text=null;

	private JButton submitBttn=null;
	private JButton cancelBttn=null;
	
	private String category_name=null;

	private ClassLoader clr= this.getClass().getClassLoader();
	private ClientObject clientObj=ClientObject.getController();
	
	public static AddChannel getController(){
		if(channel==null){
			channel=new AddChannel();
		}
		return channel;
	}

	public void AddChannel(){}

	public void createGUI(){
		setTitle("Create new channel");
                con=this.getContentPane();

		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		centerPanel=new JPanel();
		TitledBorder centerPanelBorder=BorderFactory.createTitledBorder("");
		centerPanel.setBorder(centerPanelBorder);
		centerPanel.setLayout(new GridBagLayout());
		Insets insets = new Insets(5,10,5,10);
                GridBagConstraints center_gbc = new GridBagConstraints();
		center_gbc.fill=GridBagConstraints.HORIZONTAL;

		center_gbc.insets = insets;

		choose_category=new JLabel("Select category *");
		Vector channelCategory=clientObj.getCategory();
                Collections.sort(channelCategory);
		channelCategory.remove(0);
		category_box=new JComboBox(channelCategory);
		category_name=(String)category_box.getSelectedItem();
		category_box.addActionListener(this);

		center_gbc.gridx=0;
		center_gbc.gridy=0;
		//center_gbc.insets = insets;
		centerPanel.add(choose_category,center_gbc);

		center_gbc.gridx=1;
                center_gbc.gridy=0;
		center_gbc.insets = insets;
		//center_gbc.ipady = 5;
                centerPanel.add(category_box,center_gbc);

		ch_name=new JLabel("Channel name * ");
		ch_name_text=new JTextField(20);
		center_gbc.gridx = 0;
                center_gbc.gridy = 1;
		centerPanel.add(ch_name,center_gbc);
		center_gbc.gridx = 1;
                center_gbc.gridy = 1;
		center_gbc.ipady = 5;
		center_gbc.insets = insets;
		centerPanel.add(ch_name_text,center_gbc);

	/*	ch_icon=new JLabel("Channel Icon *");
		ch_icon_text=new JTextField(20);
		ch_icon_bttn=new JButton("Browse");
		ch_icon_bttn.addActionListener(this);
		ch_icon_bttn.setActionCommand("browse");
		
		center_gbc.gridx = 0;
                center_gbc.gridy = 1;
		center_gbc.insets = insets;
		centerPanel.add(ch_icon,center_gbc);
		center_gbc.gridx = 1;
                center_gbc.gridy = 1;
		center_gbc.ipady = 5;
		center_gbc.insets = insets;
		centerPanel.add(ch_icon_text,center_gbc);
		center_gbc.gridx = 2;
                center_gbc.gridy = 1;	
		center_gbc.insets=new Insets(0,0,0,0);
		centerPanel.add(ch_icon_bttn,center_gbc);
	*/	
		ch_desc=new JLabel("Channel Description");		
		ch_desc_text=new JTextArea(5,20);
		ch_desc_text.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(ch_desc_text);
	 	center_gbc.gridx = 0;
                center_gbc.gridy = 2;
		center_gbc.insets = insets;

		centerPanel.add(ch_desc,center_gbc);
	 	center_gbc.gridx = 1;
                center_gbc.gridy = 2;
		center_gbc.insets = insets;
		centerPanel.add(scroll,center_gbc);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		bttnPanel=new JPanel();
		TitledBorder bttnPanelBorder=BorderFactory.createTitledBorder("");
                bttnPanel.setBorder(bttnPanelBorder);
		bttnPanel.setLayout(new GridBagLayout());
                GridBagConstraints bttn_gbc = new GridBagConstraints();
		bttn_gbc.fill=GridBagConstraints.HORIZONTAL;
//		bttn_gbc.weighty = 2.0;
//              bttn_gbc.weightx = 2.0;


		JButton submitBttn=new JButton("Submit",new ImageIcon(clr.getResource("resources/images/ok.png")));

		submitBttn.addActionListener(this);
                submitBttn.setActionCommand("submit");
		bttn_gbc.gridx=0;
		bttn_gbc.gridy=0;
		bttn_gbc.insets = insets;
		bttnPanel.add(submitBttn,bttn_gbc);

		JButton cancelBttn=new JButton("Cancel",new ImageIcon(clr.getResource("resources/images/quit.png")));

		cancelBttn.addActionListener(this);
                cancelBttn.setActionCommand("cancel");
		bttn_gbc.gridx=1;
                bttn_gbc.gridy=0;
		bttn_gbc.insets = insets;
		bttnPanel.add(cancelBttn,bttn_gbc);

		mainPanel.add(bttnPanel,BorderLayout.SOUTH);

		con.add(mainPanel);	
		setSize(460, 210);
                //setLocation(515,100);
                pack();
		setLocationRelativeTo(null);
                setVisible(true);
                setResizable(true);
	}	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==category_box){
                        JComboBox combo = (JComboBox)e.getSource();
                        category_name=(String)combo.getSelectedItem();

		}else if(e.getActionCommand().equals("submit")){
			String name=ch_name_text.getText();
			String desc=ch_desc_text.getText();

			if(!(name.equals(""))&&(!(category_name.equals("")))){
				String user_id=clientObj.getUserID();
				String ipAddr="";
				try{
					InetAddress ownIP=InetAddress.getLocalHost();
	                                ipAddr=ownIP.getHostAddress();

				}catch(Exception ex){System.out.println("Error in get local ip address"+ex.getMessage());}
				String ch_port=RuntimeObject.getController().getStreamingPort();
				boolean value=clientObj.addChannel(category_name, name, desc, user_id,ipAddr,ch_port);
				if(value==false){
					JOptionPane.showMessageDialog(null, "Channel name already exist!!", "Error in send channel details", JOptionPane.INFORMATION_MESSAGE);

				}else{
					this.dispose();
				}	
			}else{
				JOptionPane.showMessageDialog(null, "Please fill all mandatory fields", "Error in add channel", JOptionPane.INFORMATION_MESSAGE);

			}
					
	
		}else if(e.getActionCommand().equals("cancel")){
			this.dispose();	
		}
	}
}

