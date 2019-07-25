package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Student_Quiz.java
 *
 *  Copyright (c) 2010-2011 MHRD, DEI Agra.
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

import java.util.Calendar;
import java.util.Iterator;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.QuizUtil;
import java.util.Vector;
import java.util.HashMap;
import java.io.File;
import java.util.StringTokenizer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import java.util.Date;
import org.apache.torque.util.Criteria;
import java.util.List;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.om.Quiz;
import javax.servlet.http.*;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;

import org.iitk.brihaspati.om.QuizIpaddress;
import org.iitk.brihaspati.om.QuizIpaddressPeer;

/**
 *   This class contains code for quiz attempt part from student login
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 */
public class Student_Quiz extends SecureScreen
{
	public void doBuildTemplate( RunData data, Context context )
	{
		ParameterParser pp=data.getParameters();
		String LangFile=data.getUser().getTemp("LangFile").toString();
		try{
			Attempt_Quiz.msg = 0;
			User user=data.getUser();
			String loginname=user.getName();
			String user_id=Integer.toString(UserUtil.getUID(loginname));
			String cid=(String)user.getTemp("course_id");
			Criteria crit=new Criteria();
			String Role=(String)user.getTemp("role");
			context.put("user_role",Role);
			String count = pp.getString("count","");
			context.put("userID",user_id);
//			ErrorDumpUtil.ErrorLog(" line 1----------------"+Role);
			if(count.isEmpty()){
				count=(String)user.getTemp("count");
			}
			String type = pp.getString("type","");
			context.put("type",type);
			if(type.equalsIgnoreCase("practice"))
				count="2";
			context.put("tdcolor",count);
			String filePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizPath="/Quiz.xml";
			String quizPath2="Quiz.xml";
			String scorePath="/score.xml";
			File file=new File(filePath+"/"+quizPath);
			Vector quizList=new Vector();
			Vector attemptedQuizList=new Vector();
			Vector finalQuizList=new Vector();
			Vector futureQuizList = new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
//			ErrorDumpUtil.ErrorLog(" line 2-eema--------------"+type);
			if(type.equalsIgnoreCase("practice")){
//					ErrorDumpUtil.ErrorLog(" line 2-------neer loop if ---------"+type);
				if(!file.exists()){
					data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));
				}
				else{
					context.put("isFile","exist");
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath);
					quizList=quizmetadata.getPracticeQuiz_Detail();
					if(quizList!=null){
						if(quizList.size()!=0){
							context.put("quizList",quizList);
//					ErrorDumpUtil.ErrorLog(" line 2----sjadhkashdkhasdhhs-pal-----------"+quizList);
						}
						else{
							data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));
						}
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_nopracticequiz",LangFile));
				}
			}
			else{
				if(!file.exists()){
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
				}
				else{
					context.put("isFile","exist");
					quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizPath2);
					quizList=quizmetadata.readyToAttemptQuiz();
//			ErrorDumpUtil.ErrorLog(" line 151 in else cond-------------"+quizList+"\nfilepath==="+filePath+"\nquizpath=="+quizPath2+"\nquizmetadata==="+quizmetadata);

				//-------------------------------------------DEVENDRA-------------------------------------------------------
				//----------Functionality to get Security String for Login Student of a perticular Quiz from xml files------

					HashMap securityData=new HashMap();
					//String IPAddr=request.getRemoteAddr();
					String IPAddr=getClientIpAddr(data.getRequest());
					context.put("ip",IPAddr);
//			ErrorDumpUtil.ErrorLog(" line padd-d------------"+IPAddr);

					if(quizList!=null && quizList.size()!=0){
						for(int i=0;i<quizList.size();i++){
							String quizid=((QuizFileEntry) quizList.elementAt(i)).getQuizID();
							String path=filePath+"/"+quizid;
							File secFile=new File(path+"/"+quizid+"_Security.xml");
//							ErrorDumpUtil.ErrorLog(" line secfiled------------"+secFile);

							Vector collect=new Vector();
							if(secFile.exists()){
								QuizMetaDataXmlReader reader=new QuizMetaDataXmlReader(path+"/"+quizid+"_Security.xml");
								collect=reader.getSecurityDetail();
//								ErrorDumpUtil.ErrorLog(" line collectd------------"+collect);


								if(collect!=null && collect.size()!=0){
									for(int j=0;j<collect.size();j++){
										String student=((QuizFileEntry) collect.elementAt(j)).getStudentID();
//										 ErrorDumpUtil.ErrorLog(" line student object-----------"+student);
										if(student.equals(loginname)){
											String securityString=((QuizFileEntry) collect.elementAt(j)).getSecurityID();
//										 	ErrorDumpUtil.ErrorLog(" line 183 sec string-----------"+securityString);

											Criteria crt=new Criteria();
					                                		crt.add(QuizIpaddressPeer.USER_ID,user_id);
                                							crt.add(QuizIpaddressPeer.QUIZ_ID,quizid);
					                                		List iplist=QuizIpaddressPeer.doSelect(crt);
//											ErrorDumpUtil.ErrorLog(" ip list 4----------------"+iplist);
											String ip="";
                                							if(iplist.size()!=0)
                                							{			
						                                        	QuizIpaddress element=(QuizIpaddress)iplist.get(0);
                                        							ip=element.getIpAddress();
//												ErrorDumpUtil.ErrorLog(" ip add 5----------- "+IPAddr);
                                							}
										

												//String ip=((QuizFileEntry) collect.elementAt(j)).getIP();
												String temp=securityString+":"+ip;
												securityData.put(quizid, temp);
//										 		ErrorDumpUtil.ErrorLog(" line 202------------"+temp);
											}//if2
										}//for
									}//collect
								}//isexists
							}//for
						}//ifquizlist

					context.put("securityData",securityData);

					//----------------------------------------------------END------------------------------------------------------------------


					futureQuizList = quizmetadata.listFutureQuiz();
//					ErrorDumpUtil.ErrorLog("futureQuizList 6--------------------"+futureQuizList.toArray());
					context.put("futureQuizList",futureQuizList);
					//File scoreFile=new File(filePath+"/"+scorePath);
					String quizID=pp.getString("quizID","");
				 	//ErrorDumpUtil.ErrorLog(" line ------------"+collect);
					// get the quiz id form quizlist
					for(int kk=0;kk<quizList.size();kk++){
						quizID =(((QuizFileEntry) quizList.elementAt(kk)).getQuizID()); 
					}
					File scoreFile=new File(filePath+"/"+quizID+"/"+scorePath);
//					ErrorDumpUtil.ErrorLog("7----------"+quizList);
					if(quizList==null || quizList.size()==0){
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
						return;
					}
				
					if(!scoreFile.exists()){
						context.put("quizList",quizList);
						//ErrorDumpUtil.ErrorLog("quiz list 8------------ "+quizList.toArray());
					}
					else{
						//quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+scorePath);
						quizmetadata=new QuizMetaDataXmlReader(filePath+"/"+quizID+"/"+scorePath);
						attemptedQuizList=quizmetadata.getFinalScore(user_id);
						String quizid,userid,quizid1;
						boolean found = false;
					if(quizList!=null && quizList.size()!=0){
						if(attemptedQuizList!=null && attemptedQuizList.size()!=0){
							for(int i=0;i<quizList.size();i++){
								for(int j=0;j<attemptedQuizList.size();j++){
									quizid = (((QuizFileEntry) quizList.elementAt(i)).getQuizID());
									quizid1 = (((QuizFileEntry) attemptedQuizList.elementAt(j)).getQuizID());
									userid = (((QuizFileEntry) attemptedQuizList.elementAt(j)).getUserID());//enable for test
									if(quizid.equalsIgnoreCase(quizid1)){
										if(userid.equalsIgnoreCase(user_id)){ //enable for test
											found = true;
											//ErrorDumpUtil.ErrorLog("both quiz name equal and user id equal 12-1 ------>  "+quizid1 +" " + quizid+" user id "+ userid);
											break;
										}//enable for test
										else{//enable for test
											found = false;//enable for test
											break;//enable for test
										}//enable for test
									}
									else{
										found = false;
									}
								}//end for
								if(!found){
									QuizFileEntry q = (QuizFileEntry)quizList.get(i);
									finalQuizList.add(q);

								}
							}//end outer for
							if(finalQuizList!=null && finalQuizList.size()!=0){
								context.put("quizList",finalQuizList);

							}
							else{
								//context.put("quizList",zeroList);
								data.setMessage(MultilingualUtil.ConvertedString("brih_noquizattempt",LangFile));
                                                               //ErrorDumpUtil.ErrorLog("final Quiz list14.1 ------>"+zeroList.size());
							}
						}
						else{
							//data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
							context.put("quizList",quizList);
						}
					}
					else
						data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));

				}// else part of existance of score file
				}
			}

			/**
                         *Time calculaion for how long user use this page.
                         */
                         int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("assistant_teacher")))
                         {
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }

		}catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in student_quiz ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		return ip;
	}
}

