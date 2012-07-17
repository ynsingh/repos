/**
 * @(#) SystemTableTwoController.java
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
package in.ac.dei.edrp.cms.controller.systemtabletwo;

import in.ac.dei.edrp.cms.dao.systemtabletwo.SystemTableTwoService;
import in.ac.dei.edrp.cms.domain.systemtabletwo.SystemTableTwoBean;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for SystemTableTwo
 *
 * @version 1.0 14 FEB 2011
 * @author MOHD AMIR
 */
public class SystemTableTwoController extends MultiActionController {
	/** creating object of SystemTableTwoService interface */
	private SystemTableTwoService systemTableTwoService;

	/** defining setter method for object of SystemTableTwoService interface */
	public void setSystemTableTwoService(
			SystemTableTwoService systemTableTwoService) {
		this.systemTableTwoService = systemTableTwoService;
	}

	/**
	 * This method is used for getting list of component and mapping to jsp file
	 * as xml
	 *
	 * @param request
	 * @param response
	 * @return Model and View containing Category details
	 */
	public ModelAndView getComponentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		SystemTableTwoBean systemTableTwoBean = new SystemTableTwoBean();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		systemTableTwoBean.setUniversityCode(session.getAttribute(
				"universityId").toString());
		systemTableTwoBean.setGroupCode(request.getParameter("groupCode"));

		List<SystemTableTwoBean> groupDetails = systemTableTwoService
				.getGroupDetails(systemTableTwoBean);
		return new ModelAndView("systemtabletwo/groupInfo", "groupDetails",
				groupDetails);
	}

	/**
	 * This method is used for checking for duplicate record and mapping result
	 * to jsp
	 *
	 * @param request
	 * @param response
	 * @return Model and View containing duplicate check information
	 */
	public ModelAndView duplicateCheck(HttpServletRequest request,
			HttpServletResponse response) {
		SystemTableTwoBean systemTableTwoBean = new SystemTableTwoBean();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		systemTableTwoBean.setUniversityCode(session.getAttribute(
				"universityId").toString());
		systemTableTwoBean.setGroupCode(request.getParameter("groupCode"));
		systemTableTwoBean.setComponentCode(request
				.getParameter("componentCode"));

		int count = systemTableTwoService
				.getDuplicateComponentCount(systemTableTwoBean);

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method is used for deleting component details from database
	 *
	 * @param request
	 * @param response
	 * @return Model and View containing no of record deleted
	 */
	public ModelAndView deleteComponentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		SystemTableTwoBean systemTableTwoBean = new SystemTableTwoBean();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		systemTableTwoBean.setUniversityCode(session.getAttribute(
				"universityId").toString());

		StringTokenizer componentToken = new StringTokenizer(
				request.getParameter("selectedComponentCode"), "\\|");
		StringTokenizer groupToken = new StringTokenizer(
				request.getParameter("selectedGroupCode"), "\\|");

		int count = 0;
		while (componentToken.hasMoreTokens()) {
			systemTableTwoBean.setGroupCode(groupToken.nextToken());
			systemTableTwoBean.setComponentCode(componentToken.nextToken());

			int i = systemTableTwoService.deleteComponent(systemTableTwoBean);
			count = count + i;
		}

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method is used for updating component details from database
	 *
	 * @param request
	 * @param response
	 * @return Model and View containing no of record updated
	 */
	public ModelAndView updateComponentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		SystemTableTwoBean systemTableTwoBean = new SystemTableTwoBean();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		systemTableTwoBean.setUniversityCode(session.getAttribute(
				"universityId").toString());
		systemTableTwoBean.setGroupCode(request.getParameter("groupCode"));
		systemTableTwoBean.setActive(request.getParameter("active"));
		systemTableTwoBean.setComponentCode(request
				.getParameter("componentCode").toUpperCase());
		systemTableTwoBean.setComponentDescription(request
				.getParameter("componentDescription").trim());
		systemTableTwoBean.setVerificationRequired(request
				.getParameter("verificationRequired"));
		systemTableTwoBean.setDummyFlag1(request.getParameter("dummyFlag1").trim());
		systemTableTwoBean.setDummyFlag2(request.getParameter("dummyFlag2").trim());
		systemTableTwoBean.setDummyFlag3(request.getParameter("dummyFlag3").trim());
		systemTableTwoBean.setUserId(session.getAttribute("userId").toString());

		int count = systemTableTwoService.updateComponent(systemTableTwoBean);

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method is used for inserting component details to database
	 *
	 * @param request
	 * @param response
	 */
	public ModelAndView setComponentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		SystemTableTwoBean systemTableTwoBean = new SystemTableTwoBean();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		systemTableTwoBean.setUniversityCode(session.getAttribute(
				"universityId").toString());
		systemTableTwoBean.setGroupCode(request.getParameter("groupCode"));
		systemTableTwoBean.setActive(request.getParameter("active"));
		systemTableTwoBean.setComponentCode(request
				.getParameter("componentCode").toUpperCase());
		systemTableTwoBean.setComponentDescription(request
				.getParameter("componentDescription").trim());
		systemTableTwoBean.setVerificationRequired(request
				.getParameter("verificationRequired"));
		systemTableTwoBean.setDummyFlag1(request.getParameter("dummyFlag1").trim());
		systemTableTwoBean.setDummyFlag2(request.getParameter("dummyFlag2").trim());
		systemTableTwoBean.setDummyFlag3(request.getParameter("dummyFlag3").trim());
		systemTableTwoBean.setUserId(session.getAttribute("userId").toString());
		systemTableTwoService.setComponentDetails(systemTableTwoBean);
		return new ModelAndView("general/SessionInactive",
				"sessionInactive", false);
	}


	/**
		 * This method is used to build the values for different codes
		 * in system values table
		 * @param request
		 * @param response
		 * @return success
		 */
		public ModelAndView buildSystemValues(HttpServletRequest request,
				HttpServletResponse response) {
			SystemValue systemValueBean = new SystemValue();
			HttpSession session = request.getSession();

			String groupDetails = "";

			if (session.getAttribute("universityId") == null) {
				return new ModelAndView("general/SessionInactive",
						"sessionInactive", true);
			}
			systemValueBean.setUniveristyCode(session.getAttribute(
					"universityId").toString());
			systemValueBean.setCreatorId(session.getAttribute("userId").toString());
			systemValueBean.setProcess(request.getParameter("counter"));

			if(systemValueBean.getProcess().equalsIgnoreCase("one")){

				StringTokenizer codesInput = new StringTokenizer
				(request.getParameter("systemCodes"),",");

				 groupDetails = systemTableTwoService
				.buildSystemValues(systemValueBean,codesInput);
			}else{

				 groupDetails = systemTableTwoService
				.buildSystemValues(systemValueBean,new StringTokenizer(""));
			}



			return new ModelAndView("preProcessChecks/preProcessResultlist", "resultObject",
					groupDetails);
	}
}
