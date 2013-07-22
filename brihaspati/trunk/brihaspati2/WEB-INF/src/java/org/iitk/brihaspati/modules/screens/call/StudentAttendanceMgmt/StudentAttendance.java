package org.iitk.brihaspati.modules.screens.call.StudentAttendanceMgmt;

/*
 * @(#)StudentAttendance.java   
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;
import org.iitk.brihaspati.modules.utils.XMLWriter_StudentAttendance;
/**
 * @author <a href="tejdgurung20@gmail.com">Tej Bahadur</a>
 */

/* This screen class is called when User's selects a module Student Attendance Management.
 */
public class StudentAttendance extends SecureScreen
{
	/** Get path for StudentAttendanceManagement.*/

        String groupPath=TurbineServlet.getRealPath("/Courses");

        public void doBuildTemplate( RunData data, Context context )
        {
                try
                {
                        /**
                         * Getting file value from temporary variable according to selection
                         * Replacing the value from Property file
                         * @param mode: Getting mode as a String from Parameter Parser 
                         * @param userid: Getting userid as a String from Parameter Parser 
                         * @param courseid: Getting courseid as a String from Parameter Parser 
                         * @param instituteId: Getting instituteId as a String from Parameter Parser 
                         **/
			String courseid="";
			String course_name="";
                        User user=data.getUser();
                        String mode=data.getParameters().getString("mode","");
			String counter=data.getParameters().getString("count","");
                        context.put("mode",mode);
			context.put("tdcolor",counter);
			//Get Institute Id
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			String date=data.getParameters().getString("searchdate","");
			// Get current date using Calendar api
                        Calendar currentDate = Calendar.getInstance();
                        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
			if(date.equals("")){
                        date = formatter.format(currentDate.getTime());
			}
                        context.put("currentdate",date);
			
			if(data.getUser().getTemp("role").equals("student"))
			{
				String loginName=user.getName();
                        	context.put("loginName",loginName);
				String action=data.getParameters().getString("actionName","");
				if((mode.equals("All")) && (!action.equals("eventSubmit_doSearch")))
				{
                        		Vector getalldate=new Vector();
					courseid=(String)user.getTemp("course_id");
					String userId=Integer.toString(UserUtil.getUID(loginName));
					// Get student attendance xml file path and search attendance record.
	                               	String sampath=groupPath+"/"+courseid+"/"+"StudentAttendanceManagement/"+courseid+".xml";
					Vector attendreport = XMLWriter_StudentAttendance.getAttendReport(sampath,courseid,userId,getalldate);
                                	context.put("attendreport",attendreport);
                                	if(attendreport.size()!=0)
                                	{
                                        	// set status true for showing result in templates if attendance search is true.
                                        	context.put("status","true");
                                	}
                                	else
                                	{
                                        	// set status false for not showing result in templates if attendance search is not true.
                                        	context.put("status","false");
                                	}
				}
			}
			else
			{
				// Get UserId
				String userid=data.getParameters().getString("userid","");
				if(!userid.equals(""))
				{
					try
					{
						/**
					 	 * Get Login Name with the help of userId.
					 	 * @see UserUtil
					 	 */
                        			String loginName=UserUtil.getLoginName(Integer.parseInt(userid));
                        			context.put("loginName",loginName);
                       			}
                        		catch(Exception e)
					{
						ErrorDumpUtil.ErrorLog("Excption in getting login name--"+e);
					}
				}
			}
			
			// Get current date for getting current day's name
			Date now = new Date();
                        /**
			 * The day of the week spelled out completely.
			 * Here "EEEE" is the symbol which means 'day in week' has Type "Text". 
			 * @Example: "EEE" == "Tue" or "EEEE" == "Tuesday"
			 **/
                        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");

			// Get Course List if role is Instititute Admin
			if(data.getUser().getTemp("role").equals("institute_admin"))
			{
				courseid=data.getParameters().getString("group");	
				course_name=CourseUtil.getCourseName(courseid);
				Vector CourseList=InstituteDetailsManagement.getInstituteCourseDetails(instituteId);
                       		context.put("courseList",CourseList);
				if(!mode.equals("list"))
				{
					Vector userList=new Vector();
                                        int g_id=GroupUtil.getGID(courseid);
                                        //Get student list
                                        userList=UserGroupRoleUtil.getUDetail(g_id,3);
					if(userList.size()!=0)
	                                {
                                        	context.put("status","true");
                                	}
                                        context.put("studentlist",userList);
                                        context.put("dayName",simpleDateformat.format(now));
				}
					
			}
			else{
				// Get courseId and courseName using temp if role is "instructor" or "student".
                                courseid=(String)user.getTemp("course_id");
                                course_name=(String)user.getTemp("course_name");
				//set status for showing student list to the instructors according their courses
                                if(mode.equals("") ||mode.equals("attend"))
				{
				        if(simpleDateformat.format(now).equals("Sunday"))
					{
                                        	context.put("status","false");
                                        	context.put("dayName",simpleDateformat.format(now));
					}
					else
					{
                                		Vector userList=new Vector();
                                		int g_id=GroupUtil.getGID(courseid);
                                		//Get student list
                                		userList=UserGroupRoleUtil.getUDetail(g_id,3);
						if(userList.size()!=0)
						{
                                        		context.put("status","true");
						}
						else
						{
							context.put("status","false");
						}
                                		context.put("studentlist",userList);
                                        	context.put("dayName",simpleDateformat.format(now));
                                	}	
				}
			}
			// Send course_id and course name to the template
			context.put("course_id",courseid);
                        context.put("course",course_name);

                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("Exception in Student attendance management screen file-----"+e.getMessage());
                }
        }
}

