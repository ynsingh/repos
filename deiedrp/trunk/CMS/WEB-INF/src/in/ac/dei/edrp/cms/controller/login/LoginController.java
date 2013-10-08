/**
 * @(#) LoginController.java
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
package in.ac.dei.edrp.cms.controller.login;

import in.ac.dei.edrp.cms.dao.login.LoginService;
import in.ac.dei.edrp.cms.domain.login.Login;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ibatis.common.logging.Log;

/**
 * this is Server side controller class for Course Group
 * 
 * @version 1.0 11 FEB 2011
 * @author MOHD AMIR
 */
public class LoginController extends MultiActionController {

	/** creating object of LoginService interface */
	private LoginService loginService;

	/** defining setter method for object of LoginService interface */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * This method is used for getting details of login user
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getLoginDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Login login = new Login();
		login.setUserName(request.getParameter("userName"));
		login.setPassword(request.getParameter("password"));
		login.setApplication(request.getParameter("application"));
		List<Login> loginDetails = loginService.getLoginDetails(login);
		if (loginDetails.size() > 0) {
			login.setUserId(loginDetails.get(0).getUserId());
			login.setUniversityId(loginDetails.get(0).getUniversityId());
			login.setUserGroupId(loginDetails.get(0).getUserGroupId());
			int i = loginService.updateLastLogin(login);
			if (i > 0) {
				session.setAttribute("userName", loginDetails.get(0)
						.getUserName());
				session.setAttribute("userId", loginDetails.get(0).getUserId());
				session.setAttribute("universityId", loginDetails.get(0)
						.getUniversityId());
				session.setAttribute("universityName", loginDetails.get(0)
						.getUniversityName());
				session.setAttribute("startDate", loginDetails.get(0)
						.getStartDate());
				session.setAttribute("endDate", loginDetails.get(0)
						.getEndDate());

				session.setAttribute("nickName", loginDetails.get(0)
						.getNickName());
				session.setAttribute("address", loginDetails.get(0)
						.getAddress());
				session.setAttribute("city", loginDetails.get(0).getCity());
				session.setAttribute("state", loginDetails.get(0).getState());
				session.setAttribute("pin", loginDetails.get(0).getPinCode());
				session.setAttribute("phone", loginDetails.get(0).getPhone());
				session.setAttribute("otherPhone", loginDetails.get(0)
						.getOtherPhone());
				session.setAttribute("fax", loginDetails.get(0).getFax());
				session.setAttribute("country", loginDetails.get(0)
						.getCountry());
				session.setAttribute("userGroupId", loginDetails.get(0)
						.getUserGroupId());
				session.setAttribute("userGroupName", loginDetails.get(0)
						.getUserGroupName());
			}
		}
		return new ModelAndView("login/loginInfo", "loginDetails", loginDetails);
	}
	
	
	public ModelAndView getRoles(HttpServletRequest request,
			HttpServletResponse response) {
		
		Login login = new Login();
		login.setUserId(request.getParameter("userId"));
		login.setUserName(request.getParameter("userName"));
		login.setUniversityId(request.getParameter("universityId"));
		login.setApplication(request.getParameter("application"));
		
		List<Login> loginDetails = loginService.getLoginRoles(login);
		return new ModelAndView("login/loginInfo", "loginDetails", loginDetails);
	}

	/**
	 * This method is used for logout user
	 * 
	 * @param request
	 * @param response
	 */
	public void setLogout(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		session.invalidate();
	}

	/**
	 * this method will create the menu
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView generateMenu(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		session.setAttribute("userGroupId", request.getParameter("userGroupId"));
		session.setAttribute("userGroupName",
				request.getParameter("userGroupName"));
		session.setAttribute("application",request.getParameter("application"));
		//System.out.println(session.getAttribute("universityId").toString()+"university");
	
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			System.out.println("session expired");
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		Login login = new Login();
		login.setUniversityId(session.getAttribute("universityId").toString());
		login.setUserGroupId(session.getAttribute("userGroupId").toString());
		login.setApplication(session.getAttribute("application").toString());

		
		StringWriter menu = loginService.getMenu(login);

		if (menu.toString().contains("errorMenu:")) {
			return new ModelAndView("login/MenuStructure", "menu", menu);
		}

		return new ModelAndView("login/MenuStructure", "menu", menu);
	}
	
	/**
	 * this method will get the  Session Start-End Date
	 * 
	 * @param request
	 * @param response
	 */
 public void getSessionStartDate(HttpServletRequest request,HttpServletResponse response)throws Exception{
	 List<List<Login>> sessionDate=new ArrayList<List<Login>>();
	 HttpSession session=request.getSession();
	 String universityId =(String) session.getAttribute("universityId");
	 Login input = new Login();
	 input.setUniversityId(universityId);
	 sessionDate=loginService.getSessionDate(input);
	 response.setContentType("text/xml");
	 PrintWriter writer=response.getWriter();
	 writer.write("<data>");
	 List<Login> startDates=sessionDate.get(0);
	 List<Login> enrollDays=sessionDate.get(1);
	 List<Login> enrollExtDays=sessionDate.get(2);
	 List<Login> regDays=sessionDate.get(3);
	 List<Login> regExtDays=sessionDate.get(4);
	 
	 for(int i=0;i<startDates.size();i++){
		 writer.write("<sessionStartDates>"+startDates.get(i).getSessionStartDate()+"</sessionStartDates>");
	 }
	 
	 for(int j=0;j<enrollDays.size();j++){
		 writer.write("<enrollmentPeriod>"+enrollDays.get(j).getEnrollmentPeriod()+"</enrollmentPeriod>");
	 }
	 for(int k=0;k<enrollExtDays.size();k++){
		 writer.write("<enrollExtDays>"+enrollExtDays.get(k).getEnrollExtendDays()+"</enrollExtDays>");
	 }
	 for(int k=0;k<regDays.size();k++){
		 writer.write("<regDays>"+regDays.get(k).getRegDays()+"</regDays>");
	 }
	 for(int k=0;k<regExtDays.size();k++){
		 writer.write("<regExtDays>"+regExtDays.get(k).getRegExtDays()+"</regExtDays>");
	 }	 
	 writer.write("</data>");
	 writer.close();
	 writer.flush();
 }
 
	/**
	 * this method will get the  Registration Start-End Date
	 * 
	 * @param request
	 * @param response
	 */
 public ModelAndView getRegistrationStartDate(HttpServletRequest request,HttpServletResponse response)throws Exception{
	 List<Login>regisDateList=new ArrayList<Login>();
	 HttpSession session=request.getSession();
	 String universityId =(String) session.getAttribute("universityId");
	 Login input = new Login();
	 input.setUniversityId(universityId);
	 regisDateList=loginService.getRegisDate(input);
	 return new ModelAndView("login/SessionDate", "resultObject", regisDateList);
 }

}
