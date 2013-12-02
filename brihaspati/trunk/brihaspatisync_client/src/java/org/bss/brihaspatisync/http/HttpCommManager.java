package org.bss.brihaspatisync.http;

/**
 * HttpsConnection.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008,2013 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import java.net.URLEncoder;
import java.net.InetAddress;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> 
 */

public class HttpCommManager {
	
	private static String indexServerName="";

   	private static Vector loginResult=null;
   	
	/** 
	 * We instantiate connection to master server to retrieve the secondry indexing serveris' list
         * If there is secondary indexing serveris's list is find from master server, open main window GUI
         * otherwise it return from main method.
         */
	public static Vector connectToMasterServer() {
   		try {
			/** First entry is kept as "Select", to signal the users that he need to make a choice of secondry indexing server,
	        	 *  The url is created to retrieve the secondry indexing servers' list
        		 *  from master indexing server
         		 */
			String m_url=org.bss.brihaspatisync.util.RuntimeDataObject.getController().getMasterUrl()+"/ProcessRequest?req=getISList";
                        if(!m_url.equals("")){
				Vector indexServerList=HttpsUtil.getvectorMessage(m_url,"noLecture");
				indexServerList.insertElementAt("Select",0); 
				return indexServerList;
                        } 
                } catch(Exception ioe) { System.out.println("Error at connectToMasterServer()in HttpsConnection"+ioe.getMessage());}
                System.out.println("Master URL not found. Clear the cache and download the new binary from www.brihaspatisolutions.co.in.\n");
		return null;
   	}
	
	/**
	 * Send login details with username and password to get a session key and userid for current login.
	 */   	
   	public static boolean connectToIndexServer(String indexServer,String usr_name, String password){
		boolean index=false;
  		try{
			// store indexServer name in local variable for later use.
			if(indexServer!=null && usr_name !=null && password !=null){
  				String usr = "usr="+URLEncoder.encode(usr_name, "UTF-8");
      				String pass= "pass="+URLEncoder.encode(password, "UTF-8");
      				String ip="ip="+URLEncoder.encode((String)InetAddress.getLocalHost().getHostAddress(),"UTF-8");
				String req_url=indexServer+"/ProcessRequest?req=login&"+usr+"&"+pass+"&"+ip;
                                loginResult=HttpsUtil.getvectorMessage(req_url,"noLecture");
				if((loginResult != null) && (loginResult.size()>0)){
					indexServerName=indexServer;
                                        index=true;
  				}
			} else 
				System.out.println("No Sufficient Arguments to call connectToIndexServer()");
         	}catch(Exception e) { System.out.println("Exception at connectToIndexserver()in HttpsConnection : "); }
		return index;
        }

	/**
	 * This method are used to get all instructor session list from iserver  
	 */
        public static Vector getInstSessionList() {
		return HttpsUtil.getSessionForCourse(getInstCourseList(),indexServerName);
        } 
		
	/**
         * This method are used to get all student session list from iserver  
         */	
        public static Vector getStudSessionList(){
                return HttpsUtil.getSessionForCourse(getStudCourseList(),indexServerName);
        }
	
	/**
         * This method are used to get all course list from iserver in which user are student 
         */
        public static Vector getStudCourseList(){
		try {
			String id="id="+URLEncoder.encode((String)loginResult.get(0),"UTF-8");
			String indexServer=indexServerName+"/ProcessRequest?req=getCourse&"+id+"&role="+3;
			return HttpsUtil.getvectorMessage(indexServer,"noLecture");
		}catch(Exception e) {System.out.println("Exception in getStudCourseList() "); }
                return null;
        }
	
	/**
         * This method are used to get all course list from iserver in which user are instructor 
         */
	public static Vector getInstCourseList(){
		try {
	                String id="id="+URLEncoder.encode((String)loginResult.get(0),"UTF-8");
        	        String indexServer=indexServerName+"/ProcessRequest?req=getCourse&"+id+"&role="+2;	
                	return HttpsUtil.getvectorMessage(indexServer,"noLecture");
		}catch(Exception e){ System.out.println("Exception in getInstCourseList() "); }
                return null;
      	}
	
	/**
         * This method are used to get server time from iserver . 
         */
	public static String  getTimeIndexingServer(){
		try {
                        String  indexServer=indexServerName+"/ProcessRequest?req=getTimeforLecture&";
			return HttpsUtil.getStringMessage(indexServer,"UnSuccessfull");	
		} catch(Exception e){System.out.println("Exception in getTimeIndexingServer() ");}
		return null;
        }

        	
}
