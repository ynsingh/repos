package org.iitk.brihaspati.modules.screens;

/*
 * @(#)ViewInstituteList.java	
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur. 
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */

import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.DeleteInstituteUtil;
import org.iitk.brihaspati.modules.utils.InstituteFileEntry;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.apache.turbine.services.servlet.TurbineServlet;

/**
* Class for to display for list of approved institute.
* @author <a href="nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
* @author <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>21102010,09may2012,23may2012
* @author <a href="palseema@rediffmail.com">Manorama Pal</a>09may2012,23may2012
* @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
*/

public class ViewInstituteList extends VelocityScreen
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		try{
			ParameterParser pp=data.getParameters();
			/* Get the Language parameter */
                        String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String file = MultilingualUtil.LanguageSelectionForScreenMessage(lang);
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String query=pp.getString("queryList","");
			context.put("query",query);
			String valueString = StringUtil.replaceXmlSpecialCharacters(pp.getString("valueString",""));
			context.put("valueString",valueString);
			/**
			  *Call InstAdminDetail to display the Institute list with the Institute Admin
			  */
			Vector iidvector=InstAdminDetail(mode, query, valueString);
			//for page listing
			context.put("iidvector1",iidvector);
			String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                        String adminemail =AdminProperties.getValue(path,"brihaspati.mail.email");
			context.put("adminemail",adminemail);
                        int list_conf=Integer.parseInt(conf);
                        context.put("userConf",new Integer(list_conf));
                        context.put("userConf_string",conf);
			Vector vctr1=CommonUtility.PListing(data ,context ,iidvector,list_conf);
			if((vctr1.size()==0)&&(mode.equals("Search")))
                        data.setMessage((MultilingualUtil.ConvertedString("instnotexist",file))+" "+query+" "+valueString);
			context.put("entry1",vctr1);
			context.put("institutenum",iidvector.size());
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in View Institute List =====>"+e);
		}
    	}

	/**
	  *In this method set the value of Institute details. 
	  */		
	public static Vector InstAdminDetail(String mode, String query, String valueString){
                Vector instuser=new Vector();
                try{
                        Criteria crit=new Criteria();
			List instdetail=InstituteIdUtil.searchInst(mode, query, valueString);
			for(int i=0;i<instdetail.size();i++){
                                InstituteAdminRegistration iar=(InstituteAdminRegistration)instdetail.get(i);
                                int iid=iar.getInstituteId();
                                String Admaddress =iar.getInstiuteAddress();
                                String InstName=iar.getInstituteName();
                                String Instcity=iar.getCity();
                        	crit=new Criteria();
                        	crit.add(InstituteAdminUserPeer.INSTITUTE_ID,iid);
                        	List admindetail=InstituteAdminUserPeer.doSelect(crit);
                               	InstituteFileEntry InstfileEntry=new InstituteFileEntry();
				if(admindetail.size()!=0)
				{
                               		InstituteAdminUser instadminuser=(InstituteAdminUser)admindetail.get(0);
                               		String email=instadminuser.getAdminUname();
                               		crit=new Criteria();
                               		crit.add(org.iitk.brihaspati.om.TurbineUserPeer.LOGIN_NAME,email);
                               		List tulist=org.iitk.brihaspati.om.TurbineUserPeer.doSelect(crit);
                               		org.iitk.brihaspati.om.TurbineUser udetail=(org.iitk.brihaspati.om.TurbineUser)tulist.get(0);
                               		String fname=udetail.getFirstName();
                               		String lname=udetail.getLastName();
                               		String temail=udetail.getEmail();
                               		String uname=udetail.getLoginName();
					if(admindetail.size()==1)
                                	{
                               			InstfileEntry.setID(iid);
                                		InstfileEntry.setInstituteAddress(Admaddress);
                                		InstfileEntry.setInstituteName(InstName);
                                		InstfileEntry.setInstituteCity(Instcity);
                                		InstfileEntry.setInstituteFName(fname);
                                		InstfileEntry.setInstituteLName(lname);
                                		InstfileEntry.setInstituteEmail(temail);
                                		InstfileEntry.setInstituteUserName(uname);
                                		instuser.add(InstfileEntry);
                        		}
					else
					{
						InstfileEntry.setID(iid);
                                        	InstfileEntry.setInstituteAddress(Admaddress);
                                        	InstfileEntry.setInstituteName(InstName);
                                        	InstfileEntry.setInstituteCity(Instcity);
                                        	InstfileEntry.setInstituteEmail(temail);
                                        	InstfileEntry.setInstituteUserName("checkname");
                                        	instuser.add(InstfileEntry);
					}
				}//if
				else{
						InstfileEntry.setID(iid);
                                                InstfileEntry.setInstituteAddress(Admaddress);
                                                InstfileEntry.setInstituteName(InstName);
                                                InstfileEntry.setInstituteCity(Instcity);
                                        	InstfileEntry.setInstituteUserName("noAdminExist");
                                        	instuser.add(InstfileEntry);

				}//else
			}//for
                }//try
                catch (Exception e){ErrorDumpUtil.ErrorLog("Error: ViewInstituteList(InstAdminDetail) method" + e);}
                return instuser;
	}//method
}
