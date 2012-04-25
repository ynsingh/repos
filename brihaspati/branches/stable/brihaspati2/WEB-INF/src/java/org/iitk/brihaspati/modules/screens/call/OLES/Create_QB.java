package org.iitk.brihaspati.modules.screens.call.OLES;

/*
 * @(#)Create_QB.java	
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

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.commons.fileupload.FileItem;

import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

/**
* This class manage all online examination system 
* @author <a href="mailto:palseema30@gmail.com">Manorama Pal</a>
*/

public class Create_QB extends SecureScreen{
	//VelocityScreen vs=new VelocityScreen();
	public void doBuildTemplate(RunData data,Context context) 
	{
		try{

			User user=data.getUser();
			ParameterParser pp=data.getParameters();
			context.put("tdcolor",pp.getString("count","1"));
			context.put("course",(String)user.getTemp("course_name"));
			String mode =pp.getString("mode","");
			context.put("mode",mode);
			String topic=pp.getString("Topicname","");
			context.put("Topicname",topic);
			String typeques=pp.getString("typeques","");
			context.put("typeques",typeques);
			String Questype=pp.getString("valQuestype","");
			context.put("valQuestype",Questype);
			String difflevel=pp.getString("valdifflevel","");
			context.put("valdifflevel",difflevel);
			String status=pp.getString("status","");
                	context.put("status",status);
			String addques=pp.getString("addques","");
                	context.put("addques",addques);
			String Role = (String)user.getTemp("role");
			/**
                         *Time calculaion for how long user use this page.
                         */
                         int uid=UserUtil.getUID(user.getName());
                         if((Role.equals("student")) || (Role.equals("instructor")))
                         {
                                CourseTimeUtil.getCalculation(uid);
                                ModuleTimeUtil.getModuleCalculation(uid);
                         }

			//ErrorDumpUtil.ErrorLog("\n screenQuestype======"+addques+"\ntopic====="+topic);
		}//try
		catch(Exception ex)
		{
		data.setMessage("The error in Create_QB !! "+ex);
		}
	}
}

