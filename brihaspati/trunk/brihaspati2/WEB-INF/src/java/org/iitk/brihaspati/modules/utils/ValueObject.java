package org.iitk.brihaspati.modules.utils;
/*
 * @(#)ValueObject.java  
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
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date: 31-08-15 (Seemanti)---Retrieval of Runtime Proxy Parameters added---
 */

public class ValueObject{
   
   MailAuth mailauth; 
   TelObject telobj;   
   UserInfo userinfo;
   TurbineConfig turbineconfig;
   QuotaConfig quotaconfig;
   AdminConfig adminconfig;
   private boolean debug=false;

   public ValueObject(RunData data){

      CreateMailAuth(data);
      getMailAuth();
      CreateQuotaConfig(data);
      getQuotaConfig();
      CreateTelObject(data);
      CreateTurbineConfig(data);
      getTurbineConfig();
      CreateUserInfo(data);
      getUserInfo();
      CreateAdminConfig(data);
      getAdminConfig();        
      getTelObject();
   }

   public void CreateMailAuth(RunData data){
      ParameterParser pp=data.getParameters();
      String a, b, c, d, e, f;
      a = pp.getString("mailServ","");
      b = pp.getString("mailServPort","");
      c = pp.getString("mailFrom","");
      d = pp.getString("muName","");
      e = pp.getString("mPass","");
      f = pp.getString("mailDomain","");
      mailauth = new MailAuth(a, b, c, d, e, f);
   }
   public MailAuth getMailAuth() {
       if(debug) ErrorDumpUtil.ErrorLog("PRINT THE VALUE OF domain name in value object class!!!!!!!!!!!"+mailauth.getDomainNM());
      return mailauth;
   }

   public void CreateQuotaConfig(RunData data){
   	ParameterParser pp =data.getParameters();
 	String a,b,c;
	a=pp.getString("iquota","");
	b=pp.getString("cquota","");
	c=pp.getString("uquota","");
	quotaconfig = new QuotaConfig(a,b,c);
   }
   public QuotaConfig getQuotaConfig() {
         return quotaconfig;
   }
  
    public void CreateAdminConfig(RunData data) {
      ParameterParser pp = data.getParameters();
      String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v;
      a = pp.getString("AdminConf","");
      b = pp.getString("AdminCrsExp","");
      c = pp.getString("AdminPassExp","");
      d =pp.getString("AdminFaqExp","");
      e = pp.getString("hdir","");
      f =pp.getString("datastorage","Local");
      g =pp.getString("hdfsurl","");
      h =pp.getString("authmethod","Local");
      i =pp.getString("ldapurl","");
      j =pp.getString("ldapbase","");
      k =pp.getString("ldapcate","");
      l =pp.getString("TweetExp","30");
      m = pp.getString("spoolMailResendTime","");
      n = pp.getString("mailSpoolingExpiry","");
      o = pp.getString("normalTraffic");
      p = pp.getString("highTraffic");
      q = pp.getString("brihServerUrl");
      r = pp.getString("port","");
      s = pp.getString("proxyIpAdd","");
      t = pp.getString("proxyPort","");
      u = pp.getString("proxyUsrnm","");
      v = pp.getString("proxyPwd","");
      ErrorDumpUtil.ErrorLog("print___________++++++++++++:::::::::::::"+s);
      adminconfig= new AdminConfig(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v);
   }
   public AdminConfig getAdminConfig() {
         return adminconfig;
   }


   public void CreateUserInfo(RunData data) {
      ParameterParser pp = data.getParameters();
      String a,b,c;
      a=pp.getString("AFName","");
      b=pp.getString("ALName","");
      c=pp.getString("eMail","");
      userinfo = new UserInfo(a,b,c);
   }
   public UserInfo getUserInfo() {
       return userinfo;
   }


   public void CreateTurbineConfig(RunData data) {
      ParameterParser pp = data.getParameters();
      String a;
      a=pp.getString("upldsze","");
      turbineconfig = new TurbineConfig(a);
   }
   public TurbineConfig getTurbineConfig() {
      return turbineconfig ;
   }

   public void CreateTelObject(RunData data)
    {
      //retrieve the runtime telephone data.  
      ParameterParser pp=data.getParameters();
      String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o;
      a = pp.getString("eMail","");   
      //b = pp.getString("Name","");
      c = pp.getString("Address","");
      d = pp.getString("State","");
      e = pp.getString("Country","");
      f = pp.getString("Department","");
      g = pp.getString("Designation","");
      h =pp.getString("Offprefix","x")+"-"+pp.getString("Offccode","x")+"-"+pp.getString("Offrcode","x")+"-"+pp.getString("Offphnumber","x");
      i =pp.getString("Mobprefix","x")+"-"+pp.getString("Mobccode","x")+"-"+pp.getString("Mobrcode","x")+"-"+pp.getString("Mobphnumber","x");
      j =pp.getString("Homeprefix","x")+"-"+pp.getString("Homeccode","x")+"-"+pp.getString("Homercode","x")+"-"+pp.getString("Homephnumber","x");
      k =pp.getString("Othprefix","x")+"-"+pp.getString("Othccode","x")+"-"+pp.getString("Othrcode","x")+"-"+pp.getString("Othphnumber","x");
      l =pp.getString("Offdirectory","");
      m =pp.getString("Mobdirectory","");
      n =pp.getString("Homedirectory","");
      o =pp.getString("Othdirectory","");
      telobj = new TelObject(a,c,d,e,f,g,h,i,j,k,l,m,n,o);                                                      
   }
   public TelObject getTelObject() {
         return telobj ;
   }   
   
}//End of class.

