package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Quiz_Score.java	
 *
 *  Copyright (c) 2010 MHRD, DEI Agra.
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
 *   Contributors: Members of MHRD, DEI Agra.
 */

//java
import java.util.Vector;
import java.util.HashMap;
import java.io.File;
import java.lang.Math;

//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;

//brihaspati
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
/**
 *   This class is used to show score of student after attempting the quiz
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Quiz_Score extends SecureScreen{
//	MultilingualUtil mu=new MultilingualUtil();

	public void doBuildTemplate( RunData data, Context context ){
		ParameterParser pp=data.getParameters();
		String file=data.getUser().getTemp("LangFile").toString();
		try{
			//			String stat = pp.getString("status");			
			//			context.put("status",stat);
			User user=data.getUser();
			String uname=user.getName();
			String courseid=(String)user.getTemp("course_id");
			String uid = "";
			String role=(String)user.getTemp("role");
			context.put("role",role);
			String type1=pp.getString("type","");
			context.put("type",type1);
			ErrorDumpUtil.ErrorLog("\n role is :"+role+" and type is :"+type1);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			ErrorDumpUtil.ErrorLog("\n quiz name & quiz id is :"+quizName +" : "+quizID);
			String maxTime="";
			String maxMarks="";
			String maxQuestion="";
			if(role.equalsIgnoreCase("instructor")){
				context.put("setVisible","hidden");
				String quizPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/");
				String quizXmlPath = "Quiz.xml";	
				File quizFile= new File(quizPath+"/"+quizXmlPath);
				Vector<QuizFileEntry> quizDetail =new Vector<QuizFileEntry>();
				
				if(!quizFile.exists()){
					
				}
				else{
					String questionSettingPath=quizID+"_QuestionSetting.xml";
					QuizMetaDataXmlReader quesmetadata=null;
					quesmetadata = new QuizMetaDataXmlReader(quizPath+"/"+quizID+"/"+questionSettingPath);
					HashMap<String,Integer> maxMarksQuestion = quesmetadata.getQuizQuestionNoMarks(quesmetadata,quizID);
					maxMarks =(String.valueOf(maxMarksQuestion.get("marks")));
					maxQuestion = (String.valueOf(maxMarksQuestion.get("noQuestion")));
					ErrorDumpUtil.ErrorLog("max marks and max questions"+maxMarks+" ; "+maxQuestion);
					QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizPath+"/"+quizXmlPath);
					quizDetail = quizmetadata.getQuiz_Detail(quizID);
					if(quizDetail==null || quizDetail.size()==0){
						data.setMessage(MultilingualUtil.ConvertedString("brih_deletequizEntry",file));						
					}
					else{
						for(QuizFileEntry a:quizDetail){
							 maxTime = a.getMaxTime();
//							 maxMarks = a.getMaxMarks();
//							 maxQuestion = a.getnoQuestion();
							 ErrorDumpUtil.ErrorLog("\n max time & max marks,max question is :"+maxTime);
						}
					}
				}				
			}
			else if(role.equalsIgnoreCase("student")){
				context.put("setVisible","visible");				
				maxTime=pp.getString("maxTime","");				
				maxMarks=pp.getString("maxMarks","");				
				maxQuestion=pp.getString("maxQuestion","");
				//this code block for general quiz not for practice quiz
				String marksQuestions = pp.getString("maxMarksQuestion","");
				String temp[] = marksQuestions.split(",");
				if(maxMarks.isEmpty()){
					maxMarks = temp[1];
					maxQuestion = temp[2];
				}
				//==========================================================
					String filePath1=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID);
					String type = "";
					String check = "y";
            				String quizSettingPath=quizID+"_QuestionSetting.xml";
					File file1=new File(filePath1+"/"+quizSettingPath);
            				QuizMetaDataXmlReader quizmetadata1=null;
					Vector collect=new Vector();
					if(file1.exists()){
						quizmetadata1=new QuizMetaDataXmlReader(filePath1+"/"+quizSettingPath);	
						collect=quizmetadata1.getQuizQuestionDetail(quizID);		
						if(collect!=null && collect.size()!=0){
								for(int i=0;i<collect.size();i++){
										type=((QuizFileEntry)collect.elementAt(i)).getQuestionType();
										ErrorDumpUtil.ErrorLog("question type "+type);
										if((type.equals("sat")) ||(type.equals("lat"))){
												check = "n";
										}
								}
						}
					}
					if(check.equals("n")){
						context.put("setVisible","visible");
					}
					else{
						context.put("setVisible","hidden");
					}
			}
			context.put("maxTime",maxTime);	
			context.put("maxMarks",maxMarks);
			context.put("maxQuestion",maxQuestion);
			String answerSheetFlag = pp.getString("answerSheetFlag","no");
			context.put("answerSheetFlag",answerSheetFlag);
			ErrorDumpUtil.ErrorLog("answer sheet flag"+answerSheetFlag);
			Double passingMarks=0.0;			
			Double passingPercentage = 33.0;
			String finalResult="";
			ErrorDumpUtil.ErrorLog("max marks "+maxMarks);
			passingMarks = (Double.parseDouble(maxMarks)/100)*passingPercentage;
			ErrorDumpUtil.ErrorLog("passing marks of student"+passingMarks);
			
			context.put("passingMarks",Math.round(passingMarks));
			Vector answerDetail=new Vector();
			int studentMarks=0;
			//									
			//			if(QuizID.equals("")) {
			//				context.put("checkedQuiz","Uncheked");
			//				return;
			//			}
			//			context.put("checkedQuiz","cheked");

			String quizAnswerPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);
			if(role.equalsIgnoreCase("instructor")){
				String studentLoginName=pp.getString("studentLoginName","");
				context.put("studentLoginName",studentLoginName);
				uid=Integer.toString(UserUtil.getUID(studentLoginName));
				ErrorDumpUtil.ErrorLog("student login name :"+uid);
			}
			else if(role.equalsIgnoreCase("student")){
				context.put("studentLoginName",uname);
				//uid=Integer.toString(UserUtil.getUID(studentLoginName));	
				uid=Integer.toString(UserUtil.getUID(uname));			
			}
			String quizAnswerFile = uid+".xml";						
			File answerFile= new File(quizAnswerPath+"/"+quizAnswerFile);
			ErrorDumpUtil.ErrorLog("answer file path :"+answerFile.getPath());
			if(!answerFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
				return;
			}
			else{
				QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizAnswerPath+"/"+quizAnswerFile);
				answerDetail = quizmetadata.getFinalAnswer();
				ErrorDumpUtil.ErrorLog("after answer detail fetching :"+answerDetail);
				if(answerDetail==null || answerDetail.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
					return;
				}
				else{
					context.put("answerDetail",answerDetail);
					for(int i=0;i<answerDetail.size();i++){
						int studentMark = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getAwardedMarks());					
						studentMarks +=studentMark;
					}
					ErrorDumpUtil.ErrorLog("total marks of student"+studentMarks);
					context.put("studentMarks",studentMarks);
					String percentageScore = String.valueOf((studentMarks*100)/(Integer.parseInt(maxMarks)));
					context.put("percentageScore",percentageScore);
					if(Integer.parseInt(percentageScore)>=passingPercentage)
						finalResult="Pass";
					else
						finalResult="Fail";
					context.put("finalResult",finalResult);
				}			

			}											
			/**
                         *Time calculaion for how long user use this page.
                         */
			 int userid=UserUtil.getUID(user.getName());
                         if((role.equals("student")) || (role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(userid,eid);
                         }

		}	
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in detail Score Quiz file!!"+ex);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception",file));
		}
	}
}


