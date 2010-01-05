package org.iitk.brihaspati.modules.screens.call.TrackingReport;

/*
 * @(#)Track_ReportCourses.java
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
import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.Vector;
import java.util.Date;
import java.util.ListIterator;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import com.workingdogs.village.Record;

import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import java.text.SimpleDateFormat;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;





public class Track_ReportCourses extends SecureScreen
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
			String usrname=pp.getString("usrname","");
			context.put("usrname",usrname);
			Criteria crit=new Criteria();
			int noUid[]={0,1};
			int uid1=UserUtil.getUID(username);
			if(!username.equals("admin"))
			{
				String tname=pp.getString("tname","");
				context.put("tname",tname);
				int uid=UserUtil.getUID(usrname);
				String jdate="";
				/*joining date of user or login date Last login--------------*/
                        	java.util.Date date=new java.util.Date();
                        	crit.add(TurbineUserPeer.USER_ID,uid);
                        	v=TurbineUserPeer.doSelect(crit);
                        	for(int i=0;i<v.size();i++)
                        	{
                                	TurbineUser element=(TurbineUser)v.get(i);
                                	jdate=element.getCreateDate().toString();
                                	int day1=0,month1=0,year1=0;
                                	year1=Integer.parseInt(jdate.substring(0,4));
                                	month1=Integer.parseInt(jdate.substring(5,7));
                                	day1=Integer.parseInt(jdate.substring(8,10));
                                	jdate=year1+"/"+month1+"/"+day1;
                                	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                                	java.util.Date utilDate1 = null;
                                	utilDate1 = formatter.parse(jdate);
                                	context.put("jdate",utilDate1);
                        	}
                       		/* -------------courses----------------------*/
				String status =pp.getString("status");
				context.put("status",status);
				context.put("coursename",(String)user.getTemp("course_name"));
				String courseid=(String)user.getTemp("course_id");
				context.put("courseid",courseid);
				/** getting Rolename---------------*/
				int g_id=GroupUtil.getGID(courseid);
				int Rid=UserManagement.getRoleinCourse(uid,g_id);
				String Rolename=UserGroupRoleUtil.getRoleName(Rid);
				context.put("Rolename",Rolename);
				/** getting Rolename---------------*/
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
                                context.put("Active","Active");
                                if(Active == 0)
                                context.put("Active","Inactive");
				/*----------------------------course detail------------------------*/
				String comPath=data.getServletContext().getRealPath("/Courses")+"/"+courseid;
                        	String filePath=comPath+"/Content";
                        	File Path=new File(filePath+"/content__des.xml");
				Vector publish=new Vector();
				Vector unpublish=new Vector();
				Vector totallist=new Vector();
				Vector pubfiles=new Vector();
				int pub=0,unpub=0;
                        	if(Path.exists())
                        	{
                                	TopicMetaDataXmlReader topicMetaData=null;
					topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"content__des.xml");
                                	Vector dc=topicMetaData.getFileDetails();
					if(dc!=null)
					{
                                		context.put("dcourse",dc);
                                		context.put("dc",dc.size());
                                		context.put("value","noempty");
					}
					for(int i=0;i<dc.size();i++)
					{
						String fileName= ((FileEntry)dc.elementAt(i)).getName();
						topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+fileName+"/"+fileName+"__des.xml");
						pubfiles=topicMetaData.getFileDetails();
						if(pubfiles == null)
						pub=0;
						else
						pub=pubfiles.size();
						publish.addElement(pub);
					       	File f=new File(filePath+"/"+fileName+"/Unpublished");
						String [] listfile=f.list();
						List list = Arrays.asList(listfile);
						unpub=list.size();
						unpublish.addElement(unpub);
						int totalfile=pub+unpub;
						totallist.addElement(totalfile);
                        		}
                                	context.put("nofiles",publish);
                        		context.put("unpublist",unpublish);
                        		context.put("totalfile",totallist);
				}
				long ttime=0;
				String cctime="";
                                crit =new Criteria();
				crit.add(UsageDetailsPeer.USER_ID,uid);
                                crit.addNotIn(UsageDetailsPeer.USER_ID,noUid);
                                u=UsageDetailsPeer.doSelect(crit);
				if(u!=null)
                                {
                                        for(int a=0;a<u.size();a++)
                                        {
                                                UsageDetails element=(UsageDetails)u.get(a);
                                                int cuid=element.getUserId();
						java.util.Date logindate=new java.util.Date();
                                        	logindate=(element.getLoginTime());
                                        	long ldate=logindate.getTime();
                                        	java.util.Date logoutdate=new java.util.Date();
                                        	logoutdate=(element.getLogoutTime());
                                        	long loutdate=logoutdate.getTime();
                                                if(cuid==uid)
                                                {
						ErrorDumpUtil.ErrorLog("\nunid3"+cuid);
							long elapsedTime5 =(loutdate - ldate)/1000;
                                                	ttime=ttime+elapsedTime5;
                                                	String seconds5 = Integer.toString((int)(ttime % 60));
                                                	String minutes5 = Integer.toString((int)((ttime % 3600) / 60));
                                                	String hours5 = Integer.toString((int)(ttime / 3600));
                                                	for (int c = 0; c < 2; c++)
                                                	{
                                                        	if (seconds5.length() < 2)
                                                        	{
                                                                	seconds5 = "0" + seconds5;
                                                        	}
                                                        	if (minutes5.length() < 2)
                                                        	{
                                                                	minutes5 = "0" + minutes5;
                                                        	}
                                                        	if (hours5.length() < 2)
                                                        	{
                                                                	hours5 = "0" + hours5;
                                                        	}
                                                	}
                                                	cctime =  hours5 + ":" + minutes5 + ":" + seconds5;
                                        	}
                                	}
				}
                                context.put("cctime",cctime);
			}
                       	//--------------------------most active user and course-----------------//
			else
			{
				//int uid1=UserUtil.getUID(username);
                       		Vector usrid=new Vector();
                       		Vector usrid1=new Vector();
                       		Vector usrid3=new Vector();
				long Totaltime=0,Totaltime1=0;
				String uname="",time="";
                       		int uid5=0,uid2=0,uid4=0,uid3=0,tmp=0,no=0;
                        	crit=new Criteria();
                        	crit.add(UsageDetailsPeer.USER_ID,uid1);
                        	crit.addNotIn(UsageDetailsPeer.USER_ID,noUid);
                        	u=UsageDetailsPeer.doSelect(crit);
                        	if(u!=null)
                        	{
                        		for(int i=0;i<u.size();i++)
                                	{
                                		UsageDetails element=(UsageDetails)u.get(i);
                                        	uid3=element.getUserId();
                                       		if(!usrid.contains(uid3))
                                        	{
                                            		usrid.addElement(uid3);
                                        	}
					}
                               	}	
                        	for(int k=0;k<usrid.size(); k++)
                       		{
                       			uid4 =(Integer)usrid.get(k);
                                	for(int j=0;j<u.size(); j++)
					{
						UsageDetails element=(UsageDetails)u.get(j);
                                                uid2=element.getUserId();
                                        	if(uid4==uid2)
                                        	{
                                       			tmp=tmp+1;
                                        	}
                                	}
                                	usrid3.addElement(tmp);
                                	tmp=0;
                       		}
				for(int i=0;i<usrid3.size();i++)
				{
                       			uid3=(Integer)usrid3.get(i);
					if(uid3>tmp)
					tmp=uid3;
				}
                        	context.put("tmp",tmp);
                        	for(int k=0;k<usrid.size(); k++)
                       		{
                       			uid4 =(Integer)usrid.get(k);
                               		for(int j=0;j<u.size(); j++)
					{
						UsageDetails element=(UsageDetails)u.get(j);
                                        	uid2=element.getUserId();
                                        	if(uid4==uid2)
                                			usrid1.addElement(uid2);
							if(usrid1.size()==tmp)
							{
							uid5=uid4;
							uname=UserUtil.getLoginName(uid5);
							}
					}	
				}	
                        	context.put("uname",uname);
				for(int b=0;b<u.size();b++)
                                {
                                	UsageDetails element=(UsageDetails)u.get(b);
                                        uid3=element.getUserId();
                                        java.util.Date logindate=new java.util.Date();
                                        logindate=(element.getLoginTime());
                                        long ldate=logindate.getTime();
                                        java.util.Date logoutdate=new java.util.Date();
                                        logoutdate=(element.getLogoutTime());
                                        long loutdate=logoutdate.getTime();
                                        if(uid5==uid3)
                                        {
                                        	long elapsedTime =(loutdate - ldate)/1000;
                                                Totaltime=Totaltime+elapsedTime;
						String seconds = Integer.toString((int)(Totaltime % 60));
                                        	String minutes = Integer.toString((int)((Totaltime % 3600) / 60));
                                        	String hours = Integer.toString((int)(Totaltime / 3600));
                                        	for (int i = 0; i < 2; i++)
                                        	{
                                                	if (seconds.length() < 2)
                                                	{
                                                        	seconds = "0" + seconds;
                                                	}
                                                	if (minutes.length() < 2)
                                                	{
                                                        	minutes = "0" + minutes;
                                                	}
                                                	if (hours.length() < 2)
                                                	{
                                                        	hours = "0" + hours;
                                                	}
                                        	}
                                        	time =  hours + ":" + minutes + ":" + seconds;
                                        }
                                }
                        	context.put("time",time);
				//------------------------------------------active user-------------//
				//------------------------------------------active course-------------//
				Vector cname=new Vector();
                                Vector totalname=new Vector();
                                Vector totaluser=new Vector();
                                Vector course=new Vector();
                                String gname1="",gname="",ctime="";
                                int gid2=0,gid3=0;
                                crit =new Criteria();
                                crit.add(TurbineUserGroupRolePeer.USER_ID,uid1);
                                crit.addNotIn(TurbineUserGroupRolePeer.USER_ID,noUid);
                                v=TurbineUserGroupRolePeer.doSelect(crit);
                                for(int a=0;a<usrid.size();a++)
                                {
                                        uid4 =(Integer)usrid.get(a);
                                        for(int i=0;i<v.size();i++)
                                        {
                                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                                uid2=element.getUserId();
                                                gid2=element.getGroupId();
                                                if((uid4 == uid2) && (gid2 !=1) &&(gid2 !=2))
                                                {
                                                        if(!cname.contains(gid2))
                                                        {
                                                                cname.addElement(gid2);
                                                        }
                                                }
                                        }
                                }
				for(int m=0;m<cname.size();m++)
                                {
                                        gid3 =(Integer)cname.get(m);
                                        for(int j=0;j<usrid.size();j++)
                                        {
                                                uid3=(Integer)usrid.get(j);
                                                for(int n=0;n<v.size();n++)
                                                {
                                                        TurbineUserGroupRole element1=(TurbineUserGroupRole)v.get(n);
                                                        uid2=element1.getUserId();
                                                        gid2=element1.getGroupId();
                                                        if(uid3 == uid2 && gid3 == gid2 && gid2!=uid2 )
                                                        {
                                                                no=no+1;
                                                                totaluser.addElement(uid3);
                                                        }
                                                }
                                        }
                                        totalname.addElement(no);
                                        no=0;
                                }
				for(int i=0;i<totalname.size();i++)
                                {
                                        int no1=(Integer)totalname.get(i);
                                        if(no1>no)
                                        no=no1;
                                }
                                context.put("tmp1",no);
				for(int k=0;k<cname.size(); k++)
                                {
                                        gid3 =(Integer)cname.get(k);
                                        for(int j=0;j<v.size(); j++)
                                        {
						TurbineUserGroupRole element1=(TurbineUserGroupRole)v.get(j);
                                                uid2=element1.getUserId();
                                                gid2=element1.getGroupId();
                                                if(gid3==gid2)
                                                        course.addElement(gid3);
                                                        if(course.size()==no)
                                                        {
                                                        uid5=gid3;
							gname=GroupUtil.getGroupName(gid3);
                                                        gname1=CourseUtil.getCourseName(gname);

                                                        }
                                        }
                                }
                                context.put("gname1",gname1);
                                String gname2=CourseUtil.getCourseId(gname1);
                                int gid4=GroupUtil.getGID(gname2);
                                for(int n=0;n<v.size();n++)
                               	{
                                	TurbineUserGroupRole element1=(TurbineUserGroupRole)v.get(n);
                                        uid2=element1.getUserId();
                                        gid2=element1.getGroupId();
                                        for(int b=0;b<u.size();b++)
                                        {
                                               	UsageDetails element=(UsageDetails)u.get(b);
                                                uid3=element.getUserId();
                                                java.util.Date logindate=new java.util.Date();
                                                logindate=(element.getLoginTime());
                                                long ldate=logindate.getTime();
                                                java.util.Date logoutdate=new java.util.Date();
                                                logoutdate=(element.getLogoutTime());
                                                long loutdate=logoutdate.getTime();
                                        	if(uid3 == uid2 && gid4 == gid2 && gid2!=uid2 )
                                                {	
                                                	long elapsedTime =(loutdate - ldate)/1000;
                                                        Totaltime1=Totaltime1+elapsedTime;
							String seconds1 = Integer.toString((int)(Totaltime1 % 60));
                                        		String minutes1 = Integer.toString((int)((Totaltime1 % 3600) / 60));
                                        		String hours1 = Integer.toString((int)(Totaltime1 / 3600));
                                        		for (int i = 0; i < 2; i++)
                                        		{
                                                		if (seconds1.length() < 2)
                                                		{
                                                        		seconds1 = "0" + seconds1;
                                                		}
                                                		if (minutes1.length() < 2)
                                                		{
                                                        		minutes1 = "0" + minutes1;
                                                		}
                                                		if (hours1.length() < 2)
                                                		{
                                                        		hours1 = "0" + hours1;
                                                		}
                                        		}
                                        		ctime =  hours1 + ":" + minutes1 + ":" + seconds1;
                                                }
                                        }
                                }
                                context.put("ctime",ctime);
				//------------------------------------------active course-------------//
			}
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Screen:Track_Reportcourse !!"+e);	
                                   data.setMessage("See ExceptionLog !! " );
                                  }
	}//method close
}//class
