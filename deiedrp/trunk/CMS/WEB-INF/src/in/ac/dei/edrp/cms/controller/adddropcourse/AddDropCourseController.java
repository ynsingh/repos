/**
 * @(#) AddDropCourseController.java
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
package in.ac.dei.edrp.cms.controller.adddropcourse;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.adddropcourse.AddDropCourseService;
import in.ac.dei.edrp.cms.domain.adddropcourse.AddDropCourseBean;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Add drop course
 * 
 * @version 1.0 12 AUGUST 2011
 * @author MOHD AMIR
 * @version 2.0 12 Feb 2012
 * @author ASHISH MOHAN
 */
public class AddDropCourseController extends MultiActionController {
	/** creating object of addDropCourseService interface */
	private AddDropCourseService addDropCourseService;

	/** defining setter method for object of interface */
	public void setAddDropCourseService(
			AddDropCourseService addDropCourseService) {
		this.addDropCourseService = addDropCourseService;
	}

	/**
	 * This method get student course list from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course list
	 */
	public ModelAndView getStudentCourseList(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		input.setEntityId(request.getParameter("entity_id"));
		input.setProgramId(request.getParameter("program_id"));
		input.setBranchId(request.getParameter("branch_code"));
		input.setSpecializationId(request.getParameter("new_specialization"));
		input.setSemesterId(request.getParameter("semester_code"));
		input.setRegNumber(request.getParameter("regNumber"));
		input.setUserId(request.getSession(true).getAttribute("universityId").toString());
		

		if(request.getParameter("processFlag")==null){
			input.setStudentId("TMP");
		}
		else{
			input.setStudentId("MST");
			input.setRollNumber(request.getParameter("rollNumber"));
		}
		List<AddDropCourseBean> detailsList = addDropCourseService
				.getStudentCourseList(input);

		return new ModelAndView("adddropcourse/componentInfo", "detailsList",
				detailsList);
	}

	/**
	 * This method get student details from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student details
	 */
	public ModelAndView getStudentDetailsList(HttpServletRequest request,
			HttpServletResponse response) {
		List<AddDropCourseBean> detailsList = addDropCourseService
				.getProgramDetails(request.getParameter("regNumber"));

		return new ModelAndView("adddropcourse/programInfo", "detailsList",
				detailsList);
	}

	/**
	 * This method get total credits from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing total credits
	 */
	public ModelAndView getTotalCredits(HttpServletRequest request,
			HttpServletResponse response) {
		String credits = addDropCourseService.getTotalCredits(request
				.getParameter("regNumber"));
		System.out.println(credits);
		return new ModelAndView("enrollment/info", "info", credits);
	}

	/**
	 * This method get available course for student from database and map to a
	 * jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing available course list
	 */
	public ModelAndView getCourseAvailable(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean addDropCourseBean = new AddDropCourseBean();
		addDropCourseBean.setUserId(request.getSession(true).getAttribute("universityId").toString());
		addDropCourseBean.setRegNumber(request.getParameter("regNumber"));
		addDropCourseBean.setProgramId(request.getParameter("programId"));
		addDropCourseBean.setBranchId(request.getParameter("branchId"));
		addDropCourseBean.setSpecializationId(request
				.getParameter("specializationId"));
		addDropCourseBean.setSemesterId(request.getParameter("semesterId"));
		addDropCourseBean.setCourseCode(request.getParameter("courseCode"));
		if(request.getParameter("processFlag")==null){
			addDropCourseBean.setStudentId("TMP");
		}
		else{
			addDropCourseBean.setStudentId("MST");
			addDropCourseBean.setRollNumber(request.getParameter("rollNumber"));
		}
		List<AddDropCourseBean> detailsList = addDropCourseService
				.getCourseAvailable(addDropCourseBean);

		return new ModelAndView("adddropcourse/courseInfo", "detailsList",
				detailsList);
	}

	/**
	 * This method add and drop student course
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing add drop info
	 */
	public ModelAndView addDropStudentCourse(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		AddDropCourseBean dropCourseBean = new AddDropCourseBean();
		dropCourseBean.setRegNumber(request.getParameter("regNumber"));
		dropCourseBean.setCourseCode(request.getParameter("dropcourseCode"));
		dropCourseBean.setSemesterStartDate(request
				.getParameter("semesterStartDate"));
		dropCourseBean.setSemesterEndDate(request
				.getParameter("semesterEndDate"));
		dropCourseBean.setUserId(session.getAttribute("userId").toString());
		

		// added by Ashish Mohan
		if(request.getParameter("processFlag")==null){
			dropCourseBean.setStudentId("TMP");
		}
		else{
			dropCourseBean.setStudentId("MST");
			dropCourseBean.setRollNumber(request.getParameter("rollNumber"));
			dropCourseBean.setEntityId(request.getParameter("entityId"));
			dropCourseBean.setProgramId(request.getParameter("programId"));
			dropCourseBean.setBranchId(request.getParameter("branchId"));
			dropCourseBean.setSpecializationId(request
					.getParameter("specializationId"));
			dropCourseBean.setSemesterId(request.getParameter("semesterId"));
			dropCourseBean.setCourseType(request.getParameter("courseCategory"));
			dropCourseBean.setMaximumCredits(request.getParameter("registerCredit"));//for register credit
			dropCourseBean.setMaximumLectureCredits(request.getParameter("regTCExAudit"));//for register theory credit ex audit
			dropCourseBean.setMinimumLectureCredits(request.getParameter("regPCExAudit"));//for register pratical credit ex audit
			dropCourseBean.setMinimumCredits(request.getParameter("registerCreditExAudit"));//for register credit ex audit
		}
		
		StringTokenizer courseCodes = new StringTokenizer(
				request.getParameter("courseCodes"), "|");
//		StringTokenizer courseNames=new StringTokenizer("");
		
//		if(dropCourseBean.getStudentId().equalsIgnoreCase("MST")){
		StringTokenizer courseNames = new StringTokenizer(
				request.getParameter("courseNames"), "|");
//		}
		List<AddDropCourseBean> addCourseBeans = new ArrayList<AddDropCourseBean>();

		while (courseCodes.hasMoreTokens()) {
			AddDropCourseBean addCourseBean = new AddDropCourseBean();

			addCourseBean.setRegNumber(request.getParameter("regNumber"));
			addCourseBean.setCourseGroup(request.getParameter("courseGroup"));
			addCourseBean.setSemesterStartDate(request
					.getParameter("semesterStartDate"));
			addCourseBean.setSemesterEndDate(request
					.getParameter("semesterEndDate"));
			addCourseBean.setEntityId(request.getParameter("entityId"));
			addCourseBean.setProgramId(request.getParameter("programId"));
			addCourseBean.setBranchId(request.getParameter("branchId"));
			addCourseBean.setSpecializationId(request
					.getParameter("specializationId"));
			addCourseBean.setSemesterId(request.getParameter("semesterId"));
			addCourseBean.setRollNumber(request.getParameter("rollNumber"));
			addCourseBean.setEnrollNumber(request.getParameter("enrollNumber"));
			addCourseBean.setCourseCode(courseCodes.nextToken());
			addCourseBean.setCourseName(courseNames.nextToken());
			addCourseBean.setStudentId(request.getParameter("studentId"));
			addCourseBean.setUserId(session.getAttribute("userId").toString());
			
			addCourseBeans.add(addCourseBean);
		
		}
		
		String str = addDropCourseService.addDropStudentCourse(addCourseBeans,
				dropCourseBean);

		return new ModelAndView("enrollment/info", "info", str);
	}
}
