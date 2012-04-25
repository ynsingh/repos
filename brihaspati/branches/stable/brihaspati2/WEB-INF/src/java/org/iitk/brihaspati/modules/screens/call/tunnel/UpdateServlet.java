package org.iitk.brihaspati.modules.screens.call.tunnel;
/*
 * @(#UpdateServlet.java
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


public class UpdateServlet extends HttpServlet {
	private String userid, courseid;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			response.setContentType("application/x-java-serialized-object");
			HttpSession session = request.getSession(true);
			userid = (String) session.getAttribute("userid");
			courseid = (String) session.getAttribute("courseid");			

			InputStream in = request.getInputStream();
			ObjectInputStream inputFromApplet = new ObjectInputStream(in);
			Hashtable data = (Hashtable) inputFromApplet.readObject();
			
			ScoData db = ScoDataFactory.get();
			db.update(userid, courseid, data);

			OutputStream outstr = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstr);
			oos.writeObject("true");
			oos.flush();
			oos.close();
			


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR : " + e.getMessage());
		}
	}
	
}
