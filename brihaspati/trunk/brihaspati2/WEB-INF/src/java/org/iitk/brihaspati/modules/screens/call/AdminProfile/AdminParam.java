package org.iitk.brihaspati.modules.screens.call.AdminProfile;

/*
 * @(#)AdminParam.java	
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
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.om.security.User;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.modules.utils.UserUtil;
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:chitvesh@yahoo.com ">Chitvesh Dutta</a> 
 * @author <a href="mailto:awadhk_t@yahoo.com ">Awadhesh Kumar Trivedi</a> 
 * @author <a href="mailto:shaistashekh@hotmail.com"> Shaista </a>
 * @modified date: 17-10-2009
 * @author <a href="mailto:sharad23nov@yahoo.com ">Sharad Singhi</a> 
 * @author <a href="mailto:jaivir_singh@rediffmail.com">Jaivir Singh</a> 
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a> 
 * @modified date: 29-09-2010
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 * @modified date: 10-01-2013
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
 * @author <a href="mailto:shaistashekh@gmail.com">Shaista</a>
 * @modified date: 22-08-2013
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date: 18-05-2015 (Seemanti)
 */


/**
 * Loads the template page for Administrator
 */

public class AdminParam extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		User user = data.getUser();
                String loginName=user.getName();
                int uid=UserUtil.getUID(loginName);
		String path="";	
			path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
		String LangFile=data.getUser().getTemp("LangFile").toString();
		context.put("tdcolor",data.getParameters().getString("count",""));
		int usedport = data.getServerPort();
                context.put("usedport",usedport);

		try{
		/**
		 * getting value of configuration parameter 	
		 * @see AdminProperties in utils
		 */
		 String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
		 context.put("AdminConf",new Integer(AdminConf));
		 String CrsExp = AdminProperties.getValue(path,"brihaspati.admin.courseExpiry");
		 context.put("CrsExp",new Integer(CrsExp));
		 String PassExp = AdminProperties.getValue(path,"brihaspati.admin.passwordExpiry");
		if(StringUtils.isBlank(PassExp)){
			PassExp="180";
		}
                 context.put("PassExp",PassExp);
		 String mserv = AdminProperties.getValue(path,"brihaspati.mail.server");
		 context.put("mServer",mserv);
		 String mServerPort = AdminProperties.getValue(path,"brihaspati.mail.smtp.port");
                 context.put("mServerPort",mServerPort);
		 String smtp = AdminProperties.getValue(path,"brihaspati.mail.smtp.from");
		 context.put("mFrom",smtp);
		 String mailnm = AdminProperties.getValue(path,"brihaspati.mail.username");
		 context.put("muname",mailnm);
		 String mailpass = AdminProperties.getValue(path,"brihaspati.mail.password");
		 context.put("mPassword",mailpass);
		 String domainNm = AdminProperties.getValue(path,"brihaspati.mail.local.domain.name");
		 context.put("dName",domainNm);
		 String email = AdminProperties.getValue(path,"brihaspati.mail.email");//admin email(add in turbinr_user)
		 context.put("eMail","");
		 String iquota = AdminProperties.getValue(path,"brihaspati.user.iquota.value");
                 context.put("iquota",iquota);
		 String cquota = AdminProperties.getValue(path,"brihaspati.admin.quota.value");
		 context.put("cquota",cquota);
		 String uquota = AdminProperties.getValue(path,"brihaspati.user.quota.value");
		 context.put("uquota",uquota);
		 String hdir = AdminProperties.getValue(path,"brihaspati.home.dir.value");
		 if(hdir.equals("")){
			hdir=System.getProperty("user.home");
		 }
		 context.put("hdir",hdir);
		 String port = AdminProperties.getValue(path,"brihaspati.spring.port");
                 context.put("port",port);
		 String dstore = AdminProperties.getValue(path,"brihaspati.admin.datastore.value");
		 context.put("dstore",dstore);
		 String hdfsu = AdminProperties.getValue(path,"brihaspati.admin.hdfsurl.value");
		 context.put("hdfsurl",hdfsu);
		 String authm = AdminProperties.getValue(path,"brihaspati.admin.authmethod.value");
		 context.put("authm",authm);
		 String ldapu = AdminProperties.getValue(path,"brihaspati.admin.ldapurl.value");
		 context.put("ldapurl",ldapu);
		 String ldapb = AdminProperties.getValue(path,"brihaspati.admin.ldapbase.value");
		 context.put("ldapbase",ldapb);
		 String ldapcat = AdminProperties.getValue(path,"brihaspati.admin.ldapcate.value");
		 context.put("ldapcate",ldapcat);
		 String twtexp = AdminProperties.getValue(path,"brihaspati.admin.twtexpiry.value");
                 context.put("twtexp",twtexp);

		 String mailSpoolingExpiry = AdminProperties.getValue(path,"brihaspati.admin.mailSpoolingExpiry.value");
                 if(!StringUtils.isBlank(mailSpoolingExpiry))

                         context.put("mailSpoolingExpiry",mailSpoolingExpiry);
                else
                         context.put("mailSpoolingExpiry","3");
                String mailResendTime =  AdminProperties.getValue(path,"brihaspati.admin.spoolMailResendTime.value");
                if(!StringUtils.isBlank(mailResendTime))
                        context.put("spoolingMailResendTime", mailResendTime);
                else
                        context.put("spoolingMailResendTime","60");
		String normal_traffic = AdminProperties.getValue(path,"brihaspati.admin.normalTraffic.value");
                context.put("normalTraffic", normal_traffic);
		String high_traffic = AdminProperties.getValue(path,"brihaspati.admin.highTraffic.value");
                context.put("highTraffic", high_traffic);
//------------------------ Getting of brihaspati Server IP  --------------------
	        //String filePath = TurbineServlet.getRealPath("../../conf/BrihaspatiServer.properties");
                String filePath = TurbineServlet.getRealPath("../../webapps/brihaspati2/WEB-INF/conf/"+"/"+"Admin.properties");
		String brihServerName = AdminProperties.getValue(filePath,"brihaspati.admin.brihaspatiServerIP.value");
                ErrorDumpUtil.ErrorLog("Printing the already saved Property file value of brihServerUrl in screen file......"+brihServerName);
                if(!StringUtils.isBlank(brihServerName))
			 context.put("brihServer",brihServerName);
//------------------------ Getting of brihaspati Server IP & Port --------------------

		// --------------------------------Telephone Directory------------------
	Criteria crt=new Criteria();
        crt.add(TelephoneDirectoryPeer.USER_ID,uid);
        List Telelist=TelephoneDirectoryPeer.doSelect(crt);
        context.put("size",Telelist.size());
        try {
        for(int i=0;i<Telelist.size();i++)
        {
                TelephoneDirectory td=(TelephoneDirectory)Telelist.get(i);
		context.put("address",td.getAddress());
                context.put("state",td.getState());
                context.put("country",td.getCountry());
                context.put("department",td.getDepartment());
                context.put("designation",td.getDesignation());
                String officeno=td.getOfficeNo();
                String[] temp;
                String delimiter = "-";
                temp = officeno.split(delimiter);
                        context.put("offradio",temp[0]);
                        context.put("offprefix",temp[1]);
                        context.put("offccode",temp[2]);
                        context.put("offrcode",temp[3]);
                        context.put("offphone",temp[4]);
                String mobileno=td.getMobileNo();
                String[] mob;
                mob=mobileno.split(delimiter);
                        context.put("mobradio",mob[0]);
                        context.put("mobprefix",mob[1]);
                        context.put("mobccode",mob[2]);
                        context.put("mobrcode",mob[3]);
			context.put("mobphone",mob[4]);
                String homeno=td.getHomeNo();
                String[] home;
                home=homeno.split(delimiter);
                        context.put("homeradio",home[0]);
                        context.put("homeprefix",home[1]);
                        context.put("homeccode",home[2]);
                        context.put("homercode",home[3]);
                        context.put("homephone",home[4]);
                String otherno=td.getOtherNo();
                String[] other;
                other=otherno.split(delimiter);
                        context.put("othradio",other[0]);
                        context.put("othprefix",other[1]);
                        context.put("othccode",other[2]);
                        context.put("othrcode",other[3]);
                        context.put("othphone",other[4]);
	}
	}catch(Exception e){}
// ---------------------------Telephone Directory------------------------------------
		//----------------------------------FAQ---------------------------
                 String FaqExp = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
                 context.put("FaqExp",new Integer(FaqExp));
                 //----------------------------------FAQ---------------------------
                 String fupldsze = AdminProperties.getValue(path,"services.UploadService.size.max");
		 long newSize = Long.parseLong(fupldsze);
		 long fupldszemb = newSize/1024/1024;
		 String upldsize = Long.toString(fupldszemb);
		
                 context.put("upldsze",upldsize);

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

