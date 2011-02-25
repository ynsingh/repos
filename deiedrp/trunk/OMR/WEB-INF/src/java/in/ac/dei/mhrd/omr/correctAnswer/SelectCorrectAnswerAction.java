/*
 * @(#) SelectCorrectAnswerAction.java
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import in.ac.dei.mhrd.omr.SelectTestId;
import in.ac.dei.mhrd.omr.dbConnection.Connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

/**
 * This class fetches sectionwise detail of the testname, selected by user for
 * uploading correct Answers, from the database.
 * 
 * MyEclipse Struts Creation date: 12-21-2010
 * 
 * @author Anshul Agarwal 
 * @version 1.0
 * XDoclet definition:
 * @struts.action path="/selectCorrect" name="selectCorrectAns"
 *                input="/SelectCorrectAnsJsp.jsp" scope="request"
 *                validate="true"
 * @struts.action-forward name="fillCorrectAns" path="/fllAnswer.jsp"
 * 
 * 
 */
public class SelectCorrectAnswerAction extends Action {

	private static Logger log = Logger
			.getLogger(SelectCorrectAnswerAction.class);

	/*
	 * Generated Methods
	 */

	/**
	 * This method fetches the testname selected by the user, and then retrieves
	 * the section detail from the database
	 * 
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SelectCorrectAnsForm selectCorrectAns = (SelectCorrectAnsForm) form;// TODO
																			// Auto-generated
																			// method
																			// stub
		HttpSession hs = request.getSession(true);

		if (isCancelled(request)) {
			return mapping.findForward("home");
		}
		hs.setAttribute("testName", selectCorrectAns.getTestName());
		Connection con = null;
		PreparedStatement psSectionDetail = null;
		ResultSet rsTestSecDetail = null;
		int testid = 0;
		int i;
		int questionNo = 1;
		// displaying question no.

		ArrayList<Integer> quesNo = new ArrayList<Integer>();
		// ArrayList of integer for question no

		ArrayList<Integer> sectionNo = new ArrayList<Integer>();
		// ArrayList of Section no.

		testid = SelectTestId.getTestId((String) hs.getAttribute("testName"));

		try {
			// establishes connection with the database
			con = Connect.prepareConnection();

			// retrieves section and No. of questions in each section from the
			// testsectiondetail
			psSectionDetail = con
					.prepareStatement("select Section_number, No_of_question from testsectiondetail where TestId=?");
			psSectionDetail.setInt(1, testid);

			rsTestSecDetail = psSectionDetail.executeQuery();

			// storing the question no. to session attribute to display number
			// of checkboxes accordingly.

			hs.setAttribute("qno", quesNo);
			// storing the question no. to session attribute to display section
			// number accordingly.

			hs.setAttribute("secno", sectionNo);
			// storing the section no. arraylist to session attribute
			hs.setAttribute("testid", testid);

			while (rsTestSecDetail.next()) {
				quesNo.add(rsTestSecDetail.getInt(2));
				sectionNo.add(rsTestSecDetail.getInt(1));
			}
		} catch (Exception e) {

			log.error("Exception in select Ans " + e);
			// TODO: handle exception
		} finally {
			//free database connection
			Connect.freeConnection(con);
		}

		return mapping.findForward("fillCorrectAns");
	}
}