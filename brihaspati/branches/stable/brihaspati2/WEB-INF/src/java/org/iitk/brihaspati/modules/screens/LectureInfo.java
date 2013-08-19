package org.iitk.brihaspati.modules.screens;
/*
 * @(#) LectureInfo.java	
 *
 *  Copyright (c) 2005, 2009,2010,2012,2013 ETRG,IIT Kanpur. 
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

import java.util.List;
import java.util.Date;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;

import org.iitk.brihaspati.om.Lecture;
import org.iitk.brihaspati.om.LecturePeer;
import org.iitk.brihaspati.om.UrlConectionPeer;
import org.iitk.brihaspati.om.UrlConection;

/**
 * This class contains code for display details of all registered courses 
 * in System 
 * 
 * @author <a href="arvindjss17@gmail.com">Arvind Pal</a>
 * @author <a href="shikhashuklaa@gmail.com">Shikha Shukla</a>
 */
public class LectureInfo extends VelocityScreen
{
    	/**
     	* @param data Rundata
     	* @param context Context
     	* @see CourseUserDetail in Utils
     	*/
	public void doBuildTemplate( RunData data, Context context ) 
        {
		try
               	{	
			org.apache.turbine.om.security.User user=data.getUser();
			ParameterParser pp=data.getParameters();
			String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			pp.add("str","session");
                        String mode=pp.getString("mode","");
                        String LecId = pp.getString("lectureId","");

                        Criteria lec = new Criteria();
                        lec.add(LecturePeer.LECTUREID,LecId);
                        List ltable_list = LecturePeer.doSelect(lec);
                        Lecture element=(Lecture)ltable_list.get(0);
                        String lecname = element.getLecturename();
                        String user_name=element.getUrlname();
                        String courseid=element.getGroupName();
                        int uid=UserUtil.getUID(user_name);
                        String userfullname=UserUtil.getFullName(uid);
			String courseName=org.iitk.brihaspati.modules.utils.CourseUtil.getCourseAlias(courseid)+"-"+org.iitk.brihaspati.modules.utils.CourseUtil.getCourseName(courseid);
			Date lDate=element.getSessiondate();
                        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String lDate_str = formatter.format(lDate);
                        String lDate_split[]=lDate_str.split(" ");
                        String lTime=element.getSessiontime();
                        String lDuration=element.getDuration();
                        String sGuest=element.getGroupName();
			String iName=org.iitk.brihaspati.modules.utils.CourseManagement.IsPrimaryInstructor(courseid);
			boolean guestatus=org.iitk.brihaspati.modules.utils.CourseUtil.getCourseGuestStatus(GroupUtil.getGID(sGuest));
			int Sessionkey=0;
			if(!(mode.equals("Clecture"))) {
				Criteria role = new Criteria();
				role.add(UrlConectionPeer.LECTUREID,Integer.parseInt(LecId));
        	                role.add(UrlConectionPeer.LOGIN_ID,"guest");
                	        role.add(UrlConectionPeer.ROLE,"student");
                        	List urlconection=UrlConectionPeer.doSelect(role);
	                        UrlConection urlcon=(UrlConection)urlconection.get(0);
        	                Sessionkey = urlcon.getSessionKey();	
			}else if(mode.equals("Clecture")) {
				Criteria role = new Criteria();
                                role.add(UrlConectionPeer.LECTUREID,Integer.parseInt(LecId));
                                role.add(UrlConectionPeer.LOGIN_ID,user.getName());
                                List urlconection=UrlConectionPeer.doSelect(role);
                                UrlConection urlcon=(UrlConection)urlconection.get(0);
                                Sessionkey = urlcon.getSessionKey();		
			}
                        context.put("key",Sessionkey);
                        context.put("date",lDate_split[0]);
                        context.put("duration",lDuration);
                        context.put("time",lTime);
                        context.put("InstructorName",iName);
			context.put("lecname",lecname);
                        context.put("coursename",courseName);
		}//end try
	       	catch(Exception e)
		{
			data.setMessage("The error in View Registered Course List !!"+e);
		}  
	}          
}
