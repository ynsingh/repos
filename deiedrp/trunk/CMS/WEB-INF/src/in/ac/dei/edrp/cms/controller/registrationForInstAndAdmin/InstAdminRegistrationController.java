/*
 * @(#) InstAdminRegistrationController.java
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
package in.ac.dei.edrp.cms.controller.registrationForInstAndAdmin;

import in.ac.dei.edrp.cms.dao.registrationForInstAndAdmin.InstAdminRegistrationDAO;
import in.ac.dei.edrp.cms.domain.registrationForInstAndAdmin.InstAdminRegistrationInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This file consist of the methods used for setting up the institute admin registration 
 * setup.
 * 
 * @author Upasana 
 * @date 21	November 2012
 * @version 1.0
 */
public class InstAdminRegistrationController extends MultiActionController{

	static final Logger logger = Logger.getLogger(InstAdminRegistrationController.class);
	private InstAdminRegistrationDAO instAdminRegistrationDAO;
	
	public void setInstAdminRegistrationDAO(InstAdminRegistrationDAO instAdminRegistrationDAO) {
		this.instAdminRegistrationDAO = instAdminRegistrationDAO;
	}
	
	public ModelAndView insertIntoInstituteAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		InstAdminRegistrationInfo input = new InstAdminRegistrationInfo();
        /*HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }*/
		//System.out.println("inside controller");
		input.setUserId("CMS");
        
        input.setEmployeeFirstName(request.getParameter("employeeFirstName").trim());
        input.setEmployeeLastName(request.getParameter("employeeLastName").trim());
        input.setDesignation(request.getParameter("designation"));
        input.setPrimaryEmailId(request.getParameter("primaryEmailId").trim());
        input.setPassword(request.getParameter("password"));
        
        input.setInstituteName(request.getParameter("instituteName"));
        input.setInstituteNickName(request.getParameter("instituteNickName"));
        input.setInstituteType(request.getParameter("instituteType"));
        input.setInstituteDomain(request.getParameter("instituteDomain"));
        
        input.setSessionStartDate("9999-01-01");
        input.setSessionEndDate("9999-01-01");
        
        input.setAddress(request.getParameter("address".trim()));
        input.setState(request.getParameter("state").trim());
        input.setCity(request.getParameter("city").trim());
        input.setCountry(request.getParameter("country").trim());
        input.setPinCode(request.getParameter("pinCode"));
        input.setOfficePhoneNumber(request.getParameter("officePhoneNumber"));
        input.setOtherPhoneNumber(request.getParameter("otherPhoneNumber"));
        
       	String resultonDesignationDetails = instAdminRegistrationDAO.setInstituteAdminProfile(input);
       	return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject", resultonDesignationDetails);
        
	}
	
	/**
	 * This method activate the account for user
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView activation info
	 */
	public ModelAndView activateAccount(HttpServletRequest request,
			HttpServletResponse response) {
		InstAdminRegistrationInfo input = new InstAdminRegistrationInfo();
        HttpSession session = request.getSession(true);
		
        input.setUserId(request.getParameter("userId"));
        input.setInstituteName(request.getParameter("instituteName"));
        input.setInstituteNickName(request.getParameter("instituteNickName"));
        input.setCity(request.getParameter("city"));
        input.setState(request.getParameter("state"));
        input.setCountry(request.getParameter("country"));
        input.setPrimaryEmailId(request.getParameter("adminEmail"));
        int i = instAdminRegistrationDAO.updateRequestStatus(input);
		if (i > 0) {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Account request confirmed successfully.");
		} else {
			return new ModelAndView("sendpassword/accountInfo", "info",
					"Page Expired.");
		}
	}
	
	public ModelAndView getInstituteAdminDetail(HttpServletRequest request,HttpServletResponse response){
		InstAdminRegistrationInfo input = new InstAdminRegistrationInfo();
		HttpSession session = request.getSession(true);
        
		String userId = (String) session.getAttribute("userId");
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		List<InstAdminRegistrationInfo> AdminInstituteDetails = instAdminRegistrationDAO.getAdminInstituteDetails(input);

        return new ModelAndView("ApprovalForInstituteAndAdmin/InstituteAdminDetail",
            "resultObject", AdminInstituteDetails);
		
	}
	
	public ModelAndView updateStatusInstitute(HttpServletRequest request,HttpServletResponse response){
		InstAdminRegistrationInfo input = new InstAdminRegistrationInfo();
        
		HttpSession session = request.getSession(true);
        
		String userId = (String) session.getAttribute("userId");
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		
        input.setInstituteName(request.getParameter("instituteName"));
        input.setInstituteNickName(request.getParameter("instituteNickName"));
        input.setCity(request.getParameter("city"));
        input.setState(request.getParameter("state"));
        input.setCountry(request.getParameter("country"));
        input.setPrimaryEmailId(request.getParameter("adminEmail"));
        input.setStatus(request.getParameter("requestStatus"));
        System.out.println("status "+input.getStatus());
		int count = instAdminRegistrationDAO.updateAdminInstituteDetails(input);
		
		List<InstAdminRegistrationInfo> AdminInstituteDetails = instAdminRegistrationDAO.getAdminInstituteDetails(input);
        return new ModelAndView("ApprovalForInstituteAndAdmin/InstituteAdminDetail","resultObject", AdminInstituteDetails);
	}
	
	public ModelAndView insertIntoUniversityUser(HttpServletRequest request,HttpServletResponse response){
		InstAdminRegistrationInfo input = new InstAdminRegistrationInfo();
		HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("userId");
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setInstituteName(request.getParameter("instituteName"));
        input.setInstituteNickName(request.getParameter("instituteNickName"));
        input.setCity(request.getParameter("city"));
        input.setState(request.getParameter("state"));
        input.setCountry(request.getParameter("country"));
        input.setPrimaryEmailId(request.getParameter("adminEmail"));
        input.setStatus(request.getParameter("requestStatus"));
        System.out.println("status "+input.getStatus());
        String detail = instAdminRegistrationDAO.insertIntoUniversityUserDetails(input);
        
       	return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject", detail);
		
		/*List<InstAdminRegistrationInfo> AdminInstituteDetails = instAdminRegistrationDAO.getAdminInstituteDetails(input);
		return new ModelAndView("ApprovalForInstituteAndAdmin/InstituteAdminDetail","resultObject", AdminInstituteDetails);*/
	}
}
