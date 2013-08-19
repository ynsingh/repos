package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Oles_Gen.java	
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
 * 
 *  
 *  Contributors: Members of MHRD Project DEI Agra 
 * 
 */

//Jdk
import java.io.File;
import java.util.Vector;
//Turbine
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

/**
 * This class manage Quiz Generation process 
 * @author <a href="mailto:noopur.here@gmail.com">Nupur Dixit</a>
 */

public class Oles_Gen extends SecureScreen{
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
			String uname=user.getName();
			String crsId=(String)data.getUser().getTemp("course_id");
			String mode =pp.getString("mode","");
			String quizID=pp.getString("quizID","");
			String quizName=pp.getString("quizName","");
			String maxMarks=pp.getString("maxMarks","");
			String maxTime=pp.getString("maxTime","");
			String noQuestion=pp.getString("noQuestion","");
			String status=pp.getString("status","");
			String type =pp.getString("type","");
			String checkstatus=pp.getString("checkstatus","");
			String allow = pp.getString("allow","");
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(uname);
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(uid,eid);
                         }

			context.put("tdcolor",pp.getString("count",""));
			context.put("course",(String)user.getTemp("course_name"));						
			context.put("mode",mode);
			context.put("quizName",quizName);			
			context.put("quizID",quizID);			
			context.put("maxMarks",maxMarks);			
			context.put("maxTime",maxTime);			
			context.put("noQuestion",noQuestion);			
			context.put("status",status);			
			context.put("type",type);
			context.put("allow",allow);
			
			if(mode.equals("QuizDetail")){
				String qname=pp.getString("qname","");
				String quizMode=pp.getString("quizMode","");
				
				context.put("qid",qname);
				context.put("quizMode",quizMode);
				
				String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/");
				QuizMetaDataXmlReader quizmetadata=null;
				Vector allQuiz=new Vector();
				String quizPath="/Quiz.xml";
				File f=new File(filePath+"/"+quizPath);
				
				if(f.exists()){
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
					allQuiz=quizmetadata.getQuiz_Detail(qname);
				}
				if(allQuiz==null)
					return;
				if(allQuiz.size()!=0){
					checkstatus="NoBlank";
					context.put("allQuiz",allQuiz);	                   	
				}
				else{
					checkstatus="blank";
				}
				context.put("checkstatus",checkstatus);
			}			
			if(!mode.equals("QuizDetail")) {
				String filePath=data.getServletContext().getRealPath("/Courses"+"/"+crsId+"/Exam/");
				QuizMetaDataXmlReader quizmetadata=null;
				Vector allQuiz=new Vector();
				String maxQuizID = "";
				String quizPath="/Quiz.xml";
				File f=new File(filePath+"/"+quizPath);

				if(f.exists()){
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
					maxQuizID=quizmetadata.getMaxQuizID()+"_"+uname;
					context.put("qid",maxQuizID);
				}
				else{
					context.put("qid","Quiz1_"+uname);
				}
			}			
		}
		catch(Exception ex){
			ErrorDumpUtil.ErrorLog("The exception in Oles_Gen screen::"+ex);
	    		data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+ex,lang));
		}
	}
}
