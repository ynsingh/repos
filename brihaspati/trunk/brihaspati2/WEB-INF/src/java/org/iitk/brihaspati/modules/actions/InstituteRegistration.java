package org.iitk.brihaspati.modules.actions;

/**
 * @(#)InstituteAdminRegistration.java
 *
 *  Copyright (c) 2009,2010,2012 ETRG,IIT Kanpur.
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
import java.io.File;
import java.util.Properties;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.util.Vector;
import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

//Local classes
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.modules.utils.PasswordUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:palseema@rediffmail.com">Seema Pal </a>23April2012
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>23April2012
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author: <a href="mailto:shaistashekh@hotmail.com">Shaista </a>
 * @modified date: 22-11-2010, 16-06-2011
 * @author: <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @modified date: 22-11-2010, 08-08-2012(Shaista)
 * @author modified date 09-08-2012, 25-09-2012<a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modified date 02-11-2012, 06-11-2012 (Priyanka)
 */
/**
* class for registration of a new institute as well institute admin information
* in the brihaspati system
* new institute will be registered by checking domain name of institute in the 
* system, if domain name exist in the system registration unsuccessfull else 
* registration process successfull in the system and email will go to system admin 
* for approval or rejection.
*/

public class InstituteRegistration extends VelocitySecureAction
{
	private Log log = LogFactory.getLog(this.getClass());
	private String institutename = "", instituteaddress = "", institutecity = "" ,institutepincode = "", institutestate = "", institutelandline = "", institutedomain = "", institutetype = "", instituteaffiliation = "", institutewebsite = "", instituteadminfname = "", instituteadminlname = "", instituteadminemail = "", instituteadmindesignation = "", instituteadminusername = "", instituteadminpassword = "", instituteregisterdate = "" ;
	
	 Properties pr ;
         String fileName=new String();
         String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", confirmationMail="";
         String Mailmsg=new String();
         String message=new String();
	 String mode="", filepath, reg_msg, key, str2;
	 InstituteFileEntry InstfileEntry=new InstituteFileEntry();
	 Vector v=new Vector();

	/** boolean return true because anybody can make request for registration*/

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
                else
                {
                        rundata.setMessage("Action not found");
                }
        }

	/**method for registeration of a new Institute as well as institute admin.
	 *@param rundata (RunData)	   		
	 *@param context (Context)	   		
	 */
	
	public void InstituteRegister(RunData rundata, Context context) 
	{	

		try{
			 ParameterParser parameterparser = rundata.getParameters();
                         mode=rundata.getParameters().getString("mode");
			 String serverName=rundata.getServerName();
		         int srvrport=rundata.getServerPort();
        		 String serverPort=Integer.toString(srvrport);
			 MultilingualUtil mu = new MultilingualUtil();
        		 String lang="";
			 lang=parameterparser.getString("lang","english");
                         String Lang=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
			
		// Following check added by Priyanka
			if(mode == "cnfrm_i" || (mode.equalsIgnoreCase("cnfrm_i")))
			{
				String flag1;
				
                                StringUtil str=new StringUtil();
				pr = new Properties();
                                /**get all the values of form filled by user
                                  *for registration of a new institute in the system.
                                  */
			//	ParameterParser parameterparser = rundata.getParameters();
                                institutename = parameterparser.getString("INAME");
                                instituteaddress = parameterparser.getString("IADDRESS");
                                institutecity = parameterparser.getString("ICITY");
				//pincode must be 6 digit
				institutepincode = parameterparser.getString("IPINCODE");

                                institutestate = parameterparser.getString("ISTATE");
                                String ccode = parameterparser.getString("ccode");
                                String rcode = parameterparser.getString("rcode");
                                String phnum = parameterparser.getString("phnumber");

				/**check for Indian institute */
				
                                if(ccode.equals("91"))
                                institutelandline=rcode+phnum;
                                else
                                institutelandline=ccode+rcode+phnum;
                                institutedomain = parameterparser.getString("IDOMAIN");
                                institutetype = parameterparser.getString("ITYPE");
                                instituteaffiliation = parameterparser.getString("IAFFILIATION");
                                institutewebsite = parameterparser.getString("IWEBSITE");

                               instituteadminfname = parameterparser.getString("IADMINFNAME");
                               instituteadminlname = parameterparser.getString("IADMINLNAME");
                               instituteadminemail = parameterparser.getString("IADMINEMAIL");
                                instituteadmindesignation = parameterparser.getString("IADMINDESIGNATION");
                                String adminusername = instituteadminemail;
                                String instpassword = parameterparser.getString("IADMINPASSWORD");
				
				/**
                         	 * @see ExpiryUtil in Utils
                         	 */
                        	String curdate=ExpiryUtil.getCurrentDate("-");

                        	/** getting path for creating InstituteRegistration directory*/

                        	filepath=TurbineServlet.getRealPath("/InstituteRegistration");
                        	File f=new File(filepath);
                        	if(!f.exists())
                        	f.mkdirs();
                        	/* Check for the illegal symbols*/
				if(str.checkString(adminusername)==-1 && str.checkString(instituteadminfname)==-1 && str.checkString(instituteadminlname)==-1)
				{
					filepath=filepath+"/InstituteRegistrationList.xml";
                                	/** check for existence of Institute
                                 	* @see XMLWriter_InstituteRegistration (method-DomainExist)in Utils
                                 	*/
                                	boolean flag=XMLWriter_InstituteRegistration.DomainExist(filepath,institutedomain);
                                	//ErrorDumpUtil.ErrorLog("flag====InstituteRegistrationaction="+flag+"\nfilepath====="+filepath+"\ninstitutedomain==="+institutedomain);
					/**if institute is new write the information in xml file for approval or rejection.*/
                                	if(flag==false)
                                	{

						/**
 						 * Check whether user is already  
 						 * registered in Brihaspati
 						 */
						int cmpid=-1;
			                        int uid=UserUtil.getUID(instituteadminemail);
                        			boolean Result= uid == cmpid;
                        			//ErrorDumpUtil.ErrorLog("GETTING USER ID....." +uid +" "+ Result);
						
					   if(Result)
					   {
						flag1="0";
						
						//following lines added by Priyanka	
						/**
                                                 * Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
                                                 * instAdmin/ brihaspatiAdmin defined in brihasapti.properties
                                                 */	
					
						if(serverPort.equals("8080"))
                                		      info_Opt = "newUser";
                		                else
		                                     info_Opt = "newUserhttps";
						// CREATE MD5 HASH....................
						
						String randm_n = PasswordUtil.randmPass();
                                		String str1=randm_n+instituteadminemail;
                                		String a_key=EncryptionUtil.createDigest("SHA1",str1);
                                		fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                                		pr =MailNotification.uploadingPropertiesFile(fileName);
                                		msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
                                		msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", instituteadminemail);
                                		msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                                		msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
						reg_msg=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgBrihAdmin");
						msgRegard=msgRegard+reg_msg;
                                		subject=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".c_subject");
                                		messageFormate = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".c_message"); // get a_key
                                		confirmationMail=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".confirmationMail");
                                		confirmationMail=MailNotification.getMessage(confirmationMail, instituteadminemail, a_key, mode, lang);
                                		confirmationMail=MailNotification.replaceServerPort(confirmationMail, serverName, serverPort);
						confirmationMail=MailNotification.getMessage(confirmationMail, institutedomain, "");
                                		messageFormate = messageFormate+confirmationMail;
                                		Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", instituteadminemail, subject, "", Lang, "", "");
				
						//...............
						String writeinxml=XMLWriter_InstituteRegistration.InstituteRegistrationListXml(filepath,institutename,instituteaddress,institutecity,institutepincode,institutestate,institutelandline,institutedomain,institutetype,instituteaffiliation,institutewebsite,curdate,"2020-12-01",instituteadminfname,instituteadminlname,instituteadminemail,instituteadmindesignation,adminusername,instpassword,a_key,flag1);
						rundata.setMessage(mu.ConvertedString("cnfrm_mail",Lang));
					   }//if
					   else
					   {
						flag1="1";
						String writeinxml=XMLWriter_InstituteRegistration.InstituteRegistrationListXml(filepath,institutename,instituteaddress,institutecity,institutepincode,institutestate,institutelandline,institutedomain,institutetype,instituteaffiliation,institutewebsite,curdate,"2020-12-01",instituteadminfname,instituteadminlname,instituteadminemail,instituteadmindesignation,adminusername,instpassword,"",flag1);

						 // Maintain Log
                                                java.util.Date date= new java.util.Date();
                                                log.info("Institute Admin Registration with name --> "+institutename+" | IP Address --> "+rundata.getRemoteAddr());

						/**Get email of Sysadmin for sending email
	                                         *regarding to the registration of a new Institute.
                                                 */
						String Mail_msg=sendMail(instituteadminemail, instituteadminfname, instituteadminlname, institutename, Lang);
						if(!Mail_msg.equals("unsuccessfull"))
							rundata.setMessage(mu.ConvertedString("brih_Institue", Lang)+" "+mu.ConvertedString("brih_registration", Lang)+" "+mu.ConvertedString("brih_successful", Lang)+" "+mu.ConvertedString("brih_waitForApprove", Lang));
					   }//else

					}//flag
					else
                                	{
						if(Lang.endsWith("_hi.properties"))
                                                	rundata.setMessage(mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_alreadyRegister", Lang)+", "+mu.ConvertedString("brih_inSame", Lang)+" "+mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+""+mu.ConvertedString("brih_toRegAsAn", Lang)+" "+mu.ConvertedString("brih_pleaseContact", Lang)+" "+mu.ConvertedString("brih_system", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+" /  "+mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+" "+mu.ConvertedString("brih_from", Lang));
	                                        else
        	                                        rundata.setMessage(mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_alreadyRegister", Lang)+", "+mu.ConvertedString("brih_pleaseContact", Lang)+" "+mu.ConvertedString("brih_system", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+" /  "+mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+" "+mu.ConvertedString("brih_toRegAsAn", Lang)+" "+mu.ConvertedString("brih_institute", Lang)+" "+mu.ConvertedString("brih_admin", Lang)+" "+mu.ConvertedString("brih_inSame", Lang)+" "+mu.ConvertedString("brih_institute", Lang));
  
    	                          	}
				}//strif
				else{
					rundata.setMessage(mu.ConvertedString("brih_specialSymbol&char", Lang)+" "+mu.ConvertedString("Notallow", Lang)	);
					//"Special symbol and character is not allowed"
				}
			
			}//if
		
		//Following check added by Priyanka
			if(mode == "act" || (mode.equalsIgnoreCase("act")))
			{
					boolean c=false;
					String e_mail=rundata.getParameters().getString("email");
					String d_name=rundata.getParameters().getString("domain");
					filepath=TurbineServlet.getRealPath("/InstituteRegistration/InstituteRegistrationList.xml");
					v = XMLWriter_InstituteRegistration.ReadInstDomainDeatils(filepath, d_name);
					
					/**
 					 * Getting the institute details
 					 * on the basis of domain and email id
 					 * fetched from the mail
 					 */				
					if(v.size()>0)
					{
						InstfileEntry=(InstituteFileEntry)v.get(0);
						instituteadminemail = InstfileEntry.getInstituteEmail();
						institutename = InstfileEntry.getInstituteName();
				        	instituteadminfname = InstfileEntry.getInstituteFName();
						instituteadminlname = InstfileEntry.getInstituteLName();
					}						
					
					/**
 					 * When entry corresponding to the
 					 * email and domain exist in the xml.
					 */
					
					if(!institutename.equals("") && !instituteadminfname.equals("") && !instituteadminlname.equals("") && instituteadminemail.equals(e_mail))
					{
						/**Get email of Sysadmin for sending email
                	                       	 *regarding to the registration of a new Institute.
                                         	 */
						String Mail_msg = sendMail(instituteadminemail, instituteadminfname, instituteadminlname, institutename, Lang);
						if(!Mail_msg.equals("unsuccessfull"))
                                        		rundata.setMessage(mu.ConvertedString("brih_Institue", Lang)+" "+mu.ConvertedString("brih_registration", Lang)+" "+mu.ConvertedString("brih_successful", Lang)+" "+mu.ConvertedString("brih_waitForApprove", Lang));
                                        	//"Institute Registeration Successfull"
					}
					else
					{	
						/**
 					 	 * When entry corresponding to the
 					 	 * email and domain doesn't exist in the xml.
 					 	 */
						try{
                	                                str2=mu.ConvertedString("oopsCnfrm_msg",Lang);
                        	                        rundata.setMessage(str2);
							rundata.getResponse().sendRedirect(rundata.getServerScheme()+"://"+rundata.getServerName()+":"+rundata.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str2);
                                                }
        	                                catch (Exception ex){
                                        	        String msg1 = "ERROR IN EMAIL VERIFICATION";
                                                	ErrorDumpUtil.ErrorLog("User's email could not be verified "+ex);
                                                	throw new RuntimeException(msg1,ex);
                                        	}
					}
			}// if mode act
		}
		catch (Exception e)
		{
			rundata.setMessage("problem in registration method" +e);
		}
	}//method

	/**
	 * Sending mail to institute admin
	 */
	String sendMail(String instituteadminemail, String instituteadminfname, String instituteadminlname, String institutename, String Lang)
	{
		String Mail_msg="unsuccessfull";
		try{
			String server_name=TurbineServlet.getServerName();
                	String srvrPort=TurbineServlet.getServerPort();
                	String subject="",  info_Opt="";
                	Criteria criteria=new Criteria();
                	criteria.add(TurbineUserPeer.USER_ID,1);
                	List adminemail=TurbineUserPeer.doSelect(criteria);
                	String EMAIL=((TurbineUser)adminemail.get(0)).getEmail();
                	String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                	pr=MailNotification.uploadingPropertiesFile(fileName);
                	if(srvrPort.equals("8080")){
	                	subject="newInstituteRegister";
                        	info_Opt = "newUser";
                	}
                	else {
                        	subject="newInstituteRegisterhttps";
                        	info_Opt = "newUserhttps";
                	}
                	String subj = MailNotification.subjectFormate(subject, "", pr);
                	String messageFormate = MailNotification.getMessage(subject, "("+instituteadminemail+")", pr);
                	messageFormate=MailNotification.getMessage_new(messageFormate, instituteadminfname+" "+instituteadminlname ,"" ,institutename,"");
                	String msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                	msgRegard = MailNotification.replaceServerPort(msgRegard, server_name, srvrPort);
                	Mail_msg= MailNotificationThread.getController().set_Message(messageFormate, "", msgRegard, "", EMAIL, subj, "", Lang, "","");//last parameter added by Priyanka
		}	
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error while sending mail to institute admin");
		}
		return Mail_msg;
	}
}//class


