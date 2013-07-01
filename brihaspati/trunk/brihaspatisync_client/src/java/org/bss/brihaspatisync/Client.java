package org.bss.brihaspatisync;

/**
 * Client.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import javax.swing.UIManager;
import javax.swing.JOptionPane;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.Language;
import org.bss.brihaspatisync.gui.MainWindow;
import org.bss.brihaspatisync.gui.LoginWindow;
import org.bss.brihaspatisync.gui.MailLogin;
import javax.swing.UIManager.LookAndFeelInfo;

import java.io.File;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class Client {
	  
	private Client(){}

	private void startClient() {
                try{
			Language.getController().SelectLanguage("English");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                		if ("Nimbus".equals(info.getName())) {
                    			UIManager.setLookAndFeel(info.getClassName());
                    			break;
                		}
            		}
               	}catch(Exception e) { System.out.println("Error on Loading Look&Feel"); }
                if(((ClientObject.getController().getIndexServerList()).size()) > 1)
			MainWindow.getController().createGUI();
		else
                	JOptionPane.showMessageDialog(null,Language.getController().getLangValue("Client.MessageDialog"));
	}

	private void startFromURL(String user_name,String lect_id,String course_id,String indexServerName,String ins_std){
		try {
			Language.getController().SelectLanguage("English");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                		if ("Nimbus".equals(info.getName())) {
                    			UIManager.setLookAndFeel(info.getClassName());
                    			break;
                		}
            		}

			MainWindow.getController().createGUI();
			MailLogin.getController().joindirect(user_name,lect_id,course_id,indexServerName,ins_std);
		}catch(Exception e){System.out.println("Error on Loading Look&Feel"+e.getMessage());}
	}

	/**
         * Main Method for client
         */
	
	public static void main(String args[]) {
		try {
			String user_name=args[0].trim();
			if(user_name.equals("no"))
				new Client().startClient();
			else { 
				String lect_id=args[1].trim();
				String course_id=args[2].trim();
				String indexServerName=args[3].trim();
				String ins_std=args[4].trim();
				new Client().startFromURL(user_name,lect_id,course_id,indexServerName,ins_std);
			}
		}catch(Exception e){System.out.println("Exception in Client class on Loading Look&Feel"+e.getMessage());}
	}
}
