package org.iitk.brihaspati.modules.actions;

/**
 * @(#) Institute_RootAdmin.java
 *
 *  Copyright (c) 2009-2011,2012 ETRG,IIT Kanpur.
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

import java.io.File;
import java.util.Properties;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.sql.Date;

//turbine classes

import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;

//torque classes

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteQuotaPeer;

//utils classes

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.iitk.brihaspati.modules.utils.DeleteInstituteUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;

/**
 * @author <a href="mailto:palseema@rediffmail.com">Seema Pal </a>23April2012
 * @author <a href="mailto:singh_jaivir.rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author: <a href="mailto:shaistashekh@hotmail.com">Shaista </a>
 * @author: <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @modified date:20101020, 20101120, 20110117, 20120316
 *@Code tested by: (Sharad Singh,kishore kumar shukla)
 */

/** 
*   This class basically used for accepting, adding, deleting and rejecting institute admin only.
*/

public class Institute_RootAdmin extends VelocitySecureAction
{
	private String LangFile=new String();

	protected boolean isAuthorized(RunData data) throws Exception
	{
		return true;
	}

	/** 
	*  Method for giving approval to a institute by sysadmin.
	*/

	public void AcceptInstituteAdmin(RunData data, Context context)
	{
		try
		{
			Vector institutedetail=new Vector();
			/** 
			*  Get parameters passed from templates.
			*/
			ParameterParser pp = data.getParameters();
			LangFile = (String)data.getUser().getTemp("LangFile");
			/** 
			*  Get list of institute to be approved .
			*/

			String institutelist = data.getParameters().getString("deleteFileNames");
			UserManagement usermanagement = new UserManagement();
			String mode = data.getParameters().getString("mode");
			String filePath=TurbineServlet.getRealPath("/InstituteRegistration");
			
			/**
			*    check for institute list not to be empty.	
			*/
			if(!institutelist.equals(""))
			{	
				StringTokenizer st = new StringTokenizer(institutelist,"^");
				for(int j = 0; st.hasMoreTokens(); j++)
				{
					/**
                                        *  Get details of an institute according to the Domain from 
                                        */
					String instdomain= st.nextToken();
                                        if(mode.equals("pendinglist")){
                                                institutedetail=XMLWriter_InstituteRegistration.ReadInstDomainDeatils(filePath+"/InstituteRegistrationList.xml",instdomain);
                                                //ErrorDumpUtil.ErrorLog("institutedetail pendinglist mode======"+institutedetail);
                                        }
                                        else{
                                                institutedetail=XMLWriter_InstituteRegistration.ReadInstDomainDeatils(filePath+"/InstituteRejectList.xml",instdomain);
                                        }
                                        for(int k=0;k<institutedetail.size();k++)
                                        {
                                                InstituteFileEntry ifdetail=(InstituteFileEntry) institutedetail.elementAt(k);
                                                String i_name =ifdetail.getInstituteName();
                                                String i_address =ifdetail.getInstituteAddress();
                                                String i_city =ifdetail.getInstituteCity();
                                                String i_pincode =ifdetail.getInstitutePincode();
                                                String i_state =ifdetail.getInstituteState();
                                                String i_landline =ifdetail.getInstituteLandLineNo();
                                                String i_domain =ifdetail.getInstituteDomain();
                                                String i_type =ifdetail.getInstituteType();
                                                String i_affiliation =ifdetail.getInstituteAffiliation();
                                                String i_website =ifdetail.getInstituteWebsite();
                                                String regdate=ifdetail.getInstituteRegDate();
                                                Date registrationdate=Date.valueOf(regdate);
                                                String expdate=ifdetail.getInstituteExpDate();
                                                Date exdate=Date.valueOf(expdate);
                                                String i_adminfname =ifdetail.getInstituteFName();
                                                String i_adminlname =ifdetail.getInstituteLName();
                                                String i_adminemail =ifdetail.getInstituteEmail();
                                                String i_admindesignation =ifdetail.getInstituteDesignation();
                                                String i_adminuname =ifdetail.getInstituteUserName();
                                                String i_adminpassword=ifdetail.getInstitutePassword();
						//Insert these values in InstituteAdminRegistration .
                                                Criteria criteria = new Criteria();
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,i_name);
                                                criteria.add(InstituteAdminRegistrationPeer.INSTIUTE_ADDRESS,i_address);
                                                criteria.add(InstituteAdminRegistrationPeer.CITY,i_city);
                                                criteria.add(InstituteAdminRegistrationPeer.PINCODE,i_pincode);
                                                criteria.add(InstituteAdminRegistrationPeer.STATE,i_state);
                                                criteria.add(InstituteAdminRegistrationPeer.LANDLINE_NO,i_landline);
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,i_domain);
                                                criteria.add(InstituteAdminRegistrationPeer.TYPE_OF_INSTITUTION,i_type);
                                                criteria.add(InstituteAdminRegistrationPeer.AFFILIATION,i_affiliation);
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_WEBSITE,i_website);
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,1);
                                                criteria.add(InstituteAdminRegistrationPeer.REGISTRATION_DATE,registrationdate);
                                                criteria.add(InstituteAdminRegistrationPeer.EXPIRY_DATE,exdate);
                                                InstituteAdminRegistrationPeer.doInsert(criteria);

                                                // getting instituteId from InstituteAdminRegistration table according to Domain
                                                criteria = new Criteria();
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,instdomain);
                                                List getinstitutedetail = InstituteAdminRegistrationPeer.doSelect(criteria);
                                                int instid = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteId();
                                                //Insert these values in InstituteAdminUser
                                                criteria=new Criteria();
                                                criteria.add(InstituteAdminUserPeer.INSTITUTE_ID,instid);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_UNAME,i_adminemail);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,i_admindesignation);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,1);
                                                InstituteAdminUserPeer.doInsert(criteria);
						/**
						*   Get ServerName and ServerPort for sending mail.Call usermanagement 
						*   util method to create user profile and update Insitute status (1)for 
						*   approved in Institute Admin Registration table.  
						*/
						String serverName=data.getServerName();
	                                	int srvrPort=data.getServerPort();
						String serverPort=Integer.toString(srvrPort);
						String rollno = data.getParameters().getString("rollno","").trim();
						/**
			                 	* check if rollno have any special character then return message
	                                 	*/
						if(StringUtil.checkString(rollno) != -1)
                        			{
		                                	data.addMessage(MultilingualUtil.ConvertedString("c_msg3",LangFile));
                		               		return;
		                        	}
						String program = data.getParameters().getString("prg","");
						String usermgmt = usermanagement.CreateUserProfile(i_adminuname,i_adminpassword,i_adminfname,i_adminlname,i_name,i_adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile,rollno,program);
						data.setMessage(usermgmt);
						/**
					 	*Set Quota in 'INSTITUTE_QUOTA' table
					 	*/
						String path= "";
                                        	path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                        	String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                        	String instquota =AdminProperties.getValue(path,"brihaspati.user.iquota.value");
                                        	criteria = new Criteria();
                                        	criteria.add(InstituteQuotaPeer.INSTITUTE_ID,instid);
                                        	criteria.add(InstituteQuotaPeer.INSTITUTE_AQUOTA,instquota);
                                        	InstituteQuotaPeer.doInsert(criteria);
					 	/**
                                         	* Creating default Institute Admin Profile while accepting Institute admin
                                         	* The values are set as a default value because at initial stage no institute profile exist.
                                        	*/
                                        	path = "";
                                        	path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instid+"Admin.properties";
                                        	AdminProperties.setValue(path,conf,"brihaspati.admin.listconfiguration.value");
                                        	AdminProperties.setValue(path,"365","brihaspati.admin.courseExpiry");
                                        	AdminProperties.setValue(path,i_domain,"brihaspati.mail.local.domain.name");
                                        	AdminProperties.setValue(path,"500","brihaspati.admin.quota.value");
                                        	AdminProperties.setValue(path,"100","brihaspati.user.quota.value");
                                        	AdminProperties.setValue(path,"45","brihaspati.admin.FaqExpiry");
                                        	AdminProperties.setValue(path,"365","brihaspati.user.expdays.value");
						String remfrom="";
                                        	if(mode.equals("pendinglist"))
                                        		remfrom=XMLWriter_InstituteRegistration.RemoveElement(filePath+"/InstituteRegistrationList.xml",instdomain);
                                        	else
                                        		remfrom=XMLWriter_InstituteRegistration.RemoveElement(filePath+"/InstituteRejectList.xml",instdomain);
					}//for
				}//for
			}//if
		}//try
		catch(Exception e)
		{
			data.setMessage("Error in Acceptence.."+e);
		}
	}//end method
	
        /** 
        *  Method to reject an institute by sysadmin.
        */
        public void RejectInstituteAdmin(RunData data, Context context)
        {
               /** 
               *  Get parameters passed from templates.
               */
		String curdate=ExpiryUtil.getCurrentDate("-");
		String expdate=ExpiryUtil.getExpired(curdate,8);
		ParameterParser pp = data.getParameters();
		LangFile = (String)data.getUser().getTemp("LangFile");
                String institutelist = data.getParameters().getString("deleteFileNames");
		String mode=(data.getParameters()).getString("mode","");
		String filePath=TurbineServlet.getRealPath("/InstituteRegistration");
		try{
                        /**
                        *    check for institute list not to be empty.  
                        */
		        if(!institutelist.equals(""))
                        {
                                StringTokenizer st = new StringTokenizer(institutelist,"^");
                                for(int j = 0; st.hasMoreTokens(); j++)
                                {
                                        String instdomain = st.nextToken();
					String filePathsrc=filePath+("/InstituteRegistrationList.xml");
                                        String filepathdest=filePath+("/InstituteRejectList.xml");
                                        String rejectelement=XMLWriter_InstituteRegistration.MoveElement(filePathsrc,instdomain,filepathdest);
					XMLWriter_InstituteRegistration.UpdateRejectxml(filepathdest,instdomain,expdate);
					String msg=MultilingualUtil.ConvertedString("instAreg_msg6",LangFile);
					//data.setMessage("Institute as well as institute admin has been rejected");							
					data.setMessage(msg);								
					
				}
			}//if	
		}//try
		catch(Exception e)
                {
                        data.setMessage("Error in Reject."+e);
                }
        }
	/**
	*  Method is called to add more institute admin in an institute by sysadmin.
	*/

        public void AddAdmin(RunData data, Context context)	
	{
			StringUtil str=new StringUtil();
                        ParameterParser pp = data.getParameters();
			String usermgmt = "";
			String mail_msg="";
                        LangFile = (String)data.getUser().getTemp("LangFile");
			MultilingualUtil mu = new MultilingualUtil();
			/**
			*   Get parameter passed from templates.
			*/
			String adminfname = pp.getString("IADMINFNAME");
			String adminlname = pp.getString("IADMINLNAME");
			String admindesig = pp.getString("IADMINDESIGNATION");
			String adminemail = pp.getString("IADMINEMAIL");
			String adminusername = adminemail;
			String adminpass = adminemail;
			String rollno = pp.getString("rollno","").trim();
			String mode = pp.getString("mode");
			String instituteid = pp.getString("Institute_Id");
                        context.put("Institute_Id",instituteid);
			/**
                         * check if rollno have any special character then return message
                         */
			if(StringUtil.checkString(rollno) != -1)
                        {
                                data.addMessage(MultilingualUtil.ConvertedString("c_msg3",LangFile));
                               return;
                        }
			String program = pp.getString("prg","");
			String instName=InstituteIdUtil.getIstName(Integer.parseInt(instituteid));

                        /**
                        *   Create password string by spliting email with "@" . 
                        */

			String adminpassword []= adminpass.split("@");
			String password = adminpassword[0];
			//String encrPassword;
			Criteria crit = new Criteria();
			try{	
				
				/**
				*   Check for user to already exist in same institute as an admin.
				*/
		
				crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
				crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminemail);
				List userexistininstitute=InstituteAdminUserPeer.doSelect(crit);
				if(userexistininstitute.size()==0)
				{
					int inststat=0;
					/**
					*   Check for special character.
					*/
					if(str.checkString(adminusername)==-1 && str.checkString(adminfname)==-1 && str.checkString(adminlname)==-1)
                                	{
						crit=new Criteria();
						crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
						List instdetail = InstituteAdminRegistrationPeer.doSelect(crit);
	                	      		inststat = ((InstituteAdminRegistration)instdetail.get(0)).getInstituteStatus();
						crit = new Criteria();
						/**
						*  Check for institute_status to addning institute admin.
						*/
						if(inststat == 1)
						{
							crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
							crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesig);
							crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminemail);
							crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,0);
							InstituteAdminUserPeer.doInsert(crit);
							String serverName=data.getServerName();
	                        	                int srvrPort=data.getServerPort();
        	                        	        String serverPort=Integer.toString(srvrPort);
							/**
							*   Create User Profile to call UserManagement util Method							  *   CreateUserProfile. 
							*/
							UserManagement usermanagement = new UserManagement();
							usermgmt = usermanagement.CreateUserProfile(adminusername,password,adminfname,adminlname,instName,adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile,rollno,program);
							data.setMessage(usermgmt +" "+ mail_msg);
						}//if
					}//charif
					else{
						data.setMessage(mu.ConvertedString("brih_specialSymbol&char", LangFile)+" "+mu.ConvertedString("Notallow", LangFile)+" "+(mu.ConvertedString("brih_exceptAtTheRate&Dot",LangFile) +"!!"));
						//special character are not allowed in email except @ and .
					}
				}////if userexists
				else
				{
					data.setMessage(mu.ConvertedString("brih_user", LangFile) +" "+mu.ConvertedString("Wikiaction6", LangFile) +" "+mu.ConvertedString("brih_asAn", LangFile)+" "+mu.ConvertedString("brih_institute", LangFile)+" "+mu.ConvertedString("brih_admin", LangFile));
					//"User is already exist as an Institute Admin ");
				}
				context.put("mode","viewadmin");
			}
			catch(Exception ex){}	
	}

	/**
	* This method is called when sysadmin delete an institute admin from an institute.
	*/
	
	public void DeleteAdmin(RunData data, Context context) throws Exception
	{
                ParameterParser pp = data.getParameters();
                LangFile = (String)data.getUser().getTemp("LangFile");
                /**
                * Get username and instituteid passed by templates to delete a institute admin 
                */
                String username = pp.getString("username","");
                String instituteid = pp.getString("Institute_Id");
                context.put("Institute_Id",instituteid);
                String mode = pp.getString("mode");
                context.put("mode",mode);
                String file=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                /** Delete InstituteAdmin from the selected institute
                *By calling method DeleteInstAdmin
                *@see DeleteInstituteUtil in Utils
                */
                String DelInstAdmin=DeleteInstituteUtil.DeleteInstAdmin(username,instituteid,LangFile,file);
                data.setMessage(DelInstAdmin);
	}
	/**
	*This method called when Sysadmin update the detail of Institute admin
	*/
	public void doUpdateDetail(RunData data, Context context) throws Exception
	{
		/**
		* Get the value of Institute,Institute admin for update to corresponding table
		*/
		String LangFile=data.getUser().getTemp("LangFile").toString();
		ParameterParser pp = data.getParameters();
		String instadname=pp.getString("iadname");
		int uid=UserUtil.getUID(instadname);
		int id=pp.getInt("id");
		String instname=pp.getString("INAME");
		String instadd=pp.getString("IADDRESS");
		String instcity=pp.getString("ICITY");
		String instpincode=pp.getString("IPINCODE");
		String inststate=pp.getString("ISTATE");
		String instlandln=pp.getString("ILANDLINE");
		String instdomain=pp.getString("IDOMAIN");
		String insttype=pp.getString("ITYPE");
		String instwebsite=pp.getString("IWEBSITE");
		String adminfname=pp.getString("IADMINFNAME");
		String adminlname=pp.getString("IADMINLNAME");
		String admindesignation=pp.getString("IADMINDESIGNATION");
		String email=pp.getString("IADMINEMAIL");
		String status=pp.getString("status");
                context.put("status",status);
                String mode=pp.getString("mode");
                context.put("mode",mode);
                String tdcolor=pp.getString("count");
                context.put("count",tdcolor);
                String instid=pp.getString("Institute_Id");
                context.put("Institute_Id",instid);

		/**
		* Update the field in 'INSTITUTE_ADMIN_REGISTRATION' table
		*/	
		Criteria crit=new Criteria();
		if(status.equals("instituteedit")){
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instid);	
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_NAME,instname);	
			crit.add(InstituteAdminRegistrationPeer.INSTIUTE_ADDRESS,instadd);	
			crit.add(InstituteAdminRegistrationPeer.CITY,instcity);	
			crit.add(InstituteAdminRegistrationPeer.PINCODE,instpincode);	
			crit.add(InstituteAdminRegistrationPeer.STATE,inststate);	
			crit.add(InstituteAdminRegistrationPeer.LANDLINE_NO,instlandln);	
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,instdomain);	
			crit.add(InstituteAdminRegistrationPeer.TYPE_OF_INSTITUTION,insttype);	
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_WEBSITE,instwebsite);	
			InstituteAdminRegistrationPeer.doUpdate(crit);
			data.setScreenTemplate("call,Root_Admin,InstituteList.vm");
		}
		/**
		* Update the field in 'INSTITUTE_ADMIN_USER' table
		*/	
		if(status.equals("instadminedit")){
			crit=new Criteria();
			crit.add(InstituteAdminUserPeer.ID,id);
			crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesignation);
			InstituteAdminUserPeer.doUpdate(crit);
			/**
			* Update the field in 'TURBINE_USER' table
			*/
			crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);	
			crit.add(TurbineUserPeer.FIRST_NAME,adminfname);	
			crit.add(TurbineUserPeer.LAST_NAME,adminlname);	
			crit.add(TurbineUserPeer.EMAIL,email);
			TurbineUserPeer.doUpdate(crit);
			data.setScreenTemplate("call,Root_Admin,AddAdmin.vm");
		}	
		String msg=MultilingualUtil.ConvertedString("instAreg_msg4",LangFile);
		data.setMessage(msg);
	}
	public void doDeleteInstitute(RunData data, Context context) throws Exception{
                try{
                        LangFile=data.getUser().getTemp("LangFile").toString();
                        String instid=data.getParameters().getString("instituteid");
			String mode=data.getParameters().getString("mode");
                	context.put("mode",mode);

                        /** Institute backup. 
                        *By calling method InstituteBackup.
                        *@see DeleteInstituteUtil in Utils
                        */
                        String backup=DeleteInstituteUtil.InstituteBackup(instid,LangFile);
                        String file=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                        String gname=GroupUtil.getGroupName(3);
                        /** DeleteCourse. 
                        *By calling method DeleteCourse.
                        *@see DeleteInstituteUtil in Utils
                        */
                        String delcourse=DeleteInstituteUtil.DeleteCourse(instid,LangFile,file);
                        /** Delete Institute. 
                        *By calling method DeleteInstitute.
                        *@see DeleteInstituteUtil in Utils
                        */
                        String delinst=DeleteInstituteUtil.DeleteInstitute(instid,LangFile,file,gname);
			data.setMessage(MultilingualUtil.ConvertedString("brih_Institue",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
                }//try
                catch(Exception ex){data.setMessage("Error in Institute_RootAdmin method doDeleteInstitute"+ex);}
        }
	public void doPerform(RunData data, Context context) throws Exception
	{
		String action = data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_AcceptAdmin"))
			AcceptInstituteAdmin(data, context);
		else if(action.equals("eventSubmit_RejectAdmin"))
			RejectInstituteAdmin(data, context);	
		else if(action.equals("eventSubmit_AddAdmin"))
			AddAdmin(data,context);
                else if(action.equals("eventSubmit_doDelete"))
                        DeleteAdmin(data,context);
                else if(action.equals("eventSubmit_doUpdate"))
                        doUpdateDetail(data,context);
		else if(action.equals("eventSubmit_doDeleteInstitute"))
                       doDeleteInstitute(data,context);
		else
			data.setMessage("Action not found");		
				
	}
}
