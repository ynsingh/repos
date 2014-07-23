package org.bss.brihaspatisync.gui;

/**
 * ForgetPass.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 20011 ETRG,IIT Kanpur.
 */

import java.awt.Container;
import java.awt.Cursor;
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
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a>Modified for signalling on button click.
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */
 
public class ForgetPass extends JFrame implements ActionListener{
	
	
	private static ForgetPass forgetpass=null;
	private Container con=null;
	private JButton submitBttn;
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

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
   		setTitle(Language.getController().getLangValue("ForgetPassword.Title"));
		con=this.getContentPane();
   		con.setLayout(new BorderLayout());
     
   		JPanel northPanel=new JPanel();
   		JLabel InstructionLabel= new JLabel(Language.getController().getLangValue("ForgetPassword.InstructionLabel"));
   		northPanel.add(InstructionLabel);//,BorderLayout.NORTH);
   		con.add(northPanel,BorderLayout.NORTH);
   
   		JPanel centerPanel=new JPanel();
   		JLabel entermail=new JLabel(Language.getController().getLangValue("ForgetPassword.EnterMail"));
   		JTextField email=new JTextField(15); 
   		centerPanel.add(entermail);
   		centerPanel.add(email);
   
   		submitBttn=new JButton(Language.getController().getLangValue("ForgetPassword.SubmitButton"));
		
   		submitBttn.addActionListener(this);
   		submitBttn.setActionCommand("Submit.Action");
		submitBttn.setCursor(new Cursor(Cursor.HAND_CURSOR));	

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
			
			submitBttn.setCursor(busyCursor);
				try{Thread.sleep(300);
				}catch(InterruptedException ie){
					submitBttn.setCursor(defaultCursor);
				}finally{
					submitBttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
  			this.hide();    
  			JOptionPane.showMessageDialog( null, Language.getController().getLangValue("ForgetPassword.MessageDialog1"));
  			  			
      		}
    	}

}

