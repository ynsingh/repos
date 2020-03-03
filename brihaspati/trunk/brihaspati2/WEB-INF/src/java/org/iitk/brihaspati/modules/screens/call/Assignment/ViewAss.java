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
	 *   @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
	 * @modified date: 31-03-2014(Dewanshu Singh)
	*/


public class ViewAss extends  SecureScreen
{
	
        public void doBuildTemplate(RunData data, Context context)
        {
                try
                {
                        User user=data.getUser();
                        String UserName=data.getUser().getName();
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        ParameterParser pp=data.getParameters();
                        context.put("coursename",(String)user.getTemp("course_name"));
                        String courseid=(String)user.getTemp("course_id");
                        String DB_subject1=pp.getString("topicList","");
			context.put("topicList",DB_subject1);
                        context.put("tdcolor",pp.getString("count",""));
        		
			String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                        Vector w=new Vector();
                        String Role="";
                                Role=pp.getString("role","");
                        if(Role.equals(""))
                                Role=(String)user.getTemp("role");
		        context.put("user_role",Role);
			/*
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(UserName);
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }

			Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
			if(Role.equals("student"))
				crit.add(AssignmentPeer.PUBLSH_STATUS,0);
                        List u=AssignmentPeer.doSelect(crit);
                        for(int i=0;i<u.size();i++)
                        {
                                Assignment element=(Assignment)(u.get(i));
                                Date date1=(element.getDueDate());
                                String Assid=(element.getAssignId());
                                if(Assid.startsWith(courseid))
                                {
					String str2=(element.getTopicName());
                                        w.add(str2);
                                        if(str2.equals(DB_subject1))
                                        {
                                                Assign =Assign+"/"+Assid;
                                                context.put("str1",Assid);
						//context.put("dest_folder",Assid);
                                        }
                                }
                        }
				
                        int uid=GroupUtil.getGID(courseid);
                        Vector stname=new Vector();
			if((Role.equals("instructor"))||(Role.equals("teacher_assistant"))){	
                        	String GetUser =pp.getString("GetUser","");
				Criteria crit3=new Criteria();
				// if topic list is null and getuser have the value
				// then get the user id for that user and give the list
				// of assignment that he has uploaded and pending assignment.
				// else
                        	crit3.add(TurbineUserGroupRolePeer.GROUP_ID,uid);
                        	crit3.and(TurbineUserGroupRolePeer.ROLE_ID,3);
                        	List v3=TurbineUserGroupRolePeer.doSelect(crit3);
				/**select Student id  */
                        	for(int i=0;i<v3.size();i++)
                        	{
					TurbineUserGroupRole element=(TurbineUserGroupRole)v3.get(i);
                                	int s=(element.getUserId());
                                	if(s>1)
                                	{
                                       		Criteria crit4=new Criteria();
                                        	crit4.add(TurbineUserPeer.USER_ID,s);
                                        	List v4=TurbineUserPeer.doSelect(crit4);
                                        	for(int j=0;j<v4.size();j++)
                                        	{
                                                	TurbineUser element1=(TurbineUser)v4.get(j);
                                                	String name=(element1.getLoginName());
                                                	if(GetUser.equals(""))
								stname.add(name);
							else if(name.startsWith(GetUser))	
								stname.add(name);
                                        	}
                                	}
                        	}
			} //if
			if(Role.equals("student")){
				stname.add(UserName);
			}
				int gid=GroupUtil.getGID(courseid);
	                        String g_Id=Integer.toString(gid);
				crit=new Criteria();
                                crit.add(NewsPeer.GROUP_ID,gid);
                                List news=NewsPeer.doSelect(crit);
				Vector entry=new Vector();
                                for(int i=0;i<news.size();i++)
                                {
                                        String news_title=new String(((News)news.get(i)).getNewsTitle());
                                        String news_desc=new String(((News)news.get(i)).getNewsDescription());

                                        boolean flag=StringUtils.contains(news_desc, DB_subject1);
                                        //ErrorDumpUtil.ErrorLog("bool return from screen file----"+flag);
                                        if(flag)
                                        {
						News element=(News)(news.get(i));
	                                        String news_subject=(element.getNewsTitle());
        	                                String news_id=Integer.toString(element.getNewsId());
                	                        int userId=(element.getUserId());
                        	                String senderName=UserUtil.getLoginName(userId);
	
        	                                Date pd=element.getPublishDate();
                	                        String pdate=pd.toString();
	                                        NewsDetail newsD=new NewsDetail();
        	                                newsD.setNews_Subject(news_subject);
                	                        newsD.setNews_ID(news_id);
                        	                newsD.setSender(senderName);
                                	        newsD.setPDate(pdate);
                                        	entry.add(newsD);
                                        }
                                }
				context.put("detail",entry);
		//	}
			context.put("allTopics",w);
                        //read the xml file
			if(StringUtils.isNotBlank(DB_subject1))
			{                
                        TopicMetaDataXmlReader topicmetadata=null;
                        Vector Assignmentlist=new Vector();
                        topicmetadata=new TopicMetaDataXmlReader(Assign+"/__file.xml");
                        Assignmentlist=topicmetadata.getAssignmentDetails();
                        int g_id=GroupUtil.getGID(courseid);
			Vector assignmentDetail=new Vector();
			String anscheck="notok";
                        String datecheck="notok";
                        String gradecheck="notok";
                        String studentfilecheck="notok";
			String postAnsChk="no";
			if(stname.size()==0)
				context.put("startpage",stname.size());	
			else
				context.put("startpage",stname.size());	
			
			if(stname.size()!=0)
			{
			for(int k=0;k<stname.size();k++)
			{
				String fileAssignment="";
				String fileanswer="";
				String filedate="";
				String filestudent="";
				String filegrade="";
				String grade="";
				String feedback="";
				String duedate="";
				String studentname=(String)stname.get(k);


				for(int c=0;c<Assignmentlist.size();c++)
				{

					/**
					 * Getting the filename,  through xml file
					 * @see TopicMetaDataXmlReader in Util.
					 */
					
					String filereader =((FileEntry) Assignmentlist.elementAt(c)).getfileName();
                                	String username=((FileEntry) Assignmentlist.elementAt(c)).getUserName();
	                                if((filereader.startsWith("AssignmentFile")||StringUtils.isBlank(filereader)) && c<1 )
        	                        {       
						fileAssignment=filereader;
                	                        filegrade =((FileEntry) Assignmentlist.elementAt(c)).getGrade();
                                	        filedate  =((FileEntry) Assignmentlist.elementAt(c)).getDuedate();
                                	}
                                	else if(filereader.startsWith("Answer")){
                                        	fileanswer=filereader;
						anscheck="ok";	
					}
				}
				for(int c=0;c<Assignmentlist.size();c++)
                                {
					String filereader =((FileEntry) Assignmentlist.elementAt(c)).getfileName();
                                        String username=((FileEntry) Assignmentlist.elementAt(c)).getUserName();
	                                if(studentname.equals(username) )
        	                        {
						
                	                  	filestudent=filereader;
						studentfilecheck="ok";
						/**
                        	                * Getting the Due Date,UserName,  through xml file
                                	        * @see TopicMetaDataXmlReader in Util.
                                        	*/
						
						duedate =((FileEntry) Assignmentlist.elementAt(c)).getDuedate();
						datecheck="ok";
                                
					        try{
							
	                                        /**
        	                                * @see TopicMetaDataXmlReader in Util.
                	                        */
							
        	                                TopicMetaDataXmlReader topicmetadata1=null;
                	                        Vector Assignmentlist1=new Vector();
                        	                topicmetadata1=new TopicMetaDataXmlReader(Assign+"/__Gradefile.xml");
                                	        Assignmentlist1=topicmetadata1.getAssignmentDetails1();
                                        	File f2= new File(Assign+"/__Gradefile.xml");
                                                if(f2.exists())
                                                {
                                                	if(Assignmentlist1!=null)
                                                	{
                                                       		for(int intgrade=0;intgrade<Assignmentlist1.size();intgrade++)
                                                       		{
                                                  	     		/**
                                                       			* Getting the username  through xml file
                                                       			* @see TopicMetaDataXmlReader in Util.
                                                       			*/
										
									String filereader1 =((FileEntry) Assignmentlist1.elementAt(intgrade)).getUserName();
                                                        		if(filereader1.equals(studentname))
      	                                                		{
									
									/**
                                	                              	* Getting the grade,feedback  through xml file
                                        	                       	* see TopicMetaDataXmlReader in Util
                                                                	* @see student given  grade
									*/
									
					                               	grade =((FileEntry) Assignmentlist1.elementAt(intgrade)).getGrade();
		                					feedback =((FileEntry) Assignmentlist1.elementAt(intgrade)).getfeedback();
									gradecheck="ok";
				
						       			}//end inner if
							        }//end for
                                                        }  //end middile if
                                                }  //end outer if
                                        	}  //end inner try
					      	catch(Exception e){     }
                        		} //end else
				}// for
				// get the full of student 
				String stFName=UserUtil.getFullName(UserUtil.getUID(studentname));
				// Get the roll no of this student		
				String  stRlNo=CourseProgramUtil.getUserRollNo(studentname,courseid);

				Date date1 = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateFormatted = dateFormat.format(date1);
				Date todayDate = dateFormat.parse(dateFormatted);
				Date newDueDate = dateFormat.parse(filedate);
				
				//ErrorDumpUtil.ErrorLog("filedate=="+filedate);
				//ErrorDumpUtil.ErrorLog("(todayDate.compareTo(newDueDate==="+todayDate.compareTo(newDueDate));
 				if ((todayDate.compareTo(newDueDate)<0)||(todayDate.compareTo(newDueDate)==0))
					postAnsChk = "Yes";
				
				AssignmentDetail assignmentdetail=new AssignmentDetail();

				if((todayDate.compareTo(newDueDate)>0)){
					assignmentdetail.setBoolean(true);
				}
				assignmentdetail.setStudentname(studentname);
				assignmentdetail.setFullName(stFName);
				assignmentdetail.setRollNo(stRlNo);
				assignmentdetail.setStudentfile(filestudent);
	                        assignmentdetail.setAssignmentfile(fileAssignment);
                	        assignmentdetail.setDuedate(duedate);
                        	assignmentdetail.setAssignmentDuedate(filedate);
                        	assignmentdetail.setmaxgrade(filegrade);
                        	assignmentdetail.setgrade(grade);
                        	assignmentdetail.setanswerfile(fileanswer);
                        	assignmentdetail.setfeedback(feedback);
                        	assignmentdetail.setPostAnsMode(postAnsChk);
                       		assignmentDetail.add(assignmentdetail); 	
				//ErrorDumpUtil.ErrorLog("   "+studentname);		
					
			} // for
			context.put("gradecheck",gradecheck);
			context.put("anscheck",anscheck);
			context.put("datecheck",datecheck);
			context.put("studentfilecheck",studentfilecheck);
			context.put("Assignmentlist",assignmentDetail);
			}
			else
			{
				data.setMessage(MultilingualUtil.ConvertedString("assignment_msg17",LangFile));
			}
		}	

		} //try
                catch(Exception e){ ErrorDumpUtil.ErrorLog("The Error in View asignment screen for student and instructor "+e); }
        }
}
