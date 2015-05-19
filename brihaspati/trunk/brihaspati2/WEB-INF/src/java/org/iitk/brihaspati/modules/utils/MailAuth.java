package org.iitk.brihaspati.modules.utils;
/*
 * @(#)MailAuth.java  
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

import java.io.IOException;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MailNotification;

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 */

//Create MailAuth class so as to retrieve  mailauth object.
 public class MailAuth {

   private String mailServ;
   private String mailServPort;
   private String mailFrom;
   private String muName;
   private String mPass;
   private String domainNM;
   private boolean debug=true;

   //This constructor 1 retrieves MailAuth data at Runtime! 
   public MailAuth(String a, String b, String c, String d, String e, String f) {
      if(debug) ErrorDumpUtil.ErrorLog("PRINT THE VALUE OF DOMAIN NAME!"+f);
      mailServ = a;
      mailServPort = b;
      mailFrom = c;
      muName = d;
      mPass = e;
      domainNM = f; 
   }

   //This constructor 2 retrieves MailAuth data from PropertiesFile.
   public MailAuth(String path) {
      try {
         mailServ = AdminProperties.getValue(path,"brihaspati.mail.server");
         mailServPort = AdminProperties.getValue(path,"brihaspati.mail.smtp.port");
         mailFrom = AdminProperties.getValue(path,"brihaspati.mail.smtp.from");
         muName = AdminProperties.getValue(path,"brihaspati.mail.username");
         mPass =  AdminProperties.getValue(path,"brihaspati.mail.password");
         domainNM = AdminProperties.getValue(path,"brihaspati.mail.local.domain.name");
     } 
     catch(Exception e) {
      e.printStackTrace(); 
     }
   }

   //In this method we set the MailAuth Data in PropertiesFile.
   public void setMailAuth(String path) {
   
      try {
         if(debug) ErrorDumpUtil.ErrorLog("PRINT THE VALUE OF DOMAIN NAME FUNCTION!!!!"+getDomainNM());
         AdminProperties.setPropertyValue(path,muName,"brihaspati.mail.username");
         AdminProperties.setPropertyValue(path,mPass,"brihaspati.mail.password");
         AdminProperties.setPropertyValue(path,mailServ,"brihaspati.mail.server");
         AdminProperties.setPropertyValue(path,mailServPort,"brihaspati.mail.smtp.port");
         AdminProperties.setPropertyValue(path,mailFrom,"brihaspati.mail.smtp.from");
         AdminProperties.setPropertyValue(path,domainNM,"brihaspati.mail.local.domain.name");
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }

   public String getMailServ(){
      return mailServ;
   } 
   
   public String getMailServPort(){
      return mailServPort;
   }   
   
   public String getMailFrom(){
      return mailFrom; 
   }   
   
   public String getMuName(){
      return muName;
   }   
   
   public String getMPass(){
      return mPass;
   }   
   
   public String getDomainNM(){
      return domainNM; 
   }     
   
   //This method compares MailAuth data from runtime & MailAuth from PropertiesFile to return a boolean value.  

   public boolean isEqual(MailAuth tmp){
      if(tmp == null)
         return false;
      if(!mailServ.equals(tmp.getMailServ()))
         return false;
      if(!mailServPort.equals(tmp.getMailServPort()))
	 return false;
      if(!mailFrom.equals(tmp.getMailFrom()))
	 return false;
      if(!muName.equals(tmp.getMuName()))
	 return false;
      if(!mPass.equals(tmp.getMPass())) 
	 return false;
      if(!domainNM.equals(tmp.getDomainNM()))
         return false;
      return true;
   }

}//End of class
