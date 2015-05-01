package org.iitk.brihaspati.modules.actions;
/*
 * @(#)ChangeFgPassword.java	
 *
 *  Copyright (c) 2015 ETRG,IIT Kanpur. 
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
import java.util.Date;

import org.apache.velocity.context.Context;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.util.RunData;  
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.util.Criteria; 
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspati.modules.utils.UserUtil; 
import org.iitk.brihaspati.om.ForgotpassPeer;
import org.iitk.brihaspati.om.Forgotpass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class have different methods for using forgot password procedure
 * @author <a href="nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 */

public class ChangeFgPassword extends VelocitySecureAction
{
	String LangFile=new String();
	String lang=new String();
	String msg=new String();
	private Log log = LogFactory.getLog(this.getClass());
	protected boolean isAuthorized( RunData data ) throws Exception
	{
		return true;
	}
	/**
 	* This method checks the existence and authority of the user to retrieve the new password
 	* @param data RunData instance
 	* @param context Context instance
 	* 
 	*/
 	public void doUpdate(RunData data, Context context)
        {
		ParameterParser pp=data.getParameters();
                try
                {
                	String usrid=pp.getString("usrid");
                	String keyhash=pp.getString("rankey");
                	String newpassword=pp.getString("newpassword");
                	String retyppasswd=pp.getString("retypepassword");
			//check both password are same
			if(newpassword.equals(retyppasswd)){
				String uName=UserUtil.getLoginName(Integer.parseInt(usrid));
				Criteria crit=new Criteria();
                                crit.add(ForgotpassPeer.USER_NAME,uName);	
				List retkey=ForgotpassPeer.doSelect(crit);
				if(retkey.size() !=0){
					Forgotpass element=(Forgotpass)retkey.get(0);
                		        String dbkey=element.getRkey();
					//check the keyhash
		                        if(keyhash.equals(dbkey)){
						//update password
						/**
 *                                                 * new Password encrypted by MD5 then modify database 
 *                                                 * password for user
 *                                              */
                                                String encPass=EncryptionUtil.createDigest("SHA1",newpassword);
						User user = TurbineSecurity.getUser(uName);
                                                user.setPassword(encPass);
                                                TurbineSecurity.saveUser(user);
                                                msg=MultilingualUtil.ConvertedString("forgotPwd_msg3",LangFile);
						// Maintain Log File
						Date date = new Date();
                                                log.info("Successfully Forget Password Operation called by --> "+uName +" | Time of operation --> "+date+ " | IP Address --> "+data.getRemoteAddr());
						//send mail to user
						String server_scheme = TurbineServlet.getServerScheme();
	                                        String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
        	                                String msg1=new String();
                	                        try
                        	                {
							String msgRegard="", msgBrihAdmin="";
							Properties pr =MailNotification.uploadingPropertiesFile(fileName);
							msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
							msgRegard = MailNotification.replaceServerPort(msgRegard);
							msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification.newUser.msgBrihAdmin");
							String subject = MailNotification.subjectFormate("newPassword", "", pr );
							String message = MailNotification.getMessage("newPassword", "", "", "", newpassword, pr);
	                                                message = MailNotification.replaceServerPort(message);
							msg1=MailNotificationThread.getController().set_Message(message, "", msgRegard, msgBrihAdmin, uName, subject, "", LangFile);
						}
						catch(Exception ex)
				                {
				                        data.setMessage("The error in send new password via forgot password over email. "+ex);
				                }

                                               	if(msg1.equals("Success"))
                                                {
                                                	msg1=MultilingualUtil.ConvertedString("mail_msg",LangFile);
	                                                data.setMessage(msg1);
							//remove keyhash from database
							crit=new Criteria();
			                                crit.add(ForgotpassPeer.USER_NAME,uName);
							ForgotpassPeer.doDelete(crit);
						}

						//redirect brihaspatilogin
						data.addMessage(msg);
                                                setTemplate(data,"BrihaspatiLogin.vm");

					}
					else{
						msg=MultilingualUtil.ConvertedString("forgotPwd_msg9",LangFile);
	                                        data.setMessage(msg);
        	                                setTemplate(data,"BrihaspatiLogin.vm");
					}
				
				}
				else{
					msg=MultilingualUtil.ConvertedString("assignment_msg17",LangFile);
                                        data.setMessage(msg);
                                        setTemplate(data,"BrihaspatiLogin.vm");
				}

			}
			else{
				msg=MultilingualUtil.ConvertedString("forgotPwd_msg8",LangFile);
                                data.setMessage(msg);
                                setTemplate(data,"BrihaspatiLogin.vm");			
			}
		}
		catch(Exception e)
                {
                        data.setMessage("The error password update via forgot password "+e);
                }
	}

	/**
 	* This method is invoked when no button corresponding to
 	* doForgotpassword  is found
 	* @param data RunData
 	* @param context Context
 	* @exception Exception, a generic exception
 	*
 	*/
        public void doPerform(RunData data,Context context) throws Exception
	{
		ParameterParser pp=data.getParameters();
        	lang=pp.getString("lang","english");
        	context.put("lang",lang);
		LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(lang);
        	context.put("LangFile",LangFile);
                String action=data.getParameters().getString("actionName","");
                if(action.equals("eventSubmit_doUpdate"))
                        doUpdate(data,context);
                else
		{
			msg=MultilingualUtil.ConvertedString("error_msg4",LangFile);
                        data.setMessage(msg);
		}
        }
}

