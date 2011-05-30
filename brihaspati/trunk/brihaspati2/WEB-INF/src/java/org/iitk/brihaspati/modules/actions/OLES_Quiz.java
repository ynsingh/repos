package org.iitk.brihaspati.modules.actions;
/*
 * @(#)OLES_Quiz.java	
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
 * This Action class for Generate quiz  module of online examination system 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a> 
 * @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a> 
 */
public class OLES_Quiz extends SecureAction{

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
		if(action.equals("eventSubmit_doUploadQuiz"))
			doUploadQuiz(data,context);
		else if(action.equals("eventSubmit_doUpdate"))
			doUpdateQuiz(data,context);
		else if(action.equals("eventSubmit_generateQuiz"))
			generateQuiz(data,context);
		else if(action.equals("eventSubmit_randomQuiz"))
			randomQuiz(data,context);
		else if(action.equals("eventSubmit_doRemove"))
			doRemove(data,context);
		else if(action.equals("eventSubmit_oneByOneQuiz"))
			oneByOneQuiz(data,context);
		else if(action.equals("eventSubmit_addQuestion"))
			addQuestion(data,context);
		else if(action.equals("eventSubmit_updateQuizQuestionSetting"))
			updateQuizQuestionSetting(data,context);
		else if(action.equals("eventSubmit_PreviewQuiz"))
			acceptQuizPreview(data,context);
		else if(action.equals("eventSubmit_ShowPreview"))
			showPreview(data,context);
		else if(action.equals("eventSubmit_RejectQuiz"))
			rejectQuizPreview(data,context);
		else if(action.equals("eventSubmit_updateOneByOneQuiz"))
			updateOneByOneQuiz(data,context);
		else if(action.equals("eventSubmit_announceExam"))
			announceExam(data,context);
		else if(action.equals("eventSubmit_newAnnouncement"))
			newAnnouncement(data,context);
		else if(action.equals("eventSubmit_practiceQuiz"))
			practiceQuiz(data,context);
		else
			data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));				
	}


	/** This method is responsible for uploading quiz setting in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void doUploadQuiz(RunData data, Context context){
		ErrorDumpUtil.ErrorLog("inside action");
		try {	        	
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();			
			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");
			String quizName=pp.getString("quizName","");
			String quizID = pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxTime=pp.getString("maxTime","")+":00";
			String noQuestion=pp.getString("numberQuestion","");
			String allow = pp.getString("allow","");

			String filepath=CoursePath+"/"+crsId+"/Exam/";
			File ff=new File(filepath);
			if(!ff.exists())
				ff.mkdirs();
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID;
			File ff1=new File(filepath1);
			if(!ff1.exists())
				ff1.mkdirs();
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			File QuizQuestionxmls=new File(filepath1+"/"+quizQuestionPath);
			String QuizQuestionxmlsPath =  QuizQuestionxmls.getAbsolutePath();
			String QuizQuestionSettingxmlsPath =  filepath1+"/"+quizQuestionSettingPath;
			String quizPath="/Quiz.xml";				
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			/*
			 * At the time of quiz setup, status is saved to "INA"
			 * when quiz is created (either randomly/one by one) status is changed to "ACT"
			 */
			String status="INA";
			xmlwriteQuizlist(filepath,quizID,quizName,maxMarks,maxTime,noQuestion,status,Cur_date,quizPath,data,context,QuizQuestionxmlsPath,QuizQuestionSettingxmlsPath);
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:doUploadQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for creating xml file for quiz setting 
	 * @param filepath String path to quiz.xml
	 * @param quizID String ID of quiz (currently equal to quiz name)
	 * @param quizName String Name of quiz 
	 * @param maxMars String maximum marks in quiz 
	 * @param maxTime String maximum time for quiz 
	 * @param noQuestion String number of questions in quiz
	 * @param status String status of quiz(active/inactive)
	 * @param CreationDate String
	 * @param quizPath String quiz.xml file name
	 * @param data RunData instance
	 * @param context Context instance
	 * @param quizQuestionPath String quizID_Question.xml file name(where questions of a quiz is stored)
	 * @param quizQuestionSettingPath String quizID_QuestionSetting.xml file name(where question setting of a quiz is stored)
	 * @exception Exception, a generic exception
	 */
	public void xmlwriteQuizlist(String filepath,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String CreationDate,String quizPath,RunData data,Context context, String QuizQuestionxmlsPath, String QuizQuestionSettingxmlsPath){
		try{
			ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			String allow = pp.getString("allow","");
			XmlWriter xmlWriter=null;
			boolean found=false;
			File Quizxmls=new File(filepath+"/"+quizPath);
			File QuizQuestionxmls=new File(QuizQuestionxmlsPath);
			String Filename = QuizQuestionxmls.getName();
			ErrorDumpUtil.ErrorLog("\n file name! "+Filename);
			File QuizQuestionSettingxmls=new File(QuizQuestionSettingxmlsPath);
			QuizMetaDataXmlReader quizMetaData=null;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Quizxmls.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(Quizxmls.getAbsolutePath());
			}
			/**
			 *Checking for  the existing quiz setting
			 *@see QuizMetaDataXmlReader in Util.
			 */
			else {
				quizMetaData=new QuizMetaDataXmlReader(filepath+"/"+quizPath);
				Vector collect=quizMetaData.getQuesBanklist_Detail();
				if(collect!=null){
					for(int i=0;i<collect.size();i++) {
						String quizid=((QuizFileEntry) collect.elementAt(i)).getQuizID();
						if((quizID.equals(quizid))){
							found=true;
							data.setMessage(MultilingualUtil.ConvertedString("oles_quiz_duplicate",LangFile));
						}
					}
				}
			}
			if(found==false){                     
				xmlWriter=new XmlWriter(filepath+"/"+quizPath);
				QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,allow);
				xmlWriter.writeXmlFile();
				if(!QuizQuestionxmls.exists()) {
					QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionxmls.getAbsolutePath());
				}
				if(!QuizQuestionSettingxmls.exists()) {
					QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionSettingxmls.getAbsolutePath());
				}
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("oles_msg",LangFile));
			}                
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !! "+e);
			data.setMessage("See ExceptionLog !!" );
		}
	}

	/** This method is responsible to update the quiz setting in /courses/courseid/Exam/Quiz.xml
	 * this method is also responsible to delete the quiz setting in /courses/courseid/Exam/Quiz.xml,
	 * quizID_Questions.xml quizID_QuestionSetting.xml files in /courses/courseid/Exam/QuizID/
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void doUpdateQuiz(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();

			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");
			String count=pp.getString("count","");
			context.put("tdcolor",count);

			String quizName=pp.getString("quizName","");
			String quizID = pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxTime=pp.getString("maxTime","")+":00";
			//String maxTime=pp.getString("maxTime","");
			String noQuestion=pp.getString("numberQuestion","");
			String status = "ACT";
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			String modifiedDate = Cur_date;
			
			boolean success;
			int seq=-1;
			XmlWriter xmlWriter=null;
			Vector collect=new Vector();
			Vector str=new Vector();
			HashMap insertedQuestionHashMap=new HashMap();
			String deltype = pp.getString("delType","");
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
			String quizPath="/Quiz.xml";
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			String fileName = filepath+quizQuestionPath;
			QuizMetaDataXmlReader quizmetadata=null;
			quizmetadata=new QuizMetaDataXmlReader(filepath+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();

			quizmetadata=new QuizMetaDataXmlReader(filepath1+quizQuestionSettingPath);
			insertedQuestionHashMap=quizmetadata.getQuizQuestionNoMarks(quizmetadata,quizID);
			int insertedMarksQuiz =((Integer)insertedQuestionHashMap.get("marks"));
			int insertedQuestionQuiz = ((Integer)insertedQuestionHashMap.get("noQuestion"));

			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					if(quizid.equals(quizID)){
						seq=i;  
						break;
					}
				}
				if(deltype.equals("quizDel")){ 
					String quizid;
					File scoreFile = new File(filepath+"/score.xml");
					Vector<QuizFileEntry> scoreVector=new Vector<QuizFileEntry>();
					if(scoreFile.exists()){
						quizmetadata=new QuizMetaDataXmlReader(filepath+"/score.xml");
						scoreVector = quizmetadata.getDistinctIDFromFinalScore();
						for(QuizFileEntry a:scoreVector){
							quizid = a.getQuizID();
							if(quizid.equalsIgnoreCase(quizID)){
								ErrorDumpUtil.ErrorLog("quiz is stored in score.xml");
								data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotdeleted",LangFile));
								return;
							}
						}
					}
					xmlWriter=new XmlWriter(filepath+"/"+quizPath);
					xmlWriter.removeElement("Quiz",seq); 
					File file=new File(filepath1);
					success = deleteDir(file);
//					File file=new File(filepath1+"/"+quizQuestionPath);
//					File fileTemp=new File(filepath1+"/"+tempQuizQuestionPath);
//					File fileQuestionSetting=new File(filepath1+"/"+quizQuestionSettingPath);
//					if(!file.exists()){
//						return;
//					}
//					else if(!fileQuestionSetting.exists()){
//						file.delete();
//						return;
//					} 
//					else {
//						file.delete();
////						fileQuestionSetting.delete();						
//					}
//					if(fileTemp.exists()){
//						fileTemp.delete();
//					}
					xmlWriter.writeXmlFile();
					data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
				}
				else{
					if(insertedQuestionQuiz<=Integer.parseInt(noQuestion)){
						if(insertedMarksQuiz<=Integer.parseInt(maxMarks)){
							xmlWriter=QuizMetaDataXmlWriter.Update_QuizList(filepath,quizPath,seq,quizID,maxMarks,maxTime,noQuestion,modifiedDate);
							xmlWriter.writeXmlFile();
							data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));							
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_quizupdate",LangFile)+" "+insertedMarksQuiz+" "+MultilingualUtil.ConvertedString("brih_insertedmarksmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_maxmarks",LangFile));														
						}
					}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_quizupdate1",LangFile)+" "+insertedQuestionQuiz+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_maxquestion",LangFile));
					}					
				}
			}			
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:doUpdateQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to set the quiz details in Random_Quiz.vm for creation of a quiz
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void generateQuiz(RunData data, Context context){
		try{
			String quizName=data.getParameters().getString("quizName","");
			context.put("quizName",quizName);			
			String[] temp = quizName.split(",");
			String allowPractice=temp[3];
			//			context.put("quizID",quizID);
			//			String maxMarks=temp[1];		
			ErrorDumpUtil.ErrorLog("quiz id from drop down "+allowPractice);
			if(allowPractice.equalsIgnoreCase("yes")){
				context.put("type","createQuiz");
				ErrorDumpUtil.ErrorLog("inside data set screen template");
				data.setScreenTemplate("call,OLES,Practice_Quiz.vm");				
			}			
//			context.put("type","createQuiz");		
			ErrorDumpUtil.ErrorLog("quiz id from drop down "+quizName);
				
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:generateQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for uploading quiz_questions setting (randomly) in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void randomQuiz(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			String mode=data.getParameters().getString("mode","");
			String quizMode=data.getParameters().getString("quizMode","");
			String topicName = data.getParameters().getString("topicName","");
			ErrorDumpUtil.ErrorLog("\n topic name is:"+topicName);
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");
			String status = "ACT";
			String quizStatus="ACT";
			ErrorDumpUtil.ErrorLog("\n mode in oles quiz is:"+mode);

			String page = data.getParameters().getString("page","");
			ParameterParser pp=data.getParameters();
			String numberQuestion="";
			if(mode.equals("one")|quizMode.equals("one")){
				numberQuestion = "1";
				if(levelName.equalsIgnoreCase("Easy"))
					levelName="0-3";
				else if(levelName.equalsIgnoreCase("Medium"))
					levelName="4-6";
				else if(levelName.equalsIgnoreCase("Hard"))
					levelName="7-9";
			}
			else
				numberQuestion = pp.getString("numberQuestion","");

			String marksQuestion = pp.getString("marksQuestion","");

			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			String questionsPath=quizID+"_Questions.xml";

			File newFile=new File(newFilePath+"/"+questionSettingPath);
			XmlWriter xmlWriter=null;
			if(!newFile.exists())
				context.put("isFile","");
			else
			{
				context.put("isFile","exist");
				QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
				HashMap hm = new HashMap();
				hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID);
				int mark =((Integer)hm.get("marks"));
				int enteredQuestions = ((Integer)hm.get("noQuestion"));
				if(enteredQuestions < Integer.parseInt(maxnoQuestions) | mark < Integer.parseInt(maxMarks)){
					if(enteredQuestions==0){
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)){
							if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks))
								insertQuestionRandomly(xmlWriter,newFilePath,numberQuestion,topicName,typeName,levelName,marksQuestion,status,data,username,courseid,questionSettingPath,questionsPath);
							else
								data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
						}
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
					}
					else{
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)-enteredQuestions){                           
							if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks)-mark)
								insertQuestionRandomly(xmlWriter,newFilePath,numberQuestion,topicName,typeName,levelName,marksQuestion,status,data,username,courseid,questionSettingPath,questionsPath);                            
							else
								data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
						}
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
					}
				}
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
			}            
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:randomQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for inserting quiz_questions setting in a xml file
	 * @param xmlWriter XmlWriter instance
	 * @param String filepath, String xml file name, String no of question
	 * @param String topic name, String type of question, String difficulty level, String marks per question
	 * @param String status of question, data RunData instance, String name, String courseid
	 * @param quizQuestionSettingPath String quizID_QuestionSetting.xml file name(where question setting of a quiz is stored)
	 * @param quizQuestionPath String quizID_Question.xml file name(where questions of a quiz is stored)
	 * @exception Exception, a generic exception
	 */
	public String[] insertQuestionRandomly(XmlWriter xmlWriter, String newFilePath, String numberQuestion,
			String topicName, String typeName, String levelName, String marksQuestion, String status, RunData data, String username, String courseid, String questionSettingPath, String questionsPath){
		String variable[]=new String[4];
		try{ 
			LangFile=(String)data.getUser().getTemp("LangFile");
			String mode=data.getParameters().getString("mode","");
			String quizMode=data.getParameters().getString("quizMode","");
			String page = data.getParameters().getString("page","");
			String quizID=data.getParameters().getString("quizID","");
			String quizStatus="ACT";
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
			Vector questionVector = new Vector();
			String[] insertedQuestionVector = new String[2];
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			String questionID="";
			String question="";
			String answer="";
			String option1="";
			String option2="";
			String option3="";
			String option4="";

			if(mode.equals("one")|quizMode.equals("one")){
				questionID=data.getParameters().getString("questionID","");
				question=data.getParameters().getString("question","");
				answer=data.getParameters().getString("answer","");
				if(typeName.equals("mcq")){
					option1=data.getParameters().getString("option1","");
					option2=data.getParameters().getString("option2","");
					option3=data.getParameters().getString("option3","");
					option4=data.getParameters().getString("option4","");
				}    			
			}
			File ff = new File(questionBankFilePath+"/"+questionBankQuestionsPath);
			ErrorDumpUtil.ErrorLog("ff file path :"+ff.getPath());
			if(!ff.exists())
				variable[0] = "empty";
			else{
				QuizMetaDataXmlReader questionBankXmlReader;         
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);              
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);             
				if(questionVector!=null){
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					insertedQuestionVector = questionBankXmlReader.getQuizQuestions(questionBankQuestionsPath,numberQuestion,questionVector.size());                    
					if(insertedQuestionVector[0].equalsIgnoreCase("a"))
						variable[0]="empty";
					else if(insertedQuestionVector[0].equalsIgnoreCase("firstEntry")){
						String questionNo = "";
						if(Integer.parseInt(numberQuestion)<=questionVector.size()){
							variable[0]="firstInsert";
							questionNo = numberQuestion;
						}
						else{
							variable[0]="insert";
							questionNo = String.valueOf(questionVector.size());
							variable[1]=""+questionVector.size();
							variable[2]="0";
							variable[3] = ""+String.valueOf(questionVector.size());
						}
						xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
						QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
						xmlWriter.writeXmlFile();
						if(mode.equals("one")|quizMode.equals("one")){
							xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
							QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
							xmlWriter.writeXmlFile();
							variable[0]="success";
						}
						updateQuizRandomly(quizID, quizStatus, courseid, mode);
					}
					else{

						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="insert";
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,numberQuestion,id);
							xmlWriter.writeXmlFile(); 
							if(mode.equals("one")|quizMode.equals("one")){
								xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
								QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
								xmlWriter.writeXmlFile();
								variable[0]="success";
							}
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont insert";
						else{
							variable[0]="insert";
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
							xmlWriter.writeXmlFile();
							if(mode.equals("one")|quizMode.equals("one")){
								xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
								QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
								xmlWriter.writeXmlFile();
								variable[0]="success";
							}
							variable[3] = ""+questionNo;
						}
						variable[1]=""+questionVector.size();
						variable[2]=""+insertedQuestionVector[0];
					}
				}
				else
					variable[0]="empty";
			}        
			if(variable[0].equalsIgnoreCase("empty"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_repository",LangFile));
			else if(variable[0].equalsIgnoreCase("success"))
				data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			else if(variable[0].equalsIgnoreCase("firstInsert"))
				data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			else if(variable[0].equalsIgnoreCase("insert")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			}
			else if(variable[0].equalsIgnoreCase("dont insert")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_sono",LangFile));
			} 
			if(variable[0].equalsIgnoreCase("empty")){
			}
			else{
				if(page.equalsIgnoreCase("exit")){
					if(mode.equals("one"))
						data.setScreenTemplate("call,OLES,Oles_Gen.vm");
					if(quizMode.equalsIgnoreCase("random")|quizMode.equalsIgnoreCase("one"))
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
					else
						data.setScreenTemplate("call,OLES,Create_Quiz.vm");
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:insertQuestionRandomly !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
		return variable;
	}

	/** This method is responsible for updating quiz setting after first time question setting insertion
	 * status is set to ACT and mode is random / one
	 * @param String quizID
	 * @param String quiz status
	 * @param String courseid
	 * @param String quiz mode
	 * @exception Exception, a generic exception
	 */
	public void updateQuizRandomly(String quizID, String quizStatus, String courseid, String quizMode){
		try{
			
			XmlWriter xmlWriter = null;
			String newFilePath1=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/");
			String quizPath="/Quiz.xml";
			int seq=-1;
			ErrorDumpUtil.ErrorLog("quiz mode in update quiz randomy is :"+quizMode);
			if(quizMode.trim().isEmpty()){
				ErrorDumpUtil.ErrorLog("inside quiz mode empty");
				quizMode="random";
			}
			Vector collect=new Vector();
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(newFilePath1+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					if(quizid.equals(quizID)){
						seq=i;
						break;
					}
				}
				xmlWriter=QuizMetaDataXmlWriter.UpdateRandomQuizList(newFilePath1,quizPath,seq,quizID,quizStatus,quizMode);
				xmlWriter.writeXmlFile();
			}                   
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:updateQuizRandomly !! "+e);    			
		}
	}

	/** This method is responsible to delete multiple quiz settings in /courses/courseid/Exam/Quiz.xml
	 * and quizID_Questions.xml files in /courses/courseid/Exam/QuizID/
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void doRemove(RunData data,Context context){
		try{
			String qdelete=data.getParameters().getString("deleteFileNames");
			if(!qdelete.equals("")){
				StringTokenizer st=new StringTokenizer(qdelete,"^");
				for(int j=0;st.hasMoreTokens();j++){
					String q_id=st.nextToken();
					doRemoveQuiz(data,context,q_id);
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:doRemove !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to delete the quiz setting in /courses/courseid/Exam/Quiz.xml
	 * ,quizID_Questions.xml quizID_QuestionSetting.xml files in /courses/courseid/Exam/QuizID/
	 * @param data RunData instance
	 * @param context Context instance
	 * @param quizID String
	 * @exception Exception, a generic exception
	 */
	public void doRemoveQuiz(RunData data, Context context, String quizID){
		try{
			boolean success;
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();

			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");

			int seq=-1;
			XmlWriter xmlWriter=null;
			Vector collect=new Vector();
			Vector str=new Vector();
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
			String quizPath="/Quiz.xml";
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			String fileName = filepath+quizQuestionPath;
			QuizMetaDataXmlReader quizmetadata=null;
			quizmetadata=new QuizMetaDataXmlReader(filepath+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();

			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					if(quizid.equals(quizID)){
						seq=i;            	   
						break;
					}
				}  
				String quizid;
				File scoreFile = new File(filepath+"/score.xml");
				Vector<QuizFileEntry> scoreVector=new Vector<QuizFileEntry>();
				if(scoreFile.exists()){
					quizmetadata=new QuizMetaDataXmlReader(filepath+"/score.xml");
					scoreVector = quizmetadata.getDistinctIDFromFinalScore();
					for(QuizFileEntry a:scoreVector){
						quizid = a.getQuizID();
						if(quizid.equalsIgnoreCase(quizID)){
							ErrorDumpUtil.ErrorLog("quiz id entry in score.xml");
							return;
						}
					}
				}
				xmlWriter=new XmlWriter(filepath+"/"+quizPath);
				xmlWriter.removeElement("Quiz",seq); 
				//=====================
				File file=new File(filepath1);
				success = deleteDir(file);
//				File file=new File(filepath1+"/"+quizQuestionPath);
//				File fileTemp=new File(filepath1+"/"+tempQuizQuestionPath);
//				File fileQuestionSetting=new File(filepath1+"/"+quizQuestionSettingPath);
//				if(!file.exists()){
//					return;
//				}
//				else if(!fileQuestionSetting.exists()){
//					file.delete();
//					return;
//				} 
//				else {
//					file.delete();
////					fileQuestionSetting.delete();						
//				}
//				if(fileTemp.exists()){
//					fileTemp.delete();
//				}
				//=========================
//				File file=new File(filepath1+"/"+quizQuestionPath);
//				File fileTemp=new File(filepath1+"/"+tempQuizQuestionPath);
//				File fileQuestionSetting=new File(filepath1+"/"+quizQuestionSettingPath);
//				file.delete(); 
//				fileQuestionSetting.delete();
//				if(fileTemp.exists()){
//					fileTemp.delete();
//				}
				xmlWriter.writeXmlFile();
				data.setMessage("Unattempted "+MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
			}			
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:doRemoveQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for uploading quiz_questions setting (one by one) in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void oneByOneQuiz(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String mode=data.getParameters().getString("mode","");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			String topicName = data.getParameters().getString("topicName","");
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");

			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";

			String quizFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String quizQuestionsPath=quizID+"_Questions.xml";
			Vector quizQuestionsVector = new Vector();
			Vector questionBankVector = new Vector();
			Vector questionVector = new Vector();
			File newFile=new File(questionBankFilePath+"/"+questionBankQuestionsPath);
			int marksQuestion = 0;
			String var = "";  
			String selectedQuestionID="";
			String id="";
			if(mode.equals("update")){
				String quizSetting = data.getParameters().getString("quizSetting","");
				context.put("quizSetting",quizSetting);
				String[] temp = quizSetting.split(",");
				id = temp[5];			
			}
			if(!newFile.exists())
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
			else{
				QuizMetaDataXmlReader quizXmlReader=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionsPath);
				if(mode.equals("update")){
					quizQuestionsVector = quizXmlReader.getInsertedQuizQuestions(id);
					selectedQuestionID = quizXmlReader.getInsertedQuizQuestionID(id);
				}
				else
					quizQuestionsVector = quizXmlReader.getInsertedQuizQuestions();

				QuizMetaDataXmlReader questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				questionBankVector = questionBankXmlReader.getRandomQuizQuestions(typeName);
				if(quizQuestionsVector!=null & questionBankVector!=null){
					for(int i=0;i<quizQuestionsVector.size();i++){
						String marks = ((QuizFileEntry) quizQuestionsVector.elementAt(i)).getMarksPerQuestion();
						marksQuestion = marksQuestion + Integer.parseInt(marks);		           		
					}
					if(quizQuestionsVector.size()!=Integer.parseInt(maxnoQuestions)){
						if(marksQuestion!=Integer.parseInt(maxMarks)){
							if(quizQuestionsVector.size()==0){
								questionVector.addAll(questionBankVector);
								var="reinsert";
							}
							else{
								for(int i=0;i<questionBankVector.size();i++){
									String questionid =((QuizFileEntry) questionBankVector.elementAt(i)).getQuestionID();
									for(int j=0;j<quizQuestionsVector.size();j++){
										String questionID =((QuizFileEntry) quizQuestionsVector.elementAt(j)).getQuestionID();
										String fileName =((QuizFileEntry) quizQuestionsVector.elementAt(j)).getFileName();
										if(questionid.equals(questionID) & fileName.equals(questionBankQuestionsPath))
											break;
										else{
											if(j==quizQuestionsVector.size()-1){
												questionVector.add(questionBankVector.get(i));
												var="reinsert";
											}
										}
									}
								}
							}							
							if(questionVector.size()==0)
								var="complete";							
						}
						else
							var="maxMarks";						
					}
					else
						var="maxQuestion";						
				}
				else{
					if(questionBankVector!=null)
						var="insert";							
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
				}
				if(var.equalsIgnoreCase("complete"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
				else if(var.equalsIgnoreCase("maxQuestion"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions+". "+MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
				else if(var.equalsIgnoreCase("maxMarks"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks+". "+MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
				else{
					String quizDetail=data.getParameters().getString("quizDetail","");
					context.put("quizDetail",quizDetail);
					context.put("topicName",topicName);
					context.put("typeName",typeName);
					context.put("levelName",levelName);
					if(mode.equals("update"))
						context.put("selectedQuestionID",selectedQuestionID);
					if(var.equalsIgnoreCase("insert"))
						context.put("questionVector",questionBankVector);
					if(var.equalsIgnoreCase("reinsert"))
						context.put("questionVector",questionVector);
					data.setScreenTemplate("call,OLES,Question_List_OneByOne.vm");	
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:oneByOneQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}    	

	/** This method is responsible for adding questions (one by one) in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void addQuestion(RunData data, Context context){
		try{
			String quizName=data.getParameters().getString("quizName","");
			String topicName = data.getParameters().getString("topicName","");
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");
			String questionDetails = data.getParameters().getString("g","");

			context.put("quizName",quizName);
			context.put("topicName",topicName);
			context.put("typeName",typeName);
			context.put("levelName",levelName);
			context.put("questionDetails",questionDetails);
			context.put("insertQuestions","insertQuestions");

			String[] temp = questionDetails.split(",");
			context.put("questionID",temp[0]);
			context.put("question",temp[1]);
			context.put("answer",temp[2]);
			if(typeName.equals("mcq")){
				context.put("option1",temp[3]);
				context.put("option2",temp[4]);
				context.put("option3",temp[5]);
				context.put("option4",temp[6]);
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:addQuestion !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}    	

	/** This method is responsible for updating quiz_questions setting (randomly) in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void updateQuizQuestionSetting(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			ParameterParser pp=data.getParameters();
			String topicID = pp.getString("topicID","");
			String numberQuestion = pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";

			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			HashMap hm = new HashMap();
			hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID,topicID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion"));
			if(enteredQuestions < Integer.parseInt(maxnoQuestions) | mark < Integer.parseInt(maxMarks)){
				if(enteredQuestions==0){
					if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)){
						if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks))									
							updateQuizQuestionRandomly(newFilePath,data,questionSettingPath);
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
				}
				else{
					if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)-enteredQuestions){                           
						if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks)-mark)							        
							updateQuizQuestionRandomly(newFilePath,data,questionSettingPath);
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
				}
			}
			else
				data.setMessage(MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));                   
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:updateQuizQuestionSetting !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for updating quiz_questions setting (randomly) in a xml file
	 * @param path to quiz_questionsetting.xml String
	 * @param data RunData Instance
	 * @param String xml file name
	 * @exception Exception, a generic exception
	 */
	public String[] updateQuizQuestionRandomly(String newFilePath,RunData data,String questionSettingPath){
		String variable[]=new String[4];
		try{ 
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String quizMode=data.getParameters().getString("quizMode","");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			String topicName = data.getParameters().getString("topicName","");
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");
			String page = data.getParameters().getString("page","");

			ParameterParser pp=data.getParameters();
			String topicID = pp.getString("topicID","");
			String numberQuestion = pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");

			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
			Vector questionVector = new Vector();
			String[] insertedQuestionVector = new String[2];
			int seq=-1;
			Vector collect=new Vector();
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			collect=quizmetadata.getQuizQuestionDetail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String topicid =((QuizFileEntry) collect.elementAt(i)).getID();
					if(topicid.equals(topicID)){
						seq=i;
						break;
					}
				}
			}
			File ff = new File(questionBankFilePath+"/"+questionBankQuestionsPath);
			if(!ff.exists())
				variable[0] = "empty";
			else{
				QuizMetaDataXmlReader questionBankXmlReader;         
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				XmlWriter xmlWriter = new XmlWriter(newFilePath+"/"+questionSettingPath);
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);             
				if(questionVector!=null){
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					insertedQuestionVector = questionBankXmlReader.getQuizQuestions(questionBankQuestionsPath,topicID);                    
					if(insertedQuestionVector[0].equalsIgnoreCase("a"))
						variable[0]="empty";
					else if(insertedQuestionVector[0].equalsIgnoreCase("firstUpdate")){
						String questionNo = "";
						if(Integer.parseInt(numberQuestion)<=questionVector.size()){
							variable[0]="firstUpdate";
							questionNo = numberQuestion;
						}
						else{
							variable[0]="update";
							questionNo = String.valueOf(questionVector.size());
							variable[1]=""+questionVector.size();
							variable[2]="0";
							variable[3] = ""+String.valueOf(questionVector.size());
						}
						xmlWriter=QuizMetaDataXmlWriter.Update_QuizQuestionSetting(newFilePath,questionSettingPath,seq,topicName,typeName,levelName,marksQuestion,questionNo,topicID);
						xmlWriter.writeXmlFile(); 
					}
					else{
						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="update";
							xmlWriter=QuizMetaDataXmlWriter.Update_QuizQuestionSetting(newFilePath,questionSettingPath,seq,topicName,typeName,levelName,marksQuestion,numberQuestion,topicID);
							xmlWriter.writeXmlFile(); 
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont update";
						else{
							variable[0]="update";
							xmlWriter=QuizMetaDataXmlWriter.Update_QuizQuestionSetting(newFilePath,questionSettingPath,seq,topicName,typeName,levelName,marksQuestion,questionNo,topicID);
							xmlWriter.writeXmlFile();  
							variable[3] = ""+questionNo;
						}
						variable[1]=""+questionVector.size();
						variable[2]=""+insertedQuestionVector[0];						
					}
				}
				else            	
					variable[0]="empty";
			}        
			if(variable[0].equalsIgnoreCase("empty"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_repository",LangFile));
			else if(variable[0].equalsIgnoreCase("success"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_questioninsertsuccess",LangFile));
			else if(variable[0].equalsIgnoreCase("firstUpdate"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile));
			else if(variable[0].equalsIgnoreCase("update")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("update_msg",LangFile));
			}
			else if(variable[0].equalsIgnoreCase("dont update")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_sonoupdate",LangFile));				
			} 

			if(variable[0].equalsIgnoreCase("empty")){
			}
			else{
				if(page.equalsIgnoreCase("exit")){
					if(quizMode.equalsIgnoreCase("random"))
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
					else
						data.setScreenTemplate("call,OLES,Oles_Gen.vm");
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:updateQuizQuestionRandomly !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
		return variable;
	}

	/** This method is responsible to accept the quiz preview and stored in the quizID_questions xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void acceptQuizPreview(RunData data, Context context){
		try {	        	
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String count = data.getParameters().getString("count","");
			context.put("tdcolor",count);
			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");
			String quizID = pp.getString("quizID","");

			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/"+quizID+"/");
			File ff=new File(filePath);
			if(!ff.exists())
				ff.mkdirs();
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			File tempquizQuestionxmls=new File(filePath+"/"+tempQuizQuestionPath);
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			File QuizQuestionxmls=new File(filePath+"/"+quizQuestionPath);
			if(QuizQuestionxmls.exists()){
				QuizQuestionxmls.delete();
			}
			tempquizQuestionxmls.renameTo(QuizQuestionxmls);
			data.setMessage(MultilingualUtil.ConvertedString("brih_preview",LangFile));
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:acceptQuizPreview !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}	

	/** This method is responsible for uploading stored preview setting in the page
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void showPreview(RunData data, Context context){
		try {	        	
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String count = data.getParameters().getString("count","");
			context.put("tdcolor",count);
			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");
			String quizID = pp.getString("quizID","");
			String quizDetail = pp.getString("quizDetail","");
			String quizName = pp.getString("quizName","");
			context.put("quizID",quizID);
			context.put("quizDetail",quizDetail);
			context.put("quizName",quizName);

			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/"+quizID+"/");
			File ff=new File(filePath);
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			Vector question = new Vector();
			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(filePath+"/"+quizQuestionPath);
			if(!ff.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_nopreview",LangFile));
				return;
			}
			File QuizQuestionxmls=new File(filePath+"/"+quizQuestionPath);
			if(!QuizQuestionxmls.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_nopreview",LangFile));
				return;
			}
			question = questionReader.getInsertedQuizQuestions();
			if(question==null){
				data.setMessage(MultilingualUtil.ConvertedString("brih_nopreview",LangFile));
				return;
			}
			context.put("finalq",question);			
			data.setScreenTemplate("call,OLES,Stored_Preview.vm");			
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:showPreview !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to reject the preview showed on the page
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void rejectQuizPreview(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String count = data.getParameters().getString("count","");
			context.put("tdcolor",count);
			String quizID = data.getParameters().getString("quizID","");
			crsId=(String)data.getUser().getTemp("course_id");

			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/"+quizID+"/");
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			File tempquizQuestionxmls=new File(filePath+"/"+tempQuizQuestionPath);
			if(tempquizQuestionxmls.exists()){
				tempquizQuestionxmls.delete();
			}
			data.setScreenTemplate("call,OLES,Preview_Quiz.vm");				
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:rejectQuizPreview !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}	

	/** This method is responsible for updating questions as well as settings of one by one type of quiz in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void updateOneByOneQuiz(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
			String quizDetail = pp.getString("quizDetail","");
			String quizSetting = pp.getString("quizSetting","");
			String[] temp = quizSetting.split(",");
			String id = temp[5];
			String option1,option2,option3,option4,levelName;
			String topicName = pp.getString("topicName","");
			String typeName = pp.getString("typeName","");
			String level = pp.getString("levelName","");
			if(level.equalsIgnoreCase("Easy"))
				levelName="0-3";
			else if(level.equalsIgnoreCase("Medium"))
				levelName="4-6";
			else
				levelName="7-9";				
			String fileName = topicName+"_"+levelName+"_"+typeName+".xml";
			String questionID = pp.getString("questionID","");
			String question = pp.getString("question","");
			String answer = pp.getString("answer","");			
			if(typeName.equals("mcq")){
				option1 = pp.getString("option1","");
				option2 = pp.getString("option2","");
				option3 = pp.getString("option3","");
				option4 = pp.getString("option4","");
			}
			else{
				option1 = "";
				option2 = "";
				option3 = "";
				option4 = "";
			}
			String marksQuestion = pp.getString("marksQuestion","");
			String questionNo = "1";
			String courseID = pp.getString("courseID","");
			String quizID = pp.getString("quizID","");
			String quizFilePath=TurbineServlet.getRealPath("/Courses/"+courseID+"/Exam/"+quizID);
			String quizQuestionSettingPath=quizID+"_QuestionSetting.xml";
			String quizQuestionsPath=quizID+"_Questions.xml";
			String maxMarks = pp.getString("maxMarks","");
			QuizMetaDataXmlReader quizXmlReader=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionSettingPath);
			HashMap insertedMarksHashMap = new HashMap();
			insertedMarksHashMap = quizXmlReader.getQuizQuestionNoMarks(quizXmlReader,quizID);
			int insertedMarksQuiz =((Integer)insertedMarksHashMap.get("marks"));			
			if(Integer.parseInt(marksQuestion)<Integer.parseInt(maxMarks)){
				if(Integer.parseInt(marksQuestion)+insertedMarksQuiz<Integer.parseInt(maxMarks)){
					XmlWriter xmlWriter=null;
					xmlWriter=new XmlWriter(quizFilePath+"/"+quizQuestionSettingPath);
					xmlWriter = QuizMetaDataXmlWriter.Update_QuizQuestionSetting(quizFilePath,quizQuestionSettingPath,(Integer.parseInt(id))-1,topicName,typeName,levelName,marksQuestion,questionNo,id);
					xmlWriter.writeXmlFile();			
					XmlWriter xmlWriter1=null;
					xmlWriter1=new XmlWriter(quizFilePath+"/"+quizQuestionsPath);
					xmlWriter1 = QuizMetaDataXmlWriter.UpdateQuizQuestion(quizFilePath,quizQuestionsPath,Integer.parseInt(id),questionID,question,option1,option2,option3,option4,answer,marksQuestion,fileName);
					xmlWriter1.writeXmlFile();			
					data.setMessage(MultilingualUtil.ConvertedString("c_msg5",LangFile));
				}
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+
							" "+insertedMarksQuiz+" "+MultilingualUtil.ConvertedString("brih_insertedmarksmsg",LangFile));
			}
			else
				data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:updateOneByOneQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to get the quiz details to announce/update a quiz
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void announceExam(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String quizID=data.getParameters().getString("quizID","");
			String courseid=(String)user.getTemp("course_id");  

			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizPath="/Quiz.xml";
			QuizMetaDataXmlReader quizmetadata=null;
			//functionality code-if preview is not saved then quiz can't announced
			String previewFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String previewPath=quizID+"_Questions.xml";
			Vector previewDetail=new Vector();
			File previewFile=new File(previewFilePath+"/"+previewPath);
			if(previewFile.exists()){
				ErrorDumpUtil.ErrorLog("inside preview file exist");
				quizmetadata=new QuizMetaDataXmlReader(previewFilePath+"/"+previewPath);				
				previewDetail=quizmetadata.getInsertedQuizQuestions();
				if(previewDetail==null || previewDetail.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotannounced",LangFile));
					data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
				}
			}
			else{
				data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotannounced",LangFile));
				data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
			}

			//============================================================================
			String startDate = "",startTime = "",endDate = "",endTime = "",allowPractice = "";

			File file=new File(filePath+"/"+quizPath);
			Vector quizDetail=new Vector();

			if(file.exists()){
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				quizDetail=quizmetadata.getQuiz_Detail(quizID);
				if(quizDetail!=null){
					if(quizDetail.size()!=0){
						for(int i = 0; i<quizDetail.size(); i++){
							context.put("quizID",((QuizFileEntry) quizDetail.elementAt(i)).getQuizID());
							context.put("quizName",((QuizFileEntry) quizDetail.elementAt(i)).getQuizName());
							context.put("maxTime",((QuizFileEntry) quizDetail.elementAt(i)).getMaxTime());
							context.put("maxMarks",((QuizFileEntry) quizDetail.elementAt(i)).getMaxMarks());
							context.put("noQuestions",((QuizFileEntry) quizDetail.elementAt(i)).getnoQuestion());
							context.put("creationDate",((QuizFileEntry) quizDetail.elementAt(i)).getCreationDate());
							startDate = ((QuizFileEntry) quizDetail.elementAt(i)).getExamDate();
							startTime = ((QuizFileEntry) quizDetail.elementAt(i)).getStartTime();
							endDate = ((QuizFileEntry) quizDetail.elementAt(i)).getExpiryDate();
							endTime = ((QuizFileEntry) quizDetail.elementAt(i)).getEndTime();
							allowPractice = ((QuizFileEntry) quizDetail.elementAt(i)).getAllowPractice();
							ErrorDumpUtil.ErrorLog("start date and end date "+startDate+endDate);
						}							              
					}
				}
			}

			String m = "";
			if(startDate==null & startTime==null & endDate==null & endTime==null){
				m="new";
			}
			else{
				m="update";				
			}
			ErrorDumpUtil.ErrorLog("value of m "+m);
			context.put("mode",m);
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:announceExam !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible to announce/update a quiz
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void newAnnouncement(RunData data, Context context){
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
			String quizID=pp.getString("quizID","");
			//maxTime modification since for timer functionality time must be in the format mm:ss
			int maxTime;
			String maxtime = pp.getString("maxTime","");
			ErrorDumpUtil.ErrorLog("\n string maxtime is :"+maxtime);
			if(maxtime.indexOf(":")==-1){
				ErrorDumpUtil.ErrorLog("\n inside -1");
				maxTime=Integer.parseInt(pp.getString("maxTime",""));
			}
			else{
				String maxtimeArray[] = maxtime.split(":");
				ErrorDumpUtil.ErrorLog("\n inside -1 else after split");
				maxTime=Integer.parseInt(maxtimeArray[0]);
			}

			String allow=pp.getString("allow","");
			String courseID=(String)data.getUser().getTemp("course_id");
			boolean flag = false;

			String startYear = pp.getString("Start_year","");
			String startMonth = pp.getString("Start_mon","");
			String startDay = pp.getString("Start_day","");			
			String startHour = pp.getString("Start_hr","");
			String startMinute = pp.getString("Start_min","");

			String startDate = startYear+"-"+startMonth+"-"+startDay;
			String startTime = startHour+":"+startMinute;

			String endYear = pp.getString("End_year","");
			String endMonth = pp.getString("End_mon","");
			String endDay = pp.getString("End_day","");			
			String endHour = pp.getString("End_hr","");
			String endMinute = pp.getString("End_min","");

			String endDate = endYear+"-"+endMonth+"-"+endDay;
			String endTime = endHour+":"+endMinute;

			Calendar current = Calendar.getInstance();
			Calendar examDate = Calendar.getInstance();
			examDate.clear();
			Calendar expiryDate = Calendar.getInstance();
			expiryDate.clear();			
			examDate.set(Integer.parseInt(startYear),(Integer.parseInt(startMonth)-1), Integer.parseInt(startDay),Integer.parseInt(startHour),Integer.parseInt(startMinute));
			expiryDate.set(Integer.parseInt(endYear),(Integer.parseInt(endMonth)-1), Integer.parseInt(endDay),Integer.parseInt(endHour),Integer.parseInt(endMinute));

			Calendar examDay = Calendar.getInstance();
			examDay.clear();
			Calendar expiryDay = Calendar.getInstance();
			expiryDay.clear();			
			examDay.set(Integer.parseInt(startYear),(Integer.parseInt(startMonth)-1), Integer.parseInt(startDay));
			expiryDay.set(Integer.parseInt(endYear),(Integer.parseInt(endMonth)-1), Integer.parseInt(endDay));

			if(current.compareTo(examDate)==-1 || current.compareTo(examDate)==0){
				if(examDate.compareTo(expiryDate)!=1){
					if(examDay.compareTo(expiryDay)==0){
						if(Integer.parseInt(endHour)*60+Integer.parseInt(endMinute)-Integer.parseInt(startHour)*60+Integer.parseInt(startMinute)>maxTime){
							flag = true;
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_announeerror",LangFile)+" ("+maxTime+" "+ MultilingualUtil.ConvertedString("brih_minutes",LangFile)+")");
						}
					}
					else{
						flag = true;
					}						
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("Task_msg5",LangFile));

				}
			}
			else if(current.compareTo(examDate)==1){
				data.setMessage(MultilingualUtil.ConvertedString("brih_datemsg",LangFile));
			}

			if(flag==true){
				int seq = -1;
				XmlWriter xmlWriter=null;
				Vector collect = new Vector();
				String filePath=CoursePath+"/"+courseID+"/Exam/";
				String quizPath="/Quiz.xml";
				QuizMetaDataXmlReader quizmetadata=null;
				quizmetadata=new QuizMetaDataXmlReader(filePath+quizPath);
				collect=quizmetadata.getQuesBanklist_Detail();
				if(collect!=null){
					for(int i=0;i<collect.size();i++){
						String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
						if(quizid.equals(quizID)){
							seq=i;  
							break;
						}
					}
					xmlWriter=QuizMetaDataXmlWriter.announceQuiz(filePath,quizPath,seq,quizID,startDate,startTime,endDate,endTime);
					xmlWriter.writeXmlFile();
				}
				String mode=pp.getString("mode","");
				if(mode.equals("update"))
					data.setMessage(MultilingualUtil.ConvertedString("c_msg5",LangFile));
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_announced",LangFile));
					data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
			}			
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:newAnnouncement !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for writing practice quiz questions setting in xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void practiceQuiz(RunData data, Context context){
		ParameterParser pp=data.getParameters();
		try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=(String)data.getUser().getTemp("course_id");			
			String quizID=pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxnoQuestions=pp.getString("noQuestions","");
			String numberQuestion=pp.getString("numberQuestion","");			
			String marksQuestion = pp.getString("marksQuestion","");
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";			
			File newFile=new File(newFilePath+"/"+questionSettingPath);			
			XmlWriter xmlWriter=null;

			if(!newFile.exists())
				QuizMetaDataXmlWriter.OLESRootOnly(newFile.getAbsolutePath());
			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			HashMap hm = new HashMap();
			hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion"));
			ErrorDumpUtil.ErrorLog("marks and entered questions "+mark +" : "+enteredQuestions);
			if(enteredQuestions < Integer.parseInt(maxnoQuestions) | mark < Integer.parseInt(maxMarks)){
				if(enteredQuestions==0){
					if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)){
						if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks))
							insertPreviewQuestionSetting(data,context);
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
				}
				else{
					if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)-enteredQuestions){                           
						if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks)-mark)
							insertPreviewQuestionSetting(data,context);                            
						else
							data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
				}
			}
			else
				data.setMessage(MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));          
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:practiceQuiz !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for inserting quiz_questions setting in a xml file
	 * @param xmlWriter XmlWriter instance
	 * @param String filepath, String xml file name, String no of question
	 * @param String topic name, String type of question, String difficulty level, String marks per question
	 * @param String status of question, data RunData instance, String name, String courseid
	 * @param quizQuestionSettingPath String quizID_QuestionSetting.xml file name(where question setting of a quiz is stored)
	 * @param quizQuestionPath String quizID_Question.xml file name(where questions of a quiz is stored)
	 * @exception Exception, a generic exception
	 */
	public String[] insertPreviewQuestionSetting(RunData data,Context context){
		ParameterParser pp=data.getParameters();
		String variable[]=new String[4];
		try{ 
			LangFile=(String)data.getUser().getTemp("LangFile");
			String username=data.getUser().getName();
			String courseid=(String)data.getUser().getTemp("course_id");

			String topicName = pp.getString("topicName","");
			String typeName = pp.getString("typeName","");
			String levelName = pp.getString("levelName","");
			String quizID=pp.getString("quizID","");
			String numberQuestion=pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");

			String mode=data.getParameters().getString("mode","");
			String quizMode=data.getParameters().getString("quizMode","");
			String page = data.getParameters().getString("page","");
			//			String quizID=data.getParameters().getString("quizID","");
			String quizStatus="ACT";
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";	

			Vector questionVector = new Vector();
			String[] insertedQuestionVector = new String[2];
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			String questionID="";
			String question="";
			String answer="";
			String option1="";
			String option2="";
			String option3="";
			String option4="";
			XmlWriter xmlWriter = null;

			File ff = new File(questionBankFilePath+"/"+questionBankQuestionsPath);
			ErrorDumpUtil.ErrorLog("\n question bank file path :"+ff.getPath());
			if(!ff.exists())
				variable[0] = "empty";
			else{
				QuizMetaDataXmlReader questionBankXmlReader;         
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);              
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);             
				if(questionVector!=null){
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					insertedQuestionVector = questionBankXmlReader.getQuizQuestions(questionBankQuestionsPath,numberQuestion,questionVector.size());                    
					if(insertedQuestionVector[0].equalsIgnoreCase("a"))
						variable[0]="empty";
					else if(insertedQuestionVector[0].equalsIgnoreCase("firstEntry")){
						String questionNo = "";
						if(Integer.parseInt(numberQuestion)<=questionVector.size()){
							variable[0]="firstInsert";
							questionNo = numberQuestion;
						}
						else{
							variable[0]="insert";
							questionNo = String.valueOf(questionVector.size());
							variable[1]=""+questionVector.size();
							variable[2]="0";
							variable[3] = ""+String.valueOf(questionVector.size());
						}
						xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
						QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
						xmlWriter.writeXmlFile();

						updateQuizRandomly(quizID, quizStatus, courseid, mode);
					}
					else{

						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="insert";
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,numberQuestion,id);
							xmlWriter.writeXmlFile(); 							
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont insert";
						else{
							variable[0]="insert";
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
							xmlWriter.writeXmlFile();

							variable[3] = ""+questionNo;
						}
						variable[1]=""+questionVector.size();
						variable[2]=""+insertedQuestionVector[0];
					}
				}
				else
					variable[0]="empty";
			}        
			if(variable[0].equalsIgnoreCase("empty"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_repository",LangFile));
			else if(variable[0].equalsIgnoreCase("success"))
				data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			else if(variable[0].equalsIgnoreCase("firstInsert"))
				data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			else if(variable[0].equalsIgnoreCase("insert")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			}
			else if(variable[0].equalsIgnoreCase("dont insert")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_sono",LangFile));
			} 
			if(variable[0].equalsIgnoreCase("empty")){
			}
			else{
				if(page.equalsIgnoreCase("exit")){
					if(mode.equals("one"))
						data.setScreenTemplate("call,OLES,Oles_Gen.vm");
					if(quizMode.equalsIgnoreCase("random")|quizMode.equalsIgnoreCase("one"))
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
					else
						data.setScreenTemplate("call,OLES,Create_Quiz.vm");
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:insertPreviewQuestionSetting !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
		return variable;
	}
	
	public static boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    // The directory is now empty so delete it
	    return dir.delete();
	}
}	                           