/*
 * @(#) DownloadSheetAction.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr.download;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This classs defines the method that provides the utility to download the OMR
 * sheet template
 * 
 * MyEclipse Struts Creation date: 12-07-2010
 * 
 * @author Anshul Agarwal
 * @version 1.0 XDoclet definition:
 * @struts.action input="/Menu.jsp" validate="true"
 * @struts.action-forward name="home" path="/Home.jsp"
 */
public class DowLoadSheetAction extends Action {

	private static Logger log = Logger.getLogger(DowLoadSheetAction.class);

	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws ServletException
	 * @throws IOException
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String base = "OMRSheetTemplate.pdf";// default name of the file
		int index = base.lastIndexOf(".");
		int length = base.length();

		String extension = base.substring(index, length); // extract the
															// extension of the
															// file
		String parent = "OMRSheetTemplate";
		String filename = parent + extension;

		/*
		 * location where the file resides
		 */
		String filepath = getServlet().getServletContext().getRealPath("/")
				+ "OMRSheet" + "//" + base;

		BufferedInputStream buf = null;
		ServletOutputStream myOut = null;

		try {

			myOut = response.getOutputStream();
			File myfile = new File(filepath);
			// define the format of the file to be downloaded
			// set response headers
			response.setContentType("application/pdf");

			response.addHeader("Content-Disposition", "attachment; filename="
					+ filename);

			response.setContentLength((int) myfile.length());

			FileInputStream input = new FileInputStream(myfile);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1)
				myOut.write(readBytes);

		} catch (IOException ioe) {
			log.error("Cannot download the file : " + ioe);
			throw new ServletException(ioe.getMessage());

		} finally {

			// close the input/output streams
			if (myOut != null)
				myOut.close();
			if (buf != null)
				buf.close();

		}
		return mapping.findForward("home");

	}
}