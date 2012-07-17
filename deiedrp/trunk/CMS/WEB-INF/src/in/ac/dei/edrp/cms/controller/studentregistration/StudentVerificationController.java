/*
 * @(#) StudentVerificationController.java
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
package in.ac.dei.edrp.cms.controller.studentregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.studentregistration.StudentVerificationDao;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;
import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This controller is providing middle layer for the methods of
 * <code>StudentVerificationRPC</code> DAO interface and request coming from
 * client side. This controller is being used for Student verification checklist
 * 
 * @author Manpreet Kaur
 * @version 1.0
 * @date 20-01-2011
 */
public class StudentVerificationController extends MultiActionController {
	private StudentVerificationDao studentVerification;

    /**
     * Method to initialize controller
     * @param studentVerification
     */
    public void setStudentVerificationDao(
        StudentVerificationDao studentVerification) {
        this.studentVerification = studentVerification;
    }
    

	

	String sep = System.getProperty("file.separator");

	ResourceBundle message = ResourceBundle.getBundle("in" + sep + "ac" + sep
			+ "dei" + sep + "edrp" + sep + "cms" + sep + "databasesetting"
			+ sep + "MessageProperties", new Locale("en", "US"));

	/**
	 * Method for fetching list of students with their corresponding details
	 * based on entity, program, branch, specialization,semester, semester start
	 * date, semester end date basis
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getStudentDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentInfoGetter programObject = new StudentInfoGetter();
		programObject.setEntityId(request.getParameter("entity_id"));
		programObject.setProgramId(request.getParameter("program_id"));
		programObject.setBranchCode(request.getParameter("branch_id"));
		programObject.setNewSpecialization(request
				.getParameter("specialization_id"));
		programObject.setSemesterCode(request.getParameter("semester"));
		programObject.setSessionStartDate(request
				.getParameter("semester_start_date"));
		programObject.setSessionEndDate(request
				.getParameter("semester_end_date"));
		programObject.setFalg(request.getParameter("flag"));
		if(programObject.getFalg().equalsIgnoreCase("P")){
			System.out.println("rohitP"+programObject.getFalg());
		programObject.setFalg1("P");
		programObject.setFalg2("V");
		}else if(programObject.getFalg().equalsIgnoreCase("R")){
			System.out.println("rohitR"+programObject.getFalg());
			programObject.setFalg1("R");
			programObject.setFalg2("R");	
		}
		
		List<StudentInfoGetter> studentList = new ArrayList<StudentInfoGetter>();
		studentList = studentVerification.getStudentBasicDetails(programObject);
		
		if(programObject.getFalg().equalsIgnoreCase("P")){
		if (studentList.size() == 0) {
			return new ModelAndView("general/MyException", "exception", "Y");
		}
		}
		
		return new ModelAndView("VerificationChecklist/StudentList",
				"studentList", studentList);
	}

	/**
	 * Method for fetching component list with their verification status for a
	 * particular student and for inserting verification components that were
	 * laterally added to system.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getComponentVerificationDetails(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentInfoGetter programObject = new StudentInfoGetter();

		programObject.setRegistrationNumber(request
				.getParameter("registrationNumber"));

		programObject.setRollNumber(request.getParameter("roll_number"));
		programObject.setAdmissionMode(request.getParameter("admission_mode"));
		System.out.println(request.getParameter("roll_number") + "     "
				+ request.getParameter("admission_mode"));
		programObject
				.setSequenceNumber(request.getParameter("sequence_number"));
		programObject.setProgramId(request.getParameter("program_id"));

		List<StudentInfoGetter> compList = studentVerification
				.getComponentVerificationDetails(programObject);

		return new ModelAndView(
				"VerificationChecklist/ComponentVerificationDetails",
				"compList", compList);
	}

	/**
	 * Method for updating component verification status for student and for
	 * setting verified status as "verified" if all components for student
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveComponentStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentInfoGetter[] studentObject = new StudentInfoGetter[Integer
				.parseInt(request.getParameter("total"))];
		String[] component = request.getParameter("component").trim()
				.split("\\|");
		String[] verified = request.getParameter("verified").trim()
				.split("\\|");
		String[] notes = request.getParameter("notes").trim().split("\\|");

		for (int i = 0; i < Integer.parseInt(request.getParameter("total")); i++) {
			studentObject[i] = new StudentInfoGetter();

			studentObject[i].setComponentCode(component[i + 1]);
			studentObject[i].setVerified(verified[i + 1]);
			studentObject[i].setNotes(notes[i + 1]);
			studentObject[i].setSequenceNumber(request
					.getParameter("sequence_number"));
			studentObject[i].setRegistrationNumber(request
					.getParameter("registrationNumber"));
			studentObject[i].setRollNumber(request.getParameter("roll_number"));
			studentObject[i].setEnrollmentNumber(request
					.getParameter("enrollment_number"));
			studentObject[i].setAdmissionMode(request
					.getParameter("admission_mode"));
		}

		studentVerification.saveComponentStatus(studentObject);

		return new ModelAndView("VerificationChecklist/SaveCompStatus",
				"result", null);
	}

	/**
	 * Method for fetching full details of student
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getStudentFullDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentInfoGetter programObject = new StudentInfoGetter();
		programObject.setRegistrationNumber(request
				.getParameter("registrationNumber"));
		programObject.setRollNumber(request.getParameter("roll_number"));
		programObject.setAdmissionMode(request.getParameter("admission_mode"));
		programObject
				.setSequenceNumber(request.getParameter("sequence_number"));

		StudentInfoGetter studentDetails = studentVerification
				.getStudentFullDetails(programObject);

		return new ModelAndView("VerificationChecklist/StudentFullDetails",
				"studentDetails", studentDetails);
	}

	/**
	 * Method for getting list of courses opted by student
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getCourseList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		StudentInfoGetter programObject = new StudentInfoGetter();
		programObject.setRegistrationNumber(request
				.getParameter("registrationNumber"));
		programObject.setRollNumber(request.getParameter("roll_number"));
		programObject.setAdmissionMode(request.getParameter("admission_mode"));
		programObject
				.setSequenceNumber(request.getParameter("sequence_number"));
		programObject.setProgramId(request.getParameter("program_id"));
		programObject.setSessionStartDate(request
				.getParameter("semester_start_date"));
		programObject.setSessionEndDate(request
				.getParameter("semester_end_date"));

		List<CourseInfoGetter> courseList = studentVerification
				.getCourseList(programObject);
		return new ModelAndView("VerificationChecklist/OptedCourseList",
				"courseList", courseList);
	}

	/**
	 * Method For getting enrollment details of the student
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getEnrollmentPersonalDetails(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		String registrationNo = request.getParameter("regNo");
		String enrollmentNumber = request.getParameter("enrollNo");
		
		String admission = request.getParameter("admissionMode");
		
		List<EnrollmentInfo> personalDetails;
		
		if(!(admission.equalsIgnoreCase("SWT"))){
			
			enrollmentInfo.setRegistrationNo(registrationNo);
			personalDetails= studentVerification.getEnrollmentPersonalDetails(enrollmentInfo);
			
		}
		else{
			
			
			enrollmentInfo.setEnrollmentNo(enrollmentNumber);
						
			personalDetails = studentVerification.getEnrollmentPersonalDetailsSWT(enrollmentInfo);
						
		}
		
		return new ModelAndView("registration/EnrollPersonalDetails",
				"personalDetails", personalDetails);
	}

	/**
	 * Method for getting the academic details of student
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getEnrollmentAcademicDetails(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		String registrationNo = request.getParameter("regNo");
		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		
		String enrollmentNumber = request.getParameter("enrollNo");
		
		String admission = request.getParameter("admissionMode");
		
		List<EnrollmentInfo> academicDetails;
		
		if(!(admission.equalsIgnoreCase("SWT"))){
			
			enrollmentInfo.setRegistrationNo(registrationNo);
			academicDetails = studentVerification
			.getEnrollmentAcademicDetails(enrollmentInfo);
			
		}
		else{
			
			
			enrollmentInfo.setEnrollmentNo(enrollmentNumber);
						
			academicDetails = studentVerification
			.getEnrollmentAcademicDetailsSWT(enrollmentInfo);
						
		}
		return new ModelAndView("registration/EnrollAcademicDetails",
				"academicDetails", academicDetails);
	}

	/**
	 * Method for setting the status of the student to rejected
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ModelAndView setStudentStatusToReject(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		String registrationNo = request.getParameter("regNo");
		String enrollmentNo = request.getParameter("enrollNo");
		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		//enrollmentInfo.setRegistrationStatus(message.getString("code.reject"));
		enrollmentInfo.setRegistrationNo(registrationNo);
		enrollmentInfo.setEnrollmentNo(enrollmentNo);
		enrollmentInfo
				.setAdmissionMode(request.getParameter("modeOfAdmission"));
		int updateConfirm = studentVerification
				.updateStudentStatus(enrollmentInfo);
		return new ModelAndView("registration/ReturnStatus", "status",
				updateConfirm);
	}
	
	/**
	 * Method for setting the status of the student to rejected
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ModelAndView cancelRejectStudent(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		StringTokenizer registrationNo =new StringTokenizer(request.getParameter("regNo"),"|");
		StringTokenizer enrollmentNo =new StringTokenizer(request.getParameter("enrollNo"),"|");
		StringTokenizer admMode =new StringTokenizer(request.getParameter("modeOfAdmission"),"|");
		int updateConfirm=0;
		
		System.out.println(registrationNo.countTokens()+"no. of token");
		while(registrationNo.hasMoreTokens()){
			
			EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
			enrollmentInfo.setRegistrationNo(registrationNo.nextToken());
			if(enrollmentNo.hasMoreTokens()){
			enrollmentInfo.setEnrollmentNo(enrollmentNo.nextToken());
			}
			enrollmentInfo.setAdmissionMode(admMode.nextToken());
			
			updateConfirm = studentVerification.updateRejectStatus(enrollmentInfo);
			System.out.println("req going rr");
		}
		//System.out.println("rohit return value"+updateConfirm);
		return new ModelAndView("registration/ReturnStatus", "status",
				updateConfirm);
	}
	
	
}
