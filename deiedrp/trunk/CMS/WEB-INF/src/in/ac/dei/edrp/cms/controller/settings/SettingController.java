/**
 * @(#) SettingController.java
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
package in.ac.dei.edrp.cms.controller.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import in.ac.dei.edrp.cms.dao.settings.SettingService;
import in.ac.dei.edrp.cms.domain.login.Login;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for setting
 * 
 * @version 1.0 12 JULY 2011
 * @author MOHD AMIR
 */
public class SettingController extends MultiActionController {

	/** creating object of settingService interface */
	private SettingService settingService;

	/** defining setter method for object of interface */
	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}

	/**
	 * This method get user address details from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing user address details
	 */
	public ModelAndView getUserAddress(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		List<StudentMasterInfoBean> addressData = settingService
				.getUserAddress(session.getAttribute("userId").toString());

		return new ModelAndView("studentmaster/addressInfo", "details",
				addressData);
	}

	/**
	 * This method change user address
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing upadation details
	 */
	public ModelAndView changeUserAddress(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		List<StudentMasterInfoBean> addressBeanList = new ArrayList<StudentMasterInfoBean>();

		StringTokenizer addresses = new StringTokenizer(
				request.getParameter("address"), "|");
		StringTokenizer cities = new StringTokenizer(
				request.getParameter("city"), "|");
		StringTokenizer states = new StringTokenizer(
				request.getParameter("state"), "|");
		StringTokenizer pinCodes = new StringTokenizer(
				request.getParameter("pin"), "|");
		int i = 0;

		while (cities.hasMoreTokens()) {

			StudentMasterInfoBean addressBean = new StudentMasterInfoBean();
			addressBean.setUserType(request.getParameter("userType"));
			addressBean.setUserId(session.getAttribute("userId").toString());
			addressBean.setStudentId(session.getAttribute("userId").toString());

			if (i == 0) {
				addressBean.setAddressKey("PER");
				addressBean.setOfficePhone(request.getParameter("officePhone").trim());
				addressBean.setHomePhone(request.getParameter("homePhone").trim());
				addressBean.setOtherPhone(request.getParameter("otherPhone").trim());
				addressBean.setFax(request.getParameter("fax").trim());
			} else {
				addressBean.setAddressKey("COR");
			}

			addressBean.setState(states.nextToken().trim());
			addressBean.setCity(cities.nextToken().trim());

			if (addresses.hasMoreTokens()) {
				addressBean.setAddressLineOne(addresses.nextToken().trim());
			}

			if (pinCodes.hasMoreTokens()) {
				addressBean.setPinCode(pinCodes.nextToken().trim());
			}
			i++;
			addressBeanList.add(addressBean);
		}

		Boolean isUpdated = settingService.changeUserAddress(addressBeanList);
		return new ModelAndView("enrollment/info", "info", isUpdated);
	}

	/**
	 * This method change password into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing upadte password details
	 */
	public ModelAndView changePassword(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		Login login = new Login();
		login.setUserId(session.getAttribute("userId").toString());
		login.setUserName(session.getAttribute("userName").toString());
		login.setPassword(request.getParameter("password"));
		login.setNewPassword(request.getParameter("newPassword"));	
		login.setApplication(request.getParameter("application"));
		login.setUniversityId((String) session.getAttribute("universityId"));
		Boolean isUpdated = settingService.changeUserPassword(login);

		return new ModelAndView("enrollment/info", "info", isUpdated);
	}
}
