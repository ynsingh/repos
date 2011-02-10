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
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
/**
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @modify date:23-12-2010,10-02-2011
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
         * getting institute id of instructor and studend user by the help of userid 
         */
        public static String getInstId(int uid)
        {
                Criteria crit=new Criteria();
                String e=null;
                int[] uId2 ={0,1};
                try{
                crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
                crit.andNotIn(TurbineUserGroupRolePeer.GROUP_ID,uId2);
                List v=TurbineUserGroupRolePeer.doSelect(crit);
                                for(int k=0;k<=v.size();k++){
                                TurbineUserGroupRole element=(TurbineUserGroupRole)v.get(k);
                                int s=(element.getGroupId());
				if(s!=2){
                                String gname=GroupUtil.getGroupName(s);
                                StringTokenizer st=new  StringTokenizer(gname,"_");
                                for(int j=0;st.hasMoreTokens();j++){
                                                String instid=st.nextToken();
                                                e=st.nextToken();
                                        }
                                }else
                               {
					e="author";

                              }
			     }

                }catch(Exception ex){}
                return e;
 }
	/** Get the Institute Name on the basis of Institute Id
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
	/* Get the Program name on the basis of programe code
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
        /*
         * getting the instituteid of institute admin by the help of userid
         */
        public static String getAdminInstId(int uid)
        {
                Criteria crit=new Criteria();
                String ef=null;
                try{
                        String username=UserUtil.getLoginName(uid);
                                crit.add(InstituteAdminUserPeer.ADMIN_EMAIL,username);
                                List v3=InstituteAdminUserPeer.doSelect(crit);

                                for(int b=0;b<v3.size();b++)
                                        {
                                                InstituteAdminUser el=(InstituteAdminUser)v3.get(b);
                                                int aid=(el.getInstituteId());
                                                ef = Integer.toString(aid);
                                        }
                        }catch(Exception ex){}
                        return ef;
 }
        /**
         * search method for given id  is a instituteAdmin or normal user
         */
        public static String getSearch(int uid)
        {
                String efg=null;
                String f=null;
                try{
                        efg=InstituteIdUtil.getInstId(uid);
                        if(efg.equals("admin")){
                        f=InstituteIdUtil.getAdminInstId(uid);
                        }
                        else{
                         f=InstituteIdUtil.getInstId(uid);
                        }
                }catch(Exception ex){}
                return f;
        }

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

                        for(int k=0;k<=v.size();k++){
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
                                }else if(s==2){
				e="author";
				instidlist.add(e);
				}
                                else
                                {
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
//        }
}
	public static String getTimeCalculation(int uid)
	{	String utime=null;
		try{
			Date de=new Date();
                	DateFormat df = DateFormat.getDateInstance();
                	String s = df.format(de);
                	Criteria crit=new Criteria();
                	crit.add(UsageDetailsPeer.USER_ID,uid);
               		 List v=UsageDetailsPeer.doSelect(crit);
                	Vector vec=new Vector();
                 	for(int p=0;p<v.size();p++) {
                 		UsageDetails element=(UsageDetails)v.get(p);
                        	Date time=(element.getLoginTime());
                        	long te=time.getTime();
                        	String s1 = df.format(time);
                        	if(s.equals(s1))
                        	vec.add(te);
                	}
                	Object obm = Collections.max(vec);
                	String str1 = obm.toString();
                	long l = Long.parseLong(str1.trim());
                	long diff = de.getTime() - l;
                	long diffHours = diff/(60 * 60 * 1000);
                	long diffHour = diff%(60 * 60 * 1000);
                	long diffMin=diffHour/(60*1000);
			//String username=user.getName();
			utime=+diffHours+"Hrs"+" "+diffMin+"Min";
		}catch(Exception ex){}
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

}

