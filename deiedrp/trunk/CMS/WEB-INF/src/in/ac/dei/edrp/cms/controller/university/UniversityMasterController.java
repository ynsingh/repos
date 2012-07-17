/*
 * @(#) UniversityMasterController.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.cms.controller.university;

import in.ac.dei.edrp.cms.dao.university.UniversityMasterConnect;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This controller is designed for setting & retrieving information about the
 * set up for a university for any concerned role.
 * 
 * @author Ashish
 * @date 9 Feb 2011
 * @version 1.0
 */
public class UniversityMasterController extends MultiActionController {
	private UniversityMasterConnect universityMasterConnect;

	/**
	 * Setter method of the Interface associated with this controller
	 * 
	 * @param UniversityMasterConnect
	 */
	public void setUniversityMasterConnect(
			UniversityMasterConnect universityMasterConnect) {
		this.universityMasterConnect = universityMasterConnect;
	}

	/**
	 * Method for getting university details for creation
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView defineUniversity(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UniversityMasterInfoGetter input = new UniversityMasterInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setNickName(request.getParameter("nickName"));
		input.setUniversityName(request.getParameter("universityName").trim());
		input.setSessionStartDate(request.getParameter("sessionStartDate"));
		input.setSessionEndDate(request.getParameter("sessionEndDate"));
		input.setUniversityAddress(request.getParameter("address").trim());
		input.setUniversityCity(request.getParameter("city").trim());
		input.setUniversityState(request.getParameter("state").trim());
		input.setUniversityPincode(request.getParameter("pinCode"));
		input.setUniversityPhoneNumber(request.getParameter("phoneNumber"));
		input.setUniversityOtherPhoneNumber(request
				.getParameter("otherPhoneNumber"));
		input.setUniversityFaxNumber(request.getParameter("faxNumber"));
		input.setCountryName(request.getParameter("country").trim());

		String resultSetUniversity = universityMasterConnect
				.defineUniversityCreation(input.getUserId(), input
						.getUniversityName(), input.getSessionStartDate(),
						input.getSessionEndDate(),
						input.getUniversityAddress(),
						input.getUniversityCity(), input.getUniversityState(),
						input.getUniversityPincode(), input
								.getUniversityPhoneNumber(), input
								.getUniversityOtherPhoneNumber(), input
								.getUniversityFaxNumber(), input.getNickName(),input.getCountryName());

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultSetUniversity);
	}

	/**
	 * Method for getting the universities for managing
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getUniversities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UniversityMasterInfoGetter input = new UniversityMasterInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setFlag(request.getParameter("flag"));

		List<UniversityMasterInfoGetter> resultGetUniversitiesDetails = universityMasterConnect
				.getUniversitiesList(input.getUserId(), input.getFlag());

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultGetUniversitiesDetails);
	}

	/**
	 * Method for getting the universities for managing
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getUniversitiesDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UniversityMasterInfoGetter input = new UniversityMasterInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setUniversityCode(request.getParameter("universityCode"));

		List<UniversityMasterInfoGetter> resultGetUniversitiesDetails = universityMasterConnect
				.getUniversityDetails(input);

		return new ModelAndView("UniversityMasterSetup/universityMaster",
				"resultObject", resultGetUniversitiesDetails);
	}

	/**
	 * Method for updating the university details
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateUniversitiesDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UniversityMasterInfoGetter input = new UniversityMasterInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setNickName(request.getParameter("nickName"));
		input.setUniversityName(request.getParameter("universityName").trim());
		input.setSessionStartDate(request.getParameter("sessionStartDate"));
		input.setSessionEndDate(request.getParameter("sessionEndDate"));
		input.setUniversityAddress(request.getParameter("address").trim());
		input.setUniversityCity(request.getParameter("city").trim());
		input.setUniversityState(request.getParameter("state").trim());
		input.setUniversityPincode(request.getParameter("pinCode"));
		input.setUniversityPhoneNumber(request.getParameter("phoneNumber"));
		input.setUniversityOtherPhoneNumber(request
				.getParameter("otherPhoneNumber"));
		input.setUniversityFaxNumber(request.getParameter("faxNumber"));
		input.setUniversityCode(request.getParameter("universityCode"));
		input.setFlag(request.getParameter("flag"));
		input.setCountryName(request.getParameter("country").trim());

		String resultSetUniversitiesDetails = universityMasterConnect
				.updateUniversityDetails(input.getUserId(), input
						.getUniversityName(), input.getSessionStartDate(),
						input.getSessionEndDate(),
						input.getUniversityAddress(),
						input.getUniversityCity(), input.getUniversityState(),
						input.getUniversityPincode(), input
								.getUniversityPhoneNumber(), input
								.getUniversityOtherPhoneNumber(), input
								.getUniversityFaxNumber(), input
								.getUniversityCode(), input.getNickName(),
						input.getFlag(),input.getCountryName());

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultSetUniversitiesDetails);
	}

	/**
	 * Method for updating the status of an already created university
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateuniversitystatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UniversityMasterInfoGetter input = new UniversityMasterInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setUniversityCode(request.getParameter("universityCode"));
		input.setCurrentStatus(request.getParameter("status"));
		input.setSessionStartDate(request.getParameter("sessionStartDate"));

		String resultupdateUniversityStatus = universityMasterConnect
				.updateUniversityStatus(input.getUserId(), input
						.getUniversityCode(), input.getCurrentStatus(), input
						.getSessionStartDate());

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultupdateUniversityStatus);
	}
}
