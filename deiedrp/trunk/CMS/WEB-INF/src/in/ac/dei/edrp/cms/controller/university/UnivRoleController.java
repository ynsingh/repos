/*
 * @(#) UnivRoleController.java
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

import in.ac.dei.edrp.cms.dao.university.RoleConnect;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This controller is designed for setting & retrieving information about the
 * different roles set up for a university,associated links(menu items) for any
 * concerned role.
 * 
 * @author Ashish
 * @date 18 Jan 2011
 * @version 1.0
 */
public class UnivRoleController extends MultiActionController {
	private RoleConnect roleConnect;

	/**
	 * Setter method of the Interface associated with this controller
	 * 
	 * @param RoleConnect
	 */
	public void setRoleConnect(RoleConnect RoleConnect) {
		this.roleConnect = RoleConnect;
	}

	/**
	 * Method for getting the roles defined for the university
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setEmployeeCode(request.getParameter("employeeCode"));
		input.setCounter(request.getParameter("counter"));
		
		if(input.getCounter().equalsIgnoreCase("three")){
			
			input.setApplicationId(request.getParameter("applicationId"));
			
		}

		List<UnivRoleInfoGetter> resultGetUniversityRoles = roleConnect
				.getUniversityRoles(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultGetUniversityRoles);
	}

	/**
	 * Method for getting the list of menuItems for the concerned role
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getMenuList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setRoleId(request.getParameter("roleId"));
		input.setApplicationId(request.getParameter("applicationId"));

		List<UnivRoleInfoGetter> resultGetMenuList = roleConnect.getMenuList(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultGetMenuList);
	}

	/**
	 * Method for updating authorities for the role.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setRoleAuthorities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setRoleId(request.getParameter("roleId"));
		input.setMenuItemId(request.getParameter("selectedMenus"));
		input.setApplicationId(request.getParameter("applicationId"));

		StringTokenizer items = new StringTokenizer(request
				.getParameter("selectedMenus"), ",");

		String resultSetRole = roleConnect.setRoleAuthorities(items, input);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultSetRole);
	}

	/**
	 * Method for getting the list of set items for the role.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getsetMenuList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setRoleId(request.getParameter("roleId"));
		input.setApplicationId(request.getParameter("applicationId"));

		List<UnivRoleInfoGetter> resultSetMenu = roleConnect.getSetMenuList(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultSetMenu);
	}

	/**
	 * Method for deleting links for the role.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteItems(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setRoleId(request.getParameter("roleId"));
		input.setMenuItemId(request.getParameter("deleteMenus").trim());
		input.setApplicationId(request.getParameter("applicationId"));

		StringTokenizer items = new StringTokenizer(request
				.getParameter("deleteMenus"), ",");

		String resultDeleteItems = roleConnect.deleteUniversityRolesDetails(
				items,  input);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultDeleteItems);
	}
	
	/**
	 * Method for getting the list of universities from university_master 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getUniversities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);

		List<UnivRoleInfoGetter> resultGetUniversityList = roleConnect.getUniversitiesList();

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultGetUniversityList);
	}
	
	/**
	 * Method for getting the list of universities for whose 
	 * login already exists in the database 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getUniversitieswithLogins(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);
		
		String dummyID = "0000000000";

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setUniversityCode(request.getParameter("universityId"));
		input.setCreatorId("E"+input.getUniversityCode()+dummyID);
		input.setApplicationId(request.getParameter("applicationId"));

		List<UnivRoleInfoGetter> resultGetUniversityList = roleConnect.getUniversitieswithLogins(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultGetUniversityList);
	}
	
	/**
	 * Method adds a default user for newly created university 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addDefaultUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UnivRoleInfoGetter input = new UnivRoleInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		input.setUserId(userId);
		input.setUniversityCode(request.getParameter("universityId"));
		input.setComponentId(request.getParameter("emailId"));
		input.setCounter(request.getParameter("counter"));
		input.setApplicationId(request.getParameter("applicationId"));

		String resultGetUniversityList = roleConnect.addDefaultUser(input);
		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultGetUniversityList);
	}
}
