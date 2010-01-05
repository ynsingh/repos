package org.iitk.brihaspati.modules.screens.call.TrackingReport;

/*
 * @(#)Track_Reportquiz.java
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
import java.util.Vector;
import java.util.ListIterator;
import java.sql.Date;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import com.workingdogs.village.Record;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizDetail;
import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.AdminProperties;

public class Track_Reportquiz extends SecureScreen
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
			String quizname=pp.getString("quizname","");
			context.put("quizname",quizname);
			String mode1 =pp.getString("mode1","");
			context.put("mode1",mode1);
			String quizmess =pp.getString("file","");
			String grouptype =pp.getString("grouptype","");
			context.put("grouptype",grouptype);
                        int uid1=UserUtil.getUID(username);
                        Criteria crit=new Criteria();
			/** getting Rolename---------------*/
			if(!username.equals("admin"))
			{
				context.put("coursename",(String)user.getTemp("course_name"));
				String courseid=(String)user.getTemp("course_id");
				context.put("courseid",courseid);
				int uid=UserUtil.getUID(usrname);
				String comPath=data.getServletContext().getRealPath("/Courses")+"/"+courseid;
                        	String createquiz=comPath+"/Quiz";
				TopicMetaDataXmlReader topicmetadata=null;
				/* ---------QuizDetails--------------------------*/
                        	Vector quiz=new Vector();
                        	crit.add(QuizPeer.CID,courseid);
                        	crit.add(QuizPeer.USER_ID,uid1);
                        	u = QuizPeer.doSelect(crit);
				if(u!=null)
				{
                        		for(int i=0;i<u.size();i++)
                        		{
                        			Quiz element=(Quiz)(u.get(i));
                                		String quizid=(element.getQuizId());
                                		QuizDetail qDetail= new QuizDetail();
                                		qDetail.setquizid(quizid);
                                		File f1=new File(createquiz+"/"+quizid+"/Quizid.xml");
                                		if(!f1.exists())
                                        		quizid="0";
                                		else
                                        		quizid="1";
                                		qDetail.setsqid(quizid);
                                		quiz.add(qDetail);

                        		}
					if(quiz.size()!=0)
					{
                        		context.put("Quizid",quiz);
                        		context.put("value","noempty");
					}
				}
                        	if(mode1.equals("quizinfo"))
                        	{
				//----------------------------totaltime for quiz-------------//
                        		for(int i=0;i<u.size();i++)
					{
                        			Quiz element=(Quiz)(u.get(i));
                                		String quizid1=(element.getQuizId());
                                		String stime=(element.getStartTime().toString());
                                		String etime=(element.getEndTime().toString());
						if(quizname.equals(quizid1))
						{
							DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
							java.util.Date stdate = sdf.parse(stime);
							java.util.Date etdate = sdf.parse(etime);
							long sdate=stdate.getTime();
							long edate=etdate.getTime();
							long elapsedTime =(edate - sdate)/1000;
                               				String seconds = Integer.toString((int)(elapsedTime % 60));
                               				String minutes = Integer.toString((int)((elapsedTime % 3600) / 60));
                               				String hours = Integer.toString((int)(elapsedTime / 3600));
                               				for (int c = 0; c < 2; c++)
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
                                			String qtime =  hours + ":" + minutes + ":" +seconds; 
							context.put("quiztime",qtime);
						}
					}
                                	Vector detail=new Vector();
                                	Vector detail1=new Vector();
					String ttlques="",instques="";
                                	String quizpath=createquiz+"/"+quizname;
                                	File f2=new File(quizpath+"/Student_Quiz"+"/"+uid+".xml");
                                	File f1=new File(createquiz+"/"+quizname+"/Quizid.xml");
                                	if(f1.exists()||f2.exists())
					{//if1
                                		topicmetadata=new TopicMetaDataXmlReader(quizpath+"/Quizid.xml");
                                		detail=topicmetadata.getQuizDetails();
						if(detail!=null)
						{//if2
                                			for(int l=0;l<detail.size();l++)
                                			{//for1
                                        			String option_a=((FileEntry) detail.elementAt(l)).getoptionA();
                                        			String option_c=((FileEntry) detail.elementAt(l)).getoptionC();
                                        			String totalquestion=((FileEntry) detail.elementAt(l)).getinstructorans();
                                        			String Typeofquestion=((FileEntry) detail.elementAt(l)).getTypeofquestion();
                                        			String Question=((FileEntry) detail.elementAt(l)).getquestion();
                                        			String tmarks=((FileEntry) detail.elementAt(l)).getmarksperqustion();
                                        			if(option_a.equals(quizname))
                                        			{//if3
									ttlques=totalquestion;
									instques=Question;
                                                			context.put("totalmarks",option_c);
                                                			context.put("passmarks",Question);
                                                			context.put("marksperques",tmarks);
                                                			context.put("Typeofquestion",Typeofquestion);
                                                			context.put("totalquestion",totalquestion);
                                        			}//if3
							}
						}
					}
					else
					context.put("no","noexist");	
					if(f2.exists())
					{
                                        	topicmetadata=new TopicMetaDataXmlReader(quizpath+"/Student_Quiz"+"/"+uid+".xml");
                                        	Vector detail2=topicmetadata.getAssignmentDetails1();
                                        	context.put("quesattempt",detail2.size());
                                        	if(detail2.size()== Integer.parseInt(ttlques))
                                               	context.put("comp","complete");
                                        	else
                                               	context.put("comp","incomplete");
					}
					else
                                                context.put("Qmess","nofile");
					if(f1.exists() && f2.exists())
					{
						topicmetadata=new TopicMetaDataXmlReader(createquiz+"/GradeCard.xml");
                                        	detail1=topicmetadata.getAssignmentDetails1();
						if(detail1!=null)
						{
                                        		for(int k=0;k<detail1.size();k++)
                                        		{//for2
                                                		String gradeqid=((FileEntry) detail1.elementAt(k)).getUserName().trim();
                                                		String gradeuid=( (FileEntry) detail1.elementAt(k)).getGrade().trim();
                                                		String gradefeed=( (FileEntry) detail1.elementAt(k)).getfeedback().trim();
                                                		if((gradeqid.equals(quizname)) && (Integer.parseInt(gradeuid) == uid))
                                                		{//if5
									context.put("obtmarks",gradefeed);
                                                        		int finalscore=Integer.parseInt(gradefeed);
                                                        		if( finalscore >= Integer.parseInt(instques))
                                                                	context.put("pass","passed");
                                                        		else
                                                                	context.put("pass","failed");
                                                		}//if5
                                        		}//for2
						}
                                                context.put("quizmess","file");
					}
                        	}//mode
			}//admincheck
			//--------------------------date and time-----------------//
			else
			{
				String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                context.put("userConf",new Integer(AdminConf));
                                context.put("userConf_string",AdminConf);
				int startIndex=pp.getInt("startIndex",0);

				String chktype=pp.getString("chktype");
				String coltype=pp.getString("coltype");
				context.put("coltype",coltype);
				Vector usrid=new Vector();
				Vector usrid1=new Vector();
				Vector usrid3=new Vector();
				Vector usrid4=new Vector();
				Vector usrid5=new Vector();
				Vector usrid6=new Vector();
				String uname="",time="";
				long Totaltime=0;
				int noUid[]={0,1};
				int uid2=0,uid4=0,uid3=0,tmp=0,eid=0,eid1=0,intdiff=0;
                        	// Retrieves the current date of the System see ExpiryUtil in utils
                        	String cdate=ExpiryUtil.getCurrentDate("");
				String actionName=pp.getString("actionName","");
				context.put("actionName",actionName);
                        	/**
                         	* Get the current day from the currentdate
                         	*/
                        	context.put("cdays",cdate.substring(6,8));
                        	context.put("cmonth",cdate.substring(4,6));
                        	context.put("cyear",cdate.substring(0,4));
                        	context.put("year_list",YearListUtil.getYearList());
				//--------------------------date and time-----------------//
				String year="",month="",day="";
				String year1="",month1="",day1="";
				if(actionName.equals("eventSubmit_doUserCourseInfo"))
				{
				year=pp.getString("fStart_year");
                        	month=pp.getString("fStart_mon");
                        	day=pp.getString("fStart_day");
				context.put("fStart_year",year);
				context.put("fStart_mon",month);
				context.put("fStart_day",day);
                        	String fdate=year+"-"+month+"-"+day;
				Date fdate1= Date.valueOf(fdate);
				long fdate2= fdate1.getTime();
				year1=pp.getString("tStart_year");
                        	month1=pp.getString("tStart_mon");
                        	day1=pp.getString("tStart_day");
				context.put("tStart_year",year1);
				context.put("tStart_mon",month1);
				context.put("tStart_day",day1);
                        	String tdate=year1+"-"+month1+"-"+day1;
				Date tdate1=Date.valueOf(tdate);
                        	long tdate2= tdate1.getTime();
                       		long  nodays=(fdate2-tdate2)/(24*3600*1000)+1;
				String diff=Long.toString(nodays);
				intdiff=Integer.parseInt(diff);
				context.put("intdiff",intdiff);
				//----------------------------------------------------------//
				//Time
				crit=new Criteria();
                		crit.add(UsageDetailsPeer.USER_ID,uid1);
				crit.addNotIn(UsageDetailsPeer.USER_ID,noUid);
                		v=UsageDetailsPeer.doSelect(crit);
				if(v !=null)
				{
					for(int i=0;i<v.size();i++)
					{
						UsageDetails element=(UsageDetails)v.get(i);
						uid3=element.getUserId();	
						uname=UserUtil.getLoginName(uid3);	
						eid=element.getEntryId();	
						java.util.Date logindate=new java.util.Date();
						logindate=(element.getLoginTime());
						long ldate=logindate.getTime();
						long ldays=(ldate-tdate2)/(24*3600*1000)+1;
						String diff1=Long.toString(ldays);
						int intdiff1=Integer.parseInt(diff1);
						if(intdiff1 >= intdiff)
						{
							if(!usrid.contains(uname))
							{
								usrid.addElement(uname);
							}
							usrid1.addElement(uid3);
							usrid4.addElement(eid);
						}
					}
				}
				//}
				CommonUtility.PListing(data,context,usrid);
				context.put("chktype","userlog");
				for(int k=0;k < usrid.size(); k++)
				{
					uname =(String)usrid.get(k);
					uid4 =UserUtil.getUID(uname);
					for(int j=0;j < usrid1.size(); j++)
					{
						uid2=(Integer)usrid1.get(j);
						if(uid4==uid2)
						{
							tmp=tmp+1;
						}
					}
					usrid3.addElement(tmp);
					tmp=0;
				}
				int t_size=usrid3.size();
                                int value[]=new int[7];
                                value=ListManagement.linkVisibility(startIndex,t_size,Integer.parseInt(AdminConf));
                                int k2=value[6];
                                context.put("k",String.valueOf(k2));
                                Integer total_size=new Integer(t_size);
                                context.put("total_size",total_size);

                                int eI=value[1];
                                Integer endIndex=new Integer(eI);
                                context.put("endIndex",endIndex);
                                //check_first shows the first five records
                                int check_first=value[2];
                                context.put("check_first",String.valueOf(check_first));
                                //check_pre shows the first the previous list to the current records
                                int check_pre=value[3];
                                context.put("check_pre",String.valueOf(check_pre));
                                //check_last1 gives the remainder values:-
                                int check_last1=value[4];
                                context.put("check_last1",String.valueOf(check_last1));

                                //check_last shows the last records:-
                                int check_last=value[5];
                                context.put("check_last",String.valueOf(check_last));
                                context.put("startIndex",String.valueOf(eI));
                                Vector splitlist=ListManagement.listDivide(usrid3,startIndex,Integer.parseInt(AdminConf));
				context.put("usrid3",splitlist);
				//--------------------------tiotal access time-------------------//
				for(int a=0;a<usrid.size();a++)
				{
					uname =(String)usrid.get(a);
					uid4 =UserUtil.getUID(uname);
					for(int c=0;c<usrid4.size();c++)
					{
						eid =(Integer)usrid4.get(c);
						for(int b=0;b<v.size();b++)
						{
							UsageDetails element=(UsageDetails)v.get(b);
							uid3=element.getUserId();	
							eid1=element.getEntryId();	
                                       			java.util.Date logindate=new java.util.Date();
                                       			logindate=(element.getLoginTime());
                                 			long ldate=logindate.getTime();
                                        		java.util.Date logoutdate=new java.util.Date();
                                         		logoutdate=(element.getLogoutTime());
                                         		long loutdate=logoutdate.getTime();
							if(uid4==uid3 && eid == eid1)
							{	
								long elapsedTime =(loutdate - ldate)/1000;
								Totaltime=Totaltime+elapsedTime;
							}
						}
					}
					usrid5.addElement(Totaltime);
					Totaltime=0;
				}
				//-------------------------change seconds in hh:mm:ss------------//
				for(int d=0;d<usrid5.size();d++)
				{
					long sec=(Long)usrid5.get(d);
					String seconds = Integer.toString((int)(sec % 60));  
					String minutes = Integer.toString((int)((sec % 3600) / 60));  
					String hours = Integer.toString((int)(sec / 3600));
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
					usrid6.addElement(time);
				}
				int t_size1=usrid6.size();
                                int value1[]=new int[7];
                                value1=ListManagement.linkVisibility(startIndex,t_size1,Integer.parseInt(AdminConf));
                                int k1=value1[6];
                                context.put("k",String.valueOf(k1));
                                Integer total_size1=new Integer(t_size1);
                                context.put("total_size",total_size1);

                                int eI1=value[1];
                                Integer endIndex1=new Integer(eI1);
                                context.put("endIndex",endIndex1);
                                //check_first shows the first five records
                                int check_first1=value1[2];
                                context.put("check_first",String.valueOf(check_first1));
                                //check_pre shows the first the previous list to the current records
                                int check_pre1=value1[3];
                                context.put("check_pre",String.valueOf(check_pre1));
                                //check_last1 gives the remainder values:-
                                int check_last2=value1[4];
                                context.put("check_last1",String.valueOf(check_last2));

                                //check_last shows the last records:-
                                int check_last3=value1[5];
                                context.put("check_last",String.valueOf(check_last3));
                                context.put("startIndex",String.valueOf(eI1));
                                Vector splitlist1=ListManagement.listDivide(usrid6,startIndex,Integer.parseInt(AdminConf));
                                context.put("usrid6",splitlist1);
				//-------------------------change seconds in hh:mm:ss------------//
				//--------------------------tiotal access time-------------------//
				//--------------------------all course details-------------------//
				Vector cname=new Vector();
				Vector totalname=new Vector();
				Vector totaluser=new Vector();
				Vector ctime=new Vector();
				Vector ctime1=new Vector();
				String gname1="",gname="";
				int gid2=0,gid3=0;
				crit =new Criteria();
                		crit.add(TurbineUserGroupRolePeer.USER_ID,uid1);
				crit.addNotIn(TurbineUserGroupRolePeer.USER_ID,noUid);
                               	u=TurbineUserGroupRolePeer.doSelect(crit);
				for(int a=0;a<usrid.size();a++)
				{
					uname =(String)usrid.get(a);
					uid4 =UserUtil.getUID(uname);
                               		for(int i=0;i<u.size();i++)
                               		{
                                       		TurbineUserGroupRole element=(TurbineUserGroupRole)u.get(i);
                                       		uid2=element.getUserId();
                                       		gid2=element.getGroupId();
						if((uid4 == uid2) && (gid2 !=1) &&(gid2 !=2))
						{
							gname=GroupUtil.getGroupName(gid2);
							gname1=CourseUtil.getCourseName(gname).trim();
							if(!cname.contains(gname1))
							{
								cname.addElement(gname1);
							}
						}
					}
				}
				context.put("cname",cname);
				//------------------------no og users access--------------------------//
				for(int m=0;m<cname.size();m++)
				{
					gname =(String)cname.get(m);
					gname1=CourseUtil.getCourseId(gname);
					gid3=GroupUtil.getGID(gname1);
					for(int j=0;j<usrid.size();j++)
					{
						uname =(String)usrid.get(j);
						uid3 =UserUtil.getUID(uname);
						for(int n=0;n<u.size();n++)
						{
							TurbineUserGroupRole element1=(TurbineUserGroupRole)u.get(n);
                                      			uid2=element1.getUserId();
                                       			gid2=element1.getGroupId();
							if(uid3 == uid2 && gid3 == gid2 && gid2!=uid2 )
							{
								tmp=tmp+1;
								totaluser.addElement(uid3);
							}
						}
					}
					totalname.addElement(tmp);
					tmp=0;
				}
				context.put("totalname",totalname);
				for(int m=0;m<cname.size();m++)
				{
					gname =(String)cname.get(m);
					gname1=CourseUtil.getCourseId(gname);
					gid3=GroupUtil.getGID(gname1);
					for(int k=0;k<totaluser.size();k++)
					{
						uid4 =(Integer)totaluser.get(k);
						for(int n=0;n<u.size();n++)
						{
							TurbineUserGroupRole element1=(TurbineUserGroupRole)u.get(n);
                                        		uid2=element1.getUserId();
                                               		gid2=element1.getGroupId();
							if(uid4 == uid2 && gid3 == gid2 && gid2!=uid2 )
							{
                                        			for(int c=0;c<usrid4.size();c++)
                                        			{
                                                			eid =(Integer)usrid4.get(c);
									for(int b=0;b<v.size();b++)
                                                			{
                                                       				UsageDetails element=(UsageDetails)v.get(b);
                                                       				uid3=element.getUserId();
                                                       				eid1=element.getEntryId();
                                                       				java.util.Date logindate=new java.util.Date();
                                                       				logindate=(element.getLoginTime());
                                                       				long ldate=logindate.getTime();
                                                       				java.util.Date logoutdate=new java.util.Date();
                                                       				logoutdate=(element.getLogoutTime());
                                                       				long loutdate=logoutdate.getTime();
                                                       				if(uid4==uid3 && eid == eid1)
                                                       				{
                                                               				long elapsedTime =(loutdate - ldate)/1000;
                                                               				Totaltime=Totaltime+elapsedTime;
                                                       				}	
                                                			}
                                        			}
							}
						}
					}
                                       	ctime.addElement(Totaltime);
                                       	Totaltime=0;
				}
				for(int d=0;d<ctime.size();d++)
                               	{
                                       	long sec=(Long)ctime.get(d);
                                       	String seconds = Integer.toString((int)(sec % 60));
                                       	String minutes = Integer.toString((int)((sec % 3600) / 60));
                                       	String hours = Integer.toString((int)(sec / 3600));
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
                                       	ctime1.addElement(time);
                               	}
                               	context.put("ctime1",ctime1);
				//------------------------no of users access--------------------------//
				//--------------------------all course details-------------------//
				}
			}//else
		}//try
		catch(Exception e){
                	ErrorDumpUtil.ErrorLog("Error in Screen:Track_Reportquiz !!"+e);	
                        data.setMessage("See ExceptionLog !! " );
                        }
	}//method close
}//class
