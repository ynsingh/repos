package org.bss.brihaspatisync.gui;

/**
 * ForgetPass.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */
 
public class ForgetPass extends JFrame implements ActionListener{
	
	
	private static ForgetPass forgetpass=null;
	private Container con=null;
	private JButton submitBttn;
	
	/**
	 *Controller for the class.
	 */
	protected static ForgetPass getController(){
		if (forgetpass==null){
			forgetpass=new ForgetPass();
		}
		return forgetpass;
	}

  	private ForgetPass(){
   		setTitle("Brihaspati Live Classroom");
		con=this.getContentPane();
   		con.setLayout(new BorderLayout());
     
   		JPanel northPanel=new JPanel();
   		JLabel InstructionLabel= new JLabel("Enter the email address that you supplied when you signed up with brihaspatisync.");
   		northPanel.add(InstructionLabel);//,BorderLayout.NORTH);
   		con.add(northPanel,BorderLayout.NORTH);
   
   		JPanel centerPanel=new JPanel();
   		JLabel entermail=new JLabel("Enter E-Mail:");
   		JTextField email=new JTextField(15); 
   		centerPanel.add(entermail);
   		centerPanel.add(email);
   
   		JButton submitBttn=new JButton("Submit");
   		submitBttn.addActionListener(this);
   		submitBttn.setActionCommand("Submit.Action");
   		centerPanel.add(submitBttn);
   		con.add(centerPanel,BorderLayout.CENTER);
   
  	 	JPanel southPanel=new JPanel();
   		JLabel infoLabel= new JLabel("");
   		southPanel.add(infoLabel);
   
   		con.add(southPanel,BorderLayout.SOUTH);
     		
		// setResizable(false);
   		pack();
   		setSize(480,150); 
   		setVisible(true);
  	}
  
	public void actionPerformed(ActionEvent e) {
  		if(e.getActionCommand().equals("Submit.Action")){
  			this.hide();    
  			JOptionPane.showMessageDialog( null, "Your password will be sent to your email address." );
  			  			
      		}
    	}

}

