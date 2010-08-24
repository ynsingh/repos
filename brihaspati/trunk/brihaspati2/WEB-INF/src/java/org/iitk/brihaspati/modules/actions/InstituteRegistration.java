package org.iitk.brihaspati.modules.actions;

/**
 * @(#)InstituteAdminRegistration.java
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
 */

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.actions.VelocitySecureAction;

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

//Local classes
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;

import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
/**
 * 
 * 
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @Created Date
 */


public class InstituteRegistration extends VelocitySecureAction
{
	private String institutename = "", instituteaddress = "", institutecity = "" ,institutepincode = "", institutestate = "", institutelandline = "", institutedomain = "", institutetype = "", instituteaffiliation = "", institutewebsite = "", instituteadminfname = "", instituteadminlname = "", instituteadminemail = "", instituteadmindesignation = "", instituteadminusername = "", instituteadminpassword = "", instituteregisterdate = "" ;

	protected boolean isAuthorized( RunData rundata ) throws Exception
        {
                return true;
        }

	public void doPerform(RunData rundata,Context context) throws Exception
        {
                String action = rundata.getParameters().getString("actionName","");
                if(action.equals("eventSubmit_InstituteRegister")){
                        InstituteRegister(rundata,context);
		}
		//---else if(action.equals("eventSubmit_doDelete"))
                     //---   doDelete(rundata,context);
                else
                {
                        rundata.setMessage("Action not found");
                }
        }

	
	public void InstituteRegister(RunData rundata, Context context) 
	{
		
		try{
			
			rundata.setMessage("Test");
			StringUtil str=new StringUtil();
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
			
			instituteadminfname = parameterparser.getString("IADMINFNAME");
			instituteadminlname = parameterparser.getString("IADMINLNAME");
			instituteadminemail = parameterparser.getString("IADMINEMAIL");
			instituteadmindesignation = parameterparser.getString("IADMINDESIGNATION");
			String adminusername = instituteadminemail;
			instituteadminpassword = parameterparser.getString("IADMINPASSWORD");
			//instituteadminusername=adminusername+"@"+institutedomain;
			 
			if(str.checkString(adminusername)==-1 && str.checkString(instituteadminfname)==-1 && str.checkString(instituteadminlname)==-1)
			{

				Criteria criteria = new Criteria();
				criteria.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
				List registeredinstitute=InstituteAdminRegistrationPeer.doSelect(criteria);
				boolean flag=false;
				if(registeredinstitute.size() !=0){
					for(int i=0;i<registeredinstitute.size();i++){
						InstituteAdminRegistration instituteregister=(InstituteAdminRegistration)(registeredinstitute.get(i));
						String domainname=instituteregister.getInstituteDomain().toString();
					
						// check institute with domain name exist
			
						if(domainname.equals(institutedomain)){
							flag=true;
						}
					}
				}

				if(flag){
	
					rundata.setMessage("Institute Already Registered ,Please Contact System Admin or Your Institute Admin for registered to you as a Institute Admin in same Institute");

				}
				else{
					//Insert these values in InstituteAdminRegistration .

					criteria=new Criteria();
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
					InstituteAdminRegistrationPeer.doInsert(criteria);
						
	                   		criteria = new Criteria();
	        	                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,institutedomain);
        	        	        List getinstid=InstituteAdminRegistrationPeer.doSelect(criteria);
					int instid=0;;
		                        if(getinstid.size() !=0){
        		                        for(int i=0;i<getinstid.size();i++){
                		                        InstituteAdminRegistration inst=(InstituteAdminRegistration)(getinstid.get(i));
                        		                instid=inst.getInstituteId();
	                               		}
                        		}
				
					//Insert these values in InstituteAdminUser

					criteria.add(InstituteAdminUserPeer.INSTITUTE_ID,instid);
					criteria.add(InstituteAdminUserPeer.ADMIN_FNAME,instituteadminfname);
					criteria.add(InstituteAdminUserPeer.ADMIN_LNAME,instituteadminlname);
					criteria.add(InstituteAdminUserPeer.ADMIN_EMAIL,instituteadminemail);
					criteria.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,instituteadmindesignation);
					criteria.add(InstituteAdminUserPeer.ADMIN_UNAME,adminusername);
					criteria.add(InstituteAdminUserPeer.ADMIN_PASSWORD,instituteadminpassword);
					InstituteAdminUserPeer.doInsert(criteria);
					rundata.setMessage("Institute Admin Registeration Successfull");
				}
			}
			else{
				rundata.setMessage("Special symbol and character is not allowed");
			}	
		}
		catch (Exception e)
		{
			rundata.setMessage("problem in registration method" +e);
		}
	}

	/*
	public void doDelete(RunData rundata, Context context) 
	{
		try{
			ParameterParser pp=rundata.getParameters();
			String InstituteId=pp.getString("instituteId");
			String InstituteName=pp.getString("instituteName");
			Criteria crit=new Criteria();
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,InstituteId);
			InstituteAdminRegistrationPeer.doDelete(crit);
			rundata.setMessage("Admin of" +InstituteName +"has been deleted");
		}
		catch (Exception e)
		{
			rundata.setMessage("problem in delete method" +e);
		}
	}
	
	public void doUpdate(RunData rundata, Context context) 
	{
		try{
			ParameterParser pp=rundata.getParameters();
			String InstituteId=pp.getString("instituteId");
			String InstituteName=pp.getString("instituteName");
			Criteria crit=new Criteria();
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,InstituteId);
			InstituteAdminRegistrationPeer.doUpdate(crit);
			rundata.setMessage("Details of" +InstituteName +"has been Updated");
		}
		catch (Exception e)
		{
			rundata.setMessage("problem in Update method" +e);
		}
	}*/

}

