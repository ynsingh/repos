package org.iitk.brihaspati.modules.screens.call.Quota_Mgmt;

/*
 * @(#)InstituteQuotaList.java	
 *
 *  Copyright (c)2011 ETRG,IIT Kanpur. 
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
import java.math.BigDecimal;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.om.InstituteQuotaPeer;
import org.iitk.brihaspati.om.InstituteQuota;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.TurbineUserPeer;
import org.apache.commons.io.FileSystemUtils;
import org.iitk.brihaspati.modules.utils.QuotaUtil;
import java.io.File;

/**
 * This class shows the List of all Institute with alloted Quota(space)
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a>
 * @modifieddate : 19-03-2013
 */

public class InstituteQuotaList extends SecureScreen_Admin
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
	public void doBuildTemplate( RunData data, Context context )
	{
		try
		{
			ParameterParser pp=data.getParameters();
			String mode=data.getParameters().getString("mode","");
			String counter=data.getParameters().getString("count","");
			String status=pp.getString("status");
			context.put("tdcolor",counter);
			context.put("mode",mode);
			String query="",valueString="";
			Vector userList=new Vector();
			Criteria crit=new Criteria();
			List list;
			List lst;
			Vector vct=new Vector();
			Vector vct1=new Vector();
			int uid[]={0,1};
			String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                        String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                        int list_conf=Integer.parseInt(conf);
                        context.put("userConf",new Integer(list_conf));
                        context.put("userConf_string",conf);

			//Calculating total and available size 
			//of brihaspati2 folder
			
			//String dir_path = TurbineServlet.getRealPath("");
			String dir_path = AdminProperties.getValue(path,"brihaspati.home.dir.value");
	                if(dir_path.equals("")){
        	               dir_path=System.getProperty("user.home");
                	}
			//ErrorDumpUtil.ErrorLog("DIRECTORY PATH= "+dir_path);
			File pdir = new File(dir_path);
			long total_size = pdir.getTotalSpace();
			total_size = total_size/1024;
			//ErrorDumpUtil.ErrorLog("TOTAL SIZE IN KB = "+total_size);	
			total_size = (total_size/1024)/1024;
			//ErrorDumpUtil.ErrorLog("TOTAL SIZE IN GB = "+total_size);
			context.put("totalsize",total_size);
			long avail_size = pdir.getFreeSpace();
			avail_size=avail_size/1024;
			//ErrorDumpUtil.ErrorLog("AVAILABLE SIZE IN KB = "+avail_size);
                        avail_size=(avail_size/1024)/1024;
			//ErrorDumpUtil.ErrorLog("AVAILABLE SIZE IN GB = "+avail_size);
			context.put("availablesize",avail_size);
			
			if(mode.equals("instlistquota"))
			{
				if((status.equals("nosearch")))
				{
					crit=new Criteria();
					crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
					crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,1);
					lst=InstituteAdminRegistrationPeer.doSelect(crit);
					vct1=new Vector(lst);
					crit=new Criteria();
					crit.addGroupByColumn(InstituteQuotaPeer.INSTITUTE_ID);
					list=InstituteQuotaPeer.doSelect(crit);
					vct=new Vector(list);
					context.put("status","nosearch");
                        	}
				else
                        	{
                                	String str="";
                                	query=pp.getString("queryList");
					ErrorDumpUtil.ErrorLog("qry at line 115===="+query);
                                	if(query.equals(""))
                                        query=pp.getString("query");
                           		valueString =StringUtil.replaceXmlSpecialCharacters(pp.getString("valueString"));
					crit=new Criteria();
                                	String table1="INSTITUTE_ADMIN_REGISTRATION";
					crit.add(table1,"INSTITUTE_NAME",(Object)("%"+valueString+"%"),crit.LIKE);
					List idlist=InstituteAdminRegistrationPeer.doSelect(crit);
					int instituteId=((InstituteAdminRegistration)idlist.get(0)).getInstituteId();	
                                        str=Integer.toString(instituteId);
                                	String table="INSTITUTE_QUOTA";
					crit=new Criteria();
					crit.add(InstituteQuotaPeer.INSTITUTE_ID,str);
                                	lst=InstituteQuotaPeer.doSelect(crit);
					vct=new Vector(lst);
					crit=new Criteria();
					crit.add(InstituteAdminRegistrationPeer.INSTITUTE_ID,str);
					List detail=InstituteAdminRegistrationPeer.doSelect(crit);
					String institutename=((InstituteAdminRegistration)detail.get(0)).getInstituteName();
					context.put("institutename",institutename);
                                	context.put("query",query);
                                	context.put("valueString",valueString);
                                	context.put("status","Search");
                        	}
                                Vector vctr= CommonUtility.PListing(data ,context ,vct,list_conf);
                                context.put("entry",vctr);
                                Vector vctr1= CommonUtility.PListing(data ,context ,vct1,list_conf);
                                context.put("entry1",vctr1);
			}
			Vector vctr=new Vector();
			List lst1;
                       	if(mode.equals("ulistquota"))
                        {
                               	if((status.equals("nosearch")))
                               	{
                                       	crit.addNotIn(TurbineUserPeer.USER_ID,uid);
                                       	crit.addGroupByColumn(TurbineUserPeer.USER_ID);
                                       	lst1=TurbineUserPeer.doSelect(crit);
                                       	vctr=new Vector(lst1);
                                       	context.put("status","nosearch");
                               	}
                               	else
                               	{
                                       	crit=new Criteria();
                                       	String str="";
                                       	query=pp.getString("queryList");
                                       	if(query.equals(""))
                                       	query=pp.getString("query");
                                       	if(query.equals("First Name"))
                                        str="FIRST_NAME";
                                        else if(query.equals("Last Name"))
                                        str="LAST_NAME";
                                        else if(query.equals("User Name"))
                                        str="LOGIN_NAME";
                                        else if(query.equals("Email"))
                                        str="EMAIL";
                                        valueString =StringUtil.replaceXmlSpecialCharacters(pp.getString("valueString"));
                                        String table="TURBINE_USER";
                                        crit.addNotIn(TurbineUserPeer.USER_ID,uid);
                                        crit.add(table,str,(Object)("%"+valueString+"%"),crit.LIKE);
                                        lst1=TurbineUserPeer.doSelect(crit);
                                        vctr=new Vector(lst1);
                                        context.put("query",query);
                                        context.put("valueString",valueString);
                                        context.put("status","Search");
                                }
                                Vector vctr1= CommonUtility.PListing(data ,context ,vctr,list_conf);
                                context.put("entry",vctr1);
			}
			if(mode.equals("edit"))
			{
				String instname=pp.getString("iname");
				String instid=pp.getString("instid");
				String instquota=pp.getString("iquota");
				context.put("iid",instid);
				context.put("name",instname);
				context.put("quota",instquota);
			}
			if(mode.equals("uedit"))
			{
				String name=pp.getString("name");
				String uid1=pp.getString("uid");
				String quota=pp.getString("quota");
				context.put("uid",uid1);
				context.put("name",name);
				context.put("quota",quota);
			}
		}
		catch(Exception ex)
		{data.setMessage("The error in getting list:"+ex);}		
	}
}


