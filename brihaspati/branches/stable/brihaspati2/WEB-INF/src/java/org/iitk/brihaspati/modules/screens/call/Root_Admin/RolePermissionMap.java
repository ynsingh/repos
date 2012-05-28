package org.iitk.brihaspati.modules.screens.call.Root_Admin;

/*
 * @(#)RolePermissionMap.java	
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


import java.util.Vector;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.TurbineRolePermissionPeer;
import org.iitk.brihaspati.om.TurbineRolePermission;
import org.iitk.brihaspati.om.TurbinePermissionPeer;
import org.iitk.brihaspati.om.TurbinePermission;
import org.iitk.brihaspati.om.TurbineRolePeer;
import org.iitk.brihaspati.om.TurbineRole;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
//import org.iitk.brihaspati.modules.screens.call.SecureScreen_RootAdmin;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.apache.turbine.services.security.TurbineSecurity;
/**
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a>
 */

public class RolePermissionMap extends SecureScreen_Admin
{
	public void doBuildTemplate( RunData data, Context context )
    	{
		try{
			ParameterParser pp = data.getParameters();
			String file = (String)data.getUser().getTemp("LangFile");
			MultilingualUtil m_u = new MultilingualUtil();
			CourseUserDetail rDetail= new CourseUserDetail();	
			String mode=pp.getString("mode","all");
			context.put("mode",mode);
			String tdcolor=pp.getString("count","1");
			context.put("tdcolor",tdcolor);
			Criteria crit = new Criteria();
			String rp=null;
			Vector u=new Vector();
			Vector li=new Vector();
			List lp=null;
			List roleDetail=null;
			if((mode.equals("all"))||(mode.equals(""))||(mode.equals("map"))){
					//	ErrorDumpUtil.ErrorLog("The value in Stirng is(role permission screen)1 "+rp);
				crit.addAscendingOrderByColumn(TurbineRolePeer.ROLE_ID);
				roleDetail=TurbineRolePeer.doSelect(crit);
				for(int i=0;i<roleDetail.size();i++){
					rp="";
					int rid=((TurbineRole)roleDetail.get(i)).getRoleId();
					String rname=((TurbineRole)roleDetail.get(i)).getRoleName();
					crit =new Criteria();
					crit.add(TurbineRolePermissionPeer.ROLE_ID,rid);
					List permDetail=TurbineRolePermissionPeer.doSelect(crit);
					for(int j=0;j<permDetail.size();j++){
						int permid=((TurbineRolePermission)permDetail.get(j)).getPermissionId();
						Criteria crit1 =new Criteria();
						crit1.add(TurbinePermissionPeer.PERMISSION_ID,permid);
	                                        List permName=TurbinePermissionPeer.doSelect(crit1);
						String pname=((TurbinePermission)permName.get(0)).getPermissionName();
						rp=rp+pname+",";
					}
					u.add(rp);
					li.add(rname);
				}
				context.put("info2",roleDetail);
				crit =new Criteria();
				crit.addAscendingOrderByColumn(TurbinePermissionPeer.PERMISSION_ID);
	                        List perm=TurbinePermissionPeer.doSelect(crit);
				context.put("info",u);
				context.put("perm",perm);
			}
			if(mode.equals("map")){
				String crlid=pp.getString("rlid","");
	                        context.put("rlid",crlid);
				String crlnme=pp.getString("rlnme","");
                	        context.put("rlnme",crlnme);
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception in Role Permission Mapping java {screen} =====>"+e);
			
		}
	
    	}
}

