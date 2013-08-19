
package org.iitk.brihaspati.modules.screens.call;
/*
 * @(#)StudentInfo.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur.
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



import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;
import org.iitk.brihaspati.modules.utils.MarksView;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.Assignment;
import org.iitk.brihaspati.om.AssignmentPeer;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.AssignmentDetail;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
/**
 *   @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 **/

public class StudentInfo extends SecureScreen{

        public void doBuildTemplate( RunData data, Context context )
	{

		try{
			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			String LangFile=data.getUser().getTemp("LangFile").toString();
			pp.add("str","studentinfo");
			String Crsname = pp.getString("courseId","");
			String courseid = CourseUtil.getCourseId((Crsname).trim());
			String mode = pp.getString("mode","");
			String DB_subject1=pp.getString("topicList","");
			String semail = pp.getString("semail","");
			int uid = UserUtil.getUID(semail);
			context.put("course",Crsname);
			context.put("mode",mode);
			context.put("courseid",courseid);
			context.put("student",semail);

			//code for marks
			if(mode.equals("marks")){
				Vector marks = MarksView.getMarks(semail,courseid);
				context.put("alias",marks.get(0));
                	        context.put("markHeading",marks.get(1));
                        	context.put("markDetail",marks.get(2));
				Vector v = (Vector)marks.get(2);
        	                context.put("marksDSize",v.size());
				if(v.size()!=0)
				{
					
					String msg1=MultilingualUtil.ConvertedString("Marks_msg8",LangFile);
					context.put("marksMsg",msg1);
				}
					
			}

			// code for assignment
			else if(mode.equals("assign")){
				Vector w=new Vector();
				Vector stname=new Vector();
				Vector totalassgn = new Vector();
				stname.add(semail);
				//read the xml file
	                        TopicMetaDataXmlReader topicmetadata=null;
        	                Vector Assignmentlist=new Vector();
                	        Vector Topiclist=new Vector();

				Criteria brit=new Criteria();
                	        brit.add(AssignmentPeer.GROUP_NAME,courseid);
	                        List ul=AssignmentPeer.doSelect(brit);
				if(ul.size()>0)
				{
				String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
				String str2 = "";
        	                for(int i=0;i<ul.size();i++)
                	        {
                        	        Assignment element=(Assignment)(ul.get(i));
	                                Date date1=(element.getDueDate());
        	                        String Assid=(element.getAssignId());
					Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                        	        if(Assid.startsWith(courseid))
	                                {
        	                                str2=(element.getTopicName());
                                                Assign =Assign+"/"+Assid;
						topicmetadata=new TopicMetaDataXmlReader(Assign+"/__file.xml");
			                        Assignmentlist=topicmetadata.getAssignmentDetails();
						//ErrorDumpUtil.ErrorLog("==Assignmentlist===="+Assignmentlist);
		        	                Vector assignmentDetail=new Vector();
		                	        String anscheck="notok";
			                        String datecheck="notok";
			                        String gradecheck="notok";
			                        String studentfilecheck="notok";
						String fileAssignment="";
	                        	        String fileanswer="";
		                                String filedate="";
		                                String filestudent="";
               			                String filegrade="";
	                	                String grade="";
               		        	        String feedback="";
                               			String duedate="";
						for(int c=0;c<Assignmentlist.size();c++)
	                        	        {
							String filereader =((FileEntry) Assignmentlist.elementAt(c)).getfileName();
        	                       		        String username=((FileEntry) Assignmentlist.elementAt(c)).getUserName();
	        	                                if(filereader.startsWith("AssignmentFile") && c<1 )
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
	                                        	if(semail.equals(username))
	               		                        {	
        	       		                                filestudent=filereader;
                	               		                studentfilecheck="ok";
								duedate =((FileEntry) Assignmentlist.elementAt(c)).getDuedate();
	                        	                        datecheck="ok";
								try{
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
												String filereader1 =((FileEntry) Assignmentlist1.elementAt(intgrade)).getUserName();
		                                                        	                if(filereader1.equals(semail))
		                                                                	        //if(filereader1.equals(studentname))
                       			                                                	{
													grade =((FileEntry) Assignmentlist1.elementAt(intgrade)).getGrade();
				                                                                        feedback =((FileEntry) Assignmentlist1.elementAt(intgrade)).getfeedback();
                	                				
								                                        gradecheck="ok";
												}
											}
										}
									}
								}
								catch(Exception e){     }
							}
						}
						AssignmentDetail assignmentdetail=new AssignmentDetail();
		                                //assignmentdetail.setStudentname(studentname);
		                                assignmentdetail.setTopic(str2);
	        	                        assignmentdetail.setStudentname(semail);
              				        assignmentdetail.setStudentfile(filestudent);
	                        	        assignmentdetail.setAssignmentfile(fileAssignment);
	                                	assignmentdetail.setDuedate(duedate);
			               	        assignmentdetail.setAssignmentDuedate(filedate);
	        	                        assignmentdetail.setmaxgrade(filegrade);
	                	                assignmentdetail.setgrade(grade);
	                        	        assignmentdetail.setanswerfile(fileanswer);
		                       	        assignmentdetail.setfeedback(feedback);
		                       	        assignmentdetail.setAssignmentId(Assid);
		                                assignmentDetail.add(assignmentdetail);
					context.put("gradecheck",gradecheck);
	                	        context.put("anscheck",anscheck);
               				context.put("datecheck",datecheck);
		                        context.put("studentfilecheck",studentfilecheck);
		                        context.put("Assignmentlist",assignmentDetail);
					}
                       	    	}
				}
				else
				{
					String msg1=MultilingualUtil.ConvertedString("assignment_msg14",LangFile);
					context.put("assignMsg",msg1);
				}
                	}
			//Code for message
			else if(mode.equals("msg")){
				String message = pp.getString("msg_val","");
				String CrsInstrmail = CourseUtil.getCourseInstrEmail(courseid);
				context.put("CrsInstremail",CrsInstrmail);
			}
			//code for quiz
			else if(mode.equals("quiz")){
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
                        String quizPath="/Quiz.xml";
                        String scorePath="/score.xml";
                        File file=new File(filePath+"/"+quizPath);
                        Vector quizList=new Vector();
                        Vector attemptedQuizList=new Vector();
                        Vector QuizDetail = new Vector();
                        Vector attemptQuiz = new Vector();
                        QuizMetaDataXmlReader quizmetadata=null;
			QuizFileEntry q=null;
			Map map = new HashMap();
                        ArrayList list = new ArrayList();

                        if(!file.exists()){
				String msg1=MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile);
				context.put("quizAnnounceMsg",msg1);
                                data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
                        }
                        else{
                                context.put("isFile","exist");
				Vector<QuizFileEntry> finalQuizList = new Vector();
                                quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
                                QuizDetail = quizmetadata.getQuesBanklist_Detail();
				if(QuizDetail!=null && QuizDetail.size()!=0){
	                                for(int i=0;i<QuizDetail.size();i++){
						map = new HashMap();
						boolean flag = false;
						String quizId = (((QuizFileEntry)QuizDetail.elementAt(i)).getQuizID());
						String quizname = (((QuizFileEntry)QuizDetail.elementAt(i)).getQuizName());
						String quizCrtdate = (((QuizFileEntry)QuizDetail.elementAt(i)).getCreationDate());
                                		//ErrorDumpUtil.ErrorLog("quizId from QuizDetail-------------"+quizId);
						quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+scorePath);
						Vector totalQuiz = quizmetadata.attemptedQuiz();
						q = new QuizFileEntry();
						if(totalQuiz!=null && totalQuiz.size()!=0){
	                                                for(int j=0;j<totalQuiz.size();j++){
								String scorequizID = (((QuizFileEntry)totalQuiz.get(j)).getQuizID());
								String xmlUserID = (((QuizFileEntry)totalQuiz.get(j)).getUserID());
								if(scorequizID.equals(quizId) && xmlUserID.equals(Integer.toString(uid)))
								{
									flag=true;
									String evaluate = (((QuizFileEntry)totalQuiz.get(j)).getEvaluate());
									if(StringUtils.isNotBlank(evaluate))
	                                                                        map.put("Evaluate",evaluate);
                                                                        else              
										map.put("Evaluate","Quiz is not Evaluated Yet");                                      
									String marksObtain = (((QuizFileEntry)totalQuiz.get(j)).getScore());
									String questionSettingPath=quizId+"_QuestionSetting.xml";
                                                                                        QuizMetaDataXmlReader quesmetadata=null;
                                                                                        quesmetadata = new QuizMetaDataXmlReader(filePath+"/"+quizId+"/"+questionSettingPath);
                                                                                        HashMap maxMarksQuestion = quesmetadata.getQuizQuestionNoMarks(quesmetadata,quizId);
                                                                                        int insertedMarksQuiz =((Integer)maxMarksQuestion.get("marks"));
                                                                                        int insertedQuestionQuiz = ((Integer)maxMarksQuestion.get("noQuestion"));
											map.put("MaxMarks",String.valueOf(insertedMarksQuiz));
											map.put("QuestionNumber",String.valueOf(insertedQuestionQuiz));
											map.put("Score",marksObtain);
											quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
                                                                                        Vector quizDetail = quizmetadata.getQuiz_Detail(quizId);
                                                                                        for(int k=0;k<quizDetail.size();k++){
                                                                                                String quizName = (((QuizFileEntry) quizDetail.elementAt(k)).getQuizName());
                                                                                                String maxTime = (((QuizFileEntry) quizDetail.elementAt(k)).getMaxTime());
                                                                                                String CreateDate = (((QuizFileEntry) quizDetail.elementAt(k)).getCreationDate());
												map.put("QuizStatus","Yes");
												map.put("QuizMode","Yes");
												map.put("ExamDate",CreateDate);
												map.put("QuizName",quizName);
												map.put("MaxTime",maxTime);
                                                                                        }
											list.add(map);
								}
							}
						}
								if(!flag)
								{
									map.put("QuizStatus","Quiz is not attempt by student.");
									map.put("QuizMode","No");
									map.put("QuizName",quizname);
									map.put("ExamDate",quizCrtdate);
									list.add(map);
								}
					}
					}
						context.put("quizList",list);
				}
			}
		}catch(Exception exp){}
        }
}
