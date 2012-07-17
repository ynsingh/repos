/*
 * @(#) SwitchRuleController.java
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
package in.ac.dei.edrp.cms.controller.switchrule;

import in.ac.dei.edrp.cms.dao.switchrule.SwitchRuleConnect;
import in.ac.dei.edrp.cms.domain.switchrule.SwitchRuleInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This controller is designed for setting & retrieving information about switch
 * rule setup.
 * 
 * @author Ashish
 * @date 26 Feb 2011
 * @version 1.0
 */
public class SwitchRuleController extends MultiActionController {
	private SwitchRuleConnect switchRuleConnect;

	/**
	 * The setter method of the interface associated with this controller
	 * 
	 * @param switchRuleConnect
	 */
	public void setSwitchRuleConnect(SwitchRuleConnect switchRuleConnect) {
		this.switchRuleConnect = switchRuleConnect;
	}

	/**
	 * Method for rules Details(active) list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRule3Details(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<SwitchRuleInfoGetter> resultonRule3Details = switchRuleConnect
				.getRule3Details(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultonRule3Details);
	}

	/**
	 * Method for rules Details(active) list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRule4Details(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<SwitchRuleInfoGetter> resultonRule4Details = switchRuleConnect
				.getRule4Details(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultonRule4Details);
	}

	/**
	 * Method for rules Details(active) list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRule5Details(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		in.ac.dei.edrp.cms.domain.switchrule.SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<SwitchRuleInfoGetter> resultonRule5Details = switchRuleConnect
				.getRule5Details(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultonRule5Details);
	}

	/**
	 * Method for retrieving rules Details(active) list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRule6Details(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<SwitchRuleInfoGetter> resultonRule6Details = switchRuleConnect
				.getRule6Details(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultonRule6Details);
	}

	/**
	 * Method for category(active) list
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setRuleDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setRuleCode1(request.getParameter("ruleCode1"));
		input.setRuleCode2(request.getParameter("ruleCode2"));
		input.setRuleCode3(request.getParameter("ruleCode3"));
		input.setRuleCode4(request.getParameter("ruleCode4"));
		input.setRuleCode5(request.getParameter("ruleCode5"));
		input.setRuleCode6(request.getParameter("ruleCode6"));
		input.setRuleFormula(request.getParameter("ruleFormula"));
		input.setActivity(request.getParameter("activity"));
		input.setRuleId(request.getParameter("ruleId"));

		String resultonInsertingDetails = switchRuleConnect
				.insertRuleDetails(input);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultonInsertingDetails);
	}

	/**
	 * Method for retrieving set records for switch rules
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRecordsDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<SwitchRuleInfoGetter> resultonSetRecordsDetails = switchRuleConnect
				.getRuleRecordsDetails(input);

		return new ModelAndView("SwitchRule/switchRuleRecords", "resultObject",
				resultonSetRecordsDetails);
	}

	/**
	 * Method for deleting records for the concerned ruleId
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteRecords(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SwitchRuleInfoGetter input = new SwitchRuleInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		StringTokenizer items = new StringTokenizer(request
				.getParameter("switchRuleRecords"), ",");

		String resultDeleteDetails = switchRuleConnect.deleteSwitchRuleRecords(
				input, items);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultDeleteDetails);
	}
}
