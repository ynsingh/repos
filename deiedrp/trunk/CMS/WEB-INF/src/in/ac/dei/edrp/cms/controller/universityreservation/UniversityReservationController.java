/**
 * @(#) UniversityReservationController.java
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
package in.ac.dei.edrp.cms.controller.universityreservation;

import in.ac.dei.edrp.cms.dao.universityreservation.UniversityReservationService;
import in.ac.dei.edrp.cms.domain.universityreservation.UniversityReservation;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for University Reservation
 * 
 * @version 1.0 19 JAN 2011
 * @author MOHD AMIR
 */
public class UniversityReservationController extends MultiActionController {

	/** creating object of UniversityReservationService interface */
	private UniversityReservationService universityReservationService;

	/**
	 * defining setter method for object of UniversityReservationService
	 * interface
	 */
	public void setUniversityReservationService(
			UniversityReservationService universityReservationService) {
		this.universityReservationService = universityReservationService;
	}

	/**
	 * This method is used for getting list of category and mapping to jsp file
	 * as xml
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Category details
	 */
	public ModelAndView getCategories(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		List<UniversityReservation> universityReservation = universityReservationService
				.getAllCategories(session.getAttribute("universityId")
						.toString());
		return new ModelAndView("universityreservation/categoryDetails",
				"categoryInfo", universityReservation);

	}

	/**
	 * This method is used for getting reservation details and mapping to jsp
	 * file as xml
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Reservation details
	 */
	public ModelAndView getReservationDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		List<UniversityReservation> universityReservation = universityReservationService
				.getAllReservationDetails(session.getAttribute("universityId")
						.toString());
		return new ModelAndView("universityreservation/reservationDetails",
				"reservationInfo", universityReservation);

	}

	/**
	 * This method is used for deleting reservation details from database
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView deleteReservation(HttpServletRequest request,
			HttpServletResponse response) {
		UniversityReservation universityReservation = new UniversityReservation();

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		String universityId = session.getAttribute("universityId").toString();

		StringTokenizer categoryCodes = new StringTokenizer(
				request.getParameter("categoryCode"), "|");
		universityReservation.setUniversityId(universityId);

		while (categoryCodes.hasMoreTokens()) {
			universityReservation.setCategoryCode(categoryCodes.nextToken());
			universityReservationService
					.deleteReservationDetails(universityReservation);
		}
		return new ModelAndView("general/SessionInactive", "sessionInactive",
				false);
	}

	/**
	 * This method is used for inserting reservation details to database
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView addReservation(HttpServletRequest request,
			HttpServletResponse response) {
		UniversityReservation universityReservation = new UniversityReservation();

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		universityReservation.setCategoryCode(request
				.getParameter("categoryCode"));
		universityReservation.setUniversityId(session.getAttribute(
				"universityId").toString());
		universityReservation.setPercentage(request.getParameter("percentage"));
		universityReservation.setCreatorId(session.getAttribute("userId")
				.toString());
		universityReservationService
				.addReservationDetails(universityReservation);

		return new ModelAndView("general/SessionInactive", "sessionInactive",
				false);
	}

	/**
	 * This method is used for updating reservation details from database
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView updateReservation(HttpServletRequest request,
			HttpServletResponse response) {
		UniversityReservation universityReservation = new UniversityReservation();

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		universityReservation.setCategoryCode(request
				.getParameter("categoryCode"));
		universityReservation.setUniversityId(session.getAttribute(
				"universityId").toString());
		universityReservation.setPercentage(request.getParameter("percentage"));
		universityReservation.setModifierId(session.getAttribute("userId")
				.toString());

		universityReservationService
				.updateReservationDetails(universityReservation);
		return new ModelAndView("general/SessionInactive", "sessionInactive",
				false);
	}

	/**
	 * This method is used for checking for duplicate record and mapping result
	 * to jsp
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing duplicate check information
	 */
	public ModelAndView checkDuplicateCategory(HttpServletRequest request,
			HttpServletResponse response) {
		UniversityReservation universityReservation = new UniversityReservation();

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		universityReservation.setCategoryCode(request
				.getParameter("categoryCode"));
		universityReservation.setUniversityId(session.getAttribute(
				"universityId").toString());

		Boolean check = universityReservationService
				.checkForDuplicateCategory(universityReservation);
		return new ModelAndView("universityreservation/duplicateCheck",
				"checkInfo", check);
	}
}
