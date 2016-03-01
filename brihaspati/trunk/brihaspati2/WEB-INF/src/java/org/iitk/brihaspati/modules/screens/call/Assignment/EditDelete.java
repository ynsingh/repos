package org.iitk.brihaspati.modules.screens.call.Assignment;

/*
 * @(#)EditDelete.java 
 *
 *  Copyright (c) 2010, 2013 ETRG,IIT Kanpur.
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

import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil; 
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.apache.turbine.om.security.User;       
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

import org.iitk.brihaspati.modules.utils.NewsDetail;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.News;

	/**
	 *   This class contains code for disply all assignment
	 *   only instructor and do update/delete
	 *   @author  <a href="sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
	*/

public class EditDelete extends  SecureScreen
{
        public void doBuildTemplate(RunData data, Context context)
        {
                try
                {
			/**
                        * Retrieve the parameters by using the ParameterParser
                        */

			User user=data.getUser();
                        String UserName=data.getUser().getName();
                        ParameterParser pp=data.getParameters();
			/**
			 *Get User Role
			 */
			 String Role = (String)user.getTemp("role");
			 int uid=UserUtil.getUID(UserName);
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }


			/**
			* Get Course Name
			*/
                        context.put("coursename",(String)user.getTemp("course_name"));
                        String courseid=(String)user.getTemp("course_id");
			//ErrorDumpUtil.ErrorLog("\n\ncourseid="+courseid);
                        context.put("tdcolor",pp.getString("count",""));
			/**
			* Get Topic Name
			*/
                        String topic=pp.getString("topic","");
                        context.put("topic",topic);
			/**
			* Get Course_Id
			*/
                        String cid=pp.getString("cid","");
                        context.put("cid",cid);
                        String DB_subject1=pp.getString("topicList");
                        context.put("tdcolor",pp.getString("count",""));

			/**
                        *This mode use for update and delete assignment
                        */

                        String mode=pp.getString("mode","");
                        context.put("mode",mode);
                        context.put("DB_subject1",DB_subject1);
			//ErrorDumpUtil.ErrorLog("\n\nDB_subject1=="+DB_subject1+"\n topic=="+topic+"mode=="+mode);
			/**
                        *Find the Assignment Course path and show assignment 
                        */	

			String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                        Vector w=new Vector();
                        
			Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                        List u=AssignmentPeer.doSelect(crit);
                        for(int i=0;i<u.size();i++)
                        {
				/**
				* Get Assignment_id, Assignment, Assingment Date and Max Marks
				*/
                                Assignment element=(Assignment)(u.get(i));
                                String Assid=(element.getAssignId());
				String pubst=(element.getPublshStatus());
                                if(Assid.startsWith(courseid))
                                {
					//////////////////////////////
					String id = Integer.toString(element.getId());
					//ErrorDumpUtil.ErrorLog(" Assid ==="+ Assid );
					AssignmentDetail assignmentdetail=new AssignmentDetail();
					TopicMetaDataXmlReader topicmetadata = null;

					// This block for the grade file
					try{
						Vector gradeList=new Vector();
			                        File f2= new File(Assign+"/"+Assid+"/__Gradefile.xml");
                        			if(f2.exists()){
							//TopicMetaDataXmlReader topicMetaData;
							topicmetadata=new TopicMetaDataXmlReader(Assign+"/"+Assid+"/__Gradefile.xml");
	                        			gradeList = topicmetadata.getAssignmentDetails1();
			                               	//ErrorDumpUtil.ErrorLog("Grade size in else Grade=="+ gradeList.size());
                        				if(gradeList!=null){
								assignmentdetail.setBoolean(true);
							}
						}
						else
							assignmentdetail.setBoolean(false);
					
					}	
					catch(Exception ex){data.setMessage("Error in reading grade file under editdelete assignment screen " + ex);}
					// This block for assignment file 
					String topicname=(element.getTopicName());
                                        String str2=(element.getTopicName());
		                        Vector Assignmentlist=new Vector();
					//TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(Assign+"/"+Assid+"/__file.xml");
					String filegrade=""; //Assignment File
					String fileAssignment=""; //Due Date
					String filedate="";
					try{
						File f2= new File(Assign+"/"+Assid+"/__file.xml");
						if(f2.exists()){
							topicmetadata=new TopicMetaDataXmlReader(Assign+"/"+Assid+"/__file.xml");
				                        Assignmentlist=topicmetadata.getAssignmentDetails(); //Grade
							for(Object val : Assignmentlist) {
	                                			String filereader =((FileEntry)val).getfileName();
		                                        	String username=((FileEntry)val).getUserName();
							       	if(filereader.startsWith("AssignmentFile")||StringUtils.isBlank(filereader))
                                		        	{
                                                			fileAssignment=filereader;
									if(filereader.startsWith("AssignmentFile")){
                		                                		filegrade =((FileEntry)val).getGrade();
                                		                		filedate  =((FileEntry)val).getDuedate();
									}
									break;
                		                        	}
							}		
						}
						else{
							filegrade =Integer.toString(element.getGrade());
							filedate  =(element.getDueDate()).toString();
					
						}
					}
					catch (Exception ex){data.setMessage("Error in reading assignment file under editdelete assignment screen  "+Assignmentlist +"-"+topicmetadata +"-" + ex);}
					// This block for the Instruction set
					
					int gid=GroupUtil.getGID(courseid);
                                	String g_Id=Integer.toString(gid);
                                	crit=new Criteria();
                                	crit.add(NewsPeer.GROUP_ID,gid);
                                	List news=NewsPeer.doSelect(crit);
					String news_id="", senderName="",pdate="";
//                                	Vector entry=new Vector();
                                	for(int j=0;j<news.size();j++)
                                	{
                                        //	String news_title=new String(((News)news.get(j)).getNewsTitle());
                                        	String news_desc=new String(((News)news.get(j)).getNewsDescription());

                                        	boolean flag=StringUtils.contains(news_desc, topicname);
                                        //ErrorDumpUtil.ErrorLog("bool return from screen file----"+flag);
                                                if(flag)
                                                {
                                        	         News ele=(News)(news.get(j));
                                                        // String news_subject=(ele.getNewsTitle());
                                                         news_id=Integer.toString(ele.getNewsId());
                                                         int userId=(ele.getUserId());
                                                         senderName=UserUtil.getLoginName(userId);
                                                         Date pd=ele.getPublishDate();
                                                         pdate=pd.toString();
							 break;
                                                       //  NewsDetail newsD=new NewsDetail();
                                                       //  newsD.setNews_Subject(news_subject);
                                                       //  newsD.setNews_ID(news_id);
                                                     //    newsD.setSender(senderName);
                                                   //      newsD.setPDate(pdate);
                                                 //        entry.add(newsD);
                                                }
                                        }
                                       // context.put("detail",entry);
                                       
					//AssignmentDetail assignmentdetail=new AssignmentDetail();

					assignmentdetail.setAssignmentId(id);
					assignmentdetail.setStudentname(topicname);
					assignmentdetail.setStudentfile(Assid);
					assignmentdetail.setAssignmentfile(fileAssignment);
					assignmentdetail.setDuedate(filedate);
					assignmentdetail.setmaxgrade(filegrade);
					assignmentdetail.setPubstatus(pubst);
					assignmentdetail.setRollNo(news_id);
					assignmentdetail.setFullName(senderName);
					assignmentdetail.setAssignmentdate(pdate);
					w.add(assignmentdetail);
				}//if
                        }//for
			context.put("Assignmentlist",w);
		} //try
                catch(Exception e){data.setMessage("Error in screen under edit delete assignment" + e); }
        }
}
