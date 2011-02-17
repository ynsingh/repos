package org.bss.brihaspatisync.gui;

/**
 * UserListTimer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import java.net.URLEncoder;

import java.util.Vector;
import java.util.TimerTask;
import org.bss.brihaspatisync.util.ClientObject;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListTimer extends TimerTask{

	private static UserListTimer request=null;
	private ClientObject client_obj=ClientObject.getController();
	private Log log=Log.getController();
	private Vector vector=new Vector();
	
	/**
	 * Controller for class
	 */
        public static UserListTimer getController() {
                if(request==null);
                	request=new UserListTimer();
                return request;
        }

        public UserListTimer() { }

        /**Overrides the run method */

        public void run(){
                try{
			String ref_ip =ClientObject.getController().getReflectorIP();
			if(ref_ip != null) {
				String lect_id=client_obj.getLectureID();
                        	if(!(lect_id.equals(""))) {
					vector.clear();
					
					try {
						String str=RuntimeDataObject.getController().getUserList();
						str=str.replaceAll(","," ");
						if(str.length()>0){
							java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
                                                	while(Tok.hasMoreElements()) {
                                                        	String str1=(String)Tok.nextElement();
								vector.add(str1);
                                                	}	
							UserListPanel.getController().userlistPanel(vector);
						}		
					}catch(Exception e){ System.out.println("Error in RequestuserList class"+e.getMessage()); }
					
				}
			} else { 
				System.out.println("ref_ip is blanck  in RequestuserList class");	
			}
			
       		}catch(Exception ex){
			System.out.println("Error in RequestuserList class"+ex.getMessage());
		}
	}
}

	
