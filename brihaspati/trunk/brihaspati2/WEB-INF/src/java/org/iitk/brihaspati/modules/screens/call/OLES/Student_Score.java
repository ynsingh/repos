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
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils. MailNotificationThread;
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

			String count = pp.getString("count","");
			ErrorDumpUtil.ErrorLog("inside Student_Score count is:"+count);
			if(count.isEmpty()){
				count=(String)user.getTemp("count");
			}			
			String type = pp.getString("type","");
			context.put("type",type);
			ErrorDumpUtil.ErrorLog("type is "+type);
			if(type.isEmpty())
				count="3";
			else
				count = "4";
			ErrorDumpUtil.ErrorLog("type is count"+count);
			context.put("tdcolor",count);
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizPath="/Quiz.xml";  
			String scorePath="/score.xml";
//			String questionSettingPath=quizID+"_QuestionSetting.xml";
			ErrorDumpUtil.ErrorLog("type is count 2"+count);
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector attemptedQuizList=new Vector();
//			Vector finalQuizList=new Vector();
			Vector futureQuizList = new Vector();
			QuizMetaDataXmlReader quizmetadata=null;			
						
//			if(!type.isEmpty()){
//				if(!file.exists()){
//					data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));									
//				}
//				else{
//					context.put("isFile","exist");	
//					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
//					quizList=quizmetadata.getPracticeQuiz_Detail();
//					if(quizList!=null){
//						if(quizList.size()!=0){
//							context.put("quizList",quizList);	              
//						}
//						else
//							data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));
//					}
//					else
//						data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));
//				}	
//			}
//			else{
				ErrorDumpUtil.ErrorLog("inside else");
				if(!file.exists()){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));				
				}
				else{
					ErrorDumpUtil.ErrorLog("inside inner else 1");
					context.put("isFile","exist");
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
//					quizList=quizmetadata.readyToAttemptQuiz();
					futureQuizList = quizmetadata.listFutureQuiz();
					ErrorDumpUtil.ErrorLog("inside inner else 2::"+futureQuizList.size());
//					if(futureQuizList==null || futureQuizList.size()==0){
					ErrorDumpUtil.ErrorLog("after future quiz list"+futureQuizList);
						context.put("futureQuizList",futureQuizList)
//						return;
//					}
					;
//					else{
						
//					}					
					ErrorDumpUtil.ErrorLog("after adding to context");
					File scoreFile=new File(filePath+"/"+scorePath);
					ErrorDumpUtil.ErrorLog("inside inner else");
//					if(quizList==null || quizList.size()==0){
//						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
//						return;
//					}
					if(!scoreFile.exists()){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
					}
					else{
						quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+scorePath);
						attemptedQuizList=quizmetadata.getFinalScore(user_id);
//						String quizid,userid,quizid1;
//						boolean found = false;
//						ErrorDumpUtil.ErrorLog("The value of attempted quiz list is::"+attemptedQuizList);
//					if(quizList!=null && quizList.size()!=0){
						Vector quizDetail = new Vector();
						Vector<QuizFileEntry> finalQuizList = new Vector();
						quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
						if(attemptedQuizList!=null && attemptedQuizList.size()!=0){
//							for(int i=0;i<quizList.size();i++){
								for(int j=0;j<attemptedQuizList.size();j++){
//									quizid = (((QuizFileEntry) quizList.elementAt(i)).getQuizID());
									QuizFileEntry q = (QuizFileEntry)attemptedQuizList.get(j);
									ErrorDumpUtil.ErrorLog("quiz id "+q.getQuizID()+"score:"+q.getScore());									
									String quizid1 = q.getQuizID();
									
									//==========================================
									    String questionSettingPath=quizid1+"_QuestionSetting.xml";
										QuizMetaDataXmlReader quesmetadata=null;
										quesmetadata = new QuizMetaDataXmlReader(filePath+"/"+quizid1+"/"+questionSettingPath);
										HashMap maxMarksQuestion = quesmetadata.getQuizQuestionNoMarks(quesmetadata,quizid1);
										int insertedMarksQuiz =((Integer)maxMarksQuestion.get("marks"));
										int insertedQuestionQuiz = ((Integer)maxMarksQuestion.get("noQuestion"));
										ErrorDumpUtil.ErrorLog("max marks and max questions"+insertedMarksQuiz+" ; "+insertedQuestionQuiz);
//										context.put("maxMarks",insertedMarksQuiz);
//										context.put("maxQuestion",insertedQuestionQuiz);
									 //===========================================
										q.setMaxMarks(String.valueOf(insertedMarksQuiz));
										q.setQuestionNumber(String.valueOf(insertedQuestionQuiz));
									quizDetail = quizmetadata.getQuiz_Detail(q.getQuizID());
									ErrorDumpUtil.ErrorLog("quiz detail size "+quizDetail.size());
									for(int k=0;k<quizDetail.size();k++){
										String quizName = (((QuizFileEntry) quizDetail.elementAt(k)).getQuizName());
										String maxTime = (((QuizFileEntry) quizDetail.elementAt(k)).getMaxTime());
										ErrorDumpUtil.ErrorLog("quiz name "+quizName);
										q.setQuizName(quizName);
										q.setMaxTime(maxTime);
										ErrorDumpUtil.ErrorLog("max marks & questions "+q.getMaxMarks()+" : "+q.getQuestionNumber());
//										q.setMarksPerQuestion(markperquestion);
									}
									finalQuizList.add(q);
									ErrorDumpUtil.ErrorLog("final quiz list "+finalQuizList.size());
								}
								ErrorDumpUtil.ErrorLog("final quiz list "+finalQuizList.size());
								context.put("quizList",finalQuizList);
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattemptyet",LangFile));
						}
//									userid = (((QuizFileEntry) attemptedQuizList.elementAt(j)).getUserID());
//									if(quizid.equalsIgnoreCase(quizid1)){
//										if(userid.equalsIgnoreCase(user_id)){
//											found = true;
//											break;
//										}
//										else{
//											found = false;
//											break;													
//										}
//									}
//									else
//										found = false;
//								}//end for
//								if(!found){
//									QuizFileEntry q = (QuizFileEntry)quizList.get(i);
//									finalQuizList.add(q);
//									ErrorDumpUtil.ErrorLog("size of final quiz list ::"+finalQuizList);
//								}
//							}//end outer for
//							if(finalQuizList!=null && finalQuizList.size()!=0){
//								context.put("quizList",finalQuizList);
//							}
//							else
//								data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
//						}									
//						else
//							context.put("quizList",quizList);								
//					}
//					else
//						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
				}
				}
//			}          
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				MailNotificationThread.getController().CourseTimeSystem(userid);
                         }
  		
		}catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in student_quiz ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}
}

