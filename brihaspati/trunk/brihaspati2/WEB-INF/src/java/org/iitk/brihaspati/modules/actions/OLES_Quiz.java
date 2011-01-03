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
//            ErrorDumpUtil.ErrorLog("inside action"+crsId+" user name "+username);
            
			String quizName=pp.getString("quizName","");
			String quizID = pp.getString("quizID","");
			String maxMarks=pp.getString("maxMarks","");
			String maxTime=pp.getString("maxTime","");
			String noQuestion=pp.getString("numberQuestion","");
	//		ErrorDumpUtil.ErrorLog("quiz id "+quizID+" maxMarks "+maxMarks);				
			String filepath=CoursePath+"/"+crsId+"/Exam/";
	//		ErrorDumpUtil.ErrorLog("course path "+CoursePath+" file path "+filepath);
			File ff=new File(filepath);
            if(!ff.exists())
                ff.mkdirs();
            String filepath1=CoursePath+"/"+crsId+"/Exam/"+quizID;
            File ff1=new File(filepath1);
            if(!ff1.exists())
                ff1.mkdirs();
            String quizQuestionPath="/"+quizID+"_Questions.xml";
            File QuizQuestionxmls=new File(filepath1+"/"+quizQuestionPath);
            String QuizQuestionxmlsPath =  QuizQuestionxmls.getAbsolutePath();
           // ErrorDumpUtil.ErrorLog("quiz question path "+QuizQuestionxmlsPath);
            String quizPath="/Quiz.xml";				
			String Cur_date=ExpiryUtil.getCurrentDate("-");
			/*
			 * At the time of quiz setup, status is saved to "INA"
			 * when quiz is created (either randomly/one by one) status is changed to "ACT"
			 */
			String status="INA";
		//	ErrorDumpUtil.ErrorLog("after date "+ Cur_date);
			xmlwriteQuizlist(filepath,quizID,quizName,maxMarks,maxTime,noQuestion,status,Cur_date,quizPath,data,context,QuizQuestionxmlsPath);
		//	ErrorDumpUtil.ErrorLog("after write topic method ");    

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
		else
			data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));				
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
	 public void xmlwriteQuizlist(String filepath,String quizID,String quizName,String maxMarks,String maxTime,String noQuestion,String status,String CreationDate,String quizPath,RunData data,Context context, String QuizQuestionxmlsPath){
      	try{
			ParameterParser pp=data.getParameters();
            LangFile=data.getUser().getTemp("LangFile").toString();
            XmlWriter xmlWriter=null;
            boolean found=false;
            String Filename=QuizQuestionxmlsPath;
            File Quizxmls=new File(filepath+"/"+quizPath);
            File QuizQuestionxmls=new File(QuizQuestionxmlsPath);
           // ErrorDumpUtil.ErrorLog("full quiz xml path"+Quizxmls.getAbsolutePath());
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
             //   ErrorDumpUtil.ErrorLog("inside found false");               
                QuizMetaDataXmlWriter.appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,Filename,CreationDate);
               // ErrorDumpUtil.ErrorLog("after append question");  
                xmlWriter.writeXmlFile();
                if(!QuizQuestionxmls.exists()) {
                	QuizMetaDataXmlWriter.OLESRootOnly(QuizQuestionxmls.getAbsolutePath());
                }
               // ErrorDumpUtil.ErrorLog("after xml writing process");  
                data.setMessage(MultilingualUtil.ConvertedString("oles_msg",LangFile));
            }                
                
        }//try
        catch(Exception e){
           ErrorDumpUtil.ErrorLog("Error in Action[OLES_Quiz] method:xmlwriteQuizlist !!"+e);
           data.setMessage("See ExceptionLog !! " );
       }//catch
    }//method end
		
	 
	 /** This method is responsible to update the quiz setting in /courses/courseid/Exam/Quiz.xml
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
     //   ErrorDumpUtil.ErrorLog("inside action"+crsId+" user name "+username);
        
		String quizName=pp.getString("quizName","");
		String quizID = pp.getString("quizID","");
		String maxMarks=pp.getString("maxMarks","");
		String maxTime=pp.getString("maxTime","");
		String noQuestion=pp.getString("numberQuestion","");
		String status = "ACT";
		String Cur_date=ExpiryUtil.getCurrentDate("-");
//		ErrorDumpUtil.ErrorLog("quiz id "+quizID+" maxMarks "+maxMarks);
		
		int seq=-1;
		XmlWriter xmlWriter=null;
	    Vector collect=new Vector();
	    Vector str=new Vector();
	    String deltype = "quizDelete";
	    String filepath=CoursePath+"/"+crsId+"/Exam/";
	    String quizPath="/Quiz.xml";
	    String quizQuestionPath="/"+quizID+"_Questions.xml";
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
           xmlWriter=QuizMetaDataXmlWriter.Update_QuizList(filepath,quizPath,seq,quizID,maxMarks,maxTime,noQuestion);
//           xmlWriter.changeAttributes("Quiz",)
//           xmlWriter.removeElement("Quiz",seq);
//           xmlWriter.appendQues_Banklist(xmlWriter,quizID,quizName,maxMarks,maxTime,noQuestion,status,fileName,Cur_date);
           xmlWriter.writeXmlFile();
           data.setMessage(MultilingualUtil.ConvertedString("brih_quiz",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeenedit",LangFile));
       }
//       Vector str3=Arrangeseq(filepath,fulltopic+".xml",questiontype,deltype,data);
//       setTemplate(data,"call,OLES,View_QB.vm");
	}//try
	catch(Exception e){
		 ErrorDumpUtil.ErrorLog("The exception in OLES_Quiz Action - doUpdateQuiz "+e);
       	 data.setMessage("Error in action[OLES:doUpdateQuiz]"+e);
   	}
}

}	//end class	
	
	

