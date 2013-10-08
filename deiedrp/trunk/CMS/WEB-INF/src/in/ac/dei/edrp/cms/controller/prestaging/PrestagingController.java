/**
 * @(#) PrestagingController.java
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
package in.ac.dei.edrp.cms.controller.prestaging;

import in.ac.dei.edrp.cms.dao.prestaging.PrestagingService;
import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for prestaging data transfer
 * 
 * @version 1.0 12 AUGUST 2011
 * @author MOHD AMIR
 */
public class PrestagingController extends MultiActionController {
	/** creating object of prestagingService interface */
	private PrestagingService prestagingService;

	/** defining setter method for object of interface */
	public void setPrestagingService(PrestagingService prestagingService) {
		this.prestagingService = prestagingService;
	}

	/**
	 * This method get entity list from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing entity list
	 */
	public ModelAndView getEntityList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		CourseGroupBean prestagingInfoGetter = new CourseGroupBean();
		prestagingInfoGetter.setParentEntity(session.getAttribute("userId")
				.toString().substring(1, 9));
		prestagingInfoGetter.setUniversityId(session.getAttribute(
				"universityId").toString());
		String mode = request.getParameter("mode");

		if ((mode != null) && (mode != "") && mode.equalsIgnoreCase("all")) {
			prestagingInfoGetter.setParentEntity("%");
		}

		List<CourseMasterBean> entityList = prestagingService
				.getChildEntityList(prestagingInfoGetter);

		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				entityList);
	}

	/**
	 * This method get program list from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing program list
	 */
	public ModelAndView getProgramList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		List<CourseMasterBean> programList = prestagingService
				.getProgramList(request.getParameter("entityId"));
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				programList);
	}

	/**
	 * This method get branch list from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing branch list
	 */
	public ModelAndView getBranchList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		CourseGroupBean prestagingInfoGetter = new CourseGroupBean();
		prestagingInfoGetter.setUniversityId(session.getAttribute("universityId").toString());
		prestagingInfoGetter.setEntityId(request.getParameter("entityId"));
		prestagingInfoGetter.setProgramId(request.getParameter("programId"));

		List<CourseMasterBean> branchList = prestagingService
				.getBranchList(prestagingInfoGetter);

		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				branchList);
	}

	/**
	 * This method get specialization list from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing specialization list
	 */
	public ModelAndView getSpecializationList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseGroupBean prestagingInfoGetter = new CourseGroupBean();
		prestagingInfoGetter.setUniversityId(session.getAttribute("universityId").toString());
		prestagingInfoGetter.setEntityId(request.getParameter("entityId"));
		prestagingInfoGetter.setProgramId(request.getParameter("programId"));
		prestagingInfoGetter.setBranchId(request.getParameter("branchId"));

		List<CourseMasterBean> specializationList = prestagingService
				.getSpecializationList(prestagingInfoGetter);

		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				specializationList);
	}

	/**
	 * This method set prestaging data into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing record inserted
	 */
	public ModelAndView setPrestagingData(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		String fileName = this.getServletContext().getRealPath("/")
				+ "PrestagingDataDocuments/" + request.getParameter("studentType")+ request.getParameter("fileName");

		List<EnrollmentInfo> inputEnrollmentInfo = new ArrayList<EnrollmentInfo>();
		File inputWorkbook = new File(fileName);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);

			for (int s = 0; s < w.getNumberOfSheets(); s++) {
				Sheet sheet = w.getSheet(s);

				for (int i = 2; i < sheet.getRows(); i++) {
					if ((sheet.getCell(1, i).getContents() == "")
							|| (sheet.getCell(1, i).getContents() == null)) {
						continue;
					}

					EnrollmentInfo enrollmentInfo = new EnrollmentInfo();

					enrollmentInfo.setUniversityId(session.getAttribute(
							"universityId").toString());
					enrollmentInfo.setEntityCode(request
							.getParameter("entityId"));
					enrollmentInfo.setProgramCode(request
							.getParameter("programId"));
					enrollmentInfo.setBranchCode(request
							.getParameter("branchId"));
					enrollmentInfo.setSpecializationCode(request
							.getParameter("specializationId"));
					enrollmentInfo.setSemesterCode(request
							.getParameter("semesterId"));
					enrollmentInfo.setSessionStartDate(session.getAttribute(
							"startDate").toString());
					enrollmentInfo.setSessionEndDate(session.getAttribute(
							"endDate").toString());
					enrollmentInfo.setFacRegNo(sheet.getCell(0, i)
							.getContents());
					// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
					// Starts
//					enrollmentInfo
//							.setRegistrationNo(sheet.getCell(0, i).getContents());
					// Ends
					enrollmentInfo.setStudentFirstName(sheet.getCell(1, i)
							.getContents());
					enrollmentInfo.setStudentMiddleName(sheet.getCell(2, i)
							.getContents());
					enrollmentInfo.setStudentLastName(sheet.getCell(3, i)
							.getContents());
					enrollmentInfo.setFatherFirstName(sheet.getCell(4, i)
							.getContents());
					enrollmentInfo.setFatherMiddleName(sheet.getCell(5, i)
							.getContents());
					enrollmentInfo.setFatherLastName(sheet.getCell(6, i)
							.getContents());
					enrollmentInfo.setMotherFirstName(sheet.getCell(7, i)
							.getContents());
					enrollmentInfo.setMotherMiddleName(sheet.getCell(8, i)
							.getContents());
					enrollmentInfo.setMotherLastName(sheet.getCell(9, i)
							.getContents());

					if ((sheet.getCell(10, i).getContents() != "")
							&& (sheet.getCell(10, i).getContents() != null)) {
						enrollmentInfo.setDob(sheet.getCell(10, i)
								.getContents());
					}

					enrollmentInfo.setCategoryCode(sheet.getCell(11, i)
							.getContents());
					enrollmentInfo.setPrimaryMail(sheet.getCell(12, i)
							.getContents());
					enrollmentInfo.setSecondaryMail(sheet.getCell(13, i)
							.getContents());
					enrollmentInfo.setNationality(sheet.getCell(14, i)
							.getContents());
					enrollmentInfo.setReligion(sheet.getCell(15, i)
							.getContents());
					enrollmentInfo.setGuardianName(sheet.getCell(16, i)
							.getContents());
					enrollmentInfo
							.setGender(sheet.getCell(17, i).getContents());
					// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
					// Starts
					enrollmentInfo
							.setAddressKey("PER");
					enrollmentInfo
							.setAddress(sheet.getCell(18, i).getContents());
					enrollmentInfo
							.setCity(sheet.getCell(19, i).getContents());
					enrollmentInfo
							.setState(sheet.getCell(20, i).getContents());
					enrollmentInfo
							.setPinCode(sheet.getCell(21, i).getContents());
					enrollmentInfo
							.setHomePhone(sheet.getCell(22, i).getContents());
					enrollmentInfo
							.setUserType("STD");				
					enrollmentInfo.setRollNoGroupCode(sheet.getCell(23, i).getContents());//Add By Devendra
					enrollmentInfo.setLongField(sheet.getCell(24, i).getContents());//Add By Devendra
					// Ends
					
					enrollmentInfo
							.setCreatorId(session.getAttribute("userName").toString());
					
					enrollmentInfo.setNickName(session.getAttribute("nickName")
							.toString());
					inputEnrollmentInfo.add(enrollmentInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<EnrollmentInfo> insertedEnrollmentInfo = prestagingService
				.setPrestagingData(inputEnrollmentInfo);
		if (insertedEnrollmentInfo.size() > 0) {
			String s = this.getServletContext().getRealPath("/")
					+ "PrestagingDataDocuments/New/"
					+ request.getParameter("fileName");
			File source = new File(s);
			s = this.getServletContext().getRealPath("/")
					+ "UploadedPrestagingDataDocuments/New/";
			File destination = new File(s);
			destination.mkdir();
			s = s + request.getParameter("fileName");
			destination = new File(s);
			source.renameTo(destination);
		}
		return new ModelAndView("prestaging/insertInfo", "dataList",
				insertedEnrollmentInfo);
	}
}