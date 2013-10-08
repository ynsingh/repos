/*
 * @(#) EmployeeMasterController.java
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
package in.ac.dei.edrp.cms.controller.employee;

import in.ac.dei.edrp.cms.dao.employee.EmployeeMasterConnect;
import in.ac.dei.edrp.cms.domain.employee.EmployeeMasterInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about the employee of a university
 * @author Ashish
 * @date 21 Feb 2011
 * @version 1.0
 */
public class EmployeeMasterController extends MultiActionController {
    private EmployeeMasterConnect employeeMasterConnect;

    /**
    * The setter method of the interface associated
    * with this controller
    * @param employeeMasteConnect
    */
    public void setEmployeeMasterConnect(
        EmployeeMasterConnect employeeMasterConnect) {
        this.employeeMasterConnect = employeeMasterConnect;
    }

    /**
     * Method for getting parent entities for the university
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getParentEntityDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonLoadDetails = employeeMasterConnect.getParentEntity(input.getUserId());

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonLoadDetails);
    }

    /**
     * Method for getting designations(active) of the concerned university
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getDesignationsDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonDesignationDetails = employeeMasterConnect.getDesignations(input.getUserId());

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonDesignationDetails);
    }

    /**
     * Method for gender(active) list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getGenderDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonDesignationDetails = employeeMasterConnect.getGenderDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonDesignationDetails);
    }

    /**
     * Method for category(active) list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCategoryDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonDesignationDetails = employeeMasterConnect.getCategoryDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonDesignationDetails);
    }
    
    /**
     * Method for minority groups(active) list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getMinorityDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonDesignationDetails = employeeMasterConnect.getMinorityDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonDesignationDetails);
    }
    
    /**
     * Method for pension descriptions(active) list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getPensionDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EmployeeMasterInfoGetter> resultonDesignationDetails = employeeMasterConnect.getPensionDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultonDesignationDetails);
    }

    /**
     * Method for creating employee profile
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView setEmployeeProfile(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setUniversityCode(session.getAttribute("universityId").toString());
        input.setParentEntity(request.getParameter("parentEntityId"));
        input.setDesignation(request.getParameter("designationId"));
        input.setDateOfJoining(request.getParameter("dateOfJoining"));
        input.setEmployeeFirstName(request.getParameter("empFirstName").trim());
        input.setEmployeeMiddleName(request.getParameter("empMiddleName").trim());
        input.setEmployeeLastName(request.getParameter("empLastName").trim());
        input.setPrimaryEmailId(request.getParameter("primaryEmailId").trim());
        input.setSecondryEmailId(request.getParameter("secondaryEmailId").trim());
        input.setDateOfBirth(request.getParameter("dateOfBirth"));
        input.setQualification(request.getParameter("qualification").trim());
        input.setGenderCode(request.getParameter("gender").trim());
        input.setCategoryCode(request.getParameter("category").trim());
        input.setPermanentAddress(request.getParameter("permanentAddress".trim()));
        input.setPermanentState(request.getParameter("state").trim());
        input.setPermanentCity(request.getParameter("city").trim());
        input.setPermanentPinCode(request.getParameter("pinCode"));
        input.setCoresspondAddress(request.getParameter("coresspondenceAddress").trim());
        input.setCoresspondenceState(request.getParameter("coresState").trim());
        input.setCoresspondenceCity(request.getParameter("coresCity").trim());
        input.setCoresspondencePinCode(request.getParameter("coresPinCode").trim());
        input.setOfficePhoneNumber(request.getParameter("officePhone"));
        input.setHomePhoneNumber(request.getParameter("homePhone"));
        input.setOtherPhoneNumber(request.getParameter("otherPhone"));
        input.setFaxNumber(request.getParameter("faxPhone"));
        input.setPermanentAddressKey("PER");
        input.setCoresAddresskey("COR");
        input.setActivity(request.getParameter("activity"));
        input.setEmployeeStatus(request.getParameter("employeeStatus"));
        input.setEmployeeCodeUpdate(request.getParameter("updateEmployeeCode"));
        input.setHandicapped(request.getParameter("physical").trim());
        input.setPostGraduate(request.getParameter("postGraduate").trim());
        input.setNetQualified(request.getParameter("net").trim());
        input.setMinorityFlag(request.getParameter("minorityFlag").trim());
        input.setMinority(request.getParameter("minorityCode").trim());
        input.setPensionCode(request.getParameter("pension").trim());
        
        //added by ashish mohan
        if(request.getParameter("activity").equalsIgnoreCase("update")){
        	try{
        		String resultonDesignationDetails = employeeMasterConnect.setEmployeeProfile(input);
        		return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject", resultonDesignationDetails);
        	}
        	//to catch error in update added by ashish mohan
        	catch(Exception e){
    			return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject", "ErrorInUpdate"+e);
    		}
        }
        //this works on insert
        else{
        	String resultonDesignationDetails = employeeMasterConnect.setEmployeeProfile(input);
        	return new ModelAndView("preProcessChecks/preProcessResultlist","resultObject", resultonDesignationDetails);
        	}
    }

    /**
     * Method for getting the details of the concerned employee
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getEmployeeDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("empCode"));
        input.setEmployeeFirstName(request.getParameter("empName"));

        List<EmployeeMasterInfoGetter> resultBasicDetails = employeeMasterConnect.getEmployeeDetails(input);

        return new ModelAndView("EmployeeMaster/EmployeeBasicDetails",
            "resultObject", resultBasicDetails);
    }

    /**
     * Method for getting employee address details
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getEmployeeAddressDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EmployeeMasterInfoGetter input = new EmployeeMasterInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEmployeeCode(request.getParameter("employeeCode"));

        List<EmployeeMasterInfoGetter> resultBasicDetails = employeeMasterConnect.getEmployeeAddressDetails(input);

        return new ModelAndView("EmployeeMaster/EmployeeAddressDetails",
            "resultObject", resultBasicDetails);
    }
}
