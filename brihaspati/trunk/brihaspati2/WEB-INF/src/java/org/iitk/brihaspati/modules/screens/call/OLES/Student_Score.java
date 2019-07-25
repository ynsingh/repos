package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Student_Score.java
 *
 *  Copyright (c) 2010-2011 MHRD, DEI Agra.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code mNo question is attemptedust retain the above copyright
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
 *  Contributors: Members of MHRD, DEI Agra.
 *
 */

import java.util.Calendar;
import java.util.Iterator;
import java.util.HashMap;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.QuizUtil;
import java.util.Vector;
import java.io.File;
import java.util.StringTokenizer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import java.util.Date;
import org.apache.torque.util.Criteria;
import java.util.List;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
 *   This class contains code for quiz attempt part from student login
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */
public class Student_Score extends SecureScreen
{
	public void doBuildTemplate( RunData data, Context context )
	{
		ParameterParser pp=data.getParameters();
		String LangFile=data.getUser().getTemp("LangFile").toString();
		try{
			Attempt_Quiz.msg = 0;
			User user=data.getUser();
			String loginname=user.getName();
			String user_id=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			Criteria crit=new Criteria();
			String Role=(String)user.getTemp("role");
			context.put("user_role",Role);

			//String quizID=pp.getString("quizID","");

			String count = pp.getString("count","");
			if(count.isEmpty()){
				count=(String)user.getTemp("count");
			}
			String type = pp.getString("type","");
			context.put("type",type);
			if(type.isEmpty())
				count="3";
			else
				count = "4";
			context.put("tdcolor",count);
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizPath="/Quiz.xml";
			String scorePath="/score.xml";
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector attemptedQuizList=new Vector();
			Vector futureQuizList = new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			/*read the quiz.xml and find the no of quiz and match quizid and rad the score.xml form individual
			quiz
			Vector Read=new Vector();
			TopicMetaDataXmlReader tr=null;
			tr =new TopicMetaDataXmlReader(filepath+"/"+fulltopic+".xml");
			Read=tr.getQuesBank_DetailAg();*/
			String quizpath="Quiz.xml";
		    	File newfile=new File(filePath+"/"+quizpath);
    			Vector vec=new Vector();
   			String res_date,quizID="";
		//ErrorDumpUtil.ErrorLog("newfile exists"+newfile);
    		if(!newfile.exists()){
		}
		else{
      QuizMetaDataXmlReader quiznewMetadata=new QuizMetaDataXmlReader(filePath+"/"+quizpath);
      vec=quiznewMetadata.getQuesBanklist_Detail();
			//ErrorDumpUtil.ErrorLog("newfile exists line bo 134"+newfile);
		  	//ErrorDumpUtil.ErrorLog("vec siz is"+vec.size());
		}
		int counter=0;
		Vector<QuizFileEntry> finalQuizList = new Vector();
      for(int i=0;i<vec.size();i++)
      {
        //res_date =((QuizFileEntry) vec.elementAt(i)).getRes_date();
        quizID=((QuizFileEntry) vec.elementAt(i)).getQuizID();
        //ErrorDumpUtil.ErrorLog("res-data is ::"+res_date);
        //ErrorDumpUtil.ErrorLog("quizID inside loop is9"+quizID);


			if(!file.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
			}
			else{
				context.put("isFile","exist");
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
				futureQuizList = quizmetadata.listFutureQuiz();
				context.put("futureQuizList",futureQuizList);
			//	File scoreFile=new File(filePath+"/"+scorePath);
				File scoreFile=new File(filePath+"/"+quizID+"/"+scorePath);
				if(!scoreFile.exists()){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
				}
				else{
					//quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+scorePath);
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizID+"/"+scorePath);
					attemptedQuizList=quizmetadata.getFinalScore(user_id);
					Vector quizDetail = new Vector();
					//Vector<QuizFileEntry> finalQuizList = new Vector();
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
					if(attemptedQuizList!=null && attemptedQuizList.size()!=0){
						for(int j=0;j<attemptedQuizList.size();j++){
							QuizFileEntry q = (QuizFileEntry)attemptedQuizList.get(j);
							String quizid1 = q.getQuizID();
							//String resultdate=q.getResultDate();
							//==========================================
							if(quizID.equals(quizid1))
							{
								String questionSettingPath=quizid1+"_QuestionSetting.xml";
								QuizMetaDataXmlReader quesmetadata=null;
								quesmetadata = new QuizMetaDataXmlReader(filePath+"/"+quizid1+"/"+questionSettingPath);
								HashMap maxMarksQuestion = quesmetadata.getQuizQuestionNoMarks(quesmetadata,quizid1);
								int insertedMarksQuiz =((Integer)maxMarksQuestion.get("marks"));
								int insertedQuestionQuiz = ((Integer)maxMarksQuestion.get("noQuestion"));
								 //===========================================
								q.setMaxMarks(String.valueOf(insertedMarksQuiz));
								q.setQuestionNumber(String.valueOf(insertedQuestionQuiz));
								quizDetail = quizmetadata.getQuiz_Detail(q.getQuizID());
								for(int k=0;k<quizDetail.size();k++){
									String quizName = (((QuizFileEntry) quizDetail.elementAt(k)).getQuizName());
									String maxTime = (((QuizFileEntry) quizDetail.elementAt(k)).getMaxTime());
									String resdate = (((QuizFileEntry) quizDetail.elementAt(k)).getResDate());
									q.setQuizName(quizName);
									q.setMaxTime(maxTime);
									q.setResDate(resdate);
								}
								finalQuizList.add(q);
							}
						  /*String questionSettingPath=quizid1+"_QuestionSetting.xml";
							QuizMetaDataXmlReader quesmetadata=null;
							quesmetadata = new QuizMetaDataXmlReader(filePath+"/"+quizid1+"/"+questionSettingPath);
							HashMap maxMarksQuestion = quesmetadata.getQuizQuestionNoMarks(quesmetadata,quizid1);
							int insertedMarksQuiz =((Integer)maxMarksQuestion.get("marks"));
							int insertedQuestionQuiz = ((Integer)maxMarksQuestion.get("noQuestion"));
							 //===========================================
							q.setMaxMarks(String.valueOf(insertedMarksQuiz));
							q.setQuestionNumber(String.valueOf(insertedQuestionQuiz));
							quizDetail = quizmetadata.getQuiz_Detail(q.getQuizID());
							for(int k=0;k<quizDetail.size();k++){
								String quizName = (((QuizFileEntry) quizDetail.elementAt(k)).getQuizName());
								String maxTime = (((QuizFileEntry) quizDetail.elementAt(k)).getMaxTime());
								q.setQuizName(quizName);
								q.setMaxTime(maxTime);
							}
							finalQuizList.add(q);*/
						}
					/*	counter++;
						ErrorDumpUtil.ErrorLog("counter is"+counter);
						context.put("quizList",finalQuizList);*/
					}//if
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattemptyet",LangFile));
					}
				}//else
			}//else
		}
		counter++;
		ErrorDumpUtil.ErrorLog("counter is"+counter);
		context.put("quizList",finalQuizList);
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);                         }

		}catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in student_quiz ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}
}
