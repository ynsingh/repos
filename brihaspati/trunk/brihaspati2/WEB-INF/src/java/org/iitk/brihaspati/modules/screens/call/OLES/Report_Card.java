package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Report_Card.java	
 *
 *  Copyright (c) 2010,2013 MHRD, DEI Agra, IITK .
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
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
 *   This class is used to show report card of student after attempting the quiz
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 *   @author  <a href="jaivirpal@gmail.com">Jaivir Singh</a>
 *   @author  <a href="palseema30@gmail.com">Manorama Pal</a>
 */

public class Report_Card extends SecureScreen{
	public void doBuildTemplate( RunData data,Context context ){
		ParameterParser pp=data.getParameters();
		String file=data.getUser().getTemp("LangFile").toString();
		try{
			/**Get user name and put in context for use in template*/			
			User user=data.getUser();
			String uname=user.getName();
			context.put("userName",uname);
			ErrorDumpUtil.ErrorLog("report card screen");

			/**Get CourseId from temp*/
			String courseid=(String)user.getTemp("course_id");

			/**Get userid according to User name
                         *@see UserUtil in utils
                         */
			String uid=Integer.toString(UserUtil.getUID(uname));
			ErrorDumpUtil.ErrorLog("user id is :"+uid);
			int index = courseid.lastIndexOf("_");
			int instID = Integer.valueOf(courseid.substring(index+1, courseid.length()));
			ErrorDumpUtil.ErrorLog("institute id is :"+instID);
			String rollNo="";

			/*Select Roll Number from 'StudentRollno' table */
			Criteria crit=new Criteria();
			crit.add(StudentRollnoPeer.EMAIL_ID,uname);
			crit.add(StudentRollnoPeer.INSTITUTE_ID,instID);
			ErrorDumpUtil.ErrorLog("criteriaIS :"+crit);
			List v=StudentRollnoPeer.doSelect(crit);

			/*Check Rollno. exist or not */

			if(v.size()!=0){
				StudentRollno element=(StudentRollno)v.get(0);
				rollNo=element.getRollNo().toString();
			}
			String fullName = UserUtil.getFullName(Integer.valueOf(uid));
			context.put("fullName",fullName);
			context.put("rollNo",rollNo);

			/**Get Quiz details by the ParameterParser and
                         *put in context to use in templates
                         */
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);	
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);	
			String maxTime=pp.getString("maxTime","");
			context.put("maxTime",maxTime);	
			String maxMarks=pp.getString("maxMarks","");				
			String maxQuestion=pp.getString("maxQuestion","");
			String marksQuestions = pp.getString("maxMarksQuestion","");
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

			/**Find the Path where the 'quiz answer file' and 'score.xml'  exists */
			String quizAnswerPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);
			String quizAnswerFile = uid+".xml";	
			String quizPath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/");
			String quizXmlFile = "score.xml";
			String usedTime="00:00";
			File answerFile= new File(quizAnswerPath+"/"+quizAnswerFile);
			File quizFile= new File(quizPath+"/"+quizXmlFile);

			/**Check the "score.xml" file exists or not */
			if(!quizFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
				return;
			}
			else{

				/**Get details from "score.xml" file and store in a vector. */
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
			String hbtn="";
                        String cid=(String)user.getTemp("course_id");
                        String examPath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
                        String scoreXml="score.xml";
                        File scorefile=new File(examPath+"/"+scoreXml);
                        QuizMetaDataXmlReader qdata=null;
                        Vector scoreCollect=new Vector();
                        String evaluate="";
                        String qid="";
                        if(scorefile.exists()){
                                qdata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
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
                        if(evaluate!=null){
                                if(evaluate.equals("complete")){
					if(!answerFile.exists()){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
						return;
					}
					else{
						/**Get the Details from student uid.xml store in a vector*/
						QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizAnswerPath+"/"+quizAnswerFile);
						answerDetail = quizmetadata.getFinalAnswer();
						/**If file is empty, set message else put in context for use in template*/
						if(answerDetail==null || answerDetail.size()==0){
							data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionAttempt",file));
							return;
						}
						else{
							/**Get the AwardedMarks and MarksPerQuestion
                                          		*calculate the number of correct answer and percentage
                                          		*/
							context.put("answerDetail",answerDetail);
							for(int i=0;i<answerDetail.size();i++){
								int studentMark = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getAwardedMarks());
								int marksPerQues = Integer.parseInt(((QuizFileEntry) answerDetail.elementAt(i)).getMarksPerQuestion());
								if(studentMark==marksPerQues){
									noCorrectAns = noCorrectAns + 1;
								}
								studentMarks +=studentMark;
							}
							/**put in context for use in template*/
							context.put("noCorrectAns",noCorrectAns);
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
				}//if evaluate complete                                                                                 
                        }//if evaluate null
                        else{
                                hbtn="true";
                                data.setMessage(MultilingualUtil.ConvertedString("quiz_reportcard",file));
                        }
                                context.put("hbtn",hbtn);				
			/**
                         *Time calculaion for how long user use this page.
                         */
			 String Role = (String)user.getTemp("role");
			 int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				 ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }
							
		}	
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in report card file!!"+ex); 
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,file));
		}
	}
}


