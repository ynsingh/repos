package org.iitk.brihaspati.modules.screens.call.Notice_User;

/*
 * @(#)Notices.java	
 *
 *  Copyright (c) 2005, 2010 ETRG,IIT Kanpur. 
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
 * @ modified date: 13-Oct-2010 (Shaista)
 */

import java.util.Vector;
import java.util.List;

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
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

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
                        String loginname=user.getName();
                        int user_id=UserUtil.getUID(loginname);
			ParameterParser pp = data.getParameters();
                        //used for group management
                        //String mode1=data.getParameters().getString("mode1","");
                        String mode1=pp.getString("mode1","");
                        context.put("mode1",mode1);
                        //String grpname=data.getParameters().getString("val1","");
                        String grpname=pp.getString("val1","");
                        context.put("val",grpname);

                        //String flag=data.getParameters().getString("nflag","");
                        String flag=pp.getString("nflag","");
			
                        context.put("nflag",flag);
                        //String counter=data.getParameters().getString("count","");
                        String counter=pp.getString("count","");
                        context.put("tdcolor",counter);
                        context.put("tdcolor1",pp.getString("countTemp",""));
                        /**
                         * Retreives all courses for use of Admin and Institute Admin
                         */
			String rolename=(user.getTemp("role")).toString();
			String instituteId=(user.getTemp("Institute_id")).toString();
			ErrorDumpUtil.ErrorLog("rname=="+rolename+"\niId==="+instituteId);
			List CList=null;
                        if(loginname.equals("admin")){
                        	CList=ListManagement.getCourseList();
				ErrorDumpUtil.ErrorLog("clist in admin loop====="+CList);
                        	//context.put("clist",CList);
			}
			else{
				ErrorDumpUtil.ErrorLog("testing in else loop in notices");
				if(rolename.equals("institute_admin")){	
                                //CList=ListManagement.getInstituteCourseList(instituteId);
				CList=CourseManagement.getInstituteCourseNUserDetails("All",instituteId);
				ErrorDumpUtil.ErrorLog("size()instituteadmin loop=="+CList.size());
                        	//context.put("clist",CList);
				}
			}
			ErrorDumpUtil.ErrorLog("clist out of admin loop====="+CList);
                        context.put("clist",CList);
                        //CList=null;
                        /**
                         * Retreives all the courses in which the user is an instructor
                         */
			Vector courselist=new Vector();
			Vector groupIdList=new Vector();
                        Vector v=UserGroupRoleUtil.getGID(user_id,2);
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
			ErrorDumpUtil.ErrorLog("\n\n\n\nfrom Notices.java  courseId="+courseid+"\n userInCourse="+userInCourse);
			if( userInCourse!=null && !userInCourse.equals("") && courseid.equals(""))
			{
				ErrorDumpUtil.ErrorLog("\n\nin if from Notices.java  courseId="+courseid+"\n userInCourse="+userInCourse);
				courseid = userInCourse;
                        	C_Name=(String)(user.getTemp("course_name"));
			}
			else
			{
				ErrorDumpUtil.ErrorLog("\n\nin else from Notices.java  courseId="+courseid+"\n userInCourse="+userInCourse);
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
		if((Role.equals("instructor"))||(Role.equals("student")))
		{
			CourseTimeUtil.getCalculation(user_id);
        	        ModuleTimeUtil.getModuleCalculation(user_id);
		}
                }
                catch(Exception e){data.setMessage("the error in notice send java---->"+e);}

			

	}
}

