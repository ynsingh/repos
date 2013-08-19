package org.iitk.brihaspati.modules.utils;

/*
 * @(#)TopicMetaDataXmlReader.java
 *
 *  Copyright (c) 2005-2008,2009-10,2013 ETRG,IIT Kanpur.
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */
import java.io.File;
import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.iitk.brihaspati.modules.utils.XmlData;
import org.iitk.brihaspati.modules.utils.XmlReader;
import org.xml.sax.helpers.AttributesImpl;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
/**
 * This class Read Xml file with attributes and values
 * @author <a href="mailto:ammuamit@hotmail.com">Amit Joshi</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @modify 20-03-2009
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @modify 20-08-2010
 * @author: <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
 * @author: <a href="mailto:kishore.shukla@gmail.com">Kishore kumar shukla</a>
 * @author: <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modify 23-12-2010
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modify date: 09-08-2012 (Priyanka),07-02-2013,10-06-2013
 */

public class TopicMetaDataXmlReader
{
	XmlReader xr;
	/**
        * This method create XmlReader type object 
        * @param file String
        */
	public TopicMetaDataXmlReader(String file) throws Exception
	{
			xr=new XmlReader(file);
	}
	/**
        * This method get details of Topic Description 
        * @return String
        */
		
	public String getTopicDescription()
	{
		XmlData xmlDesc=xr.getElement("Desc",0);
		return xmlDesc.getData();
	}
	public String getActivity()
        {
                XmlData xmlactivity=xr.getElement("activity",0);
                return xmlactivity.getData();
        }

	/**
        * This method get all details of file element 
        * @return Vector
        */

	public Vector getFileDetails()
	{
			Vector vt=new Vector();
		try
		{	
		XmlData files[]=xr.getElements("File");
	//	ErrorDumpUtil.ErrorLog("fls in util"+files);
		if(files!=null)
		{
			Attributes ats;
			String fileName,alias;
			for(int j=0;j<files.length;j++)
			{
			//	ErrorDumpUtil.ErrorLog("vt return vale======"+"gfjgfdjsgfjdfghj");
				FileEntry fileEntry=new FileEntry();	
				ats=files[j].getAttributes();
				fileName=ats.getValue("name");
				alias=ats.getValue("alias");
				String dateString=ats.getValue("publishingDate");
			//	ErrorDumpUtil.ErrorLog("pbdate======"+dateString);

				fileEntry.setName(fileName);
				fileEntry.setAlias(alias);
				fileEntry.setPDate(dateString);
				
				vt.add(fileEntry);
			//	ErrorDumpUtil.ErrorLog("vt return vale======"+vt);
			}
			return vt;
		}
		
		}catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 112::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
			//return null;
		}
	return null;	
	}

	/**
        * This method get all details of file element for Permission
        * @return Vector
        */
	public Vector getDetails(){
	try
	{
	 XmlData file[]=xr.getElements("Topic");
                if(file!=null)
                {
                        Vector v=new Vector();
                        Attributes ats;
                        String TopicName,UserName,CourseName,Role;
                        for(int j=0;j<file.length;j++)
                        {
			
                                FileEntry fileEntry=new FileEntry();
                                ats=file[j].getAttributes();
                                TopicName=ats.getValue("topicname");
                                UserName=ats.getValue("username");
                                CourseName=ats.getValue("coursename");
                                Role=ats.getValue("role");
                                fileEntry.setTopic(TopicName);
                                fileEntry.setUserName(UserName);
                                fileEntry.setCourseName(CourseName);
                                fileEntry.setRole(Role);
                                v.addElement(fileEntry);
                        }
                        return v;
                }
                }catch(Exception e){ 
			 ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 149::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
	//		return null ;
		}
        return null;
        }
	
	/**
        *This method get all details of group manangement
        *@return Vector
        */
        public Vector getGroupDetails()
        {
                        Vector vg=new Vector();
                try
                {
                XmlData files[]=xr.getElements("Group");
                if(files!=null)
                {
                        Attributes ats;
			String StudfullName="";
			String firstName=" ";
			String lastName=" ";
                        String GroupName,type,CreationDate,studentname,studentno;
                        for(int j=0;j<files.length;j++)
                        {
                                FileEntry fileEntry=new FileEntry();
                                ats=files[j].getAttributes();
                                GroupName=ats.getValue("name");
                                type=ats.getValue("type");
                                CreationDate=ats.getValue("CreationDate");
                                studentname=ats.getValue("studentname");
                                studentno=ats.getValue("studentno");
				/*
				* Get student's userid using studnt LoginId(Email Id).
				* Get student's FullName using userId.
				* @UserUtil
				*/
				int userid = UserUtil.getUID(studentname);
				String tempStudfullName = UserUtil.getFullName(userid);
				// Check student name, if LoginId or EmailId
				// if equal to login/Email Id then set blank
				if(tempStudfullName.equals(studentname)){
				StudfullName="";
				}
				// else set student full name 
				else{
				StudfullName=tempStudfullName;
				}
                                fileEntry.setName(GroupName);
                                fileEntry.settype(type);
                                fileEntry.setPDate(CreationDate);
                                fileEntry.setUserName(studentname);
                                fileEntry.setstudentno(studentno);
                                fileEntry.setStudentFullName(StudfullName);
                                vg.add(fileEntry);
                        }
		 }

                }catch(Exception e)
                {
			 ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 192::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
//                        return null;
                }
         return vg;
        //return null;
        }

	/**
        * This method get all file names
        * @return String[]
        */

	public String[] getFileNames()
	{
		try
		{
		XmlData files[]=xr.getElements("File");
		if(files!=null)
		{
			Attributes ats;
			String temp[]=new String[files.length];
			for(int j=0;j<files.length;j++)
			{
				ats=files[j].getAttributes();
			//	ErrorDumpUtil.ErrorLog("ats The exception in xmlreaderutil in line 222::"+ats);
				temp[j]=ats.getValue("name");
			}
			return temp;
		}
		}catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 221::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");

		//return null;
		}
		return null;	
	}
	
	/**
        * This method get all Topic names
        * @return String[]
        */
	public String[] getTopicNames()
        {
                try
                {
                XmlData files[]=xr.getElements("Topic");
                if(files!=null)
                {
                        Attributes ats;
                        String temp[]=new String[files.length];
                        for(int j=0;j<files.length;j++)
                        {
				
				
                                ats=files[j].getAttributes();
                                temp[j]=ats.getValue("topicname");
			}
                        return temp;
                }
                }catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 251::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
//		return null;
		}
                return null;
        }
	
	public String[] getNames()
        {
                try
                {
                XmlData files[]=xr.getElements("File");
                if(files!=null)
                {
                        Attributes ats;
                        String temp[]=new String[files.length];
                        for(int j=0;j<files.length;j++)
                        {
				
				
                                ats=files[j].getAttributes();
                                temp[j]=ats.getValue("name");
			}
                        return temp;
                }
                }catch(Exception e){
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 251::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
//		return null;
		}
                return null;
        }
	
	/**
        * This method get all Details of Questions in Quiz
        * @return Vector
        */

	public Vector getQuizDetails()
        {
                Vector vt=new Vector();
                try
                {
                XmlData files[]=xr.getElements("File");
                if(files!=null)
                {
                        Attributes ats;
                        String optionA,optionB,optionC,optionD,instructorans,Typeofquestion,question,questionid,marksperqustion;
                        for(int j=0;j<files.length;j++)
                        {
                                FileEntry fileEntry=new FileEntry();
                                ats=files[j].getAttributes();
                                optionA=ats.getValue("optionA");
                                optionB=ats.getValue("optionB");
                                optionC=ats.getValue("optionC");
                                optionD=ats.getValue("optionD");
                                instructorans=ats.getValue("instructorans");
                                Typeofquestion=ats.getValue("Typeofquestion");
                                question=ats.getValue("question");
                                questionid=ats.getValue("questionid");
                                marksperqustion=ats.getValue("marksperqustion");

                                fileEntry.setoptionA(optionA);
                                fileEntry.setoptionB(optionB);
                                fileEntry.setoptionC(optionC);
                                fileEntry.setoptionD(optionD);
                                fileEntry.setinstructorans(instructorans);
                                fileEntry.setTypeofquestion(Typeofquestion);
                                fileEntry.setquestion(question);
                                fileEntry.setquestionid(questionid);
                                fileEntry.setmarksperqustion(marksperqustion);
                                vt.add(fileEntry);
                        }
                        return vt;
                }
                }
                catch(Exception e)
                {
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 302::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");

			//return null;
		}
        return null;
        }
	
	/**
        * This method get all Details of Assignment result for user
        * @return Vector
        */
	public Vector getAssignmentDetails()
        {
                Vector vt=new Vector();
                try
                {
                XmlData files[]=xr.getElements("File");
                if(files!=null)
                {
                        Attributes ats;
                        String fileName,grade,Duedate,UserName;
                        for(int j=0;j<files.length;j++)
                        {

                                FileEntry fileEntry=new FileEntry();
                                ats=files[j].getAttributes();
                                fileName=ats.getValue("fileName");
                                grade=ats.getValue("grade");
                                Duedate=ats.getValue("Duedate");
                                UserName=ats.getValue("username");

                                fileEntry.setfileName(fileName);
                                fileEntry.setGrade(grade);
                                fileEntry.setDuedate(Duedate);
                                fileEntry.setUserName(UserName);
                                vt.add(fileEntry);

                        }
                        return vt;
                }

                }catch(Exception e)
                {
                        return null;
                }
        return null;
        }

	/**
        * This method get all Details of Quiz result for user
        * @return Vector
        */

        public Vector getAssignmentDetails1()
        {
                Vector vt=new Vector();
                try
                {
	//		ErrorDumpUtil.ErrorLog("345::=====");
                XmlData files[]=xr.getElements("File");
                if(files!=null)
                {
                        Attributes ats;
                        //String userid,grade,UserName,feedback;
                        String grade,UserName,feedback;
                        for(int j=0;j<files.length;j++)
                        {
                                FileEntry fileEntry=new FileEntry();
                                ats=files[j].getAttributes();
                                //userid=ats.getValue("userid");
                                UserName=ats.getValue("username");
                                grade=ats.getValue("grade");
                                feedback=ats.getValue("feedback");
                                //fileEntry.setfileName(userid);
                                fileEntry.setUserName(UserName);
                                fileEntry.setGrade(grade);
                                fileEntry.setfeedback(feedback);
                                vt.add(fileEntry);

                        }
                        return vt;
                }

                }catch(Exception e)
                {
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 345::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
                        //return null;
                }
        return null;
        //return 0;
        }
 	
	/**
        *This method get all details of ResearchRepository
        *@return Vector
        */
        public Vector getResearchRepositoryDetails()
        {
                        Vector vt=new Vector();
                try
                {
                	XmlData files[]=xr.getElements("Repository");
                	if(files!=null)
                	{
                        	Attributes ats;
                        	String RepositoryName,username,joineduser,NumberofPost,CreationDate;
                        	for(int j=0;j<files.length;j++)
                        	{
                                	FileEntry fileEntry=new FileEntry();
                                	ats=files[j].getAttributes();
                                	RepositoryName=ats.getValue("name");
                                	username=ats.getValue("username");
                                	joineduser=ats.getValue("joineduser");
                                	NumberofPost=ats.getValue("NumberofPost");
                                	CreationDate=ats.getValue("CreationDate");

                                	fileEntry.setTopic(RepositoryName);
                                	fileEntry.setUserName(username);
                                	fileEntry.setName(joineduser);
                                	fileEntry.setstudentno(NumberofPost);
                                	fileEntry.setPDate(CreationDate);
                                	vt.add(fileEntry);
                        	}
                        	return vt;
                 	}
		}
		catch(Exception e)
                {
                         ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 192::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
//                        return null;
                }
        return null;
        }
	 
	/**
         *This method get all details of Online user registration request
         *@return Vector
         */

	public Vector getOnlineUserDetails(){
	Vector v=new Vector();
        try
        {
         XmlData file[]=xr.getElements("File");
                if(file!=null)
                {
                        Attributes ats;
                        String uname,passwd,fname,lname,email,gname,roleName,registerationDate,rollno,program, instAdminName,flag,activation;
                        for(int j=0;j<file.length;j++)
                        {

                                CourseUserDetail fileEntry=new CourseUserDetail();
                                ats=file[j].getAttributes();

                                uname=ats.getValue("uname");
                                passwd=ats.getValue("passwd");
				fname=ats.getValue("fname");
				lname=ats.getValue("lname");
				//orgtn=ats.getValue("orgtn");	
                                email=ats.getValue("email");
                                gname=ats.getValue("gname");
                                roleName=ats.getValue("roleName");
                                registerationDate =ats.getValue("registerationDate");
                                rollno =ats.getValue("rollno");
				program = ats.getValue("program");
				instAdminName = ats.getValue("instAdminName");
				//For Confirmation purpose
				flag = ats.getValue("flag");
				activation = ats.getValue("actionKey");
                                fileEntry.setLoginName(uname);
                                fileEntry.setActive(passwd);
				fileEntry.setInstructorName(fname);
				fileEntry.setUserName(lname);
				//fileEntry.setDept(orgtn);
                                fileEntry.setEmail(email);
                                fileEntry.setGroupName(gname);
                                fileEntry.setRoleName(roleName);
				fileEntry.setCreateDate(registerationDate);
				fileEntry.setRollNo(rollno);
				fileEntry.setPrgCode(program);
				fileEntry.setInstAdminName(instAdminName);
				fileEntry.setFlag(flag);
				fileEntry.setActivation(activation);
                                v.addElement(fileEntry);
                        }
                        return v;
                }
                }catch(Exception e){ return null ;}
        return null;
        }
	 
	/**
         *This method get all details of Online course registration request
         *@return Vector
         */

	public Vector getOnlineCourseDetails(){
        try
        {
         XmlData file[]=xr.getElements("File");
                if(file!=null)
                {
                        Vector v=new Vector();
                        Attributes ats;
                        String gname,cname,uname,email,fname,lname,registerationDate,instid,flag,activation,dept,scname;
                        for(int j=0;j<file.length;j++)
                        {

                                CourseUserDetail fileEntry=new CourseUserDetail();
                                ats=file[j].getAttributes();

                                gname=ats.getValue("gname");
                                cname=ats.getValue("cname");
                                uname=ats.getValue("uname");
				//orgtn=ats.getValue("orgtn");
                                email=ats.getValue("email");
                                fname=ats.getValue("fname");
                                lname=ats.getValue("lname");
                                registerationDate=ats.getValue("registerationDate");
				instid=ats.getValue("instituteid");
				//For confirmation purpose
				flag = ats.getValue("flag");
                                activation = ats.getValue("actionKey");
				//Get department and school/center name from xml file
                                dept = ats.getValue("dept");
                                scname = ats.getValue("sch_center");

				int InstId=Integer.parseInt(instid);
                                fileEntry.setGroupName(gname);
                                fileEntry.setCourseName(cname);
                                fileEntry.setLoginName(uname);
				//fileEntry.setOrganisation(orgtn);
                                fileEntry.setEmail(email);
                                fileEntry.setInstructorName(fname);
                                fileEntry.setUserName(lname);
				fileEntry.setCreateDate(registerationDate);
				fileEntry.setUserName(lname);
				fileEntry.setInstId(InstId);
				fileEntry.setFlag(flag);
                                fileEntry.setActivation(activation);
                                fileEntry.setDept(dept);
                                fileEntry.setSchoolCenter(scname);
                                v.addElement(fileEntry);
                        }
                        return v;
                }
                }catch(Exception e){ return null ;}
        return null;
        }
	

	/**
	* This method get all Details of UpdationDetails
        * @return Vector
	*/	
	public Vector getUpdationDetails(){
                try {
                        Vector v=new Vector();
                        XmlData file[]=xr.getElements("Course");
                        if(file!=null) {
                                Attributes ats;
                                String userid,emailId,status;
				for(int j=0;j<file.length;j++) {
                                        FileEntry fileEntry=new FileEntry();
                                        ats=file[j].getAttributes();
				        userid=ats.getValue("userid");
                                        emailId=ats.getValue("emailId");
                                        status=ats.getValue("status");
                                        fileEntry.setuserid(userid);
                                        fileEntry.setemailId(emailId);
                                        fileEntry.setstatus(status);
                                        v.addElement(fileEntry);
                                }
                                return v;
                        }
                }catch(Exception e){ 
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in getUpdationDetails method ::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
			return null ;
		}
                return null;
        }
 	
	/**
        *This method get all details of Bookmarks 
        *@return Vector
        */
        public Vector getBookmarksDetails()
        {
                        Vector vt=new Vector();
                try
                {
                	XmlData files[]=xr.getElements("Bookmarks");
                	if(files!=null)
                	{
                        	Attributes ats;
                        	String Bookmarkname,urllocation,dirname,type,comment;
                        	for(int j=0;j<files.length;j++)
                        	{
                                	FileEntry fileEntry=new FileEntry();
                                	ats=files[j].getAttributes();
                                	Bookmarkname=ats.getValue("name");
                                	urllocation=ats.getValue("url");
                                	dirname=ats.getValue("dirname");
                                	type=ats.getValue("type");
                                	comment=ats.getValue("comment");

                                	fileEntry.setTopic(Bookmarkname);
                                	fileEntry.setUrl(urllocation);
                                	fileEntry.setName(dirname);
                                	fileEntry.settype(type);
                                	fileEntry.setfeedback(comment);
                                	vt.add(fileEntry);
                        	}
                        	return vt;
                 	}
		}
		catch(Exception e)
                {
                         ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in BookmarksDetailmethod ::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
                }
        return null;
        }
//Jaivir Singh
	public Vector getFileDetailsModify()
	{
			Vector vt=new Vector();
		try
		{	
		XmlData files[]=xr.getElements("File");
//		ErrorDumpUtil.ErrorLog("fls in util"+files);
		if(files!=null)
		{
			Attributes ats;
			String fileName,alias,UName,locationidef,guestaccess;
			for(int j=0;j<files.length;j++)
			{
				FileEntry fileEntry=new FileEntry();	
				ats=files[j].getAttributes();
				fileName=ats.getValue("name");
				alias=ats.getValue("alias");
				String dateString=ats.getValue("publishingDate");
				UName=ats.getValue("username");
				locationidef=ats.getValue("location");
				guestaccess=ats.getValue("guestlogin");

				fileEntry.setName(fileName);
				fileEntry.setAlias(alias);
				fileEntry.setPDate(dateString);
				fileEntry.setUserName(UName);
				fileEntry.setLocation(locationidef);
				fileEntry.setGuestAccess(guestaccess);
				vt.add(fileEntry);
			//	ErrorDumpUtil.ErrorLog("vt return vale======"+vt);
			}
			return vt;
		}
		
		}catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in line 112::"+e);
                        System.out.println("See Exception message in ExceptionLog.txt file:: ");
			//return null;
		}
	return null;	
	}
	
	/**
        *This method get all details of FAQ
        *@return Vector
        */
        public Vector getFaqDetails()
        {
                        Vector vt=new Vector();
                try
                {
                        XmlData files[]=xr.getElements("FAQ");
                        if(files!=null)
                        {
                                Attributes ats;
                                String Id,QuesId,Question,Answer,Version;
                                for(int j=0;j<files.length;j++)
                                {
                                        FileEntry fileEntry=new FileEntry();
                                        ats=files[j].getAttributes();
                                        Id=ats.getValue("Id");
                                        QuesId=ats.getValue("QuesId");
                                        Question=ats.getValue("Question");
                                        Answer=ats.getValue("Answer");
                                        Version=ats.getValue("Version");

                                        fileEntry.setFaqid(Id);
                                        fileEntry.setquestionid(QuesId);
                                        fileEntry.setquestion(Question);
                                        fileEntry.setAnswer(Answer);
                                        fileEntry.setVersion(Version);
                                        vt.add(fileEntry);
                                }
                                return vt;
                        }
                }
                catch(Exception e)
                {
                         ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in FaqDetailmethod ::"+e);
                        System.out.println("See Exception message inExceptionLog.txt file:: ");
                }
        return null;
        }

	//OLES
	 /**
         *This method get all details of OLES
         *@return Vector
         */
        public Vector getQuesBank_Detail()
        {
                        Vector vt=new Vector();
                try
                {
                        XmlData files[]=xr.getElements("Question");
                        if(files!=null)
                        {
                                Attributes ats;
                                String QuesId,Question,opt1,opt2,opt3,opt4,Answer,Description,ImgUrl;
                                for(int j=0;j<files.length;j++)
                                {
                                        FileEntry fileEntry=new FileEntry();
                                        ats=files[j].getAttributes();
                                        QuesId=ats.getValue("Quesid");
                                        Question=ats.getValue("Ques");
                                        opt1=ats.getValue("opt1");
                                        opt2=ats.getValue("opt2");
                                        opt3=ats.getValue("opt3");
                                        opt4=ats.getValue("opt4");
                                        Answer=ats.getValue("Answer");
                                        Description=ats.getValue("Description");
                                        ImgUrl=ats.getValue("ImgUrl");

                                        fileEntry.setquestionid(QuesId);
                                        fileEntry.setquestion(Question);
                                        fileEntry.setoptionA(opt1);
                                        fileEntry.setoptionB(opt2);
                                        fileEntry.setoptionC(opt3);
                                        fileEntry.setoptionD(opt4);
                                        fileEntry.setAnswer(Answer);
                                        fileEntry.setDescription(Description);
                                        fileEntry.setUrl(ImgUrl);
                                        vt.add(fileEntry);
                                }
                                return vt;
          }
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);
                        System.out.println("See Exception message inExceptionLog.txt file:: ");
                }
        return null;
        }

	/**
         *This method get all details of OLES
         *@return Vector
         */
        public Vector getQuesBanklist_Detail()
        {
                        Vector vt=new Vector();
                try
                {
                        XmlData files[]=xr.getElements("Question");
                        if(files!=null)
                        {
                                Attributes ats;
                                String Topicname,Questiontype,Difflevel,Filename,CreationDate;
                                for(int j=0;j<files.length;j++)
                                {
                                        FileEntry fileEntry=new FileEntry();
                                        ats=files[j].getAttributes();
                                        Topicname=ats.getValue("Topicname");
                                        Questiontype=ats.getValue("Questiontype");
                                        Difflevel=ats.getValue("Difflevel");
                                        Filename=ats.getValue("Filename");
                                        CreationDate=ats.getValue("CreationDate");

                                        fileEntry.setTopic(Topicname);
                                        fileEntry.setTypeofquestion(Questiontype);
                                        fileEntry.setDifflevel(Difflevel);
                                        fileEntry.setfileName(Filename);
                                        fileEntry.setPDate(CreationDate);
                                        vt.add(fileEntry);
                                }
                                return vt;
                        }
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);
                        System.out.println("See Exception message inExceptionLog.txt file:: ");
                }
        return null;
	}
	/**
         *This method get all details of OLES
         *@return Vector
         */
	public Vector getQuesBank_Detail1()
        {
                        Vector vt=new Vector();
                try
                {
                        XmlData files[]=xr.getElements("Question");
                        if(files!=null)
                        {
                                Attributes ats;
                                String QuesId,Question,Answer,Description,ImgUrl;
                                for(int j=0;j<files.length;j++)
                                {
                                        FileEntry fileEntry=new FileEntry();
                                        ats=files[j].getAttributes();
                                        QuesId=ats.getValue("Quesid");
                                        Question=ats.getValue("Ques");
                                        Answer=ats.getValue("Answer");
                                        Description=ats.getValue("Description");
                                        ImgUrl=ats.getValue("ImgUrl");

                                        fileEntry.setquestionid(QuesId);
                                        fileEntry.setquestion(Question);
                                        fileEntry.setAnswer(Answer);
                                        fileEntry.setDescription(Description);
                                        fileEntry.setUrl(ImgUrl);
                                        vt.add(fileEntry);
                                }
                                return vt;
                        }
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("The exception in xmlreaderutil in OLESmethod ::"+e);
                        System.out.println("See Exception message inExceptionLog.txt file:: ");
                }
        return null;
	}	
}//end of  file

