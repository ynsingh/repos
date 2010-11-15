package org.iitk.brihaspati.modules.utils;


/*@(#)CommonUtility.java
 *  Copyright (c) 2005-2008,2010 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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

import java.util.List;
import java.util.Vector;
import java.lang.reflect.Array;
import java.util.StringTokenizer;
import java.util.Iterator;

import com.workingdogs.village.Record;

import java.sql.Date;

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
import org.iitk.brihaspati.om.CalInformationPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.GlossaryAliasPeer;
import org.iitk.brihaspati.om.GlossaryPeer;
import org.iitk.brihaspati.om.HintQuestionPeer;
import org.iitk.brihaspati.om.LecturePeer;
import org.iitk.brihaspati.om.MailReceivePeer;
import org.iitk.brihaspati.om.MailSendPeer;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.ProxyUserPeer;
import org.iitk.brihaspati.om.QuizPeer;
import org.iitk.brihaspati.om.RemoteCoursesPeer;
import org.iitk.brihaspati.om.ResearchRepositoryPeer;
import org.iitk.brihaspati.om.SessionaddressPeer;
import org.iitk.brihaspati.om.SurveyQuestionPeer;
import org.iitk.brihaspati.om.SurveyResultPeer;
import org.iitk.brihaspati.om.SurveyStudentPeer;
import org.iitk.brihaspati.om.SystemCleantimePeer;
import org.iitk.brihaspati.om.SystemCleantime;
import org.iitk.brihaspati.om.TaskPeer;
import org.iitk.brihaspati.om.Task;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.TurbineGroup;
import org.iitk.brihaspati.om.TurbinePermissionPeer;
import org.iitk.brihaspati.om.TurbineRolePeer;
import org.iitk.brihaspati.om.TurbineRolePermissionPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;

import org.iitk.brihaspati.modules.actions.UploadAction;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.actions.Groupmanagement;

/**
 * This class is used for call the method in mylogin 
 * like Create index for Search, Clean the system 
 * 
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:kalpanagtm@gmail.com">Kalpana Gautam</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date:09-11-2010
 * @version 1.0
 * @since 1.0
 * @see ExpiryUtil
 */
public class CommonUtility{
	/**
	 * This method create the index of course
	 * @return boolean 
	 */
	public static boolean CreateIndex(){

	try{
		File f1=new File(TurbineServlet.getRealPath("/Courses"));
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
        		        File f2=new File(s+"/Courses/"+arr[i]+"_Index");
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
		                StringTokenizer st=new StringTokenizer(arr[i],"_");
                		if(st.hasMoreTokens()){
			                String ne=st.nextToken();
			                CreateIndexer.StartIndex(TurbineServlet.getRealPath("/Courses")+"/"+arr[i],TurbineServlet.getRealPath("/Courses")+"/"+ne);
		                }
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
                Date current_date=Date.valueOf(c_date);
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
                        	boolean Expiry_Success=ExpiryUtil.Expiry();
			//	UpdateInfoMail.checknWriteXml();
		//		String  Update_Mail = UpdateInfoMail.getUpdationMail();
                                if(Expiry_Success=true){//3 if
                                	/**
                                        *Code for update the clean date in database
                                        */
					
                                	crit=new Criteria();
                                        crit.add(SystemCleantimePeer.ID,1);
                                        crit.add(SystemCleantimePeer.CLEAN_DATE,current_date);
                                        SystemCleantimePeer.doUpdate(crit);
                                }//end of if 3 loop
				boolean CI=CreateIndex();
				boolean OT=optimizeTables();
				boolean ADB=autoDeletebackup();
				grpLeader();

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
			int seqno=((Task)v.get(i)).getSeqNo();
                        TaskDetail tDetail=new TaskDetail();
                        tDetail.setTask_Id(tid);
                        tDetail.setTitle(Title);
			tDetail.setSeqNumber(seqno);
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

                CalInformationPeer.executeQuery("ANALYZE TABLE CAL_INFORMATION");
                CalInformationPeer.executeQuery("OPTIMIZE TABLE CAL_INFORMATION");

                CoursesPeer.executeQuery("ANALYZE TABLE COURSES");
                CoursesPeer.executeQuery("OPTIMIZE TABLE COURSES");

                DbReceivePeer.executeQuery("ANALYZE TABLE DB_RECEIVE");
                DbReceivePeer.executeQuery("OPTIMIZE TABLE DB_RECEIVE");

                DbSendPeer.executeQuery("ANALYZE TABLE DB_SEND");
                DbSendPeer.executeQuery("OPTIMIZE TABLE DB_SEND");

                GlossaryPeer.executeQuery("ANALYZE TABLE GLOSSARY");
                GlossaryPeer.executeQuery("OPTIMIZE TABLE GLOSSARY");

                GlossaryAliasPeer.executeQuery("ANALYZE TABLE GLOSSARY_ALIAS");
                GlossaryAliasPeer.executeQuery("OPTIMIZE TABLE GLOSSARY_ALIAS");

		HintQuestionPeer.executeQuery("ANALYZE TABLE HINT_QUESTION");
		HintQuestionPeer.executeQuery("OPTIMIZE TABLE HINT_QUESTION");

//		MailReceivePeer.executeQuery("ANALYZE TABLE ID_TABLE");
//              MailReceivePeer.executeQuery("OPTIMIZE TABLE ID_TABLE");

		LecturePeer.executeQuery("ANALYZE TABLE LECTURE");
                LecturePeer.executeQuery("OPTIMIZE TABLE LECTURE");

		MailReceivePeer.executeQuery("ANALYZE TABLE MAIL_RECEIVE");
                MailReceivePeer.executeQuery("OPTIMIZE TABLE MAIL_RECEIVE");

                MailSendPeer.executeQuery("ANALYZE TABLE MAIL_SEND");
                MailSendPeer.executeQuery("OPTIMIZE TABLE MAIL_SEND");

                NewsPeer.executeQuery("ANALYZE TABLE NEWS");
                NewsPeer.executeQuery("OPTIMIZE TABLE NEWS");

                NoticeReceivePeer.executeQuery("ANALYZE TABLE NOTICE_RECEIVE");
                NoticeReceivePeer.executeQuery("OPTIMIZE TABLE NOTICE_RECEIVE");

                NoticeSendPeer.executeQuery("ANALYZE TABLE NOTICE_SEND");
                NoticeSendPeer.executeQuery("OPTIMIZE TABLE NOTICE_SEND");

		ProxyUserPeer.executeQuery("ANALYZE TABLE PROXY_USER");
		ProxyUserPeer.executeQuery("OPTIMIZE TABLE PROXY_USER");
	
		QuizPeer.executeQuery("ANALYZE TABLE QUIZ");
		QuizPeer.executeQuery("OPTIMIZE TABLE QUIZ");

		RemoteCoursesPeer.executeQuery("ANALYZE TABLE REMOTE_COURSES");
		RemoteCoursesPeer.executeQuery("OPTIMIZE TABLE REMOTE_COURSES");

		ResearchRepositoryPeer.executeQuery("ANALYZE TABLE RESEARCH_REPOSITORY");
		ResearchRepositoryPeer.executeQuery("OPTIMIZE TABLE RESEARCH_REPOSITORY");

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

		TaskPeer.executeQuery("ANALYZE TABLE TASK");
		TaskPeer.executeQuery("OPTIMIZE TABLE TASK");

                TurbineGroupPeer.executeQuery("ANALYZE TABLE TURBINE_GROUP");
                TurbineGroupPeer.executeQuery("OPTIMIZE TABLE TURBINE_GROUP");

                TurbinePermissionPeer.executeQuery("ANALYZE TABLE TURBINE_PERMISSION");
                TurbinePermissionPeer.executeQuery("OPTIMIZE TABLE TURBINE_PERMISSION");

                TurbineRolePeer.executeQuery("ANALYZE TABLE TURBINE_ROLE");
                TurbineRolePeer.executeQuery("OPTIMIZE TABLE TURBINE_ROLE");

                TurbineRolePermissionPeer.executeQuery("ANALYZE TABLE TURBINE_ROLE_PERMISSION");
                TurbineRolePermissionPeer.executeQuery("OPTIMIZE TABLE TURBINE_ROLE_PERMISSION");

                TurbineUserPeer.executeQuery("ANALYZE TABLE TURBINE_USER");
                TurbineUserPeer.executeQuery("OPTIMIZE TABLE TURBINE_USER");

                TurbineUserGroupRolePeer.executeQuery("ANALYZE TABLE TURBINE_USER_GROUP_ROLE");
                TurbineUserGroupRolePeer.executeQuery("OPTIMIZE TABLE TURBINE_USER_GROUP_ROLE");

                UsageDetailsPeer.executeQuery("ANALYZE TABLE USAGE_DETAILS");
                UsageDetailsPeer.executeQuery("OPTIMIZE TABLE USAGE_DETAILS");

                UserConfigurationPeer.executeQuery("ANALYZE TABLE USER_CONFIGURATION");
                UserConfigurationPeer.executeQuery("OPTIMIZE TABLE USER_CONFIGURATION");
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
                                                       ErrorDumpUtil.ErrorLog("name"+grpname);
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
                                                                        ErrorDumpUtil.ErrorLog("name"+grpname);
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
                                                                                
     public static void PListing( RunData data , Context context , Vector entry)
        {
                try
                {
                                User user=data.getUser();
                                ParameterParser pp=data.getParameters();
				String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
		                String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                int list_conf=Integer.parseInt(conf);

                                context.put("userConf",new Integer(list_conf));
                                context.put("userConf_string",conf);

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
                                Vector splitlist=ListManagement.listDivide(entry,startIndex,list_conf);
                                context.put("entry",splitlist);
                }
                catch(Exception e){data.setMessage("The error in [PListing] of [CommonUtility Util] !!"+e);}
    }//method

	/**
	 * This method checks necessary data entered by user
	 * @return Message
	 */
	public static String CheckData(String role,String email,String file)
	{
		String msg="";
		String rollno="";
		Criteria crit = new Criteria();
		try
		{
				/** if role is instructor{
			         *  getting detail from turbine user & check first name 
			         *  If first name is null then show message
			         * }
				 */
				if(role.equals("instructor"))
				{
					crit.add(TurbineUserPeer.LOGIN_NAME,email);
					List v = TurbineUserPeer.doSelect(crit);
					TurbineUser element = (TurbineUser)v.get(0);
	                                String name = element.getFirstName();
					if(name.equals(""))
						msg=MultilingualUtil.ConvertedString("brih_alert",file);
				}

				/** if role is student{
			         *   check his rollno & if it is null
			         *   then show message
				 */
				if(role.equals("student"))
				{
					crit=new Criteria();
	                                crit.add(StudentRollnoPeer.EMAIL_ID,email);
	                                List v=StudentRollnoPeer.doSelect(crit);
					if(v.size()!=0)
					{
		                                StudentRollno element=(StudentRollno)v.get(0);
		                                rollno=element.getRollNo();
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
		
}//end of class
