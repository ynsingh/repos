
package org.iitk.brihaspati.modules.actions;

/**
 * @(#)OnlineRegistration.java	
 *  
 *  Copyright (c) 2008-2010,2012 ETRG,IIT Kanpur. 
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
import java.util.Properties;
import java.sql.Date;

import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;

/**
 * This class is called for requesting to add a new user in specified group and 
 * assigned a role to the system. 
 * 
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:omprakash_kgp@yahoo.co.in">Om Prakash</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @modify 20-03-09
 * @modified date: 08-07-2010
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>20092010
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a>3May2012
 * @modified date: 20-10-2010,23-12-2010, 16-06-2011,20-04-2012
 */


public class OnlineRegistration extends VelocitySecureAction
{

	private String LangFile="";
	private	String server_name= "";
        private String srvrPort= "";
	private String emailId = "";
	private String MsgForExpireTime = "";
	private String status = "", lang="";
	private long longCreationDate;
	private long longCurrentDate;
	private long noOfdays;
	private Vector vc = new Vector();
	private String  orgtn="",curDate="", path="";
        private String uname="", gname="", email="", fname="", lname="", passwd="", rollno="", program="";
	private Properties pr;
	private String subject="", message="", info_new = "", fileName, Mail_msg="";

        protected boolean isAuthorized( RunData data ) throws Exception
        {
                return true;
        }

/**
 *
 * Method for registered a user as Secondary Instructor,Student
 * and Content Author
 * @param data RunData instance
 * @param context Context instance
 *
 */
	public void UserRegister(RunData data, Context context) throws Exception
	{
		Vector indexList= new Vector();
		StringUtil S=new StringUtil();
		int group_id =0;
			
		ParameterParser pp=data.getParameters();
		
		/**
                 * Retreiving details entered by the user
                 */
                XmlWriter xmlWriter=null;
		fname=pp.getString("FNAME","");
		lname=pp.getString("LNAME","");
		orgtn=pp.getString("ORGTN","");
                email=pp.getString("EMAIL");
                rollno=pp.getString("rollno","").trim();
                program=pp.getString("prg","");
		uname=email;
                passwd=pp.getString("PASSWD");
                if(passwd.equals("")){
		String []starr=email.split("@");
                passwd=starr[0];
		}
		gname=pp.getString("group","");
		//Get onlinecong value of the course added by sharad on 02022011
		Criteria crit = new Criteria();
		crit.add(CoursesPeer.GROUP_NAME,gname);
		List onconf=CoursesPeer.doSelect(crit);
		int onlineconf = ((Courses)(onconf.get(0))).getOnlineconf();
                //end
		String instituteid="", instAdminName="";	
		if(!gname.equals("author")){
			/*String []gnamesplit=gname.split("_");
			//String Agname=gnamesplit[0];
			instituteid=gnamesplit[1];
			*/
			instituteid=org.apache.commons.lang.StringUtils.substringAfterLast(gname,"_");
		}
		 String instName = InstituteIdUtil.getIstName(Integer.parseInt(instituteid));	
		// below line added by shaista
		try{	crit=new Criteria();
			crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                        List inm=InstituteAdminUserPeer.doSelect(crit);
			InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
			/**modify by jaivir,seema 
                        *Getting full name of user using UserUtil.
                        *@see UserUtil in utils
                        */
			int Auid=UserUtil.getUID(element.getAdminUname());
                        instAdminName=UserUtil.getFullName(Auid);
			//instAdminName=element.getAdminFname() +" "+element.getAdminLname();

		}
		catch(Exception e){}
		//ErrorDumpUtil.ErrorLog("instAdminNameeeeeeeeeeeeeeeeeeee OnlineRegistration Action="+instAdminName);
                String roleName=pp.getString("role","");
		curDate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                if(gname.equals(""))
                {
                        gname=pp.getString("group_author");
                }
                if(roleName.equals(""))
                {
                        roleName=pp.getString("role_author");
                }
                lang=pp.getString("lang","english");
                LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);

		//for checking online configuration value for course added modified by sharad 
		if(onlineconf==0){ 
	                path=data.getServletContext().getRealPath("/OnlineUsers");
        	        File filepath=new File(path);
                if(!filepath.exists())
                {
                        filepath.mkdirs();
                }
                File file=new File(path+"/OnlineUser.xml");

                if(!file.exists())
                {
			TopicMetaDataXmlWriter.WriteOnlineReqRootOnly(file.getAbsolutePath());
                        xmlWriter=new XmlWriter(path+"/OnlineUser.xml");			
		}
		TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path+"/OnlineUser.xml");
                Vector userlist = topicmetadata.getOnlineUserDetails();
        	context.put("lang",lang);
		context.put("status","UserResitration");
		if(S.checkString(uname)==-1 && S.checkString(fname)==-1 && S.checkString(lname)==-1 && S.checkString(gname)==-1 && S.checkString(orgtn)==-1 && S.checkString(rollno)==-1)
		{ //if 1
			boolean userExists = true;
			userExists=UserManagement.checkUserExist(uname);
			boolean RoleExist= true;
			if(userExists == false)
			{
				int user_id=UserUtil.getUID(uname);
                                group_id=GroupUtil.getGID(gname);
                          	RoleExist=UserManagement.checkRoleinCourse(user_id,group_id);
			}
                        /**
                         *Checks if existing user already has some
                         * role in the group if yes then the specified
                         * message is given else the user is assigned
                         * the role in specified group
                         */
			if(userExists == false && RoleExist==false)
			{ //if 2
				/**
                        	for(int j=0; j<10; j++)
	                        {
					userExists=UserManagement.checkUserExist(uname +getNext());
					if(userExists == true)
					 	vc.addElement(uname+getNext()+"\n");
				}
				*/
				data.addMessage(MultilingualUtil.ConvertedString("u_msg",LangFile));
                        //	data.addMessage(MultilingualUtil.ConvertedString("online_msg10",LangFile) +vc.toString());
                        	data.addMessage(MultilingualUtil.ConvertedString("online_msg11",LangFile));
                                setTemplate(data,"OnlineRegistration.vm");
				return;
			} //if 2					
			else
			{ //else 2
				if(userlist != null) //if 3
				{ 
					int i=0,j=0;
	                                String gName = "";
        	                        String uName = "";
                	                boolean flag= false;
                                	vc = new Vector();
        	                        for( i=0; i <userlist.size(); i++)
                	                {
                        	                gName =((CourseUserDetail)userlist.get(i)).getGroupName();
                                	        uName=((CourseUserDetail)userlist.get(i)).getLoginName();
	                                        if(gName.equals(gname) && uName.equals(uname))
        	                                {
               	                                        flag = true;
	                                        	break;
                	                                //for(j=0; j<10; j++)
                        	                        //{
                                	                        /**
                                        	                 * Getting Random Group name if Given Group Name with same User Name is already  exist in xml
                                                	         * See OnlineReg/OnlineUser.xml file
                                                        	 */
	                                                        //vc.addElement(uname+getNext()+"\n");
                	                                        //flag = true;
                        	                       // }
                                	        }
                                        	//if(flag == true)
	                                        //break;
        	                        }
					//if(vc != null && vc.size() > 0 ) //if 4
                                       	if(flag == true)
	                                {
        	                                data.addMessage(MultilingualUtil.ConvertedString("u_msg9",LangFile));
						//data.addMessage(vc.toString());
                        			//data.addMessage(MultilingualUtil.ConvertedString("online_msg10",LangFile) +vc.toString());
                        			data.addMessage(MultilingualUtil.ConvertedString("online_msg11",LangFile));
                                	        setTemplate(data,"OnlineRegistration.vm");
                                        	return;

	                                } //if 4
	                                else  //else 4
        	                        {
						server_name= TurbineServlet.getServerName();
	        	                	srvrPort= TurbineServlet.getServerPort();
						MsgForExpireTime = "forUser "; 
						indexList = sendMail_MoreThanSevenDays(userlist, MsgForExpireTime, uname, server_name, srvrPort, LangFile, instAdminName, instituteid);
						xmlWriter=TopicMetaDataXmlWriter.WriteXml_OnlineUser(path,"/OnlineUser.xml",indexList);
						if(program.equals("Select Program"))
                                                        program="";
				                TopicMetaDataXmlWriter.appendOnlineUserElement(xmlWriter,uname,passwd,fname,lname,orgtn,email,gname,roleName,curDate,rollno,program, instName);
        				        xmlWriter.writeXmlFile();
						if(gname.equals("author"))
							sendMailToApproval(gname,LangFile,uname,"","","",0);
						else
							sendMailToApproval(gname,LangFile,uname,fname,lname,"",(Integer.parseInt(instituteid)));
					
			        	} //else 4
				} //if 3

				else //else 3
				{
					indexList.add(-1);
	                		xmlWriter=TopicMetaDataXmlWriter.WriteXml_OnlineUser(path,"/OnlineUser.xml",indexList);
					if(program.equals("Select Program"))
                                                        program="";
		        	        TopicMetaDataXmlWriter.appendOnlineUserElement(xmlWriter,uname,passwd,fname,lname,orgtn,email,gname,roleName,curDate,rollno,program, instName);
        		        	xmlWriter.writeXmlFile();
					sendMailToApproval(gname,LangFile,uname,fname,lname,"",(Integer.parseInt(instituteid)));
				} //else 3
				String str=MultilingualUtil.ConvertedString("online_msg5",LangFile);
        		        data.setMessage(str);
			} // else 2
		} //if 1
		else //else 1
                {
			data.addMessage(MultilingualUtil.ConvertedString("c_msg3",LangFile));
			setTemplate(data,"OnlineRegistration.vm");
                        return;
                } //else 1
		}//end sharad
		else{
			String serverName=data.getServerName();
                        int srvrPort=data.getServerPort();
                        String serverPort=Integer.toString(srvrPort);
                        String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,orgtn,email,gname,"student",serverName,serverPort,LangFile,rollno,program); 
                        data.setMessage(msg);

		}

	} //method

	/**
	 * This method actually registers a new course along with the instructor in the system
 	 */
        public void CourseRegister(RunData data, Context context) throws Exception
        {
 
		boolean doesNotExists = true, bl= false;
		String instAdminName ="";
		//Vector vc = new Vector();
                ParameterParser pp=data.getParameters();
                /**
                 * store details from the page where user has entered them
                 */
                String instname=pp.getString("instName");
		Criteria crit=new Criteria();
		crit.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,instname);
		List ialist=InstituteAdminRegistrationPeer.doSelect(crit);
		int instituteid=((InstituteAdminRegistration)ialist.get(0)).getInstituteId();
		String InstituteId=Integer.toString(instituteid);
		//InstituteIdUtil Instid=new InstituteIdUtil();
		//int instid =Instid.getIst_Id(instname);
                gname=pp.getString("COURSEID","").toUpperCase();
                String cname=pp.getString("CNAME","");
                String dept=pp.getString("DEPT","");
                passwd=pp.getString("PASS");
                fname=pp.getString("FNAME","");
                lname=pp.getString("LNAME","");
		String orgtn=pp.getString("ORGTN","");
                email=pp.getString("EMAIL","");
		uname=email;
                if(passwd.equals("")){
		String []starr=email.split("@");
                passwd=starr[0];
		}
		/**
		 * Below line added by Shaista 
		 * Getting institute admin's First n Last Name according to instituteId
		 */
		 try{   crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                        List inm=InstituteAdminUserPeer.doSelect(crit);
                        InstituteAdminUser element=(InstituteAdminUser)inm.get(0);
			/**modify by jaivir,seema 
                        *Getting full name of user using UserUtil.
                        *@see UserUtil in utils
                        */
                        int Auid=UserUtil.getUID(element.getAdminUname());
                        instAdminName=UserUtil.getFullName(Auid);
                        //instAdminName=element.getAdminFname() +" "+element.getAdminLname();

		}

                catch(Exception e){}

		curDate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                path=TurbineServlet.getRealPath("/OnlineUsers");
                File filepath=new File(path);
                XmlWriter xmlWriter=null;
                if(!filepath.exists())
                {
                        filepath.mkdirs();
                }
                File file=new File(path+"/courses.xml");
                if(!file.exists())
                {
                        TopicMetaDataXmlWriter.WriteOnlineReqRootOnly(file.getAbsolutePath());
                        xmlWriter=new XmlWriter(path+"/courses.xml");
                }

		TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path+"/courses.xml");
                Vector courselist = topicmetadata.getOnlineCourseDetails();
		Vector indexList = new Vector();
		CourseManagement courseMgmt = new  CourseManagement();
                lang=pp.getString("lang","english");
                LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
		context.put("status","CourseRegistration");

		/**
		 * Getting Random Group name if Given Group Name for same User already  exist in database
		 * @param vc is vector having list of Random Group name 
		 */	
		String coursename=gname+uname+"_"+instituteid;
		doesNotExists = courseMgmt.CheckCourseExist(coursename,LangFile);
                if(doesNotExists==false)
                {
		 	/** Again checking Random Group name if Given Group Name for same User in database **/

                        for(int j=0; j<10; j++)
                        {
                                //getNext();
			doesNotExists = courseMgmt.CheckCourseExist(gname+getNext()+uname,LangFile);
                	if(doesNotExists==true)
			
                                vc.addElement(gname+getNext()+"\n");
                        }
                        data.addMessage(MultilingualUtil.ConvertedString("c_msg1",LangFile));
                        //data.addMessage(vc.toString());
       			data.addMessage(MultilingualUtil.ConvertedString("online_msg9",LangFile) +vc.toString());
                        setTemplate(data,"OnlineRegistration.vm");
                        return;
		}
                else 
		{
			vc = new Vector();
			if(courselist != null)
			{
				int i=0,j=0;
				String gName = "";
				String uName = "";
				boolean flag= false;
				Vector randomGroupNameVect = new Vector();
				for( i=0; i <courselist.size(); i++)
				{
					gName =((CourseUserDetail)courselist.get(i)).getGroupName();
					uName=((CourseUserDetail)courselist.get(i)).getLoginName();
					if(gName.equals(gname) && uName.equals(uname))
					{
                        			for(j=0; j<10; j++)
			                	{
							/**
							 * Getting Random Group name if Given Group Name with same User Name is already  exist in xml
							 * See OnlineReg/courses.xml file
							 */	
                	        	        	vc.addElement(gname+getNext()+"\n");
		        	                }
						flag = true;
					}
					if(flag == true)
					break;
				}
				if(vc != null && vc.size() > 0)
				{
					data.addMessage(MultilingualUtil.ConvertedString("c_msg1",LangFile));
		       			data.addMessage(MultilingualUtil.ConvertedString("online_msg9",LangFile) +vc.toString());
			                setTemplate(data,"OnlineRegistration.vm");
                			return;
					
				}
				else //inner
				{
					MsgForExpireTime = "forCourse";
					server_name= TurbineServlet.getServerName();
        	                	srvrPort= TurbineServlet.getServerPort();
					indexList = sendMail_MoreThanSevenDays(courselist, MsgForExpireTime, gName, server_name, srvrPort, LangFile, instAdminName, InstituteId);
					xmlWriter=TopicMetaDataXmlWriter.WriteXml_OnlineCourse(path,"/courses.xml",indexList);
	        	        	//TopicMetaDataXmlWriter.appendOnlineCrsElement(xmlWriter,gname,cname,uname,orgtn,email,fname,lname,curDate, instname);
	        	        	TopicMetaDataXmlWriter.appendOnlineCrsElement(xmlWriter,gname,cname,uname,orgtn,email,fname,lname,curDate,InstituteId);
	        		        xmlWriter.writeXmlFile();
					sendMailToApproval("fromCourse",LangFile,uname,fname,lname, cname,instituteid);
				} //else inner
        		}
			else
			{
				indexList.add(-1);
                		xmlWriter=TopicMetaDataXmlWriter.WriteXml_OnlineCourse(path,"/courses.xml",indexList);
		                //TopicMetaDataXmlWriter.appendOnlineCrsElement(xmlWriter,gname,cname,uname,orgtn,email,fname,lname,curDate,instname);
		                TopicMetaDataXmlWriter.appendOnlineCrsElement(xmlWriter,gname,cname,uname,orgtn,email,fname,lname,curDate,InstituteId);
        		        xmlWriter.writeXmlFile();
				sendMailToApproval("fromCourse",LangFile,uname,fname,lname,cname,instituteid);
			}
		}
                context.put("lang",lang);
                String str=MultilingualUtil.ConvertedString("online_msg6",LangFile);
                data.setMessage(str);
        }
	/**
	* Method for get and send email.
	*if request for role student then mail send to Instructor.
	*else mail send to Institute admin.  
	*/		
	void sendMailToApproval(String gname, String LangFile, String unme, String fname, String lname, String courseName ,int instituteId)
	{
		int j=0;
		String temp="";
		String info_msg="", info_Opt="", msgRegard="", msgBrihAdmin="" ;
		server_name= TurbineServlet.getServerName();
                srvrPort= TurbineServlet.getServerPort();
		try{
			fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
       		        pr =MailNotification.uploadingPropertiesFile(fileName);
			//MsgForExpireTime = " A user ";
			//String subMsgForExpireTime =", has requested for registration as student in your course"+" "+gname+" "+" on brihaspati. Kindly do the needful to approve or reject the request";
			if(!gname.equals("fromCourse") && !gname.equals("author")) {
				if(srvrPort.equals("8080")){
					info_new="approvalOfonLineRegReqForStudent";
					info_Opt = "newUser";
				}
				else {
					info_new="approvalOfonLineRegReqForStudent_https";
					info_Opt = "newUserhttps";
				}
				subject = MailNotification.subjectFormate(info_new, "", pr );
				msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
	                        msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
				message = MailNotification.getMessage(info_new, gname, "", unme, "", pr); 
				//message=MailNotification.getMessage_new(message,fname,lname,"","");//added by Shikha
				if(fname.equals("") && lname.equals("")){
		                        fname=unme;
                		}
				int counter=0;
				int gid=GroupUtil.getGID(gname);
				Vector uid=UserGroupRoleUtil.getUID(gid,2);
				//String []gnamesplit=gname.split("_");
				//String Agname=gnamesplit[0];
				String Agname = org.apache.commons.lang.StringUtils.substringBeforeLast(gname, "_");
				//ErrorDumpUtil.ErrorLog("\n\n\n actions OnlineRegist at 503 msgRegard="+msgRegard+"\n fname="+fname+"\n lname="+lname);
				for(counter =0; counter<uid.size(); counter++)
				{
					String s=uid.elementAt(counter).toString();
					List st=UserManagement.getUserDetail(s);
               	        		for(j=0;j<st.size();j++)
	                       		{
						TurbineUser element1=(TurbineUser)st.get(j);
						String userName = element1.getUserName();
						//boolean check_Primary=CourseManagement.IsPrimaryInstructor(gname,userName);
						boolean check_Primary=CourseManagement.IsPrimaryInstructor(Agname,userName);
						boolean check_Active=CourseManagement.CheckcourseIsActive(gid);
						if(check_Primary==true && check_Active==false)
						{
							emailId = element1.getEmail();
							//Mail_msg=MailNotification.sendMail(message, emailId, subject, "", LangFile);
							 String Mail_msg = MailNotificationThread.getController().set_Message(message, "", msgRegard, fname+" "+lname, emailId, subject, "", LangFile, "");
						}		
					}
				}// for
			} //if
			else
			{
				if(srvrPort.equals("8080")){
					info_new="approvalOfonLineRegReqForCourse";
					info_Opt = "newUser";
				}
				else{
					info_new="approvalOfonLineRegReqForCourse_https";
					info_Opt = "newUserhttps";
				}
					
				subject = MailNotification.subjectFormate(info_new, "", pr );
				message = MailNotification.getMessage(info_new, courseName, "", unme,"" , pr); 
				msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
	                        msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
				msgBrihAdmin = pr.getProperty("brihaspati.MailNotification."+info_Opt+".msgBrihAdmin");
				/**
				 * Get email Id if role is author
				 * {System admin}else
				 * {Institute admin}
				 * send the mail using util
				 *@see MailNotification util in utils  
				 */
				if(gname.equals("author")){
					String s=Integer.toString(UserUtil.getUID("admin"));
					List st=UserManagement.getUserDetail(s);
					for(j=0;j<st.size();j++){
						TurbineUser element1=(TurbineUser)st.get(j);
						emailId = element1.getEmail();
						}
				}
				else{
				Criteria crit=new Criteria();	
				crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteId);
				List iadetlist=InstituteAdminUserPeer.doSelect(crit);
				emailId=((InstituteAdminUser)iadetlist.get(0)).getAdminUname();
				}
				//Mail_msg=MailNotification.sendMail(message, emailId, subject, "", LangFile);
				String Mail_msg = MailNotificationThread.getController().set_Message(message, "", msgRegard, msgBrihAdmin, emailId, subject, "", LangFile, Integer.toString(instituteId));
			}
		} //try close
		catch(Exception e){ErrorDumpUtil.ErrorLog("Erro in approvalOfonLineRegReqForStudent"+e);}
	}// sendMailToApproval

	/* Generate a Password object with a random password. */

	public String getNext() {
		/** Minimum length for a decent password */
		final int MIN_LENGTH = 2;
  		/** The random number generator. */
		java.util.Random r = new java.util.Random();

		  /*
		   * Set of characters that is valid. Must be printable, memorable, and "won't
		   * break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands
		   * (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out,
		   * as are numeric zero and one.
		   */
	      char[] goodChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 
			'p','q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '-', };

    		StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < MIN_LENGTH; i++) {
		      sb.append(goodChar[r.nextInt(goodChar.length)]);
		    }
		    return sb.toString();
		
	}
	public Vector sendMail_MoreThanSevenDays(Vector listCrs_OR_Usr, String MsgForExpireTime, String name, String serverName, String serverPort, String LangFile, String instAdminName, String instId)
	{
		Vector indexList = new Vector();
		Date Creation_date;
		String msgRoleInfo="", msgRegard="", info_Opt="";
		//String subMsgForExpireTime =" registration is expired. Please talk to the administrator personally";
		try{
			fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
        	        pr =MailNotification.uploadingPropertiesFile(fileName);

			if(MsgForExpireTime.equals("forUser") ){
				if(serverPort.equals("8080")){ 
					info_new = "onLineRegRequestForUserExpire";
					info_Opt = "newUser";
				}
				else {
					info_new = "onLineRegRequestForUserExpirehttps";
					info_Opt = "newUserhttps";
				}
                		message = MailNotification.getMessage(info_new, "", "", name, "", pr);
                                //msgRoleInfo = pr.getProperty("brihaspati.MailNotification."+info_Opt+".msgBrihAdmin");
			}
			if(MsgForExpireTime.equals("forCourse") ){
				if(serverPort.equals("8080")) { 
					info_new = "onLineRegRequestForCourseExpire";
					info_Opt = "newUser";
				}
				else {
					info_new = "onLineRegRequestForCourseExpirehttps";
					info_Opt = "newUserhttps";
				}
	               		message = MailNotification.getMessage(info_new, name, "", "", "", pr);
			}
                		subject = MailNotification.subjectFormate(info_new, "", pr );
				msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                                msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
                                msgRoleInfo = pr.getProperty("brihaspati.MailNotification."+info_Opt+".msgInstAdmin");
				if(msgRoleInfo.length() >0)
					msgRoleInfo = msgRoleInfo.replaceAll("institute_admin",instAdminName);

			for( int i=0; i <listCrs_OR_Usr.size(); i++)
			{
				String cdate=((CourseUserDetail)listCrs_OR_Usr.get(i)).getCreateDate();
				String creation_date = cdate.substring(0,4)+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);
				java.util.Date currentDate= new java.util.Date();
		        	Creation_date=Date.valueOf(creation_date);
			        longCreationDate= Creation_date.getTime();
	        		longCurrentDate= currentDate.getTime();
			        noOfdays=(longCurrentDate-longCreationDate)/(24*3600*1000)+1;
	
        			if(noOfdays > 7 && (longCurrentDate-longCreationDate)!=0)
			        {
        				emailId = ((CourseUserDetail)listCrs_OR_Usr.get(i)).getEmail();
	                		//Mail_msg=MailNotification.sendMail(message, emailId, subject, "", LangFile);
					
					Mail_msg = MailNotificationThread.getController().set_Message(message, "", msgRegard, msgRoleInfo, emailId, subject, "", LangFile, instId);
		                	indexList.add(i);
				}
			}
		} //try close
		catch(Exception e){ErrorDumpUtil.ErrorLog("Error in Exipiration of onLineRegReq"+e);}
		return indexList;              
	}
        public void doSearch(RunData data, Context context) throws Exception
        {
		/**
 		*Get only those courses in which instructor allowed the student for online Registration
		*@see CourseManagement util in utils
 		*/ 
		String LangFile=(String)data.getUser().getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		String instName=pp.getString("instName");
		String status=pp.getString("status");
		Criteria crit=new Criteria();
		crit.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,instName);
		List lst=InstituteAdminRegistrationPeer.doSelect(crit);
		int instituteId=((InstituteAdminRegistration)lst.get(0)).getInstituteId();
		//Vector courseList=InstituteDetailsManagement.getInstituteCourseDetails(Integer.toString(instituteId));	
		Vector courseList=CourseManagement.getCrsOnlinDetails(Integer.toString(instituteId));	
		context.put("courseList",courseList);
	}
	/**
	 * This is the default method called when the button is not found
	 * @param data RunData
	 * @param context Context
	 */

	public void doPerform(RunData data,Context context) throws Exception
	{
		String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_UserRegister"))
			UserRegister(data,context);
		else if(action.equals("eventSubmit_CourseRegister"))
                        CourseRegister(data,context);
		else if(action.equals("eventSubmit_doSearch"))
                        doSearch(data,context);

		else
		{
			data.setMessage("Action not found");
		}
	}
}

