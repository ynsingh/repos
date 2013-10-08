/**
 * @(#) EnrollmentController.java
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
package in.ac.dei.edrp.cms.controller.enrollment;

import in.ac.dei.edrp.cms.dao.enrollment.EnrollmentService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Enrollment
 * 
 * @version 1.0 11 MAR 2011
 * @author MOHD AMIR
 */
public class EnrollmentController extends MultiActionController {
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/** creating object of enrollmentService interface */
	private EnrollmentService enrollmentService;

	/** defining setter method for object of interface */
	public void setEnrollmentService(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	/**
	 * This method get personal details from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing personal details
	 */
	public ModelAndView getPersonalInfo(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		EnrollmentInfo input = new EnrollmentInfo();

		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setRegistrationNo(request.getParameter("registrationNo"));
		input.setEntityCode("%");
		input.setProgramCode("%");
		input.setBranchCode("%");
		input.setSpecializationCode("%");
		input.setSessionStartDate("%");
		input.setSessionEndDate("%");
		input.setStatus("%");
		List<EnrollmentInfo> details = new ArrayList<EnrollmentInfo>();
		try {
			details = enrollmentService.getPersonalDetails(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logObj.error(e.getMessage());
		}
		return new ModelAndView("enrollment/personalInfo", "details", details);
	}

	/**
	 * This method get academic details from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing academic details
	 */
	public ModelAndView getAcademicInfo(HttpServletRequest request,
			HttpServletResponse response) {
		List<EnrollmentInfo> details = new ArrayList<EnrollmentInfo>();
		try {
			details = enrollmentService.getAcademicDetails(request
					.getParameter("registrationNo"));
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return new ModelAndView("enrollment/academicInfo", "details", details);
	}

	/**
	 * This method get list of addresses from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing addresses List
	 */
	public ModelAndView getContactInfo(HttpServletRequest request,
			HttpServletResponse response) {
		List<EnrollmentInfo> details = new ArrayList<EnrollmentInfo>();

		try {
			details = enrollmentService.getContactDetails(request
					.getParameter("studentId"));
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return new ModelAndView("enrollment/contactInfo", "details", details);
	}

	/**
	 * This method insert student info into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (True/False)
	 */
	public ModelAndView setStudentInfo(HttpServletRequest req,
			HttpServletResponse res) {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		EnrollmentInfo personalInfo = new EnrollmentInfo();
		if (req.getParameter("status").equalsIgnoreCase("SUB")) {
			String date = (new java.sql.Timestamp(new Date().getTime()))
					.toString().substring(0, 19);
			personalInfo.setSubmitDate(date);
		}

		personalInfo.setEnrollmentNo(req.getParameter("enrollmentNo"));
		personalInfo.setRegistrationNo(req.getParameter("registrationNo"));
		personalInfo.setStudentId(req.getParameter("studentId"));
		personalInfo.setSessionStartDate(session.getAttribute("startDate")
				.toString());
		personalInfo.setSessionEndDate(session.getAttribute("endDate")
				.toString());
		personalInfo.setHindiName(req.getParameter("studentHindiName"));
		personalInfo.setFatherHindiName(req.getParameter("fatherHindiName"));
		personalInfo.setMotherHindiName(req.getParameter("motherHindiName"));
		personalInfo.setGender(req.getParameter("gender"));
		personalInfo.setStatus(req.getParameter("status"));
		personalInfo.setStudentFirstName(req.getParameter("firstName").trim());
		personalInfo.setStudentMiddleName(req.getParameter("middleName").trim());
		personalInfo.setStudentLastName(req.getParameter("lastName").trim());
		personalInfo.setFatherFirstName(req.getParameter("fatherFirstName").trim());
		personalInfo.setFatherMiddleName(req.getParameter("fatherMiddleName").trim());
		personalInfo.setFatherLastName(req.getParameter("fatherLastName").trim());
		personalInfo.setMotherFirstName(req.getParameter("motherFirstName").trim());
		personalInfo.setMotherMiddleName(req.getParameter("motherMiddleName").trim());
		personalInfo.setMotherLastName(req.getParameter("motherLastName").trim());
		personalInfo.setCategoryCode(req.getParameter("category"));
		personalInfo.setReligion(req.getParameter("religion"));
		personalInfo.setNationality(req.getParameter("nationality"));
		personalInfo.setEntityCode(req.getParameter("entity"));
		personalInfo.setProgramCode(req.getParameter("program"));
		personalInfo.setBranchCode(req.getParameter("branch"));
		personalInfo.setSpecializationCode(req.getParameter("specialization"));
		personalInfo.setGuardianName(req.getParameter("guardian").trim());
		personalInfo.setDob(req.getParameter("dob"));
		personalInfo.setPrimaryMail(req.getParameter("primaryEmailId").trim());
		personalInfo.setSecondaryMail(req.getParameter("secondaryEmailId").trim());
		personalInfo.setPhotoPath(req.getParameter("path"));

		List<EnrollmentInfo> addressList = new ArrayList<EnrollmentInfo>();
		List<EnrollmentInfo> academicList = new ArrayList<EnrollmentInfo>();

		StringTokenizer addressToken = new StringTokenizer(
				req.getParameter("address"), "|");
		StringTokenizer cityToken = new StringTokenizer(
				req.getParameter("city"), "|");
		StringTokenizer stateToken = new StringTokenizer(
				req.getParameter("state"), "|");
		StringTokenizer pinToken = new StringTokenizer(req.getParameter("pin"),
				"|");
		int i = 0;
		while (cityToken.hasMoreTokens()) {
			EnrollmentInfo addressInfo = new EnrollmentInfo();
			if (i == 0) {
				addressInfo.setAddressKey("PER");
				addressInfo.setOfficePhone(req.getParameter("officePhone"));
				addressInfo.setHomePhone(req.getParameter("homePhone"));
				addressInfo.setOtherPhone(req.getParameter("otherPhone"));
				addressInfo.setFax(req.getParameter("faxPhone"));
			} else {
				addressInfo.setAddressKey("COR");
			}
			addressInfo.setStudentId(req.getParameter("studentId"));
			addressInfo.setUserType("STD");
			addressInfo.setCity(cityToken.nextToken());
			addressInfo.setState(stateToken.nextToken());

			if (addressToken.hasMoreTokens()) {
				addressInfo.setAddress(addressToken.nextToken());
			}

			if (pinToken.hasMoreTokens()) {
				addressInfo.setPinCode(pinToken.nextToken());
			}

			addressList.add(addressInfo);
			i++;
		}

		StringTokenizer examToken = new StringTokenizer(
				req.getParameter("examCode"), "|");
		StringTokenizer yearToken = new StringTokenizer(
				req.getParameter("year"), "|");
		StringTokenizer boardToken = new StringTokenizer(
				req.getParameter("boardCode"), "|");
		StringTokenizer schoolCollegeToken = new StringTokenizer(
				req.getParameter("schoolCollege"), "|");
		StringTokenizer marksObtainedToken = new StringTokenizer(
				req.getParameter("marksObtained"), "|");
		StringTokenizer totalMarksToken = new StringTokenizer(
				req.getParameter("totalMarks"), "|");
		StringTokenizer cgpaToken = new StringTokenizer(
				req.getParameter("cgpa"), "|");
		
		while (examToken.hasMoreTokens()) {
			EnrollmentInfo academicInfo = new EnrollmentInfo();
			academicInfo.setRegistrationNo(req.getParameter("registrationNo"));
			academicInfo.setBoardCode(boardToken.nextToken());
			academicInfo.setExamCode(examToken.nextToken());
			academicInfo.setYear(yearToken.nextToken());
			academicInfo.setTotalMarks(Integer.parseInt(totalMarksToken
					.nextToken()));
			academicInfo.setMarksObtained(Integer.parseInt(marksObtainedToken
					.nextToken()));
			academicInfo.setCollege(schoolCollegeToken.nextToken());
			String str=cgpaToken.nextToken();
			academicInfo.setCgpa(str);
			academicList.add(academicInfo);
		}

		String isInserted = "";
		try {
			isInserted = enrollmentService.updateStudentDetails(personalInfo,
					addressList, academicList);
		} catch (Exception e) {
			logObj.error(e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("enrollment/info", "info", req.getParameter("status") + isInserted);
	}

	/**
	 * This method insert student info into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (True/False)
	 */
	public ModelAndView uploadStudentPhotos(HttpServletRequest req,
			HttpServletResponse res) {
		String filePath = this.getServletContext().getRealPath("/")
				+ req.getParameter("filePath");
		Boolean bool = false;
		try {
			bool = enrollmentService.uploadStudentPhotos(filePath, this
					.getServletContext().getRealPath("/") + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("enrollment/info", "info", e.getMessage());
		}
		return new ModelAndView("enrollment/info", "info", bool);
	}
	
	/**
	 * This method get Roll Number/Enrollment Number Format
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing Roll Number/Enrollment Number Format
	 * @author Devendra Singhal
	 */
	public ModelAndView getFormat(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		EnrollmentInfo input=new EnrollmentInfo();
		input.setUniversityId((String)session.getAttribute("universityId"));
		input.setType(req.getParameter("type"));
		List<EnrollmentInfo>list=enrollmentService.getFormat(input);
		return new ModelAndView("enrollment/format", "details", list);
	}
	/**
	 * This method insert Roll Number/Enrollment Number Format
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing Roll Number/Enrollment Number Format
	 * @author Devendra Singhal
	 */
	public ModelAndView insertFormat(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		EnrollmentInfo input=new EnrollmentInfo();
		input.setUniversityId((String)session.getAttribute("universityId"));
		input.setUserId((String)session.getAttribute("userId"));		
		input.setType(req.getParameter("type"));
		input.setRangeFrom(Integer.parseInt(req.getParameter("rangeFrom")));
		input.setRangeTo(Integer.parseInt(req.getParameter("rangeTo")));
		input.setFormat(req.getParameter("format"));
		String msg=enrollmentService.insertFormat(input);
		return new ModelAndView("enrollment/info", "info", msg);
	}
	/**
	 * This method delete Roll Number/Enrollment Number Format
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing Roll Number/Enrollment Number Format
	 * @author Devendra Singhal
	 */
	public ModelAndView deleteFormat(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		EnrollmentInfo input=new EnrollmentInfo();
		input.setUniversityId((String)session.getAttribute("universityId"));		
		input.setType(req.getParameter("type"));		
		String format=req.getParameter("format");
		String msg=enrollmentService.deleteFormat(input,format);
		return new ModelAndView("enrollment/info", "info", msg);
	}
}