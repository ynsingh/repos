package org.iitk.brihaspati.modules.screens.call.TrackingReport;

/*
 * @(#)Track_Report.java
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
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**
 *This class contains code for Creating a group
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */
//Java
import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.sql.Date;
import java.text.SimpleDateFormat;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.Turbine;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
//Brihaspati
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.MailReceivePeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.StudentInstructorMAP;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;

public class Track_Report extends SecureScreen
{
	/**
     	* Place all the data object in the context
     	* for use in the template.
     	*/
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();	
                        ParameterParser pp=data.getParameters();
			List v=null;
                	List u=null;

			/**
                        * Get courseid  and coursename for the user currently logged in
                        *Put it in the context for Using in templates
                        * @see UserUtil in Util.
                        */
                	User user=data.getUser();
			String username=data.getUser().getName();
                        context.put("username",username);
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String type =pp.getString("type","");
			context.put("type",type);
			String status =pp.getString("status");
			context.put("status",status);
			String usrname=pp.getString("usrname","");
			context.put("usrname",usrname);
			String userrole=data.getUser().getTemp("role").toString();
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			int readflg=0;
                        int flg=1,uid=0;
			Vector userList=new Vector();
			Vector userList1=new Vector();
                        Criteria crit=new Criteria();
			String jdate="",Ldate="",comPath="";
			Vector date1=new Vector();
                        java.util.Date date=new java.util.Date();
			if(!usrname.equals(""))
			{
				uid=UserUtil.getUID(usrname);
				/*joining date of user or  Last login--------------*/
                        	crit.add(TurbineUserPeer.USER_ID,uid);
                        	v=TurbineUserPeer.doSelect(crit);
                        	for(int i=0;i<v.size();i++)
                        	{
					TurbineUser element=(TurbineUser)v.get(i);
                                	jdate=element.getCreateDate().toString();
                                	Ldate=element.getLastLogin().toString();
					int day1=0,month1=0,year1=0;
					year1=Integer.parseInt(jdate.substring(0,4));
                                	month1=Integer.parseInt(jdate.substring(5,7));
                                	day1=Integer.parseInt(jdate.substring(8,10));
					jdate=year1+"/"+month1+"/"+day1;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                                	java.util.Date utilDate1 = null;
                                	utilDate1 = formatter.parse(jdate);
                        		context.put("jdate",utilDate1);
                                	java.util.Date utilDate2 = null;
					year1=Integer.parseInt(Ldate.substring(0,4));
                                	month1=Integer.parseInt(Ldate.substring(5,7));
                                	day1=Integer.parseInt(Ldate.substring(8,10));
					Ldate=year1+"/"+month1+"/"+day1;
                                	utilDate2 = formatter.parse(Ldate);
                        		context.put("ldate",utilDate2);
				}
				List v1=null;
				crit=new Criteria();
                       	 	crit.add(org.iitk.brihaspati.om.TurbineUserPeer.USER_ID,uid);
                        	v1=org.iitk.brihaspati.om.TurbineUserPeer.doSelect(crit);
				org.iitk.brihaspati.om.TurbineUser element1=(org.iitk.brihaspati.om.TurbineUser)v1.get(0);
				String UserLang=element1.getUserLang().toString();
				context.put("userLanguage",UserLang);
			}
			uid=UserUtil.getUID(usrname);
			/** getting Rolename---------------*/
			if(userrole.equals("instructor"))
			{
				context.put("coursename",(String)user.getTemp("course_name"));
				String courseid=(String)user.getTemp("course_id");
				context.put("courseid",courseid);
				int g_id=GroupUtil.getGID(courseid);
				int role=UserManagement.getRoleinCourse(uid,g_id);
				String Rolename=UserGroupRoleUtil.getRoleName(role);
				context.put("Rolename",Rolename);
				comPath=data.getServletContext().getRealPath("/Courses")+"/"+courseid;
				/*----------------------------*/
                        	/* -------------courses----------------------*/
                        	String filePath=comPath+"/Content";
                        	File Path=new File(filePath+"/coursecontent__des.xml");
                        	if(Path.exists())
                        	{
                                	TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"coursecontent__des.xml");
                                	Vector dc=topicMetaData.getFileDetails();
					if(dc.size()!=0)
					{
                                		context.put("dc",dc.size());
                                		context.put("value","noempty");
					}
                        	}
                        	/* -----------------------------------*/
                        	/* ---------Groupdetail--------------------------*/
				String gname="",sname="";
                        	TopicMetaDataXmlReader topicmetadata=null;
                        	String groupPath=comPath+"/GroupManagement";
                        	File f=new File(groupPath+"/GroupList__des.xml");
                        	if(f.exists())
                        	{
                                	topicmetadata=new TopicMetaDataXmlReader(groupPath+"/GroupList__des.xml");
                                	Vector grplist=topicmetadata.getGroupDetails();
                                	if(grplist!=null)
                                	{
                                        	for(int k=0;k<grplist.size();k++)
                                        	{
                                                	gname =((FileEntry) grplist.elementAt(k)).getName();
                                                	String sname2 =((FileEntry) grplist.elementAt(k)).getUserName();
							if(sname2.equals(usrname))
							{
								context.put("sname",sname2);
                                                		context.put("name2","yes");
							}
                                                	topicmetadata=new TopicMetaDataXmlReader(groupPath+"/"+gname+"__des.xml");
                                                	Vector selectlist=topicmetadata.getGroupDetails();
                                                	if(selectlist!=null)
                                                        	for(int j=0;j<selectlist.size();j++)
                                                        	{
                                                                	/** Check if the student profile already exists in group.*/
                                                                	sname =((FileEntry) selectlist.elementAt(j)).getUserName();
                                                                	if(sname.equals(usrname))
									{
                                                                		context.put("gname",gname);
                                                                		context.put("name","yes");
									}
                                                        	}
                                        	}
                                	}
                        	}
	                	/* -----------------------------------*/
	                	/* ------------DBoard-----------------------*/
				crit=new Criteria();
                        	crit.add(DbReceivePeer.RECEIVER_ID,uid);
                        	crit.add(DbReceivePeer.GROUP_ID,g_id);
                        	v=DbReceivePeer.doSelect(crit);
                        	context.put("DB",v.size());

                        	crit.add(DbReceivePeer.RECEIVER_ID,uid);
                        	crit.add(DbReceivePeer.GROUP_ID,g_id);
                        	if(readflg==0)
                        	{
                                	crit.add(DbReceivePeer.READ_FLAG,readflg);
                                	v=DbReceivePeer.doSelect(crit);
                                	context.put("unreadDB",v.size());
                        	}
                        	if(flg==1)
                        	{
                                	crit.add(DbReceivePeer.READ_FLAG,flg);
                                	u=DbReceivePeer.doSelect(crit);
                                	context.put("readDB",u.size());
                        	}
                        	/* -----------------------------------*/
                        	/* ---------Notices- --------------------------*/
                        	crit=new Criteria();
                        	crit.add(NoticeReceivePeer.RECEIVER_ID,uid);
                        	crit.add(NoticeReceivePeer.GROUP_ID,g_id);
                        	v=NoticeReceivePeer.doSelect(crit);
                        	context.put("notices",v.size());
                        	/* ---------Notices- --------------------------*/
				/**
                       		*Selecting the particular course student detail
                       		*put in the context for the use in templates.
                       		*/
                       		crit =new Criteria();
                       		crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                       		crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                       		crit.and(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                       		crit.setDistinct();
                       		v=TurbineUserPeer.doSelect(crit);
                       		for(int i=0;i<v.size();i++)
                       		{
                               		TurbineUser element=(TurbineUser)v.get(i);
                               		String studentname=element.getUserName();
                               		userList.addElement(studentname);
                       		}
                       		if(userList.size()==0)
                       		{
                               		data.setMessage(MultilingualUtil.ConvertedString("stu_msgc",LangFile));
                               		context.put("nolist","nolist");
                       		}
                       		else
                       		{	
                        		context.put("userList",userList);
                               		context.put("nolist","yeslist");
                        	}
                        	/* -----------------------------------*/
				int Active=0;
                        	crit =new Criteria();
                        	crit.add(CoursesPeer.GROUP_NAME,courseid);
                        	v=CoursesPeer.doSelect(crit);
				for(int b=0;b<v.size();b++)
				{
                               		Courses element=(Courses)v.get(b);
                               		Active=element.getActive();
				}
				if(Active == 1)
                                context.put("Active","Yes");
				if(Active == 0)
                                context.put("Active","No");
			}//ifclose
			else  //-----admin------------------part//
			{
				/** getting Rolename---------------*/
                        	int rid1=0;
				int[] uId2 ={0,1,6};
				Vector roles=new Vector();
				Vector crs_name=new Vector();
                        	crit =new Criteria();
                        	crit.addJoin(TurbineUserGroupRolePeer.USER_ID,TurbineUserPeer.USER_ID);
				crit.andNotIn(TurbineUserGroupRolePeer.GROUP_ID,uId2);
                        	crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        	v=TurbineUserGroupRolePeer.doSelect(crit);
                        	for(int n=0;n<v.size();n++)
                        	{
                                	TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(n);
                                	int uid2=element.getUserId();
                                	int rid=element.getRoleId();
					String Rolename=UserGroupRoleUtil.getRoleName(rid);
                                	if((uid2==uid) && (!roles.contains(Rolename)))
                                	{
						roles.add(Rolename);
                                	}
					if(rid==2)
					{
						crs_name=StudentInstructorMAP.getIMAP(uid);
					}
					if(rid==3)
					{
						crs_name=StudentInstructorMAP.getSMAP(uid);
					}
                        	}
				String delimiter=" , ";
				String role_name=vectorToString(roles,delimiter);
                        	context.put("Rolename",role_name);
				
				Vector crsnamelist=new Vector();
                        	for(int i=0;i<crs_name.size();i++)
                        	{
					String courseName=((CourseUserDetail) crs_name.elementAt(i)).getCourseName();
					crsnamelist.add(courseName);
				}
				String crsname=vectorToString(crsnamelist,delimiter);
                               	context.put("dc",crsname);
				
                        	//---------------------------------------------role------//
				/* ------------DBoard-----------------------*/
                        	crit=new Criteria();
                        	crit.add(DbReceivePeer.RECEIVER_ID,uid);
                        	v=DbReceivePeer.doSelect(crit);
                        	context.put("DB",v.size());

                        	crit.add(DbReceivePeer.RECEIVER_ID,uid);
                        	if(readflg==0)
                        	{
                                	crit.add(DbReceivePeer.READ_FLAG,readflg);
                                	v=DbReceivePeer.doSelect(crit);
                                	context.put("unreadDB",v.size());
                        	}
                        	if(flg==1)
                        	{
                                	crit.add(DbReceivePeer.READ_FLAG,flg);
                                	u=DbReceivePeer.doSelect(crit);
                                	context.put("readDB",u.size());
                        	}
                        	/* -----------------------------------*/
                        	/* ---------Notices- --------------------------*/
                        	crit=new Criteria();
                        	crit.add(NoticeReceivePeer.RECEIVER_ID,uid);
                        	v=NoticeReceivePeer.doSelect(crit);
                        	context.put("notices",v.size());
                        	/* -----------------------------------*/
				/*--------------Last week LastMonth login---------------------*/
				// Retrieves the current date of the System see ExpiryUtil in utils
				Vector usrid=new Vector();
				Vector usrid1=new Vector();
                                String cdate=ExpiryUtil.getCurrentDate("");
                                /**
                                * Get the current day from the currentdate
                                */
                                String day=cdate.substring(6,8);
                                String month=cdate.substring(4,6);
				int mon=Integer.parseInt(month);
                                String year=cdate.substring(0,4);
				String cdate1=year+"-"+month+"-"+day;
				Date tdate1=Date.valueOf(cdate1);
                                long tdate2= tdate1.getTime();
				int intdiff=30;
				int noUid[]={0,1};
                                int uid2=0,uid4=0,uid3=0,tmp=0,temp=0;
                                crit=new Criteria();
                                crit.add(UsageDetailsPeer.USER_ID,uid);
                                crit.addNotIn(UsageDetailsPeer.USER_ID,noUid);
                                v=UsageDetailsPeer.doSelect(crit);
                                if(v !=null)
                                {
                                        for(int a=0;a<v.size();a++)
                                        {
                                                UsageDetails element=(UsageDetails)v.get(a);
                                                uid3=element.getUserId();
                                                if(uid == uid3)
                                                {
                                                	java.util.Date logindate=new java.util.Date();
                                                	logindate=element.getLoginTime();
							String logindate1=logindate.toString();
							int newdate=Integer.parseInt(logindate1.substring(5,7));
							if(newdate!=mon)
							{
                                                		usrid.addElement(logindate1);
							}

                                                }
                                        }
                                }
				for(int m=0;m<usrid.size();m++)
				{
					String ldate=(String)usrid.get(m);
					int newdate=Integer.parseInt(ldate.substring(5,7));
					int newdate1=Integer.parseInt(ldate.substring(8,10));
					if(newdate>tmp && newdate1>temp)
					{
						tmp=newdate;
						temp=newdate1;
					}
					
				}
				String lastdate=year+"/"+tmp+"/"+temp;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				java.util.Date utilDate = null;
      				utilDate = formatter.parse(lastdate);
				context.put("lastdate",utilDate);
				/*--------------Last week LastMonth login---------------------*/
                        	/* ---------Groupdetail--------------------------*/
				String courseid1=GroupUtil.getGroupName(uid,rid1);
				comPath=data.getServletContext().getRealPath("/Courses")+"/"+courseid1;
				String gname="",sname="";
                        	TopicMetaDataXmlReader topicmetadata=null;
                        	String groupPath=comPath+"/GroupManagement";
                        	File f=new File(groupPath+"/GroupList__des.xml");
                        	if(f.exists())
                        	{
                                	topicmetadata=new TopicMetaDataXmlReader(groupPath+"/GroupList__des.xml");
                                	Vector grplist=topicmetadata.getGroupDetails();
                                	if(grplist!=null)
                                	{
                                        	for(int k=0;k<grplist.size();k++)
                                        	{
                                                	gname =((FileEntry) grplist.elementAt(k)).getName();
                                                	String sname2 =((FileEntry) grplist.elementAt(k)).getUserName();
							if(sname2.equals(usrname))
							{
								context.put("sname",sname2);
                                                		context.put("name2","yes");
							}
                                                	topicmetadata=new TopicMetaDataXmlReader(groupPath+"/"+gname+"__des.xml");
                                                	Vector selectlist=topicmetadata.getGroupDetails();
                                                	if(selectlist!=null)
                                                        	for(int j=0;j<selectlist.size();j++)
                                                        	{
                                                                	/** Check if the student profile already exists in group.*/
                                                                	sname =((FileEntry) selectlist.elementAt(j)).getUserName();
                                                                	if(sname.equals(usrname))
									{
                                                                		context.put("gname",gname);
                                                                		context.put("name","yes");
									}
                                                        	}
                                        	}
                                	}
                        	}
	                	/* -----------------------------------*/
				/**
                       		*Selecting the UserList 
                       		*put in the context for the use in templates.
                       		*/
				if(userrole.equals("institute_admin"))
					userList1=ListManagement.getInstituteUserList(instituteId);
				else
                       			userList1=ListManagement.getUserList();
				context.put("userList1",userList1);
	                	/* -----------------------------------*/
				int Active=0;
                                crit =new Criteria();
                                crit.add(CoursesPeer.GROUP_NAME,courseid1);
                                v=CoursesPeer.doSelect(crit);
                                for(int b=0;b<v.size();b++)
                                {
                                        Courses element=(Courses)v.get(b);
                                        Active=element.getActive();
                                }
				if(Active == 1)
                                context.put("Active","Yes");
				if(Active == 0)
                                context.put("Active","No");
			}//else
                        /* -----------------------------------*/
                        /* ------------PersonalMessages-----------------------*/
                        crit=new Criteria();
                        crit.add(MailReceivePeer.RECEIVER_ID,uid);
                        crit.addDescendingOrderByColumn(MailReceivePeer.MAIL_ID);
                        v=MailReceivePeer.doSelect(crit);
                        context.put("personalmess",v.size());

                        crit.add(MailReceivePeer.RECEIVER_ID,uid);
                        crit.addDescendingOrderByColumn(MailReceivePeer.MAIL_ID);
                        if(readflg==0)
                        {
                        crit.add(MailReceivePeer.MAIL_READFLAG,readflg);
                        v=MailReceivePeer.doSelect(crit);
                        context.put("unreadmess",v.size());
                        }
                        if(flg==1)
                        {
                        crit.add(MailReceivePeer.MAIL_READFLAG,flg);
                        u=MailReceivePeer.doSelect(crit);
                        context.put("readmess",u.size());
                        }
			//------------------------//
			//---------Progaram and Rollno---------------//
			if(!usrname.equals(""))
			{
				crit=new Criteria();
                        	crit.add(StudentRollnoPeer.EMAIL_ID,usrname);
                        	v=StudentRollnoPeer.doSelect(crit);
				if(v.size()!=0)
				{
					StudentRollno element=(StudentRollno)v.get(0);
                        		String Rollno=element.getRollNo().toString();
                        		String Prgcode=element.getProgram().toString();
					String PrgName=InstituteIdUtil.getPrgName(Prgcode);
					context.put("prgname",PrgName);
					context.put("rollno",Rollno);
				}
				else
					context.put("empty","falgrollnoprg");
				
			}
			//-------------------------------------------//

		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Screen:Track_Report !!"+e);	
                                   data.setMessage("See ExceptionLog !! " );
                                  }
	}//method close
	public String vectorToString(Vector vector, String delimiter)
	{
    		StringBuilder vcTostr = new StringBuilder();
    		if (vector.size() > 0)
		{
        		vcTostr.append(vector.get(0));
        		for (int i=1; i<vector.size(); i++) {
            		vcTostr.append(delimiter);
            		vcTostr.append(vector.get(i));
        		}
    		}
    		return vcTostr.toString();
	}
}//class
