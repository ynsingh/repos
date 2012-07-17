/**
 * @(#)WithdrawStudentController.java
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
package in.ac.dei.edrp.cms.controller.withdrawstudent;

import in.ac.dei.edrp.cms.dao.studentregistration.CancelStudentRegistrationDao;
import in.ac.dei.edrp.cms.dao.withrawstudent.WithdrawStudentService;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This is Server side controller class for Withdraw Students
 * @author Devendra Singhal
 * @version 1.0 11
 * @date April 20 2011
 */
public class WithdrawStudentController extends MultiActionController {
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(WithdrawStudentController.class);

	/** creating object of WithdrawStudentService interface */
	private WithdrawStudentService withdrawStudentService;

	/** defining setter method for object of interface */
	public void setWithdrawStudentService(WithdrawStudentService withdrawStudentService) {
		this.withdrawStudentService = withdrawStudentService;
	}
	
	/** creating object of CancelStudentRegistrationDao interface */
	 private CancelStudentRegistrationDao cancelRegistrationDao;
	 
	 /** defining setter method for object of interface */
	 public void setCancelRegistrationDao(CancelStudentRegistrationDao studentConnect) {
	    this.cancelRegistrationDao = studentConnect;
	 }
	 
	 
	/**
	 * This method to get student program detail from database
	 * @param request
	 * @param response
	 * @return ModelAndView containing personal details
	 */
	public ModelAndView getProgramDetail(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentRemedialInfoGetter input=new StudentRemedialInfoGetter();
		input.setUniversityCode(universityId);
		input.setRollNo(request.getParameter("rollNumber"));
		//set university session start date
		input.setSemesterStartDate((String)session.getAttribute("startDate"));
		//set university session end date
		input.setSemesterEndDate((String)session.getAttribute("endDate"));
		
		List<StudentRemedialInfoGetter>list=null;
		try {
			list=withdrawStudentService.getProgramDetail(input);
		} catch (Exception e) {
			logObj.error("Exception in WithdrawStudentController: inside method getProgramDetail : "+e.getMessage());
		}
		return new ModelAndView("StudentRemedial/StudentDetails", "resultObject", list);
	}
	
	/**
	 * This method to Withdraw Student
	 * @param request
	 * @param response
	 * @return ModelAndView containing String message
	 */
	public ModelAndView withdrawStudent(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentRemedialInfoGetter input=new StudentRemedialInfoGetter();
		input.setUniversityCode(universityId);
		input.setUserId((String)session.getAttribute("userId"));
		input.setSessionStartDate((String)session.getAttribute("startDate"));
		input.setSessionEndDate((String)session.getAttribute("endDate"));
		input.setRollNo(request.getParameter("rollNumber"));		
		input.setSemesterStartDate(request.getParameter("semesterStartDate"));		
		input.setSemesterEndDate(request.getParameter("semesterEndDate"));		
		input.setEnrollmentNumber(request.getParameter("enrollment"));
		input.setEntityId(request.getParameter("entity"));
		input.setProgramId(request.getParameter("programId"));
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setBranchId(request.getParameter("branchId"));
		input.setSpecializationId(request.getParameter("spclId"));
		input.setSemesterId(request.getParameter("semesterId"));
		String message="";		
		try {
			message = withdrawStudentService.withrawStudent(input,cancelRegistrationDao);
		} catch (Exception e) {
			logObj.error("Exception in WithdrawStudentController: inside method withdrawStudent : "+e.getMessage());
		}		
		return new ModelAndView("enrollment/info", "info", message);
	}
}
