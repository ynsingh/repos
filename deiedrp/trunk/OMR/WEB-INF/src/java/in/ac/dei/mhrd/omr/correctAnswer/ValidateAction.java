/*
 * @(#) ValidateAction.java
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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.log4j.Logger;

/**
 * This class defines the method that displays the correct answers to the user
 * for verification MyEclipse Struts Creation date: 12-30-2010
 * 
 * @author Ashutosh
 * @version 1.0 XDoclet definition:
 * @struts.action path="/validate" name="answerForm" input="/fillAnswer.jsp"
 *                scope="request" validate="true"
 */
public class ValidateAction extends Action {

	private static Logger log = Logger.getLogger(ValidateAction.class);

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
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AnswerForm answerForm = (AnswerForm) form;// TODO Auto-generated
													// method stub

		String question;
		// question no. access to check box

		String[] checkBoxValue;
		// array for holding Check Box values as array

		int k, l, m = 1;
		int questionSequence = 1;
		// for the sequence of question for displaying

		int noSection = 0;
		// for section number

		int noQuestion = 1;
		// for question no.

		byte temp = 0;
		// temporary variable for calculating marks

		HttpSession session = request.getSession(true);

		ArrayList<Byte> byteConvert = new ArrayList<Byte>();
		// ArrayList for retrieving byte values

		ArrayList<Integer> questionNo = new ArrayList<Integer>(
				(ArrayList<Integer>) session.getAttribute("qno"));
		// ArrayList for Question no.
		System.out.println("questionNo : " + questionNo.size());
		ArrayList<Integer> sectionNo = new ArrayList<Integer>(
				(ArrayList<Integer>) session.getAttribute("secno"));
		// ArrayList for the section no.

		Iterator itr1 = questionNo.iterator();
		Iterator itr2 = sectionNo.iterator();
		Object element1;

		// converting the check box value to byte values

		while (itr1.hasNext()) {
			element1 = itr1.next();

			for (k = 1; k <= element1.hashCode(); k++) {
				temp = 0;
				question = "q" + noQuestion;

				checkBoxValue = request.getParameterValues(question);

				if (checkBoxValue != null) {
					for (l = 0; l < checkBoxValue.length; l++) {
						if (checkBoxValue[l].equalsIgnoreCase("a")) {
							temp = (byte) (temp + 1);
						} else if (checkBoxValue[l].equalsIgnoreCase("b")) {
							temp = (byte) (temp + 2);
						} else if (checkBoxValue[l].equalsIgnoreCase("c")) {
							temp = (byte) (temp + 4);
						} else if (checkBoxValue[l].equalsIgnoreCase("d")) {
							temp = (byte) (temp + 8);
						}
					}
				} else {
					temp = 0;
				}
				noQuestion++;
				byteConvert.add(temp);
			}
			noSection++;
		}
		session.setAttribute("byteTransffer", byteConvert);

		return mapping.findForward("populate");
	}
}