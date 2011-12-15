package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) InstituteIdUtil.java
 *  Copyright (c) 2010 ETRG,IIT Kanpur 
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
 */
import java.io.*;
import java.lang.*;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.util.Collections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRole;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UsageDetails;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Program;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.UsageDetailsUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 * @modify date:23-12-2010,10-02-2011,05-08-2011(Richa)
 */
public class InstituteIdUtil
{

	/**
          * getting all institute list  //sunil
          * return list
          */
        public static List getInstList()
        {
		List instdetail=null;
		Criteria crit=new Criteria();
		try{
			crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
        	        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
                	crit.or(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"3");
	                instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
		}
		catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in getInstList() - Institute Id Util class !!"+ex);}

		return instdetail;
	}
	/**	
	* Search Institute on the basis of Id, Name and address
	* @param md String mode (search /all)
	* @param by String search criteria
	* @param vale String search value
	* @return List InstituteInfo
	*/ 	
	public static List searchInst(String md, String by, String vale)
        {
		List instdetail=null;
		try{
		Criteria crit = new Criteria();
                        if((md.equals("Search"))&&(org.apache.commons.lang.StringUtils.isNotBlank(vale))){
                                if(by.equals("InstituteId")){
                                        crit.add("INSTITUTE_ADMIN_REGISTRATION","INSTITUTE_ID",(Object)("%"+vale+"%"),crit.LIKE);
                                }
                                if(by.equals("InstituteName")){
                                        crit.add("INSTITUTE_ADMIN_REGISTRATION","INSTITUTE_NAME",(Object)("%"+vale+"%"),crit.LIKE);
                                }
                                if(by.equals("InstituteAdd")){
                                        crit.add("INSTITUTE_ADMIN_REGISTRATION","INSTIUTE_ADDRESS",(Object)("%"+vale+"%"),crit.LIKE);
                                }
                        }
                        else{
                        /**
                         * Get the list of all registered Institute
                         *  status for approved(1) institute list
                         *  orphan(3) having no institute admin in an institute.
                         */
					crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
                                	crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
                        	        crit.or(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"3");

                        }
                        instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
		}
		catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in searchInst() - Institute Id Util class !!"+ex);}
		return instdetail;
	}
	/** 
	 * Get the Institute Name on the basis of Institute Id
	 */
	public static String getIstName(int instituteid){
		String iName=null;
		Criteria crit=new Criteria();
		try{
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
			List inm=InstituteAdminRegistrationPeer.doSelect(crit);
			InstituteAdminRegistration element=(InstituteAdminRegistration)inm.get(0);
			iName=element.getInstituteName();
		}
		catch(Exception ex){	
			ErrorDumpUtil.ErrorLog("The error in getIstName() - Institute Id Util class !!"+ex);
		}
		return iName;
	}
	
	/** 
	 * Get the Program name on the basis of programe code
 	 */
	public static String getPrgName(String PrgCode){
		String pName=null;
		Criteria crt=new Criteria();
		try{
                crt.add(ProgramPeer.PROGRAM_CODE,PrgCode);
                List pnm=ProgramPeer.doSelect(crt);
                Program element=(Program)pnm.get(0);
                pName=element.getProgramName();
		}
		catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("The error in getPrgName() - Institute Id Util class !!"+ex);
                }
		return pName;
	} 	
	
        /**
         * getting the instituteid of institute admin by the help of userid
         */

	public static Vector getAllInstId(int uid)
        {
                Vector instidlist=new Vector();
                String e="";
                Criteria crit=new Criteria();
                int[] uId2 ={0,1};
                try{
	                crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
        	        crit.andNotIn(TurbineUserGroupRolePeer.GROUP_ID,uId2);
                	List v=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int k=0;k<v.size();k++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(k);
                                int s=(element.getGroupId());
                                if(s==3){
                                        String username=UserUtil.getLoginName(uid);
                                        crit=new Criteria();
                                        crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
                                        List v3=InstituteAdminUserPeer.doSelect(crit);
                                        for(int b=0;b<v3.size();b++)
                                        {
                                                InstituteAdminUser el=(InstituteAdminUser)v3.get(b);
                                                int aid=(el.getInstituteId());
                                                e = Integer.toString(aid);
                                                instidlist.add(e);
                                        }
                                }else if(s==2) {
					e="author";
					instidlist.add(e);
				} else {
                                        String gname=GroupUtil.getGroupName(s);
                                        StringTokenizer st=new  StringTokenizer(gname,"_");
                                        for(int j=0;st.hasMoreTokens();j++){
                                                String instid=st.nextToken();
                                                e=st.nextToken();
                                                instidlist.add(e);
					}
                                }
                        }
                }catch(Exception ex){ErrorDumpUtil.ErrorLog("Exception in getting all institute id according to  user id --[InstituteIdUtil]"+ex);}
                return instidlist;
	}
	
	public static String getTimeCalculation(int uid)
	{	String utime=null;
		try{
			int eid=UsageDetailsUtil.getentryId(uid);
			Date de=new Date();
                	Criteria crit=new Criteria();
			crit.add(UsageDetailsPeer.ENTRY_ID,eid);
               		List v=UsageDetailsPeer.doSelect(crit);
                 		UsageDetails element=(UsageDetails)v.get(0);
                        	Date time=(element.getLoginTime());
                	long diff = de.getTime()-time.getTime();
                	long diffHours = diff/(60 * 60 * 1000);
                	long diffHour = diff%(60 * 60 * 1000);
                	long diffMin=diffHour/(60*1000);
			utime=+diffHours+"Hrs"+" "+diffMin+"Min";
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("Exception in time Calculation of every Activeuser----------");}
                return utime;

	}
		
	/** Get the Institute id on the basis of Institute Name
	 */

	public static int getIst_Id(String Inst_Name)
	{
                int inst_id=0;
                Criteria crit=new Criteria();
                try{
                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,Inst_Name);
                        List inm=InstituteAdminRegistrationPeer.doSelect(crit);
                        InstituteAdminRegistration element=(InstituteAdminRegistration)inm.get(0);
                	inst_id=element.getInstituteId();
        	}
		catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("The error in getIsteId() -Institute Id Util class !!"+ex);
                }

        return inst_id;
        }
	public static Vector getInst_Courselist(String inst_id)
        {
                Vector vct=new Vector();
                try{
                        List v=null;
                        /**
                        *select all group name from TURBINE_GROUP table.
                        */
                        Criteria crit=new Criteria();
                        crit.addGroupByColumn(org.iitk.brihaspati.om.TurbineGroupPeer.GROUP_NAME);
                        v=org.iitk.brihaspati.om.TurbineGroupPeer.doSelect(crit);
                        /**
                        *get group name one by one.and store in a vector whose GName ends with InstituteId.
                        *return Vector.
                        */
                        for(int j=0;j<v.size();j++){
                                String GName=((org.iitk.brihaspati.om.TurbineGroup)v.get(j)).getGroupName();
                                if(GName.endsWith(inst_id)){
                                        vct.add(GName);
                                }
                        }
                }//try
                catch(Exception ex){
                        ErrorDumpUtil.ErrorLog("The error in getInst_Courselist() - Institute Id Util class !!"+ex);
                }
        	return vct;
        }
	public static Vector getInstStudentDetail(String instituteId)
        {
                Vector vct=new Vector();
                Vector uidvct=new Vector();
                List details=null;
                try{
                        Criteria crit=new Criteria();
                        List userList=UserManagement.getUserDetail1("All",instituteId);
                        for(int i=0;i<userList.size();i++)
                        {
                                TurbineUser element=(TurbineUser)(userList.get(i));
                                int userid=element.getUserId();
                                vct.add(userid);
                        }
                        for(int j=0;j<vct.size();j++){
                                String str=(vct.get(j)).toString();
                                crit=new Criteria();
                                crit.add(TurbineUserGroupRolePeer.USER_ID,str);
                                details=TurbineUserGroupRolePeer.doSelect(crit);
                                for(int r=0;r<details.size();r++){
                                        TurbineUserGroupRole tugr=(TurbineUserGroupRole) (details.get(r));
                                        int roleid=tugr.getRoleId();
                                        String roleId=Integer.toString(roleid);
                                        if(roleId.equals("3"))
                                        {
                                                int userid = tugr.getUserId();
                                                uidvct.add(userid);

                                        }
                                }

                        }
                } catch(Exception ex){}
                return uidvct;
        }

	/**
         * Generate Rollno for RegistrationWithoutProgram
         * concatenate institute id with static "RWP" and then
         * numbers according to generate rollno for student 
         */
	public static String generateRollno(int Inst_id)
        {
                String Rnd_rollno=null;
                try
                {
                        String InstRollno = "RWP"+Inst_id;
                        String table="STUDENT_ROLLNO";
                        String str = "ROLL_NO";
                        Criteria crit = new Criteria();
                        crit.add(table,str,(Object)("%"+InstRollno+"%"),crit.LIKE);
                        crit.addAscendingOrderByColumn(StudentRollnoPeer.ROLL_NO);
                        List v = StudentRollnoPeer.doSelect(crit);
                        int lstsize = v.size()-1;
                        StudentRollno element=(StudentRollno)v.get(lstsize);
                        String Rollno=element.getRollNo();
                        String strg1 = Rollno.substring(0,7);
                        int strg2 = Integer.parseInt(Rollno.substring(7));
                        if((v.size())==0)
                        {
                                int id = v.size()+1;
                                Rnd_rollno=InstRollno+"000"+id;
                        }
                        else
                        {
                                int id = strg2+1;
                                if(id<=9)
                                {
                                        Rnd_rollno=InstRollno+"000"+id;
                                }
                                else if((id>9) && (id<=99))
                                {
                                        Rnd_rollno=InstRollno+"00"+id;
                                }
                                else if((id>99) && (id<=999))
                                {
                                        Rnd_rollno=InstRollno+"0"+id;
                                }
                        }

                }
                catch(Exception ex)
                {
                        ErrorDumpUtil.ErrorLog("The error in generate Rollno -[InstituteIdUtil class] !!"+ex);
                }
        return Rnd_rollno;
        }

	/**
         * Get list of registered institute for perticular instructor
         * first get list of all registered instructor then get their group id.
         * From group id, get group name that shows in which group instructor is registered
         * from group name, get institute id of that instructor.
         */
	public static Vector getInstructorInstId(int uid)
        {
                Vector instidlist=new Vector();
                Criteria crit=new Criteria();
                try{
                        crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                        crit.and(TurbineUserGroupRolePeer.ROLE_ID,2);
                        List v=TurbineUserGroupRolePeer.doSelect(crit);
                        for(int k=0;k<v.size();k++)
                        {
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(k);
                                int s=(element.getGroupId());
                                String gname=GroupUtil.getGroupName(s);
                                String actgname[]=gname.split("_");
                                String InsId=actgname[1];
                                if(!instidlist.contains(InsId))
                                        instidlist.add(InsId);
                        }
                }catch(Exception ex){ErrorDumpUtil.ErrorLog("Exception in getInstructorInstId() method --[InstituteIdUtil]"+ex);}
        return instidlist;
        }
}

