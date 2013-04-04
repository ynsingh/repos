package org.iitk.brihaspati.modules.utils;


/*@(#)CommonUtility.java
 *  Copyright (c) 2005-2008,2010-2011,2012,2013 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */
//java
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.List;
import java.util.Calendar;
import java.util.Vector;
import java.lang.reflect.Array;
import java.util.StringTokenizer;
import java.util.Iterator;

import com.workingdogs.village.Record;

//import java.sql.Date;
import java.util.Date;
import java.math.BigDecimal;
//turbine
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

//brihaspati
import org.iitk.brihaspati.om.AssignmentPeer;
import org.iitk.brihaspati.om.AttendenceSeetPeer;
import org.iitk.brihaspati.om.AttendenceSeet;
import org.iitk.brihaspati.om.BatchCoursePeer;
import org.iitk.brihaspati.om.BatchPeer;
import org.iitk.brihaspati.om.CalInformationPeer;
import org.iitk.brihaspati.om.CourseInfoPeer;
import org.iitk.brihaspati.om.CourseMonthPeer;
import org.iitk.brihaspati.om.CourseProgramPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CourseTimedayPeer;
import org.iitk.brihaspati.om.CourseTimePeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.DepartmentPeer;
import org.iitk.brihaspati.om.EventsPeer;
import org.iitk.brihaspati.om.FacInfoPeer;
import org.iitk.brihaspati.om.FacultyCoursePeer;
import org.iitk.brihaspati.om.FaqmovePeer;
import org.iitk.brihaspati.om.FaqPeer;
import org.iitk.brihaspati.om.FaqVotePeer;
import org.iitk.brihaspati.om.GlossaryAliasPeer;
import org.iitk.brihaspati.om.GlossaryPeer;
import org.iitk.brihaspati.om.HintQuestionPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteQuotaPeer;
import org.iitk.brihaspati.om.InstituteQuota;
import org.iitk.brihaspati.om.InstructorPermissionsPeer;
import org.iitk.brihaspati.om.LecturePeer;
import org.iitk.brihaspati.om.LearnerScoPeer;
import org.iitk.brihaspati.om.MailReceivePeer;
import org.iitk.brihaspati.om.MailSendPeer;
import org.iitk.brihaspati.om.ModuleTimePeer;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.NotePeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.OpenidPeer;
import org.iitk.brihaspati.om.Openid;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.ProxyUserPeer;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.om.RdfPeer;
import org.iitk.brihaspati.om.RemoteCoursesPeer;
import org.iitk.brihaspati.om.RemoteUsersPeer;
import org.iitk.brihaspati.om.ResearchRepositoryPeer;
import org.iitk.brihaspati.om.RoomPeer;
import org.iitk.brihaspati.om.SessionaddressPeer;
import org.iitk.brihaspati.om.StudentExpiryPeer;
import org.iitk.brihaspati.om.SurveyQuestionPeer;
import org.iitk.brihaspati.om.SurveyResultPeer;
import org.iitk.brihaspati.om.SurveyStudentPeer;
import org.iitk.brihaspati.om.SystemCleantimePeer;
import org.iitk.brihaspati.om.SystemCleantime;
import org.iitk.brihaspati.om.TableIdPeer;
import org.iitk.brihaspati.om.TaskPeer;
import org.iitk.brihaspati.om.Task;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TimetableCourseInfoPeer;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.TurbineGroup;
import org.iitk.brihaspati.om.TurbinePermissionPeer;
import org.iitk.brihaspati.om.TurbineRolePeer;
import org.iitk.brihaspati.om.TurbineRolePermissionPeer;
import org.iitk.brihaspati.om.TurbineScheduledJobPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.VenuePeer;
import org.iitk.brihaspati.om.ModulePermissionPeer;
import org.iitk.brihaspati.om.CourseModulePeer;

import org.apache.commons.io.FileSystemUtils;
import org.iitk.brihaspati.modules.actions.UploadAction;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.actions.Groupmanagement;
import org.iitk.brihaspati.om.StudentExpiryPeer;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.UserPref;
//Lucene
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;
import org.iitk.brihaspati.modules.utils.GraphUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.iitk.brihaspati.om.PollPeer;
import org.iitk.brihaspati.om.Poll;

/**
 * This class is used for call the method in mylogin 
 * like Create index for Search, Clean the system 
 * 
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:kalpanagtm@gmail.com">Kalpana Gautam</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @author <a href="mailto:kishore.shukla@gmail.com">Kishore shukla</a>
 * @author <a href="mailto:gaurav.soni992@gmail.com">Gaurav Verma</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @author <a href="mailto:piyushm45@gmail.com">PiyushMishra</a>	
 * @modified date:09-11-2010,03-03-2011,02-07-2011,04-10-2011,05-09-2012
 * @modified date:12-09-2012,10-10-2012,23-10-2012,24-03-2013,19-03-2013
 * @version 1.0
 * @since 1.0
 * @see ExpiryUtil
 */
public class CommonUtility{

	static final long ONE_HOUR = 3600000L;

	/**
	 * This method create the index of course
	 * @return boolean 
	 */
	public static boolean CreateIndex(String Path){

	try{
		//File f1=new File(TurbineServlet.getRealPath("/Courses"));
		File f1=new File(Path);
                String arr[]=null;
                if(f1.isDirectory())
                {
			 arr=f1.list();
                }
                for(int i=0;i<arr.length;i++)
                {
	                if(arr[i].endsWith("_Index")){
        	        }
                	else
                	{
	                	String s=TurbineServlet.getRealPath("/");
        		        //File f2=new File(s+"/Courses/"+arr[i]+"_Index");
        		        File f2=new File(Path+"/"+arr[i]+"_Index");
		                if(!f2.exists()){
                			boolean success=false;
			                success=f2.mkdir();
		                }
			}
                }
                arr=f1.list();
                for(int i=0;i<arr.length;i++)
                {
                	if(arr[i].endsWith("_Index")){
				int l=arr[i].length();
				String cst=arr[i].substring(0,l-6);
			        //CreateIndexer.StartIndex(TurbineServlet.getRealPath("/Courses")+"/"+arr[i],TurbineServlet.getRealPath("/Courses")+"/"+cst);
			        CreateIndexer.StartIndex(Path+"/"+arr[i],Path+"/"+cst);
                	}
                }
	}
        catch(Exception e){
		ErrorDumpUtil.ErrorLog("The error in getCreateIndex() - CommonUtil class !!"+e);
	}
	return true;
	}//end of Method

	/**
	 * This method clear all the expired entries from database
	 * @return String 
	 */
	public static boolean CleanSystem(){
	try {
		String c_date=ExpiryUtil.getCurrentDate("-");
                java.sql.Date current_date=java.sql.Date.valueOf(c_date);
                Criteria crit=new Criteria();
                crit.add(SystemCleantimePeer.ID,1);
                List z=SystemCleantimePeer.doSelect(crit);
                if(z.size()!=0){//1 if
			SystemCleantime element=(SystemCleantime)z.get(0);
                        String Clean_date=(element.getCleanDate()).toString();
                        /**
                        * Date and Time seprated from Date
                        * here using only Date
                        */
				
                        String clean_Date=Clean_date.substring(0,10);
                        if(!clean_Date.equals(c_date)){//2 if
                        	/**
                        	*Code for update the clean date in database
                        	*/
                       		crit=new Criteria();
                                crit.add(SystemCleantimePeer.ID,1);
                                crit.add(SystemCleantimePeer.CLEAN_DATE,current_date);
                                SystemCleantimePeer.doUpdate(crit);
                        	boolean Expiry_Success=ExpiryUtil.Expiry();
				UpdateInfoMail.checknWriteXml();
				String  Update_Mail = UpdateInfoMail.getUpdationMail();
				 /*method for day wiseCourseTime in database*/
                                CourseTimeUtil.CourseDay();
				CourseTimeUtil.deleteSameDateEntry();
				Calendar cal = Calendar.getInstance();
        		        int tdate=cal.get(Calendar.DAY_OF_MONTH);
	                	if(tdate==1){
                                        CourseTimeUtil.UpdateCourseMonth();
					ModuleTimeUtil.deleteLastmonthentry();
				}
                               // if(Expiry_Success=true){//3 if
	                               // this code moved to upside
                               // }//end of if 3 loop
				/*calculation for graph and data insert in XML file*/
				GraphUtil.midnightcalculation();
				GraphUtil.midnightModuleCalculation();
                                String path = TurbineServlet.getRealPath("/");
				boolean CI=CreateIndex(path+"Courses");
				boolean CIndx=CreateIndex(path+"UserArea");
				boolean OT=optimizeTables();
				boolean ADB=autoDeletebackup();
				grpLeader();
				//Call method removeNonce();
				boolean nonce = removeNonce();
				boolean uquota = setQuota();
				//Calling Emailspooling file to send Failure mail
				 MailNotificationThread.getController().emailXMLRead();
				
				//Call method removeNupdateEmailUser();
				boolean rmvNupdt = removeNupdateEmailUser();	
				//InsertStuExpRecord();
                        }//end of if 2 loop

		}//end of if 1 loop
                else{
                	/**
                        * Code for first time fill the clean Date in database
                        */
                	crit=new Criteria();
			crit.add(SystemCleantimePeer.ID,1);
                        crit.add(SystemCleantimePeer.CLEAN_DATE,current_date);
                        SystemCleantimePeer.doInsert(crit);
		}
	}//end of try
        catch(Exception ex)
        {
		ErrorDumpUtil.ErrorLog("The error in CleanSystem - CommonUtil class !!"+ex);
        }
	return true;
	}//end of method

	/**
                * This method is responsble for automatic
                * deleting the 3days old backup files.
		* @return boolean
                */
        public static boolean autoDeletebackup(){
                try{ //try0
                        File dir=new File(TurbineServlet.getRealPath("/BackupData/"));
                        if(dir.exists()){
                                File list[]=dir.listFiles();
                                for(int i=0;i<(Array.getLength(list));i++){
                                        String filename=list[i].getName();
                                        int ln=filename.length();
                                        String dtime=filename.substring(ln-12,ln-4);
                                        int fdt=Integer.parseInt(dtime);
                                //      get the current date
                                        String curdate=ExpiryUtil.getCurrentDate("");
                                        int cdt=Integer.parseInt(curdate);
                                        int diffday=cdt - fdt;
                                //      compare it
                                //      if greater than 4
                                        if(diffday>4){
                                                File fle=list[i].getAbsoluteFile();
                                                ErrorDumpUtil.ErrorLog("delete file path in util is :"+fle.toString());
                                //      delete it
                                                fle.delete();

                                        }

                                }
                        }
                }
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("The error in Backups file deletion : " +e);
		}
	return true;
        }//autoDeletebackup


	/**
	 * This method display all task which are moved to index page
	 * @return Vector listTask 
	 */
	public static Vector DisplayTask(int uid,String date){
		Vector listTask=new Vector();
	try{
		Criteria crit=new Criteria();
		crit.add(TaskPeer.USER_ID,uid);
		crit.add(TaskPeer.STATUS,1);
		crit.addAscendingOrderByColumn(TaskPeer.START_DATE);
		crit.add(TaskPeer.DUE_DATE,(Object)date,crit.GREATER_EQUAL);
                List v = TaskPeer.doSelect(crit);

                for(int i=0;i<v.size();i++)
                {
                	String Title=((Task)v.get(i)).getTitle();
                        int tid=((Task)v.get(i)).getTaskId();
			int tid1=((Task)v.get(i)).getParentTaskId();
			int seqno=((Task)v.get(i)).getSeqNo();
			int pseqno=((Task)v.get(i)).getPseqNo();
			int depth=((Task)v.get(i)).getDepth();
                        TaskDetail tDetail=new TaskDetail();
                        tDetail.setTask_Id(tid);
                        tDetail.setTitle(Title);
			tDetail.setParentTask_Id(tid1);
			tDetail.setSeqNumber(seqno);
			tDetail.setPSeq_No(pseqno);
			tDetail.setDepth(depth);
                        listTask.add(tDetail);
                }
 
	}//end of try
	catch(Exception ex)
        {
                ErrorDumpUtil.ErrorLog("The error in Display Task - CommonUtil class !!"+ex);
                listTask.add(ex);
        }
	return listTask;
	}//end of Method
	/**
	* This Method used to update text file
	* @param filePath String
	* @param msgid String
	* @param DbMessage String
	* @param update boolean 
	*
	*/
	public static void UpdateTxtFile(String filePath, String msg_idd, String DB_message, boolean update)
	{
	try{
		String str[] = new String[1000];
                int i =0;
                int startd = 0;
                int stopd = 0;

                 /* deleting the message from the txt file  */
                BufferedReader br=new BufferedReader(new FileReader (filePath));
                while ((str[i]=br.readLine()) != null)
		{                
                        if (str[i].equals("<"+msg_idd+">"))
                        {
                               	startd = i;
                        }
                        else if(str[i].equals("</"+msg_idd+">"))
                        {
                        	stopd = i;
                        }
                        i= i +1;
                } //while
                br.close();
                FileWriter fw=new FileWriter(filePath);
		if(update){ 
			for(int x=0;x<=startd;x++)
                	{
                		fw.write(str[x]+"\r\n");	
			}
                }
		else{    
			for(int x=0;x<startd;x++) 
			{                
                		fw.write(str[x]+"\r\n");
			}
                }
		if(update)
			fw.write(DB_message+"\n");
		if(update){ 
			for(int y=stopd;y<i;y++)
			{                
                		fw.write(str[y]+"\r\n");
			}
                }
		else {   
			for(int y=stopd+1;y<i;y++) 
                	{
                		fw.write(str[y]+"\r\n");
			}
                }
                fw.close();
		str=null;
	}
	catch(Exception ex){
		ErrorDumpUtil.ErrorLog("The error in UpdateTxtFile() - CommonUtil class !!"+ex);
	}
	}
			/**
                        * Write all topic in xml file if topic is not present in xml file
			* @param filePath string
                        */
		public static void cretUpdtxml(String filePath,String uName,String location){
			try{
                        String filter[]={"Permission","Remotecourse","content__des.xml","coursecontent__des.xml"};
                        NotInclude exclude=new  NotInclude(filter);
                        String file[]=(new File(filePath)).list(exclude);
			File rmPath=new File(filePath+"/content__des.xml");
			if(rmPath.exists()){
			rmPath.delete();
			}
			File Path=new File(filePath+"/coursecontent__des.xml");
                        for(int i=0;i<file.length;i++)
                        {
				UploadAction uA=new UploadAction();
                                // get the value from xml file
                                if(Path.exists()){
                                        TopicMetaDataXmlReader topicMetaData=new TopicMetaDataXmlReader(filePath+"/"+"coursecontent__des.xml");
                                        String tpcxml[]=topicMetaData.getTopicNames();
                                        for(int j=0;j<tpcxml.length;j++){
                                                String tnm=tpcxml[j];
                                //check topic name exist in xml file or not. If not then write in to xml file
                                                if(file[i].equals(tnm)){
                                                }
                                                else{
                                                        uA.topicSequence(filePath,file[i],((new java.util.Date()).toString()),uName,location);
                                                }
                                        }
                                }
                                else{
                                        uA.topicSequence(filePath,file[i],((new java.util.Date()).toString()),uName,location);
                                }
                        }
			}//try
			catch (Exception ex){
				ErrorDumpUtil.ErrorLog("The error in Update and create topic xml file - CommonUtil class(cretUpdtxml method) !!"+ex);
			}
		}

	/**
         * The method analyzes and optimizes the tables of the database
	 * return boolean
         */
	
        public static boolean  optimizeTables() {
		try{
		
		AttendenceSeetPeer.executeQuery("ANALYZE TABLE ATTENDENCE_SEET");
		AttendenceSeetPeer.executeQuery("OPTIMIZE TABLE ATTENDENCE_SEET");

		AssignmentPeer.executeQuery("ANALYZE TABLE ASSIGNMENT");
		AssignmentPeer.executeQuery("OPTIMIZE TABLE ASSIGNMENT");

                BatchCoursePeer.executeQuery("ANALYZE TABLE BATCH_COURSE");
                BatchCoursePeer.executeQuery("OPTIMIZE TABLE BATCH_COURSE");

		BatchPeer.executeQuery("ANALYZE TABLE BATCH");
                BatchPeer.executeQuery("OPTIMIZE TABLE BATCH");
		
		CalInformationPeer.executeQuery("ANALYZE TABLE CAL_INFORMATION");
                CalInformationPeer.executeQuery("OPTIMIZE TABLE CAL_INFORMATION");

                CoursesPeer.executeQuery("ANALYZE TABLE COURSES");
                CoursesPeer.executeQuery("OPTIMIZE TABLE COURSES");

		CourseInfoPeer.executeQuery("ANALYZE TABLE COURSE_INFO");
                CourseInfoPeer.executeQuery("OPTIMIZE TABLE COURSE_INFO");

		CourseModulePeer.executeQuery("ANALYZE TABLE COURSE_MODULE");
                CourseModulePeer.executeQuery("OPTIMIZE TABLE COURSE_MODULE");

		CourseMonthPeer.executeQuery("ANALYZE TABLE COURSE_MONTH");
                CourseMonthPeer.executeQuery("OPTIMIZE TABLE COURSE_MONTH");

		CourseProgramPeer.executeQuery("ANALYZE TABLE COURSE_PROGRAM");
                CourseProgramPeer.executeQuery("OPTIMIZE TABLE COURSE_PROGRAM");
		
		CourseTimedayPeer.executeQuery("ANALYZE TABLE COURSE_TIMEDAY");
                CourseTimedayPeer.executeQuery("OPTIMIZE TABLE COURSE_TIMEDAY");

		CourseTimePeer.executeQuery("ANALYZE TABLE COURSE_TIME");
                CourseTimePeer.executeQuery("OPTIMIZE TABLE COURSE_TIME");

                DbReceivePeer.executeQuery("ANALYZE TABLE DB_RECEIVE");
                DbReceivePeer.executeQuery("OPTIMIZE TABLE DB_RECEIVE");

                DbSendPeer.executeQuery("ANALYZE TABLE DB_SEND");
                DbSendPeer.executeQuery("OPTIMIZE TABLE DB_SEND");

		DepartmentPeer.executeQuery("ANALYZE TABLE DEPARTMENT");
                DepartmentPeer.executeQuery("OPTIMIZE TABLE DEPARTMENT");

		EventsPeer.executeQuery("ANALYZE TABLE EVENTS");
                EventsPeer.executeQuery("OPTIMIZE TABLE EVENTS");

		FacultyCoursePeer.executeQuery("ANALYZE TABLE FACULTY_COURSE");
                FacultyCoursePeer.executeQuery("OPTIMIZE TABLE FACULTY_COURSE");

		FacInfoPeer.executeQuery("ANALYZE TABLE FAC_INFO");
                FacInfoPeer.executeQuery("OPTIMIZE TABLE FAC_INFO");

		FaqPeer.executeQuery("ANALYZE TABLE FAQ");
                FaqPeer.executeQuery("OPTIMIZE TABLE FAQ");

		FaqmovePeer.executeQuery("ANALYZE TABLE FAQMOVE");
                FaqmovePeer.executeQuery("OPTIMIZE TABLE FAQMOVE");

		FaqVotePeer.executeQuery("ANALYZE TABLE FAQ_VOTE");
                FaqVotePeer.executeQuery("OPTIMIZE TABLE FAQ_VOTE");

                GlossaryPeer.executeQuery("ANALYZE TABLE GLOSSARY");
                GlossaryPeer.executeQuery("OPTIMIZE TABLE GLOSSARY");

                GlossaryAliasPeer.executeQuery("ANALYZE TABLE GLOSSARY_ALIAS");
                GlossaryAliasPeer.executeQuery("OPTIMIZE TABLE GLOSSARY_ALIAS");

		HintQuestionPeer.executeQuery("ANALYZE TABLE HINT_QUESTION");
		HintQuestionPeer.executeQuery("OPTIMIZE TABLE HINT_QUESTION");

		InstituteAdminRegistrationPeer.executeQuery("ANALYZE TABLE INSTITUTE_ADMIN_REGISTRATION");
                InstituteAdminRegistrationPeer.executeQuery("OPTIMIZE TABLE INSTITUTE_ADMIN_REGISTRATION");

		InstituteAdminUserPeer.executeQuery("ANALYZE TABLE INSTITUTE_ADMIN_USER");
                InstituteAdminUserPeer.executeQuery("OPTIMIZE TABLE INSTITUTE_ADMIN_USER");		

		InstituteProgramPeer.executeQuery("ANALYZE TABLE INSTITUTE_PROGRAM");
                InstituteProgramPeer.executeQuery("OPTIMIZE TABLE INSTITUTE_PROGRAM");

		InstituteQuotaPeer.executeQuery("ANALYZE TABLE INSTITUTE_QUOTA");
                InstituteQuotaPeer.executeQuery("OPTIMIZE TABLE INSTITUTE_QUOTA");

		InstructorPermissionsPeer.executeQuery("ANALYZE TABLE INSTRUCTOR_PERMISSIONS");
                InstructorPermissionsPeer.executeQuery("OPTIMIZE TABLE INSTRUCTOR_PERMISSIONS");

		LecturePeer.executeQuery("ANALYZE TABLE LECTURE");
                LecturePeer.executeQuery("OPTIMIZE TABLE LECTURE");

		LearnerScoPeer.executeQuery("ANALYZE TABLE Learner_sco");
                LearnerScoPeer.executeQuery("OPTIMIZE TABLE Learner_sco");

		MailReceivePeer.executeQuery("ANALYZE TABLE MAIL_RECEIVE");
                MailReceivePeer.executeQuery("OPTIMIZE TABLE MAIL_RECEIVE");

                MailSendPeer.executeQuery("ANALYZE TABLE MAIL_SEND");
                MailSendPeer.executeQuery("OPTIMIZE TABLE MAIL_SEND");

		ModulePermissionPeer.executeQuery("ANALYZE TABLE MODULE_PERMISSION");
                ModulePermissionPeer.executeQuery("OPTIMIZE TABLE MODULE_PERMISSION");

		ModuleTimePeer.executeQuery("ANALYZE TABLE MODULE_TIME");
                ModuleTimePeer.executeQuery("OPTIMIZE TABLE MODULE_TIME");

                NewsPeer.executeQuery("ANALYZE TABLE NEWS");
                NewsPeer.executeQuery("OPTIMIZE TABLE NEWS");

		NotePeer.executeQuery("ANALYZE TABLE NOTE");
                NotePeer.executeQuery("OPTIMIZE TABLE NOTE");

                NoticeReceivePeer.executeQuery("ANALYZE TABLE NOTICE_RECEIVE");
                NoticeReceivePeer.executeQuery("OPTIMIZE TABLE NOTICE_RECEIVE");

                NoticeSendPeer.executeQuery("ANALYZE TABLE NOTICE_SEND");
                NoticeSendPeer.executeQuery("OPTIMIZE TABLE NOTICE_SEND");

		OpenidPeer.executeQuery("ANALYZE TABLE OPENID");
                OpenidPeer.executeQuery("OPTIMIZE TABLE OPENID");

		ProgramPeer.executeQuery("ANALYZE TABLE PROGRAM");
                ProgramPeer.executeQuery("OPTIMIZE TABLE PROGRAM");

		ProxyUserPeer.executeQuery("ANALYZE TABLE PROXY_USER");
		ProxyUserPeer.executeQuery("OPTIMIZE TABLE PROXY_USER");
	
		QuizPeer.executeQuery("ANALYZE TABLE QUIZ");
		QuizPeer.executeQuery("OPTIMIZE TABLE QUIZ");

		RdfPeer.executeQuery("ANALYZE TABLE RDF");
                RdfPeer.executeQuery("OPTIMIZE TABLE RDF");

		RemoteCoursesPeer.executeQuery("ANALYZE TABLE REMOTE_COURSES");
		RemoteCoursesPeer.executeQuery("OPTIMIZE TABLE REMOTE_COURSES");

		RemoteUsersPeer.executeQuery("ANALYZE TABLE REMOTE_USERS");
                RemoteUsersPeer.executeQuery("OPTIMIZE TABLE REMOTE_USERS");

		ResearchRepositoryPeer.executeQuery("ANALYZE TABLE RESEARCH_REPOSITORY");
		ResearchRepositoryPeer.executeQuery("OPTIMIZE TABLE RESEARCH_REPOSITORY");

		RoomPeer.executeQuery("ANALYZE TABLE ROOM");
                RoomPeer.executeQuery("OPTIMIZE TABLE ROOM");

		StudentExpiryPeer.executeQuery("ANALYZE TABLE STUDENT_EXPIRY");
                StudentExpiryPeer.executeQuery("OPTIMIZE TABLE STUDENT_EXPIRY");

		StudentRollnoPeer.executeQuery("ANALYZE TABLE STUDENT_ROLLNO");
                StudentRollnoPeer.executeQuery("OPTIMIZE TABLE STUDENT_ROLLNO");

		SurveyQuestionPeer.executeQuery("ANALYZE TABLE SURVEY_QUESTION");
		SurveyQuestionPeer.executeQuery("OPTIMIZE TABLE SURVEY_QUESTION");

		SurveyResultPeer.executeQuery("ANALYZE TABLE SURVEY_RESULT");
		SurveyResultPeer.executeQuery("OPTIMIZE TABLE SURVEY_RESULT");

		SurveyStudentPeer.executeQuery("ANALYZE TABLE SURVEY_STUDENT");
		SurveyStudentPeer.executeQuery("OPTIMIZE TABLE SURVEY_STUDENT");

                SystemCleantimePeer.executeQuery("ANALYZE TABLE SYSTEM_CLEANTIME");
                SystemCleantimePeer.executeQuery("OPTIMIZE TABLE SYSTEM_CLEANTIME");

                SessionaddressPeer.executeQuery("ANALYZE TABLE SessionAddress");
                SessionaddressPeer.executeQuery("OPTIMIZE TABLE SessionAddress");

		TableIdPeer.executeQuery("ANALYZE TABLE TABLE_ID");
                TableIdPeer.executeQuery("OPTIMIZE TABLE TABLE_ID");

		TaskPeer.executeQuery("ANALYZE TABLE TASK");
		TaskPeer.executeQuery("OPTIMIZE TABLE TASK");

		TelephoneDirectoryPeer.executeQuery("ANALYZE TABLE TELEPHONE_DIRECTORY");
                TelephoneDirectoryPeer.executeQuery("OPTIMIZE TABLE TELEPHONE_DIRECTORY");

		TimetableCourseInfoPeer.executeQuery("ANALYZE TABLE TIMETABLE_COURSE_INFO");
                TimetableCourseInfoPeer.executeQuery("OPTIMIZE TABLE TIMETABLE_COURSE_INFO");

                TurbineGroupPeer.executeQuery("ANALYZE TABLE TURBINE_GROUP");
                TurbineGroupPeer.executeQuery("OPTIMIZE TABLE TURBINE_GROUP");

                TurbinePermissionPeer.executeQuery("ANALYZE TABLE TURBINE_PERMISSION");
                TurbinePermissionPeer.executeQuery("OPTIMIZE TABLE TURBINE_PERMISSION");

                TurbineRolePeer.executeQuery("ANALYZE TABLE TURBINE_ROLE");
                TurbineRolePeer.executeQuery("OPTIMIZE TABLE TURBINE_ROLE");

                TurbineRolePermissionPeer.executeQuery("ANALYZE TABLE TURBINE_ROLE_PERMISSION");
                TurbineRolePermissionPeer.executeQuery("OPTIMIZE TABLE TURBINE_ROLE_PERMISSION");

		TurbineScheduledJobPeer.executeQuery("ANALYZE TABLE TURBINE_SCHEDULED_JOB");
                TurbineScheduledJobPeer.executeQuery("OPTIMIZE TABLE TURBINE_SCHEDULED_JOB");

                TurbineUserPeer.executeQuery("ANALYZE TABLE TURBINE_USER");
                TurbineUserPeer.executeQuery("OPTIMIZE TABLE TURBINE_USER");

                TurbineUserGroupRolePeer.executeQuery("ANALYZE TABLE TURBINE_USER_GROUP_ROLE");
                TurbineUserGroupRolePeer.executeQuery("OPTIMIZE TABLE TURBINE_USER_GROUP_ROLE");

                UsageDetailsPeer.executeQuery("ANALYZE TABLE USAGE_DETAILS");
                UsageDetailsPeer.executeQuery("OPTIMIZE TABLE USAGE_DETAILS");

                UserConfigurationPeer.executeQuery("ANALYZE TABLE USER_CONFIGURATION");
                UserConfigurationPeer.executeQuery("OPTIMIZE TABLE USER_CONFIGURATION");

		UserPrefPeer.executeQuery("ANALYZE TABLE USER_PREF");
                UserPrefPeer.executeQuery("OPTIMIZE TABLE USER_PREF");

		VenuePeer.executeQuery("ANALYZE TABLE VENUE");
                VenuePeer.executeQuery("OPTIMIZE TABLE VENUE");

		}
		catch(Exception ex)
        	{
                	ErrorDumpUtil.ErrorLog("The error in optimize database - CommonUtil class !!"+ex);
	        }

		return true;
        }//end of method

	/**
         * The method put the first login entry of instructor in LMS 
	 * in a day, in the database
         * return boolean
         */

        public static boolean  IFLoginEntry(int uid, java.util.Date date) {
		
	try {
		
	        Vector Instructor_Role=UserGroupRoleUtil.getGID(uid,2);
                if(Instructor_Role.size()!=0){
	                int cdate = Integer.parseInt(ExpiryUtil.getCurrentDate(""));
                        Criteria crit=new Criteria();
                        crit.add(AttendenceSeetPeer.USER_ID,uid);
                        List check=AttendenceSeetPeer.doSelect(crit);
                        int intdate=0;
                        if((check.size()) != 0 ){
                        	String find_max="SELECT MAX(ID) FROM ATTENDENCE_SEET WHERE USER_ID="+uid;
                                List v=AttendenceSeetPeer.executeQuery(find_max);
                                int max_entry=0;
                                for(Iterator j=v.iterator();j.hasNext();){
                                	Record item2=(Record)j.next();
                                        max_entry=item2.getValue("MAX(ID)").asInt();
                                }
                                crit=new Criteria();
                                crit.add(AttendenceSeetPeer.ID,max_entry);
                                List check1=AttendenceSeetPeer.doSelect(crit);
                                for(int i=0;i<check1.size();i++){
	                                AttendenceSeet element=(AttendenceSeet)(check1.get(i));
                                        String date1=(element.getLoginDate()).toString();
                                        date1=date1.substring(0,10);
                                        date1=date1.replaceAll("-","");
                                        intdate=Integer.parseInt(date1);
                                }
                        }
			if((intdate < cdate) || (check.size() == 0)) {
                	        if(check.size() > 30) {

                        	        String find_minimum="SELECT MIN(ID) FROM ATTENDENCE_SEET WHERE USER_ID="+uid;
                                        List v=UsageDetailsPeer.executeQuery(find_minimum);
                                        int least_entry=0;
                                        for(Iterator j=v.iterator();j.hasNext();){
                                        	Record item2=(Record)j.next();
                                                least_entry=item2.getValue("MIN(ID)").asInt();
                                        }
                                        crit=new Criteria();
                                        crit.add(AttendenceSeetPeer.ID,least_entry);
                                        crit.add(AttendenceSeetPeer.USER_ID,uid);
                                        AttendenceSeetPeer.doDelete(crit);
                                 }
                                 else {
                                 	crit=new Criteria();
                                        crit.add(AttendenceSeetPeer.USER_ID,uid);
                                        crit.add(AttendenceSeetPeer.LOGIN_DATE,date);
                                        AttendenceSeetPeer.doInsert(crit);
                                 }
                        }
                 }//if top
           }catch(Exception ex){
		ErrorDumpUtil.ErrorLog("The error in IFLoginEntry() - CommonUtil class !!"+ex);
	   }
	   return true;
	}//end of method
   //-----Method is for the Group Management----------------//
//public static void grpLeader(RunData data)
public static void grpLeader()
{
        try
        {
                RunData data=null;
                String studentname="",stuno="",sname="",grpname="",type="",Cdate="",filepath="",Cdate1="";
                XmlWriter xmlWriter=null;
                Vector str=new Vector();
                TopicMetaDataXmlReader topicmetadata=null;
                Groupmanagement grpAc=new Groupmanagement();
                int grpid[]={1,2};
                Criteria crit=new Criteria();
                crit.addNotIn(TurbineGroupPeer.GROUP_ID,grpid);
                crit.addGroupByColumn(TurbineGroupPeer.GROUP_NAME);
                List w=TurbineGroupPeer.doSelect(crit);
                for(int y=0;y<w.size();y++)
                {//for1
                        TurbineGroup element=(TurbineGroup)w.get(y);
                        String gname=(element.getGroupName()).toString();
                        filepath=TurbineServlet.getRealPath("/Courses")+"/"+gname+"/"+"GroupManagement";
                        File f=new File(filepath+"/Pollexptime__des.xml");
                        if(f.exists())
                        {//exitss
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/Pollexptime__des.xml");
                                Vector Detail=topicmetadata.getGroupDetails();
                                if(Detail!=null)
                                {//detail
                                        for(int k=0;k<Detail.size();k++)
                                        {//for2
                                                grpname=((FileEntry) Detail.elementAt(k)).getName();
                                                type=((FileEntry) Detail.elementAt(k)).gettype();
                                                Cdate=((FileEntry) Detail.elementAt(k)).getPDate();
                                                studentname=((FileEntry) Detail.elementAt(k)).getUserName();
                                                stuno=((FileEntry) Detail.elementAt(k)).getstudentno();
                                                String c_date=ExpiryUtil.getCurrentDate("-");
                                                java.sql.Date Post_date=java.sql.Date.valueOf(c_date);
                                                Cdate1=Post_date.toString();
                                                if(stuno.equals(Cdate1))
                                                {//cond1
                                                        topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+grpname+"__des.xml");
                                                        Vector Detail1=topicmetadata.getGroupDetails();
                                                        if(Detail1!=null)
                                                        {//ifdetail1
                                                        for(int g=0;g<Detail1.size();g++)
                                                                {//for3
                                                                String grpname4=((FileEntry) Detail.elementAt(g)).getName();
                                                                stuno=((FileEntry) Detail1.elementAt(g)).getstudentno();
                                                                if((grpname.equals(grpname4))&& (!stuno.equals("leader")))
                                                                {//cond2
                                                                        int temp=0;
                                                                        for(int n=0;n<Detail1.size();n++)
                                                                        {//for3
                                                                            studentname=((FileEntry) Detail1.elementAt(n)).getUserName();

                                                                                stuno=((FileEntry) Detail1.elementAt(n)).getstudentno();
                                                                                        if(!stuno.equals(""))
                                                                                {
                                                                                                int sno1=Integer.parseInt(stuno);
                                                                                        if(sno1>temp)
                                                                                                {
                                                                                                temp=sno1;
                                                                                                sname=studentname;
                                                                                                }
                                                                                }
                                                                }//for3
                                                                                for(int a=0;a<Detail1.size();a++)
                {//for4
                       Cdate=((FileEntry) Detail1.elementAt(a)).getPDate();
                       type=((FileEntry) Detail1.elementAt(a)).gettype();
                       studentname=((FileEntry) Detail1.elementAt(a)).getUserName();
                       stuno=((FileEntry) Detail1.elementAt(a)).getstudentno();
                           if(!stuno.equals(""))
                               {
                                  int sno2=Integer.parseInt(stuno);
                                  if(sno2==temp)
                                     {
                                         stuno="leader";
                                         xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,grpname);
                                         TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,type,Cdate,studentname,stuno);
                                         xmlWriter.writeXmlFile();
                                         str=grpAc.DeleteEntry(filepath,grpname,studentname,"studel",data);
                                       }
                               }
               }//for4
                             topicmetadata=new TopicMetaDataXmlReader(filepath+"/GroupList__des.xml");
                             String groupdesc1=topicmetadata.getTopicDescription();
                             Vector Detail2=topicmetadata.getGroupDetails();
                                  for(int l=0;l<Detail2.size();l++)
                                       {//for5
                                             String name=((FileEntry) Detail2.elementAt(l)).getName();
                                             type=((FileEntry) Detail2.elementAt(l)).gettype();
                                             Cdate=((FileEntry) Detail2.elementAt(l)).getPDate();
                                             stuno=((FileEntry) Detail2.elementAt(l)).getstudentno();
                                               if(grpname.equals(name))
                                                  {
                                                     xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,"/GroupList__des.xml");
                                                     TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,type,Cdate,sname,stuno);
                                                     xmlWriter.writeXmlFile();
                                                     str=grpAc.DeleteEntry(filepath,"GroupList",grpname,"grpdel",data);
                                                  }
                                             //          ErrorDumpUtil.ErrorLog("name"+grpname);
                                           }//for5
                                                                        //}//cond2
                                                                //}//for3
                                                        //}//ifdetail1
                                                        topicmetadata=new TopicMetaDataXmlReader(filepath+"/PollingList__des.xml");
                                                        Vector Detail3=topicmetadata.getGroupDetails();
                                                        for(int b=0;b<Detail3.size();b++)
                                                        {//for6
                                                                String name=((FileEntry) Detail3.elementAt(b)).getName();
                                                                type=((FileEntry) Detail3.elementAt(b)).gettype();
                                                                Cdate=((FileEntry) Detail3.elementAt(b)).getPDate();
                                                                stuno=((FileEntry) Detail3.elementAt(b)).getstudentno();
                                                                studentname=((FileEntry) Detail3.elementAt(b)).getUserName();
                                                                if(grpname.equals(name))
                                                                {
                                                                   str=grpAc.DeleteEntry(filepath,"PollingList",studentname,"studel",data);
                                                                }
                                                        }//for6
                                                        str=grpAc.DeleteEntry(filepath,"Pollexptime",grpname,"grpdel",data);
                                               //                         ErrorDumpUtil.ErrorLog("name"+grpname);
                                                }
                                        }
                                }
                                                }//cond1
                                        }//for2
                                }//detailif
                        }//if exists
                }//fortable
        }//close try
        catch(Exception ex){
                ErrorDumpUtil.ErrorLog("The error in IFLoginEntry() - CommonUtil class !!"+ex);
           }
        }//close method
                                                                                
	public static Vector PListing( RunData data , Context context , Vector entry,int list_conf )
        {
                                Vector splitlist=new Vector();
                try
                {
                                User user=data.getUser();
                                ParameterParser pp=data.getParameters();
                                int startIndex=pp.getInt("startIndex",0);
                                int t_size=entry.size();

                                int value[]=new int[7];
                                value=ListManagement.linkVisibility(startIndex,t_size,list_conf);

                                int k=value[6];
                                context.put("k",String.valueOf(k));

                                Integer total_size=new Integer(t_size);
                                context.put("total_size",total_size);
                                int eI=value[1];
                                Integer endIndex=new Integer(eI);
                                context.put("endIndex",endIndex);
                                int check_first=value[2];
                                context.put("check_first",String.valueOf(check_first));
                                int check_pre=value[3];
                                context.put("check_pre",String.valueOf(check_pre));
                                int check_last1=value[4];
				context.put("check_last1",String.valueOf(check_last1));
                                int check_last=value[5];
                                context.put("check_last",String.valueOf(check_last));
                                context.put("startIndex",String.valueOf(eI));
                                splitlist=ListManagement.listDivide(entry,startIndex,list_conf);
                                //context.put("entry",splitlist);
                }
                catch(Exception e){data.setMessage("The error in [PListing] of [CommonUtility Util] !!"+e);}
                return splitlist;
    }//method

	/**
	 * This method checks necessary data entered by user
	 * @return Message
	 */
	public static String CheckData(String email,String file)
	{
		String msg="";
		String rollno="";
		int uid=UserUtil.getUID(email);
		//ErrorDumpUtil.ErrorLog("uid in commonutility---------->"+uid);
		Vector Student_Role=UserGroupRoleUtil.getGID(uid,3);
		//ErrorDumpUtil.ErrorLog("Student_Role in commonutility--------->"+Student_Role);
		Criteria crit = new Criteria();
		try
		{
			        /**  getting detail from turbine user & check first name 
			         *  If first name is null then show message
			         * 
				 */
					crit.add(TurbineUserPeer.LOGIN_NAME,email);
					List v = TurbineUserPeer.doSelect(crit);
					TurbineUser element = (TurbineUser)v.get(0);
	                                String name = element.getFirstName();
					if(name.equals(""))
						msg=MultilingualUtil.ConvertedString("brih_alert",file);

				/** if role is student{
			         *   check his rollno & if it is null
			         *   then show message
				 */
				if(Student_Role.size()!=0)
				{
					crit=new Criteria();
	                                crit.add(StudentRollnoPeer.EMAIL_ID,email);
	                                List ve=StudentRollnoPeer.doSelect(crit);
					if(ve.size()!=0)
					{
		                                StudentRollno element1=(StudentRollno)ve.get(0);
		                                rollno=element1.getRollNo();
					}
						if(rollno.equals(""))
						msg=MultilingualUtil.ConvertedString("brih_alert",file);
					
				}									
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The error in check data of[CommonUtility Util] !!"+e);
		}
		return msg;
	}
	
	/** 
  	 * This method perform the search process and return the result
         */

	public static Vector doMySearch(File indexDir, String q, Context context)  throws Exception{
        Vector search=new Vector();
	int cou=0;
	long ti=0;
        Directory fsDir = FSDirectory.getDirectory(indexDir, false);
        IndexSearcher is = new IndexSearcher(fsDir);
        Query query = QueryParser.parse(q, "contents", new StandardAnalyzer());

        long start=new java.util.Date().getTime();
        Hits hits = is.search(query);
        long end=new java.util.Date().getTime();
        cou=cou+hits.length();
        ti=ti+(end-start);
        for (int i = 0; i < hits.length(); i++) {
                Document doc = hits.doc(i);
                search.addElement(doc.get("filename"));
        }
        return search;

   }
	/** 
         * This method write EmailId in file 
	 * This method gives autosuggestion list for EmailId of users in compose mail.
	 * @return String UserFile
         */

	public static String autocomplte(String instituteId,String Role){
		String UserFile="";
		try{
		 	Vector userlist=new Vector();
			String EmailFile="";
			String filepath=TurbineServlet.getRealPath("/scrpts/AutoSuggestUser/UserEmailId");
			File tempFile=new File(filepath);
			
			//Create directory
                	tempFile.mkdirs();
			
			//Get file path according user role
			if(Role.equals("admin"))
			{
                		EmailFile=tempFile+"/adminUser.js";
				UserFile="/scrpts/AutoSuggestUser/UserEmailId/adminUser.js";
				// Get admin user list
				userlist=ListManagement.getUserList();
			}
			else
			{
				EmailFile=tempFile+"/"+instituteId+".js";
				UserFile="/scrpts/AutoSuggestUser/UserEmailId/"+instituteId+".js";
				// Get instiute admin user list
				userlist=ListManagement.getInstituteUserList(instituteId);
			}
			
			// Write file in specific location
			File UserFilepath=new File(EmailFile);
			FileWriter fstream = new FileWriter(UserFilepath);
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write(("userid =["));
			
			// Get UserList from vector
			for(int p=0;p<userlist.size();p++)
			{
				CourseUserDetail cud=(CourseUserDetail)userlist.get(p);
                        	String loginName=cud.getLoginName().toString();
                                if(loginName!=null)
				{
					//write login name in file for email search in mail compose
                                	out.write('"'+loginName+'"'+",");
                                }
                        }
                        out.write(']');
                        //Close the output stream
                        out.close();
                }
                catch (Exception e){//Catch exception if any
                        ErrorDumpUtil.ErrorLog("Exception in getting user list in auto selection--"+e.getMessage());
                }
		return UserFile;
        }

	// Following method added by Priyanka.
	/**
 	 * This method cleans the expired nonce in database,
	 * the nonce expiry time has been set to one hour.
	 * @return boolean
	 */

	 public static boolean removeNonce()
	 {
		Criteria criteria = null;
	        Criteria crit = null;

         	int cnt=0;
        	try{
                	//ErrorDumpUtil.ErrorLog("inside removeNonce");
                	long boundary = System.currentTimeMillis() - ONE_HOUR;
                	crit = new Criteria();
                	List list = OpenidPeer.doSelect(crit);
                	for (Iterator i = list.iterator();i.hasNext() ;)
                	{
                	//	ErrorDumpUtil.ErrorLog("removeNonce");
                        	Openid openid = (Openid) i.next();
                        	long date = openid.getToDate();
			//	ErrorDumpUtil.ErrorLog("date is "+date);
                        	if(date<boundary)
                        	{
                                	String exp_nonce = openid.getNonce();
                          //      	ErrorDumpUtil.ErrorLog("Nonce from db is "+exp_nonce);
                                	String exp_provider = openid.getProvider();
                            //    	ErrorDumpUtil.ErrorLog("Provider from db is "+exp_provider);
                                        criteria = new Criteria();
                                        criteria.add(OpenidPeer.NONCE,exp_nonce);
                                        criteria.add(OpenidPeer.PROVIDER,exp_provider);
                                        OpenidPeer.doDelete(criteria);
                                        cnt++;
                              //  	ErrorDumpUtil.ErrorLog("count is "+cnt);
                                }//if
			}//for
		}//try
        	catch(Exception e)
        	{
               		ErrorDumpUtil.ErrorLog("Error in removeNonce() of CommonUtility  "+e);
                	throw new RuntimeException("error cleaning up client nonce from table ");
        	}//catch
		return true;
	}//method removeNonce()


	public static String GetBrihVideoId(String path,String key) throws Exception{
                InputStream f = new FileInputStream(path);
                Properties p = new Properties();
                 p.load(f);
                 String val = p.getProperty(key);
                 String[] link_Id1= val.split("v=");
                 String[] link_Id2= link_Id1[1].split("&");
                //return(val);
                 return(link_Id2[0]);
         }
	
	/**
         * This method remove entry of emailId from file,
         * and update periodically(15 days after file creation date),
         * This method help to update entry of User Email id for auto search for Email Compose.
         * @return boolean
         */

        public static boolean removeNupdateEmailUser()
        {
                try{
                        // Get Current date using Util date and convert into sql date.
                        java.util.Date TempCurrentDate = new java.util.Date();
                        java.sql.Date CurrentDate= new java.sql.Date(TempCurrentDate.getTime());
                        
			// Get FilePath of User email List.
                        File UserFile = new File(TurbineServlet.getRealPath("/scrpts/AutoSuggestUser/UserEmailId"));
                        
			// Get list of files from UserEmailId folder.
                        String[] listOfFiles = UserFile.list();
                        if(listOfFiles.length>0){
                                
				//Get modification date of all User Email-Id File within folder
                                for (int i=0; i<listOfFiles.length; i++){
                                        String tempfilenm = listOfFiles[i];
                                        File EmailIdFile=new File(UserFile+"/"+tempfilenm);
                                       
					 //Get Last modified date of file.
                                        long lastModifiedDate = EmailIdFile.lastModified();
                                        
					/**
                                         * Create a new date object and pass last modified information
                                         * to the date object
                                         * Get last file-modification date.
                                         */
                                        
					Date tempdate = new Date(lastModifiedDate);
                                        java.sql.Date fileMdate= new java.sql.Date(tempdate.getTime());
                                        String fileMdate1=fileMdate.toString();
                                        
					// calculate expiry date of file periodically(15 days).
                                        String E_date=ExpiryUtil.getExpired(fileMdate1,15);
                                        java.sql.Date FileExpdate= java.sql.Date.valueOf(E_date);
                                        
					// compare file expiry date and current date.
                                        if(CurrentDate.compareTo(FileExpdate) >= 0)  {
                                                //Delete file
                                                EmailIdFile.delete();
                                        }
                                }
                        }
                }
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("error in getting file modification date : "+e);
                }
                return true;
        }
	 /**
         * This method remove shutdown.properties file after Expity Time
         * This method also return shutdown message for showing in template.
         * @return string
         */
	
	public static String removeShutDownNotice(String ShutdNoticePath){
		String Shutdownnotice="";
		try{
			//Get File path Shutdown.properties
			File ExistShutdFile=new File(ShutdNoticePath);
			//Check file exist.
                        if(ExistShutdFile.exists()){
                        /**
			* Get Current time using SimpleDateFormat.
			* Get Expity datetime from Properties file
			* Get Current system datetime.
			*/
                        Date CurrentDateTime = new Date();
                        String TempShutdownExpDate = AdminProperties.getValue(ShutdNoticePath,"brihaspati.admin.ShutdownExpDate.value");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date ShutdownExpDateTime=dateFormat.parse(TempShutdownExpDate);
			/**
			 * Compare System DateTime from File Expiry DateTime
			 * If System DateTime is equal to File Expiry DateTime then delete existing file.
			 * else return Message of Shutdown notices.
			 */
                        if(CurrentDateTime.compareTo(ShutdownExpDateTime)>=0){
                        	//Delete file
                                ExistShutdFile.delete();
                       		}
                        else {
				// Get Shutdown Message form Properties file.
                        	Shutdownnotice = AdminProperties.getValue(ShutdNoticePath,"brihaspati.admin.ShutdownHeading.value");
                        	}
                  	}
		}
		catch(Exception e){
			ErrorDumpUtil.ErrorLog("error in removing Shutdown notice and return message : "+e);
		}
		// Return Message
		return Shutdownnotice;
	}

	/**
 	 * This method calculates the total space
 	 * used by all the courses of a particular 
 	 * institute and updates the same in
 	 * the database
	 */
	public static boolean setQuota()
	{
		boolean quota=false;
		Criteria crit = new Criteria();
		int inst_id;
		long inst_quota;
		int l, i, j, k, inst;
		//String group_name[], courses[];
		long size=0, avail_size=0;
		File dir;

		try{
			String path = TurbineServlet.getRealPath("/Courses");
//                        ErrorDumpUtil.ErrorLog("Path of courses folder "+path);
			File file = new File(path);
                        String[] contents = file.list();
//			ErrorDumpUtil.ErrorLog("Size of contents in Courses folder "+contents.length);

			//Getting list of all courses
			crit.addGroupByColumn(TurbineGroupPeer.GROUP_NAME);
			List l2 = TurbineGroupPeer.doSelect(crit);
			l=l2.size();
//			ErrorDumpUtil.ErrorLog("111111");
			String group_name[] = new String[l];
			
			for(i=0;i<l;i++)
			{
				group_name[i] = ((TurbineGroup)l2.get(i)).getGroupName();
//				ErrorDumpUtil.ErrorLog("RRRRR "+group_name[i]);
			}
			
			//Getting id of all institutes
			crit = new Criteria();
			crit.addGroupByColumn(InstituteQuotaPeer.INSTITUTE_ID);
                        List list = InstituteQuotaPeer.doSelect(crit);
                        l=list.size();
//			ErrorDumpUtil.ErrorLog("Value of l "+l);
                        //int inst_id[] = new int[l];
                        //Getting quota alloted to all institutes

			/**
 			 * Computing the available space for 
 			 * each institute, on the basis of list
 			 * fetched from Institute_Quota table
 			 */
                        for(i=0; i<l; i++)
                        {
                                inst_id = ((InstituteQuota)list.get(i)).getInstituteId();
  //                              ErrorDumpUtil.ErrorLog("SSSSS "+inst_id);
				crit = new Criteria();
				crit.add(InstituteQuotaPeer.INSTITUTE_ID,inst_id);
                        	List list1 = InstituteQuotaPeer.doSelect(crit);
				BigDecimal uquota =((InstituteQuota)list1.get(0)).getInstituteAquota();
				inst_quota = uquota.longValue();
//				ErrorDumpUtil.ErrorLog("TTTTT "+inst_quota);

				/**
 				 * Getting courses from the list
 				 * fetched from Turbine_Group table
 				 * that belongs to "this" institute.
 				 */
				for(j=0; j<group_name.length; j++)
				{
					k= group_name[j].lastIndexOf("_");
				//	ErrorDumpUtil.ErrorLog("Value of group_name[j] "+group_name[j]);
					if(!group_name[j].substring(k+1).equals("admin"))
					{
						if(k>0)
						{
                                        		inst = Integer.parseInt(group_name[j].substring(k+1));
							if(inst==inst_id)
							{
								/**
 								 * Getting the size of each course
 								 * of "this" particular Institute
 								 * In future, we'll compute user space
 								 * as well.
 								 */
								for(k=0; k<contents.length; k++)
								{
									if(contents[k].contains(group_name[j]))
									{
										dir = new File(path+"/"+contents[k]);
				//						ErrorDumpUtil.ErrorLog("Value of dir "+dir);
                        							size = size+QuotaUtil.getDirSizeInMegabytes(dir);		
				//						ErrorDumpUtil.ErrorLog("Size in mb "+size);
									}  	
								}//for3
							}//if3	
						}//if2
					}//if1													
				}//for2
			
				/**
 				 * Changing size from Mb to Gb
				 */
				size=size/1024;
				//ErrorDumpUtil.ErrorLog("Total used space "+size);
				/**
 				 * Updating Institute_Quota table
 				 * with the computed value of used space
				 */
				crit.add(InstituteQuotaPeer.INSTITUTE_ID,inst_id);
                                crit.add(InstituteQuotaPeer.INSTITUTE_AQUOTA,size);
                                InstituteQuotaPeer.doUpdate(crit);

			}//for1

						
			quota=true;
		}
		catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("Error inside CommonUtility: setQuota "+e);
                }       
                return quota;
	}
	 public static void updatePoll(){
                try{
                        /**
                        *This method uses outdated polls
                        *prints the txt  document to file

                        */
                        java.util.Date date_Now = new java.util.Date();
                        Criteria crit_upDate=new Criteria();
                        crit_upDate.add(PollPeer.VALID_TILL,(Object)date_Now,crit_upDate.LESS_EQUAL);
                        PollPeer.doDelete(crit_upDate);
                }
                catch(Exception ez){
                 ErrorDumpUtil.ErrorLog("error in update poll : "+ez);
                }
        }




//Add method
}//end of class

