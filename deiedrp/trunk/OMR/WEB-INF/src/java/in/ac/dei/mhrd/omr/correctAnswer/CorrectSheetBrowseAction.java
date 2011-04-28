/*
 * @(#) CorrectSheetBrowseAction.java
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

package in.ac.dei.mhrd.omr.correctAnswer;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.Resources;

/**
 * This class defines the method that uploads the scanned correct answer sheet,
 * reads the data from the sheet and finally deletes the sheet
 * 
 * MyEclipse Struts 
 * Creation date: 09-29-2010
 * @author Anshul
 * @version 1.0 
 * XDoclet definition:
 * @struts.action path="/correctBrowse" name="correctSheetBrowse"
 *                input="/correctAnsBrowse" validate="true"
 */
public class CorrectSheetBrowseAction extends Action {

	private static Logger log = Logger.getLogger(CorrectSheetBrowseAction.class);

	/*
	 * Generated Methods
	 */

	/**
	 * This method creates a folder named "correct"(if not created) on the
	 * server, upload the correct Answer sheet inside this folder, reads the
	 * answers marked in the sheet, and finally deletes the sheet from the
	 * server. Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CorrectSheetBrowseForm correctSheetBrowse = (CorrectSheetBrowseForm) form;// TODO
																					// Auto-generated
																					// method
																					// stub
        HttpSession hs = request.getSession(false);
        if (isCancelled(request)) {
			return mapping.findForward("fillAnswer");
		}
		int testid = SelectTestId.getTestId(correctSheetBrowse.getTest());
		byte[] correctAns;
		ArrayList<String> ansList;
		FormFile correctSheet = correctSheetBrowse.getCorrectPath();
		String fileName = correctSheet.getFileName();
		File fileToCreate = null;
		Connection con = null;
		try {

			byte[] fileData = correctSheet.getFileData();
			/*
			 * Create a folder named "correct" on the server and upload the
			 * correct answer sheet in this folder
			 */
			File fol = new File(getServlet().getServletContext().getRealPath(
					"/"), "correct");
			fol.mkdir();
			String filePath = getServlet().getServletContext().getRealPath("/")
					+ "correct";

			if (!fileName.equals("")) {

				fileToCreate = new File(filePath, fileName);
				if (!fileToCreate.exists()) {
					FileOutputStream fo = new FileOutputStream(fileToCreate);
					fo.write(correctSheet.getFileData());
					fo.flush();

					fo.close();
				}
			}

			hs.setAttribute("correctSheetMsg", "NA");

			con = Connect.prepareConnection();
			// get total number of questions in the test
			PreparedStatement ps = con
					.prepareStatement("select  Total_question from testheader where TestId = ?");
			ps.setInt(1, testid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String str = filePath + "/" + fileName;
			RotateImg correctImg = new RotateImg();
			/*
			 * This method reads the answers written on the sheet,
			 * and returns the arraylist containing the correct answers 
			 */
			correctAns = correctImg.processCorrectSheet(str, testid, (rs.getInt(1)));
			/*
			 * this method returns the arraylist containg 
			 * the correct answers in user readable form
			 */
			if(!RotateImg.flag){
			ansList = correctImg.convertToString(correctAns);

			System.out.println("anslist size ");
			request.setAttribute("confirmAnsList", ansList);
			request.setAttribute("correctAnsId", String.valueOf(testid));
			request.setAttribute("path", filePath);
			request.setAttribute("Ques", rs.getString(1));
			}else{
				hs.setAttribute("correctSheetMsg", "Improper Image format. Error in printing or scanning.");
			RotateImg.flag=false;
			return mapping.getInputForward();
			}
		} catch (Exception e) {
			log.error("Error in correct Sheet browse action " + e);
		} finally {
		
			fileToCreate.delete();

			Connect.freeConnection(con);
		}
		return mapping.findForward("confirmAns1");

	}
}