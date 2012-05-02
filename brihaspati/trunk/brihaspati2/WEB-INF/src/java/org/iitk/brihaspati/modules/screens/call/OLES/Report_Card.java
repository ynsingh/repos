package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Report_Card.java	
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
import java.util.List;
import java.io.File;
import java.lang.Math;

//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.util.Criteria;

//brihaspati
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.StudentRollnoPeer;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
/**
 *   This class is used to show report card of student after attempting the quiz
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Report_Card extends SecureScreen{
	//	MultilingualUtil mu=new MultilingualUtil();

	public void doBuildTemplate( RunData data,Context context ){
		ParameterParser pp=data.getParameters();
		String file=data.getUser().getTemp("LangFile").toString();
		try{			
			User user=data.getUser();
			String uname=user.getName();
			context.put("userName",uname);
			String courseid=(String)user.getTemp("course_id");
			String uid=Integer.toString(UserUtil.getUID(uname));
			ErrorDumpUtil.ErrorLog("user id is :"+uid);
			int index = courseid.lastIndexOf("_");
			int instID = Integer.valueOf(courseid.substring(index+1, courseid.length()));
			ErrorDumpUtil.ErrorLog("institute id is :"+instID);
			String rollNo="";
			Criteria crit=new Criteria();
			crit.add(StudentRollnoPeer.EMAIL_ID,uname);
			crit.add(StudentRollnoPeer.INSTITUTE_ID,instID);
			ErrorDumpUtil.ErrorLog("criteriaIS :"+crit);
			List v=StudentRollnoPeer.doSelect(crit);
			StudentRollno element=(StudentRollno)v.get(0);
			rollNo=element.getRollNo().toString();
			ErrorDumpUtil.ErrorLog("roll no is :"+rollNo);
			String fullName = UserUtil.getFullName(Integer.valueOf(uid));
			context.put("fullName",fullName);
			ErrorDumpUtil.ErrorLog("full name is :"+fullName);
			context.put("rollNo",rollNo);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			String maxTime=pp.getString("maxTime","");
			context.put("maxTime",maxTime);	
			String maxMarks=pp.getString("maxMarks","");				
			String maxQuestion=pp.getString("maxQuestion","");
			String marksQuestions = pp.getString("maxMarksQuestion","");
			ErrorDumpUtil.ErrorLog("all variables :"+quizName +" :"+quizID+" :"+maxTime+" :"+maxMarks+" :"+maxQuestion+" :"+marksQuestions);
			//			String temp[] = marksQuestions.split(",");
			//			if(maxMarks.isEmpty()){
			//				maxMarks = temp[1];
			//				maxQuestion = temp[2];
			//			}
			//==========================================================
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
			int noCorrectAns = 0;
			context.put("passingMarks",Math.round(passingMarks));
			Vector answerDetail=new Vector();
			int studentMarks=0;
			String quizAnswerPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);
			String quizAnswerFile = uid+".xml";	
			String quizPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/");
			String quizXmlFile = "score.xml";
			String usedTime="00:00";
			File answerFile= new File(quizAnswerPath+"/"+quizAnswerFile);
			File quizFile= new File(quizPath+"/"+quizXmlFile);
			if(!quizFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
				return;
			}
			else{
				QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizPath+"/"+quizXmlFile);
				Vector quizDetail = quizmetadata.getFinalScore(uid);
				if(quizDetail==null || quizDetail.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
					return;
				}
				else{
					for(int i=0;i<quizDetail.size();i++){
						String quizid = (((QuizFileEntry) quizDetail.elementAt(i)).getQuizID());
						if(quizid.equalsIgnoreCase(quizID)){
							usedTime = (((QuizFileEntry) quizDetail.elementAt(i)).getUsedTime());
							ErrorDumpUtil.ErrorLog("\n used time is :"+usedTime); 
							break;
						}
						
					}
					context.put("usedTime",usedTime);
				}
			}
			if(!answerFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
				return;
			}
			else{
				QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizAnswerPath+"/"+quizAnswerFile);
				answerDetail = quizmetadata.getFinalAnswer();
				if(answerDetail==null || answerDetail.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
					return;
				}
				else{
					context.put("answerDetail",answerDetail);
					for(int i=0;i<answerDetail.size();i++){
						int studentMark = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getAwardedMarks());
						int marksPerQues = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getMarksPerQuestion());
						if(studentMark==marksPerQues){
							noCorrectAns = noCorrectAns + 1;
						}
						studentMarks +=studentMark;
					}
					ErrorDumpUtil.ErrorLog("total no of correct answer of student"+noCorrectAns);
					context.put("noCorrectAns",noCorrectAns);
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
			 String Role = (String)user.getTemp("role");
			 int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				 MailNotificationThread.getController().CourseTimeSystem(userid);
                         }
							
		}	
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in report card file!!"+ex); 
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,file));
		}
	}
}


