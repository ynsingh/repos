package org.iitk.brihaspati.modules.screens.call.Root_Admin;

/* 
 * @(#) AddAdmin.java
 *
 *  Copyright (c) 2010,2012 ETRG,IIT Kanpur.
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
 *
 */

import java.util.Vector;
import java.util.List;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Admin;
import org.iitk.brihaspati.modules.utils.DeleteInstituteUtil;

 /**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a>
 * @ Modified 12 april 2012
 */

/* Class for adding institute admin, view institute admin, delete institute admin in a institute by system admin
*/

public class AddAdmin extends SecureScreen_Admin 
{
	public void doBuildTemplate(RunData data, Context context)
	{
		try{
			/* Instantiate ParameterParser object.
			 * count use for tab color.
			 * mode is for addadmin, viewadmin.
			 * institute_id and institute_name in which institute admin is registered.
			*/
			
 			ParameterParser pp=data.getParameters();
			String count=pp.getString("count","");
			context.put("tdcolor",count);
			String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String mode = pp.getString("mode","");
			context.put("mode",mode);
			String institute_id=pp.getString("Institute_Id","");
			context.put("Institute_Id",institute_id);
			String institute_name=pp.getString("Institute_Name","");
			context.put("Institute_Name",institute_name);
			Vector instuser=new Vector();
			// Check for view institute admin in an institute.
			if(mode.equals("viewadmin"))
			{
				/* Get details of all institute admin acording to institute id in an 
				* institute
				*/
				instuser=DeleteInstituteUtil.InstAdminDetail(institute_id);
				context.put("adminlist",instuser);
			}//ifmode
		}
		catch(Exception e) { 	data.setMessage("Error in AddAdmin !!" +e);
		}
		
	}
}

