package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Quiz_Detail.java	
 *
 *	Copyright (c) 2010 DEI Agra.
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
 *  Contributors: Members of MHRD Project, DEI Agra
 * 
 */
//Jdk
import java.util.*;
import java.io.File;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//brihaspati
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;

/**
 *   This class contains code for displaying list of all active quiz 
 *   (active quiz=quizes which are already created)
 *   @author<a href="noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Quiz_Detail extends SecureScreen{
	public void doBuildTemplate( RunData data, Context context ){
		/**
	        *Retrieve the Parameters by using the Parameter Parser
	        *Get the UserName and put it in the context
	        *for template use
	        */
	        ParameterParser pp=data.getParameters();
		String LangFile=data.getUser().getTemp("LangFile").toString();
		try {			
            User user=data.getUser();
            String uname=user.getName();
            int userid=UserUtil.getUID(uname);
            String uid=Integer.toString(userid);
            String courseid=(String)user.getTemp("course_id");
            String mode =pp.getString("mode","");
            String quizMode=pp.getString("quizMode","");
            String checkstatus=pp.getString("checkstatus","");
            
            context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));
			context.put("mode",mode);
			context.put("quizMode",quizMode);
			ErrorDumpUtil.ErrorLog("count in java "+pp.getString("count",""));
			
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/");
			QuizMetaDataXmlReader quizmetadata=null;
            Vector allQuiz=new Vector();
            HashMap visibilityFlag = new HashMap(); 
            String quizPath="Quiz.xml";
			File f=new File(filePath+"/"+quizPath);
			
			if(f.exists()){
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
				allQuiz=quizmetadata.listActiveAndCurrentlyNotRunningQuiz(filePath+"/"+quizPath,uname);
//				allQuiz=quizmetadata.getStatusQuiz_Detail("ACT");			
			}
			if(allQuiz==null)
                return;
            if(allQuiz.size()!=0){
            	checkstatus="NoBlank";
                context.put("allQuiz",allQuiz);
                for(int i=0;i<allQuiz.size();i++){
                	String quizid =((QuizFileEntry) allQuiz.elementAt(i)).getQuizID();
                	String maxmarks =((QuizFileEntry) allQuiz.elementAt(i)).getMaxMarks();
                	String maxquestions =((QuizFileEntry) allQuiz.elementAt(i)).getnoQuestion();
                	String allowPractice = ((QuizFileEntry) allQuiz.elementAt(i)).getAllowPractice();
                	String newFilePath=filePath+"/"+quizid+"/";
                	String questionsPath=quizid+"_QuestionSetting.xml";        	                    	       
                	
                	File newFile=new File(newFilePath+questionsPath);
                	if(!newFile.exists()){
                		data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_setting",LangFile));
                	}
                	else{
                		ErrorDumpUtil.ErrorLog("inside else !!");
                		QuizMetaDataXmlReader questionReader = new QuizMetaDataXmlReader(newFilePath+"/"+questionsPath);
                		HashMap hm = new HashMap();
                		hm = questionReader.getQuizQuestionNoMarks(questionReader,quizid);
                		int mark =((Integer)hm.get("marks"));
                		int enteredQuestions = ((Integer)hm.get("noQuestion")); 
                		if(mark==(Integer.parseInt(maxmarks))||enteredQuestions==(Integer.parseInt(maxquestions))){
                			ErrorDumpUtil.ErrorLog("inside if !!");
                			visibilityFlag.put(quizid, "true");			        				       
			        	}
                		else{
                			ErrorDumpUtil.ErrorLog("inside false ");
                			visibilityFlag.put(quizid, "false");
			        	}		   		
					}    
				}
				context.put("visibilityFlag",visibilityFlag);
            }
			else{
				checkstatus="blank";
            }
			context.put("checkstatus",checkstatus);
		}		        
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in detail quiz file!!"+ex);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,LangFile));
        }
	}
}
