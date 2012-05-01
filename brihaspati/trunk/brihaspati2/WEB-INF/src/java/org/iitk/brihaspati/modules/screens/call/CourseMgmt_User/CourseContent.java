package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)CourseContent.java	
 *
 *  Copyright (c) 2006-2008,2010,2012 ETRG,IIT Kanpur. 
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
import java.util.List;
import java.io.File;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.AccessControlList;
//import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.Turbine;
//import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;;
import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
//import org.iitk.brihaspati.modules.utils.NotInclude;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.xml.sax.helpers.AttributesImpl;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;


/**
* This class manage all course contents
* @author <a href="mailto:ammuamit@hotmail.com">Amit Joshi</a>
* @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
* @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
* @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
* @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
* @modified date:30-04-2012(Richa)
*/

public class CourseContent extends VelocitySecureScreen{
	 MultilingualUtil Mutil=new MultilingualUtil();

	public void doBuildTemplate(RunData data,Context context) 
	{
		String langfile=data.getUser().getTemp("LangFile").toString();
		try{

			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			AccessControlList acl=data.getACL();
			context.put("tdcolor",pp.getString("count",""));
			context.put("isAdmin",acl.hasRole("turbine_root")?"true":"false");
			String group,dir;
			String stat=pp.getString("stat","");
			context.put("course",(String)user.getTemp("course_name"));
			group=dir=(String)user.getTemp("course_id");
			String role=(String)user.getTemp("role");
                        context.put("user_role",role);
			String filePath=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content";
			//File dFile=new File(filePath+"/"+"content__des.xml");
			File dFile=new File(filePath+"/"+"coursecontent__des.xml");
			String filePath1=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content"+"/Permission";
	
			File f=new File(filePath1+"/permissionReceive__des.xml");
			 /**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if((role.equals("student")) || (role.equals("instructor")))
                         {
                                CourseTimeUtil.getCalculation(uid);
                                ModuleTimeUtil.getModuleCalculation(uid);
                         }

			if( acl.hasRole("instructor",group))
			{
				context.put("isInstructor","true");
			}
			
			File Path=new File(filePath+"/coursecontent__des.xml");
			/**
			* Write all topic in xml file if topic is not present
			*/
			CommonUtility.cretUpdtxml(filePath,user.getName(),"course");
			int gid = GroupUtil.getGID(group);
			Criteria crit = new Criteria();
	                crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
	                crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
	                crit.add(TurbineUserGroupRolePeer.USER_ID,0);
	                List v=TurbineUserGroupRolePeer.doSelect(crit);
			String gstaccess = pp.getString("GuestAccess","");
			String contentTopic = pp.getString("topic","");
			XmlWriter xmlWriter=null;	
			String st=""; 
			boolean flag=false;
			context.put("AccessMsg","");
                        if(Path.exists())
                        {
                                TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"coursecontent__des.xml");
                                Vector dc=topicMetaData.getFileDetailsModify();
                                if(dc.size()!=0)
                                {
                                        context.put("dirContent",dc);
                                }
				/**
 				 * Instructor have permission to give guest access inside course content.
 				 * So below code is only executed when role is instructor.
 				 * Before giving access to guest, first check guest have permission to access that course or not.
 				 */ 	
				if(role.equals("instructor"))
				{
					topicMetaData=null;
					String Xmlgstaccess=null;
					for(int i=0;i<dc.size();i++){
                	                       	st=((FileEntry) dc.elementAt(i)).getName();
						String Pdate=((FileEntry)dc.elementAt(i)).getPDate();
						Xmlgstaccess=((FileEntry)dc.elementAt(i)).getGuestAccess();
						if(st.equals(contentTopic))
						{
							xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(filePath,"coursecontent",st,gstaccess);
							xmlWriter.writeXmlFile();
						}
						else
							flag=true;				
					}
					if(flag)
					{
						st="";
						if(v.size()==0)
						{
							xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(filePath,"coursecontent",st,"true");
							xmlWriter.writeXmlFile();
							context.put("courseaccess",v.size());
							String Accessmsg = MultilingualUtil.ConvertedString("Guest_msg3",langfile);
							context.put("AccessMsg",Accessmsg);
						}
						else if(org.apache.commons.lang.StringUtils.isBlank(Xmlgstaccess))	
						{
							xmlWriter=TopicMetaDataXmlWriter.WriteXml_NewModify(filePath,"coursecontent",st,"false");
	                                                xmlWriter.writeXmlFile();
						}
					}
	                                topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"coursecontent__des.xml");
	                                Vector reader=topicMetaData.getFileDetailsModify();
					context.put("dirContent",reader);
				}
			}
                        /**
                        * Remote Course Path
                        */

                        String filePathR=data.getServletContext().getRealPath("/Courses")+"/"+dir+"/Content"+"/Remotecourse";

                        File fR=new File(filePathR+"/RemoteCourse__des.xml");


                        if(fR.exists())
                        {
                                Vector ReadR=new Vector();
                                TopicMetaDataXmlReader permissionReadR=new TopicMetaDataXmlReader(filePathR+"/RemoteCourse__des.xml");
                                ReadR=permissionReadR.getDetails();
                                context.put("readR",ReadR);
                        }



		}//try
		catch(Exception ex)
		{
		data.setMessage("The error in Course Contents !! "+ex);
		}
	}
	/**
	* This method responsible for authorization
	* @param data RunData
	*/	
	protected boolean isAuthorized( RunData data )  throws Exception
        {
                boolean isAuthorized = false;
                AccessControlList acl = data.getACL();
		if( acl.hasRole("turbine_root") )
		{
			isAuthorized=true;
			return isAuthorized;
		}
                try
                {
                        User user=data.getUser();
                        String g=user.getTemp("course_id").toString();

                        if (acl==null || (! acl.hasRole("instructor",g) && !acl.hasRole("student",g) && !acl.hasRole("turbine_root")) )
                        {
                                data.setScreenTemplate( Turbine.getConfiguration().getString("template.login"));

                                isAuthorized = false;
                        }
                        else if(acl.hasRole("instructor",g) || acl.hasRole("student",g) || acl.hasRole("turbine_root"))
                        {
                                isAuthorized = true;
                        }
                }
                catch(Exception e)
                {
                        data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));
                        return false;
                }
                return isAuthorized;
        }
}

