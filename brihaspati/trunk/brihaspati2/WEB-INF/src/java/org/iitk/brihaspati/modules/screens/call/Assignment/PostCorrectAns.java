package org.iitk.brihaspati.modules.screens.call.Assignment;

/*
 * @(#)ViewAss.java 
 *
 *  Copyright (c) 2007,2013 ETRG,IIT Kanpur.
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.io.File; 
import java.util.List;

import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.AssignmentDetail;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;

import org.iitk.brihaspati.om.Assignment;   
import org.iitk.brihaspati.om.AssignmentPeer;   
import org.iitk.brihaspati.om.TurbineUser;   
import org.iitk.brihaspati.om.TurbineUserPeer;   
import org.iitk.brihaspati.om.TurbineUserGroupRole;   
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;   
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.News;

import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil; 
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.apache.turbine.om.security.User;       
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;
import org.iitk.brihaspati.modules.utils.NewsHeadlinesUtil;
import org.iitk.brihaspati.modules.utils.NewsDetail;
	/**
	 *   This class contains code for all discussions in workgroup
	 *   Compose a discussion and reply.
	 *   @author  <a href="arvindjss17@yahoo.co.in">Arvind Pal</a>
	 *   @author  <a href="smita37uiet@gmail.com">smita Pal</a>
	 *   @author  <a href="Tej Bahadur@gmail.com">Tej Bahadur</a>
	 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
	 * @modified date: 31-03-2014(Dewanshu Singh)
	*/


public class PostCorrectAns extends  SecureScreen
{
	
        public void doBuildTemplate(RunData data, Context context)
        {
                try
                {
                        User user=data.getUser();
                        String UserName=data.getUser().getName();
                        ParameterParser pp=data.getParameters();
			String Role = (String)user.getTemp("role");
                        //context.put("user_role",Role);
                        context.put("coursename",(String)user.getTemp("course_name"));
                        //String courseid=(String)user.getTemp("course_id");
			context.put("topicName",data.getParameters().getString("topicname",""));
			context.put("fileName",data.getParameters().getString("filename",""));
                        context.put("tdcolor",pp.getString("count",""));
			 Date curDate=new Date();
                        long longCurDate= curDate.getTime();
                        /**
                          *Time calculaion for how long user use this page.
                          */
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
                                int eid=0;
                                ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }

			String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date3=cdate.substring(0,4);
                        date3=date3+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);
                        context.put("date",date3);

        		
		} //try
                catch(Exception e){ ErrorDumpUtil.ErrorLog("The Error in PostCorrectAns screen for instructor "+e); }
        }
}
