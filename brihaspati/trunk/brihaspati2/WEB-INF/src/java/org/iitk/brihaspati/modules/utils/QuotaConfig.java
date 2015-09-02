package org.iitk.brihaspati.modules.utils;
/*
 * @(#)QuotaConfig.java  
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
import java.util.Date;
import java.text.SimpleDateFormat;
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
 * @modified date: 31-08-15 (Seemanti) ---Date of Quota Updation added as Key-Value pair---
 */

//create QuotaConfig class so as to retrieve  quotaconfig object.
   public class QuotaConfig{

   private String iquota;
   private String cquota;
   private String uquota;
    
   //This constructor 1 retrieves QuotaConfig data at Runtime!
   public QuotaConfig(String a,String b,String c)
   {
    iquota =a; 
    cquota =b; 
    uquota =c;
   }
   
   //This constructor 2 retrieves QuotaConfig data from PropertiesFile.
   public QuotaConfig(String path)
   {
   try {
      iquota = AdminProperties.getValue(path,"brihaspati.user.iquota.value");
      cquota = AdminProperties.getValue(path,"brihaspati.user.quotaCourse.value");
      uquota = AdminProperties.getValue(path,"brihaspati.user.quota.value");
    }
    catch(Exception e) {
      e.printStackTrace();
     }
   }

   //In this method we set and update the QuotaConfig Data in PropertiesFile.
   public void setQuotaConfig(String path){
      try {
      // Instantiate a Date object
      Date date = new Date(); 
      //formatting date in Java using SimpleDateFormat
      SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
      String Date = DATE_FORMAT.format(date);
      AdminProperties.setPropertyValue(path,Date,"brihaspati.user.dateOfQuotaUpdation");
      AdminProperties.setPropertyValue(path,iquota,"brihaspati.user.iquota.value");
      AdminProperties.setPropertyValue(path,cquota,"brihaspati.user.quotaCourse.value");
      AdminProperties.setPropertyValue(path,uquota,"brihaspati.user.quota.value");
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }

   public String getIquota(){
      return iquota;
   }
   public String getCquota(){
      return cquota;
   }
   public String getUquota(){
      return uquota;
   }

   //This method compares QuotaConfig data from runtime & QuotaConfigfrom PropertiesFile to return a boolean value.
   public boolean isEqual(QuotaConfig tmp){
      if( tmp == null)
         return false;
      if(!iquota.equals(tmp.getIquota()) )
         return false;
      if(!cquota.equals(tmp.getCquota()))
         return false;
      if(!uquota.equals(tmp.getUquota()))
         return false;
      else return true;

   }
}
