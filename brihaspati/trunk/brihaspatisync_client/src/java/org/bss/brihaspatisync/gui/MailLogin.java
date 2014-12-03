package org.bss.brihaspatisync.gui;

/**
 * MailLogin.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
 */


import org.bss.brihaspatisync.util.ClientObject;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */
 
public class MailLogin {
	private static MailLogin ml=null;

	public static MailLogin getController(){
                if(ml==null)
                        ml=new MailLogin();
                return ml;
        }
	
	public void  joindirect(String username,String lect_id,String course_id,String indexServerName,String ins_std) {
		try {
			ClientObject.setIndexServerName(indexServerName);
                      	ClientObject.setUserName(username);
			MainWindow.getController().setCouseid(course_id);
			ClientObject.setLectureID(lect_id);
                        ClientObject.setUserRole(ins_std);
                        new JoinSession(lect_id, ins_std);
		}catch(Exception e){System.out.println("Error in MilLogin");}	
	}	
}	
