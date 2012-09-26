package org.iitk.brihaspati.modules.actions;

/*
 * @(#) OnlineRegistration_InstituteAdmin.java	
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.StringTokenizer;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria; 
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.commons.lang.StringUtils;
//brihaspati classes
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 */
//class for accepting and rejecting for the online course request by institute admin
public class  OnlineRegistration_InstituteAdmin extends SecureAction_Institute_Admin{

	private String LangFile=null;
	private String tokn="", uName="", gName="", mailid="", instAdminName="";
        private String uname="", gname="", email="", fname="", lname="", passwd="";
	private String [] splitedTokn ;
         
//method for rejecting the online course request by institute admin
        public void doDeleteCourse(RunData data, Context context)
        {
		ParameterParser pp=data.getParameters();
		 String instituteId=pp.getString("instituteid");
                 int instid=Integer.parseInt(instituteId);
                 String instName=InstituteIdUtil.getIstName(instid);
		 try{
                        User user=data.getUser();
			//get path of diectory where online course request store
                        String path=TurbineServlet.getRealPath("/OnlineUsers");
                        XmlWriter xmlWriter=null;
                        String accept=pp.getString("deleteFileNames");
                        Vector indexList=new Vector();
                        Vector courselist = new Vector();
			String Mail_msg="";
			String server_name= TurbineServlet.getServerName();
			String srvrPort= TurbineServlet.getServerPort();
			//get the xml file of online registered courses list in an institute.
                        TopicMetaDataXmlReader topicmetadata =new TopicMetaDataXmlReader(path+"/courses.xml");
                        courselist=topicmetadata.getOnlineUserDetails();
                        StringTokenizer st= new StringTokenizer(accept,"^");
			String message ="";
                        String info_new = "", info_Opt="", msgRegard="", instAdminName="";
			 try{    
				Criteria crit=new Criteria();
				crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instid);
				List inm=InstituteAdminUserPeer.doSelect(crit);
	                        InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
                                int Auid=UserUtil.getUID(element.getAdminUname());
                                instAdminName=UserUtil.getFullName(Auid);
			}
	                catch(Exception e){ErrorDumpUtil.ErrorLog("Error in OnlineRegistration_Admin class in action at line 245");}

                        if(srvrPort == "8080"){
                                info_new="onLineRegReqForCourseReject";
				info_Opt = "newUser";
	                }
        	        else {
                                info_new="onLineRegReqForCourseReject_https";
				info_Opt = "newUserhttps";
	                }
			Properties pr =MailNotification.uploadingPropertiesFile(TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties"));
                        String subject = MailNotification.subjectFormate(info_new, "", pr );
			msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
        	        msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
	                String msgRoleInfo = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgInstAdmin"); 
                	msgRoleInfo = msgRoleInfo.replaceAll("institute_admin",instAdminName);
                        LangFile=(String)data.getUser().getTemp("LangFile");
                        for(int j=0;st.hasMoreTokens();j++)
                        {
				/** @param gName getting GroupId  and user name**/

				tokn=st.nextToken();
				mailid = StringUtils.substringBefore(tokn, ":");
				gName = StringUtils.substringAfter(tokn, ":");
                                if(courselist!= null)
                                {
                                        for(int i=0;i<courselist.size();i++)
                                        {
                                                email=((CourseUserDetail) courselist.elementAt(i)).getEmail();
						gname=((CourseUserDetail) courselist.elementAt(i)).getGroupName().replace("&colon",":")+((CourseUserDetail) courselist.elementAt(i)).getLoginName();
                                                if((email.equals(mailid)) && gName.equals(gname))
                                                {
							message = MailNotification.getMessage(info_new, gname, "", "", "", pr);
							message = MailNotification.getMessage_new(message, "","",instName,"");
							//Mail_msg = MailNotificationThread.getController().set_Message(message, instName, msgRegard, msgRoleInfo, mailid, subject, "", LangFile, instituteId);
                                                        indexList.add(i);
							LangFile=(String)data.getUser().getTemp("LangFile");
                                                        data.setMessage(MultilingualUtil.ConvertedString("online_msg4",LangFile));
							data.addMessage(Mail_msg);
                                                }
                                        }
                                }
                        }
                        xmlWriter=TopicMetaDataXmlWriter.WriteXml_OnlineCourse(path,"/courses.xml",indexList);
                        xmlWriter.writeXmlFile();
                }//try
 
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("The error in Online registartion action do delete course method" +e);
	                data.setMessage("Please see Error log or Contact to administrator");
			}
        }
//method for accepting the online course request by an institute admin.
        public void doAcceptCourses(RunData data, Context context)
        {
                try{

			ParameterParser pp=data.getParameters();
			String gidUname="", passwd="", cname="", instName="";
			String instituteId=pp.getString("instituteid");
			int instId=Integer.parseInt(instituteId);
			instName= InstituteIdUtil.getIstName(instId);
                        Vector indexList=new Vector();
                        Vector courselist=new Vector();
			context.put("status","CourseRegistration");
                        User user=data.getUser();
                        String serverName=data.getServerName();
			String serverPort=TurbineServlet.getServerPort();
                        String accept=pp.getString("deleteFileNames");
			//get the path where online course request xml file stored
                        String path=data.getServletContext().getRealPath("/OnlineUsers"+"/courses.xml");
                        String xmlfilepath=data.getServletContext().getRealPath("/OnlineUsers");
                        TopicMetaDataXmlReader topicmetadata =new TopicMetaDataXmlReader(path);
			//read the content of xml file
                        courselist=topicmetadata.getOnlineCourseDetails();
                        StringTokenizer st=new StringTokenizer(accept,"^");

                        for(int j=0;st.hasMoreTokens();j++)
                        {
				tokn=st.nextToken();
				mailid = StringUtils.substringBefore(tokn, ":");
				gName = StringUtils.substringAfter(tokn, ":"); //Getting gid +user name
                                if(courselist!=null)
				{
                                        for(int i=0;i<courselist.size();i++)
                                        {
 
                                                email=((CourseUserDetail) courselist.elementAt(i)).getEmail();
						gidUname=((CourseUserDetail) courselist.elementAt(i)).getGroupName().replace("&colon",":")+((CourseUserDetail) courselist.elementAt(i)).getLoginName();
						//check registered online courses with selected courses
                                                if((email.equals(mailid)) && gName.equals(gidUname))
                                                {
 
                                                        gname=((CourseUserDetail) courselist.elementAt(i)).getGroupName().replace("&colon",":");
                                                        cname=((CourseUserDetail) courselist.elementAt(i)).getCourseName();
                                                        uname=((CourseUserDetail) courselist.elementAt(i)).getLoginName();
							/**
							*Set the Password if empty.
							*password is the value of 0th position of mailid
							*/
                                                        if(passwd.equals("")){
							String []starr=email.split("@");
                					passwd=starr[0];
							}
                                                        fname=((CourseUserDetail) courselist.elementAt(i)).getInstructorName();
                                                        lname=((CourseUserDetail) courselist.elementAt(i)).getUserName();
                                                       {
								//call for utils to perform registration successfull in the courses.
                                                                try{
                                                                        String msg=CourseManagement.CreateCourse(gname,cname,"","",uname,passwd,fname,lname,email,serverName,serverPort,LangFile,instId,instName,"cnfrm_c");
                                                                        data.setMessage(msg);
                                                                }
                                                                catch(Exception e){data.setMessage("Error in new Course Registration "+e);}
                                                        }
                                                        indexList.add(i);
 
                                                } //if
                                        }  //for
                                } //if
                        }
                        XmlWriter xmlwriter=TopicMetaDataXmlWriter.WriteXml_OnlineCourse(xmlfilepath,"/courses.xml",indexList);
                        xmlwriter.writeXmlFile();

                 }
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("The error in Online registartion action do accept course method" +e);
	                data.setMessage("Please see Error log or Contact to administrator");
		}
        }

	/**
          * ActionEvent responsible if no method found in this action i.e. Default Method
          * @param data RunData
          * @param context Context
          */

	public void doPerform(RunData data,Context context) throws Exception
	{
	    try{	
			LangFile=(String)data.getUser().getTemp("LangFile");
         		String action=data.getParameters().getString("actionName","");
			if(action.equals("eventSubmit_doAcceptCourses"))
			{
				doAcceptCourses(data,context);	
			}
			else if(action.equals("eventSubmit_doDeleteCourse"))
			{
				doDeleteCourse(data,context);
			}
			else
			{	
                       		data.setMessage("Action is not properly defind");
                	}   
		}
		catch(Exception e){ 
			ErrorDumpUtil.ErrorLog("The error in Online registartion admin action file" +e);
	                data.setMessage("Please see Error log or Contact to administrator");
		}			
	}

}
