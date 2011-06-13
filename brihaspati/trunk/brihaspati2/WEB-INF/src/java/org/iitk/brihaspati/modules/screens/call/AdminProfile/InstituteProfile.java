package org.iitk.brihaspati.modules.screens.call.AdminProfile;

/*
 * @(#)InstituteProfile.java	
 *  Copyright (c) 2010, 2011 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;

/**
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a> 
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a> 
 * @modified date: 22-02-2011 (Tej)
 */

/**
 * Loads the template page for Institute Administrator
 */

//public class InstituteProfile extends SecureScreen
public class InstituteProfile extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		User user = data.getUser();
		String instituteid=user.getTemp("Institute_id").toString();
		//Get Institute name(iname)	
		String iname=InstituteIdUtil.getIstName(Integer.parseInt(instituteid));
                context.put("iname",iname);
		//set path InstituteName+Admin.properies
		String path="";	
		path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+instituteid+"Admin.properties";
		
		String LangFile=user.getTemp("LangFile").toString();
		context.put("tdcolor",data.getParameters().getString("count",""));
		try{
		/**
		 * getting value of configuration parameter 	
		 * @see AdminProperties in utils
		 */
		 String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
		 context.put("AdminConf",new Integer(AdminConf));
		 String CrsExp = AdminProperties.getValue(path,"brihaspati.admin.courseExpiry");
		 context.put("CrsExp",new Integer(CrsExp));
		 String domainNm = AdminProperties.getValue(path,"brihaspati.mail.local.domain.name");
		 context.put("dName",domainNm);
		 String email = AdminProperties.getValue(path,"brihaspati.mail.email");
		 context.put("eMail","");
		 String cquota = AdminProperties.getValue(path,"brihaspati.admin.quota.value");
		 context.put("cquota",cquota);
		 String uquota = AdminProperties.getValue(path,"brihaspati.user.quota.value");
		 context.put("uquota",uquota);
		 String hdir = AdminProperties.getValue(path,"brihaspati.home.dir.value");
		 if(hdir.equals("")){
			hdir=System.getProperty("user.home");
		 }
		 context.put("hdir",hdir);
                 String FaqExp = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
                 context.put("FaqExp",new Integer(FaqExp));
		/**
		* Get user's course's expiry date
		* Add by @Tej
		*/
		String expdays = AdminProperties.getValue(path,"brihaspati.user.expdays.value");
		context.put("expdays",expdays);
		}
		catch(Exception e) {	
			//data.addMessage(MultilingualUtil.ConvertedString("adm_msg1",LangFile)); 
			//data.addMessage("Some Problem Occured in getting the Parameter Value"); 
			data.addMessage(MultilingualUtil.ConvertedString("brih_instadminnote",LangFile));
		}
		context.put("afname",user.getFirstName());
		context.put("alname",user.getLastName());	
		context.put("eMail",user.getEmail());	
	}
}

