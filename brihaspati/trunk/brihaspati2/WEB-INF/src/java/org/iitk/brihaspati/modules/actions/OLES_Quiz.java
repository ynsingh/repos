package org.iitk.brihaspati.modules.actions;
/*
 * @(#)OLES_Quiz.java
 *
 *  Copyright (c) 2010,2012,2013 DEI Agra, IITK.
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
//import java.io.FileReader;
//import java.io.BufferedReader;
import java.util.Vector;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Properties;
import java.text.SimpleDateFormat;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
//import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
//import org.iitk.brihaspati.modules.utils.CourseUserDetail;
//import org.iitk.brihaspati.modules.utils.CourseUtil;
//import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
//import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.XmlData;
//import org.iitk.brihaspati.modules.utils.MailNotification;
//import org.iitk.brihaspati.modules.utils.MailNotificationThread;

//import org.iitk.brihaspati.modules.utils.GroupUtil;
//import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.OnlineExamSystemMail;

/**
 * This Action class for Generate quiz  module of online examination system
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir singh</a>28jan2013
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>14aug2013
 * @author <a href="mailto:ankitadwivedikit007@gmail.com">Ankita Dwivedi</a>
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
		else if(action.equals("eventSubmit_doDeleteQuestions"))
			deleteQuestions(data,context);
		else
			data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));
	}

	/** This method is responsible for uploading quiz setting in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	public void doUploadQuiz(RunData data, Context context){
		try{
			/**Get parameter from template through Parameter Parser*/
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
			//String type = pp.getString("type","");
			pp.setString("count","2");
			/**get path where the quiz stored */
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			File ff=new File(filepath);
			/**Check for the existence of directory*/
			if(!ff.exists())
				ff.mkdirs();
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID;
			File ff1=new File(filepath1);
			/**Check for the existence of directory*/
			if(!ff1.exists())
				ff1.mkdirs();
			/**get path where the quiz setting and quiz question file stored */
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			File QuizQuestionxmls=new File(filepath1+"/"+quizQuestionPath);
			String QuizQuestionxmlsPath =  QuizQuestionxmls.getAbsolutePath();
			String QuizQuestionSettingxmlsPath =  filepath1+"/"+quizQuestionSettingPath;
			String quizPath="Quiz.xml";
			String Cur_date=ExpiryUtil.getCurrentDate("-");

			/** At the time of quiz setup, status is saved to "INA"
			 * when quiz is created (either randomly/one by one) status is changed to "ACT"
			 */

			String status="INA";
			xmlwriteQuizlist(filepath,quizID,quizName,maxMarks,maxTime,noQuestion,status,Cur_date,quizPath,data,context,QuizQuestionxmlsPath,QuizQuestionSettingxmlsPath);
			/** This  part is responsible for sending mail to student to inform about the Practice Quiz
			  *@see OnlineExamSystemMail (method:SendMail) in util
			  */
			if(allow.equals("yes")){
				String str=OnlineExamSystemMail.SendMail(crsId,username,"practice","","","","",LangFile);
				if(str.equals("Success"))
				str=" "+MultilingualUtil.ConvertedString("mail_msg",LangFile);
                        	data.addMessage(str);
			}
		}//try
		catch(Exception e){
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
			/** get LangFile for multingual changes*/
			ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			String allow = pp.getString("allow","");
			XmlWriter xmlWriter=null;
			boolean found=false;
			File Quizxmls=new File(filepath+"/"+quizPath);
			File QuizQuestionxmls=new File(QuizQuestionxmlsPath);
			String Filename = QuizQuestionxmls.getName();
			File QuizQuestionSettingxmls=new File(QuizQuestionSettingxmlsPath);
			QuizMetaDataXmlReader quizMetaData=null;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Quizxmls.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(Quizxmls.getAbsolutePath());
				xmlWriter=new XmlWriter(filepath+"/"+quizPath);
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
				/**This part read existing xml (Quiz.xml)file and write new xml file with old values
				 *@see QuizMetaDataXmlWriter (method:QuizXml) in utils
				 *modify by Jaivir and Seema
        			 */
				xmlWriter=QuizMetaDataXmlWriter.QuizXml(filepath,quizPath);
				//-----------------------------------------------------------------
				QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,CreationDate,"",allow);
				xmlWriter.writeXmlFile();
				/**
                         	*Checking for  xml file presence and create blank xml for quiz setting and quiz questions xml
                         	*@see QuizMetaDataXmlWriter in Util.
                         	*/
				if(!QuizQuestionxmls.exists()) {
					QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionxmls.getAbsolutePath());
				}
				if(!QuizQuestionSettingxmls.exists()) {
					QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionSettingxmls.getAbsolutePath());
				}
				data.addMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("oles_msg",LangFile));
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
			String mode=pp.getString("mode","");
			String quizMode=pp.getString("quizMode","");
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

			/**get path where the Exam directory,quiz setting and quiz question file stored */
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
			String quizPath="/Quiz.xml";
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			String fileName = filepath+quizQuestionPath;
			/** read the xml file and put the all values in vector (collect)
			 * get total counting and marks counting of already inserted questions put in hashmap
         		 * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
			 */
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
				/**gets all distinct quizID,userID and score from score.xml file
				 *check for quiz submitted by any student or not
				 *@see QuizMetaDataXmlReader (reader of score.xml) in Util
				 */
				if(deltype.equals("quizDel")){
					String quizid;
					//File scoreFile = new File(filepath+"/score.xml");
					File scoreFile = new File(filepath+"/"+quizID+"/score.xml");
					ErrorDumpUtil.ErrorLog("scorefile in OLES_Quiz is"+scoreFile);
					Vector<QuizFileEntry> scoreVector=new Vector<QuizFileEntry>();
					if(scoreFile.exists()){
						//quizmetadata=new QuizMetaDataXmlReader(filepath+"/score.xml");
						quizmetadata=new QuizMetaDataXmlReader(filepath+"/"+quizID+"/score.xml");
						scoreVector = quizmetadata.getDistinctIDFromFinalScore();
						if(scoreVector!=null){
							for(QuizFileEntry a:scoreVector){
								quizid = a.getQuizID();
								if(quizid.equalsIgnoreCase(quizID)){
									data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotdeleted",LangFile));
									return;
								}
							}
						}
					}
					xmlWriter=new XmlWriter(filepath+"/"+quizPath);
					/**This part read existing xml (Quiz.xml)file and write new xml file with old values
                                         *@see QuizMetaDataXmlWriter (method:QuizXml) in utils
					 *modified by Jaivir/Manorama
					 */
					xmlWriter=QuizMetaDataXmlWriter.QuizXml(filepath,quizPath);
					//-----------------------------------------------------------
					xmlWriter.removeElement("Quiz",seq);
					File file=new File(filepath1);
					success = deleteDir(file);
					xmlWriter.writeXmlFile();
					data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
				}
				else{
					if(insertedQuestionQuiz<=Integer.parseInt(noQuestion)){
						if(insertedMarksQuiz<=Integer.parseInt(maxMarks)){
							//xmlWriter=QuizMetaDataXmlWriter.Update_QuizList(filepath,quizPath,seq,quizID,maxMarks,maxTime,noQuestion,modifiedDate);
							/**update file element in existing xml file with sequence number
							 *read existing xml (Quiz.xml)file and write new xml file with old values also updated values
                                         		 *@see QuizMetaDataXmlWriter (method:QuizXml) in utils
                                         		 *modified by Jaivir/Manorama
							 */
							UpdateQuizSetup(filepath,quizID,maxMarks,maxTime,noQuestion,modifiedDate);
							//xmlWriter.writeXmlFile();
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
			/** get the quizname from the template
			 *check the practice quiz status
		 	 * set template according to the quiz status(allow value yes or no)
			 */
			String quizName=data.getParameters().getString("quizName","");
			context.put("quizName",quizName);
			String[] temp = quizName.split(",");
			String allowPractice=temp[3];
			if(allowPractice.equalsIgnoreCase("yes")){
				context.put("type","createQuiz");
				data.setScreenTemplate("call,OLES,Practice_Quiz.vm");
			}
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */

            ErrorDumpUtil.ErrorLog("--------------------OLES_Quiz Action------------randomQuiz()-------");
			ParameterParser pp=data.getParameters();
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			String mode=data.getParameters().getString("mode","");
			String quizMode=data.getParameters().getString("quizMode","");
			String topicName = data.getParameters().getString("topicName","");
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");

            ErrorDumpUtil.ErrorLog("----randomQuiz()-------"+typeName);


			/**get the count parameter of tab colour
			 *put in the context for use in template
			 */
			String count=data.getParameters().getString("count","");
			context.put("tdcolor",count);

			String status = "ACT";
			//String quizStatus="ACT";
			/** get the parameter from the template
			 *set the value of level of question(easy,medium and hard)
			 */

			String page = data.getParameters().getString("page","");
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
			/**get path where the Exam directory,quiz setting and quiz question file stored */
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			String questionsPath=quizID+"_Questions.xml";

			File newFile=new File(newFilePath+"/"+questionSettingPath);
			XmlWriter xmlWriter=null;
			if(!newFile.exists())
				context.put("isFile","");
			else {
				/** read the xml file and put vector hashmap(hm)
                         	 * get total counting and marks counting of already inserted questions
                         	 * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         	 * @return hashmap
				 */
           			// ErrorDumpUtil.ErrorLog("----OLES_Quiz action----");
				context.put("isFile","exist");
				QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
				HashMap hm = new HashMap();
				hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID);//here---

               			// ErrorDumpUtil.ErrorLog("----OLES_Quiz action----1");
				int mark =((Integer)hm.get("marks"));
				int enteredQuestions = ((Integer)hm.get("noQuestion"));
				if(enteredQuestions < Integer.parseInt(maxnoQuestions) | mark < Integer.parseInt(maxMarks)){
					if(enteredQuestions==0){
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)){
							if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks))
								/*part responsible for inserting quiz_questions setting in a xml file*/
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
        //ErrorDumpUtil.ErrorLog("----OLES_Quiz action----insertQuestionRandomly()");
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
            String min="";
            String max="";

			data.getParameters().setString("count","3");
			/**check for insert question one bye one mode
			 * and get parameter according to the type of question(mcq, tft, sat, sart, lat)
			 */
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

           // ErrorDumpUtil.ErrorLog("----OLES_Quiz action----insertQuestionRandomly()---------");
            //ErrorDumpUtil.ErrorLog("questionID---->"+questionID+"---question---->"+question);

			String quizXmlPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizXml="Quiz.xml";
			String startDate=null;
			String startTime=null;
			Calendar current=Calendar.getInstance();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateNow = formatter.format(current.getTime());
			String amt[]=dateNow.split(" ");
			String currentDate=amt[0];
			String currentTime=amt[1];

			Vector dateCollect=new Vector();
			File file1=new File(quizXmlPath+"/"+quizXml);
			QuizMetaDataXmlReader topipcmetadata=null;
			if(file1.exists()){
				/** read the xml file and put the all values in vector (datecollect)
				 *get quiz detail on the basis of the passed quizID
				 *@see xmlReader QuizMetaDataXmlReader in Util
                         	 */
				topipcmetadata=new QuizMetaDataXmlReader(quizXmlPath+"/"+quizXml);
				dateCollect=topipcmetadata.getQuiz_Detail(quizID);
					if(dateCollect!=null && dateCollect.size()!=0){
						for(int i=0;i<dateCollect.size();i++){
							startDate=((QuizFileEntry) dateCollect.elementAt(i)).getExamDate();
							startTime=((QuizFileEntry) dateCollect.elementAt(i)).getStartTime();
						}
					}
			}
			/**get question Bank file path where the questions stored*/
			File ff = new File(questionBankFilePath+"/"+questionBankQuestionsPath);
			if(!ff.exists())
				variable[0] = "empty";
			else{
				/**read the xml file and put the all values in vector (questionVector)
				 * gets all questions from question bank for random quiz on the basis of the passed QuestionType(mcq,tft,sat,lat)
				 *@see xmlReader QuizMetaDataXmlReader in Util
				 */
				QuizMetaDataXmlReader questionBankXmlReader;
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);
				/**check for the availability of question in question bank*/
				if(questionVector!=null){
					/**read the xml file and get id stored in QuizSettings.xml
					 *@see xmlReader QuizMetaDataXmlReader in Util
					 */
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					/**read the xml file and gets all inserted questions for random quiz
					 *@see xmlReader QuizMetaDataXmlReader in Util
					 *@return String[]
					 */
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
						/**append element in existing xml (quizid_questionSetting.xml) file
						 *@see QuizMetaDataXmlWriter in Util
						 *write xml in the given path
						 *@see XmlWriter (method: writeXmlFile()) in Util
						 */
						xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
						QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
						xmlWriter.writeXmlFile();
						/**check for insert question one bye one mode*/
						if(mode.equals("one")|quizMode.equals("one")){

							/**read existing xml file(quizID__Questions.xml) and write new xml file with old values
							 *append element in existing xml (quizid_questions.xml) file
                                                         *@see QuizMetaDataXmlWriter in Util
                                                 	 *write xml in the given path
                                                 	 *@see XmlWriter (method: writeXmlFile()) in Util
							 *modified by Jaivir/Manorama
                                                 	 */
							xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
							xmlWriter=QuizMetaDataXmlWriter.RandomQuizWriteTempxml(newFilePath,questionsPath,typeName);
							QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,min,max,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
							xmlWriter.writeXmlFile();
							variable[0]="success";
						}
						/**This method is responsible for updating quiz setting after first time question setting insertion
         					 * status is set to ACT and mode is random / one
						 */
						updateQuizRandomly(quizID, quizStatus, courseid,mode);
					}
					else{

						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="insert";
							/**read existing xml file(quizID_QuestionSetting.xml) and write new xml file with old values
							 *append element in existing xml (quizid_questionSetting.xml) file
							 **@see QuizMetaDataXmlWriter in Util
							 *write xml in the given path
                                                         *@see XmlWriter (method: writeXmlFile()) in Util
                                                         *modified by Jaivir/Manorama
                                                         */
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(newFilePath,questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,numberQuestion,id);
							xmlWriter.writeXmlFile();
							/**check for insert question one bye one mode*/
							if(mode.equals("one")|quizMode.equals("one")){

								/**read existing xml file(quizID__Questions.xml) and write new xml file with old values
                                                         	 *append element in existing xml (quizid_questions.xml) file
                                                         	 *@see QuizMetaDataXmlWriter in Util
                                                         	 *write xml in the given path
                                                         	 *@see XmlWriter (method: writeXmlFile()) in Util
                                                         	 *modified by Jaivir/Manorama
								 */
								xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
								xmlWriter=QuizMetaDataXmlWriter.RandomQuizWriteTempxml(newFilePath,questionsPath,typeName);
								QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,min,max,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
								xmlWriter.writeXmlFile();
								variable[0]="success";
							}
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont insert";
						else{
							variable[0]="insert";
							/**read existing xml file(quizID_QuestionSetting.xml) and write new xml file with old values
                                                         *append element in existing xml (quizid_questionSetting.xml) file
                                                         **@see QuizMetaDataXmlWriter in Util
                                                         *write xml in the given path
                                                         *@see XmlWriter (method: writeXmlFile()) in Util
                                                         *modified by Jaivir/Manorama
							 */
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
                                                        xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(newFilePath,questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
							xmlWriter.writeXmlFile();
							/**check for insert question one bye one mode*/
							if(mode.equals("one")|quizMode.equals("one")){
								/**read existing xml file(quizID__Questions.xml) and write new xml file with old values
                                                                 *append element in existing xml (quizid_questions.xml) file
                                                                 *@see QuizMetaDataXmlWriter in Util
                                                                 *write xml in the given path
                                                                 *@see XmlWriter (method: writeXmlFile()) in Util
                                                                 *modified by Jaivir/Manorama
                                                                 */
								xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
								xmlWriter=QuizMetaDataXmlWriter.RandomQuizWriteTempxml(newFilePath,questionsPath,typeName);
								QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,min,max,questionBankQuestionsPath,typeName,marksQuestion,Cur_date);
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
			/**set message according to the diffrent cases(empty,success,firstInsert,insert)
			 * and according to the mode random/one by one
			 * and set the updates messages also
			 *@see MultilingualUtil in Util
			 */
			if(variable[0].equalsIgnoreCase("empty"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_repository",LangFile));
			else if(variable[0].equalsIgnoreCase("success")){
				data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			}
			else if(variable[0].equalsIgnoreCase("firstInsert")){
					data.setMessage(MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
			}
			else if(variable[0].equalsIgnoreCase("insert")){
				if(mode.equals("random")|quizMode.equals("random")){
					if(startDate!=null && startTime!=null){
						if((currentDate.compareTo(startDate)==-1) && (currentTime.compareTo(startTime)==-1 ||  currentTime.compareTo(startTime)==0 || currentTime.compareTo(startTime)==1)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile)+" "+MultilingualUtil.ConvertedString("brih_updateAnnouncequizSave",LangFile));
						}
						else if((currentDate.compareTo(startDate)==0) && (currentTime.compareTo(startTime)==-1 ||  currentTime.compareTo(startTime)==0)){
						data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+	" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile)+" "+" "+MultilingualUtil.ConvertedString("brih_updateAnnouncequizSave",LangFile));
						}
						else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
						}
				 	}
					else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+MultilingualUtil.ConvertedString("QueBankUtil_msg1",LangFile));
				}
			}
			else if(variable[0].equalsIgnoreCase("dont insert")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_sono",LangFile));
			}
			if(variable[0].equalsIgnoreCase("empty")){
			}
			else{
				/**set templates according to the mode random/one by one*/
				if(page.equalsIgnoreCase("exit")){
					if(mode.equals("one"))
						data.setScreenTemplate("call,OLES,Oles_Gen.vm");
					if(quizMode.equalsIgnoreCase("random")|quizMode.equalsIgnoreCase("one"))
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
					else
						//data.setScreenTemplate("call,OLES,Create_Quiz.vm");{
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
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
			/** check the quizmode in xml and set quiz mode random*/
			if(quizMode.trim().isEmpty()){
				quizMode="random";
			}
			Vector collect=new Vector();
			/**read the xml file get all details of Quiz.xml
                         *@see xmlReader QuizMetaDataXmlReader in Util
                         */
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(newFilePath1+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					String quizName=((QuizFileEntry)collect.get(i)).getQuizName();
                                	String maxMarks=((QuizFileEntry)collect.get(i)).getMaxMarks();
                                	String maxTime=((QuizFileEntry)collect.get(i)).getMaxTime();
                                	String noQuestion=((QuizFileEntry)collect.get(i)).getnoQuestion();
                                	String status=((QuizFileEntry)collect.get(i)).getQuizStatus();
                                	String Filename=((QuizFileEntry)collect.get(i)).getQuizFileName();
                                	String CreationDate=((QuizFileEntry)collect.get(i)).getCreationDate();
                                	String modifiedDate=((QuizFileEntry)collect.get(i)).getModifiedDate();
                                	String Qmode=((QuizFileEntry)collect.get(i)).getQuizMode();
                                	String allowPractice = ((QuizFileEntry)collect.get(i)).getAllowPractice();
					if(quizid.equals(quizID)){
						/**read existing xml file(Quiz.xml) and write new xml file with old values
						 *and also update the quiz status and quiz mode in existing xml file
						 * and delete the onld entry from the xml(DeleteEntryinXml)
                                                 *@see QuizMetaDataXmlWriter in Util
                                                 *write xml in the given path
                                                 *@see XmlWriter (method: writeXmlFile()) in Util
                                                 *modified by Jaivir/Manorama
                                                 */
						xmlWriter=QuizMetaDataXmlWriter.QuizXml(newFilePath1,quizPath);
						QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizid,quizName,maxMarks,maxTime,noQuestion,"ACT",Filename,CreationDate,modifiedDate,quizMode,allowPractice);
						xmlWriter.writeXmlFile();
						Vector str=DeleteEntryinXml(newFilePath1,quizPath,quizid);
					}
				}
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
			/**get path where the Exam directory,quiz, quiz setting and quiz question file stored */
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
			String quizPath="/Quiz.xml";
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			String fileName = filepath+quizQuestionPath;

                        /**read the xml file get all details of Quiz.xml and put in vector (collect)
			 *@see QuizMetaDataXmlReader in Util
			 */
			QuizMetaDataXmlReader quizmetadata=null;
			quizmetadata=new QuizMetaDataXmlReader(filepath+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();
			/**by reading xml get the sequence of the entry*/
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					if(quizid.equals(quizID)){
						seq=i;
						break;
					}
				}
				/**read the xml file (score.xml) and put in vector(scoreVector)
				 *@see QuizMetaDataXmlReader in Util
				 *and by matching quizid check that quiz is attempted by any student or not
				 *if any student attempt that quiz then we cannot delete that quiz
				 */
				String quizid;
				//File scoreFile = new File(filepath+"/score.xml");
				File scoreFile = new File(filepath+"/"+quizID+"/score.xml");
				Vector<QuizFileEntry> scoreVector=new Vector<QuizFileEntry>();
				if(scoreFile.exists()){
					//quizmetadata=new QuizMetaDataXmlReader(filepath+"/score.xml");
					quizmetadata=new QuizMetaDataXmlReader(filepath+"/"+quizID+"/score.xml");

					scoreVector = quizmetadata.getDistinctIDFromFinalScore();
					if(scoreVector.size()!=0){
					for(QuizFileEntry a:scoreVector){
						quizid = a.getQuizID();
						if(quizid.equalsIgnoreCase(quizID)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotdeleted",LangFile));
							return;
						}
					}
					}
				}
				/**read existing xml file(Quiz.xml) and write new xml file with old values
                                 *and delete the  entry from the xml on the basis of seq
                                 *@see QuizMetaDataXmlWriter in Util
                                 *write xml in the given path
                                 *@see XmlWriter (method: writeXmlFile()) in Util
                                 *modified by Jaivir/Manorama
				 */
				xmlWriter=new XmlWriter(filepath+"/"+quizPath);
                                xmlWriter=QuizMetaDataXmlWriter.QuizXml(filepath,quizPath);
				xmlWriter.removeElement("Quiz",seq);
				/**get the path of the quiz directory which we want to delete
				 *delete the directory of deleted quiz
				 */
				File file=new File(filepath1);
				success = deleteDir(file);
				xmlWriter.writeXmlFile();
				data.setMessage(MultilingualUtil.ConvertedString("brih_unattempt",LangFile)+" "+MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));

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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=data.getParameters().getString("courseID","");
			String username=data.getUser().getName();
			String quizID=data.getParameters().getString("quizID","");
			String mode=data.getParameters().getString("mode","one");
			String maxMarks=data.getParameters().getString("maxMarks","");
			String maxnoQuestions=data.getParameters().getString("noQuestions","");
			String topicName = data.getParameters().getString("topicName","");
			String typeName = data.getParameters().getString("typeName","");
			String levelName = data.getParameters().getString("levelName","");

			String quizMode=data.getParameters().getString("quizMode","");
			/**get the path of question bank where the questions is stored */
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
			/**get path where the Exam directory,quiz, quiz setting and quiz question file stored */
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
				/**read the xml file (quizID__Questions.xml) and put in vector(quizQuestionsVector)(selectedQuestionID)
				 *gets all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
                                 *@see QuizMetaDataXmlReader in Util
				 */
				QuizMetaDataXmlReader quizXmlReader=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionsPath);
				if(mode.equals("update")){
					quizQuestionsVector = quizXmlReader.getInsertedQuizQuestions(id);
					selectedQuestionID = quizXmlReader.getInsertedQuizQuestionID(id);
				}
				else
					quizQuestionsVector = quizXmlReader.getInsertedQuizQuestions();
				/**read the xml file (questionBank) and put in vector (questionBankVector)
				 *gets all questions from question bank for random quiz
				 *@see QuizMetaDataXmlReader in Util
				 */
				QuizMetaDataXmlReader questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				questionBankVector = questionBankXmlReader.getRandomQuizQuestions(typeName);
				if(quizQuestionsVector!=null & questionBankVector!=null){
					for(int i=0;i<quizQuestionsVector.size();i++){
						String marks = ((QuizFileEntry) quizQuestionsVector.elementAt(i)).getMarksPerQuestion();
						marksQuestion = marksQuestion + Integer.parseInt(marks);
					}
					/**check the inserted no of question is equals to the no of questions defined in the quiz setting xml
					 *if not equals then add the more question otherwise not
					 */
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
				/**set messages according to the checks
				 *@see MultilingualUtil in utils
				 */
				if(var.equalsIgnoreCase("complete"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
				else if(var.equalsIgnoreCase("maxQuestion"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions+". "+MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
				else if(var.equalsIgnoreCase("maxMarks"))
					data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks+". "+MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
				else{
					/**put parameters in the context for use in template
					 *set template according to the mode
					 */
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
			/**Get parameters from template through Parameter Parser
			 *put in the context for use in template
                         */
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
			/**get path where the Exam directory,quiz, quiz setting file stored */
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";

			/** read the xml file and put in the hashmap
                         * get total counting and marks counting of already inserted questions
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         * @return hashmap
                         */
			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			HashMap hm = new HashMap();
			hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID,topicID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion"));
			/**check the entered question according to the total counting and marks counting of already inserted questions
			*/
			if(enteredQuestions < Integer.parseInt(maxnoQuestions) | mark < Integer.parseInt(maxMarks)){
				if(enteredQuestions==0){
					if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)){
						if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks))										/**updating quiz_questions setting (randomly) in a xml file*/
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
							/**updating quiz_questions setting (randomly) in a xml file*/
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
			String mode=data.getParameters().getString("mode","");

			ParameterParser pp=data.getParameters();
			String topicID = pp.getString("topicID","");
			String numberQuestion = pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");
			/**get the path of question bank where the questions is stored */
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
			Vector questionVector = new Vector();
			String[] insertedQuestionVector = new String[2];
			int seq=-1;
			/** read the xml file and put in the vector(collect)
			 *get quiz_questions detail from the quizID_QuestionSetting.xml
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         */
			Vector collect=new Vector();
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			collect=quizmetadata.getQuizQuestionDetail();
			/**get path where the Exam directory,quiz, quiz setting and quiz question file stored */
			String quizXmlPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizXml="Quiz.xml";
			String startDate=null;
			String startTime=null;
			Calendar current=Calendar.getInstance();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateNow = formatter.format(current.getTime());
			String amt[]=dateNow.split(" ");
			String currentDate=amt[0];
			String currentTime=amt[1];
			Vector dateCollect=new Vector();
			File file1=new File(quizXmlPath+"/"+quizXml);
			QuizMetaDataXmlReader topipcmetadata=null;
			/**check for the presence of xml file
			 *read the xml file and put in the vector(dateCollect)
			 *get quiz detail on the basis of the passed quizID
			 *@see xmlReader QuizMetaDataXmlReader in Util
			 */
			if(file1.exists()){
				topipcmetadata=new QuizMetaDataXmlReader(quizXmlPath+"/"+quizXml);
				dateCollect=topipcmetadata.getQuiz_Detail(quizID);
					if(dateCollect!=null && dateCollect.size()!=0){
						for(int i=0;i<dateCollect.size();i++){
							startDate=((QuizFileEntry) dateCollect.elementAt(i)).getExamDate();
							startTime=((QuizFileEntry) dateCollect.elementAt(i)).getStartTime();
						}
					}
			}
			/**if the entry exists in the xml file
			 *get sequence through topicid
			 */
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
			if(!ff.exists()){
				variable[0] = "empty";
			}
			else{
				/**read the xml file and put the all values in vector (questionVector)
                                 *gets all questions from question bank for random quiz on the basis of the passed QuestionType(mcq,tft,sat,lat)
                                 *@see xmlReader QuizMetaDataXmlReader in Util
                                 */
				QuizMetaDataXmlReader questionBankXmlReader;
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				XmlWriter xmlWriter = new XmlWriter(newFilePath+"/"+questionSettingPath);
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);
				/**check for the availability of question in question bank*/
				if(questionVector!=null){
					/**read the xml file and get id stored in QuizSettings.xml
                                         *@see xmlReader QuizMetaDataXmlReader in Util
                                         */
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					/**read the xml file and gets all inserted questions for random quiz
                                         *@see xmlReader QuizMetaDataXmlReader in Util
                                         *@return String[]
                                         */
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
						/**update file element in existing quizid_questionSetting.xml file with sequence number
        					 *and all updated variables values
                                                 *@see QuizMetaDataXmlWriter in Util
                                                 *write xml in the given path
                                                 *@see XmlWriter (method: writeXmlFile()) in Util
                                                 */
						xmlWriter=QuizMetaDataXmlWriter.Update_QuizQuestionSetting(newFilePath,questionSettingPath,seq,topicName,typeName,levelName,marksQuestion,questionNo,topicID);
						xmlWriter.writeXmlFile();
					}
					else{
						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="update";
							/**update file element in existing quizid_questionSetting.xml file with sequence number
                                                 	 *and all updated variables values
							 *modified by Jaivir/Manorama
                                                 	 */
							UpdateQuesSettingXml(newFilePath,questionSettingPath,topicID,topicName,typeName,levelName,marksQuestion,numberQuestion);
							//xmlWriter=QuizMetaDataXmlWriter.Update_QuizQuestionSetting(newFilePath,questionSettingPath,seq,topicName,typeName,levelName,marksQuestion,numberQuestion,topicID);
							//xmlWriter.writeXmlFile();
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont update";
						else{
							variable[0]="update";
							/**update file element in existing quizid_questionSetting.xml file with sequence number
                                                         *and all updated variables values
                                                         *@see QuizMetaDataXmlWriter in Util
                                                         *write xml in the given path
                                                         *@see XmlWriter (method: writeXmlFile()) in Util
                                                         */
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
			/**set the messages acording to the cases
			 *that questions available or not in the question bank
			 */
			if(variable[0].equalsIgnoreCase("empty"))

				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_repository",LangFile));
			else if(variable[0].equalsIgnoreCase("success"))
				data.setMessage(MultilingualUtil.ConvertedString("brih_questioninsertsuccess",LangFile));
			else if(variable[0].equalsIgnoreCase("firstUpdate")){
				if(mode.equals("update") && quizMode.equals("random")){
					if(startDate!=null && startTime!=null){
						if((currentDate.compareTo(startDate)==-1) && (currentTime.compareTo(startTime)==-1 ||  currentTime.compareTo(startTime)==0 || currentTime.compareTo(startTime)==1)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile)+" "+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
						}
						else if((currentDate.compareTo(startDate)==0) && (currentTime.compareTo(startTime)==-1 ||  currentTime.compareTo(startTime)==0)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile)+" "+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile));
						}
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile));
				}
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_questionupdatesuccess",LangFile));
			}
			/**set the messages when update the xml file(quiz.xml and quizquestionsetting.xml)
			 *set the message according to the mode and conditions
			 *@see MultilingualUtil in Util
			 */
			else if(variable[0].equalsIgnoreCase("update")){
				if(mode.equals("update") && quizMode.equals("random")){
					if(startDate!=null && startTime!=null){
						if((currentDate.compareTo(startDate)==-1)&&(currentTime.compareTo(startTime)==0 ||currentTime.compareTo(startTime)==-1 || currentTime.compareTo(startTime)==1)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("update_msg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
						}
						else if((currentDate.compareTo(startDate)==0)&&(currentTime.compareTo(startTime)==-1 || currentTime.compareTo(startTime)==0)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("update_msg",LangFile)+" "+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("update_msg",LangFile));
						}

					}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
								" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
								" "+MultilingualUtil.ConvertedString("update_msg",LangFile));
					}
			}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
							" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
							" "+MultilingualUtil.ConvertedString("brih_soonly",LangFile)+" "+variable[3]+" "+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
							" "+MultilingualUtil.ConvertedString("update_msg",LangFile));
				}
			}
			else if(variable[0].equalsIgnoreCase("dont update")){
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionrepo",LangFile)+" "+variable[1]+" "+MultilingualUtil.ConvertedString("oles_questions",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_and",LangFile)+" "+variable[2]+" "+MultilingualUtil.ConvertedString("brih_insertedquestionmsg",LangFile)+
						" "+MultilingualUtil.ConvertedString("brih_sonoupdate",LangFile));
			}

			if(variable[0].equalsIgnoreCase("empty")){
			}
			else{
				/**set the template according to the mode*/
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			/**get the count parameter of tab colour
                         *put in the context for use in template
                         */
			String count = data.getParameters().getString("count","");
			context.put("tdcolor",count);
			String username=data.getUser().getName();
			crsId=(String)data.getUser().getTemp("course_id");
			String course = (String)user.getTemp("course_name");
			String quizID = pp.getString("quizID","");
			/**get path where the Exam directory,quiztempquestions  and quiz question file stored
			 *check the presence of directory if not exists then make directory
			 */
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/"+quizID+"/");
			File ff=new File(filePath);
			if(!ff.exists())
				ff.mkdirs();
			/**get the path where the quiztempquestions xml stored
			 *ans also get the path of quizquestions xml
			 *if file exists then delete the file if user accept the preview
			 * and rename the quizID_Temp_Questions.xml into quizID_Questions.xml
			 *and questions saved in xml file
			 */
			String tempQuizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			File tempquizQuestionxmls=new File(filePath+"/"+tempQuizQuestionPath);
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			File QuizQuestionxmls=new File(filePath+"/"+quizQuestionPath);
			if(QuizQuestionxmls.exists()){
				QuizQuestionxmls.delete();
			}
			tempquizQuestionxmls.renameTo(QuizQuestionxmls);
			data.setMessage(MultilingualUtil.ConvertedString("brih_preview",LangFile)); // added by Ankita Dwivedi
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
			 * and parameters put in the context for use in template
                         */
                       //ErrorDumpUtil.ErrorLog("-----------OLES_Quiz action-----------showPreview Method------");
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
			/**get path where the Exam directory,quiz question file stored*/
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/"+quizID+"/");
			File ff=new File(filePath);
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			/**
			*/
			Vector question = new Vector();
			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(filePath+"/"+quizQuestionPath);
			/**check quizID director exists or not*/
			if(!ff.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_nopreview",LangFile));
				return;
			}
			/**check quizID_Questions.xml file exists or not*/
			File QuizQuestionxmls=new File(filePath+"/"+quizQuestionPath);
			if(!QuizQuestionxmls.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_nopreview",LangFile));
				return;
			}
			/**read xml file file
			 *get all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
			 *put in the context for use in template
			 *and set the template screen for show preview
			 */
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         * and parameters put in the context for use in template
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String count = data.getParameters().getString("count","");
			context.put("tdcolor",count);
			String quizID = data.getParameters().getString("quizID","");
			crsId=(String)data.getUser().getTemp("course_id");
			/**get path where the Exam directory,quiztempquestions  and quiz question file stored
			 *check the existence of tempquizQuestionxmls and delete it
			 */
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
			/**Get parameters from template through Parameter Parser
                        * get LangFile for multingual changes
                        */
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
			String quizDetail = pp.getString("quizDetail","");
			String quizSetting = pp.getString("quizSetting","");
			String[] temp = quizSetting.split(",");
			String id = temp[5];
			/**get parameters according to the question level and question type*/
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
			/**get path where the Exam directory,quiztempquestions  and quiz question file stored*/
			String quizFilePath=TurbineServlet.getRealPath("/Courses/"+courseID+"/Exam/"+quizID);
			String quizQuestionSettingPath=quizID+"_QuestionSetting.xml";
			String quizQuestionsPath=quizID+"_Questions.xml";
			String maxMarks = pp.getString("maxMarks","");

			/** read the xml file
                         * get total counting and marks counting of already inserted questions put in hashmap
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         */
			QuizMetaDataXmlReader quizXmlReader=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionSettingPath);
			HashMap insertedMarksHashMap = new HashMap();
			insertedMarksHashMap = quizXmlReader.getQuizQuestionNoMarks(quizXmlReader,quizID);
			int insertedMarksQuiz =((Integer)insertedMarksHashMap.get("marks"));
			/**check the marks of inserted question and max marks of the question from the setting xml
			 * according to that condition update file element in existing quizid_questionSetting.xml file with sequence number
        		 * and all updated variables values
			 */
			if(Integer.parseInt(marksQuestion)<Integer.parseInt(maxMarks)){
				if(Integer.parseInt(marksQuestion)+insertedMarksQuiz<Integer.parseInt(maxMarks)){
					XmlWriter xmlWriter=null;
					xmlWriter=new XmlWriter(quizFilePath+"/"+quizQuestionSettingPath);
					xmlWriter = QuizMetaDataXmlWriter.Update_QuizQuestionSetting(quizFilePath,quizQuestionSettingPath,(Integer.parseInt(id))-1,topicName,typeName,levelName,marksQuestion,questionNo,id);
					xmlWriter.writeXmlFile();
					/**update file element in existing quizid_question.xml file with sequence number
         				 * and all updated variables values
					 */
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName();
			String quizID=data.getParameters().getString("quizID","");
			String quizPath="Quiz.xml";
			//String allowPrac=data.getParameters().getString("allowPractice","");
			//ErrorDumpUtil.ErrorLog("allow ankita==== "+allowPractice);
			String courseid=(String)user.getTemp("course_id");
			/**get path of the Exam directory*/
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			QuizMetaDataXmlReader quizmetadata=null;
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			if(file.exists()){
				context.put("isFile","exist");
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				quizList=quizmetadata.listActiveAndCurrentlyNotRunningQuiz(filePath+"/"+quizPath,uname);
				if(quizList!=null && quizList.size()!=0){
					for(int i=0;i<quizList.size();i++){
						String qid=((QuizFileEntry) quizList.elementAt(i)).getQuizID();
						if(qid.equalsIgnoreCase(quizID)){
							String allowPractice =((QuizFileEntry) quizList.elementAt(i)).getAllowPractice();
								if(allowPractice.equalsIgnoreCase("yes")){
								//ErrorDumpUtil.ErrorLog("allowPractice"+allowPractice);		
									data.setMessage(MultilingualUtil.ConvertedString("brih_noneedpractice",LangFile));
									data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
									return;
								}
						}
					}
				}
			}// the above code of announceExam() is modified by ankita dwivedi as there is no need of announcing exam in practice quiz	
				
					//data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
				
			//==========functionality - if quiz is attempted then can not be reannounced===============
			/**reading the score xml get the information quiz is attempted by ant one or not
			 *if quiz is attempted then can not be reannounced
			 */
			String quizid;
			//File scoreFile = new File(filePath+"/score.xml");
			File scoreFile = new File(filePath+"/"+quizID+"/score.xml");
			Vector<QuizFileEntry> scoreVector=new Vector<QuizFileEntry>();
			if(scoreFile.exists()){
					//quizmetadata=new QuizMetaDataXmlReader(filePath+"/score.xml");
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizID+"/score.xml");
					scoreVector = quizmetadata.getDistinctIDFromFinalScore();
					if(scoreVector!=null){
					for(QuizFileEntry a:scoreVector){
						quizid = a.getQuizID();
						if(quizid.equalsIgnoreCase(quizID)){
							data.setMessage(MultilingualUtil.ConvertedString("brih_quizcannotreannounce",LangFile));
							data.setScreenTemplate("call,OLES,AnnounceExam_Manage.vm");
							return;
						}
					}
				}
			}
			//functionality code-if preview is not saved then quiz can't announced
			/**get path where the Exam directory,quiztempquestions  and quiz question file stored*/
			String previewFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String previewPath=quizID+"_Questions.xml";
			Vector previewDetail=new Vector();
			/**check the quiztempquestions xml exists or not
			 *if exists then by reading the xml file (if return null)
			 *check that preview is saved or not if preview is not saved
			 *then quiz can't announced
			 */
			File previewFile=new File(previewFilePath+"/"+previewPath);
			if(previewFile.exists()){
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
			String startDate = "",startTime = "",endDate = "",endTime = "",allowPractice = "",resDate = "";

			
			Vector quizDetail=new Vector();
			/**check the Quiz xml file exists or not
			 *get quiz detail on the basis of the passed quizID
			 * and put in the context for use in template
			 */
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
							resDate = ((QuizFileEntry) quizDetail.elementAt(i)).getResDate();
						}
					}
				}
			}
			
			String m = "";
			if(startDate==null & startTime==null & endDate==null & endTime==null & resDate==null){
				m="new";
			}
			else{
				m="update";
			}
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
			String quizID=pp.getString("quizID","");
			//maxTime modification since for timer functionality time must be in the format mm:ss
			int maxTime;
			String mode=pp.getString("mode","");
			String maxtime = pp.getString("maxTime","");
			if(maxtime.indexOf(":")==-1){
				maxTime=Integer.parseInt(pp.getString("maxTime",""));
			}
			else{
				String maxtimeArray[] = maxtime.split(":");
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

			String resYear = pp.getString("Res_year","");
			String resMonth = pp.getString("Res_mon","");
			String resDay = pp.getString("Res_day","");

			String endDate = endYear+"-"+endMonth+"-"+endDay;
			String endTime = endHour+":"+endMinute;
			String resDate = resYear+"-"+resMonth+"-"+resDay;

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
						if((Integer.parseInt(endHour)*60+Integer.parseInt(endMinute))-(Integer.parseInt(startHour)*60+Integer.parseInt(startMinute))>maxTime){
							flag = true;
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_announeerror",LangFile)+" ("+maxTime+" "+ MultilingualUtil.ConvertedString("brih_minutes",LangFile)+")");
						}
					}
					else{
						if((Integer.parseInt(endHour)*60+Integer.parseInt(endMinute))-(Integer.parseInt(startHour)*60+Integer.parseInt(startMinute))>maxTime){
							flag = true;
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_announeerror",LangFile)+" ("+maxTime+" "+ MultilingualUtil.ConvertedString("brih_minutes",LangFile)+")");
						}
						//flag = true;
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("Task_msg5",LangFile));

				}
			}
			else if(current.compareTo(examDate)==1){
				data.setMessage(MultilingualUtil.ConvertedString("brih_datemsg",LangFile));
			}

			if(resDate.compareTo(endDate)==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_resdatgrtEnd",LangFile)); //updated by ankita dwivedi
					return;
			}
			else if(resDate.compareTo(endDate)==-1){
				data.setMessage(MultilingualUtil.ConvertedString("brih_resdatgrtEnd",LangFile));	//updated by ankita dwivedi
				return;
			}
			/**In this part after get the start time/date end time/date and result data
			 *by reading xml  get all details of Quiz.xml
			 *according to the detail get the sequence of that quiz
			 *and update the start time/date end time/date and result data in the xml
			 */
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
					xmlWriter=QuizMetaDataXmlWriter.announceQuiz(filePath,quizPath,seq,quizID,startDate,startTime,endDate,endTime,resDate);
				}
				if(mode.equals("update"))
					data.setMessage(MultilingualUtil.ConvertedString("c_msg5",LangFile));
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_announced",LangFile));
				}
				/** This  method is responsible for sending mail to student to inform about the  quiz announcement
                          	*@see OnlineExamSystemMail (method:SendMail) in util
                          	*/
				String str=OnlineExamSystemMail.SendMail(courseID,data.getUser().getName(),"announce",startDate,startTime,"","",LangFile);
				if(str.equals("Success"))
				str=" "+MultilingualUtil.ConvertedString("mail_msg",LangFile);
                        	data.addMessage(str);
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
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			ParameterParser pp=data.getParameters();
			LangFile=(String)data.getUser().getTemp("LangFile");
			String courseid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxnoQuestions=pp.getString("noQuestions","");
			String numberQuestion=pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");
			/**get path where the Exam directory,quizquestionsSetting.xml  and quiz question file stored*/
			String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			File newFile=new File(newFilePath+"/"+questionSettingPath);
			XmlWriter xmlWriter=null;
			/** read the xml file
                         * get total counting and marks counting of already inserted questions put in hashmap
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
			 * inserting quiz_questions setting in a xml file( method:insertPreviewQuestionSetting)
                         */
			if(!newFile.exists())
				QuizMetaDataXmlWriter.OLESRootOnly(newFile.getAbsolutePath());
			QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
			HashMap hm = new HashMap();
			hm = questionReader.getQuizQuestionNoMarks(questionReader,quizID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion"));
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String username=data.getUser().getName();
			String courseid=(String)data.getUser().getTemp("course_id");

			String temptopicName = pp.getString("topicName","");
			String typeName = pp.getString("typeName","");
			String levelName = pp.getString("levelName","");
			String quizID=pp.getString("quizID","");
			String numberQuestion=pp.getString("numberQuestion","");
			String marksQuestion = pp.getString("marksQuestion","");

			String mode=data.getParameters().getString("mode","");
			String quizMode=data.getParameters().getString("quizMode","");
			String page = data.getParameters().getString("page","");
			String temp[]= temptopicName.split(",");
			String topicName=temp[0];
			username=temp[1];
			//String quizID=data.getParameters().getString("quizID","");
			pp.setString("count","3");
			String quizStatus="ACT";
			/**get path where the Exam directory,quizquestionsSetting.xml  and quiz question file stored*/
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
			if(!ff.exists())
				variable[0] = "empty";
			else{
				/**read the xml file and put the all values in vector (questionVector)
                                 * gets all questions from question bank for random quiz on the basis of the passed QuestionType(mcq,tft,sat,lat)
                                 *@see xmlReader QuizMetaDataXmlReader in Util
                                 */
				QuizMetaDataXmlReader questionBankXmlReader;
				questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
				questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);
				/**check for the availability of question in question bank*/
				if(questionVector!=null){
					/**read the xml file and get id stored in QuizSettings.xml
                                         *@see xmlReader QuizMetaDataXmlReader in Util
                                         */
					questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionSettingPath);
					String id = questionBankXmlReader.getID_RandomQuiz();
					/**read the xml file and gets all inserted questions for random quiz
                                         *@see xmlReader QuizMetaDataXmlReader in Util
                                         *@return String[]
                                         */
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
						/**append element in existing xml (quizid_questionSetting.xml) file
                                                 *@see QuizMetaDataXmlWriter in Util
                                                 *write xml in the given path
                                                 *@see XmlWriter (method: writeXmlFile()) in Util
                                                 */
						xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
						QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,questionNo,id);
						xmlWriter.writeXmlFile();
						/**This method is responsible for updating quiz setting after first time question setting insertion
                                                 * status is set to ACT and mode is random / one
                                                 */
						updateQuizRandomly(quizID, quizStatus, courseid, mode);

					}
					else{

						String questionNo = String.valueOf(questionVector.size()-Integer.parseInt(insertedQuestionVector[0]));
						if(Integer.parseInt(numberQuestion)<=Integer.parseInt(questionNo)){
							variable[0]="insert";
							/**append element in existing xml (quizid_questionSetting.xml) file
                                                 	 *@see QuizMetaDataXmlWriter in Util
                                                 	 *write xml in the given path
                                                 	 *@see XmlWriter (method: writeXmlFile()) in Util
                                                 	 */
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(newFilePath,questionSettingPath);
							QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,numberQuestion,id);
							xmlWriter.writeXmlFile();
							variable[3] = ""+numberQuestion;
						}
						else if(questionNo.equalsIgnoreCase("0"))
							variable[0]="dont insert";
						else{
							variable[0]="insert";
							/**append element in existing xml (quizid_questionSetting.xml) file
                                                         *@see QuizMetaDataXmlWriter in Util
                                                         *write xml in the given path
                                                         *@see XmlWriter (method: writeXmlFile()) in Util
                                                         */
							xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
							xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(newFilePath,questionSettingPath);
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
			/**set message according to the diffrent cases(empty,success,firstInsert,insert)
                         * and according to the mode random/one by one
                         * and set the updates messages also
                         *@see MultilingualUtil in Util
                         */
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
				/**set templates according to the mode random/one by one*/
				if(page.equalsIgnoreCase("exit")){
					if(mode.equals("one"))
						data.setScreenTemplate("call,OLES,Oles_Gen.vm");
					if(quizMode.equalsIgnoreCase("random")|quizMode.equalsIgnoreCase("one"))
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
					else
						//data.setScreenTemplate("call,OLES,Create_Quiz.vm");
						data.setScreenTemplate("call,OLES,Quiz_Detail.vm");
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

	/** This method is responsible for Delete Question Setting and Quiz Setting from xml files.
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 * @author Devendra singhal
	 */
	public void deleteQuestions(RunData data,Context context){
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp=data.getParameters();
			crsId=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String topicName=pp.getString("topicName","");
			String questionNumber=pp.getString("questionNumber","");
			String quizName=pp.getString("quizName","");
			String ID=pp.getString("ID","");
			String questionLevel=pp.getString("questionLevel","");
			String questionMarks=pp.getString("questionMarks","");
			String questionType=pp.getString("questionType","");
			String deltype = pp.getString("delType","");
			String mode=pp.getString("Mode","");
			String quizMode=pp.getString("quizMode","");
			boolean success;
			boolean flag=false;
			boolean flag1=false;
			int seq=-1;
			int seq1=-1;
			XmlWriter xmlWriter=null;
			XmlWriter xmlWriter1=null;
			Vector collect=new Vector();
			Vector questionCollect=new Vector();
			/**get path where the Exam directory,quizquestionsSetting.xml  and quiz question file stored*/
			String filePath=TurbineServlet.getRealPath("/Courses/"+crsId+"/Exam/"+quizID);
			String quizQuestionPath="/"+quizID+"_Questions.xml";
			String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
			QuizMetaDataXmlReader quizmetadata=null;
			File questionFile=new File(filePath+quizQuestionPath);
			File questionSettingFile=new File(filePath+quizQuestionSettingPath);

			String quizXmlPath=TurbineServlet.getRealPath("/Courses"+"/"+crsId+"/Exam/");
			String quizXml="Quiz.xml";
			String startDate=null;
			String startTime=null;
			String endDate=null;
			String endTime=null;
			Calendar current=Calendar.getInstance();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateNow = formatter.format(current.getTime());
			String amt[]=dateNow.split(" ");
			String currentDate=amt[0];
			String currentTime=amt[1];
			Vector dateCollect=new Vector();
			File file1=new File(quizXmlPath+"/"+quizXml);
			if(file1.exists()){
				/**read the xml file and get quiz detail on the basis of the passed quizID
				 *@see QuizMetaDataXmlReader in Util
				 */
				quizmetadata=new QuizMetaDataXmlReader(quizXmlPath+"/"+quizXml);
				dateCollect=quizmetadata.getQuiz_Detail(quizID);
					if(dateCollect!=null && dateCollect.size()!=0){
						for(int i=0;i<dateCollect.size();i++){
							startDate=((QuizFileEntry) dateCollect.elementAt(i)).getExamDate();
							startTime=((QuizFileEntry) dateCollect.elementAt(i)).getStartTime();
							endDate=((QuizFileEntry) dateCollect.elementAt(i)).getExpiryDate();
							endTime=((QuizFileEntry) dateCollect.elementAt(i)).getEndTime();
						}
					}
			}
			/**read the xml file and
			 *get quiz_questions detail from the quizID_Questions.xml except the specified topicid
			 *get sequence from the detail
                         *@see QuizMetaDataXmlReader in Util
			 */
			if(questionSettingFile.exists()){
				quizmetadata=new QuizMetaDataXmlReader(filePath+quizQuestionSettingPath);
				collect=quizmetadata.getQuizQuestionDetail(quizID);
				if(collect!=null && collect.size()!=0){
					for(int i=0;i<collect.size();i++){
						String id=((QuizFileEntry) collect.elementAt(i)).getID();
						String topic=((QuizFileEntry) collect.elementAt(i)).getTopic();
						String questionNumber1=((QuizFileEntry) collect.elementAt(i)).getQuestionNumber();
						if(id.equals(ID) && topic.equals(topicName) && questionNumber1.equals(questionNumber)){
							seq=i;
							break;
						}
					}
				}
			}
			/**read the xml file and
			 *gets all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
			 *get sequence from the detail
                         *@see QuizMetaDataXmlReader in Util
			 */
			if(mode.equals("update") && quizMode.equals("one")){
				if(questionFile.exists()){
					String file_Name=topicName+"_"+questionLevel+"_"+questionType+".xml";
					quizmetadata=new QuizMetaDataXmlReader(filePath+quizQuestionPath);
					questionCollect=quizmetadata.getInsertedQuizQuestions();
					if(questionCollect!=null && questionCollect.size()!=0){
						for(int j=0;j<questionCollect.size();j++){
							String questionID=((QuizFileEntry) questionCollect.elementAt(j)).getQuestionID();
							String questionMarks1=((QuizFileEntry) questionCollect.elementAt(j)).getMarksPerQuestion();
							String fileName=((QuizFileEntry) questionCollect.elementAt(j)).getFileName();
							if(fileName.equals(file_Name) && questionMarks1.equals(questionMarks)){
								seq1=j;
								break;
							}
						}
					}
				}
			}
			/** according to the above code information
			 * according to the seq number delete the entry from the xml
			 * and also related files and directories
			 */
			if(deltype.equals("quizDel")){
					if(mode.equals("update") && quizMode.equals("random")){
						if(seq >= 0){
						xmlWriter=new XmlWriter(filePath+quizQuestionSettingPath);
						//=====modified by seema=================================//
						xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(filePath,quizQuestionSettingPath);
						//=====modified by seema=================================//
						xmlWriter.removeElement("QuizQuestions",seq);
						xmlWriter.writeXmlFile();
						if(questionFile.exists()){
							success=questionFile.delete();
						}
								if(startDate!=null && startTime!=null){
									if((currentDate.compareTo(startDate)==-1) && (currentTime.compareTo(startTime)==0 ||currentTime.compareTo(startTime)==-1 || currentTime.compareTo(startTime)==1)){
										data.setMessage(MultilingualUtil.ConvertedString("brih_anuncquestiondelete",LangFile)+""+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
									}
									else if((currentDate.compareTo(startDate)==0) && (currentTime.compareTo(startTime)==-1 || currentTime.compareTo(startTime)==0)){
										data.setMessage(MultilingualUtil.ConvertedString("brih_anuncquestiondelete",LangFile)+""+MultilingualUtil.ConvertedString("brih_savepreview_reannounce",LangFile));
									}
									else{
										data.setMessage(MultilingualUtil.ConvertedString("brih_questiondelete",LangFile));
									}
								}
								else{
									data.setMessage(MultilingualUtil.ConvertedString("brih_questiondelete",LangFile));
								}
						}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_noQuestionTodelete",LangFile));
						return;
					}
				}
				/** according to the above code information
                         	* according to the seq number delete the entry from the xml
                         	* and also related files and directories
				* @see XmlWriter in Utils
                         	*/
				else if(mode.equals("update") && quizMode.equals("one")){
					if(seq1 >= 0){
						xmlWriter1=new XmlWriter(filePath+quizQuestionSettingPath);
						xmlWriter1=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(filePath,quizQuestionSettingPath);
						xmlWriter1.removeElement("QuizQuestions",seq);
						xmlWriter1.writeXmlFile();

						xmlWriter=new XmlWriter(filePath+quizQuestionPath);
						xmlWriter=QuizMetaDataXmlWriter.RandomQuizWriteTempxml(filePath,quizQuestionPath,"typename");
						xmlWriter.removeElement("QuizQuestions",seq1);
						xmlWriter.writeXmlFile();
						data.setMessage(MultilingualUtil.ConvertedString("brih_questiondelete",LangFile));
					}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_noQuestionTodelete",LangFile));
						return;
					}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_questionNotdelete",LangFile));
					return;
				}
			}
			else{
				data.setMessage(MultilingualUtil.ConvertedString("brih_questionNotdelete",LangFile));
				return;
			}
	 }
	 catch(Exception e){
		ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:deleteQuestions !!"+e);
		data.setMessage("See ExceptionLog !!");
	 }
   }
	/** This method is responsible for Delete quiz from xml files.
         * @param String filePath
         * @param String xmlfile
         * @param String quizID
	 * return Vector
         * @author Manorama Pal /Jaivir
         */
	public Vector DeleteEntryinXml(String filePath,String xmlfile,String quizID){
		Vector collect=null;
		try{
			/**reading the xml file and get detail
                         *and  select the seqence for deletion
			 *and delete the entry from xml according to the sequence number
                         *@see  TopicMetaDataXmlReader and XmlWriter in Utils
                         */
			XmlWriter xmlWriter = null;
			int seq=-1;
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
			collect=quizmetadata.getQuesBanklist_Detail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					if(quizid.equals(quizID)){
						seq=i;
						break;
					}
				}
			}
			xmlWriter=QuizMetaDataXmlWriter.QuizXml(filePath,xmlfile);
			xmlWriter.removeElement("Quiz",seq);
			xmlWriter.writeXmlFile();
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method: DeleteEntryinXml !! "+e);
		}
		return collect;
	}
	/**This method used to update the status of quiz if it is once created(Quiz.xml)
	 * and all updated variables values
         * @param String FilePath
         * @param String quizID
         * @param String maxMarks
         * @param String maxTime
         * @param String noQuestion
         * @param String modifiedDate
         * @author Manorama Pal /Jaivir
	 */
	public void UpdateQuizSetup(String FilePath,String quizID, String maxMarks,String maxTime,String noQuestion,String modifiedDate){
		try{
			/**read the xml file and get all detail
			 *@see QuizMetaDataXmlReader in utils
			 * according to detail get the sequence number
			 * and update the status of that sequence
			 * and delete the old once (method:DeleteEntryinXml )
			 */
			XmlWriter xmlWriter = null;
			String quizPath="/Quiz.xml";
			int seq=-1;
			Vector collect=new Vector();
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(FilePath+quizPath);
			collect=quizmetadata.getQuesBanklist_Detail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String quizid =((QuizFileEntry) collect.elementAt(i)).getQuizID();
					String quizName=((QuizFileEntry)collect.get(i)).getQuizName();
                                	String status=((QuizFileEntry)collect.get(i)).getQuizStatus();
                                	String Filename=((QuizFileEntry)collect.get(i)).getQuizFileName();
                                	String CreationDate=((QuizFileEntry)collect.get(i)).getCreationDate();
                                	String Qmode=((QuizFileEntry)collect.get(i)).getQuizMode();
                                	String allowPractice = ((QuizFileEntry)collect.get(i)).getAllowPractice();
					if(quizid.equals(quizID)){
						xmlWriter=QuizMetaDataXmlWriter.QuizXml(FilePath,quizPath);
						QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizid,quizName,maxMarks,maxTime,noQuestion,"ACT",Filename,CreationDate,modifiedDate,Qmode,allowPractice);
						xmlWriter.writeXmlFile();
						Vector str=DeleteEntryinXml(FilePath,quizPath,quizid);
					}
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:UpdateQuizSetup !! "+e);
		}
	}
	/** This method update file element in existing quizid_questionSetting.xml file with sequence number
         * and all updated variables values
         * @param String FilePath
         * @param String quesSettingPath
         * @param String topicID
         * @param String topicname
         * @param String questype
         * @param String queslevel
         * @param String quesMarks
         * @param String noofQues
         * @author Manorama Pal /Jaivir
	 */
	public void UpdateQuesSettingXml(String FilePath,String quesSettingPath,String topicID,String topicname,String questype,String queslevel,String quesMarks,String noofQues){
		try{
			XmlWriter xmlWriter = null;
			int seq=-1;
			Vector collect=new Vector();
			QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(FilePath+"/"+quesSettingPath);
			collect=quizmetadata.getQuizQuestionDetail();
			if(collect!=null){
				for(int i=0;i<collect.size();i++){
					String ID =((QuizFileEntry) collect.elementAt(i)).getID();
					if(ID.equals(topicID)){
						xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(FilePath,quesSettingPath);
						QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicname,questype,queslevel,quesMarks,noofQues,topicID);;
						xmlWriter.writeXmlFile();
						Vector str=DeleteEntryinQuesSettingXml(FilePath,quesSettingPath,topicID);
					}
				}
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:UpdateQuesSettingXml !! "+e);
		}
	}
	/** This method delete entry from quizid_questionSetting.xml file with sequence number
         * @param String filePath
         * @param String xmlfile
         * @param String topicID
         * @author Manorama Pal /Jaivir
         */
	 public Vector DeleteEntryinQuesSettingXml(String filePath,String xmlfile,String topicID){
                Vector collect=null;
                try{
			/**read the xml file and get all detail
                         *@see QuizMetaDataXmlReader in utils
                         * according to detail get the sequence number
                         * and delete the entry (xmlWriter method:removeElement)
                         */
                        XmlWriter xmlWriter = null;
                        int seq=-1;
                        QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                        collect=quizmetadata.getQuizQuestionDetail();
                        if(collect!=null){
                                for(int i=0;i<collect.size();i++){
                                        String ID =((QuizFileEntry) collect.elementAt(i)).getID();
                                        if(ID.equals(topicID)){
                                                seq=i;
                                                break;
                                        }
                                }
                        }
                        xmlWriter=QuizMetaDataXmlWriter.RandomWriteinQues_settingxml(filePath,xmlfile);
                        xmlWriter.removeElement("QuizQuestions",seq);
                        xmlWriter.writeXmlFile();
                }catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method: DeleteEntryinXml !! "+e);
                }
                return collect;
        }
	
	

}
