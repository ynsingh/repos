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
import org.iitk.brihaspati.om.UserPrefPeer;
//utils classes

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserManagement;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.iitk.brihaspati.modules.utils.DeleteInstituteUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:palseema@rediffmail.com">Seema Pal </a>23April2012
 * @author <a href="mailto:singh_jaivir.rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author: <a href="mailto:shaistashekh@hotmail.com">Shaista </a>
 * @author: <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @modified date:20101020, 20101120, 20110117, 20120316
 *@Code tested by: (Sharad Singh,kishore kumar shukla)
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modify date: 09-08-2012 (Priyanka)
 */

/** class for accepting of a new institute as well institute admin in the brihaspati system
* adding other institute admin in the institute
* deleting institute, before deletion take backup of an institute
* deletng institute as well as institute admin by checking the other role exist
* in the brihaspati system
* code for rejection of a institute and update the expiry date of rejection(8 days) 
*/
public class Institute_RootAdmin extends SecureAction_Admin
{
	private String LangFile=new String();
	java.util.Date date= new java.util.Date();
	private Log log = LogFactory.getLog(this.getClass());
	/** 
	*  Method for giving approval to a institute by sysadmin.
	*/
	public void AcceptInstituteAdmin(RunData data, Context context)
	{
		try
		{
			Vector institutedetail=new Vector();
			/** 
			* parameter for the Multilanguage.
			*/
			ParameterParser pp = data.getParameters();
			LangFile = (String)data.getUser().getTemp("LangFile");
			/** 
			*  Get list of institute to be approved .
			*/
			String institutelist = data.getParameters().getString("deleteFileNames");
			UserManagement usermanagement = new UserManagement();
			String mode = data.getParameters().getString("mode");

			/* getting path of the directory for  the xml file */
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
                                        *Reading all details of an institute according to the Domain from the xml file(InstituteRegistrationList.xml)
					*and storing details in vector(institutedetail).
					*@see XMLWriter_InstituteRegistration (method:ReadInstDomainDeatils) in utils.
                                        */
					String instdomain= st.nextToken();
					// Maintain Log
					log.info("User Name --> Admin| Operation --> Approval of Institube Admin with domain = "+institutelist+"| Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
                                        if(mode.equals("pendinglist")){
                                           institutedetail=XMLWriter_InstituteRegistration.ReadInstDomainDeatils(filePath+"/InstituteRegistrationList.xml",instdomain);
                                        }
                                        else{
						/**
						*Reading all details of rejected institute according to the Domain
						*from the xml file(InstituteRejectList.xml) for approval
						*and storing details in vector(institutedetail).
						*@see XMLWriter_InstituteRegistration (method:ReadInstDomainDeatils) in utils. 
						*/
                                                institutedetail=XMLWriter_InstituteRegistration.ReadInstDomainDeatils(filePath+"/InstituteRejectList.xml",instdomain);
                                        }
					/**
					* getting all institute details from the vector(institutedetail)
					* for insert these value in database.
					*/
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
						
						/**Insert these values in 'InstituteAdminRegistration' table .*/
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

                                                /** get instituteId from 'InstituteAdminRegistration' table according to Domain
						* use the instituteId for insert the details of institute admin
						* in 'InstituteAdminUser' table.
						*/
                                                criteria = new Criteria();
                                                criteria.add(InstituteAdminRegistrationPeer.INSTITUTE_DOMAIN,instdomain);
                                                List getinstitutedetail = InstituteAdminRegistrationPeer.doSelect(criteria);
                                                int instid = ((InstituteAdminRegistration)(getinstitutedetail.get(0))).getInstituteId();
							
                                                /**Insert details of institute admin in InstituteAdminUser table
						* with ADMIN_PERMISSION_STATUS 1.
						* insert ADMIN_PERMISSION_STATUS 1 because institute admin registered with institute registration.
						* otherewise ADMIN_PERMISSION_STATUS 0,2.
						*/
                                                criteria=new Criteria();
                                                criteria.add(InstituteAdminUserPeer.INSTITUTE_ID,instid);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_UNAME,i_adminemail);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,i_admindesignation);
                                                criteria.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,1);
                                                InstituteAdminUserPeer.doInsert(criteria);

						/**Get ServerName and ServerPort for sending mail.*/
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
							
					//THIS IS NEW
					/*	 criteria = new Criteria();
                                                 int uid=UserUtil.getUID(i_adminemail);
                                                 criteria.add(UserPrefPeer.USER_ID,uid);
                                                 criteria.add(UserPrefPeer.ACTIVATION,"ACTIVATE");
                                                 UserPrefPeer.doUpdate(criteria);
					*/
						/** create user profile
						* insert the institute admin details in database. 
						*@see usermanagement in utils
                                                */
						
						String usermgmt = usermanagement.CreateUserProfile(i_adminuname,i_adminpassword,i_adminfname,i_adminlname,i_name,i_adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile,rollno,program,"cnfrm_i");// last parameter added by Priyanka
						data.setMessage(usermgmt);
						
						/**Read Quota value from the property file (Admin.properties).
						 *@see AdminProperties in utils.
						 */
						String path= "";
                                        	path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                        	String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                        	String instquota =AdminProperties.getValue(path,"brihaspati.user.iquota.value");

						/**Set institute quota in 'INSTITUTE_QUOTA' table*/

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
						
						/** Removing the institute details from the xml file
						* if from pending list (InstituteRegistrationList.xml)
						* else rejection list (InstituteRejectList.xml)
						* After accepting the institute. 
						*/
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
                /** get expirydate for updating the expiry date of institute in the xml file
		 *@see ExpiryUtil in utils
		 */
		String curdate=ExpiryUtil.getCurrentDate("-");
		String expdate=ExpiryUtil.getExpired(curdate,8);
		ParameterParser pp = data.getParameters();
		/**get language parameter for message*/

		LangFile = (String)data.getUser().getTemp("LangFile");

		/** get the institutelist from the template*/

                String institutelist = data.getParameters().getString("deleteFileNames");
		String mode=(data.getParameters()).getString("mode","");

		/** getting path of the institute directory for  the xml file */

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
					/** get the path of both xml file(InstituteRegistrationList.xml,InstituteRejectList.xml)
					 * if reject any institute then move
					 * the institute details from InstituteRegistrationList.xml to InstituteRejectList.xml
					 * @see XMLWriter_InstituteRegistration (mthod:MoveElement)in utils
					 * and also update the expiry date in InstituteRejectList.xml file
					 * @see XMLWriter_InstituteRegistration (mthod:UpdateRejectxml)in utils	
					 */
                                        String instdomain = st.nextToken();
					String filePathsrc=filePath+("/InstituteRegistrationList.xml");
                                        String filepathdest=filePath+("/InstituteRejectList.xml");
                                        String rejectelement=XMLWriter_InstituteRegistration.MoveElement(filePathsrc,instdomain,filepathdest);
					XMLWriter_InstituteRegistration.UpdateRejectxml(filepathdest,instdomain,expdate);
					String msg=MultilingualUtil.ConvertedString("instAreg_msg6",LangFile);
					//data.setMessage("Institute as well as institute admin has been rejected");							
					data.setMessage(msg);
					// Maintain Log
                                        java.util.Date date= new java.util.Date();
					log.info("User Name --> Admin| Operation --> Rejection of Institube Admin with domain= "+institutelist+"| Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
					
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

			/** Get parameter passed from templates.
			 * these parameters required to add the institute admin
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
				
				/** Check for user to already exist in same institute as an admin.*/
		
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
						/** select the InstituteStatus from the InstituteAdminRegistration table
						 * if InstituteStatus 1 that means we can add institute admin in that institute 
						 */
					
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
							/** insert the details of secondary institute admin
							 * with ADMIN_PERMISSION_STATUS 0 in the table InstituteAdminUser 
							 */
							crit.add(InstituteAdminUserPeer.INSTITUTE_ID,instituteid);
							crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesig);
							crit.add(InstituteAdminUserPeer.ADMIN_UNAME,adminemail);
							crit.add(InstituteAdminUserPeer.ADMIN_PERMISSION_STATUS,0);
							InstituteAdminUserPeer.doInsert(crit);
							
							/**Get ServerName and ServerPort for sending mail.*/  
							String serverName=data.getServerName();
	                        	                int srvrPort=data.getServerPort();
        	                        	        String serverPort=Integer.toString(srvrPort);
		
							/** Create User Profile
							 * insert the institute admin details in database. 
                                                         * @see usermanagement in utils
                                                         */
							UserManagement usermanagement = new UserManagement();
							usermgmt = usermanagement.CreateUserProfile(adminusername,password,adminfname,adminlname,instName,adminemail,"institute_admin","institute_admin",serverName,serverPort,LangFile,rollno,program,"");// last parameter added by Priyanka
							data.setMessage(usermgmt +" "+ mail_msg);
							// Maintain Log
							log.info("Addition of Secondary Institute Admin by Admin with email "+adminemail +" in " +instName +" | Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
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
					//"User is already exist as an Institute Admin "
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

                /**Get username and instituteid from the templates to delete a institute admin*/
                String username = pp.getString("username","");
                String instituteid = pp.getString("Institute_Id");
                context.put("Institute_Id",instituteid);
		
		/** get mode for set the template to same form*/
                String mode = pp.getString("mode");
                context.put("mode",mode);
		
		/** get the property file(brihaspati.properties) for the message*/
                String file=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");

                /** Delete InstituteAdmin from the selected institute
                 *@see DeleteInstituteUtil (method: DeleteInstAdmin) in Utils 
                 */
                String DelInstAdmin=DeleteInstituteUtil.DeleteInstAdmin(username,instituteid,LangFile,file);
                data.setMessage(DelInstAdmin);
		// Maintain Log
		log.info("Deletion of Institute Admin by Admin with email "+username +" in " +InstituteIdUtil.getIstName(Integer.parseInt(instituteid)) +" | Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
	}
	/**
	*This method called when Sysadmin update the detail of Institute admin
	*/
	public void doUpdateDetail(RunData data, Context context) throws Exception
	{
		/** Get the value of Institute,Institute admin for update to corresponding table*/

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

	
		Criteria crit=new Criteria();
		
		/**check status for the institute updation
		 * update the detail of institute
		 */	
		if(status.equals("instituteedit")){

			/** Update the field in 'INSTITUTE_ADMIN_REGISTRATION' table*/

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
			
			/** set the template for the same form*/
			data.setScreenTemplate("call,Root_Admin,InstituteList.vm");
			// Maintain Log
			log.info("Updation of Institute by Admin on userid --> "+instadname +id +" | Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
		}

		/**check status for the institute admin updation
                 * update the detail of institute admin
                 */     
	
		if(status.equals("instadminedit")){
			
			/** Update the field in 'INSTITUTE_ADMIN_USER' table*/	
			crit=new Criteria();
			crit.add(InstituteAdminUserPeer.ID,id);
			crit.add(InstituteAdminUserPeer.ADMIN_DESIGNATION,admindesignation);
			InstituteAdminUserPeer.doUpdate(crit);

			/** Update the field in 'TURBINE_USER' table*/
			crit=new Criteria();
			crit.add(TurbineUserPeer.USER_ID,uid);	
			crit.add(TurbineUserPeer.FIRST_NAME,adminfname);	
			crit.add(TurbineUserPeer.LAST_NAME,adminlname);	
			crit.add(TurbineUserPeer.EMAIL,email);
			TurbineUserPeer.doUpdate(crit);
			data.setScreenTemplate("call,Root_Admin,AddAdmin.vm");
			// Maintain Log
			log.info("Updation of Institute Admin by Admin on userid --> "+uid +" | Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
		}	
		String msg=MultilingualUtil.ConvertedString("instAreg_msg4",LangFile);
		data.setMessage(msg);
	}
	
	/**This method called when Sysadmin delete the Institute*/

	public void doDeleteInstitute(RunData data, Context context) throws Exception{
                try{
                        LangFile=data.getUser().getTemp("LangFile").toString();
			
			/**Get mode and instituteid from the templates to delete a institute */
                        String instid=data.getParameters().getString("instituteid");
			String mode=data.getParameters().getString("mode");
                	context.put("mode",mode);

                        /** Take Institute backup before deleting the institute. 
                         *@see DeleteInstituteUtil(method: InstituteBackup) in Utils
                         */
                        String backup=DeleteInstituteUtil.InstituteBackup(instid,LangFile);

			/** get the property file(brihaspati.properties) for the message*/
                        String file=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");

			/** get the Groupname of the Institute admin
			 *@see GroupUtil(method: getGroupName) in Utils
			 * by passing the GroupId 3
			 */
                        String gname=GroupUtil.getGroupName(3);
			
                        /** DeleteCourse of the deleted institute. 
                         *@see DeleteInstituteUtil (method:DeleteCourse) in Utils
                         */
                        String delcourse=DeleteInstituteUtil.DeleteCourse(instid,LangFile,file);
		
                        /** finally Delete Institute. 
                         *@see DeleteInstituteUtil (method:DeleteInstitute)in Utils
                         */
                        String delinst=DeleteInstituteUtil.DeleteInstitute(instid,LangFile,file,gname);
			data.setMessage(MultilingualUtil.ConvertedString("brih_Institue",LangFile)+" "+MultilingualUtil.ConvertedString("brih_hasbeendelete",LangFile));
		        // Maintain Log
			log.info("Deletion of Institute by Admin name --> "+InstituteIdUtil.getIstName(Integer.parseInt(instid)) +" | Date --> "+date+ "| IP Address --> "+data.getRemoteAddr());
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
