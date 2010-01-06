package org.iitk.brihaspatisync;

/*@(#)ProcessRequest.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
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
import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import java.text.ParsePosition;

import java.net.InetAddress;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.iitk.brihaspatisync.om.TurbineGroup;
import org.iitk.brihaspatisync.om.TurbineGroupPeer;
import org.iitk.brihaspatisync.om.TurbineUserGroupRole;
import org.iitk.brihaspatisync.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspatisync.om.TurbineUser;
import org.iitk.brihaspatisync.om.TurbineUserPeer;

 /**
 * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
 * @author <a href="mailto:@arvindjss17@gmai..com"> Arvind Pal </a>
 */

public class ProcessRequest extends HttpServlet {
	
   	private Timer dbTimer=null;
	private static Hashtable table;
	private Torque set=null;
	private String message="";
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		if(set==null){
			try{
				set=new Torque();
				set.init("/home/suneel/brihaspati_sync/webapps/brihaspatisync_iserver/Torque.properties");
		      		dbTimer=new Timer(true);
               		}catch(Exception dberror){ServerLog.getController().Log("Error in database connection "+dberror.getMessage());}
		}
		try{
  			dbTimer.schedule(TimerOfDatabase.getController(), 1000, 60*60*1000);
 		}catch(Exception e){ ServerLog.getController().Log("Error in database Scheduler "+e.getMessage());}

		//Retriving client sent req parameters
		String reqType=request.getParameter("req");
		PrintWriter out = response.getWriter();
		/* arvind -----------------------------------------*/
		if(reqType.equals("reflector_logout")){
                        String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
			ServerLog.getController().Log("reflector_logout------------->"+reflectorIP);	
                        String msg =ReflectorManager.getController().removePeer(reflectorIP);
                        message=msg;
                        response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
                }
		/* arvind -----------------------------------------*/

                           
		/**
		 * This block of code is used to register a reflector on this server with ipAddress of reflector 
		 * and initial status which return success or failure message.
		 */
		
		if(reqType.equals("reflector_registration")){

			String reflectorIP  =InetAddress.getByName(request.getRemoteAddr()).toString();
			String status=request.getParameter("status");
			String arvind =ReflectorManager.getController().Register(reflectorIP,status);
			message="successfull";
			response.setContentLength(message.length());
                        out.println(message);
                        out.flush();
                        out.close();
			
		}

		/**
		 * This block of code is used to change the status of reflector which decide that is this reflector 
		 * is currently ready to accespt aclient connection or not
		 * this is used ip address of reflector and status request to change it value in xml file.
		 */
			
		else if(reqType.equals("reflector_status")){
			
			String reflectorIP=InetAddress.getByName(request.getRemoteAddr()).toString();
			String status=request.getParameter("status");
                        message =ReflectorManager.getController().updateStatus(reflectorIP,status);
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
			message=clientIP.toString();
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
			ServerLog.getController().Log("Client = "+clientIP+" Connect to TCPServer");
			*/
				
		}else if(reqType.equals("login")){
				
			String userName=request.getParameter("usr");
	                String userPassword=request.getParameter("pass");
        		String ipAddress=request.getParameter("ip");
			String nodeType=request.getParameter("fw-node");
			message=loginAuthorised(userName,userPassword);
			if(message.equals("loginfailed")){
				response.setContentLength(message.length());
               			out.println(message);
                       		out.flush();
                       		out.close();
			}else{
				
				response.setContentLength(message.length());
                        	out.println(message);
       	                	out.flush();
				/** Send the userid and  session key of the client for the authentication purpose each time 
				    when the request is coming from the client to the server This session key is creating by
				    using the server util.After sending the session key to the user also store this session
				    key as a key with the ipAddress of the client in the hash table for the handling
				    of logger,task which is related with the Handraise,query handling in future and
				    for the single session 
				*/
              			int key=ServerUtil.getController().generateSessionKey();
       		                message=Integer.toString(key);
				response.setContentLength(message.length());
        	                out.println(message);
       	        	        out.flush();
				/* send server date to client user for synchrozise clock with this server. */
				message=getCurrentDate("");
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
			ServerLog.getController().Log("reflector ip for logout==>"+reflector+"\nusername==>"+username);
			/** Remove Entry from the peer list from LecturePeer.xml */
			if(!(reflector.equals(""))){
				String lectID=request.getParameter("lectID");
				ServerLog.getController().Log("lectID==>"+lectID);
				PeerManager.getController().removePeer(lectID,username);
				ReflectorManager.getController().removeLoad(reflector);
			}
			out.flush();
                        out.close();
		}else if(reqType.equals("getCourse")){
			Vector result=new Vector();
			int id=Integer.parseInt(request.getParameter("id"));
			int role=Integer.parseInt(request.getParameter("role"));
			result=getCourse(id,role);
			int resultSize=result.size();
			if(resultSize!=0){
				for(int i=0;i<=resultSize;i++){
					message=result.elementAt(i).toString();
					response.setContentLength(message.length());
        	              		out.println(message);
                	        	out.flush();
				}
                        	out.close();
			}else{
				message="noCourse";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			}
		}else if(reqType.equals("getSession")){
			String courseName=request.getParameter("cn");
			Vector result=getSessionList(courseName);
			int resultSize=result.size();
			if(resultSize!=0){
                        	for(int i=0;i<=resultSize;i++){
                                	message=result.elementAt(i).toString();
                                	response.setContentLength(message.length());
                                	out.println(message);
                                	out.flush();
                        	}
                        	out.close();
			}else{
				message="noLecture";
                                response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
				out.close();
			}
		}else if(reqType.equals("putLecture")){
                      	String str=request.getParameter("lectValue");
			int startindex=str.indexOf("$",0);
			String fristString=str.substring(0,startindex);
			message=putLecture(fristString,str);                        
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
				message=ReflectorManager.getController().getIP(lect_id,role);
				ServerLog.getController().Log("message---> 1"+message);
				if((!message.equals("UnSuccessfull")) && (!message.equals("Reflector is not running"))){
					String ref_ip=message;
					ServerLog.getController().Log("ref_ip ---> 2 "+ref_ip);
					if(ref_ip.startsWith("current/")){
						String str1[]=ref_ip.split(",");
                	                        ref_ip=str1[0].replaceAll("current/","");
						ServerLog.getController().Log("ref_ip ---> 3 "+ref_ip);
						str1=null;
						String msg=PeerManager.getController().createPeer(lect_id,publicIP,user,role,status,privateIP,proxy,ref_ip);
					}
       				}
				ServerLog.getController().Log("message---> 4 "+message);
				response.setContentLength(message.length());
                        	out.println(message);
                        	out.flush();
                        	out.close();
			}catch(Exception e){
				ServerLog.getController().Log("error on getting input string==>"+e.getMessage());
			}
                }else if(reqType.equals("userlist")){
			try{
				String lect_id=request.getParameter("lect_id");
				/** Get Userlist in Vector for given Lecture Id */
				Vector result=PeerManager.getController().getPeerList(lect_id);
				int resultSize=result.size();
                        	if(resultSize!=0){
                                	for(int i=0;i<=resultSize;i++){
                                        	message=result.elementAt(i).toString();
        	                                response.setContentLength(message.length());
						out.println(message);
		                                out.flush();
                                	}
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
        	                message="Successfull";//putLecture(str);
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
				message=PeerManager.getController().updateStatus(userAction,login,lectID);
				response.setContentLength(message.length());
                                out.println(message);
                                out.flush();
                                out.close();
			}catch(Exception e){ ServerLog.getController().Log("error Cancle Lecture  for userlis==> "+e.getMessage()); }
                }



	}//end of post method
		
	/**  getCurrent Date  **/
	private static String getCurrentDate(String delimiter)
        {
		String cdate="";
                try{
                        java.util.Calendar calendar=java.util.Calendar.getInstance();

                        int curr_day=calendar.get(calendar.DATE);
                        int curr_month=calendar.get(calendar.MONTH)+1;
                        int curr_year=calendar.get(calendar.YEAR);
                        String current_day=Integer.toString(curr_day);
                        String current_month=Integer.toString(curr_month);
                        String current_year=Integer.toString(curr_year);
                        if(curr_month<10)
                                current_month="0"+current_month;
                        if(curr_day<10)
                                current_day="0"+current_day;
                        if(!delimiter.equals(""))
                                cdate=current_year+delimiter+current_month+delimiter+current_day;
                        else
                                cdate=current_year+current_month+current_day;
                }
                catch(Exception ex)
                {
                        System.out.println("Error in Expiry Util");
                }
                return(cdate);
        }
	

	/**  getCurrent Date  **/
	
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
                                Iterator forResult=returnValue.iterator();
                                while(forResult.hasNext()) {
                                	TurbineUser next=(TurbineUser) forResult.next();
                                        result=next.getUserId().toString();
                        	}
        		}

		} catch(Exception e) { ServerLog.getController().Log("Error in authentication in ProcessRequest class= "+e.getMessage());}
		return result;
	}
	
	/** Here Server send the request to the database to get the course in which Client is registered
     	on the basis of their user group role */

	private Vector getCourse(int id,int role){
		Vector result=new Vector();
                try{
                	Criteria forgroupId = new Criteria();
                        forgroupId.add(TurbineUserGroupRolePeer.USER_ID,id);
                        forgroupId.and(TurbineUserGroupRolePeer.ROLE_ID,role);
                        List tugr = TurbineUserGroupRolePeer.doSelect(forgroupId);
                        Iterator iterate = tugr.iterator();
                        while (iterate.hasNext()){
                        	TurbineUserGroupRole resultOftugr=(TurbineUserGroupRole)iterate.next();
                                Criteria forgroupName=new Criteria();
                                forgroupName.add(TurbineGroupPeer.GROUP_ID,resultOftugr.getGroupId());
                                List tg=TurbineGroupPeer.doSelect(forgroupName);
                                Iterator itr=tg.iterator();
                                while(itr.hasNext()){
                               		TurbineGroup resultOftg=(TurbineGroup)itr.next();
                                        result.addElement(resultOftg.getGroupName());
                              	}
                      	}
            	} catch(Exception e) { ServerLog.getController().Log("Error in selection of course"+e); }
                return result;

	}

	/** Get Session list from the database on the basis of coursename
       	Actually here the Session list means get the List of Lecture in which user is registered */

	private Vector getSessionList(String CourseName){

        	String  cid="";
           	Date systemDate=new Date();
           	long syDateintime=Math.abs(systemDate.getTime());
           	Vector result=new Vector();
    
		/** Get the CourseId from the Course Name */
            	try{
                	Criteria crit=new Criteria();
                 	crit.add(CoursesPeer.GROUP_NAME,CourseName);
                 	List selectCid=CoursesPeer.doSelect(crit);
                 	Iterator iter=selectCid.iterator();
                    	while(iter.hasNext()) {
                       		Courses resultOfCourse=(Courses)iter.next();
                       		cid=resultOfCourse.getGroupName().toString();
                    	}

                  	/** After getting the CourseId then Select the Lecture from the database on the basis of CourseId */
                   	Criteria forlect=new Criteria();
                   	forlect.add(LecturePeer.GROUP_NAME,cid);
                   	List selectList=LecturePeer.doSelect(forlect);
                   	Iterator iterate=selectList.iterator();
                       	while(iterate.hasNext()) {
                        	Lecture resultofLecture=(Lecture)iterate.next();
                              	result.addElement((String)resultofLecture.getLectureid().toString());
                              	Date lectureDate=resultofLecture.getSessiondate();
                              	long lectDateintime=Math.abs(lectureDate.getTime());

                        	/** Here our motive to provide the special symbol to that lecture
                            	which should be running in next one or two days.So at the client side
                            	we can seperate these lecture. */

                       		/** Add all of the elements of lecture in a vector */

                                if(Math.abs(lectDateintime-syDateintime)<(2*86400000))
                                	result.addElement(resultofLecture.getLecturename().toString() + "#");
				else
                           		result.addElement((String)resultofLecture.getLecturename().toString());
                           		result.addElement((String)resultofLecture.getLectureinfo());
                           		result.addElement((String)resultofLecture.getUrlname());
                           		result.addElement((String)resultofLecture.getPhoneno());
                           		result.addElement((String)resultofLecture.getForvideo());
                           		result.addElement((String)resultofLecture.getForaudio());
                           		result.addElement((String)resultofLecture.getForwhiteboard());
                           		result.addElement(resultofLecture.getSessiondate().toString());
                           		result.addElement((String)resultofLecture.getSessiontime());
                           		result.addElement((String)resultofLecture.getDuration());
                           		result.addElement((String)resultofLecture.getRepeatd());
                           		result.addElement((String)resultofLecture.getFortime());
                       		}//else
             	} catch(Exception ex) { ServerLog.getController().Log("Error in selection of session list==>" +ex); }
       		return result;
     	}// end of the method getSessionList(String CourseName)
	
	private String putLecture(String check1,String lectValues){
		String tempvar="";
		Lecture lect=null;
		String return_str="";
		Vector lectureInfo=new Vector();
		int repeattime=0;
		int repeatday=0;
		String  cid="";
                Date lecDate2=null;
		long millisecond=0;
		Criteria crit; int j=0;
		String tempid="";
		StringTokenizer st = new StringTokenizer(lectValues,"$");

		while(st.hasMoreTokens()) {
			if(check1.equals("GetAnnounceValues")){	
				lectureInfo.addElement(st.nextToken());
			}else if(check1.equals("GetUpdateLectValues")) {
			  	if(j==0){
					tempvar=st.nextToken();
                                        j++;
                                }else if(j==1){
                                        tempid=st.nextToken(); j++;
                                        lectureInfo.addElement(tempid);
                        	}else
					lectureInfo.addElement(st.nextToken());
			}
             	}
		
                Date lecDate=new SimpleDateFormat("yyyy-MM-dd").parse((String)lectureInfo.elementAt(9),new ParsePosition(0));
		String repeat=(String)lectureInfo.elementAt(12);
		String repeatforTime=(String)lectureInfo.elementAt(13);
		if(repeat.equals("No"))
                        repeatday=0;
                if(repeat.equals("Daily"))
                        repeatday=1;
                if(repeatforTime.equals("7 Days"))
                        repeattime=7;
              	if(repeatforTime.equals("15 Days"))    
			repeattime=15;
                try{
                        crit=new Criteria();
                        crit.add(CoursesPeer.GROUP_NAME,(String)lectureInfo.elementAt(1));
                        List selectCid=CoursesPeer.doSelect(crit);
                        Iterator iter=selectCid.iterator();
                        while(iter.hasNext()) {
                                Courses resultOfCourse=(Courses)iter.next();
                                cid=resultOfCourse.getGroupName().toString();
				
                        }
		}catch(Exception e){ServerLog.getController().Log("Error to get Courseid at putLecture() in ProcessRequest == >"+e.getMessage());}
               	if(repeatday==0){
			
			/**
	                 * Here the lecture details are inserted only once showing that the
        	         * lecture will be delivered only once
		       	 */
			
			try{
                                crit=new Criteria();
                                if(tempvar.equals("GetUpdateLectValues"))
				{
					crit.add(LecturePeer.LECTUREID,tempid);
					crit.add(LecturePeer.GROUP_NAME,(String)lectureInfo.elementAt(1));//COURSEID,new NumberKey(cid));
                                        crit.add(LecturePeer.LECTURENAME,(String)lectureInfo.elementAt(2));
                                        crit.add(LecturePeer.LECTUREINFO,(String)lectureInfo.elementAt(3));
                                        crit.add(LecturePeer.URLNAME,(String)lectureInfo.elementAt(4));
                                        crit.add(LecturePeer.PHONENO,(String)lectureInfo.elementAt(5));
                                        crit.add(LecturePeer.FORVIDEO,(String)lectureInfo.elementAt(6));
                                        crit.add(LecturePeer.FORAUDIO,(String)lectureInfo.elementAt(7));
                                        crit.add(LecturePeer.FORWHITEBOARD,(String)lectureInfo.elementAt(8));
                                        crit.add(LecturePeer.SESSIONDATE,lecDate);
                                        crit.add(LecturePeer.SESSIONTIME,(String)lectureInfo.elementAt(10));
                                        crit.add(LecturePeer.DURATION,(String)lectureInfo.elementAt(11));
                                        crit.add(LecturePeer.REPEATD,(String)lectureInfo.elementAt(12));
                                        crit.add(LecturePeer.FORTIME,(String)lectureInfo.elementAt(13));
                                        LecturePeer.doUpdate(crit);
					
				}else {
					crit.add(LecturePeer.GROUP_NAME,(String)lectureInfo.elementAt(1));//COURSEID,new NumberKey(cid));
					crit.add(LecturePeer.LECTURENAME,(String)lectureInfo.elementAt(2));
        	                        crit.add(LecturePeer.LECTUREINFO,(String)lectureInfo.elementAt(3));
                	                crit.add(LecturePeer.URLNAME,(String)lectureInfo.elementAt(4));
                        	        crit.add(LecturePeer.PHONENO,(String)lectureInfo.elementAt(5));
                                	crit.add(LecturePeer.FORVIDEO,(String)lectureInfo.elementAt(6));
	                                crit.add(LecturePeer.FORAUDIO,(String)lectureInfo.elementAt(7));
        	                        crit.add(LecturePeer.FORWHITEBOARD,(String)lectureInfo.elementAt(8));
                	                crit.add(LecturePeer.SESSIONDATE,lecDate);
                        	        crit.add(LecturePeer.SESSIONTIME,(String)lectureInfo.elementAt(10));
                                	crit.add(LecturePeer.DURATION,(String)lectureInfo.elementAt(11));
	                                crit.add(LecturePeer.REPEATD,(String)lectureInfo.elementAt(12));
        	                        crit.add(LecturePeer.FORTIME,(String)lectureInfo.elementAt(13));
                	                LecturePeer.doInsert(crit);
				}
			 }catch(Exception e){
                                ServerLog.getController().Log("Error in Insert of Lecture Data when repeat day=0 : "+e+" " +e.getMessage());
			      	return return_str="Error";
                	}
		 return return_str="Successfull";
		/**
                 * Here the lecture details are inserted for the number of times it has
                 * to be delivered.The only difference comes in the date field
                 */
             	}else {
			for(int i=1;i<=repeattime;i++) {
                                if(i==1)
                                        millisecond=Math.abs(lecDate.getTime());
                                else
                                        millisecond=Math.abs(lecDate2.getTime());
                                millisecond=Math.abs(millisecond+86400000);
                                Date sessionDate=new Date(millisecond);
                                SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
                                lecDate2 = sd.parse(Integer.toString(sessionDate.getYear()+1900)+"-"+Integer.toString(sessionDate.getMonth()+1)+"-"+Integer.toString(sessionDate.getDate()) ,new ParsePosition(0));

                                try{
                                        crit=new Criteria();
                                        crit.add(LecturePeer.GROUP_NAME,(String)lectureInfo.elementAt(1));//COURSEID,new NumberKey(cid));
                                        crit.add(LecturePeer.LECTURENAME,(String)lectureInfo.elementAt(2));
                                        crit.add(LecturePeer.LECTUREINFO,(String)lectureInfo.elementAt(3));
                                        crit.add(LecturePeer.URLNAME,(String)lectureInfo.elementAt(4));
                                        crit.add(LecturePeer.PHONENO,(String)lectureInfo.elementAt(5));
                                        crit.add(LecturePeer.FORVIDEO,(String)lectureInfo.elementAt(6));
                                        crit.add(LecturePeer.FORAUDIO,(String)lectureInfo.elementAt(7));
                                        crit.add(LecturePeer.FORWHITEBOARD,(String)lectureInfo.elementAt(8));
                                        if(i==1)
                                                // lecDate contains the date for the first day when the lecture starts
                                                crit.add(LecturePeer.SESSIONDATE,lecDate);
                                        else
                                                /** lecDate2 contains the date for the next days the lecture is going to run.This is null in
                                                 *the first loop and initialized later on.
                                                 */
                                                crit.add(LecturePeer.SESSIONDATE,lecDate2);
                                        crit.add(LecturePeer.SESSIONTIME,(String)lectureInfo.elementAt(10));
                                        crit.add(LecturePeer.DURATION,(String)lectureInfo.elementAt(11));
                                        crit.add(LecturePeer.REPEATD,(String)lectureInfo.elementAt(12));
                                        crit.add(LecturePeer.FORTIME,(String)lectureInfo.elementAt(13));
					LecturePeer.doInsert(crit);
                                }catch(Exception e) {
                                        ServerLog.getController().Log("Error in Insert of Lecture Data when repeat day=1: "+e.getMessage()+" "+e);
                                        return return_str="Error";
                        	}
			}
			return return_str="Successfull";
                }
		
	}
}//end of class



