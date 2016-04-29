package org.iitk.brihaspati.modules.actions;

/*
 * @(#)InstchangeAParam.java	
 *
 *  Copyright (c) 2010 - 2012 ETRG,IIT Kanpur. 
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
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 */
import java.io.File;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.apache.turbine.services.security.TurbineSecurity;
import java.util.List;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;

/**
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @author <a href="mailto:singh_jaivir@gmail.com">Jaivir Singh</a>29apr2011
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modified date: 15-10-2012
 */
//Last update 19-10-2011(Sunil)
/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date: 18-05-2015 (Seemanti);
 * @modified date: 25-04-2016 (Seemanti);Get Max. Upload file size from user and set into Institute Admin's properties file. 
 */

public class InstchangeAParam extends SecureAction_Institute_Admin{

	/**
	 * This method updates the first, last name and configuration 
	 * parameters of Institute Admin
	 * @see MultilingualUtil in  utils
	 * @param data RunData
	 * @param context Context
	 * @return nothing
	 */
	private String LangFile=null;
	MultilingualUtil m_u= new MultilingualUtil();
	public void doUpdate(RunData data, Context context) throws Exception{
		/**
		 * Get the user object from RunData for the user
		 * currently logged in
		 */
	
		User user=data.getUser();
		/**
 		*Get User Name and uid for getting the InstituteId.
		*@see UserUtil in utils
		*@see InstituteIdUtil in utils. 	
 		*/ 
		String uname=user.getName();
		int uid=UserUtil.getUID(uname);
		//String instituteid=InstituteIdUtil.getAdminInstId(uid);13may11

		/**
                 * getting property file According to selection of Language in temporary variable 
		 * getting the values of first,last names and
		 * configuration parameter.
		 */

		LangFile=(String)user.getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		//Institute Id
		String instituteid=user.getTemp("Institute_id").toString(); 
		//Admin First Name
		String AFName=pp.getString("AFName","");
		//Admin Last Name
		String ALName=pp.getString("ALName","");
		//List config Value
	 	String AdminConf = pp.getString("AdminConf","");	
		//Course Expiry Time
	 	String AdminCrsExp = pp.getString("AdminCrsExp","");	
		//Local Doamin Name	
	 	String domainNM = pp.getString("mailDomain","");	
		//Email
	 	String eMail = pp.getString("eMail","");	
		//Course Quota
	 	String aquota = pp.getString("cquota","");
		//User Quota
	 	String uquota = pp.getString("uquota","");
		//Faq Expiry time in days
		String AdminFaqExp=pp.getString("AdminFaqExp","");
		//Expiry Days
		String expdays = pp.getString("expdays","");
                String Max_File_upld_no = pp.getString("Max_File_upld_no","");
                ErrorDumpUtil.ErrorLog(" value of parameteer ..........."+Max_File_upld_no);
		String name=AFName+ALName;
                String address=pp.getString("address");
                String state=pp.getString("state");
                String country=pp.getString("country");
                String department=pp.getString("department");
                String designation=pp.getString("designation");

		String officeno=pp.getString("Offprefix","x")+"-"+pp.getString("Offccode","x")+"-"+pp.getString("Offrcode","x")+"-"+pp.getString("Offphnumber","x");
                String mobileno=pp.getString("Mobprefix","x")+"-"+pp.getString("Mobccode","x")+"-"+pp.getString("Mobrcode","x")+"-"+pp.getString("Mobphnumber","x");
                String homeno=pp.getString("Homeprefix","x")+"-"+pp.getString("Homeccode","x")+"-"+pp.getString("Homercode","x")+"-"+pp.getString("Homephnumber","x");
                String otherno=pp.getString("Othprefix","x")+"-"+pp.getString("Othccode","x")+"-"+pp.getString("Othrcode","x")+"-"+pp.getString("Othphnumber","x");

                String offdirectory=pp.getString("Offdirectory");
                String mobdirectory=pp.getString("Mobdirectory");
                String homedirectory=pp.getString("Homedirectory");
                String othdirectory=pp.getString("Othdirectory");

		//For email verification
		String email_msg="";
		// Get value of email as in TURBINE_USER
		Criteria crit = new Criteria();
                crit.add(TurbineUserPeer.USER_ID,uid);
                List list = TurbineUserPeer.doSelect(crit);
                String oldEmail =((TurbineUser)list.get(0)).getEmail();
		ErrorDumpUtil.ErrorLog("Old email = "+oldEmail);
                EmailVerification everify = new EmailVerification(data);
		

		// -----------------Telephone Directory -----------------------------------------
		/* Insert or update value into database
 		* Insert value in table "TELEPHONE_DIRECTORY" when corresponding data are not present
		* and update value in table "TELEPHONE_DIRECTORY" when corresponding data are present
		*/
	
		Vector instid1=InstituteIdUtil.getAllInstId(uid);
                                String str11="";
                                String instid="";
                                String str33="";
                                try{
                                        for(int j=0;j<instid1.size();j++){
                                                str11=instid1.elementAt(j).toString();
                                                if(!str11.equals(str33)){
                                                        instid=instid+"/"+str11;
                                                }
                                        str33=str11;
                                        }
                                }catch(Exception ex){ErrorDumpUtil.ErrorLog("The error in getting institute Id in institute admin Profile User Action "+ex);};


				
				List li=null;
                                Criteria tele = new Criteria();

                                tele.add(TelephoneDirectoryPeer.USER_ID,uid);
                                
                                               li = TelephoneDirectoryPeer.doSelect(tele);
                                                if(li.size()>0) {
                                                        TelephoneDirectory element=(TelephoneDirectory)(li.get(0));
                                                        int id=(element.getId());
                                                        tele.add(TelephoneDirectoryPeer.ID,id);
							//added for email verification
							oldEmail = (element.getMailId());
                                                 }
                                ErrorDumpUtil.ErrorLog("Old email = "+oldEmail);
				/**
 				 * now email will be updated after verification
				 * thus value of old email will be maintained.
				 * It will be same as of TURBINE_USER in case email is being updated
				 * and will remain as it is, in case email is not updated.
				 */
				tele.add(TelephoneDirectoryPeer.MAIL_ID,oldEmail);
				//tele.add(TelephoneDirectoryPeer.MAIL_ID,eMail);
                                if(!eMail.equals(""))
				{
					ErrorDumpUtil.ErrorLog("New email = "+eMail);
					email_msg = everify.profileDetails(eMail, uname, LangFile, false);
				}
					
                                tele.add(TelephoneDirectoryPeer.NAME, name);
                                tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                                tele.add(TelephoneDirectoryPeer.STATE, state);
                                tele.add(TelephoneDirectoryPeer.COUNTRY, country);
                                tele.add(TelephoneDirectoryPeer.DEPARTMENT, department);
                                tele.add(TelephoneDirectoryPeer.DESIGNATION, designation);
				tele.add(TelephoneDirectoryPeer.INSTITUTE_ID,instid);
                                if(offdirectory.equals("Public")){
                                        String PubOffNo="1-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, PubOffNo);
                                        }
                                        else if(offdirectory.equals("Protected")){
                                        String ProOffNo="2-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, ProOffNo);
                                        }
                                        else{
					String PriOffNo="3-"+officeno;
                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, PriOffNo);
                                        }
                                        if(mobdirectory.equals("Public")){
                                        String PubMobNo="1-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PubMobNo);
                                        }
                                        else if(mobdirectory.equals("Protected")){
                                        String ProMobNo="2-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, ProMobNo);
                                        }
                                        else{
                                        String PriMobNo="3-"+mobileno;
                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PriMobNo);
                                        }
                                        if(homedirectory.equals("Public")){
                                        String PubHomeNo="1-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, PubHomeNo);
                                        }
                                        else if(homedirectory.equals("Protected")){
                                        String ProHomeNo="2-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, ProHomeNo);
                                        }
                                        else{
                                        String PriHomeNo="3-"+homeno;
                                        tele.add(TelephoneDirectoryPeer.HOME_NO, PriHomeNo);
                                        }
                                        if(othdirectory.equals("Public")){
                                        String PubOthNo="1-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, PubOthNo);
					}
                                        else if(othdirectory.equals("Protected")){
                                        String ProOthNo="2-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, ProOthNo);
                                        }
                                        else{
                                        String PriOthNo="3-"+otherno;
                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, PriOthNo);
                                        }
                                        if(li.size()==0)
                                                TelephoneDirectoryPeer.doInsert(tele);
                                        else
                                                TelephoneDirectoryPeer.doUpdate(tele);

		// -------------------------------------------------------------------------------
		ErrorDumpUtil.ErrorLog("After making changes in TELEPHONE_DIRECTORY");
		/**
		 * Replacing the variable value from Property file
		 * Update the first,last name configuration parameter values for Institute Admin
		 * @see InstituteName+Admin.Properties in utils
		 */
		//iname=Institute name
		String path="";	
		path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";
		File f = new File(path);
       		if (!f.exists()) {
                   f.createNewFile();
		}
		StringUtil S = new StringUtil();
		String prof_update=null;
		if (S.checkString(AFName)==-1 && S.checkString(ALName)==-1){
			user.setFirstName(AFName);
			user.setLastName(ALName);
			TurbineSecurity.saveUser(user);
			AdminProperties.setPropertyValue(path,AdminConf,"brihaspati.admin.listconfiguration.value");
			AdminProperties.setPropertyValue(path,AdminCrsExp,"brihaspati.admin.courseExpiry");
			AdminProperties.setPropertyValue(path,domainNM,"brihaspati.mail.local.domain.name");
			AdminProperties.setPropertyValue(path,aquota,"brihaspati.admin.quota.value");
			AdminProperties.setPropertyValue(path,uquota,"brihaspati.user.quota.value");
		        AdminProperties.setPropertyValue(path,AdminFaqExp,"brihaspati.admin.FaqExpiry");
			AdminProperties.setPropertyValue(path,expdays,"brihaspati.user.expdays.value");// Add by @tej
                        AdminProperties.setPropertyValue(path,Max_File_upld_no,"brihaspati.user.maxFileUploadSize.value");
			//setTemplate(data,"Index.vm");
			//For email verification
			if(email_msg.equals("Successfull"))
	                        prof_update=MultilingualUtil.ConvertedString("usr_prof3",LangFile);
                        else if(email_msg.equals("Exists"))
                                prof_update=MultilingualUtil.ConvertedString("usr_prof5",LangFile);
                        else if(email_msg.equals("UnSuccessfull") || email_msg.equals(""))
                                prof_update=MultilingualUtil.ConvertedString("usr_prof4",LangFile);
                        else if(email_msg.equals("Email_updated"))
                                prof_update=MultilingualUtil.ConvertedString("usr_prof",LangFile);
                        else
				prof_update=m_u.ConvertedString("usr_prof",LangFile);
			data.setMessage(prof_update);
			boolean qct=QuotaUtil.CreateandUpdate();	
			//data.addMessage("Disk space is update for user and Courses");
			data.addMessage(m_u.ConvertedString("qmgmt_msg6",LangFile));
			}
		else
			prof_update=m_u.ConvertedString("usr_prof1",LangFile);
			data.setMessage(prof_update);
	}	

	/**
	 * This is the default method called when the action is not
	 * found
	 * @param data RunData
	 * @param context Context
	 */

	public void doPerform(RunData data, Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		LangFile=(String)data.getUser().getTemp("LangFile");
		if(action.equals("eventSubmit_doUpdate"))
			doUpdate(data,context);
		else
		{
			String msg=MultilingualUtil.ConvertedString("usr_prof2",LangFile);
			data.setMessage(msg);
		}
	}
}
