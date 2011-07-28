package org.bss.brihaspatisync.gui;

/**
 * LoginWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.network.Log;
import javax.swing.JOptionPane;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha</a> Modified this class for signalling. 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class LoginWindow extends JInternalFrame implements ActionListener, MouseListener {	

	private JPanel mainPanel;
	private JPanel panel;
	private JPanel titlePane;
	private JPanel NorthPanel;
	private JPanel langPanel;
	private JPanel CenterPanel;
	private JPanel SouthPanel;	
	
	private JComboBox indexServerListCombo=null;
	private JComboBox languageListCombo=null;	
	
	private JPanel loginPanel;
	private JPanel buttonPanel;
	private JLabel chooseServerLabel;
	private JLabel chooseLanguageLabel;
	private JLabel username;
	private JLabel password;
	private JLabel bottomLabel;
	private JButton submitButton;
	private JButton cancelButton;
	private JLabel forgetpass;
	private JPasswordField passwordField;
	private JTextField usernameText;
	private TitledBorder titledBorder1;
	private TitledBorder titledBorder2;
	private TitledBorder titledBorder3;
	private String indexServerName=null;
	private String languageName=null;

   	private JMenuItem menuItem1;
	private String userName=null;
	private JPanel mainLoginPanel;
        private JPanel loginPanel1;
        private JLabel submitLabel;
        private JLabel cancelLabel;
        private boolean loginFlag=false;
	private boolean loginValue=false;

	private MainWindow mainWindow=MainWindow.getController();		
	
	private ClientObject client_obj=ClientObject.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private java.util.Vector languages = new java.util.Vector();
	private ClassLoader clr= this.getClass().getClassLoader();
	
	/**
	 * Constructor detail
	 */
	public LoginWindow(){
		super(Language.getController().getLangValue("LoginWindow.Title"),true,false,true,true);
		createGUI();
		setFrameIcon(new ImageIcon(clr.getResource("resources/images/login.png")));
	}
	
	/**
	 * Create GUI for LoginWindow
	 */	
	protected void createGUI(){
		languages.add("English");
		languages.add("Greek");
		languages.add("Korean");
		//languages.add("Hindi");
    		panel=new JPanel();
    		panel.setLayout(new BorderLayout());
    		titlePane = new JPanel();

		JLabel imageLabel = new JLabel(new ImageIcon(clr.getResource("resources/images/Title.jpg")));

		imageLabel.setFont(new java.awt.Font("Times", 1, 20));
		titlePane.add(imageLabel);
		titlePane.setSize(301,200);
		titlePane.setBackground(Color.WHITE);
		titlePane.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED), BorderFactory
				.createLineBorder(new Color(0, 150, 150), 1)));
    	
    		panel.add(titlePane,BorderLayout.NORTH);
    
        	mainPanel=new JPanel();
        	mainPanel.setLayout(new BorderLayout());
    
    		NorthPanel =new JPanel();
    		NorthPanel.setLayout(new GridLayout(2,2,5,5));
    		titledBorder1 = BorderFactory.createTitledBorder(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
    		NorthPanel.setBorder(titledBorder1);
		
                chooseLanguageLabel=new JLabel(Language.getController().getLangValue("LoginWindow.chooseLanguage"));
                NorthPanel.add(chooseLanguageLabel);

		languageListCombo=new JComboBox(languages);
                languageListCombo.setPreferredSize(new Dimension(160,25));
                languageListCombo.addActionListener(this);
                NorthPanel.add(languageListCombo);
		chooseServerLabel=new JLabel(Language.getController().getLangValue("LoginWindow.ChooseServer"));
                NorthPanel.add(chooseServerLabel);
              
    		indexServerListCombo=new JComboBox(client_obj.getIndexServerList());
    		indexServerListCombo.addActionListener(this);
    		NorthPanel.add(indexServerListCombo);

           	mainPanel.add(NorthPanel,BorderLayout.NORTH);

        	mainPanel.add(createLoginPanel(),BorderLayout.CENTER);   	
   	
    		panel.add(mainPanel,BorderLayout.CENTER);
    	
    		SouthPanel=new JPanel();
    		SouthPanel.setBackground(Color.LIGHT_GRAY);
    		titledBorder3 = BorderFactory.createTitledBorder("");
    		SouthPanel.setBorder(titledBorder3);
    		bottomLabel=new JLabel(Language.getController().getLangValue("LoginWindow.bottomLabel"));
    		SouthPanel.add(bottomLabel);
    	
    		panel.add(SouthPanel,BorderLayout.SOUTH);
    	
		add(panel,BorderLayout.CENTER);
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((((int)dim.getWidth()/2)-185),(((int)dim.getHeight()/2)-220));
    		setSize(355,450); 
    		setVisible(true);
		//setResizable(false);
  	}

	protected JPanel createLoginPanel(){

		mainLoginPanel=new JPanel();
                mainLoginPanel.setLayout(new BorderLayout());

                CenterPanel=new JPanel();
                CenterPanel.setLayout(new BorderLayout());
                titledBorder2 = BorderFactory.createTitledBorder(Language.getController().getLangValue("LoginWindow.login"));
                CenterPanel.setBorder(titledBorder2);


                loginPanel1=new JPanel();
                loginPanel1.setLayout(new GridLayout(2,3,0,4));
               	username =new JLabel(Language.getController().getLangValue("LoginWindow.username"));


                username.setEnabled(false);
                usernameText=new JTextField();
                usernameText.setEnabled(false);

                usernameText.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
				System.out.println("");
                                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                        }
                });
                password=new JLabel(Language.getController().getLangValue("LoginWindow.password"));
                password.setEnabled(false);
                passwordField=new JPasswordField();
                passwordField.setEnabled(false);
                passwordField.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
				usernameText.setCursor(busyCursor);
				passwordField.setCursor(busyCursor);
				mainLoginPanel.setCursor(busyCursor);
				panel.setCursor(busyCursor);
				mainWindow.getController().setCursor(busyCursor);
				loginValue=client_obj.getAuthentication(indexServerName,usernameText.getText(),passwordField.getText());			
				try{
						Thread.sleep(1000);
				}catch(InterruptedException ie){
					usernameText.setCursor(defaultCursor);
					mainWindow.getController().setCursor(defaultCursor);
				}finally{
					usernameText.setCursor(defaultCursor);
			                mainWindow.getController().setCursor(defaultCursor);
				}
						
                        	if(loginValue==false){
                                	usernameText.setText("");
                                	passwordField.setText("");
					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("LoginWindow.MessageDialog1"), "Message", JOptionPane.ERROR_MESSAGE);
				}else {
                                	System.out.println("Login Successful");
					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("LoginWindow.MessageDialog2"));
					client_obj.setUserName(usernameText.getText());
					if(((client_obj.getStudSessionList())!=null)||((client_obj.getInstSessionList())!=null)){
						mainWindow.getMenuItem4().setEnabled(true);
	                                        mainWindow.getDesktop().add(CourseSessionWindow.getController());
        	                                setVisible(false);
                	                }
                        	}
                               	KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                        }
                });
		loginPanel1.add(username);
                loginPanel1.add(usernameText);
                loginPanel1.add(password);
                loginPanel1.add(passwordField);

                CenterPanel.add(loginPanel1,BorderLayout.CENTER);

                buttonPanel=new JPanel();
                buttonPanel.setLayout(new FlowLayout());
                /*submitButton=new JButton("Submit");
                submitButton.setEnabled(false);
                submitButton.addActionListener(this);
                cancelButton=new JButton("Cancel");
                cancelButton.setEnabled(false);
                cancelButton.addActionListener(this);
                */

		ClassLoader clr= this.getClass().getClassLoader();
                submitLabel=new JLabel(new ImageIcon(clr.getResource("resources/images/submit.jpg")));
                submitLabel.setEnabled(false);
	        submitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                submitLabel.addMouseListener(this);
                submitLabel.setName("submit.Action");
                cancelLabel=new JLabel(new ImageIcon(clr.getResource("resources/images/cancle.jpg")));
                cancelLabel.setEnabled(false);
                cancelLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cancelLabel.addMouseListener(this);
                cancelLabel.setName("cancle.Action");

                forgetpass=new JLabel("<html><font color=blue><u>"+Language.getController().getLangValue("LoginWindow.forgetpass")+"</u></font></html>");
                forgetpass.addMouseListener(this);
                forgetpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
                forgetpass.setName("forgetpass.Action");

                buttonPanel.add(forgetpass);
                buttonPanel.add(submitLabel);//Button);
                buttonPanel.add(cancelLabel);//Button);
                CenterPanel.add(buttonPanel,BorderLayout.SOUTH);

                mainLoginPanel.add(CenterPanel,BorderLayout.CENTER);

               return mainLoginPanel;
	}
  	
  	public void actionPerformed(ActionEvent e) {
  	
  		if(e.getSource()==indexServerListCombo){
      			JComboBox combo = (JComboBox)e.getSource();
        		indexServerName=(String)combo.getSelectedItem();
			// set this indexServerName object to ClientObject for later use by this client.
			client_obj.setIndexServerName(indexServerName);
        		if(!(indexServerName.equals("Select"))){
        			username.setEnabled(true);
        			usernameText.setEnabled(true);
        			password.setEnabled(true);
        			passwordField.setEnabled(true);
        			submitLabel.setEnabled(true);
        			cancelLabel.setEnabled(true);
        			username.setFocusable(true);
           		}
        		if(indexServerName.equals("Select")){
        			username.setEnabled(false);
        			usernameText.setEnabled(false);
	        		password.setEnabled(false);
        			passwordField.setEnabled(false);
        			submitLabel.setEnabled(false);
        			cancelLabel.setEnabled(false);
	           	}
      		}else if(e.getSource()==languageListCombo) {
			JComboBox combo = (JComboBox)e.getSource();
                        languageName=(String)combo.getSelectedItem();
			Language.getController().SelectLanguage(languageName);

			if(languageName.equals("English")){
				
				try {
					chooseLanguageLabel.setText(Language.getController().getLangValue("LoginWindow.chooseLanguage"));
					chooseServerLabel.setText(Language.getController().getLangValue("LoginWindow.ChooseServer"));
					bottomLabel.setText(Language.getController().getLangValue("LoginWindow.bottomLabel"));
					titledBorder1.setTitle(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
					setTitle(Language.getController().getLangValue("LoginWindow.Title"));
					mainPanel.remove(1);
                                        mainPanel.add(createLoginPanel(),BorderLayout.CENTER);
                                        mainPanel.revalidate();
                                        mainPanel.validate();
                                        panel.repaint();
					mainWindow.setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
					mainWindow.setMenuText();
					mainWindow.setMenuItemText();

				}catch(Exception ex){}
				return;
			} else if(languageName.equals("Greek")){
				try {
					chooseLanguageLabel.setText(Language.getController().getLangValue("LoginWindow.chooseLanguage"));    
			        	chooseServerLabel.setText(Language.getController().getLangValue("LoginWindow.ChooseServer"));
					bottomLabel.setText(Language.getController().getLangValue("LoginWindow.bottomLabel"));
                                        titledBorder1.setTitle(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
                                        setTitle(Language.getController().getLangValue("LoginWindow.Title"));
					mainPanel.remove(1);
					mainPanel.add(createLoginPanel(),BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.validate();
					panel.repaint();
					mainWindow.setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
					mainWindow.setMenuText();
                                        mainWindow.setMenuItemText();
		                }catch(Exception ex){}
				return;
			}else if(languageName.equals("Korean")){
                                try {
                                        chooseLanguageLabel.setText(Language.getController().getLangValue("LoginWindow.chooseLanguage"));
                                        chooseServerLabel.setText(Language.getController().getLangValue("LoginWindow.ChooseServer"));
                                        bottomLabel.setText(Language.getController().getLangValue("LoginWindow.bottomLabel"));
                                        titledBorder1.setTitle(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
                                        setTitle(Language.getController().getLangValue("LoginWindow.Title"));
                                        mainPanel.remove(1);
                                        mainPanel.add(createLoginPanel(),BorderLayout.CENTER);
                                        mainPanel.revalidate();
                                        mainPanel.validate();
                                        panel.repaint();
                                    	mainWindow.setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
                                     	mainWindow.setMenuText();
                                	mainWindow.setMenuItemText();
                            	}catch(Exception ex){}
                                        return;
                    	}
    		}
	}   
		
	public void mouseClicked(MouseEvent e) {
                 if(e.getComponent().getName().equals("forgetpass.Action")){
			forgetpass.setCursor(busyCursor);
			try{
				Thread.sleep(500);
			}catch(InterruptedException ie){
				forgetpass.setCursor(defaultCursor);
			}finally{
				forgetpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			ForgetPass.getController();
		}
                 else if(e.getComponent().getName().equals("submit.Action")){
			submitLabel.setCursor(busyCursor);
                        loginValue=client_obj.getAuthentication(indexServerName,usernameText.getText(),passwordField.getText());
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				submitLabel.setCursor(defaultCursor);
			}finally{
				submitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			if(loginValue==false){
                        	usernameText.setText("");
                        	passwordField.setText("");
				JOptionPane.showMessageDialog(null,Language.getController().getLangValue("LoginWindow.MessageDialog1"), "Message", JOptionPane.ERROR_MESSAGE);
			}else {
				//System.out.println("Login Successfull");
				//JOptionPane.showMessageDialog(null,Language.getController().getLangValue("LoginWindow.MessageDialog2"));
				//System.out.println("Login Successfull");
				//JOptionPane.showMessageDialog(null,"Login Successfull");
				client_obj.setUserName(usernameText.getText());
				if(((client_obj.getStudSessionList())!=null)||((client_obj.getInstSessionList())!=null)){
					mainWindow.getMenuItem4().setEnabled(true);
                                	mainWindow.getDesktop().add(CourseSessionWindow.getController());
                                	setVisible(false);
				}
			}
			
                 }
                 else if(e.getComponent().getName().equals("cancle.Action")){
			cancelLabel.setCursor(busyCursor);
			try{
                                Thread.sleep(500);
                        }catch(InterruptedException ie){
                                cancelLabel.setCursor(defaultCursor);
                        }finally{
                                cancelLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }
			Logout.getController().sendLogoutRequest();
			
                 }else 
			System.out.println("oop!! Incorrect Action in LoginWindow");
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}


	protected JComboBox getIndexServerListCombo(){
		return indexServerListCombo;
	}
        protected JTextField getUserNameText(){
                return usernameText;
        }

        protected JLabel getPassword(){
                return password;
        }
        protected JPasswordField getPasswordField(){
                return passwordField;
        }
	

}
