package org.iitk.brihaspati.modules.utils;

/*
 * @(#)QuizMetaDataXmlWriter.java
 *
 *  Copyright (c) 2010-2011,2013 DEI Agra, IITK, 2017 IITK.
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
 *  Contributors: DEI Agra, IITK
 *
 */

import java.util.Date;
import java.io.File;
import java.util.Vector;
import org.xml.sax.helpers.AttributesImpl;
import java.io.FileOutputStream;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.FileLockUnlock;
import org.iitk.brihaspati.modules.utils.BufferQuizThread;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.io.IOException;
import org.apache.commons.lang.math.Range;
import org.apache.commons.lang.math.DoubleRange;
import java.util.Random;
import java.util.Map;
/**
 * This class generate Xml file with attributes and values
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>01Aug2013
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>03jan2013
 */

public class QuizMetaDataXmlWriter
{
	/**
	* This method write xml file with tags
	* @param fileName String
	*/


	public synchronized static void OLESRootOnly(String fileName) throws Exception
	{
/*
        FileOutputStream fos=new FileOutputStream(fileName);
        fos.write( ("<QuizFile>\n</QuizFile>").getBytes() );
        fos.close();
*/
        FileLockUnlock fl =new FileLockUnlock(fileName);
        boolean gl = fl.getlock();
        if(gl)
        {
            FileOutputStream fop = new FileOutputStream(fileName);
            fop.write(("<QuizFile>\n</QuizFile>").getBytes());
            fop.close();
            fl.releaselock();
        }
    }

	/**
     * This method append element in existing xml file
     * @param xmlWriter XmlWriter
     * @param quizId String
     * @param quizName String
     * @param maxMarks String
     * @param maxTime String
     * @param noQuestion String
     * @param status String
     * @param Filename String
     * @param CreationDate String
     * @param ModifiedDate String
     * @param quizMode String
     * @param allow String
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void appendQues_Banklist(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String ModifiedDate,String quizMode,String allow)
	{
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));
		ats.addAttribute("","QuizName","","",StringUtil.replaceXmlSpecialCharacters(quizName));
		ats.addAttribute("","MaxMarks","","",StringUtil.replaceXmlSpecialCharacters(maxMarks));
		ats.addAttribute("","MaxTime","","",StringUtil.replaceXmlSpecialCharacters(maxTime));
		ats.addAttribute("","NumberQuestion","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));
		ats.addAttribute("","status","","",StringUtil.replaceXmlSpecialCharacters(status));
		ats.addAttribute("","Filename","","",StringUtil.replaceXmlSpecialCharacters(Filename));
		ats.addAttribute("","CreationDate","","",CreationDate);
		ats.addAttribute("","ModifiedDate","","",ModifiedDate);
		ats.addAttribute("","QuizMode","","",quizMode);
		ats.addAttribute("","AllowPractice","","",StringUtil.replaceXmlSpecialCharacters(allow));
		xmlWriter.appendElement("Quiz",null,ats);
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
     * @param ModifiedDate String
     * @param quizMode String
     * @param startDate String
     * @param startTime String
     * @param endDate String
     * @param endTime String
     * @param allow String
     * @param resDate String
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
	public static void appendQuiz_list(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String ModifiedDate,String quizMode,String startDate,String startTime,String endDate,String endTime,String allow,String resDate)
	{
		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuizID","","",StringUtil.replaceXmlSpecialCharacters(quizID));
		ats.addAttribute("","QuizName","","",StringUtil.replaceXmlSpecialCharacters(quizName));
		ats.addAttribute("","MaxMarks","","",StringUtil.replaceXmlSpecialCharacters(maxMarks));
		ats.addAttribute("","MaxTime","","",StringUtil.replaceXmlSpecialCharacters(maxTime));
		ats.addAttribute("","NumberQuestion","","",StringUtil.replaceXmlSpecialCharacters(noQuestion));
		ats.addAttribute("","status","","",StringUtil.replaceXmlSpecialCharacters(status));
		ats.addAttribute("","Filename","","",StringUtil.replaceXmlSpecialCharacters(Filename));
		ats.addAttribute("","CreationDate","","",CreationDate);
		ats.addAttribute("","ModifiedDate","","",ModifiedDate);
		ats.addAttribute("","QuizMode","","",quizMode);
		ats.addAttribute("","ExamDate","","",StringUtil.replaceXmlSpecialCharacters(startDate));
        ats.addAttribute("","StartTime","","",StringUtil.replaceXmlSpecialCharacters(startTime));
      	ats.addAttribute("","ExpiryDate","","",StringUtil.replaceXmlSpecialCharacters(endDate));
        ats.addAttribute("","EndTime","","",StringUtil.replaceXmlSpecialCharacters(endTime));
		ats.addAttribute("","AllowPractice","","",StringUtil.replaceXmlSpecialCharacters(allow));
       	ats.addAttribute("","ResultDate","","",resDate);
		xmlWriter.appendElement("Quiz",null,ats);
	}

	public static void update_QuizList(XmlWriter xmlWriter,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String Filename,String CreationDate,String modifiedDate, String quizMode,int seq,String allowPractice,String ExamDate,String startTime,String ExpiryDate,String endTime,String resDate)
	{
		try{
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
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update quiz list]::"+e);
		}
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
				String allowPractice = ((QuizFileEntry)v.get(i)).getAllowPractice();
				String ExamDate=((QuizFileEntry) v.elementAt(i)).getExamDate();
				String StartTime=((QuizFileEntry) v.elementAt(i)).getStartTime();
				String ExpiryDate=((QuizFileEntry) v.elementAt(i)).getExpiryDate();
				String EndTime=((QuizFileEntry) v.elementAt(i)).getEndTime();
				String resDate=((QuizFileEntry) v.elementAt(i)).getResDate();
				if(ExamDate!=null && StartTime!=null && ExpiryDate!=null && EndTime!=null && resDate!=null){
					update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice,ExamDate,StartTime,ExpiryDate,EndTime,resDate);
				}
				/*else
					update_QuizList(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,modifiedDate,quizMode,seq,allowPractice);*/
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
		}
		catch(Exception e){
			//ErrorDumpUtil.ErrorLog("The exception in Quizxmlwriterutil [XmlWriter update_quizlist]::"+e);
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
	    AttributesImpl ats=new AttributesImpl();
	    ats.addAttribute("","TopicName","","",StringUtil.replaceXmlSpecialCharacters(topicName));
	    ats.addAttribute("","QuestionType","","",StringUtil.replaceXmlSpecialCharacters(type));
	    ats.addAttribute("","QuestionLevel","","",StringUtil.replaceXmlSpecialCharacters(level));
	    ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
	    ats.addAttribute("","QuestionNumber","","",StringUtil.replaceXmlSpecialCharacters(numberQuestion));
	    ats.addAttribute("","ID","","",StringUtil.replaceXmlSpecialCharacters(id));

	    xmlWriter.appendElement("QuizQuestions",null,ats);
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
	public static void appendRandomQuizSettinglist(XmlWriter xmlWriter,String questionID,String question,String option1, String option2, String option3, String option4, String answer,String min,String max,String fileName, String typeName, String marks, String creationDate)
	{
        //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter---appendRandomQuizSettinglist-----Line 283--typeName-->"+typeName);

		AttributesImpl ats=new AttributesImpl();
		ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
		ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
		if(typeName.equals("mcq"))
		{
			ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
			ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
			ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
			ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
		}

        if(typeName.equals("sart"))
        {
		    ats.addAttribute("","Min","","",StringUtil.replaceXmlSpecialCharacters(min));
    		ats.addAttribute("","Max","","",StringUtil.replaceXmlSpecialCharacters(max));
        }
        else
    		ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));
		ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
		ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
		ats.addAttribute("","CreationDate","","",StringUtil.replaceXmlSpecialCharacters(creationDate));
        //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter---appendRandomQuizSettinglist-----Line 308--typeName-->"+typeName);
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
	//public static void xmlwriteFinalQuestion(String filePath,String quizXmlPath,String questionID,String question,String option1,String option2, String option3, String option4, String answer,String marksPerQuestion,String fileName,String typeName, String CreationDate,String min,String max){
	public static void xmlwriteFinalQuestion(String filePath,String quizXmlPath,String questionID,String question,String option1,String option2, String option3, String option4, String answer,String marksPerQuestion,String fileName,String typeName, String CreationDate,String min,String max){
		try{
			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
//			ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter.xmlwriteFinalQuestion-----Tempxmls--->"+Tempxmls+"---typeName--->"+typeName);
			QuizMetaDataXmlReader quizMetaData=null;
			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */
			if(!Tempxmls.exists()) {
  //              ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter.xmlwriteFinalQuestion-----Tempxmls--->"+Tempxmls);

				QuizMetaDataXmlWriter.OLESRootOnly(Tempxmls.getAbsolutePath());
			}
			xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
			xmlWriter=RandomQuizWriteTempxml(filePath,quizXmlPath,typeName);
			//QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,fileName,typeName,marksPerQuestion,CreationDate,min,max);
    //        ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter.xmlwriteFinalQuestion-----341--->");
			QuizMetaDataXmlWriter.appendRandomQuizSettinglist(xmlWriter,questionID,question,option1,option2,option3,option4,answer,min,max,fileName,typeName,marksPerQuestion,CreationDate);
			xmlWriter.writeXmlFile();
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in util method:xmlwriteFinalQuestion !!"+e);
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
			//if(seq!= -1){
			QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
			Vector v=quizMetaData.getQuiz_Detail(quizID);
			xmlWriter=new XmlWriter(filePath+"/"+quizPath);
			for(int i=0;i<v.size();i++)
			{
				String quizid=((QuizFileEntry)v.get(i)).getQuizID();
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
				xmlWriter=QuizMetaDataXmlWriter.QuizXml(filePath,quizPath);
				xmlWriter.writeXmlFile();
				xmlWriter.changeAttributes("Quiz",ats,seq);
				xmlWriter.writeXmlFile();
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
	public static void xmlwriteFinalAnswer(String filePath,String quizXmlPath,RunData data)
    {
		try
        {
			User user=data.getUser();
			String courseid=(String)user.getTemp("course_id");
			String quizID=data.getParameters().getString("quizID","");
			String quesID=data.getParameters().getString("quesID","");
			String fileName=data.getParameters().getString("fileName","");
			String studentAnswer=data.getParameters().getString("finalAnswer","");
			String quesType=data.getParameters().getString("quesType","");
            //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns--->"+studentAnswer);
			String awardedMarks = "";
			String markPerQues = data.getParameters().getString("markPerQues","");

			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
			QuizMetaDataXmlReader quizMetaData=null;
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");
			String question,realAnswer,option1,option2,option3,option4,minr,maxr;
			question=realAnswer=option1=option2=option3=option4="";
            double stud_Answer = 0.0d, min_r = 0.0d, max_r = 0.0d;
			if(questionVector!=null)
            {
				for(int i=0;i<questionVector.size();i++)
                {
					String quesid=((QuizFileEntry) questionVector.elementAt(i)).getQuestionID();
					String filename=((QuizFileEntry) questionVector.elementAt(i)).getFileName();
					if((quesID.equals(quesid)) && (fileName.equals(filename)) )
                    {
						question = ((QuizFileEntry) questionVector.elementAt(i)).getQuestion();
                        if(quesType.equalsIgnoreCase("sart"))
                        {
                            minr = ((QuizFileEntry) questionVector.elementAt(i)).getMin();
                            maxr = ((QuizFileEntry) questionVector.elementAt(i)).getMax();
                            min_r = Double.parseDouble(minr);
                            max_r = Double.parseDouble(maxr);

                            //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns1--->");
                            try
                            {
                                stud_Answer = Double.parseDouble(studentAnswer);

                            }
                            catch(NumberFormatException ex)
                            {
                                //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns2--->"+stud_Answer);
                            }
                            //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns3--->"+studentAnswer);

                        }
                        else
                        {
						    realAnswer = ((QuizFileEntry) questionVector.elementAt(i)).getAnswer();
                        }
						if(quesType.equalsIgnoreCase("mcq"))
                        {
							option1=((QuizFileEntry) questionVector.elementAt(i)).getOption1();
							option2=((QuizFileEntry) questionVector.elementAt(i)).getOption2();
							option3=((QuizFileEntry) questionVector.elementAt(i)).getOption3();
							option4=((QuizFileEntry) questionVector.elementAt(i)).getOption4();

							if(studentAnswer.equals(option1))
                                studentAnswer="A";
                            if(studentAnswer.equals(option2))
                               studentAnswer="B";
                            if(studentAnswer.equals(option3))
                               studentAnswer="C";
                            if(studentAnswer.equals(option4))
                               studentAnswer="D";
						}
                        if(quesType.equalsIgnoreCase("sart"))
                        {
                            Range ansrange = new DoubleRange( min_r, max_r );

                            if( !ansrange.containsDouble(stud_Answer))
                            {
                         //       ErrorDumpUtil.ErrorLog("----No----");
                                awardedMarks = "0";
                                break;

                            }
                            else
                            {
                           //     ErrorDumpUtil.ErrorLog("----Yes----");
                                awardedMarks = markPerQues;
                                break;
                            }
                        }
                        else
                        {
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
			}//if(questionvector)

			boolean foundDuplicate = false;
			int seq=-1;

			/**
			 *Checking for  xml file presence
			 *@see QuizMetaDataXmlWriter in Util.
			 */

			if(!Tempxmls.exists()) {
                //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----!Tempxmls.exists()-->");
                //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns-2-->"+quizXmlPath);
				QuizMetaDataXmlWriter.OLESRootOnly(Tempxmls.getAbsolutePath());

			}
			else{
                //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns-3-->"+filePath);
                //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----StudentAns-4-->"+quizXmlPath);
				quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+quizXmlPath);
				Vector finalAnswer=quizMetaData.getFinalAnswer();
				if(finalAnswer!=null){
					for(int i=0;i<finalAnswer.size();i++) {
						String quesid=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionID();
						String filename=((QuizFileEntry) finalAnswer.elementAt(i)).getFileName();
                        //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----Tempxmls.exists()-->");
                        //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----filename-->"+filename);
						if((quesID.equals(quesid)) && (fileName.equals(filename)) ){
							foundDuplicate=true;
							seq = i;
							break;
						}
					}
				}//end if

                if((foundDuplicate==true) &&(seq!=-1)){
                    ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----foundDuplicate==true-->");
					xmlWriter=WriteinStudtAnswerxml(filePath,quizXmlPath,quesType,-1);
					QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,min_r,max_r,quesType,seq);//call overload method at line 721
					xmlWriter.writeXmlFile();
				}
			}//end else

            //case for ist time writing in xml file.


			if((foundDuplicate==false )&&(seq==-1)){
                ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----foundDuplicate==false-->")     ;
			    xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
//			QuizMetaDataXmlWriter.appendAnswer(xmlWriter,quesID,fileName,answer,markPerQues,awardedMarks,seq);
			//----------------------------------modification done by seema and jaivir---------------------------//
    			xmlWriter=WriteinStudtAnswerxml(filePath,quizXmlPath,quesType,seq);
			//-------------------------------------------------------------//

                //modify this code for writing in xml added 2 more param min and max
	    		QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,min_r,max_r,quesType,seq);//call overload method at line 721, 14 parameter
		    	xmlWriter.writeXmlFile();
			//========================this part is to add scores in final score.xml concurrently========================================
			   // String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			    String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID);  //chaged by prajwal
            		String scorePath="score.xml";
	    		QuizMetaDataXmlWriter.xmlwriteFinalScore(scoreFilePath, scorePath, data);
			}
			//============================================================================
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
		if(seq != -1){
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			xmlWriter.appendElement("QuizQuestions",null,ats);
		}
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
    /* modified the method according to the sart type addes 2 more parameter min and max  */
    //14 parameter
	public static void appendAnswerPractice(XmlWriter xmlWriter,String questionID,String fileName,String question,String studentAnswer,String realAnswer,String markPerQues,String awardedMarks,String option1,String option2,String option3,String option4,double min,double max,String quesType, int seq){
		try{
//        ErrorDumpUtil.ErrorLog("---QuizMetaDataXmlWriter---appendAnswerPractice()---main");
		AttributesImpl ats=new AttributesImpl();
        String min_s = String.valueOf(min);//min.toString();
        String max_s = String.valueOf(max);//max.toString();
		ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
		ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
		ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
		ats.addAttribute("","StudentAnswer","","",StringUtil.replaceXmlSpecialCharacters(studentAnswer));
		//ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
		ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));
        if(quesType.equalsIgnoreCase("sart")){
            ats.addAttribute("","Min","","",StringUtil.replaceXmlSpecialCharacters(min_s));
            ats.addAttribute("","Max","","",StringUtil.replaceXmlSpecialCharacters(max_s));
        }
        else
            ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));

		ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
/*        if(quesType.equalsIgnoreCase("sart")){
            ats.addAttribute("","Min","","",StringUtil.replaceXmlSpecialCharacters(min_s));
            ats.addAttribute("","Max","","",StringUtil.replaceXmlSpecialCharacters(max_s));
        }
        else
            ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
*/
		if(quesType.equalsIgnoreCase("mcq")){
			ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
			ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
			ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
			ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
		}
		if(seq != -1){
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			xmlWriter.appendElement("QuizQuestions",null,ats);
		}
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
			String maxTime = data.getParameters().getString("maxTime","");
			String usedTime = calcUsedTime(maxTime,remainTime);
			String messageFlag = data.getParameters().getString("messageFlag","");
			int totalScore=0;
			int awardedMarks = 0;
            //ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----filename-->"+filename);
			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			//ErrorDumpUtil.ErrorLog("answerfilepath in superman"+answerFilePath);
            		String answerPath=uid+".xml";
            		File answerFile=new File(answerFilePath+"/"+answerPath);
            		File scoreFile=new File(filePath+"/"+quizXmlPath);
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
            //============================================

            xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
            if(!answerFile.exists()) {
		//BufferThread.storage(quizID,uid,totalScore,usedTime,seq);
		xmlWriter=WriteinScorexml(filePath,quizXmlPath);
            	QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
            	xmlWriter.writeXmlFile();
            }
            else{
            	quizMetaData=new QuizMetaDataXmlReader(answerFilePath+"/"+answerPath);
            	Vector studentAnswer = quizMetaData.getFinalAnswer();
            	if(studentAnswer!=null && studentAnswer.size()!=0){
            		for(int i=0;i<studentAnswer.size();i++) {
            			awardedMarks=Integer.parseInt(((QuizFileEntry) studentAnswer.elementAt(i)).getAwardedMarks());
            			totalScore = totalScore+awardedMarks;
    			}
			xmlWriter=WriteinScorexml(filePath,quizXmlPath);
            		QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
			xmlWriter.writeXmlFile();
    			}
            	else{
			xmlWriter=WriteinScorexml(filePath,quizXmlPath);
            		QuizMetaDataXmlWriter.writeScore(xmlWriter,quizID,uid,totalScore,usedTime,seq);
            		xmlWriter.writeXmlFile();
            	}
            }
//ErrorDumpUtil.ErrorLog("-----QuizMetaDataXmlWriter-----xmlwriteFinalAnswer()-----filename-->"+filename);
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
		if(seq != -1){
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			xmlWriter.appendElement("QuizQuestions",null,ats);
		}
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
		if(seq != -1){
			xmlWriter.changeAttributes("QuizQuestions",ats,seq);
		}
		else{
			xmlWriter.appendElement("QuizQuestions",null,ats);
		}
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
			String quizID=data.getParameters().getString("quizID","");
			String quesID=data.getParameters().getString("quesID","");
			String fileName=data.getParameters().getString("fileName","");
			String studentAnswer=data.getParameters().getString("finalAnswer","");
			String quesType=data.getParameters().getString("quesType","");
			String awardedMarks = "";
			String markPerQues = data.getParameters().getString("markPerQues","");
			XmlWriter xmlWriter=null;
			File Tempxmls=new File(filePath+"/"+quizXmlPath);
			QuizMetaDataXmlReader quizMetaData=null;
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");
			String question,realAnswer,option1,option2,option3,option4;
			question=realAnswer=option1=option2=option3=option4="";
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
							/*if(studentAnswer.equals("A"))
                                				studentAnswer=option1;
                        				if(studentAnswer.equals("B"))
                                				studentAnswer=option2;
                        				if(studentAnswer.equals("C"))
                               	 				studentAnswer=option3;
                        				if(studentAnswer.equals("D"))
                                				studentAnswer=option4;*/
							if(studentAnswer.equals(option1))
                                				studentAnswer="A";
                        				if(studentAnswer.equals(option2))
                                				studentAnswer="B";
                        				if(studentAnswer.equals(option3))
                               	 				studentAnswer="C";
                        				if(studentAnswer.equals(option4))
                                				studentAnswer="D";
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
			if((foundDuplicate==true)&&(seq!=-1)){
                        	xmlWriter=WriteinStudtAnswerxml(filePath,quizXmlPath,quesType,-1);
                                QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,0.0,0.0,quesType,seq);
                                xmlWriter.writeXmlFile();
			}
			if((foundDuplicate==false )&&(seq==-1)){
			xmlWriter=new XmlWriter(filePath+"/"+quizXmlPath);
			//-----------------------------------------modify by jaivir and seema--------------------
			xmlWriter=WriteinStudtAnswerxml(filePath,quizXmlPath,quesType,seq);
			//-----------------------------------------modify by jaivir and seema--------------------
			QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,realAnswer,markPerQues,awardedMarks,option1,option2,option3,option4,0.0,0.0,quesType,seq);
			xmlWriter.writeXmlFile();
			}
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
			XmlWriter xmlWriter=null;
			File answerFile=new File(answerFilePath+"/"+answerPath);
			QuizMetaDataXmlReader quizMetaData=null;
			boolean foundDuplicate = false;
			int seq=-1;
			String question,studentAnswer,instructorAnswer,markPerQues;
			question = studentAnswer = instructorAnswer = markPerQues = "";
			String questionType="";
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
						questionType=((QuizFileEntry) finalAnswer.elementAt(i)).getQuestionType();
						 if(questionType.equals("sat") || questionType.equals("lat")){
							 flag=true;
							 //evaluate ="partial";
							 break;
						 }
					}
				}
			}
			if((foundDuplicate==true) &&(seq!=-1)){
                                xmlWriter=WriteinStudtAnswerxml(answerFilePath,answerPath,questionType,-1);
                                QuizMetaDataXmlWriter.appendAnswerPractice(xmlWriter,quesID,fileName,question,studentAnswer,instructorAnswer,markPerQues,awardedMarks,"","","","",0.0,0.0,questionType,seq);
                                xmlWriter.writeXmlFile();
                        }
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
        		//String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
						String scoreFilePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID);
						ErrorDumpUtil.ErrorLog("scorefilepath is xmen"+scoreFilePath);
    	        	String scorePath="score.xml";
    	        	String usedTime="";
    	        	quizMetaData=new QuizMetaDataXmlReader(scoreFilePath+"/"+scorePath);
    	        	Vector scoreDetail = quizMetaData.getDetailOfAlreadyInsertedScore(scoreFilePath,scorePath,quizID,uid);
    	        	XmlWriter xmlScoreWriter = null;
    	        	if(scoreDetail!=null && scoreDetail.size()!=0){
            			for(int i=0;i<scoreDetail.size();i++) {
            				usedTime = (((QuizFileEntry) scoreDetail.elementAt(i)).getUsedTime());
            				seq = Integer.valueOf((((QuizFileEntry) scoreDetail.elementAt(i)).getID()));
            			}
			}
    	        	xmlScoreWriter = new XmlWriter(scoreFilePath+"/"+scorePath);
    	        	if(flag){
				xmlScoreWriter=WriteinScorexml(scoreFilePath,scorePath);
    	        		QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq,evaluate);
				// code used in buffer writer then comment above two line
		/*	String tscore=String.valueOf(totalScore);
			String seq1=String.valueOf(seq);	
				Map m=BufferQuizThread.storage(quizID,uid,tscore,usedTime,seq1,evaluate);
				ErrorDumpUtil.ErrorLog("Map !!"+m);
		 		quizID=(String)m.get("QuizID");
				uid=(String)m.get("UserID");
				tscore=(String)m.get("TotalScore");
				usedTime=(String)m.get("UsedTime");
				seq1=(String)m.get("seq");
				evaluate=(String)m.get("evaluate");
				//xmlScoreWriter=WriteinScorexml(scoreFilePath,scorePath);
				QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,Integer.parseInt(tscore),usedTime,Integer.parseInt(seq1),evaluate);
    	        		//QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq,evaluate);
			*/
    	        	}
    	        	else{
			 xmlScoreWriter=WriteinScorexml(scoreFilePath,scorePath);
    	        	 QuizMetaDataXmlWriter.writeScore(xmlScoreWriter,quizID,uid,totalScore,usedTime,seq);
    	        	}
			xmlScoreWriter.writeXmlFile();
			data.setMessage("score is saved successfully" );
		}//try
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in QuizMetaDataXmlWriter !!"+e);
			data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
		}//catch
	}//method end


	/** This method is responsible for writing Student ID,Secrity Strings and IP Addrss in xml file.
	 * @author Devendra singhal
		@author Anand Gupta
		Add starttime and endtime in xml file.
	 */
	//public static void writeSecurityString(XmlWriter xmlWriter,String studentID,String SecurityID,String IPAddress,String Start_time,String End_time){
	public static void writeSecurityString(XmlWriter xmlWriter,String studentID,String SecurityID){
		try{
			//String Start_time="";
			//String End_time="";
			AttributesImpl ats=new AttributesImpl();
			ats.addAttribute("","StudentID","","",studentID);
			ats.addAttribute("","SecurityID","","",SecurityID);
			//ats.addAttribute("","IPAddress","","",IPAddress);
			//ats.addAttribute("","Start_time","","",Start_time);
			//ats.addAttribute("","End_time","","",End_time);
			xmlWriter.appendElement("Quiz",null,ats);
			xmlWriter.writeXmlFile();
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizMetaDataXmlWriter method:writeScore !!"+e);
		}

	}
	/** This method is responsible for changing IP Address in xml file.
	 * @author Devendra singhal 
		@author Anand Gupta
			Add startTime and endTime in the xml file.
	 */
	public static void updateSecurity(XmlWriter xmlWriter,String studentID,String SecurityID,int seq,String filePath,String xmlfile){
//	public static void updateSecurity(XmlWriter xmlWriter,String studentID,String SecurityID,String IPAddress,int seq,String filePath,String xmlfile,String Start_time,String End_time){
		try{
			AttributesImpl ats=new AttributesImpl();
			ats.addAttribute("","StudentID","","",studentID);
			ats.addAttribute("","SecurityID","","",SecurityID);
		//	ats.addAttribute("","IPAddress","","",IPAddress);
		//	ats.addAttribute("","Start_time","","",Start_time);
		//	ats.addAttribute("","End_time","","",End_time);
			xmlWriter.changeAttributes("Quiz",ats,seq);
			xmlWriter.writeXmlFile();
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in quizMetaDataXmlWriter method:updateSecurity !!"+e);
		}
	}
//-------------------------------------------------------IITKanpur---------------------------------
	/**
	*This method read existing xml (Quiz.xml)file and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
	public static XmlWriter QuizXml(String filePath,String xmlfile)
      	{
        	XmlWriter xmlWriter=null;
              	File descFile=new File(filePath+"/"+xmlfile);
              	try{
			/**Get details from xml file in a vector
   			  *after getting details delete the file and create the new file
			  */
                	QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                      	Vector v=quizMetaData.getQuesBanklist_Detail();
                      	descFile.delete();
                      	OLESRootOnly(descFile.getAbsolutePath());
                      	xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			if(v!=null)
                        {
				/**Get the value from vector
				  *and compare the number of attributes for using in other methods
				  */
                      		for(int i=0;i<v.size();i++)
                      		{
                        		String quizID=((QuizFileEntry)v.get(i)).getQuizID();
                              		String quizName=((QuizFileEntry)v.get(i)).getQuizName();
                              		String maxMarks=((QuizFileEntry)v.get(i)).getMaxMarks();
                              		String maxTime=((QuizFileEntry)v.get(i)).getMaxTime();
                              		String noQuestion=((QuizFileEntry)v.get(i)).getnoQuestion();
                              		String status=((QuizFileEntry)v.get(i)).getQuizStatus();
                              		String Filename=((QuizFileEntry)v.get(i)).getQuizFileName();
                              		String CreationDate=((QuizFileEntry)v.get(i)).getCreationDate();
                              		String ModifiedDate=((QuizFileEntry)v.get(i)).getModifiedDate();
                              		String QuizMode=((QuizFileEntry)v.get(i)).getQuizMode();
                              		String AllowPractice=((QuizFileEntry)v.get(i)).getAllowPractice();
                                	String noofAttr=((QuizFileEntry) v.elementAt(i)).getnoofAttribute();
					/**If number of attribute is 16, call method appendQuiz_list
					  *else appendQues_Banklist
					  */
					if(noofAttr.equals("16")){
					String ExamDate=((QuizFileEntry) v.elementAt(i)).getExamDate();
                               		String StartTime=((QuizFileEntry) v.elementAt(i)).getStartTime();
                                	String ExpiryDate=((QuizFileEntry) v.elementAt(i)).getExpiryDate();
                                	String EndTime=((QuizFileEntry) v.elementAt(i)).getEndTime();
                                	String resDate=((QuizFileEntry) v.elementAt(i)).getResDate();
                              		appendQuiz_list(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,ModifiedDate,QuizMode,ExamDate,StartTime,ExpiryDate,EndTime,AllowPractice,resDate);
					}
					else
                              		appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate,ModifiedDate,QuizMode,AllowPractice);
                      		}
			}
              	}
              	catch(Exception e){
                	ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter QuizXml]::"+e);
              	}
              	return xmlWriter;
      	}

	/**
	*This method read existing xml file(quizID_Temp_Questions.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
	public static XmlWriter RandomQuizWriteTempxml(String filePath,String xmlfile,String typename)
    {
        XmlWriter xmlWriter=null;
        File descFile=new File(filePath+"/"+xmlfile);
        //File descFile=new File(filePath+xmlfile);
		/**Get the details from xml file in a vector
		  *after getting the details delete the xml file and create the new xml file
		  */
     //   ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---file1--->"+filePath+"/"+xmlfile);
       // ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---file2--->"+filePath);
        //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---file3--->"+xmlfile);
        try{
            QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
            //QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+xmlfile);
            Vector v=quizMetaData.getRandomTempQuizQuestions(typename);
            //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1116---vectorsize--->"+v.size());
            descFile.delete();
            OLESRootOnly(descFile.getAbsolutePath());
            xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
	    	String option1="",option2="",option3="",option4="",min="",max="";//,answer="";
		    /**Read the value from vector and get the tag value */
            //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1130---vectorsize--->");
            //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1131---vectorsize--->"+v.size());

			if(v!=null){
                //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1132---vectorsize--->");

                for(int i=0;i<v.size();i++)
                {
                 //   ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1136---vectorsize--->");

                    String quesid=((QuizFileEntry)v.get(i)).getQuestionID();
                    String question = ((QuizFileEntry)v.get(i)).getQuestion();
                    String answer = ((QuizFileEntry)v.get(i)).getAnswer();
                    String quesMarks = ((QuizFileEntry)v.get(i)).getMarksPerQuestion();
                    String Filename = ((QuizFileEntry)v.get(i)).getFileName();
                    String Creationdate = ((QuizFileEntry)v.get(i)).getCreationDate();
					String noOfAts=((QuizFileEntry)v.get(i)).getnoofAttribute();
					/**If number of attribute is 10, call appendRandomQuizSettinglist for MultiChoiceType Question
					  *else appendRandomQuizSettinglist for T/F,ShortAnswer, LongAnswer Type
					  */
                    //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1137---vectorsize--->"+question);

					if(noOfAts.equals("10"))
                    {
                     //   ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1152--->");

					    option1=((QuizFileEntry)v.get(i)).getOption1();
                        option2=((QuizFileEntry)v.get(i)).getOption2();
                        option3=((QuizFileEntry)v.get(i)).getOption3();
                        option4=((QuizFileEntry)v.get(i)).getOption4();
                        answer = ((QuizFileEntry)v.get(i)).getAnswer();
    					appendRandomQuizSettinglist(xmlWriter,quesid,question,option1,option2,option3,option4,answer,quesMarks,Filename,Creationdate);
	    			}
            		else if(noOfAts.equals("7"))
                    {
                //        ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1163--->");

					    min=((QuizFileEntry)v.get(i)).getMin();
					    max=((QuizFileEntry)v.get(i)).getMax();
                  //      ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1167--->");
                        appendRandomQuizSettinglist(xmlWriter,quesid,question,min,max,quesMarks,Filename,Creationdate);
                    //    ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1169--->");

                    }
                    else
                    {
//                        ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1174--->");
//                        answer = ((QuizFileEntry)v.get(i)).getAnswer();
//                        ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1175--->"+question);
    	        		appendRandomQuizSettinglist(xmlWriter,quesid,question,answer,quesMarks,Filename,Creationdate);
                    }
		        }
	        }
  //          ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----RandomQuizWriteTempxml()---1170---vectorsize--->");

        }
		catch(Exception e){
                ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter RandomQuizWriteTempxml]::"+e);
        }
        return xmlWriter;
	}

	/**
	*This method read existing xml file(quizID_QuestionSetting.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
	public static XmlWriter RandomWriteinQues_settingxml(String filePath,String xmlfile)
      	{
        	XmlWriter xmlWriter=null;
              	File descFile=new File(filePath+"/"+xmlfile);
              	try{
			/**Get the details from xml file and store in a vector
			  *after storing in xml file delete the xml file and create the new xml file
                          */
                	QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                      	Vector v=quizMetaData.getQuizQuestionDetail();
                      	descFile.delete();
                      	OLESRootOnly(descFile.getAbsolutePath());
                      	xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			if(v!=null){
				/**Get the value from vector for using in method appendRandomQuizlist*/
                      		for(int i=0;i<v.size();i++)
                      		{
					String topicname=((QuizFileEntry)v.get(i)).getTopic();
                                	String questype = ((QuizFileEntry)v.get(i)).getQuestionType();
                                	String queslevel = ((QuizFileEntry)v.get(i)).getQuestionLevel();
                                	String quesMarks = ((QuizFileEntry)v.get(i)).getMarksPerQuestion();
                                	String noQues = ((QuizFileEntry)v.get(i)).getQuestionNumber();
                                	String ID = ((QuizFileEntry)v.get(i)).getID();
					appendRandomQuizlist(xmlWriter,topicname,questype,queslevel,quesMarks,noQues,ID);
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter RandomWriteinQues_settingxml]::"+e);
                }
                return xmlWriter;
	}
	/**
	*This method read existing xml file(quizID_Security.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
	public static XmlWriter WriteinSecurityxml(String filePath,String xmlfile)
      	{
        	XmlWriter xmlWriter=null;
              	File descFile=new File(filePath+"/"+xmlfile);
              	try{
			/**Get the details from xml file and store in a vector
                          *after storing in xml file delete the xml file and create the new xml file
                          */
                	QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                      	Vector v=quizMetaData.getSecurityDetail();
                      	descFile.delete();
                      	OLESRootOnly(descFile.getAbsolutePath());
                      	xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			if(v!=null){
				/**Get the value from vector for using in method writeSecurityString*/
                      		for(int i=0;i<v.size();i++)
                      		{
				String studtid=((QuizFileEntry)v.get(i)).getStudentID();
                               	String securityid = ((QuizFileEntry)v.get(i)).getSecurityID();
                               	//String ipadd = ((QuizFileEntry)v.get(i)).getIP();
				//String a=	((QuizFileEntry)v.get(i)).getStartTime();
				//String b=	((QuizFileEntry)v.get(i)).getEndTime();
				//	writeSecurityString(xmlWriter,studtid,securityid,ipadd,a,b);
					writeSecurityString(xmlWriter,studtid,securityid);
				            }
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter WriteinSecurityxml]::"+e);
                }
                return xmlWriter;
	}
	/**
	*This method read existing xml file(uid.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */

	public static XmlWriter WriteinStudtAnswerxml(String filePath,String xmlfile,String questype,int seq)
    {
        XmlWriter xmlWriter=null;
        File descFile=new File(filePath+"/"+xmlfile);
        try
        {
		    /** Get the details from xml file and store in a vector
             *  after storing in xml file delete the xml file and create the new xml file
             */
            //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----1399------>");
            QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
           	Vector v=quizMetaData.getFinalAnswer();
           	descFile.delete();
           	OLESRootOnly(descFile.getAbsolutePath());
           	xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			String option1="",option2="",option3="",option4="",min="",max="";
//            ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----filePath------>"+filePath);
  //          ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----xmlfile------->"+xmlfile);
    //        ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----vsize------->"+v.size());
			if(v!=null)
            {
                for(int i=0;i<v.size();i++)
                {
					/**Get the values from vector for using in other methods */

    				String quesid=((QuizFileEntry)v.get(i)).getQuestionID();
                   	String filename = ((QuizFileEntry)v.get(i)).getFileName();
                   	String ques =((QuizFileEntry)v.get(i)).getQuestion();
                   	String stdtans=((QuizFileEntry)v.get(i)).getStudentAnswer();
                   	String Instans=((QuizFileEntry)v.get(i)).getInstructorAnswer();
                   	String quesmark=((QuizFileEntry)v.get(i)).getMarksPerQuestion();
                   	String awardedmark=((QuizFileEntry)v.get(i)).getAwardedMarks();
					String noOfAts=((QuizFileEntry)v.get(i)).getnoofAttribute();
                    //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----qtype-->"+qtype);
      //              ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----stdtans-->"+stdtans);
        //            ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----Instans-->"+Instans);
          //          ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----noOfAts-->"+noOfAts);

					//if(questype.equals("mcq")){
					if(noOfAts.equals("11"))
                    {
                        option1=((QuizFileEntry)v.get(i)).getOption1();
                        option2=((QuizFileEntry)v.get(i)).getOption2();
                        option3=((QuizFileEntry)v.get(i)).getOption3();
                        option4=((QuizFileEntry)v.get(i)).getOption4();
						appendAnswerPractice(xmlWriter,quesid,filename,ques,stdtans,Instans,quesmark,awardedmark,option1,option2,option3,option4,seq);
					}
/*
					else
                    {
                        ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----questype--->"+questype);
					    appendAnswerPractice(xmlWriter,quesid,filename,ques,stdtans,Instans,quesmark,awardedmark,questype,seq);
					}
*/
                    else if(questype.equalsIgnoreCase("sart"))
                    {
                        //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----SART");
                        min=((QuizFileEntry)v.get(i)).getMin();
                        max=((QuizFileEntry)v.get(i)).getMax();
                        //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----1449--min-->");
                        double min_r = Double.parseDouble(min);
                        double max_r = Double.parseDouble(max);
                        //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----1452--min-->"+min_r+"-max-->"+max_r);
                        appendAnswerPractice(xmlWriter,quesid,filename,ques,stdtans,min_r,max_r,quesmark,awardedmark,questype,seq);
                    }
                    else
                    {
              //          ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----WriteinStudtAnswerxml()----OTHER--->");
                        appendAnswerPractice(xmlWriter,quesid,filename,ques,stdtans,Instans,quesmark,awardedmark,questype,seq);
                    }


				}
			}
		}
		catch(Exception e){
            ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter WriteinSecurityxml]::"+e);
        }
        return xmlWriter;
	}

	/**
	*This method read existing xml file(score.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
	public static XmlWriter WriteinScorexml(String filePath,String xmlfile)
      	{
		int seq=-1;
        	XmlWriter xmlWriter=null;
              	File descFile=new File(filePath+"/"+xmlfile);
              	try{
			/**Get the details from xml file and store in a vector
                          *after storing in xml file delete the xml file and create the new xml file
                          */
                	QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                      	Vector v=quizMetaData.attemptedQuiz();
                      	descFile.delete();
                      	OLESRootOnly(descFile.getAbsolutePath());
                      	xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
			if(v!=null){
				/**Get the value from vector for comparing in attributes and use in methods.*/
                      		for(int i=0;i<v.size();i++)
                      		{
					String quizid=((QuizFileEntry)v.get(i)).getQuizID();
                                	String userid = ((QuizFileEntry)v.get(i)).getUserID();
                                	String totalscore =((QuizFileEntry)v.get(i)).getScore();
                                	String usedtime=((QuizFileEntry)v.get(i)).getUsedTime();
                                	String noOfAts=((QuizFileEntry)v.get(i)).getnoofAttribute();
					if(noOfAts.equals("5"))
					{
                                		String evaluate=((QuizFileEntry)v.get(i)).getEvaluate();
						writeScore(xmlWriter,quizid,userid,Integer.parseInt(totalscore),usedtime,seq,evaluate);
					}
					else
					writeScore(xmlWriter,quizid,userid,Integer.parseInt(totalscore),usedtime,seq);
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter WriteinSecurityxml]::"+e);
                }
                return xmlWriter;
	}

	/**Method for append attribute in xml for MultipleChoiceType Question
          * @param questionID String
          * @param question String
          * @param option1 String
          * @param option2 String
          * @param option3 String
          * @param option4 String
          * @param answer String
          * @param marks String
          * @param fileName String
          * @param creationDate String
	  */
	public static void appendRandomQuizSettinglist(XmlWriter xmlWriter,String questionID,String question,String option1, String option2, String option3, String option4, String answer,String marks, String fileName, String creationDate)
        {
                AttributesImpl ats=new AttributesImpl();
                ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
                ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
                ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
                ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
                ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));
                ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
                ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                ats.addAttribute("","CreationDate","","",StringUtil.replaceXmlSpecialCharacters(creationDate));
                xmlWriter.appendElement("QuizQuestions",null,ats);
        }

	/**Method for append attribute in xml for T/F,ShortAnswer,LongAnswer type Question
          * @param questionID String
          * @param question String
          * @param answer String
          * @param marks String
          * @param fileName String
          * @param creationDate String
	  */
	 public static void appendRandomQuizSettinglist(XmlWriter xmlWriter,String questionID,String question, String answer, String marks, String fileName, String creationDate)
        {
     //           ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----appendRandomQuizSettinglist()---1397--->"+question);
                AttributesImpl ats=new AttributesImpl();
                ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                ats.addAttribute("","Answer","","",StringUtil.replaceXmlSpecialCharacters(answer));
                ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
                ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                ats.addAttribute("","CreationDate","","",StringUtil.replaceXmlSpecialCharacters(creationDate));
                xmlWriter.appendElement("QuizQuestions",null,ats);
        }

    /**Method for append attribute in xml for ShortAnswerRange type Question
      * @param questionID String
      * @param question String
      * @param min String
      * @param max String
      * @param marks String
      * @param fileName String
      * @param creationDate String
      */
    public static void appendRandomQuizSettinglist(XmlWriter xmlWriter,String questionID,String question,String min,String max, String marks, String fileName, String creationDate)
      {
       //          ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----appendRandomQuizSettinglist()---1418--->"+question);
                 AttributesImpl ats=new AttributesImpl();
                 ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                 ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                 ats.addAttribute("","Min","","",StringUtil.replaceXmlSpecialCharacters(min));
                 ats.addAttribute("","Max","","",StringUtil.replaceXmlSpecialCharacters(max));
                 ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(marks));
                 ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                 ats.addAttribute("","CreationDate","","",StringUtil.replaceXmlSpecialCharacters(creationDate));
                 xmlWriter.appendElement("QuizQuestions",null,ats);
                 //ErrorDumpUtil.ErrorLog("QuizMetaDataXmlWriter-----appendRandomQuizSettinglist()---1418--->");

       }


     /**
     * This method append final answers in existing xml (userid.xml) file for sart type
     * @param xmlWriter XmlWriter
     * @param questionID String
     * @param file name String
     * @param min String
     * @param max String
     * @param marks per question String
     * @param awarded marks String
     * @param sequence int
     * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
     */
     public static void appendAnswerPractice(XmlWriter xmlWriter,String questionID,String fileName,String question,String studentAnswer,double min,double max,String markPerQues,String awardedMarks,String quesType, int seq){
                try{
                String min_r = String.valueOf(min);//min.toString();
                String max_r = String.valueOf(max);//max.toString();

                ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----appendAnswerPractice()---min&max");
                AttributesImpl ats=new AttributesImpl();
                ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                ats.addAttribute("","StudentAnswer","","",StringUtil.replaceXmlSpecialCharacters(studentAnswer));
                //ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
                ats.addAttribute("","Min","","",StringUtil.replaceXmlSpecialCharacters(min_r));
                ats.addAttribute("","Max","","",StringUtil.replaceXmlSpecialCharacters(max_r));
                ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));
                ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
                if(seq != -1){
                        xmlWriter.changeAttributes("QuizQuestions",ats,seq);
                }
                else{
                        xmlWriter.appendElement("QuizQuestions",null,ats);
                }
                }catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in quiz writer method :appendAnswerPractice !!"+e);
//                      data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
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
     public static void appendAnswerPractice(XmlWriter xmlWriter,String questionID,String fileName,String question,String studentAnswer,String realAnswer,String markPerQues,String awardedMarks,String quesType, int seq){
                try{
                //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----appendAnswerPractice()---other");
                AttributesImpl ats=new AttributesImpl();
                ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                ats.addAttribute("","StudentAnswer","","",StringUtil.replaceXmlSpecialCharacters(studentAnswer));
                ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
                ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));
                ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
                if(seq != -1){
                        xmlWriter.changeAttributes("QuizQuestions",ats,seq);
                }
                else{
                        xmlWriter.appendElement("QuizQuestions",null,ats);
                }
                }catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in quiz writer method :appendAnswerPractice !!"+e);
//                      data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
                }
        }
	/**Method for append answer practice attribute in xml
          * @param questionID String
          * @param fileName String
          * @param question String
          * @param studentAnswer String
          * @param realAnswer String
          * @param markPerQues String
          * @param awardedMarks String
          * @param option1 String
          * @param option2 String
          * @param option3 String
          * @param option4 String
          * @param seq int
	  */
	public static void appendAnswerPractice(XmlWriter xmlWriter,String questionID,String fileName,String question,String studentAnswer,String realAnswer,String markPerQues,String awardedMarks,String option1,String option2,String option3,String option4, int seq){
                try{
                //ErrorDumpUtil.ErrorLog("----QuizMetaDataXmlWriter----appendAnswerPractice()--option--");
                AttributesImpl ats=new AttributesImpl();
                ats.addAttribute("","QuestionID","","",StringUtil.replaceXmlSpecialCharacters(questionID));
                ats.addAttribute("","FileName","","",StringUtil.replaceXmlSpecialCharacters(fileName));
                ats.addAttribute("","Question","","",StringUtil.replaceXmlSpecialCharacters(question));
                ats.addAttribute("","StudentAnswer","","",StringUtil.replaceXmlSpecialCharacters(studentAnswer));
                ats.addAttribute("","InstructorAnswer","","",StringUtil.replaceXmlSpecialCharacters(realAnswer));
                ats.addAttribute("","QuestionMarks","","",StringUtil.replaceXmlSpecialCharacters(markPerQues));
                ats.addAttribute("","AwardedMarks","","",StringUtil.replaceXmlSpecialCharacters(awardedMarks));
                ats.addAttribute("","OptionA","","",StringUtil.replaceXmlSpecialCharacters(option1));
                ats.addAttribute("","OptionB","","",StringUtil.replaceXmlSpecialCharacters(option2));
                ats.addAttribute("","OptionC","","",StringUtil.replaceXmlSpecialCharacters(option3));
                ats.addAttribute("","OptionD","","",StringUtil.replaceXmlSpecialCharacters(option4));
		/**If Sequence is -1, call changeAttributes of xmlWriter
		  *else call appendElement of xmlWriter
		  */
                if(seq != -1){
                        xmlWriter.changeAttributes("QuizQuestions",ats,seq);
                }
                else{
                        xmlWriter.appendElement("QuizQuestions",null,ats);
                }
                }catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in quiz writer method :appendAnswerPractice !!"+e);
//                      data.setMessage("some problem to save answer kindly See ExceptionLog !! " );
                }
        }

	/**
         * This method append element in existing xml (quizid_PracticeQuizInfo.xml) file
         * @param xmlWriter XmlWriter
         * @param topic name String
         * @param type of question String
         * @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a>
        */
        public static void appendPracticeQuizInfo(XmlWriter xmlWriter,String StudentID,int NoofAttempt,int seq)
        {
		try{
        		AttributesImpl ats=new AttributesImpl();
	    		String Attemptno = String.valueOf(NoofAttempt);
            		ats.addAttribute("","StudentID","","",StringUtil.replaceXmlSpecialCharacters(StudentID));
            		ats.addAttribute("","NoofAttempt","","",StringUtil.replaceXmlSpecialCharacters(Attemptno));
	    		if(seq != -1){
                		xmlWriter.changeAttributes("Quiz",ats,seq);
			}
			else{
            			xmlWriter.appendElement("Quiz",null,ats);
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("Error in QuizMetaDataXmlWriter method :appendPracticeQuizInfo !!"+e);
                }

        }

	/**
        *This method read existing xml file(quizID_PracticeQuizInfo.xml) and write new xml file with old values
        * @param filePath String
        * @param xmlFile String
        * @return xmlWriter XmlWriter
        */
        public static XmlWriter Write_PracticeQuizInfoxml(String filePath,String xmlfile)
        {
		int seq=-1;
                XmlWriter xmlWriter=null;
                File descFile=new File(filePath+"/"+xmlfile);
                try{
                        /**Get the details from xml file and store in a vector
                          *after storing in xml file delete the xml file and create the new xml file
                          */
                        QuizMetaDataXmlReader quizMetaData=new QuizMetaDataXmlReader(filePath+"/"+xmlfile);
                        Vector v=quizMetaData.getAttemptPracticeQuizDetail();
                        descFile.delete();
                        OLESRootOnly(descFile.getAbsolutePath());
                        xmlWriter=new XmlWriter(filePath+"/"+xmlfile);
                        if(v!=null){
                                /**Get the value from vector for comparing in attributes and use in methods.*/
                                for(int i=0;i<v.size();i++)
                                {
                                        String studentid = ((QuizFileEntry)v.get(i)).getStudentID();
                                        String noofattempt =((QuizFileEntry)v.get(i)).getNoofAttempt();
                                        appendPracticeQuizInfo(xmlWriter,studentid,Integer.parseInt(noofattempt),seq);
                                }
                        }
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The exception in QuizMetaDataXmlWriter [XmlWriter Write_PracticeQuizInfoxml]:"+e);
                }
                return xmlWriter;
	}

//-------------------------------------------------------IITKanpur---------------------------------

}
