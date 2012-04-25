package org.iitk.brihaspati.modules.screens.call.TrackingReport;

/*
 * @(#)Track_Report.java
 *
 *  Copyright (c) 2009 ETRG,IIT Kanpur.
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
 *This class contains code for Creating a group
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */
//Java
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import java.sql.Date;
//Apache turbine
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.torque.util.Criteria;
//brihaspati
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;

public class TrkReport_Instadmin extends SecureScreen
{
	/**
     	* Place all the data object in the context
     	* for use in the template.
     	*/
	public void doBuildTemplate(RunData data, Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();	
                        ParameterParser pp=data.getParameters();
			/**
                        * Get courseid  and coursename for the user currently logged in
                        *Put it in the context for Using in templates
                        * @see UserUtil in Util.
                        */
                	User user=data.getUser();
			String username=data.getUser().getName();
                        context.put("username",username);
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String type =pp.getString("type","");
			context.put("type",type);
			String valdir=pp.getString("valdir","");
			context.put("inst_name",valdir);

			/**
                        *  Get the list of registered Institute
                        *  status for approved(1) institute list
                        *  orphan(3) having no institute admin in an institute.
                        */

                        Criteria crit = new Criteria();
                        crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
                        crit.or(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"3");
                        List instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
			context.put("instdetail",instdetail);

			/**Get the list of all registered Courses*/
			int inst_id=InstituteIdUtil.getIst_Id(valdir);
			Vector Course_list=InstituteIdUtil.getInst_Courselist(Integer.toString(inst_id));
			context.put("ttlno_Course",Course_list.size());
			//ErrorDumpUtil.ErrorLog("inst_id"+inst_id+"Course_list"+Course_list.size());
			/** Get the list of institute admin acording to institute id in an institute */
			Vector instadmin=new Vector();
			crit=new Criteria();
                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,inst_id);
                       	List admindetail=InstituteAdminUserPeer.doSelect(crit);
                        for(int k=0;k<admindetail.size();k++)
                        {
                               InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get(k);
                               instadmin.add(instadminuser);
                        }
			context.put("instadmin",instadmin.size());
			/** Get the list of Instructor acording to institute id in an institute */
			Vector instructorlist=InstituteDetailsManagement.getInstUserDeatil(Integer.toString(inst_id));
			context.put("istructorlist",instructorlist.size());
			Vector Studentlist=InstituteIdUtil.getInstStudentDetail(Integer.toString(inst_id));
			context.put("Studentlist",Studentlist.size());
			/**-----------------------ProgrammeList--------------------*/
			crit=new Criteria();
                        crit.add(InstituteProgramPeer.INSTITUTE_ID,inst_id);
                        List Instproglist= InstituteProgramPeer.doSelect(crit);	
			context.put("Instproglist",Instproglist.size());
			/**-------------------------------------------------------*/

		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in Screen:TrkReport_Instadmin !!"+e);	
                                   data.setMessage("See ExceptionLog !! " );
                                  }
	}//method close
}//class
