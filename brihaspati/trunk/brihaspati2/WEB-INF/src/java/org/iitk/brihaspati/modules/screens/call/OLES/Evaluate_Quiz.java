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
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

/**
 *   This class is used to show score of student after attempting the quiz
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Evaluate_Quiz extends SecureScreen{
//	MultilingualUtil mu=new MultilingualUtil();

	public void doBuildTemplate( RunData data,Context context ){
		ParameterParser pp=data.getParameters();
		String file=data.getUser().getTemp("LangFile").toString();
		try{
			boolean flag=false;
			User user=data.getUser();
			String uname=user.getName();
			String courseid=(String)user.getTemp("course_id");
			String uid=Integer.toString(UserUtil.getUID(uname));				
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);
			String count=pp.getString("count","");
			context.put("tdcolor",count);
			String type1=pp.getString("type","");
			ErrorDumpUtil.ErrorLog("type in evaluate Quiz"+type1);
			context.put("type",type1);
			
			String studentLoginName=pp.getString("studentLoginName","0");
			context.put("studentLoginName",studentLoginName);	
			ErrorDumpUtil.ErrorLog(" quizID, studentLoginName "+quizID+" : "+studentLoginName);	
			String studentID=Integer.toString(UserUtil.getUID(studentLoginName));
			String fullName = UserUtil.getFullName(Integer.valueOf(studentID));
			context.put("fullName",fullName);
//			Double passingMarks=0.0;			
//			Double passingPercentage = 33.0;
//			String finalResult="";
//			ErrorDumpUtil.ErrorLog("max marks "+maxMarks);
//			passingMarks = (Double.parseDouble(maxMarks)/100)*passingPercentage;
//			ErrorDumpUtil.ErrorLog("passing marks of student"+passingMarks);
//			
//			context.put("passingMarks",Math.round(passingMarks));
			Vector answerDetail=new Vector();
//			int studentMarks=0;
			//									
			//			if(QuizID.equals("")) {
			//				context.put("checkedQuiz","Uncheked");
			//				return;
			//			}
			//			context.put("checkedQuiz","cheked");

			String quizAnswerPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);
			String quizAnswerFile = studentID+".xml";						
			File answerFile= new File(quizAnswerPath+"/"+quizAnswerFile);
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
					for(int i=0;i<answerDetail.size();i++){
						String type=((QuizFileEntry)answerDetail.elementAt(i)).getQuestionType();
						if(type.equalsIgnoreCase("sat") || type.equalsIgnoreCase("lat")){
							flag=true;
							break;
						}
					}
					context.put("flag",flag);
					context.put("answerDetail",answerDetail);
//					for(int i=0;i<answerDetail.size();i++){
//						int studentMark = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getAwardedMarks());					
//						studentMarks +=studentMark;
//					}
//					ErrorDumpUtil.ErrorLog("total marks of student"+studentMarks);
//					context.put("studentMarks",studentMarks);
//					String percentageScore = String.valueOf((studentMarks*100)/(Integer.parseInt(maxMarks)));
//					context.put("percentageScore",percentageScore);
//					if(Integer.parseInt(percentageScore)>=passingPercentage)
//						finalResult="Pass";
//					else
//						finalResult="Fail";
//					context.put("finalResult",finalResult);
				}				
			}					
			/**
                         *Time calculaion for how long user use this page.
                         */
			
			String Role = (String)user.getTemp("role");	
			int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                CourseTimeUtil.getCalculation(userid);
                                ModuleTimeUtil.getModuleCalculation(userid);
                         }

		}	
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in detail Score Quiz file!!"+ex); 
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,file));
		}
	}
}


