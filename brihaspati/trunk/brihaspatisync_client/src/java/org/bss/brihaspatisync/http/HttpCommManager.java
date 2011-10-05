package org.bss.brihaspatisync.http;

/**
 * HttpsConnection.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import java.net.URLEncoder;
import java.net.InetAddress;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> 
 */

public class HttpCommManager {
	
	private String indexServerName="";

   	private Vector loginResult=null;

   	private static HttpCommManager httpsConnection=null;
	
	private HttpsUtil httpsUtil= HttpsUtil.getController();

	/**
	 * Controller for the class.
	 */
	public static HttpCommManager getController(){
		if (httpsConnection==null){
			httpsConnection=new HttpCommManager();
		}
		return httpsConnection;
	}

   	/** 
	 * We instantiate connection to master server to retrieve the secondry indexing serveris' list
         * If there is secondary indexing serveris's list is find from master server, open main window GUI
         * otherwise it return from main method.
         */
	public Vector connectToMasterServer() {
   		try {
			/** First entry is kept as "Select", to signal the users that he need to make a choice of secondry indexing server,
	        	 *  The url is created to retrieve the secondry indexing servers' list
        		 *  from master indexing server
         		 */
			String m_url=RuntimeDataObject.getController().getMasterUrl()+"/ProcessRequest?req=getISList";
                        if(!(m_url.equals(""))){
				Vector indexServerList=httpsUtil.getvectorMessage(m_url,"noLecture");
				indexServerList.insertElementAt("Select",0); 
				if(indexServerList.size()!=0){
					return indexServerList;
                                } else {
                                        System.out.println("Error in getting secondary indexing Servers.");
					return indexServerList;
                                }
			
                        }else {
                                System.out.println("Master URL not found. Clear the cache and download the new binary from www.brihaspatisolutions.co.in.\n");
                        }
                }catch (Exception ioe) {
                        System.out.println("Error at connectToMasterServer()in HttpsConnection"+ioe.getMessage());
                }
		return null;
   	}
	
	/**
	 * Send login details with username and password to get a session key and userid for current login.
	 */   	
   	public boolean connectToIndexServer(String indexServer,String usr_name, String password){
		boolean index=false;
  		try{
			// store indexServer name in local variable for later use.
			if(indexServer!=null || usr_name!=null || password!=null){
  				String usr = "usr="+URLEncoder.encode(usr_name, "UTF-8");
      				String pass= "pass="+URLEncoder.encode(password, "UTF-8");
      				String ip="ip="+URLEncoder.encode((String)InetAddress.getLocalHost().getHostAddress(),"UTF-8");
				
				String req_url=indexServer+"/ProcessRequest?req=login&"+usr+"&"+pass+"&"+ip;
                                loginResult=httpsUtil.getvectorMessage(req_url,"noLecture");
                                //serverDate=(String)loginResult.get(2);
				if(loginResult!=null){
					indexServerName=indexServer;
                                        index=true;
  				}
			}else{
				System.out.println("No Sufficient Arguments to call connectToIndexServer()");
			}

         	}catch(Exception e){
                	System.out.println("Error at connectToIndexserver()in HttpsConnection : "+e.getMessage());
                }
		return index;
        }
	
	
 	
        public Vector getInstSessionList(){
		return httpsUtil.getController().getSessionForCourse(getInstCourseList(),indexServerName);//return instSessionList;
        }
	
        public Vector getStudSessionList(){
                return httpsUtil.getController().getSessionForCourse(getStudCourseList(),indexServerName);//studSessionList;
        }
	
        public Vector getStudCourseList(){
		try {
			String id="id="+URLEncoder.encode((String)loginResult.get(0),"UTF-8");
			String indexServer=indexServerName+"/ProcessRequest?req=getCourse&"+id+"&role="+3;
			return httpsUtil.getvectorMessage(indexServer,"noLecture");
		}catch(Exception e){ }
                return null;
        }
	
	public Vector getInstCourseList(){
		try {
	                String id="id="+URLEncoder.encode((String)loginResult.get(0),"UTF-8");
        	        String indexServer=indexServerName+"/ProcessRequest?req=getCourse&"+id+"&role="+2;	
                	return httpsUtil.getvectorMessage(indexServer,"noLecture");
		}catch(Exception e){ }
                return null;
      	}
	
	public String  getTimeIndexingServer(){
		try {
                        String  indexServer=indexServerName+"/ProcessRequest?req=getTimeforLecture&";
			return httpsUtil.getStringMessage(indexServer,"UnSuccessfull");	
		}catch(Exception e){System.out.println("Error in getTimeIndexingServer() ");}
		return null;
        }

        	
}
