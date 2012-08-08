package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)AnnounceExam_Manage.java	
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
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;

import org.iitk.brihaspati.modules.utils.UserUtil;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
/**
* This class displays the list of quizzes to announce/update that quiz
* @author <a href="mailto:aayushi.sr@gmail.com">Aayushi Sr</a>
*/

public class AnnounceExam_Manage extends SecureScreen{
	public void doBuildTemplate(RunData data,Context context){
	String LangFile=data.getUser().getTemp("LangFile").toString();
	/**
        *Retrieve the Parameters by using the Parameter Parser
        *Get the UserName and put it in the context
        *for template use
        */
        ParameterParser pp=data.getParameters();
        try{
        	User user=data.getUser();
        	String uname=user.getName();
//        	String count = pp.getString("count","3");
//        	ErrorDumpUtil.ErrorLog("The count value :"+count);
//        	context.put("tdcolor",pp.getString("count","3"));
        	context.put("tdcolor","3");
        	context.put("course",(String)user.getTemp("course_name"));
        	String courseid=(String)user.getTemp("course_id");        	
        	String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
	        String quizPath="Quiz.xml";
		String Role = (String)user.getTemp("role");
            	/**
                 *Time calculaion for how long user use this page.
                 */
                 int uid=UserUtil.getUID(user.getName());
                 if((Role.equals("student")) || (Role.equals("instructor")))
                 {
                         //CourseTimeUtil.getCalculation(uid);
                         //ModuleTimeUtil.getModuleCalculation(uid);
			 int eid=0;
			 MailNotificationThread.getController().CourseTimeSystem(uid,eid);
                  }

            File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector finalQuizList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			if(file.exists()){
				context.put("isFile","exist");
				ErrorDumpUtil.ErrorLog("inside file exist:");
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				quizList=quizmetadata.listActiveAndCurrentlyNotRunningQuiz(filePath+"/"+quizPath,uname);
				ErrorDumpUtil.ErrorLog("after active n currently not running quizzes:"+quizList);
				if(quizList!=null && quizList.size()!=0){
					for(int i=0;i<quizList.size();i++){
						String quizName =((QuizFileEntry) quizList.elementAt(i)).getQuizName();
						String quizMode = ((QuizFileEntry) quizList.elementAt(i)).getQuizMode();
						String allowPractice =((QuizFileEntry) quizList.elementAt(i)).getAllowPractice();
						//=============modification on 31 march-reason--> mode was not passing
						String startDate = ((QuizFileEntry) quizList.elementAt(i)).getExamDate();
						ErrorDumpUtil.ErrorLog("The start date :"+startDate);
						//=================================
//						if(quizMode.equalsIgnoreCase("random")){
							if(allowPractice.equalsIgnoreCase("no")){
								finalQuizList.add(quizList.get(i));				
							}
//						}						
					}	
//					if(quizList.size()!=0){
						context.put("quizList",finalQuizList);	              
//					}
				}
			}
			else
				context.put("isFile","");
        }catch(Exception e) {
        	ErrorDumpUtil.ErrorLog("The exception in AnnounceExam_Manage screen::"+e);
        	data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));	
        }
    }
}
