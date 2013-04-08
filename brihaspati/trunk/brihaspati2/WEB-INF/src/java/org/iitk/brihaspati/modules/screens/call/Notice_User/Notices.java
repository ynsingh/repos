package org.iitk.brihaspati.modules.screens.call.Notice_User;

/*
 * @(#)Notices.java	
 *
 *  Copyright (c) 2005, 2010,2013 ETRG,IIT Kanpur. 
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
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
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

/**
 * @author <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
 * @author <a href="mailto:sunil0711@gmail.com">Sunil Yadav</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @ modified date: 13-Oct-2010 (Shaista),01-feb-2012(Dewanshu Singh Sisaudiya)
 * @ modified date: 24-08-2012 (Sunil Yadav),08-03-2013
 */

import java.util.Vector;
import java.util.List;
import java.util.Iterator;


import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import com.workingdogs.village.Record;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.Notification;
import org.iitk.brihaspati.modules.utils.AdminProperties;

public class Notices extends SecureScreen
{
	/**
	 * It is getting All courses list for Admin if Admin Loggedin and setting in context to display
	 * Getting institute wise CourseList if Institute's admin logged in and setting in context to display
	 * Getting Course list inwhich user is register as an instructor and setting in context to display
	 * Loads the template screen
	 */
	public void doBuildTemplate( RunData data, Context context ) {
		 try{  
                        /**
                         * Retreives the login name and user id of the user logged in
                         */

                        User user=data.getUser();
			String user_name = user.getName();
                        String loginname=user.getName();
                        int user_id=UserUtil.getUID(loginname);
			ParameterParser pp = data.getParameters();
                        String mode1=pp.getString("mode1","");
                        context.put("mode1",mode1);
                        String grpname=pp.getString("val1","");
                        context.put("val",grpname);

                        String flag=pp.getString("nflag","");
			
                        context.put("nflag",flag);
                        // Get Type of Hedaing 
			String htype=pp.getString("htype","");
                        context.put("hflag",htype);
			String dir=(String)user.getTemp("course_id");
                        String counter=pp.getString("count","");
                        context.put("tdcolor",counter);
                        context.put("tdcolor1",pp.getString("countTemp",""));
			String stats=data.getParameters().getString("stats","");
                        String mode2=data.getParameters().getString("mode2","");
                        String dev = Notification.DisBoardNf(user_name,dir,stats,mode2);
                        context.put("unreadm",dev);
			//Code for showing message in FCK editor
			try{
			String path="";
			String fhead="";
			String fhrole=data.getUser().getName();
			//Code for showing existing Notification message in FCK editor.
			if((fhrole.equals("admin")) && ((htype.equals("Flash Heading")) || (flag.equals("Flash Heading")))){
			path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Notification.properties";
                        fhead = AdminProperties.getValue(path,"brihaspati.admin.flashHeading.value");
                        context.put("fNoti",fhead);
			}
			//Code for showing existing Shutdown message in FCK editor.
			if((fhrole.equals("admin")) && ((htype.equals("Shutdown Notices")) || (flag.equals("Shutdown Notices")))){
			path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Shutdown.properties";
                        String shead = AdminProperties.getValue(path,"brihaspati.admin.ShutdownHeading.value");
                        context.put("sNoti",shead);
                        }
			}
		catch(Exception e){

			}

                        /**
                         * Retreives all courses for use of Admin and Institute Admin
                         */

			String rolename=(user.getTemp("role")).toString();
			String instituteId=(user.getTemp("Institute_id")).toString();
			List CList=null;
                        if(loginname.equals("admin"))	{
                        	CList=ListManagement.getCourseList();
			
			}
			
			else {
				if(rolename.equals("institute_admin")){	
				CList=CourseManagement.getInstituteCourseNUserDetails("All",instituteId);
				}
			}

                        context.put("clist",CList);
			Vector courselist=new Vector();
			Vector groupIdList=new Vector();
			Vector v = new Vector();

			 /**
                         * Retreives all the courses in which the user is an instructor
			 * with roleId 2 and Teacher Assistant with roleId 8.
                         */

			//modify by Sunil Yadav

			if(rolename.equals("instructor")) {
                        
				v=UserGroupRoleUtil.getGID(user_id,2);
			} else {
				
				v=UserGroupRoleUtil.getGID(user_id,8);
			}
                        int rows=v.size();
                        for(int count=0;count<rows;count++){
                                String groupId=(String)(v.elementAt(count));
                                int gid=Integer.parseInt(groupId);
                                String group_name=GroupUtil.getGroupName(gid);
                                String coursename=CourseUtil.getCourseName(group_name);
                                courselist.addElement(coursename);
                                groupIdList.addElement(group_name);
                        }
			String courseid="", C_Name="", userInCourse ="";
			courseid=pp.getString("courseId","");
			userInCourse=(String)user.getTemp("course_id");
			if( userInCourse!=null && !userInCourse.equals("") && courseid.equals(""))
			{
				courseid = userInCourse;
                        	C_Name=(String)(user.getTemp("course_name"));
			}
			else
			{
				user.setTemp("course_id",courseid);
				C_Name=CourseUtil.getCourseName(courseid);
				user.setTemp("course_name",C_Name);
			}
			context.put("courseId",courseid);
	               //String C_Name=(String)(user.getTemp("course_name"));
                        context.put("course",C_Name);
                        context.put("courselist",courselist);
                        context.put("groupIdList",groupIdList);
		/*
		 *method for how much time user spend in this page.
		 */

		String Role=(String)data.getUser().getTemp("role");
		if((Role.equals("instructor"))||(Role.equals("student")) || (Role.equals("teacher_assistant")))
		{
			int eid=0;
			ModuleTimeThread.getController().CourseTimeSystem(user_id,eid);
		}
                }
                catch(Exception e){data.setMessage("the error in notice send java---->"+e);}

			

	}
}

