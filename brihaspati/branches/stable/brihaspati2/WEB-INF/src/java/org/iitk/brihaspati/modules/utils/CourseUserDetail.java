package org.iitk.brihaspati.modules.utils;
/*
 * @(#)CourseUserDetail.java
 *
 *  Copyright (c) 2006-2008.2010 ETRG,IIT Kanpur. 
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

/**
 *  In this class all details of Course and User are set and get in Velocity Screens
 *  @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 *  @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 *  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 *  @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista Bano</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 *  @modified date: 20-10-2010,3-11-2010, 23-12-2010, 13-07-2011
 *  @modified date: 09-08-2012 (Priyanka)
 */

import java.util.Date;
import java.sql.Timestamp;

public class CourseUserDetail 
{
	private String groupName;
	private String roleName;
	private String InsName;
	private String courseName;
	private String courseAlias;
	private String active;
	private String cguest;
	private String deptt;
	private String desc;
	private String createD;
	private Date lastModified;
	private String userName;
	private String loginName;
	private String userEmail;
	private String userRollno;
	private String Err_user;
	private String Err_type;
	private String CMsg;
	private String userProgm;
	private String Instnm;
	private int Instid;
	private String Prgcode;
	private String Prgnm;
	private int onlineconf;
	private int studsrlid;
	private String instAdminName;
	private String courseTime;
	private String moduleName;
	private String moduleTime;
	private String grpNameLeader;
	private String loginDate;
	private String loginTime;
	private int countLogins;
	private String activate;
	private String flag;
	/**
	 * Course details
	 */ 
	public void setGroupName(String gName)
	{
		this.groupName=gName;
	}
	public String getGroupName()
	{
		return groupName;
	}
	public void setRoleName(String rName)
	{
		this.roleName=rName;
	}
	public String getRoleName()
	{
		return roleName;
	}
	public void setInstructorName(String insName)
	{
		this.InsName=insName;
	}
	public String getInstructorName()
	{
		return InsName;
	}
	public void setLastModified(Date lastModified)
	{
		this.lastModified=lastModified;
	}
	public Date getLastModified()
	{
		return lastModified;
	}
	public boolean hasLastModifiedAfter(Timestamp lastVisited)
	{
		return lastModified.after(lastVisited);
	}
	public void setCourseName(String name)
	{
		this.courseName=name;
	}
	public String getCourseName()
	{
		return courseName;
	}
	public void setCAlias(String alias)
	{
		this.courseAlias=alias;
	}
	public String getCAlias()
	{
		return courseAlias;
	}
	public void setActive(String act)
	{
		this.active=act;
	}
	public String getActive()
	{
		return active;
	}

	public void setCGuest(String gst)
        {
                this.cguest=gst;
        }
        public String getCGuest()
        {
                return cguest;
        }

	public void setDept(String dept)
	{
		this.deptt=dept;
	}
	public String getDept()
	{
		return deptt;
	}
	public void setDescription(String desc)
	{
		this.desc=desc;
	}
	public String getDescription()
	{
		return desc;
	}
	public void setCreateDate(String Cdate)
	{
		this.createD=Cdate;
	}
	public String getCreateDate()
	{
		return createD;
	}
	/**
	 * User details
	 */ 
	public void setLoginName(String uname)
	{
		this.loginName=uname;
	}
	public String getLoginName()
	{
		return loginName;
	}
	public void setUserName(String name)
	{
		this.userName=name;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setEmail(String email)
	{
		this.userEmail=email;
	}
	public String getEmail()
	{
		return userEmail;
	}
	public void setRollNo(String rollno)
	{
		this.userRollno=rollno;
	}
	public String getRollNo()
	{
		return userRollno;
	}
	public void setProgm(String progm)
	{
		this.userProgm=progm;
	}
	public String getProgm()
	{
		return userProgm;
	}
	public void setStudsrid(int srid)
	{
		this.studsrlid=srid;
	}
	public int getStudsrid()
	{
		return studsrlid;
	}
//For Activation, added by Priyanka
	public void setActivation(String a_key)
        {
                this.activate=a_key;
        }
        public String getActivation()
        {
                return activate;
        }
	public void setFlag(String flag)
        {
                this.flag=flag;
        }
        public String getFlag()
        {
                return flag;
        }
//..........

	// Massages for Removal Case
	public void setErr_User(String Err_User)
	{
		this.Err_user=Err_User;
	}
	public String getErr_User()
	{
		return Err_user;
	}
	public void setErr_Type(String Err_Type)
	{
		this.Err_type=Err_Type;
	}
	public String getErr_Type()
	{
		return Err_type;
	}
	public void setCourse_Msg(String msg)
	{
		this.CMsg=msg;
	}
	public String getCourse_Msg()
	{
		return CMsg;
	}
	// for Program & InstituteId
	public void setInstName(String instnm)
        {
                this.Instnm=instnm;
        }
        public String getInstName()
        {
                return Instnm;
        }
	public void setInstId(int instid)
        {
                this.Instid=instid;
        }
        public int getInstId()
        {
                return Instid;
        }
	public void setPrgCode(String prgcode)
        {
                this.Prgcode=prgcode;
        }
        public String getPrgCode()
        {
                return Prgcode;
        }
	public void setPrgName(String prgnm)
        {
                this.Prgnm=prgnm;
        }
        public String getPrgName()
        {
                return Prgnm;
        }
	//added by sharad for course configuration for online user 10-02-2011
        public void setOnlineconf(int onlineconf)
        {
                this.onlineconf=onlineconf;
        }
        public int getOnlineconf()
        {
                return onlineconf;
        }
	// Shaista did Modification for mail Sending 
        public void setInstAdminName(String instAdminName)
        {
                this.instAdminName=instAdminName;
        }
        public String getInstAdminName()
        {
                return instAdminName;
        }
	public void setCourseTime(String courseTime)
        {
                this.courseTime=courseTime;
        }
        public String getCourseTime()
        {
                return courseTime;
        }
	 public void setModuleName(String moduleName)
        {
                this.moduleName=moduleName;
        }
        public String getModuleName()
        {
                return moduleName;
        }
	 public void setModuleTime(String moduleTime)
        {
                this.moduleTime=moduleTime;
        }
        public String getModuleTime()
        {
                return moduleTime;
        }

	public void setgrpNameLeader(String grpNameLeader)
        {
                this.grpNameLeader=grpNameLeader;
        }
        public String getgrpNameLeader()
        {
                return grpNameLeader;
        }
	public void setLoginDate(String loginDate)
        {
                this.loginDate=loginDate;
        }
        public String getLoginDate()
        {
                return loginDate;
        }
	public void setLoginTime(String loginTime)
        {
                this.loginTime=loginTime;
        }
        public String getLoginTime()
        {
                return loginTime;
        }
	public void setCountLogins(int countLogins)
        {
                this.countLogins=countLogins;
        }
        public int getCountLogins()
        {
                return countLogins;
        }



}
