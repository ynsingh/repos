package org.iitk.brihaspati.modules.screens.call.Root_Admin;

/*
 * @(#)InstituteList.java	
 *
 *  Copyright (c) 2009-2012 ETRG,IIT Kanpur. 
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

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:parasharirajeev@gmail.com">Rajeev Parashari</a>
 * @modifyDate:01 March 2012(jaivir,Seema).
 */

import java.util.Vector;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminUser;
import org.iitk.brihaspati.modules.utils.CommonUtility;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.XMLWriter_InstituteRegistration;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.apache.turbine.services.servlet.TurbineServlet;
/**
*  Class for to display for pending , approved as well as rejected institute.
*/
public class InstituteList extends SecureScreen_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		try{
			
			ParameterParser pp = data.getParameters();
			String file = (String)data.getUser().getTemp("LangFile");
			/**
			*  mode for approved(1) ,pending(0) as well as rejected(2) institute list
			*  tdcolor for tab view
			*/
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String tdcolor=pp.getString("count","");
			context.put("tdcolor",tdcolor);
			String uname=(data.getUser()).getName();
			Criteria crit = new Criteria();
			String filePath=TurbineServlet.getRealPath("/InstituteRegistration");
			if(mode.equals("pendinglist")){
				Vector instdetail =XMLWriter_InstituteRegistration.ReadInstituteDeatils(filePath+"/InstituteRegistrationList.xml");
				context.put("idetail",instdetail);
			}
			if(mode.equals("approved")){
				String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
                                String conf =AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
                                int list_conf=Integer.parseInt(conf);
                                context.put("userConf",new Integer(list_conf));
                                context.put("userConf_string",conf);
				String md=pp.getString("mod","");
				String query=pp.getString("queryList","");
				String values=pp.getString("valueString","");
				List instdetail=InstituteIdUtil.searchInst(md, query, values);
				Vector vctr=new Vector(instdetail);
                                Vector vct=CommonUtility.PListing(data,context,vctr,list_conf);
				if((vct.size()==0)&&(md.equals("Search")))
					data.setMessage((MultilingualUtil.ConvertedString("instnotexist",file))+" "+query+" "+values);	
                                context.put("approved",vct);
			}			
			if(mode.equals("reject")){
				Vector instuser=XMLWriter_InstituteRegistration.ReadInstituteDeatils(filePath+"/InstituteRejectList.xml");
                        	context.put("userdetail",instuser);
			}			
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("Exception=====>"+e);}
    	}
}

