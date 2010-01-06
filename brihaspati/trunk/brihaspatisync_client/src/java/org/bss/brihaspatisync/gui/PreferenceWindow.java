package org.bss.brihaspatisync.gui;

/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Container;
import java.awt.BorderLayout;
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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.io.InputStream;
//import java.lang.*;
import java.io.File;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.network.Log;


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
	private Log log=Log.getController();

	
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
    	   	setTitle("Preferences");
    		con=this.getContentPane();
  			window_mainPanel=new JPanel();
  			window_mainPanel.setLayout(new BorderLayout());
  				
  			JTabbedPane tabPane = new JTabbedPane();
  			tabPane.add("Connection Setting",createTabPane());
  			window_mainPanel.add(tabPane,BorderLayout.CENTER);
  			con.add(window_mainPanel); 
    		setSize(420, 300);
    		setLocation(515,100);
    		setVisible(true);
    		setResizable(true);
    	}

	public Properties getProperties(){
		try{
			prop=new Properties();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/preference");
            		prop.load(inputStream);
		}catch(Exception e){
			log.setLog("Error on get Properties file"+e.getCause());
			JOptionPane.showMessageDialog(null,"Error on loading preference file !!");
		}
		return prop;
	}

/*	public int getNetType(){
	}

	public String getProxyHost(){
	}

	public int getProxyPort(){
	}

	public String getProxyUser(){
	}

	public String getProxyPass(){
	}
*/
	/**
	 * Creating Tabbed Pane For Connection Setting Window.
	 */
	protected JPanel createTabPane(){
		
		mainPanel=new JPanel();
  		mainPanel.setLayout(new BorderLayout());

		centerPanel=new JPanel();
        centerPanel.setLayout(new BorderLayout());

		proxyPanel1=new JPanel();
        proxyPanel1.setLayout(new FlowLayout());

		proxyPanel2=new JPanel();
        proxyPanel2.setLayout(new FlowLayout());

  		
  		rbttnPanel=new JPanel();
  		rbttnPanel.setLayout(new GridLayout(3,1,1,1));
  		proxyhost= new JLabel("Host");
		proxyhosttext=new JTextField(20);
        proxyport= new JLabel("Port");
		proxyporttext=new JTextField(8);

		proxyuser= new JLabel("Username");
        proxyusertext=new JTextField(25);
        proxypass= new JLabel("Password");
        proxypasstext=new JPasswordField(25);

  		bttngroup= new ButtonGroup();
 		
 		/****************************************MODIFIED********************************/
 		netType=Integer.parseInt(getProperties().getProperty("Type"));
  		
  		if(netType==1){
  			rb1= new JRadioButton("Direct Connection to the Internet", true);
		//	rb1.addActionListener(this);
        //  rb1.setActionCommand("rb1");

			rb3= new JRadioButton("proxy configuration : ", false);
			proxyhost.setEnabled(false);
			proxyhosttext.setEditable(false);
			proxyport.setEnabled(false);
			proxyporttext.setEditable(false);
			proxyuser.setEnabled(false);
			proxyusertext.setEditable(false);
			proxypass.setEnabled(false);
			proxypasstext.setEditable(false);

		} else {
/**************************************************MODIFIED**********************************************************/

			rb1= new JRadioButton("Direct Connection to the Internet", false);
			rb3= new JRadioButton("proxy configuration : ", true);
		//	rb3.addActionListener(this);
        //  rb3.setActionCommand("rb3");

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
/********************************************************************************************************************/	
			rb1.addActionListener(this);
            rb1.setActionCommand("rb1");
	
			rb3.addActionListener(this);
            rb3.setActionCommand("rb3");
	
				bttngroup.add(rb1);
                bttngroup.add(rb3);
                rbttnPanel.add(rb1);
                rbttnPanel.add(rb3);
                
				titledBorder = BorderFactory.createTitledBorder("Proxy-Login");
        		centerPanel.setBorder(titledBorder);

				proxyPanel1.add(proxyhost);
                proxyPanel1.add(proxyhosttext);
                proxyPanel1.add(proxyport);
                proxyPanel1.add(proxyporttext);

                centerPanel.add(proxyPanel1,BorderLayout.NORTH);

                proxyPanel2.add(proxyuser);
                proxyPanel2.add(proxyusertext);
                proxyPanel2.add(proxypass);
                proxyPanel2.add(proxypasstext);

                centerPanel.add(proxyPanel2,BorderLayout.CENTER);

                bttnPanel=new JPanel();
                bttnPanel.setLayout(new FlowLayout());
                bttnPanel.setBorder(BorderFactory.createTitledBorder(""));

                appbttn=new JButton("Apply");
                appbttn.addActionListener(this);
                cancelbttn=new JButton("Cancel");
                cancelbttn.addActionListener(this);

                bttnPanel.add(appbttn);
                bttnPanel.add(cancelbttn);

				mainPanel.add(rbttnPanel,BorderLayout.NORTH);
                mainPanel.add(centerPanel,BorderLayout.CENTER);
                mainPanel.add(bttnPanel,BorderLayout.SOUTH);
                return mainPanel;
  		
	}
	
	/**********************MODIFIED**********************************************************************/
	public File  createFile(){
		File f=new File("./conn.ini");
		try{	
	  		if(f.exists()){
        	        	log.setLog("file exists");
        		       	return f;
        	        }else
                		f.createNewFile();
		}catch(Exception e){
			log.setLog("there is no such file"+e.getMessage());
		}
		return f;
	}

	public void deleteFile(){
		File f=new File("./conn.ini");
		if(f.exists())
			f.delete();
		else
			log.setLog("existing file cannot be deleted");
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
				log.setLog("1234");
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
        					log.setLog("this is a test");
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
  			log.setLog("testing");
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
  
  		log.setLog("NetType="+netType);
  		
  		
  		if(	rb1.isSelected()==true){
  					log.setLog("this is direct net type");
  					}
  		if(rb3.isSelected()==true){
  if(((proxyhosttext.getText().equals("")))){
  
  		JOptionPane.showMessageDialog(null,"THE HOST FIELD IS LEFT BLANK");
  		proxyhosttext.requestFocus();
  	return;
  }
  	if(((proxyporttext.getText().equals("")))){
  
  		JOptionPane.showMessageDialog(null,"THE PORT FIELD IS LEFT BLANK");
  		proxyporttext.requestFocus();
  	return;
  }
  if(((proxyusertext.getText().equals("")))){
  
  		JOptionPane.showMessageDialog(null,"THE USERNAME FIELD IS LEFT BLANK");
  		proxyusertext.requestFocus();
  	return;
  }
  
  if(((proxypasstext.getText().equals("")))){
  
  		JOptionPane.showMessageDialog(null,"THE PASSWORD FIELD iS LEFT BLANK");
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
  		if(e.getSource()==cancelbttn){
  			dispose();
         	}     
  	}	


/*******************************************************************************************************************/


}
