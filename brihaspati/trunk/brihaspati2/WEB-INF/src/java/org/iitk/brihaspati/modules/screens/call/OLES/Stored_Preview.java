package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Stored_Preview.java
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
import java.util.*;
import java.io.File;
//Turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
//brihaspati
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
/**
 * This class is used to view the last stored preview. 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Stored_Preview extends  SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		/**
	        *Retrieve the Parameters by using the Parameter Parser
	        *Get the UserName and put it in the context
	        *for template use
	        */
	        ParameterParser pp=data.getParameters();
		String lang=data.getUser().getTemp("LangFile").toString();
		try{
			User user=data.getUser();

			String courseid=(String)user.getTemp("course_id");
			String courseName=(String)user.getTemp("course_name");
			String CoursePath=TurbineServlet.getRealPath("/Courses");
			String count = pp.getString("count","");
			String username=data.getUser().getName();
			String quizName = pp.getString("quizName","");
			String exist = "disabled";		
			String quizDetail = pp.getString("quizDetail","");			
			String[] temp = quizDetail.split(",");
			String quizID = temp[0];			
			String maxMarks = temp[1];			
			String noQuestions = temp[2];	
			String maxTime = temp[3];
			
			String filePath=data.getServletContext().getRealPath("/Courses"+"/"+courseid+"/Exam/"+quizID+"/");
			String quizPath=quizID+"_QuestionSetting.xml";
			
			context.put("course",courseName);
			context.put("quizDetail",quizDetail);
			context.put("quizID",quizID);
			context.put("quizName",quizName);
			context.put("maxMarks",maxMarks);
			context.put("noQuestions",noQuestions);
			context.put("maxTime",maxTime);
			context.put("quizMode","random");
			context.put("tdcolor",count);	
			
			QuizMetaDataXmlReader quizmetadata=null;
			quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);	
			HashMap hm = new HashMap();
			hm = quizmetadata.getQuizQuestionNoMarks(quizmetadata,quizID);
			int mark =((Integer)hm.get("marks"));
			int enteredQuestions = ((Integer)hm.get("noQuestion")); 
			
			context.put("enteredQuestion",enteredQuestions);
			context.put("marks",mark);               			
		}
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in Stored_Preview Screen::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,lang));	
		}
	}
}			
