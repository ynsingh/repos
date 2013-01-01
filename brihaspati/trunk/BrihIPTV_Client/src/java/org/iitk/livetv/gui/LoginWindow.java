package org.iitk.livetv.gui;

/**
 * LoginWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.io.File;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.JFrame;
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

import com.sun.jna.Platform;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import org.iitk.livetv.http.HttpCommManager;
import org.iitk.livetv.util.UnPackNativeLibs;
import org.iitk.livetv.util.CheckNativeLibrary;
import org.iitk.livetv.util.ClientObject;
import org.iitk.livetv.util.HttpsUtil;

import java.util.Arrays;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 23Aug2012
 */

public class LoginWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel mainPanel;
	private JPanel panel;
	private JPanel titlePane;
	private JPanel NorthPanel;
	private JPanel NorthPanel1;
	private JPanel NorthPanel_new;
	private JPanel langPanel;
	private JPanel centerLoginPanel;
	private JPanel SouthPanel;
	private JPanel mainBttnPanel=null;
	private JPanel northBttnPanel=null;


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
	private JLabel newUserRegistration;
	private  JLabel loginStatus=null;
	private JPasswordField passwordField;
	private JTextField usernameText;

	private TitledBorder optionTitle;
	private TitledBorder loginTitle;

	private String indexServerName=null;
	private String languageName=null;
	private String userName=null;

	private JMenuBar menuBar=null;
        private JMenu livetv_menu=null;
        private JMenu option_menu=null;
        private JMenu help_menu=null;
        private JMenuItem quitMenuItem=null;
        private JMenuItem addchannelMenuItem=null;
        private JMenuItem connMenuItem=null;
        private JMenuItem aboutMenuItem=null;


	private JPanel mainLoginPanel;
        //private JPanel loginPanel1;
	private JLabel label1=null;
        private boolean loginFlag=false;
	private boolean loginValue=false;
	private Container content=null;
	private Dimension dim=null;
	private ClientObject client_obj=ClientObject.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private ClassLoader clr= this.getClass().getClassLoader();
	private String[] languages={"English","Hindi","Bhojpuri","Arabic","Greek","Persian","Russian","French","Spanish","Dutch","Nepali","German","Italian"};
	private static LoginWindow login=null;

	/**
 	 * Controller for class.
 	 **/
        public static LoginWindow getController(){
                if (login==null){
                        login=new LoginWindow();
                }
                return login;
        }

	/**
	 * Constructor detail
	 */
	private LoginWindow(){
		createGUI();
	}

	/**
	 * Create GUI for LoginWindow
	 */
	private void createGUI(){

		content = getContentPane();
                setTitle("LiveTV");
		setIconImage((new ImageIcon(clr.getResource("resources/images/title_icon.png"))).getImage());
		setJMenuBar(createMenu());

		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		titlePane = new JPanel();
                JLabel imageLabel = new JLabel(new ImageIcon(clr.getResource("resources/images/banner1.jpg")));
                titlePane.add(imageLabel);
		mainPanel.add(titlePane, BorderLayout.NORTH);

		JPanel center_mainPanel= new JPanel();
		center_mainPanel.setLayout(new BorderLayout());
		center_mainPanel.add(createOptionPanel(),BorderLayout.NORTH);
		center_mainPanel.add(createLoginPanel(),BorderLayout.CENTER);

		mainPanel.add(center_mainPanel,BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(),BorderLayout.SOUTH);

		content.add(mainPanel);
		dim=Toolkit.getDefaultToolkit().getScreenSize();
                setLocation((((int)dim.getWidth()/2)-200),(((int)dim.getHeight()/2)-350));
                setSize(410,530);
                setVisible(true);
		addWindowListener(new WindowAdapter() {
            		public void windowClosing(WindowEvent evt) {
                		System.exit(0);
            		}
        	});
                setResizable(false);
	}

	private JMenuBar createMenu(){
		menuBar = new JMenuBar();
		livetv_menu = new JMenu("LiveTV");

		quitMenuItem = new JMenuItem("Quit",new ImageIcon(clr.getResource("resources/images/quit.png")));
		quitMenuItem.addActionListener(this);
		quitMenuItem.setActionCommand("quit");
		livetv_menu.add(quitMenuItem);

		option_menu = new JMenu("Option");

		addchannelMenuItem = new JMenuItem("Add Channel",new ImageIcon(clr.getResource("resources/images/add-channel.png")));
                addchannelMenuItem.addActionListener(this);
                addchannelMenuItem.setActionCommand("addchannel");
		addchannelMenuItem.setEnabled(false);
                option_menu.add(addchannelMenuItem);


		connMenuItem = new JMenuItem("Connection Setting",new ImageIcon(clr.getResource("resources/images/connection.png")));
		connMenuItem.addActionListener(this);
		connMenuItem.setActionCommand("connection");
		option_menu.add(connMenuItem);


		help_menu = new JMenu("Help");
		aboutMenuItem = new JMenuItem("About LiveTV",new ImageIcon(clr.getResource("resources/images/about.png")));
		aboutMenuItem.addActionListener(this);
		aboutMenuItem.setActionCommand("about");
		help_menu.add(aboutMenuItem);

		menuBar.add(livetv_menu);
		menuBar.add(option_menu);
		menuBar.add(help_menu);
		return menuBar;
	}

	private JPanel createOptionPanel(){
		JPanel optionPanel=new JPanel();
		optionPanel.setLayout(new GridBagLayout());
               	GridBagConstraints gbc = new GridBagConstraints();
		Insets insets = new Insets(5,10,5,10);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets = insets;

		chooseLanguageLabel=new JLabel("Select language");
		gbc.gridx = 0;
                gbc.gridy = 0;
                optionPanel.add(chooseLanguageLabel,gbc);

                languageListCombo=new JComboBox(languages);
                languageListCombo.addActionListener(this);
		gbc.gridx = 1;
                gbc.gridy = 0;
                optionPanel.add(languageListCombo,gbc);

                chooseServerLabel=new JLabel("Select server");
		gbc.gridx = 0;
                gbc.gridy = 1;
                optionPanel.add(chooseServerLabel,gbc);

                indexServerListCombo=new JComboBox(client_obj.getIndexServerList());
                indexServerListCombo.addActionListener(this);
		gbc.gridx = 1;
                gbc.gridy = 1;
                optionPanel.add(indexServerListCombo,gbc);
		return optionPanel;

	}

	private JPanel createLoginPanel(){
		mainLoginPanel=new JPanel();
                mainLoginPanel.setLayout(new BorderLayout());

                centerLoginPanel=new JPanel();
                centerLoginPanel.setLayout(new BorderLayout());
                loginTitle = BorderFactory.createTitledBorder("");
                centerLoginPanel.setBorder(loginTitle);

		loginStatus=new JLabel("");
                centerLoginPanel.add(loginStatus,BorderLayout.NORTH);

                loginPanel=new JPanel();
                loginPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
                Insets insets = new Insets(0,10,0,10);
		gbc.weighty = 1.0;
                gbc.weightx = 1.0;
                gbc.insets = insets;
                gbc.fill=GridBagConstraints.HORIZONTAL;

                username =new JLabel("Enter your email-id");
                username.setEnabled(false);
                usernameText=new JTextField();
                usernameText.setEnabled(false);

                usernameText.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                        }
                });
		gbc.gridx = 0;
                gbc.gridy = 0;
		loginPanel.add(username,gbc);

		gbc.ipady = 10;
		gbc.gridx = 0;
                gbc.gridy = 1;
                loginPanel.add(usernameText,gbc);

                password=new JLabel("Password");
                password.setEnabled(false);
                passwordField=new JPasswordField();
                passwordField.setEnabled(false);
                passwordField.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                        }
                });
		gbc.gridx = 0;
                gbc.gridy = 2;
                loginPanel.add(password,gbc);

		gbc.ipady = 10;
		gbc.gridx = 0;
                gbc.gridy = 3;
                loginPanel.add(passwordField,gbc);

                centerLoginPanel.add(loginPanel,BorderLayout.CENTER);

                mainLoginPanel.add(centerLoginPanel,BorderLayout.CENTER);

               return mainLoginPanel;

	}

	private JPanel createButtonPanel(){

		JPanel mainBttnPanel=new JPanel();
		mainBttnPanel.setLayout(new BorderLayout());

		northBttnPanel =new JPanel();
		northBttnPanel.setLayout( new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);

                submitButton=new JButton("Sign in",new ImageIcon(clr.getResource("resources/images/ok.png")));
                submitButton.setEnabled(false);
                submitButton.addActionListener(this);
                submitButton.setActionCommand("signin_action");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.insets = insets;
		northBttnPanel.add(submitButton,gbc);

	        forgetpass=new JLabel("<html><font color=blue><u>Forget your password?</u></font></html>");
                forgetpass.addMouseListener(this);
                forgetpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
                forgetpass.setName("forgetpass.Action");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.insets = insets;
                northBttnPanel.add(forgetpass,gbc);

		newUserRegistration=new JLabel("<html><font color=blue><u>New user registration</u></font></html>");
		newUserRegistration.addMouseListener(this);
                newUserRegistration.setCursor(new Cursor(Cursor.HAND_CURSOR));
                newUserRegistration.setName("newuser.Action");

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.insets = insets;
                northBttnPanel.add(newUserRegistration,gbc);

		mainBttnPanel.add(northBttnPanel,BorderLayout.CENTER);
		return mainBttnPanel;
	}

	private void updateLoginPanel(){
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

		}catch(Exception e){}
	}

	public void actionPerformed(ActionEvent e) {

  		if(e.getSource()==indexServerListCombo){
			JComboBox combo = (JComboBox)e.getSource();
        		indexServerName=(String)combo.getSelectedItem();
			updateLoginPanel();
      		}else if(e.getSource()==languageListCombo) {

    		}else if(e.getActionCommand().equals("signin_action")){
			loginStatus.setText("");
			if( (!(usernameText.getText()).equals(""))) {
	                        loginValue=client_obj.getAuthentication(indexServerName,usernameText.getText(),passwordField.getText());

				if(loginValue==false){
					usernameText.setText("");
					passwordField.setText("");
					usernameText.setCursor(defaultCursor);
					loginStatus.setText("<html><blink><Font size=3 color=red><b>"+" Username and Password doesn't match!!"+"</b></font></blink></html>");
					setSize(360,570);
				}else {
					client_obj.setUserName(usernameText.getText());
					client_obj.setIndexServerName(indexServerName);// set this indexServerName object to ClientObject for later use by this client.

					if(client_obj.getCategory()!=null){
						if(CheckNativeLibrary.getController().getNativeLibrary()){
							content.removeAll();
							content.add(LiveTVMainPanel.getController().createGUI());
							setResizable(true);
							setSize(((int)dim.getWidth()-800),((int)dim.getHeight()-500));
							setLocation((((int)dim.getWidth()/3)-200),(((int)dim.getHeight()/3)-200));
							validate();
                      					repaint();
							addchannelMenuItem.setEnabled(true);
						}else
							System.out.println("Error in checknativeLibrary");
					}else
						System.out.println("Error : Category list is null");

            	}
			}else
			System.out.println("Error in Sign in action");

    		}else if(e.getActionCommand().equals("quit")){
			System.exit(0);
		}else if(e.getActionCommand().equals("addchannel")){

    		}else if(e.getActionCommand().equals("connection")){
			new PreferenceWindow();
    		}else if(e.getActionCommand().equals("about")){
			new AboutusWindow();
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
             	else if(e.getComponent().getName().equals("newuser.Action")){

		}else
			System.out.println("oop!! Incorrect Action in LoginWindow");
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
}
