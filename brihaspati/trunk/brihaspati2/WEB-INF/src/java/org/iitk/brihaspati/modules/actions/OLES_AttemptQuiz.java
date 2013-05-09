package org.iitk.brihaspati.modules.actions;
/*
 * @(#)OLES_AttemptQuiz.java	
 *
 *  Copyright (c) 2010,2013 DEI Agra, IITK. 
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Properties;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.MailNotification;

/**
 * This Action class is used to Generate Attempt quiz,practice quiz, score card and grade card module of online examination system 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a> 
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>28jan2013
 */
public class OLES_AttemptQuiz extends SecureAction{

	String CoursePath=TurbineServlet.getRealPath("/Courses");
	private String crsId=new String();
	private String LangFile=new String();	
	
	//--------------------to Generate Uniques 5 Digits Security String-------------------------
	//---------------------------------Devendra-----------------------------------------------
	//-------Start
	private static final char[] symbols = new char[36];
	  private static final Random random = new Random();
	  private static final char[] securityString=new char[5];
	  static {
	    for (int idx = 0; idx < 10; ++idx)
	      symbols[idx] = (char) ('0' + idx);
	    for (int idx = 10; idx < 36; ++idx)
	      symbols[idx] = (char) ('a' + idx - 10);
	  }
	  public static String generateSecurityString()
	  {
	    for (int idx = 0; idx < securityString.length; ++idx) 
	    securityString[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(securityString);
	  }
	  //------End
	  
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
		else if(action.equals("eventSubmit_Security"))
			securityString(data,context);
		else if(action.equals("eventSubmit_generateSecurity"))
			generateSecurity(data,context);
		else if(action.equals("eventSubmit_GetStudent"))
			GetCrsStudent(data,context);	
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
			/**get LangFile for multingual changes
			 * get user Id and user name
			 *@see UserUtil in Util
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID="";
			String maxTime="";
			/**Get parameters from template through Parameter Parser*/
			String ip=data.getParameters().getString("ip","");
			
			String quizIDTime = data.getParameters().getString("quizIDTime","");
			if(!quizIDTime.isEmpty()){
				String quizIDTimeArray[] = quizIDTime.split(",");				                               	
				quizID = quizIDTimeArray[0];        		
				maxTime = quizIDTimeArray[1];        						
			}
			else{
				quizID = data.getParameters().getString("quizID","");
			}
			String courseid=(String)user.getTemp("course_id");  
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");
			/**get path where the Exam directory,quiz setting and quiz question file stored */ 
			String quizFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String quizQuestionPath=quizID+"_Questions.xml"; 
			String userQuestionPath=uid+".xml";
			File quizQuestionFile=new File(quizFilePath+"/"+quizQuestionPath);
			Vector quizQuestionList=new Vector();
			QuizMetaDataXmlReader quizQuestionMetaData=null;
			
			if(questionVector==null || questionVector.size()==0){						
				if(!quizQuestionFile.exists()){
				}
				else{
					/**read xml file file
                         		 *get all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
					 *@see QuizMetaDataXmlReader in Util
					 * shuffle the question for the user (Login user)
					 * set Temp the Vector of shuffled question for the user 
                         		 *put in the context for use in template
                         		 */
					quizQuestionMetaData=new QuizMetaDataXmlReader(quizFilePath+"/"+quizQuestionPath);				
					quizQuestionList=quizQuestionMetaData.getInsertedQuizQuestions();
					if(quizQuestionList!=null && quizQuestionList.size()!=0){							
						Collections.shuffle(quizQuestionList);	
						context.put("quizQuestionList",quizQuestionList);   
						data.getUser().setTemp("questionvector",quizQuestionList);									
					}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_quizwithoutquestion",LangFile));	
					}           
				}
			}
			else{
				context.put("quizQuestionList",questionVector); 
			}	
			
			//----------------To Store the IP Address in Xml File----------------------
			//------------------------------DEVENDRA-----------------------------------
			/**get the path of quizID_Security xml 
			 *read the xml file get the sequence no
			 *and changing IP Address in xml file 
			 *@see QuizMetaDataXmlWriter in Util
			 */
			String securityPath=quizID+"_Security.xml";
			File securityFile=new File(quizFilePath+"/"+securityPath);
			if(securityFile.exists()){
				QuizMetaDataXmlReader reader=new QuizMetaDataXmlReader(quizFilePath+"/"+securityPath);
				QuizMetaDataXmlWriter writer=new QuizMetaDataXmlWriter();
				XmlWriter xmlwriter=new XmlWriter(quizFilePath+"/"+securityPath);
				String security="";
				String student="";
				int seq=-1;
				Vector col=reader.getSecurityDetail();
				if(col!=null && col.size()!=0){
					for(int i=0;i<col.size();i++){
						student=((QuizFileEntry) col.elementAt(i)).getStudentID();
						security=((QuizFileEntry) col.elementAt(i)).getSecurityID();
						if(student.equals(uname)){
							seq=i;
							break;
						}
					}
					xmlwriter=writer.WriteinSecurityxml(quizFilePath,securityPath);
					//writer.updateSecurity(xmlwriter,student,security,ip,seq);
					writer.updateSecurity(xmlwriter,student,security,ip,seq,quizFilePath,securityPath);
					//writer.updateSecurity(xmlwriter,student,security,ip,seq,quizFilePath,securityPath);
				}
			}
			/*else{
				
			}*/
			//----------------------------------END-------------------------------
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
			/**get LangFile for multingual changes*/
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName();
			/**Get parameters from template through Parameter Parser*/
			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID=data.getParameters().getString("quizID","");		
			String quesID=data.getParameters().getString("quesID","");		
			String fileName=data.getParameters().getString("fileName","");		
			String answer=data.getParameters().getString("finalAnswer","");	
			String quesType=data.getParameters().getString("quesType","");	
			String courseid=(String)user.getTemp("course_id"); 
			String markPerQues = data.getParameters().getString("markPerQues","");
			String type = data.getParameters().getString("type","");
			/**get path where the Exam directory,and quizAnswer file stored */
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String answerPath=uid+".xml"; 
			File quizAnswerFile=new File(answerFilePath+"/"+answerPath);
			/**responsible for writing student'a answer in userid.xml file*/
			if(type.equalsIgnoreCase("practice")){
				QuizMetaDataXmlWriter.xmlwriteFinalAnswerPractice(answerFilePath,answerPath,data);
			}  
			else{
				/**responsible for writing student'a answer in userid.xml file for general quiz*/
				QuizMetaDataXmlWriter.xmlwriteFinalAnswer(answerFilePath,answerPath,data);
			}	
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
			/**get LangFile for multingual changes*/
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName(); 
			/**Get parameters from template through Parameter Parser*/           
			String uid=Integer.toString(UserUtil.getUID(uname));
			String quizID=data.getParameters().getString("quizID","");		
			String courseid=(String)user.getTemp("course_id"); 
			//======================functionality to add unattended question in userid.xml
			addUnattendedQuestions(data,context);
			//======================================================================
			/**get path where the Exam score xml file stored */
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String answerPath="score.xml";
			/** this part is for writing final score.xml file */
			QuizMetaDataXmlWriter.xmlwriteFinalScore(answerFilePath,answerPath,data);
			user.setTemp("count","1");
			user.setTemp("questionvector",new Vector());
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
			/**get LangFile for multingual changes*/
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			/**Get parameters from template through Parameter Parser
			 * and update the timer value
			 *put in the context for use in template 
			 */
			String timerValue = pp.getString("timerValue","");
			String maxTime = pp.getString("maxTime","");
			context.put("maxTime",maxTime);
			String timerValueSession = (String)user.getTemp("timerValue"); 
			if(timerValue==null || timerValue.equalsIgnoreCase("")){
				if(timerValueSession==null || timerValueSession.equalsIgnoreCase("")){
					context.put("timerValue",maxTime);
				}
				else{
					context.put("timerValue",timerValueSession);
				}
			}
			else{
				context.put("timerValue",timerValue);
				user.setTemp("timerValue",timerValue);
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
			/**get LangFile for multingual changes
			 */
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));
			String courseid=(String)user.getTemp("course_id"); 
			String courseAlias=CourseUtil.getCourseAlias(courseid);
			String courseArray[]=courseid.split("_");
			int len = courseAlias.length();
			int len1 = courseArray[0].length();
			String instructorName = (courseid.substring(len, len1)).trim();
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
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");  
			//========================================================================
			/**get path where the Exam directory,quiz setting and quiz question file stored */
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			QuizMetaDataXmlReader quizmetadata=null;
			Vector allQuizSetting=new Vector();
			/** read the xml file and put the all values in vector (collect)
	                 *get quiz_questions detail from the quizID_QuestionSetting.xml 
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         */
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
						/**read the xml file and put the all values in vector (questionVector)
                                 		 * gets all questions from question bank for random quiz on the basis of the passed QuestionType(mcq,tft,sat,lat)
                                 		 *@see xmlReader QuizMetaDataXmlReader in Util
						 * and shuffle the question 
						 */
						questionReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+fileName);
						question = questionReader.getRandomQuizQuestions(questionType);

						for(int i=0;i<Integer.parseInt(noquestion);i++){
							Collections.shuffle(question);
							for(int k=0;k<question.size();k++){  
								found = false;
								QuizFileEntry q = question.get(k);
								q.setFileName(fileName);
								q.setMarksPerQuestion(markperquestion);
								q.setQuestionType(questionType);
								Iterator it = finalQuestion.iterator();
								while (it.hasNext()) {
									QuizFileEntry a = (QuizFileEntry) it.next();
									String que = a.getQuestion();
									String an = a.getAnswer();
									if (que.equals(q.getQuestion())&& an.equals(q.getAnswer())){ // Are they exactly the same instance?							    	 
										found=true;
										break;
									}
								}
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
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
			AttemptedpracticeQuizReport(data,context);						
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */	
			User user=data.getUser();
			LangFile=(String)data.getUser().getTemp("LangFile");
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");

			context.put("quizID",quizID);
			String loginname=user.getName();
			String userID=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			/**get path where the Exam directory,quiz setting and quiz question file stored */
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
//			Date dt = new Date();
//			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
//			String curDate = sd.format(dt);
			String evaluate="",userId="",quizId="";
			String resDate = "";
			String type = "";
			String check = "y";
			boolean flag=false;
			/** read the xml file and put the all values in vector (scoreCollect)
			 * gets all quizID,userID and score from score.xml file for a particular user
                         * @see xmlReader QuizMetaDataXmlReader in Util
			 * and set flag for evaluation 
                         */	
			if(file.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
				scoreCollect=quizmetadata.getFinalScore(userID);
				if(scoreCollect!=null && scoreCollect.size()!=0){
					for(int i=0;i<scoreCollect.size();i++){
						evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
						userId=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
						quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(evaluate!=null){
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
			/** read the xml file and put the all values in vector (collect)
                         *get quiz_questions detail from the quizID_QuestionSetting.xml 
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         */	
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
			 /** read the xml file and put the all values in vector (quizDetail)
			 *get quiz detail on the basis of the passed quizID
                         * @see xmlReader QuizMetaDataXmlReader in Util
                         */
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
									break;
								}
							}    						         
						}
					}
			}
			/**check for the set template page according to the falg value
			 * and also message by checking the result date about the evaluation
			 */		
			if(check.equals("n")){
				Calendar current = Calendar.getInstance();
				Calendar resDate_fin = Calendar.getInstance();
				String arr_resDate[]=resDate.split("-");
				resDate_fin.clear();
				resDate_fin.set(Integer.parseInt(arr_resDate[0]),(Integer.parseInt(arr_resDate[1])-1),Integer.parseInt(arr_resDate[2]));
				
				int i=resDate_fin.compareTo(current);
				if(resDate_fin.compareTo(current)==0 || resDate_fin.compareTo(current)==-1){
					if(flag){
						data.setScreenTemplate("call,OLES,Quiz_Score.vm");
					}
					else{
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String uname=user.getName();            
			String uid=Integer.toString(UserUtil.getUID(uname));		
			String courseid=(String)user.getTemp("course_id"); 
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");
			/**get path where the Exam directory,quiz uid xml(student answer file) file stored */ 
			String studentAnswerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String studentAnswerPath=uid+".xml";
			/** read the xml file and put the all values in vector (attemptedQuestion)
                         * gets all question ids and filepaths from final answer xml file(userid.xml)
                         * @see xmlReader QuizMetaDataXmlReader in Util
                         */ 
			File studentAnswerFile=new File(studentAnswerFilePath+"/"+studentAnswerPath);
			QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(studentAnswerFilePath+"/"+studentAnswerPath);
			Vector attemptedQuestion = quizreader.getFinalAnswer();
			if(attemptedQuestion==null || attemptedQuestion.size()==0){}
			else{
				if(attemptedQuestion.size()==questionVector.size()){
					questionVector.removeAllElements();
				}
				else{
					for(int i=0;i<attemptedQuestion.size();i++){
						for(int j=0;j<questionVector.size();j++){
							QuizFileEntry q1 = (QuizFileEntry)attemptedQuestion.get(i);
							QuizFileEntry q2 = (QuizFileEntry)questionVector.get(j);
							String quesID1 = q1.getQuestionID();
							String quesID2 = q2.getQuestionID();
							String fileName1 = q1.getFileName();
							String fileName2 = q2.getFileName();
							if(quesID1.equals(quesID2) && fileName1.equals(fileName2)){
								questionVector.removeElementAt(j);
								questionVector.trimToSize();
								break;
							}
						}
					}
				}
			}
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
				/**read existing xml file(uid.xml) and write new xml file with old values
                                *append final answers in existing xml (userid.xml) file
                                *@see QuizMetaDataXmlWriter in Util
                                *write xml in the given path 
                                *@see XmlWriter (method: writeXmlFile()) in Util
                                *modified by Jaivir/Manorama
                                */
				xmlWriter=QuizMetaDataXmlWriter.WriteinStudtAnswerxml(studentAnswerFilePath,studentAnswerPath,quesType,seq);
				QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesid,filename,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,quesType,seq);
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
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			User user=data.getUser();
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String count=pp.getString("count","");
			context.put("count",count);
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	

			String loginname=user.getName();
			String userID=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			/**get path where the Exam directory,quiz setting and score.xml file stored */
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
//			Date dt = new Date();
//			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); 
//			String curDate = sd.format(dt);
			String evaluate="",userId="",quizId="";
			String resDate = "";
			String type = "";
			String check = "y";
			boolean flag=false;
			/**This part read the xml file score.xml
			 *gets all quizID,userID and score from score.xml file for a particular user
                         *@see QuizMetaDataXmlReader  in utils
			 * check the evaluation is complete or not set falg for this
			 */
			if(file.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
				scoreCollect=quizmetadata.getFinalScore(userID);
				if(scoreCollect!=null && scoreCollect.size()!=0){
					for(int i=0;i<scoreCollect.size();i++){
						evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
						userId=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
						quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(evaluate!=null){
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
			/** read the xml file and put the all values in vector (collect)
                         *get quiz_questions detail from the quizID_QuestionSetting.xml 
                         * @see xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml) in Util
                         */
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
			/** read the xml file and put the all values in vector (quizDetail)
                         *get quiz detail on the basis of the passed quizID
                         * @see xmlReader QuizMetaDataXmlReader in Util
                         */
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
								break;
							}
						}    						         
					}
				}
			}
			/**check for the set template page according to the falg value
                         * and also message by checking the result date about the evaluation
                         */
			if(check.equals("n")){
				Calendar current = Calendar.getInstance();
				Calendar resDate_fin = Calendar.getInstance();
				String arr_resDate[]=resDate.split("-");
				resDate_fin.clear();
				resDate_fin.set(Integer.parseInt(arr_resDate[0]),(Integer.parseInt(arr_resDate[1])-1),Integer.parseInt(arr_resDate[2]));
				if(resDate_fin.compareTo(current)==0 || resDate_fin.compareTo(current)==-1){
					if(flag){
						data.setScreenTemplate("call,OLES,Report_Card.vm");
					}
					else{
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
				data.setScreenTemplate("call,OLES,Report_Card.vm");
			}	
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:showReportCard !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	/** This method is responsible for evaluate the quiz submitted by student
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */	
	public void evaluate(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
			 */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			/**get path where the Exam directory,quiz setting and quiz score.xml file stored */
			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String scoreXml="score.xml";
			String questionSettingPath=quizID+"_QuestionSetting.xml";
			Vector<QuizFileEntry> questionDetailVector=new Vector<QuizFileEntry>();
			File scoreFile = new File(scoreFilePath+"/"+scoreXml);
			int seq = 0;
			boolean flag=true;
			/**check for the quiz score.xml file exists or not*/
			if(!scoreFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				/**read the xml file and put in the vector(questionDetailVector)
                         	 *get quiz_questions detail from the quizID_QuestionSetting.xml
                         	 * @see xmlReader QuizMetaDataXmlReader (quizId_questionSetting.xml) in Util
                         	 */
				QuizMetaDataXmlReader typeReader= new QuizMetaDataXmlReader(scoreFilePath+"/"+quizID+"/"+questionSettingPath);
				questionDetailVector=typeReader.getQuizQuestionDetail(quizID);
				if(questionDetailVector!=null && questionDetailVector.size()!=0){
					/*Below line comment for verification of MCQType/TF type question*/ 
					/*for(int i=0;i<questionDetailVector.size();i++){
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
					}*/
					/**read the xml file and put in the vector(questionDetailVector)
					 *get sequence number from score.xml file with the same quizid and userid
					 * @see xmlReader QuizMetaDataXmlReader in Util
					 */
					QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
					seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
					if(seq==-1){
					data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));}
					else{
					data.setScreenTemplate("call,OLES,Evaluate_Quiz.vm");}
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionToevaluate",LangFile));
					return;
				}
			}									
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	/** This method is responsible for evaluate the quiz submitted by student
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
	public void evaluateQuestion(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String quizName=pp.getString("quizName","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			String quesID=pp.getString("quesID","");
			String fileName=pp.getString("fileName","");
			/**get path where the Exam directory,uid.xml file stored */
			String answerFilePath = TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/"+quizID+"/");
			String answerPath = uid+".xml";
			String evaluate=pp.getString("evaluate","partial");		
			File answerFile = new File(answerFilePath+"/"+answerPath);
			/**check answerfile not exists */
			if(!answerFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				/**writing marks given by instructor during evaluation in userid.xml file
				 *@see QuizMetaDataXmlWriter in Util
				 */
				QuizMetaDataXmlWriter.xmlwriteEvaluateMarks(answerFilePath,answerPath,data,evaluate);

			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:evaluate !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	/** This method is responsible for reevaluate the quiz if student applied for the reevaluation
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */	
	public void reevaluate(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */	
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			//String quizName=pp.getString("quizName","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			int seq=-1;
			/**get path where the Exam directory and xml file stored */
    			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
	        	String scorePath="score.xml";
	        	String usedTime="";
	        	String quizid="";
	        	String userID="";
	        	int totalScore=0;
	        	String evaluate=null;
			/**read the xml file and put in the vector(scoreDetail)
			 *get detail from score.xml file for the given quizid and userid
			 *@see xmlReader QuizMetaDataXmlReader in Util
			 */
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
			}		
	        	xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
	        	if(uid.equalsIgnoreCase(userID) && quizID.equalsIgnoreCase(quizid)){
			
				if(evaluate!=null && evaluate.equalsIgnoreCase("complete")){
	        			evaluate="ReEvaluate";
					/**This part read existing xml (score.xml)file and write new xml file with old values
                         		 *@see QuizMetaDataXmlWriter (method:QuizXml) in utils
                         		 *modified by Jaivir/Manorama
			 		 */
					xmlScoreWriter=QuizMetaDataXmlWriter.WriteinScorexml(scoreFilePath,scorePath);
					/**write(append/overwrite) final scores in existing xml (score.xml) file*/
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
	/** This method is responsible for show result 
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */	
	public void result(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");		
			String studentLoginName=pp.getString("studentLoginName","");
			/**get uid
			 *@see UserUtil in Util
			 */
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			/**get path of score.xml*/
			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String scoreXml="score.xml";
			File scoreFile = new File(scoreFilePath+"/"+scoreXml);
			int seq = 0;
			/**set message if score.xml file is not exists*/
			if(!scoreFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_quiznotattempted",LangFile));
				return;
			}
			else{
				/**read the xml file and put in the vector(questionDetailVector)
                                *get sequence number from score.xml file with the same quizid and userid
				* for set message and template screen
                                * @see xmlReader QuizMetaDataXmlReader in Util
                                */
				QuizMetaDataXmlReader quizreader= new QuizMetaDataXmlReader(scoreFilePath+"/"+scoreXml);
				seq = quizreader.getSeqOfAlreadyInsertedScore(scoreFilePath,scoreXml,quizID,uid);
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
	/** This method is responsible for evaluate the quiz 
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
	public void evaluateQuestionDone(RunData data,Context context){
		ParameterParser pp = data.getParameters();
		try{
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */	
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String type=pp.getString("type","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			int seq=-1;
			/**get path where the Exam directory,score.xml file stored */
    			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
	        	String scorePath="score.xml";
	        	String usedTime="";
	        	String quizid="";
	        	String userID="";
	        	int totalScore=0;
	        	String evaluate=null;
			/**read the xml file and put in vector(scoreDetail)
			*get detail from score.xml file for the given quizid and userid
			*@see QuizMetaDataXmlReader () in Util
			*/
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
	        }			
	        xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
	        if(uid.equalsIgnoreCase(userID) && quizID.equalsIgnoreCase(quizid)){
	        	evaluate="complete";
			/**This part read existing xml (score.xml)file and write new xml file with old values
                         *@see QuizMetaDataXmlWriter (method:QuizXml) in utils
                         *modified by Jaivir/Manorama
                         */
			xmlScoreWriter=QuizMetaDataXmlWriter.WriteinScorexml(scoreFilePath,scorePath);
			/**write(append/overwrite) final scores in existing xml (score.xml) file*/
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
	/** This method is responsible for the View AnswerSheet 
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
	public void answerSheet(RunData data, Context context){
		LangFile=(String)data.getUser().getTemp("LangFile");
		String type=data.getParameters().getString("type","");
		context.put("type",type);
	}
	/** This method is responsible for  to View the Security String
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */	
	public void securityString(RunData data, Context context){
		ParameterParser pp = data.getParameters();
		try{	
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
			LangFile=(String)data.getUser().getTemp("LangFile");
			String cid=(String)data.getUser().getTemp("course_id");
			String quizID=pp.getString("quizID","");
			String studentLoginName=pp.getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			pp.setString("flag","show");
			/**get path where the Exam directory,quizID+"_Security.xml file stored */
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/"+"/"+quizID);
			String securityFilePath=quizID+"_Security.xml";
			Vector collect=new Vector();
			File file=new File(filePath+"/"+securityFilePath);
			Vector quizList=new Vector();
			Vector instructorQuizList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			String securityString="";
			if(file.exists()){
				/**read the xml file and put the all values in vector (collect)
				 *gets the Detail of all SecurityStrings
				 *@see xmlReader QuizMetaDataXmlReader in Util
				 */
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+securityFilePath+"/");
				collect=quizmetadata.getSecurityDetail();
				if(collect !=null && collect.size()!=0){
					for(int i=0;i<collect.size();i++){
						String studentid=((QuizFileEntry) collect.elementAt(i)).getStudentID();
						if(studentid.equals(studentLoginName)){
							securityString=((QuizFileEntry) collect.elementAt(i)).getSecurityID();
							break;
						}
					}
					data.setMessage(MultilingualUtil.ConvertedString("brih_studentSecurity",LangFile)+" "+studentLoginName+" "+MultilingualUtil.ConvertedString("brih_is",LangFile)+" : "+securityString);
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noSecurity",LangFile));
				}
			}
			else{
				data.setMessage(MultilingualUtil.ConvertedString("brih_noSecurity",LangFile));
			}
			
			data.setScreenTemplate("call,OLES,SecurityString.vm");
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:securityString !! "+e);
			data.setMessage("See ExceptionLog !!");
		}
	}
	/** This method is responsible for  to generate the Security String
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
	public static void generateSecurity(RunData data, Context context){
		try{
			XmlWriter xmlWriter=null;
			/**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */ 
			String LangFile=(String)data.getUser().getTemp("LangFile");
			String courseID=(String)data.getUser().getTemp("course_id");
			ParameterParser pp=data.getParameters();
			String quizID=pp.getString("quizID",""); 
			String quizName=pp.getString("quizName","");
			//pp.setString("flag","generate");
			pp.setString("flag1","generate");
                        pp.setString("flag","security");
                        pp.setString("counttemp","1");
			String sendMail = pp.getString("sendMail","");	
			int g_id=GroupUtil.getGID(courseID);
			Vector userList=new Vector();
			Vector collectSecurity=new Vector();
			
			userList=UserGroupRoleUtil.getUDetail(g_id,3);
			//boolean flag=false;
			/**get path where the Exam directory,quizID_Security.xml file stored */ 
			String securityFile=quizID+"_Security.xml";
			String examFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseID+"/Exam/"+"/"+quizID);
			File securityFile1=new File(examFilePath+"/"+securityFile);
			QuizMetaDataXmlWriter createXmlfile=new QuizMetaDataXmlWriter();
			/**create the balnk xm file*/
			if(!securityFile1.exists()){
				createXmlfile.OLESRootOnly(examFilePath+"/"+securityFile);
			}
			/**read the xml file and put the all values in vector (collect)
                        *gets the Detail of all SecurityStrings
                        *@see xmlReader QuizMetaDataXmlReader in Util
			*if security string is not exists in xml then generate the security string
                        */
			if(securityFile1.exists()){
				QuizMetaDataXmlReader readSecurity=new QuizMetaDataXmlReader(examFilePath+"/"+securityFile+"/");
				XmlWriter xmlwrite=new XmlWriter(examFilePath+"/"+securityFile);
				collectSecurity=readSecurity.getSecurityDetail();
				for(int i=0;i<userList.size();i++){
					String IPAddress="";
					String securityID="";
					String student=((CourseUserDetail) userList.elementAt(i)).getLoginName();
					int uids=UserUtil.getUID(student);
					if(collectSecurity.size()==0){
						securityID=generateSecurityString();
					}
					if(!securityID.equals("")){
						/**writing Student ID,Secrity Strings and IP Addrss in xml file.
						 *@see QuizMetaDataXmlWriter in Util
						 */
						createXmlfile.writeSecurityString(xmlwrite,student,securityID,IPAddress);
						data.setMessage(MultilingualUtil.ConvertedString("brih_securitySuccess",LangFile));
						//flag=true;
					}
					else{
						data.setMessage(MultilingualUtil.ConvertedString("brih_allreadygenerateSecurity",LangFile));
					}
					/** This  part is responsible for sending mail to student to inform about the securitystring for Quiz
                          	 	*@see MailNotificationThread in util
                          	 	*/
					if((sendMail.equals("sendMail"))&&(!securityID.equals(""))){
						String subject="", msgDear="",msgRegard="",message="";
                        			String srvrPort=TurbineServlet.getServerPort();
						String email=UserUtil.getEmail(uids);
						String Crsname=CourseUtil.getCourseName(courseID);
						Properties pr =MailNotification.uploadingPropertiesFile(TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties"));
						if(srvrPort.equals("8080")){
							subject = MailNotification.subjectFormate("studentsecuritystring",quizName, pr );
							msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
                        				msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
							message = MailNotification.getQuizMessage("studentsecuritystring","","","",quizName,securityID,Crsname,pr);
						}
						else{
							subject = MailNotification.subjectFormate("studentsecuritystringhttps",quizName, pr );
							msgDear = pr.getProperty("brihaspati.Mailnotification.newUserhttps.msgDear");
                        				msgRegard=pr.getProperty("brihaspati.Mailnotification.newUserhttps.msgRegard");
							message = MailNotification.getQuizMessage("studentsecuritystringhttps","","","",quizName,securityID,Crsname,pr);
						}
                        			//String msgRegard=pr.getProperty("brihaspati.Mailnotification."+value+".msgRegard");
						msgRegard = MailNotification.replaceServerPort(msgRegard);
						msgDear = MailNotification.getMessage_new(msgDear, "","", "",student);
						String Mail_msg =MailNotificationThread.getController().set_Message(message,msgDear,msgRegard,"",email,subject,"",LangFile);
						if(Mail_msg.equals("Success")){
                                       			Mail_msg=" "+MultilingualUtil.ConvertedString("mail_msg",LangFile);
                                       			data.addMessage(Mail_msg);
						}
					}
				}
			}
			else{
				data.setMessage(MultilingualUtil.ConvertedString("brih_canNotgenerateSecurity",LangFile));
			}
		 }
		 catch(Exception ex){
			 ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:generateSecurity !! "+ex);
				data.setMessage("See ExceptionLog !!");
		 }
		
	}
	
	public void GetCrsStudent(RunData data, Context context){
		try{
			ParameterParser pp=data.getParameters();
			String qid=pp.getString("quizlist","");
			User user=data.getUser();	
			String cid=(String)user.getTemp("course_id");
			String examPath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
                        String scoreXml="score.xml";
                        File file=new File(examPath+"/"+scoreXml);
                        QuizMetaDataXmlReader quizmetadata=null;
                        Vector scoreCollect=new Vector();
                        Vector quizDetail=new Vector();
                        Vector collect=new Vector();
			Map map=new HashMap();
                        if(file.exists()){
				quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
				scoreCollect=quizmetadata.attemptedQuiz();
                                if(scoreCollect!=null && scoreCollect.size()!=0){
                                	for(int i=0;i<scoreCollect.size();i++){
                                		String quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(quizId.equals(qid)){
                                			String uid=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
							String sname=UserUtil.getFullName(Integer.parseInt(uid));
							map = new HashMap();
							map.put("quizId",quizId);
							map.put("studentLoginName",sname);
							collect.add(map);
						}
					}
				}
			}
			context.put("details",collect);
		}
		catch(Exception ex){
			 ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:GetCrsStudent !! "+ex);
			 data.setMessage("See ExceptionLog !!");
		}
	}

	/** This method is responsible for to write the detail of parctice quiz in xml file
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
        public static void AttemptedpracticeQuizReport(RunData data, Context context){
        	try{
                	XmlWriter xmlWriter=null;
                        /**Get parameters from template through Parameter Parser
                         * get LangFile for multingual changes
                         */
                        String username=data.getUser().getName();
                        String LangFile=(String)data.getUser().getTemp("LangFile");
                        String courseID=(String)data.getUser().getTemp("course_id");
                        ParameterParser pp=data.getParameters();
                        String quizID=pp.getString("quizID","");
                        String quizName=pp.getString("quizName","");

                        /**get path where the Exam directory,quizID_Security.xml file stored */
                        String PractinfoFile=quizID+"_PracticeQuizInfo.xml";
                        String examFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseID+"/Exam/"+"/"+quizID);
                        File PractinfoFile1=new File(examFilePath+"/"+PractinfoFile);
                        /**create the balnk xm file*/
                        if(!PractinfoFile1.exists()){
                                QuizMetaDataXmlWriter.OLESRootOnly(examFilePath+"/"+PractinfoFile);
                        }
                        /**read the xml file and put the all values in vector (collectPractinfo)
                         *gets the Detail of all parctice quiz
			 *@see xmlReader QuizMetaDataXmlReader in Util
                         *if exists in xml then  update  the information and  appened  also the  new entry
                         */
                        String attemptnos="",studentid="";
                        int attemptednos=1;
                        int seq=-1;
                        QuizMetaDataXmlReader readPractinfoFile=new QuizMetaDataXmlReader(examFilePath+"/"+PractinfoFile+"/");
                        Vector collectPractinfo=readPractinfoFile.getAttemptPracticeQuizDetail();
                        if(collectPractinfo!=null){
                                for(int i=0;i<collectPractinfo.size();i++){
                                        studentid=((QuizFileEntry)collectPractinfo.get(i)).getStudentID();
                                        attemptnos=((QuizFileEntry)collectPractinfo.get(i)).getNoofAttempt();
                                        if(studentid.equals(username)){
                                                attemptednos=Integer.parseInt(attemptnos)+1;
                                                seq=i;
                                                break;
                                        }
                                }

                        }
                        xmlWriter=new XmlWriter(examFilePath+"/"+PractinfoFile);
                        xmlWriter=QuizMetaDataXmlWriter.Write_PracticeQuizInfoxml(examFilePath,PractinfoFile);
                        QuizMetaDataXmlWriter.appendPracticeQuizInfo(xmlWriter,username,attemptednos,seq);
                        xmlWriter.writeXmlFile();
                }
		catch(Exception ex){
                         ErrorDumpUtil.ErrorLog("Error in Action[OLES_AttemptQuiz] method:AttemptedpracticeQuizReport !! "+ex);
                                data.setMessage("See ExceptionLog !!");
                 }
        }

}	                           
