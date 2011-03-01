package org.iitk.brihaspati.modules.actions;

/*
 * @(#)TrackingReport.java 
 *
 *  Copyright (c) 2009 ETRG,IIT Kanpur.
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
 */

import java.io.File;
import java.util.Vector;
import java.util.List;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;

import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;




/**This class contain the code Create, Delete, Update
* @author: <a href="mailto:seema_020504@yahoo.com">seema pal</a>
* @author: <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
*/


public class TrackingReport extends SecureAction
{
		VelocityScreen vs=new VelocityScreen();
	public void doSearchvalue(RunData data,Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();
			User user=data.getUser();
			String username=data.getUser().getName();
			int uid=UserUtil.getUID(username);
                        context.put("coursename",(String)user.getTemp("course_name"));
                        String courseid=(String)user.getTemp("course_id");
                        int g_id=GroupUtil.getGID(courseid);
			String userrole=data.getUser().getTemp("role").toString();
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			ParameterParser pp=data.getParameters();
			String status=pp.getString("status","");
			context.put("status",status);
                        /*Check for special characters*/
                        String matchvalue=StringUtil.replaceXmlSpecialCharacters(pp.getString("valueString"));
                        context.put("valueString",matchvalue);
			Vector userList=new Vector();
			Vector userList1=new Vector();
			String studentname="";
			List v2=null;
			/**
                        *Selecting the particular course student detail
                        *put in the context for the use in templates.
                        */
			Criteria crit =new Criteria();
			if(username.equals("admin"))
			{	
				int noUid[]={0,1};
				crit=new Criteria();
                        	crit.add(TurbineUserPeer.USER_ID,uid);
				crit.addNotIn(TurbineUserPeer.USER_ID,noUid);
                        	List v=TurbineUserPeer.doSelect(crit);
				for(int i=0;i<v.size();i++)
                                {
                                        /* This code is for Searching the group*/
                                        /*and user by the String or character*/
                                        TurbineUser element=(TurbineUser)v.get(i);
					studentname=element.getUserName().toString();
                                	userList.addElement(studentname);
				}

			}
			else if(userrole.equals("institute_admin"))
			{
				v2=ListManagement.getInstituteUserList(instituteId);
				for(int i=0;i<v2.size();i++)
                                {
                                        /* This code is for Searching the group*/
                                        /*and user by the String or character*/
					CourseUserDetail element=(CourseUserDetail)(v2.get(i));
                                        studentname=element.getLoginName().toString();
                                        userList.addElement(studentname);
                                }

			}
			else
			{
                        	crit =new Criteria();
                        	crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                        	crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        	crit.and(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                        	crit.setDistinct();
                        	List v=TurbineUserPeer.doSelect(crit);
                        	for(int i=0;i<v.size();i++)
                        	{
                       			TurbineUser element=(TurbineUser)v.get(i);
                                	studentname=element.getUserName();
                                	userList.addElement(studentname);
                       		}
			}
                       	if(userList!=null)
                       	{
                        	for(int i=0;i<userList.size();i++)
				{
					/* This code is for Searching the group*/
					/*and user by the String or character*/
                                	studentname =(String)userList.elementAt(i);
					Pattern pat =  Pattern.compile(matchvalue);
                                	Matcher mat = pat.matcher(studentname);
                                	if(mat.lookingAt())
                                	{
                                		userList1.addElement(studentname);
                                	}
				}//for
				if(userList1.size()!=0)
				{
                        		context.put("userList2",userList1);
					//context.put("check","Noblank");
					context.put("status","Search");
				}
				else
				//data.setMessage(MultilingualUtil.ConvertedString("stu_msgc",LangFile));
				data.setMessage(MultilingualUtil.ConvertedString("check_user",LangFile));
			}
		}//try
		catch(Exception e){
                ErrorDumpUtil.ErrorLog("Error in method:doSearch !!"+e);
                data.setMessage("See ExceptionLog !! " );
		}
	}//method close
	public void doSelectValue(RunData data,Context context)
	{
		try
		{
			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			String uname=pp.getString("valdir");
			context.put("usrname",uname);
			context.put("status1","Searchno");
			Vector userList1=new Vector();
			userList1.add(uname);
                        context.put("userList2",userList1);
			
		}
		catch(Exception e){
                ErrorDumpUtil.ErrorLog("Error in method:doSubmit !!"+e);
                data.setMessage("See ExceptionLog !! " );
		}
	}//method close
	public void doUserCourseInfo(RunData data,Context context)
	{
		try
		{
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
                        String username=data.getUser().getName();
                        context.put("username",username);
			int uid1=UserUtil.getUID(username);
			String mode =pp.getString("mode","");
                        context.put("mode",mode);
                        String type =pp.getString("type","");
                        context.put("type",type);
			String grouptype =pp.getString("grouptype");
                        context.put("grouptype",grouptype);


                        String year="",month="",day="";
                        year=pp.get("fStart_year");
                        context.put("fStart_year",year);
                        month=pp.get("fStart_mon");
                        context.put("fStart_mon",month);
                        day=pp.get("fStart_day");
                        context.put("fStart_day",day);
                        String fdate=year+"-"+month+"-"+day;
                        Date fdate1= Date.valueOf(fdate);
                      	long fdate2= fdate1.getTime();
                        year=pp.get("tStart_year");
                        context.put("tStart_year",year);
                        month=pp.get("tStart_mon");
                        context.put("tStart_mon",month);
                        day=pp.get("tStart_day");
                        context.put("tStart_day",day);
			data.setScreenTemplate("call,TrackingReport,Track_Reportquiz.vm");
                        //----------------------------------------------------------//
		}
		catch(Exception e){
                ErrorDumpUtil.ErrorLog("Error in method:doSubmit !!"+e);
                data.setMessage("See ExceptionLog !! " );
		}
	}//method close
	
	
 	/**
	* Default action to perform if the specified action
	* cannot be executed.
	*/
	public void doPerform(RunData data,Context context)throws Exception
	{
		String LangFile=data.getUser().getTemp("LangFile").toString();
		ParameterParser pp=data.getParameters();
       		String action = pp.getString("actionName","");
		context.put("actionName",action);
       		if(action.equals("eventSubmit_doSearchvalue"))
       			doSearchvalue(data,context);
       		else if(action.equals("eventSubmit_doSelectValue"))
       			doSelectValue(data,context);
       		else if(action.equals("eventSubmit_doUserCourseInfo"))
       			doUserCourseInfo(data,context);
		
       		else
       	 		data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));	
	}
}//class
