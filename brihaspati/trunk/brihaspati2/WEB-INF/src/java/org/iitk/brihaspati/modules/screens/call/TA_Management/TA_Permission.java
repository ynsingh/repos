package org.iitk.brihaspati.modules.screens.call.TA_Management;

/*
 * @(#)TA_Permission.java 
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

import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;      
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

import org.iitk.brihaspati.om.ModulePermissionPeer;
import org.iitk.brihaspati.om.ModulePermission;
import org.iitk.brihaspati.om.CourseModulePeer;
import org.iitk.brihaspati.om.CourseModule;


	/**
	 *   This class contains code for disply all assignment
	 *   only instructor and do update/delete
	 *   @author  <a href="mail2sunil00@gmail.com">Sunil Yadav</a>
	*/

public class TA_Permission extends  SecureScreen
{
        public void doBuildTemplate(RunData data, Context context)
        {	
                try
                {
			User user=data.getUser();
                        ParameterParser pp=data.getParameters();
			context.put("coursename",(String)user.getTemp("course_name"));
                        context.put("tdcolor",pp.getString("count",""));
			int gid=GroupUtil.getGID(user.getTemp("course_id").toString());
			String inst_id=(data.getUser().getTemp("Institute_id")).toString();
			String uname = pp.getString("username","");
                        int userid=UserUtil.getUID(uname);
                        context.put("username",uname);
			
			/*
			 * Getting The ModuleId of autherise Course Module. 	
			 * Table MODULE_PERMISSION table
			 */	
				
			Criteria crit =new Criteria();
		        crit.add(ModulePermissionPeer.USER_ID,userid);
			List l2=ModulePermissionPeer.doSelect(crit);
			context.put("courseModuleList",l2);
			
			/*
                         * From COURSE_MODULE table getting all name of  
			 * Instructor Course Home module name.
                         */		
				
			Criteria crit1 = new Criteria();
			crit1.addGroupByColumn(CourseModulePeer.MODULE_ID);
			List module_name = CourseModulePeer.doSelect(crit1);
			context.put("courseModule",module_name);
			
		} catch(Exception e){data.setMessage("Error in Module_Permission.java==== !!"+e);}
		
        }
}

