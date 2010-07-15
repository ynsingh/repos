package org.iitk.brihaspati.modules.actions;

/**
 * @(#) Institute_RootAdmin.java
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
 */


import java.util.List;
import java.util.StringTokenizer;

import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;

//turbine classes
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.actions.VelocitySecureAction;

//torque classes
import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;

//utils classes
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;

/**
 *
 * @author <a href="mailto:sharad23nov@yahoo.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @Created Date
 */

public class Institute_RootAdmin extends VelocitySecureAction
{
	private String LangFile=new String();

	protected boolean isAuthorized(RunData data) throws Exception
	{
		return true;
	}
	public void AcceptInstituteAdmin(RunData data, Context context)
	{
		try
		{
			ParameterParser pp = data.getParameters();
			String roleid = pp.getString("role");
			
			String LangFile = (String)data.getUser().getTemp("LangFile");
			String institutelist = data.getParameters().getString("deleteFileNames");
			UserManagement usermanagement = new UserManagement();
			

			//ErrorDumpUtil.ErrorLog("Accept Admin in Accept Method"+institutelist);
			//ParameterParser pp = data.getParameters();
			if(!institutelist.equals(""))
			{
				StringTokenizer st = new StringTokenizer(institutelist,"^");
				for(int j = 0; st.hasMoreTokens(); j++)
				{
					String instituteid = st.nextToken();
					//ErrorDumpUtil.ErrorLog("IstituteId======>"+instituteid);
					Criteria crit = new Criteria();
					crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
					List getinstitutedetail = InstituteAdminRegistrationPeer.doSelect(crit);
					int i_id = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteId();
					ErrorDumpUtil.ErrorLog("instid at line 103===="+i_id);
					String i_name = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteName();
					String i_address = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstiuteAddress();
					String i_city = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getCity();
					int i_pincode = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getPincode();
					String i_state = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getState();
					int i_landline = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getLandlineNo();
					String i_domain = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteDomain();
					String i_type = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getTypeOfInstitution();
					String i_affiliation = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAffiliation();
					String i_website = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteWebsite();
					String i_adminfname = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminFname();
					String i_adminlname = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminLname();
					String i_adminemail = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminEmail();
					String i_admindesignation = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminDesignation();
					String i_adminuname = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminUname();
					String i_adminpassword = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAdminPassword();
					//String adminusername = i_adminuname+"@"+i_domain;
					String serverName=data.getServerName();
	                                int srvrPort=data.getServerPort();
					String serverPort=Integer.toString(srvrPort);
					//ErrorDumpUtil.ErrorLog("Server Name:"+serverName+"\n"+"Server Port"+serverPort);
					LangFile=(String)data.getUser().getTemp("LangFile");
					String usermgmt = usermanagement.CreateUserProfile(i_adminuname,i_adminpassword,i_adminfname,i_adminlname,i_adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile);
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
					InstituteAdminRegistrationPeer.doUpdate(crit);

					
				
				}
			}
		}
		catch(Exception e)
		{
			data.setMessage("Error in Acceptence.."+e);
		}
	}
	


        public void RejectInstituteAdmin(RunData data, Context context)
        {
                data.setMessage("Reject Admin");
		ParameterParser pp = data.getParameters();
		String LangFile = (String)data.getUser().getTemp("LangFile");
                String institutelist = data.getParameters().getString("deleteFileNames");
		try{
		        if(!institutelist.equals(""))
                        {
                                StringTokenizer st = new StringTokenizer(institutelist,"^");
                                for(int j = 0; st.hasMoreTokens(); j++)
                                {
                                        String instituteid = st.nextToken();
                                        Criteria crit = new Criteria();
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"2");
					InstituteAdminRegistrationPeer.doUpdate(crit);
								
					
				}
			}	
		}
		catch(Exception e)
                {
                        data.setMessage("Error in Acceptence.."+e);
                }



        }

	
	public void doPerform(RunData data, Context context) throws Exception
	{
		String action = data.getParameters().getString("actionName","");
		//context.put("actionName",action);
		if(action.equals("eventSubmit_AcceptAdmin"))
			AcceptInstituteAdmin(data, context);
		else if(action.equals("eventSubmit_RejectAdmin"))
			RejectInstituteAdmin(data, context);	
		else
			data.setMessage("Action not found");		
				
	}
	
	

}
