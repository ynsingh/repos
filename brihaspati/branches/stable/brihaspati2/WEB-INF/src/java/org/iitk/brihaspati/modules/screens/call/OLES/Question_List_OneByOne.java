package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Question_List_OneByOne.java	
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
 *  Contributors: Members of MHRD, DEI Agra. 
 * 
 */
//Jdk
import java.io.File;
import java.util.Vector;
//Turbine
import org.apache.velocity.context.Context;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
* This class is used to display question list for one by one
* @author <a href="mailto:aayushi.sr@gmail.com">Aayushi</a>
*/

public class Question_List_OneByOne extends SecureScreen{
	public void doBuildTemplate(RunData data,Context context) {
		/**
        *Retrieve the Parameters by using the Parameter Parser
        *Get the UserName and put it in the context
        *for template use
        */
        ParameterParser pp=data.getParameters();
	String lang=data.getUser().getTemp("LangFile").toString();
        try{
        	User user=data.getUser();
        	
        	String count = pp.getString("count","");
        	String mode =pp.getString("mode"," ");
        	String quizMode =pp.getString("quizMode"," "); 
        	String qName = pp.getString("qName","");
        	String quizName = pp.getString("quizName","");
        	context.put("quizMode",quizMode);
        	context.put("mode",mode);
        	context.put("course",(String)user.getTemp("course_name"));
        	context.put("count",count);
        	context.put("quizName",quizName);
			context.put("qName",qName);
			
			if(mode.equalsIgnoreCase("update")){
				String quizDetail = pp.getString("quizDetail","");
				String quizSetting = pp.getString("quizSetting","");
				context.put("quizSetting",quizSetting);
				context.put("quizDetail",quizDetail);
			
				String[] temp = quizDetail.split(",");
				String quizID=temp[0];
				context.put("quizID",quizID);
				String maxMarks=temp[1];
				context.put("maxMarks",maxMarks);
				String noQuestions=temp[2];
				context.put("noQuestions",noQuestions);								
			}
			else{
				String[] temp = quizName.split(",");
				String quizID=temp[0];
				context.put("quizID",quizID);
				String maxMarks=temp[1];
				context.put("maxMarks",maxMarks);
				String noQuestions=temp[2];
				context.put("noQuestions",noQuestions);								
			}
		/**
                  *Time calculaion for how long user use this page.
                  */
		 String Role = (String)user.getTemp("role");
		 int uid=UserUtil.getUID(user.getName());
                 if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                 {
			  int eid=0;
			  ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                 }


        }
	    catch(Exception e){
	    	ErrorDumpUtil.ErrorLog("The exception in Question_List_OneByOne screen::"+e);
	    	data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,lang));
	    }
	}
}
