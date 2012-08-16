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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iitk.brihaspati.om.UserPrefPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.TorqueException;

/**
 * Action class to resend activation mail
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 */

public class ResendActivation extends VelocityAction{
        private Log log = LogFactory.getLog(this.getClass());
	
	public void doPerform( RunData data, Context context )
        {

                System.gc();
                Criteria crit = null;
                String e_mail=data.getParameters().getString("email");
		 Properties pr ;
		 String fileName=new String();
		String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", activationLink="";	
		String serverName=data.getServerName();
                int srvrPort=data.getServerPort();
                String serverPort=Integer.toString(srvrPort);
		String Mail_msg=new String();
         	 String message=new String();

		// Get user id
		int cmpid=-1;
                int uid=UserUtil.getUID(e_mail);
                boolean Result= uid == cmpid;
                ErrorDumpUtil.ErrorLog("GETTING USER ID....." +uid +" "+ Result);
		
                if(Result){
	                  try{
                              data.setMessage("You are not registered in Brihaspati LMS");
                              data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=You are not registered in Brihaspati LMS");
                          }
                          catch (Exception ex){
				String msg = "Error in activation	";
                               ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS inside 1st catch "+ex);
				 throw new RuntimeException(msg,ex);
                          }
		}
		else{

                        try{
			 // select activation key corresponding to user id in a_key
			  crit = new Criteria();
                          crit.add(UserPrefPeer.USER_ID,uid);
                          List list = UserPrefPeer.doSelect(crit);
                          String a_key =((UserPref)list.get(0)).getActivation();
			ErrorDumpUtil.ErrorLog("ACTIVATION KEY INSIDE RESEND ACTIVATION	"+a_key);
			  if (a_key == null || a_key.equalsIgnoreCase("NULL"))
			  {
				try{
                              data.setMessage("You are not registered in Brihaspati LMS.If you are registered then you contact to administrator.");
                              data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=You are not registered in Brihaspati LMS.If you are registered then you contact to administrator.");
                          	}
                          	catch (Exception ex){
					String msg1 = "Error in activation	";
                               		ErrorDumpUtil.ErrorLog("User not registered in Brihaspati LMS  inside 2nd catch"+ex);
                        		throw new RuntimeException(msg1,ex);
				}
			  }
			ErrorDumpUtil.ErrorLog("AFTER FIRST IF INSIDE RESEND ACTIVATION");
			   if (a_key == "ACTIVATE" || a_key.equalsIgnoreCase("ACTIVATE"))
			   {
				try{
	                              data.setMessage("Your account has already been activated.");
        	                      data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Your account has already been activated.");
                                }
                                catch (Exception ex){
					String msg2 = "Error in activation	";
                                        ErrorDumpUtil.ErrorLog("User account already exists inside 3rd catch"+ex);
					 throw new RuntimeException(msg2,ex);
                                }

			   }
			ErrorDumpUtil.ErrorLog("AFTER SECOND IF INSIDE RESEND ACTIVATION");
			    if (a_key != "ACTIVATE" && a_key != null)
			     {
				ErrorDumpUtil.ErrorLog("INSIDE 4TH IF");
				//sending activation link in the mail
				
				/**
 				* Assigning a string "newUser" in info_opt to get the keys like msgDear, msgRegard, 
                                * instAdmin/ brihaspatiAdmin defined in brihasapti.properties
                                */
				if(serverPort.equals("8080"))
                                      info_Opt = "newUser";
                                else
                                      info_Opt = "newUserhttps";
				
				fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                                pr =MailNotification.uploadingPropertiesFile(fileName);
				msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
//                                msgDear = MailNotification.getMessage_new(msgDear, FName, LName, "", UName);
				msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", e_mail);
				msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                                msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
				subject=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".a_subject");
      //                          messageFormate = MailNotification.getMessage(userRole, cAlias, dept, UName, "", serverName, serverPort, pr);
        			messageFormate = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".a_message"); // get a_key
	                        activationLink=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".activationLink");
                                activationLink=MailNotification.getMessage(activationLink, e_mail, a_key,"");
                                activationLink=MailNotification.replaceServerPort(activationLink, serverName, serverPort);
                                messageFormate = messageFormate+activationLink;
			//	Mail_msg = message + MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, msgBrihAdmin, email_existing, subject, "", file, instituteid);
				Mail_msg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", e_mail, subject, "", "", "","");//last parameter added by Priyanka
				data.setMessage(Mail_msg);
				

				try{
                             		 data.setMessage("An Activation email has been  sent to "+e_mail+" .Please click on the Activation link to activate your account.");
                             		 data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=An Activation email has been sent to "+e_mail+" .Please click on the Activation link to	activate your account.");
                                }
                                catch (Exception ex){
					String msg3 = "Error in activation	";
					ErrorDumpUtil.ErrorLog("Activation mail sending	inside 4th catch "+ex);
                               		throw new RuntimeException(msg3);
				 }
	
			     }
			      else
				{
					try{
                                         data.setMessage("Oops ! An error occured. Please re-enter your Email Id.");
                                         data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg=Oops ! An error occured. Please re-enter your Email Id.");
	                                }
        	                        catch (Exception ex){
						String msg4 = "Error in activation       ";
	                                        ErrorDumpUtil.ErrorLog(" Error sending activation mail inside 5th catch "+ex);
                        	     		throw new RuntimeException(msg4);

					   }
				}
				
			  }
			 catch(Exception ex){
                                String msg5 = "Error in activation   ";
				ErrorDumpUtil.ErrorLog(" Error sending activation mail inside 6th catch "+ex);
				 throw new RuntimeException(msg5);
			}

		  }
	}
}

