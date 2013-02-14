package org.iitk.brihaspati.modules.utils;
/*
 * @(#)CourseProgramUtil.java   
 *
 *  Copyright (c) 2011,2012 ETRG,IIT Kanpur. 
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
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
	public static void InsertCourseProgram(String uname,String CourseId,String Program)
        {
                        Criteria crit=new Criteria();
                try
                {
                        crit.add(CourseProgramPeer.EMAIL_ID,uname);
                        crit.add(CourseProgramPeer.COURSE_ID,CourseId);
                        crit.add(CourseProgramPeer.PROGRAM_CODE,Program);
                        List v=CourseProgramPeer.doSelect(crit);
                        //ErrorDumpUtil.ErrorLog("list of course rollno --"+v);
                        if(v.size()==0)
                        {
                                crit = new Criteria();
                        	crit.add(CourseProgramPeer.EMAIL_ID,uname);
                                crit.add(CourseProgramPeer.COURSE_ID,CourseId);
                                crit.add(CourseProgramPeer.PROGRAM_CODE,Program);
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
		MultilingualUtil m_u=new MultilingualUtil();
                try
                {
		 	//First check existence of program ,  institute with user in db
			 crit=new Criteria();	
			 crit.add(StudentRollnoPeer.EMAIL_ID,uname);
	                 crit.add(StudentRollnoPeer.PROGRAM,Prg);
		         crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                	 List v=StudentRollnoPeer.doSelect(crit);
			 /*if(exist)
				then get roll no of user for this combination from db.
				chk course id if(not null)
					then check existence of user with course in course_program table
					if(exist)
						then update program in course_program  table.
					else
                                                enter detail of course and program in course_program  table.

				else
					out of program and return to screen with suitable message	
			  Else
				get all input value, and insert in course program and student roll no.
			*/
			 if(v.size()!=0)
	                 {
				String rollno = ((StudentRollno)v.get(0)).getRollNo();
				if(!CourseId.equals("")&&!CourseId.equals("Select Course"))
				{
					List CrsList = getUserCourseProgram(uname,CourseId);
					if(CrsList.size()>0)
					{
						CourseProgram element = (CourseProgram)CrsList.get(0);
	                                        int Id = element.getId();
		                                crit = new Criteria();
                	                        crit.add(CourseProgramPeer.ID,Id);
                        	                crit.add(CourseProgramPeer.EMAIL_ID,uname);
                                	        crit.add(CourseProgramPeer.COURSE_ID,CourseId);
                                        	crit.add(CourseProgramPeer.PROGRAM_CODE,Prg);
	                                        CourseProgramPeer.doUpdate(crit);
					}
					else
					{
						InsertCourseProgram(uname,CourseId,Prg);
					}
				}
				else
				{
					Msg= m_u.ConvertedString("course_msg",File);
				}
			}
			else
			{
				if(!uname.equals("")&&!Prg.equals("Select Program")&&!Instid.equals("")&&!Instid.equals("0")&&!CourseId.equals("Select Course")&&!Prg.equals("")&&!CourseId.equals("")&&!Rollno.equals(""))
        	                {
					List chkuser=CheckDuplicateRollno(Rollno,Prg,Instid);
					if(chkuser.size()!=0)
		                        {	
                		                Msg =MultilingualUtil.ConvertedString("rollno_msg",File);
        		                }
					else
					{
						crit = new Criteria();
	                        	        crit.add(StudentRollnoPeer.EMAIL_ID,uname);
		                               	crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
        		                        crit.add(StudentRollnoPeer.PROGRAM,Prg);
                		                crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
	                        	        StudentRollnoPeer.doInsert(crit);
        	                        	InsertCourseProgram(uname,CourseId,Prg);
					}
				}
				else
				{
					if(!Prg.equals("Select Program")&&!CourseId.equals("Select Course"))
						Msg= m_u.ConvertedString("prgm_msg1",File);
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
	//public static List getUserCourseProgram(int studid,String Crsid,String Prg)
	public static List getUserCourseProgram(String uname,String Crsid)
        {
                List v=null;
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(CourseProgramPeer.EMAIL_ID,uname);
                        crit.add(CourseProgramPeer.COURSE_ID,Crsid);
                        //crit.add(CourseProgramPeer.PROGRAM_CODE,Prg);
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
         * @param Instituteid Integer The instituteid of the user
         * @return List 
         */
	 public static List getListOfRollNo(int gid,int Instid)
        {
                Vector ulist = new Vector();
                Vector urlist = new Vector();
                String progm="";
                String prgname="";
		//int Instid=0;
                try
                {
			/**
                         * Getting record of students & then take email
                         * from email get user rollnorecord from rollno table 
                         */
                        ulist=UserGroupRoleUtil.getUDetail(gid,3);
			String gname = GroupUtil.getGroupName(gid);
                        for(int i=0;i<ulist.size();i++)
                        {
                                CourseUserDetail element=(CourseUserDetail)ulist.get(i);
                                String email = element.getLoginName();
                                //rollrecord = getUserRollNo(email);
				List rollrecord=getUserInstituteRollnoList(email,Integer.toString(Instid));
                                //ErrorDumpUtil.ErrorLog("email in get list of rollno inside utils----------->"+email);
                                /**
                                 * If Rollrecord is zero it shows user have not rollno
                                 */
				if(rollrecord.size()!=0)
                                {
                                        for(int j=0;j<rollrecord.size();j++)
                                        {
                                                StudentRollno element1 = (StudentRollno)rollrecord.get(j);
                                                String Rollno = element1.getRollNo();
                                		//ErrorDumpUtil.ErrorLog("rollnoin get list of rollno inside utils----------->"+Rollno);
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
						List lst = getCourseRollnoDetail(email,Instid);
						//ErrorDumpUtil.ErrorLog("lst--"+lst);
						if(lst.size()>0)
			                        {
			                                for(ListIterator k = lst.listIterator();k.hasNext();)
                        		                {
                                        		        Record item = (Record)k.next();
		                                                String CrsId = item.getValue ("COURSE_ID").asString();
		                                                String Prg = item.getValue ("PROGRAM_CODE").asString();
								//ErrorDumpUtil.ErrorLog("COURSE_ID=="+CrsId+"--gname--"+gname);
								/**
 								 * Below check is put to show rollno and program of user for this 
 								 * course only	 
 								 */ 
								if(CrsId.equals(gname)&&Prg.equals(progm))
								{
			                                                CourseUserDetail cDetails=new CourseUserDetail();
                        			                        cDetails.setEmail(email);
                                                			cDetails.setRollNo(Rollno);
			                                                cDetails.setPrgName(prgname);
                        			                        urlist.add(cDetails);
                                        			}
                                			}
                        			}
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
	 public static List getCourseRollnoDetail(String uname,int Instid)
        {
		List v=null;
		try
		{
			String qm ="select * from COURSE_PROGRAM join STUDENT_ROLLNO on STUDENT_ROLLNO.PROGRAM=COURSE_PROGRAM.PROGRAM_CODE and STUDENT_ROLLNO.EMAIL_ID=COURSE_PROGRAM.EMAIL_ID where STUDENT_ROLLNO.EMAIL_ID='"+uname+"' and STUDENT_ROLLNO.INSTITUTE_ID="+Instid;
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
			/**
 			* Get list of registered program of the user 
 			*/ 
			Criteria crit = new Criteria();
			crit.add(StudentRollnoPeer.EMAIL_ID,username);
			List v=StudentRollnoPeer.doSelect(crit);
			//ErrorDumpUtil.ErrorLog("existance from courseprgutil---"+v.isEmpty());
			if(v.isEmpty()==false)
			{
				/**
 				* If courseid is not null this shows delete perticular course of user from
 				* COURSE_PROGRAM table
 				* else delete all entry of user from COURSE_PROGRAM table 
 				*/ 
				if(!CourseId.equals(""))
				{
					/**
 					*Get one program from list and check existence of user with this program and course in database.
					*If (exist) 
					* then check is this last entry of user with this program or not?
					* If entry is last then delete entry of course and program from COURSE_PROGRAM table
					* 	and set expiry of user in STUDENT_ROLLNO table   
					* else
					* 	delete entry from COURSE_PROGRAM table
 					*/	 
					for(int i=0;i<v.size();i++)
					{
						String prg = ((StudentRollno)v.get(i)).getProgram();
						String Instid = StringUtils.substringAfterLast(CourseId,"_");
						crit = new Criteria();
						crit.add(CourseProgramPeer.EMAIL_ID,username);
                                                crit.and(CourseProgramPeer.COURSE_ID,CourseId);
                                                crit.and(CourseProgramPeer.PROGRAM_CODE,prg);
                                                List ve=CourseProgramPeer.doSelect(crit);
						if(ve.isEmpty()==false){
							crit = new Criteria();
							crit.add(CourseProgramPeer.EMAIL_ID,username);
							crit.and(CourseProgramPeer.PROGRAM_CODE,prg);
							List crslist=CourseProgramPeer.doSelect(crit);
							if(crslist.size()==1)
							{
								int id = ((StudentRollno)v.get(i)).getId();	
								DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar cal = Calendar.getInstance();
								cal.add(Calendar.DATE,180);
								String Stdexpdate=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+ "-" + cal.get(Calendar.DATE);
								//ErrorDumpUtil.ErrorLog("------------Strx--------"+Stdexpdate);
								crit=new Criteria();
								crit.add(StudentRollnoPeer.ID,id);
                                                                crit.add(StudentRollnoPeer.EMAIL_ID,username);
                                                                crit.add(StudentRollnoPeer.EXPIRY_DATE,sdf.parse(Stdexpdate));
                                                                crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                                                                StudentRollnoPeer.doUpdate(crit);

								crit=new Criteria();
								crit.add(CourseProgramPeer.EMAIL_ID,username);
								crit.and(CourseProgramPeer.COURSE_ID,CourseId);
								crit.and(CourseProgramPeer.PROGRAM_CODE,prg);
								CourseProgramPeer.doDelete(crit);
							}
							else	
							{
								crit=new Criteria();
								crit.add(CourseProgramPeer.EMAIL_ID,username);
								crit.and(CourseProgramPeer.COURSE_ID,CourseId);
								crit.and(CourseProgramPeer.PROGRAM_CODE,prg);
								CourseProgramPeer.doDelete(crit);
							}
						}
					}
				}
				else
				{
					crit = new Criteria();
					crit.add(CourseProgramPeer.EMAIL_ID,username);
					List ve=CourseProgramPeer.doSelect(crit);
					if(ve.isEmpty()==false){
						crit=new Criteria();
						crit.add(CourseProgramPeer.EMAIL_ID,username);
						CourseProgramPeer.doDelete(crit);
					}
				}
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in deleting course program !!(CourseProgramUtil)"+e);
		}			
	}

	/**
         * In this method, get user rollno list according to institute
         * @param uid String the email id of user
         * @param Institute_Id String the institute id 
         * @return List
         */
	public static List getUserInstituteRollnoList(String uid,String Institute_Id)
        {
                List UsrInstList=null;
                try
                {
                        Criteria crit = new Criteria();
                        crit.add(StudentRollnoPeer.EMAIL_ID,uid);
                        crit.add(StudentRollnoPeer.INSTITUTE_ID,Institute_Id);
                        UsrInstList=StudentRollnoPeer.doSelect(crit);
                }
                catch(Exception e)
                {
                        ErrorDumpUtil.ErrorLog("This is the exception in getting user rollno list according to institute id!!(CourseProgramUtil)"+e);
                }
                return UsrInstList;

        }
	/**
         * In this method, check duplicate rollno for perticular program in institute 
         * @param Rollno String rollno
         * @param Prg String Program
         * @param Instid String Institute id
         * @return List
         */
	
	public static List CheckDuplicateRollno(String Rollno,String Prg, String Instid)
	{
		List RollnoList=null;
		try{
			Criteria crit = new Criteria();
			crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
                        crit.add(StudentRollnoPeer.PROGRAM,Prg);
                        crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                        RollnoList=StudentRollnoPeer.doSelect(crit);
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("This is the exception in checking existence of duplicate rollno!!(CourseProgramUtil)"+e);
		} 
		return RollnoList;
	}
	/**
         * In this method, update rollno of the student for perticular program  
         * @param uname String the username
         * @param Program String program of the student
         * @param Rollno String rollno of the student
         * @param Instid String institute id
         * @param File String language file for messages
         * @return String
         */
	public static String updateRollno(String uname, String Program, String Rollno, String Instid, String File)
	{
		String msg="";
                Criteria crit=new Criteria();
		String prgcode = InstituteIdUtil.getPrgCode(Program);
		try{
                        crit.add(StudentRollnoPeer.EMAIL_ID,uname);
                        crit.add(StudentRollnoPeer.PROGRAM,prgcode);
                        crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                        List v=StudentRollnoPeer.doSelect(crit);
			if(v.size()>0)
			{
				int id= ((StudentRollno)v.get(0)).getId();
				//ErrorDumpUtil.ErrorLog("Id inside util file----"+id);
				if(!Rollno.equals("") && !prgcode.equals("")){
					crit=new Criteria();
					crit.add(StudentRollnoPeer.ID,id);
                	                crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
                        	        crit.add(StudentRollnoPeer.PROGRAM,prgcode);
                                	StudentRollnoPeer.doUpdate(crit);
					msg=MultilingualUtil.ConvertedString("updaterlno_msg",File);
				}
				else
				{
					msg=MultilingualUtil.ConvertedString("prgm_msg5",File);
				}
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in updaterollno !!(CourseProgramUtil)"+e);
		}
		return msg;
	}

	/**
         * In this method, Insert rollno of the student for new program  
         * @param uname String the username
         * @param Program String program of the student
         * @param Rollno String rollno of the student
         * @param Instid String institute id
         * @param File String language file for messages
         * @return String
         */
	public static String InsertRollno(String uname, String Program, String Rollno, String Instid, String File)
	{
		String Msg="";
		Criteria crit=new Criteria();
		try{
			List chkuser=CheckDuplicateRollno(Rollno,Program,Instid);
                        if(chkuser.size()!=0)
               	        {
	               	         Msg =MultilingualUtil.ConvertedString("rollno_msg",File);
	                }
                        else
               	        {
	       	                 crit = new Criteria();
                                 crit.add(StudentRollnoPeer.EMAIL_ID,uname);
               	                 crit.add(StudentRollnoPeer.ROLL_NO,Rollno);
                       	         crit.add(StudentRollnoPeer.PROGRAM,Program);
	                         crit.add(StudentRollnoPeer.INSTITUTE_ID,Instid);
                                 StudentRollnoPeer.doInsert(crit);
	               	         Msg =MultilingualUtil.ConvertedString("prgm_msg8",File);
               	 	}
		}
		catch(Exception e)
		{	
			ErrorDumpUtil.ErrorLog("Exception in Insertrollno !!(CourseProgramUtil)"+e);
		}
		return Msg;
	}
}
