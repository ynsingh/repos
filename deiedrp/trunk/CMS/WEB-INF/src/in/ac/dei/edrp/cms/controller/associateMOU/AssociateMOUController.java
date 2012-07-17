/**
 * @(#) AssociateMOUdaoImpl.java
 * Author :Arush Kumar
 * Date :21/3/2011
 * Version 1.0
 * 
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
 * Redistribution in binary form must reproducuce the above copyright
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
package in.ac.dei.edrp.cms.controller.associateMOU;

import in.ac.dei.edrp.cms.dao.associateMOU.AssociateMOUDAO;
import in.ac.dei.edrp.cms.domain.associateMOU.AssociateMOU;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Associate MOU
 * 
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class AssociateMOUController extends MultiActionController {

	/** creating object of AssociateMOUDAO interface */
	private AssociateMOUDAO associateMOUDAO;

	/**
	 * The setter method of the interface associated with this controller
	 * 
	 * @param buildActivityMaster
	 */
	public void setAssociateMOUDAO(AssociateMOUDAO associateMOUDAO) {
		this.associateMOUDAO = associateMOUDAO;
	}

	/**
	 * This method is used for getting list of university and mapping to jsp
	 * file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing university list
	 */
	public ModelAndView getUniversityList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		AssociateMOU associateMOU = new AssociateMOU();
		associateMOU.setUniversityId(session.getAttribute("universityId")
				.toString());

		List<AssociateMOU> univlist = associateMOUDAO
				.getUniversityList(associateMOU);
		return new ModelAndView("associateMOU/AssociateMOU", "univlist",
				univlist);
	}

	/**
	 * This method is used for set mou university
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing insert operation result
	 */
	public ModelAndView setMouDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		AssociateMOU associateMOU = new AssociateMOU();

		associateMOU.setUniversityId(request.getParameter("id"));
		associateMOU.setMouId(request.getParameter("mouid"));
		associateMOU.setCreatorId(session.getAttribute("userId").toString());
		boolean added = associateMOUDAO.setMouDetails(associateMOU);

		String result;
		if (added) {
			result = "P";
		} else {
			result = "F";
		}

		return new ModelAndView("associateMOU/result", "res", result);

	}

	/**
	 * This method is used for getting default university Name and mapping to
	 * jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing university Name
	 */
	public ModelAndView getDefault(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		AssociateMOU associateMOU = new AssociateMOU();

		associateMOU.setUniversityId(session.getAttribute("universityId")
				.toString());
		associateMOU.setUniversityName(session.getAttribute("universityName")
				.toString());

		return new ModelAndView("associateMOU/Defaultunv", "dfu", associateMOU);
	}

	/**
	 * This method is used for get mou university details
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing mou university details
	 */
	public ModelAndView getMouDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		AssociateMOU associateMOU = new AssociateMOU();
		associateMOU.setUniversityId(session.getAttribute("universityId")
				.toString());

		List<AssociateMOU> mouList = associateMOUDAO
				.getMouDetails(associateMOU);

		return new ModelAndView("associateMOU/mouList", "mouList", mouList);
	}

	/**
	 * This method is used for delete mou university
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing delete operation result
	 */
	public ModelAndView deletMouDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String unvDataTokens = request.getParameter("delrec");

		boolean deleted = associateMOUDAO.deletMouDetails(unvDataTokens);
		String result;
		if (deleted) {
			result = "P";
		} else {
			result = "F";
		}

		return new ModelAndView("associateMOU/result", "res", result);
	}
}
