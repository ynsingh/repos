package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Preview.java
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
 *   Contributors: Members of MHRD, DEI Agra.
 *
 */
//Jdk
import java.util.Collections;
import java.io.File;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
//brihaspati
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.XmlWriter;

/**
 * This class manages the preview feature of quiz questions 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Preview extends  SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		/**
	        *Retrieve the Parameters by using the Parameter Parser
	        *Get the UserName and put it in the context
	        *for template use
	        */
	        ParameterParser pp=data.getParameters();
		String LangFile=data.getUser().getTemp("LangFile").toString();
		try{
			XmlWriter xmlWriter=null;
			User user=data.getUser();
			
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				 ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }

			String courseid=(String)user.getTemp("course_id");
			String courseName=(String)user.getTemp("course_name");
			String CoursePath=TurbineServlet.getRealPath("/Courses");
			String count = pp.getString("count","");
			String username=data.getUser().getName();
			String quizName = pp.getString("quizName","");
			String exist = "disabled";
			String quizDetail = pp.getString("quizDetail","");			
			String[] temp = quizDetail.split(",");
			String quizID = temp[0];			
			String maxMarks = temp[1];			
			String noQuestions = temp[2];	
			String maxTime = temp[3];
			
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			QuizMetaDataXmlReader insertedQuestionReader=null;
			File ff=new File(filePath);
			if(!ff.exists())
				ff.mkdirs();
			
			String questionPath = quizID+"_Questions.xml";
			String quizPath=quizID+"_QuestionSetting.xml";
			String quizQuestionPath="/"+quizID+"_Temp_Questions.xml";
			File QuizQuestionxmls=new File(filePath+"/"+quizQuestionPath);
			File questionXml = new File(filePath+"/"+questionPath);
			QuizQuestionxmls.deleteOnExit();
			if(QuizQuestionxmls.exists()) {
				QuizQuestionxmls.delete();				
			}
			if(questionXml.exists()){
				insertedQuestionReader=new QuizMetaDataXmlReader(filePath+"/"+questionPath);	
				Vector insertedQues = insertedQuestionReader.getInsertedQuizQuestions();
				if(insertedQues==null){
					exist="disabled";					
				}
				else{
					exist="enabled";
				}								
			}
			context.put("exist",exist);
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			context.put("course",courseName);
			context.put("quizDetail",quizDetail);
			context.put("quizID",quizID);
			context.put("quizName",quizName);
			context.put("maxMarks",maxMarks);
			context.put("noQuestions",noQuestions);
			context.put("maxTime",maxTime);
			context.put("quizMode","random");
			context.put("tdcolor",count);
			
			QuizMetaDataXmlReader quizmetadata=null;
			Vector allQuizSetting=new Vector();
			File f=new File(filePath+"/"+quizPath);
			if(!f.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquiz",LangFile));
				return;
			}
			quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);	
			HashMap hm = new HashMap();
			hm = quizmetadata.getQuizQuestionNoMarks(quizmetadata,quizID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion")); 
			context.put("enteredQuestion",enteredQuestions);
			context.put("marks",mark);               			
			allQuizSetting=quizmetadata.getQuizQuestionDetail();
			if(allQuizSetting==null){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquiz",LangFile));
				return;
			}	                
			String topicName,questionType,questionLevel,fileName,noquestion,markperquestion; 
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
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
				if(finalQuestion==null){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
					return;
				}	                
				String Quesid,Ques,opt1,opt2,opt3,opt4,Answer,markques,questionty,filename,type;
				opt1="";
				opt2="";
				opt3="";
				opt4="";				
				
				finalQues.addAll(finalQuestion);
				Iterator it = finalQues.iterator();
				if(finalQues.size()!=0){                   	
					context.put("finalq",finalQues);
				}
				while (it.hasNext()) {
					QuizFileEntry a = (QuizFileEntry) it.next();
					questionty = a.getQuestionType();
					Quesid = a.getQuestionID();
					Ques = a.getQuestion();
					Answer = a.getAnswer();
					markques = a.getMarksPerQuestion();
					filename = a.getFileName();
					if(questionty.equalsIgnoreCase("mcq")){
						opt1=a.getOption1();
						opt2=a.getOption2();					
						opt3=a.getOption3();
						opt4=a.getOption4();
					}
					/**writing temporary xml file for final question list
					 *@see QuizMetaDataXmlWriter in Util
					 */
					QuizMetaDataXmlWriter.xmlwriteFinalQuestion(filePath,quizQuestionPath,Quesid,Ques,opt1,opt2,opt3,opt4,Answer,markques,filename,questionty,Cur_date);
				} 
			}
		}
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in Preview screen::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));	
		}
	}
}			
