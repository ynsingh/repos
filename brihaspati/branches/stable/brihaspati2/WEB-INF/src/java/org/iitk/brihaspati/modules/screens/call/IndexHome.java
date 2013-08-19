package org.iitk.brihaspati.modules.screens.call;

/*
 * @(#)IndexHome.java	
 *
 *  Copyright (c) 2004-2006, 2010, 2011 ETRG,IIT Kanpur. 
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
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
i *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.text.*;
import java.util.Vector;
import java.util.List;
import java.util.Date;
import java.io.File;
import java.sql.Timestamp;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.StudentInstructorMAP;
import org.iitk.brihaspati.modules.utils.NoticeUnreadMsg;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.UserConfiguration;
import org.iitk.brihaspati.om.CalInformationPeer;
import org.iitk.brihaspati.om.CalInformation;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.modules.utils.UsageDetailsUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.om.ModulePermissionPeer;
import org.iitk.brihaspati.om.ModulePermission;

import org.iitk.brihaspati.modules.utils.PasswordUtil;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
/**
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kuamr Trivedi</a>
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kuamr Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:rekha20july@gmail.com">Rekha Pal</a>
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @author <a href="mailto:sunil0711@gmail.com">Sunil Yadav</a>
 * @modified date: 26-07-2010, 06-08-2010, 09-11-2010, 22-02-2011, 18-07-2011
 * @modified date: 23-08-2012 (Sunil Yadav)
 */

/* This screen class is called when User's selects a home location as instructor/
 * student.
 */
public class IndexHome extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){

/* Verify if the user has selected Instructor's role or Student's role.
 * Set the context course to be NULL. The role context of the user remain same.
 * If Instructor's role is choosen {
 *   Get list of all the courses in which user is instructors.
 *   Set this list for display.
 * }
 * If student's role is choosen {
 *    Get list of all the courses in which user is student.
 *    Set this list for display.
 * }
 * For each course in the courses list {
 *    Check if there is unread notice is there or not. Store this info for
 *        this course for creation of link for directly viewing the unread notice list.
 * }
 */
		try{
		/**
		 * Getting User Detail & user id
		**/
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
	                int AdminConf = Integer.valueOf(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
        	        context.put("AdminConf",AdminConf);
			User user=data.getUser();
		        System.gc();	
			String username=user.getName();
			
			int u_id=UserUtil.getUID(username);
			String id=Integer.toString(u_id);
			String fnme=UserUtil.getFullName(u_id);
                        context.put("username",fnme);
			/**
                        * Get the current date
			* Getting Task list according to current date  & setting to display 
                        */
                        String Cdate=ExpiryUtil.getCurrentDate("-");
			Vector taskList=CommonUtility.DisplayTask(u_id,Cdate);	
			context.put("taskList",taskList);
			/**
			 * Getting last login time from database according to user name & setting it to display while user is registered in a new course
			 */
			Criteria crit=new Criteria();
			crit.add(TurbineUserPeer.LOGIN_NAME,username);		
			List v=TurbineUserPeer.doSelect(crit);
			Timestamp tStamp=(Timestamp)((TurbineUser)v.get(0)).getLastLogin();
			context.put("lastlogin",tStamp);

			/**
			 * if user is guest set guest login true
			 * else set guest login false
			 */
			if(user.getName().equals("guest")){
				context.put("guest_login","true");
			}
			else{
				context.put("guest_login","false");
			}
			/**
			 * Getting Role from parameter parser
			 * if role is null get from temporary variable 
			 */

			ParameterParser pp=data.getParameters();
			String Role="";
				Role=pp.getString("role","");
			if(Role.equals(""))
				Role=(String)user.getTemp("role");
			context.put("user_role",Role);

			/**
			 * Getting configuration Parameter 
			 * if configuration parameter is not empty set it to display
			 * else set default value as 10 
			 */

			crit=new Criteria();
                        crit.add(UserConfigurationPeer.USER_ID,Integer.toString(u_id));
                        List user_conf=UserConfigurationPeer.doSelect(crit);
			
			if(!user_conf.isEmpty()){
                        	int list_conf=((UserConfiguration)user_conf.get(0)).getListConfiguration();
				String List_conf=Integer.toString(list_conf);
				user.setTemp("confParam",List_conf);
			}
			else{
				user.setTemp("confParam","10");
			}
/*
			String userNm=user.getName();
			int uId=UserUtil.getUID(userNm);
			String str=userNm+"_"+Integer.toString(uId);
			user.setTemp("stuId",str);
*/
			// This is check for set temp variables
			/**
			 * @param course_name String, Default value should set as null
			 * @param course_id String, Default value should set as null
			 */ 
			user.setTemp("course_name","");
                        user.setTemp("course_id","");
			user.setTemp("role",Role);
                        Vector unread_inst=new Vector();
                        Vector unread_stud=new Vector();
			
			/**
			 * if role is instructor 
			 * Getting List of course's object according to user id in which user is instructor
			 * to show the list of courses 
			 * Getting unread message & setting it to display
			 */
			if(Role.equals("instructor"))
			{
				ArrayList list=CourseUtil.getCourseList(u_id,2);
				context.put("clistd",list);
			}
		
			/**
                         * if role is Teacher Assistant 
                         * Getting List of course's object according to user id in which user is Teacher Assistant
                         * Getting unread message & setting it to display
                         */


	   		if(Role.equals("teacher_assistant"))
                        {
				ArrayList list=CourseUtil.getCourseList(u_id,8);
                                context.put("clistd",list);
                        }

			/**
			 * else if role is student 
			 * Getting List of course's object in which user is student  to show the list of courses 
			 *  gettign unread message & setting it to display
			*/
                        else if(Role.equals("student"))
			{
				Vector course_stud=StudentInstructorMAP.getSMAP(u_id);
                        	context.put("stud",course_stud);
				// getting Unread Notices
				unread_stud=NoticeUnreadMsg.getUnreadNotice(u_id,3,"All");
				context.put("unread_msg",unread_stud);
			}
                        /**
                        * Retreive the current time from System date and it to display for setting task colour
                        */
                        String date=new Date().toString();
                        String ch=date.substring(11,13);
                        String cm=date.substring(14,16);
                        String currtime=ch+cm;
                        context.put("currenttime",currtime);
                        /**
                        * Sending object Integer to context
			* to set begining time and ending time into INT to compare 
                        */

                        Integer ii = new Integer(1000);
                        context.put("INT",ii);
                        /**
                         * Retreive the list of events from the database based on today's date for the
                         * specific user to display
                         */
                        crit=new Criteria();
                        crit.add(CalInformationPeer.USER_ID,id);
                        crit.add(CalInformationPeer.P_DATE,(Object)Cdate,crit.EQUAL);
                        crit.addAscendingOrderByColumn(CalInformationPeer.START_TIME);
                        List Cdetail=CalInformationPeer.doSelect(crit);

			ArrayList tsklist = new ArrayList();
                        Map map = new HashMap();

                        for(int i=0;i<Cdetail.size();i++)
			{
				String ETime=null;
                                CalInformation element=(CalInformation)Cdetail.get(i);
                                String stime=(element.getStartTime()).toString();
                                String sh=stime.substring(0,2);
                                String sm=stime.substring(3,5);
                                String btime=sh+sm;
                                byte b[]=element.getDetailInformation();
                                String description=new String(b);
                                String DI=new String();
                                StringBuffer sb1=new StringBuffer(stime);
                                sb1.delete(5,8);
                                try{
                                String etime=(element.getEndTime()).toString();
                                String eh=etime.substring(0,2);
                                String em=etime.substring(3,5);
                                ETime=eh+em;
                                StringBuffer sb2=new StringBuffer(etime);
                                sb2.delete(5,8);
                                DI=sb1 + "-" + sb2 + " " + description;

                                }
                                catch(Exception ex){data.addMessage("new error"+ex);}
				map = new HashMap();
                                map.put("stime", btime);
                                map.put("etime", ETime);
                                map.put("minfo", DI);
                                tsklist.add(map);
                        }
                        if(tsklist.size()!=0)
                        {
                                context.put("info",tsklist);
                        }

			System.gc();
			//Retrieving list of book mark from xml files to display
			String filePath=data.getServletContext().getRealPath("/Bookmarks"+"/"+username);
                        File f=new File(filePath+"/BookmarksList.xml");
			TopicMetaDataXmlReader topicmetadata=null;
                        Vector topicList=new Vector();
                        if(f.exists())
                        {
                        	topicmetadata=new TopicMetaDataXmlReader(filePath+"/BookmarksList.xml");
                                topicList=topicmetadata.getBookmarksDetails();
				if(topicList==null)
                                return;
                                if(topicList.size()!=0)
					context.put("allTopics",topicList);
			}
			/*
                         *check for Course_time table update
                         */
                        /*entry id fron USAGE_DETAILS*/
                        int eid1=UsageDetailsUtil.getentryId(u_id);
                        /*entry id from COURSE_TIME */
                        int eid2=CourseTimeUtil.getentryid(u_id);
                        if(eid1==eid2){
                        	ModuleTimeThread.getController().CourseTimeSystem(u_id,eid2);
			}

		}
		catch(Exception e){data.setMessage("The error in IndexHome !!"+e);}
	}
}

