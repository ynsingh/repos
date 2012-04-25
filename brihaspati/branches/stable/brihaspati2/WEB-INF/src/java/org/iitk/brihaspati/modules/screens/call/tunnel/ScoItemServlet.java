package org.iitk.brihaspati.modules.screens.call.tunnel;

/*
 * @(#)Db.java
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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import mecca.lcms.ManifestParser;
import org.iitk.brihaspati.modules.utils.ManifestParser;


public class ScoItemServlet extends HttpServlet {
	
	private Vector scoItems;
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/x-java-serialized-object");
			HttpSession session = request.getSession(true);
                     	 ServerLog.getController().Log("request.getContextPath()--->"+request.getContextPath());
			 ServerLog.getController().Log("request.getContextPath()--->"+request.getPathInfo());   
			 ServerLog.getController().Log("request.getContextPath()--->"+request.getRequestURL());   
			 ServerLog.getController().Log("request.getContextPath()--->"+request.getServletPath());   
			 ServerLog.getController().Log("request.getContextPath()--->"+request.getPathTranslated());   
			InputStream in = request.getInputStream();
			ObjectInputStream inputFromApplet = new ObjectInputStream(in);
			String courseid = (String) inputFromApplet.readObject();
			OutputStream outstr = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstr);
			//String filename = Resource.getPATH() + courseid + "/imsmanifest.xml";
			String filename = courseid + "/imsmanifest.xml";
				ServerLog.getController().Log("session ScoItemServlet. filename  "+filename);
			//System.out.println("Getting Resources from " + filename);
			if ( new File(filename).exists() ) {
				scoItems = ManifestParser.parse(filename);
				//testScoItems(scoItems);
			} else {
				scoItems = null;
			}

			//put the resources in the session object for later use
			//for example: to check the prerequisites			
			session.setAttribute("scoItems", scoItems);
				ServerLog.getController().Log("session ScoItemServlet. scoItems  "+scoItems);

			oos.writeObject(scoItems);
			oos.flush();
			oos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}	
	
	void testScoItems(Vector v) {
		Vector scoList = (Vector) v.elementAt(0);
		Vector cmiElementList = (Vector) v.elementAt(1);
		Hashtable idenHash = (Hashtable) v.elementAt(2);
		for( int i=0; i < scoList.size(); i++ ) {
			System.out.println( (String) scoList.elementAt(i));
				
		}
		for( int i=0; i < cmiElementList.size(); i++ ) {
			System.out.println( (String) cmiElementList.elementAt(i));
				
		}	
		for (Enumeration e = idenHash.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			System.out.println(key + " = " + (String) idenHash.get(key) );
		}
				ServerLog.getController().Log("session ScoItemServlet.  scoList  "+scoList);
				ServerLog.getController().Log("session ScoItemServlet.  cmiElementList  "+cmiElementList);
		
	}
	

	
}
