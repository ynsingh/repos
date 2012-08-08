package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)OLES_ReEvaluation.java	
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
import java.util.Vector;
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
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;

/**
 *   This class contains code for displaying list of all records for ReEvaluation
 *   @author<a href="dev.singha93@gmail.com">Devendra Singhal</a>
 */

public class OLES_ReEvaluation extends SecureScreen{
	public void doBuildTemplate( RunData data,Context context ){
		ErrorDumpUtil.ErrorLog("inside OLES_ReEvaluation.java file!!"); 
			ParameterParser pp=data.getParameters();
			String langfile=data.getUser().getTemp("LangFile").toString();
		try {			
			User user=data.getUser();
			String uname=user.getName();
			String courseid=(String)user.getTemp("course_id");
			String courseName=(String)user.getTemp("course_name");
			String uid=Integer.toString(UserUtil.getUID(uname));				
			String quizID=pp.getString("quizID","");
			context.put("quizID",quizID);
			String quizName=pp.getString("quizName","");
			context.put("quizName",quizName);
			String count = pp.getString("count","6");			
			context.put("tdcolor",count);
			String studentLoginName=pp.getString("studentLoginName","0");
			context.put("studentLoginName",studentLoginName);	
			String studentID=Integer.toString(UserUtil.getUID(studentLoginName));
			String fullName = UserUtil.getFullName(Integer.valueOf(studentID));
			context.put("fullName",fullName);
			context.put("studentID",studentID);
			ErrorDumpUtil.ErrorLog("courseID, quizID, quizName, count, studentLoginName, studentID,coursename "+courseid+" : "+quizID+" : "+quizName+" : "+count+" : "+studentLoginName+" : "+studentID+" : "+courseName);	
			Vector collectScore=new Vector();
			List collectStudentLoginName=new ArrayList();
			String quizScorePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String scoreFilePath = "score.xml";						
			File scoreFile= new File(quizScorePath+"/"+scoreFilePath);
			boolean flag=false;
			if(!scoreFile.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noreevaluation",langfile));
				return;
			}
			else{
				QuizMetaDataXmlReader quizmetadata=new QuizMetaDataXmlReader(quizScorePath+"/"+scoreFilePath);
				collectScore = quizmetadata.attemptedQuiz();
				if(collectScore==null || collectScore.size()==0){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noreevaluation",langfile));
					//return;
				}
				else{
					context.put("collectScore",collectScore);
					ErrorDumpUtil.ErrorLog("inside OLES_ReEvaluation.java file in else part !!"+collectScore.size());
					for(int i=0;i<collectScore.size();i++){
						String evaluate=((QuizFileEntry)collectScore.elementAt(i)).getEvaluate();
						if(evaluate==null || evaluate.equals("complete") || evaluate.equals("partial")){
							//flag=false;
						}
						else if(evaluate.equals("ReEvaluate")){
							flag=true;
							break;
						}
					}
					context.put("flag",flag);
					if(data.getMessage()==null){
						if(!flag){
							data.setMessage(MultilingualUtil.ConvertedString("brih_noreevaluation",langfile));
						}
					}
				}
			}
			/**
                         *Time calculaion for how long user use this page.
                         */
			String Role = (String)user.getTemp("role");
			int userid=UserUtil.getUID(user.getName());
                        if((Role.equals("student")) || (Role.equals("instructor")))
                        {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				int eid=0;
				MailNotificationThread.getController().CourseTimeSystem(userid,eid);
                        }

		}
		catch(Exception exc){
			ErrorDumpUtil.ErrorLog("The exception in OLES_ReEvaluation.java file!!"+exc); 
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+exc,langfile));
		}	
	}
}


































