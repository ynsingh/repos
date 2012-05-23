package org.iitk.brihaspati.modules.utils;

/*
 * @(#)DeleteInstituteUtil.java	
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
//Java classes
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import java.sql.Date;
import java.util.Vector;
import java.util.Properties;
import java.util.StringTokenizer;
//Turbine Classes
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.security.torque.om.TurbineUser;
//Brihaspati classes
import org.iitk.brihaspati.om.TurbineGroup;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;

import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.DbSendPeer;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.om.DbReceivePeer;
import org.iitk.brihaspati.om.NoticeSendPeer;
import org.iitk.brihaspati.om.NoticeReceivePeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.apache.torque.util.BasePeer;
import org.iitk.brihaspati.om.TurbineUserPeer;

/**
 *  This Class contains all information of Instructor and Student
 *  @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 *  @author <a href="mailto:palseema30@gmail.com">Seema Pal</a>
 *  @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 *  @author <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
 *  @Code tested by: (Sharad Singh,kishore kumar shukla)
 */

public class DeleteInstituteUtil
{
	/**
	*
	*/
	public static Vector AllUserinInstitute(String instId){
		List v=null;
		List lst=null;
		Vector alluid=new Vector();
		Vector evect=new Vector();
		try
		{
			/* get course list according to Institute Id*/
			Criteria crit=new Criteria();
                	crit.addGroupByColumn(org.iitk.brihaspati.om.TurbineGroupPeer.GROUP_NAME);
                	v=org.iitk.brihaspati.om.TurbineGroupPeer.doSelect(crit);
                	/**
                	*get group name one by one.and store in a vector whose GName ends with InstituteId.
                	*if Group Name not endswith InstituteId,then store "author" in that vector.
                	*replace this vector in a List .
                	*return List .
                	*/
                	for(int j=0;j<v.size();j++){
                        	String GName=((org.iitk.brihaspati.om.TurbineGroup)v.get(j)).getGroupName();
                                if(GName.endsWith(instId)){
                                	int gid=GroupUtil.getGID(GName);
                                	crit=new Criteria();
                                	crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                                	List m=TurbineUserGroupRolePeer.doSelect(crit);
                                	for(int l=0;l<m.size();l++){
                                        	TurbineUserGroupRole element=(TurbineUserGroupRole)m.get(l);
                                        	int UId=element.getUserId();
                                                alluid.add(UId);
                                	}
                                }
			}
                        crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instId);
                        List Iau=InstituteAdminUserPeer.doSelect(crit);
                        for(int i=0;i<Iau.size();i++)
                        {
                                InstituteAdminUser iauser=(InstituteAdminUser)Iau.get(i);
                                String email=iauser.getAdminUname();
				//ErrorDumpUtil.ErrorLog("email at line 131========="+email);
                                evect.add(email);
				//ErrorDumpUtil.ErrorLog("evect at line 133========="+evect);
                        }
                        for(int n=0;n<evect.size();n++)
                        {
                                String mail=evect.get(n).toString();
                                crit=new Criteria();
                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.LOGIN_NAME,mail);
                                lst=org.iitk.brihaspati.om.TurbineUserPeer.doSelect(crit);
                        	for(int k=0;k<lst.size();k++){
                                	org.iitk.brihaspati.om.TurbineUser tuser=(org.iitk.brihaspati.om.TurbineUser)lst.get(k);
                                	int userid=tuser.getUserId();
                                	alluid.add(userid);
                        	}
                        }
		}//try
		catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in AllUserinInstitute Method "+e);}
		return alluid;
	}
	/**
         * In this method,Check User Exists in this group. 
         * @param gid (Integer) 
         * @return boolean
         */
	public static boolean getUserExistsingroup(int gid)
	{
		Vector vtr=new Vector();
		boolean flag=false;
		try{
			/*get uid on the basis of particular gid*/
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List listt=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int m=0;m<listt.size();m++){
                                TurbineUserGroupRole tugrole=(TurbineUserGroupRole)listt.get(m);
                                int uid=tugrole.getUserId();
                                	vtr.add(uid);
                                
                        }
			/*if there is no user of this GID (flag return false in this condition)*/
			if(vtr.size()!=0)
				flag=true;
                }//try
		catch(Exception e){}
		return flag;	
	}

	/**
         * In this method,get User Exists in this group. 
         * @param gid (Integer) 
         * @return Vector 
         */
	public static Vector getUseringroup(int gid)
	{
		Vector vtr=new Vector();
		try{
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.GROUP_ID,gid);
                        List listt=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int m=0;m<listt.size();m++){
                                TurbineUserGroupRole tugrole=(TurbineUserGroupRole)listt.get(m);
                                int uid=tugrole.getUserId();
                                	vtr.add(uid);
                        }
                }//try
		catch(Exception e){}
		return vtr;
	}	
	/**
         * In this method,get Gid according to the uid. 
         * @param uid (Integer) 
         * @return List 
         */
	public static List getGId(int uid)
	{
		List vtr=null;
		int nogid []={1,3,4,5};
		try{
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
                        vtr=TurbineUserGroupRolePeer.doSelect(crit);
                }
		catch(Exception e){ErrorDumpUtil.ErrorLog("Error in get GIDmethod in utilDeleteInstituteUtil"+e);}
		return vtr;	
	}

	/**
	 *In this method we check that the user have any other course
	 *or in any other Institute.
	 */	

	public static boolean CourseUserExist(int uid)
	{
		boolean flag=false;
		Vector vtr=new Vector();
		int nogid []={1};
		try
		{
			/**Get all uid from TurbineUserGroupRole table
			*except general gid(1)
			*/
			Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
			crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
                        List listt=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int m=0;m<listt.size();m++){
                                TurbineUserGroupRole tugrole=(TurbineUserGroupRole)listt.get(m);
				int gid=tugrole.getGroupId();
					vtr.add(gid);
			}
			/* Return flag true if uid belongs to other Courses on the behalf of gid*/
			if(vtr.size()>1)
				flag=true;
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in CourseUserExist Method "+e);}
                return flag;     
	}

        /** In this method,Remove all details of a course
	* Course Delete of a Deleted  instituteId
	* and delete profile also (if user have not any other role).
        * @return String
        */
	public static String DeleteCourse(String instId,String langFile,String file){
		String message="";
		try
		{
			boolean checkgidsize=false;
			Criteria crit=new Criteria();
			int delgid=0,uid=0;
			String delgname="";
			Vector vct=new Vector();
			List allgrpname=ListManagement.getInstituteCourseList(instId);
			for(int j=0;j<allgrpname.size();j++)
			{
				delgid=GroupUtil.getGID(allgrpname.get(j).toString());	
				delgname=(String)allgrpname.get(j);	
				vct=getUseringroup(delgid);
				for(int k=0;k<vct.size();k++)
                       		{
					/**In this method we check that the user have any other course
         				*in any other Institute.
					* Return flag true if uid belongs to other Courses on the behalf of gid
					*/
                       			uid=(Integer)vct.get(k);
                       			checkgidsize=CourseUserExist(uid);
					if(checkgidsize==true)
					{
                       				crit=new Criteria();
                       				crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                       				crit.add(TurbineUserGroupRolePeer.GROUP_ID,delgid);
                       				TurbineUserGroupRolePeer.doDelete(crit);
					}//if
                        		else{
						String userName=UserUtil.getLoginName(uid);
						UserManagement umt=new UserManagement();
                                        	message=umt.removeUserProfile(userName,delgname,file);
						/**
                                        	* Delete the entries from USER_PREFS table.
                                        	*/
                                        	crit=new Criteria();
                                        	crit.add(UserPrefPeer.USER_ID,uid);
                                        	UserPrefPeer.doDelete(crit);
                       			}//else
				}//for
				String remdata= RemoveDataCrs(delgid,delgname);
			}//for
		}//try
		catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in DeleteCourse Method "+e);}
		return message;
	}//method

	public static String RemoveDataCrs(int gid,String gName)
       	{
               	String message="";
               	try{
			Criteria crit=new Criteria();
			/**
                       	* Delete the entries of the Notices from the related tables
                       	*/
                       	crit=new Criteria();
                       	crit.add(NoticeSendPeer.GROUP_ID,gid);
                       	NoticeSendPeer.doDelete(crit);
                       	crit=new Criteria();
                       	crit.add(NoticeReceivePeer.GROUP_ID,gid);
                       	NoticeReceivePeer.doDelete(crit);
                       	/**
                       	* Delete the entries of the discussion board from the related tables
                       	*/
                       	crit=new Criteria();
                       	crit.add(DbSendPeer.GROUP_ID,gid);
                       	DbSendPeer.doDelete(crit);
                       	crit=new Criteria();
                       	crit.add(DbReceivePeer.GROUP_ID,gid);
                       	DbReceivePeer.doDelete(crit);
                       	/**
                       	* Delete the entries of the News from the related tables
                      	*/
                       	crit=new Criteria();
                       	crit.add(NewsPeer.GROUP_ID,gid);
                       	NewsPeer.doDelete(crit);
                       	/**
                       	* Delete the repository from the server for
                       	* this course
                       	*/
			crit=new Criteria();
                       	crit.add(CoursesPeer.GROUP_NAME,gName);
                       	CoursesPeer.doDelete(crit);
                       	String coursesRealPath=TurbineServlet.getRealPath("/Courses");
                       	String fileName=coursesRealPath+"/"+gName;
                       	File f=new File(fileName);
               		SystemIndependentUtil.deleteFile(f);
            		File f_Index=new File(fileName+"_Index");
              		SystemIndependentUtil.deleteFile(f_Index);
			/**
                       	* Remove the group completely from the system
                       	*/
                       	Group g=TurbineSecurity.getGroupByName(gName);
                       	g.remove();
                }//try
                catch(Exception e)
                {
                        message="The error in "+gName+" Course removal "+e;
                }
                return(message);
        }//method
	public static boolean ExistInstAdmin(String username)
        {
                boolean flag=false;
                Vector vtr=new Vector();
                try
                {
                        Criteria crit=new Criteria();
			crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
                        List list=InstituteAdminUserPeer.doSelect(crit);
                        for(int m=0;m<list.size();m++){
				InstituteAdminUser instAuser=(InstituteAdminUser)(list.get(m));
                                int instituteid=instAuser.getInstituteId();
                                vtr.add(instituteid);
                        }
                        if(vtr.size()>1)
                                flag=true;
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in ExistInstAdmin Method "+e);}
                return flag;
        }
	public static boolean InstAdminRoleExist(int uid)
        {
                boolean flag=false;
                Vector vtr=new Vector();
		int nogid[]={1,3};
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,nogid);
                        List listt=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int m=0;m<listt.size();m++){
                                TurbineUserGroupRole tugrole=(TurbineUserGroupRole)listt.get(m);
                                int gid=tugrole.getGroupId();
                                vtr.add(gid);
                        }
                        if(vtr.size()!=0)
                                flag=true;
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in InstAdminRoleExist Method"+e);}
                return flag;
        }
	public static String DeleteInstitute(String instId,String langFile,String file,String gname){
                String message="";
		boolean adminrole=false;
		boolean otherinstExist=false;
                try
                {
			Criteria crit=new Criteria();
			Vector instadminlist=InstAdminList(instId);
			//ErrorDumpUtil.ErrorLog("instadminlist===="+instadminlist);
			for(int m=0;m<instadminlist.size();m++)
			{
				int uid=UserUtil.getUID((instadminlist.get(m).toString()));
				String email=instadminlist.get(m).toString();
				//ErrorDumpUtil.ErrorLog("uid===="+uid);
				adminrole=InstAdminRoleExist(uid);
				otherinstExist=ExistInstAdmin(email);
				/*flag true return when more than one role exits*/
				if((adminrole==true)&&(otherinstExist==false))
				{
					/*Remove from turbine user group role with gid 3
					*and from InstituteAdminUser table.
					*/
					crit=new Criteria();
                        		crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        		crit.add(TurbineUserGroupRolePeer.GROUP_ID,3);
                        		TurbineUserGroupRolePeer.doDelete(crit);
					//ErrorDumpUtil.ErrorLog("undr if condition adminrole===="+adminrole+"\notherinstExist====="+otherinstExist);
				}
				else
				{
					//ErrorDumpUtil.ErrorLog("undr else condition adminrole===="+adminrole+"\notherinstExist====="+otherinstExist);
					if((adminrole==false)&&(otherinstExist==false))
					{
						//ErrorDumpUtil.ErrorLog("undr elseif=== condition adminrole===="+adminrole+"\notherinstExist====="+otherinstExist);
						/** Delete Institute with User Profile
				 		*By calling method removeUserProfileWithMail
				 		* @see UserManagement in Utils.
				 		*/
						String server_name=TurbineServlet.getServerName();
                        			String srvrPort=TurbineServlet.getServerPort();
						String subject="";
						if(srvrPort.equals("8080"))
                                                	subject="deleteinstadmin";
                                        	else
                                                	subject="deleteinstadminhttps";
						UserManagement umt=new UserManagement();
						message=umt.removeUserProfileWithMail(email,gname,langFile,subject,email,"","","","",file,server_name,srvrPort);
						/**
                                		* Delete the entries from USER_PREFS table.
                                		*/
                                		crit=new Criteria();
                                		crit.add(UserPrefPeer.USER_ID,uid);
                                		UserPrefPeer.doDelete(crit);
					}//if
				}//else
			}//for
			/* Remove InstituteAdmin from InstituteAdminUser table*/
			crit=new Criteria();
                	crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instId);
			InstituteAdminUserPeer.doDelete(crit);

			/* Update status 2 for deleted institute in  InstituteAdminRegistration table*/
			String curdate=ExpiryUtil.getCurrentDate("-");
                	String expdate=ExpiryUtil.getExpired(curdate,8);
                	Date Expnewdate=Date.valueOf(expdate);
			crit=new Criteria();
                	crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instId);
                	crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,2);
                	crit.add(InstituteAdminRegistrationPeer.EXPIRY_DATE,Expnewdate);
			InstituteAdminRegistrationPeer.doUpdate(crit);

			/* Remove InstituteProgram from InstituteProgram table*/
			crit=new Criteria();
                	crit.add(InstituteProgramPeer.INSTITUTE_ID,instId);
			InstituteProgramPeer.doDelete(crit);
			
			/**
			  *Delete Institute Profile 				 
			  */
                	String delfpath=TurbineServlet.getRealPath("WEB-INF/conf/InstituteProfileDir"+"/"+instId+"Admin.properties");
			SystemIndependentUtil.deleteFile(new File(delfpath));
			//}
		}//try
		catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in Delete Institute Method"+e);}
                return message;
        }
	/**
        *Method for getting all database details of all users of a particular Institute. 
        */
        public static String InstituteBackup(String instId,String langfile){
		String message="";
                /**
                  *Get InstituteAdmin List
                  */
		try{
                Vector vctr=AllUserinInstitute(instId);
                Vector instCourse=InstituteIdUtil.getInst_Courselist(instId);
		Vector instvtr=new Vector();
		instvtr.add(instId);	
                /**
                  *Get course from above vector and run query select from database 
                  *compare if the string obtained from select query is starts with value obtained from above vector then write in file
                  */
                String CoursesRealPath=TurbineServlet.getRealPath("/BackupData"+"/"+instId);
                File fdir=new File(CoursesRealPath +"/");
                fdir.mkdirs();
                String filePath=CoursesRealPath+"/CompleteDBBackup"+ExpiryUtil.getCurrentDate("");
                String destPath=CoursesRealPath+"/CompleteDBBackup"+ExpiryUtil.getCurrentDate("")+".sql";
		Vector uidtable=UidTableFieldName();
		Vector gidtable=GidTableFieldName();
		Vector Pgtable=PgcodeTableFieldName();
		Vector insttable=InstIDTableFieldName();
		TableEntry(uidtable,filePath+"uid.txt",vctr);
		TableEntry(gidtable,filePath+"gid.txt",instCourse);
		Vector Pgcode=PgcodeList(instId);
		TableEntry(Pgtable,filePath+"prg.txt",Pgcode);
		TableEntry(insttable,filePath+"inst.txt",instvtr);
		File desf=new File(destPath);
		copyFile(CoursesRealPath,desf);
		//message="backup done";
		message=MultilingualUtil.ConvertedString("backup_backup",langfile)+" "+MultilingualUtil.ConvertedString("backup_msg1",langfile);
		}//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("The Error in DeleteInstituteUtil (InstituteBackup method)======"+ex);}
		return message;
        }
        public static Vector AllTable() {
		Vector vcttable=new Vector();
		try{
			String tableName="SHOW TABLES";
                	List tblnm=BasePeer.executeQuery(tableName);
			for(int i=0;i<tblnm.size();i++)
                	{//for0
                        	String tName=(tblnm.get(i)).toString();
                        	int u=tName.length();
                        	String tn=tName.substring(2,u-2);
				vcttable.add(tn);
			}
		}//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (AllTable) method======"+ex);}
		return vcttable;
        }
	public static Vector UidTableFieldName(){
                Vector uidtable=new Vector();
                try{
			List tabfield=null;
			String tName="";
                	Vector vcttable=AllTable();
                        for(int i=0;i<vcttable.size();i++)
                        {//for0
                                tName=(vcttable.get(i)).toString();
				String fieldnme="DESC "+tName;
                        	tabfield=BasePeer.executeQuery(fieldnme);
				for(int j=0;j<tabfield.size();j++){
                                	String fldName=(tabfield.get(j)).toString();
					String gettoken=FieldName(fldName);
					if((gettoken.equals("USER_ID"))||(gettoken.equals("RECEIVER_ID"))||(gettoken.equals("SENDER_ID")))
						if(!tName.equals("QUIZ"))	
							uidtable.add(tName);
				
				}
                        }//for
                }//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (UidTableFieldName) method======"+ex);}
                return uidtable;
        }
	public static Vector GidTableFieldName(){
                Vector Gidtable=new Vector();
                try{
			List tabfield=null;
                        String tName="";
                        Vector vcttable=AllTable();
                        for(int i=0;i<vcttable.size();i++)
                        {//for0
                                tName=(vcttable.get(i)).toString();
                                String fieldnme="DESC "+tName;
                                tabfield=BasePeer.executeQuery(fieldnme);
                        	for(int j=0;j<tabfield.size();j++){
                                	String fldName=(tabfield.get(j)).toString();
					String gettoken=FieldName(fldName);
                                	if((gettoken.equals("GROUP_NAME"))||(gettoken.equals("CID")))
						if(!tName.equals("INSTRUCTOR_PERMISSIONS"))	
                                			Gidtable.add(tName);

                        	}
                        }//for
                }//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (GidTableFieldName()) method======"+ex);}
                return Gidtable;
        }

	public static Vector InstIDTableFieldName(){
                Vector InstIdtable=new Vector();
                try{
			List tabfield=null;
                        String tName="";
                        Vector vcttable=AllTable();
                        for(int i=0;i<vcttable.size();i++)
                        {//for0
                                tName=(vcttable.get(i)).toString();
                                String fieldnme="DESC "+tName;
                                tabfield=BasePeer.executeQuery(fieldnme);
                        	for(int j=0;j<tabfield.size();j++){
                                	String fldName=(tabfield.get(j)).toString();
					String gettoken=FieldName(fldName);
                                	if(gettoken.equals("INSTITUTE_ID"))
						if((!tName.equals("INSTRUCTOR_PERMISSIONS"))&&(!tName.equals("CAL_INFORMATION"))&&(!tName.equals("INSTITUTE_PROGRAM"))){
                                			InstIdtable.add(tName);
						}
                        	}
                        }//for
                }//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (InstIDTableFieldName() ) method======"+ex);}
                return InstIdtable;
        }
	public static Vector PgcodeTableFieldName(){
                Vector Pgcodetable=new Vector();
                try{
			List tabfield=null;
                        String tName="";
                        Vector vcttable=AllTable();
                        for(int i=0;i<vcttable.size();i++)
                        {//for0
                                tName=(vcttable.get(i)).toString();
                                String fieldnme="DESC "+tName;
                                tabfield=BasePeer.executeQuery(fieldnme);
                        	for(int j=0;j<tabfield.size();j++){
                                	String fldName=(tabfield.get(j)).toString();
					String gettoken=FieldName(fldName);
                                	if(gettoken.equals("PROGRAM_CODE"))
                                		Pgcodetable.add(tName);
                        	}
                        }//for
                }//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (PgcodeTableFieldName() ) method======"+ex);}
                return Pgcodetable;
        }
	public static void TableEntry(Vector tables,String filePath,Vector vtr){
                try{
			FileWriter fw=new FileWriter(filePath,true);
			for(int k=0;k<tables.size();k++)
			{
				String tablename=tables.get(k).toString();
				fw.write("# drop table if exists "+tablename+";"+"\n");
                       		fw.write(" DELETE FROM "+tablename+";"+"\n");
				String detail="SELECT * FROM "+tablename;
                        	List tdetail=BasePeer.executeQuery(detail);
                        	for(int m=0;m<tdetail.size();m++)
                        	{
                                	String e1=(tdetail.get(m)).toString();
                                	int n=e1.length();
                                	String e2=e1.substring(1,n-1);
					String entry="";
                                	StringTokenizer st1 = new StringTokenizer(e2,",");
                                	int a=st1.countTokens();
                                	if(tablename.equals("TURBINE_USER")){
                                       		a=11;
                                	}
                        		try{
                                		for(int c=1;c<=a; c++){
                                        		String token=st1.nextToken() ;
                                        		int tl=token.length();
                                        		String gettoken=token.substring(1,tl-1);
                                        		String finaltoken="'"+gettoken.replace("'","\\'")+"'";
                                        		if(a==c){
                                                		if(tablename.equals("TURBINE_USER")){
                                                			entry=entry+"'null'";
                                                		}
                                                		else{
                                                        		entry=entry+finaltoken;
                                                		}
                                        		}
                                        		else{
                                                		entry=entry+finaltoken+",";
                                       	 		}
                                        	}
                        		}//try
					catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in TableEntry method inner part======"+ex);}
					for(int x=0;x<vtr.size();x++)
					{
						String str=vtr.get(x).toString();
						if(e2.contains(str))
                                                           fw.write("\n"+"INSERT INTO "+tablename+" values ("+entry+");");
					}
				}//for
				fw.write("\n\n");
			}//for
			fw.close();
		}//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (TableEntry) method======"+ex);}
		//return entry;
	}
	public static String FieldName(String fldName){
		String fieldname="";
                try{
                        int n=fldName.length();
                        String e2=fldName.substring(1,n-1);
                        StringTokenizer st1 = new StringTokenizer(e2,",");
                        String token=st1.nextToken() ;
                        int tl=token.length();
                        fieldname=token.substring(1,tl-1);
		}//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (FieldName()) method======"+ex);}
                return fieldname;
	}
	public static Vector PgcodeList(String instId){
		Vector pgcode=new Vector();
                try{
			Criteria crit=new Criteria();
                        crit.add(InstituteProgramPeer.INSTITUTE_ID,instId);
                        List prgList=InstituteProgramPeer.doSelect(crit);
                        for(int z=0;z<prgList.size();z++)
                        {
                        	InstituteProgram iprogramDetail=(InstituteProgram)prgList.get(z);
                                String programcode=iprogramDetail.getProgramCode().toString();
				pgcode.add(programcode);
                        }
		}//try
                catch(Exception ex){ErrorDumpUtil.ErrorLog("the error in DeleteInstituteUtil (FieldName()) method======"+ex);}
                return pgcode;
	}

	public static void copyFile(String filepath,File dst){
		try{
			Vector vctr=fileterfile(filepath);
                	BufferedWriter bw = new BufferedWriter(new FileWriter(dst,true));
			for(int i=0;i<vctr.size();i++){
				String fvalue=vctr.get(i).toString();
                        	BufferedReader br = new BufferedReader(new FileReader(filepath+"/"+fvalue));
                        	String line;
                        	while ((line=br.readLine())!=null) {
                                	bw.write(line);
                                	bw.newLine();
                        	}
                        	br.close();
				SystemIndependentUtil.deleteFile(new File (filepath+"/"+fvalue));
                	}
                	bw.close();
		}//try
		catch (Exception e){ErrorDumpUtil.ErrorLog("Error: in DeleteInstituteUtil(copyFile method)" + e);}
	}
	public static Vector fileterfile(String filePath){
		Vector fvct=new Vector();
		try{
			File fdir=new File(filePath);
                	String filter[]={".sql"};
                	NotInclude exclude=new NotInclude(filter);
                	String flist []=fdir.list(exclude);
                	for(int j=0;j<flist.length;j++)
                        {
                                fvct.add(flist[j]);
                        }
		}//try
		catch (Exception e){ErrorDumpUtil.ErrorLog("Error: fileterfile method" + e);}
		return fvct;
	}
	public static Vector InstAdminDetail(String InstId){
		Vector instuser=new Vector();
		try{
			List admindetail=null;
                        Criteria crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
                        admindetail=InstituteAdminUserPeer.doSelect(crit);
                        for(int k=0;k<admindetail.size();k++)
                        {
                        	InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get(k);
                                int Id=instadminuser.getId();
                                String email=instadminuser.getAdminUname();
                                String ADesg=instadminuser.getAdminDesignation();
                                crit=new Criteria();
                                crit.add(org.iitk.brihaspati.om.TurbineUserPeer.LOGIN_NAME,email);
                                List tulist=org.iitk.brihaspati.om.TurbineUserPeer.doSelect(crit);
                                org.iitk.brihaspati.om.TurbineUser udetail=(org.iitk.brihaspati.om.TurbineUser)tulist.get(0);
                                String fname=udetail.getFirstName();
                                String lname=udetail.getLastName();
                                String temail=udetail.getEmail();
                                String uname=udetail.getLoginName();
                                InstituteFileEntry InstfileEntry=new InstituteFileEntry();
                                InstfileEntry.setInstituteFName(fname);
                                InstfileEntry.setInstituteLName(lname);
                                InstfileEntry.setInstituteEmail(temail);
                                InstfileEntry.setInstituteUserName(uname);
                                InstfileEntry.setID(Id);
                                InstfileEntry.setInstituteDesignation(ADesg);
                                instuser.add(InstfileEntry);
			}
		}//try
		catch (Exception e){ErrorDumpUtil.ErrorLog("Error: fileterfile method" + e);}
		return instuser;
	}
	public static String DeleteInstAdmin(String username,String InstId,String langFile,String file){
		String message="";
		try{
			Criteria crit=new Criteria();
			int uid=UserUtil.getUID(username);
			boolean flag=StatusInstAdmin(username,InstId);
			boolean otherinstExist=ExistInstAdmin(username);
			boolean adminrole=InstAdminRoleExist(uid);
			if(flag==false){
				/*flag true return when more than one role exits*/
                        	if((adminrole==true)&&(otherinstExist==false))
                        	{
                                	/*Remove from turbine user group role with gid 3
                                	*/
                                	crit=new Criteria();
                                	crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                                	crit.add(TurbineUserGroupRolePeer.GROUP_ID,3);
                                	TurbineUserGroupRolePeer.doDelete(crit);
                                	//message="User Role institute_admin removed Successfully";
                                	message=MultilingualUtil.ConvertedString("insturole_msg",file);
                        	}
                        	else
                        	{
                                	if((adminrole==false)&&(otherinstExist==false))
                                	{
                                	/** Delete InstituteAdmin with Profile
                                	*By calling method removeUserProfileWithMail
                                	*@see UserManagement in Utils.
                                	*/
					String gname=GroupUtil.getGroupName(3);
                                	String server_name=TurbineServlet.getServerName();
                                	String srvrPort=TurbineServlet.getServerPort();
					String subject="";
					if(srvrPort.equals("8080"))
                        			subject="deleteinstadmin";
                			else
                        			subject="deleteinstadminhttps";
                                	UserManagement umt=new UserManagement();
                                	message=umt.removeUserProfileWithMail(username,gname,langFile,subject,username,"","","","",file,server_name,srvrPort);
                                	/**
                                	* Delete the entries from USER_PREFS table.
                                	*/
                                	crit=new Criteria();
                                	crit.add(UserPrefPeer.USER_ID,uid);
                                	UserPrefPeer.doDelete(crit);
                                	}//if
                        	}//else
				/* Remove InstituteAdmin from InstituteAdminUser table*/
                        	crit=new Criteria();
                        	crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
                        	crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
                        	InstituteAdminUserPeer.doDelete(crit);
				if(otherinstExist==true)
				//message=username+" Institute Admin role has been deleted.";
				message=MultilingualUtil.ConvertedString("insturole_msg",langFile);
			}//if
			else{
				message=MultilingualUtil.ConvertedString("instprim_msg",langFile);
			}

		}//try
		catch (Exception e){ErrorDumpUtil.ErrorLog("Error: fileterfile method" + e);}
		return message;
	}
	public static boolean StatusInstAdmin(String email,String InstId)
        {
                boolean flag=false;
                try
                {
			Vector vtr=new Vector();
                        Criteria crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
                        crit.add(InstituteAdminUserPeer.ADMIN_UNAME,email);
                        List list=InstituteAdminUserPeer.doSelect(crit);
                        for(int m=0;m<list.size();m++){
                                InstituteAdminUser instAuser=(InstituteAdminUser)(list.get(m));
                                int Statusadmin=instAuser.getAdminPermissionStatus();
				if(Statusadmin==1)
				flag=true;
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in ExistInstAdmin Method "+e);}
                return flag;
        }
        public static Vector InstAdminList(String InstId)
        {
		Vector vtr=new Vector();
                try
                {
                        Criteria crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,InstId);
                        List list=InstituteAdminUserPeer.doSelect(crit);
                        for(int m=0;m<list.size();m++){
                                InstituteAdminUser instAuser=(InstituteAdminUser)(list.get(m));
                                String  adminname=instAuser.getAdminUname();
				vtr.add(adminname);
                        }
                }
                catch(Exception e){ErrorDumpUtil.ErrorLog("Exception in ExistInstAdmin Method "+e);}
                return vtr;
        }


}//class
