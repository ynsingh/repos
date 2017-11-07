package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Attempt_Quiz.java
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

import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import java.io.*;
import java.util.Calendar;
import java.lang.*;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Collections;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

import org.iitk.brihaspati.om.Quiz;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.om.QuizIpaddress;
import org.iitk.brihaspati.om.QuizIpaddressPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuizUtil;
import org.iitk.brihaspati.modules.utils.DbDetail;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.CurrentTime;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.QuizFileEntry;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.QuizMetaDataXmlWriter;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.actions.OLES_AttemptQuiz;
//import org.iitk.brihaspati.modules.screens.call.SecureScreen;
//import org.iitk.brihaspati.modules.utils.GroupUtil;
//import org.iitk.brihaspati.modules.utils.ListManagement;
//import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
//import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
/**
 *   This class contains code for attempt quiz part of student
 *   @author  <a href="noopur.here@gmail.com">Nupur Dixit</a>
 *   @author  <a href="fictionalvicky@gmail.com">PRAJWAL GAURAV SAH</a>
 */

public class Attempt_Quiz extends SecureScreen
{
	static int msg = 0;
	static long maxitime=0;
	static String maxi_time="";
	static String new_max_time="";
	static String Final_max_time="";
	static int sys_hrs;
	static int sys_min;
	static int sys_sec;
	static int user_hrs;
	static int user_min;
	static int user_sec;
	static int server_hrs;
	static int server_min;
	static int check;
	public void doBuildTemplate( RunData data, Context context )
	{
		ParameterParser pp=data.getParameters();
		String LangFile=data.getUser().getTemp("LangFile").toString();
		try{

			User user=data.getUser();
			String uname=user.getName();
			Vector<QuizFileEntry> questionVector = (Vector)user.getTemp("questionvector");
			String loginname=user.getName();
			String uid=Integer.toString(UserUtil.getUID(uname));
			String cid=(String)user.getTemp("course_id");
			context.put("crsId",cid);
			String Role=(String)user.getTemp("role");
			context.put("user_role",Role);
			String quizName = pp.getString("quizName","");
			context.put("quizName",quizName);
			String quizID="";
			String maxTime="";
			int totalMarks =0;
			String count = pp.getString("count","");
			context.put("tdcolor",count);
			String quizIDTime = pp.getString("quizIDTime","");
			if(!quizIDTime.isEmpty()){
				String quizIDTimeArray[] = quizIDTime.split(",");
				quizID = quizIDTimeArray[0];
				//maxTime = quizIDTimeArray[1];no need !@uthor PRAJWAL GAURAV SAH
			}
			else{
				quizID = pp.getString("quizID","");
				//maxTime = pp.getString("maxTime","");no need !@uthor PRAJWAL GAURAV SAH
			}

			context.put("quizID",quizID);
			//context.put("maxTime",maxTime);
			String quesID,fileName,quesType,markPerQues;
			quesID=fileName=quesType=markPerQues="";
			quesType = pp.getString("quesType","");
			String quesDetail = pp.getString("quesDetail","");
			//ErrorDumpUtil.ErrorLog(" string quesDetail is"+quesDetail);

			if(quesDetail.isEmpty()){
				markPerQues = pp.getString("markPerQues","");
				quesID = pp.getString("quesID","");
				fileName = pp.getString("fileName","");
			}
			else{
				String queDetail[] = quesDetail.split(",");
				quesID = queDetail[0];
				fileName = queDetail[1];
				quesType = queDetail[2];
				markPerQues = queDetail[3];
			}

			context.put("quesID",quesID);
			context.put("fileName",fileName);
			context.put("quesType",quesType);
			context.put("markPerQues",markPerQues);

			String question = pp.getString("question","");
			question = QuizUtil.fwslash_unreplace(question);
			question = QuizUtil.bwslash_unreplace(question);
			context.put("question",question);
			String option1 = pp.getString("option1","");
			context.put("option1",option1);
			String option2 = pp.getString("option2","");
			context.put("option2",option2);
			String option3 = pp.getString("option3","");
			context.put("option3",option3);
			String option4 = pp.getString("option4","");
			context.put("option4",option4);
			String finalAnswer = pp.getString("finalAnswer","");
			finalAnswer = QuizUtil.fwslash_unreplace(finalAnswer);
                        finalAnswer = QuizUtil.bwslash_unreplace(finalAnswer);
			context.put("finalAnswer",finalAnswer);

			String answerFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/"+quizID+"/");

/*this block is for updating the timer value i.e. if student comes late for exam timer will
show the remaining time !@uthor PRAJWAL GAURAV SAH*/
			String quizFilePath=TurbineServlet.getRealPath("/Courses"+"/"+cid+"/Exam/");
			String quizPath="Quiz.xml";

			File newfile=new File(quizFilePath+"/"+quizPath);

			Vector vec=new Vector();
			if(newfile.exists()){
				QuizMetaDataXmlReader quiznewMetadata=new QuizMetaDataXmlReader(quizFilePath+"/"+quizPath);

				vec=quiznewMetadata.getQuiz_Detail(quizID);
				String startTime,endTime="";
				
				for(int i=0;i<vec.size();i++)
				{
					maxi_time =((QuizFileEntry) vec.elementAt(i)).getMaxTime();
					startTime=((QuizFileEntry) vec.elementAt(i)).getStartTime();
          				endTime=((QuizFileEntry) vec.elementAt(i)).getEndTime();

				}
//breakpoint
			/*
					This part is used to read the secuirty file and get the start time, end time of the user.
					1. If the user has enter the attempt_quiz for the first time then the stdqendtime will be null and it fetch the startTime of the user.
					2. The condition check for the student endTime if null then calculate the end time for the user.
					3. Updated the stdqendtime with calculated endTime for the first time only.
					4. If the student login second time with resetsecurity string the stdqendtime will not be updated.(so that the user donot get any extra time).
					5. The server calculate the remaining time for the student in attemptquiz mode via calculating from currenttime of the server and the end time.
						1.If the end time of the user calculated is less than the Quiz end time then user End time is usedTime
							else
								server end time is used.
						2.if the user remaining time of the user is 0 or less then the quiz is ended.  
			*/
//for time
				String student="";
				String Stdendtime="";
				String Stdstarttime="";
				String ip="";
				int seq=-1;
				// read from database in place of xml
				// start here
			/*	String securityPath=quizID+"_Security.xml";
				Vector col1= new Vector();
				QuizMetaDataXmlReader reader1=new QuizMetaDataXmlReader(answerFilePath+"/"+securityPath);
				col1=reader1.getSecurityDetail();
				ErrorDumpUtil.ErrorLog("security file size:  "+col1.size());
				if(col1!=null && col1.size()!=0){
						for(int i=0;i<col1.size();i++){
							student=((QuizFileEntry) col1.elementAt(i)).getStudentID();
							//security=((QuizFileEntry) col.elementAt(i)).getSecurityID();
							Stdendtime=((QuizFileEntry) col1.elementAt(i)).getEndTime();
							Stdstarttime=((QuizFileEntry) col1.elementAt(i)).getStartTime();
							ip=((QuizFileEntry) col1.elementAt(i)).getIP();
                                                        ErrorDumpUtil.ErrorLog("under attemptQuiz end time"+Stdendtime);
							ErrorDumpUtil.ErrorLog("under attemptQuiz Strat time"+Stdstarttime);
								if(student.equals(loginname))
								{
								seq=i;
								break;
								}
					}
					
				}
			*/		
				//close here
				//get the userid from loginname
				int usid=UserUtil.getUID(loginname);
				//get details of student attempted quiz from quiz ipaddress on the basis of userid and quizid
				Criteria crit=new Criteria();
        	                crit.add(QuizIpaddressPeer.USER_ID,uid);
				crit.add(QuizIpaddressPeer.QUIZ_ID,quizID);
	                        List qulist=QuizIpaddressPeer.doSelect(crit);
				//ErrorDumpUtil.ErrorLog("test 3 --IP----"+qulist.toString());
				//get the  starttime and end time
				if(qulist.size()!=0)
                                {
					QuizIpaddress element=(QuizIpaddress)qulist.get(0);
			                Stdstarttime=element.getQuizStime();
					Stdendtime=element.getQuizEtime();
                        	}
					

					String stdqendtime="";
  					if(Stdendtime.equals(""))
						{
							SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
							Date d1 = null;
							Date d2 = null;
							d1 = format.parse(Stdstarttime);
							//ErrorDumpUtil.ErrorLog("Student Start time in d1"+d1);
							String d="00:"+maxi_time;
							int z,hrs,min,sec;
					/*if(d.indexOf(":")==-1){
				 		z=Integer.parseInt(d);
								}
					else{
						String maxtimeArray[] = d.split(":");
						z=Integer.parseInt(maxtimeArray[1]);
						
					   }*/
						String maxtimeArray[] = Stdstarttime.split(":");
						hrs=Integer.parseInt(maxtimeArray[0]);
						min=Integer.parseInt(maxtimeArray[1]);
						sec=Integer.parseInt(maxtimeArray[2]);
						String maxi_timeArray[]=maxi_time.split(":");
						float duration_maxtime=Float.parseFloat(maxi_timeArray[0]);
						
						float duration1=duration_maxtime/60;
						String duration_string=Float.toString(duration1);
						String duration1_timeArray[]=duration_string.split("\\.");
						int duration_hr=Integer.parseInt(duration1_timeArray[0]);
						float duration_min=Float.parseFloat("."+duration1_timeArray[1]);
					//	ErrorDumpUtil.ErrorLog("value of duration hrs and min  "+duration_hr+"  "+duration_min);
						
						int End_min=(int)(duration_min*60)+min;
						int final_min,final_hr;
						String Final_end_time="";
						if(End_min>=60)
							{
								final_min=End_min-60;
								final_hr=hrs+duration_hr+1;
								String str_final_min=Integer.toString(final_min);
								String str_final_hr=Integer.toString(final_hr);
								Final_end_time=str_final_hr+":"+str_final_min+":"+maxtimeArray[2];
								//ErrorDumpUtil.ErrorLog("value of final end time is   "+Final_end_time);
								
							}
						else
							{
								final_min=End_min;
								final_hr=hrs+duration_hr;
								String str_final_min=Integer.toString(final_min);
								String str_final_hr=Integer.toString(final_hr);
								Final_end_time=str_final_hr+":"+str_final_min+":"+maxtimeArray[2];
								//ErrorDumpUtil.ErrorLog("value of final end time in else 1   "+Final_end_time);
								
							}
						// update in database in place of xml
						// start here
				/*		QuizMetaDataXmlWriter writer=new QuizMetaDataXmlWriter();
						XmlWriter xmlwriter=new XmlWriter(answerFilePath+"/"+securityPath);
						xmlwriter=writer.WriteinSecurityxml(answerFilePath,securityPath);
						String security="";
						writer.updateSecurity(xmlwriter,student,security,ip,seq,answerFilePath,securityPath,Stdstarttime,Final_end_time);
						ErrorDumpUtil.ErrorLog("Update Securty String   "+writer);

				*/
						
						// set security=null and end time= final end time on the basis of useid and quizid
						// start here	
						List v=null;
						String queryString="Update QUIZ_IPADDRESS set QUIZ_ETIME='"+Final_end_time+"' where USER_ID='"+uid+"' and  QUIZ_ID='"+quizID+"'" ;
						//ErrorDumpUtil.ErrorLog("update time and ipafter query------> "+queryString);
						QuizIpaddressPeer.executeStatement(queryString);
						

					//long c=d1.getTime();
					//ErrorDumpUtil.ErrorLog("value of d1 and c and d  "+d1+" ,  "+c+" "+" ,  "+d);
					/*long duration =(long)z*60*1000;
					long new_stdendtime=c+duration;
					ErrorDumpUtil.ErrorLog("new_stdendtime  "+new_stdendtime);
					long second = (new_stdendtime / 1000) % 60;
					long minute = (new_stdendtime / (1000 * 60)) % 60;
					long hour = (new_stdendtime / (1000 * 60 * 60)) % 24;
				String st_time_in_min=Long.toString(minute);
				String st_time_in_sec=Long.toString(second);
				String st_time_in_hrs=Long.toString(hour);
				new_max_time=hour+":"+minute+":"+second;*/
				//ErrorDumpUtil.ErrorLog("new_max_time isawdawd--------->"+new_max_time);
					stdqendtime=Final_end_time;
					}
				else
					stdqendtime=Stdendtime;
/*This block is for getting start time and end time of server time @uthor PRAJWAL GAURAV SAH and Anand Gupta*/
			OLES_AttemptQuiz cur=new OLES_AttemptQuiz(); 
			String a=cur.CurTime();
			//String a=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
			//ErrorDumpUtil.ErrorLog("Start time and Stdendtime dagadg"+a+"   "+Stdendtime);
			String b=endTime+":00";
			String d="00:"+maxi_time;
			int z;
			if(d.indexOf(":")==-1){
				 z=Integer.parseInt(d);
			}
			else{
				String maxtimeArray[] = d.split(":");
				z=Integer.parseInt(maxtimeArray[1]);
			}
			String System_current_timeArray[]=a.split(":");
						sys_hrs=Integer.parseInt(System_current_timeArray[0]);
						sys_min=Integer.parseInt(System_current_timeArray[1]);
						sys_sec=Integer.parseInt(System_current_timeArray[2]);
			String user_end_timeArray[] = stdqendtime.split(":");
						user_hrs=Integer.parseInt(user_end_timeArray[0]);
						user_min=Integer.parseInt(user_end_timeArray[1]);
						user_sec=Integer.parseInt(user_end_timeArray[2]);
			String server_end_timeArray[] = b.split(":");
						server_hrs=Integer.parseInt(server_end_timeArray[0]);
						server_min=Integer.parseInt(server_end_timeArray[1]);
			//ErrorDumpUtil.ErrorLog("first time adding value"+sys_hrs+"  "+user_hrs+"  "+sys_min+"  "+user_min);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		Date d3 = null;
		Date d4 = null;
				d1 = format.parse(a);
				d2 = format.parse(stdqendtime);
				d3 = format.parse(b);
				long x=d3.getTime()-d1.getTime();

				//in milliseconds
				long c = d2.getTime() - d1.getTime();
				long duration =(long)z*60*1000;
				//long maxtime;
			        /*if(x>duration)
						{
						maxitime=c;
						}
				else
					{	
						if(user_hrs>server_hrs)
						maxtime=x;
						else if(user_hrs==server_hrs && user_min>server_min)
						maxtime=x;
						else
						{
							if(c>0)
							maxtime=c;
							else
							maxtime=0;
						}	
					}*/
						if(user_hrs>server_hrs)
						{
							check=0;
						maxitime=x;
						//ErrorDumpUtil.ErrorLog("x and check--------->  "+x+" "+check);
						}
						else if(user_hrs==server_hrs && user_min>server_min)
						{
							check=0;
						maxitime=x;
						//ErrorDumpUtil.ErrorLog("else if x and check--------->  "+x);
						}
						else
						{
							check=1;
						maxitime=c;
						//ErrorDumpUtil.ErrorLog("else c and check--------->  "+c+"  "+check);
						}	  					
				long t=maxitime;
				long time_in_sec=maxitime/1000;
				long time_in_minutes=time_in_sec/60;
				long new_time_in_sec=time_in_sec%60;
				String st_time_in_min=Long.toString(time_in_minutes);
				String st_time_in_sec=Long.toString(new_time_in_sec);
				new_max_time=st_time_in_min+":"+st_time_in_sec;
				//ErrorDumpUtil.ErrorLog("new_max_time is--------->"+new_max_time);
				context.put("maxTime",new_max_time);
				}
				//ErrorDumpUtil.ErrorLog("maxitime is"+maxitime);


			String answerPath=uid+".xml";
			String questionPath=quizID+"_Questions.xml";
			File file=new File(answerFilePath+"/"+questionPath);
			Vector quizList=new Vector();
			Vector answerList=new Vector();
			QuizMetaDataXmlReader quizmetadata=null;
			if(!file.exists()){
				data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
			}
			else{
				quizmetadata=new QuizMetaDataXmlReader(answerFilePath+"/"+questionPath);
				quizList=quizmetadata.getInsertedQuizQuestions();
				if(quizList!=null && quizList.size()!=0){
					for(int i=0;i<quizList.size();i++){
						totalMarks = totalMarks + Integer.parseInt(((QuizFileEntry)quizList.elementAt(i)).getMarksPerQuestion());
					}
					context.put("maxQuestion",quizList.size());
					context.put("maxMarks",totalMarks);
				}
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_noquizannounced",LangFile));
			}

			/*no need redundant code !@uthor PRAJWAL GAURAV SAH
			 * back side timer implementation
			 */
			/*int maxtime;
			if(maxTime.indexOf(":")==-1){
				 maxtime=Integer.parseInt(maxTime);
			}
			else{
				String maxtimeArray[] = maxTime.split(":");
				maxtime=Integer.parseInt(maxtimeArray[0]);
			}
			//long new_maxtime=maxitime;
			maxtime=maxtime*60*1000;


			ErrorDumpUtil.ErrorLog("maxtime is"+maxtime);
			ErrorDumpUtil.ErrorLog("maxitime is"+maxitime);*/
			/*
			 * this block is used to set timer value in session so that if student refresh the
			 * page timer is not restarted from the starting point
			 */
			if(maxitime>0)
			{
			Timer timer = new Timer();
		    	timer.schedule(new TimerTask(){
		    	public void run(){
		    		Attempt_Quiz.msg = 1;
		    	}
				},maxitime);
			}	// time above is already in milliseconds @uthor PRAJWAL GAURAV SAH (maxtime*60) * 1000);

		    	//if(msg==1)
					if(maxitime<=0){
		    		data.setMessage(MultilingualUtil.ConvertedString("brih_overQuizTime",LangFile));
		    		data.setScreenTemplate("call,OLES,Student_Quiz.vm");
		    	}
			/*
			 * this block is used to set timer value in session so that if student refresh the
			 * page timer is not restarted from the starting point
			 */
			//ErrorDumpUtil.ErrorLog("maxitime is"+maxitime);
			//ErrorDumpUtil.ErrorLog("new_max_time is--------->300:w0   "+new_max_time);

			String Final_max_time="";
			if(check==0)
				{
					if(sys_hrs>server_hrs)
					{
						Final_max_time="0:0";
					}
					else if(sys_hrs==server_hrs && sys_min>server_min)
					{
						Final_max_time="0:0";
					}
					else
					{
						Final_max_time=new_max_time;
					}
				}
			else
				{
					///ErrorDumpUtil.ErrorLog("check value is 1");
					if(sys_hrs>user_hrs)
					{
						//ErrorDumpUtil.ErrorLog("System hr time is more than end hr time");
						Final_max_time="0:0";
					}
					else if(sys_hrs==user_hrs && sys_min>user_min)
					{
						Final_max_time="0:0";
					}
					else
					{
						Final_max_time=new_max_time;
					}
				}
			String timerValue = pp.getString("timerValue","");
			String timerValueSession = (String)user.getTemp("timerValue");
			if(timerValue==null || timerValue.equalsIgnoreCase("")){
				if(timerValueSession==null || timerValueSession.equalsIgnoreCase("")){
					context.put("timerValue",Final_max_time);//now use new_max_time for updating timer
				}
				else{
					//context.put("timerValue",timerValueSession);
					context.put("timerValue",Final_max_time);
				}

			}
			else{
				context.put("timerValue",Final_max_time);
				//context.put("timerValue",timerValue);
				  user.setTemp("timerValue",Final_max_time);
				//user.setTemp("timerValue",timerValue);
			}
			//this part is used to show shuffed list of questions
			if(questionVector!=null && questionVector.size()!=0){
				context.put("quizQuestionList",questionVector);
			}
			//this part is used to show already inserted answers
			File answerFile=new File(answerFilePath+"/"+answerPath);
			if(!answerFile.exists()){
			}
			else{
				quizmetadata=new QuizMetaDataXmlReader(answerFilePath+"/"+answerPath);
				answerList=quizmetadata.getFinalAnswer();
				if(answerList!=null && answerList.size()!=0){
					context.put("answerList",answerList);
				}
			}

		// code for image display in quiz attempt for student
			/*
				@Anand Gupta
					1. To display images in AttemptQuiz.
						1. Base64 method is applied.
			*/
			String [] parts=quizID.split("_", 2);
                        String qzowner=parts[1];
                        context.put("qzowner",qzowner);
                        parts=fileName.split("_", 2);
                        String qbname=parts[0];
                        context.put("qbname",qbname);
			if(!(fileName.equals(""))){
                        String qbfilePath=TurbineServlet.getRealPath("/QuestionBank"+"/"+qzowner+"/"+cid);
			//ErrorDumpUtil.ErrorLog("qbfilePath : "+qbfilePath);
                        TopicMetaDataXmlReader tmdxr=null;
			tmdxr =new TopicMetaDataXmlReader(qbfilePath+"/"+fileName);
                        Vector Read=new Vector();
                        Read=tmdxr.getQuesBank_Detail1();
                        if(Read != null)
                        {
                                for(int i=0;i<Read.size();i++)
                                {
                                        String questonid=((FileEntry)Read.elementAt(i)).getquestionid();
                                        if(questonid.equals(quesID))
                                        {
                                                String Quesimage=((FileEntry)Read.elementAt(i)).getUrl();
						String new_newfilepath=qbfilePath+"/"+qbname+"/"+Quesimage;
						if(!Quesimage.equals(""))
						{
						 File file1=new File(new_newfilepath);
		                                	 FileInputStream imageFile=new FileInputStream(file1);
							 byte imageData[]=new byte[(int)file1.length()];
						         imageFile.read(imageData);
						         String imageDataString=Base64.getEncoder().encodeToString(imageData);
						          imageFile.close();
							 //ErrorDumpUtil.ErrorLog("The BASE 64 IMAGE IS : "+imageDataString);
						          context.put("quesimage",imageDataString);
						}
                                        }
                                }
                        }
			}//check for empty

			/**
                         *Time calculaion for how long user use this page.
                         */
			 int userid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                //CourseTimeUtil.getCalculation(userid);
                                //ModuleTimeUtil.getModuleCalculation(userid);
				int eid=0;
				ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }

		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in attempt_quiz ::"+e);
			data.setMessage(MultilingualUtil.ConvertedString("brih_exception"+e,LangFile));
		}
	}
}
