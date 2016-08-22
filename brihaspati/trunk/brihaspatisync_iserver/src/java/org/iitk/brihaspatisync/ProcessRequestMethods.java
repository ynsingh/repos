package org.iitk.brihaspatisync;

/*@(#)ProcessRequest.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2013 ETRG, IIT Kanpur
 * All Rights Reserved.
 */

import java.util.List;
import java.util.Vector;

import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.util.ServerUtil;
import org.iitk.brihaspatisync.util.EncryptionUtil;

import org.iitk.brihaspatisync.om.Lecture;
import org.iitk.brihaspatisync.om.LecturePeer;
import org.iitk.brihaspatisync.om.TurbineUser;
import org.iitk.brihaspatisync.om.TurbineUserPeer;
import org.iitk.brihaspatisync.om.TurbineUserGroupRole;
import org.iitk.brihaspatisync.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspatisync.om.UrlConectionPeer;
import org.iitk.brihaspatisync.om.UrlConection;

import org.apache.torque.util.Criteria;

import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;

import java.sql.Date;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 */

public class ProcessRequestMethods {
	
	/** 
	 * If authorisation is successful then return client's USER_ID else return loginfailed. 
	 **/
        protected static String loginAuthorised(String username,String password) {
                String result=null;
                List returnValue=null;
                try {
                        //for SHA1
                        String authPassword=EncryptionUtil.createDigest("SHA1",password);
                        try {
                                Criteria crit = new Criteria();
                                crit.add(TurbineUserPeer.LOGIN_NAME,username);
                                crit.and(TurbineUserPeer.PASSWORD_VALUE,authPassword);
                                returnValue=TurbineUserPeer.doSelect(crit);
                        } catch(Exception e) { ServerLog.log("Exception in Criteria in case of SHA1 in ProcessRequest class = "+e.getMessage()); }
                        //if SHA1 does not exist then check for MD5
                        if(returnValue.size()== 0) {
                                /** use the server util for getting the password in string form from the MD5 */
                                authPassword=EncryptionUtil.createDigest("MD5",password);
                                /** send the Login and Password to the Mysql Database for the authentication purpose */
                                try {
                                        Criteria crit = new Criteria();
                                        crit.add(TurbineUserPeer.LOGIN_NAME,username);
                                        crit.and(TurbineUserPeer.PASSWORD_VALUE,authPassword);
                                        returnValue=TurbineUserPeer.doSelect(crit);
                                } catch(Exception e) { ServerLog.log("Exception in Criteria in case of MD5 in ProcessRequest class = "+e.getMessage()); }
                        }
                        /** if authorisation is unsuccessfull then return null in result vector */
                        if(returnValue.size()== 0) {
                                result="loginfailed";
                        }

                        /** If authorization is successfull then put information of the client
			 *  in result vector.These information are such as userid of the client. 
			 */
                        else {
                                try {
                                        TurbineUser element=(TurbineUser)returnValue.get(0);
                                        result=Integer.toString(element.getUserId());
                                } catch(Exception ex) { ServerLog.log("Exception in getting client's information in ProcessRequest Class"+ex.getMessage()); }
                        }

                } catch(Exception e) { ServerLog.log("Exception in authentication in ProcessRequest class= "+e.getMessage()); }
                return result;
        }
	
	/** 
	 * Here Server send the request to the database to get the course in which Client is registered
	 * on the basis of their user group role. 
	 **/

        protected static Vector getCourse(int id,int role) {
                Vector result=new Vector();
                try {
                        Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,id);
                        crit.and(TurbineUserGroupRolePeer.ROLE_ID,role);
                        List v=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int i=0;i<v.size();i++) {
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                String gid=Integer.toString(element.getGroupId());
                                crit=new Criteria();
                                crit.add(TurbineGroupPeer.GROUP_ID,gid);
                                List group_list=TurbineGroupPeer.doSelect(crit);
                                String groupName=((TurbineGroup)group_list.get(0)).getName();
                                result.addElement(groupName);
                        }
                } catch(Exception e) { ServerLog.log("Exception in selection of course in ProcessRequest class"+e); }
                return result;
        }

	protected static String putLecture(javax.servlet.ServletContext context,StringBuffer url,String lect_id,String lectGetParameter,String lectUserName,String lectCouseName,String lectName,String lectInfo,String lectNo,String lectDate,String lectTime,String lectDuration,String lectAudio,String lectVedio,String lectWhiteBoard,String mailsend_permission) {
                String subject="";
                String message=" ";
                Date date=Date.valueOf(lectDate);
                try {
                        /** this method is called to update and announce session and returns course name and session name **/
                        subject=getLectureValues(lect_id,lectGetParameter,lectUserName,lectCouseName,lectName,lectInfo,lectNo,lectDate,lectTime,lectDuration,lectAudio,lectVedio,lectWhiteBoard, mailsend_permission);

			/**
                        *This block of if is responsible to insert data
                        *into UrlConectionPeer table when a session is
                        *announced.
                        */
                        if(lectGetParameter.equals("GetAnnounceValues")) {
                                Criteria crit=new Criteria();
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
				/**
				* get Mail Id of student.
				**/
                                Vector mail_id=AdminProperties.getUDetail(gid,3);
                                for(int i=0;i<mail_id.size();i++) {
                                        String mail_id_new[]=(mail_id.get(i).toString()).split("-");
                                        int key=ServerUtil.generateSessionKey();
                                        crit=new Criteria();
                                        crit.add(UrlConectionPeer.SESSION_KEY,key);
                                        crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lect_id));
                                        crit.add(UrlConectionPeer.LOGIN_ID,mail_id_new[0]);
                                        crit.add(UrlConectionPeer.GROUP_NAME,lectCouseName);
                                        crit.add(UrlConectionPeer.LECTURENAME,lectName);
                                        crit.add(UrlConectionPeer.ROLE,"student");
                                        UrlConectionPeer.doInsert(crit);
					/**
					 * Call to sendMail method to send mail to student when session is announced.
					 **/

                                         if((mailsend_permission.equals("1")) && (!(mail_id_new[0].equals("guest")))) {
                                                MailNotification.getController().sendMail(context,subject,mail_id_new,date,lectTime,lectDuration,lectName,lectCouseName,"student",Integer.toString(key),url);
                                        }

                                }
				mail_id.clear();
				/**
				* get mail Id of Instructor
				**/
                                mail_id=AdminProperties.getUDetail(gid,2);
                                for(int i=0;i<mail_id.size();i++) {
                                        String mail_id_new[]=(mail_id.get(i).toString()).split("-");
                                        int key=ServerUtil.generateSessionKey();
                                        crit=new Criteria();
                                        crit.add(UrlConectionPeer.SESSION_KEY,key);
                                        crit.add(UrlConectionPeer.LECTUREID,Integer.parseInt(lect_id));
                                        crit.add(UrlConectionPeer.LOGIN_ID,mail_id_new[0]);
                                        crit.add(UrlConectionPeer.GROUP_NAME,lectCouseName);
                                        crit.add(UrlConectionPeer.LECTURENAME,lectName);
                                        crit.add(UrlConectionPeer.ROLE,"instructor");
                                        UrlConectionPeer.doInsert(crit);
                                        if(mailsend_permission.equals("1")) {
						/**
					 	 * Call to sendMail method to send mail to instructor when session is announced.
					  	 **/
                                      		MailNotification.getController().sendMail(context,subject,mail_id_new,date,lectTime,lectDuration,lectName,lectCouseName,"instructor",Integer.toString(key),url);
					}
                                }
                        }
                } catch(Exception e){ ServerLog.log("Exception in put lecture method in ProcessRequest class "+e); }
                return "Successfull";
        }
	
	/**
         * This block of code is used to Announce Session and Update Session.It inserts all information 
         * to Lecture table which has been announced.It returns Course Name and Session Name for 
         * sending mail.    
         */

        private static String getLectureValues(String lect_id,String lectGetParameter,String lectUserName,String lectCouseName,String lectName,String lectInfo,String lectNo,String lectDate,String lectTime,String lectDuration,String lectAudio,String lectVedio,String lectWhiteBoard,String mailsend_permission) {
                String subject=" ";
                try {
                        Date date=Date.valueOf(lectDate);
                        if(lectGetParameter.equals("GetAnnounceValues")) {
                                Criteria crit=new Criteria();
                                crit.add(LecturePeer.GROUP_NAME,lectCouseName);
                                crit.add(LecturePeer.LECTURENAME,lectName);
                                crit.add(LecturePeer.LECTUREINFO,lectInfo);
                                crit.add(LecturePeer.URLNAME,lectUserName);
                                crit.add(LecturePeer.PHONENO,lectNo);
                                crit.add(LecturePeer.FORVIDEO,lectVedio);
                                crit.add(LecturePeer.FORAUDIO,lectAudio);
                                crit.add(LecturePeer.FORWHITEBOARD,lectWhiteBoard);
                                crit.add(LecturePeer.MAIL_NOTIFICATION,mailsend_permission);
                                crit.add(LecturePeer.SESSIONDATE,date);
                                crit.add(LecturePeer.SESSIONTIME,lectTime);
                                crit.add(LecturePeer.DURATION,lectDuration);
                                crit.add(LecturePeer.REPEATLEC,"NO");
                                crit.add(LecturePeer.FORTIME,"NO");
                                LecturePeer.doInsert(crit);
                                subject=lectCouseName.substring(0,lectCouseName.lastIndexOf("_"))+"  session name "+lectName+" has been Announced  " ;
                        } else if(lectGetParameter.equals("GetUpdateLectValues")) {
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
                                crit.add(LecturePeer.MAIL_NOTIFICATION,mailsend_permission);
                                crit.add(LecturePeer.SESSIONDATE,date);
                                crit.add(LecturePeer.SESSIONTIME,lectTime);
                                crit.add(LecturePeer.DURATION,lectDuration);
                                crit.add(LecturePeer.REPEATLEC,"NO");
                                crit.add(LecturePeer.FORTIME,"NO");
                                LecturePeer.doUpdate(crit);
                                subject=lectCouseName.substring(0,lectCouseName.lastIndexOf("_")) +"  session name "+lectName+" has been updated." ;
                        }
                } catch(Exception e){ ServerLog.log( "Exception in Lecture in ProcessRequest class: "+e.getMessage()); }
                return subject;
        }
	/**
	* Will return full name of user on the basic of his email.
	**/	
		
	protected static String getFullName(String user) {
		String first_lst_name="";
		try {
	                Criteria crit=new Criteria();
                        crit.add(TurbineUserPeer.LOGIN_NAME,user);
                        List u=TurbineUserPeer.doSelect(crit);
                        for(int i=0;i<u.size();i++) {
				TurbineUser element=(TurbineUser)(u.get(i));
                                String firstname=(element.getFirstName());
                                if(firstname.length()>1) {
                                	String lastname=(element.getLastName());
                                        first_lst_name=firstname+" "+lastname;
                                        first_lst_name=java.net.URLEncoder.encode(first_lst_name);
                             	}
			}
            	} catch(Exception e) { ServerLog.log("Exception in getting Full Name in ProcessRequest class"); }
		if(first_lst_name.equals(""))
                       	first_lst_name=user;
		return first_lst_name;
	}	
}
