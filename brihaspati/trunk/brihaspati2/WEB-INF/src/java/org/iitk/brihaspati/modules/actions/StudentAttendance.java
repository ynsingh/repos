package org.iitk.brihaspati.modules.actions;

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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 */

/**
 * @author <a href="tejdgurung20@gmail.com">Tej Bahadur</a>
 */

import java.io.File;

import java.util.Map;
import java.util.Date;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_StudentAttendance;

import org.apache.turbine.services.servlet.TurbineServlet;

public class StudentAttendance extends SecureAction
{
	/**
         * ActionEvent responsible for store attendance of every studnet, course wise in the system.
         * @param data RunData
         * @param context Context
         * @see XMLWriter_StudentAttendance from Utils
         */

	//Get path for Studnet Attendance Management.
        String groupPath=TurbineServlet.getRealPath("/Courses");
        String LangFile ="";

        public void doAttendance(RunData data, Context context)
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
                         * @see UserUtil in Util.
                         **/
			
			String courseid="";
                        User user = data.getUser();
                        ParameterParser pp=data.getParameters();
                        LangFile=data.getUser().getTemp("LangFile").toString();
			String userrole= user.getTemp("role").toString();
			String currentdate=pp.getString("searchdate","");
			String counter=data.getParameters().getString("count","");
                        context.put("tdcolor",counter);
			
			// Get courseid according to user role
			if(userrole.equals("institute_admin"))
			{
                                courseid=pp.getString("group");
                        }
                        else
			{
                                courseid=(String)user.getTemp("course_id");
                        }
                        
			// Get path for create StudentAttendanceManagemnt Directory.
			String path=groupPath+"/"+courseid+"/"+"StudentAttendanceManagement";
                        String sampath=path+"/"+courseid+".xml";
			File f = new File(sampath);
			boolean AttenDateExist=false;
			
			// check student attendance already done or not in attendance xml file. 
	  		if(f.exists())
			{
				AttenDateExist=XMLWriter_StudentAttendance.getAttendanceDate(sampath,currentdate);
			}
			
			// show message if attendance already done.
			if(AttenDateExist)
			{
				data.setMessage(MultilingualUtil.ConvertedString("brih_sam2",LangFile));
			}
			
			/**
			 * Code for submit student attendance.
			 * getting student attendance list from tempalte.
			 */
			else
			{
				String AttendanceList=pp.getString("selectFileNames","");
				Vector attend_list=new Vector();
				
				/**
				 * Get student attendance directory.
				 * If not exist then create it and store attendance.
				 */
				File sam=new File(path);
				if(!sam.exists())
				{
                        		sam.mkdirs();
				}
                        
				// Use StringTokenizer to break string after "^".
                        	StringTokenizer st=new StringTokenizer(AttendanceList,"^");
                        	Vector v=new Vector();
                        	for(int i=0;st.hasMoreTokens();i++)
                        	{
                                	v.addElement(st.nextToken());
                        	}
				for(int i=0;i<v.size();i++)
                        	{	
                                	String temp=(v.elementAt(i).toString());
                                	String tempSplit[]=temp.split(":");
					//String srno=tempSplit[0];
					String loginname=tempSplit[1];	// Get Login Name
					String userid=Integer.toString(UserUtil.getUID(loginname));	// Get UserId with the help of loginName.
					String email=tempSplit[2];	// Get Email Id
					String att_status1=tempSplit[3];
					String att_status=pp.getString(att_status1,"");	// Get Attendance Status(Present,Absent,Leave)
					String remark1=tempSplit[4];
					String remark=pp.getString(remark1,"");		//Get Remarks
				
					// Set all values 
					CourseUserDetail cu=new CourseUserDetail();
					cu.setUserId(userid);		// Set UserId
					cu.setEmail(email);		// Set Email
					cu.setStatus(att_status);	// Set Status
					cu.setRemarks(remark);		// Set Remarks
					cu.setCreateDate(currentdate);	// Set Attendance Date 
					attend_list.add(cu);		// Add in Vector
				}	
				if(attend_list.size()!=0)
				{
					String writeinxml=XMLWriter_StudentAttendance.StudentAttendanceListXml(sampath,attend_list);
					if(writeinxml.equals("Successfull"))
					{		
						data.setMessage(MultilingualUtil.ConvertedString("brih_sam1",LangFile));
						doAttendanceSearch(data,context);
						context.put("status","true");
					}
				}
			}
        	}
		catch(Exception e)
		{
				ErrorDumpUtil.ErrorLog("Exception in getting list");
		}

	}
        
	/**
         * ActionEvent responsible for search and showing attendance as per requirement in the system.
         * @param data RunData
         * @param context Context
         * @see XMLWriter_StudentAttendance from Utils
         */
	
	public void doAttendanceSearch(RunData data, Context context)
        {
        	try
                {
			/**
                         * Getting file value from temporary variable according to selection
                         * Replacing the value from Property file
                         * @param mode: Getting mode as a String from Parameter Parser 
                         * @param userid: Getting userid as a String from Parameter Parser 
                         * @param courseid: Getting courseid as a String from Parameter Parser 
                         * @param date: Getting date as a String from Parameter Parser 
                         * @see XMLWriter_StudentAttendance in Util.
                         **/
                        ParameterParser pp=data.getParameters();
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String courseid="";
			String coursename="";
			String enddate="";
                        LangFile=data.getUser().getTemp("LangFile").toString();
			String date=pp.getString("searchdate","");
			String counter=data.getParameters().getString("count","");
                        context.put("tdcolor",counter);

			if(mode.equals("update")){
				date=pp.getString("date","");
			}
			// Get courseid and course name according to user role
			if(data.getUser().getTemp("role").equals("institute_admin")){
                                courseid=pp.getString("group");
				coursename=CourseUtil.getCourseName(courseid);
                        }
			else{
				courseid=(String)data.getUser().getTemp("course_id");
			}
			// put data in context for showing in template
			context.put("course",coursename);
			context.put("course_id",courseid);
			context.put("searchdate",date);
			context.put("encodedate",java.net.URLEncoder.encode(date,"UTF-8"));
		
			// split two dates and getting all dates between two date.	
			String tempSplit[]=date.split("-");
			String startdate=tempSplit[0];
			if(date.length()>10)
			{
				enddate=tempSplit[1];
			}
			else
			{
				enddate= startdate;
			}
			if(!mode.equals("attend"))
			{
				String loginname="";
				String userId="";	
				// Get loginname and userid according to user role.
				if(data.getUser().getTemp("role").equals("student"))
				{
					loginname=data.getUser().getName();
                                        userId= Integer.toString(UserUtil.getUID(loginname));
				}
				else{
					userId=pp.getString("userid","");
                                        if(userId.equals(""))
					{
                                                loginname=pp.getString("loginName","");
                                                if(!loginname.equals(""))
                                                {
                                                        userId= Integer.toString(UserUtil.getUID(loginname));
                                                }
                                        }
				}
				
				// put values in context for showing in template.
				context.put("userid",userId);
				context.put("loginName",loginname);
				// Get student attendance xml file path and search attendance record.
				String sampath=groupPath+"/"+courseid+"/"+"StudentAttendanceManagement/"+courseid+".xml";
				context.put("path",sampath);
				
				Vector getalldate=new Vector();
				//Get Full attendance of student if mode is "All".
				if(mode.equals("All")){
					if(!date.equals("")){
						getalldate=XMLWriter_StudentAttendance.getAllDates(startdate,enddate);
						context.put("date",getalldate);
					}
					else{
						getalldate=new Vector();
					}
				}
				else{
					getalldate=XMLWriter_StudentAttendance.getAllDates(startdate,enddate);
				}
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
			else
			{
				int g_id=GroupUtil.getGID(courseid);
                        	Vector userList=new Vector();
                        	userList=UserGroupRoleUtil.getUDetail(g_id,3);
                        	context.put("studentlist",userList);
				if(userList.size()==0)
				{
					// set status false for not showing result in templates if attendance search is not true.
					context.put("status","false");
				}
				else	
				{
					// set status true for showing result in templates if attendance search is true.
					context.put("status","true");
				}
			}
                }
                catch(Exception e)
                {
                                ErrorDumpUtil.ErrorLog("Exception getting in search of student's attendance"+e);
                }
        }
	
	/**
         * ActionEvent responsible for update attendance record.
         * @param data RunData
         * @param context Context
         * @see XMLWriter_StudentAttendance from Utils
         */

	public void UpdateAttendance(RunData data, Context context)
        {
        	try
        	{
			/**
                  	 * Get  courseid for the user currently logged in
                  	 * @see UserUtil in Util.
                  	 */
                  	User user = data.getUser();
                        ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			String date=pp.getString("date","");
			String counter=data.getParameters().getString("count","");
                        context.put("tdcolor",counter);
                        context.put("searchdate",date);
			String courseid="";
			if(data.getUser().getTemp("role").equals("institute_admin"))
			{
                                courseid=pp.getString("group");
                        }
			else
			{
                        	courseid=(String)user.getTemp("course_id");
			}
			String mode=pp.getString("mode","");
                        context.put("mode",mode);

                        // Get path for create StudentAttendanceManagemnt Directory.
                        String path=groupPath+"/"+courseid+"/"+"StudentAttendanceManagement";
                        String sampath=path+"/"+courseid+".xml";
			
                        String loginname=pp.getString("loginName","");
			context.put("loginName",loginname);
			String userid=Integer.toString(UserUtil.getUID(loginname));
                        Vector attend_list=new Vector();
			
			/**
                         * Get student attendance directory.
                         * If exist then update student attendance record.
                         */
                        File sam=new File(path);
                        if(sam.exists()){
                        
                        // Use StringTokenizer to break string after "^".
                        String AttendanceList=pp.getString("selectFileNames","");
                        StringTokenizer st=new StringTokenizer(AttendanceList,"^");
                        Vector v=new Vector();
                        for(int i=0;st.hasMoreTokens();i++)
                        {
                                v.addElement(st.nextToken());
                        }
                        for(int i=0;i<v.size();i++)
                        {
                                String temp=(v.elementAt(i).toString());
                                String tempSplit[]=temp.split(":");
                                //String srno=tempSplit[0];
                                String att_status1=tempSplit[1];
                                String att_status=pp.getString(att_status1,"");	// Get Attendance Status
                                String creationdate=tempSplit[2];		// Get Attendance Date
				
				// Set all values 
                                CourseUserDetail cu=new CourseUserDetail();
                                cu.setStatus(att_status);	// Set Attendance Status
                                cu.setCreateDate(creationdate);	// Set Attendance Date
                                attend_list.add(cu);		// Add in Vector
                        }
                                if(attend_list.size()!=0)
				{
                                	String UpdateUser=XMLWriter_StudentAttendance.UpdateUserAttendance(sampath,userid,attend_list);
					doAttendanceSearch(data,context);
					data.setMessage(MultilingualUtil.ConvertedString("brih_sam3",LangFile));
					context.put("status","true");
                                }
                        }
		}	
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in updation of student attendance record !!"+e.getMessage());
		}

	}
	 
	/**
         * Default action to perform if the specified action
         * cannot be executed.
         * @param data RunData
         * @param context Context
         */
        public void doPerform(RunData data, Context context) throws Exception
        {
                String action=data.getParameters().getString("actionName","");
                if(action.equals("eventSubmit_doAttendance"))
                {
                        doAttendance(data,context);
                }
		else if(action.equals("eventSubmit_doSearch"))
                {
                        doAttendanceSearch(data,context);
                }
		else if(action.equals("eventSubmit_doUpdate"))
		{
			UpdateAttendance(data,context);
		}
        }
}
