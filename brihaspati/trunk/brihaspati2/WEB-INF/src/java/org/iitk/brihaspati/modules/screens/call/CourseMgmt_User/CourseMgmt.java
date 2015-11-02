package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)CourseMgmt.java   
 *
 *  Copyright (c) 2011,2012, 2013 ETRG,IIT Kanpur. 
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

import java.util.Vector;
import java.io.File;
import java.lang.String;
import java.util.List;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_Cms;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.StudentInstructorMAP;
import org.apache.turbine.util.security.AccessControlList;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kuamr Singh</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka rawat</a> 
 * @modified date : 01-08-2013
 */

public class CourseMgmt extends SecureScreen {

	MultilingualUtil Mutil=new MultilingualUtil();
	public void doBuildTemplate(RunData data,Context context) {
		Vector v1=new Vector();
		AccessControlList acl=data.getACL();	
		ParameterParser pp=data.getParameters();
                String langfile=data.getUser().getTemp("LangFile").toString();
		context.put("tdcolor",pp.getString("count",""));
		User user = data.getUser();
		context.put("isAdmin",acl.hasRole("turbine_root")?"true":"false");
		/**
		*This is use for get course name and course_id
		*/
		context.put("course",((String)user.getTemp("course_name")));	
                String cid=(String)user.getTemp("course_id");
		context.put("cId",(String)user.getTemp("course_id"));
		
		context.put("isInstructor",acl.hasRole("instructor",cid)?"true":"false");
        	int GID=GroupUtil.getGID((String)user.getTemp("course_id"));
		/**
		* This vector use for get Course Instructor & secondry Instructor 
		*/
		String username=user.getName();
		int u_id=UserUtil.getUID(username);
		String loginname1="";	
	
                Vector UID=UserGroupRoleUtil.getUID(GID,2);
                List uidvector=new Vector();
                for(int i=0;i<UID.size();i++) {
                	int uid=Integer.parseInt(UID.get(i).toString());
                   	String loginname=UserUtil.getLoginName(uid);
			if(cid.indexOf(loginname)>0){
				UserUtil.getFullName(UserUtil.getUID(loginname));
                		context.put("firstname",UserUtil.getFullName(UserUtil.getUID(loginname)));
			}	
                        String uName=UserUtil.getFullName(uid);
                        uidvector.add(uName);
            	}
		/**
                  *Time calculaion for how long user use this page.
                  */
		  String role=(String)user.getTemp("role");
                 if((role.equals("student")) || (role.equals("instructor")) || (role.equals("teacher_assistant")))
                 {
			int eid=0;
			ModuleTimeThread.getController().CourseTimeSystem(u_id,eid);
                 }

		/**
		* This is use for select CourseDays
		*/
		String selectdays=data.getParameters().getString("selectdays","");
		context.put("selectdays1",selectdays);
		/**
		*Create XML file in coursemgmt directory
		*/
		String filePath=data.getServletContext().getRealPath("/Courses")+"/"+(String)user.getTemp("course_id")+"/coursemgmt/Coursemgmt.xml";
		File f=new File(filePath);
		if(f.exists()){
		   	Vector v=XMLWriter_Cms.getSearchElement(filePath,(String)user.getTemp("course_id"));
			if(v.size()>3){	
				context.put("sch4",v.get(0));
				context.put("sch5",v.get(1));
                		context.put("midsem",v.get(2));
				context.put("assignment", v.get(3));
				context.put("classnote", v.get(4));
	                	context.put("quiz",v.get(5));
	        	        context.put("labwork",v.get(6));
        	        	context.put("endsem",v.get(7));
                		context.put("message",v.get(8));
                		context.put("fileName",v.get(9));
				context.put("labinst",v.get(10));
				context.put("labinst1",v.get(11));
				context.put("tute",v.get(12));
				context.put("tute1",v.get(13));
				context.put("t",v.get(14));
				context.put("t1",v.get(15));
				context.put("t2",v.get(16));	
				context.put("t3",v.get(17));
				context.put("t4",v.get(18));
				context.put("t5",v.get(19));	
				context.put("sch",v.get(20));
				context.put("sch1",v.get(21));
				context.put("sch2",v.get(22));
				context.put("sch3",v.get(23));
                                context.put("section",v.get(24));
                                context.put("section1",v.get(25));
                                context.put("section2",v.get(26));
                                context.put("section3",v.get(27));
                                context.put("section4",v.get(28));
                                context.put("section5",v.get(29));
			}
			
		}
                context.put("seclist",uidvector);
       }
}
