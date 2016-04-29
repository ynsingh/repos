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
import java.util.List;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.apache.turbine.util.parser.ParameterParser;

/**
 * @author <a href="mailto:sunil.singh6094@gmail.com">Sunil Kumar</a> 
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a> 
 * @modified date: 22-02-2011 (Tej)19-10-2011(Sunil)
 * @author <a href="mailto:vipulk@iitk.ac.in">Vipul Kumar Pal</a>
 */

/**
 * Loads the template page for Institute Administrator
 */
/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 * @modified date: 25-04-2016 (Seemanti);Add Max. Upload file parameter into Institute Admin's Profile. 
 *  */

//public class InstituteProfile extends SecureScreen
public class InstituteProfile extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context){
		User user = data.getUser();
		String username=user.getName();
        	int uid=UserUtil.getUID(username);
		String instituteid=user.getTemp("Institute_id").toString();
		//Get Institute name(iname)	
		String iname=InstituteIdUtil.getIstName(Integer.parseInt(instituteid));
                context.put("iname",iname);
		//set path InstituteName+Admin.properies
		String path="";	
		path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteid+"Admin.properties";
		//when properties file not exists, set given defaul value
                java.io.File f=new java.io.File(path);
                if(!f.exists()){
                        context.put("AdminConf","10");
                        context.put("CrsExp","210");
                try{
                        //get Local domain name, whose given login time
                        Criteria crit = new Criteria();
                        ParameterParser pp = data.getParameters();
                        crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,instituteid);
                        List instdetail = InstituteAdminRegistrationPeer.doSelect(crit);
                        for(int i=0;i<instdetail.size();i++)
                        {
                                InstituteAdminRegistration element=(InstituteAdminRegistration)instdetail.get(i);
                                String dName=null;
                                dName= element.getInstituteDomain();
                                context.put("dName",dName);
                                //ErrorDumpUtil.ErrorLog("Sunil=====>>>>"+instdomain);
                        }
                }catch(Exception e) {}
                        context.put("cquota","500");
                        context.put("uquota","100");
                        context.put("FaqExp","45");
                        context.put("expdays","210");
                        context.put("Max_File_upld_no","10");
                }
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

		/******************************/
//------------------------------Telephone Directory-------------------------
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
//----------------------------Telephone Directory------------------------------
        /******************************/
                 String FaqExp = AdminProperties.getValue(path,"brihaspati.admin.FaqExpiry");
                 context.put("FaqExp",new Integer(FaqExp));
		/**
		* Get user's course's expiry date
		* Add by @Tej
		*/
		String expdays = AdminProperties.getValue(path,"brihaspati.user.expdays.value");
		context.put("expdays",expdays);
                String Max_File_upld_no = AdminProperties.getValue(path,"brihaspati.user.maxFileUploadSize.value");
                //If properties file didn't had this parameter then set it equal to 10.
                if(Max_File_upld_no ==null || Max_File_upld_no.equals("") )
                {  
                   int mxUpld = 10 ;
                   String MaxFileUpld_no = Integer.toString(mxUpld);
                   AdminProperties.setPropertyValue(path,MaxFileUpld_no,"brihaspati.user.maxFileUploadSize.value");
                   context.put("Max_File_upld_no",Max_File_upld_no);
                }
                else//Otherwise fetch the existing value of this parameter from properties file.
                   context.put("Max_File_upld_no",Max_File_upld_no);
		}
		catch(Exception e) {	
			data.addMessage(MultilingualUtil.ConvertedString("brih_instadminnote",LangFile));
		}
		context.put("afname",user.getFirstName());
		context.put("alname",user.getLastName());	
		context.put("eMail",user.getEmail());	
	}
}

