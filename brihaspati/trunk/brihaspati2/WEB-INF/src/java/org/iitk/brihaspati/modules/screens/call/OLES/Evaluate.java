package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)Evaluate.java
 *
 *  Copyright (c) 2010-2011,2013 MHRD, DEI Agra, IITK. 
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
 */


import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.Calendar;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.om.QuizPeer;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.QuizDetail;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;

import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/**
 *@author  <a href="noopur.here@gmail.com">Nupur Dixit</a> 
 */

public class Evaluate extends  SecureScreen{               
	public void doBuildTemplate(RunData data,Context context){
		ParameterParser pp=data.getParameters();
		String LangFile=(String)data.getUser().getTemp("LangFile");	
		try{		
			User user=data.getUser();
			String uname=user.getName();
			String uid=Integer.toString(UserUtil.getUID(uname));
			context.put("uid",uid);			
			String courseid=(String)user.getTemp("course_id");
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(uname);
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }
			
			String count = pp.getString("count","4");			
			context.put("tdcolor",count);
			String type = pp.getString("type","");
			context.put("type",type);
			String quizID=pp.getString("quizID","0");
			String studentLoginName=pp.getString("studentLoginName","0");
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizPath="/Quiz.xml";  
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector instructorQuizList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			if(!file.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToEvaluate",LangFile));
				return;
			}
			else{
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);				
				quizList=quizmetadata.listAnnouncedAndExpiredQuiz();
				if(quizList!=null && quizList.size()!=0){
					for(int i=0; i<quizList.size();i++){
						String quizid = ((QuizFileEntry) quizList.elementAt(i)).getQuizID();
						String userName = quizid.substring((quizid.lastIndexOf("_")+1),(quizid.length()));
						if((userName.trim()).equalsIgnoreCase(uname.trim())){
							instructorQuizList.add((QuizFileEntry)quizList.get(i));
						}
					}
					context.put("quizList",instructorQuizList);
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToEvaluate",LangFile));
					return;
				}
				context.put("quizID",quizID);
				context.put("selected","selected");	
			}
			// rohit------------------------

			String filePath2=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
                	String quizPath2="/Quiz.xml";       
                	String resDate = "";
                	File file2=new File(filePath2+"/"+quizPath2);
    			Vector quizDetail=new Vector();
    			QuizMetaDataXmlReader quizmetadata2=null;
    			if(file2.exists()){
    				quizmetadata2=new QuizMetaDataXmlReader(filePath2+"/"+quizPath2);				
    				quizDetail=quizmetadata.getQuiz_Detail(quizID);
    				if(quizDetail!=null){
    					if(quizDetail.size()!=0){
    						for(int i = 0; i<quizDetail.size(); i++){
    							resDate = ((QuizFileEntry) quizDetail.elementAt(i)).getResDate();
    						}    						         
    					}
    				}
    			}

			// rohit end------------------------------------------


			String createquiz=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam"+"/"+quizID);	
			
			int g_id=GroupUtil.getGID(courseid);
			Vector userList2=UserGroupRoleUtil.getUDetail(g_id,3);
			context.put("userList2",userList2);
			context.put("studentLoginName",studentLoginName);
		}
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in gradequiz class ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}
}			
