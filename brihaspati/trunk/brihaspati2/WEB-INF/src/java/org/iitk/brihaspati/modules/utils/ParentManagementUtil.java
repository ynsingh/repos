package org.iitk.brihaspati.modules.utils;

/*
 * @(#)ParentManagementUtil.java      
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
 *  WHETHER IN CONTRACT, 
 *  RICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 */

import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.sql.Timestamp;
import java.util.Date;
import com.workingdogs.village.Record;
import com.workingdogs.village.Value;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.commons.lang.StringUtils;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineGroup;
import org.apache.turbine.services.security.torque.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CourseProgramPeer;
import org.iitk.brihaspati.om.CourseProgram;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.ParentInfoPeer;
import org.iitk.brihaspati.om.ParentInfo;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

 /**
 * This file contains utility methods
 * used for displaying parnts' information
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 * @createdate 14-10-2013
 */
public class ParentManagementUtil
{
	//public static Vector getParentListBySearchString(String user_query,String searchString,String instituteId)
	public static Vector getParentListBySearchString(String user_query, String searchString, String instituteId, String searchFor, int g_id)
        {
                MultilingualUtil m_u=new MultilingualUtil();
                Vector Details=new Vector();
                Vector lnamelist=new Vector();
                CourseUserDetail cuDetail=new CourseUserDetail();
                List parent =null;
                List v = null, v1 = null, v2= null;
                List student = null, v3 = null;
                int i=0, j=0, m=0, p=0;
		Criteria criteria, criteria1, criteria2;
                //String query="", user_id="", institute_id="";
		int user_id = 0, Id=0;
		int [] uId={1,0};
		String userId = "", s="";
                String query="", institute_id="";
		String searchId = "0", searchId1 = "1";
                String student_id="", email="", roll_no="";
                String fname="", lname="", name="";
                String pname="", mail_id="", mobile_no="";

                try{
                        Criteria crit=new Criteria();
			//ErrorDumpUtil.ErrorLog("user's query = "+user_query);
			//ErrorDumpUtil.ErrorLog("PFname g_id----"+g_id);
                        if(user_query.equals("ParentFName"))
                        {
		/**
		        cuDetail = new Details();       
		        // 'name' can be null in telephone_directory, but not in turbine_user.
		        select user_id from turbine_user WHERE FirstName LIKE '%'+searchString+'%';
		        foreach(user_id)
		        {
		                select student_id from parent_info where parent_id = user_id;
		                select email, firstname + '' + lastname as student_name from turbine_user where user_id = student_id;
				if(mode == 'inst_admin'){
		                	select institute_id, roll_no from student_rollno where email_id = email;
			                if(institute_id == instituteId)
				}
				else{
					get rows from 'TurbineUserGroupRole' table
		                        where role is 'student' for the given group_id
					// we get student's id in st_id
					foreach(id in result)
						if(student_id == st_id)
				}
		                {
		                        select name, mail_id, mobile_no from telephone_directory where user_id = this.user_id;
		                        cuDetail.setParentName(name);
               			        cuDetail.setParentEmail(mail_id); 
		                        cuDetail.setPrentPhone(mobile_no);
		                        cuDetail.setStudentName(student_name);
		                        cuDetail.setStudentEmail(email);
		                        cuDetail.setStudentRollNo(roll_no);
                		}
		                Details.add(cuDetail);
		        }                       
		*/
                                //query ="select USER_ID from TURBINE_USER where FIRST_NAME+' '+LAST_NAME LIKE '%'"+searchString+"'%'";
                                query ="select USER_ID from TURBINE_USER where FIRST_NAME LIKE '%"+searchString+"%'";
                                parent = TurbineUserPeer.executeQuery(query);
				//ErrorDumpUtil.ErrorLog("PFname parent.size==="+parent.size());
                                if(parent.size()>0)
                                {
                                        for(ListIterator l = parent.listIterator();l.hasNext();)
                                        {
						searchId = "0";
						searchId1 = "1";
						student_id = "";
						roll_no = "";
                                                Record item = (Record)l.next();
						cuDetail=new CourseUserDetail();
                                                userId = item.getValue ("USER_ID").asString();
						//ErrorDumpUtil.ErrorLog("Parent's Id = "+userId);
                                                criteria=new Criteria();
                                                criteria.add(ParentInfoPeer.PARENT_ID,userId);
                                                v1 = ParentInfoPeer.doSelect(criteria);
                                                for(j=0;j<v1.size();j++)
                                                {
                                                        student_id = ((ParentInfo)v1.get(j)).getStudentId();
                                                        //ErrorDumpUtil.ErrorLog("student's id "+student_id);
                                                }

                                                if(!student_id.equals(""))
                                                {
                                                        query = "select EMAIL, FIRST_NAME, LAST_NAME from TURBINE_USER where USER_ID = '"+student_id+"'";
                                                        student = TurbineUserPeer.executeQuery(query);
                                                        if(student.size()>0)
                                                        {
                                                                for(ListIterator k = student.listIterator();k.hasNext();)
                                                                {
                                                                        Record item1 = (Record)k.next();
                                                                        email = item1.getValue ("EMAIL").asString();
									//ErrorDumpUtil.ErrorLog("student's email "+email);
                                                                        fname = item1.getValue ("FIRST_NAME").asString();
									//ErrorDumpUtil.ErrorLog("student's fname "+fname);
                                                                        lname = item1.getValue ("LAST_NAME").asString();
									//ErrorDumpUtil.ErrorLog("student's lname "+lname);
                                                                }
                                                                name = fname+" "+lname;
                                                        }
							//ErrorDumpUtil.ErrorLog("PFname searchFor===="+searchFor);
							//if(searchFor.equals("inst_admin")){
	                                                        criteria1=new Criteria();
        	                                                criteria1.add(StudentRollnoPeer.EMAIL_ID,email);
								criteria1.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                	                                        v2 = StudentRollnoPeer.doSelect(criteria1);
                        	                                for(i=0;i<v2.size();i++)
                                	                        {
                                        	                        //institute_id = ((StudentRollno)v2.get(i)).getInstituteId();
									//ErrorDumpUtil.ErrorLog("institute id "+institute_id);
                                                        	        roll_no = ((StudentRollno)v2.get(i)).getRollNo();
									//ErrorDumpUtil.ErrorLog("student's roll no "+roll_no);
									/*if(institute_id.equals(instituteId)){
										searchId = institute_id;
		                                                                searchId1 = instituteId;
									}*/
	                                                        }
							if(searchFor.equals("inst_admin")){
								//if(institute_id.equals(instituteId)){
								if(!roll_no.equals(null)){
 	                                                               //searchId = institute_id;
									searchId = "true";
                                                                       //searchId1 = instituteId;
									searchId1 = "true";
                                                                }
							}
							else{
								//searchFor = "instructor"
								criteria1 = new Criteria();
						                criteria1.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
					                        criteria1.and(TurbineUserGroupRolePeer.ROLE_ID,"3");
					                        criteria1.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
						                v2=TurbineUserGroupRolePeer.doSelect(criteria1);
								//ErrorDumpUtil.ErrorLog("PFname v2.size()---"+v2.size());
								for(i=0;i<v2.size();i++)
					                        {
					                                //cDetails=new CourseUserDetail();
					                                TurbineUserGroupRole element=(TurbineUserGroupRole)v2.get(i);
					                                //Here 's' is the user_id
							                s=Integer.toString(element.getUserId());
									if(s.equals(student_id)){
										//ErrorDumpUtil.ErrorLog("PFname student's id---"+s);
										searchId = s;
										searchId1 = student_id;
									}
								}//for
							}

                                                        if(searchId.equals(searchId1))
                                                        {
                                                                criteria2 = new Criteria();
                                                                criteria2.add(TelephoneDirectoryPeer.USER_ID,userId);
                                                                v3 = TelephoneDirectoryPeer.doSelect(criteria2);
                                                                for(m=0; m<v3.size(); m++)
                                                                {
                                                                       // pname = ((TelephoneDirectory)v3.get(m)).getName();
                                                                        mail_id = ((TelephoneDirectory)v3.get(m)).getMailId();
									//ErrorDumpUtil.ErrorLog("parent's mail "+mail_id);
                                                                        mobile_no = ((TelephoneDirectory)v3.get(m)).getMobileNo();												//ErrorDumpUtil.ErrorLog("parent's mobile no "+mobile_no);
                                                                }

								criteria2 = new Criteria();
                                                                criteria2.add(TurbineUserPeer.USER_ID,userId);
                                                                v3 = TurbineUserPeer.doSelect(criteria2);
                                                                for(m=0; m<v3.size(); m++)
                                                                {
                                                                       // pname = ((TelephoneDirectory)v3.get(m)).getName();
                                                                        fname = ((TurbineUser)v3.get(m)).getFirstName();
									//ErrorDumpUtil.ErrorLog("parent's fname "+fname);
                                                                        lname = ((TurbineUser)v3.get(m)).getLastName();
									//ErrorDumpUtil.ErrorLog("parent's lname "+lname);
                                                                }

                                                                cuDetail.setEmail(email);
								//ErrorDumpUtil.ErrorLog("parent's username ------->"+name);
                                                                cuDetail.setUserName(name);
                                                                cuDetail.setParentFirstName(fname);
								cuDetail.setParentLastName(lname);
                                                                cuDetail.setParentEmail(mail_id);
                                                                cuDetail.setParentMobileNo(mobile_no);
                                                                cuDetail.setRollNo(roll_no);
								cuDetail.setParentId(Integer.parseInt(userId));
								cuDetail.setUserId(student_id);
                                                                Details.add(cuDetail);
                                                        }
                                                }//if student_id
                                       }
                               }
                        }// query 'ParentName'
			else if(user_query.equals("ParentLName"))
			{
			/**
                        cuDetail = new Details();       
                        // 'name' can be null in telephone_directory, but not in turbine_user.
                        select user_id from turbine_user WHERE LastName LIKE '%'+searchString+'%';
                        foreach(user_id)
                        {
                                select student_id from parent_info where parent_id = user_id;
                                select email, firstname + '' + lastname as student_name from turbine_user where user_id = student_id;
                                //select institute_id, roll_no from student_rollno where email_id = email;
				if(mode == 'inst_admin'){
                                        select institute_id, roll_no from student_rollno where email_id = email;
                                        if(institute_id == instituteId)
                                }
                                else{
                                        get rows from 'TurbineUserGroupRole' table
                                        where role is 'student' for the given group_id
                                        // we get student's id in st_id
                                        if(student_id == st_id)
                                }
                                //if(institute_id == instituteId)
                                {
                                        select name, mail_id, mobile_no from telephone_directory where user_id = this.user_id;
                                        cuDetail.setParentName(name);
                                        cuDetail.setParentEmail(mail_id); 
                                        cuDetail.setPrentPhone(mobile_no);
                                        cuDetail.setStudentName(student_name);
                                        cuDetail.setStudentEmail(email);
                                        cuDetail.setStudentRollNo(roll_no);
                                }
                                Details.add(cuDetail);
                        }                       
                */
			query ="select USER_ID from TURBINE_USER where LAST_NAME LIKE '%"+searchString+"%'";
                                parent = TurbineUserPeer.executeQuery(query);
                                if(parent.size()>0)
                                {
                                        for(ListIterator l = parent.listIterator();l.hasNext();)
                                        {
						searchId = "0";
                                                searchId1 = "1";
						student_id = "";
						roll_no = "";
                                                Record item = (Record)l.next();
						cuDetail=new CourseUserDetail();
                                                userId = item.getValue ("USER_ID").asString();
                                                //ErrorDumpUtil.ErrorLog("Parent's Id = "+userId);
                                                criteria=new Criteria();
                                                criteria.add(ParentInfoPeer.PARENT_ID,userId);
                                                v1 = ParentInfoPeer.doSelect(criteria);
                                                for(j=0;j<v1.size();j++)
                                                {
                                                        student_id = ((ParentInfo)v1.get(j)).getStudentId();
                                                        //ErrorDumpUtil.ErrorLog("student's id "+student_id);
                                                }
						
						
                                                if(!student_id.equals(""))
                                                {
                                                        query = "select EMAIL, FIRST_NAME, LAST_NAME from TURBINE_USER where USER_ID = '"+student_id+"'";
                                                        student = TurbineUserPeer.executeQuery(query);
                                                        if(student.size()>0)
                                                        {
                                                                for(ListIterator k = student.listIterator();k.hasNext();)
                                                                {
                                                                        Record item1 = (Record)k.next();
                                                                        email = item1.getValue ("EMAIL").asString();
                                                                        //ErrorDumpUtil.ErrorLog("student's email "+email);
                                                                        fname = item1.getValue ("FIRST_NAME").asString();
                                                                       //ErrorDumpUtil.ErrorLog("student's fname "+fname);
                                                                        lname = item1.getValue ("LAST_NAME").asString();
                                                                        //ErrorDumpUtil.ErrorLog("student's lname "+lname);
                                                                }
                                                                name = fname+" "+lname;
                                                        }
							//ErrorDumpUtil.ErrorLog("PFname searchFor===="+searchFor);
                                                        //if(searchFor.equals("inst_admin")){
                                                                criteria1=new Criteria();
                                                                criteria1.add(StudentRollnoPeer.EMAIL_ID,email);
                                                                criteria1.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                                                                v2 = StudentRollnoPeer.doSelect(criteria1);
                                                                for(i=0;i<v2.size();i++)
                                                                {
                                                                        //institute_id = ((StudentRollno)v2.get(i)).getInstituteId();
                                                                        //ErrorDumpUtil.ErrorLog("institute id "+institute_id);
                                                                        roll_no = ((StudentRollno)v2.get(i)).getRollNo();
                                                                        //ErrorDumpUtil.ErrorLog("student's roll no "+roll_no);
                                                                        /*if(institute_id.equals(instituteId)){
                                                                                searchId = institute_id;
                                                                                searchId1 = instituteId;
                                                                        }*/
                                                                }
                                                        if(searchFor.equals("inst_admin")){
                                                                //if(institute_id.equals(instituteId)){
                                                                if(!roll_no.equals("")){
                                                                       //searchId = institute_id;
                                                                        searchId = "true";
                                                                       //searchId1 = instituteId;
                                                                        searchId1 = "true";
                                                                }
                                                        }
                                                        else{
                                                                //searchFor = "instructor"
                                                                criteria1 = new Criteria();
                                                                criteria1.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                                                                criteria1.and(TurbineUserGroupRolePeer.ROLE_ID,"3");
                                                                criteria1.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
                                                                v2=TurbineUserGroupRolePeer.doSelect(criteria1);
                                                                for(i=0;i<v2.size();i++)
                                                                {
                                                                        //cDetails=new CourseUserDetail();
                                                                        TurbineUserGroupRole element=(TurbineUserGroupRole)v2.get(i);
                                                                        //Here 's' is the user_id
                                                                        s=Integer.toString(element.getUserId());
                                                                        if(s.equals(student_id)){
                                                                                searchId = s;
                                                                                searchId1 = student_id;
                                                                        }
                                                                }//for
                                                        }

                                                        //if(institute_id.equals(instituteId))
							if(searchId.equals(searchId1))
                                                        {
                                                                criteria2 = new Criteria();
                                                                criteria2.add(TelephoneDirectoryPeer.USER_ID,userId);
                                                                v3 = TelephoneDirectoryPeer.doSelect(criteria2);
                                                                for(m=0; m<v3.size(); m++)
                                                                {
                                                                       // pname = ((TelephoneDirectory)v3.get(m)).getName();
                                                                        mail_id = ((TelephoneDirectory)v3.get(m)).getMailId();
                                                                        //ErrorDumpUtil.ErrorLog("parent's mail "+mail_id);
                                                                        mobile_no = ((TelephoneDirectory)v3.get(m)).getMobileNo();                                                                                              //ErrorDumpUtil.ErrorLog("parent's mobile no "+mobile_no);
                                                                }

                                                                criteria2 = new Criteria();
                                                                criteria2.add(TurbineUserPeer.USER_ID,userId);
                                                                v3 = TurbineUserPeer.doSelect(criteria2);
                                                                for(m=0; m<v3.size(); m++)
                                                                {
                                                                       // pname = ((TelephoneDirectory)v3.get(m)).getName();
                                                                        fname = ((TurbineUser)v3.get(m)).getFirstName();
                                                                        //ErrorDumpUtil.ErrorLog("parent's fname "+fname);
                                                                        lname = ((TurbineUser)v3.get(m)).getLastName();
                                                                        //ErrorDumpUtil.ErrorLog("parent's lname "+lname);
                                                                }
								//ErrorDumpUtil.ErrorLog("Adding data to vector "+email+" " +name+" "+fname+" "+lname+" " +mail_id+" "+mobile_no+" "+roll_no);
                                                                cuDetail.setEmail(email);
                                                                cuDetail.setUserName(name);
                                                                cuDetail.setParentFirstName(fname);
                                                                cuDetail.setParentLastName(lname);
                                                                cuDetail.setParentEmail(mail_id);
                                                                cuDetail.setParentMobileNo(mobile_no);
                                                                cuDetail.setRollNo(roll_no);
								cuDetail.setParentId(Integer.parseInt(userId));
                                                                cuDetail.setUserId(student_id);
                                                                Details.add(cuDetail);
                                                        }
                                                }//if student_id
                                       }
                               }
			}
                        else if(user_query.equals("ParentEmail"))
                        {

			/**
			        cuDetail = new Details();
			        // 'email' can be null in turbine_user, but not in telephone_directory.
			        select user_id from telephone_directory where mail_id = searchString;
			        foreach(user_id)
			        {
			                select student_id from parent_info where parent_id = user_id;
			                select email, firstname + '' + lastname as student_name from turbine_user where user_id = student_id;
			                //select institute_id, roll_no from student_rollno where email_id = email;
			                //if(institute_id == instituteId)
					if(mode == 'inst_admin'){
                                	        select institute_id, roll_no from student_rollno where email_id = email;
                                        	if(institute_id == instituteId)
                                	}
                                	else{
                                        	get rows from 'TurbineUserGroupRole' table
                                        	where role is 'student' for the given group_id
                                        	// we get student's id in st_id
                                        	if(student_id == st_id)
                                	}
			                {
			                        select name, mail_id, mobile_no from telephone_directory where user_id = this.user_id;
			                        cuDetail.setParentName(name);
			                        cuDetail.setParentEmail(mail_id); 
			                        cuDetail.setPrentPhone(mobile_no);
			                        cuDetail.setStudentName(student_name);
			                        cuDetail.setStudentEmail(email);
			                        cuDetail.setStudentRollNo(roll_no);
			                }
		                Details.add(cuDetail);
			        }
			                        
			*/
                                criteria = new Criteria();
                                criteria.add(TelephoneDirectoryPeer.MAIL_ID,searchString);
                                v1 = TelephoneDirectoryPeer.doSelect(criteria);
                                for(i=0; i<v1.size(); i++)
                                {
					cuDetail=new CourseUserDetail();
                                        user_id = ((TelephoneDirectory)v1.get(i)).getUserId();
                                        criteria1 = new Criteria();
                                        criteria1.add(ParentInfoPeer.PARENT_ID, user_id);
                                        v2 = ParentInfoPeer.doSelect(criteria1);
                                        for(j=0; j<v2.size(); j++)
                                        {
						searchId = "0";
                                                searchId1 = "1";
						roll_no="";
                                                student_id = ((ParentInfo)v2.get(j)).getStudentId();
						//ErrorDumpUtil.ErrorLog("student_id======"+student_id);
                                                query = "select EMAIL, FIRST_NAME, LAST_NAME from TURBINE_USER where USER_ID = '"+student_id+"'";
                                                student = TurbineUserPeer.executeQuery(query);
						//ErrorDumpUtil.ErrorLog("student.size()==="+student.size());
                                                if(student.size()>0)
                                                {
                                                        for(ListIterator k = student.listIterator();k.hasNext();)
                                                        {
                                                                Record item1 = (Record)k.next();
                                                                email = item1.getValue ("EMAIL").asString();
                                                                fname = item1.getValue ("FIRST_NAME").asString();
                                                                lname = item1.getValue ("LAST_NAME").asString();
                                                        }
                                                        name = fname+ " "+ lname;

							criteria2=new Criteria();
							//if(searchFor.equals("inst_admin")){
								//criteria2=new Criteria();
                                                                criteria2.add(StudentRollnoPeer.EMAIL_ID,email);
								criteria2.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                                                                v3 = StudentRollnoPeer.doSelect(criteria2);
                                                                for(p=0;p<v3.size();p++)
                                                                {
                                                                        //institute_id = ((StudentRollno)v3.get(p)).getInstituteId();
                                                                        //ErrorDumpUtil.ErrorLog("institute id "+institute_id);
                                                                        roll_no = ((StudentRollno)v3.get(p)).getRollNo();
                                                                        //ErrorDumpUtil.ErrorLog("student's roll no "+roll_no);
                                                                        /*if(institute_id.equals(instituteId)){
                                                                                searchId = institute_id;
                                                                                searchId1 = instituteId;
                                                                        }*/
                                                                }
							if(searchFor.equals("inst_admin")){
								if(!roll_no.equals("")){
	                                                                searchId = "true";
                                                                        searchId1 = "true";
                                                                 }
                                                        }
                                                        else{
                                                                //searchFor = "instructor"
                                                                criteria2 = new Criteria();
                                                                criteria2.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                                                                criteria2.and(TurbineUserGroupRolePeer.ROLE_ID,"3");
                                                                criteria2.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
                                                                v3=TurbineUserGroupRolePeer.doSelect(criteria2);
                                                                for(p=0;p<v3.size();p++)
                                                                {
                                                                        //cDetails=new CourseUserDetail();
                                                                        TurbineUserGroupRole element=(TurbineUserGroupRole)v3.get(p);
                                                                        //Here 's' is the user_id
                                                                        s=Integer.toString(element.getUserId());
									//ErrorDumpUtil.ErrorLog("s========="+s);
                                                                        if(s.equals(student_id)){
                                                                                searchId = s;
                                                                                searchId1 = student_id;
                                                                        }
                                                                }//for
                                                        }

                                                        if(searchId.equals(searchId1))
                                                        {
                                                                crit = new Criteria();
                                                                crit.add(TelephoneDirectoryPeer.USER_ID,user_id);
                                                                v = TelephoneDirectoryPeer.doSelect(crit);
                                                                for(m=0; m<v.size(); m++)
                                                                {
	                                                                //pname = ((TelephoneDirectory)v.get(m)).getName();
                                                                        mail_id = ((TelephoneDirectory)v.get(m)).getMailId();
									//ErrorDumpUtil.ErrorLog("mail_id========="+mail_id);
                                                                        mobile_no = ((TelephoneDirectory)v.get(m)).getMobileNo();
									//ErrorDumpUtil.ErrorLog("mobile_no====="+mobile_no);
                                                                }

								crit = new Criteria();
	                                                        crit.add(TurbineUserPeer.USER_ID,user_id);
                                                                v3 = TurbineUserPeer.doSelect(crit);
               	                                                for(m=0; m<v3.size(); m++)
                       	                                        {
                               	                                       // pname = ((TelephoneDirectory)v3.get(m)).getName();
                                       	                                fname = ((TurbineUser)v3.get(m)).getFirstName();
									//ErrorDumpUtil.ErrorLog("fname====="+fname);
                                               	                        lname = ((TurbineUser)v3.get(m)).getLastName();
									//ErrorDumpUtil.ErrorLog("lname====="+lname);
                                                       	        }
                                                                       cuDetail.setEmail(email);
                                                                       cuDetail.setUserName(name);
	                                                               cuDetail.setParentFirstName(fname);
									cuDetail.setParentLastName(lname);
                                                                        cuDetail.setParentEmail(mail_id);
                                                                        cuDetail.setParentMobileNo(mobile_no);
                                                                        cuDetail.setRollNo(roll_no);
									cuDetail.setParentId(user_id);
	                                                                cuDetail.setUserId(student_id);
                                                                        Details.add(cuDetail);

                                                         }//if
                                                }
                                        }
                                }

                        }//query 'ParentEmail'
                        else if(user_query.equals("StudentEmail"))
                        {
			/**
				cuDetail = new Details();
				if(mode == 'inst_admin'){
                                 	select roll_no from student_rollno where email_id = searchString and institute_id = instituteId;
					if(roll_no != null)
                                }
                                else{
                                        get rows from 'TurbineUserGroupRole' table
                                        where role is 'student' for the given group_id
                                        // we get student's id in st_id
                                  	select user_id from turbine_user where email = searchString;
					if(user_id == st_id)	
                                }
			        //select institute_id, roll_no from student_rollno where email_id = searchString;
				//select roll_no from student_rollno where email_id = searchString and institute_id = instituteId;
			        //if(institute_id == instituteId)
				//if(roll_no != null)
			        {
			                select user_id, firstname + '' + lastname as student_name from turbine_user where email = searchString;
			                select parent_id from parent_info where student_id = user_id;
			                select name, mail_id, mobile_no from telephone_directory where user_id = parent_id;
			                cuDetail.setParentName(name);
			                cuDetail.setParentEmail(mail_id); 
			                cuDetail.setPrentPhone(mobile_no);
			                cuDetail.setStudentName(student_name);
			                cuDetail.setStudentEmail(searchString);
			                cuDetail.setStudentRollNo(roll_no);
			        }
			        Details.add(cuDetail);
			*/
				//ErrorDumpUtil.ErrorLog("search string ->>>"+searchString);
				criteria = new Criteria();
				if(searchFor.equals("inst_admin")){
	                                //criteria2=new Criteria();
                                        criteria.add(StudentRollnoPeer.EMAIL_ID,searchString);
					criteria.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                                        v = StudentRollnoPeer.doSelect(criteria);
                                        for(i=0;i<v.size();i++)
                                        {
         	                               roll_no = ((StudentRollno)v.get(i)).getRollNo();
                                               //ErrorDumpUtil.ErrorLog("student's roll no "+roll_no);
                                               if(!roll_no.equals("")){
	                                               searchId = "true";
                                                       searchId1 = "true";
                                               }
                                        }
                                 }
                                 else{
                                        //searchFor = "instructor"
                                        //criteria2 = new Criteria();
                                        criteria.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                                        criteria.and(TurbineUserGroupRolePeer.ROLE_ID,"3");
                                        criteria.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
                                        v=TurbineUserGroupRolePeer.doSelect(criteria);
					//ErrorDumpUtil.ErrorLog("v.size()==="+v.size());
                                        for(i=0;i<v.size();i++)
                                        {
           	                                //cDetails=new CourseUserDetail();
                                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                                //Here 's' is the user_id
                                                s=Integer.toString(element.getUserId());
						//ErrorDumpUtil.ErrorLog("s===="+s);
						criteria1 = new Criteria();
						criteria1.add(TurbineUserPeer.EMAIL,searchString);
						v1 = TurbineUserPeer.doSelect(criteria1);
						//ErrorDumpUtil.ErrorLog("v1.size()==="+v1.size());
						for(j=0;j<v1.size();j++)
						{
							userId = Integer.toString(((TurbineUser)v1.get(j)).getUserId());
							//ErrorDumpUtil.ErrorLog("userId===="+userId);
						}
                                                //if(s.equals(user_id)){
						if(s.equals(userId)){
                 	                        	searchId = s;
                                                        searchId1 = userId;
                                                }
                                        }//for
                                 }

				cuDetail=new CourseUserDetail();
                                        //if(institute_id.equals(instituteId))
				if(searchId.equals(searchId1))
                                {
	                                query = "select USER_ID, FIRST_NAME, LAST_NAME from TURBINE_USER where EMAIL = '"+searchString+"'";
                                        student = TurbineUserPeer.executeQuery(query);
                                        if(student.size()>0)
                                        {
         	                               for(ListIterator k = student.listIterator();k.hasNext();)
                                               {
                 	                                Record item1 = (Record)k.next();
                                                        student_id = item1.getValue ("USER_ID").asString();
							//ErrorDumpUtil.ErrorLog("student id->>>>>"+student_id);
                                                        fname = item1.getValue ("FIRST_NAME").asString();
							//ErrorDumpUtil.ErrorLog("student fname ->>>"+fname);
                                                        lname = item1.getValue ("LAST_NAME").asString();
							//ErrorDumpUtil.ErrorLog("student lname ->>>"+lname);
                                               }
                                               name = fname+ " "+ lname;
					}

                                        criteria1 = new Criteria();
					criteria1.add(ParentInfoPeer.STUDENT_ID,student_id);
                                        v1 = ParentInfoPeer.doSelect(criteria1);
                                        for(j=0; j<v1.size(); j++)
                                        {
                                        	Id = ((ParentInfo)v1.get(j)).getId();
					}
                                
					//ErrorDumpUtil.ErrorLog("Id->>>>>>"+Id);                
					//criteria1.add(ParentInfoPeer.STUDENT_ID,student_id);
					criteria1 = new Criteria();
					criteria1.add(ParentInfoPeer.ID,Id);
                                        v1 = ParentInfoPeer.doSelect(criteria1);
                                        for(j=0; j<v1.size(); j++)
                                        {
                                        	userId = ((ParentInfo)v1.get(j)).getParentId();
						user_id = Integer.parseInt(userId);
						//ErrorDumpUtil.ErrorLog("parent id ->>>"+user_id);
                                                criteria2 = new Criteria();
                                                criteria2.add(TelephoneDirectoryPeer.USER_ID, user_id);
                                                v2 = TelephoneDirectoryPeer.doSelect(criteria2);
                                                for(m=0; m<v2.size();m++)
                                                {
                                                	// pname = ((TelephoneDirectory)v.get(m)).getName();
                                                        mail_id = ((TelephoneDirectory)v2.get(m)).getMailId();	
							//ErrorDumpUtil.ErrorLog("parent mail ->>>"+mail_id);
                                                        mobile_no = ((TelephoneDirectory)v2.get(m)).getMobileNo();
							//ErrorDumpUtil.ErrorLog("parent mobile ->>>"+mobile_no);
						}
			
						criteria2 = new Criteria();
                                                criteria2.add(TurbineUserPeer.USER_ID, user_id);
                                                v2 = TurbineUserPeer.doSelect(criteria2);
                                                for(m=0; m<v2.size();m++)
                                                {
                                                	// pname = ((TelephoneDirectory)v.get(m)).getName();
                                                        fname = ((TurbineUser)v2.get(m)).getFirstName();
							//ErrorDumpUtil.ErrorLog("parent fname ->>>"+fname);
                                                        lname = ((TurbineUser)v2.get(m)).getLastName();
							//ErrorDumpUtil.ErrorLog("parent lname ->>>"+lname);
						}
                                                cuDetail.setEmail(searchString);
                                                cuDetail.setUserName(name);
                                                cuDetail.setParentFirstName(fname);
						cuDetail.setParentLastName(lname);
                                                cuDetail.setParentEmail(mail_id);
                                                cuDetail.setParentMobileNo(mobile_no);
                                                cuDetail.setRollNo(roll_no);
						cuDetail.setParentId(user_id);
                                                cuDetail.setUserId(student_id);
                                                Details.add(cuDetail);
                                       }
                                }
                        }//query 'StudentEmail'
                        else if(user_query.equals("StudentRollNo"))
                        {
			/**
			        cuDetail = new Details();
			        //select institute_id, email_id from student_rollno where roll_no = searchString;
				//select email_id from student_rollno where roll_no = searchString and institute_id = instituteId;
				if(mode == 'inst_admin'){
                                        select email_id from student_rollno where roll_no = searchString and institute_id = instituteId;
                                        if(email_id != null)
                                }
                                else{
                                        get rows from 'TurbineUserGroupRole' table
                                        where role is 'student' for the given group_id
                                        // we get student's id in st_id
					select email_id from student_rollno where roll_no = searchString and institute_id = instituteId;
                                        select user_id from turbine_user where email = email_id;
                                        if(user_id == st_id)    
                                }

			        //if(institute_id == instituteId)
				//if(email_id != null)
			        {
			                select user_id, firstname + '' + lastname as student_name from turbine_user where email = email_id;
			                select parent_id from parent_info where student_id = user_id;
			                select name, mail_id, mobile_no from telephone_directory where user_id = parent_id;
			                cuDetail.setParentName(name);
			                cuDetail.setParentEmail(mail_id); 
			                cuDetail.setPrentPhone(mobile_no);
			                cuDetail.setStudentName(student_name);
			                cuDetail.setStudentEmail(email_id);
			                cuDetail.setStudentRollNo(searchString);
			        }
			        Details.add(cuDetail);
			*/
				criteria = new Criteria();
				/*String g_name = GroupUtil.getGroupName(Integer.parseInt(g_id));
                                criteria.add(CourseProgramPeer.EMAIL_ID,eMail);
                                criteria.add(CourseProgramPeer.COURSE_ID,g_name);
                                v1 = CourseProgramPeer.doSelect(crit1);
                                for(k=0;k<v1.size();k++)
                                {
  	                              prog_code = ((CourseProgram)v1.get(k)).getProgramCode();
                                }*/

				if(searchFor.equals("inst_admin")){
                                        //criteria2=new Criteria();
					//ErrorDumpUtil.ErrorLog("i reached inside if");
                                        criteria.add(StudentRollnoPeer.ROLL_NO,searchString);
                                        criteria.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                                        v = StudentRollnoPeer.doSelect(criteria);
                                        for(i=0;i<v.size();i++)
                                        {
                                               mail_id = ((StudentRollno)v.get(i)).getEmailId();
                                               //ErrorDumpUtil.ErrorLog("student's email id "+mail_id);
                                               if(!mail_id.equals(null)){
                                                       searchId = "true";
                                                       searchId1 = "true";
                                               }
                                        }
                                 }
                                 else{
                                        //searchFor = "instructor"
                                        //criteria2 = new Criteria();
					//ErrorDumpUtil.ErrorLog("i reached inside else");
                                        criteria.add(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                                        criteria.and(TurbineUserGroupRolePeer.ROLE_ID,"3");
                                        criteria.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
                                        v=TurbineUserGroupRolePeer.doSelect(criteria);
					//ErrorDumpUtil.ErrorLog("v.size()==="+v.size());
                                        for(i=0;i<v.size();i++)
                                        {
                                                criteria = new Criteria();
                                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                                //Here 's' is the user_id fetched from TurbineUserGroupRole 
                                                s=Integer.toString(element.getUserId());
						//ErrorDumpUtil.ErrorLog("s==="+s);
						//ErrorDumpUtil.ErrorLog("roll no==="+searchString);
						//ErrorDumpUtil.ErrorLog("institute id==="+instituteId);
						//criteria.add(StudentRollnoPeer.ID,s);
						criteria.add(StudentRollnoPeer.ROLL_NO,searchString);
                                	        criteria.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                        	                v1 = StudentRollnoPeer.doSelect(criteria);
						//ErrorDumpUtil.ErrorLog("v1.size()==="+v1.size());
                	                        for(j=0;j<v1.size();j++)
        	                                {
	                                               mail_id = ((StudentRollno)v1.get(j)).getEmailId();
							//ErrorDumpUtil.ErrorLog("mail_id---"+mail_id);
						}

                                                criteria = new Criteria();
                                                criteria.add(TurbineUserPeer.EMAIL,mail_id);
                                                v1 = TurbineUserPeer.doSelect(criteria);
                                                for(j=0;j<v1.size();j++)
                                                {
                                                        userId = Integer.toString(((TurbineUser)v1.get(j)).getUserId());
							//ErrorDumpUtil.ErrorLog("user id from turbine user-----"+userId);
                                                }
                                                if(s.equals(userId)){
                                                        searchId = s;
                                                        searchId1 = userId;
							break;
                                                }
                                        }//for
                                 }
                                /*
                                criteria = new Criteria();
                                criteria.add(StudentRollnoPeer.ROLL_NO,searchString);
				criteria.add(StudentRollnoPeer.INSTITUTE_ID, instituteId);
                                v = StudentRollnoPeer.doSelect(criteria);
                                for(i=0;i<v.size();i++)
                                {
                                       // institute_id = ((StudentRollno)v.get(i)).getInstituteId();
                                        email = ((StudentRollno)v.get(i)).getEmailId();
					cuDetail=new CourseUserDetail();
                                       // if(institute_id.equals(instituteId))*/
				cuDetail=new CourseUserDetail();
				if(searchId.equals(searchId1))
                                {
	                                query = "select USER_ID, FIRST_NAME, LAST_NAME from TURBINE_USER where EMAIL = '"+mail_id+"'";
                                        student = TurbineUserPeer.executeQuery(query);
                                        if(student.size()>0)
                                        {
          	                              for(ListIterator k = student.listIterator();k.hasNext();)
                                              {
                	                              Record item1 = (Record)k.next();
                                                      student_id = item1.getValue ("USER_ID").asString();
							//ErrorDumpUtil.ErrorLog("userId=="+student_id);
                                                      fname = item1.getValue ("FIRST_NAME").asString();
							//ErrorDumpUtil.ErrorLog("stu fname==="+fname);
                                                      lname = item1.getValue ("LAST_NAME").asString();
							//ErrorDumpUtil.ErrorLog("stu lanem=="+lname);
                                              }
                                              name = fname+ " "+ lname;
                                        }

                                        /*criteria1 = new Criteria();
					criteria1.add(ParentInfoPeer.STUDENT_ID,student_id);
                                        v1 = ParentInfoPeer.doSelect(criteria1);
                                        for(j=0; j<v1.size(); j++)
                        	        {
                                	        Id = ((ParentInfo)v1.get(j)).getId();
                                        }

					ErrorDumpUtil.ErrorLog("parent Id ->>>>>>"+Id);*/
                                        //criteria1.add(ParentInfoPeer.STUDENT_ID, student_id);
					criteria1 = new Criteria();
                                        criteria1.add(ParentInfoPeer.STUDENT_ID, student_id);
                                        v1 = ParentInfoPeer.doSelect(criteria1);
                                        for(j=0;j<v1.size();j++)
                                        {
                                        	userId = ((ParentInfo)v1.get(j)).getParentId();
						user_id = Integer.parseInt(userId);
						//ErrorDumpUtil.ErrorLog("parent id=="+user_id);
                                                criteria2 = new Criteria();
                                                criteria2.add(TelephoneDirectoryPeer.USER_ID, user_id);
                                                v2 = TelephoneDirectoryPeer.doSelect(criteria2);
                                                for(m=0; m<v2.size();m++)
                                                {
                                                	//pname = ((TelephoneDirectory)v.get(m)).getName();
                                                        email = ((TelephoneDirectory)v2.get(m)).getMailId();
                                                        mobile_no = ((TelephoneDirectory)v2.get(m)).getMobileNo();
						}

						criteria2 = new Criteria();
                                                criteria2.add(TurbineUserPeer.USER_ID, user_id);
                                                v2 = TurbineUserPeer.doSelect(criteria2);
                                                for(m=0; m<v2.size();m++)
                                                {
                                                	fname = ((TurbineUser)v2.get(m)).getFirstName();
                                                        lname = ((TurbineUser)v2.get(m)).getLastName();
                                                        // mobile_no = ((TelephoneDirectory)v.get(m)).getMobileNo();
                                                }

                                                cuDetail.setEmail(mail_id);
                                                cuDetail.setUserName(name);
                                                cuDetail.setParentFirstName(fname);
						cuDetail.setParentLastName(lname);
                                                cuDetail.setParentEmail(email);
                                                cuDetail.setParentMobileNo(mobile_no);
                                                cuDetail.setRollNo(searchString);
						cuDetail.setParentId(user_id);
                                                cuDetail.setUserId(student_id);
                                                Details.add(cuDetail);
					}
                                }
                        }//query 'StudentRollNo'
                        else
                        {
                                String ErrorMsg="The search string you have entered is invalid";
                                Details.add(ErrorMsg);
                        }

                }
                catch(Exception e)
                {
                        String searchMsg="The error in method:getParentListBySearchString of class:ParentManagementUtil "+e;
			ErrorDumpUtil.ErrorLog("The error in method:getParentListBySearchString of class:ParentManagementUtil "+e);
                        //Details.add(searchMsg);
                }
                return Details;
        }//method

	/**
	 * This method creates list of parents
	 * to be displayed, for the given institute.
	 */
	public static Vector getListOfAllParents(String mode, String instituteId)
	{
		String email = "", rollNo = "", fname="", lname="";
                String parent_id="", mobile="", parent_email="";
                String query="", name="";
                List parent =null;
                String student_id="";
                int i=0, j=0, k=0;
                Vector Details=new Vector();
                List v = null;
                List v1 = null;
                List v2 = null, v3=null;
		Criteria criteria, criteria1, criteria2;
		//ErrorDumpUtil.ErrorLog("inside parent util. mode = "+mode);
		try{
			if(mode.equals("All"))
			{
				Criteria crit=new Criteria();
				// Student needs to be selected on the basis of 
				// roll_no.program.institute_id combination
                		crit.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
                		v = StudentRollnoPeer.doSelect(crit);
//                		CourseUserDetail cuDetail=new CourseUserDetail();
                		for(i=0;i<v.size();i++)
                		{
					//ErrorDumpUtil.ErrorLog("size of v="+v.size());
 	                		CourseUserDetail cuDetail=new CourseUserDetail();
                        		email=((StudentRollno)v.get(i)).getEmailId();

                        		cuDetail.setEmail(email);
					//ErrorDumpUtil.ErrorLog("email = "+email);
                        		query ="select * from PARENT_INFO join TURBINE_USER on PARENT_INFO.STUDENT_ID=TURBINE_USER.USER_ID where TURBINE_USER.LOGIN_NAME='"+email+"'";
                        		parent = TurbineUserPeer.executeQuery(query);
                        		//ErrorDumpUtil.ErrorLog("before for loop");
                        		if(parent.size()>0)
					{
						for(ListIterator l = parent.listIterator();l.hasNext();)
                             			{
                	             			Record item = (Record)l.next();
                                     			parent_id = item.getValue ("PARENT_ID").asString();
                                     			//student_id = item.getValue ("STUDENT_ID").asString();
						}//for
				
						criteria1=new Criteria();
                                        	criteria1.add(TelephoneDirectoryPeer.USER_ID,parent_id);
                                        	v2 = TelephoneDirectoryPeer.doSelect(criteria1);
                                        	for(k=0;k<v2.size();k++)
                                        	{
	                                                //ErrorDumpUtil.ErrorLog("size of v2="+v2.size());
	                                                parent_email=((TelephoneDirectory)v2.get(k)).getMailId();
	                                                //ErrorDumpUtil.ErrorLog("parent email = "+parent_email);
	                                                cuDetail.setParentEmail(parent_email);
	                                                mobile=((TelephoneDirectory)v2.get(k)).getMobileNo();
	                                                //ErrorDumpUtil.ErrorLog("parent mobile = "+mobile);
	                                                cuDetail.setParentMobileNo(mobile);
        	                                        //set parent userId
	                                                int parentId = Integer.parseInt(parent_id);
	                                                //ErrorDumpUtil.ErrorLog("parent id = "+parentId);
	                                                cuDetail.setParentId(parentId);
        	                                }

	                                        criteria2 = new Criteria();
        	                                criteria2.add(TurbineUserPeer.USER_ID,parent_id);
	                                        v3 = TurbineUserPeer.doSelect(criteria2);
	                                        for(k=0;k<v3.size();k++)
        	                                {
	                                                //ErrorDumpUtil.ErrorLog("size of v3="+v3.size());
	                                                fname = ((TurbineUser)v3.get(k)).getFirstName();
	                                                //ErrorDumpUtil.ErrorLog("parent first name "+fname);
	                                                lname = ((TurbineUser)v3.get(k)).getLastName();
	                                                //ErrorDumpUtil.ErrorLog("parent last name "+lname);
	                                                cuDetail.setParentFirstName(fname);
	                                                cuDetail.setParentLastName(lname);
        	                                }
					}//if
					else{
						cuDetail.setParentEmail("");
						cuDetail.setParentMobileNo("");
						String blank = null;
						int number = 0;
						try
						{
						    number = Integer.parseInt(blank);
						}
						catch (Exception e)
						{
						    number = 0;
						}
						//cuDetail.setParentId("");
						cuDetail.setParentId(number);
						cuDetail.setParentFirstName("");
						cuDetail.setParentLastName("");
					}
	
					criteria=new Criteria();
                      			//criteria.add(TurbineUserPeer.USER_ID,student_id);
					criteria.add(TurbineUserPeer.EMAIL,email);
                       			v1 = TurbineUserPeer.doSelect(criteria);
                       			for(j=0;j<v1.size();j++)
                       			{
						//ErrorDumpUtil.ErrorLog("size of v1="+v1.size());
                               			fname = ((TurbineUser)v1.get(j)).getFirstName();
                               			//ErrorDumpUtil.ErrorLog("student first name "+fname);
                              			lname = ((TurbineUser)v1.get(j)).getLastName();
                              			//ErrorDumpUtil.ErrorLog("student last name "+lname);
                               			name = fname+" "+lname;
                               			//ErrorDumpUtil.ErrorLog("student name "+name);
                               			cuDetail.setUserName(name);
						int studentId = ((TurbineUser)v1.get(j)).getUserId();
						student_id = Integer.toString(studentId);
						cuDetail.setUserId(student_id);
                        		}
                        		rollNo=((StudentRollno)v.get(i)).getRollNo();
                        		//ErrorDumpUtil.ErrorLog("student rol  no = "+rollNo);
                    		    	cuDetail.setRollNo(rollNo);
					//cuDetail.setUserId(student_id);
                        		Details.add(cuDetail);
				}//for
			}//if mode
			else{
/*	select first_name, last_name, email from turbine_user where user_id=mode;
	select mobile_no from telephone_directory where user_id = mode;
	cuDetail.setParentFirstName(first_name);		
	cuDetail.setParentLastName(last_name);
	cuDetail.setParentMobileNo(mobile_no);
	cuDetail.setParentEmail(email);
	Details.add(cuDetail);
 */
				CourseUserDetail cuDetail1=new CourseUserDetail();
				if(mode.equals("0"))
				{
					//CourseUserDetail cuDetail1=new CourseUserDetail();
					cuDetail1.setParentFirstName("");
					cuDetail1.setParentLastName("");
					cuDetail1.setParentEmail("");
					cuDetail1.setUserName("");
					cuDetail1.setParentMobileNo("");
					cuDetail1.setParentId(Integer.parseInt(mode));
					//its the student id
					cuDetail1.setUserId(instituteId);
				}
				else{
					//CourseUserDetail cuDetail1=new CourseUserDetail();
					criteria = new Criteria();
					criteria.add(TurbineUserPeer.USER_ID, mode);
					v1 = TurbineUserPeer.doSelect(criteria);
					for(i=0;i<v1.size();i++)						
					{
						fname = ((TurbineUser)v1.get(i)).getFirstName();
						lname = ((TurbineUser)v1.get(i)).getLastName();
						cuDetail1.setParentFirstName(fname);                
					        cuDetail1.setParentLastName(lname);
						parent_email = ((TurbineUser)v1.get(i)).getEmail();
						cuDetail1.setParentEmail(parent_email);
						parent_id = ((TurbineUser)v1.get(i)).getUserName();
						cuDetail1.setUserName(parent_id);
						cuDetail1.setParentId(Integer.parseInt(mode));
					}
					
					criteria1 = new Criteria();
					criteria1.add(TelephoneDirectoryPeer.USER_ID, mode);
					v2 = TelephoneDirectoryPeer.doSelect(criteria1);
					for(j=0;j<v2.size();j++)
					{
						mobile = ((TelephoneDirectory)v2.get(j)).getMobileNo();
						cuDetail1.setParentMobileNo(mobile);
					}
					//its the student id
					cuDetail1.setUserId(instituteId);
				}
				Details.add(cuDetail1);
			}//else mode
        	}//try
		catch(Exception e)
		{
			String searchMsg="The error in method:getListOfAllParents of class:ParentManagementUtil "+e;
                        Details.add(searchMsg);
		}                        

		return Details;
	}//method

	/**
	 * The method adds the parent details
	 * in the database if the parent id 
	 * has value 'null', otherwise the details
	 * of the parent are updated in the database
	 * for the given 'parent id'
	 */
	public static String updateParentDetails(String parent_id, String student_id, String LangFile, String fname, String lname, String email, String mobile)
	//public static String updateParentDetails(String parent_id, String LangFile, String fname, String lname, String email, String mobile)
	{
               // String email = "", rollNo = "", fname="", lname="";
               // String parent_id="", mobile="", parent_email="";
                String name="";
		String message="";
               /* List parent =null;
                String student_id="";
                int i=0, j=0, k=0;
                List v = null;
                List v1 = null;
                List v2 = null, v3=null;
                */
                Criteria crit;
                
		try{
		
			/* parent_id is null, thus details will be added*/
			if(parent_id.equals("0"))
			{
				//LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(language);
				String passwd = email;
        	                String []starr = passwd.split("@");
	                        passwd = starr[0];
				/**
                                * for creating user profile use UserManagement util.
                                * @see UserManagement util in utils 
                                */
                                String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,"",email,"general","parent",LangFile,"","","act");
				//get language 
				//String msg=UserManagement.CreateUserProfile(email,passwd,fname,lname,"",email,"general","parent","","","","act");
				/*
                                * The Code is Responsible For inserting data into ParentInfo Table
                                */
                                int parentId = UserUtil.getUID(email);
                                if(parentId!=-1){
                                        Criteria s = new Criteria();
                                        s.add(ParentInfoPeer.PARENT_ID,parentId);
                                        List lst = ParentInfoPeer.doSelect(s);
                                        if(lst.size()==0){
                                                crit = new Criteria();
                                                crit.add(ParentInfoPeer.PARENT_ID,Integer.toString(parentId));
                                                crit.add(ParentInfoPeer.STUDENT_ID,student_id);
                                                ParentInfoPeer.doInsert(crit);

                                                String fullName=UserUtil.getFullName(parentId);
						String address="";
						
						//Get student's address
						crit = new Criteria();
                                                crit.add(TelephoneDirectoryPeer.USER_ID,student_id);
                                                List studentAdd = TelephoneDirectoryPeer.doSelect(crit);
						for(int i =0;i<studentAdd.size();i++)
						{
							address = ((TelephoneDirectory)studentAdd.get(i)).getAddress();
						}

                                                crit = new Criteria();
                                                crit.add(TelephoneDirectoryPeer.USER_ID,parentId);
                                                List UserExist = TelephoneDirectoryPeer.doSelect(crit);
                                                if(UserExist.size()>0)
                                                {
                                                        int existParntid = ((TelephoneDirectory)UserExist.get(0)).getId();
                                                        Criteria tele = new Criteria();
                                                        tele.add(TelephoneDirectoryPeer.ID,existParntid);
                                                        tele.add(TelephoneDirectoryPeer.NAME,fullName );
							//get address
                                                        tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, mobile);
                                                        TelephoneDirectoryPeer.doUpdate(tele);
                                                }
                                                else
                                                {
                                                        Criteria tele = new Criteria();
                                                        tele.add(TelephoneDirectoryPeer.USER_ID,parentId);
                                                        tele.add(TelephoneDirectoryPeer.MAIL_ID,email);
                                                        tele.add(TelephoneDirectoryPeer.NAME,fullName );
							//get address
                                                        tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, mobile);
                                                        TelephoneDirectoryPeer.doInsert(tele);
                                                }

                                        }
                                   //     data.setMessage(msg);
                                }//if
			}//if
			else{
				/*
				parent_id = (int)parent_info;
				name = fname+' '+lname;
				update table turbine_user set email=email, fname=fname, lname=lname, modified = current_timestamp where user_id = parent_id;
				update table parent_info set name = name, mail_id = email, mobile_no = mobile where user_id = parent_id; 				
				*/
	                        Date date=new Date();
				Timestamp current_timestamp = new Timestamp(date.getTime());
				int id = 0;
				int parentId = Integer.parseInt(parent_id);
       				//ErrorDumpUtil.ErrorLog("inside edit parent util");
				//ErrorDumpUtil.ErrorLog(current_timestamp+" "+parentId+" "+fname+" "+lname+" "+email);
		               	crit=new Criteria();
				crit.add(TurbineUserPeer.USER_ID, parentId);
	                        crit.add(TurbineUserPeer.FIRST_NAME,fname);
	                        crit.add(TurbineUserPeer.LAST_NAME,lname);
        	                crit.add(TurbineUserPeer.EMAIL,email);
	                        crit.add(TurbineUserPeer.MODIFIED,current_timestamp);
	                        TurbineUserPeer.doUpdate(crit);
		
				name = fname+ ' '+lname;
				//ErrorDumpUtil.ErrorLog("before inserting data in telephone directory "+name+" "+mobile);
				crit=new Criteria();
				crit.add(TelephoneDirectoryPeer.USER_ID, parentId);
				List user = TelephoneDirectoryPeer.doSelect(crit);
				for(int p=0;p<user.size();p++)
				{
					id = ((TelephoneDirectory)user.get(p)).getId();
				}

				crit = new Criteria();
                        	crit.add(TelephoneDirectoryPeer.ID, id);
				crit.add(TelephoneDirectoryPeer.USER_ID, parentId);
	                        crit.add(TelephoneDirectoryPeer.NAME,name);
        	                crit.add(TelephoneDirectoryPeer.MOBILE_NO,mobile);
                	        crit.add(TelephoneDirectoryPeer.MAIL_ID,email);
	                        TelephoneDirectoryPeer.doUpdate(crit);
	
                        	message="Details updated successfully";
			}//else
		}
		catch(Exception e)
		{
			String searchMsg="The error in method:updateParentDetails of class:ParentManagementUtil "+e;
			ErrorDumpUtil.ErrorLog(searchMsg);
			message= "Error updating parent details";
		}
		return message;
	}//method

	public static Vector getParentsGroupwise(String mode, String instituteId, String groupId)
	{
		Vector pdetails = new Vector();
		// '3' is the role_id of 'student'
		int role_id = 3;
		List v=null;
                List v1=null;
                int [] uId={1,0};
		int i, j, k;
		List st = null;
		List st1 = null;
		Criteria crit;
		Criteria crit1;
		String roll_no="", s="", eMail="", prog_code="";
		String userName="", fname="", lname="";
		String name="", p_email="", mobile="", parent_id="";	
	
		try
		{
			/*
			  get rows from 'TurbineUserGroupRole' table
			  where role is 'student' for the given group_id
			*/
			crit=new Criteria();
                        //crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
			crit.add(TurbineUserGroupRolePeer.GROUP_ID,groupId);
                        crit.and(TurbineUserGroupRolePeer.ROLE_ID,role_id);
                        crit.andNotIn(TurbineUserGroupRolePeer.USER_ID,uId);
                        v=TurbineUserGroupRolePeer.doSelect(crit);						
		}
                catch (Exception ex)
                {
                        ErrorDumpUtil.ErrorLog("The error in try1 of method:getParentsGroupwise() of ParentManagementUtil"+ex);
                }

                try{
			crit1 = new Criteria();
			
                        CourseUserDetail cDetails;
                        for(i=0;i<v.size();i++)
                        {
				cDetails=new CourseUserDetail();
				TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                //Here 's' is the user_id
                                s=Integer.toString(element.getUserId());
				roll_no = "";
				//Get parent details
                                crit= new Criteria();
                                crit.add(ParentInfoPeer.STUDENT_ID,s);
                                st = ParentInfoPeer.doSelect(crit);
                                for(j=0;j<st.size();j++)
                                {
                                        parent_id = ((ParentInfo)st.get(j)).getParentId();
                                //}	
					cDetails.setParentId(Integer.parseInt(parent_id));
	                        	crit1 = new Criteria();
                                	crit1.add(TurbineUserPeer.USER_ID, parent_id);
                                	v1 = TurbineUserPeer.doSelect(crit1);
                                	for(k=0;k<v1.size();k++)
                                	{
                                        	fname = ((TurbineUser)v1.get(k)).getFirstName();
                                        	lname = ((TurbineUser)v1.get(k)).getLastName();
	                                        cDetails.setParentFirstName(fname);
        	                                cDetails.setParentLastName(lname);
                	                        p_email = ((TurbineUser)v1.get(k)).getEmail();
                        	                cDetails.setParentEmail(p_email);
                               		}

	                                crit1 = new Criteria();
        	                        crit1.add(TelephoneDirectoryPeer.USER_ID, parent_id);
	                                v1 = TelephoneDirectoryPeer.doSelect(crit1);
        	                        for(j=0;j<v1.size();j++)
	                                {
        	                                mobile = ((TelephoneDirectory)v1.get(j)).getMobileNo();
                                        	cDetails.setParentMobileNo(mobile);
	                                }

//                        	TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                //Here 's' is the user_id
  //                              s=Integer.toString(element.getUserId());

					//Get all details for given user id
                	                st1=UserManagement.getUserDetail(s);
                        	        for(j=0;j<st1.size();j++)
                                	{
	                                	TurbineUser element1=(TurbineUser)st1.get(j);
        	                        	eMail=element1.getEmail();
                	                	//CourseUserDetail cDetails=new CourseUserDetail();
                        		       	userName=UserUtil.getFullName(Integer.parseInt(s)); // Get User Full Name
                                	
						//set students name, email
        	                        	cDetails.setUserName(userName);
                	                	cDetails.setEmail(eMail);
					
						//get student roll_no
						String g_name = GroupUtil.getGroupName(Integer.parseInt(groupId));
						crit1 = new Criteria();
						crit1.add(CourseProgramPeer.EMAIL_ID,eMail);
						//crit1.add(CourseProgramPeer.COURSE_ID,groupId);
						crit1.add(CourseProgramPeer.COURSE_ID,g_name);
						v1 = CourseProgramPeer.doSelect(crit1);
						for(k=0;k<v1.size();k++)
						{
							prog_code = ((CourseProgram)v1.get(k)).getProgramCode();
						}
					
                                		//String  stRlNo=CourseProgramUtil.getUserRollNo(userName,GroupUtil.getGroupName(Integer.parseInt(groupId)));
						//ErrorDumpUtil.ErrorLog("student's email in group wise parent list==== "+eMail);
						//ErrorDumpUtil.ErrorLog("student's program code in group wise parent list==== "+prog_code);
						//ErrorDumpUtil.ErrorLog("student's inst id in group wise parent list==== "+instituteId);
						//ErrorDumpUtil.ErrorLog("student's group name in group wise parent list==== "+g_name);
						crit1 = new Criteria();
						crit1.add(StudentRollnoPeer.EMAIL_ID,eMail);
						crit1.add(StudentRollnoPeer.PROGRAM,prog_code);
						crit1.add(StudentRollnoPeer.INSTITUTE_ID,instituteId);
						v1 = StudentRollnoPeer.doSelect(crit1);
						for(k=0;k<v1.size();k++)
                                	        {
                                        	        roll_no = ((StudentRollno)v1.get(k)).getRollNo();
	                                        }	
						
						//set student roll_no
						//ErrorDumpUtil.ErrorLog("student's roll no in group wise parent list==== "+roll_no);
						cDetails.setRollNo(roll_no);
						cDetails.setUserId(s);

                                	}//for
					pdetails.add(cDetails);
				}
				/*
				//Get parent details
				crit= new Criteria();
				crit.add(ParentInfoPeer.STUDENT_ID,s);
				st = ParentInfoPeer.doSelect(crit);
				for(j=0;j<st.size();j++)	
				{
					parent_id = ((ParentInfo)st.get(j)).getParentId();
				}

				crit = new Criteria();
                                crit.add(TurbineUserPeer.USER_ID, parent_id);
                                v1 = TurbineUserPeer.doSelect(crit);
                                for(k=0;k<v1.size();k++)
                                {
                                        fname = ((TurbineUser)v1.get(k)).getFirstName();
                                        lname = ((TurbineUser)v1.get(k)).getLastName();
                                        cDetails.setParentFirstName(fname);
                                        cDetails.setParentLastName(lname);
                                        p_email = ((TurbineUser)v1.get(k)).getEmail();
                                        cDetails.setParentEmail(p_email);
                               }

                                crit = new Criteria();
                                crit.add(TelephoneDirectoryPeer.USER_ID, parent_id);
                                v1 = TelephoneDirectoryPeer.doSelect(crit);
                                for(j=0;j<v1.size();j++)
                                {
                                        mobile = ((TelephoneDirectory)v1.get(j)).getMobileNo();
                                        cDetails.setParentMobileNo(mobile);
                                }
				pdetails.add(cDetails);*/
                        }//for	
		}
		catch(Exception e)
		{
                        String searchMsg="The error in method:getParentsGroupwise of class:ParentManagementUtil "+e;
                        ErrorDumpUtil.ErrorLog(searchMsg);
			String message= "Error displaying parent details";
                }
		return pdetails;
	}
}//class
