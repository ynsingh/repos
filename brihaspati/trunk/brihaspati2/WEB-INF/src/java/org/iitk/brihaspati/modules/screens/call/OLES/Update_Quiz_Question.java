package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Update_Quiz_Question.java
 *
 *  Copyright (c) 2010 MHRD, DEI Agra.
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
import java.io.File;
import java.util.Vector;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
//brihaspati
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;

/**
 * This class manages the update feature of quiz_questions 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Update_Quiz_Question extends  SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		/**
	        *Retrieve the Parameters by using the Parameter Parser
	        *Get the UserName and put it in the context
	        *for template use
	        */
	        ParameterParser pp=data.getParameters();
		try{
			User user=data.getUser();

			String LangFile=data.getUser().getTemp("LangFile").toString();
			String courseid=(String)user.getTemp("course_id");
			String courseName=(String)user.getTemp("course_name");
			String username=data.getUser().getName();
			String quizDetail = pp.getString("quizDetail","");			
			String[] temp = quizDetail.split(",");
			String quizID=temp[0];			
			String maxMarks=temp[1];			
			String noQuestions=temp[2];	
			String allowPractice = temp[3];
			String quizName=pp.getString("quizName","");			
			String mode=pp.getString("mode","");			
			String quizMode=pp.getString("quizMode","");			

			context.put("course",courseName);
			context.put("quizDetail",quizDetail);
			context.put("quizID",quizID);
			context.put("quizName",quizName);
			context.put("maxMarks",maxMarks);
			context.put("noQuestions",noQuestions);
			context.put("quizMode",quizMode);
			context.put("mode",mode);
			context.put("allowPractice",allowPractice);

			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String quizPath=quizID+"_QuestionSetting.xml";
			QuizMetaDataXmlReader quizmetadata=null;
			Vector allQuizSetting=new Vector();

			File f=new File(filePath+"/"+quizPath);
			
			if(!f.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquiz",LangFile));
				return;
			}
			quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
			allQuizSetting=quizmetadata.getQuizQuestionDetail();			
			if(allQuizSetting==null){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquestion_quiz",LangFile));
				return;
			}	                
			if(allQuizSetting.size()!=0){                   	
				context.put("allQuizSetting",allQuizSetting);
			}
		}
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in ViewQuizFile::"+e);
			data.setMessage("See ExceptionLog !! ");	
		}
	}
}