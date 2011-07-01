package org.iitk.brihaspati.modules.utils;

/*
 * @(#)QuizMetaDataXmlWriter.java
 *
 *  Copyright (c) 2010-2011 DEI, Agra.
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
 *  Contributors: Members of MHRD Project, DEI, Agra
 *
 */

import java.util.Date;
import java.io.File;
import java.util.Vector;
import java.io.FileOutputStream;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
 * This class generate Xml file with attributes and values
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 */

public class QuizMetaDataXmlWriter
{
	/**
	* This method write xml file with tags
	* @param fileName String
	*/
	public static void OLESRootOnly(String fileName) throws Exception
	{
		FileOutputStream fos=new FileOutputStream(fileName);
		fos.write( ("<QuizFile>\n</QuizFile>").getBytes() );
		fos.close();
	}

	/**
     * This method append element in existing xml file
     * @param xmlWriter XmlWriter
     * @param quizid String
     * @param quizname String
     * @param max marks String
     * @param max time String
     * @param number of question String
     * @param status String
     * @param filename String
     * @param CreationDate String     
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void appendQues_Banklist(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String allow)
	{
		ErrorDumpUtil.ErrorLog("inside append question "+quizID);
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));              
		ats.addAttribute("","QuizName","","",StringUtil.replaceXmlSpecialCharacters(quizName));		
		ats.addAttribute("","MaxMarks","","",StringUtil.replaceXmlSpecialCharacters(maxMarks));		
		ats.addAttribute("","MaxTime","","",StringUtil.replaceXmlSpecialCharacters(maxTime));              
		ats.addAttribute("","NumberQuestion","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));		
		ats.addAttribute("","status","","",StringUtil.replaceXmlSpecialCharacters(status));              		
		ats.addAttribute("","Filename","","",StringUtil.replaceXmlSpecialCharacters(Filename));		
		ats.addAttribute("","CreationDate","","",CreationDate);
		ats.addAttribute("","ModifiedDate","","",CreationDate);
		ats.addAttribute("","QuizMode","","","");
		ats.addAttribute("","AllowPractice","","",StringUtil.replaceXmlSpecialCharacters(allow));
		xmlWriter.appendElement("Quiz",null,ats);
		ErrorDumpUtil.ErrorLog("after append question "+quizID);
	}
	
	
	/**
	* This method update file element in existing xml file with sequence number
	* and all updated variables values
	* @param xmlWriter XmlWriter
	* @param quizid String
	* @param quizname String
	* @param max marks String
	* @param max time String
	* @param number of question String
	* @param status String
	* @param filename String
	* @param CreationDate String  
	* @param ModifiedDate String  
	* @param quizMode(random/one by one) String	
	* @param seqno Integer
	* @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
	*/
	public static void update_QuizList(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String modifiedDate, String quizMode,int seq,String allowPractice)
	{
		try{
			ErrorDumpUtil.ErrorLog("inside update quiz "+quizID);
			AttributesImpl ats=new AttributesImpl();		
			ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));			
			ats.addAttribute("","QuizName","","",StringUtil.replaceXmlSpecialCharacters(quizName));			
			ats.addAttribute("","MaxMarks","","",StringUtil.replaceXmlSpecialCharacters(maxMarks));			
			ats.addAttribute("","MaxTime","","",StringUtil.replaceXmlSpecialCharacters(maxTime));			
			ats.addAttribute("","NumberQuestion","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));			
			ats.addAttribute("","status","","",StringUtil.replaceXmlSpecialCharacters(status));			
			ats.addAttribute("","Filename","","",StringUtil.replaceXmlSpecialCharacters(Filename));			
			ats.addAttribute("","CreationDate","","",CreationDate);
			ats.addAttribute("","QuizMode","","",quizMode);
			ats.addAttribute("","ModifiedDate","","",modifiedDate);
			ats.addAttribute("","AllowPractice","","",allowPractice);

			xmlWriter.changeAttributes("Quiz",ats,seq);
			ErrorDumpUtil.ErrorLog("after change attributes ");
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update quiz list]::"+e);
		}
	}
	
	public static void update_QuizList(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String modifiedDate, String quizMode,int seq,String allowPractice,String ExamDate,String startTime,String ExpiryDate,String endTime,String resDate)
	{
		try{
			ErrorDumpUtil.ErrorLog("inside update quiz "+quizID);
			AttributesImpl ats=new AttributesImpl();		
			ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));			
			ats.addAttribute("","QuizName","","",StringUtil.replaceXmlSpecialCharacters(quizName));			
			ats.addAttribute("","MaxMarks","","",StringUtil.replaceXmlSpecialCharacters(maxMarks));			
			ats.addAttribute("","MaxTime","","",StringUtil.replaceXmlSpecialCharacters(maxTime));			
			ats.addAttribute("","NumberQuestion","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));			
			ats.addAttribute("","status","","",StringUtil.replaceXmlSpecialCharacters(status));			
			ats.addAttribute("","Filename","","",StringUtil.replaceXmlSpecialCharacters(Filename));			
			ats.addAttribute("","CreationDate","","",CreationDate);
			ats.addAttribute("","QuizMode","","",quizMode);
			ats.addAttribute("","ModifiedDate","","",modifiedDate);
			ats.addAttribute("","ExamDate","","",ExamDate);
			ats.addAttribute("","StartTime","","",startTime);
			ats.addAttribute("","ExpiryDate","","",ExpiryDate);
			ats.addAttribute("","EndTime","","",endTime);
			ats.addAttribute("","AllowPractice","","",allowPractice);
			ats.addAttribute("","ResultDate","","",resDate);
			xmlWriter.changeAttributes("Quiz",ats,seq);
			ErrorDumpUtil.ErrorLog("after change attributes ");
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update quiz list]::"+e);
		}
	}
	
	/**
	* This method write new xml file with specified file path and xml file name
	* @param filePath String
	* @param xmlFile String	
	* @return xmlWriter XmlWriter
	*/
//	public static XmlWriter Ques_BankXmlist(String filePath,String xmlfile)
//	{
//		XmlWriter xmlWriter=null;
//		File descFile=new File(filePath+"/"+xmlfile);		
//		try{
//			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
//			Vector v=quizMetaData.getQuesBanklist_Detail();
//			ErrorDumpUtil.ErrorLog("The vector size in write:ques_bankxmlist::"+v.size());
//			descFile.delete();
//			OLESRootOnly(descFile.getAbsolutePath());
//			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
//			for(int i=0;i<v.size();i++)
//			{
//				String quizID=((QuizFileEntry)v.get(i)).getQuizID();
//				String quizName=((QuizFileEntry)v.get(i)).getQuizName();
//				String maxMarks=((QuizFileEntry)v.get(i)).getMaxMarks();
//				String maxTime=((QuizFileEntry)v.get(i)).getMaxTime();
//				String status=((QuizFileEntry)v.get(i)).getQuizStatus();
//				String noQuestion=((QuizFileEntry)v.get(i)).getnoQuestion();
//				String Filename=((QuizFileEntry)v.get(i)).getQuizFileName();
//				String CreationDate=((QuizFileEntry)v.get(i)).getCreationDate();
//				ErrorDumpUtil.ErrorLog("before going to append question "+quizID+quizName+maxMarks+maxTime+status+noQuestion+Filename);
//				appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate);
//			}
//		}
//		catch(Exception e){
//			ErrorDumpUtil.ErrorLog("The exception in xmlwriterutil [XmlWriter Ques_BankXmllist]::"+e);			
//		}
//		return xmlWriter;
//	}

	/**
	* This method update file element in existing xml file with sequence number
	* and all updated variables values
	* @param file path String
	* @param xmlfileName String
	* @param seqno Integer
	* @param quizid String
	* @param max marks String
	* @param max time String
	* @param number of question String 
	* @param ModifiedDate String  	
	* @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
	*/
	public static XmlWriter Update_QuizList(String filePath,String xmlfile, int seq, String quizID,String maxMarks,String maxTime,String noQuestion,String modifiedDate)
	{
		XmlWriter xmlWriter=null;
		try{
			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
			Vector v=quizMetaData.getQuiz_Detail(quizID);			
			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			for(int i=0;i<v.size();i++)
			{				
				String quizName=((QuizFileEntry)v.get(i)).getQuizName();	
				String status=((QuizFileEntry)v.get(i)).getQuizStatus();
				String Filename=((QuizFileEntry)v.get(i)).getQuizFileName();
				String CreationDate=((QuizFileEntry)v.get(i)).getCreationDate();
				String quizMode = ((QuizFileEntry)v.get(i)).getQuizMode();
				String allowPractice = ((QuizFileEntry)v.get(i)).getAllowPractice();
				String ExamDate=((QuizFileEntry) v.elementAt(i)).getExamDate();
				String StartTime=((QuizFileEntry) v.elementAt(i)).getStartTime();
				String ExpiryDate=((QuizFileEntry) v.elementAt(i)).getExpiryDate();
				String EndTime=((QuizFileEntry) v.elementAt(i)).getEndTime();
				String resDate=((QuizFileEntry) v.elementAt(i)).getResDate();
				ErrorDumpUtil.ErrorLog("before going to update quiz part "+quizID+quizName+maxMarks+maxTime+status+noQuestion+Filename+":::"+resDate);
				if(ExamDate!=null && StartTime!=null && ExpiryDate!=null && EndTime!=null && resDate!=null){
					update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice,ExamDate,StartTime,ExpiryDate,EndTime,resDate);
				}
				else
					update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice);
			}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update_quizlist]::"+e);			
		}
		return xmlWriter;
	}
	
	/**
	* This method update file element in existing quizid_questionSetting.xml file with sequence number
	* and all updated variables values
	* @param file path String
	* @param xmlfileName String
	* @param seqno Integer
	* @param topicName String
	* @param question type String
	* @param question Level String
	* @param marks per question String
	* @param number of question String 
	* @param ID of row String  	
	* @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
	*/
	public static XmlWriter Update_QuizQuestionSetting(String filePath,String xmlfile, int seq, String topicName,String queType,String queLevel,String queMarks,String noQuestion,String ID)
	{
		XmlWriter xmlWriter=null;
		try{		
			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);			
			AttributesImpl ats=new AttributesImpl();		
			ats.addAttribute("","TopicName","","",StringUtil.replaceXmlSpecialCharacters(topicName));			
			ats.addAttribute("","QuestionType","","",StringUtil.replaceXmlSpecialCharacters(queType));			
			ats.addAttribute("","QuestionLevel","","",StringUtil.replaceXmlSpecialCharacters(queLevel));			
			ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(queMarks));			
			ats.addAttribute("","QuestionNumber","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));			
			ats.addAttribute("","ID","","",StringUtil.replaceXmlSpecialCharacters(ID));						
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
			ErrorDumpUtil.ErrorLog("after new change attributes ");
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update_quizlist]::"+e);			
		}
		return xmlWriter;
	}
	
	/**
	* This method used to update the status of quiz if it is once created
	* @param file path String
	* @param xmlfileName String
	* @param seqno Integer
	* @param quizid String
	* @param status String 
	* @param quiz mode String  	
	* @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
	*/
	public static XmlWriter UpdateRandomQuizList(String filePath,String xmlfile, int seq, String quizID,String quizStatus, String quizMode)
	{
		XmlWriter xmlWriter=null;
		try{
			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
			Vector v=quizMetaData.getQuiz_Detail(quizID);			
			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			for(int i=0;i<v.size();i++)
			{
				String quizName=((QuizFileEntry)v.get(i)).getQuizName();
				String maxMarks=((QuizFileEntry)v.get(i)).getMaxMarks();
				String maxTime=((QuizFileEntry)v.get(i)).getMaxTime();
				String noQuestion=((QuizFileEntry)v.get(i)).getnoQuestion();
				String Filename=((QuizFileEntry)v.get(i)).getQuizFileName();
				String CreationDate=((QuizFileEntry)v.get(i)).getCreationDate();
				String modifiedDate=((QuizFileEntry)v.get(i)).getModifiedDate();
				String allowPractice = ((QuizFileEntry)v.get(i)).getAllowPractice();
//				String ExamDate=((QuizFileEntry) v.elementAt(i)).getExamDate();
//				String StartTime=((QuizFileEntry) v.elementAt(i)).getStartTime();
//				String ExpiryDate=((QuizFileEntry) v.elementAt(i)).getExpiryDate();
//				String EndTime=((QuizFileEntry) v.elementAt(i)).getEndTime();
				ErrorDumpUtil.ErrorLog("before going to update quiz part "+quizID+quizName+maxMarks+maxTime+quizStatus+noQuestion+Filename);
//				update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,quizStatus,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice,ExamDate,StartTime,ExpiryDate,EndTime);
				update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,quizStatus,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice);
			}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in UpdateRandomQuizList::"+e);			
		}
		return xmlWriter;
	}
	
	/**
     * This method append element in existing xml (quizid_questionSetting.xml) file
     * @param xmlWriter XmlWriter
     * @param topic name String
     * @param type of question String
     * @param level of question String
     * @param marks per question String
     * @param number of question String      
     * @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
     */
	public static void appendRandomQuizlist(XmlWriter xmlWriter,String topicName,String type,String level,String marks, String numberQuestion, String id)
	{
	    ErrorDumpUtil.ErrorLog("random method in quizmetadataxmlwriter begins ");
	    AttributesImpl ats=new AttributesImpl();
	    ats.addAttribute("","TopicName","","",StringUtil.replaceXmlSpecialCharacters(topicName));	    
	    ats.addAttribute("","QuestionType","","",StringUtil.replaceXmlSpecialCharacters(type));
	    ats.addAttribute("","QuestionLevel","","",StringUtil.replaceXmlSpecialCharacters(level));
	    ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
	    ats.addAttribute("","QuestionNumber","","",StringUtil.replaceXmlSpecialCharacters(numberQuestion));
	    ats.addAttribute("","ID","","",StringUtil.replaceXmlSpecialCharacters(id));
	  
	    xmlWriter.appendElement("QuizQuestions",null,ats);
	    ErrorDumpUtil.ErrorLog("random method in quizmetadataxmlwriter finished ");
	}

	/**
     * This method append element in existing xml (quizid_question.xml) file
     * @param xmlWriter XmlWriter
     * @param questionID String
     * @param question String
     * @param option1 String
     * @param option2 String
     * @param option3 String
     * @param option4 String
     * @param Answer String
     * @param file name String
     * @param type of question String  
     * @param CreationDate String	    
     * @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
     */
	public static void appendRandomQuizSettinglist(XmlWriter xmlWriter,String questionID,String question,String option1, String option2, String option3, String option4, String answer, String fileName, String typeName, String marks, String creationDate)
	{
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));		
		ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));		
		if(typeName.equalsIgnoreCase("mcq"))
		{
			ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));			
			ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));		
			ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));		
			ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
			ErrorDumpUtil.ErrorLog("option4 "+option4);
		}
		ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));		
		ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));		
		ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
		ats.addAttribute("","CreationDate","","",StringUtil.replaceXmlSpecialCharacters(creationDate));	
		xmlWriter.appendElement("QuizQuestions",null,ats);           
	}
	
	/** This method is responsible for writing temporary xml file for final question list 
	 * @param filepath String path to quizid_temp_question.xml
	 * @param filename String quizid_temp_question.xml 
	 * @param questionID String ID of question
	 * @param question String question
	 * @param options String option1, option2, option3, option4
	 * @param answer String answer of question
	 * @param Marks String marks per question
	 * @param filename String filename of question
	 * @param question type String type(mcq,tft,sat,lat)	 
	 * @param CreationDate String	
	 * @author nupur dixit 
	 */
	public static void xmlwriteFinalQuestion(String filePath,String quizXmlPath,String questionID,String question,String option1,String option2, String option3, String option4, String answer,String marksPerQuestion,String fileName,String typeName, String CreationDate){
		try{
			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
			ErrorDumpUtil.ErrorLog("full quiz xml path"+Tempxmls.getAbsolutePath());
			QuizMetaDataXmlReader quizMetaData=null;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Tempxmls.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(Tempxmls.getAbsolutePath());
			}						
			xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
			QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,fileName,typeName,marksPerQuestion,CreationDate);
			ErrorDumpUtil.ErrorLog("after append question");
			xmlWriter.writeXmlFile();			
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
//			data.setMessage("See ExceptionLog !! " );
		}//catch
	}//method end

	/**
	 * This method update file element in existing quizid_question.xml file with sequence number
	 * and all updated variables values
	 * @param file path String
	 * @param xmlfileName String
	 * @param seqno Integer
	 * @param questionid String
	 * @param question String
	 * @param option1 String
	 * @param option2 String
	 * @param option3 String
	 * @param option4 String
	 * @param answer String
	 * @param marks per question String
	 * @param file name String 
	 * @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
	 */
	public static XmlWriter UpdateQuizQuestion(String filePath,String xmlfile, int seq, String questionID, String question, String option1, String option2, String option3, String option4, String answer, String questionMarks, String fileName)
	{
		XmlWriter xmlWriter=null;
		try{
			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			AttributesImpl ats=new AttributesImpl();
			
			ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));			
			ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
			ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
			ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
			ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
			ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
			ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));			
			ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(questionMarks));			
			ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));			
						
			xmlWriter.changeAttributes("QuizQuestions",ats,seq-1);
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update_quizquestionlist]::"+e);			
		}
		return xmlWriter;
	}
	
	
	/**
	* This method update file element in existing quiz.xml file with sequence number
	* and all updated variables values
	* @param file path String
	* @param quizPath String
	* @param seqno Integer
	* @param quizid String
	* @param start date String
	* @param start time String
	* @param end date String
	* @param end time String
	* @param allow practice String 
	* @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
	*/
	public static XmlWriter announceQuiz(String filePath,String quizPath,int seq,String quizID,String startDate,String startTime,String endDate,String endTime,String resDate)
	{
		XmlWriter xmlWriter=null;
		try{
			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
			Vector v=quizMetaData.getQuiz_Detail(quizID);			
			xmlWriter=new XmlWriter(filePath+"/"+quizPath);
			for(int i=0;i<v.size();i++)
			{				
				String quizName=((QuizFileEntry)v.get(i)).getQuizName();	
				String maxMarks=((QuizFileEntry)v.get(i)).getMaxMarks();
				String maxTime=((QuizFileEntry)v.get(i)).getMaxTime();
				String noQuestion=((QuizFileEntry)v.get(i)).getnoQuestion();
				String status=((QuizFileEntry)v.get(i)).getQuizStatus();
				String fileName=((QuizFileEntry)v.get(i)).getQuizFileName();
				String creationDate=((QuizFileEntry)v.get(i)).getCreationDate();
				String modifiedDate=((QuizFileEntry)v.get(i)).getModifiedDate();
				String quizMode = ((QuizFileEntry)v.get(i)).getQuizMode();
				String allowPractice = ((QuizFileEntry)v.get(i)).getAllowPractice();
								
				AttributesImpl ats=new AttributesImpl();		
				ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));			
				ats.addAttribute("","QuizName","","",quizName);			
				ats.addAttribute("","MaxMarks","","",maxMarks);			
				ats.addAttribute("","MaxTime","","",maxTime);			
				ats.addAttribute("","NumberQuestion","","",noQuestion);			
				ats.addAttribute("","status","","",status);			
				ats.addAttribute("","Filename","","",fileName);			
				ats.addAttribute("","CreationDate","","",creationDate);
				ats.addAttribute("","ModifiedDate","","",modifiedDate);
				ats.addAttribute("","QuizMode","","",quizMode);
				ats.addAttribute("","ExamDate","","",StringUtil.replaceXmlSpecialCharacters(startDate));
				ats.addAttribute("","StartTime","","",StringUtil.replaceXmlSpecialCharacters(startTime));
				ats.addAttribute("","ExpiryDate","","",StringUtil.replaceXmlSpecialCharacters(endDate));
				ats.addAttribute("","EndTime","","",StringUtil.replaceXmlSpecialCharacters(endTime));
				ats.addAttribute("","AllowPractice","","",allowPractice);
				if(!(resDate.equals("$Res_year-$Res_month-$Res_day"))){
					ats.addAttribute("","ResultDate","","",resDate);
				}
				xmlWriter.changeAttributes("Quiz",ats,seq);
			}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter announceQuiz]::"+e);			
		}
		return xmlWriter;
	}
	
	/** This method is responsible for writing student'a answer in userid.xml file for general quiz
	 * @param filepath String path to userid.xml
	 * @param filename String userid.xml 
	 * @param data RunData	
	 * @author nupur dixit 
	 */
	public static void xmlwriteFinalAnswer(String filePath,String quizXmlPath,RunData data){
		try{
			User user=data.getUser();
			String courseid=(String)user.getTemp("course_id"); 
			String quizID=data.getParameters().getString("quizID","");		
			String quesID=data.getParameters().getString("quesID","");		
			String fileName=data.getParameters().getString("fileName","");		
			String studentAnswer=data.getParameters().getString("finalAnswer","");	
			String quesType=data.getParameters().getString("quesType","");	
			String awardedMarks = "";
			ErrorDumpUtil.ErrorLog("\n inside save answer quiz \n  "+quizID+"\n"+quesID+"\n"+fileName+"\n"+studentAnswer);
			String markPerQues = data.getParameters().getString("markPerQues","");
			
			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
			ErrorDumpUtil.ErrorLog("full quiz xml path"+Tempxmls.getAbsolutePath());
			QuizMetaDataXmlReader quizMetaData=null;
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");  
			String question,realAnswer,option1,option2,option3,option4;
			question=realAnswer=option1=option2=option3=option4="";
			
//			String questionBankFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
//            String questionBankPath=quizID+"_Questions.xml"; 
//            ErrorDumpUtil.ErrorLog("path to question's real answer "+questionBankFilePath+"/"+questionBankPath);
//            quizMetaData=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankPath);				
//			Vector insertedQuestion=quizMetaData.getInsertedQuizQuestions();
//			if(insertedQuestion!=null){
			if(questionVector!=null){
//				for(int i=0;i<insertedQuestion.size();i++) {
//					String quesid=((QuizFileEntry) insertedQuestion.elementAt(i)).getQuestionID();
//					String filename=((QuizFileEntry) insertedQuestion.elementAt(i)).getFileName();
//					String rightAnswer = ((QuizFileEntry) insertedQuestion.elementAt(i)).getAnswer();
//					if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
//						if(answer.equalsIgnoreCase(rightAnswer)){
//							awardedMarks = markPerQues;
//							break;
//						}
//						else{
//							awardedMarks = "0";
//							break;
//						}						
//					}						
//				}
				for(int i=0;i<questionVector.size();i++) {
					String quesid=((QuizFileEntry) questionVector.elementAt(i)).getQuestionID();
					String filename=((QuizFileEntry) questionVector.elementAt(i)).getFileName();										
					if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
						question = ((QuizFileEntry) questionVector.elementAt(i)).getQuestion();
						realAnswer = ((QuizFileEntry) questionVector.elementAt(i)).getAnswer();
						if(quesType.equalsIgnoreCase("mcq")){
							option1=((QuizFileEntry) questionVector.elementAt(i)).getOption1();
							option2=((QuizFileEntry) questionVector.elementAt(i)).getOption2();
							option3=((QuizFileEntry) questionVector.elementAt(i)).getOption3();
							option4=((QuizFileEntry) questionVector.elementAt(i)).getOption4();
						}
						if(studentAnswer.equalsIgnoreCase(realAnswer)){
							awardedMarks = markPerQues;
							break;
						}
						else{
							awardedMarks = "0";
							break;
						}						
					}						
				}
			}						
			boolean foundDuplicate = false;
			int seq=-1;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Tempxmls.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(Tempxmls.getAbsolutePath());
			}	
			else{
				quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+quizXmlPath);				
				Vector finalAnswer=quizMetaData.getFinalAnswer();
				if(finalAnswer!=null){
					for(int i=0;i<finalAnswer.size();i++) {
						String quesid=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionID();
						String filename=((QuizFileEntry) finalAnswer.elementAt(i)).getFileName();
						if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
							foundDuplicate=true;
							seq = i;
							break;
						}						
					}
				}
			}
			xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
			ErrorDumpUtil.ErrorLog("before append answer in writer");
//			QuizMetaDataXmlWriter.appendAnswer(xmlWriter,quesID,fileName,answer,markPerQues,awardedMarks,seq);
			QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,quesType,seq);
			ErrorDumpUtil.ErrorLog("after append answer in writer");
			xmlWriter.writeXmlFile();
			//========================this part is to add scores in final score.xml concurrently========================================
			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
	        String scorePath="score.xml";
			QuizMetaDataXmlWriter.xmlwriteFinalScore(scoreFilePath, scorePath, data);			
			//============================================================================
			ErrorDumpUtil.ErrorLog("after append question");
			xmlWriter.writeXmlFile();
			data.setMessage("Answer is saved successfully" );
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}//catch
	}//method end
	/**
     * This method append final answers in existing xml (userid.xml) file
     * @param xmlWriter XmlWriter
     * @param questionID String
     * @param file name String     
     * @param Answer String
	 * @param marks per question String
     * @param awarded marks String  
     * @param sequence int	    
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void appendAnswer(XmlWriter xmlWriter,String questionID,String fileName,String answer,String markPerQues,String awardedMarks, int seq){
		try{
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));		
		ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));				
		ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));				
		ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));		
		ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
		ErrorDumpUtil.ErrorLog("after add attribute");
		if(seq != -1){
			ErrorDumpUtil.ErrorLog("inside seq != -1");
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			ErrorDumpUtil.ErrorLog("inside seq != -1 else");
			xmlWriter.appendElement("QuizQuestions",null,ats); 
		}		  
		ErrorDumpUtil.ErrorLog("after append element");
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
//			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}
	}
	
	/**
     * This method append final answers in existing xml (userid.xml) file
     * @param xmlWriter XmlWriter
     * @param questionID String
     * @param file name String     
     * @param Answer String
	 * @param marks per question String
     * @param awarded marks String  
     * @param sequence int	    
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void appendAnswerPractice(XmlWriter xmlWriter,String questionID,String fileName,String question,String studentAnswer,String realAnswer,String markPerQues,String awardedMarks,String option1,String option2,String option3,String option4,String quesType, int seq){
		try{
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));		
		ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
		ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));	
		ats.addAttribute("","StudentAnswer","","",StringUtil.replaceXmlSpecialCharacters(studentAnswer));
		ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
		ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));		
		ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
		if(quesType.equalsIgnoreCase("mcq")){
			ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
			ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
			ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
			ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
		}
		ErrorDumpUtil.ErrorLog("after add attribute");
		if(seq != -1){
			ErrorDumpUtil.ErrorLog("inside seq != -1");
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			ErrorDumpUtil.ErrorLog("inside seq != -1 else");
			xmlWriter.appendElement("QuizQuestions",null,ats); 
		}		  
		ErrorDumpUtil.ErrorLog("after append element");
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quiz writer method :appendAnswerPractice !!"+e);
//			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}
	}
	
	/** This method is responsible for writing final score.xml file 
	 * @param filepath String path to score.xml
	 * @param filename String score.xml 
	 * @param RunData data
	 * @author nupur dixit 
	 */
	public static void xmlwriteFinalScore(String filePath,String quizXmlPath,RunData data){
		try{
			User user=data.getUser();					
			String uname=user.getName();			 
            String uid=Integer.toString(UserUtil.getUID(uname));
            String courseid=(String)user.getTemp("course_id");            
			String quizID=data.getParameters().getString("quizID","");	
			String remainTime = data.getParameters().getString("timerValue","");
			ErrorDumpUtil.ErrorLog("\n remaining time "+remainTime);
			String maxTime = data.getParameters().getString("maxTime","");
			ErrorDumpUtil.ErrorLog("\n max time "+maxTime);
			String usedTime = calcUsedTime(maxTime,remainTime);
			String messageFlag = data.getParameters().getString("messageFlag","");	
			int totalScore=0;
			int awardedMarks = 0;
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
            String answerPath=uid+".xml"; 
            File answerFile=new File(answerFilePath+"/"+answerPath);
            File scoreFile=new File(filePath+"/"+quizXmlPath);
            ErrorDumpUtil.ErrorLog("path to search "+answerFilePath+"/"+answerPath);
            XmlWriter xmlWriter=null;
            QuizMetaDataXmlReader quizMetaData=null;
            if(!scoreFile.exists()) {
            	QuizMetaDataXmlWriter.OLESRootOnly(scoreFile.getAbsolutePath());
            } 
            //==============This part is use to overwrite the content of score.xml if entries are for the same user=================
            QuizMetaDataXmlReader scoreData = null;
            scoreData=new QuizMetaDataXmlReader(filePath+"/"+quizXmlPath);
            int seq = -1;
            seq = scoreData.getSeqOfAlreadyInsertedScore(filePath,quizXmlPath,quizID,uid);
            ErrorDumpUtil.ErrorLog("sequence number in writer "+seq);
            //============================================
                      
            xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
            if(!answerFile.exists()) {            		
            	QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
            	ErrorDumpUtil.ErrorLog("after append question");
            }	
            else{
            	quizMetaData=new QuizMetaDataXmlReader(answerFilePath+"/"+answerPath);
            	Vector studentAnswer = quizMetaData.getFinalAnswer();
            	if(studentAnswer!=null && studentAnswer.size()!=0){
            		for(int i=0;i<studentAnswer.size();i++) {
            			awardedMarks=Integer.parseInt(((QuizFileEntry) studentAnswer.elementAt(i)).getAwardedMarks());
            			totalScore = totalScore+awardedMarks;
    				}
            		ErrorDumpUtil.ErrorLog("total marks "+totalScore);
            		QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
    			}						            		
            	else{
            		QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
            	}    				     			    					
            }
            xmlWriter.writeXmlFile();
            if(messageFlag.equalsIgnoreCase("submit"))
            	data.setMessage("score is saved successfully" ); 
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizMetaXmlWriter:xmlwriteFinalScore !!"+e);
			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}//catch
	}//method end
	
	/**
     * This method write(append/overwrite) final scores in existing xml (score.xml) file
     * @param xmlWriter XmlWriter
     * @param quizID String
     * @param user id String     
     * @param total score String	 
     * @param sequence int	    
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void writeScore(XmlWriter xmlWriter,String quizID,String userID, int totalScore,String usedtime, int seq){
		try{
		AttributesImpl ats=new AttributesImpl();
		String score = String.valueOf(totalScore);
		ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));		
		ats.addAttribute("","UserID","","",StringUtil.replaceXmlSpecialCharacters(userID));				
		ats.addAttribute("","TotalScore","","",StringUtil.replaceXmlSpecialCharacters(score));
		ats.addAttribute("","UsedTime","","",StringUtil.replaceXmlSpecialCharacters(usedtime));
		ErrorDumpUtil.ErrorLog("after add attribute");
		if(seq != -1){
			ErrorDumpUtil.ErrorLog("inside seq != -1");
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			ErrorDumpUtil.ErrorLog("inside seq != -1 else");
			xmlWriter.appendElement("QuizQuestions",null,ats); 
		}		  
		ErrorDumpUtil.ErrorLog("after append element");
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizMetaDataXmlWriter method:writeScore !!"+e);
		}
	}
	public static void writeScore(XmlWriter xmlWriter,String quizID,String userID, int totalScore,String usedtime, int seq,String evaluate){
		try{
		AttributesImpl ats=new AttributesImpl();
		String score = String.valueOf(totalScore);
		ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));		
		ats.addAttribute("","UserID","","",StringUtil.replaceXmlSpecialCharacters(userID));				
		ats.addAttribute("","TotalScore","","",StringUtil.replaceXmlSpecialCharacters(score));
		ats.addAttribute("","UsedTime","","",StringUtil.replaceXmlSpecialCharacters(usedtime));
		ats.addAttribute("","evaluate","","",StringUtil.replaceXmlSpecialCharacters(evaluate));
		ErrorDumpUtil.ErrorLog("inside WriteScore evaluate is :"+evaluate);
		ErrorDumpUtil.ErrorLog("after add attribute");
		if(seq != -1){
			ErrorDumpUtil.ErrorLog("inside seq != -1");
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			ErrorDumpUtil.ErrorLog("inside seq != -1 else");
			xmlWriter.appendElement("QuizQuestions",null,ats); 
		}		  
		ErrorDumpUtil.ErrorLog("after append element");
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizMetaDataXmlWriter method:writeScore !!"+e);
		}
	}
	
	/** This method is responsible for writing student'a answer in userid.xml file 
	 * @param filepath String path to userid.xml
	 * @param filename String userid.xml 
	 * @param data RunData	
	 * @author nupur dixit 
	 */
	public static void xmlwriteFinalAnswerPractice(String filePath,String quizXmlPath,RunData data){
		try{
			User user=data.getUser();
			String courseid=(String)user.getTemp("course_id"); 			
            String courseAlias=CourseUtil.getCourseAlias(courseid);           
            String courseArray[]=courseid.split("_");
            int len = courseAlias.length();
            int len1 = courseArray[0].length();
            String instructorName = (courseid.substring(len, len1)).trim();
            ErrorDumpUtil.ErrorLog("index "+courseid.substring(len, len1));
                        
			String quizID=data.getParameters().getString("quizID","");		
			String quesID=data.getParameters().getString("quesID","");		
			String fileName=data.getParameters().getString("fileName","");		
			String studentAnswer=data.getParameters().getString("finalAnswer","");	
			String quesType=data.getParameters().getString("quesType","");	
			String awardedMarks = "";
			ErrorDumpUtil.ErrorLog("\n inside save answer quiz \n  "+quizID+"\n"+quesID+"\n"+fileName+"\n"+studentAnswer);
			String markPerQues = data.getParameters().getString("markPerQues","");
			
			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
			ErrorDumpUtil.ErrorLog("full quiz xml path"+Tempxmls.getAbsolutePath());
			QuizMetaDataXmlReader quizMetaData=null;
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");  
			String question,realAnswer,option1,option2,option3,option4;
			question=realAnswer=option1=option2=option3=option4="";
			
//			String questionBankFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
//            String questionBankPath=quizID+"_Questions.xml"; 
//            ErrorDumpUtil.ErrorLog("path to question's real answer "+questionBankFilePath+"/"+questionBankPath);
//            quizMetaData=new QuizMetaDataXmlReader(questionBankFilePath+"/"+questionBankPath);				
//			Vector insertedQuestion=quizMetaData.getInsertedQuizQuestions();
			if(questionVector!=null){
				for(int i=0;i<questionVector.size();i++) {
					String quesid=((QuizFileEntry) questionVector.elementAt(i)).getQuestionID();
					String filename=((QuizFileEntry) questionVector.elementAt(i)).getFileName();										
					if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
						question = ((QuizFileEntry) questionVector.elementAt(i)).getQuestion();
						realAnswer = ((QuizFileEntry) questionVector.elementAt(i)).getAnswer();
						if(quesType.equalsIgnoreCase("mcq")){
							option1=((QuizFileEntry) questionVector.elementAt(i)).getOption1();
							option2=((QuizFileEntry) questionVector.elementAt(i)).getOption2();
							option3=((QuizFileEntry) questionVector.elementAt(i)).getOption3();
							option4=((QuizFileEntry) questionVector.elementAt(i)).getOption4();
						}
						if(studentAnswer.equalsIgnoreCase(realAnswer)){
							awardedMarks = markPerQues;
							break;
						}
						else{
							awardedMarks = "0";
							break;
						}						
					}						
				}
			}						
			boolean foundDuplicate = false;
			int seq=-1;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Tempxmls.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(Tempxmls.getAbsolutePath());
			}	
			else{
				quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+quizXmlPath);				
				Vector finalAnswer=quizMetaData.getFinalAnswer();
				if(finalAnswer!=null){
					for(int i=0;i<finalAnswer.size();i++) {
						String quesid=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionID();
						String filename=((QuizFileEntry) finalAnswer.elementAt(i)).getFileName();
						if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
							foundDuplicate=true;
							seq = i;
							break;
						}						
					}
				}
			}
			xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
			ErrorDumpUtil.ErrorLog("before append answer in writer");
			QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,quesType,seq);
			ErrorDumpUtil.ErrorLog("after append answer in writer");
			xmlWriter.writeXmlFile();
//			//========================this part is to add scores in final score.xml concurrently========================================
//			String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
//	        String scorePath="score.xml";
//			QuizMetaDataXmlWriter.xmlwriteFinalScore(scoreFilePath, scorePath, data);			
			//============================================================================
//			ErrorDumpUtil.ErrorLog("after append question");
//			xmlWriter.writeXmlFile();
			data.setMessage("Answer is saved successfully" );
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizwitere method : xmlwriteFinalAnswerPractice !!"+e);
			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}//catch
	}//method end
	
	public static String calcUsedTime(String maxTime, String remainTime){
		String usedTime="";
		try{
//			String maxTime="10:00";
//			String remainTime = "07:50";
			String usedMin,usedSec;		
			String arr[] = maxTime.split(":");		
			int totalMax = Integer.valueOf(arr[0])*60 + Integer.valueOf(arr[1]);
			String arr1[] = remainTime.split(":");		
			int totalRemain = Integer.valueOf(arr1[0])*60 + Integer.valueOf(arr1[1]);
			int usedSeconds = totalMax - totalRemain;
			int usedMinute = usedSeconds/60;
			int usedSecond = usedSeconds%60;		
			if(usedMinute<10)
				usedMin = "0" + (String.valueOf(usedMinute));
			else
				usedMin = String.valueOf(usedMinute);
			if(usedSecond<10)
				usedSec = "0" + (String.valueOf(usedSecond));
			else
				usedSec = String.valueOf(usedSecond);
			usedTime = usedMin+":"+usedSec;
			ErrorDumpUtil.ErrorLog(totalMax+" : "+totalRemain +" --"+usedTime);
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizwitere method : calcUsedTime !!"+e);
		}
		return usedTime;
	}
	
	/** This method is responsible for writing marks given by instructor during evaluation in userid.xml file 
	 * @param filepath String path to userid.xml
	 * @param filename String userid.xml 
	 * @param data RunData	
	 * @author nupur dixit 
	 */
	public static void xmlwriteEvaluateMarks(String answerFilePath,String answerPath,RunData data,String evaluate){
		try{
			User user=data.getUser();
			String courseid=(String)user.getTemp("course_id");
			String studentLoginName=data.getParameters().getString("studentLoginName","");
			String uid=Integer.toString(UserUtil.getUID(studentLoginName));
			String quizID=data.getParameters().getString("quizID","");		
			String quesID=data.getParameters().getString("quesID","");		
			String fileName=data.getParameters().getString("fileName","");		
			String awardedMarks = data.getParameters().getString("awardedMarks","");
			boolean flag=false;
			ErrorDumpUtil.ErrorLog("inside writer xmlwriteEvaluateMarks evaluate is "+evaluate);
			ErrorDumpUtil.ErrorLog("\n inside save evaluate marks \n  "+quizID+"\n"+quesID+"\n"+fileName+"\n"+awardedMarks);
			
			XmlWriter xmlWriter=null;
			File answerFile=new File(answerFilePath+"/"+answerPath);
			ErrorDumpUtil.ErrorLog("full answer xml path"+answerFile.getAbsolutePath());
			QuizMetaDataXmlReader quizMetaData=null;				
			boolean foundDuplicate = false;
			int seq=-1;
			String question,studentAnswer,instructorAnswer,markPerQues;
			question = studentAnswer = instructorAnswer = markPerQues = "";
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!answerFile.exists()) {
				QuizMetaDataXmlWriter.OLESRootOnly(answerFile.getAbsolutePath());
			}	
			else{
				quizMetaData=new QuizMetaDataXmlReader(answerFilePath+"/"+answerPath);				
				Vector finalAnswer=quizMetaData.getFinalAnswer();
				if(finalAnswer!=null){
					for(int i=0;i<finalAnswer.size();i++) {
						String quesid=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionID();
						String filename=((QuizFileEntry) finalAnswer.elementAt(i)).getFileName();
						 question=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestion();
						 studentAnswer=((QuizFileEntry) finalAnswer.elementAt(i)).getStudentAnswer();
						 instructorAnswer=((QuizFileEntry) finalAnswer.elementAt(i)).getInstructorAnswer();
						 markPerQues=((QuizFileEntry) finalAnswer.elementAt(i)).getMarksPerQuestion();
						if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
							foundDuplicate=true;
							seq = i;
							break;
						}						
					}
					for(int i=0;i<finalAnswer.size();i++){
						 String questionType=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionType();
						 if(questionType.equalsIgnoreCase("sat") || questionType.equalsIgnoreCase("lat")){
							 flag=true;
							 //evaluate ="partial";
							 break;
						 }
					}
				}
			}
			xmlWriter=new XmlWriter(answerFilePath+"/"+answerPath);
			ErrorDumpUtil.ErrorLog("before append answer in writer");
			QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,instructorAnswer,markPerQues,awardedMarks,"","","","","",seq);
			ErrorDumpUtil.ErrorLog("after append answer in writer");
			xmlWriter.writeXmlFile();
			
			quizMetaData=new QuizMetaDataXmlReader(answerFilePath+"/"+answerPath);
			int getMarks=0;
			int totalScore=0;
        	Vector studAnswer = quizMetaData.getFinalAnswer();
        	if(studentAnswer!=null && studAnswer.size()!=0){
        		for(int i=0;i<studAnswer.size();i++) {
        			getMarks=Integer.parseInt(((QuizFileEntry) studAnswer.elementAt(i)).getAwardedMarks());
        			totalScore = totalScore+getMarks;
				}
        	}
        		ErrorDumpUtil.ErrorLog("total marks "+totalScore);
        		String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
    	        String scorePath="score.xml";
    	        String usedTime="";
//    	        int seq;
    	        quizMetaData=new QuizMetaDataXmlReader(scoreFilePath+"/"+scorePath);
    	        Vector scoreDetail = quizMetaData.getDetailOfAlreadyInsertedScore(scoreFilePath,scorePath,quizID,uid);
    	        XmlWriter xmlScoreWriter = null;
    	       
//				ErrorDumpUtil.ErrorLog("sequence number is :"+seq);
//    			QuizMetaDataXmlWriter.xmlwriteFinalScore(scoreFilePath, scorePath, data);
    	        if(scoreDetail!=null && scoreDetail.size()!=0){
            		for(int i=0;i<scoreDetail.size();i++) {
            			usedTime = (((QuizFileEntry) scoreDetail.elementAt(i)).getUsedTime());
            			seq = Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getID()));
            		}  
            		ErrorDumpUtil.ErrorLog("used time and sequence number "+usedTime+" : "+seq);        		
			}		
    	        xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
    	        ErrorDumpUtil.ErrorLog("total detail to save score "+scoreFilePath+" : "+scorePath);  
    	        ErrorDumpUtil.ErrorLog("total detail to save score "+quizID+" : "+uid);
    	        ErrorDumpUtil.ErrorLog("total detail to save score "+totalScore+" : "+usedTime+" : "+seq+" : "+evaluate);
    	        if(flag){
    	        	QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq,evaluate);
    	        }
    	        else{
    	        	 QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq);
    	        }
    	       // QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq);
			//========================this part is to add scores in final score.xml concurrently========================================
					
			//============================================================================
			ErrorDumpUtil.ErrorLog("after append score");
			xmlScoreWriter.writeXmlFile();
			data.setMessage("score is saved successfully" );
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}//catch
	}//method end
	
}