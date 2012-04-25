package org.iitk.brihaspati.modules.actions;

/*
 * @(#)OnlineConfiguration.java	
 *
 *  Copyright (c) 2011 ETRG,IIT Kanpur. 
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
 *  
 */
//Java classes
import java.util.Vector;
//Apache classes
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.om.security.Role;
import org.apache.turbine.om.security.Group;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.services.security.TurbineSecurity;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.parser.ParameterParser;

import org.iitk.brihaspati.om.CoursesPeer;
//Brihaspati classes
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 **/

public class OnlineConfiguration extends VelocitySecureAction
{

					
	public boolean isAuthorized(RunData data)
	{
		boolean isAuthorized=false;
		AccessControlList acl=data.getACL();
		String courseId=data.getUser().getTemp("course_id").toString();
		if(acl.hasRole("instructor",courseId))
		{
			isAuthorized=true;
		}
		else{
			isAuthorized=false;
			data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));
		}
		return isAuthorized;
	}
   
	public void doPerform(RunData data,Context context) throws Exception
	{
		
		ParameterParser parameterparser = data.getParameters();
		String confvalue = parameterparser.getString("onlineconf");
		String courseId=data.getUser().getTemp("course_id").toString();
                if(courseId.equals("")){
                        courseId= data.getParameters().getString("courseId", "" );
                }

		int courseconf=0;
		if (confvalue.equals("onapproval"))
			courseconf=0;
		if (confvalue.equals("withoutapproval"))
			courseconf=1;
		if (confvalue.equals("notallowed"))
			courseconf=2;
	
		Criteria crit=new Criteria();
                crit.add(CoursesPeer.GROUP_NAME,courseId);
                crit.add(CoursesPeer.ONLINECONF,courseconf);
                CoursesPeer.doUpdate(crit);
	}
}

