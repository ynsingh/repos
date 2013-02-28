package org.iitk.brihaspati.modules.utils;

/* 
 *  @(#)CourseUtil.java
 *
 *  Copyright (c) 2004-2010,2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */
//Java classes
import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Turbine classes
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.util.security.AccessControlList;
//Brihaspati classes4
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.CoursesPeer;

/**
 *
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modify Date:- 07-02-2012
 */


public class CourseUtil{

	public static String getCourseName(String Course_id)
	{
		String CourseName=null;
		//List Course_list=null;
		try{
			Criteria crit=new Criteria();
			crit.add(CoursesPeer.GROUP_NAME,Course_id);
			List Course_list=CoursesPeer.doSelect(crit);
			CourseName=((Courses)Course_list.get(0)).getCname();
		}
		catch(Exception e){}
		return CourseName;
	}

	public static String getCourseId(String Course_name)
	{
		String CourseId=null;
		//List Course_list=null;
		try{
			Criteria crit=new Criteria();
			crit.add(CoursesPeer.CNAME,Course_name);
			List Course_list=CoursesPeer.doSelect(crit);
			CourseId=((Courses)Course_list.get(0)).getGroupName();
		}
		catch(Exception e){
		
			//log something
		}
		return CourseId;

	}
	public static String getCourseAlias(String Course_id)
	{
		String CAlias=null;
		List Course_list=null;
		try{
			Criteria crit=new Criteria();
			crit.add(CoursesPeer.GROUP_NAME,Course_id);
			Course_list=CoursesPeer.doSelect(crit);
			CAlias=((Courses)Course_list.get(0)).getGroupAlias();
		}
		catch(Exception e){
			
			//log something
		}
		return CAlias;
	}
	public static Date getLastModified(String courseId) throws Exception
	{
        	Criteria crit=new Criteria();
        	crit.add(CoursesPeer.GROUP_NAME,courseId);
        	List mainResult=CoursesPeer.doSelect(crit);

        	return (Date)((Courses)mainResult.get(0)).getLastmodified();

	}
// this method takes time
	public static boolean getCourseGuestStatus(String Course_id)
        {
		boolean gstat=false;
                try{
			User user=TurbineSecurity.getUser("guest");
			AccessControlList acl=TurbineSecurity.getACL(user);
			gstat=acl.hasRole("student",Course_id);
                }
                catch(Exception e){
			ErrorDumpUtil.ErrorLog("The Error in course Util Guest Status"+e);
                }
                return gstat;

        }

	/**
        * Get Group Name with the help of course alias(course id).
        **/
        public static Vector getCourseNameWithGAlias(String GAlias,String instId)
        {
                Vector GroupName=new Vector();
                try{
                        Criteria crit=new Criteria();
                        crit.add(CoursesPeer.GROUP_ALIAS,GAlias);
                        List Course_list=CoursesPeer.doSelect(crit);
			for(int i=0;i<Course_list.size();i++){
                        	String GName=((Courses)Course_list.get(i)).getGroupName();
				if(GName.endsWith(instId)){
				GroupName.add(GName);
				}
			}
                }
                catch(Exception e){
		 ErrorDumpUtil.ErrorLog("The Error in getting group name in course Util "+e);
		}
                return GroupName;
        }
/**
 *      This method is used for geting guest status in a course
 *      @param gid int GROUP_ID
 *	@exception Exception, a generic exception
 *	@return boolean
 */	
	public static boolean getCourseGuestStatus(int gid)
        {
                boolean gstat=false;
                try{
                        Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,0);
                        crit.and(TurbineUserGroupRolePeer.ROLE_ID,3);
                        crit.and(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List v=TurbineUserGroupRolePeer.doSelect(crit);
                        if(v.size()>0){
                                gstat=true;
                        }
                }
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The Error in course Util Guest Status in int gid "+e);
                }
                return gstat;

        }

	/**
 	 *  This method is used for instructor, Student and TA
 	 *  @param uid int USER_ID
 	 *  @param rid int ROLE_ID
 	 *  @see InstituteIdUtil from Utils
 	 *  @see CourseUtil from Utils
 	 *  @see CourseTimeUtil from Utils
 	 *  @see CourseManagement from Utils
 	 *  @exception Exception, a generic exception
	 *  This method is used for instructor, Student and TA
	 *  it returns list contains( Institute Name, Course name , Course Alias, Course Id, Course Status,
	 *  Course Weekly Access Time, Course Guest Status, Unread Message in Course)
	 */  
	public static ArrayList getCourseList(int uid,int rid)
	{
		ArrayList list = new ArrayList();
                Map map = new HashMap();
		String act="", statc="", unread="false";
		Vector iList=InstituteIdUtil.getInstructorInstId(uid,rid);
		for(int i=0;i<iList.size();i++)
                {
			String instIds=((String)iList.get(i)).trim();
			int instid=Integer.parseInt(instIds);
			String instName = InstituteIdUtil.getIstName(instid);
			map = new HashMap();
                        map.put("instName", instName);
                        map.put("instId", instid);
                        list.add(map);
			Criteria crit=new Criteria();
			try{
        	                crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                	        crit.and(TurbineUserGroupRolePeer.ROLE_ID,rid);
                        	List v=TurbineUserGroupRolePeer.doSelect(crit);
	                        for(int j=0;j<v.size();j++)
        	                {
                	                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(j);
                        	        int gid=(element.getGroupId());
                                	String gName=GroupUtil.getGroupName(gid);
					if(gName.endsWith(instIds)){
						String courseName=getCourseName(gName);
        		                        String Coursealias=getCourseAlias(gName);
                        		        String weekTime=CourseTimeUtil.getLastweekTime(uid,gName);
						String loginName=org.apache.commons.lang.StringUtils.substringBetween(gName, Coursealias,"_"+instid);
						int UID=UserUtil.getUID(loginName);
						String fullname=UserUtil.getFullName(UID);
                                        	boolean check_act=CourseManagement.CheckcourseIsActive(gid);
		                                if(check_act==false)
                		                        act="1";
                                		else
		                                        act="0";
                		                boolean gustst=getCourseGuestStatus(gid);
                                		if(gustst)
		                                        statc="true";
                		                else
                                		        statc="false";
						crit=new Criteria();
                                        	crit.add(NoticeReceivePeer.RECEIVER_ID,uid);
						crit.add(NoticeReceivePeer.GROUP_ID,gid);
                                        	crit.and(NoticeReceivePeer.READ_FLAG,"0");
						List v2=NoticeReceivePeer.doSelect(crit);
						if(v2.size()>0){
							unread="true";
						}
						else{
							unread="false";
						}
						map = new HashMap();
                                                map.put("crsId", gName);
                                                map.put("crsName", courseName);
                                                map.put("crsAlias", Coursealias);
                                                map.put("crswTime", weekTime);
                                                map.put("crsact", act);
                                                map.put("gustSts", statc);
                                                map.put("unreadM", unread);
                                                map.put("FullName",fullname);
                                                list.add(map);			
					}
				
				}
			}
			catch (Exception ex){ErrorDumpUtil.ErrorLog("The error in getCourseList in Course Util "+ex );}
		}
		return list;
	}
}
