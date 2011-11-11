package org.bss.brihaspatisync;

/**
 * Client.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
 */

import javax.swing.UIManager;
import javax.swing.JOptionPane;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.gui.LoginWindow;

import java.io.File;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
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
			Language.getController().SelectLanguage("English");
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
               	}catch(Exception e){System.out.println("Error on Loading Look&Feel");}

                if(((ClientObject.getController().getIndexServerList()).size()) > 1){
			MainWindow.getController().createGUI();
                	MainWindow.getController().getDesktop().add(new LoginWindow());
		}else{
                	JOptionPane.showMessageDialog(null,Language.getController().getLangValue("Client.MessageDialog"));
              	}
	}
	
	/**
         * Main Method for client
         */
	public static void main(String args[]){
		Client.getController().startClient();
	}
}
