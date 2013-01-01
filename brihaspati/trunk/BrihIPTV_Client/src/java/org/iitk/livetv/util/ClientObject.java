package org.iitk.livetv.util;

/**
 * ClientObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.util.Vector;
import org.iitk.livetv.http.HttpCommManager;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 15Sep2012
 * This class is used to store objects which are needed in runtime by this client.
 */

public class ClientObject {

	private static ClientObject cb=null;
	private Vector indexServerList=null;
	private String indexServerName="";
	private Vector categoryList=null;	
	private String localIP="";
	private String usr_name="";
	private String usr_id="";
	private String usr_role="";

	private HttpCommManager commMgr = HttpCommManager.getController();

	public static ClientObject getController(){
		if(cb==null)
			cb=new ClientObject();
		return cb;
	}
	
	/**
	 * This method is used to retrive the list of index server by calling connectToMasterServer() 
	 * from HttpCommManager which initiate a connection with master server and get indexing server 
	 * list.
	 */
	public Vector getIndexServerList(){
		if(indexServerList == null)
			indexServerList=commMgr.connectToMasterServer();
		return indexServerList;
	}
	
	/**
         * This method is used to get index server name which is choosen by this client for login authentication.
         */
	public String getIndexServerName(){
		return indexServerName;
	}
	
     	/**
         * This method is used to get user name of this client which is used to login authentication from login window.
         */
	public String getUserName(){
		return usr_name;
	}

	public String getUserID(){
                return usr_id;
        }

	
	public String getLocalIP(){
                return this.localIP;
        }

	public Vector getCategory(){
		if(categoryList == null)
			categoryList=commMgr.getCategoryList();
		return categoryList;
	}
	
	/**
         * This method is used to send username and password to indexSerName which match these username
	 * and password to database for client authentication.if authentication is successfull this method 
	 * return true otherwise it return false.
         */
	public boolean getAuthentication(String indexSerName, String usr, String pass){
		boolean value =commMgr.connectToIndexServer(indexSerName,usr,pass);
		return value;
	}

	public void setIndexServerName(String value){
		this.indexServerName=value;
        }

        public void setUserName(String value){
		this.usr_name=value;
        }
	
	public void setUserID(String value){
                this.usr_id=value;
        }

	public void setLocalIP(String str){
		this.localIP=str;
	}
}

