package org.bss.brihaspatisync.gui;

/**
 * Logout.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT kanpur.
 */

import javax.swing.JOptionPane;
import java.net.URLEncoder;

import org.bss.brihaspatisync.gui.JoinSession;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.ReceiveQueueHandler;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class Logout{

	private static Logout logout=null;
	private ClientObject client_obj=ClientObject.getController();
	private Log log=Log.getController();
	
	/**
	 * Controller for Class
	 */
	protected static Logout getController(){
		if(logout==null){
			logout=new Logout();
		}
		return logout;
	}

	/**
	 * Constructor for class
	 */
	private Logout(){
	}

	/**
	 * close all local network thread and media threads 
         * send Logout request to indexing server to remove this client from existing peer network 
	 * and reform peer network (use Backup Tree Algorithm)
	 */

	public void sendLogoutRequest(){
		String parentIP="";		
		try{
			log.setLog("Logout in process");
			//code for closing all thrads and release all resource and memory by jvm
			try{
				if(client_obj.getUserRole().equals("instructor")){
					if(JoinSession.getController().getUL_Timer()==null)
						JoinSession.getController().getUL_Timer().cancel(); 
				}
			}catch(Exception e){}
			
			log.setLog("Sending Request for Logout");
			String ref_addr=client_obj.getReflectorIP();
			String username="",lectID="";
			if(!(ref_addr.equals(""))) {	
				ref_addr="reflector="+URLEncoder.encode(client_obj.getReflectorIP(),"UTF-8");
				username="username="+URLEncoder.encode(client_obj.getUserName(),"UTF-8");
				lectID="lectID="+URLEncoder.encode(client_obj.getLectureID(),"UTF-8");
			}else
				log.setLog("Insufficient reflector address in Logout Class :"+ref_addr);

			String indexName=client_obj.getIndexServerName();
			String reflectorIP =client_obj.getReflectorIP();
			log.setLog("ref_addr ----> "+ref_addr);
			if(!(indexName.equals(""))){
                               String  indexServer=indexName+"/ProcessRequest?req=logout&"+ref_addr+"&"+username+"&"+lectID;
   	                       HttpsUtil.getController().getIndexingMessage(indexServer);
			}else{
                        	log.setLog("insufficient indexServer name in AnnounceSession :" + indexName);
                       	}
									
		}catch(Exception ex){}
		log.setLog("Logout is completed");
		System.exit(0);

	}
	
}	
