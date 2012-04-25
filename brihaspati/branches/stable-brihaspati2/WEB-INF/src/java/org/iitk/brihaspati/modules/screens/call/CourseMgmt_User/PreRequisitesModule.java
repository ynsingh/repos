package org.iitk.brihaspati.modules.screens.call.CourseMgmt_User;

/*
 * @(#)PreRequisitesModule.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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


import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import org.iitk.brihaspati.modules.screens.call.SecureScreen;
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


/*this class contain the code for Pre-Requisites status for sco's*/

public class PreRequisitesModule extends SecureScreen  {
	
	public void doBuildTemplate(RunData data, Context context){
		try
		{
			ParameterParser pp=data.getParameters();
			pp.add("str","runscorm");
			HttpSession session = data.getSession();
			/**
                 	*Retrieve the parameters by using the ParameterParser
                 	*Putting the parameters context for use in templates.
                	*/
                	User user=data.getUser();
                	String uname=data.getUser().getName();
                	String userid=Integer.toString(UserUtil.getUID(uname));
                	context.put("tdcolor",pp.getString("count",""));
                	String Role=(String)user.getTemp("role");
                	context.put("user_role",Role);
                	String courseid=(String)user.getTemp("course_id");
                	context.put("courseid",courseid);
			//ErrorDumpUtil.ErrorLog("courseid==Presaghag=="+courseid);
	
			String sco = (String) session.getAttribute("sco_id");
			Vector preqScos = (Vector) session.getAttribute("preq_scos");
			String preqStatus = (String) session.getAttribute("preq_scos_status");
			String preqRelation = (String) session.getAttribute("preq_scos_relation");
			//System.out.println("status=" + preqStatus);
			String scoTitle = "";
			Vector preScoTitles = new Vector();
			Vector scoItems = (Vector) session.getAttribute("scoItems"); //from ScoItemServlet
			Vector scoList = (Vector) scoItems.elementAt(0);
			Hashtable scoTbl = new Hashtable();
			for( int i=0; i < scoList.size(); i++ ) {
				int cnt = 0;
				String id = "";
				for (
					StringTokenizer tokens = new StringTokenizer((String) scoList.elementAt(i), "|");
					tokens.hasMoreTokens();cnt++) {
					String token = tokens.nextToken();
					if ( cnt == 1 ) {
						id = token;
					}
					else if ( cnt == 3 ) {
						scoTbl.put(id, token);
					}

				}
			}
			//the sco titles		
			scoTitle = (String) scoTbl.get(sco);
			for ( int i=0; i<preqScos.size(); i++) {
				Hashtable h = new Hashtable();
				h.put("title", (String) scoTbl.get( (String) preqScos.elementAt(i) ));
				h.put("status", preqStatus.substring(i,i+1));
				if ( i < preqScos.size() - 1 )
					h.put("relation", preqRelation.substring(i,i+1));
				else
					h.put("relation", "");
				preScoTitles.addElement(h);
			}
			context.put("scoTitle", scoTitle);
			context.put("preScoTitles", preScoTitles);
		}//try
		catch(Exception ex){data.setMessage("The Error in PreRequisitesModule !!"+ex);}

	}	
}
