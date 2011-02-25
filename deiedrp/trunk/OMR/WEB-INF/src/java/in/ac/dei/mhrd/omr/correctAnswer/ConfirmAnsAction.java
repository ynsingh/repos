/*
 * @(#) ConfirmAnsAction.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import in.ac.dei.mhrd.omr.img.CorrectAns;
import in.ac.dei.mhrd.omr.img.*;

/**
 * This class defines the method that inserts the correct answers read from the
 * scanned sheet once after user verified them.
 * 
 * MyEclipse Struts Creation date: 10-06-2010
 * 
 * @author Anshul Agarwal
 * @version 1.0 XDoclet definition:
 * @struts.action path="/confirm" name="confirmAns" input="/confirmAnsJsp.jsp"
 *                scope="request" validate="true"
 * @struts.action-forward name="index" path="/index.jsp"
 * 
 */
public class ConfirmAnsAction extends Action {
	private static Logger log = Logger.getLogger(ConfirmAnsAction.class);

	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * This method inserts the correct Answers and directs to the new jsp page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ConfirmAnsForm confirmAns = (ConfirmAnsForm) form;// TODO
															// Auto-generated
															// method stub
		int testid =Integer.parseInt(confirmAns.getTestid());
		
		/* This method returns the number of correct 
		 * answers insert into the database
		 */
		
		int i = CorrectAns.insertCorrectAns(RotateImg.correctAns, testid);
		/*
		 * Check if all answers inserted successfully
		 */
		if (i > 0) {
			request.setAttribute("confirmAnsMsg",
					"Answer Inserted Successfully");
		} else {
			log.error("correct answers couldn't be inserted");
			request.setAttribute("confirmAnsMsg", "Answer not Inserted");

		}
		return mapping.findForward("confirmAnsMsg1");
	}
}