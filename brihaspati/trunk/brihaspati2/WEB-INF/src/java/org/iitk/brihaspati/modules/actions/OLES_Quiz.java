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
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//Brihaspati
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
 */
public class OLES_Quiz extends SecureAction
{
	
	String CoursePath=TurbineServlet.getRealPath("/Courses");
	private String crsId=new String();
	private String LangFile=new String();
	
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
            ErrorDumpUtil.ErrorLog("inside action"+crsId+" user name "+username);
            
			String quizName=pp.getString("quizName","");
			String quizID = pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxTime=pp.getString("maxTime","");
			String noQuestion=pp.getString("numberQuestion","");
			ErrorDumpUtil.ErrorLog("quiz id "+quizID+" maxMarks "+maxMarks);				
			String filepath=CoursePath+"/"+crsId+"/Exam/";
			ErrorDumpUtil.ErrorLog("course path "+CoursePath+" file path "+filepath);
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
            ErrorDumpUtil.ErrorLog("quiz question path "+QuizQuestionxmlsPath);
            String quizPath="/Quiz.xml";				
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			/*
			 * At the time of quiz setup, status is saved to "INA"
			 * when quiz is created (either randomly/one by one) status is changed to "ACT"
			 */
			String status="INA";
			ErrorDumpUtil.ErrorLog("after date "+ Cur_date);
			xmlwriteQuizlist(filepath,quizID,quizName,maxMarks,maxTime,noQuestion,status,Cur_date,quizPath,data,context,QuizQuestionxmlsPath,QuizQuestionSettingxmlsPath);
			ErrorDumpUtil.ErrorLog("after write topic method ");    

		}//try
			catch(Exception e){
				 ErrorDumpUtil.ErrorLog("The exception in On Line Examination Action - doUploadQues_Bank ======"+e);
            	 data.setMessage("Error in action[OLES:doUploadQues_Bank]"+e);
        	}
	}//method	
		
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
		else
			data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));				
	}
	
	
	/** This method is responsible for uploading quiz_questions setting in a xml file
	 * @param data RunData instance
	 * @param context Context instance
	 * @exception Exception, a generic exception
	 */
	
	public void randomQuiz(RunData data, Context context){
        try{
            String courseid=data.getParameters().getString("courseID","");
            String username=data.getUser().getName();
            String quizID=data.getParameters().getString("quizID","");
            String quizMode=data.getParameters().getString("quizMode","");
            String maxMarks=data.getParameters().getString("maxMarks","");
            String maxnoQuestions=data.getParameters().getString("noQuestions","");
           
            String topicName = data.getParameters().getString("topicName","");
            String typeName = data.getParameters().getString("typeName","");
            String levelName = data.getParameters().getString("levelName","");
            String status = "ACT";
            String quizStatus="ACT";
            String page = data.getParameters().getString("page","");
            ParameterParser pp=data.getParameters();
            String numberQuestion = pp.getString("numberQuestion","");
            String marksQuestion = pp.getString("marksQuestion","");
           
            String newFilePath=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/"+quizID);
            String questionsPath=quizID+"_Questions.xml";
            String questionSettingPath=quizID+"_QuestionSetting.xml";
               
            File newFile=new File(newFilePath+"/"+questionSettingPath);
            XmlWriter xmlWriter=null;
            String var="";
           
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
                            if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks)){
                                var=insertQuestionRandomly(xmlWriter,newFilePath,questionsPath,numberQuestion,topicName,typeName,levelName,marksQuestion,status,data,username,courseid,questionSettingPath);
                                if(var.equalsIgnoreCase("create"))
                                    updateQuizRandomly(xmlWriter,quizID, quizStatus, courseid);
                            }
                            else
                            	data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);                          
                        }                       
                        else
                            data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);
                    }
                    else{
                        if(Integer.parseInt(numberQuestion)<=Integer.parseInt(maxnoQuestions)-enteredQuestions){
                            if((Integer.parseInt(marksQuestion)*Integer.parseInt(numberQuestion))<=Integer.parseInt(maxMarks)-mark)
                                var=insertQuestionRandomly(xmlWriter,newFilePath,questionsPath,numberQuestion,topicName,typeName,levelName,marksQuestion,status,data,username,courseid,questionSettingPath);                             
                            else
                            	data.setMessage(MultilingualUtil.ConvertedString("brih_marksmsg",LangFile)+" "+maxMarks);  
                        }
                        else
                        	data.setMessage(MultilingualUtil.ConvertedString("brih_quesmsg",LangFile)+" "+maxnoQuestions);   
                    }
                }
                else
                	data.setMessage(MultilingualUtil.ConvertedString("brih_excessmsg",LangFile));
               
                if(var.equalsIgnoreCase("create")){
                    data.setMessage("Questions are added successfully!!");
                    if(page.equalsIgnoreCase("exit")){
                    	data.setScreenTemplate("call,OLES,Oles_Gen.vm");
                    }
                }
                else if(var.equalsIgnoreCase("empty"))
                    data.setMessage("Question Bank has no questions");
                else
                    data.setMessage("The question bank has "+var+" questions and "+enteredQuestions+" questions are already inserted");
            }                   
        }catch(Exception e){
            ErrorDumpUtil.ErrorLog("random quiz method"+e);
        }
    }
	
	/** This method is responsible for inserting quiz_questions setting in a xml file
	 * @param xmlWriter XmlWriter instance
	 * @param String filepath, String xml file name, String no of question
	 * @param String topic name, String type of question, String difficulty level, String marks per question
	 * @param String status of question, String name
	 * @exception Exception, a generic exception
	 */
	public String insertQuestionRandomly(XmlWriter xmlWriter, String newFilePath, String questionsPath, String numberQuestion,
            String topicName, String typeName, String levelName, String marksQuestion, String status, RunData data, String username, String courseid, String questionSettingPath){
        String variable="";
        try{      
            Boolean flag=false;
            String questionBankFilePath=TurbineServlet.getRealPath("/QuestionBank/"+username+"/"+courseid);
            String questionBankQuestionsPath=topicName+"_"+levelName+"_"+typeName+".xml";
            Vector questionVector = new Vector();
            Vector insertedQuestionVector = new Vector();
            File ff = new File(questionBankFilePath+"/"+questionBankQuestionsPath);
            if(!ff.exists()){
            	variable = "empty";
            	return variable;
            }
            QuizMetaDataXmlReader questionBankXmlReader;          
            questionBankXmlReader=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankQuestionsPath);
            
            
            questionVector = questionBankXmlReader.getRandomQuizQuestions(typeName);              
          
            questionBankXmlReader=new QuizMetaDataXmlReader(newFilePath+"/"+questionsPath);
            ErrorDumpUtil.ErrorLog("path "+questionBankQuestionsPath);
            insertedQuestionVector = questionBankXmlReader.getQuizQuestions(questionBankQuestionsPath);
          
            String questionArray[][];
            String questionID="";
            String question,option1,option2,option3,option4,answer,fileName;
          
            if(questionVector!=null){
                ErrorDumpUtil.ErrorLog("step1");
                if(questionVector.size()>=Integer.parseInt(numberQuestion)){
                    ErrorDumpUtil.ErrorLog("step2");
                    questionArray = new String[questionVector.size()][8];
                    if(insertedQuestionVector!=null){
                        ErrorDumpUtil.ErrorLog("step3");
                        int k = 0;
                        for(int i=0;i<questionVector.size();i++){
                            questionID=((QuizFileEntry) questionVector.elementAt(i)).getQuestionID();
                            ErrorDumpUtil.ErrorLog("step4"+questionID);
                            for(int j=0;j<insertedQuestionVector.size();j++){
                                String questionid=((QuizFileEntry) insertedQuestionVector.elementAt(j)).getQuestionID();
                                String filePath=((QuizFileEntry) insertedQuestionVector.elementAt(j)).getFileName();
                                ErrorDumpUtil.ErrorLog("step5"+questionid+filePath+questionBankQuestionsPath);
                                if(questionID.equals(questionid)){
                                    break;
                                }
                                else{
                                    if(j==insertedQuestionVector.size()-1){
                                        questionArray[k][0]=questionID;
                                        questionArray[k][1]=((QuizFileEntry) questionVector.elementAt(j)).getQuestion();
                                        if(typeName.equalsIgnoreCase("mcq"))
                                        {
                                            questionArray[k][2]=((QuizFileEntry) questionVector.elementAt(j)).getOption1();
                                            questionArray[k][3]=((QuizFileEntry) questionVector.elementAt(j)).getOption2();
                                            questionArray[k][4]=((QuizFileEntry) questionVector.elementAt(j)).getOption3();
                                            questionArray[k][5]=((QuizFileEntry) questionVector.elementAt(j)).getOption4();
                                        }
                                        questionArray[k][6]=((QuizFileEntry) questionVector.elementAt(j)).getAnswer();
                                        questionArray[k][7]=questionBankQuestionsPath;
                                        k++;  
                                        flag=true;
                                    }
                                }
                            }
                        }
                    }
                       else
                       {
                           for(int i=0;i<questionVector.size();i++){
                               questionArray[i][0]=((QuizFileEntry) questionVector.elementAt(i)).getQuestionID();
                            questionArray[i][1]=((QuizFileEntry) questionVector.elementAt(i)).getQuestion();
                            if(typeName.equalsIgnoreCase("mcq"))
                            {
                                questionArray[i][2]=((QuizFileEntry) questionVector.elementAt(i)).getOption1();
                                questionArray[i][3]=((QuizFileEntry) questionVector.elementAt(i)).getOption2();
                                questionArray[i][4]=((QuizFileEntry) questionVector.elementAt(i)).getOption3();
                                questionArray[i][5]=((QuizFileEntry) questionVector.elementAt(i)).getOption4();
                            }
                            questionArray[i][6]=((QuizFileEntry) questionVector.elementAt(i)).getAnswer();
                            questionArray[i][7]=questionBankQuestionsPath;
                        }
                           flag=true;
                       }
                }
                else{variable=String.valueOf(questionVector.size()); return variable;}
            }
            else{variable="empty"; return variable;}
          
            if(flag==true)
            {
                xmlWriter=new XmlWriter(newFilePath+"/"+questionSettingPath);
                QuizMetaDataXmlWriter.appendRandomQuizlist(xmlWriter,topicName,typeName,levelName,marksQuestion,numberQuestion);
                xmlWriter.writeXmlFile();
              
                xmlWriter=new XmlWriter(newFilePath+"/"+questionsPath);
                for(int i=0; i<Integer.parseInt(numberQuestion); i++){
                    QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionArray[i][0],questionArray[i][1],questionArray[i][2],
                            questionArray[i][3],questionArray[i][4],questionArray[i][5],questionArray[i][6],questionArray[i][7],typeName);
                }
              
                xmlWriter.writeXmlFile();
                variable="create";
                data.setMessage("Questions are inserted successfully!!");
            }
            else
                variable=String.valueOf(questionVector.size());
          
            return variable;
        }catch(Exception e){
            ErrorDumpUtil.ErrorLog("insert question method"+e);
        }
        return variable;
    }
	//random method
	public void updateQuizRandomly(XmlWriter xmlWriter, String quizID, String quizStatus, String courseid){
        try{
            String quizMode="random";
            String newFilePath1=TurbineServlet.getRealPath("/Courses/"+courseid+"/Exam/");
            String quizPath="/Quiz.xml";
            int seq=-1;
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
            ErrorDumpUtil.ErrorLog("after xml writing process");   
        }catch(Exception e){
            ErrorDumpUtil.ErrorLog("update quiz status method"+e);
        }
    }
	//random method
	
public void generateQuiz(RunData data, Context context){
	try{
		String quizName=data.getParameters().getString("quizName","");
		context.put("quizName",quizName);
		ErrorDumpUtil.ErrorLog("quiz id from drop down "+quizName);
		context.put("type","createQuiz");			
	}catch(Exception e){
		ErrorDumpUtil.ErrorLog("generate quiz method"+e);
	}
}
	
	
 /** This method is responsible for creating xml file for quiz setting 
   * @param filepath String path to quiz.xml
   * @param quizName String Name of quiz 
   * @param quizID String ID of quiz (currently equal to quiz name)
   * @param maxMars String maximum marks in quiz 
   * @param maxTime String maximum time for quiz 
   * @param noQuestion String number of questions in quiz
   * @param status String status of quiz(active/inactive)
   * @param CreationDate String
   * @param quizPath String quiz.xml file name
   * @param quizQuestionPath String quizID_Question.xml file name(where question setting of a quiz is stored)
   * @param data RunData instance
   * @exception Exception, a generic exception
   */
	 public void xmlwriteQuizlist(String filepath,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String CreationDate,String quizPath,RunData data,Context context, String QuizQuestionxmlsPath, String QuizQuestionSettingxmlsPath){
      	try{
			ParameterParser pp=data.getParameters();
            LangFile=data.getUser().getTemp("LangFile").toString();
            XmlWriter xmlWriter=null;
            boolean found=false;
            String Filename=QuizQuestionxmlsPath;
            File Quizxmls=new File(filepath+"/"+quizPath);
            File QuizQuestionxmls=new File(QuizQuestionxmlsPath);
            File QuizQuestionSettingxmls=new File(QuizQuestionSettingxmlsPath);
            ErrorDumpUtil.ErrorLog("full quiz xml path"+Quizxmls.getAbsolutePath());
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
                    for(int i=0;i<collect.size();i++) {//for
                    	String quizid=((QuizFileEntry) collect.elementAt(i)).getQuizID();
                        if((quizID.equals(quizid))){
                            found=true;
                            data.setMessage(MultilingualUtil.ConvertedString("oles_quiz_duplicate",LangFile));
                        }
                     }//for
                }//if
            }//else
                
            if(found==false){                     
                xmlWriter=new XmlWriter(filepath+"/"+quizPath);
                ErrorDumpUtil.ErrorLog("inside found false");               
                QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate);
                ErrorDumpUtil.ErrorLog("after append question");  
                xmlWriter.writeXmlFile();
                if(!QuizQuestionxmls.exists()) {
                	QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionxmls.getAbsolutePath());
                }
                if(!QuizQuestionSettingxmls.exists()) {
                	QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionSettingxmls.getAbsolutePath());
                }
                ErrorDumpUtil.ErrorLog("after xml writing process");  
                data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("oles_msg",LangFile));
            }                
                
        }//try
        catch(Exception e){
           ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
           data.setMessage("See ExceptionLog !! " );
       }//catch
    }//method end
		

	 /** This method is responsible to update the quiz setting in /courses/courseid/Exam/Quiz.xml
	  * this method is also responsible to delete the quiz setting in /courses/courseid/Exam/Quiz.xml
	  * ,quizID_Questions.xml quizID_QuestionSetting.xml files in /courses/courseid/Exam/QuizID/
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
        ErrorDumpUtil.ErrorLog("inside action"+crsId+" user name "+username);
        
		String quizName=pp.getString("quizName","");
		String quizID = pp.getString("quizID","");
		String maxMarks=pp.getString("maxMarks","");
		String maxTime=pp.getString("maxTime","");
		String noQuestion=pp.getString("numberQuestion","");
		String status = "ACT";
		String Cur_date=ExpiryUtil.getCurrentDate("-");
		String modifiedDate = Cur_date;
		ErrorDumpUtil.ErrorLog("quiz id "+quizID+" maxMarks "+maxMarks);
		
		int seq=-1;
		XmlWriter xmlWriter=null;
	    Vector collect=new Vector();
	    Vector str=new Vector();
	    String deltype = pp.getString("delType","");
	    String filepath=CoursePath+"/"+crsId+"/Exam/";
	    String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
	    String quizPath="/Quiz.xml";
	    String quizQuestionPath="/"+quizID+"_Questions.xml";
	    String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
	    String fileName = filepath+quizQuestionPath;
		ErrorDumpUtil.ErrorLog("course path "+CoursePath+" file path "+filepath);
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
           ErrorDumpUtil.ErrorLog("del type "+deltype);
           if(deltype.equals("quizDel")){        	
        	   xmlWriter=new XmlWriter(filepath+"/"+quizPath);
        	   xmlWriter.removeElement("Quiz",seq);        	  
        	   File file=new File(filepath1+"/"+quizQuestionPath);
        	   File fileQuestionSetting=new File(filepath1+"/"+quizQuestionSettingPath);
        	   file.delete(); 
        	   fileQuestionSetting.delete();
        	   xmlWriter.writeXmlFile();
        	   data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
           }
           else{
        	   xmlWriter=QuizMetaDataXmlWriter.Update_QuizList(filepath,quizPath,seq,quizID,maxMarks,maxTime,noQuestion,modifiedDate);
        	   xmlWriter.writeXmlFile();
        	   data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
           }
        }
//       Vector str3=Arrangeseq(filepath,fulltopic+".xml",questiontype,deltype,data);
//       setTemplate(data,"call,OLES,View_QB.vm");
	}//try
	catch(Exception e){
		 ErrorDumpUtil.ErrorLog("The exception in OLES_Quiz Action - doUpdateQuiz "+e);
       	 data.setMessage("Error in action[OLES:doUpdateQuiz]"+e);
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
		LangFile=(String)data.getUser().getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		User user=data.getUser();
	
	   String username=data.getUser().getName();
	   crsId=(String)data.getUser().getTemp("course_id");
	   String course = (String)user.getTemp("course_name");
	   ErrorDumpUtil.ErrorLog("inside action"+crsId+" user name "+username);   
	
		int seq=-1;
		XmlWriter xmlWriter=null;
		Vector collect=new Vector();
		Vector str=new Vector();
		String filepath=CoursePath+"/"+crsId+"/Exam/";
		String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID+"/";
		String quizPath="/Quiz.xml";
		String quizQuestionPath="/"+quizID+"_Questions.xml";
		String quizQuestionSettingPath="/"+quizID+"_QuestionSetting.xml";
		String fileName = filepath+quizQuestionPath;
		ErrorDumpUtil.ErrorLog("course path "+CoursePath+" file path "+filepath);
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
   	   xmlWriter=new XmlWriter(filepath+"/"+quizPath);
   	   xmlWriter.removeElement("Quiz",seq);        	  
   	   File file=new File(filepath1+"/"+quizQuestionPath);
   	   File fileQuestionSetting=new File(filepath1+"/"+quizQuestionSettingPath);
	   file.delete(); 
	   fileQuestionSetting.delete();  		
   	   xmlWriter.writeXmlFile();
   	   data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
   }
//  Vector str3=Arrangeseq(filepath,fulltopic+".xml",questiontype,deltype,data);
//  setTemplate(data,"call,OLES,View_QB.vm");
}//try
catch(Exception e){
	 ErrorDumpUtil.ErrorLog("The exception in OLES_Quiz Action - doUpdateQuiz "+e);
  	 data.setMessage("Error in action[OLES:doUpdateQuiz]"+e);
	}
}

/** This method is responsible to delete multiple quiz settings in /courses/courseid/Exam/Quiz.xml
 * and quizID_Questions.xml files in /courses/courseid/Exam/QuizID/
 * @param data RunData instance
 * @param context Context instance
 * @exception Exception, a generic exception
 */

public void doRemove(RunData data,Context context)
{
        try{
                String qdelete=data.getParameters().getString("deleteFileNames");
                if(!qdelete.equals("")){
                        StringTokenizer st=new StringTokenizer(qdelete,"^");
                        for(int j=0;st.hasMoreTokens();j++){
                                String q_id=st.nextToken();
                                ErrorDumpUtil.ErrorLog("quiz id to delete:"+q_id);
                                doRemoveQuiz(data,context,q_id);
                        }
                }
        }
        catch(Exception e)
        {
        	ErrorDumpUtil.ErrorLog("exception in remove method::"+e);
        	 data.setMessage("See ExceptionLog !! ");
}
}




}	//end class	
	
	
