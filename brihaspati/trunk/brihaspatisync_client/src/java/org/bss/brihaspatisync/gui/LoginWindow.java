package org.bss.brihaspatisync.gui;

/**
 * LoginWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ,2013 ETRG,IIT Kanpur.
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.KeyboardFocusManager;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import org.bss.brihaspatisync.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha</a> Modified this class for signalling. 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> testing
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind pal </a> last modified in 
 */

public class LoginWindow extends JInternalFrame implements ActionListener, MouseListener {
	
	private JPanel loginGUIPanel;
	
	private JComboBox indexServerListCombo=null;
	private JComboBox languageListCombo=null;	
	
	
	private JLabel username;
	private JLabel password;
	private JLabel forgetpass;
	private JLabel bottomLabel;
        private JLabel submitLabel;
	private JLabel messagelabel=null;
	private JLabel chooseServerLabel;
	private JLabel chooseLanguageLabel;
	
	private JButton submitButton;
	private JButton cancelButton;

	private JPasswordField passwordField;

	private JTextField usernameText;
	
	private TitledBorder serverInfoBorder;
	private TitledBorder logintitledBorder;
	private TitledBorder advertistitledBorder;
	
	private String userName=null;
	private String languageName=null;
	private String indexServerName=null;
	
	private MainWindow mainWindow=MainWindow.getController();		
	
	//private ClientObject client_obj=ClientObject.getController();
		
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
	private ClassLoader clr= this.getClass().getClassLoader();
	private String[] languages={"English","Hindi","Bhojpuri","Arabic","Greek","Persian","Russian","French","Spanish","Dutch","Nepali","German","Italian","Urdu","Gujarati"}; //"Tamil","Telugu","Japanese","Korean","Bangala","Chinese"

	/**
	 * Constructor detail and 
	 * Create GUI for LoginWindow
	 */	
	protected LoginWindow(){
		super(Language.getController().getLangValue("LoginWindow.Title"),true,false,false,true);
		setFrameIcon(new ImageIcon(clr.getResource("resources/images/login.png")));
		/**
		 * This block of code is use to fixed Logo image in Login Panen .
		 */  
		
		JLabel imageLabel = new JLabel(new ImageIcon(clr.getResource("resources/images/Title.jpg")));
		imageLabel.setFont(new java.awt.Font("Times", 1, 20));
		
    		JPanel logoPane = new JPanel();
		logoPane.add(imageLabel);
		logoPane.setSize(301,200);
		logoPane.setBackground(Color.WHITE);
    			
    		loginGUIPanel=new JPanel();
    		loginGUIPanel.setLayout(new BorderLayout());
    		loginGUIPanel.add(logoPane,BorderLayout.NORTH);
		
		/**
		 * This block of code is used to server panel . 
		 */	
    		JPanel serverInfoPanel =new JPanel();
    		serverInfoPanel.setLayout(new GridLayout(2,2,5,5));
    		serverInfoBorder = BorderFactory.createTitledBorder(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
    		serverInfoPanel.setBorder(serverInfoBorder);

                chooseLanguageLabel=new JLabel(Language.getController().getLangValue("LoginWindow.chooseLanguage"));
                serverInfoPanel.add(chooseLanguageLabel);

		languageListCombo=new JComboBox(languages);
                languageListCombo.setPreferredSize(new Dimension(160,25));
                languageListCombo.addActionListener(this);
                serverInfoPanel.add(languageListCombo);
		chooseServerLabel=new JLabel(Language.getController().getLangValue("LoginWindow.ChooseServer"));
                serverInfoPanel.add(chooseServerLabel);
              
    		indexServerListCombo=new JComboBox(ClientObject.getIndexServerList());
    		indexServerListCombo.addActionListener(this);
    		serverInfoPanel.add(indexServerListCombo);
		
		messagelabel = new JLabel();
	
		/**
		 * This block of code is used to join server panel and login panel . 
		 */
        	JPanel server_login_cimb_Panel=new JPanel();
        	server_login_cimb_Panel.setLayout(new BorderLayout());
           	server_login_cimb_Panel.add(serverInfoPanel,BorderLayout.NORTH);
        	server_login_cimb_Panel.add(createLoginPanel(),BorderLayout.CENTER);   	
   	
    		loginGUIPanel.add(server_login_cimb_Panel,BorderLayout.CENTER);

    		/**
		 * This block of code is used to Add server panel . 
		 */ 	
    		JPanel advertisPanel=new JPanel();
    		advertisPanel.setBackground(Color.LIGHT_GRAY);
    		advertistitledBorder = BorderFactory.createTitledBorder("");
    		advertisPanel.setBorder(advertistitledBorder);
    		bottomLabel=new JLabel(Language.getController().getLangValue("LoginWindow.bottomLabel"));
		advertisPanel.add(bottomLabel);
    		loginGUIPanel.add(advertisPanel,BorderLayout.SOUTH);

		add(loginGUIPanel,BorderLayout.CENTER);
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((((int)dim.getWidth()/2)-180),((int)dim.getHeight()/2)-300);
    		setSize(355,520); 
    		setVisible(true);
		setResizable(false);
  	}
	
	
	public void setMessage(String str){
                messagelabel.setText("<html><blink><Font size=3 color=red><b>"+str+"</b></font></blink></html>");
	}
		
	private JPanel createLoginPanel(){
		
		JPanel mainLoginPanel=new JPanel();
                mainLoginPanel.setLayout(new BorderLayout());
		
		logintitledBorder = BorderFactory.createTitledBorder(Language.getController().getLangValue("LoginWindow.login"));
                mainLoginPanel.setBorder(logintitledBorder);	

                JPanel loginPanel=new JPanel();
                loginPanel.setLayout(new GridLayout(2,3,0,4));
		
               	username =new JLabel(Language.getController().getLangValue("LoginWindow.username"));
                username.setEnabled(false);
                usernameText=new JTextField("guest");
                usernameText.setEnabled(false);
		usernameText.setName("username");
		usernameText.addMouseListener(this);
		
                usernameText.addActionListener(new ActionListener() {
			
                        public void actionPerformed(ActionEvent ae) {
                                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                        }
                });
                password=new JLabel(Language.getController().getLangValue("LoginWindow.password"));
                password.setEnabled(false);
                passwordField=new JPasswordField("guest");
                passwordField.setEnabled(false);
		passwordField.setName("passwd");
		passwordField.addMouseListener(this);
		
                passwordField.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
				StatusPanel.getController().setProcessBar("yes");
				checkUserNamePasswd();
				StatusPanel.getController().setProcessBar("yes");
                        }
                });
		loginPanel.add(username);
                loginPanel.add(usernameText);
                loginPanel.add(password);
                loginPanel.add(passwordField);

                JPanel buttonPanel=new JPanel();
                buttonPanel.setLayout(new FlowLayout());

		submitButton=new JButton(Language.getController().getLangValue("ForgetPassword.SubmitButton"),new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                submitButton.setEnabled(false);
                submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                submitButton.setName("submit.Action");
		submitButton.addMouseListener(this);
                
		cancelButton=new JButton(Language.getController().getLangValue("InstructorCSPanel.Cancel"),new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                cancelButton.setEnabled(false);
                cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cancelButton.setName("cancel.Action");
		cancelButton.addMouseListener(this);
	
                forgetpass=new JLabel("<html><Font color=blue><u>"+Language.getController().getLangValue("LoginWindow.forgetpass")+"</u></font></html>");
		forgetpass.setForeground(Color.BLUE);
                forgetpass.addMouseListener(this);
                forgetpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
                forgetpass.setName("forgetpass.Action");

                buttonPanel.add(forgetpass);
                buttonPanel.add(submitButton);
                buttonPanel.add(cancelButton);
	
                mainLoginPanel.add(loginPanel,BorderLayout.CENTER);
                mainLoginPanel.add(buttonPanel,BorderLayout.SOUTH);
               	return mainLoginPanel;
	}

	private void updateGUI() {
		
		chooseLanguageLabel.setText(Language.getController().getLangValue("LoginWindow.chooseLanguage"));
                chooseServerLabel.setText(Language.getController().getLangValue("LoginWindow.ChooseServer"));
                bottomLabel.setText(Language.getController().getLangValue("LoginWindow.bottomLabel"));
                serverInfoBorder.setTitle(Language.getController().getLangValue("LoginWindow.ServerPanelTitle"));
		logintitledBorder.setTitle(Language.getController().getLangValue("LoginWindow.login"));
		forgetpass.setText(Language.getController().getLangValue("LoginWindow.forgetpass"));
		
                setTitle(Language.getController().getLangValue("LoginWindow.Title"));
		username.setText(Language.getController().getLangValue("LoginWindow.username"));
		password.setText(Language.getController().getLangValue("LoginWindow.password"));
				
		cancelButton.setText(Language.getController().getLangValue("InstructorCSPanel.Cancel"));		
		submitButton.setText(Language.getController().getLangValue("ForgetPassword.SubmitButton"));		
		
                loginGUIPanel.repaint();
		mainWindow.setMenuItemText();
                mainWindow.getContainer().validate();
                mainWindow.getContainer().repaint();	
		StatusPanel.getController().updateGUI();
	}
  	
	private void Update_Enable_Decable(){
		try {
			if(!(indexServerName.equals("Select"))){
                                username.setEnabled(true);
                                usernameText.setEnabled(true);
                                password.setEnabled(true);
                                passwordField.setEnabled(true);
                                submitButton.setEnabled(true);
                                cancelButton.setEnabled(true);
                                username.setFocusable(true);
                        }
                        if(indexServerName.equals("Select")){
                                username.setEnabled(false);
                                usernameText.setEnabled(false);
                                password.setEnabled(false);
                                passwordField.setEnabled(false);
                                submitButton.setEnabled(false);
                                cancelButton.setEnabled(false);
                        }
	
		}catch(Exception e) { System.out.println(this.getClass()+ " "+e.getMessage()); }
	}
  	public void actionPerformed(ActionEvent e) {
  		if(e.getSource()==indexServerListCombo){
			JComboBox combo = (JComboBox)e.getSource();
        		indexServerName=(String)combo.getSelectedItem();
			// set this indexServerName object to ClientObject for later use by this client.
			ClientObject.setIndexServerName(indexServerName);
			Update_Enable_Decable();
      		}else if(e.getSource()==languageListCombo) {
			JComboBox combo = (JComboBox)e.getSource();
                        languageName=(String)combo.getSelectedItem();
			Language.getController().SelectLanguage(languageName);
			if((languageName.equals("English")) ||(languageName.equals("Hindi"))||(languageName.equals("Tamil"))||(languageName.equals("Telugu"))||(languageName.equals("Bhojpuri"))||(languageName.equals("Arabic"))||(languageName.equals("Chinese")) ||(languageName.equals("Greek"))||(languageName.equals("Japanese"))||(languageName.equals("Korean"))||(languageName.equals("Persian"))||(languageName.equals("Russian"))||(languageName.equals("Bangala"))||(languageName.equals("French"))||(languageName.equals("Spanish"))||(languageName.equals("Dutch"))||(languageName.equals("Nepali"))||(languageName.equals("German"))||(languageName.equals("Italian"))) {
				updateGUI();
				return;
                        }
		}
	}   
		
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName().equals("username")) {
			if(passwordField.getText().equals(""))		
				passwordField.setText("guest");
			usernameText.setText("");
		} 
		
		if(e.getComponent().getName().equals("passwd")) {
			if(usernameText.getText().equals(""))            
        	        	usernameText.setText("guest");
              		passwordField.setText("");
                } 
			
                if(e.getComponent().getName().equals("forgetpass.Action")){
			forgetpass.setCursor(busyCursor);
			forgetpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
			ForgetPass.getController();
			forgetpass.setCursor(defaultCursor);
		}

                if(e.getComponent().getName().equals("submit.Action")) {	
			StatusPanel.getController().setProcessBar("yes");
			checkUserNamePasswd();
			StatusPanel.getController().setProcessBar("no");
			submitButton.setCursor(defaultCursor);
                }
		if(e.getComponent().getName().equals("cancel.Action")) {
			cancelButton.setCursor(busyCursor);
                        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        cancelButton.setCursor(defaultCursor);
			usernameText.setText("");
			passwordField.setText("");
                } 
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e)  {}

	private void checkUserNamePasswd() {  
		try {
			if(passwordField.getText().equals(""))
                        	passwordField.setText("guest");
                       	if(usernameText.getText().equals(""))
                        	usernameText.setText("guest");
                     	submitButton.setCursor(busyCursor);
                        if((!(usernameText.getText()).equals(""))) {
                        	boolean loginValue=ClientObject.getAuthentication(indexServerName,usernameText.getText(),passwordField.getText());
				System.out.println(loginValue);
                                if(loginValue==false) {
                                	passwordField.setText("");
                                        setMessage(Language.getController().getLangValue("LoginWindow.MessageDialog1") +"<br>  "+Language.getController().getLangValue("LoginWindow.MessageDialog3"));
                                        StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog1")+" "+Language.getController().getLangValue("LoginWindow.MessageDialog3"));
                                        submitButton.setCursor(defaultCursor);
                           	} else {
                                	ClientObject.setUserName(usernameText.getText());
					mainWindow.setMenuItemText();
		                        mainWindow.getDesktop().removeAll();
					mainWindow.getDesktop().setBackground(new Color(220,220,220));	
					mainWindow.getDesktop().add(new CourseSessionWindow());
					mainWindow.getContainer().add(mainWindow.getDesktop(),BorderLayout.CENTER);
					mainWindow.getContainer().validate();
		                        mainWindow.getContainer().repaint();	
                                        StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog2"));
                             	}
                     	} else
                        	setMessage(Language.getController().getLangValue("LoginWindow.MessageDialog4"));
                       	submitButton.setCursor(defaultCursor);			
		} catch(Exception e) { System.out.println(this.getClass()+ " "+e.getMessage());  }
	}

}
