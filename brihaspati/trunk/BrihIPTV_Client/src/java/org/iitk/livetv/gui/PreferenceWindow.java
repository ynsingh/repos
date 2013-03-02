package org.iitk.livetv.gui;

/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012-2013 ETRG,IIT Kanpur.
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.io.InputStream;
import java.io.File;
import javax.swing.JOptionPane;
import org.iitk.livetv.util.HttpsUtil;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */
 
public class PreferenceWindow extends JFrame implements ActionListener{
	
	private Container con=null;
	private JSplitPane splitPane;
	private JPanel mainPanel;
	private boolean connFlag=false;
	private static PreferenceWindow preWin=null;
	protected JPanel window_mainPanel;
	private JPanel rbttnPanel;
	private JPanel centerPanel;
	private JPanel proxyPanel1;
	private JPanel proxyPanel2;
	private JPanel bttnPanel;
	private ButtonGroup bttngroup;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private TitledBorder titledBorder;
	private JLabel proxyhost;
  	private JTextField proxyhosttext;
  	private JLabel proxyport;
  	private JTextField proxyporttext;
  	private JLabel proxyuser;
  	private JTextField proxyusertext;
  	private JLabel proxypass;
  	private JPasswordField proxypasstext;
  	private JButton appbttn;
  	private JButton cancelbttn;
	private Boolean str=false;
	private int netType;
	private Properties prop;
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private ClassLoader clr= this.getClass().getClassLoader();


	public static PreferenceWindow getController(){
		if (preWin==null){
			preWin=new PreferenceWindow();
		}
		return preWin;
	}

	public PreferenceWindow(){
		createWindow();
	}
  	
	protected void createWindow(){
    	   	setTitle("Connection Settings");
    		con=this.getContentPane();
		window_mainPanel=new JPanel();
		window_mainPanel.setLayout(new BorderLayout());
  				
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("Connectin Settings",createTabPane());
		window_mainPanel.add(tabPane,BorderLayout.CENTER);
		con.add(window_mainPanel); 
		pack();
                setLocationRelativeTo(null);
    		setSize(470,290 );
    		setVisible(true);
    		setResizable(true);
    	}

	public Properties getProperties(){
		try{
			prop=new Properties();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/conf/preference.properties");
            		prop.load(inputStream);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error on loading preference properties file");
		}
		return prop;
	}

	/**
	 * Creating Tabbed Pane For Connection Setting Window.
	 */
	protected JPanel createTabPane(){
		
		mainPanel=new JPanel();
  		mainPanel.setLayout(new BorderLayout());

		centerPanel=new JPanel();
        	centerPanel.setLayout(new BorderLayout());

		proxyPanel1=new JPanel();
	        proxyPanel1.setLayout(new GridBagLayout());
                Insets insets = new Insets(5,5,5,5);
                GridBagConstraints proxyPanel1_gbc = new GridBagConstraints();
                proxyPanel1_gbc.fill=GridBagConstraints.HORIZONTAL;

		proxyPanel2=new JPanel();
        	proxyPanel2.setLayout(new GridBagLayout());
                GridBagConstraints proxyPanel2_gbc = new GridBagConstraints();
                proxyPanel2_gbc.fill=GridBagConstraints.HORIZONTAL;

  		
  		rbttnPanel=new JPanel();
  		rbttnPanel.setLayout(new GridLayout(3,1,1,1));
  		proxyhost= new JLabel("Http Proxy");
		proxyhosttext=new JTextField(20);	
		proxyport= new JLabel("Port");
		
		//SpinnerModel model1 = new SpinnerNumberModel(8080,1025,10000,1);
    		//JSpinner spinner = new JSpinner(model1);
		proxyporttext=new JTextField(8);

		proxyuser= new JLabel("User name");
        	proxyusertext=new JTextField(25);
	        proxypass= new JLabel("Password");
        	proxypasstext=new JPasswordField(25);

  		bttngroup= new ButtonGroup();

 		netType=Integer.parseInt(getProperties().getProperty("Type"));
  		
  		if(netType==1){
  			rb1= new JRadioButton("No Proxy", true);
		//	rb1.addActionListener(this);
        	//  	rb1.setActionCommand("rb1");

			rb3= new JRadioButton("Use Proxy", false);
			proxyhost.setEnabled(false);
			proxyhosttext.setEditable(false);
			proxyport.setEnabled(false);
			proxyporttext.setEditable(false);
			proxyuser.setEnabled(false);
			proxyusertext.setEditable(false);
			proxypass.setEnabled(false);
			proxypasstext.setEditable(false);

		} else {

			rb1= new JRadioButton("No Proxy", false);
			rb3= new JRadioButton("Use Proxy", true);
		//	rb3.addActionListener(this);
        	//  	rb3.setActionCommand("rb3");

			String host=getProperties().getProperty("ProxyHost");
			String port=getProperties().getProperty("ProxyPort");
			String user=getProperties().getProperty("ProxyUser");
			String pass=getProperties().getProperty("ProxyPass");

            		proxyhost.setEnabled(true);
			proxyhosttext.setEditable(true);
			proxyhosttext.setText(host);

			proxyport.setEnabled(true);
			proxyporttext.setEditable(true);
			proxyporttext.setText(port);

			proxyuser.setEnabled(true);
			proxyusertext.setEditable(true);
			proxyusertext.setText(user);

			proxypass.setEnabled(true);
			proxypasstext.setEditable(true);
			proxypasstext.setText(pass);
		}
		rb1.addActionListener(this);
            	rb1.setActionCommand("rb1");
	
		rb3.addActionListener(this);
            	rb3.setActionCommand("rb3");

		bttngroup.add(rb1);
               	bttngroup.add(rb3);
               	rbttnPanel.add(rb1);
               	rbttnPanel.add(rb3);
               
		titledBorder = BorderFactory.createTitledBorder("Proxy Authentication");
       		centerPanel.setBorder(titledBorder);

		proxyPanel1_gbc.gridx = 0;
                proxyPanel1_gbc.gridy = 0;
		proxyPanel1_gbc.insets = insets;
		proxyPanel1.add(proxyhost,proxyPanel1_gbc);
		proxyPanel1_gbc.gridx = 1;
                proxyPanel1_gbc.gridy = 0;
               	proxyPanel1.add(proxyhosttext,proxyPanel1_gbc);
		proxyPanel1_gbc.gridx = 2;
                proxyPanel1_gbc.gridy = 0;
               	proxyPanel1.add(proxyport,proxyPanel1_gbc);
		proxyPanel1_gbc.gridx = 3;
                proxyPanel1_gbc.gridy = 0;
               	proxyPanel1.add(proxyporttext,proxyPanel1_gbc);

               	centerPanel.add(proxyPanel1,BorderLayout.NORTH);

		proxyPanel2_gbc.gridx = 0;
                proxyPanel2_gbc.gridy = 1;
		proxyPanel2_gbc.insets = insets;
               	proxyPanel2.add(proxyuser,proxyPanel2_gbc);
		proxyPanel2_gbc.gridx = 1;
                proxyPanel2_gbc.gridy = 1;
               	proxyPanel2.add(proxyusertext,proxyPanel2_gbc);
		proxyPanel2_gbc.gridx = 0;
                proxyPanel2_gbc.gridy = 2;
               	proxyPanel2.add(proxypass,proxyPanel2_gbc);
		proxyPanel2_gbc.gridx = 1;
                proxyPanel2_gbc.gridy = 2;
               	proxyPanel2.add(proxypasstext,proxyPanel2_gbc);

               	centerPanel.add(proxyPanel2,BorderLayout.CENTER);

               	bttnPanel=new JPanel();
               	bttnPanel.setLayout(new FlowLayout());
               	bttnPanel.setBorder(BorderFactory.createTitledBorder(""));

               	appbttn=new JButton("Apply",new ImageIcon(clr.getResource("resources/images/ok.png")));

               	appbttn.addActionListener(this);
		appbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));

               	cancelbttn=new JButton("Cancel",new ImageIcon(clr.getResource("resources/images/quit.png")));
               	cancelbttn.addActionListener(this);
		cancelbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));

               	bttnPanel.add(appbttn);
               	bttnPanel.add(cancelbttn);

		mainPanel.add(rbttnPanel,BorderLayout.NORTH);
               	mainPanel.add(centerPanel,BorderLayout.CENTER);
               	mainPanel.add(bttnPanel,BorderLayout.SOUTH);
               	return mainPanel;
  		
	}

	public File  createFile(){
		File f=new File("./conn.ini");
		try{	
	  		if(f.exists()){
        	        	System.out.println("conn.ini file exists");
        		       	return f;
        	        }else
                		f.createNewFile();
		}catch(Exception e){
			System.out.println("there is no such file"+e.getMessage());
		}
		return f;
	}

	public void deleteFile(){
		File f=new File("./conn.ini");
		if(f.exists())
			f.delete();
		else
			System.out.println("existing file cannot be deleted");
	}
	
	/**
 	* Save proxy setting to con.ini file.
 	*/	
	private void saveProxySetting(){
		FileOutputStream fos; 
    		DataOutputStream dos;
    		deleteFile();
		File f=createFile();
		if(rb1.isSelected()==true){
			try{
				//	BufferedWriter out = new BufferedWriter(new FileWriter(f));
				fos = new FileOutputStream(f);
      				dos=new DataOutputStream(fos);
      				dos.writeBytes("Type="+"1"+"\n");
      				dos.flush();
		  		//	out.write("Type="+"1"+"\n");
			}catch(IOException e){}
		}else {
			if(	rb3.isSelected()==true){
				if((!(proxyhosttext.getText().equals("")))|| (!(proxyporttext.getText().equals("")))||(!(proxyusertext.getText().equals("")))||(!(proxypasstext.getText().equals("")))){
		
					String host=proxyhosttext.getText();
					String port=proxyporttext.getText();
					String user=proxyusertext.getText();
					String pass=proxypasstext.getText();
					try {
        					fos = new FileOutputStream(f);
      						dos=new DataOutputStream(fos);
		      				dos.writeBytes("Type="+"2"+"\n");       
        					dos.writeBytes("ProxyHost="+host+"\n");
        					dos.writeBytes("ProxyPort="+port+"\n");
	        				dos.writeBytes("ProxyUser="+user+"\n");
		        			dos.writeBytes("ProxyPass="+pass);
			       			dos.flush();        	
    					}catch (IOException e) {}
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
  		
  		if(e.getActionCommand() == "rb1"){
  			rb3.setSelected(false);
  			
  			proxyhost.setEnabled(false);
  			proxyhosttext.setEnabled(false);
  			
  			proxyport.setEnabled(false);
  			proxyporttext.setEnabled(false);
  			
  			proxyuser.setEnabled(false);
  			proxyusertext.setEnabled(false);
  			
  			proxypass.setEnabled(false);
  			proxypasstext.setEnabled(false);
  			
  	
   		} 
  		
  		if(e.getActionCommand() == "rb3"){
  			
  			
  			rb1.setSelected(false);
  			
  			proxyhost.setEnabled(true);
  			proxyhosttext.setEnabled(true);
			String host=getProperties().getProperty("ProxyHost");
  			proxyhosttext.setText(host);
  			proxyhosttext.setEditable(true);
  			
  			proxyport.setEnabled(true);
  			proxyporttext.setEnabled(true);
  			String port=getProperties().getProperty("ProxyPort");
  			proxyporttext.setText(port);
  			proxyporttext.setEditable(true);
  			
  			proxyuser.setEnabled(true);
  			proxyusertext.setEnabled(true);
  			String userName=getProperties().getProperty("ProxyUser");
  			proxyusertext.setText(userName);
  			proxyusertext.setEditable(true);
  			
  			proxypass.setEnabled(true);
  			proxypasstext.setEnabled(true);
  			String passWord=getProperties().getProperty("ProxyPass");
  			proxypasstext.setText(passWord);
  			proxypasstext.setEditable(true);
  				
  			
  	
  				
  		}
		/*      if(e.getActionCommand() == "rb2"){

                        proxyhost.setEnabled(false);
                        proxyhosttext.setEnabled(false);
                        proxyport.setEnabled(false);
                        proxyporttext.setEnabled(false);
                        proxyuser.setEnabled(false);
                        proxyusertext.setEnabled(false);
                        proxypass.setEnabled(false);
                        proxypasstext.setEnabled(false);

                        if(netType!=2){
                                netType=2;
                        }

                }
         */
 
  		
  if(e.getSource()==appbttn){
		
		appbttn.setCursor(busyCursor);
		
  		try{Thread.sleep(500);
		}catch(InterruptedException ie){
			appbttn.setCursor(defaultCursor);
		}finally{
			appbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));

  			if(rb1.isSelected()==true){
  			}
  			if(rb3.isSelected()==true){
  				if(((proxyhosttext.getText().equals("")))){
  					JOptionPane.showMessageDialog(null,"Http proxy url field can not be blank");
  					proxyhosttext.requestFocus();
  					return;
				}
  				if(((proxyporttext.getText().equals("")))){
  					JOptionPane.showMessageDialog(null,"Http proxy port field can not be blank");
  					proxyporttext.requestFocus();
  					return;
  				}
  				if(((proxyusertext.getText().equals("")))){
  					JOptionPane.showMessageDialog(null,"Http proxy username field can not be blank");
  					proxyusertext.requestFocus();
  					return;
  				}
  
  				if(((proxypasstext.getText().equals("")))){
  					JOptionPane.showMessageDialog(null,"Http proxy password field can not be blank");
  					proxyhosttext.requestFocus();
  					return;
  				}
  
  
if((!(proxyhosttext.getText().equals("")))|| (!(proxyporttext.getText().equals("")))||(!(proxyusertext.getText().equals("")))||(!(proxypasstext.getText().equals("")))){	
  		
  				//proxyhost.setEnabled(false);
  				proxyhosttext.setEnabled(false);
  				//proxyport.setEnabled(false);
  				proxyporttext.setEnabled(false);
  				//proxyuser.setEnabled(false);
  				proxyusertext.setEnabled(false);
  				//proxypass.setEnabled(false);
  				proxypasstext.setEnabled(false);	
  				}
  		}	
  		
  		saveProxySetting();
  		
  		dispose();
  			 			
  } 
}
  if(e.getSource()==cancelbttn){
			cancelbttn.setCursor(busyCursor);
			try{
				Thread.sleep(300);
			}catch(InterruptedException ie){
				cancelbttn.setCursor(defaultCursor);
			}finally{
				cancelbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
  			dispose();
         	    } 
  }	


/*******************************************************************************************************************/


}
