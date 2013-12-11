package org.bss.brihaspatisync.reflector;

/**
 * RegisterToIndexServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Timer;
import java.util.Vector;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.bss.brihaspatisync.reflector.util.HttpsUtil;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.util.CertificateVerify;

import org.bss.brihaspatisync.reflector.network.nms.PostNmsServer;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListTimer;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> 
 */


public class RegisterToIndexServer {

	private static Timer UL_Timer=null;
	private static String indexServer="";

	/**
	 * Get user list from reflector. 
	 */  	

	public static void getUserListToMasterServer(String m_url,String course_id) {
		try {
                      	URL indexurl = new URL(m_url);
                        HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection != null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String temp="",str="";
				try {
					while((str=in.readLine())!=null){
						if(temp.equals(""))
							temp=str;	
						else 
							temp=temp+","+str;	
					}
					UserListUtil.getContriller().addDataForVector(course_id,temp);	
				} finally {
					if(in != null) {
                                        	in.close();
                                        }
                               	}
			} else 
                        	System.out.println("Check your Network Connection failed in iserver ");
		} catch(Exception e) { 
			System.out.println("Exception in RegisterToIndexServer class,Check your Network Connection or try again");
		}
	}

	/**
         * Instantiate connection to master server to retrieve the secondry indexing servers' list./ 
         * If secondary indexing servers' list is found from master server, pass it to make connection 
	 * with indexserver otherwise it return from main method.
         */

        public static Vector connectToMasterServer() {
		Vector server_ip_vector=new Vector();
                String m_url=CertificateVerify.getController().getMasterUrl().trim()+"req=getISList";;
                if(!(m_url.equals(""))) {
                	try {
                        	URL indexurl = new URL(m_url);
                                HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                if(connection != null) {
                                	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                        String str="";
                                        try {
                                           	while((str=in.readLine())!=null) {
                                                	if(!(str.equals(""))) 
                                                        	server_ip_vector.add(str);
                                               	}
                                       	} finally {
                                        	if(in != null) 
                                                	in.close();
                                   	}
                             	} else
                                	System.out.println("Check your Network Connection failed ");
                      	} catch(Exception e){
                        	System.out.println("Exception in RegisterToIndexServer class on connectToMasterServer() mehod "+e.getMessage());
                     	}
		}
                return server_ip_vector;
	}

	/**
	 * Instantiate connection with index server with "inactive" status.
	 */
	
	public static String connectToIndexServer() {
              	try {
                	indexServer=RuntimeDataObject.getController().getindexServerAddr();
		      	if(!indexServer.equals("")) {
                                String status= "status="+URLEncoder.encode("active", "UTF-8");
                                URL indexurl = new URL(indexServer+"req=reflector_registration&"+status);
                                HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                if(connection !=null) {
                                        if(bufferReader(connection.getInputStream())) {
						startThreads(); 
						return "Successfully";
					} else {
						System.out.println("Reflector already started !! ");
						return "fail_registeration";	
                                        } 
                                } else
                                        System.out.println("Check your Network Connection or try again");
                	}else {
                        	System.out.println("Indexing server ip could not find !! ");
				return "fail_registeration";
			}
		} catch(Exception e) {
			System.out.println("Exception in RegisterToIndexServer Class on connectToIndexServer() method "+e.getMessage());
                }
		return "fail_registeration";
	}

	/**
	 * This method is used to change status for request 
	 */  	
	public static void requestToChangeStatus(String request_status) {
            	try {
                	String str[]=request_status.split(",");
                        String lectid ="lectID="+URLEncoder.encode(str[0].trim(),"UTF-8");
                        String user="loginName="+URLEncoder.encode(str[1].trim(),"UTF-8");
                        String action="userAction="+URLEncoder.encode(str[2].trim(),"UTF-8");
                        URL indexurl = new URL(indexServer+"req=Permissions&"+lectid+"&"+user+"&"+action);
                        HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection !=null) {
                        	if(!bufferReader(connection.getInputStream())) 
                                	System.out.println("RegisterToIndexServer class change status faild !! ");
                     	} else
	                        System.out.println("Check your Network Connection or try again");
        	} catch(Exception e) {
			System.out.println("Exception in RegisterToIndexServer Class on requestToChangeStatus method "+e.getMessage());
              	}
        }
	
	/**
         * This method is used to remove user from userlist in iserver  
         */
	
        public static void request_For_RemoveUser(String lectid,String user) {
                try {
                        lectid ="lectID="+URLEncoder.encode(lectid,"UTF-8");
                        user="username="+URLEncoder.encode(user,"UTF-8");
                        URL indexurl = new URL(indexServer+"req=logout&"+lectid+"&"+user);
                        HttpsURLConnection connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection !=null) {
                                if(!bufferReader(connection.getInputStream()))
                                        System.out.println("RegisterToIndexServer class change status faild !! ");
                        } else
                                System.out.println("Check your Network Connection or try again");
                } catch(Exception e) {
                        System.out.println("Exception in RegisterToIndexServer Class on requestToChangeStatus method "+e.getMessage());
                }
        }

	
	private static boolean bufferReader(InputStream ins ) {
		boolean flag=false;
		try {
			BufferedReader in=null;
			try {		
				in = new BufferedReader(new InputStreamReader(ins));
                       		String str=in.readLine();
				if((str.equals("Successfull")) || (str.equals("successfull")) ) {
					flag=true;		
				}
			} finally {
        	        	if(in != null){
					in.close();
				}
              		}
		} catch(Exception e) { System.out.println("Exception in RegisterToIndexServer Class in bufferReadeR() "+e.getMessage());}
		return flag;
	} 	

	protected static String startThreads(){
		try{
			org.bss.brihaspatisync.reflector.network.singleport.SinglePortServer.getController().start();	
                        UL_Timer=new Timer(true);
               	        UL_Timer.schedule(UserListTimer.getController(),01,60*60*2);
			System.out.println("timer start successfully");
			return "start_ok";
	       	} catch(Exception e) { System.out.println("Exception in startThreads method of PeerManager class "+e.getMessage()); }
		return "";
	}
	
	protected static Timer getTimer(){
                return UL_Timer;
        }

}	
	

