package org.iitk.brihaspati.modules.screens.call.Root_Admin;

/*
 * @(#)InstituteList.java	
 *
 *  Copyright (c) 2009 ETRG,IIT Kanpur. 
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
 */

import java.util.Vector;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
//import org.iitk.brihaspati.modules.screens.call.SecureScreen_RootAdmin;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;


public class InstituteList extends SecureScreen_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		try{
			ParameterParser pp = data.getParameters();
			String file = (String)data.getUser().getTemp("LangFile");
			MultilingualUtil m_u = new MultilingualUtil();
			String mode=pp.getString("mode","");
			context.put("mode",mode);
			String tdcolor=pp.getString("count","");
			context.put("tdcolor",tdcolor);
			Criteria crit = new Criteria();
			if(mode.equals("pendinglist")){
			crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
			crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"0");
			//crit.addAscendingOrderByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
			List instdetail=InstituteAdminRegistrationPeer.doSelect(crit);
			context.put("idetail",instdetail);
			}
			if(mode.equals("approved")){
				crit=new Criteria();
				//crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
				crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"1");
				List approvedlist=InstituteAdminRegistrationPeer.doSelect(crit);
				context.put("approvedlist",approvedlist);
			}			
			if(mode.equals("reject")){
				crit=new Criteria();
				//crit.addGroupByColumn(InstituteAdminRegistrationPeer.INSTITUTE_ID);
				crit.add(InstituteAdminRegistrationPeer.INSTITUTE_STATUS,"2");
				List rejectedlist=InstituteAdminRegistrationPeer.doSelect(crit);
				context.put("rejectedlist",rejectedlist);
			}			
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception=====>"+e);
			
		}
	
    	}
}

