package org.iitk.brihaspati.modules.utils;

/*
 * @(#)QuizMetaDataXmlReader.java
 *
 *  Copyright (c) 2010-2011 DEI, Agra
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
 *  Contributors: Members of MHRD, DEI, Agra
 *
 */

import java.util.HashMap;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.iitk.brihaspati.modules.utils.XmlData;
import org.iitk.brihaspati.modules.utils.XmlReader;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
/**
 * This class Read Xml file with attributes and values
 * @author: <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author: <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
 */

public class QuizMetaDataXmlReader
{
	XmlReader xr;
	/**
	 * This method create XmlReader type object 
	 * @param file String
	 */
	public QuizMetaDataXmlReader(String file) throws Exception
	{
		xr=new XmlReader(file);
	}
	/**
	 * This method get details of Topic Description 
	 * @return String
	 */

	public String getTopicDescription()
	{
		XmlData xmlDesc=xr.getElement("Desc",0);
		return xmlDesc.getData();
	}

	public String getActivity()
	{
		XmlData xmlactivity=xr.getElement("activity",0);
		return xmlactivity.getData();
	}
	
	/**
	 * This method get all details of Quiz.xml
	 * @return Vector
	 * @author Nupur Dixit
	 **/
	public Vector getQuesBanklist_Detail()
	{
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("Quiz");
			ErrorDumpUtil.ErrorLog("before if inside quiz reader");
			if(files!=null)
			{
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,fileName, status,quizMode,modifiedDate;				
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();					
					quizID = ats.getValue("QuizID");
					quizName = ats.getValue("QuizName");
					maxMarks = ats.getValue("MaxMarks");
					maxTime = ats.getValue("MaxTime");
					noQuestion = ats.getValue("NumberQuestion");
					creationDate = ats.getValue("CreationDate");
					fileName = ats.getValue("Filename");
					status = ats.getValue("status");
					quizMode = ats.getValue("QuizMode");
					modifiedDate = ats.getValue("ModifiedDate");
					fileEntry.setQuizID(quizID);
					fileEntry.setQuizName(quizName);
					fileEntry.setMaxMarks(maxMarks);
					fileEntry.setMaxTime(maxTime);
					fileEntry.setQuizStatus(status);
					fileEntry.setCreationDate(creationDate);
					fileEntry.setModifiedDate(modifiedDate);
					fileEntry.setnoQuestion(noQuestion);
					fileEntry.setQuizFileName(fileName);  
					fileEntry.setQuizMode(quizMode);  
					vt.add(fileEntry);
				}
				return vt;
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);			
		}
		return null;
	}
	/**
	 * This method get maximum quizid to generate new quizID           
	 * @return String
	 * @author Nupur Dixit
	 */
	public String getMaxQuizID(){
		String maxQuizID = "Quiz1";
		Vector vt=new Vector();
		try {
			XmlData files[]=xr.getElements("Quiz");
			ErrorDumpUtil.ErrorLog("before if inside quiz reader");
			int max = 0;
			if(files!=null) {
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,fileName, status;
				ErrorDumpUtil.ErrorLog("after if inside quiz reader"+files.length);
				for(int j=0;j<files.length;j++) {
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizID = ats.getValue("QuizID"); 					
					int num=Integer.parseInt(quizID.substring(4));					
					if(num>max){
						max = num;
					}        			
				}
				maxQuizID = "Quiz"+(max+1);
				return maxQuizID;
			}
			return maxQuizID;
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);			
		}
		return maxQuizID;
	}
        
	/**
	 * This method get quiz_questions detail from the quizID_Questions.xml           
	 * @return vector
	 * @author Aayushi Sr.
	 */
	public Vector getQuizQuestionDetail()
	{
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null)
			{
				Attributes ats;
				ErrorDumpUtil.ErrorLog("length is == "+files.length);
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					ErrorDumpUtil.ErrorLog("inside quiz reader"+j);
					fileEntry.setTopic(ats.getValue("TopicName"));
					fileEntry.setQuestionType(ats.getValue("QuestionType"));
					fileEntry.setQuestionLevel(ats.getValue("QuestionLevel"));
					fileEntry.setMarksPerQuestion(ats.getValue("QuestionMarks"));
					fileEntry.setQuestionNumber(ats.getValue("QuestionNumber"));
					vt.add(fileEntry);        				                                        
				}        			
			}        		
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in getquizquestiondetail ::"+e);			
		}    
		return vt;
	}
	
	/**
	 * This method get total counting and marks counting of already inserted questions
	 * @param xmlReader QuizMetaDataXmlReader (reader of quizId_questions.xml)
	 * @param quizID String
	 * @return hashmap
	 * @author nupur dixit
	 */
	public HashMap getQuizQuestionNoMarks(QuizMetaDataXmlReader questionReader,String quizID){
		HashMap hm = new HashMap(); 
		try{
			int markscount = 0;
			int numberofquestionsInserted = 0;
			Vector questionList = new Vector();
			questionList = questionReader.getQuizQuestionDetail();
			if(questionList!=null & questionList.size()!=0){	        		
				for(int j=0;j<questionList.size();j++){
					markscount = markscount + Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getMarksPerQuestion());
					numberofquestionsInserted = numberofquestionsInserted + Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getQuestionNumber());
				}				     	
			}
			hm.put("marks", markscount);
			hm.put("noQuestion", numberofquestionsInserted);
			ErrorDumpUtil.ErrorLog("marks & number of question inserted "+markscount+numberofquestionsInserted);		        			        		        			        	        		      		
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in getQuizQuestionNoMarks ::"+e);			
		}    
		return hm;	
	}//method end
        
	/**
	 * This method get quiz detail on the basis of the passed status    
	 * @param status String
	 * @return vector
	 * @author nupur dixit
	 */
	public Vector getStatusQuiz_Detail(String status)
	{
		ErrorDumpUtil.ErrorLog("inside function "+status);
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("Quiz");
			ErrorDumpUtil.ErrorLog("before if inside quiz reader");
			if(files!=null)
			{
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,quizFileName, quizStatus,modifiedDate,quizMode;
				ErrorDumpUtil.ErrorLog("after if inside quiz reader"+files.length);
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					ErrorDumpUtil.ErrorLog("inside for of quiz reader"+j);
					quizID = ats.getValue("QuizID");
					quizName = ats.getValue("QuizName");
					maxMarks = ats.getValue("MaxMarks");
					maxTime = ats.getValue("MaxTime");
					noQuestion = ats.getValue("NumberQuestion");
					creationDate = ats.getValue("CreationDate");
					quizFileName = ats.getValue("Filename");
					quizStatus = ats.getValue("status");
					quizMode = ats.getValue("QuizMode");
					modifiedDate = ats.getValue("ModifiedDate");
					if(quizStatus.equalsIgnoreCase(status)){
						fileEntry.setQuizID(quizID);
						fileEntry.setQuizName(quizName);
						fileEntry.setMaxMarks(maxMarks);
						fileEntry.setMaxTime(maxTime);
						fileEntry.setQuizStatus(quizStatus);
						fileEntry.setCreationDate(creationDate);
						fileEntry.setnoQuestion(noQuestion);
						fileEntry.setQuizFileName(quizFileName);
						fileEntry.setQuizMode(quizMode);
						fileEntry.setModifiedDate(modifiedDate);
						vt.add(fileEntry);
					}                                        
				}
				return vt;
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);
		}
		return null;
	}


	/**
	 * This method get quiz detail on the basis of the passed quizID    
	 * @param quizID String
	 * @return vector
	 * @author nupur dixit
	 */        
	public Vector getQuiz_Detail(String quizid)
	{
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("Quiz");
			ErrorDumpUtil.ErrorLog("value of passes quiz id"+quizid);
			if(files!=null)
			{
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,quizFileName, quizStatus,quizMode,modifiedDate; 			
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					ErrorDumpUtil.ErrorLog("inside for of quiz reader"+j);
					quizID = ats.getValue("QuizID");
					quizName = ats.getValue("QuizName");
					maxMarks = ats.getValue("MaxMarks");
					maxTime = ats.getValue("MaxTime");
					noQuestion = ats.getValue("NumberQuestion");
					creationDate = ats.getValue("CreationDate");
					quizFileName = ats.getValue("Filename");
					quizStatus = ats.getValue("status");
					quizMode = ats.getValue("QuizMode");
					modifiedDate = ats.getValue("ModifiedDate");        				
					if(quizID.equalsIgnoreCase(quizid)){
						fileEntry.setQuizID(quizID);
						fileEntry.setQuizName(quizName);
						fileEntry.setMaxMarks(maxMarks);
						fileEntry.setMaxTime(maxTime);
						fileEntry.setQuizStatus(quizStatus);
						fileEntry.setCreationDate(creationDate);
						fileEntry.setnoQuestion(noQuestion);
						fileEntry.setQuizFileName(quizFileName);
						fileEntry.setQuizMode(quizMode);
						fileEntry.setModifiedDate(modifiedDate);
						vt.add(fileEntry);
						ErrorDumpUtil.ErrorLog("value of status in file entry"+fileEntry.getQuizStatus());
					}                                        
				}
				ErrorDumpUtil.ErrorLog("size of vector"+vt.size());
				return vt;
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);
		}
		return null;
	}	

	/**
	 * This method gets all questions from question bank for random quiz
	 * @param type of question String
	 * @return Vector
	 */
	public Vector getRandomQuizQuestions(String typeName)
	{
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("Question");
			if(files!=null)
			{
				Attributes ats;
				String questionID,question,option1,option2,option3,option4,answer;
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					questionID=ats.getValue("Quesid");
					question=ats.getValue("Ques");
					if(typeName.equalsIgnoreCase("mcq"))
					{
						option1=ats.getValue("opt1");
						option2=ats.getValue("opt2");
						option3=ats.getValue("opt3");
						option4=ats.getValue("opt4");
						fileEntry.setOption1(option1);
						fileEntry.setOption2(option2);
						fileEntry.setOption3(option3);
						fileEntry.setOption4(option4);
					}
					answer=ats.getValue("Answer");
					fileEntry.setQuestionID(questionID);
					fileEntry.setQuestion(question);                               
					fileEntry.setAnswer(answer);                               
					vt.add(fileEntry);
				}
				return vt;
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in quizmetadataxmlreaderutil in getrandomquizquestionsmethod ::"+e);			
		}
		return null;
	}


	/**
 	 * This method gets all already inserted questions from quizid_question.xml on the 
 	 * basis of the passed file name 
	 * @param topicname_level_type.xml file name String
	 * @return Vector
	 */
	public Vector getQuizQuestions(String questionBankQuestionsPath)
	{
		Vector vt=new Vector();
		try
		{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null)
			{
				Attributes ats;
				String questionID,fileName;
				for(int j=0;j<files.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					questionID=ats.getValue("QuestionID");
					fileName=ats.getValue("FileName");                                 
					if(fileName.equalsIgnoreCase(questionBankQuestionsPath))
					{
						fileEntry.setQuestionID(questionID);
						fileEntry.setFileName(fileName);
						vt.add(fileEntry);                                        
					}                               
				}
				if(vt!=null){
					if(vt.size()==0){
						vt=null;                                	
					}
				}
				return vt;
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in quizmetadataxmlreaderutil in getquizquestionsmethod ::"+e);
			System.out.println("See Exception message inExceptionLog.txt file:: ");
		}
		return null;
	}

	/**
	 * This method gets all Topic names stored in QBtopiclist.xml (under question bank folder)
	 * @return vector
	 */
	public Vector getTopicNames(){
		try
		{
			XmlData file[]=xr.getElements("Question");
			if(file!=null)
			{
				Vector v=new Vector();
				Attributes ats;
				String topic;
				for(int j=0;j<file.length;j++)
				{
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=file[j].getAttributes();
					topic=ats.getValue("Topicname");
					fileEntry.setTopic(topic);                    
					v.addElement(fileEntry);
				}
				return v;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in quizmetadataxmlreaderutil in gettopicnamemethod::"+e);
			System.out.println("See Exception message in ExceptionLog.txt file:: ");
		}
		return null;
	}	    	     
}//end of  file
