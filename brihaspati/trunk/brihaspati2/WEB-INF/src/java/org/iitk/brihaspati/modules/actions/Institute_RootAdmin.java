package org.iitk.brihaspati.modules.actions;

/**
 * @(#) Institute_RootAdmin.java
 *
 *  Copyright (c) 2009,2010 ETRG,IIT Kanpur.
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
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;

//torque classes

import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.om.TurbineUserGroupRolePeer;
import org.iitk.brihaspati.om.TurbineUserGroupRole;
import org.iitk.brihaspati.om.UsageDetailsPeer;
import org.iitk.brihaspati.om.UserConfigurationPeer;
//import org.iitk.brihaspati.om.TurbineUserPeer;

//utils classes

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;
import babylon.babylonUserTool;

/**
 *
 * @author <a href="mailto:singh_jaivir.rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
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
			/** 
			*  Get parameters passed from templates.
			*/
			ParameterParser pp = data.getParameters();
			String LangFile = (String)data.getUser().getTemp("LangFile");
			/** 
			*  Get list of institute to be approved .
			*/

			String institutelist = data.getParameters().getString("deleteFileNames");
			UserManagement usermanagement = new UserManagement();
			
			/**
			*    check for institute list not to be empty.	
			*/
			if(!institutelist.equals(""))
			{	
				StringTokenizer st = new StringTokenizer(institutelist,"^");
				for(int j = 0; st.hasMoreTokens(); j++)
				{
					String instituteid = st.nextToken();
					//ErrorDumpUtil.ErrorLog("IstituteId======>"+instituteid);
					Criteria crit = new Criteria();
					Criteria crit1 = new Criteria();
					/**
					*  Get details of an institute according to the institute id from 
					*  Institute Admin Registration table.
					*/
					crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
					List getinstitutedetail = InstituteAdminRegistrationPeer.doSelect(crit);
					int i_id = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteId();
					String i_name = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteName();
					String i_address = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstiuteAddress();
					String i_city = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getCity();
					String i_pincode = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getPincode();
					String i_state = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getState();
					String i_landline = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getLandlineNo();
					String i_domain = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteDomain();
					String i_type = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getTypeOfInstitution();
					String i_affiliation = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getAffiliation();
					String i_website = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteWebsite();
					/**
					*   Get Institute admin detail from Institute Admin User table.
					*/
					crit= new Criteria();
                                        crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                                        List getinstituteadmindetail = InstituteAdminUserPeer.doSelect(crit);

					String i_adminfname = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminFname();
					String i_adminlname = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminLname();
					String i_adminemail = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminEmail();
					String i_admindesignation = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminDesignation();
					String i_adminuname = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminUname();

					String i_adminpassword = ((InstituteAdminUser)(getinstituteadmindetail.get(0))).getAdminPassword();
					/**
					*   Get ServerName and ServerPort for sending mail.Call usermanagement 
					*   util method to create user profile and update Insitute status (1)for 
					*   approved in Institute Admin Registration table.  
					*/
					String serverName=data.getServerName();
	                                int srvrPort=data.getServerPort();
					String serverPort=Integer.toString(srvrPort);
					String usermgmt = usermanagement.CreateUserProfile(i_adminuname,i_adminpassword,i_adminfname,i_adminlname,i_adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile);
					crit= new Criteria();
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
					InstituteAdminRegistrationPeer.doUpdate(crit);
					data.setMessage(usermgmt);			
				}
			}
			
		}
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
                Date rejectdate=Date.valueOf(curdate);
		ParameterParser pp = data.getParameters();
		String LangFile = (String)data.getUser().getTemp("LangFile");
                String institutelist = data.getParameters().getString("deleteFileNames");
		try{
                        /**
                        *    check for institute list not to be empty.  
                        */
		        if(!institutelist.equals(""))
                        {
                                StringTokenizer st = new StringTokenizer(institutelist,"^");
                                for(int j = 0; st.hasMoreTokens(); j++)
                                {
                                        String instituteid = st.nextToken();
					/**
					*   Update institute status(Institute_status=2) for Rejecting.
					*/
                                        Criteria crit = new Criteria();
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                                        crit.add(InstituteAdminRegistrationPeer.REGISTRATION_DATE,rejectdate);
                                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"2");
					InstituteAdminRegistrationPeer.doUpdate(crit);
					String msg=MultilingualUtil.ConvertedString("instAreg_msg6",LangFile);
					//data.setMessage("Institute as well as institute admin has been rejected");								
					data.setMessage(msg);								
					
				}
			}	
		
		}
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
			UserManagement usermanagement = new UserManagement();
                        ParameterParser pp = data.getParameters();
			String usermgmt = "";
                        String LangFile = (String)data.getUser().getTemp("LangFile");
                        String instadmininf = data.getParameters().getString("deleteFileNames");
			/**
			*   Get parameter passed from templates.
			*/
			String instituteid = pp.getString("instituteid");
			String adminfname = pp.getString("IADMINFNAME");
			String adminlname = pp.getString("IADMINLNAME");
			String admindesig = pp.getString("IADMINDESIGNATION");
			String adminemail = pp.getString("IADMINEMAIL");
			String adminusername = adminemail;
			String adminpass = adminemail;
                        /**
                        *   Create password string by spliting email with "@" . 
                        */

			String adminpassword []= adminpass.split("@");
			String password = adminpassword[0];
			String encrPassword;
			Criteria crit = new Criteria();
			try{	
				
				/**
				*   Check for user to already exist in same institute as an admin.
				*/
		
				crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
				crit.add(InstituteAdminUserPeer.ADMIN_EMAIL,adminemail);
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
						encrPassword=EncryptionUtil.createDigest("MD5",password);
						crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
						List instdetail = InstituteAdminRegistrationPeer.doSelect(crit);
        	        		        List admindetail = null;
	                	      		inststat = ((InstituteAdminRegistration)instdetail.get(0)).getInstituteStatus();
						crit = new Criteria();
						/**
						*  Check for institute_status to addning institute admin.
						*/
						if((inststat == 1) || (inststat == 3))
						{
							crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
							crit.add(InstituteAdminUserPeer.ADMIN_FNAME,adminfname);
							crit.add(InstituteAdminUserPeer.ADMIN_LNAME,adminlname);
							crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesig);
							crit.add(InstituteAdminUserPeer.ADMIN_EMAIL,adminemail);
							crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminusername);
							crit.add(InstituteAdminUserPeer.ADMIN_PASSWORD,encrPassword);
							InstituteAdminUserPeer.doInsert(crit);
							String serverName=data.getServerName();
	                        	                int srvrPort=data.getServerPort();
        	                        	        String serverPort=Integer.toString(srvrPort);
							/**
							*   Create User Profile to call UserManagement util Method							  *   CreateUserProfile. 
							*/
							LangFile=(String)data.getUser().getTemp("LangFile");
							usermgmt = usermanagement.CreateUserProfile(adminusername,password,adminfname,adminlname,adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile);
							/**
							*Update institute status if institute is orphan to active.    							  */
							if(inststat==3)
							{
								crit=new Criteria();
								crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
								crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,1);
								InstituteAdminRegistrationPeer.doUpdate(crit);
							}
						}
					}
					else{
						data.setMessage("special character are not allowed in email except @ and .");
					}
				}
				else
				{
					data.setMessage("User is already exist as an Institute Admin ");
				}
			}
			catch(Exception ex){}	
				//data.setMessage("Add Admin=========>"+usermgmt);			
	}

	/**
	* This method is called when sysadmin delete an institute admin from an institute.
	*/
	
	public void DeleteAdmin(RunData data, Context context) throws Exception
	{
		babylonUserTool tool=new babylonUserTool();
		ParameterParser pp = data.getParameters();
		String action = data.getParameters().getString("actionName","");
		UserUtil userutil = new UserUtil();
		String Lang = data.getUser().getTemp("lang").toString();
		/**
		* Get username and instituteid passed by templates to delete a institute admin 
		*/
		String username = pp.getString("username","");
		String instituteid = pp.getString("Institute_Id");
		/**
		*Get userid with username
		*/
		int userid = UserUtil.getUID(username);	
		String uid=Integer.toString(userid);	
		Vector Messages=new Vector();
		Criteria crit=new Criteria();
		Criteria crit1=new Criteria();
		Criteria crit2=new Criteria();
		int in[]={1};
		String email="";
		/**
		*Get all Groups belongs to a user(institute admin) from TURBINE_USER_GROUP_ROLE table.
		*/
		crit.add(TurbineUserGroupRolePeer.USER_ID,uid);
		crit.addNotIn(TurbineUserGroupRolePeer.GROUP_ID,in);
		List lst=TurbineUserGroupRolePeer.doSelect(crit);

		TurbineUser element=(TurbineUser)UserManagement.getUserDetail(uid).get(0);
                email=element.getEmail();


		//UserGroupRoleUtil usergrouproleutil=new UserGroupRoleUtil();
		boolean flag=false;
		boolean flag1=false;
                crit=new Criteria();
                /**
                * get list of institute in which user is instituteadmin.
                */
                crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
                List instituteuser=InstituteAdminUserPeer.doSelect(crit);
                /**
                * Get no of institute admin in a institute
                */
                crit= new Criteria();
                crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                List noofadmin=InstituteAdminUserPeer.doSelect(crit);

		crit = new Criteria();
		crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
		List instname=InstituteAdminRegistrationPeer.doSelect(crit);
		String institutename=((InstituteAdminRegistration)instname.get(0)).getInstituteName();

		/**
		* if user(institute admin) belongs groups other then institute_admin. 
		*/

		if(lst.size()>1)
		{
			for(int i=0;i<instituteuser.size();i++)
			{
				/**
				* get instituteid in which user is institute admin.
				*/
				int instid=((InstituteAdminUser)instituteuser.get(i)).getInstituteId();
				String InstId=Integer.toString(instid);
				/**
				* check for user exist in other institute as an institute admin.
				*/
				if(!InstId.equals(instituteid))
				{
					/**
					*if exist jump to flag=true otherwise flag=flase 								  */
					flag=true;
					
				}
			}
			/**
			*  user is institute admin only in one institute.  
			*/
			if(flag==false)
			{
			
				int groupid=3;
				/**
				*  Delete the entry of institute admin from TURBINE_USER_GROUP_ROLE 
				*/
				crit2=new Criteria();
				crit2.add(TurbineUserGroupRolePeer.USER_ID,userid);
                	        crit2.add(TurbineUserGroupRolePeer.GROUP_ID,groupid);
			
				TurbineUserGroupRolePeer.doDelete(crit2);
				data.setMessage("Successfully Deleted1");
			}// end flag(false)
	

		}//enf if

		/** user is only institute-admin not in other group .check where user is admin 
		*   in any other institute  
		*/
		else
		{
			/**
			*  Get list of institute in which user is institute admin.
			*/
                        for(int i=0;i<instituteuser.size();i++)
                        {
				/**
				*  Get instituteid in which user is institute admin
				*/
                                int instid=((InstituteAdminUser)instituteuser.get(i)).getInstituteId();
                                String InstId=Integer.toString(instid);
				/**
				*  check if user is institute admin in other institute.
				*/
                                if(!InstId.equals(instituteid))
                                {
					/**
					*  if exist jump to flag1=true otherwise flag1=flase
					*/
                                        flag1=true;

                                }
                        }

			
	
			/**
			*   if user(institute admin) in only one institute delete all details from the system.
			*/
			if(flag1==false)
			{
				//Get the user
				User user=TurbineSecurity.getUser(username);
        	                /**
                	        * Remove the user's login details
                        	*/
	                        crit=new Criteria();
        	                crit.add(UsageDetailsPeer.USER_ID,userid);
                	        UsageDetailsPeer.doDelete(crit);
                        	/**
	                        * Remove the user's configuration details
        	                */
                	        crit=new Criteria();
                        	crit.add(UserConfigurationPeer.USER_ID,userid);
	                        UserConfigurationPeer.doDelete(crit);
				/**
				*remove the user from the system as well as babylon.
				*/
				TurbineSecurity.removeUser(user);
				tool.deleteUser(username);
                	        /**
                        	*Remove the user area of the user.
	                        */
        	                String userRealPath=TurbineServlet.getRealPath("/UserArea");
                	        String fileName=userRealPath+"/"+username;
                        	File f=new File(fileName);
	                        SystemIndependentUtil.deleteFile(f);
				//data.setMessage("Successfully Deleted2");
			

			}
		}//end else
                /**
                *  Delete user(institute admin) from an institute which exist in other institute
                *  as an institute admin
		*  if there is only one institute admin in an institute
                *  update the institute status as an orphan (INSTITUTE_STATUS=3).
                */
                if (noofadmin.size()==1)
                {
                	ErrorDumpUtil.ErrorLog("check for noadmin1=======>"+noofadmin.size());             
                        crit1=new Criteria();
                        crit1.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                        crit1.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,3);
                        InstituteAdminRegistrationPeer.doUpdate(crit1);
                }
		crit=new Criteria();
                /**
                *  Delete institute admin entry from the InstituteAdminUser table.
                */
                crit.add(InstituteAdminUserPeer.ADMIN_UNAME,username);
                crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
                InstituteAdminUserPeer.doDelete(crit);
		
		String server_name=TurbineServlet.getServerName();
                String srvrPort=TurbineServlet.getServerPort();
                String subject="";
		String messageFormate="";
		String Gname=institutename;
		Lang=MultilingualUtil.LanguageSelectionForScreenMessage(Lang);
		/**
		*  Properties file to read sebject message and mail message on secure and unsecure channel.
		*/
		String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
		Properties pr =MailNotification.uploadingPropertiesFile(fileName);
                if(srvrPort.equals("8080"))
                	subject="deleteinstadmin";
                else
                        subject="deleteinstadminhttps";

		/**
		*  Get mail subject and mail message for sending mail for deletion.
		*/
		String subj = MailNotification.subjectFormate(subject, "", pr );
		messageFormate = MailNotification.getMessage(subject, Gname, "", "", "", server_name, srvrPort,pr);
		String Mail_msg=MailNotification.sendMail(messageFormate, email, subj, "", Lang);

		
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
		String status=pp.getString("status");
		String instid=pp.getString("instituteid");
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
		}
		/**
		* Update the field in 'INSTITUTE_ADMIN_USER' table
		*/	
		if(status.equals("instadminedit")){
		crit=new Criteria();
		crit.add(InstituteAdminUserPeer.ID,id);
		crit.add(InstituteAdminUserPeer.ADMIN_FNAME,adminfname);	
		crit.add(InstituteAdminUserPeer.ADMIN_FNAME,adminfname);	
		crit.add(InstituteAdminUserPeer.ADMIN_LNAME,adminlname);	
		crit.add(InstituteAdminUserPeer.ADMIN_EMAIL,email);	
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
		context.put("mode","viewadmin");
		}	
		String msg=MultilingualUtil.ConvertedString("instAreg_msg4",LangFile);
		data.setMessage(msg);
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
		else
			data.setMessage("Action not found");		
				
	}
	
	

}
