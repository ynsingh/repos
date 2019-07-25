package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)AnnounceExam_Manage.java	
 *
 *  Copyright (c) 2010,2013 MHRD, DEI Agra,IITK. 
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
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
* This class displays the list of quizzes to announce/update that quiz
* @author <a href="mailto:ankitadwivedikit007@gmail.com">Ankita Dwivedi</a>
* @modify date:14aug2013 
*/

public class Certificate extends SecureScreen{
	public void doBuildTemplate(RunData data,Context context){
	String LangFile=data.getUser().getTemp("LangFile").toString();
	/**
        *Retrieve the Parameters by using the Parameter Parser
        *Get the UserName and put it in the context
        *for template use
        */
	String file=data.getUser().getTemp("LangFile").toString();
        ParameterParser pp=data.getParameters();
        try{
        	User user=data.getUser();
        	String uname=user.getName();
		context.put("uname",uname);
        	context.put("tdcolor","3");
        	String username=user.getName();
		int u_id=UserUtil.getUID(username);
		String id=Integer.toString(u_id);
		String fnme=UserUtil.getFullName(u_id);
                context.put("username",fnme);
		String courseid=(String)user.getTemp("course_id");	
		context.put("courseid",courseid); 
		String coursenm=(String)user.getTemp("course_name") ;     	
        	context.put("coursenm",coursenm);
		//String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
	        //String quizPath="Quiz.xml";
		String uid = "";
			String role=(String)user.getTemp("role");
			context.put("role",role);
			String type1=pp.getString("type","");
			context.put("type",type1);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String ResultDate=pp.getString("ResultDate","");
			context.put("ResultDate",ResultDate);
			ErrorDumpUtil.ErrorLog("resultdate of screen is !! "+ResultDate);
			String maxTime="";
			String maxMarks="";
			String maxQuestion="";
			String AllowPractice="";
			String quizPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/");
			if(role.equalsIgnoreCase("instructor")){
				context.put("setVisible","hidden");
				//String quizPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/");
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
					QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizPath+"/"+quizXmlPath);
					quizDetail = quizmetadata.getQuiz_Detail(quizID);
					if(quizDetail==null || quizDetail.size()==0){
						data.setMessage(MultilingualUtil.ConvertedString("brih_deletequizEntry",file));
					}
					else{
						for(QuizFileEntry a:quizDetail){
							 maxTime = a.getMaxTime();
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
					//String filePath1=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID);
					String filePath1=quizPath+"/"+quizID;
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
			Double passingMarks=0.0;
			Double passingPercentage = 33.0;
			String finalResult="";
			passingMarks = (Double.parseDouble(maxMarks)/100)*passingPercentage;

			context.put("passingMarks",Math.round(passingMarks));
			Vector answerDetail=new Vector();
			int studentMarks=0;
			//String quizAnswerPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);
			String quizAnswerPath=quizPath+"/"+quizID;
			if(role.equalsIgnoreCase("instructor")){
				String studentLoginName=pp.getString("studentLoginName","");
				context.put("studentLoginName",studentLoginName);
				uid=Integer.toString(UserUtil.getUID(studentLoginName));
			}
			else if(role.equalsIgnoreCase("student")){
				context.put("studentLoginName",uname);
				//uid=Integer.toString(UserUtil.getUID(studentLoginName));
				uid=Integer.toString(UserUtil.getUID(uname));
			}
			//------------------------------------------jaivir singh--------------------//
			String hbtn="";
			//String cid=(String)user.getTemp("course_id");
			//String examPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
                       	String scoreXml="score.xml";
			//File scorefile=new File(quizPath+"/"+scoreXml);
			File scorefile=new File(quizPath+"/"+quizID+"/"+scoreXml);
			QuizMetaDataXmlReader qdata=null;
			Vector scoreCollect=new Vector();
			String evaluate="";
			String qid="";
			if(scorefile.exists()){
                               	//qdata=new QuizMetaDataXmlReader(quizPath+"/"+scoreXml);
				qdata=new QuizMetaDataXmlReader(quizPath+"/"+quizID+"/"+scoreXml);
                               	scoreCollect=qdata.getFinalScore(uid);
                               	if(scoreCollect!=null && scoreCollect.size()!=0){
                                       	for(int i=0;i<scoreCollect.size();i++){
                                               	qid=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
						if(quizID.equals(qid)){
                                               		evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
						}
					}
				}
			}
			if(((evaluate!=null)&&(evaluate.equals("complete")))||(type1.equals("practice"))){
				//if(evaluate.equals("complete"){
			//------------------------------------------jaivir singh--------------------//
				String quizAnswerFile = uid+".xml";
				File answerFile= new File(quizAnswerPath+"/"+quizAnswerFile);
					if(!answerFile.exists()){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
						return;
					}
					else{
						QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizAnswerPath+"/"+quizAnswerFile);
						//answerDetail = quizmetadata.getFinalAnswer();

							ErrorDumpUtil.ErrorLog("Quiz_score called");
							String [] parts=quizID.split("_", 2);
							String qzowner=parts[1];
							ErrorDumpUtil.ErrorLog("Quiz_id available"+quizID+"   "+qzowner);
							String quesbankpath=TurbineServlet.getRealPath("/QuestionBank"+"/"+qzowner+"/"+courseid);
			/*
				@Anand Gupta
					Call getFinalAnswer() overloading method to display images.
			*/
						answerDetail = quizmetadata.getFinalAnswer(courseid,uname,quesbankpath);
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
							context.put("studentMarks",studentMarks);
							String percentageScore = String.valueOf((studentMarks*100)/(Integer.parseInt(maxMarks)));
							context.put("percentageScore",percentageScore);
							if(Integer.parseInt(percentageScore)>=passingPercentage)
								finalResult="Passed";
							else
								finalResult="Failed";
								context.put("finalResult",finalResult);
						}
					}
				//}//if evaluate complete
			}//if evaluate null
			else{
				hbtn="true";
				//data.setMessage("Quiz is not verified");
				data.setMessage(MultilingualUtil.ConvertedString("quiznverified",file));
			}
			context.put("hbtn",hbtn);
					}
	catch(Exception e) {
        	ErrorDumpUtil.ErrorLog("The exception in AnnounceExam_Manage screen::"+e);
        	data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));	
        }
}}
