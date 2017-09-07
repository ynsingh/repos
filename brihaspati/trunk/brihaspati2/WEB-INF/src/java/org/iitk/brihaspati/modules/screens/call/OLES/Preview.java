package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Preview.java
 *
 *  Copyright (c) 2010-13 MHRD, DEI Agra.
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
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.*;

/**
 * This class manages the preview feature of quiz questions 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modify date:14aug2013 
 */

public class Preview extends  SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		/**
	        *Retrieve the Parameters by using the Parameter Parser
	        *Get the UserName and put it in the context
	        *for template use
	        */
//        ErrorDumpUtil.ErrorLog("------Preview.java------");

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
          		// ErrorDumpUtil.ErrorLog("----Preview.java------CourseName---->"+courseid);
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

               // ErrorDumpUtil.ErrorLog("------Preview.java------1");
			QuizQuestionxmls.deleteOnExit();
			if(QuizQuestionxmls.exists()) {
                //ErrorDumpUtil.ErrorLog("------Preview.java------2");
				QuizQuestionxmls.delete();				
			}
                //ErrorDumpUtil.ErrorLog("------Preview.java------3");
			if(questionXml.exists()){
				insertedQuestionReader=new QuizMetaDataXmlReader(filePath+"/"+questionPath);	
				Vector insertedQues = insertedQuestionReader.getInsertedQuizQuestions();
                //ErrorDumpUtil.ErrorLog("------Preview.java------insertedQues--->");
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
            		//ErrorDumpUtil.ErrorLog("------Preview.java------allQuizSetting---->"+allQuizSetting);
			if(allQuizSetting==null){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquiz",LangFile));
				return;
			}	                
			String topicName,questionType,questionLevel,fileName,noquestion,markperquestion;
			String tempusername[]=quizID.split("_"); 
			username=tempusername[1];
			String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
			QuizMetaDataXmlReader questionReader=null;
            		//ErrorDumpUtil.ErrorLog("------Preview.java------3");
			Vector<QuizFileEntry> question=new Vector<QuizFileEntry>();
			Vector<QuizFileEntry> finalQues = new Vector<QuizFileEntry>();
			Set finalQuestion = new TreeSet();
			boolean found = false;
			int ans = 0;
            //20-02-2017
			if(allQuizSetting!=null & allQuizSetting.size()!=0){	        		
				for(int j=0;j<allQuizSetting.size();j++){
					topicName = (((QuizFileEntry) allQuizSetting.elementAt(j)).getTopic());
					questionLevel = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionLevel());
					questionType = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionType());
					noquestion = (((QuizFileEntry) allQuizSetting.elementAt(j)).getQuestionNumber());
					markperquestion = (((QuizFileEntry) allQuizSetting.elementAt(j)).getMarksPerQuestion());
                    //ErrorDumpUtil.ErrorLog("------Preview.java------4"+"topicName--->"+topicName+"---questionLevel--->"+questionLevel+"---questionType--->"+questionType+"---Noof Ques--->"+noquestion);

					fileName = topicName +"_"+questionLevel+"_"+questionType+".xml";
                    //ErrorDumpUtil.ErrorLog("------Preview.java------5---fileName--->"+fileName);
					questionReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+fileName);
					question = questionReader.getRandomQuizQuestions(questionType);
                    //ErrorDumpUtil.ErrorLog("------Preview.java------501--->"+question.size());

					for(int i=0;i<Integer.parseInt(noquestion);i++){
                        //ErrorDumpUtil.ErrorLog("------Preview.java------551---");

						Collections.shuffle(question);
						ErrorDumpUtil.ErrorLog("Size Of Question"+question.size());
						for(int k=0;k<question.size();k++){  
                            
                            String Quest = ((QuizFileEntry)question.get(0)).getQuestion();
                            String mi = ((QuizFileEntry)question.get(0)).getMin();
                            String ma = ((QuizFileEntry)question.get(0)).getMax();
                            String ane = ((QuizFileEntry)question.get(0)).getAnswer();
                            String img= ((QuizFileEntry)question.get(0)).getUrl();
			/*
				@Anand Gupta
					use base64 method for image.
					Add to the finalQuestion method.
			*/
			    String imgpath=questionBankFilePath+"/"+topicName+"/"+img;
			    String imageDataString="";
				if(!img.equals(""))
				{
			    File file1=new File(imgpath);
                                            FileInputStream imageFile=new FileInputStream(file1);
                                            byte imageData[]=new byte[(int)file1.length()];
                                            imageFile.read(imageData);
                                            imageDataString=Base64.getEncoder().encodeToString(imageData);
                                            imageFile.close();
						}
				      	    //context.put("quesimage",imageDataString);
					    //ErrorDumpUtil.ErrorLog("base 64 STRING-  "+imageDataString);
							found = false;
							QuizFileEntry q = question.get(k);
                            //String Questt = (QuizFileEntry)question;
							q.setFileName(fileName);
							q.setMarksPerQuestion(markperquestion);
							q.setQuestionType(questionType);
						if(!img.equals(""))
							{							
							q.setImg(imageDataString);
							}
							Iterator it = finalQuestion.iterator();
                            
                            //ErrorDumpUtil.ErrorLog("------Preview.java------552-1---"+finalQuestion);
							while (it.hasNext()) {
								QuizFileEntry a = (QuizFileEntry) it.next();
								String que = a.getQuestion();
//                                String an = a.getAnswer();

                                String Min ="",Max="",an="";
                                //ErrorDumpUtil.ErrorLog("------Preview.java------553---que--->"+que);
                                if(questionType.equals("sart"))
                                {    
                                     Min = a.getMin();
                                     Max= a.getMax();
                                }
                                else
								     an = a.getAnswer();
                                if(questionType.equals("sart"))
                                {
                                    if (que.equals(q.getQuestion()) && Min.equals(q.getMin()) && Max.equals(q.getMax())){ // Are they exactly the same instance?
                                        found=true;
                                        //ErrorDumpUtil.ErrorLog("------Preview.java------6---fileName--->found sart");

                                        break;
                                    }
                                    
                                }
                                else
                                {
								    if (que.equals(q.getQuestion())&& an.equals(q.getAnswer())){ // Are they exactly the same instance?
							    		found=true;
							    		break;
							        }
                                }
							}
							if(found){//question bank element in the treeset is already present
								continue;
							}
							else{
                                try{
                                
								finalQuestion.add(q);				
				//ErrorDumpUtil.ErrorLog("------Preview.java------911---"+q.getImg());
                                }
                                catch(Exception et){
                                        ErrorDumpUtil.ErrorLog("------Preview.java------91-1---"+et);
                                }

								question.removeElementAt(k);
								question.trimToSize();
								break;
							}
						}//for
					}				     	
				}
				if(finalQuestion==null){
                                ErrorDumpUtil.ErrorLog("------Preview.java------10---");
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion",LangFile));
					return;
				}	                
                                ErrorDumpUtil.ErrorLog("------Preview.java------11---");
				String Quesid,Ques,opt1,opt2,opt3,opt4,Answer,markques,questionty,filename,type,Min,Max;
				opt1="";
				opt2="";
				opt3="";
				opt4="";				
				Answer="";
                Min="";
                Max="";
				finalQues.addAll(finalQuestion);
				Iterator it = finalQues.iterator();
               // ErrorDumpUtil.ErrorLog("------Preview.java------12---finalQues--->"+finalQues);
				if(finalQues.size()!=0){                   	
					context.put("finalq",finalQues);
					
				}
				while (it.hasNext()) {
					QuizFileEntry a = (QuizFileEntry) it.next();
					questionty = a.getQuestionType();
					Quesid = a.getQuestionID();
					Ques = a.getQuestion();
					
					//ErrorDumpUtil.ErrorLog("question under while loop "+Ques);
//					Answer = a.getAnswer();
//                    Min = a.getMin();
//                    Max = a.getMax();
					markques = a.getMarksPerQuestion();
					filename = a.getFileName();
					if(questionty.equalsIgnoreCase("mcq")){
						opt1=a.getOption1();
						opt2=a.getOption2();					
						opt3=a.getOption3();
						opt4=a.getOption4();
					}
					if(questionty.equalsIgnoreCase("sart")){
                        Min = a.getMin();
                        Max = a.getMax();
                        Answer="";
                    }
                    else
                    {
					    Answer = a.getAnswer();
                    }
					/**writing temporary xml file for final question list
					 *@see QuizMetaDataXmlWriter in Util
					 */
					//QuizMetaDataXmlWriter.xmlwriteFinalQuestion(filePath,quizQuestionPath,Quesid,Ques,opt1,opt2,opt3,opt4,Answer,markques,filename,questionty,Cur_date);
//check this method later for proper use(code review)
					QuizMetaDataXmlWriter.xmlwriteFinalQuestion(filePath,quizQuestionPath,Quesid,Ques,opt1,opt2,opt3,opt4,Answer,markques,filename,questionty,Cur_date,Min,Max);
				} 

			}
		}
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in Preview screen::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));	
		}
	}
}			
