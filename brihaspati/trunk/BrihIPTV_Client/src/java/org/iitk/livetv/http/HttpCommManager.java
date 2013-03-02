package org.iitk.livetv.http;

/**
 * HttpsConnection.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012-2013 ETRG,IIT Kanpur.
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
//	private ClientObject clientObj=ClientObject.getController();

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
				loginResult=new Vector();
                                loginResult=httpsUtil.getvectorMessage(req_url,"null");
				if(loginResult.size()>0){
						String[] result=((loginResult.get(0)).toString()).split(",");
						if(((loginResult.get(0)).toString()).equals("login_successful")){
						ClientObject.getController().setIndexServerName(indexServer);
						ClientObject.getController().setUserID((loginResult.get(1)).toString());
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
		
	public boolean putChannelDetails(String category, String channel_name, String channel_desc, String userID, String ch_ip, String ch_port){
		boolean value=false;
		try{
			if((!(category.equals("")))&&(!(channel_name.equals("")))&&(!(channel_desc.equals("")))&&(!(userID.equals("")))){
				String cat="category="+URLEncoder.encode(category, "UTF-8");
				String name="ch_name="+URLEncoder.encode(channel_name, "UTF-8");
				String desc="ch_desc="+URLEncoder.encode(channel_desc, "UTF-8");
				String id="usr_id="+URLEncoder.encode(userID, "UTF-8");
				String ip="ip="+URLEncoder.encode(ch_ip, "UTF-8");
				String port="port="+URLEncoder.encode(ch_port, "UTF-8");
				String status="status="+URLEncoder.encode("0", "UTF-8");
				String reqURL=ClientObject.getController().getIndexServerName()+"/ProcessRequest?req=addChannel&"+cat+"&"+name+"&"+desc+"&"+id+"&"+ip+"&"+port+"&"+status;
				boolean result=httpsUtil.getIndexingMessage(reqURL);
				return result;
			}else{
				System.out.println("No Sufficient Arguments to call connectToIndexServer()");
				return value;
			}
		}catch(Exception e){System.out.println("Error at connectToIndexserver()in HttpsConnection : "+e.getMessage());}
		return value;
	}

	public Vector getChannelList(String category){
		try{
			String cat="category="+URLEncoder.encode(category, "UTF-8");
			String reqUrl=ClientObject.getController().getIndexServerName()+"/ProcessRequest?req=getChannelList&"+cat;
			return httpsUtil.getvectorMessage(reqUrl, "noChannel");

		}catch(Exception e){System.out.println("Error in getChannelList() :"+e.getMessage());}
		return null;
	}	
}
