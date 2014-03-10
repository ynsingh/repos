package org.bss.brihaspatisync.gui;

/**
 * Logout.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008,2013 ETRG, IIT kanpur.
 */

import javax.swing.JOptionPane;
import java.net.URLEncoder;

import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class Logout {

	/**
	 * close all local network thread and media threads 
         * send Logout request to indexing server to remove this client from existing peer network 
	 * and reform peer network (use Backup Tree Algorithm)
	 */

	public void sendLogoutRequest() {
		String parentIP="";		
		try{
			org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);	
			String ref_addr=ClientObject.getReflectorIP();
			String username="",lectID="";
			if(!(ref_addr.equals(""))) {	
				ref_addr="reflector="+URLEncoder.encode(ClientObject.getReflectorIP(),"UTF-8");
				username="username="+URLEncoder.encode(ClientObject.getUserName(),"UTF-8");
				lectID="lectID="+URLEncoder.encode(ClientObject.getLectureID(),"UTF-8");
			}
			String indexName=ClientObject.getIndexServerName();
			String reflectorIP =ClientObject.getReflectorIP();
			if(!(indexName.equals(""))){
                               String  indexServer=indexName+"/ProcessRequest?req=logout&"+ref_addr+"&"+username+"&"+lectID;
   	                       HttpsUtil.getIndexingMessage(indexServer);
                       	}
									
		}catch(Exception ex){}
	}
	
	/**
 	 * This Method is used to stop all thread when session time out.
 	 *
 	 */  	
	
	public void sessionOutMessage(){
		try {
			org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);
			Thread.sleep(1000);
			org.bss.brihaspatisync.gui.StatusPanel.getController().sethttpClient("no");
        		org.bss.brihaspatisync.gui.StatusPanel.getController().setaudioClient("no");	
			org.bss.brihaspatisync.gui.StatusPanel.getController().setdestopClient("no");
			StatusPanel.getController().setStatus("Session time out ");
		}catch(Exception e){}
	}
}	
