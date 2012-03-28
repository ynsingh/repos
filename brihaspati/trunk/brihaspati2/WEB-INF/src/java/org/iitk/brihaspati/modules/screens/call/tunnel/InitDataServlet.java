package org.iitk.brihaspati.modules.screens.call.tunnel;

/*
 * @(#)InitDataServlet .java
 *
 *  Copyright (c) 2011-12 ETRG,IIT Kanpur.
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

/**This class contain the code for  Set activity
*@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/
                                                                                           

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.modules.utils.UserUtil;

public class InitDataServlet extends HttpServlet {
	private String userid = "";
	private String username = "";
	private String courseid = "";
	private String scoId = "";
	
	public InitDataServlet() {  }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/x-java-serialized-object");
			HttpSession session = request.getSession(true);

			InputStream in = request.getInputStream();
			ObjectInputStream obinput = new ObjectInputStream(in);
			Object object = obinput.readObject();			
			if ( object != null ) {
				scoId = (String) object;
			}
			userid = (String) session.getAttribute("userid");
			courseid = (String) session.getAttribute("courseid");
			OutputStream outstr = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstr);
			
			Hashtable item = prepareData();
			oos.writeObject(item);
			
			oos.flush();
			oos.close();


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	//should be changed to prepareElement()
	private Hashtable prepareData() {
		try {
			String name = "";
			//get sco data
			//ScoData should be ScoElement
			//get the data from a database
			name=UserUtil.getFullName(Integer.parseInt(userid));
			ScoData db = ScoDataFactory.get();
			Hashtable data = db.get(userid, courseid, scoId);
			//put extra information into the hashtable
			if ( data != null ) {
				//put global data
				data.put("scoId", scoId);
				data.put("cmi.core._children", putSupportedElements());
				data.put("cmi.core.student_id", userid);
				data.put("cmi.core.student_name", name);
				
				/*
				some data should be extracted from the imsmanifest file -
				these includes - adlcp:masteryscore,  adlcp:datafromlms, adlcp:prerequisites,
				adlcp:maxtimeallowed, adlcp:timelimitaction
				The APIAdapter is responsible in doing this
				*/
				data.put("cmi.launch_data", "");
				data.put("cmi.student_data.mastery_score", "");
				data.put("cmi.student_data.max_time_allowed", "");
				data.put("cmi.student_data.time_limit_action", "");				
			} else {
				System.out.println("DATA IS NULLLL");	
			}
			return data;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//mandatory supported elements by the LMS
	// :) this is hardcoded -
	private static String putSupportedElements() {
		StringBuffer sb = new StringBuffer("");
		sb.append("student_id,");
		sb.append("student_name,");
		sb.append("lesson_location,");
		sb.append("credit,");
		sb.append("lesson_status,");
		sb.append("entry,");
		sb.append("score,");
		sb.append("total_time,");
		sb.append("exit,");
		sb.append("session_time");
		return sb.toString();
	}
}
