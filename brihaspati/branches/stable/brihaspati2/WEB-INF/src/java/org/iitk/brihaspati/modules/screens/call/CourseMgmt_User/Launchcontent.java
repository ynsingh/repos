package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#) Launchcontent.java
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
 *This class contains code for Creating a group
 *@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
 *@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ManifestParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseTimeUtil;
import org.iitk.brihaspati.modules.utils.ModuleTimeUtil;

public class Launchcontent extends SecureScreen
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
    public void doBuildTemplate( RunData data, Context context )

    {
	  try{
		User user=data.getUser();
                ParameterParser pp=data.getParameters();
		String course_id=(String)user.getTemp("course_id");
                context.put("courseid",course_id);
		String role=(String)user.getTemp("role");
                context.put("user_role",role);

		/**
		* Get UserName, Passwd, CourseId, serverName and Base Path
		*/
          	String uname=data.getUser().getName();
	  	String pword=data.getUser().getPassword();
	  	String cid=data.getUser().getTemp("course_id").toString();
	  	String hostIP=data.getServerName();
	  	String codeBase=data.getServerScheme()+"://"+hostIP+":"+data.getServerPort()+data.getContextPath()+"/scorm/";
	  	context.put("serverName",hostIP);
	  	context.put("codeBase",codeBase);
	  	context.put("password",pword);
	  	context.put("username",uname);
	  	context.put("course",data.getUser().getTemp("course_name").toString());
                String topic=pp.getString("package","");
                context.put("package",topic);
                File scormDir1=new File(TurbineServlet.getRealPath("/Courses")+"/"+cid+"/Scormpackage/"+topic);
                context.put("scormDir1",scormDir1);
                Vector Allvalue=new Vector();
                Vector report=new Vector();
                String level="",id="",start_page="",lesson_title="";
                String scormDir=(TurbineServlet.getRealPath("/Courses")+"/"+cid+"/Scormpackage"+"/"+topic+"/"+"imsmanifest.xml");
                Allvalue=ManifestParser.parse(scormDir);
                Vector v = (Vector) Allvalue.elementAt(0);
                for ( int i = 0; i < v.size(); i++ ) {
                StringTokenizer st = new StringTokenizer((String) v.elementAt(i), "|");
                level = (String) st.nextToken(); //level
                id = (String) st.nextToken(); // id
                start_page = (String) st.nextToken(); //this sco start page
                lesson_title = (String) st.nextToken(); //lesson title
                Hashtable item = new Hashtable();
                item.put("id", id);
                item.put("lesson_title",lesson_title);
                item.put("start_page",start_page);
                report.add(item);
                }
                context.put("report",report);
		 /*
                  *method for how much time user spend in this page.
                  */
 		int uid=UserUtil.getUID(uname);
                if((role.equals("student")) || (role.equals("instructor")))
                {
                           CourseTimeUtil.getCalculation(uid);
                           ModuleTimeUtil.getModuleCalculation(uid);
                }

		}
	catch(Exception ex)
	{
		data.setMessage("The error in Launchcontent screen !!"+ex);
	}
          
    }
}

