package org.iitk.brihaspatisync;

/*@(#)ProcessRequest.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2012.
 * All Rights Reserved.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Timer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import java.text.ParsePosition;

import java.net.InetAddress;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import org.apache.torque.Torque;
import org.apache.torque.om.NumberKey;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspatisync.PeerManager;
import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.util.ServerUtil;
import org.iitk.brihaspatisync.util.EncryptionUtil;
import org.iitk.brihaspatisync.schedular.TimerOfDatabase;


import org.iitk.brihaspatisync.om.Sessionaddress;
import org.iitk.brihaspatisync.om.SessionaddressPeer;
import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.om.Courses;
import org.iitk.brihaspatisync.om.CoursesPeer;
import org.iitk.brihaspatisync.om.TurbineUser;
import org.iitk.brihaspatisync.om.TurbineUserGroupRole;
import org.iitk.brihaspatisync.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspatisync.om.TurbineUser;
import org.iitk.brihaspatisync.om.TurbineUserPeer;
import org.iitk.brihaspatisync.om.UrlConectionPeer;
import org.iitk.brihaspatisync.om.UrlConection;

import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.apache.turbine.services.security.torque.om.TurbineGroup;

import java.sql.Date;
 /**
  * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
  * @author <a href="mailto:@arvindjss17@gmai..com"> Arvind Pal </a>
  */

public class ProcessRequest extends HttpServlet {
	
   	private Timer dbTimer=null;
	private static Hashtable table;
	private Torque set=null;
	private ServletContext context=null;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		if(set==null){
			try{
				set=new Torque();
				context = getServletContext();
				ServerLog.getController().setContext(context);
				ReflectorManager.getController().setContext(context);
				PeerManager.getController().setContext(context);
                        	ReflectorStatusManager.getController().setContext(context);        
				String tempFileName =context.getRealPath("Torque.properties");
                                set.init(tempFileName);
		      		dbTimer=new Timer(true);
				ServerLog.getController().Log("database connection ");
               		}catch(Exception dberror){ServerLog.getController().Log("Error in database connection "+dberror.getMessage());}
		}
		
		try{
  			dbTimer.schedule(TimerOfDatabase.getController(), 1000, 60*60*1000);
 		}catch(Exception e){ ServerLog.getController().Log("Error in database Scheduler "+e.getMessage());}

		try {
			org.iitk.brihaspatisync.util.ReflectorHandler.getController().LectureHandler(context);
		}catch(Exception e){}
			
		String reqType=request.getParameter("req");
		PrintWriter out = response.getWriter();

		if(reqType.equals("reflector_logout")){
                        String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
                        String msg =ReflectorManager.getController().removePeer(reflectorIP);
                        String message=msg;
                        response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
                }
                           
		/**
		 * This block of code is used to register a reflector on this server with ipAddress of reflector 
		 * and initial status which return success or failure message.
		 */
		
		if(reqType.equals("reflector_registration")){

			String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
			String status=request.getParameter("status");
			String message =ReflectorManager.getController().Register(reflectorIP,status);
			if(!message.equals("UnSuccessfull")){
                                message="successfull";
                        }
			response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
			
		}else if(reqType.equals("getjnlp")){
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
					
				
				if(filepath.isFile()){
					filepath.delete();
				}
				if(v.size()>0){
					String url=null;
					try {
						url=request.getRequestURL().toString();
						url=url.substring(0,url.lastIndexOf("/"));	
					} catch (Exception e) { ServerLog.getController().Log("Error in get url ");}
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(filepath,true));
        	        		dos.writeBytes( "<?xml version=\"1.0\" encoding=\"utf-8\"?><jnlp  spec=\"1.0+\"  codebase=\"https://202.141.40.216:8443/brihaspatisync_client/jnlp\"  href=\"brihaspatisync.jnlp\"> <information> <title> Brihaspatisync Client </title> <vendor>IIT Kanpur</vendor>  <homepage href=\"http://www.brihaspatisolutions.co.in\"/> <description>Brihaspatisync</description>  <description kind=\"short\">Brihaspatisync Client </description>  <icon href=\"images/info.gif\"/>  <icon kind=\"splash\" href=\"images/Title.jpg\"/>  <!-- <offline-allowed/> -->        </information>           <security>  <all-permissions/>  </security>   <resources>  <j2se version=\"1.0+\" />  <property name=\"sun.java2d.d3d\" value=\"false\"/> <jar href=\"brihaspatisync.jar\"/> </resources> <application-desc main-class=\"org.bss.brihaspatisync.Client\"> <argument> "+usr+"</argument> <argument> "+lect_id+"</argument><argument> "+l_name+"</argument> <argument>"+url+"</argument> <argument> "+ins_std+"</argument> </application-desc> </jnlp>"); 
					dos.flush();
				        dos.close();
                                	RequestDispatcher rd= context.getRequestDispatcher("/brihaspati.html");
					rd.include(request, response);
				}else{
                                	RequestDispatcher rd= context.getRequestDispatcher("/checkuserbrihaspati.html");
					rd.include(request, response);
				}
                                out.flush();
                                out.close();
                        }catch(Exception e){}
                }

		/**
		 * This block of code is used to change the status of reflector which decide that is this reflector 
		 * is currently ready to accespt aclient connection or not
		 * this is used ip address of reflector and status request to change it value in xml file.
		 */
			
		else if(reqType.equals("reflector_status")){
			
			String reflectorIP=InetAddress.getByName(request.getRemoteAddr()).toString();
			String status=request.getParameter("status");
                        String message =ReflectorManager.getController().updateStatus(reflectorIP,status);
			if(!message.equals("UnSuccessfull")){
                        	message="successfull";
			}
                        response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
		}	
		
		else if(reqType.equals("http")){
			
			InetAddress clientIP  = InetAddress.getByName(request.getRemoteAddr());
			String message=clientIP.toString();
			response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
			
                }else if(reqType.equals("tcp")){
			
			/**
			message="start_TCP_client";
                        response.setContentLength(message.length());
			out.println(message);
                        out.flush();
	                out.close();
			if(TCPServer.getController().serverThread!=null){
				TCPServer.getController().stop();
			}
			TCPServer.getController().start();			      		
			*/
				
		}else if(reqType.equals("login")){
			String userName=request.getParameter("usr");
	                String userPassword=request.getParameter("pass");
        		String ipAddress=request.getParameter("ip");
			String nodeType=request.getParameter("fw-node");
			String message=loginAuthorised(userName,userPassword);
			if(message.equals("loginfailed")){
				response.setContentLength(message.length());
               			out.println(message);
                       		out.flush();
                       		out.close();
			}else{
				/** Send the userid and  session key of the client for the authentication purpose each time 
				    when the request is coming from the client to the server This session key is creating by
				    using the server util.After sending the session key to the user also store this session
				    key as a key with the ipAddress of the client in the hash table for the handling
				    of logger,task which is related with the Handraise,query handling in future and
				    for the single session 
				*/
              			int key=ServerUtil.getController().generateSessionKey();
       		                message=message+"\n"+Integer.toString(key);
				/* send server date to client user for synchrozise clock with this server. */
				message=message+"\n"+ServerUtil.getController().getCurrentDate("");
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
               	        	out.close();
                	        table=new Hashtable();
				table.put(ipAddress,new Integer(key));
			}//else

		}else if(reqType.equals("logout")){
			String ipAddress=InetAddress.getByName(request.getRemoteAddr()).toString();
			String reflector=request.getParameter("reflector");
			String username=request.getParameter("username");
			/** Remove Entry from the peer list from LecturePeer.xml */
			if(!(reflector.equals(""))){
				String lectID=request.getParameter("lectID");
				PeerManager.getController().removePeer(lectID,username);
				ReflectorStatusManager.getController().updateStatusPeer(username);
			}
			out.flush();
                        out.close();
		}else if(reqType.equals("getCourse")){
			int id=Integer.parseInt(request.getParameter("id"));
			int role=Integer.parseInt(request.getParameter("role"));
			Vector result=getCourse(id,role);
			int resultSize=result.size();
			String message="";
			if(resultSize!=0){
				for(int i=0;i<resultSize;i++){
					if(i==0)
						message=result.elementAt(i).toString();
					else
						message=message+","+result.elementAt(i).toString();
				}
				response.setContentLength(message.length());
        	              	out.println(message);
                	        out.flush();
                        	out.close();
			}else{
				message="noCourse";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			}

		}else if(reqType.equals("getLectureInfo")) {
                        String lecture_id=request.getParameter("l_id");
                        String message=ServerUtil.getController().getLectureInfo(lecture_id);
                        if(!message.equals("")) {
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
                        }else{
                                message="noLecture";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
                        }
		}else if(reqType.equals("getSession")){
			
			String courseName=request.getParameter("cn");
			
			String message=ServerUtil.getController().getSessionList(courseName);
			if(!message.equals(""))	{
                        	response.setContentLength(message.length());
	                        out.println(message);
        	                out.flush();
                	        out.close();
			}else{
				message="noLecture";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
				out.close();
			}
			
		} else if(reqType.equals("putLecture")){
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
			StringBuffer url =null;
			try {
				url=request.getRequestURL();
			}catch(Exception e){}
			String message=putLecture(url,lect_id,lectGetParameter,lectUserName,lectCouseName,lectName,lectInfo,lectNo,lectDate,lectTime,lectDuration,lectAudio,lectVedio,lectWhiteBoard);                        
                       	out.println(message);
                        out.flush();
			out.close();
		}else if(reqType.equals("getTimeforLecture")) {
			String message=ServerUtil.getController().getSystemDateTime();
                        response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
		}else if(reqType.equals("join")){
			try{
				String lect_id=request.getParameter("lect_id");
				InetAddress ipaddress=InetAddress.getByName(request.getRemoteAddr());
				String publicIP=ipaddress.toString();
				String user=request.getParameter("user");
				String status=request.getParameter("status");
				String role=request.getParameter("role");
				String privateIP=ipaddress.toString();
				String proxy="NO";	
				String message=ReflectorStatusManager.getController().Register(user,lect_id,role);  
				if((!message.equals("UnSuccessfull")) && (!message.equals("Reflector is not available !!")) && (!message.equals("Reflector have insufficient Load !!")) ){
					HandRaisePortHandler.getController().handRaisePortHandler(lect_id);
					String ref_ip=message;
					if(ref_ip.startsWith("current")){
						String str1[]=ref_ip.split(",");
                	                        ref_ip=str1[0].replaceAll("current","");
						str1=null;
						String first_lst_name="";
						{
							try {
								Criteria crit=new Criteria();
		                                                crit.add(TurbineUserPeer.LOGIN_NAME,user);
                	        	                        List u=TurbineUserPeer.doSelect(crit);
								for(int i=0;i<u.size();i++)
					                        {
					                                TurbineUser element=(TurbineUser)(u.get(i));
									String firstname=(element.getFirstName());
									if(firstname.length()>1){
										String lastname=(element.getLastName());
										first_lst_name=firstname+" "+lastname;
										first_lst_name=java.net.URLEncoder.encode(first_lst_name);
									}
								}
							}catch(Exception e){}
						}
						if(first_lst_name.equals("")){
							first_lst_name=user;
						}
						String msg=PeerManager.getController().createPeer(lect_id,publicIP,user,role,status,privateIP,proxy,ref_ip,first_lst_name);
					}
       				}
				String av_status=ServerUtil.getController().getAVStatus(lect_id);
				String port=",port="+HandRaisePortHandler.getController().getPort(lect_id);
				message	= message + port+av_status;	
				response.setContentLength(message.length());
                        	out.println(message);
                        	out.flush();
                        	out.close();
			}catch(Exception e){ServerLog.getController().Log("error on getting input string==>"+e.getMessage());}
                }else if(reqType.equals("userlist")){
			try{
				String lect_id=request.getParameter("lect_id");
				/** Get Userlist in Vector for given Lecture Id */
				Vector result=PeerManager.getController().getPeerList(lect_id);
				int resultSize=result.size();
				String message="";
                        	if(resultSize!=0){
					for(int i=0;i<resultSize;i++){
						if(message.equals(""))
        	                                        message=result.elementAt(i).toString();
	                                        else
                	                                message=message+","+result.elementAt(i).toString();
					}
                              		response.setContentLength(message.length());
					out.println(message);
		                        out.flush();
	                                out.close();
        	                }else{
                	                message="noUser";
                        	        response.setContentLength(message.length());
                                	out.println(message);
	                                out.flush();
       	 	                        out.close();
                	        }
			}catch(Exception e){ServerLog.getController().Log("error messsage for userlis==> "+e.getMessage());}
		
		}else if(reqType.equals("cancleLecture")){
		       try{
				String str=request.getParameter("lectValue");
				Criteria crit=new Criteria();
                                crit.add(LecturePeer.LECTUREID,str);
		                LecturePeer.doDelete(crit);
        	                String message="Successfull";
                        	response.setContentLength(message.length());
                        	out.println(message);
                       		out.flush();
                        	out.close();
			}catch(Exception e){ ServerLog.getController().Log("error Cancle Lecture  for userlis==> "+e.getMessage()); }
		}
		else if(reqType.equals("Permissions")){
                       try{
				String ipAddress=InetAddress.getByName(request.getRemoteAddr()).toString();
				String lectID=request.getParameter("lectID");
				String userAction=request.getParameter("userAction");
				String login=request.getParameter("loginName");
				login=login.replaceAll("loginName=","");
				String message=PeerManager.getController().updateStatus(userAction,login,lectID);
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			}catch(Exception e){ ServerLog.getController().Log("error Cancle Lecture  for userlis==> "+e.getMessage()); }
                }else if(reqType.equals("GetReflectorStatusXML")){
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
			}catch(Exception e){ServerLog.getController().Log("error GetReflectorStatusXML ==> "+e.getMessage());}
		}else if(reqType.equals("GetReflectorXML")){
                        try {
                                FileInputStream fstream = new FileInputStream(context.getRealPath("Reflector.xml"));
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
                        }catch(Exception e){ServerLog.getController().Log("error GetReflectorXML ==> "+e.getMessage());}
                }else if(reqType.equals("GetLectureXml")){
                        try {
				String lectID=request.getParameter("lectID");
                                FileInputStream fstream = new FileInputStream(context.getRealPath(lectID+".xml"));
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
                        }catch(Exception e){ServerLog.getController().Log("error GetReflectorXML ==> "+e.getMessage());}
                }




	}//end of post method
		
	/** If authorisation is successful then return client's USER_ID else return loginfailed */
	
	private String loginAuthorised(String username,String password){
		String result=null;
		List returnValue=null;
		try{
			 /** use the server util for getting the password in string form from the MD5 */
                        String authPassword=EncryptionUtil.createDigest("MD5",password);

                        /** send the Login and Password to the Mysql Database for the authentication purpose */
                        try{
                        	Criteria crit = new Criteria();
                                crit.add(TurbineUserPeer.LOGIN_NAME,username);
                                crit.and(TurbineUserPeer.PASSWORD_VALUE,authPassword);
                                returnValue=TurbineUserPeer.doSelect(crit);
                   	}catch(Exception e){ServerLog.getController().Log("Error in Criteria = "+e.getMessage());}

                        /* if authorisation is unsuccessfull then return null in result vector */
                        if(returnValue.size()== 0) {
                                result="loginfailed";
                     	}
                        /** If authorisation is successfull then put information of the client
                            in result vector.These information are such as userid of the client. */
                        else {
				try {	
					TurbineUser element=(TurbineUser)returnValue.get(0);
        	                        result=Integer.toString(element.getUserId());
				} catch(Exception ex) { ServerLog.getController().Log(ex.getMessage());}	
        		}

		} catch(Exception e) { ServerLog.getController().Log("Error in authentication in ProcessRequest class= "+e.getMessage());}
		return result;
	}
	
	/** Here Server send the request to the database to get the course in which Client is registered
     	on the basis of their user group role */

	private Vector getCourse(int id,int role){
		Vector result=new Vector();
                try{
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,id);
                        crit.and(TurbineUserGroupRolePeer.ROLE_ID,role);
                        List v=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int i=0;i<v.size();i++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                String gid=Integer.toString(element.getGroupId());
                                crit=new Criteria();
                                crit.add(TurbineGroupPeer.GROUP_ID,gid);
                                List group_list=TurbineGroupPeer.doSelect(crit);
                                String groupName=((TurbineGroup)group_list.get(0)).getName();
                                result.addElement(groupName);
                        }
            	} catch(Exception e) { ServerLog.getController().Log("Error in selection of course"+e); }
                return result;
	}
	
	private String putLecture(StringBuffer url,String lect_id,String lectGetParameter,String lectUserName,String lectCouseName,String lectName,String lectInfo,String lectNo,String lectDate,String lectTime,String lectDuration,String lectAudio,String lectVedio,String lectWhiteBoard) { 
		String subject="";
		String message=" ";
		Date date=Date.valueOf(lectDate);
		if(lectGetParameter.equals("GetAnnounceValues")) {
			try {
				Criteria crit=new Criteria();
				crit.add(LecturePeer.GROUP_NAME,lectCouseName);
				crit.add(LecturePeer.LECTURENAME,lectName);
				crit.add(LecturePeer.LECTUREINFO,lectInfo);
				crit.add(LecturePeer.URLNAME,lectUserName);
				crit.add(LecturePeer.PHONENO,lectNo);	
				crit.add(LecturePeer.FORVIDEO,lectVedio);
				crit.add(LecturePeer.FORAUDIO,lectAudio);
				crit.add(LecturePeer.FORWHITEBOARD,lectWhiteBoard);
				crit.add(LecturePeer.SESSIONDATE,date);
				crit.add(LecturePeer.SESSIONTIME,lectTime);
				crit.add(LecturePeer.DURATION,lectDuration);
				crit.add(LecturePeer.REPEATLEC,"NO");
				crit.add(LecturePeer.FORTIME,"NO");
	        	        LecturePeer.doInsert(crit);
				{
					crit=new Criteria();
					crit.addGroupByColumn(LecturePeer.LECTUREID);
					java.util.List list=LecturePeer.doSelect(crit);
					int ints[]=new int[list.size()];
					for(int i=0;i<list.size();i++) {
                                		Lecture element=(Lecture)(list.get(i));
						ints[i]=(element.getLectureid());
					}
					java.util.Arrays.sort(ints);
					lect_id=Integer.toString(ints[list.size()-1]);
				}
				subject=lectCouseName.substring(0,lectCouseName.lastIndexOf("_"))+"  session name "+lectName+" has been Announced  " ;
			}catch(Exception e){ServerLog.getController().Log("Error Log in Lecture: "+e.getMessage()); }
		}else if(lectGetParameter.equals("GetUpdateLectValues")) {
			try {
				Criteria crit=new Criteria();
        	                crit.add(LecturePeer.LECTUREID,Integer.parseInt(lect_id));
                	        crit.add(LecturePeer.GROUP_NAME,lectCouseName);
                        	crit.add(LecturePeer.LECTURENAME,lectName);
				crit.add(LecturePeer.LECTUREINFO,lectInfo);
	                        crit.add(LecturePeer.URLNAME,lectUserName);
        	                crit.add(LecturePeer.PHONENO,lectNo);
                	        crit.add(LecturePeer.FORVIDEO,lectVedio);
                        	crit.add(LecturePeer.FORAUDIO,lectAudio);
	                        crit.add(LecturePeer.FORWHITEBOARD,lectWhiteBoard);
        	                crit.add(LecturePeer.SESSIONDATE,date);
                	        crit.add(LecturePeer.SESSIONTIME,lectTime);
                        	crit.add(LecturePeer.DURATION,lectDuration);
	                        crit.add(LecturePeer.REPEATLEC,"NO");
        	                crit.add(LecturePeer.FORTIME,"NO");
                	        LecturePeer.doUpdate(crit);
				subject=lectCouseName.substring(0,lectCouseName.lastIndexOf("_")) +"  session name "+lectName+" has been updated." ;
			}catch(Exception e){ServerLog.getController().Log("Error Log in Lecture:  "+e.getMessage()); }
		}
		
		try {
			Criteria crit=new Criteria();
	                crit.add(TurbineGroupPeer.GROUP_NAME,lectCouseName);
                	java.util.List v=TurbineGroupPeer.doSelect(crit);
			int gid=0;
                        for(int i=0;i<v.size();i++)
                        {
                               	TurbineGroup element=(TurbineGroup)v.get(i);
	                        gid =element.getGroupId();
			}
		
			int size=lectCouseName.lastIndexOf("_");
			{	
				Vector mail_id=AdminProperties.getUDetail(gid,3);
				for(int i=0;i<mail_id.size();i++){
					String mail_id_new[]=(mail_id.get(i).toString()).split("-");
					int key=ServerUtil.getController().generateSessionKey();
					crit=new Criteria();
                	                crit.add(UrlConectionPeer.SESSION_KEY,key);
                        	        crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lect_id));
                                	crit.add(UrlConectionPeer.LOGIN_ID,mail_id_new[0]);
	                                crit.add(UrlConectionPeer.GROUP_NAME,lectCouseName);
        	                        crit.add(UrlConectionPeer.LECTURENAME,lectName);
                	                crit.add(UrlConectionPeer.ROLE,"student");
                        	        UrlConectionPeer.doInsert(crit);
					MailNotification.getController().sendMail(context,subject,mail_id_new,date,lectTime,lectDuration,lectName,lectCouseName,"student",Integer.toString(key),url);
				}
				int key_g=ServerUtil.getController().generateSessionKey();
                                crit=new Criteria();
                                crit.add(UrlConectionPeer.SESSION_KEY,key_g);
                                crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lect_id));
                                crit.add(UrlConectionPeer.LOGIN_ID,"NULL");
                                crit.add(UrlConectionPeer.GROUP_NAME,lectCouseName);
                                crit.add(UrlConectionPeer.LECTURENAME,lectName);
                                crit.add(UrlConectionPeer.ROLE,"guest");
                                UrlConectionPeer.doInsert(crit);

				mail_id.clear();
				mail_id=AdminProperties.getUDetail(gid,2);
				for(int i=0;i<mail_id.size();i++){
					String mail_id_new[]=(mail_id.get(i).toString()).split("-");
                                        int key=ServerUtil.getController().generateSessionKey();
                                        crit=new Criteria();
                                        crit.add(UrlConectionPeer.SESSION_KEY,key);
                                        crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lect_id));
                                        crit.add(UrlConectionPeer.LOGIN_ID,mail_id_new[0]);
                                        crit.add(UrlConectionPeer.GROUP_NAME,lectCouseName);
                                        crit.add(UrlConectionPeer.LECTURENAME,lectName);
                                        crit.add(UrlConectionPeer.ROLE,"instructor");
                                        UrlConectionPeer.doInsert(crit);
					MailNotification.getController().sendMail(context,subject,mail_id_new,date,lectTime,lectDuration,lectName,lectCouseName,"instructor",Integer.toString(key),url);
				}
			}
		}catch(Exception e){}
		return "Successfull";
	}
}//end of class



