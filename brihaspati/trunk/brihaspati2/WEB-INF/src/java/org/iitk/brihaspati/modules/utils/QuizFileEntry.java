package org.iitk.brihaspati.modules.utils;
/*
 * @(#)QuizFileEntry.java
 *
 *  Copyright (c) 2010-2011,2012,2013 DEI, Agra, IITK .
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
 *  Contributors: Members of MHRD, DEI, Agra, IITK
 *
 */
/**
 * This class set some value and get in templates from XML file 
 * @author: <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 * @author: Aayushi
 * @author: <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author: <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>03jan2013
 */
public class QuizFileEntry implements Comparable<QuizFileEntry> {
			
	private char replacingChar='$'; //default value;	
	
//...................................Quiz(DEI Agra)
	
	private String quizID;
	private String quizName;
	private String maxMarks;
	private String maxTime;
	private String quizFileName;
	private String creationDate;
	private String noQuestion;
	private String quizStatus;
	private String modifiedDate;
	private String quizMode;
	private String topic;
	private String questionType;
	private String questionLevel;
	private String marksPerQuestion;
	private String status;
	private String questionID;

    	private String question;
    	private String answer;
    	private String option1;
    	private String option2;
    	private String option3;
    	private String option4;
    	private String questionNumber;
    	private String fileName;
    	private String id;
    	private String examDate;
    	private String expiryDate;
    	private String startTime;
    	private String endTime;
    	private String allowPractice;
    	private String awardedMarks;
    	private String userID;
    	private String score;
    	private String studentAnswer;
    	private String instructorAnswer;
    	private String usedTime;
    	private String resDate;
    	private String evaluate;
    	private String studentLoginName;
    	private String securityID;
    	private String studentID;
    	private String IPAddress;
    	private String Attributeno;

//-----------------------------OLES
	
//	public String getName()
//	{
//		int index=name.lastIndexOf('/'); // truncate the relative path from the this.name
//		return name.substring(index+1);  // to retrieve the file name.
//	}	
//	
//
//	public void setReplacingChar(char replacingChar)
//	{
//		this.replacingChar=replacingChar;
//	}
//
//	public String getCheckboxName()
//	{
//		String temp=name.replace('.', replacingChar); 
//		return temp;
//	}	
//---------------------------------Quiz(DEI Agra)-------------------------------
	/*
      	* compareTo method is overrided to make quizFileEntry objects comparable
      	*/
    	public int compareTo(QuizFileEntry o1){
    		//ErrorDumpUtil.ErrorLog("\n questionbank's question"+o1.getQuestion());
    		//ErrorDumpUtil.ErrorLog("\n tree set question"+this.getQuestion());
    		if (o1.getQuestion().equals(this.getQuestion())&& o1.getAnswer().equals(this.getAnswer())){ // Are they exactly the same instance?    	
    			//ErrorDumpUtil.ErrorLog("inside exactly same");
    			return 0;
    		}
		if (o1 == null){ // Is the object being compared null?
			//ErrorDumpUtil.ErrorLog("inside null situation");
			return 1;
		}

		if (!(o1 instanceof QuizFileEntry)){ // Is the object being compared also a QuizFileEntry object?
			//ErrorDumpUtil.ErrorLog("inside wrong object type");
			return 1;
		}
		//ErrorDumpUtil.ErrorLog("after every condition check");
    		return 1;
    	}
    
    
	public boolean equals(QuizFileEntry o1)
    	{
    		//ErrorDumpUtil.ErrorLog("o1 's question"+o1.getQuestion());
    		//ErrorDumpUtil.ErrorLog("this 's question"+this.getQuestion());
    		if (o1.getQuestion().equals(this.getQuestion())&& o1.getAnswer().equals(this.getAnswer())){ // Are they exactly the same instance?
    	 
    			//ErrorDumpUtil.ErrorLog("inside exactly same");
    			return true;
    		}
		if (o1 == null){ // Is the object being compared null?
			//ErrorDumpUtil.ErrorLog("inside null situation");
			return false;
		}

		if (!(o1 instanceof QuizFileEntry)){ // Is the object being compared also a Person?
			//ErrorDumpUtil.ErrorLog("inside wrong object type");
			return false;
		}
		//ErrorDumpUtil.ErrorLog("after every condition check");
    		return false;
    	}
    
	public void setQuizID(String quizID){
		this.quizID = quizID;
	}
	
	public String getQuizID(){
		return quizID;
	}
	public void setQuizName(String quizName){
		this.quizName = quizName;
	}
	public String getQuizName(){
		return quizName;
	}
	public void setMaxMarks(String maxMarks){
		this.maxMarks = maxMarks;
	}
	public String getMaxMarks(){
		return maxMarks;
	}
	public void setMaxTime(String maxTime){
		this.maxTime = maxTime;
	}
	public String getMaxTime(){
		return maxTime;
	}
	public void setnoQuestion(String noQuestion){
		this.noQuestion = noQuestion;
	}
	public String getnoQuestion(){
		return noQuestion;
	}
	public void setCreationDate(String creationDate){
		this.creationDate = creationDate;
	}
	public String getCreationDate(){
		return creationDate;
	}
	public void setQuizFileName(String quizFileName){
		this.quizFileName = quizFileName;
	}
	public String getQuizFileName(){
		return quizFileName;
	}
	public void setQuizStatus(String quizStatus){
		this.quizStatus = quizStatus;
	}
	public String getQuizStatus(){
		return quizStatus;
	}
	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedDate(){
		return modifiedDate;
	}
	public void setQuizMode(String quizMode){
		this.quizMode = quizMode;
	}
	public String getQuizMode(){
		return quizMode;
	}
	public void setTopic(String topic)
	{
		this.topic=topic;
	}
	public String getTopic()
	{
//		int index=topic.lastIndexOf('/'); // truncate the relative path from the this.name
//		return topic.substring(index+1);  // to retrieve the topic name.
		return topic;
	}
	public void setQuestionType(String questionType)
	{
		this.questionType=questionType;
	}
	public String  getQuestionType()
	{
		return questionType;
	}
	public void setQuestionLevel(String questionLevel)
	{
		this.questionLevel=questionLevel;
	}
	public String  getQuestionLevel()
	{
		return questionLevel;
	}
	public void setMarksPerQuestion(String marksPerQuestion)
	{
		this.marksPerQuestion=marksPerQuestion;
	}
	public String  getMarksPerQuestion()
	{
		return marksPerQuestion;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public String getStatus()
	{
		return status;
	}
	 public void setQuestionID(String questionID)
     {
             this.questionID=questionID;
     }
     public String  getQuestionID()
     {
             return questionID;
     }
     public void setQuestion(String question)
     {
             this.question=question;
     }
     public String  getQuestion()
     {
             return question;
     }
     public void setAnswer(String answer)
     {
             this.answer=answer;
     }
     public String  getAnswer()
     {
             return answer;
     }
     public void setOption1(String option1)
     {
             this.option1=option1;
     }
     public String  getOption1()
     {
             return option1;
     }
     public void setOption2(String option2)
     {
             this.option2=option2;
     }
     public String  getOption2()
     {
             return option2;
     }
     public void setOption3(String option3)
     {
             this.option3=option3;
     }
     public String  getOption3()
     {
             return option3;
     }
     public void setOption4(String option4)
     {
             this.option4=option4;
     }
     public String  getOption4()
     {
             return option4;
     }
     public void setQuestionNumber(String questionNumber)
     {
             this.questionNumber=questionNumber;
     }
     public String  getQuestionNumber()
     {
             return questionNumber;
     }
     public void setFileName(String fileName)
     {
             this.fileName=fileName;
     }
     public String  getFileName()
     {
             return fileName;
     }
     public void setID(String id)
     {
             this.id=id;
     }
     public String  getID()
     {
             return id;
     }
     
     public void setExamDate(String examDate){
 		this.examDate = examDate;
 	}
 	public String getExamDate(){
 		return examDate;
 	}
 	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}
	public String getExpiryDate(){
		return expiryDate;
	}
	public void setResDate(String resDate){
 		this.resDate = resDate;
 	}
 	public String getResDate(){
 		return resDate;
 	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	public String getStartTime(){
		return startTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setAllowPractice(String allowPractice){
		this.allowPractice = allowPractice;
	}
	public String getAllowPractice(){
		return allowPractice;
	}
	public void setAwardedMarks(String awardedMarks){
		this.awardedMarks = awardedMarks;
	}
	public String getAwardedMarks(){
		return awardedMarks;
	}
	public void setUserID(String userID){
		this.userID = userID;
	}
	public String getUserID(){
		return userID;
	}
	public void setScore(String score){
		this.score = score;
	}
	public String getScore(){
		return score;
	}
	public void setStudentAnswer(String studentAnswer){
		this.studentAnswer = studentAnswer;
	}
	public String getStudentAnswer(){
		return studentAnswer;
	}
	public void setInstructorAnswer(String instructorAnswer){
		this.instructorAnswer = instructorAnswer;
	}
	public String getInstructorAnswer(){
		return instructorAnswer;
	}
	
	public void setUsedTime(String usedTime){
		this.usedTime = usedTime;
	}
	public String getUsedTime(){
		return usedTime;
	}
	public void setEvaluate(String evaluate){
		this.evaluate=evaluate;
	}
	public String getEvaluate(){
		return evaluate;
	}
	public void setStudentName(String name){
		this.studentLoginName=name;
	}
	public String getStudentName(){
		return studentLoginName;
	}
    public void setSecurityID(String securityID){
		this.securityID=securityID;
	}
	public String getSecurityID(){
		return securityID;
	}
	public void setStudentID(String studentID){
		this.studentID=studentID;
	}
	public String getStudentID(){
		return studentID;
	}
	public void setIP(String ip){
		this.IPAddress=ip;
	}
	public String getIP(){
		return IPAddress;
	}
	public void setnoofAttribute(String ats){
		this.Attributeno=ats;
	}
	public String getnoofAttribute(){
		return Attributeno;
	}
}
