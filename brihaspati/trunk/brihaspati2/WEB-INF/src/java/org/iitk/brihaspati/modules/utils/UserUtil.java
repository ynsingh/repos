package org.iitk.brihaspati.modules.utils;

/*
 * @(#) UserUtil.java 
 *  Copyright (c) 2004-2006,2011, 2013 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */  
import java.util.List;
import java.util.Vector;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.AssignmentPeer;
import org.iitk.brihaspati.om.Assignment;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;


/**
 * This utils class have all details of User
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 */


public class UserUtil
{
	/**
	 * @param userName String
	 * @return integer
	 */ 
	public static int getUID(String userName)
	{
		//List v=null;
		int uid=-1;

		Criteria crit=new Criteria();
		crit.add(TurbineUserPeer.LOGIN_NAME,userName);
		try
		{			
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			uid=element.getUserId();
		}
		catch(Exception e)
		{
		}
		return uid;
	}
	/**
	 * @param uid Integer
	 * @return String
	 */ 
	public static String getLoginName(int uid){
		String LoginName=null;
		try{

			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);			
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			LoginName=element.getUserName().toString();
		}
		catch(Exception e){
			//log something
		}
		return LoginName;
	}
	
	/**
	 * @param uid Integer
	 * @return String full name of user
	 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit(DEI Agra)</a>
	 */ 
	public static String getFullName(int uid){
		String fullName="";
		try{

			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);						
			List v=TurbineUserPeer.doSelect(crit);
			TurbineUser element=(TurbineUser)v.get(0);
			String LoginName= element.getUserName().toString();
			String firstName = element.getFirstName().toString();
			String lastName = element.getLastName().toString();
			fullName = firstName + " "+lastName;
			if(StringUtils.isBlank(fullName)){
				fullName=LoginName;
			}  			
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("inside exception : userutil:getFullName "+e);
			//log something
		}		
		return fullName;
	}
	
	
				
	public static User getUDetail(int uid)throws Exception
	{
		String LoginName=null;
		List v=null;
		try{
			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);
			v=TurbineUserPeer.doSelect(crit);
		}
		catch(Exception e){
			//Log something
		}
		try{
			for(int i=0;i<v.size();i++){
			TurbineUser element=(TurbineUser)v.get(i);
			LoginName=element.getUserName().toString();
			}
		}
		catch(Exception e){
			//log something
		}
		return TurbineSecurity.getUser(LoginName);	
	}
	/**
 	 * This method gives all registered groupid for given userid 
 	 * @param uid Integer user id of the user		//Richa
 	 * @return List  
 	 */ 
	
	public static List getAllGrpId(int uid)throws Exception
	{
		List v=null;
		int nogid[]={1,4,5};
		try{
			Criteria crit = new Criteria();
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
			v=TurbineUserGroupRolePeer.doSelect(crit);
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Exception inside getAllGrpId() UserUtil.java!!"+e);
		}
	return v;
	
	}
	//added by sharad for removal		
        public static List inst_stud_group(int uid)throws Exception
        {
                List v=null;
                int nogid[]={1,4,5};
		int roleid[]={2,3,8};
                try{
                        Criteria crit = new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
                        crit.addIn(TurbineUserGroupRolePeer.ROLE_ID,roleid);
                        v=TurbineUserGroupRolePeer.doSelect(crit);
                }
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Exception inside getAllGrpId() UserUtil.java!!"+e);
                }
        return v;

        }

	/**
         * Get Email on the basis of uid        
         * @param uid Integer
         * @return String
         * @Author Jaivir Singh/Manorama Pal
         */
        public static String getEmail(int uid){
                String email=null;
                try{

                        Criteria crit=new Criteria();
                        crit.add(TurbineUserPeer.USER_ID,uid);
                        List v=TurbineUserPeer.doSelect(crit);
                        TurbineUser element=(TurbineUser)v.get(0);
                        email=element.getEmail().toString();
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Exception inside getEmail() UserUtil.java!!"+e);}
                return email;
      }

        /**
  	 * Below method is used to send reminder mail to students when their assignment date is going to expired. 
 	 */ 

	public static void sendAssignmentReminderMail(){
		try{
			   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   //get current date time with Date()
		           Date date = new Date();
			   String dt = dateFormat.format(date);
			   Criteria crit=new Criteria();
                           crit.add(AssignmentPeer.DUE_DATE,java.sql.Date.valueOf(dt));
                           List u=AssignmentPeer.doSelect(crit);
			   if(u.size()>0)
			   {
				for(int i=0; i<u.size(); i++)
				{
					Assignment element=(Assignment)(u.get(i));
					String Assid=(element.getAssignId());
					String Topicname=(element.getTopicName());
					String GrpName=element.getGroupName();
					int gid = GroupUtil.getGID(GrpName);
					String Crsname = CourseUtil.getCourseName(GrpName);
					Vector UID = UserGroupRoleUtil.getUID(gid,3);
					for(int j=0; j<UID.size(); j++)
					{
						String uid = (UID.get(j)).toString();
						String email = getEmail(Integer.parseInt(uid));
						String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
          					Properties pr =MailNotification.uploadingPropertiesFile(fileName);
						String msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
						msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", email);
						String msgformat=pr.getProperty("brihaspati.Mailnotification.newAssignment.message");
						msgformat=MailNotification.getAssignmentMessage(msgformat, Crsname, Topicname);
					        String subject=pr.getProperty("brihaspati.Mailnotification.newAssignment.subject");
						subject = MailNotification.getAssignmentMessage(subject, Crsname, Topicname);
			                        String Mailmsg = MailNotificationThread.getController().set_Message(msgformat, msgDear, "", "", email, subject, "", "");
					}
				}
			}

		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception inside sendAssignmentReminderMail() UserUtil.java!!"+e);
		}
	}
	

}
