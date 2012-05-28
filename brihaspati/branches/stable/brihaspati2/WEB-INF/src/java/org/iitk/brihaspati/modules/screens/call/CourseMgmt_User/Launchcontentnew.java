package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)Launchcontentnew.java
 *
 *  Copyright (c)  2011-12 ETRG,IIT Kanpur.
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
 *@author: <a href="mailto:seema_020504@yahoo.com">Manorama Pal</a>
 *@author: <a href="mailto:Kishore.shukla@gmail.com">Kishorekumar shukla</a>
 * @modified date:09-12-2011
 */

import java.io.File;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.MailNotificationThread;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * This class contains code for launch scorm content
 */

public class Launchcontentnew extends SecureScreen
{
        public void doBuildTemplate(RunData data, Context context )
        {
                try{
                        ParameterParser pp=data.getParameters();
			/*
                         * sending the varible to default.vm
                         */
                        pp.add("str","runscorm");
			/*
	       		 *getting the servername and portno
			 */
                        String Servername=data.getServerName();
                        int Portno=data.getServerPort();
			String hostIP=data.getServerName();
                	String codeBase=data.getServerScheme()+"://"+hostIP+":"+data.getServerPort()+data.getContextPath()+"/ScormPlayer/";
                	String Baseurl=data.getServerScheme()+"://"+hostIP+":"+data.getServerPort()+data.getContextPath()+"/";
                	context.put("serverName",hostIP);
                	context.put("Portno",Portno);
                	context.put("codeBase",codeBase);
                	context.put("Baseurl",Baseurl);
			User user=data.getUser();
                	String courseid=(String)user.getTemp("course_id");
                	context.put("courseid",courseid);
			String role=(String)user.getTemp("role");
                	context.put("user_role",role);

                	/**
                	* Get UserName, Passwd, CourseId,Scorm package path
			* put in the context for the use in templates
                	*/
                	String uname=data.getUser().getName();
                	String userid=Integer.toString(UserUtil.getUID(uname));
                	context.put("username",uname);
                	context.put("userid",userid);
                	context.put("course",data.getUser().getTemp("course_name").toString());
                	String topic=pp.getString("package","");
                	context.put("package",topic);
                	File scormDir1=new File(TurbineServlet.getRealPath("/Courses")+"/"+courseid+"/Scormpackage/"+topic);
                	context.put("scormDir1",scormDir1);
			//---------------------------end---setting userid and course id for the applet----------------------------------------------
                        HttpSession session = data.getSession();
			session.setAttribute("courseid",courseid);
			session.setAttribute("userid",userid);
			java.awt.Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			//ErrorDumpUtil.ErrorLog("dim===="+dim);
			context.put("dim",dim);
                        //frame.setSize(700, 320);
                        //frame.setLocation((((int)dim.getWidth()/2)-350),(((int)dim.getHeight()/2)-160));
 			 if((role.equals("student")) || (role.equals("instructor")))
                        {
                                MailNotificationThread.getController().CourseTimeSystem(Integer.parseInt(userid));
                         }


                }//try
                catch(Exception e)
                {
                        String msg="Error in Launchcontent !!"+ e;
                        ErrorDumpUtil.ErrorLog(msg);

                }
 	}//dobuild
}
