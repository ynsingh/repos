package org.iitk.brihaspati.modules.actions;

/**
 * @(#) InstituteAdminMail.java  
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.StringReader;
//import java.io.FileReader;
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
import org.iitk.brihaspati.modules.utils.UserUtil;
import java.util.Iterator;
import java.util.Vector;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

/**
 * This class is responsible for verification of source email
 * before sending mail to Insitute Admin.
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 * @modify date: 10-12-2012
 */


public class InstituteAdminMail extends VelocityAction{

	private Log log = LogFactory.getLog(this.getClass());
	private String serverName="";
	private int srvrport;
	private String serverPort="";
	private ParameterParser parameterparser; 
        String str, msg1;
        MultilingualUtil mu = new MultilingualUtil();
	String info_Opt="", msgRegard="", msgDear="", messageFormate="", subject="", confirmationMail="";
        String Mailmsg=new String();
	String fileName;
	Properties pr ;
        
	/**
         * This method is called when user submits form
         * for sending mail.
         * @param data RunData instance
         * @param context Context instance
         */
	public void doPerform(RunData rundata,Context context) throws Exception
        {
		System.gc();
        	serverName=rundata.getServerName();
                srvrport=rundata.getServerPort();
                serverPort=Integer.toString(srvrport);
                parameterparser = rundata.getParameters();

		String mode=parameterparser.getString("mode","");	
		if(mode.equals("store"))
		{
			storeMail(rundata);
		//	rundata.setMessage(str);
		}
		if(mode.equals("send"))
		{
			sendMail(rundata);
		}
	}//method
	
	/**
 	 * Stores mail along with other information
 	 * in the xml and sends confirmation mail at 
 	 * sender's email id.
 	 */

	public void storeMail(RunData data)
	{
		String msg="";
		ParameterParser pp = data.getParameters();
		XMLWriter_EmailUpdation exml=new XMLWriter_EmailUpdation();
		String instDet=pp.getString("inst");
		//ErrorDumpUtil.ErrorLog("inst details "+instDet);
		String instName = StringUtils.substringBeforeLast(instDet,"~??~");
		//ErrorDumpUtil.ErrorLog("institute name = "+instName);
		String adminEmail= StringUtils.substringAfterLast(instDet,"~??~");
		//ErrorDumpUtil.ErrorLog("institute admin email = "+adminEmail);
	        String lang=pp.getString("lang","");
		String senderEmail=pp.getString("from","");
                String subject=pp.getString("subject","");
		String message=pp.getString("message","");	
		String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);	
		if(instName.equals("Select Institute"))	
		{
			data.setMessage(MultilingualUtil.ConvertedString("instName",LangFile));
			return;
		}
		if(StringUtil.checkString(senderEmail) != -1)
                {
                     data.addMessage(MultilingualUtil.ConvertedString("usr_prof1",LangFile));
                     return;
                }
                if(senderEmail.indexOf("@") == -1)
                {
                     data.addMessage(MultilingualUtil.ConvertedString("malformed_email",LangFile));
                     return;
                }
		try{
			BufferedReader reader = new BufferedReader(new StringReader(message));
			String  line = null;
                        StringBuilder stringBuilder = new StringBuilder();
			while( ( line = reader.readLine() ) != null ) {
                                stringBuilder.append( line );
				stringBuilder.append("<br>");
                        }
                        msg = stringBuilder.toString();
			//Generate a random string
			String randm_n = PasswordUtil.randmPass();
			String str1=randm_n+senderEmail;
                	String a_key=EncryptionUtil.createDigest("SHA1",str1);	
			/**
                 	* @see ExpiryUtil in Utils
                 	*/
		 	String curdate=Integer.toString(Integer.parseInt(ExpiryUtil.getCurrentDate("")));
		
			/** getting path for creating EmailUpdation directory*/
			String filepath=TurbineServlet.getRealPath("/EmailUpdation");
                	File f=new File(filepath);
                	if(!f.exists())
                		f.mkdirs();
		
			filepath=filepath+"/SourceMailVerify.xml";
			/**
		 	* Emails not verified for more than seven days
		 	* will be considered expired and thus the
		 	* corresponding entry will be removed from the xml.
		 	*/
			exml.deleteExpiredProfile(filepath);
		
			String update = XMLWriter_EmailUpdation.sourceEmailVerification(filepath, senderEmail, subject, msg, adminEmail, a_key, curdate, instName);
			 if(update.equals("Successfull"))
			 {
				Mailmsg = mailForward("","","sourceMailVerify",msg,instName,senderEmail,a_key,lang);
                	 	msg1=MultilingualUtil.ConvertedString("mail_confirm",LangFile);
			 }
			 else
				msg1=MultilingualUtil.ConvertedString("sourceVerify",LangFile);
        		data.setMessage(msg1);                
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error in class: InstituteAdminMail, method: sendMail catch1 "+e);
		}
	}//method

	/**
 	 * This method is called when sender
 	 * clicks on the verification link,
 	 * the corresponding message is then
 	 * forwarded to the institute admin.
 	 */

	public void sendMail(RunData data)
	{
		ParameterParser pp = data.getParameters();
		String lang=pp.getString("lang");
                String LangFile=MultilingualUtil.LanguageSelectionForScreenMessage(lang);
                String e_mail=pp.getString("email");
                String a_key=pp.getString("key");
		String filePath;
		String msg="",instName="",sbjct="";
		String admin_email="", sender_email="", hash="";
		try
		{
			/** getting path for EmailUpdation xml*/
			filePath=TurbineServlet.getRealPath("/EmailUpdation");
                        filePath=filePath+"/SourceMailVerify.xml";
                        Vector v = XMLWriter_EmailUpdation.readEmailDetails(filePath,e_mail,a_key);
			if(v.size()>0)
                        {
                        	admin_email =(String) v.get(0);
                                sender_email =(String) v.get(1);
				sbjct =(String) v.get(2);
				msg =(String) v.get(3);
				hash =(String) v.get(4);
				instName = (String) v.get(5);
                        }
			//mail sending
			if(sender_email.equals(e_mail) && a_key.equals(hash))
                        {
				Mailmsg=mailForward(admin_email,sbjct,"mailToAdmin", msg,"",sender_email,"","");
				// Removing the entry from the xml,
				// after message sent
				XMLWriter_EmailUpdation.setHash(filePath, sender_email, hash);
				String res = XMLWriter_EmailUpdation.removeEmailElement(filePath, e_mail);
                                if(res.equals("Successfull"))
                                {
                                       try{
                                                str=mu.ConvertedString("mail_sent",LangFile)+" "+instName;
                                                data.setMessage(str);
                                                data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                        }
                                        catch (Exception ex){
                                                 msg = "ERROR WHILE SENDING MAIL TO INSTITUTE ADMIN";
                                                 ErrorDumpUtil.ErrorLog("Error in InstituteAdminMail "+ex);
                                                 throw new RuntimeException(msg,ex);
                                        }
				}//if
			}//if1
			else
                        {
                        	/**
                                 * In this case email is being retrieved 
                                 * from the mail, but entry for "this" email 
                                 * does not exist in the xml
                                 */
				try{
                                	str=MultilingualUtil.ConvertedString("msg_admin",LangFile);
                                        data.setMessage(str);
                                        data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                                 }
                                 catch (Exception ex){
                                        String msg1 = "ERROR WHILE SENDING MAIL TO INSTITUTE ADMIN";
                                        ErrorDumpUtil.ErrorLog("Error in InstituteAdminMail "+ex);
                                        throw new RuntimeException(msg1,ex);
                                 }
                        }//else2

		}//try
		catch(Exception e)
                {
                        try{
                                str=MultilingualUtil.ConvertedString("sourceVerify",LangFile);
                                data.setMessage(str);
                                data.getResponse().sendRedirect(data.getServerScheme()+"://"+data.getServerName()+":"+data.getServerPort()+"/brihaspati/servlet/brihaspati/template/BrihaspatiLogin.vm?msg="+str);
                        }
                        catch (Exception ex){
                                String msg1 = "ERROR WHILE SENDING MAIL TO INSTITUTE ADMIN";
                                ErrorDumpUtil.ErrorLog("User's email could not be verified "+ex);
                                throw new RuntimeException(msg1,ex);
                        }
		}//catch
	}//method

private String mailForward(String email, String sbj, String str, String msg, String instName, String senderEmail, String a_key, String lang)
{	
	String subject="";
	String mail_str="";
	try
	{
		if(str.equals("mailToAdmin"))
		{
			if(serverPort.equals("8080"))	
			{
		       		info_Opt = "newUser";
				mail_str = "mailToAdmin";
			}
       	 		else
			{
                		info_Opt = "newUserhttps";
				mail_str = "mailToAdminhttps";
			}
		}
		if(str.equals("sourceMailVerify"))
                {
                        if(serverPort.equals("8080"))
                        {
                                info_Opt = "newUser";
                                mail_str = "sourceMailVerify";
                        }
                        else
                        {
                                info_Opt = "newUserhttps";
                                mail_str = "sourceMailVerifyhttps";
                        }
                }

        	fileName=TurbineServlet.getRealPath("/WEB-INF/conf/brihaspati.properties");
        	pr =MailNotification.uploadingPropertiesFile(fileName);
        	msgDear = pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgDear");
        	msgDear = MailNotification.getMessage_new(msgDear, "Brihaspati", "User", "", email);
        	msgRegard=pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgRegard");
		msgRegard=msgRegard+pr.getProperty("brihaspati.Mailnotification."+info_Opt+".msgBrihAdmin");
        	msgRegard = MailNotification.replaceServerPort(msgRegard, serverName, serverPort);
		if(str.equals("mailToAdmin"))
		{
			messageFormate = pr.getProperty("brihaspati.Mailnotification."+mail_str+".message");
                        messageFormate=MailNotification.getMessage(messageFormate, senderEmail, msg);
			Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", email, sbj, "", "", "", "");
		}
		if(str.equals("sourceMailVerify"))
		{
			subject=pr.getProperty("brihaspati.Mailnotification."+mail_str+".subject");
			messageFormate = pr.getProperty("brihaspati.Mailnotification."+mail_str+".message");
                        messageFormate=MailNotification.getMessage(messageFormate, instName, msg);
                        messageFormate=MailNotification.replaceServerPort(messageFormate, serverName, serverPort);
                        messageFormate=MailNotification.getMessage(messageFormate, senderEmail, a_key, "send", lang);
		       	Mailmsg = MailNotificationThread.getController().set_Message(messageFormate, msgDear, msgRegard, "", senderEmail, subject, "", "", "", "");
		}
	}
	catch(Exception e)
	{
		 ErrorDumpUtil.ErrorLog("Error in class: InstituteAdminMail, method: mailForward  "+e);
	}
	return Mailmsg;
}

}//class
