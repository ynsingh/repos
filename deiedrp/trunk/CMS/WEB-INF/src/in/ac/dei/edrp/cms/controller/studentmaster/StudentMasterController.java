/**
 * @(#) StudentMasterController.java
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
package in.ac.dei.edrp.cms.controller.studentmaster;

import in.ac.dei.edrp.cms.dao.studentmaster.StudentMasterService;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Student Master
 * 
 * @version 1.0 24 MAR 2011
 * @author MOHD AMIR
 */
public class StudentMasterController extends MultiActionController {

	/** creating object of StudentMasterService interface */
	private StudentMasterService studentMasterService;

	/** defining setter method for object of StudentMasterService interface */
	public void setStudentMasterService(
			StudentMasterService studentMasterService) {
		this.studentMasterService = studentMasterService;
	}

	/**
	 * This method check and get student Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student Info
	 */
	public ModelAndView checkEnrollmentInfo(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		StudentMasterInfoBean input = new StudentMasterInfoBean();
		input.setEnrollmentNumber(request.getParameter("enrollmentNumber"));
		input.setUniversityId(session.getAttribute("universityId").toString());

		StudentMasterInfoBean studentMasterInfoBean = studentMasterService
				.checkExistanceOfEnroll(input);
		return new ModelAndView("studentmaster/personalInfo", "details",
				studentMasterInfoBean);
	}

	/**
	 * This method check for duplicate info in database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing duplicate info
	 */
	public ModelAndView checkDuplicateInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Boolean bool = studentMasterService.checkDuplicateRollNumber(request
				.getParameter("enrollmentNumber"));
		int count = 1;
		if (!bool) {
			count = 0;
		}
		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method insert/update student details into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing details whether record is
	 *         inserted/updated or not
	 */
	public ModelAndView setStudentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		StudentMasterInfoBean studentMasterInfoBean = new StudentMasterInfoBean();

		studentMasterInfoBean.setSessionStartDate(session.getAttribute(
				"startDate").toString());
		studentMasterInfoBean.setSessionEndDate(session.getAttribute("endDate")
				.toString());
		studentMasterInfoBean.setReligion(request.getParameter("religion"));
		studentMasterInfoBean.setReligion(request.getParameter("religion"));
		studentMasterInfoBean.setNationality(request
				.getParameter("nationality"));
		studentMasterInfoBean.setGuardian(request.getParameter("guardian"));
		studentMasterInfoBean.setHindiName(request.getParameter("hindiName"));
		studentMasterInfoBean.setFatherHindiName(request
				.getParameter("fatherHindiName"));
		studentMasterInfoBean.setMotherHindiName(request
				.getParameter("motherHindiName"));
		studentMasterInfoBean.setPath(request.getParameter("path"));
		studentMasterInfoBean.setParentEntity(request
				.getParameter("parentEntity"));
		studentMasterInfoBean.setUserId(session.getAttribute("userId")
				.toString());
		studentMasterInfoBean.setUniversityId(session.getAttribute(
				"universityId").toString());
		studentMasterInfoBean.setStatus(request.getParameter("status"));
		studentMasterInfoBean.setStudentFirstName(request.getParameter(
				"firstName").trim());
		studentMasterInfoBean.setStudentMiddleName(request.getParameter(
				"middleName").trim());
		studentMasterInfoBean.setStudentLastName(request.getParameter(
				"lastName").trim());
		studentMasterInfoBean.setFatherFirstName(request.getParameter(
				"fatherFirstName").trim());
		studentMasterInfoBean.setFatherMiddleName(request.getParameter(
				"fatherMiddleName").trim());
		studentMasterInfoBean.setFatherLastName(request.getParameter(
				"fatherLastName").trim());
		studentMasterInfoBean.setMotherFirstName(request.getParameter(
				"motherFirstName").trim());
		studentMasterInfoBean.setMotherMiddleName(request.getParameter(
				"motherMiddleName").trim());
		studentMasterInfoBean.setMotherLastName(request.getParameter(
				"motherLastName").trim());
		studentMasterInfoBean.setCategoryCode(request.getParameter("category"));
		studentMasterInfoBean.setDateOfBirth(request.getParameter("dob"));
		studentMasterInfoBean.setEnrollmentNumber(request
				.getParameter("enrollmentNo"));
		studentMasterInfoBean.setRegisteredInSession(request
				.getParameter("registeredSession"));
		studentMasterInfoBean.setGender(request.getParameter("gender"));
		studentMasterInfoBean.setPrimaryEmailId(request.getParameter(
				"primaryEmailId").trim());
		studentMasterInfoBean.setSecondaryEmailId(request.getParameter(
				"secondaryEmailId").trim());
		Map<String, StudentMasterInfoBean> addressMap = new HashMap<String, StudentMasterInfoBean>();

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
			if (i == 0) {
				addressBean.setAddressKey("PER");
				addressBean.setOfficePhone(request.getParameter("officePhone")
						.trim());
				addressBean.setHomePhone(request.getParameter("homePhone")
						.trim());
				addressBean.setOtherPhone(request.getParameter("otherPhone")
						.trim());
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
			addressMap.put(addressBean.getAddressKey(), addressBean);
		}

		studentMasterInfoBean.setStudentAddress(addressMap);
		Boolean isInserted = false;
		if (request.getParameter("action").equalsIgnoreCase("insert")) {
			isInserted = studentMasterService
					.addStudentMaster(studentMasterInfoBean);
			return new ModelAndView("enrollment/info", "info", isInserted);
		} else {
			studentMasterInfoBean.setStudentId(request
					.getParameter("studentId"));

			isInserted = studentMasterService
					.updateStudentMaster(studentMasterInfoBean);
			return new ModelAndView("enrollment/info", "info", isInserted);
		}
	}

	/**
	 * The method retrieves the records from student tracking and displays them
	 * on the interface
	 * 
	 * @param request
	 * @param response
	 * @return StudentTrackingRecords
	 * @author Ashish
	 */
	public ModelAndView ViewStudentTracking(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		StudentInfoGetter studentBean = new StudentInfoGetter();

		studentBean.setRollNumber(request.getParameter("employeeCode"));
		studentBean.setPrimaryEmailId("emailId");
        studentBean.setUniversityId(session.getAttribute("universityId").toString());
		List<StudentInfoGetter> result = studentMasterService
				.getStudentTrackingRecords(studentBean);

		return new ModelAndView("studentmaster/StudentTracking",
				"resultObject", result);

	}

}