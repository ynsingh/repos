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
	public static void appendQues_Banklist(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate)
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
	public static void update_QuizList(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String modifiedDate, String quizMode,int seq)
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
	public static XmlWriter Ques_BankXmlist(String filePath,String xmlfile)
	{
		XmlWriter xmlWriter=null;
		File descFile=new File(filePath+"/"+xmlfile);		
		try{
			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
			Vector v=quizMetaData.getQuesBanklist_Detail();
			ErrorDumpUtil.ErrorLog("The vector size in write:ques_bankxmlist::"+v.size());
			descFile.delete();
			OLESRootOnly(descFile.getAbsolutePath());
			xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			for(int i=0;i<v.size();i++)
			{
				String quizID=((QuizFileEntry)v.get(i)).getQuizID();
				String quizName=((QuizFileEntry)v.get(i)).getQuizName();
				String maxMarks=((QuizFileEntry)v.get(i)).getMaxMarks();
				String maxTime=((QuizFileEntry)v.get(i)).getMaxTime();
				String status=((QuizFileEntry)v.get(i)).getQuizStatus();
				String noQuestion=((QuizFileEntry)v.get(i)).getnoQuestion();
				String Filename=((QuizFileEntry)v.get(i)).getQuizFileName();
				String CreationDate=((QuizFileEntry)v.get(i)).getCreationDate();
				ErrorDumpUtil.ErrorLog("before going to append question "+quizID+quizName+maxMarks+maxTime+status+noQuestion+Filename);
				appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate);
			}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlwriterutil [XmlWriter Ques_BankXmllist]::"+e);			
		}
		return xmlWriter;
	}

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
				ErrorDumpUtil.ErrorLog("before going to update quiz part "+quizID+quizName+maxMarks+maxTime+status+noQuestion+Filename);
				update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,modifiedDate,quizMode,seq);
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
				ErrorDumpUtil.ErrorLog("before going to update quiz part "+quizID+quizName+maxMarks+maxTime+quizStatus+noQuestion+Filename);
				update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,quizStatus,Filename,CreationDate,modifiedDate,quizMode,seq);
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
	* This method update file element in existing xml file with sequence number
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
	public static XmlWriter announceQuiz(String filePath,String quizPath,int seq,String quizID,String startDate,String startTime,String endDate,String endTime,String allow)
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
				ats.addAttribute("","AllowPractice","","",StringUtil.replaceXmlSpecialCharacters(allow));

				xmlWriter.changeAttributes("Quiz",ats,seq);
			}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter announceQuiz]::"+e);			
		}
		return xmlWriter;
	}

}