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
			ClientObject.getController().setIndexServerName(indexServerName);
                      	ClientObject.getController().setUserName(username);
			MainWindow.getController().setCouseid(course_id);
			ClientObject.getController().setLectureID(lect_id);
                        ClientObject.getController().setUserRole(ins_std);

                        JoinSession.getController().goToLecture(lect_id);
                        MainWindow.getController().getMenuItem5().setEnabled(true);
                        MainWindow.getController().getMenuItem6().setEnabled(true);
		}catch(Exception e){System.out.println("Error in MilLogin");}	
	}	
}	
