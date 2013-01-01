package org.iitk.livetv.http;

/**
 * HttpsConnection.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import java.net.URLEncoder;
import java.net.InetAddress;
import org.iitk.livetv.util.HttpsUtil;
import org.iitk.livetv.util.RuntimeObject;
import org.iitk.livetv.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 22Sep2012
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
			String m_url=RuntimeObject.getController().getMasterServerUrl()+"/ProcessRequest?req=getISList";
                        if(!(m_url.equals(""))){
				Vector indexServerList=httpsUtil.getvectorMessage(m_url,"null");
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
		boolean flag=false;
  		try{
			if(indexServer!=null || usr_name!=null || password!=null){
  				String usr = "usr="+URLEncoder.encode(usr_name, "UTF-8");
      				String pass= "pass="+URLEncoder.encode(password, "UTF-8");
      				String ip="ip="+URLEncoder.encode((String)InetAddress.getLocalHost().getHostAddress(),"UTF-8");
				
				String req_url=indexServer+"/ProcessRequest?req=login&"+usr+"&"+pass+"&"+ip;
                                loginResult=httpsUtil.getvectorMessage(req_url,"null");
				
				if(loginResult.size()>0){
						String[] result=((loginResult.get(0)).toString()).split(",");
						if(result[0].equals("login_successful")){
						// store indexServer name in local variable for later use.
						ClientObject.getController().setIndexServerName(indexServer);
						ClientObject.getController().setUserID(result[0]);
        	                                flag=true;
  					}
				}else{
					System.out.println("Error in index server");
					return flag;
				}
			}else{
				System.out.println("No Sufficient Arguments to call connectToIndexServer()");
				return flag;
			}

         	}catch(Exception e){
                	System.out.println("Error at connectToIndexserver()in HttpsConnection : "+e.getMessage());
                }
		return flag;
        }

	public Vector getCategoryList(){
		try {
        	        String requestURL=ClientObject.getController().getIndexServerName()+"/ProcessRequest?req=getCategory";	
                	return httpsUtil.getvectorMessage(requestURL,"");
		}catch(Exception e){System.out.println("Error on getting category list");}
                return null;
      	}
		
	
}
