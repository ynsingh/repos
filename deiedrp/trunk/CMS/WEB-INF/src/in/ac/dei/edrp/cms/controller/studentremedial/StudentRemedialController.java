/*
 * @(#) StudentRemedialController.java
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
package in.ac.dei.edrp.cms.controller.studentremedial;

/**
 * This controller is designed for setting & retrieving information about
 * the student Remedials
 * @author Rohit
 * @date 7 April 2011
 * @version 1.0
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.studentremedial.StudentRemedialConnect;

import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.studentremedial.StudentRemedialInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentRemedialController extends MultiActionController {

	/** creating object of studentRemedialConnect */
	private StudentRemedialConnect studentRemedialConnect;

	/**
	 * The setter method of the interface associated with this controller
	 */
	public void setStudentRemedialConnect(
			StudentRemedialConnect studentRemedialConnect) {
		this.studentRemedialConnect = studentRemedialConnect;
	}

	/**
	 * Method for fetching current details of the student
	 * 
	 * @param request
	 * @param response
	 * @return Model View containing student Details
	 * @throws Exception
	 */
	public ModelAndView getStudentDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		StudentRemedialInfoGetter input = new StudentRemedialInfoGetter();

		 
		 input.setUserId(session.getAttribute("userId").toString());
		 input.setRollNo(session.getAttribute("userName").toString());
		 input.setUniversityCode(session.getAttribute("universityId").toString());
		 System.out.println(input.getUniversityCode());
		 System.out.println(input.getRollNo());
		 //input.setRollNo("10FSC005");
		 //setUniversityCode("DEDU");

		List<StudentRemedialInfoGetter> resultDetails = studentRemedialConnect
				.getStudentDetails(input);

		return new ModelAndView("StudentRemedial/StudentDetails",
				"resultObject", resultDetails);

	}

	/**
	 * Method for fetching remedial details of student
	 * 
	 * @param request
	 * @param response
	 * @return Model View containing student Remedials Details
	 * @throws Exception
	 */
	public ModelAndView getRemedialDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		StudentRemedialInfoGetter input = new StudentRemedialInfoGetter();
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		input.setRollNo(session.getAttribute("userName").toString());
		//input.setRollNo("10FSC005");

		input.setProgramId(request.getParameter("programId"));
		List<StudentRemedialInfoGetter> remedials = studentRemedialConnect
				.getRemedialDetails(input);
		
		return new ModelAndView("StudentRemedial/RemedialDetails",
				"resultObject", remedials);

	}

	/**
	 * Method for inserting student remedial details on apply for Remedials
	 * 
	 * @param request
	 * @param response
	 * @return String on sucessfull insertion
	 * @throws Exception
	 */
	public ModelAndView insertRemedialDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentRemedialInfoGetter inputs = new StudentRemedialInfoGetter();

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		inputs.setUserId(session.getAttribute("userId").toString());
		inputs.setRollNo(request.getParameter("rollNo"));
		inputs.setProgramCourseKey(request.getParameter("programCourseKey"));
		inputs.setCourseCode(request.getParameter("courseCode"));
		inputs.setSemesterStartDate(request.getParameter("semesterStartDate"));
		inputs.setSemesterEndDate(request.getParameter("semesterEndDate"));
		inputs.setAttemptNumber(request.getParameter("attemptNumber"));
		inputs.setCourseStatus(request.getParameter("courseStatus"));
		inputs.setApplied(request.getParameter("applied"));
		inputs.setAppliedStartDate(request.getParameter("appliedStartDate"));
		inputs.setAppliedEndDate(request.getParameter("appliedEndDate"));
		inputs.setAppliedSemester(request.getParameter("appliedSemester"));

		String resultSetSuccess = studentRemedialConnect
				.insertRemedialDetails(inputs);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultSetSuccess);

	}
}