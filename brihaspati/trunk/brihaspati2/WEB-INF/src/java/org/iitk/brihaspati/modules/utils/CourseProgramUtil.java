package org.iitk.brihaspati.modules.utils;
/*
 * @(#)CourseProgramUtil.java   
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */

import java.util.List;
import java.util.Date;
import java.util.ListIterator;
import java.util.Vector;
import com.workingdogs.village.Record;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.iitk.brihaspati.om.CourseProgramPeer;
import org.iitk.brihaspati.om.CourseProgram;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
/**
  * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
  */ 
public class CourseProgramUtil 
{
/**
 * In this method,Insert Course id with program of student in table
 
 * @param Stdsrid String The serial id of the user
 * @param CourseId String The course id of the user
 * @param Program String The program of the user 
 */
	public static void InsertCourseProgram(int Stdsrid,String CourseId,String Program)
        {
                        Criteria crit=new Criteria();
                try
                {
                        crit.add(CourseProgramPeer.STUDENT_ID,Stdsrid);
                        crit.add(CourseProgramPeer.COURSE_ID,CourseId);
                        crit.add(CourseProgramPeer.PROGRAM,Program);
                        List v=CourseProgramPeer.doSelect(crit);
                        //ErrorDumpUtil.ErrorLog("list of course rollno --"+v);
                        if(v.size()==0)
                        {
                                crit = new Criteria();
                        	crit.add(CourseProgramPeer.STUDENT_ID,Stdsrid);
                                crit.add(CourseProgramPeer.COURSE_ID,CourseId);
                                crit.add(CourseProgramPeer.PROGRAM,Program);
                                CourseProgramPeer.doInsert(crit);
                        }
                }
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("This is the exception in Insert CourseProgram :--utils(CourseProgramUtil) "+e);
                }
        }

	/**
         * In this method,Insert Roll No with program and institute id in table
         * @param uname String The userid of the user
         * @param Rollno String The rollno of the user 
         * @param Prg String The program of the user
         * @param Instid String the institute id of the user
         * @param File String the language file for messages
         * @param CourseId String the course id of the user
         */	
	public static String InsertPrgRollNo(String uname,String Rollno,String Prg,String Instid,String File,String CourseId)
        {
                        String Msg ="";
                        Criteria crit=new Criteria();
			int stdsrid=0;
		/*
 		* check the existance of combination of Institute, Roll no, program
 		* if exist then set the message duplicate roll no in this institute
 		* else check combination of email,prg and instid of user in db
 		* 	if exist then get serial id of that user and insert entry in course program table 
 		* 	else insert given data into db.
 		*/
                try
                {
		        crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
                        crit.add(StudentRollnoPeer.PROGRAM,Prg);
                        crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                        List v=StudentRollnoPeer.doSelect(crit);
			//ErrorDumpUtil.ErrorLog("list of select inst,prg,rollno      =="+v+"size of vector  =="+v.size());
			if(v.size()!=0)
			{
				Msg =MultilingualUtil.ConvertedString("rollno_msg",File);
				
			}
			else
			{
	                	List UsDetail=getUserPrgRollNo(uname,Prg,Instid);
				//ErrorDumpUtil.ErrorLog("list of select uname,prg,instid      =="+UsDetail+"size of vector  =="+UsDetail.size());
				if(UsDetail.size()!=0)
				{
                                        StudentRollno sr = (StudentRollno)(UsDetail.get(0));
                                        stdsrid=sr.getId();
	                                if(!CourseId.equals("Select Course")&& !Prg.equals("Select Program") && stdsrid!=0)
	                                        InsertCourseProgram(stdsrid,CourseId,Prg);
					String msg1 =MultilingualUtil.ConvertedString("rollno",File);
					String msg2 =MultilingualUtil.ConvertedString("Wikiaction6",File);
					Msg = msg1+" "+msg2+"!!" ;
				}
				else
				{
					crit = new Criteria();
	                                crit.add(StudentRollnoPeer.EMAIL_ID,uname);
	                                crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
	                                crit.add(StudentRollnoPeer.PROGRAM,Prg);
	                                crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
	                                StudentRollnoPeer.doInsert(crit);
	                                crit = new Criteria();
	                                crit.add(StudentRollnoPeer.EMAIL_ID,uname);
	                                crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
	                                crit.add(StudentRollnoPeer.PROGRAM,Prg);
	                                crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
	                                List vlist=StudentRollnoPeer.doSelect(crit);
	                                if(vlist.size() !=0){
	                                        StudentRollno sr = (StudentRollno)(vlist.get(0));
	                                        stdsrid=sr.getId();
	                                }
	                                if(!CourseId.equals("Select Course")&& !Prg.equals("Select Program") && stdsrid!=0)
	                                        InsertCourseProgram(stdsrid,CourseId,Prg);
	                        }
			}
                }
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("This is the exception in Insert Roll No :--utils(CourseProgramUtil) "+e);
                }
                return Msg;
        }


	/**
         * In this method, get roll no of specific user
         * @param uid String The userid of the user
         * @return List 
         */
	public static List getUserRollNo(String uid)
        {
                List v=null;
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(StudentRollnoPeer.EMAIL_ID,uid);
                        v=StudentRollnoPeer.doSelect(crit);
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in get Roll No of specific user -utils(CourseProgramUtil)  :- "+e);

                }
                return v;
        }

	/**
         * In this method, get roll no with program of specific user
         * @param uid String The userid of the user
         * @param prg String Program of the user
         * @param Instid String Institute id of the user
         * @return List 
         */
	public static List getUserPrgRollNo(String uid,String prg,String Instid)
        {
                List v=null;
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(StudentRollnoPeer.EMAIL_ID,uid);
                        crit.add(StudentRollnoPeer.PROGRAM,prg);
                        crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                        v=StudentRollnoPeer.doSelect(crit);
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in get Roll No according to program of specific user -utils(CourseProgramUtil)  :- "+e);

                }
                return v;
        }
	/**
         * In this method, get list of course and program of user
         * @param studid Integer student id of the user
         * @param Crsid String course id of the user
         * @param Prg String the program of the user
         * @return List 
         */
	public static List getUserCourseProgram(int studid,String Crsid,String Prg)
        {
                List v=null;
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(CourseProgramPeer.STUDENT_ID,studid);
                        crit.add(CourseProgramPeer.COURSE_ID,Crsid);
                        crit.add(CourseProgramPeer.PROGRAM,Prg);
                        v=CourseProgramPeer.doSelect(crit);
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in get Course Program  according to student serial id of specific user -utils(CourseProgramUtil)  :- "+e);

                }
                return v;
        }
	/**
         * In this method, get list of roll no of all user
         * @param gid Integer The groupid of the user
         * @param roleid Integer The roleid of the user
         * @return List 
         */
	 public static List getListOfRollNo(int gid,int roleid)
        {
                Vector ulist = new Vector();
                Vector urlist = new Vector();
                String progm="";
                String prgname="";
                try
                {
			/**
                         * Getting record of students & then take email
                         * from email get user rollnorecord from rollno table 
                         */
			List rollrecord = new Vector();
                        ulist=UserGroupRoleUtil.getUDetail(gid,3);
                        for(int i=0;i<ulist.size();i++)
                        {
                                CourseUserDetail element=(CourseUserDetail)ulist.get(i);
                                String email = element.getLoginName();
                                rollrecord = getUserRollNo(email);
                                //ErrorDumpUtil.ErrorLog("Rollrecord in get list of rollno inside utils----------->"+rollrecord);
                                /**
                                 * If Rollrecord is zero it shows user have not rollno
                                 */
				if(rollrecord.size()!=0)
                                {
                                        for(int j=0;j<rollrecord.size();j++)
                                        {
                                                StudentRollno element1 = (StudentRollno)rollrecord.get(j);
                                                String Rollno = element1.getRollNo();
                                                progm = element1.getProgram();
                                                if(progm.equals("NULL")||progm.equals("")||progm.equals(null))
                                                {
                                                        progm="";
                                                        prgname="";
                                                }
                                                else
                                                {
                                                        prgname = InstituteIdUtil.getPrgName(progm);
                                                }
                                                CourseUserDetail cDetails=new CourseUserDetail();
                                                cDetails.setEmail(email);
                                                cDetails.setRollNo(Rollno);
                                                cDetails.setPrgName(prgname);
                                                urlist.add(cDetails);
                                        }
                                }
                        }
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in getting list of Roll No  -utils(CourseProgramUtil)  :- "+e);

                }
                return urlist;
        }
	
	/**
         * In this method, get list of user rollno,program and course after joining two tables
         * @param stdid Integer The student id of the user
         * @return List 
         */
	 public static List getCourseRollnoDetail(int stdid)
        {
		List v=null;
		try
		{
			String qm ="select * from STUDENT_ROLLNO join COURSE_PROGRAM on STUDENT_ROLLNO.ID=COURSE_PROGRAM.STUDENT_ID where STUDENT_ROLLNO.ID="+stdid;
			v = StudentRollnoPeer.executeQuery(qm);
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in getting Course Rollno list -utils(CourseProgramUtil)  :- "+ex);
		}
	return v;
	}
	/**
         * In this method, get Instructor name of the course
         * @param GroupName String the groupname of instructor
         * @return String
         */
	
	public static String getCourseInstructorName(String GroupName)
	{
		String userName="";
		try
		{
			Criteria crit=new Criteria();
        	        crit.add(CoursesPeer.GROUP_NAME,GroupName);
		        List v=CoursesPeer.doSelect(crit);
			for(int i=0;i<v.size();i++)
	                {
		        	String GName=((Courses)v.get(i)).getGroupName();
				String gAlias=((Courses)v.get(i)).getGroupAlias();
                                CourseUserDetail cuDetail=new CourseUserDetail();
                                int index=gAlias.length();
	                        String loginName=GName.substring(index);
				String oldloginName=StringUtils.substringBeforeLast(loginName,"@");
	                        String dmnWIid=StringUtils.substringAfterLast(loginName,"@");
                                String dname=StringUtils.substringBeforeLast(dmnWIid,"_");
          	                oldloginName=oldloginName+"@"+dname;
	                        int UId=UserUtil.getUID(oldloginName);
	                        String uID=Integer.toString(UId);
	                        List userDetails=UserManagement.getUserDetail(uID);
	                        TurbineUser element=(TurbineUser)userDetails.get(0);
	                        String firstName=element.getFirstName().toString();
	                        String lastName=element.getLastName().toString();
	                        userName=firstName+lastName;
	                        if(org.apache.commons.lang.StringUtils.isBlank(userName)){
	                        	userName=oldloginName;
	                        }
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in getting course detail !!(CourseProgramUtil)"+e);
		}
			return(userName);
	}
	/**
         * In this method, get user rollno list according to institute
         * @param Institute_Id String The institute id 
         * @return List
         */
	public static List getInstituteUserRollnoList(String Institute_Id)
        {
		List UsrList=null;
		try
		{
			Criteria crit = new Criteria();
			crit.add(StudentRollnoPeer.INSTITUTE_ID,Institute_Id);
			UsrList=StudentRollnoPeer.doSelect(crit);
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in getting user rollno list according to institute id!!(CourseProgramUtil)"+e); 
		}
		return UsrList;
		
	}
	/**
         * In this method, delete course and program of perticular student
         * @param username String The username
         * @param CourseId String The course id of the user
         */
	public static void DeleteCoursePrg(String username,String CourseId)
	{
		try
		{
			Criteria crit = new Criteria();
			crit.add(StudentRollnoPeer.EMAIL_ID,username);
			List v=StudentRollnoPeer.doSelect(crit);
			for(int i=0;i<v.size();i++)
			{
				int Id = ((StudentRollno)v.get(i)).getId();
				String prg = ((StudentRollno)v.get(i)).getProgram();
				crit = new Criteria();
				crit.add(CourseProgramPeer.STUDENT_ID,Id);
				crit.and(CourseProgramPeer.COURSE_ID,CourseId);
				crit.and(CourseProgramPeer.PROGRAM,prg);
				CourseProgramPeer.doDelete(crit);
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in deleting course program !!(CourseProgramUtil)"+e);
		}			
	}
}

