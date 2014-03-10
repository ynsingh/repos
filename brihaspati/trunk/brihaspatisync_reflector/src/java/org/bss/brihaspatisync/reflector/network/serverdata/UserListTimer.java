package org.bss.brihaspatisync.reflector.network.serverdata;

/**
 * UserListTimer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */


import java.util.Vector;
import java.util.TimerTask;

import java.net.URLEncoder;

import org.bss.brihaspatisync.reflector.RegisterToIndexServer;
import org.bss.brihaspatisync.reflector.util.RuntimeObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UserListTimer extends TimerTask{

        private static UserListTimer request=null;

        /**
         * Controller for class
         */
	
        public static UserListTimer getController() {
                if(request==null);
                        request=new UserListTimer();
                return request;
        }
	
        private UserListTimer() { }

        /**Overrides the run method */

        public void run() {
               try{
                    	String index_url=RuntimeObject.getController().getindexServerAddr().trim()+"req=userlist&";    
                        Vector masterReflector=RuntimeObject.getController().getMastrerReflecterCourseid();
                        for(int i=0;i<masterReflector.size();i++){
                                String lect_id = "lect_id="+URLEncoder.encode(masterReflector.get(i).toString(),"UTF-8");
                                String url=index_url+lect_id;
                                RegisterToIndexServer.getUserListToMasterServer(url,masterReflector.get(i).toString());
                        }
			
              	}catch(Exception ex){
			System.out.println("Error in UserListTimer class"+ex.getCause());	
              	}
         }
}
