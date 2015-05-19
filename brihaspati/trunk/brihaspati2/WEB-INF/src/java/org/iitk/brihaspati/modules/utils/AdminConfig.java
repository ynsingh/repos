package org.iitk.brihaspati.modules.utils;

/*
 * @(#)AdminConfig.java  
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

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 */

//Create AdminConfig class so as to retrieve AdminConfig object.
public class AdminConfig {

   private String AdminConf;
   private String AdminCrsExp;
   private String AdminPassExp;
   private String AdminFaqExp;   
   private String hdir;
   private String dstore;
   private String dstoreurl;
   private String authmethod;
   private String ldapurl;
   private String ldapbase;
   private String ldapcate;
   private String twtexp;
   private String mailSpoolResendTime;
   private String mailSpoolExpiryDay;
   private String normalTrafficTime;
   private String highTrafficTime;
   private String brihServerUrl;   
   private String port;

   //This constructor 1 retrieves AdminConfig data at Runtime! 
   public AdminConfig(String a,String b,String c,String d,String e,String f,String g, String h, String i, String j, String k, String l,String m,String n,String o,String p,String q,String r) {

      AdminConf = a;
      AdminCrsExp = b;
      AdminPassExp = c;
      AdminFaqExp= d;
      hdir = e;
      dstore= f;
      dstoreurl= g;
      authmethod= h;
      ldapurl= i;
      ldapbase= j;
      ldapcate= k;
      twtexp= l;
      mailSpoolResendTime = m;
      mailSpoolExpiryDay = n;
      normalTrafficTime = o;
      highTrafficTime = p;
      brihServerUrl = q;
      port = r;
   }

   //This constructor 2 retrieves AdminConfig data from PropertiesFile.
   public AdminConfig(String path) {
      try {
         AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
         AdminCrsExp = AdminProperties.getValue(path,"brihaspati.admin.courseExpiry");
         AdminPassExp = AdminProperties.getValue(path,"brihaspati.admin.passwordExpiry");
         AdminFaqExp= AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
         hdir =  AdminProperties.getValue(path,"brihaspati.home.dir.value");
         dstore= AdminProperties.getValue(path,"brihaspati.admin.datastore.value");
         dstoreurl= AdminProperties.getValue(path,"brihaspati.admin.hdfsurl.value");
         authmethod= AdminProperties.getValue(path,"brihaspati.admin.authmethod.value");
         ldapurl=  AdminProperties.getValue(path,"brihaspati.admin.ldapurl.value");
         ldapbase=  AdminProperties.getValue(path,"brihaspati.admin.ldapbase.value");
         ldapcate= AdminProperties.getValue(path,"brihaspati.admin.ldapcate.value");
         twtexp= AdminProperties.getValue(path,"brihaspati.admin.twtexpiry.value");
         mailSpoolResendTime = AdminProperties.getValue(path,"brihaspati.admin.mailSpoolingExpiry.value");
         mailSpoolExpiryDay = AdminProperties.getValue(path,"brihaspati.admin.spoolMailResendTime.value");
         normalTrafficTime =  AdminProperties.getValue(path,"brihaspati.admin.normalTraffic.value");
         highTrafficTime = AdminProperties.getValue(path,"brihaspati.admin.highTraffic.value");
         brihServerUrl = AdminProperties.getValue(path,"brihaspati.admin.brihaspatiServerIP.value");
         port = AdminProperties.getValue(path,"brihaspati.spring.port");
     } 
     catch(Exception e) {
      e.printStackTrace(); 
     }
   }

   //This method sets the AdminConfig strings in the Admin.properties file.
   public void setAdminConfig(String path) {

   try {
      AdminProperties.setPropertyValue(path,AdminConf,"brihaspati.admin.listconfiguration.value");
      AdminProperties.setPropertyValue(path,AdminCrsExp,"brihaspati.admin.courseExpiry");
      AdminProperties.setPropertyValue(path,AdminPassExp,"brihaspati.admin.passwordExpiry");
      AdminProperties.setPropertyValue(path,hdir,"brihaspati.home.dir.value");
      AdminProperties.setPropertyValue(path,AdminFaqExp,"brihaspati.admin.FaqExpiry");
      AdminProperties.setPropertyValue(path,dstore,"brihaspati.admin.datastore.value");
      AdminProperties.setPropertyValue(path,dstoreurl,"brihaspati.admin.hdfsurl.value");
      AdminProperties.setPropertyValue(path,authmethod,"brihaspati.admin.authmethod.value");
      AdminProperties.setPropertyValue(path,ldapurl,"brihaspati.admin.ldapurl.value");
      AdminProperties.setPropertyValue(path,ldapbase,"brihaspati.admin.ldapbase.value");
      AdminProperties.setPropertyValue(path,ldapcate,"brihaspati.admin.ldapcate.value");
      AdminProperties.setPropertyValue(path,twtexp,"brihaspati.admin.twtexpiry.value");
      AdminProperties.setPropertyValue(path,mailSpoolResendTime, "brihaspati.admin.spoolMailResendTime.value");
      AdminProperties.setPropertyValue(path,mailSpoolExpiryDay, "brihaspati.admin.mailSpoolingExpiry.value");
      AdminProperties.setPropertyValue(path,port,"brihaspati.spring.port");
     
      if(normalTrafficTime.equals("")){
         //in seconds
         normalTrafficTime = "15";
      }
      AdminProperties.setPropertyValue(path,normalTrafficTime,"brihaspati.admin.normalTraffic.value");

      if(highTrafficTime.equals("")){
         //in seconds
         highTrafficTime = "30";
      }
      AdminProperties.setPropertyValue(path,highTrafficTime,"brihaspati.admin.highTraffic.value");
         
      AdminProperties.setPropertyValue(path,brihServerUrl,"brihaspati.admin.brihaspatiServerIP.value");
      }
      catch(Exception e) {
         e.printStackTrace();}
   }//End of setAdminConfig() method.
   
    public String getAdminConf(){
      return AdminConf;
   }
   public String getAdminCrsExp(){
      return AdminCrsExp;
   }
   public String getAdminPassExp(){
      return AdminPassExp;
   }
   public String gethdir() {
      return hdir;
   }
   public String getAdminFaqExp() {
      return AdminFaqExp;
   }
   public String getdstore(){
      return dstore;
   }
   public String getdstoreurl(){
      return dstoreurl;
   }
   public String getauthmethod() {
      return authmethod;
   }
   public String getldapurl(){
      return ldapurl;
   }
   public String getldapbase() {
      return ldapbase;
   }
   public String getldapcate(){
      return ldapcate;
   }
   public String gettwtexp(){
      return twtexp;
   }
   public String getmailSpoolResendTime(){
      return mailSpoolResendTime;
   }
   public String getmailSpoolExpiryDay() {
      return mailSpoolExpiryDay;
   }
   public String getport(){
      return port;
   }
   public String getnormalTrafficTime(){
      return normalTrafficTime;
   }
   public String gethighTrafficTime(){
      return highTrafficTime;
   }
   public String getbrihServerUrl(){
      return brihServerUrl;
   }

   //This method compares the AdminConfig data from runtime & Admin.properties File to return a boolean value.
   public boolean isEqual(AdminConfig tmp){
      if( tmp == null)
         return false;
      if(!AdminConf.equals(tmp.getAdminConf()))
      {   return false;
      }
      if(!AdminCrsExp.equals(tmp.getAdminCrsExp()))
      {   return false;
      }
      if(!AdminPassExp.equals(tmp.getAdminPassExp()))
      {   return false;
      }
      if(!hdir.equals(tmp.gethdir()))
      {   return false;
      }
      if(!AdminFaqExp.equals(tmp.getAdminFaqExp()))
      {   return false;
      }
      if(!dstore.equals(tmp.getdstore()))
      {   return false;
      }
      if(!dstoreurl.equals(tmp.getdstoreurl()))
      {   return false;
      }
      if(!authmethod.equals(tmp.getauthmethod()))
      {   return false;
      }
      if(!ldapurl.equals(tmp.getldapurl()))
      {   return false;
      }
      if(!ldapbase.equals(tmp.getldapbase()))
      {   return false;
      }
      if(!ldapcate.equals(tmp.getldapcate()))
      {   return false;
      }
      if(!twtexp.equals(tmp.gettwtexp()))
      {   return false;
      }
      if(!mailSpoolResendTime.equals(tmp.getmailSpoolResendTime()))
      {   return false;
      }
      if(!mailSpoolExpiryDay.equals(tmp.getmailSpoolExpiryDay()))
      {   return false;
      }
      if(!port.equals(tmp.getport()))
      {   return false;
      }
      if(!normalTrafficTime.equals(tmp.getnormalTrafficTime()))
      {   return false;
      }
      if(!highTrafficTime.equals(tmp.gethighTrafficTime()))
      {   return false;
      }
      if(!brihServerUrl.equals(tmp.getbrihServerUrl()))
      {   return false;
      }
      else return true;
   }

}//End of class.


