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
//import javax.swing.JOptionPane;
import org.bss.brihaspatisync.Client;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HttpCommManager {
	
	private String indexServerName=null;

   	private String ipAddressOfHost=null;

   	private Vector loginResult=null;

   	private Vector studCourseList=null;

   	private Vector studSessionList=null;

   	private Vector instCourseList=null;

   	private Vector instSessionList=null;

	
	private String serverDate="";		
   	
   	private static HttpCommManager httpsConnection=null;
	
	private HttpsUtil httpsUtil= HttpsUtil.getController();
//	private Log log=Log.getController();

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
                                serverDate=(String)loginResult.get(2);
				if(loginResult!=null){
                                	getCourseList((String)loginResult.get(0),indexServer);
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

 	
	/**
	 * Get course list in which user registered as a student and instructor.
	 */
  	public void getCourseList(String user_id , String indexServerName){

		/*
 		 *Role 3 is used for student and role 2 is used for instructor.
 		 */
 		
		for(int role=2;role<=3;role++){   
 			try{
 				String id="id="+URLEncoder.encode(user_id,"UTF-8");
				String indexServer=indexServerName+"/ProcessRequest?req=getCourse&"+id+"&role="+role;
				if(role==3){
				      	studCourseList=httpsUtil.getvectorMessage(indexServer,"noLecture");
				}else{
	                                instCourseList=httpsUtil.getvectorMessage(indexServer,"noLecture");
        			}
			}catch(Exception e){System.out.println("Error at getCourseList()in HttpsConnection : "+e.getMessage());}    
 		}
 		if(studCourseList!=null){
 			studSessionList=httpsUtil.getController().getSessionForCourse(studCourseList, indexServerName);
 		} if(instCourseList!=null){
 			instSessionList=httpsUtil.getController().getSessionForCourse(instCourseList,indexServerName);
 		}
		if((studCourseList.contains("noCourse")) && (instSessionList.contains("noCourse")))
 		 	System.out.println(Language.getController().getLangValue("HttpCommManager.MessageDialog1"));
				
 	}
				
        public Vector getInstSessionList(){
                return instSessionList;
        }
	
	public String getServerDate(){
                return serverDate;
        }	

        public Vector getInstCourseList(){
                return instCourseList;
        }

        public Vector getStudSessionList(){
                return studSessionList;
        }

        public Vector getStudCourseList(){
                return studCourseList;
        }
	
}
