package org.iitk.brihaspati.modules.screens.call.tunnel;

/*
 * @(#)ElementServlet.java
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
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ElementServlet extends HttpServlet { 
	
	private Vector elements;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/x-java-serialized-object");
			HttpSession session = request.getSession(true);
			//ServerLog.getController().Log("session ElementServlet "+session);

			InputStream in = request.getInputStream();
			ObjectInputStream obinput = new ObjectInputStream(in);
			String courseid = (String) obinput.readObject();
			//	ServerLog.getController().Log("session ElementServlet courseid "+courseid);
			OutputStream outstr = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstr);
			
			
			Vector readonlys = new Vector();
			readonlys.addElement("cmi.core._children");
			readonlys.addElement("cmi.core.student_id"); 
			readonlys.addElement("cmi.core.student_name");
			readonlys.addElement("cmi.core.credit");
			readonlys.addElement("cmi.core.entry");
			readonlys.addElement("cmi.core.score._children");
			readonlys.addElement("cmi.core.total_time");
			readonlys.addElement("cmi.core.lesson_mode");
			readonlys.addElement("cmi.launch_data");
			//	ServerLog.getController().Log("session ElementServlet readonlys  "+readonlys);
			
			
			Vector writeonlys = new Vector();
			writeonlys.addElement("cmi.core.exit");
			writeonlys.addElement("cmi.core.session_time");
			//	ServerLog.getController().Log("session ElementServlet  writeonlys"+writeonlys);
			
			oos.writeObject(elements);
			oos.flush();
			oos.close();
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
}
