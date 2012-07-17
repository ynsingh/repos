package in.ac.dei.edrp.cms.controller.studentregistration;

/*
 * @(#) StudentLoginController.java
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
import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.studentregistration.LoginConnectDao;
import in.ac.dei.edrp.cms.domain.LoginInfoGetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentLoginController extends MultiActionController {
	private LoginConnectDao LoginConnect;

	/**
	 * Method to initialize controller
	 *
	 * @param loginConnect
	 */
	public void setLoginConnectDao(LoginConnectDao loginConnect) {
		this.LoginConnect = loginConnect;
	}

	/**
	 * Method for checking valid login and getting student's id
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getStudentLoginInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginInfoGetter input = new LoginInfoGetter();
		input.setUserName(request.getParameter("user_name"));
		input.setPassword(request.getParameter("password"));
		LoginInfoGetter result = new LoginInfoGetter();
		try {
			result = LoginConnect.getStudentLoginInfo(input);

			HttpSession session = request.getSession(true);
			session.setAttribute("userName", input.getUserName());
			session.setAttribute("userId", result.getStudentId());
			session.setAttribute("universityId", result.getUniversityId());
			session.setAttribute("universityName", result.getUniversityName());
			session.setAttribute("startDate",
					result.getSessionStartDate());
			session.setAttribute("endDate", result.getSessionEndDate());
			session.setMaxInactiveInterval(2 * 60 * 60);
		} catch (MyException e) {
			return new ModelAndView("RegistrationForm/RegisterStudent",
					"result", e.getMessage());
		}
		return new ModelAndView("RegistrationForm/LoginInfo", "result", result);
	}
    
    public ModelAndView getStudentLoginInfo1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {



			LoginInfoGetter result = new LoginInfoGetter();
			try {


			HttpSession session = request.getSession(true);

			session.setMaxInactiveInterval(2 * 60 * 60);

			LoginInfoGetter input = new LoginInfoGetter();
			input.setUserName(request.getParameter("user_name"));
			input.setUniversityId(session.getAttribute("universityId").toString());
			result = LoginConnect.getStudentLoginInfo1(input);
			} catch (MyException e) {
			return new ModelAndView("RegistrationForm/RegisterStudent",
			"result", e.getMessage());
			}
			return new ModelAndView("RegistrationForm/LoginInfo", "result", result);
			}
}
