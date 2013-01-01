package org.iitk.livetv;

/**
 * Client.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import org.iitk.livetv.gui.LoginWindow;
import org.iitk.livetv.util.ClientObject;

import java.io.File;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class Client {
	
	private static Client client=null;
	
	public static Client getController(){
		if(client==null)
			client=new Client();
		return client;
	}
	
	public Client(){}

	public void startClient(){
                try{
                	/* The look and feel is set */
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
               	}catch(Exception e){System.out.println("Error on Loading Look&Feel");}
		try{
			if(((ClientObject.getController().getIndexServerList()).size()) > 1){
				LoginWindow.getController();
			}else{
                		JOptionPane.showMessageDialog(null,"Index server list not found. Please try again!!");
              	}
		}catch(Exception e){System.out.println("Error on show login window : "+e);}
	}
	
	/**
         * Main Method for client
         */
	public static void main(String args[]){
		Client.getController().startClient();
	}
}
