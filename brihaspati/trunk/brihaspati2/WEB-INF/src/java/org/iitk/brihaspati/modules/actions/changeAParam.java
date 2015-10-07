package org.iitk.brihaspati.modules.actions;
/*
 * @(#)changeAParam.java  
 *
 *  Copyright (c) 2005,2009-2013 ETRG,IIT Kanpur. 
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
import java.io.IOException;
import java.util.List;
import java.lang.StringBuffer;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.Turbine;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MailNotification;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;

import org.iitk.brihaspati.modules.utils.MailAuth;
import org.iitk.brihaspati.modules.utils.ValueObject;
import org.iitk.brihaspati.modules.utils.AdminConfig;
import org.iitk.brihaspati.modules.utils.UserInfo;
import org.iitk.brihaspati.modules.utils.TelObject;
import org.iitk.brihaspati.modules.utils.QuotaConfig;
import org.iitk.brihaspati.modules.utils.TurbineConfig;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import org.iitk.brihaspati.modules.utils.SortedProperties;

/**
 * @author <a href="mailto:nksinghiitk@yahoo.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:chitvesh@yahoo.com">Chitvesh Dutta</a>
 * @author <a href="mailto:awadhk_t@yahoo.com">Awahesh Kumar Trivedi</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a>
 * @modified date: 17-10-2009, 29-09-2010
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @modified date: 30-1-2013
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu singh sisaudiya</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @modified date: 12-03-2013, 16-03-2013, 22-08-2013
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date: 18-05-2015 (Seemanti);
 * @modified date: 15-07-15 (Seemanti) --- Dummy mail sending functionality updated ---
 * @modified date: 31-08-15,07-10-2015 (Seemanti).
 */

public class changeAParam extends SecureAction_Admin {

   public changeAParam (){}
   private String LangFile=null;
   private boolean debug= false;

   public void doUpdate(RunData data, Context context) throws Exception {
      User user=data.getUser();
      String loginName=user.getName();
      int uid=UserUtil.getUID(loginName);
      LangFile=(String)user.getTemp("LangFile");
      java.util.Date date= new java.util.Date();
      String LogfilePath=TurbineServlet.getRealPath("/logs")+"/Operation.txt";

      //MessageString to be updated after each step. To be displayed on next string
      StringBuffer sb = new StringBuffer();
      //Value = ValueObject.create the ValueObject(Data);
      //defaults are set for some of the required fields
      ValueObject value = new ValueObject(data);
      
      //Create path to reach PropertiesFile.
      String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
      if (debug) ErrorDumpUtil.ErrorLog("check!!!!!!!!!!!!PROPERTIES FILE PATH CHECK!!!!!!!!!!!!"+path);  
    
      //Create an object of MailAuth class by calling constructor 2 to retrieve data from PropertiesFile
      MailAuth LocalMailAuth = new MailAuth(path);
      Properties props = new Properties();
      
      InputStream f = new FileInputStream(path);
      
      if (value.getMailAuth() == null)
      {
         //add log message - some problem in value object and the null object is being returned. 
         ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Some problem in value object and the null mailauth object is being returned! | Date --> "+date+" --> "+TurbineServlet.getServerName(),LogfilePath);
      }       
      else if (!(value.getMailAuth().isEqual(LocalMailAuth)))//Returns true iff runtime and proprties data are not same so as to update mailauth data. 
      {  
         //This condition is going to check whether the Admin's profile is to be set for the first time. 
         //Load the propety file through input stream.
         props.load(f);
         f.close(); 
         //check if the property file has no MailAuth key-value pair ie; for the first time.
         if(!props.containsKey("brihaspati.mail.server"))
         {   
            if (!props.containsKey("brihaspati.mail.smtp.port"))
            {
               if (!props.containsKey("brihaspati.mail.smtp.from"))
               { 
                  if (!props.containsKey("brihaspati.mail.username"))
                  {  
                     if (!props.containsKey("brihaspati.mail.password"))
                     {
                        if (!props.containsKey("brihaspati.mail.local.domain.name"))
                        {  
                           //MailNotificationUtility.send a dummy message;
                           boolean flag1 = (MailNotification.dummySendMail("Dummy mail to check mail sending functionality", value.getMailAuth().getMuName(), "Dummy mail", "", LangFile, "",data));
                           //If dummy mail functionality does not works.
                           if(!flag1)
                           {
                              String truncated_muName=StringUtils.substringBefore(value.getMailAuth().getMuName(),"@");//Try sending dummy mail using truncated muName.
                              boolean flag2 = (MailNotification.dummySendMail("Dummy mail to check mail sending functionality", truncated_muName,"Dummy mail", "", LangFile, "",data));
                              if(!flag2)//If dummy mail functionality again does not works.
                              {
                                 ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation | Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
                                 sb.append("Mail Authentication data updation failed."+"\n");
                              }
                              else//If dummy mail functionality works here with truncated muName.
                              {  
                                 value.getMailAuth().setMailAuth(path);//Set the mail information in Admin.properties file.
                                 ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
                                 sb.append("Mail Authentication details updated successfully."+"\n");
                              }
                           }         
                           else//If dummy mail functionality works here with muName.
                           {  
                              value.getMailAuth().setMailAuth(path);//Set the mail information in Admin.properties file.
                              ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
                              sb.append("Mail Authentication details updated successfully."+"\n");
                           }
                        }
                     }  
                  }
               }
            }
         }//if end inside else-if of mailauth
          
         else //Updating the mail authentication details.
         {            
            //MailNotificationUtility.send a dummy message;
            boolean flag3 = (MailNotification.dummySendMail("Dummy mail to check mail sending functionality", value.getMailAuth().getMuName(), "Dummy mail", "", LangFile, "",data));
            //If dummy mail functionality does not works.
            if(!flag3)
            {
               String truncated_muName=StringUtils.substringBefore(value.getMailAuth().getMuName(),"@");//Try sending dummy mail using truncated muName.
               boolean flag4 = (MailNotification.dummySendMail("Dummy mail to check mail sending functionality", truncated_muName,"Dummy mail", "", LangFile, "",data));             
               if(!flag4)//If dummy mail functionality again does not works.
               {
                  //Logger.add date, time, IP address of browser client and message - message send failure in mailAuth config update to log.
                  //Append to MessageString - "Dummy message after mailAuth data updation failed.\n"
                  ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation | Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
                  sb.append("Mail Authentication data updation failed."+"\n");
                  
               }
               else //If dummy mail functionality works here with truncated muName.
               { 
                  value.getMailAuth().setMailAuth(path);//Set the mail information in Admin.properties file.
	          //Logger.add date, time, IP address of browser client and message - message was succesfully sent after mailAuth config update, to log.
	          //Append to MessageString - "Dummy message after mailAuth data updation successful.\n"
                  ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation | Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
                  sb.append("Mail Authentication details updated successfully."+"\n");
               }
            }
            else //If dummy mail functionality works here with muName.
            {   
               value.getMailAuth().setMailAuth(path);//Set the mail information in Admin.properties file.
               //Logger.add date, time, IP address of browser client and message - message was successfully sent after mailAuth config update, to log.
               //Append to MessageString - "Dummy message after mailAuth updation successful.\n"
               ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull mailAuth Configuration updation.....| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
               sb.append("Mail Authentication details updated successfully."+"\n");
            }	
         }//else end inside  else-if of mailauth.
      }//End of Else if of mailauth.

      else
      {
         // write the message for no change in MailAuth data.
         sb.append("No change in Mail Authentication Details.\n" );
      }//end of final else statement of mailauth.


      //QuotaConfig= ValueOjbect.retreive the quotaConfig from value;
      //QuotaConfig LocalQuotaConfig = QuotaConfig.retreive the quotaConfig from PropertiesFile("Admin.properties")
      QuotaConfig LocalQuotaConfig = new QuotaConfig(path);
      //if( quotaConfig from value and quotaConfig from PropertiesFile are different)
      if(!(value.getQuotaConfig().isEqual(LocalQuotaConfig)))
      {
         //QuotaUtil.update the quota info in quota database using quotaUtil;
         boolean qct=QuotaUtil.CreateandUpdate();
         //update the quotaConfig in PropertiesFile;
         value.getQuotaConfig().setQuotaConfig(path);
	 //Logger.add date, time, IP address of browser client and message - message was successfully sent after admin config update, to log.
	 //Append to MessageString - "Dummy message after QuotaConfig data updation successful.\n"
         ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for message send successfull in Quota Configuration details updation | Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
         sb.append("Quota Configuration details updated successfully."+"\n");
      }//end of if block for quota..
      else 
      {
          //Append to Message String - No change in Quota Configuration;
          sb.append("No change in Quota Configuration Details."+"\n");
      }//end of else of if for quota..

      //ValueObject.retrieve the AdminConfig from value.
      //AdminConfig LocalAdminConfig = AdminConfig.retreive the AdminConfig from PropertiesFile("Admin.properties")
      AdminConfig LocalAdminConfig = new AdminConfig(path);
      //if Adminconfiguration params from Value and Propeties File are not same then update them in Properties File otherwise show message that "no change in Admin Configuration".
      if(!(value.getAdminConfig().isEqual(LocalAdminConfig)))//Returns true iff runtime and proprties data are not same so as to update Admin Config data.
      {
         //update the items in AdminConfig in PropertiesFile.
         value.getAdminConfig().setAdminConfig(path);
         //Logger.add date, time, IP address of browser client and message - admin configuration updated, to log.
         ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull admin configuration in Admin.properties| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
         //Append to Message String - admin configuration updated;
         sb.append("Admin configuration parameters updated successfully."+"\n");
      }
      else 
      {
         //Append to Message String - No change in Admin Configuration;
          sb.append("No change in Admin Configuration Details."+"\n");
      }

      //UserInfo = ValueObject.retrieve the userInfo object from value;
      if (value.getUserInfo().setUserInfo(data,path))//if UserInfo from Value and Propeties File are different then update it in Properties File as well as in database.
       {
          //Logger.add date, time, IP address of browser client and message - userInfo update in turbine user database, to log.
           ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull userInfo updation in turbine user database | Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
      }
      
      //Create path to reach TurbineResources Properties File.
      String TRpath=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"TurbineResources.properties";
      //ValueObject.retreive the TurbineConfig object from value
      //update the items in TurbineConfig in TurbineResources.properties as well as in Admin.properties.
      value.getTurbineConfig().setTurbineConfig(TRpath,path,data);
      ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull turbine parameter updation in TurbineResources.properties| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
      
      //TelObject =ValueObject.retrieve the telephone data object from value;
      //TelDirectoryUtil.update the telephone data for admin in telephone database if different then stored, should return true if updated, else the telephone data function should return false;
      if (value.getTelObject().setTelObject(data)){
         //if(true) add date, time, IP address of browser client and message - telephone data updated, to log.
          ErrorDumpUtil.ErrorLog("User Name --> Admin | Operation --> Log update for successfull telephone data updation in telephone database.| Date --> "+date+ "| IP Address --> "+TurbineServlet.getServerName(),LogfilePath);
      }
      //Display the appended string buffer object.
      data.addMessage(sb.toString());
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      /*Perform sorting of Admin.properties file just after all the key-value pairs had been written into it.
       *For this, invoke the utility method for sorting a proprtyList.
       */
      SortedProperties.sortPropertyFile(path,data);
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      boolean checkprofadmin = false;
      user.setTemp("checkprofadmin",false);
     
   }//End of doUpdate()

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

}//End of this class.     	
      


