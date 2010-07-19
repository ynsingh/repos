package org.iitk.brihaspati.modules.actions;

/**
 * @(#)CreateAdmin.java
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
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
 */

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.actions.VelocitySecureAction;

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;
//import org.apache.turbine.services.security.torque.om.TurbineUser;
//import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
//Local classes
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;

/**
 * 
 * 
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 */


public class CreateAdmin extends SecureAction_Admin
{
	private String institutename = "", instituteaddress = "", institutecity = "" ,institutepincode = "", institutestate = "", institutelandline = "", institutedomain = "", institutetype = "", instituteaffiliation = "", institutewebsite = "", instituteadminfname = "", instituteadminlname = "", instituteadminemail = "", instituteadmindesignation = "", instituteadminusername = "", instituteadminpassword = "", instituteregisterdate = "" ;

	protected boolean isAuthorized( RunData rundata ) throws Exception
        {
                return true;
        }

	public void doPerform(RunData rundata,Context context) throws Exception
        {
                String action = rundata.getParameters().getString("actionName","");
                if(action.equals("eventSubmit_CreateInstituteAdmin")){
                        CreateInstituteAdmin(rundata,context);
		}
                else
                {
                        rundata.setMessage("Action not found");
                }
        }

	
	public void CreateInstituteAdmin(RunData rundata, Context context) 
	{
		try{
			rundata.setMessage("Test");
			ParameterParser parameterparser = rundata.getParameters();
			institutename = parameterparser.getString("INAME");
			instituteaddress = parameterparser.getString("IADDRESS");
			institutecity = parameterparser.getString("ICITY");
			institutepincode = parameterparser.getString("IPINCODE");
		
			institutestate = parameterparser.getString("ISTATE");
			institutelandline = parameterparser.getString("ILANDLINE");
			institutedomain = parameterparser.getString("IDOMAIN");		
			institutetype = parameterparser.getString("ITYPE");
			instituteaffiliation = parameterparser.getString("IAFFILIATION");
			institutewebsite = parameterparser.getString("IWEBSITE");	
			instituteadminemail = parameterparser.getString("IADMINEMAIL");
			instituteadmindesignation = parameterparser.getString("IADMINDESIGNATION");
			String adminusername = parameterparser.getString("adminusername");
			context.put("adminusername",adminusername);
			String iadminfname = parameterparser.getString("iadminfname");
			String iadminlname = parameterparser.getString("iadminlname");
			Criteria criteria = new Criteria();
			criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,institutename);
			criteria.add(InstituteAdminRegistrationPeer.INSTIUTE_ADDRESS,instituteaddress);
			criteria.add(InstituteAdminRegistrationPeer.CITY,institutecity); 
			criteria.add(InstituteAdminRegistrationPeer.PINCODE,institutepincode);
			criteria.add(InstituteAdminRegistrationPeer.STATE,institutestate);
			criteria.add(InstituteAdminRegistrationPeer.LANDLINE_NO,institutelandline); 
			criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,institutedomain);
			criteria.add(InstituteAdminRegistrationPeer.TYPE_OF_INSTITUTION,institutetype);
			criteria.add(InstituteAdminRegistrationPeer.AFFILIATION,instituteaffiliation);
			criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_WEBSITE,institutewebsite);
			criteria.add(InstituteAdminRegistrationPeer.ADMIN_EMAIL,instituteadminemail);
			criteria.add(InstituteAdminRegistrationPeer.ADMIN_DESIGNATION,instituteadmindesignation);
			criteria.add(InstituteAdminRegistrationPeer.ADMIN_UNAME,adminusername);
			criteria.add(InstituteAdminRegistrationPeer.ADMIN_FNAME,iadminfname);
			criteria.add(InstituteAdminRegistrationPeer.ADMIN_LNAME,iadminlname);
			InstituteAdminRegistrationPeer.doInsert(criteria);
			rundata.setMessage("Admin Registeration Successfull");
			
		}
		catch (Exception e)
		{
			rundata.setMessage("Action Called in Catch" +e);
		}
	}
	
}

