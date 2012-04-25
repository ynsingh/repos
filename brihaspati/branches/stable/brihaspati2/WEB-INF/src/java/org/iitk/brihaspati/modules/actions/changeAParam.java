package org.iitk.brihaspati.modules.actions;

/*
 * @(#)changeAParam.java	
 *
 *  Copyright (c) 2005-2006,2009-2010 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.security.TurbineSecurity;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.apache.torque.util.Criteria;
import java.util.List;

/**
 * @author <a href="mailto:nksinghiitk@yahoo.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:chitvesh@yahoo.com">Chitvesh Dutta</a>
 * @author <a href="mailto:awadhk_t@yahoo.com">Awahesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @modified date: 17-10-2009, 29-09-2010
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 */

//public class changeAParam extends SecureAction_Admin{
public class changeAParam extends SecureAction{

	/**
	 * This method updates the first, last name and configuration 
	 * parameters of Admin
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
		String loginName=user.getName();
                int uid=UserUtil.getUID(loginName);

		/**
                 * getting property file According to selection of Language in temporary variable 
		 * getting the values of first,last names and
		 * configuration parameter.
		 */


		LangFile=(String)user.getTemp("LangFile");
		ParameterParser pp=data.getParameters();
		String AFName=pp.getString("AFName","");
		String ALName=pp.getString("ALName","");
	 	String AdminConf = pp.getString("AdminConf","");	
	 	String AdminCrsExp = pp.getString("AdminCrsExp","");	
	 	String mailServ = pp.getString("mailServ","");
		String mailServPort = pp.getString("mailServPort","");	
	 	String mailFrom = pp.getString("mailFrom","");	
	 	String muName = pp.getString("muName","");	
	 	String mPass = pp.getString("mPass","");	
	 	String eMail = pp.getString("eMail","");
	 	String domainNM = pp.getString("mailDomain","");	
                String iquota = pp.getString("iquota","");
	 	String aquota = pp.getString("cquota","");
	 	String uquota = pp.getString("uquota","");
	 	String hdir = pp.getString("hdir","");
		String AdminFaqExp=pp.getString("AdminFaqExp","");
		String fileupldsze=pp.getString("upldsze","");
		String name=AFName+ALName;
                String address=pp.getString("address","");
                String state=pp.getString("state","");
                String country=pp.getString("country","");
                String department=pp.getString("department","");
                String designation=pp.getString("designation","");
                String officeno=pp.getString("Offprefix","")+"-"+pp.getString("Offccode","")+"-"+pp.getString("Offrcode","")+"-"+pp.getString("Offphnumber","");
                String mobileno=pp.getString("Mobprefix","")+"-"+pp.getString("Mobccode","")+"-"+pp.getString("Mobrcode","")+"-"+pp.getString("Mobphnumber","");
                String homeno=pp.getString("Homeprefix","")+"-"+pp.getString("Homeccode","")+"-"+pp.getString("Homercode","")+"-"+pp.getString("Homephnumber","");
                String otherno=pp.getString("Othprefix","")+"-"+pp.getString("Othccode","")+"-"+pp.getString("Othrcode","")+"-"+pp.getString("Othphnumber","");
                String offdirectory=pp.getString("Offdirectory","");
                String mobdirectory=pp.getString("Mobdirectory","");
                String homedirectory=pp.getString("Homedirectory","");
                String othdirectory=pp.getString("Othdirectory","");

		// --------------------------Telephone Directory------------------------
		/* Insert or update value into database
		 * Insert value in table "TELEPHONE_DIRECTORY" when corresponding data are not present
		 * and update value in table "TELEPHONE_DIRECTORY" when corresponding data are present
		 */ 
		List li=null;
                                Criteria tele = new Criteria();
                                tele.add(TelephoneDirectoryPeer.USER_ID,uid);
                                li = TelephoneDirectoryPeer.doSelect(tele);
				
                                if(li.size()>0) {
                                	TelephoneDirectory element=(TelephoneDirectory)(li.get(0));
                                        int id=(element.getId());
                                        tele.add(TelephoneDirectoryPeer.ID,id);
                              	}
                               
				try {
                                	tele.add(TelephoneDirectoryPeer.MAIL_ID,eMail);
	                                tele.add(TelephoneDirectoryPeer.NAME, name);
        	                        tele.add(TelephoneDirectoryPeer.ADDRESS, address);
                	                tele.add(TelephoneDirectoryPeer.STATE, state);
                        	        tele.add(TelephoneDirectoryPeer.COUNTRY, country);
                                	tele.add(TelephoneDirectoryPeer.DEPARTMENT, department);
	                                tele.add(TelephoneDirectoryPeer.DESIGNATION, designation);
        	                        if(offdirectory.equals("Public")){
                	                        String PubOffNo="1-"+officeno;
                        	                tele.add(TelephoneDirectoryPeer.OFFICE_NO, PubOffNo);
                               		} else if(offdirectory.equals("Protected")){
                                        	String ProOffNo="2-"+officeno;
	                                        tele.add(TelephoneDirectoryPeer.OFFICE_NO, ProOffNo);
        	                     	} else{
                	                        String PriOffNo="3-"+officeno;
						tele.add(TelephoneDirectoryPeer.OFFICE_NO, PriOffNo);
                                        }
                                	if(mobdirectory.equals("Public")){
                                        	String PubMobNo="1-"+mobileno;
	                                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PubMobNo);
                                        } else if(mobdirectory.equals("Protected")){
        	                                String ProMobNo="2-"+mobileno;
                	                        tele.add(TelephoneDirectoryPeer.MOBILE_NO, ProMobNo);
                                        } else{
                        	                String PriMobNo="3-"+mobileno;
                                	        tele.add(TelephoneDirectoryPeer.MOBILE_NO, PriMobNo);
                                        }
                                        if(homedirectory.equals("Public")){
                                        	String PubHomeNo="1-"+homeno;
	                                        tele.add(TelephoneDirectoryPeer.HOME_NO, PubHomeNo);
                                        } else if(homedirectory.equals("Protected")){
        	                                String ProHomeNo="2-"+homeno;
                	                        tele.add(TelephoneDirectoryPeer.HOME_NO, ProHomeNo);
                                        } else{
                        	                String PriHomeNo="3-"+homeno;
                                	        tele.add(TelephoneDirectoryPeer.HOME_NO, PriHomeNo);
                                        }
                                        if(othdirectory.equals("Public")){
                                        	String PubOthNo="1-"+otherno;
	                                        tele.add(TelephoneDirectoryPeer.OTHER_NO, PubOthNo);
					} else if(othdirectory.equals("Protected")){
        	                                String ProOthNo="2-"+otherno;
                	                        tele.add(TelephoneDirectoryPeer.OTHER_NO, ProOthNo);
                                        } else{
                                	        String PriOthNo="3-"+otherno;
                        	                tele.add(TelephoneDirectoryPeer.OTHER_NO, PriOthNo);
                                        }
				}catch(Exception e){ ErrorDumpUtil.ErrorLog("============= "+e.getMessage());}	
                                	if(li.size()==0){
                                                TelephoneDirectoryPeer.doInsert(tele);
					}
                                        else{
                                		tele.addGroupByColumn(TelephoneDirectoryPeer.USER_ID);
                                                TelephoneDirectoryPeer.doUpdate(tele);
					}
				
		// ---------------------------------------------------------------------
		/**
		 * Replacing the variable value from Property file
		 * Update the first,last name configuration parameter values for Admin
		 * @see AdminProperties in utils
		 */
		String path="";	
		path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
		StringUtil S = new StringUtil();
		String prof_update=null;
		if (S.checkString(AFName)==-1 && S.checkString(ALName)==-1){
			user.setFirstName(AFName);
			user.setLastName(ALName);
			user.setEmail(eMail);
			TurbineSecurity.saveUser(user);
			// for delete the file  and set the value for admin configuration
		 	(new File(path)).delete();
			AdminProperties.setValue(path,AdminConf,"brihaspati.admin.listconfiguration.value");
			AdminProperties.setValue(path,AdminCrsExp,"brihaspati.admin.courseExpiry");
			AdminProperties.setValue(path,mailServ,"brihaspati.mail.server");
			AdminProperties.setValue(path,mailServPort,"brihaspati.mail.smtp.port");
			AdminProperties.setValue(path,mailFrom,"brihaspati.mail.smtp.from");
			AdminProperties.setValue(path,muName,"brihaspati.mail.username");
			AdminProperties.setValue(path,mPass,"brihaspati.mail.password");
			AdminProperties.setValue(path,eMail,"brihaspati.mail.email");
			AdminProperties.setValue(path,domainNM,"brihaspati.mail.local.domain.name");
			AdminProperties.setValue(path,iquota,"brihaspati.user.iquota.value");
			AdminProperties.setValue(path,aquota,"brihaspati.admin.quota.value");
			AdminProperties.setValue(path,uquota,"brihaspati.user.quota.value");
			AdminProperties.setValue(path,hdir,"brihaspati.home.dir.value");
			AdminProperties.setValue(path,AdminFaqExp,"brihaspati.admin.FaqExpiry");
			AdminProperties.setValue(path,fileupldsze,"services.UploadService.size.max");
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
