package org.iitk.brihaspati.modules.utils;

/*(#)PasswordUtil.java
 *
 *  Copyright (c) 2005, 2010 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */

/**
 * @author: <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author: <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 * @author: <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @modified date: 08-07-2010, 07-12-2010, 13-07-2011
 */
import java.util.Random;
import java.util.Properties;

import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.services.security.TurbineSecurity;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;
import babylon.babylonUserTool;
import babylon.babylonPasswordEncryptor;
/**
 * This is a util class used to change the password of the user
 */
public class PasswordUtil{
	
//	static String serverName= "", serverPort="", server_scheme="";
	/**
	 * Used to change the password
	 * @param user User The user object for the user for whom
	 *  password is to be modified
	 * @param oldPassword String Old password of user
	 * @param newPassword String New password to be update
	 * @return String
	 */
	public static String doChangepassword(User user, String oldPassword,String newPassword,String file){
                /**
                 * Getting file value from temporary variable according to selection of Language
                 * Replacing the static value from Property file
                 **/
		MultilingualUtil m_u=new MultilingualUtil();
		babylonUserTool tool=new babylonUserTool();
		String message=new String();
		try{
			/**
		  	 * Get the user name of the user from User object
			 */
			String userName=user.getName();
			/**
			 * Encrypt the new password
			 * @see EncryptionUtil in utils
			 */
			String encryptPasswd=EncryptionUtil.createDigest("SHA1",newPassword);
			/**
		  	 * Get the original password of the user from
		  	 * the user object
			 */
			String existingPassword=user.getPassword();
			if(!oldPassword.equals("")){
				/**
				 * Compare the old password entered by
				 * the user with the one in database.If
				 * it is not same then sent the error
				 * message
				 */
				int result=existingPassword.compareTo(EncryptionUtil.createDigest("SHA1",oldPassword));
				if(result!=0){
					result=existingPassword.compareTo(EncryptionUtil.createDigest("MD5",oldPassword));
				}
				if(result!=0){
                		/**
                 		* @param file Getting file value from temporary variable according to selection of Language
                 		* @param brih_pwdUpdate Replacing the static value from Property file
                 		**/
					message=m_u.ConvertedString("brih_pwdUpdate",file);
					return message;
				}
			}
			
			/**
			 * Set the new password in the user object of
			 * user and save it
			 */

			user.setPassword(encryptPasswd);
			TurbineSecurity.saveUser(user);
			/**
			 * Encrypt the new passoword using babylonPasswordEncryptor. Here,the parameter 
			 * that has to be passed to babylonPasswordEncryptor should be the encrypted one.
                         * Hence, the resultant password in babylon chat server will be double encrypted.
			 */
			String babylonPassword=new babylonPasswordEncryptor().encryptPassword(encryptPasswd);		
			/**
                         * Delete the user from babylon chat server
                         */
                        String msg=new String();
                        try{
                                tool.deleteUser(userName);
                        }catch(Exception e){
                                msg="Cannot delete the user's account from chat server !!"+e;
                        }
                        /**
                         * Create user into babylon chat server with new
                         * password
                         */
                        try{
                                tool.createUser(userName,babylonPassword);
                        }catch(Exception ex){
                                msg="Unable to create new user in chat server !!"+ex;
                        }
	                /**
	                 * Getting file value from temporary variable according to selection of Language
	                 * Replacing the static value from Property file
	                 **/
			
	                String loginName=user.getName();
        	        User user1 = TurbineSecurity.getUser(loginName);
                	String mailId=user1.getEmail();
			/**
	                  * checking for the user's e-mail
        	          * from the database table.
                	  */
               	        String msg1=new String();
                	if(!mailId.equals(""))
	                {
        	                String fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
                        	try
	                        {
	//				server_scheme =TurbineServlet.getServerScheme();
					// Shaista did Modification for mail Sending 
					String info_new = "", info_Opt="", msgRegard="", msgBrihAdmin="";
        	                        /*if(PasswordUtil.serverPort == "8080"){
						info_new = "newPassword";
						 info_Opt = "newUser";
					}
					else {
						info_new = "newPasswordhttps";
						info_Opt = "newUserhttps";
					}*/
					Properties pr =MailNotification.uploadingPropertiesFile(fileName);
					//msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
                                        msgRegard=pr.getProperty("brihaspati.Mailnotification.newUser.msgRegard");
					//msgRegard = MailNotification.replaceServerPort(msgRegard, PasswordUtil.serverName, PasswordUtil.serverPort);
                                        msgRegard = MailNotification.replaceServerPort(msgRegard);
					//msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgBrihAdmin");
                                        msgBrihAdmin=pr.getProperty("brihaspati.Mailnotification.newUser.msgBrihAdmin");
					//String subject = MailNotification.subjectFormate(info_new, "", pr );
					String subject = MailNotification.subjectFormate("newPassword", "", pr );
					//String messageFormat = MailNotification.getMessage(info_new, "", "", "", newPassword, PasswordUtil.serverName, PasswordUtil.serverPort,pr);
					//String messageFormat = MailNotification.getMessage(info_new, "", "", "", newPassword,PasswordUtil.serverName, PasswordUtil.serverPort, pr);
					String messageFormat = MailNotification.getMessage("newPassword", "", "", "", newPassword, pr);
					messageFormat = MailNotification.replaceServerPort(messageFormat);
					//ErrorDumpUtil.ErrorLog("\n\n\nsubject="+subject+"\n messageFormat="+messageFormat+"\nmsgRegard	"+msgRegard);
					//msg1= MailNotificationThread.getController().set_Message(messageFormat, "", msgRegard, msgBrihAdmin, mailId, subject, "", file, "","");//last parameter added by Priyanka
					msg1= MailNotificationThread.getController().set_Message(messageFormat, "", msgRegard, msgBrihAdmin, mailId, subject, "", file);
					if(msg1.equals("Success"))
						msg1=m_u.ConvertedString("mail_msg",file);
							
					//MailNotification.sendMail(messageFormat, mailId, subject, "", file);
					//ErrorDumpUtil.ErrorLog("\n msg1="+msg1);


				/**						
        	                        if(serverPort == "8080")
					{
                        	                msg1=MailNotification.sendMail("newPassword",mailId,"","","",newPassword,fileName,serverName,serverPort,file);
                                	}
                               		 else
	                                {
        	                                msg1=MailNotification.sendMail("newPasswordhttps",mailId,"","","",newPassword,fileName,serverName,serverPort,file);
                	                }
				**/
                        	}
	
        	                catch(Exception e){}
                	}
			String pwdOf=m_u.ConvertedString("pwdOf",file);
			String pwdChangeSuccess=m_u.ConvertedString("pwdChangeSuccess",file);
			 if(file.endsWith("hi.properties")) 
				message=userName+" " +pwdOf+" " +pwdChangeSuccess +msg+"\n"+msg1;
                        else if(file.endsWith("urd.properties"))
                                message=pwdOf+" "+pwdChangeSuccess+" "+userName +msg+"\n"+msg1;

			else
				message=pwdOf+" "+userName+" " +pwdChangeSuccess +msg+"\n"+msg1;
				//message=message+" and Mail can not send";
		}
		catch(Exception exc)
		{
	                /**
	                 * Getting file value from temporary variable according to selection of Language
	                 * Replacing the static value from Property file
	                 **/
			message="The error in PasswordUtil :- "+exc;
		}
		return message;
	}
/*
	public static void passwordFromUtil(String serverName, String serverPort){
		PasswordUtil.serverName = serverName;
		PasswordUtil.serverPort = serverPort;	
	}
*/
	/**
         * Used to generate random password password
         * @return String
         */

	public static String randmPass(){
		byte[] pass=new byte[8];

               /** 
               * Random is a function for 
               * generating random numbers. 
               */

               	Random rnd=new Random();
               	for(int i=0;i<8;i++)
               	{
  	        	int inPass=rnd.nextInt(26);
                        int p1;
                        if((inPass%2)==0)
                     	{
                                p1=inPass%10;
                                pass[i]=(byte)(p1+48);
                        }
                        else
                        {
                        	pass[i]=(byte)(inPass+97);
                        }
                }
		String password=new String(pass);
		return password;
	}

	}
