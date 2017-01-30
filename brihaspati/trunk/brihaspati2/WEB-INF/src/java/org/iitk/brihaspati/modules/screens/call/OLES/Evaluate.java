package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Evaluate.java
 *
 *  Copyright (c) 2010-2011,2013 MHRD, DEI Agra, IITK. 
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
 *  Contributors: Members of MHRD, DEI Agra, IITK. 
 */


import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizDetail;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
 *@author  <a href="noopur.here@gmail.com">Nupur Dixit</a> 
 *@author  <a href="jaivirpal@gmail.com">Jaivir Singh</a>02May2013
 *@modified date: 22may2013(Manorama Pal) 
 */

public class Evaluate extends  SecureScreen{               
	public void doBuildTemplate(RunData data,Context context){
		ParameterParser pp=data.getParameters();
		String LangFile=(String)data.getUser().getTemp("LangFile");
		try{		
			User user=data.getUser();
			String uname=user.getName();
			String courseid=(String)user.getTemp("course_id");
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(uname);
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }
			/** Get parameters from template through Parameter Parser
			* put in context for use in template
			*/	
			String count = pp.getString("count","4");			
			context.put("tdcolor",count);
			String type = pp.getString("type","");
			context.put("type",type);
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String quizname = pp.getString("quizName","");
			context.put("quizName",quizname);
			String studentLoginName=pp.getString("studentLoginName","");
			/**get path where the quizand quiz xmlfile stored */
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizPath="/Quiz.xml";  
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector instructorQuizList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			if(!file.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToEvaluate",LangFile));
				return;
			}
			else{
				/** Gets the list of all the quizzes which are announced and announced time is over
				* put in context for use in template
				*/
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				quizList=quizmetadata.listAnnouncedAndExpiredQuiz();
				if(quizList!=null && quizList.size()!=0){
					for(int i=0; i<quizList.size();i++){
						String quizid = ((QuizFileEntry) quizList.elementAt(i)).getQuizID();
						String userName = quizid.substring((quizid.lastIndexOf("_")+1),(quizid.length()));
						if((userName.trim()).equalsIgnoreCase(uname.trim())){
							instructorQuizList.add((QuizFileEntry)quizList.get(i));
						}
					}
					context.put("quizList",instructorQuizList);
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToEvaluate",LangFile));
					return;
				}
			}
			if(!quizID.equals("")){
				String scoreXml="score.xml";
				Double passingMarks=0.0;
                       	 	Double passingPercentage = 33.0;
				String newfilepath=filePath+"/"+quizID;
				String questionSettingPath=quizID+"_QuestionSetting.xml";
                        	File f=new File(newfilepath+"/"+questionSettingPath);
                        	if(!f.exists()){
                                	data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionStoredToAttempt",LangFile));
                           		return;
                        	}
				quizmetadata=new QuizMetaDataXmlReader(newfilepath+"/"+questionSettingPath);

				/** Read the xml file and put in the vector (questionDetailVector)
                        	* get quiz_questions detail from the quizID_QuestionSetting.xml
				* set flag according to the type of quiz by this flag differtiate that this quiz need verification or not
                        	* @see xmlReader QuizMetaDataXmlReader (quizId_questionSetting.xml) in Util
                        	*/

				boolean flag=true;
                        	Vector questionDetailVector=quizmetadata.getQuizQuestionDetail(quizID);
                        	if(questionDetailVector!=null && questionDetailVector.size()!=0){
                                	for(int i=0;i<questionDetailVector.size();i++){
                                		String quizQuestionType=((QuizFileEntry)questionDetailVector.elementAt(i)).getQuestionType();
						if(!type.equals("result")){
                                 			if(quizQuestionType.equalsIgnoreCase("sat")||quizQuestionType.equalsIgnoreCase("lat")){
                                        			flag=false;
                                                		break;
                                       			}
                                        		else{
                                        			flag=true;
                                        		}
						}
						else{
							if(quizQuestionType.equalsIgnoreCase("mcq")||quizQuestionType.equalsIgnoreCase("tft")){
								flag=false;
							}
							else{
								flag=true;
							}
						}
                                	}//for questionDetailVectorloop
                                	if(flag){

						/* Set message according to the flag*/

						if(!type.equals("result")){
                                			data.setMessage(MultilingualUtil.ConvertedString("brih_noshtlngAnswer",LangFile));
				    	 	return;
						}
						else
                                		data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToverify",LangFile));
                                	}
					context.put("flag",flag);
				}//if questionDetailVectornullcondition
				else{
                        		data.setMessage(MultilingualUtil.ConvertedString("brih_noquestionToevaluate",LangFile));
                                	return;
                        	}
				/** Count marks of already inserted questions and put in context for use in template
				* @see xmlReader QuizMetaDataXmlReader (getQuizQuestionNoMarks) in Util
				*/
                        	HashMap hm = new HashMap();
                        	hm = quizmetadata.getQuizQuestionNoMarks(quizmetadata,quizID);
				String maxMarks =(String.valueOf(hm.get("marks")));
                        	passingMarks = (Double.parseDouble(maxMarks)/100)*passingPercentage;
                        	context.put("passingMarks",Math.round(passingMarks));
                        	context.put("maxMarks",maxMarks);

				/* Gets  quizID,userID, evaluation status and score from score.xml file
				* get path of institute profile directory get list configurationvalue
				* put all details in vector for use in template
				*/

				Vector collect=GetQuizAttemptStudentList(filePath,scoreXml,courseid,quizID,maxMarks);
				String  inst_id=(String)user.getTemp("Institute_id");
				String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir"+"/"+inst_id+"Admin.properties";
                        	String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
				int list_conf=Integer.parseInt(conf);
                        	context.put("userConf",new Integer(list_conf));
                        	context.put("userConf_string",conf);
                        	Vector vctrlist=CommonUtility.PListing(data ,context ,collect,list_conf);
				context.put("details",vctrlist);
			}//ifquizIdnull
		}//try
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in Evaluate screen ::"+e);
		}
	}
	/** This method gets  quizID,userID, evaluation status and score from score.xml file
	* @return Vector
	*/
	public static Vector GetQuizAttemptStudentList(String examPath,String scoreXml,String cid,String qid,String maxMarks){
 		Vector collect=new Vector();
		try{
			Double passingPercentage = 33.0;
			String finalResult="";
			QuizMetaDataXmlReader quizmetadata=null;
			Vector scoreCollect=new Vector();
                        File file=new File(examPath+"/"+scoreXml);      
			String quizAnswerPath=examPath+"/"+qid; 
			Map map=new HashMap();
                        if(file.exists()){
                                quizmetadata=new QuizMetaDataXmlReader(examPath+"/"+scoreXml);
                                scoreCollect=quizmetadata.attemptedQuiz();
                                if(scoreCollect!=null && scoreCollect.size()!=0){
                                        for(int i=0;i<scoreCollect.size();i++){
                                                String quizId=((QuizFileEntry) scoreCollect.elementAt(i)).getQuizID();
                                                if(quizId.equals(qid)){
							String evaluate=((QuizFileEntry) scoreCollect.elementAt(i)).getEvaluate();
                                                        String uid=((QuizFileEntry) scoreCollect.elementAt(i)).getUserID();
                                                        int studentMarks=Integer.parseInt(((QuizFileEntry) scoreCollect.elementAt(i)).getScore());

//                                                        String sname=UserUtil.getFullName(Integer.parseInt(uid));
                                                        String sname=UserUtil.getLoginName(Integer.parseInt(uid));
							String percentageScore = String.valueOf((studentMarks*100)/(Integer.parseInt(maxMarks)));
                                                       	if(Integer.parseInt(percentageScore)>=passingPercentage)
                                                       	finalResult="Pass";
                                                        else
                                                        finalResult="Fail";
                                                        map = new HashMap();
                                                        map.put("quizId",quizId);
                                                        map.put("studentLoginName",sname);
                                                       	map.put("studentMarks",studentMarks);
                                                       	map.put("percentageScore",percentageScore);
                                                       	map.put("finalResult",finalResult);
							if(evaluate!=null)
                                                       	map.put("evaluate",evaluate);
							else
                                                       	map.put("evaluate","notchecked");
							
                                                        collect.add(map);
                                                 }//if
                                       	}//for
                                }//if
			}//if
		}//try
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("Error in screen Evaluate[ method:GetQuizAttemptStudentList !!] "+ex);
		}
		return collect; 
	}//method
}//class			
