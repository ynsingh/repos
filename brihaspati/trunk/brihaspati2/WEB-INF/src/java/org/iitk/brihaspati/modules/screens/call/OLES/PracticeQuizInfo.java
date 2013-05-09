
package org.iitk.brihaspati.modules.screens.call.OLES; 

/*
 * @(#)PracticeQuizInfo.java
 *
 *  Copyright (c) 2013 IITK . 
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

import java.util.Vector;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import java.util.HashMap;
import java.util.Map;

/**
 *   @author  <a href="palseema30@gmail.com">Manorama Pal</a> 
 */

public class PracticeQuizInfo extends  SecureScreen{               
	public void doBuildTemplate(RunData data,Context context){
		ParameterParser pp=data.getParameters();
		String LangFile=(String)data.getUser().getTemp("LangFile");	
		try{		
			User user=data.getUser();
			String uname=user.getName();
			String uid=Integer.toString(UserUtil.getUID(uname));
			context.put("uid",uid);			
			String courseid=(String)user.getTemp("course_id");
			String count = pp.getString("count","8");			
			context.put("tdcolor",count);
			String quizlstid=pp.getString("quizlist","");
			context.put("quizlist",quizlstid);
			String studentLoginName=pp.getString("studentLoginName","");
			context.put("studentLoginName",studentLoginName);
			String filemode=pp.getString("filemode","");
			context.put("filemode",filemode);
			
			int g_id=GroupUtil.getGID(courseid);
			Vector userList=UserGroupRoleUtil.getUDetail(g_id,3);
			context.put("studentlist",userList);
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Exam/");
			String quizPath="/Quiz.xml"; 
			String newquizname="";
			File file=new File(filePath+"/"+quizPath);
			Vector PracticeInfo=new Vector();
			Vector quizList=new Vector();
			Vector instructorQuizList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			if(!file.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToshowparcticequizinfo",LangFile));
				return;
			}
			else{
				quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
				quizList=quizmetadata.getQuesBanklist_Detail();
				if(quizList!=null && quizList.size()!=0){
					for(int i=0; i<quizList.size();i++){
						String quizid = ((QuizFileEntry) quizList.elementAt(i)).getQuizID();
						String userName = quizid.substring((quizid.lastIndexOf("_")+1),(quizid.length()));
						String allowpractice = ((QuizFileEntry) quizList.elementAt(i)).getAllowPractice();
						String quizname =((QuizFileEntry) quizList.elementAt(i)).getQuizName();
						if((userName.trim()).equalsIgnoreCase(uname.trim())&&(allowpractice.equals("yes"))){
							instructorQuizList.add((QuizFileEntry)quizList.get(i));
						}
						if(quizid.equals(quizlstid)){
							newquizname=quizname;
						}
						context.put("quizname",newquizname);
						
						
					}
					context.put("quizList",instructorQuizList);
				}
				else{
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizToshowSecurity",LangFile));
					return;
				}
				if(!quizlstid.equals("")){
					int totalAttempt=0;
					String studentid="",attemptnos="",student="";
					File Practfile=new File(filePath+"/"+quizlstid+"/"+quizlstid+"_PracticeQuizInfo.xml");
					if(!Practfile.exists()){
                                		data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequizattempted",LangFile));
						context.put("filemode","blank");
                                		return;
                        		}
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizlstid+"/"+quizlstid+"_PracticeQuizInfo.xml");
                                	Vector collectPractinfo=quizmetadata.getAttemptPracticeQuizDetail();
                                	if(collectPractinfo!=null && collectPractinfo.size()!=0){
						for(int i=0;i<userList.size();i++){
                                        		student=((CourseUserDetail) userList.elementAt(i)).getLoginName();
							Map map = new HashMap();
                                        		for(int k=0; k<collectPractinfo.size();k++){
                                                		studentid=((QuizFileEntry)collectPractinfo.get(k)).getStudentID();
                                                		attemptnos=((QuizFileEntry)collectPractinfo.get(k)).getNoofAttempt();
								if(!map.containsValue(student)){
									if(student.equals(studentid))
									{
										map.put("studentid",student);
										map.put("attemptnos",attemptnos);
										totalAttempt=totalAttempt+Integer.parseInt(attemptnos);
									}
								}
							}//for
							if(!map.containsValue(student)){
								if(!student.equals(studentid)){
									map.put("studentid",student);
                                                			map.put("attemptnos","Not Attempted");
								}
							}
							PracticeInfo.add(map);	
						}//for
					}//if
					context.put("PracticeInfo",PracticeInfo);
					context.put("TotalAttempt",totalAttempt);
				}
			}//else
		}//try
		catch(Exception e) {
			ErrorDumpUtil.ErrorLog("The exception in PracticeQuizInfo class ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}
}			
