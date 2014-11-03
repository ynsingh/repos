package org.iitk.brihaspatisync;

/*@(#)ProcessRequest.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2013.
 * All Rights Reserved.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import java.net.InetAddress;

import org.apache.torque.Torque;
import org.apache.torque.util.Criteria;

import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.util.ServerUtil;

import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.om.UrlConection;
import org.iitk.brihaspatisync.om.UrlConectionPeer;


 /**
  * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
  * @author <a href="mailto:@arvindjss17@gmail.com"> Arvind Pal </a>
  */

public class ProcessRequest extends HttpServlet {
	
	private Torque set=null;
	private ServletContext context=null;
/*
   Get method also lead to invocation of Post method of http mechanism. The
request will contain the method to be executed. These methods are usually sent
from clients, reflectors 
*/	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		if(set==null) {
			try {
/**
 * The context has been set to call getRealPath() of files. It is set in static
 * references in the objects where it is needed to get the actual path of
 * specific files.
 */
				set=new Torque();
				context = getServletContext();
                                set.init(context.getRealPath("Torque.properties"));
				ServerLog.setContext(context);
				ReflectorManager.setContext(context);
				PeerManager.setContext(context);
                        	ReflectorStatusManager.setContext(context);        
               		} catch(Exception error){ ServerLog.log("Exception in\\
ProcessRequest class during the context configuration of various objects."+error.getMessage()); }
		}
	
/*

ReflectorManager ->   purge the reflectorStatus.xml to remove all the reflectors whose registration
referesh is not received in last five minute.
TBD - code for this part to be written.
*/
/*
   The request string is retreived.
*/	
		String reqType=request.getParameter("req");
/*
   The response to the client or reflector is to be written as text.
*/
		PrintWriter out = response.getWriter();
/*
   The reflector_logout message handling by indexing server.
*/
		if(reqType.equals("reflector_logout")) {

/**
 * call to removePeer method to remove all entries for the calling reflector
 * from Reflector.xml and from ReflectorStatus.xml file
 * 
 * In case of reflector sending the reflector_logout message from behind the
 * firewall, both connection's other end IP address and local IP address
 * reported by reflector (most likely private IP) should be used to search the
 * entry in the ReflectorStatus.xml, which is to be removed.
 * TBD - the corresponding code is to be added below.
 */
			try {
	                        String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
        	                String message =ReflectorManager.removePeer(reflectorIP);
                        	response.setContentLength(message.length());
	                        out.println(message);
        	                out.flush();
                	        out.close();
			} catch(Exception e) { ServerLog.log("Exception in ProcessRequest class for reflector logout loop "+e.getMessage()); }
                }
/*
 * Reflector registration sent by reflector for adding his entry in the
 * ReflectorStatus.xml file on indexing server.
 */
		 else if(reqType.equals("reflector_registration")) {
			
/**

get the IP address from connection.
retrieve the IP address from the request.
ReflectorManager ->  update/add reflector with current time stamp.
save the record update in the log file.
TBD - code to be updated as per the above logic.
*/
			try {
				String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
				String status=request.getParameter("status");
				String message =ReflectorManager.register(reflectorIP);
				response.setContentLength(message.length());
                	        out.println(message);
                        	out.flush();
	                        out.close();
			} catch(Exception e ) { ServerLog.log("Exception in ProcessRequest Class in reflector registration"+e.getMessage()); }
			
		}
/*
 * 
 */
		else if(reqType.equals("getjnlp")) {
			/**
			 * This block of code is used to to create urlbrihaspatisync.jnlp which is used to join a session directly.
			 */
                        try {
				String key=request.getParameter("key");
				File filepath=new File(context.getRealPath("urlbrihaspatisync.jnlp")); 
				Criteria crit=new Criteria();
				crit.add(UrlConectionPeer.SESSION_KEY,Integer.parseInt(key));
                                java.util.List v=UrlConectionPeer.doSelect(crit);
				
				String usr=null;
				String l_name=null;
				String lect_id=null;
				String ins_std=null;
				for(int i=0;i<v.size();i++)
                                {
                                        UrlConection element=(UrlConection)v.get(i);
                                        lect_id =Integer.toString(element.getLectureid());
                                        ins_std =element.getRole();
                                        usr =element.getLoginId();
                                        l_name =element.getGroupName();
                                }
					
				
				if(filepath.isFile()) {
					filepath.delete();
				}
				if(v.size() > 0) {
					String url=null;
					try {
						url=request.getRequestURL().toString();
						url=url.substring(0,url.lastIndexOf("/"));	
					} catch (Exception ex) { ServerLog.log("Exception in getting url in ProcessRequest class"); }
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(filepath,true));
        	        		dos.writeBytes( "<?xml version=\"1.0\" encoding=\"utf-8\"?><jnlp  spec=\"1.0+\"  codebase=\"https://202.141.40.216:8443/brihaspatisync_client/jnlp\"  href=\"brihaspatisync.jnlp\"> <information> <title> Brihaspatisync Client </title> <vendor>IIT Kanpur</vendor>  <homepage href=\"http://www.brihaspatisolutions.co.in\"/> <description>Brihaspatisync</description>  <description kind=\"short\">Brihaspatisync Client </description>  <icon href=\"images/info.gif\"/>  <icon kind=\"splash\" href=\"images/Title.jpg\"/>  <!-- <offline-allowed/> -->        </information>           <security>  <all-permissions/>  </security>   <resources>  <j2se version=\"1.0+\" />  <property name=\"sun.java2d.d3d\" value=\"false\"/> <jar href=\"brihaspatisync.jar\"/> </resources> <application-desc main-class=\"org.bss.brihaspatisync.Client\"> <argument> "+usr+"</argument> <argument> "+lect_id+"</argument><argument> "+l_name+"</argument> <argument>"+url+"</argument> <argument> "+ins_std+"</argument> </application-desc> </jnlp>"); 
					dos.flush();
				        dos.close();
                                	RequestDispatcher rd= context.getRequestDispatcher("/brihaspati.html");
					rd.include(request, response);
				}
				else {
                                	RequestDispatcher rd= context.getRequestDispatcher("/checkuserbrihaspati.html");
					rd.include(request, response);
				}
                                out.flush();
                                out.close();
                        } catch(Exception e) { ServerLog.log("Exception in writting jnlp file in ProcessRequest class"+e.getMessage()); }
                } else if(reqType.equals("http")) {
			/**
			 * This block of code is used to for Proxy Users
			 **/
			try {
				InetAddress clientIP  = InetAddress.getByName(request.getRemoteAddr());
				String message=clientIP.toString();
				response.setContentLength(message.length());
                        	out.println(message);
	                        out.flush();
        	                out.close();
			} catch(Exception e) { ServerLog.log("Exception in condition check http in ProcessRequest class"+e.getMessage()); }
			
		}
		else if(reqType.equals("login")) {
			/**
			 * This block of code is used to authanticate user when clients make login .
			 **/
			try {
				String userName=request.getParameter("usr");
		                String userPassword=request.getParameter("pass");
        			String ipAddress=request.getParameter("ip");
				String nodeType=request.getParameter("fw-node");
				String message=ProcessRequestMethods.loginAuthorised(userName,userPassword);
				if(message.equals("loginfailed")) {
					response.setContentLength(message.length());
               				out.println(message);
                       			out.flush();
	                       		out.close();
				} 
				else {
					/**
					 * Send the userid and  session key of the client for the authentication purpose each time 
					 * when the request is coming from the client to the server This session key is created by
					 * using the server util. 
					 **/
              				int key=ServerUtil.generateSessionKey();
	       		                message=message+"\n"+Integer.toString(key);
					/**
					 * send server date to client user for synchrozise clock with this server. 
					 **/
					message=message+"\n"+ServerUtil.getCurrentDate("");
					response.setContentLength(message.length());
                                	out.println(message);
	                                out.flush();
        	       	        	out.close();
				}//else	
			} catch(Exception e) { ServerLog.log("Exception in login in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("logout")) {
			/**
			 * This block of code is used to logout user
			 * and remove entry from lecture id xml according to lecture id
			 * and also sets client's status to deactive.
			 **/
			try {
				String username=request.getParameter("username");
				String lectID=request.getParameter("lectID");
				String ipAddress=InetAddress.getByName(request.getRemoteAddr()).toString();

				/** Remove Entry from the peer list from LecturePeer.xml */
				if(!(lectID.equals(""))) {
					PeerManager.removePeer(lectID,username);
					ReflectorStatusManager.updateStatusPeer(ipAddress.replace("/",""));
				}
				out.println("Successfull");	
				out.flush();
        	                out.close();
			} catch(Exception e) { ServerLog.log("Exception in logout in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("getCourse")) {
			/**
			 * After client's do login send courses to client according to his Role. 
			 **/
			try {
				int id=Integer.parseInt(request.getParameter("id"));
				int role=Integer.parseInt(request.getParameter("role"));
				Vector result=ProcessRequestMethods.getCourse(id,role);
				int resultSize=result.size();
				String message="";
				if(resultSize!=0) {
					for(int i=0;i<resultSize;i++) {
						if(i==0)
							message=result.elementAt(i).toString();
						else
							message=message+","+result.elementAt(i).toString();
					}
					response.setContentLength(message.length());
        	              		out.println(message);
                	        	out.flush();
                        		out.close();
				}
				else {
					message="noCourse";
                                	response.setContentLength(message.length());
                                	out.println(message);
                                	out.flush();
                                	out.close();
				}
			} catch(Exception e) { ServerLog.log("Exception in getCourse in ProcessRequest class"+e.getMessage()); }
		} 
		else if(reqType.equals("getLectureInfo")) {
			/**
			 * This condition is executed to show Announced Lecture Information on client's end.
			 **/	
			try {
	                        String lecture_id=request.getParameter("l_id");
        	                String message=ServerUtil.getLectureInfo(lecture_id);
                	        if(!message.equals("")) {
                        	        response.setContentLength(message.length());
                                	out.println(message);
	                                out.flush();
        	                        out.close();
                	        } else {
                        	        message="noLecture";
                                	response.setContentLength(message.length());
	                                out.println(message);
        	                        out.flush();
                	                out.close();
                        	}
			} catch(Exception e) { ServerLog.log("Exception in getting lecture info in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("getSession")) {
			/**
			 * List of sessions in a particular course are sent to client.
			 */
			try {
				String courseName=request.getParameter("cn");
				String message=ServerUtil.getSessionList(courseName);
				if(!message.equals(""))	{
                	        	response.setContentLength(message.length());
	                	        out.println(message);
        	                	out.flush();
	                	        out.close();
				} else {
					message="noLecture";
                        	        response.setContentLength(message.length());
                                	out.println(message);
                                	out.flush();
					out.close();
				}
			} catch(Exception e) { ServerLog.log("Exception in getting session list in ProcessRequest class"+e.getMessage()); }	
		} 
		else if(reqType.equals("putLecture")) {
			/**
                	 * When client announce a Lecture all announced parameters has been
                	 * retrieved.Call to putLecture method to insert these values to Lecture Database.
                	 **/
			try {
				String lect_id=request.getParameter("lect_id");
        	              	String lectGetParameter=request.getParameter("lectGetParameter");
                	      	String lectUserName=request.getParameter("lectUserName");
                      		String lectCouseName=request.getParameter("lectCouseName");
	                      	String lectName=request.getParameter("lectName");
        	              	String lectInfo=request.getParameter("lectInfo");
                	      	String lectNo=request.getParameter("lectNo");
                      		String lectDate=request.getParameter("lectDate");
	                      	String lectTime=request.getParameter("lectTime");
        	              	String lectDuration=request.getParameter("lectDuration");
                	      	String lectAudio=request.getParameter("lectAudio");
                      		String lectVedio=request.getParameter("lectVedio");
	                      	String lectWhiteBoard=request.getParameter("lectWhiteBoard");
        	              	String mailsend_permission=request.getParameter("lectmail_send");
				StringBuffer url =null;
				try {
					url=request.getRequestURL();
				} catch(Exception e) { ServerLog.log("Exception in getting url in ProcessRequest class"+e.getMessage()); }
				String message=ProcessRequestMethods.putLecture(context,url,lect_id,lectGetParameter,lectUserName,lectCouseName,lectName,lectInfo,lectNo,lectDate,lectTime,lectDuration,lectAudio,lectVedio,lectWhiteBoard,mailsend_permission);                       
                	       	out.println(message);
                        	out.flush();
				out.close();
			} catch(Exception e) { ServerLog.log("Exception in putLecture in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("getTimeforLecture")) {
			
			/**
			 * Send response to client to know if a lecture is running for stop all thread.
			 **/	
			try {
				String message=ServerUtil.getSystemDateTime();
        	                response.setContentLength(message.length());
                	        out.println(message);
                        	out.flush();
	                        out.close();
			} catch(Exception e) { ServerLog.log("Exception in get time for lecture in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("join")) {
			
			/**
                	* When client joins a session different parameters are retreived
                	* LecturePeer.xml is written using these parameter
                	* if reflector is not running message is sent back to client for it.
                	**/
			try {
				String sessionid = request.getParameter("lect_id");
				String publicip =(InetAddress.getByName(request.getRemoteAddr())).toString();
				publicip=publicip.replaceAll("/","");
				String privateip=request.getParameter("privateip");
				String user=request.getParameter("user");
				String status=request.getParameter("status");
				String role=request.getParameter("role");
				String logintime=ServerUtil.getSystemDateTime();
                                logintime=logintime.substring(4,20);
				String proxy="NO";	
				String message=ReflectorStatusManager.Register(sessionid,publicip,privateip);  
				if((!message.equals("UnSuccessfull")) && (!message.equals("Reflector is not available !!")) && (!message.equals("Reflector have insufficient Load !!")) ) {
					String ref_ip=message;
					if(ref_ip.startsWith("current")) {
						String str1[]=ref_ip.split(",");
                	                        ref_ip=str1[0].replaceAll("current","");
						str1=null;
						String first_lst_name=ProcessRequestMethods.getFullName(user);	
						String msg=PeerManager.createPeer(sessionid,publicip,user,role,status,publicip,proxy,ref_ip,first_lst_name);
					}
       				}
				String av_status=ServerUtil.getAVStatus(sessionid);
				message	= message+av_status;	
				response.setContentLength(message.length());
                        	out.println(message);
                        	out.flush();
                        	out.close();
			} catch(Exception e) { ServerLog.log("Exception in getting input string in ProcessRequest class"+e.getMessage()); }
                }
		else if(reqType.equals("userlist")) {
			
			/**
			 * gets lecture id from client and retrieve users from
                	 * LecturePeer.xml file.Sends user list to client.
			 **/
			try {
				String lect_id=request.getParameter("lect_id");
				File filepath=new File(context.getRealPath(lect_id+".xml"));
				String message="";
				if(filepath.exists()) {
					/** Get Userlist in Vector for given Lecture Id */
					Vector result=PeerManager.getPeerList(lect_id);
					int resultSize=result.size();
                        		if(resultSize >0) {
						for(int i=0;i<resultSize;i++) {
							if(message.equals(""))
        	                                        	message=result.elementAt(i).toString();
		                                        else
        	        	                                message=message+","+result.elementAt(i).toString();
						}
                        	      		response.setContentLength(message.length());
						out.println(message);
		                        	out.flush();
		                                out.close();
        		                } 
				} else {
                	               	message="noUser";
                        	        response.setContentLength(message.length());
                                	out.println(message);
	                                out.flush();
       	 	                        out.close();
                	        }
			} catch(Exception e) { ServerLog.log("Exception in messsage for user list in ProcessRequest class"+e.getMessage()); }
		
		}
		else if(reqType.equals("cancleLecture")) {
		
			 /**
			 * If instructor cancels a lecture all values are deleted from Lecture Table.
			 **/
		       try {
				String str=request.getParameter("lectValue");
				Criteria crit=new Criteria();
                                crit.add(LecturePeer.LECTUREID,str);
		                LecturePeer.doDelete(crit);
        	                String message="Successfull";
                        	response.setContentLength(message.length());
                        	out.println(message);
                       		out.flush();
                        	out.close();
			} catch(Exception e) { ServerLog.log("Exception in Cancle Lecture for user list in ProcessRequest class"+e.getMessage()); }
		}
		else if(reqType.equals("Permissions")) {

			/**
			* This block of code handles with Get-Permission and Allow-Permission for using all tools.
			**/
                       try {
				String ipAddress=InetAddress.getByName(request.getRemoteAddr()).toString();
				String lect_id=request.getParameter("lectID");
				String message="";
				File filepath=new File(context.getRealPath(lect_id+".xml"));
                                if(filepath.exists()) {
					String userAction=request.getParameter("userAction");
					String login=request.getParameter("loginName");
					login=login.replaceAll("loginName=","");
					message=PeerManager.updateStatus(userAction,login,lect_id);
				} 
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			} catch(Exception e) { ServerLog.log(" Exception in cancle Lecture for user list in ProcessRequest class"+e.getMessage()); }
                }
		else if(reqType.equals("GetReflectorStatusXML")) {
			/**
               		 *This condition is executed for Network Management System to
                	 *to send ReflectorStatus.xml file.
                	*/

			try {
				FileInputStream fstream = new FileInputStream(context.getRealPath("ReflectorStatus.xml"));
				DataInputStream in = new DataInputStream(fstream);
  				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String message="";
  				String strLine;
				while ((strLine = br.readLine()) != null)   {
					message=message+strLine;
  				}
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
				in.close();	
			} catch(Exception e) { ServerLog.log("Exception in GetReflectorStatusXML in ProcessRequest class"+e.getMessage()); }
		} 
		else if(reqType.equals("GetReflectorXML")) {
			/**
                	*This condition is executed for Network Management System to
	                *to send Reflector.xml file.
        	        */
                        try {
                                FileInputStream fstream = new FileInputStream(context.getRealPath("Reflector.xml"));
                                DataInputStream in = new DataInputStream(fstream);
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                String message="";
                                String strLine;
                                while ((strLine = br.readLine()) != null) {
                                        message=message+strLine;
                                }
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
                                in.close();
                        } catch(Exception e) { ServerLog.log("Exception in GetReflectorXML in ProcessRequest class"+e.getMessage()); }
                }
		else if(reqType.equals("GetLectureXml")) {
			/**
	                *This condition is executed for Network Management System to
        	        *to send LecturePeer.xml file.
                	*/

                        try {
				String lectID=request.getParameter("lectID");
                                FileInputStream fstream = new FileInputStream(context.getRealPath(lectID+".xml"));
                                DataInputStream in = new DataInputStream(fstream);
                                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                                String message="";
                                String strLine;
                                while ((strLine = br.readLine()) != null) {
                                        message=message+strLine;
                                }
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
                                in.close();
                        } catch(Exception e) { ServerLog.log("Exception in GetReflectorXML in Processrequest class"+e.getMessage()); }
                }
		
		try {
			/**
	                 *call to LectureHandler to remove sessionid.xml file  and delete entry from reflector.xml according
	                 *to the  end of the durataion of session.
        	         */
		
                        org.iitk.brihaspatisync.util.ReflectorHandler.getController().LectureHandler(context);
                } catch(Exception e) { ServerLog.log("Exception in removing sessionid xml in ProcessRequest class"+e.getMessage()); }
	}//end of post method
} //end of class



