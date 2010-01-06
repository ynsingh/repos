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
import org.bss.brihaspatisync.network.http.HTTPUserList;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListTimer extends TimerTask{

	private static UserListTimer request=null;
	private ClientObject client_obj=ClientObject.getController();
	private Log log=Log.getController();

	
	/**
	 * Controller for class
	 */
        protected static UserListTimer getController() {
                if(request==null);
                	request=new UserListTimer();
                return request;
        }

        private UserListTimer() { }

        /**Overrides the run method */

        public void run(){
                try{
			String ref_ip =ClientObject.getController().getReflectorIP();
			if(ref_ip != null){
				String lect_id=client_obj.getLectureID();
                        	if(!(lect_id.equals("")))
					UserListPanel.getController().userlistPanel(HTTPUserList.getController().getUserList(ref_ip,lect_id));
			}else 
				log.setLog("ref_ip is blanck  in RequestuserList class");	
			
       		}catch(Exception ex){
			log.setLog("Error in RequestuserList class"+ex.getMessage());
		}
	}
}

	
