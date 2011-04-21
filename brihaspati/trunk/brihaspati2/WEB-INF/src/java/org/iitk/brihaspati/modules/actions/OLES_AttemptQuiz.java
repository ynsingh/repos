package org.iitk.brihaspati.modules.actions;
/*
 * @(#)OLES_AttemptQuiz.java	
 *
 *  Copyright (c) 2010 DEI Agra. 
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
//JDK
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;

/**
 * This Action class is used to Generate Attempt quiz,practice quiz, score card and grade card module of online examination system 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a> 
 */
public class OLES_AttemptQuiz extends SecureAction{

	String CoursePath=TurbineServlet.getRealPath("/Courses");
	private String crsId=new String();
	private String LangFile=new String();	
	/**
	 * This method is invoked when no button corresponding to 
	 * Action is found
	 * @param data RunData
	 * @param context Context
	 * @exception Exception, a generic exception
	 */
	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		context.put("actionName",action);		
		if(action.equals("eventSubmit_attemptQuiz"))
			attemptQuiz(data,context);
		else if(action.equals("eventSubmit_saveAnswerQuiz"))
			saveAnswerQuiz(data,context);
		else if(action.equals("eventSubmit_refreshQuiz"))
			refreshQuiz(data,context);	
		else if(action.equals("eventSubmit_saveFinalQuiz"))
			saveFinalQuiz(data,context);
		else if(action.equals("eventSubmit_attemptPracticeQuiz"))
			attemptPracticeQuiz(data,context);
		else if(action.equals("eventSubmit_savePracticeQuiz"))
			savePracticeQuiz(data,context);		
		else if(action.equals("eventSubmit_showScoreQuiz"))
			showScoreQuiz(data,context);
		else if(action.equals("eventSubmit_showReportCard"))
			showReportCard(data,context);
		else if(action.equals("eventSubmit_Evaluate"))
			evaluate(data,context);	
		else if(action.equals("eventSubmit_EvaluateQuestion"))
			evaluateQuestion(data,context);	
		else
			data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));				
	}
	
	/** This method get list of final questions in a shuffled mode to be attempted by student
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void attemptQuiz(RunData data, Context context){
		try{
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID="";
			String maxTime="";
			String quizIDTime = data.getParameters().getString("quizIDTime","");
			if(!quizIDTime.isEmpty()){
				String quizIDTimeArray[] = quizIDTime.split(",");				                               	
				quizID = quizIDTimeArray[0];        		
				maxTime = quizIDTimeArray[1];        						
			}
			else{
				quizID = data.getParameters().getString("quizID","");
			}
			ErrorDumpUtil.ErrorLog("\n maxTime "+maxTime);
			String courseid=(String)user.getTemp("course_id");  
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector"); 
			ErrorDumpUtil.ErrorLog("session question vector "+questionVector);
			String quizFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String quizQuestionPath=quizID+"_Questions.xml"; 
			String userQuestionPath=uid+".xml";
			ErrorDumpUtil.ErrorLog("path to search "+quizFilePath+"/"+quizQuestionPath);
			File quizQuestionFile=new File(quizFilePath+"/"+quizQuestionPath);
			Vector quizQuestionList=new Vector();
			QuizMetaDataXmlReader quizQuestionMetaData=null;
			if(questionVector==null || questionVector.size()==0){						
				if(!quizQuestionFile.exists()){
					ErrorDumpUtil.ErrorLog("before first message");
					data.setMessage("No questions are stored to attempt");				
				}
				else{
					quizQuestionMetaData=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionPath);				
					quizQuestionList=quizQuestionMetaData.getInsertedQuizQuestions();
					if(quizQuestionList!=null && quizQuestionList.size()!=0){							
						Collections.shuffle(quizQuestionList);	
						ErrorDumpUtil.ErrorLog("first time in oles_quiz after shuffling");
						context.put("quizQuestionList",quizQuestionList);   
						data.getUser().setTemp("questionvector",quizQuestionList);									
					}
					else{
						ErrorDumpUtil.ErrorLog("before third message"+quizQuestionList.size());
						data.setMessage("No questions are stored to attempt");
					}            
				}
			}
			else{
				ErrorDumpUtil.ErrorLog("\n vector after refresh "+questionVector);
				context.put("quizQuestionList",questionVector); 
			}			
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:attemptQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is executed when user click on save answer button while attempting the quiz
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void saveAnswerQuiz(RunData data, Context context){
		try{
			User user=data.getUser();
			String uname=user.getName();

			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID=data.getParameters().getString("quizID","");		
			String quesID=data.getParameters().getString("quesID","");		
			String fileName=data.getParameters().getString("fileName","");		
			String answer=data.getParameters().getString("finalAnswer","");	
			String quesType=data.getParameters().getString("quesType","");	
			ErrorDumpUtil.ErrorLog("\n inside save answer quiz \n  "+quizID+"\n"+quesID+"\n"+fileName+"\n"+answer);
			String courseid=(String)user.getTemp("course_id"); 
			String markPerQues = data.getParameters().getString("markPerQues","");
			ErrorDumpUtil.ErrorLog("\n inside save answer quiz marks \n  "+markPerQues);  
			String type = data.getParameters().getString("type","");
			ErrorDumpUtil.ErrorLog("\n inside  type \n  "+type);  	
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String answerPath=uid+".xml"; 
			ErrorDumpUtil.ErrorLog("path to search "+answerFilePath+"/"+answerPath);
			File quizAnswerFile=new File(answerFilePath+"/"+answerPath);
			if(type.equalsIgnoreCase("practice"))
				QuizMetaDataXmlWriter.xmlwriteFinalAnswerPractice(answerFilePath,answerPath,data);  
			else
				QuizMetaDataXmlWriter.xmlwriteFinalAnswer(answerFilePath,answerPath,data);
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:saveAnswerQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}



	/** This method is executed when user click on submit quiz button during quiz attempt
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void saveFinalQuiz(RunData data, Context context){
		try{
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID=data.getParameters().getString("quizID","");		
			String courseid=(String)user.getTemp("course_id"); 
			//======================functionality to add unattended question in userid.xml
			addUnattendedQuestions(data,context);
			//======================================================================
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String answerPath="score.xml"; 
			ErrorDumpUtil.ErrorLog("path to search "+answerFilePath+"/"+answerPath);
			QuizMetaDataXmlWriter.xmlwriteFinalScore(answerFilePath,answerPath,data);
			user.setTemp("count","1");
			ErrorDumpUtil.ErrorLog("count in session :"+user.getTemp("count"));
			user.setTemp("questionvector",new Vector());
			ErrorDumpUtil.ErrorLog("session question vector "+user.getTemp("questionvector"));
			user.setTemp("timerValue","");
			data.setScreenTemplate("call,OLES,Student_Quiz.vm");
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:saveFinalQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is executed when user click on a question to view it and timer value must be updated 
	 * through session variable to  maintain the counter
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void refreshQuiz(RunData data, Context context){
		ParameterParser pp=data.getParameters();
		try{
			ErrorDumpUtil.ErrorLog("inside oles_quiz");
			User user=data.getUser();	
			String timerValue = pp.getString("timerValue","");
			String maxTime = pp.getString("maxTime","");
			context.put("maxTime",maxTime);
			String timerValueSession = (String)user.getTemp("timerValue"); 
			ErrorDumpUtil.ErrorLog("\n timer value and timersession"+timerValue+":"+timerValueSession);
			ErrorDumpUtil.ErrorLog("max time is "+maxTime);
			if(timerValue==null || timerValue.equalsIgnoreCase("")){
				if(timerValueSession==null || timerValueSession.equalsIgnoreCase("")){
					context.put("timerValue",maxTime);
					ErrorDumpUtil.ErrorLog("inside if");
				}
				else{
					ErrorDumpUtil.ErrorLog("inside inner else");
					context.put("timerValue",timerValueSession);
				}
			}
			else{
				context.put("timerValue",timerValue);
				user.setTemp("timerValue",timerValue);
				ErrorDumpUtil.ErrorLog("inside outer else"+user.getTemp("timerValue"));
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:refreshQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	
	/** This method get list of final questions of a practice quiz in a shuffled mode to be attempted by student
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void attemptPracticeQuiz(RunData data, Context context){
		try{
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));
			String courseid=(String)user.getTemp("course_id"); 
			String courseAlias=CourseUtil.getCourseAlias(courseid);
			ErrorDumpUtil.ErrorLog("\n course alias is "+courseAlias);
			String courseArray[]=courseid.split("_");
			int len = courseAlias.length();
			int len1 = courseArray[0].length();
			String instructorName = (courseid.substring(len, len1)).trim();
			ErrorDumpUtil.ErrorLog("index "+courseid.substring(len, len1));
			String quizID="";
			String maxTime="";
			String quizIDTime = data.getParameters().getString("quizIDTime","");
			if(!quizIDTime.isEmpty()){
				String quizIDTimeArray[] = quizIDTime.split(",");				                               	
				quizID = quizIDTimeArray[0];        		
				maxTime = quizIDTimeArray[1];        						
			}
			else{
				quizID = data.getParameters().getString("quizID","");
			}
			ErrorDumpUtil.ErrorLog("\n maxTime "+maxTime);

			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");  
			//========================================================================
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			QuizMetaDataXmlReader quizmetadata=null;
			Vector allQuizSetting=new Vector();        				
			quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+questionSettingPath);	

			if(questionVector==null || questionVector.size()==0){
				allQuizSetting=quizmetadata.getQuizQuestionDetail();
				if(allQuizSetting==null || allQuizSetting.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquiz",LangFile));
					return;
				}	                
				String topicName,questionType,questionLevel,fileName,noquestion,markperquestion; 
				String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+instructorName+"/"+courseid);
				QuizMetaDataXmlReader questionReader=null;
				Vector<QuizFileEntry> question=new Vector<QuizFileEntry>();
				Vector<QuizFileEntry> finalQues = new Vector<QuizFileEntry>();
				Set finalQuestion = new TreeSet();
				boolean found = false;
				int ans = 0;
				if(allQuizSetting!=null & allQuizSetting.size()!=0){	        		
					for(int j=0;j<allQuizSetting.size();j++){
						topicName = (((QuizFileEntry) allQuizSetting.elementAt(j)).getTopic());
						questionLevel = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionLevel());
						questionType = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionType());
						noquestion = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionNumber());
						markperquestion = (((QuizFileEntry) allQuizSetting.elementAt(j)).getMarksPerQuestion());
						fileName = topicName +"_"+questionLevel+"_"+questionType+".xml";
						questionReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+fileName);
						question = questionReader.getRandomQuizQuestions(questionType);

						for(int i=0;i<Integer.parseInt(noquestion);i++){
							ErrorDumpUtil.ErrorLog("no of question"+noquestion+"loop no:"+i);
							Collections.shuffle(question);
							for(int k=0;k<question.size();k++){  
								found = false;
								QuizFileEntry q = question.get(k);
								ErrorDumpUtil.ErrorLog("question and answer"+q.getQuestion()+"answer:"+q.getAnswer());
								q.setFileName(fileName);
								q.setMarksPerQuestion(markperquestion);
								q.setQuestionType(questionType);
								Iterator it = finalQuestion.iterator();
								while (it.hasNext()) {
									QuizFileEntry a = (QuizFileEntry) it.next();
									String que = a.getQuestion();
									String an = a.getAnswer();
									ErrorDumpUtil.ErrorLog("\n ques and answer in treeset "+que +": "+an);
									if (que.equals(q.getQuestion())&& an.equals(q.getAnswer())){ // Are they exactly the same instance?							    	 
										ErrorDumpUtil.ErrorLog("inside exactly same");
										found=true;
										break;
									}
								}
								ErrorDumpUtil.ErrorLog("\n found "+found);
								if(found){//question bank element in the treeset is already present
									continue;
								}
								else{
									finalQuestion.add(q);
									question.removeElementAt(k);
									question.trimToSize();
									break;
								}
							}
						}		            				            				            						     	
					}
					if(finalQuestion==null || finalQuestion.size()==0){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
						return;
					}	                
					finalQues.addAll(finalQuestion);
					if(finalQues.size()!=0){                   	
						context.put("quizQuestionList",finalQues);   
						data.getUser().setTemp("questionvector",finalQues);
					}		        
				}
			}
			else{
				ErrorDumpUtil.ErrorLog("\n vector after refresh "+questionVector);
				context.put("quizQuestionList",questionVector); 
			}					
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:attemptPracticeQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to set the practice quiz scores on a new page
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void savePracticeQuiz(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			User user=data.getUser();
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			String maxTime=pp.getString("maxTime","");
			context.put("maxTime",maxTime);	
			String maxMarks=pp.getString("maxMarks","");
			context.put("maxMarks",maxMarks);	
			String maxQuestion=pp.getString("maxQuestion","");
			context.put("maxQuestion",maxQuestion);		
			ErrorDumpUtil.ErrorLog("quiz id from drop down "+quizID + " "+quizName);
			//======================functionality to add unattended question in userid.xml
			addUnattendedQuestions(data,context);			
			//======================================================================
			user.setTemp("questionvector",new Vector());
			//timerValue session variable is cleared before submitting the quiz. now if without logout a student will attempt another 
			//quiz a new timer value session variable is set
			user.setTemp("timerValue","");
			//=======freshQuiz session variable is set to yes so that when same is tried again its previous answers are cleared
			//and new answers are saved
			user.setTemp("freshQuiz","yes");
			data.setScreenTemplate("call,OLES,Quiz_Score.vm");						
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:savePracticeQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to set the general quiz scores on a new page
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void showScoreQuiz(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{			
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);				
			data.setScreenTemplate("call,OLES,Quiz_Score.vm");						
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:showScoreQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	/** This method is responsible to add all the unattended questions in the userID.xml
	 * which will be useful while showing answer sheet
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void addUnattendedQuestions(RunData data,Context context){
		ParameterParser pp = data.getParameters();
		try{
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));		
			String courseid=(String)user.getTemp("course_id"); 
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector"); 
			String studentAnswerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String studentAnswerPath=uid+".xml"; 
			ErrorDumpUtil.ErrorLog("path to search "+studentAnswerFilePath+"/"+studentAnswerPath);
			File studentAnswerFile=new File(studentAnswerFilePath+"/"+studentAnswerPath);
			QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(studentAnswerFilePath+"/"+studentAnswerPath);
			Vector attemptedQuestion = quizreader.getFinalAnswer();
			if(attemptedQuestion==null || attemptedQuestion.size()==0){}
			else{
				ErrorDumpUtil.ErrorLog("size of finally attempted answer :"+attemptedQuestion.size()+" temp size :"+questionVector.size());
				if(attemptedQuestion.size()==questionVector.size()){
					ErrorDumpUtil.ErrorLog("all questions are attempted "); 
					questionVector.removeAllElements();
				}
				else{
					for(int i=0;i<attemptedQuestion.size();i++){
						ErrorDumpUtil.ErrorLog("inside outer for ");
						for(int j=0;j<questionVector.size();j++){
							ErrorDumpUtil.ErrorLog("inside inner for ");
							QuizFileEntry q1 = (QuizFileEntry)attemptedQuestion.get(i);
							QuizFileEntry q2 = (QuizFileEntry)questionVector.get(j);
							String quesID1 = q1.getQuestionID();
							String quesID2 = q2.getQuestionID();
							String fileName1 = q1.getFileName();
							String fileName2 = q2.getFileName();
							ErrorDumpUtil.ErrorLog("quizid filenanme "+quesID1 +quesID2+fileName1+fileName2);
							if(quesID1.equals(quesID2) && fileName1.equals(fileName2)){
								questionVector.removeElementAt(j);
								questionVector.trimToSize();
								break;
							}
						}
					}
				}
			}
			ErrorDumpUtil.ErrorLog("Revised size of questionVector "+questionVector.size()); 
			XmlWriter xmlWriter=new XmlWriter(studentAnswerFilePath+"/"+studentAnswerPath);
			for(int i=0;i<questionVector.size();i++){
				QuizFileEntry q = (QuizFileEntry)questionVector.get(i);
				String quesid=q.getQuestionID();
				String filename=q.getFileName();										
				String question = q.getQuestion();
				String realAnswer = q.getAnswer();
				String markPerQues = q.getMarksPerQuestion();
				int lastIndex = filename.lastIndexOf("_");
				int len = filename.length();
				String quesType = filename.substring(lastIndex+1, lastIndex+4);
				ErrorDumpUtil.ErrorLog("\nquestion type "+quesType);
				String studentAnswer="";
				String awardedMarks = "0";
				String option1,option2,option3,option4;
				option1=option2=option3=option4="";
				int seq=-1;
				if(quesType.equalsIgnoreCase("mcq")){
					option1=q.getOption1();
					option2=q.getOption2();
					option3=q.getOption3();
					option4=q.getOption4();
				}									
				QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesid,filename,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,quesType,seq);
				ErrorDumpUtil.ErrorLog("after append answer in writer");
				xmlWriter.writeXmlFile();						
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:addUnattendedQuestions !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	
	/** This method is responsible to show the report card
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void showReportCard(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{		
			ErrorDumpUtil.ErrorLog("\n inside action method");
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			ErrorDumpUtil.ErrorLog("quiz id and quiz name :"+quizName+" : "+quizID);
			data.setScreenTemplate("call,OLES,Report_Card.vm");						
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:showReportCard !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void evaluate(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{	
			String cid=(String)data.getUser().getTemp("course_id");
			ErrorDumpUtil.ErrorLog("\n inside evaluate method");
			String quizID=pp.getString("quizID","");		
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			ErrorDumpUtil.ErrorLog("quiz id and student login name :"+quizID+" : "+uid);
			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String scoreXml="score.xml";
			File scoreFile = new File(scoreFilePath+"/"+scoreXml);
			ErrorDumpUtil.ErrorLog("score file path :"+scoreFile.getPath());
			int seq = 0;
			if(!scoreFile.exists()){
				data.setMessage("This Quiz is not Attempted by this Student !!");
				return;
			}
			else{
				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
				seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
				ErrorDumpUtil.ErrorLog("sequence number is :"+seq);
				if(seq==-1)
					data.setMessage("This Quiz is not Attempted by this Student !!");
				else
					data.setScreenTemplate("call,OLES,Evaluate_Quiz.vm");					
			}									
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void evaluateQuestion(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{	
			String cid=(String)data.getUser().getTemp("course_id");
			ErrorDumpUtil.ErrorLog("\n inside evaluate method");
			String quizID=pp.getString("quizID","");
			String quizName=pp.getString("quizName","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			ErrorDumpUtil.ErrorLog("quiz id and student login name :"+quizID+" : "+uid);
			String quesID=pp.getString("quesID","");
			String fileName=pp.getString("fileName","");
			ErrorDumpUtil.ErrorLog("question id and filename :"+quesID+" : "+fileName);
//			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String answerFilePath = TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/"+quizID+"/");
//			String scoreXml="score.xml";
			String answerPath = uid+".xml";
//			File scoreFile = new File(scoreFilePath+"/"+scoreXml);
			File answerFile = new File(answerFilePath+"/"+answerPath);
			ErrorDumpUtil.ErrorLog("answer file path :"+answerFile.getPath());
//			int seq = 0;
			if(!answerFile.exists()){
				data.setMessage("This Quiz is not Attempted by this Student !!");
				return;
			}
			else{
//				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
				QuizMetaDataXmlWriter.xmlwriteEvaluateMarks(answerFilePath,answerPath,data);
			}
//			if(!scoreFile.exists()){
//				data.setMessage("This Quiz is not Attempted by this Student !!");
//				return;
//			}
//			else{
//				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
//				seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
//				ErrorDumpUtil.ErrorLog("sequence number is :"+seq);
				//if(seq==-1)
					//data.setMessage("This Quiz is not Attempted by this Student !!");
				//else
					//data.setScreenTemplate("call,OLES,Evaluate_Quiz.vm");					
//			}									
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}


}	                           
