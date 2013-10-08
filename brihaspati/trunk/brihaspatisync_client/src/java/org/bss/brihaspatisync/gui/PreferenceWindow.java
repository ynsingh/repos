package org.bss.brihaspatisync.gui;

/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Container;
import java.awt.Cursor;
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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.io.InputStream;
import java.io.File;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified for file handlling and signalling.
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */
 
public class PreferenceWindow extends JFrame implements ActionListener{
	
	private Container con=null;
	private JSplitPane splitPane;
	private JPanel mainPanel;
	private boolean connFlag=false;
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
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private java.net.URL indexurl=null;

	public PreferenceWindow(){
		createWindow(indexurl);
	}
  	
	public void createWindow(java.net.URL indexServer) {
		indexurl=indexServer;
    	   	setTitle(Language.getController().getLangValue("PreferenceWindow.SetTitle"));
    		con=this.getContentPane();
		window_mainPanel=new JPanel();
		window_mainPanel.setLayout(new BorderLayout());
  				
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add(Language.getController().getLangValue("PreferenceWindow.TabPane"),createTabPane());
		window_mainPanel.add(tabPane,BorderLayout.CENTER);
		con.add(window_mainPanel); 
    		setSize(500, 400);
		java.awt.Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                setLocation((((int)dim.getWidth()/2)-210),(((int)dim.getHeight()/2)-200));
    		setVisible(true);
    		//setResizable(false);
    	}

	public Properties getProperties(){
		try{
			prop=new Properties();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resources/config/preference");
            		prop.load(inputStream);
		}catch(Exception e){
			log.setLog("Error on get Properties file"+e.getCause());
			JOptionPane.showMessageDialog(null,Language.getController().getLangValue("PreferenceWindow.MessageDialog1"));
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

		proxyPanel2=new JPanel();
        	proxyPanel2.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
  		
  		rbttnPanel=new JPanel();
  		rbttnPanel.setLayout(new GridLayout(3,1,1,1));
		
  		proxyhost= new JLabel(Language.getController().getLangValue("PreferenceWindow.Proxyhost"));
		proxyhosttext=new JTextField(20);
	        proxyport= new JLabel(Language.getController().getLangValue("PreferenceWindow.Proxyport"));
		proxyporttext=new JTextField(8);

		proxyuser= new JLabel(Language.getController().getLangValue("PreferenceWindow.Proxyuser"));
        	proxyusertext=new JTextField(25);
	        proxypass= new JLabel(Language.getController().getLangValue("PreferenceWindow.Proxypass"));
        	proxypasstext=new JPasswordField(25);

  		bttngroup= new ButtonGroup();
 		
 		/****************************************MODIFIED********************************/
 		netType=Integer.parseInt(getProperties().getProperty("Type"));
  		
  		if(netType==1){
  			rb1= new JRadioButton(Language.getController().getLangValue("PreferenceWindow.RadioButton1"), true);

			rb3= new JRadioButton(Language.getController().getLangValue("PreferenceWindow.RadioButton2"), false);
			proxyhost.setEnabled(false);
			proxyhosttext.setEditable(false);
			proxyport.setEnabled(false);
			proxyporttext.setEditable(false);
			proxyuser.setEnabled(false);
			proxyusertext.setEditable(false);
			proxypass.setEnabled(false);
			proxypasstext.setEditable(false);

		} else {

			rb1= new JRadioButton(Language.getController().getLangValue("PreferenceWindow.RadioButton1"), false);
			rb3= new JRadioButton(Language.getController().getLangValue("PreferenceWindow.RadioButton2"), true);

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
		/*************************************************************************/	
			rb1.addActionListener(this);
            		rb1.setActionCommand("rb1");
	
			rb3.addActionListener(this);
            		rb3.setActionCommand("rb3");
	
			bttngroup.add(rb1);
                	bttngroup.add(rb3);
                	rbttnPanel.add(rb1);
                	rbttnPanel.add(rb3);
                
			titledBorder = BorderFactory.createTitledBorder(Language.getController().getLangValue("PreferenceWindow.TitledBorder"));
        		centerPanel.setBorder(titledBorder);

			gbc.gridx = 0;
                	gbc.gridy = 0;
			gbc.insets = new Insets(5,5,5,5);
			proxyPanel1.add(proxyhost,gbc);
			gbc.gridx = 1;
                	gbc.gridy = 0;
                	proxyPanel1.add(proxyhosttext,gbc);
			gbc.gridx = 2;
	                gbc.gridy = 0;
                	proxyPanel1.add(proxyport,gbc);
			gbc.gridx = 3;
	                gbc.gridy = 0;
                	proxyPanel1.add(proxyporttext,gbc);

                	centerPanel.add(proxyPanel1,BorderLayout.NORTH);

                	gbc.gridx = 0;
                        gbc.gridy = 1;
                	proxyPanel2.add(proxyuser,gbc);
			gbc.gridx = 1;
                        gbc.gridy = 1;
                	proxyPanel2.add(proxyusertext,gbc);
			gbc.gridx = 0;
                        gbc.gridy = 2;
                	proxyPanel2.add(proxypass,gbc);
			gbc.gridx = 1;
                        gbc.gridy = 2;
                	proxyPanel2.add(proxypasstext,gbc);

                	centerPanel.add(proxyPanel2,BorderLayout.CENTER);

                	bttnPanel=new JPanel();
                	bttnPanel.setLayout(new FlowLayout());
                	bttnPanel.setBorder(BorderFactory.createTitledBorder(""));

                	appbttn=new JButton(Language.getController().getLangValue("PreferenceWindow.Applybttn"));
                	appbttn.addActionListener(this);
			appbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));

                	cancelbttn=new JButton(Language.getController().getLangValue("PreferenceWindow.Cancelbttn"));
                	cancelbttn.addActionListener(this);
			cancelbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));

                	bttnPanel.add(appbttn);
                	bttnPanel.add(cancelbttn);

			mainPanel.add(rbttnPanel,BorderLayout.NORTH);
                	mainPanel.add(centerPanel,BorderLayout.CENTER);
                	mainPanel.add(bttnPanel,BorderLayout.SOUTH);
                	return mainPanel;
  		
	}
	
	/**********************MODIFIED********************/
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
				//	BufferedWriter out = new BufferedWriter(new FileWriter(f));
				fos = new FileOutputStream(f);
      				dos=new DataOutputStream(fos);
      				dos.writeBytes("Type="+"1"+"\n");
      				dos.flush();
		  		//	out.write("Type="+"1"+"\n");
			}catch(IOException e){}
		}else if(rb3.isSelected()==true){
			if((!(proxyhosttext.getText().equals("")))|| (!(proxyporttext.getText().equals("")))||(!(proxyusertext.getText().equals("")))||(!(proxypasstext.getText().equals("")))){
				try {
					String host=proxyhosttext.getText();
					String port=proxyporttext.getText();
					String user=proxyusertext.getText();
					String pass=proxypasstext.getText();
					RuntimeDataObject.getController().setProxyHost(host);
		                	RuntimeDataObject.getController().setProxyPort(port);
					RuntimeDataObject.getController().setProxyUser(user);
		                        RuntimeDataObject.getController().setProxyPass(pass);
					if(indexurl != null)
						HttpsUtil.getController().createHTTPConnection(indexurl);		
					/***
        				fos = new FileOutputStream(f);
      					dos=new DataOutputStream(fos);
		      			dos.writeBytes("Type="+"2"+"\n");       
	        			log.setLog("this is a test");
        				dos.writeBytes("ProxyHost="+host+"\n");
        				dos.writeBytes("ProxyPort="+port+"\n");
	        			dos.writeBytes("ProxyUser="+user+"\n");
		        		dos.writeBytes("ProxyPass="+pass);
			       		dos.flush();        	
					*********/
	    			} catch (IOException e) {}
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
  		
		if(e.getSource()==appbttn){
			appbttn.setCursor(busyCursor);
			appbttn.setCursor(defaultCursor);
			//appbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	  		log.setLog("NetType="+netType);
  	 		
  			if(rb1.isSelected()==true) {
  					log.setLog("this is direct net type");
  			}
  			if(rb3.isSelected()==true) {
  				if(((proxyhosttext.getText().equals("")))) {
  					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("PreferenceWindow.MessageDialog2"));
  					proxyhosttext.requestFocus();
  					return;
				}
  				if(((proxyporttext.getText().equals("")))) {
  					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("PreferenceWindow.MessageDialog3"));
  					proxyporttext.requestFocus();
  					return;
  				}
  				if(((proxyusertext.getText().equals("")))) {
  					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("PreferenceWindow.MessageDialog4"));
  					proxyusertext.requestFocus();
  					return;
  				}
  
  				if(((proxypasstext.getText().equals("")))) {
  					JOptionPane.showMessageDialog(null,Language.getController().getLangValue("PreferenceWindow.MessageDialog5"));
  					proxyhosttext.requestFocus();
  					return;
  				}
  
 				if((!(proxyhosttext.getText().equals("")))|| (!(proxyporttext.getText().equals("")))||(!(proxyusertext.getText().equals("")))||(!(proxypasstext.getText().equals("")))) {
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
			cancelbttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
  			dispose();
  		} 
  	}
}
