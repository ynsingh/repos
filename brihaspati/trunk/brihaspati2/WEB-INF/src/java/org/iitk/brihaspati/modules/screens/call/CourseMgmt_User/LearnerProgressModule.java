package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)LearnerProgressModule.java
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
 */


import java.io.File;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

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

import org.iitk.brihaspati.modules.screens.call.tunnel.ScoData;
import org.iitk.brihaspati.modules.screens.call.tunnel.ScoDataFactory;
import org.iitk.brihaspati.modules.utils.ModuleTimeThread;
/* this class contain the code for tracking user scorm (sco) report*/

public class LearnerProgressModule extends SecureScreen {
	
	public void doBuildTemplate(RunData data, Context context )
        {
        	try{
                	ParameterParser pp=data.getParameters();
			/**
                        *Retrieve the parameters by using the ParameterParser
                        *Putting the parameters context for use in templates.
                        */
			User user=data.getUser();
			String uname=data.getUser().getName();
			context.put("tdcolor",pp.getString("count",""));
                        String Role=(String)user.getTemp("role");
                        context.put("user_role",Role);
                	String learnerid=Integer.toString(UserUtil.getUID(uname));
                	context.put("username",uname);
                	context.put("learnerid",learnerid);
                	context.put("course",data.getUser().getTemp("course_name").toString());
                	String topic=pp.getString("package","");
                	context.put("package",topic);
			String courseid=(String)user.getTemp("course_id");
                	context.put("courseid",courseid);
			Vector report = new Vector();
			/*getting the path for reading the manifest file of scorm package*/
			String filename=(TurbineServlet.getRealPath("/Courses")+"/"+courseid+"/Scormpackage"+"/"+topic+"/"+"imsmanifest.xml");
	
			if ( new File(filename).exists() ) {
				Vector container = ManifestParser.parse(filename);
				Vector v = (Vector) container.elementAt(0);
				for ( int i = 0; i < v.size(); i++ ) {
					StringTokenizer st = new StringTokenizer((String) v.elementAt(i), "|");
					String level = (String) st.nextToken(); //level
					String id = (String) st.nextToken(); // id
					String start_page = (String) st.nextToken(); //this sco start page
					String lesson_title = (String) st.nextToken(); //lesson title
					Hashtable item = new Hashtable();
					item.put("id", id);
					item.put("lesson_title",lesson_title);
					report.add(item);
				}
			}
			context.put("report",report);		
			//get student sco data from database
			ScoData db = ScoDataFactory.get();
			//NOW PREPARE THE REPORT
			for ( int i = 0; i < report.size(); i++ ) {
				Hashtable item = (Hashtable) report.elementAt(i);
				Hashtable scoData = db.get(learnerid, courseid, (String) item.get("id"));
				if ( scoData != null ) {
					if ( scoData.get("cmi.core.lesson_status") != null )
					item.put("lesson_status", scoData.get("cmi.core.lesson_status"));
					else
						item.put("lesson_status", "not attempted");
					
					if (scoData.get("cmi.core.exit") != null ) 
						item.put("exit_status", scoData.get("cmi.core.exit"));
					else 
						item.put("exit_status","");
					
					if (scoData.get("cmi.core.session_time") != null) 
						item.put("session_time", scoData.get("cmi.core.session_time"));
					else 
						item.put("session_time", "");
					
					if (scoData.get("cmi.core.entry") != null) 
						item.put("entry_status", scoData.get("cmi.core.entry"));
					else 
						item.put("entry_status", "");			
				}
				else {
					System.out.println("scodata is NULL");
					item.put("lesson_status", "not attempted");
					item.put("exit_status", "");
					item.put("session_time", "");
					item.put("entry_status", "");
				}			

			}//for
			int userid=UserUtil.getUID(uname);
			 if((Role.equals("student")) || (Role.equals("instructor")) || (Role.equals("teacher_assistant")))
                        {
				int eid=0;
                               ModuleTimeThread.getController().CourseTimeSystem(userid,eid);
                         }

	
		}//try
		catch(Exception ex){data.setMessage("The error in LearnerProgressModule !! "+ex);}

	}//method	
	//recursively delete files and directory
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}		
}//class
