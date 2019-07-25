package org.iitk.brihaspati.modules.actions;

/*
 * Copyright (c) 2007,2010 ETRG,IIT Kanpur.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 

 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of ETRG, I.I.T. Kanpur
 */


import java.io.File;
import java.util.List;
import java.util.Vector;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.torque.util.Criteria;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.om.News;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.Assignment;
import org.iitk.brihaspati.om.AssignmentPeer;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;//sunil
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;

	/** This class contains code of Sending Assignment  to the Assignment
	*  with  attachments file txt formate
	*  @author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
	*  @author<a href="arvindjss17@gmail.com">Arvind Pal</a>
	*  @author<a href="sunil.singh6094@gmail.com">Sunil Kumar Pal</a>
	*  <a href="sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
	* @author <a href="mailto:ankitadwivedikit007@gmail.com">Ankita Dwivedi</a>
	* @modified date: 31-03-2014 (Dewanshu Singh)
	*/

public class Assignments extends SecureAction
{
        /**
        * @param data RunData
        * @param context Context
        * @return nothing
        * @exception Exception a generic exception
        */

        VelocityScreen vs=new VelocityScreen();
        public void do_submit(RunData data,Context context)
        {
                try
                {
			String LangFile=data.getUser().getTemp("LangFile").toString();
			String msg="";			
                        ParameterParser pp=data.getParameters();
			

                        /**
                        * Get courseid  and coursename for the user currently logged in
                        * @see UserUtil in Util
                        */
				
                        User user=data.getUser();
                        String username=user.getName();
                        String courseid=(String)user.getTemp("course_id","");
			int userid=UserUtil.getUID(username);
			// get the full of student 
                                String Fullname=UserUtil.getFullName(userid);
                        // Get the roll no of this student              
                                String Rollnm=CourseProgramUtil.getUserRollNo(username,courseid);
				
				
                        /**
                        *Retrieve the parameters by using the ParameterParser
			*/

			String DB_subject1=pp.getString("contentTopic");
                        String DB_message1=pp.getString("message");
                        String Grade=pp.getString("grade");
                        String year=pp.getString("Start_year");
                        String month=pp.getString("Start_mon");
                        String day=pp.getString("Start_day");
                        String pubst=pp.getString("publish");
//			ErrorDumpUtil.ErrorLog("list of assignment-------------"+pubst);
			if(pubst.equalsIgnoreCase("0"))            // modified by ankita dwivedi
				pubst="0";
			else
				pubst="1";

			/**
                        *Get mode for update and delete Assingnment
                        */

			String mode=pp.getString("mode","");	
			String cid=null; 
			int Id = 0;
			if(mode.equals("Update")){
				cid=pp.getString("cid","");	
				Id=Integer.parseInt(pp.getString("tmpId").trim());
			}

                        /**
                        *convert the year,month,date in date formate
                        */
				
				
                        String Duedate=year+"-"+month+"-"+day;
			
			/**
			* create current Date 
			*/
			
			String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String cur_date=cdate.substring(0,4);
                        cur_date=cur_date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);

                        int GID=GroupUtil.getGID(courseid);
                        Date Cur_date=Date.valueOf(cur_date);
                        Date Post_date=Date.valueOf(Duedate);
                        long longCurDate= Cur_date.getTime();
                        long longDueDate= Post_date.getTime();
                        long Edate=(longDueDate-longCurDate)/(24*3600*1000)+1;
                        String News= "New Assignment "+Duedate;
							
									
                        String MessageBox="Course Number "+ courseid +" Topic Name "+DB_subject1+" Grade "+Grade+" Instructions is "+DB_message1;

                        /**
                        * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */
				

                        /*String uname=user.getName();
                        int u_id=UserUtil.getUID(uname);
                        String uid=Integer.toString(u_id);
*/
			
                        /**
                        * convert date String to Integer
                        */

                        int Year1=Integer.parseInt(year);
                        int Month1=Integer.parseInt(month);
			int Day=Integer.parseInt(day);
                        String pdate= year+month+day;
                        int pubdate=Integer.parseInt(pdate);
			boolean flag=false;
			//Vector str=new Vector();

                        /** Expiry date get using ExpiryUtil and convert String type to Date type*/

                        int curdate=Integer.parseInt(ExpiryUtil.getCurrentDate(""));
                        int toprict=StringUtil.checkString(DB_subject1);

                        if(toprict > -1)
        		{
				msg= MultilingualUtil.ConvertedString("assignment_msg4",LangFile);
	                 	data.setMessage(msg);
                        }
                        else if(pubdate >= curdate)
                        {
                                if((Month1==4||Month1==6||Month1==9||Month1==11) && (Day>30))
                                        return;
                                if(Month1==2)
                                {
                                        if((Day>29)&&((Year1%4==0)&&(Year1%100!=0))||((Year1%100==0)&&(Year1%400==0)))
                                                return;
                                        else if (Day>28 && (!(((Year1%4==0)&&(Year1%100!=0))||((Year1%100==0)&&(Year1%400==0)))))
                                                return;

                                }

                                /**
                                * Select GroupName
                                * from the Assignment table
                                */
				Criteria crit1;
                                Criteria crit=new Criteria();
                                crit.add(AssignmentPeer.GROUP_NAME,courseid);
				crit.addAscendingOrderByColumn(AssignmentPeer.ID);
                                List u=AssignmentPeer.doSelect(crit);

				 /**
                                * Assignment id is created
                                */
				String agroup_name;
				int assno=0;
				if(mode.equals("Update")){
					agroup_name=cid;
				}else{
					for(int i=0;i<u.size();i++)
                        		{
                                		Assignment element=(Assignment)(u.get(i));
                		                String assgnid=(element.getAssignId());
		                                assno=Integer.parseInt(StringUtils.substringAfterLast(assgnid,"-"));
						//ErrorDumpUtil.ErrorLog("list of assignment-------------"+element.toString()+" and n is "+assno);
                        		}
                                	//agroup_name=courseid+"-"+u.size();
					assno=assno+1;
                                	agroup_name=courseid+"-"+assno;
				}

                                String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment"+"/"+agroup_name);

                                /**
                                *Get  path for the Assignment
                                *and creating Assignment id dir.
                                */

                                File f=new File(Assign);
                                if(!f.exists())
                                f.mkdirs();

                                /**
                                * From here starts the code for uploading the file
                                */

                                String str3="";
				 String flist[] = f.list();
                                FileItem fileItem;
                                fileItem = pp.getFileItem("file");
                                String fileName1=fileItem.getName();
				TopicMetaDataXmlReader topicMetaData;
				if(StringUtils.isNotBlank(fileName1)){
				if(fileName1.endsWith(".txt")||fileName1.endsWith(".pdf")||fileName1.endsWith(".html")||fileName1.endsWith(".zip")||fileName1.endsWith(".doc")||fileName1.endsWith(".odt")||fileName1.endsWith(".docx")||fileName1.endsWith(".pptx")||fileName1.endsWith(".ppt")||fileName1.endsWith(".odx")||fileName1.endsWith(".odp"))
                                	{//if1
                                        /**
                                        * Select the Topic Name According to Course Id
                                        * from the Assignment table
                                        */
				
                                        crit=new Criteria();
					crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                                        List u5=AssignmentPeer.doSelect(crit);

                                        if( (u5.size()==0) || (mode.equals("Update")) )
                                        {//if2
                                                /**
                                                * Write Assignment file in txt formate
                                                */

						int startIndex=fileName1.lastIndexOf(".")+1;
        		                        String fileExt=fileName1.substring(startIndex);
                                                String fileName="AssignmentFile."+fileExt;
						for(int ss=0;ss<flist.length;ss++)
                                        	{
                                                	if(flist[ss].startsWith("AssignmentFile"))
	                                                {
        	                                                File fl = new File(Assign+"/"+flist[ss]);
                	                                        fl.delete();
                        	                        }
                                	        }	
						//ErrorDumpUtil.ErrorLog("I am here 296 Assign====>"+Assign);
                                                File scormDir1=new File(Assign+"/"+fileName);
                                                fileItem.write(scormDir1);

                                                /**
                                                * Insert the News Info
                                                * from the Assignment table
                                                */

						crit=new Criteria();
                                	        crit.add(NewsPeer.GROUP_ID,GID);
                        	                crit.add(NewsPeer.USER_ID,userid);
                        	                //crit.add(NewsPeer.USER_ID,uid);
                	                        crit.add(NewsPeer.NEWS_TITLE,News);
		                                crit.add(NewsPeer.NEWS_DESCRIPTION,MessageBox);
                                                crit.add(NewsPeer.PUBLISH_DATE,Cur_date);
                                                crit.add(NewsPeer.EXPIRY,Edate);
                                                crit.add(NewsPeer.EXPIRY_DATE,Post_date);
						NewsPeer.doInsert(crit);
                                                /**
						* Insert the Aissignment Info
                		                * from the Assignment table
                                	        */

						//ErrorDumpUtil.ErrorLog("I am here 279 agroup_name====>"+agroup_name);
						crit1=new Criteria();
						if(mode.equals("Update")) 
	                                                crit1.add(AssignmentPeer.ID,Id);
				//		ErrorDumpUtil.ErrorLog("I am here 289 for pubst ====>"+pubst);
                                                crit1.add(AssignmentPeer.ASSIGN_ID,agroup_name);
                                                crit1.add(AssignmentPeer.GROUP_NAME,courseid);
                                                crit1.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                                                crit1.add(AssignmentPeer.CUR_DATE,Cur_date);
                                                crit1.add(AssignmentPeer.DUE_DATE,Post_date);
                                                crit1.add(AssignmentPeer.PER_DATE,Post_date);
                                                crit1.add(AssignmentPeer.GRADE,Grade);
                                                crit1.add(AssignmentPeer.PUBLSH_STATUS,pubst);
						//ErrorDumpUtil.ErrorLog("I am here 289 ====>"+mode + agroup_name + courseid + DB_subject1 + Cur_date + Post_date + Post_date + Grade);
						if (mode.equals("Update")) {
//							ErrorDumpUtil.ErrorLog("In Assignment Update====>");
							AssignmentPeer.doUpdate(crit1);
						}
						else {
						//	ErrorDumpUtil.ErrorLog("In Assignment Insert1====>"+crit1);
                                                	AssignmentPeer.doInsert(crit1);
						}
						File file=new File(Assign+"/__file.xml");
                                                XmlWriter xmlwriter=null;
						int kk = -1;

                                                if(!file.exists())
                                                {
							ErrorDumpUtil.ErrorLog("!file.exists()"+!file.exists());
                                                       	TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
                                               		xmlwriter=new XmlWriter(Assign+"/__file.xml");
						}else{
                                                	if(mode.equals("Update")){
                                                        	kk=0;
/////////////////////////////////////////////////////////////////////////////////////////////////
													      //String filePath1,String xmlFile1
                                                       		//xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml","update", username);
							         Vector v= new Vector();
							         try{
							                topicMetaData=new TopicMetaDataXmlReader(Assign+"/__file.xml");
						                        v=topicMetaData.getAssignmentDetails();
                                                       			TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
									
									xmlwriter=new XmlWriter(Assign+"/__file.xml");
						                        if(v.size()>0){
										//To Write xml with modified attribute	
										writeModifiedAttribute(xmlwriter, v, username, Grade, Duedate, Fullname, Rollnm);
						                        }
						                }
						                catch(Exception e){}
//////////////////////////////////////////////////////////////////////////////////////////////
                                                	}
							else {
                                                       		//xmlwriter=new XmlWriter(Assign+"/__file.xml",kk);
								xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml",-1);
							}	
						}
                                               	if(!mode.equals("Update")){
						ErrorDumpUtil.ErrorLog("I am here 314 ====>"+MessageBox);
                                        		TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,username,Grade,Duedate,Fullname,Rollnm);     
							xmlwriter.writeXmlFile();
						}

//						ErrorDumpUtil.ErrorLog("I am here 314 ====>"+MessageBox);
						/**
                                                * Disply Message when assignment uploaded successfully
                                                * and Assignment update successfully
                                                */

						if(mode.equals("Update")){
							msg= MultilingualUtil.ConvertedString("c_msg5",LangFile);
							data.setMessage(msg);
						}else{
							msg= MultilingualUtil.ConvertedString("assignment_msg5",LangFile);
							data.setMessage(msg);
						}
						flag=true;
						
                                        }//if2
                                        else {  
						msg= MultilingualUtil.ConvertedString("assignment_msg15",LangFile);             
						data.setMessage(msg);
							
					}
                        	}//if1
                                else {
					
					/**
                                        * Disply Message The file for uploading should have '.txt', '.pdf', '.html', '.doc','.odt', '.zip.' extension.
					*/

					msg= MultilingualUtil.ConvertedString("assignment_msg6",LangFile);               
					data.setMessage(msg);
				}
			}//end of blank file if
			else
			{
                                        /**
                                        * Select the Topic Name According to Course Id
                                        * from the Assignment table
                                        */
					ErrorDumpUtil.ErrorLog("In START of else====>"+MessageBox);
				
                                        crit=new Criteria();
					crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                                        List u5=AssignmentPeer.doSelect(crit);
					ErrorDumpUtil.ErrorLog("In else====u5.size()>"+u5.size());

                                        if( (u5.size()==0) || (mode.equals("Update")) )
                                        {//if2
                                                /**
                                                * Insert the News Info
                                                * from the Assignment table
                                                */

						crit=new Criteria();
                                	        crit.add(NewsPeer.GROUP_ID,GID);
//                        	                crit.add(NewsPeer.USER_ID,uid);
                        	                crit.add(NewsPeer.USER_ID,userid);
                	                        crit.add(NewsPeer.NEWS_TITLE,News);
		                                crit.add(NewsPeer.NEWS_DESCRIPTION,MessageBox);
                                                crit.add(NewsPeer.PUBLISH_DATE,Cur_date);
                                                crit.add(NewsPeer.EXPIRY,Edate);
                                                crit.add(NewsPeer.EXPIRY_DATE,Post_date);
						NewsPeer.doInsert(crit);
						//ErrorDumpUtil.ErrorLog("In news Insert1====>"+MessageBox);
                                                /**
						* Insert the Aissignment Info
                		                * from the Assignment table
                                	        */

					//	ErrorDumpUtil.ErrorLog("I am here 279 ====>"+MessageBox);
						crit1=new Criteria();
                                                crit1.add(AssignmentPeer.ID,Id);
                                                crit1.add(AssignmentPeer.ASSIGN_ID,agroup_name);
                                                crit1.add(AssignmentPeer.GROUP_NAME,courseid);
                                                crit1.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                                                crit1.add(AssignmentPeer.CUR_DATE,Cur_date);
                                                crit1.add(AssignmentPeer.DUE_DATE,Post_date);
                                                crit1.add(AssignmentPeer.PER_DATE,Post_date);
                                                crit1.add(AssignmentPeer.GRADE,Grade);
						crit1.add(AssignmentPeer.PUBLSH_STATUS,pubst);
//						ErrorDumpUtil.ErrorLog("I am here 288 ====>"+agroup_name);
//						ErrorDumpUtil.ErrorLog("I am here 288 ====>"+mode + agroup_name + courseid + DB_subject1 + Cur_date + Post_date + Post_date + Grade);
//						ErrorDumpUtil.ErrorLog("I am here 288 ====>"+crit1);
						if (mode.equals("Update")) {
//							ErrorDumpUtil.ErrorLog("In Assignment Update====>");
							AssignmentPeer.doUpdate(crit1);
							ErrorDumpUtil.ErrorLog("In after Update ====>");
						} else {
//							ErrorDumpUtil.ErrorLog("In Assignment Insert1====>"+crit1);
                                                	AssignmentPeer.doInsert(crit1);
						}
						File file=new File(Assign+"/__file.xml");
                                                XmlWriter xmlwriter=null;
						int kk = -1;
						xmlwriter=new XmlWriter(Assign+"/__file.xml");
                                                if(!file.exists())
                                                {
							//ErrorDumpUtil.ErrorLog("I am here 444 absolutePAth====>"+file.getAbsolutePath());
                                                       	TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
                                                       	xmlwriter=new XmlWriter(Assign+"/__file.xml");
						}else{
                                                	if(mode.equals("Update")){
                                                        	kk=0;
//////////////////////////////////////////////////////////////////////////////////////////////
                                                       		//xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml","update", username);
                                                                try{
                                                       			Vector v= new Vector();
                                                                        topicMetaData=new TopicMetaDataXmlReader(Assign+"/__file.xml");
                                                                        v=topicMetaData.getAssignmentDetails();
									//ErrorDumpUtil.ErrorLog("else v =="+ v.size());
                                                                        TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
									//ErrorDumpUtil.ErrorLog("PATH==="+Assign+"/__file.xml");
									xmlwriter=new XmlWriter(Assign+"/__file.xml");
                                                                        if(v.size()>0){
										writeModifiedAttribute(xmlwriter, v, username,Grade,Duedate,Fullname,Rollnm);
                                                                        }
                                                                }
                                                                catch(Exception e){}
//////////////////////////////////////////////////////////////////////////////////////////////

                                                	}else {
                                                       		//xmlwriter=new XmlWriter(Assign+"/__file.xml",kk);
								xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml",-1);
							}
						}
						/*if(mode.equals("Update"))
						{
							if(StringUtils.isBlank(fileName1))
							fileName1=pp.getString("filename","");
						}*/
						if(!mode.equals("Update")){
					
							TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName1,username,Grade,Duedate,Fullname,Rollnm);
							xmlwriter.writeXmlFile();
						}

//						ErrorDumpUtil.ErrorLog("I am here 314 ====>"+MessageBox);
						ErrorDumpUtil.ErrorLog("I am here 574 ====>"+MessageBox);
						/**
                                                * Disply Message when assignment uploaded successfully
                                                * and Assignment update successfully
                                                */

						if(mode.equals("Update")){
							msg= MultilingualUtil.ConvertedString("c_msg5",LangFile);
							data.setMessage(msg);
						}else{
							msg= MultilingualUtil.ConvertedString("assignment_msg5",LangFile);
							data.setMessage(msg);
						}
						flag=true;
						//ErrorDumpUtil.ErrorLog("End ELSE========");
						
                                        }//if2
                                        else {  
						msg= MultilingualUtil.ConvertedString("assignment_msg15",LangFile);             
						data.setMessage(msg);
					}
			}
			}//else if
                        else {

				/**
                                * Disply Message Due date is not correct !!
                                */
				
				msg= MultilingualUtil.ConvertedString("assignment_msg7",LangFile);           
				data.setMessage(msg);
			}

			/**
 			* Break Message in textarea if topic name same or
                        * assignment file .txt, .pdf, .zip ,.doc, .odt and .html format not found.
                        */

			if(!flag){
				context.put("DB_subject",DB_subject1);
                        	context.put("DB_message",DB_message1);		
			}
		}catch(Exception ex) {   data.setMessage("Error in assignment action in do_submit method !!"+ex); }
        }

	/*
	 * A method to modified existing attribute of given XML
	 */
	/////////////////////////
	public void writeModifiedAttribute(XmlWriter xmlwriter, Vector v, String username, String Grade, String Duedate, String Fullname, String Rollnm){
		for(int i=0;i<v.size();i++){
			String fileName=((FileEntry)v.get(i)).getfileName();
			//ErrorDumpUtil.ErrorLog("fileName-------"+fileName);
			if(fileName.startsWith("AssignmentFile")){
				TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,username,Grade,Duedate,Fullname,Rollnm);
                                //ErrorDumpUtil.ErrorLog("IF ======part END");
			}
			else{
                        	String usrname=((FileEntry)v.get(i)).getUserName();
                                String grade=((FileEntry)v.get(i)).getGrade();
                                String Duedate1=((FileEntry)v.get(i)).getDuedate();
                                String Fullname1=((FileEntry)v.get(i)).getFullname();
                                String Rollnm1=((FileEntry)v.get(i)).getRollnm();
                                                                                                                                                          
                                TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,usrname,grade,Duedate1,Fullname1,Rollnm1);
                                //ErrorDumpUtil.ErrorLog("ELSE ======part END");
			}
		}
                xmlwriter.writeXmlFile();
	}
	//////////////////////

        /**
        * Place all the data object in the context for use in the template.
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        **/
        public void dosubmit(RunData data,Context context)
        {
		String fileName="";
		String username = ""; 
		String str4 = "";
		String Fullname = "";
		int userid = 0;
                try
                {
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        String msg="";

			/**
                        * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */

                        User user=data.getUser();
                        ParameterParser pp=data.getParameters();
			String mode=pp.getString("mode","");
			if(StringUtils.isBlank(mode)){
	                       username=user.getName();
                        	userid=UserUtil.getUID(username);
                        	str4=Integer.toString(userid);
				// get the full of student 
                                Fullname=UserUtil.getFullName(userid);
			}
                        /**  Select Role from temp  */
                        String user_role=(String)data.getUser().getTemp("role");
                        String courseid=(String)user.getTemp("course_id","");
                        // Get the roll no of this student              
                                String Rollnm=CourseProgramUtil.getUserRollNo(username,courseid);
                        
			/**
                        * create current Date
                        */

                        String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date=cdate.substring(0,4);
                        date=date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);

			//String date=pp.getString("date","");
                        String DB_subject1=pp.getString("topicList");
			context.put("topicList",DB_subject1);
                        String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");

                        /**
                         Select the Topic Name
                        / from the Assignment table
                        */

                        Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                        List u = AssignmentPeer.doSelect(crit);
                        for(int i=0;i<u.size();i++)
                        {
                                Assignment element=(Assignment)(u.get(i));
                                String str2=(element.getAssignId());
                                Assign =Assign+"/"+str2;
                        }
                        boolean br= false;
                        File f=new File(Assign);
                        String flist[] = f.list();
                        String userid1=Integer.toString(userid);

                        /** serching the file */

                        /*for(int ss=0;ss<flist.length;ss++)
                        {
                                if(flist[ss].startsWith("Answerfile"))
                                        br=true;
                                else if(flist[ss].startsWith(userid1))
                                        br=true;
                        }
                        if(br==false)
                        {*/
                                File file=new File(Assign+"/__file.xml");
                                FileItem fileItem;
                                fileItem = pp.getFileItem("file");
                                String fileName1=fileItem.getName();
                                
				/** check the txt formate */

		//		ErrorDumpUtil.ErrorLog("aaaa    "+fileName1);
                                
				if(fileName1.endsWith(".txt")||fileName1.endsWith(".pdf")||fileName1.endsWith(".html")||fileName1.endsWith(".zip")||fileName1.endsWith(".doc")||fileName1.endsWith(".odt")||fileName1.endsWith(".docx")||fileName1.endsWith(".pptx")||fileName1.endsWith(".ppt")||fileName1.endsWith(".odx")||fileName1.endsWith(".odp")||fileName1.endsWith(".xls")||fileName1.endsWith(".xlsx"))
                                {
					int startIndex=fileName1.lastIndexOf(".")+1;
                                        String fileExt="."+fileName1.substring(startIndex);
                                       // String fileName="AssignmentFile"+fileExt;
                                        if(user_role.equals("student")){
						if(StringUtils.isBlank(Rollnm))
	                                                fileName=userid+fileExt;
						else
                                                	fileName=Rollnm+fileExt;
					}
					else if(mode.equals("PostCorrectAns")){
                                                //fileName=userid+fileExt;
						fileName = pp.getString("filename","");
						userid = Integer.parseInt(StringUtils.substringBeforeLast(fileName,"."));
						username = UserUtil.getLoginName(userid);
                                		Fullname=UserUtil.getFullName(userid);
					}
                                        else if(user_role.equals("instructor"))
                                                fileName="Answerfile"+fileExt;
					for(int ss=0;ss<flist.length;ss++)
                        		{
						if((flist[ss].startsWith(userid1)) || (flist[ss].startsWith(Rollnm)))
						{
							File fl = new File(Assign+"/"+flist[ss]);
							fl.delete();
						}
					}
	                                //ErrorDumpUtil.ErrorLog("BL ======part ");
					File scormDir1=new File(Assign+"/"+fileName);
                                        fileItem.write(scormDir1);
                                        XmlWriter xmlwriter=null;
				        //ErrorDumpUtil.ErrorLog("fileName-------"+fileName);
					if(fileName.equals("Answerfile"+fileExt)){
					       // ErrorDumpUtil.ErrorLog("fileName-------"+fileName);
						xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml","Answerfile",username);            
					}
					else
	                                        xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml","submit",username);
                                        String Grade="10";
					TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,username,Grade,date,Fullname,Rollnm);
                                        xmlwriter.writeXmlFile();
					//msg= MultilingualUtil.ConvertedString("c_msg5",LangFile);
					msg= MultilingualUtil.ConvertedString("assignment_msg5",LangFile);
	                                data.setMessage(msg);

                                        //data.setMessage("Answer File has been uploaded successfully");
                                }
                                else {
					msg= MultilingualUtil.ConvertedString("assignment_msg6",LangFile);    
					data.setMessage(msg);

					//data.setMessage(" The file for uploading should have '.txt' extension !!   ");
				
				}
                        /*}
                        else { 
				msg= MultilingualUtil.ConvertedString("assignment_msg9",LangFile);
				data.setMessage(msg);

				//data.setMessage(" Answer file already uploaded ");      
			}*/
                }
                catch(Exception ex) {   data.setMessage(" Error in action !!       "+ex); }
	}
			
	/**
        * Place all the data object in the context for use in the template.
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        * @return nothing
        **/
        
	public void RePostAns (RunData data,Context context)
        {
                try
                {
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        String msg="";
	
                        /**
                        * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */

                        User user=data.getUser();
                        ParameterParser pp=data.getParameters();
                        String username=user.getName();
                        int userid=UserUtil.getUID(username);
                        //String str4=Integer.toString(userid);


                        /**  Get Role id Student or Instructor   */

                        String user_role=(String)data.getUser().getTemp("role");
                        String courseid=(String)user.getTemp("course_id","");
			// get the full of student 
                                String Fullname=UserUtil.getFullName(userid);
                        // Get the roll no of this student              
                                String Rollnm=CourseProgramUtil.getUserRollNo(username,courseid);

			/**
                        * create current Date
                        */

                        String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date=cdate.substring(0,4);
                        date=date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);

                        //String date=pp.getString("date","");
                        String DB_subject1=pp.getString("topicList");
                        context.put("topicList",DB_subject1);
                        String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
				
                        /**
                        * Select Topic Name
                        * from Assignment table
                        */
			
			Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                        List u = AssignmentPeer.doSelect(crit);
                        for(int i=0;i<u.size();i++)
                        {
                                Assignment element=(Assignment)(u.get(i));
                                String str2=(element.getAssignId());
                                Assign =Assign+"/"+str2;
                        }

                        /** set the path to Assignment file    */

                        File file=new File(Assign+"/__file.xml");
                        String str3="",fileName="";
                        FileItem fileItem;
                        fileItem = pp.getFileItem("file");
                        String fileName1=fileItem.getName();
         //               if(fileName1.endsWith(".txt")||fileName1.endsWith(".pdf")||fileName1.endsWith(".html")||fileName1.endsWith(".zip")||fileName1.endsWith(".doc")||fileName1.endsWith(".odt")||fileName1.endsWith(".docx")||fileName1.endsWith(".pptx"))
                        if(fileName1.endsWith(".txt")||fileName1.endsWith(".pdf")||fileName1.endsWith(".html")||fileName1.endsWith(".zip")||fileName1.endsWith(".doc")||fileName1.endsWith(".odt")||fileName1.endsWith(".docx")||fileName1.endsWith(".pptx")||fileName1.endsWith(".ppt")||fileName1.endsWith(".odx")||fileName1.endsWith(".odp")||fileName1.endsWith(".xls")||fileName1.endsWith(".xlsx"))
                        {
				int startIndex=fileName1.lastIndexOf(".")+1;
                                String fileExt="."+fileName1.substring(startIndex);
                                if(user_role.equals("student")){
                                                if(StringUtils.isBlank(Rollnm))
                                                        fileName=userid+fileExt;
                                                else
                                                        fileName=Rollnm+fileExt;
                                        }

                                if(user_role.equals("instructor"))
                                        fileName="Answerfile"+fileExt;
                                /** Write the file in Upload file  */
                                File scormDir1=new File(Assign+"/"+fileName);
                                fileItem.write(scormDir1);
                                XmlWriter xmlwriter=null;
                                //int kk=-1;

                                /**
                                * Read xml file in Assignment Dir
				* remove the duplicate entry
                                *@see TopicMetaDataXmlReader in Util
                                */
/*
                                TopicMetaDataXmlReader topicmetadata=null;
                                Vector Assignmentlist1=new Vector();
                                topicmetadata=new TopicMetaDataXmlReader(Assign+"/__file.xml");
                                Assignmentlist1=topicmetadata.getAssignmentDetails();
                                if(Assignmentlist1!=null)
                                {
                                        for(int c=0;c<Assignmentlist1.size();c++)
                                        {
                                                String filereader1 =((FileEntry) Assignmentlist1.elementAt(c)).getfileName();
						ErrorDumpUtil.ErrorLog("c==="+c);
                                                if(filereader1.startsWith(str4))
                                                        kk=c;
                                        }
                                }
                                xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml",kk);
*/
                                /**
                                *Maintaing the records in the xml file
                                * @see TopicMetaDataXmlWriter in Util.
                                */
				xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__file.xml","submit",username);
                                String Grade="10";
                                //String Duedate=pp.getString("date");
                                TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,username,Grade,date,Fullname,Rollnm);
                                xmlwriter.writeXmlFile();
				msg= MultilingualUtil.ConvertedString("c_msg5",LangFile);
                                data.setMessage(msg);
                                //data.setMessage("Answer File has been uploaded successfully");
                        }
                        else {
				msg= MultilingualUtil.ConvertedString("assignment_msg6",LangFile);
                                data.setMessage(msg);
                                //data.setMessage("The file for uploading should have '.txt' extension !! ");
			}
                }//try
                catch(Exception ex) { data.setMessage("The error in  RePostAns under  Assignment action  !!"+ex);   }
        }
	/**
        * Place all the data object in the context for use in the template.
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        * @return nothing
        **/

        public void dosubmitView(RunData data,Context context)
        {
                try{
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        String msg="";
				
                        ParameterParser pp=data.getParameters();
                        String DB_subject1=pp.getString("topicList","");
                        context.put("topicList",DB_subject1);
			String mode=pp.getString("mode","");
                        context.put("mode",mode);

                        String username1=pp.getString("topicList1","");
                        context.put("topicList1",username1);

                        String newgrade=pp.getString("newgrade","");
                        context.put("newgrade",newgrade);
                        User user=data.getUser();
                        String GetUser=pp.getString("GetUser","");
                        context.put("GetUser",GetUser);
                        String username=user.getName();
                        String courseid=(String)user.getTemp("course_id","");
                        String DB_subject=pp.getString("contentTopic","");
                        if(DB_subject.length()!=0)
                        {
                                Criteria crit=new Criteria();
                                crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                crit.add(AssignmentPeer.TOPIC_NAME,DB_subject);
                                List u=AssignmentPeer.doSelect(crit);
                                if(u.size()!=0)
				{
					msg= MultilingualUtil.ConvertedString("assignment_msg15",LangFile);
					data.setMessage(msg);
                                        //data.setMessage(" Topic Name already exists  ");
				}
                                else {
					msg= MultilingualUtil.ConvertedString("assignment_msg18",LangFile);
                                     //   data.setMessage(msg); ====> Add one line by sk
					context.put("DB_subject",DB_subject);
                                        data.setMessage("\" "+DB_subject+"  \""+msg);
				}
                        }
			if((DB_subject1.equals(""))|| (DB_subject1.equals(null)))
				data.setMessage(MultilingualUtil.ConvertedString("uploadAction_msg",LangFile));
                }
                catch(Exception ex) { data.setMessage("The error in dosubmitView under  Assignment action  !!"+ex);   }
	}
	
	/**
        * Place all the data object in the context for use in the template.
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        * @return nothing
        **/
        public void Repermission(RunData data , Context context)
        {
                try{
                        /**
                        * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
			//ErrorDumpUtil.ErrorLog("START===========");
                        String msg="";
				
                        User user=data.getUser();
                        ParameterParser pp=data.getParameters();
                        String username=user.getName();
                        int userid=UserUtil.getUID(username);
                        String str4=Integer.toString(userid);
                        String user_role=(String)data.getUser().getTemp("role");
                        String courseid=(String)user.getTemp("course_id","");
			// get the full of student 
                                String Fullname=UserUtil.getFullName(userid);
                        // Get the roll no of this student              
                                String Rollnm=CourseProgramUtil.getUserRollNo(username,courseid);

			/**
                        * create current Date
                        */

                        String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date=cdate.substring(0,4);
                        date=date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);

                        //String date=pp.getString("date","");
                        String DB_subject=pp.getString("topicList");
                        String DB_subject1=pp.getString("topicList1");
			//ErrorDumpUtil.ErrorLog("DB_subject1==="+DB_subject1);
                        String grade=pp.getString("");
                        String year=pp.getString("Start_year");
                        String month=pp.getString("Start_mon");
                        String day=pp.getString("Start_day");
                        String Duedate=year+"-"+month+"-"+day;
                        /** convert Date String to Integer  */
                        int Year1=Integer.parseInt(year);
			int Month1=Integer.parseInt(month);
                        int Day=Integer.parseInt(day);
                        String pdate= year+month+day;
                        int pubdate=Integer.parseInt(pdate);
                        /** Expiry date get using ExpiryUtil and convert String type to Date type*/
                        int curdate=Integer.parseInt(ExpiryUtil.getCurrentDate(""));

                        if(pubdate >= curdate)
                        {
                                if((Month1==4||Month1==6||Month1==9||Month1==11) && (Day>30))
                                {
                                        return;
                                }
                                if(Month1==2)
                                {
                                        if((Day>29)&&((Year1%4==0)&&(Year1%100!=0))||((Year1%100==0)&&(Year1%400==0)))
                                                return;
                                        else if (Day>28 && (!(((Year1%4==0)&&(Year1%100!=0))||((Year1%100==0)&&(Year1%400==0)))))
                                                return;
                                }

                                context.put("topicList",DB_subject1);
                                String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                                if(DB_subject1.equals("All"))
                                {
                                        Date Post_date=Date.valueOf(Duedate);
                                        Criteria crit=new Criteria();
                                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject);
                                        List u=AssignmentPeer.doSelect(crit);
                                        for(int i=0;i<u.size();i++)
                                        {       Assignment element=(Assignment)(u.get(i));
                                                String str2=(element.getAssignId());
                                                Criteria crit1=new Criteria();
                                                crit1.add(AssignmentPeer.ASSIGN_ID,str2);
                                                crit1.add(AssignmentPeer.PER_DATE,Post_date);
                                                AssignmentPeer.doUpdate(crit1);
						             }
                                }


                                /**
                                *select Topic Name from Assignment Table
                                */

                                Criteria crit=new Criteria();
                                crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                crit.add(AssignmentPeer.TOPIC_NAME,DB_subject);
                                List u = AssignmentPeer.doSelect(crit);
                                for(int i=0;i<u.size();i++)
                                {

                                        Assignment element=(Assignment)(u.get(i));
                                        String str2=(element.getAssignId());
                                        Assign =Assign+"/"+str2;

                                        /**
                                        *Getting the path for maintaing the records of xml file
                                        */

                                        File file=new File(Assign+"/__permission.xml");
                                        XmlWriter xmlwriter=null;
                                        if(!file.exists())
                                        {
                                                TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
                                                xmlwriter=new XmlWriter(Assign+"/__permission.xml");
                                        }
                                        if(!DB_subject1.equals("All")){
						//ErrorDumpUtil.ErrorLog("IF ! DB_subject1== All ="+DB_subject1);
				                Vector v= new Vector();
				                File descFile=new File(Assign+"/__permission.xml");
				                try{
                        				TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(Assign+"/__permission.xml");
							v=topicMetaData.getAssignmentDetails();
							TopicMetaDataXmlWriter.writeWithRootOnly1(descFile.getAbsolutePath());
				                        xmlwriter=new XmlWriter(Assign+"/__permission.xml");
				                        if(v.size()>0){
								for(int tmpI=0;tmpI<v.size();tmpI++){
									String fileName=((FileEntry)v.get(tmpI)).getfileName();
									if(!fileName.equals("All")){
										if(!fileName.equals(DB_subject1)){
					                                                String username1=((FileEntry)v.get(tmpI)).getUserName();
        		                			                        String grade1=((FileEntry)v.get(tmpI)).getGrade();
                		                                			String Duedate1=((FileEntry)v.get(tmpI)).getDuedate();
					                                                String Fullname1=((FileEntry)v.get(tmpI)).getFullname();
                        					                        String Rollnm1=((FileEntry)v.get(tmpI)).getRollnm();

                                                					TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,fileName,username1,grade1,Duedate1,Fullname1,Rollnm1);
										}
									}
									else{
                                        					xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__permission.xml",-1);
										break;
									}
								}
								//ErrorDumpUtil.ErrorLog("IF ! DB_subject1== All ENDDDDDDDDD="+DB_subject1);
							}
						}
						catch(Exception e){}
					}
					else
                                        	xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__permission.xml",-1);
					//ErrorDumpUtil.ErrorLog("AFTER ELSE ! DB_subject1== All ="+DB_subject1);
                                        TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,DB_subject1,str2,DB_subject,Duedate,"","");
                                        xmlwriter.writeXmlFile();	
					msg= MultilingualUtil.ConvertedString("assignment_msg16",LangFile);
					data.setMessage(msg);
                                        //data.setMessage("Permission Granted Successfully");

                                }//for
				 }//if
                        else {
				msg= MultilingualUtil.ConvertedString("assignment_msg7",LangFile);
				data.setMessage(msg);
				//data.setMessage(" Due Date is not correct ");
			}

                }//try
                catch(Exception ex) {    }
        }
        
        public void Reset(RunData data , Context context)
        {
                try{

                        /**
                        * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        String msg="";

                        User user=data.getUser();
                        ParameterParser pp=data.getParameters();
                        String username=user.getName();
                        int userid=UserUtil.getUID(username);
                        String str4=Integer.toString(userid);

                        String user_role=(String)data.getUser().getTemp("role");
                        String courseid=(String)user.getTemp("course_id","");

			/**
                        * create current Date
                        */

                        String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date=cdate.substring(0,4);
                        date=date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);
	
                        //String date=pp.getString("date","");
                        String DB_subject=pp.getString("topicList");
                        String DB_subject1=pp.getString("topicList1");

                        String year=pp.getString("Start_year");
                        String month=pp.getString("Start_mon");
                        String day=pp.getString("Start_day");
                        String Duedate="0000"+"-"+"00"+"-"+"00";
                        /** convert Date String to Integer  */
			int Year1=Integer.parseInt(year);
                        ErrorDumpUtil.ErrorLog("yyyy..."+Duedate);
                        int Month1=Integer.parseInt(month);
                        int Day=Integer.parseInt(day);

                        String pdate= year+month+day;

                        int pubdate=Integer.parseInt(pdate);
                        /** Expiry date get using ExpiryUtil and convert String type to Date type*/
                        int curdate=Integer.parseInt(ExpiryUtil.getCurrentDate(""));
                        if(pubdate <= curdate)
                        {
                                String str2="";
                                context.put("topicList",DB_subject1);
                                String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
                                Criteria crit=new Criteria();
                                crit.add(AssignmentPeer.GROUP_NAME,courseid);
                                crit.add(AssignmentPeer.TOPIC_NAME,DB_subject);
                                List u = AssignmentPeer.doSelect(crit);
                                for(int i=0;i<u.size();i++)
                                {
                                        Assignment element=(Assignment)(u.get(i));
                                        str2=(element.getAssignId());
                                        Assign =Assign+"/"+str2;
                                //      ErrorDumpUtil.ErrorLog("Assingment....."+Assign);
                                }

                                boolean reset=false;

                                /**
                                *select Topic Name from Assignment Table
                                */
                                /**
                                *Getting the path for maintaing the records of xml file
                                */
                                File file=new File(Assign+"/__permission.xml");
                                XmlWriter xmlwriter=null;
                                if(!file.exists())
				{
                                       TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
                                       xmlwriter=new XmlWriter(Assign+"/__permission.xml");
                                }

                                //Reader
                                TopicMetaDataXmlReader topicmetadata1=null;
                                Vector Assignmentlist1=new Vector();
                                topicmetadata1=new TopicMetaDataXmlReader(Assign+"/__permission.xml"); 
				Assignmentlist1=topicmetadata1.getAssignmentDetails();
                                File f2= new File(Assign+"/__permission.xml");
                                int search=-1;
                                if(f2.exists())
                                {
                                        if(Assignmentlist1!=null)
                                        {
                                                for(int grade=0;grade<Assignmentlist1.size();grade++)
                                                {
                                                        if(reset==false)
                                                        {       String  fileName =((FileEntry) Assignmentlist1.elementAt(grade)).getfileName();
                                                                if(fileName.equals(DB_subject1))
                                                                        reset=true;
                                                                if(fileName.equals(DB_subject1))
                                                                        reset=true;
                                                                search++;

                                                        }
                                                        else
                                                                break;
                                                }
                                        }
                                }
                                if(DB_subject1.equals("All"))
                                {
                                        Date Post_date=Date.valueOf(Duedate);
                                        Criteria crit1=new Criteria();
                                        crit1.add(AssignmentPeer.GROUP_NAME,courseid);
					crit1.add(AssignmentPeer.TOPIC_NAME,DB_subject);
                                        crit1.add(AssignmentPeer.ASSIGN_ID,str2);
                                        crit1.add(AssignmentPeer.PER_DATE,Post_date);
                                        AssignmentPeer.doUpdate(crit1);
                                }
                                if(reset==true)
                                {
                                        xmlwriter=TopicMetaDataXmlWriter.writeXml_Assignment(Assign,"/__permission.xml",search);
                                        TopicMetaDataXmlWriter.appendUpdationMailElement(xmlwriter,DB_subject1,str2,DB_subject,Duedate,"","");
                                        xmlwriter.writeXmlFile();
					msg= MultilingualUtil.ConvertedString("assignment_msg10",LangFile);
					data.setMessage(msg);
                                        //data.setMessage("Reset Permission Granted Successfully");
                                }
                                else {
					msg= MultilingualUtil.ConvertedString("assignment_msg11",LangFile);
                                        data.setMessage(msg);
                                        //data.setMessage(" Reset Permission failed  !!");
				}
                        }//if
                        else { 
				msg= MultilingualUtil.ConvertedString("assignment_msg7",LangFile);
				data.setMessage(msg);
				//data.setMessage(" Due Date is not correct ");
			}
                }//try
                catch(Exception ex) {    }
        }

        //arvind
        /**
        * Place all the data object in the context for use in the template.
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        * @return nothing
        **/

        public void PostGrade(RunData data , Context context)
        {
                try{
                        /**
			 * Get the user name  and find the user id
                        * @see UserUtil in utils
                        */
			String LangFile=data.getUser().getTemp("LangFile").toString();
                        String msg="";

                        User user=data.getUser();
                        ParameterParser pp=data.getParameters();
                        String username=user.getName();
                        int userid=UserUtil.getUID(username);
                        String str4=Integer.toString(userid);

                        /**
                        *Getting parameter through parameter parser
                        * Put in the context for the use in templates.
                        */

                        String user_role=(String)data.getUser().getTemp("role");
                        String courseid=(String)user.getTemp("course_id","");
                        /**
                        * create current Date
                        */

                        String cdate = Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
                        String date=cdate.substring(0,4);
                        date=date+"-"+cdate.substring(4,6)+"-"+cdate.substring(6,8);

			String DB_subject1=pp.getString("topicList");
			context.put("topicList",DB_subject1);
                        String comgrade1="";
                        String grade=pp.getString("grade","");
				
                        String username1=pp.getString("topicList1");
                        String feedback=pp.getString("feedback");
                        String Assign=TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment");
         	        /**   compare the Max grade  given grade  */
			/**
	                * select the Topic Name
                        * from Assignment table
                        */

			Criteria crit=new Criteria();
                        crit.add(AssignmentPeer.GROUP_NAME,courseid);
                        crit.add(AssignmentPeer.TOPIC_NAME,DB_subject1);
                        List u=AssignmentPeer.doSelect(crit);
                        String str2="";
                        for(int i=0;i<u.size();i++)
                        {
				Assignment element=(Assignment)(u.get(i));
                                str2=(element.getAssignId());
                                Assign =Assign+"/"+str2;
			}
			/**
                        *  Select student name !!
                        */
				
			String studentname="";
                        boolean foundstd=false;
                        String Assign1=Assign;
                        TopicMetaDataXmlReader topicmetadatafile=null;
                        Vector Assignmentlist=new Vector();
                        File ffile= new File(Assign1+"/__file.xml");
			if(ffile.exists())
                        {
				topicmetadatafile=new TopicMetaDataXmlReader(Assign1+"/__file.xml");
                                Assignmentlist=topicmetadatafile.getAssignmentDetails();
                                for(int c=0;c<Assignmentlist.size();c++)
				{
					String filereader =((FileEntry) Assignmentlist.elementAt(c)).getfileName();
                                        String stdname =((FileEntry) Assignmentlist.elementAt(c)).getUserName();
                                        if(filereader.startsWith("Assign")||StringUtils.isBlank(filereader))
						comgrade1 =((FileEntry) Assignmentlist.elementAt(c)).getGrade();
					if(stdname.equals(username1))
                                        	foundstd=true;
				}
			}

                        int cgrade=Integer.parseInt(comgrade1);
                        int cgrade1=Integer.parseInt(grade);
	                if(foundstd==false) {
        			msg= MultilingualUtil.ConvertedString("assignment_msg12",LangFile);
                                data.setMessage(msg);
				//data.setMessage("Student did not submit Assignment , he/she does note deserve grades and feedback !!");
				
			}
                        else
                        {
                                if(cgrade1<=cgrade)
                                {

					/** check the file are exixts  */
					File file=new File(Assign+"/__Gradefile.xml"); XmlWriter xmlwriter=null;
					if(!file.exists())
					{
						TopicMetaDataXmlWriter.writeWithRootOnly1(file.getAbsolutePath());
						xmlwriter=new XmlWriter(Assign+"/__Gradefile.xml");
                                	}
					Vector xmlvector=new Vector();
	                                int kk=-1;
				        /**
                                	* Getting the gradedetail through xml file
                                	* @see TopicMetaDataXmlReader in Util.
                                	*/
					TopicMetaDataXmlReader topicmetadata=null;
					Vector Assignmentlist1=new Vector();
					topicmetadata=new TopicMetaDataXmlReader(Assign+"/__Gradefile.xml");
                                	Assignmentlist1=topicmetadata.getAssignmentDetails1();
                                	if(Assignmentlist1!=null)
                                	{
						for(int c=0;c<Assignmentlist1.size();c++)
						{
                                                	String filereader1 =((FileEntry) Assignmentlist1.elementAt(c)).getUserName();
                                                	if(filereader1.equals(username1))
                                                        kk=c;
                                        	}
                                	}
	                                /**
        	                        * Maintaing the records in the xml file
                	                * @see TopicMetaDataXmlWriter in Util.
                        	        */
					xmlvector.add(kk);
	                                xmlwriter=TopicMetaDataXmlWriter.WriteXml_New4(Assign,"/__Gradefile.xml",xmlvector);
        	                        //TopicMetaDataXmlWriter.appendGrade(xmlwriter,str2,username1,grade,feedback);
        	                        TopicMetaDataXmlWriter.appendGrade(xmlwriter,username1,grade,feedback);
                	                xmlwriter.writeXmlFile();
					msg= MultilingualUtil.ConvertedString("assignment_msg8",LangFile);
					data.setMessage(msg);
					
					data.setScreenTemplate("call,Assignment,ViewAss.vm");

                        	        //data.setMessage("Grade Send Sucessfully");

                        }       // if end
                        else{ 
				msg= MultilingualUtil.ConvertedString("assignment_msg13",LangFile);
                                data.setMessage(msg);
	                        //data.setMessage("Please check the Maximum Grade");
			}	
                        }
			data.setScreenTemplate("call,Assignment,ViewAss.vm");
                }
                catch(Exception ex)  {  data.setMessage("The error in Assignment !!"+ex);                }
        }

        /**
        * Place all the data object in the context for use in the template.Delete
        * @param data RunData instance
        * @param context Context instance
        * @exception Exception, a generic exception
        **/

	public void doDelete(RunData data,Context context)
        {
               	try
               	{
                       	String LangFile=data.getUser().getTemp("LangFile").toString();
                       	ParameterParser pp=data.getParameters();
                       	User user=data.getUser();
                       	String username=data.getUser().getName();
                       	int uid=UserUtil.getUID(username);

			/**
                        *Get mode for Delete Assingnment
                        */

                       	String mode=pp.getString("mode","");
                       	context.put("mode",mode);
			String courseid=(String)user.getTemp("course_id");
                       	String assignid=pp.getString("cid","");
                       //	ErrorDumpUtil.ErrorLog("\nassignid"+assignid);
                       	String topicname=pp.getString("topic","");
                      // 	ErrorDumpUtil.ErrorLog("\nmode"+mode+"\ntopicname"+assignid);
				
			/**
                        *Delete Assingnment From database
                        */

			Criteria crit=new Criteria();
                        crit=new Criteria();
                        crit.add(AssignmentPeer.ASSIGN_ID, assignid);
                        AssignmentPeer.doDelete(crit);

			/**
                        *Delete Assingnment From Courses 
                        */

			String courseRealPath=TurbineServlet.getRealPath("/Courses");
			String filepath=(courseRealPath+"/"+courseid+"/"+"/Assignment");
                       	File AssDir=new File(filepath+"/"+assignid);
                      // 	ErrorDumpUtil.ErrorLog("\nassignid"+assignid+"\nfilepath"+filepath+"\nAssDir"+AssDir);
                       	SystemIndependentUtil.deleteFile(AssDir);
			//AssDir.delete();
                        data.setMessage(MultilingualUtil.ConvertedString("assignment_msg19",LangFile));
			//"Assignment deleted successfully !!"
		}
                catch(Exception ex){data.setMessage("Error in Delete word and alias" + ex);}
       	}//method close

        /**
        * This method is invoked when no button corresponding to
        * @param data RunData
        * @param context Context
        * @exception Exception, a generic exception
        * @return nothing
        */
        public void MarkUpload(RunData data,Context context)
        {
                data.setScreenTemplate("call,UserMgmt_User,UploadMarks.vm");
        }

	public void Go(RunData data,Context context)
        {
	
		ParameterParser pp=data.getParameters();
		String topicList=pp.getString("topicList","");
               	context.put("topicList",topicList);

        }

	/**
	 *  This method is invoked when publish or unpublish action called 
	 *  @param data RunData
	 *  @param context Context
	 *  @exception Exception, a generic exception
	 *  @return nothing
	 **/
	public void PubUnpub(RunData data,Context context)
        {
		try{
			String LangFile=data.getUser().getTemp("LangFile").toString();
	                ParameterParser pp=data.getParameters();
        	        String assid=pp.getString("cid","");
                	String asspubst=pp.getString("astat","");
			if (asspubst.equals("0"))
				asspubst="1";
			else
				asspubst="0";
	                /**
			 * Update status Assingnment in database 
			 **/

	                Criteria crit=new Criteria();
                	crit.add(AssignmentPeer.ID, assid);
	                crit.add(AssignmentPeer.PUBLSH_STATUS, asspubst);
        	        AssignmentPeer.doUpdate(crit);
			if (asspubst.equals("0"))
				data.setMessage(MultilingualUtil.ConvertedString("assignment_msg20",LangFile));
			else
				data.setMessage(MultilingualUtil.ConvertedString("assignment_msg21",LangFile));
		}
		catch (Exception ex){data.setMessage("Error in publish /unpublish assignment" + ex);}
		
        }

	
	public void doPerform(RunData data,Context context)
        {       try
                {
                        String action=data.getParameters().getString("actionName","");
			ErrorDumpUtil.ErrorLog("actionname=="+action);
                        if(action.equals("brih_upload"))
                                do_submit(data,context);
                                //do_submit();
        	        else if(action.equals("eventSubmit_doDelete"))
                	        doDelete(data,context);
                        else if(action.equals("dosubmit"))
                                dosubmit(data,context);
			else if(action.equals("doUpdate"))
                                do_submit(data,context);
                        else if(action.equals("RePostAns"))
                                RePostAns(data,context);
				//for do search
                        else if(action.equals("dosubmitView"))
                                dosubmitView(data,context);
                        else if(action.equals("PostGrade"))
                                PostGrade(data,context);
                        else if(action.equals("Repermission"))
                                Repermission(data,context);
                        else if(action.equals("Reset"))
                                Reset(data,context);
                        else if(action.equals("MarkUpload"))
                                MarkUpload(data,context);
			else if(action.equals("Go"))
                                Go(data,context);
			else if(action.equals("eventSubmit_doPublish"))
                                PubUnpub(data,context);
                        else
                        {       String LangFile=data.getUser().getTemp("LangFile").toString();
                                String msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
                                data.setMessage(msg);
                        }
			 }
                catch(Exception ex)  {    data.setMessage("The error in Assignment !!"+ex);   }
        }
}

