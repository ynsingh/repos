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
import java.util.Calendar;
import java.text.SimpleDateFormat;
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
		else if(action.equals("eventSubmit_revaluate"))
			reevaluate(data,context);
		else if(action.equals("eventSubmit_EvaluateQuestion"))
			evaluateQuestion(data,context);	
		else if(action.equals("eventSubmit_Result"))
			result(data,context);	
		else if(action.equals("eventSubmit_EvaluateQuestionDone"))
			evaluateQuestionDone(data,context);
		else if(action.equals("eventSubmit_answerSheet"))
			answerSheet(data,context);
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
					data.setMessage(MultilingualUtil.ConvertedString("brih_quizwithoutquestion",LangFile));				
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
						data.setMessage(MultilingualUtil.ConvertedString("brih_quizwithoutquestion",LangFile));	
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
			LangFile=(String)data.getUser().getTemp("LangFile");
			ErrorDumpUtil.ErrorLog("inside oles_quiz");
			User user=data.getUser();	
			String timerValue = pp.getString("timerValue","");
			String maxTime = pp.getString("maxTime","");
			context.put("maxTime",maxTime);
			String timerValueSession = (String)user.getTemp("timerValue"); 
			ErrorDumpUtil.ErrorLog("\n timer value and timersession"+timerValue+":"+timerValueSession);
			ErrorDumpUtil.ErrorLog("max timeventSubmit_EvaluateQuestionDonee is "+maxTime);
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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

			User user=data.getUser();
			LangFile=(String)data.getUser().getTemp("LangFile");
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String loginname=user.getName();
			String userID=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			ErrorDumpUtil.ErrorLog("UserID inside showScoreQuiz method  is"+ userID);
			String examPath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizSettingPath=quizID+"_QuestionSetting.xml";
			String scoreXml="score.xml";
			String quizPath2="/Quiz.xml";       
			File file2=new File(examPath+"/"+quizPath2);
			File file=new File(examPath+"/"+scoreXml);
			File file1=new File(examPath+"/"+quizID+"/"+quizSettingPath);
			QuizMetaDataXmlReader quizmetadata=null;
			Vector scoreCollect=new Vector();
			Vector quizDetail=new Vector();
			Vector collect=new Vector();
			Date dt = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
			String curDate = sd.format(dt);
			String evaluate="",userId="",quizId="";
			String resDate = "";
			String type = "";
			String check = "y";
			boolean flag=false;
			
			if(file.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
				scoreCollect=quizmetadata.getFinalScore(userID);
				if(scoreCollect!=null && scoreCollect.size()!=0){
					for(int i=0;i<scoreCollect.size();i++){
						evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
						userId=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
						quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(evaluate!=null){
							ErrorDumpUtil.ErrorLog("inside not null"+evaluate+quizId);
							if(evaluate.equals("complete") && userID.equals(userId) && quizID.equals(quizId)){
								flag=true;
								break;
							}
							else{
								flag=false;
							}
						}
						else{
							flag=false;
						}
					}
				}
			}	
			if(file1.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+quizID+"/"+quizSettingPath);	
				collect=quizmetadata.getQuizQuestionDetail(quizID);		
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
			if(file2.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+quizPath2);				
				quizDetail=quizmetadata.getQuiz_Detail(quizID);
					if(quizDetail!=null){
						if(quizDetail.size()!=0){
							for(int i = 0; i<quizDetail.size(); i++){
								String resDate1 = ((QuizFileEntry) quizDetail.elementAt(i)).getResDate();
								String quizId1 = ((QuizFileEntry) quizDetail.elementAt(i)).getQuizID();
								if(quizId1.equals(quizID)){
									resDate=resDate1;
									ErrorDumpUtil.ErrorLog("resdate in oles_attemptquiz.java "+resDate+" : "+quizId);
									break;
								}
							}    						         
						}
					}
			}		
			if(check.equals("n")){
				if(resDate.compareTo(curDate)==0 || resDate.compareTo(curDate)==-1){
					if(flag){
						ErrorDumpUtil.ErrorLog("inside complete");
						data.setScreenTemplate("call,OLES,Quiz_Score.vm");
					}
					else{
						ErrorDumpUtil.ErrorLog("inside partial/Re-Evaluate");
						data.setMessage(MultilingualUtil.ConvertedString("brih_noevaluateQuiz",LangFile));
						data.setScreenTemplate("call,OLES,Student_Score.vm");
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_waitforresult",LangFile));
					data.setScreenTemplate("call,OLES,Student_Score.vm");
				}
			}			
			else{
				data.setScreenTemplate("call,OLES,Quiz_Score.vm");
			}						
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			ErrorDumpUtil.ErrorLog("\n inside action method");
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			ErrorDumpUtil.ErrorLog("quiz id and quiz name :"+quizName+" : "+quizID);
			String loginname=user.getName();
			String userID=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			ErrorDumpUtil.ErrorLog("UserID inside showScoreQuiz method  is"+ userID);
			String examPath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizSettingPath=quizID+"_QuestionSetting.xml";
			String scoreXml="score.xml";
			String quizPath2="/Quiz.xml";
			File file=new File(examPath+"/"+scoreXml);
			File file2=new File(examPath+"/"+quizPath2);
			File file1=new File(examPath+"/"+quizID+"/"+quizSettingPath);
			QuizMetaDataXmlReader quizmetadata=null;
			Vector scoreCollect=new Vector();
			Vector quizDetail=new Vector();
			Vector collect=new Vector();
			Date dt = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
			String curDate = sd.format(dt);
			String evaluate="",userId="",quizId="";
			String resDate = "";
			String type = "";
			String check = "y";
			boolean flag=false;
				
			if(file.exists()){
				ErrorDumpUtil.ErrorLog("inside file exist if");
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
				scoreCollect=quizmetadata.getFinalScore(userID);
				if(scoreCollect!=null && scoreCollect.size()!=0){
					ErrorDumpUtil.ErrorLog("inside file if 01111");
					for(int i=0;i<scoreCollect.size();i++){
						evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
						userId=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
						quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(evaluate!=null){
							ErrorDumpUtil.ErrorLog("inside not null"+evaluate+quizId);
							if(evaluate.equals("complete") && userID.equals(userId) && quizID.equals(quizId)){
								flag=true;
								break;
							}
							else{
								flag=false;
							}
						}
						else{
							flag=false;
						}
						
						ErrorDumpUtil.ErrorLog("inside for loop"+evaluate+userId);
					}
				}
			}
			if(file1.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+quizID+"/"+quizSettingPath);	
				collect=quizmetadata.getQuizQuestionDetail(quizID);		
				if(collect!=null && collect.size()!=0){
					for(int i=0;i<collect.size();i++){
						type=((QuizFileEntry)collect.elementAt(i)).getQuestionType();
						if((type.equals("sat")) ||(type.equals("lat"))){
							check = "n";
						}
					}
				}
			}
			if(file2.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+quizPath2);				
				quizDetail=quizmetadata.getQuiz_Detail(quizID);
				if(quizDetail!=null){
					if(quizDetail.size()!=0){
						for(int i = 0; i<quizDetail.size(); i++){
							String resDate1 = ((QuizFileEntry) quizDetail.elementAt(i)).getResDate();
							String quizId1 = ((QuizFileEntry) quizDetail.elementAt(i)).getQuizID();
							if(quizId1.equals(quizID)){
								resDate=resDate1;
								ErrorDumpUtil.ErrorLog("resdate in oles_attemptquiz.java "+resDate+" : "+quizId);
								break;
							}
						}    						         
					}
				}
			}		
			if(check.equals("n")){
				if(resDate.compareTo(curDate)==0 || resDate.compareTo(curDate)==-1){
					if(flag){
						ErrorDumpUtil.ErrorLog("inside complete");
						data.setScreenTemplate("call,OLES,Quiz_Score.vm");
					}
					else{
						ErrorDumpUtil.ErrorLog("inside partial/Re-Evaluate");
						data.setMessage(MultilingualUtil.ConvertedString("brih_noevaluateQuiz",LangFile));
						data.setScreenTemplate("call,OLES,Student_Score.vm");
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_waitforresult",LangFile));
					data.setScreenTemplate("call,OLES,Student_Score.vm");
				}
			}			
			else{
				data.setScreenTemplate("call,OLES,Quiz_Score.vm");
			}	
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:showReportCard !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void evaluate(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{	
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			ErrorDumpUtil.ErrorLog("\n inside evaluate method");
			String quizID=pp.getString("quizID","");		
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			ErrorDumpUtil.ErrorLog("quiz id and student login name :"+quizID+" : "+uid);
			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String scoreXml="score.xml";
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			Vector<QuizFileEntry> questionDetailVector=new Vector<QuizFileEntry>();
			File scoreFile = new File(scoreFilePath+"/"+scoreXml);
			ErrorDumpUtil.ErrorLog("score file path :"+scoreFile.getPath());
			int seq = 0;
			boolean flag=true;
			if(!scoreFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				QuizMetaDataXmlReader typeReader= new QuizMetaDataXmlReader(scoreFilePath+"/"+quizID+"/"+questionSettingPath);
				questionDetailVector=typeReader.getQuizQuestionDetail(quizID);
				if(questionDetailVector!=null && questionDetailVector.size()!=0){
					for(int i=0;i<questionDetailVector.size();i++){
						String quizQuestionType=((QuizFileEntry)questionDetailVector.elementAt(i)).getQuestionType();
						if(quizQuestionType.equalsIgnoreCase("sat")||quizQuestionType.equalsIgnoreCase("lat")){
							flag=false;
							break;
						}
						else{
							flag=true;
						}
					}
					if(flag){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noshtlngAnswer",LangFile));
						return;
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionToevaluate",LangFile));
					return;
				}
				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
				seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
				ErrorDumpUtil.ErrorLog("sequence number is :"+seq);
				if(seq==-1)
					data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
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
			LangFile=(String)data.getUser().getTemp("LangFile");
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
			String answerFilePath = TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/"+quizID+"/");
			String answerPath = uid+".xml";
			
			String evaluate=pp.getString("evaluate","partial");		
			ErrorDumpUtil.ErrorLog("inside attempt Quiz evaluate "+evaluate);
			File answerFile = new File(answerFilePath+"/"+answerPath);
			ErrorDumpUtil.ErrorLog("answer file path :"+answerFile.getPath());
			if(!answerFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				QuizMetaDataXmlWriter.xmlwriteEvaluateMarks(answerFilePath,answerPath,data,evaluate);

			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void reevaluate(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{	
			ErrorDumpUtil.ErrorLog("inside attempt Quiz evaluateQuestionDone !! ");
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			ErrorDumpUtil.ErrorLog("\n inside evaluateQuestionDone method");
			String quizID=pp.getString("quizID","");
			//String quizName=pp.getString("quizName","");
			String studentLoginName=pp.getString("studentLoginName","");
			ErrorDumpUtil.ErrorLog("quiz id and student login name :"+studentLoginName);
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			ErrorDumpUtil.ErrorLog("quiz id and student login name :"+quizID+" : "+uid);
			int seq=-1;
        	
    		String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
	        String scorePath="score.xml";
	        String usedTime="";
	        String quizid="";
	        String userID="";
	        int totalScore=0;
	        String evaluate=null;
	        QuizMetaDataXmlReader quizmMtaData=new QuizMetaDataXmlReader(scoreFilePath+"/"+scorePath); 
	        Vector scoreDetail = quizmMtaData.getDetailOfAlreadyInsertedScore(scoreFilePath,scorePath,quizID,uid);
	        XmlWriter xmlScoreWriter = null;
	        if(scoreDetail!=null && scoreDetail.size()!=0){
        		for(int i=0;i<scoreDetail.size();i++) {
        			usedTime = (((QuizFileEntry) scoreDetail.elementAt(i)).getUsedTime());
        			totalScore=Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getScore()));
        			userID=(((QuizFileEntry) scoreDetail.elementAt(i)).getUserID());
        			quizid=(((QuizFileEntry) scoreDetail.elementAt(i)).getQuizID());
        			seq = Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getID()));
        			evaluate=(((QuizFileEntry) scoreDetail.elementAt(i)).getEvaluate());
        		}  
        		ErrorDumpUtil.ErrorLog("used time ,totalScore,userID,quizID,evaluate,StrudentLoginName and seq are : "+usedTime+" : "+totalScore+" : "+userID+" : "+quizid+" : "+evaluate+" : "+uid+" : "+seq);        		
		}		
	        xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
	        if(uid.equalsIgnoreCase(userID) && quizID.equalsIgnoreCase(quizid)){
			
			if(evaluate!=null && evaluate.equalsIgnoreCase("complete")){
	        	evaluate="ReEvaluate";
	        	QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq,evaluate);
			data.setMessage(MultilingualUtil.ConvertedString("brih_successforReevaluation",LangFile));		
			data.setScreenTemplate("call,OLES,Student_Score.vm");	
							
			}
			else{
			data.setMessage(MultilingualUtil.ConvertedString("brih_noevaluateQuiz",LangFile));
			}
	        }
	        xmlScoreWriter.writeXmlFile();
	        	
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void result(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
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
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
				seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
				ErrorDumpUtil.ErrorLog("sequence number is :"+seq);
				if(seq==-1){
					data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
					return;
				}
				else{
					data.setScreenTemplate("call,OLES,Quiz_Score.vm");
//					data.setScreenTemplate("call,OLES,Evaluate_Quiz.vm");						
				}
			}									
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	public void evaluateQuestionDone(RunData data,Context context){
		
		ParameterParser pp = data.getParameters();
		try{	
			ErrorDumpUtil.ErrorLog("inside attempt Quiz evaluateQuestionDone !! ");
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			ErrorDumpUtil.ErrorLog("\n inside evaluateQuestionDone method");
			String quizID=pp.getString("quizID","");
			String type=pp.getString("type","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			ErrorDumpUtil.ErrorLog("quiz id and student login name and type :"+quizID+" : "+uid+" : "+type);
			int seq=-1;
    		String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
	        String scorePath="score.xml";
	        String usedTime="";
	        String quizid="";
	        String userID="";
	        int totalScore=0;
	        String evaluate=null;
	        QuizMetaDataXmlReader quizmMtaData=new QuizMetaDataXmlReader(scoreFilePath+"/"+scorePath); 
	        Vector scoreDetail = quizmMtaData.getDetailOfAlreadyInsertedScore(scoreFilePath,scorePath,quizID,uid);
	        XmlWriter xmlScoreWriter = null;
	        if(scoreDetail!=null && scoreDetail.size()!=0){
        		for(int i=0;i<scoreDetail.size();i++) {
        			usedTime = (((QuizFileEntry) scoreDetail.elementAt(i)).getUsedTime());
        			totalScore=Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getScore()));
        			userID=(((QuizFileEntry) scoreDetail.elementAt(i)).getUserID());
        			quizid=(((QuizFileEntry) scoreDetail.elementAt(i)).getQuizID());
        			seq = Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getID()));
        			evaluate=(((QuizFileEntry) scoreDetail.elementAt(i)).getEvaluate());
        		}  
        		ErrorDumpUtil.ErrorLog("used time ,totalScore,userID,quizID,evaluate,StrudentLoginName and seq are : "+usedTime+" : "+totalScore+" : "+userID+" : "+quizid+" : "+evaluate+" : "+uid+" : "+seq);        		
	        }			
	        xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
	        if(uid.equalsIgnoreCase(userID) && quizID.equalsIgnoreCase(quizid)){
	        	evaluate="complete";
	        	QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq,evaluate);
	        }
	        xmlScoreWriter.writeXmlFile();
	        if(type.equals("ReEvaluate")){
	        	data.setScreenTemplate("call,OLES,OLES_ReEvaluation.vm");
	        }
	        else{
	        	data.setScreenTemplate("call,OLES,Evaluate.vm");
	        }
	        data.setMessage(MultilingualUtil.ConvertedString("brih_finalScoreSaved",LangFile));
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	
	public void answerSheet(RunData data, Context context){
		LangFile=(String)data.getUser().getTemp("LangFile");
	}
}	                           
