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

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
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

public class QuizMetaDataXmlReader{
	XmlReader xr;
	/**
	 * This method create XmlReader type object 
	 * @param file String
	 */
	public QuizMetaDataXmlReader(String file) throws Exception{
		xr=new XmlReader(file);
	}
	
	/**
	 * This method get details of Topic Description 
	 * @return String
	 */
	public String getTopicDescription(){
		XmlData xmlDesc=xr.getElement("Desc",0);
		return xmlDesc.getData();
	}

	public String getActivity(){
		XmlData xmlactivity=xr.getElement("activity",0);
		return xmlactivity.getData();
	}
	
	/**
	 * This method get all details of Quiz.xml
	 * @return Vector
	 * @author Nupur Dixit
	 **/
	public Vector getQuesBanklist_Detail(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,fileName, status,quizMode,modifiedDate;				
				for(int j=0;j<files.length;j++){
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
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuesBanklist_Detail !! "+e);		
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
			int max = 0;
			if(files!=null) {
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,fileName, status;
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
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getMaxQuizID !! "+e);			
		}
		return maxQuizID;
	}
        
	/**
	 * This method get quiz_questions detail from the quizID_Questions.xml except the specified topicid        
	 * @param topicID String  
	 * @return vector
	 * @author Nupur Dixit.
	 */
	public Vector getQuizQuestionDetail(String id){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					if(ats.getValue("ID").equalsIgnoreCase(id)){						
					}
					else{
						fileEntry.setID(ats.getValue("ID"));
						fileEntry.setTopic(ats.getValue("TopicName"));
						fileEntry.setQuestionType(ats.getValue("QuestionType"));
						fileEntry.setQuestionLevel(ats.getValue("QuestionLevel"));
						fileEntry.setMarksPerQuestion(ats.getValue("QuestionMarks"));
						fileEntry.setQuestionNumber(ats.getValue("QuestionNumber"));						
						vt.add(fileEntry);						
					}					
				}        			
			}        		
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestionDetail(id) !! "+e);			
		}    
		return vt;
	}	
	
	/**
	 * This method get quiz_questions detail from the quizID_QuestionSetting.xml           
	 * @return vector
	 * @author Aayushi Sr.
	 */
	public Vector getQuizQuestionDetail(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					fileEntry.setID(ats.getValue("ID"));
					fileEntry.setTopic(ats.getValue("TopicName"));
					fileEntry.setQuestionType(ats.getValue("QuestionType"));
					fileEntry.setQuestionLevel(ats.getValue("QuestionLevel"));
					fileEntry.setMarksPerQuestion(ats.getValue("QuestionMarks"));
					fileEntry.setQuestionNumber(ats.getValue("QuestionNumber"));
					vt.add(fileEntry);					
				}        			
			}        		
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestionDetail !! "+e);			
		}    
		return vt;
	}
	
	/**
	 * This method get total counting and marks counting of already inserted questions
	 * @param xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml)
	 * @param quizID String
	 * @param id String(rowID)
	 * @return hashmap
	 * @author Nupur Dixit
	 */
	public HashMap getQuizQuestionNoMarks(QuizMetaDataXmlReader questionReader,String quizID, String id){
		HashMap hm = new HashMap(); 
		try{
			int markscount = 0;
			int numberofquestionsInserted = 0;
			Vector questionList = new Vector();			
			questionList = questionReader.getQuizQuestionDetail(id);
			if(questionList!=null & questionList.size()!=0){	        		
				for(int j=0;j<questionList.size();j++){
					int question = Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getQuestionNumber());
					int marks = Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getMarksPerQuestion());
					numberofquestionsInserted = numberofquestionsInserted + question;
					markscount = markscount + question*marks;
				}				     	
			}
			hm.put("marks", markscount);
			hm.put("noQuestion", numberofquestionsInserted);					        			        		        			        	        		      		
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestionNoMarks(id) !! "+e);			
		}    
		return hm;	
	}
        
	/**
	 * This method get total counting and marks counting of already inserted questions
	 * @param xmlReader QuizMetaDataXmlReader (reader of quizId_questionSetting.xml)
	 * @param quizID String
	 * @return hashmap
	 * @author Nupur Dixit
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
					int question = Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getQuestionNumber());
					int marks = Integer.parseInt(((QuizFileEntry) questionList.elementAt(j)).getMarksPerQuestion());
					numberofquestionsInserted = numberofquestionsInserted + question;
					markscount = markscount + question*marks;
				}				     	
			}
			hm.put("marks", markscount);
			hm.put("noQuestion", numberofquestionsInserted);					        			        		        			        	        		      		
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestionNoMarks !! "+e);			
		}    
		return hm;	
	}
	
	/**
     * This method get quiz detail on the basis of the passed status   
     * @param status String
     * @return vector
     * @author Nupur Dixit
     */
    public Vector getStatusQuiz_Detail(String status){
        Vector vt=new Vector();
        try{
            XmlData files[]=xr.getElements("Quiz");
            if(files!=null){
                Attributes ats;
                String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,quizFileName, quizStatus,modifiedDate,quizMode;
                String startDate, startTime, endDate, endTime, allowPractice;
                for(int j=0;j<files.length;j++){
                    QuizFileEntry fileEntry=new QuizFileEntry();
                    ats=files[j].getAttributes();
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
                    startDate = ats.getValue("ExamDate");
                    startTime = ats.getValue("StartTime");
                    endDate = ats.getValue("ExpiryDate");
                    endTime = ats.getValue("EndTime");
                    allowPractice = ats.getValue("AllowPractice");
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
                        fileEntry.setExamDate(startDate);
                        fileEntry.setStartTime(startTime);
                        fileEntry.setExpiryDate(endDate);
                        fileEntry.setEndTime(endTime);
                        fileEntry.setAllowPractice(allowPractice);
                        vt.add(fileEntry);
                    }                                       
                }
                return vt;
            }
        }catch(Exception e){
            ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getStatusQuiz_Detail !! "+e);           
        }
        return null;
    }
	/**
	 * This method get quiz detail on the basis of the passed quizmode    
	 * @param status String
	 * @return vector
	 * @author Nupur Dixit
	 */
	public Vector getQuizDetailForPreview(String quizMode){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizID, quizName,maxMarks,maxTime,noQuestion,quizmode,allowPractice;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizID = ats.getValue("QuizID");
					quizName = ats.getValue("QuizName");
					maxMarks = ats.getValue("MaxMarks");
					maxTime = ats.getValue("MaxTime");
					noQuestion = ats.getValue("NumberQuestion");
					quizmode = ats.getValue("QuizMode");
					allowPractice = ats.getValue("AllowPractice");
					if(quizmode.equalsIgnoreCase(quizMode) && allowPractice.equalsIgnoreCase("no")){
						fileEntry.setQuizID(quizID);
						fileEntry.setQuizName(quizName);
						fileEntry.setMaxMarks(maxMarks);
						fileEntry.setMaxTime(maxTime);
						fileEntry.setnoQuestion(noQuestion);
						fileEntry.setQuizMode(quizMode);
						vt.add(fileEntry);
					}                                        
				}
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getModeQuiz_Detail !! "+e);			
		}
		return null;
	}
	

	/**
     * This method get quiz detail on the basis of the passed quizID   
     * @param quizID String
     * @return vector
     * @author Nupur Dixit
     */       
    public Vector getQuiz_Detail(String quizid){
        Vector vt=new Vector();
        try{
            XmlData files[]=xr.getElements("Quiz");
            if(files!=null)
            {
                Attributes ats;
                String quizID, quizName,maxMarks,maxTime,noQuestion,creationDate,quizFileName, quizStatus,quizMode,modifiedDate;
                String startDate, startTime, endDate, endTime, allowPractice;
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
                    quizFileName = ats.getValue("Filename");
                    quizStatus = ats.getValue("status");
                    quizMode = ats.getValue("QuizMode");
                    modifiedDate = ats.getValue("ModifiedDate"); 
                    startDate = ats.getValue("ExamDate");
                    startTime = ats.getValue("StartTime");
                    endDate = ats.getValue("ExpiryDate");
                    endTime = ats.getValue("EndTime");
                    allowPractice = ats.getValue("AllowPractice");
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
                        fileEntry.setExamDate(startDate);
                        fileEntry.setStartTime(startTime);
                        fileEntry.setExpiryDate(endDate);
                        fileEntry.setEndTime(endTime);
                        fileEntry.setAllowPractice(allowPractice);
                        vt.add(fileEntry);                       
                    }                                       
                }
                return vt;
            }
        }catch(Exception e){
            ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuiz_Detail !! "+e);           
        }
        return null;
    }    

	/**
	 * This method gets all questions from question bank for random quiz
	 * @param type of question String
	 * @return Vector
	 * @author Aayushi Sr
	 */
	public Vector getRandomQuizQuestions(String typeName){
		Vector<QuizFileEntry> vt=new Vector<QuizFileEntry>();
		try{
			XmlData files[]=xr.getElements("Question");
			if(files!=null){
				Attributes ats;
				String questionID,question,option1,option2,option3,option4,answer;
				for(int j=0;j<files.length;j++){
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
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getRandomQuizQuestions !! "+e);			
		}
		return null;
	}
	
	/**
     * This method gets all inserted questions for random quiz
     * @param path of xml file String
     * @param maximum number of question for quiz String
     * @param maximum number of marks for quiz Integar
     * @return String[]
     * @author Nupur Dixit
     */
    public String[] getQuizQuestions(String questionBankQuestionsPath, String numberQuestion, int maxQuestions){
        String vt[]=new String[2];
        Arrays.fill(vt, "a");
        try{
            XmlData files[]=xr.getElements("QuizQuestions");
            if(files!=null)
            {
                Attributes ats;
                String questionNumber,fileName;
                int count=0;
                for(int j=0;j<files.length;j++){
                    QuizFileEntry fileEntry=new QuizFileEntry();
                    ats=files[j].getAttributes();
                    questionNumber=ats.getValue("QuestionNumber");
                    fileName=ats.getValue("TopicName")+"_"+ats.getValue("QuestionLevel")+"_"+ats.getValue("QuestionType")+".xml";
                    if(fileName.equalsIgnoreCase(questionBankQuestionsPath)){
                        count=count+Integer.parseInt(questionNumber);                        
                    }                            
                }
                vt[0]=String.valueOf(count);                       
                return vt;
            }
            else{
                vt[0] = "firstEntry";
                return vt;
            }
        }catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestions !! "+e);
        }
        return vt;
    }
            
    /**
     * This method gets all inserted questions for random quiz
     * @param path of xml file String
     * @param topic id String
     * @return String[]
     * @author Nupur Dixit
     */
    public String[] getQuizQuestions(String questionBankQuestionsPath, String topicid){
        String vt[]=new String[2];
        Arrays.fill(vt, "a");
        try{
            XmlData files[]=xr.getElements("QuizQuestions");
            if(files!=null){
                Attributes ats;
                String questionNumber,fileName,topicID;
                int count=0;
                for(int j=0;j<files.length;j++){
                    QuizFileEntry fileEntry=new QuizFileEntry();
                    ats=files[j].getAttributes();
                    questionNumber=ats.getValue("QuestionNumber");
                    fileName=ats.getValue("TopicName")+"_"+ats.getValue("QuestionLevel")+"_"+ats.getValue("QuestionType")+".xml";
                    topicID = ats.getValue("ID");
                    if(fileName.equalsIgnoreCase(questionBankQuestionsPath)){
                    	if(topicID.equalsIgnoreCase(topicid)){                    	
                    	}
                    	else{
                    		count=count+Integer.parseInt(questionNumber);
                        }
                    }                            
                }
                vt[0]=String.valueOf(count);                       
                return vt;
            }
            else{
                vt[0] = "firstUpdate";
                return vt;
            }
        }catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getQuizQuestions(id) !! "+e);
        }
        return vt;
    }

	/**
	 * This method gets all Distinct Topic names stored in QBtopiclist.xml (under question bank folder)
	 * @return vector
	 * @author Nupur Dixit
	 */
	public Vector getTopicNames(){
		try{
			XmlData file[]=xr.getElements("Question");
			if(file!=null){
				int k=0;
				boolean found=false;
				Vector v=new Vector();
				Attributes ats;
				String topic;
				String a[] = new String[file.length];
				Arrays.fill(a, "aa");
				for(int j=0;j<file.length;j++){					
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=file[j].getAttributes();
					topic=ats.getValue("Topicname");
					//this coding to remove the duplicate topic names
					for(int i=0;i<j;i++){
						if(a[i].equalsIgnoreCase(topic)){	
							found = true;
							break;
						}
						else{
							found = false;
						}
					}				
					if(found){
						continue;
					}
					else{
						a[k] = topic;
						k++;
						fileEntry.setTopic(topic);                    
						v.addElement(fileEntry);
					}										
				}
				return v;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getTopicNames !! "+e);
		}
		return null;
	}
		
	/**
     * This method gets id stored in QuizSettings.xml
     * @return String
     * @author Aayushi Sr
     */
    public String getID_RandomQuiz(){
        String maxID = "1";
        Vector vt=new Vector();
        try {
            XmlData files[]=xr.getElements("QuizQuestions");
            int max = 0;
            if(files!=null) {
                Attributes ats;
                String id;
                for(int j=0;j<files.length;j++) {
                    QuizFileEntry fileEntry=new QuizFileEntry();
                    ats=files[j].getAttributes();
                    id = ats.getValue("ID");
                    if(Integer.parseInt(id)>max){
                        max = Integer.parseInt(id);
                    }                   
                }
                maxID = ""+(max+1);
                return maxID;
            }
            return maxID;
        }catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getID_RandomQuiz !! "+e);
        }
        return maxID;
    }

    /**
	 * This method gets all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
	 * @return Vector
	 * @author Nupur Dixit
	 */
	public Vector getInsertedQuizQuestions(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String questionID,fileName,marksQuestion;
				String question,optA,optB,optC,optD,answer,type;
				optA="";
				optB="";
				optC="";
				optD="";
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					questionID=ats.getValue("QuestionID");
					question = ats.getValue("Question");
					fileName=ats.getValue("FileName");
					int index=fileName.lastIndexOf('_'); 
					type = fileName.substring((index+1),(index+4));
					if(type.equalsIgnoreCase("mcq")){
						optA = ats.getValue("OptionA");
						optB = ats.getValue("OptionB");
						optC = ats.getValue("OptionC");
						optD = ats.getValue("OptionD");
					}
					answer = ats.getValue("Answer");
					marksQuestion=ats.getValue("QuestionMarks");
					fileEntry.setQuestionID(questionID);
					fileEntry.setQuestion(question);
					if(type.equalsIgnoreCase("mcq")){
						fileEntry.setOption1(optA);
						fileEntry.setOption2(optB);
						fileEntry.setOption3(optC);
						fileEntry.setOption4(optD);
					}
					fileEntry.setAnswer(answer);
					fileEntry.setFileName(fileName);
					fileEntry.setMarksPerQuestion(marksQuestion);
					fileEntry.setQuestionType(type);
					vt.add(fileEntry);
				}				
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestions !! "+e);
		}
		return null;
	}
	
	/**
	 * This method gets all question ids and filepaths (which are already inserted) from quizquestions file of a quiz
	 * @param id String
	 * @return Vector
	 * @author Aayushi Sr
	 */
	public Vector getInsertedQuizQuestions(String id){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String questionID,fileName,marksQuestion;
				ErrorDumpUtil.ErrorLog("files length "+files.length);
				for(int j=0;j<files.length;j++){
					if(Integer.parseInt(id)==j+1){						
					}
					else{
						QuizFileEntry fileEntry=new QuizFileEntry();
						ats=files[j].getAttributes();
						questionID=ats.getValue("QuestionID");
						fileName=ats.getValue("FileName");
						marksQuestion=ats.getValue("QuestionMarks");
						fileEntry.setQuestionID(questionID);
						fileEntry.setFileName(fileName);
						fileEntry.setMarksPerQuestion(marksQuestion);
						vt.add(fileEntry);
					}
				}				
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestions(id) !! "+e);
		}
		return null;
	}
	
	
	/**
	 * This method gets the id of inserted question of a quiz
	 * @param id String
	 * @return String
	 * @author Aayushi Sr
	 */
	public String getInsertedQuizQuestionID(String id){		
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String questionID;
				for(int j=0;j<files.length;j++){
					if(Integer.parseInt(id)==j+1){
						ats=files[j].getAttributes();
						questionID=ats.getValue("QuestionID");
						return questionID;
					}					
				}			
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestionID !! "+e);
		}
		return null;
	}
	
	/**
	 * This method gets the list of all the quizzes ready to attempt by the students
	 * @return vector
	 * @exception generic Exception
	 * @author Nupur Dixit
	 */
	public Vector readyToAttemptQuiz(){	
		Vector vt = new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizid,quizName,creationDate,examDate,expiryDate,startTime,endTime,modifiedDate;
				String maxTime,maxMarks,maxQuestions;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");						
					quizName=ats.getValue("QuizName");
					maxMarks=ats.getValue("MaxMarks");
					maxTime=ats.getValue("MaxTime");
					maxQuestions=ats.getValue("NumberQuestion");
					creationDate=ats.getValue("CreationDate");
					examDate=ats.getValue("ExamDate");
					expiryDate=ats.getValue("ExpiryDate");
					startTime=ats.getValue("StartTime");
					endTime=ats.getValue("EndTime");
					modifiedDate=ats.getValue("ModifiedDate");						
					if(examDate==null){}
					else{						
						Calendar current = Calendar.getInstance();
						Calendar examDt = Calendar.getInstance();
						examDt.clear();
						Calendar expiryDt = Calendar.getInstance();
						expiryDt.clear();
						String [] exDt = examDate.split("-");
						String [] expDt = expiryDate.split("-");
						String [] stTime = startTime.split(":");
						String [] enTime = endTime.split(":");
						examDt.set(Integer.parseInt(exDt[0]),(Integer.parseInt(exDt[1])-1), Integer.parseInt(exDt[2]),Integer.parseInt(stTime[0]),Integer.parseInt(stTime[1]));
						expiryDt.set(Integer.parseInt(expDt[0]),(Integer.parseInt(expDt[1])-1), Integer.parseInt(expDt[2]),Integer.parseInt(enTime[0]),Integer.parseInt(enTime[1]));
						if(current.compareTo(examDt)==1 || current.compareTo(examDt)==0){							
							ErrorDumpUtil.ErrorLog("exam date is announced before the current date !");
							if(current.compareTo(expiryDt)==1 || current.compareTo(expiryDt)==0){
								ErrorDumpUtil.ErrorLog("quiz is expired !");
							}
							else{
								fileEntry.setQuizID(quizid);
								fileEntry.setQuizName(quizName);
								fileEntry.setMaxMarks(maxMarks);
								fileEntry.setMaxTime(maxTime);
								fileEntry.setnoQuestion(maxQuestions);
								vt.add(fileEntry);
								ErrorDumpUtil.ErrorLog("quiz is ready to attempt !");
							}
						}
						else{
							ErrorDumpUtil.ErrorLog("quiz announce date is after the current date !");
						}
					}				
				}	
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestionID !! "+e);
			//data.setMessage("See Exception Log !!");
		}
		return null;
	}
	
	/**
	 * This method get quizzes which are attempted from score.xml
	 * @return vector
	 * @author Nupur Dixit
	 */
	public Vector attemptedQuiz(){
		Vector<QuizFileEntry> vt=new Vector<QuizFileEntry>();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String quizID, userID,score;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizID = ats.getValue("QuizID");
					userID = ats.getValue("UserID");
					score = ats.getValue("TotalScore");						
					fileEntry.setQuizID(quizID);
					fileEntry.setUserID(userID);
					fileEntry.setScore(score);
					vt.add(fileEntry);					   
				}
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:attemptedQuiz !! "+e);			
		}
		return null;
	}
	
	
	/**
	 * This method get quizzes having practice flag true(open for students' practice)  
	 * @return vector
	 * @author Nupur Dixit
	 */
	public Vector getPracticeQuiz_Detail(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizID, quizName,allowPractice,maxTime,status;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizID = ats.getValue("QuizID");
					quizName = ats.getValue("QuizName");
					allowPractice = ats.getValue("AllowPractice");
					maxTime = ats.getValue("MaxTime");
					status = ats.getValue("status");
					if(allowPractice==null){}
					else{
						if(allowPractice.equalsIgnoreCase("yes") && status.equalsIgnoreCase("act")){
							fileEntry.setQuizID(quizID);
							fileEntry.setQuizName(quizName);
							fileEntry.setMaxTime(maxTime);
							vt.add(fileEntry);
						}   
					}
				}
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getPracticeeQuiz_Detail !! "+e);			
		}
		return null;
	}
	 /**
	 * This method gets all question ids and filepaths from final answer xml file(userid.xml)
	 * @return Vector
	 * @author Nupur Dixit
	 */
	public Vector getFinalAnswer(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String questionID,fileName,answer,awardedMarks,studentAnswer,instructorAnswer,question;		
				String type,optA,optB,optC,optD;
				type = optA=optB=optC=optD="";
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					questionID=ats.getValue("QuestionID");
					question = ats.getValue("Question");
					fileName=ats.getValue("FileName");					
					answer = ats.getValue("Answer");
					awardedMarks = ats.getValue("AwardedMarks");
					studentAnswer = ats.getValue("StudentAnswer");
					instructorAnswer = ats.getValue("InstructorAnswer");
					int index=fileName.lastIndexOf('_'); 
					type = fileName.substring((index+1),(index+4));
					if(type.equalsIgnoreCase("mcq")){
						optA = ats.getValue("OptionA");
						optB = ats.getValue("OptionB");
						optC = ats.getValue("OptionC");
						optD = ats.getValue("OptionD");
					}
					fileEntry.setQuestionID(questionID);
					fileEntry.setQuestion(question);
					fileEntry.setAnswer(answer);
					fileEntry.setFileName(fileName);
					fileEntry.setAwardedMarks(awardedMarks);
					fileEntry.setStudentAnswer(studentAnswer);
					fileEntry.setInstructorAnswer(instructorAnswer);
					fileEntry.setQuestionType(type);
					if(type.equalsIgnoreCase("mcq")){
						fileEntry.setOption1(optA);
						fileEntry.setOption2(optB);
						fileEntry.setOption3(optC);
						fileEntry.setOption4(optD);
					}
//					fileEntry.setQuestionType(type);
					vt.add(fileEntry);
				}				
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getFinalAnswer !! "+e);
		}
		return null;
	}
	/**
	 * This method gets all quizID,userID and score from score.xml file for a particular user
	 * @return Vector
	 * @author Nupur Dixit
	 */
	public Vector getFinalScore(String userID){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				Attributes ats;
				String quizid,userid,score;				
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");
					userid=ats.getValue("UserID");					
					score = ats.getValue("TotalScore");	
					if(userid.equals(userID)){
						fileEntry.setQuizID(quizid);										
						fileEntry.setUserID(userid);
						fileEntry.setScore(score);			
						vt.add(fileEntry);
					}					
				}				
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getFinalscore !! "+e);
		}
		return null;
	}
	/**
	 * This method gets all distinct quizID,userID and score from score.xml file
	 * @return Vector
	 * @author Nupur Dixit
	 */
	public Vector getDistinctIDFromFinalScore(){
		Vector vt=new Vector();
		try{
			XmlData files[]=xr.getElements("QuizQuestions");
			if(files!=null){
				int k=0;
				boolean found=false;				
				Attributes ats;
				String quizid,userid,score;		
				String a[] = new String[files.length];
				Arrays.fill(a, "aa");
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");
					userid=ats.getValue("UserID");					
					score = ats.getValue("TotalScore");
					//this coding to remove the duplicate quiz ids
					for(int i=0;i<j;i++){
						if(a[i].equalsIgnoreCase(quizid)){	
							found = true;
							break;
						}
						else{
							found = false;
						}
					}				
					if(found){
						continue;
					}
					else{
						a[k] = quizid;
						k++;
						fileEntry.setQuizID(quizid);										
						fileEntry.setUserID(userid);
						fileEntry.setScore(score);			
						vt.add(fileEntry);
					}								
				}				
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getFinalAnswer !! "+e);
		}
		return null;
	}
				
	/**
	 * This method get sequence number from score.xml file with the same quizid and userid
	 * @return Vector
	 * @author Nupur Dixit
	 */
	public int getSeqOfAlreadyInsertedScore(String scoreFilePath,String scorePath,String quizID,String userID){		
			int seq = -1;
			try{
	        String quizid,userid,score;
	        quizid=userid=score="";
	        Vector scoreList=new Vector();
	        File scoreFile=new File(scoreFilePath+"/"+scorePath);
	        if(scoreFile.exists()){
	        QuizMetaDataXmlReader quizQuestionMetaData=null;            	        
	        quizQuestionMetaData=new QuizMetaDataXmlReader(scoreFilePath+"/"+scorePath);
	        scoreList = quizQuestionMetaData.attemptedQuiz();
	        if(scoreList!=null && scoreList.size()!=0){
	        	for(int i=0;i<scoreList.size();i++){
	        		quizid=((QuizFileEntry) scoreList.elementAt(i)).getQuizID();
					userid=((QuizFileEntry) scoreList.elementAt(i)).getUserID();
					if(quizid.equalsIgnoreCase(quizID) && userid.equalsIgnoreCase(userID)){
						seq = i;
						break;
					}
	        	}        	
	        }
	        }
	        ErrorDumpUtil.ErrorLog("value of score sequence "+seq);	       
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getFinalAnswer !! "+e);
		}
		 return seq;
	}
	
	/**
	 * This method gets the list of all the quizzes which are announced and announced time is over
	 * @return vector
	 * @exception generic Exception
	 * @author Nupur Dixit
	 */
	public Vector listAnnouncedAndExpiredQuiz(){	
		Vector vt = new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizid,quizName,creationDate,examDate,expiryDate,startTime,endTime,modifiedDate;
				String maxTime,maxMarks,maxQuestions;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");						
					quizName=ats.getValue("QuizName");
					maxMarks=ats.getValue("MaxMarks");
					maxTime=ats.getValue("MaxTime");
					maxQuestions=ats.getValue("NumberQuestion");
					creationDate=ats.getValue("CreationDate");
					examDate=ats.getValue("ExamDate");
					expiryDate=ats.getValue("ExpiryDate");
					startTime=ats.getValue("StartTime");
					endTime=ats.getValue("EndTime");
					modifiedDate=ats.getValue("ModifiedDate");						
					if(examDate==null){}
					else{						
						Calendar current = Calendar.getInstance();
						Calendar examDt = Calendar.getInstance();
						examDt.clear();
						Calendar expiryDt = Calendar.getInstance();
						expiryDt.clear();
						String [] exDt = examDate.split("-");
						String [] expDt = expiryDate.split("-");
						String [] stTime = startTime.split(":");
						String [] enTime = endTime.split(":");
						examDt.set(Integer.parseInt(exDt[0]),(Integer.parseInt(exDt[1])-1), Integer.parseInt(exDt[2]),Integer.parseInt(stTime[0]),Integer.parseInt(stTime[1]));
						expiryDt.set(Integer.parseInt(expDt[0]),(Integer.parseInt(expDt[1])-1), Integer.parseInt(expDt[2]),Integer.parseInt(enTime[0]),Integer.parseInt(enTime[1]));
						if(current.compareTo(examDt)==1 || current.compareTo(examDt)==0){							
							ErrorDumpUtil.ErrorLog("exam date is announced before the current date !");
							if(current.compareTo(expiryDt)==1 || current.compareTo(expiryDt)==0){
								fileEntry.setQuizID(quizid);
								fileEntry.setQuizName(quizName);
								fileEntry.setMaxMarks(maxMarks);
								fileEntry.setMaxTime(maxTime);
								fileEntry.setnoQuestion(maxQuestions);
								vt.add(fileEntry);								
								ErrorDumpUtil.ErrorLog("list of expired quizzes !");
							}							
						}						
					}				
				}	
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestionID !! "+e);
			//data.setMessage("See Exception Log !!");
		}
		return null;
	}
	/**
	 * This method gets the list of all the quizzes which are announced and announced time is over
	 * @return vector
	 * @exception generic Exception
	 * @author Nupur Dixit
	 */
	public Vector listActiveAndCurrentlyNotRunningQuiz(){	
		Vector vt = new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizid,quizName,creationDate,examDate,expiryDate,startTime,endTime,modifiedDate;
				String maxTime,maxMarks,maxQuestions,quizStatus,quizMode,allowPractice;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");						
					quizName=ats.getValue("QuizName");
					maxMarks=ats.getValue("MaxMarks");
					maxTime=ats.getValue("MaxTime");
					maxQuestions=ats.getValue("NumberQuestion");
					creationDate=ats.getValue("CreationDate");
					examDate=ats.getValue("ExamDate");
					expiryDate=ats.getValue("ExpiryDate");
					startTime=ats.getValue("StartTime");
					endTime=ats.getValue("EndTime");
					modifiedDate=ats.getValue("ModifiedDate");
					quizStatus=ats.getValue("status");
					quizMode = ats.getValue("QuizMode");
					allowPractice = ats.getValue("AllowPractice");
					if(examDate==null){
						if(quizStatus.equalsIgnoreCase("act")){
							fileEntry.setQuizID(quizid);
	                        fileEntry.setQuizName(quizName);
	                        fileEntry.setMaxMarks(maxMarks);
	                        fileEntry.setMaxTime(maxTime);
	                        fileEntry.setQuizStatus(quizStatus);
	                        fileEntry.setCreationDate(creationDate);
	                        fileEntry.setnoQuestion(maxQuestions);
//	                        fileEntry.setQuizFileName(quizFileName);
	                        fileEntry.setQuizMode(quizMode);
	                        fileEntry.setModifiedDate(modifiedDate);
	                        fileEntry.setExamDate(examDate);
	                        fileEntry.setStartTime(startTime);
	                        fileEntry.setExpiryDate(expiryDate);
	                        fileEntry.setEndTime(endTime);
	                        fileEntry.setAllowPractice(allowPractice);
	                        vt.add(fileEntry);	
						}
					}
					else{
						if(quizStatus.equalsIgnoreCase("act")){
							Calendar current = Calendar.getInstance();
							Calendar examDt = Calendar.getInstance();
							examDt.clear();
							Calendar expiryDt = Calendar.getInstance();
							expiryDt.clear();
							String [] exDt = examDate.split("-");
							String [] expDt = expiryDate.split("-");
							String [] stTime = startTime.split(":");
							String [] enTime = endTime.split(":");
//							ErrorDumpUtil.ErrorLog("\n real time and 10 minute before time "+Integer.parseInt(stTime[1])+" : "+(Integer.parseInt(stTime[1])-10));
							//exam time is set 10 minute before to block the quiz for any modification
							examDt.set(Integer.parseInt(exDt[0]),(Integer.parseInt(exDt[1])-1), Integer.parseInt(exDt[2]),Integer.parseInt(stTime[0]),(Integer.parseInt(stTime[1])-10));
							expiryDt.set(Integer.parseInt(expDt[0]),(Integer.parseInt(expDt[1])-1), Integer.parseInt(expDt[2]),Integer.parseInt(enTime[0]),Integer.parseInt(enTime[1]));
							if(current.compareTo(examDt)==1 || current.compareTo(examDt)==0){							
								ErrorDumpUtil.ErrorLog("exam date is announced before the current date !");
								if(current.compareTo(expiryDt)==1 || current.compareTo(expiryDt)==0){
									fileEntry.setQuizID(quizid);
									fileEntry.setQuizName(quizName);
									fileEntry.setMaxMarks(maxMarks);
									fileEntry.setMaxTime(maxTime);
									fileEntry.setQuizStatus(quizStatus);
									fileEntry.setCreationDate(creationDate);
									fileEntry.setnoQuestion(maxQuestions);
									//		                        fileEntry.setQuizFileName(quizFileName);
									fileEntry.setQuizMode(quizMode);
									fileEntry.setModifiedDate(modifiedDate);
									fileEntry.setExamDate(examDate);
									fileEntry.setStartTime(startTime);
									fileEntry.setExpiryDate(expiryDate);
									fileEntry.setEndTime(endTime);
									fileEntry.setAllowPractice(allowPractice);
									vt.add(fileEntry);	
								}							
							}
							else{
								fileEntry.setQuizID(quizid);
								fileEntry.setQuizName(quizName);
								fileEntry.setMaxMarks(maxMarks);
								fileEntry.setMaxTime(maxTime);
								fileEntry.setQuizStatus(quizStatus);
								fileEntry.setCreationDate(creationDate);
								fileEntry.setnoQuestion(maxQuestions);
								//	                        fileEntry.setQuizFileName(quizFileName);
								fileEntry.setQuizMode(quizMode);
								fileEntry.setModifiedDate(modifiedDate);
								fileEntry.setExamDate(examDate);
								fileEntry.setStartTime(startTime);
								fileEntry.setExpiryDate(expiryDate);
								fileEntry.setEndTime(endTime);
								fileEntry.setAllowPractice(allowPractice);
								vt.add(fileEntry);	
							}
						}//end status if
					}//end else				
				}//end for	
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestionID !! "+e);
			//data.setMessage("See Exception Log !!");
		}
		return null;
	}
	
	/**
	 * This method gets the list of all the quizzes which are announced and announced time is over
	 * @return vector
	 * @exception generic Exception
	 * @author Nupur Dixit
	 */
	public Vector listFutureQuiz(){	
		Vector vt = new Vector();
		try{
			XmlData files[]=xr.getElements("Quiz");
			if(files!=null){
				Attributes ats;
				String quizid,quizName,creationDate,examDate,expiryDate,startTime,endTime,modifiedDate;
				String maxTime,maxMarks,maxQuestions,quizStatus,quizMode,allowPractice;
				for(int j=0;j<files.length;j++){
					QuizFileEntry fileEntry=new QuizFileEntry();
					ats=files[j].getAttributes();
					quizid=ats.getValue("QuizID");						
					quizName=ats.getValue("QuizName");
					maxMarks=ats.getValue("MaxMarks");
					maxTime=ats.getValue("MaxTime");
					maxQuestions=ats.getValue("NumberQuestion");
					creationDate=ats.getValue("CreationDate");
					examDate=ats.getValue("ExamDate");
					expiryDate=ats.getValue("ExpiryDate");
					startTime=ats.getValue("StartTime");
					endTime=ats.getValue("EndTime");
					modifiedDate=ats.getValue("ModifiedDate");
					quizStatus=ats.getValue("status");
					quizMode = ats.getValue("QuizMode");
					allowPractice = ats.getValue("AllowPractice");
					if(examDate==null){
						
					}
					else{
//						if(quizStatus.equalsIgnoreCase("act")){
							Calendar current = Calendar.getInstance();
							Calendar examDt = Calendar.getInstance();
							examDt.clear();
							Calendar expiryDt = Calendar.getInstance();
							expiryDt.clear();
							String [] exDt = examDate.split("-");
							String [] expDt = expiryDate.split("-");
							String [] stTime = startTime.split(":");
							String [] enTime = endTime.split(":");
							examDt.set(Integer.parseInt(exDt[0]),(Integer.parseInt(exDt[1])-1), Integer.parseInt(exDt[2]),Integer.parseInt(stTime[0]),Integer.parseInt(stTime[1]));
							expiryDt.set(Integer.parseInt(expDt[0]),(Integer.parseInt(expDt[1])-1), Integer.parseInt(expDt[2]),Integer.parseInt(enTime[0]),Integer.parseInt(enTime[1]));
							if(current.compareTo(examDt)==1 || current.compareTo(examDt)==0){							
								ErrorDumpUtil.ErrorLog("exam date is announced before the current date !");
								if(current.compareTo(expiryDt)==1 || current.compareTo(expiryDt)==0){									
								}							
							}
							else{
								fileEntry.setQuizID(quizid);
								fileEntry.setQuizName(quizName);
								fileEntry.setMaxMarks(maxMarks);
								fileEntry.setMaxTime(maxTime);
								fileEntry.setQuizStatus(quizStatus);
								fileEntry.setCreationDate(creationDate);
								fileEntry.setnoQuestion(maxQuestions);
								//	                        fileEntry.setQuizFileName(quizFileName);
								fileEntry.setQuizMode(quizMode);
								fileEntry.setModifiedDate(modifiedDate);
								fileEntry.setExamDate(examDate);
								fileEntry.setStartTime(startTime);
								fileEntry.setExpiryDate(expiryDate);
								fileEntry.setEndTime(endTime);
								fileEntry.setAllowPractice(allowPractice);
								vt.add(fileEntry);	
							}
//						}//end status if
					}//end else				
				}//end for	
				return vt;
			}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("Error in Util[QuizMetaDataXmlReader] method:getInsertedQuizQuestionID !! "+e);
			//data.setMessage("See Exception Log !!");
		}
		return null;
	}
}