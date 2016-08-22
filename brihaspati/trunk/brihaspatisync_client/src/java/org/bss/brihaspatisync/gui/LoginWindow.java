package org.bss.brihaspatisync.gui;
/**
 * LoginWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013,2015,2016 ETRG,IIT Kanpur.
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.KeyboardFocusManager;
import java.net.URLEncoder;
import java.lang.Object;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.SwingWorker;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.DateUtil;
import java.lang.Object;
import javax.swing.JProgressBar;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.Vector;
/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha</a> Modified this class for signalling. 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> update@ swingworker class.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind pal </a> last modified in. 
 * @author <a href="mailto:chetnatrivedi1990@gmail.com">Chetna Trivedi</a> guest verification. 
 */

public class LoginWindow extends JInternalFrame implements ActionListener, MouseListener,PropertyChangeListener{
	
	private JPanel loginGUIPanel;
	//private JFrame myFrame = null;
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
        private String rndm_strng=null;

	private MainWindow mainWindow=MainWindow.getController();		
	
	//private ClientObject client_obj=ClientObject.getController();
		
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
	private ClassLoader clr= this.getClass().getClassLoader();
	private String[] languages={"English","Hindi","Bhojpuri","Arabic","Greek","Persian","Russian","French","Spanish","Dutch","Nepali","German","Italian","Urdu","Gujarati"}; //"Tamil","Telugu","Japanese","Korean","Bangala","Chinese"
	
	private JProgressBar progressBar;
	private JTextArea taskOutput;
        private Task task;
        
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
               
                submitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                                StatusPanel.getController().setProcessBar("yes");
                                checkUserNamePasswd();
                                StatusPanel.getController().setProcessBar("no");
                        }
                });

       
                
		cancelButton=new JButton(Language.getController().getLangValue("InstructorCSPanel.Cancel"),new ImageIcon(clr.getResource("resources/images/user/denie.png")));
                cancelButton.setEnabled(false);
                cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cancelButton.setName("cancel.Action");
	        
               
                cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                                StatusPanel.getController().setProcessBar("no");
                                 usernameText.setText("");
                                 passwordField.setText("");
                                StatusPanel.getController().setProcessBar("no");
                        }
                });

                
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
			if(!indexServerName.equals("Select")){
                                username.setEnabled(true);
                                usernameText.setEnabled(true);
                                password.setEnabled(true);
                                passwordField.setEnabled(true);
                                submitButton.setEnabled(true);
                                cancelButton.setEnabled(true);
                                username.setFocusable(true);
                        } else{
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
	/*	if(e.getComponent().getName().equals("username")) {
			if(passwordField.getText().equals(""))		
                 	usernameText.setText("");
		} 
		
		if(e.getComponent().getName().equals("passwd")) {
			if(usernameText.getText().equals(""))            
        		passwordField.setText("");
                } 

                 if(e.getComponent().getName().equals("guest")) {
                        if(usernameText.getText().equals(""))
                        usernameText.setText("guest");
                    	passwordField.setText("");
                }
	
	*/		
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
                    	submitButton.setCursor(busyCursor);
                        if((usernameText.getText().equals("")) && (passwordField.getText().equals(""))) {
                                StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog3"));
                                submitButton.setCursor(defaultCursor); 
                        }else {
                        	boolean loginValue=ClientObject.getAuthentication(indexServerName,usernameText.getText(),passwordField.getText());
				System.out.println(loginValue);
                                if(loginValue==false) {
                                	passwordField.setText("");
                                        StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog1"));
                        }else {
                               		ClientObject.setUserName(usernameText.getText());
                                        String usr_name=ClientObject.getUserName();
                                       	if(usr_name.equals("guest")) {
                                  	usr_name =""; 
                                        usr_name = javax.swing.JOptionPane.showInputDialog("Enter your emailid");
                                        if (usr_name!=null){
                                     	   if(!usr_name.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")){
                                            	 JOptionPane.showMessageDialog(null,Language.getController().getLangValue("LoginWindow.MessageDialog8"));
                                                 return;
                                           }
                                           else{
                                               		String usr_name1="usr_name="+URLEncoder.encode(usr_name,"UTF-8");
                                                  	String indexServerName=ClientObject.getIndexServerName();
                                                  	String  indexServer=indexServerName+"/ProcessRequest?req=putemail&"+usr_name1;
                                                  	String result=HttpsUtil.getcheckuser(indexServer);
                                                  	if(result.equals("Write succfully")){
                                                  		rndm_strng =javax.swing.JOptionPane.showInputDialog("Enter random number which is send on your email");
                                                  		if (rndm_strng!=null){
                                                                	 String rndm_string="user_rndm_strng="+URLEncoder.encode(rndm_strng,"UTF-8");
                                                                 	 String otptime=DateUtil.getSystemDateTime();
                                                                 	 String otp_send_time="user_otp_time="+URLEncoder.encode(otptime,"UTF-8");
                                                        	         String  indexServername=indexServerName+"/ProcessRequest?req=verifyotp&"+usr_name1+"&"+rndm_string+"&"+otp_send_time;
                                                                 	 boolean value=HttpsUtil.getverifyotp(indexServername);
                                                                         if(value==true){
                                                                 		StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog7"));
                                                                 		task = new Task();
                                                                 		task.addPropertyChangeListener(this);
                                                                	        task.execute();
                                                                 	 }
                                                                 	 else{
                                                                 	     JOptionPane.showMessageDialog (null,"otp not match or otp has been expired","Wrong Random No",JOptionPane.INFORMATION_MESSAGE);
                                                                	 }
                                                  	        }
                                       		        }	
                                                        else{
                                                		JOptionPane.showMessageDialog (null,"u r valid user","Wrong Random No",JOptionPane.INFORMATION_MESSAGE);
                                                	        StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog7"));
                                                		task = new Task();
                                                		task.addPropertyChangeListener(this);
                                                		task.execute();
                                           	 	}	

                                           }
                                        }
                                      }
                                      else{
                                        	StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog7"));
                                                task = new Task();
                                                task.addPropertyChangeListener(this);
                                                task.execute();
                                      }
                         }                      
                        }
                       			
		} catch(Exception e) { System.out.println(this.getClass()+ " "+e.getMessage());}
	}
 
	
	public void propertyChange(PropertyChangeEvent evt) {
                if ("progress" == evt.getPropertyName()) {
                int progress = (Integer) evt.getNewValue();
                progressBar.setValue(progress);
                taskOutput.append(String.format("Completed %d%% of task.\n",task.getProgress()));
                }
        }
	
        public class Task extends SwingWorker<CourseSessionWindow,Void>implements PropertyChangeListener{

		JFrame progressframe = new JFrame("Please Wait....");
 		Task(){
			Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
			progressframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        progressframe.setSize(355,100);
                        progressframe.setVisible(true);
			progressframe.setLocation((((int)dim.getWidth()/2)-102),((int)dim.getHeight()/2)+100);
			progressBar = new JProgressBar(0, 100);
                        progressBar.setValue(0);
                        progressBar.setStringPainted(true);
                        taskOutput = new JTextArea(5, 20);
                        taskOutput.setMargin(new Insets(5,5,5,5));
                        taskOutput.setEditable(false);
                        JPanel panel = new JPanel();
                        panel.add(progressBar);
                        progressframe.add(panel, BorderLayout.PAGE_START);
                        progressframe.add(new JScrollPane(taskOutput), BorderLayout.CENTER);
                        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  		}

		protected CourseSessionWindow doInBackground() throws Exception {
			Random random = new Random();
                        int progress = 0;
                        setProgress(0);
                        while (progress < 100) {
				try {
                                	Thread.sleep(random.nextInt(1000));
                                        progress += random.nextInt(10);
                                	setProgress(Math.min(progress, 100));
                                        CourseSessionWindow csw = new CourseSessionWindow();
                                        return csw;
                                  } catch (Exception e) { System.out.println(e.getMessage());}
			}
			return null;
  		}

		protected void done() {
    			taskOutput.append("Done!\n");
			progressframe.dispose();
    			mainWindow.setMenuItemText();
    			mainWindow.getDesktop().removeAll();
    			mainWindow.getDesktop().setBackground(new Color(220,220,220));	
			try{	
				mainWindow.getDesktop().add(get());
			}catch(Exception e){ System.out.println(e.getMessage());}
			mainWindow.getContainer().add(mainWindow.getDesktop(),BorderLayout.CENTER);
			mainWindow.getContainer().validate();
			mainWindow.getContainer().repaint();	
        		StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog2"));
			Toolkit.getDefaultToolkit().beep();
                	setCursor(null);
		}
	//} /*end guiworkerclass*/

		public void propertyChange(PropertyChangeEvent evt) {
        		if ("progress" == evt.getPropertyName()) {
            		int progress = (Integer) evt.getNewValue();
            		progressBar.setValue(progress);
            		taskOutput.append(String.format("Completed %d%% of task.\n",task.getProgress()));
        		}
    		}
	}
}
