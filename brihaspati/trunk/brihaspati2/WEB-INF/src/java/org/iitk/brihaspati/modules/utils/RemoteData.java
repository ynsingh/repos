
package org.iitk.brihaspati.modules.utils;

/*@(#)EncrptDecrpt.java
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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

/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import org.apache.commons.lang.StringUtils;
import java.util.Vector;
import java.util.List;
import java.util.Date;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineGroupPeer;
import org.iitk.brihaspati.om.TurbineGroup;
import org.iitk.brihaspati.om.CoursesPeer;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.apache.turbine.services.servlet.TurbineServlet;


public class RemoteData{
	/**
 	* This method retrun the course list with courseId, Course Name and role in that course
 	* @param  email  this is user email/ login name
 	* @param  InstituteId this is institute identification code
 	* @return      String combination (Courseid-CourseName-RoleName)
 	*/

	public static Vector getUserCrsList(String email,String InstituteId)
        {
		Vector roleid=new Vector();
		List v=null;
		List v1=null;
		List v2=null;
		Criteria crit=new Criteria();
		try{
			int uid=UserUtil.getUID(email);
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
	                crit.setDistinct();
        	        v=TurbineUserGroupRolePeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserCrsList()- RemoteData !!"+e);
                }
                try{
                        for(int i=0;i<v.size();i++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                int s=element.getRoleId();
                                int gp=element.getGroupId();

				String gnm=GroupUtil.getGroupName(gp);
                                String cnm=CourseUtil.getCourseName(gnm);

				if(gnm.endsWith(InstituteId)){
					String rnme=UserGroupRoleUtil.getRoleName(s);
					String fnl=gnm+"-"+cnm+"-"+rnme;
					roleid.add(fnl);
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserCrsList()- RemoteData !!"+e);
                }
	return roleid;
	}
	/**
 	* This method retrun the user institute and role information
 	* @param  email  this is user email/ login name
 	* @return      Vector  combination (InstituteId-InstituteName-RoleName)
 	*/
	public static Vector getUserInsList(String email)
        {
		Vector insLst=new Vector();
		List v=null;
		List v1=null;
		List v2=null;
		String Inme;
		boolean blnFound=true;
		Criteria crit=new Criteria();
		try{
			int uid=UserUtil.getUID(email);
			crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                	crit.setDistinct();
	                v=TurbineUserGroupRolePeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserInsList()- RemoteData !!"+e);
                }
                try{
                        for(int i=0;i<v.size();i++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(i);
                                int s=element.getRoleId();
                                int gp=element.getGroupId();
				crit=new Criteria();
				crit.add(TurbineGroupPeer.GROUP_ID,gp);
				v1=TurbineGroupPeer.doSelect(crit);
				for(int j=0;j<v1.size();j++){
					TurbineGroup tg=(TurbineGroup)v1.get(j);
					String gnm=tg.getGroupName();
					String iid=StringUtils.substringAfterLast(gnm, "_") ;
					Inme=InstituteIdUtil.getIstName(Integer.parseInt(iid));					
					String rnme=UserGroupRoleUtil.getRoleName(s);
					String fnl=iid+"-"+Inme+"-"+rnme;
					blnFound = insLst.contains(fnl);
					if(!blnFound){
						insLst.add(fnl);
					}
				}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try getUserInsList()- RemoteData !!"+e);
                }
		crit=new Criteria();
		try{
			crit.add(InstituteAdminUserPeer.ADMIN_UNAME,email);
	                crit.setDistinct();
        	        v=InstituteAdminUserPeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserInsList()- RemoteData !!"+e);
                }
		try{
			for(int i=0;i<v.size();i++){
				InstituteAdminUser ele=(InstituteAdminUser)v.get(i);
				int insid=ele.getInstituteId();
				Inme=InstituteIdUtil.getIstName(insid);
				String rName="institute_admin";
				String fnal=Integer.toString(insid)+"-"+Inme+"-"+rName;
				blnFound = insLst.contains(fnal);
                                        if(!blnFound){
                                                insLst.add(fnal);
                                        }
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUserInsList()- RemoteData !!"+e);
                }


	return insLst;
	}
	/**
 	* This method retrun the user registration information 
 	* @param  email  this is user email/ login name
 	* @return      Vector  combination of String (Rollno-InstituteName-InstituteId-ProgramName-ProgramId-date)
 	*/
	public static Vector getUsrRegInfo(String email)
        {
		Vector regInfo=new Vector();
		List v=null;
		List v1=null;
		List v2=null;
		String Inme;
		String crdte="";
		boolean blnFound=true;
		Criteria crit=new Criteria();
		try{
			crit.add(StudentRollnoPeer.EMAIL_ID,email);
        	        crit.setDistinct();
                	v=StudentRollnoPeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getUseRegInfo- RemoteData !!"+e);
                }
                try{
                        for(int i=0;i<v.size();i++){
                                StudentRollno element=(StudentRollno)v.get(i);
                                String rollno=element.getRollNo();
                                String  prg=element.getProgram();
                                String iid=element.getInstituteId();
				Inme=InstituteIdUtil.getIstName(Integer.parseInt(iid));
				String prgnme=InstituteIdUtil.getPrgName(prg);

				crit=new Criteria();
				crit.add(TurbineUserPeer.LOGIN_NAME,email);
				v1=TurbineGroupPeer.doSelect(crit);
				for(int j=0;j<v1.size();j++){
					TurbineUser tu=(TurbineUser)v1.get(j);
					crdte=(tu.getCreated()).toString();
				}
				String fnl=rollno+"-"+Inme+"-"+iid+"-"+prgnme+"-"+prg+"-"+crdte;
                                regInfo.add(fnl);
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try getUseRegInfo- RemoteData !!"+e);
                }
	return regInfo;
	}
	/**
 	* This method retrun the Marks of student in perticular program with institute
 	* @param  email  this is user email/ login name
 	* @param  insid  Institute indentification code
 	* @param  progid  Program identification code
 	* @return      Vector  combination of String (CourseId-AssignmentName-DateofAssignment-MaxMark-ObtainMark-Grade)
 	*/
	public static Vector getStuMark(String email, String insid, String progid)
        {
		Vector sMarkInfo=new Vector();
		List v=null;
		List v1=null;
		List v2=null;
		String Inme;
		String crdte="";
		boolean flag=true;
		String rollno=null;
		Criteria crit=new Criteria();
		try{
			crit=new Criteria();
			crit.add(StudentRollnoPeer.EMAIL_ID,email);
			crit.add(StudentRollnoPeer.PROGRAM,progid);
			crit.add(StudentRollnoPeer.INSTITUTE_ID,insid);
			v=StudentRollnoPeer.doSelect(crit);

			StudentRollno element=(StudentRollno)v.get(0);
		        rollno=element.getRollNo();
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in getting roll number getStuMark- RemoteData !!"+e);
                }

		try{
			int uid=UserUtil.getUID(email);
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        crit.setDistinct();
                        v1=TurbineUserGroupRolePeer.doSelect(crit);
		}
                catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try1 getStuMark- RemoteData !!"+e);
                }
                try{
                        for(int i=0;i<v1.size();i++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v1.get(i);
				int gp=element.getGroupId();
				String gnm=GroupUtil.getGroupName(gp);
				String cname=CourseUtil.getCourseName(gnm);	
				String crsno=CourseUtil.getCourseAlias(gnm);	
				String cc=crsno+":"+cname;
                                        if(gnm.endsWith(insid)){
						sMarkInfo.add(cc);
						String filePath=TurbineServlet.getRealPath("/Courses")+"/"+gnm+"/Marks/MARK.txt";
						boolean exists = (new File(filePath)).exists();
						if (exists) {
							BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
                        				String line=null;
							if((line=br.readLine())!=null)
                        				{
								sMarkInfo.add(line);
							}
							while ((line = br.readLine()) != null) {
				                                if (line.trim().startsWith(rollno)) {
									flag=false;
                                					sMarkInfo.add(line);
                                        				break;
                                				}
                        				}
							if(flag){
								sMarkInfo.add("Your Marks not uploaded till now");
							}
                        				br.close();
						}
						else{
							sMarkInfo.add("Marks not uploaded till now");
						}
					}
			}
		}
		catch(Exception e){
                        ErrorDumpUtil.ErrorLog("The error in try getStuMark- RemoteData !!"+e);
                }
	return sMarkInfo;
	}
}
