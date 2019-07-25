package org.iitk.brihaspati.modules.actions;

/**
 * @(#)ResendActivation.java  
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
import java.util.Properties;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.om.UserPref;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.TorqueException;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.XMLWriter_EmailUpdation;
import java.io.File;
import java.util.Vector;
import java.util.Iterator;
import org.iitk.brihaspati.modules.utils.StringUtil;


/**
 * Action class to resend activation for direct registration
 * and confirmation mail for institute registration and online regitration.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modification date: 09-08-2012, 01-10-2012, 15-10-2012, 06-11-2012(Priyanka)
 * @modification date: 15-01-2013 (Priyanka)
 */

public class ResendActivation extends VelocityAction{
        private Log log = LogFactory.getLog(this.getClass());
	//String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", activationLink="";
        String msgRegard="", msgDear="", messageFormate="", subject="", activationLink="";
	Properties pr ;
	String fileName=new String();

	
	public void doPerform( RunData data, Context context )
        {

                System.gc();
                Criteria crit = null;
		Criteria criteria = null;
       		int id;
	        String e_mail=data.getParameters().getString("email");
		String lang=data.getParameters().getString("lang","english");
		//Properties pr ;
		//String fileName=new String();
		//String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", activationLink="";	
	//	String serverName=data.getServerName();
         //       int srvrPort=data.getServerPort();
           //     String serverPort=Integer.toString(srvrPort);
	//	String server_scheme = TurbineServlet.getServerScheme();
		String Mail_msg=new String();
         	String message=new String();
		String a_key = null, domain = null;
		String path, email, str, flag;
		boolean sent = false;
		String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);	
		MultilingualUtil mu = new MultilingualUtil();		
		String hash="", emai_l="", u_name="";	
		int user = 0;

		if(StringUtil.checkString(e_mail) != -1)
                {
                     data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                     return;
                }
		if(e_mail.indexOf("@") == -1)
                {
                     data.addMessage(MultilingualUtil.ConvertedString("malformed_email",LangFile));
                     return;
                }


		try{	
		//Check, if email exists in InstituteRegistrationList.xml
		path=TurbineServlet.getRealPath("/InstituteRegistration/InstituteRegistrationList.xml");
                File f=new File(path);
		if(f.exists()){
			a_key = XMLWriter_InstituteRegistration.getActivationKey(path,e_mail);
			if(!a_key.equals("Not exist"))			
			{
				domain = XMLWriter_InstituteRegistration.getDomain(path,e_mail,a_key);
				//if(!a_key.equals(null) && !domain.equals(null))
				if(!domain.equals(null))
				{
					if(a_key.equals("Email confirmed"))
					{
						try{
							sent=true;
		                        		str=MultilingualUtil.ConvertedString("ac_cnfrm",LangFile);
                		        		data.setMessage(str);
						}
						catch (Exception ex){
                        				String msg3 = "Error in activation      ";
                       					ErrorDumpUtil.ErrorLog("Activation mail sending  "+ex);
                		        		throw new RuntimeException(msg3);
		                		}
					}			
					else{
						sent = sendMail(e_mail,a_key,"cnfrm_i",LangFile,data,lang,domain);
					}
				}
			}
		}
		if(!sent){
			//Check, if email exists in courses.xml
			path=data.getServletContext().getRealPath("/OnlineUsers/courses.xml");
			File f1 =new File(path);
			if(f1.exists()){
				TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path);
				Vector userList = topicmetadata.getOnlineCourseDetails();
				 if(userList != null){
					for(int i=0; i <userList.size(); i++)
                                 	{
                                        	email = ((CourseUserDetail)userList.get(i)).getEmail();
                                        	if(email.equals(e_mail))
                                        	{
							flag = ((CourseUserDetail)userList.get(i)).getFlag();
							if(flag.equals("0"))
							{
								a_key = ((CourseUserDetail)userList.get(i)).getActivation();
								if(a_key!=null){
				                                	sent = sendMail(e_mail,a_key,"cnfrm_c",LangFile,data, lang,"");
                        					}//if 5
							}//if 4
							else
							{
								try{
									sent=true;
			                                                str=MultilingualUtil.ConvertedString("ac_cnfrm",LangFile);
                        			                        data.setMessage(str);
                                        			}
                                        			catch (Exception ex){
                                                			String msg3 = "Error in activation      ";
                                                			ErrorDumpUtil.ErrorLog("Activation mail sending  "+ex);
                                                			throw new RuntimeException(msg3);
                                        			}
								sent=true;
							}
						}//if 3 
					}//for
				 }//if 2	
			}//if 1
		}//if

		if(!sent){
                        //Check, if email exists in OnlineUser.xml
                        path=data.getServletContext().getRealPath("/OnlineUsers/OnlineUser.xml");
                        File file =new File(path);
                        if(file.exists()){
                                TopicMetaDataXmlReader topicmetadata=new TopicMetaDataXmlReader(path);
                                Vector userList1 = topicmetadata.getOnlineUserDetails();
                                 if(userList1 != null){
                                        for(int i=0; i <userList1.size(); i++)
                                        {
                                                email = ((CourseUserDetail)userList1.get(i)).getEmail();
                                                if(email.equals(e_mail))
                                                {
							flag = ((CourseUserDetail)userList1.get(i)).getFlag();
                                                        if(flag.equals("0"))
                                                        {
								a_key = ((CourseUserDetail)userList1.get(i)).getActivation();
                                                        	if(a_key!=null){
                                                                	sent = sendMail(e_mail,a_key,"cnfrm_u",LangFile,data, lang,"");
                                                        	}//if 5
							}//if 4
							else
							{
								try{
									sent=true;
                                                			str=MultilingualUtil.ConvertedString("ac_cnfrm",LangFile);
                                                			data.setMessage(str);
                                        			}
                                        			catch (Exception ex){
                                                			String msg3 = "Error in activation      ";
                                                			ErrorDumpUtil.ErrorLog("Activation mail sending  "+ex);
                                                			throw new RuntimeException(msg3);
                                        			}
								sent=true;
							}
                                                }//if 3
                                        }//for
                                 }//if 2
                        }//if 1 
                }//if 

		if(!sent){		
			//check if mail exists in EmailUpdation.xml
			path=data.getServletContext().getRealPath("/EmailUpdation/EmailUpdation.xml");
                        File file =new File(path);
			//String hash, emai_l, u_name;
                        if(file.exists()){
				boolean flag1=XMLWriter_EmailUpdation.mailExist(path,e_mail);
				
                                //if email exists in xml file
                                //read details from file
                                if(flag1)
				{
                                	Vector v = XMLWriter_EmailUpdation.readProfileDetails(path,e_mail);
                                	if(v.capacity()>0)
                                	{
                                		u_name =(String) v.get(0);
                                        	emai_l =(String) v.get(1);
                                        	hash =(String) v.get(2);
                                	}
                                	if(emai_l.equals(e_mail))
                                	{
						if(hash.equals("updated"))
						{
                                        		try{
                                        			str=MultilingualUtil.ConvertedString("already_verify",LangFile);
                                                		data.setMessage(str);
                                        		}
                                        		catch (Exception ex){
                                                		String msg3 = "Error in confirmation      ";
                                                		ErrorDumpUtil.ErrorLog("Error while confirmation mail resending"+ex);
                                                		throw new RuntimeException(msg3);
                                        		}
							sent=true;
						}
						else
						{
							//confirmation mail sent successfully
							sent = sendMail(e_mail,hash,"cnfrm_mail",LangFile,data,lang,"");
						}
                                	}	
				}//if
				else
				{
					//email updated in database
					crit = new Criteria();
                        		List list = TurbineUserPeer.doSelect(crit);
                        		for (Iterator i = list.iterator();i.hasNext() ;)
                        		{
                              			TurbineUser tuser = (TurbineUser) i.next();
                              			email = tuser.getEmail();
                              			id = tuser.getUserId();
						if(e_mail.equals(email))
						{
							criteria = new Criteria();
		                                        criteria.add(UserPrefPeer.USER_ID,id);
                		                        List list1 = UserPrefPeer.doSelect(criteria);
							a_key =((UserPref)list1.get(0)).getActivation();
							if(a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
                                   				user++;
						}

                        		}//for 
					if(user!=0)
		                        {
						sent = true;
                		                try{
						        //str=mu.ConvertedString("brih_email",LangFile)+" "+mu.ConvertedString("update_msg",LangFile);
                                        		str=MultilingualUtil.ConvertedString("ac_act",LangFile);
	                                       		data.setMessage(str);
                                        		data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                		}
                                		catch (Exception ex){
                                        		String msg1 = "ERROR IN EMAIL VERIFICATION";
                                        		ErrorDumpUtil.ErrorLog("User's email could not be updated "+ex);
                                        		throw new RuntimeException(msg1,ex);
                                		}
                        		}
				}//else
			}//if
		}//if
		}//try
		catch(Exception ex){
                     String msg5 = "Error in activation   ";
                     ErrorDumpUtil.ErrorLog(" Error sending activation mail inside 6th catch "+ex);
                     throw new RuntimeException(msg5);
                }//catch 1


		if(!sent){
			// Get user id
			int cmpid=-1;
                	int uid=UserUtil.getUID(e_mail);
                	boolean Result= uid == cmpid;
		
                	if(Result){
	                	  try{
					str=MultilingualUtil.ConvertedString("usr_doesntExist",LangFile); 
                             		data.setMessage(str);
                          	    }
                          	    catch (Exception ex){
					String msg = "Error in activation	";
                               		ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS inside 1st catch "+ex);
				 	throw new RuntimeException(msg,ex);
                          	    }
			}//if 1
			else{

                        	try{
			 	// select activation key corresponding to user id in a_key
			  		crit = new Criteria();
                          		crit.add(UserPrefPeer.USER_ID,uid);
                          		List list = UserPrefPeer.doSelect(crit);
                          		a_key =((UserPref)list.get(0)).getActivation();
			  		if (a_key == null || a_key.equalsIgnoreCase("NULL"))
			  		{
						
						fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                                		pr =MailNotification.uploadingPropertiesFile(fileName);
                                		msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
						msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", e_mail);
						msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
						msgRegard = MailNotification.replaceServerPort(msgRegard);
						subject=pr.getProperty("brihaspati.Mailnotification.newUser.a_subject");
      	                         		activationLink=pr.getProperty("brihaspati.Mailnotification.newUser.activationLink");
						activationLink=MailNotification.getMessage(activationLink, e_mail, a_key,"", lang);
                                		activationLink=MailNotification.replaceServerPort(activationLink);
						messageFormate = messageFormate+activationLink;
						Mail_msg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", e_mail, subject, "", "");
						
						try{
							str=MultilingualUtil.ConvertedString("act_mail",LangFile);
                             		 		data.setMessage(str);
                             		 	 }
                                		catch (Exception ex){
							String msg3 = "Error in activation	";
							ErrorDumpUtil.ErrorLog("Activation mail sending	inside 4th catch "+ex);
                               				throw new RuntimeException(msg3);
				 		}


			  		}
					else if(a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
			   		{
							try{
								str=MultilingualUtil.ConvertedString("ac_activate",LangFile);
	                              				data.setMessage(str);
                                		 	}
                                		 	catch (Exception ex){
								String msg2 = "Error in activation	";
                                        			ErrorDumpUtil.ErrorLog("User account already exists inside 3rd catch"+ex);
					 			throw new RuntimeException(msg2,ex);
                                		 	}
			   		}//if 3
					else if(!(a_key.equalsIgnoreCase("ACTIVATE")) && !(a_key.equalsIgnoreCase("NULL")))
			     		{
						//sending activation link in the mail
				
						/**
 						 * Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
                                		 * instAdmin/ brihaspatiAdmin defined in brihasapti.properties
                                		 */
						/*if(serverPort.equals("8080"))
                                      			info_Opt = "newUser";
                                		else
                                      			info_Opt = "newUserhttps";
						*/
						fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                                		pr =MailNotification.uploadingPropertiesFile(fileName);
						//msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
                                		msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
//                                		msgDear = MailNotification.getMessage_new(msgDear, FName, LName, "", UName);
						msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", e_mail);
						//msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                                		msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
						//msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
						msgRegard = MailNotification.replaceServerPort(msgRegard);
						//subject=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".a_subject");
      						subject=pr.getProperty("brihaspati.Mailnotification.newUser.a_subject");
      //                          		messageFormate = MailNotification.getMessage(userRole, cAlias, dept, UName, "", serverName, serverPort, pr);
        					//messageFormate = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".a_message"); // get a_key
	                        		messageFormate = pr.getProperty("brihaspati.Mailnotification.newUser.a_message");
						//activationLink=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".activationLink");
                                		activationLink=pr.getProperty("brihaspati.Mailnotification.newUser.activationLink");
						activationLink=MailNotification.getMessage(activationLink, e_mail, a_key,"", lang);
                                		//activationLink=MailNotification.replaceServerPort(activationLink, serverName, serverPort);
                                		activationLink=MailNotification.replaceServerPort(activationLink);
						messageFormate = messageFormate+activationLink;
						//Mail_msg = message + MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_existing, subject, "", file);
						Mail_msg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", e_mail, subject, "", "");
						//Mail_msg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", e_mail, subject, "", "", "","");//last parameter added by Priyanka
						//data.setMessage(Mail_msg);
				
						try{
							str=MultilingualUtil.ConvertedString("act_mail",LangFile);
                             		 		data.setMessage(str);
                             		 	 }
                                		catch (Exception ex){
							String msg3 = "Error in activation	";
							ErrorDumpUtil.ErrorLog("Activation mail sending	inside 4th catch "+ex);
                               				throw new RuntimeException(msg3);
				 		}
	
			     		}//if 4
			      		else
					{
						//ErrorDumpUtil.ErrorLog("i m here 3");
						try{
							str=MultilingualUtil.ConvertedString("oops_msg",LangFile);
                                         		data.setMessage(str);
                                         	}
        	                        	catch (Exception ex){
							String msg4 = "Error in activation       ";
	                                        	ErrorDumpUtil.ErrorLog(" Error sending activation mail inside 5th catch "+ex);
                        	     			throw new RuntimeException(msg4);

					   	}
					}//else 3
				
			  	}//try 1
			 	catch(Exception ex){
                                	String msg5 = "Error in activation   ";
					ErrorDumpUtil.ErrorLog(" Error sending activation mail inside 6th catch "+ex);
				 	throw new RuntimeException(msg5);
				}//catch 1

		  	}//else 2
		}//if
	}//method

/**
 * Method to send confirmation mail
 * in case of Institute Regitration and Online Registration
 * @param email  User's email id
 * @param a_key activation key corresponding to email
 * @param u_mode user mode, can have values "cnfrm_i", "cnfrm_c", "cnfrm_u"
 * @param LangFile langFile for language
 * @param data RunData
 * @param lang language to be stored in xml file
 * @param domain domain of institute
 * @return boolean
 */
private boolean sendMail(String email, String a_key, String u_mode, String LangFile, RunData data, String lang, String domain){
	String str, Mailmsg, confirmationMail;
	//String serverName=data.getServerName();
        //int srvrPort=data.getServerPort();
        //String serverPort=Integer.toString(srvrPort);
	//String serverScheme= TurbineServlet.getServerScheme();
	try{
	 /**
          * Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
          * instAdmin/ brihaspatiAdmin defined in brihasapti.properties
          */
		/*if(serverPort.equals("8080"))
                        info_Opt = "newUser";
                else
                        info_Opt = "newUserhttps";
		*/
		// set key in link and pass in mail
		fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                pr =MailNotification.uploadingPropertiesFile(fileName);
                //msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
                msgDear = pr.getProperty("brihaspati.Mailnotification.newUser.msgDear");
		msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", email);
                //msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
		//msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
                msgRegard = MailNotification.replaceServerPort(msgRegard);
		//subject=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".cnfrm_subject");
                subject=pr.getProperty("brihaspati.Mailnotification.newUser.cnfrm_subject");
		//messageFormate = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".cnfrm_message"); // get a_key
                messageFormate = pr.getProperty("brihaspati.Mailnotification.newUser.cnfrm_message");
		//confirmationMail=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".confirmationMail");
		confirmationMail=pr.getProperty("brihaspati.Mailnotification.newUser.confirmationMail");
		if(u_mode.equals("cnfrm_mail"))
			confirmationMail=MailNotification.getMessage(confirmationMail, email, a_key, u_mode, LangFile);
		else
                	confirmationMail=MailNotification.getMessage(confirmationMail, email, a_key, u_mode, lang);
                //confirmationMail=MailNotification.replaceServerPort(confirmationMail, serverName, serverPort);
		confirmationMail=MailNotification.replaceServerPort(confirmationMail);
		if(u_mode.equals("cnfrm_i"))
			confirmationMail=MailNotification.getMessage(confirmationMail, domain, "");
                messageFormate = messageFormate+confirmationMail;
		//Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", email, subject, "", "", "","");
		Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", email, subject, "", "");
		if(!Mailmsg.equals(""))
		{
			//mail sent successfully
			try{
				str=MultilingualUtil.ConvertedString("cnfrm_mail",LangFile);
				data.setMessage(str);
			}
			catch (Exception ex){
                        	String msg3 = "Error in activation      ";
                        	ErrorDumpUtil.ErrorLog("Activation mail sending inside 4th catch "+ex);
                        	throw new RuntimeException(msg3);
                	}
		}//if
		else
		{
			//mail could not be sent
			String str1=MultilingualUtil.ConvertedString("oops_msg",LangFile);
	                 try{   
        	                  data.setMessage(str1);
                	 }
                 	catch (Exception ex){
                        	  ErrorDumpUtil.ErrorLog("Error while resending confirmation mail"+ex);
                  	}
		}
		//return true;
	}//try
	catch(Exception e){
		 String message = "Error occurred while resending confirmation mail!";
                 log.error(message, e);
		 String str1=MultilingualUtil.ConvertedString("oops_msg",LangFile);
                 try{
                          data.setMessage(str1);
                 }
                 catch (Exception ex){
                	  ErrorDumpUtil.ErrorLog("Error while resending confirmation mail"+ex);
		  }
		//return false;
	}
	return true;
}//method

}//class

