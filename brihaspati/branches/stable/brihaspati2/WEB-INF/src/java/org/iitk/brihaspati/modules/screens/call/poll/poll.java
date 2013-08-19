package org.iitk.brihaspati.modules.screens.call.poll;
/*
 * @(#)poll.java       
 *
 *  Copyright (c) 2012,2013 ETRG,IIT Kanpur. 
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
import org.iitk.brihaspati.om.InstituteAdminUserPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.apache.torque.util.Criteria;
import java.util.List;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
/**
 * 
 *This class contain the code for the poll. 
 *  @modified: <a href="mailto:piyushm45@gmail.com">Piyush Mishra</a>
 */

public class poll extends SecureScreen_Institute_Admin{
	/**
        * Place all the data object in the context
        * for use in the template.
        */
	public void doBuildTemplate(RunData data,Context context){ 
                try{ 
			User user=data.getUser();
                        ParameterParser pp = data.getParameters();
			/**
                        * Get Username,FirstName for the user currently logged in
                        *Put it in the context for Using in templates
                        */
			String username=user.getName();
			//String first_Name=user.getFirstName();
                        //context.put("first_Name", first_Name);
			/**
                        * Get institute Id
                        */
			String instituteId=(String)user.getTemp("Institute_id");
                        context.put("i",instituteId);
			/**
                        * Get user id
                        */
                        int Id=UserUtil.getUID(username);
                        context.put("id",Id);
			/**
                        * set path to get the list size configure by admin.
                        */
			String path="";
			path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteId+"Admin.properties";
			String AdminConf = AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value");
			context.put("AdminConf",new Integer(AdminConf));
			/**
                        * do mode for compose and view page.
                        */
			String do_Survey=data.getParameters().getString("do");
			 context.put("do",do_Survey);
			String iMode=data.getParameters().getString("inst_Mode");
			context.put("iMode",iMode);
			/**
                        * Get ip, port and scheme
                        */
			String ip_Add=data.getServerName();
			int port=data.getServerPort();//request.getParameter("port");
			String sch=data.getServerScheme();
			String ipadd=sch+"://"+ip_Add+":"+port;
			context.put("ipadd",ipadd);

                }catch(Exception e){}
        }
}

