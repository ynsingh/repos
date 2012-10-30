package org.iitk.brihaspati.modules.actions;

/**
 * @(#) EmailVerification.java  
 *  
 *  Copyright (c) 2012 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.PasswordUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_EmailUpdation;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.modules.utils.UserUtil;
import java.util.Iterator;
import java.util.Vector;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;

/**
 * This class is responsible for veification of email
 * during profile updation.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modified date: 15-10-2012 
 */

public class EmailVerification
{

	Properties pr ;
        String fileName=new String();
        String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", confirmationMail="";
        String Mailmsg=new String();
	String serverName, serverPort;
	ParameterParser parameterparser;
	int srvrport;
	MultilingualUtil mu = new MultilingualUtil();

	EmailVerification(RunData rundata)
	{
		serverName=rundata.getServerName();
                srvrport=rundata.getServerPort();
                serverPort=Integer.toString(srvrport);
		parameterparser = rundata.getParameters();
	}	

	/**
	 * Method for getting user profile details
	 * and storing them in xml 
	 * @param email User's email id
	 * @param uname User's username
	 * @param lang LangFile for the language selected by user
	 * @param photo Has value 'true', if photo was updated
	 * 		'false', if photo was not updated.
	 * 		The parameter has been added to maintain
	 * 		the consistency of the existing system.
	 * @return String
	 */
	public String profileDetails(String email, String uname, String Lang, boolean photo)
	{
	   try
	   {
		//check if email already exists in database
		int uid=UserUtil.getUID(uname);
		Criteria crit = new Criteria();
                crit.add(TurbineUserPeer.USER_ID,uid);
                List list = TurbineUserPeer.doSelect(crit);
                String mail_exist =((TurbineUser)list.get(0)).getEmail();
		
		//If email already exists in the database
		if(mail_exist.equals(email))
		{
			return "Email_updated";
		}	
		else
		{
			//If email doesn't exist in database
			String mode="cnfrm_mail";
			java.util.Date current_date= new java.util.Date();
                	MultilingualUtil mu = new MultilingualUtil();

			/** getting path for creating EmailUpdation directory*/
			String filepath=TurbineServlet.getRealPath("/EmailUpdation");
                	File f=new File(filepath);		
			if(!f.exists())
                	f.mkdirs();
                	filepath=filepath+"/EmailUpdation.xml";
			/** check for existence of entry
                 	* @see XMLWriter_EmailUpdation (method-mailExist)in Utils
                 	*/
			boolean flag=XMLWriter_EmailUpdation.mailExist(filepath,email);
			//if updation is new write the information in xml file
                	if(flag==false)
                	{
			
				try{
					/**
                       	 		* Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
                       	 		* instAdmin/ brihaspatiAdmin defined in brihasapti.properties
                       	 		*/
				
                       			if(serverPort.equals("8080"))
                               			info_Opt = "newUser";
                       			else
                               			info_Opt = "newUserhttps";
					String randm_n = PasswordUtil.randmPass();
                       			String str1=randm_n+email;
                       			String a_key=EncryptionUtil.createDigest("MD5",str1);
                       			fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                       			pr =MailNotification.uploadingPropertiesFile(fileName);
                       			msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
                			msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", email);
                       			msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                       			msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
                       			subject=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".eu_subject");
                       			messageFormate = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".eu_message"); // get a_key
                       			confirmationMail=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".confirmationMail");
                       			confirmationMail=MailNotification.getMessage(confirmationMail, email, a_key, mode, Lang);
                       			confirmationMail=MailNotification.replaceServerPort(confirmationMail, serverName, serverPort);
                       			messageFormate = messageFormate+confirmationMail;
                       			Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", email, subject, "", "", "", "");
					String update = XMLWriter_EmailUpdation.emailVerification(filepath,uname,email,a_key,current_date,photo);
					return update;
					
				}//try
				catch(Exception e)
		        	{
                	        	ErrorDumpUtil.ErrorLog("Error in class: EmailUpdation, method: profileDetails catch1 "+e);
                		}//catch1
			}//if
		
			return "Exists";
		}//else
	   }//try
	   catch(Exception e)
	   {
		ErrorDumpUtil.ErrorLog("Error in class: EmailUpdation, method: profileDetails catch2 "+e);
	   }//catch2
		return "";
	}//method

	/**
         * Method for email verification and
         * updating email in database. 
         */

	public void profileUpdation(RunData data)
	{
		String e_mail=data.getParameters().getString("email");
                String a_key=data.getParameters().getString("key");
                String LangFile=data.getParameters().getString("lang");
                //String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
		String u_name = "";
		String email = "";
		String hash = "";
		String photo = "";		
		int uid = 0;
		Criteria crit;
		String str, msg;

		try{
			//check if email already exists in database
			crit = new Criteria();
                        List list = TurbineUserPeer.doSelect(crit);
                        for (Iterator i = list.iterator();i.hasNext() ;)
                        {
                              TurbineUser tuser = (TurbineUser) i.next();
                              email = tuser.getEmail();
                              if(e_mail.equals(email))
                                   uid++;
                        }//for           
                	//If email already exist in the database
                	if(uid!=0)
                	{
                        	try{
                                        str=mu.ConvertedString("brih_email",LangFile)+" "+mu.ConvertedString("update_msg",LangFile);
                                        data.setMessage(str);
                                        data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                }
                                catch (Exception ex){
                                        String msg1 = "ERROR IN EMAIL VERIFICATION";
                                        ErrorDumpUtil.ErrorLog("User's email could not be updated "+ex);
                                        throw new RuntimeException(msg1,ex);
                                }
                	}
                	else
                	{
				//Email doesn't exist in the database
				/** getting path for EmailUpdation xml*/
				String filePath=TurbineServlet.getRealPath("/EmailUpdation");
                		filePath=filePath+"/EmailUpdation.xml";
				Vector v = XMLWriter_EmailUpdation.readProfileDetails(filePath,e_mail);
				if(v.capacity()>0)
				{
					u_name =(String) v.get(0);
					email =(String) v.get(1);
					hash =(String) v.get(2);
					photo =(String) v.get(3); 
				}
				if(email.equals(e_mail) && a_key.equals(hash))
				{
					/**
		                  	* Get the user object from TurbineSecurity 
		                  	* for the username specified
                                  	*/
					User user = TurbineSecurity.getUser(u_name);
					user.setEmail(email);
                        		TurbineSecurity.saveUser(user);
					uid=UserUtil.getUID(u_name);
				
					//Update email in TURBINE_USER
					crit = new Criteria();
					crit.add(TurbineUserPeer.USER_ID,uid);
					crit.add(TurbineUserPeer.EMAIL,email);
                                	TurbineUserPeer.doUpdate(crit);	
			
					if(photo.equals("nexist"))
					{
						ErrorDumpUtil.ErrorLog("Updating Telephone Directory");
						//Update email in TELEPHONE_DIRECTORY
						List li=null;
                                		Criteria tele = new Criteria();
		
						//int uid=UserUtil.getUID(u_name);
                                		tele.add(TelephoneDirectoryPeer.USER_ID,uid);
						{
                                			li = TelephoneDirectoryPeer.doSelect(tele);
                                			if(li.size()>0) {
                                      				TelephoneDirectory element=(TelephoneDirectory)(li.get(0));
                                     				int id=(element.getId());
                                     				tele.add(TelephoneDirectoryPeer.ID,id);
                                			}
						}
						tele.add(TelephoneDirectoryPeer.MAIL_ID,email);
						if(li.size()==0)
	                                        {
        	                                        TelephoneDirectoryPeer.doInsert(tele);
                	                                ErrorDumpUtil.ErrorLog("i m here 2");
                        	                }
                                	        else
                                        	{
                                                	TelephoneDirectoryPeer.doUpdate(tele);
                                                 	ErrorDumpUtil.ErrorLog("i m here 20");
                                        	}

					}

					// Removing the entry from the xml
					// after email updated successfully in database
					String res = XMLWriter_EmailUpdation.removeElement(filePath, email);
					if(res.equals("UnSuccessfull"))
					{
						ErrorDumpUtil.ErrorLog("ERROR IN class:EmailVerification method:profileUpdation(), EMAIL:"+email+" UPDATED IN DATABASE BUT ENTRY NOT REMOVED FROM XML.");
						XMLWriter_EmailUpdation.setHash(filePath, email);		
					}
					try{
                                       	 	str=mu.ConvertedString("brih_email",LangFile)+" "+mu.ConvertedString("update_msg",LangFile);
                                       	 	data.setMessage(str);
                                       	 	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                	}
                               	 	catch (Exception ex){
                                       		 msg = "ERROR IN EMAIL VERIFICATION";
                                       	 	 ErrorDumpUtil.ErrorLog("User's email not updated "+ex);
                                       	 	 throw new RuntimeException(msg,ex);
                                	} 
				
				}//if
				else
				{
					/**
					 * In this case email is being retrieved 
					 * from the mail, but entry for "this" email 
					 * does not exist in the xml
					 */
					try{
                                		str=MultilingualUtil.ConvertedString("oopsCnfrm_msg",LangFile);
                                		data.setMessage(str);
                                		data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                         		}
                         		catch (Exception ex){
                                		String msg1 = "ERROR IN EMAIL VERIFICATION";
                                		ErrorDumpUtil.ErrorLog("User's email could not be verified "+ex);
                                		throw new RuntimeException(msg1,ex);
                         		}
				}//else2
			}//else1	
		}//try
		catch(Exception e)
		{
			try{
				str=MultilingualUtil.ConvertedString("oopsCnfrm_msg",LangFile);
                               	data.setMessage(str);
                               	data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                       	}
                       	catch (Exception ex){
                               	String msg1 = "ERROR IN EMAIL VERIFICATION";
                               	ErrorDumpUtil.ErrorLog("User's email could not be verified "+ex);
                               	throw new RuntimeException(msg1,ex);
                       	}
		}

	}//method

}//class
