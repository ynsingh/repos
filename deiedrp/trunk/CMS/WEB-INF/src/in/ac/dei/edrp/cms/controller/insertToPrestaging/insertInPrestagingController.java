/**
 * @(#) insertInPrestagingController.java
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
package in.ac.dei.edrp.cms.controller.insertToPrestaging;

import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import in.ac.dei.edrp.cms.dao.insertToPrestagingService.insertToPrestagingService;
import in.ac.dei.edrp.cms.dao.prestaging.TransferEnrollmentToPrestagingService;
import in.ac.dei.edrp.cms.dao.prestaging.TransferOldStudentToPrestagingService;

import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for prestaging data transfer
 * 
 * @version 1.0 08 October 2012
 * @author Ashish Mohan
 */


public class insertInPrestagingController extends MultiActionController{
	
	/** creating object of insertInPrestagingService interface */
	private insertToPrestagingService prestagingService;

	/**
	 * @param prestagingService the prestagingService to set
	 */
	public void setPrestagingService(insertToPrestagingService prestagingService) {
		this.prestagingService = prestagingService;
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

		StudentMasterInfoBean studentMasterInfoBean = prestagingService
				.checkExistanceOfEnroll(input);
		return new ModelAndView("studentmaster/personalInfo", "details",
				studentMasterInfoBean);
	}
	
	
	public ModelAndView getStudentInfo(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		enrollmentInfo.setUniversityId(session.getAttribute("universityId").toString());
		enrollmentInfo.setEntityCode(request.getParameter("entityId"));
		enrollmentInfo.setProgramCode(request.getParameter("programId"));
		enrollmentInfo.setBranchCode(request.getParameter("branchId"));
		enrollmentInfo.setSpecializationCode(request.getParameter("specializationId"));
		enrollmentInfo.setSemesterCode(request.getParameter("semesterId"));
		enrollmentInfo.setSessionStartDate(session.getAttribute("startDate").toString());
		enrollmentInfo.setSessionEndDate(session.getAttribute("endDate").toString());
		
		

		List<StudentMasterInfoBean> result  = prestagingService
				.getStudent(enrollmentInfo);
		return new ModelAndView("studentmaster/studentList", "records",
				result);
	}




	/**
	 * This method set enrollment data into database
	 * @author Ashish Mohan
	 * @param request
	 * @param response
	 * @return ModelAndView containing record inserted
	 */
	public ModelAndView setEnrollmentData(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		

		List<EnrollmentInfo> inputEnrollmentInfo = new ArrayList<EnrollmentInfo>();
		System.out.println(request.getParameter("newStudentData")+": new");
		StringTokenizer studentDataTokens = new StringTokenizer(request.getParameter("newStudentData"), ",");
		try {
				while(studentDataTokens.hasMoreTokens()){
					
					EnrollmentInfo enrollmentInfo = new EnrollmentInfo();

					enrollmentInfo.setUniversityId(session.getAttribute("universityId").toString());
					enrollmentInfo.setEntityCode(request.getParameter("entityId"));
					enrollmentInfo.setProgramCode(request.getParameter("programId"));
					enrollmentInfo.setBranchCode(request.getParameter("branchId"));
					enrollmentInfo.setSpecializationCode(request.getParameter("specializationId"));
					enrollmentInfo.setSemesterCode(request.getParameter("semesterId"));
					enrollmentInfo.setSessionStartDate(session.getAttribute("startDate").toString());
					enrollmentInfo.setSessionEndDate(session.getAttribute("endDate").toString());
					enrollmentInfo.setFacRegNo(studentDataTokens.nextToken());
					enrollmentInfo.setEnrollmentNo(studentDataTokens.nextToken());
					enrollmentInfo.setStudentFirstName(studentDataTokens.nextToken());
					enrollmentInfo.setStudentMiddleName(" ");
					enrollmentInfo.setStudentLastName(" ");
					enrollmentInfo.setFatherFirstName(studentDataTokens.nextToken());
					enrollmentInfo.setFatherMiddleName(" ");
					enrollmentInfo.setFatherLastName(" ");
					enrollmentInfo.setMotherFirstName("not available");
					enrollmentInfo.setMotherMiddleName(" ");
					enrollmentInfo.setMotherLastName(" ");
					String dob=studentDataTokens.nextToken();
					if(dob.equalsIgnoreCase("NA"))
					{
						enrollmentInfo.setDob("9999-09-09");
					}
					else
					{
						enrollmentInfo.setDob(dob);
					}
					

					enrollmentInfo.setCategoryCode(studentDataTokens.nextToken());
					enrollmentInfo.setPrimaryMail(studentDataTokens.nextToken());
					enrollmentInfo.setSecondaryMail(" ");
					enrollmentInfo.setNationality(" ");
					enrollmentInfo.setReligion(" ");
					enrollmentInfo.setGuardianName(" ");
					
					String genderCode=studentDataTokens.nextToken();
					if(genderCode.equalsIgnoreCase("NA"))
					{
						enrollmentInfo.setGender(" ");
					}
					else
					{
						enrollmentInfo.setGender(genderCode);
					}
					
					enrollmentInfo.setAddressKey("PER");
					enrollmentInfo.setAddress(studentDataTokens.nextToken());
					enrollmentInfo.setCity(" ");
					enrollmentInfo.setState(" ");
					enrollmentInfo.setPinCode("282005");
					enrollmentInfo.setHomePhone(" ");
					enrollmentInfo.setUserType("STD");				
					enrollmentInfo.setRollNoGroupCode(studentDataTokens.nextToken());
					enrollmentInfo.setLongField(" ");
					enrollmentInfo.setHindiName(studentDataTokens.nextToken());
			
					enrollmentInfo.setCreatorId(session.getAttribute("userName").toString());
					enrollmentInfo.setNickName(session.getAttribute("nickName").toString());
					inputEnrollmentInfo.add(enrollmentInfo);
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}

		List<EnrollmentInfo> insertedEnrollmentInfo = prestagingService
				.setPrestagingData(inputEnrollmentInfo);

		return new ModelAndView("prestaging/insertInfo", "dataList",
				insertedEnrollmentInfo);
	}
	
	
	
	public ModelAndView setStudentData(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		

		List<EnrollmentInfo> inputEnrollmentInfo = new ArrayList<EnrollmentInfo>();
		System.out.println(request.getParameter("StudentData")+":");
		StringTokenizer studentDataTokens = new StringTokenizer(request.getParameter("StudentData"), ",");
		try {
				while(studentDataTokens.hasMoreTokens()){
					
					EnrollmentInfo enrollmentInfo = new EnrollmentInfo();

					enrollmentInfo.setUniversityId(session.getAttribute("universityId").toString());
					enrollmentInfo.setEntityCode(request.getParameter("entityId"));
					enrollmentInfo.setProgramCode(request.getParameter("programId"));
					enrollmentInfo.setBranchCode(request.getParameter("branchId"));
					enrollmentInfo.setSpecializationCode(request.getParameter("specializationId"));
					enrollmentInfo.setSemesterCode(request.getParameter("semesterId"));
					enrollmentInfo.setSessionStartDate(session.getAttribute("startDate").toString());
					enrollmentInfo.setSessionEndDate(session.getAttribute("endDate").toString());
					enrollmentInfo.setFacRegNo(studentDataTokens.nextToken());
					enrollmentInfo.setEnrollmentNo(studentDataTokens.nextToken());
					enrollmentInfo.setStudentFirstName(studentDataTokens.nextToken());
					enrollmentInfo.setStudentMiddleName(" ");
					enrollmentInfo.setStudentLastName(" ");
					enrollmentInfo.setFatherFirstName(studentDataTokens.nextToken());
					enrollmentInfo.setFatherMiddleName(" ");
					enrollmentInfo.setFatherLastName(" ");
					enrollmentInfo.setMotherFirstName("not available");
					enrollmentInfo.setMotherMiddleName(" ");
					enrollmentInfo.setMotherLastName(" ");
					String dob=studentDataTokens.nextToken();
					if(dob.equalsIgnoreCase("NA"))
					{
						enrollmentInfo.setDob("9999-09-09");
					}
					else
					{
						enrollmentInfo.setDob(dob);
					}
					

					enrollmentInfo.setCategoryCode(studentDataTokens.nextToken());
					enrollmentInfo.setPrimaryMail(studentDataTokens.nextToken());
					enrollmentInfo.setSecondaryMail(" ");
					enrollmentInfo.setNationality(" ");
					enrollmentInfo.setReligion(" ");
					enrollmentInfo.setGuardianName(" ");
					
					String genderCode=studentDataTokens.nextToken();
					if(genderCode.equalsIgnoreCase("NA"))
					{
						enrollmentInfo.setGender(" ");
					}
					else
					{
						enrollmentInfo.setGender(genderCode);
					}
					
					enrollmentInfo.setAddressKey("PER");
					enrollmentInfo.setAddress(studentDataTokens.nextToken());
					enrollmentInfo.setCity(" ");
					enrollmentInfo.setState(" ");
					enrollmentInfo.setPinCode("282005");
					enrollmentInfo.setHomePhone(" ");
					enrollmentInfo.setUserType("STD");				
					enrollmentInfo.setRollNoGroupCode(studentDataTokens.nextToken());
					enrollmentInfo.setLongField(" ");
					enrollmentInfo.setHindiName(studentDataTokens.nextToken());
			
					enrollmentInfo.setCreatorId(session.getAttribute("userName").toString());
					enrollmentInfo.setNickName(session.getAttribute("nickName").toString());
					inputEnrollmentInfo.add(enrollmentInfo);
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}

		List<EnrollmentInfo> insertedEnrollmentInfo = prestagingService
				.setTempData(inputEnrollmentInfo);

		return new ModelAndView("prestaging/insertInfo", "dataList",
				insertedEnrollmentInfo);
	}
	
	
	
	
	
	
	/**
	 * This method transfer enrollment data to prestaging table
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing no of records transfered
	 */
	public ModelAndView insertOldToPrestaging(
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		System.out.println(request.getParameter("oldStudentData")+":old");
		List<PrestagingInfoGetter> inputInfo=new ArrayList<PrestagingInfoGetter>();
		
		ConsolidatedChartBean inputForSem = new ConsolidatedChartBean();
		inputForSem.setSemSeqNo("1");
		inputForSem.setSemesterCode("%");
		inputForSem.setProgramCode(request.getParameter("programId"));
		List<ConsolidatedChartBean> semSeqList = prestagingService
		.getSemesterAndSeqNo(inputForSem);
		
		



		
		EnrollmentInfo enrollmentInfo = new EnrollmentInfo();
		enrollmentInfo.setUniversityId(session.getAttribute("universityId").toString());
		enrollmentInfo.setEntityCode(request.getParameter("entityId"));
		enrollmentInfo.setProgramCode(request.getParameter("programId"));
		enrollmentInfo.setBranchCode(request.getParameter("branchId"));
		enrollmentInfo.setSpecializationCode(request.getParameter("specializationId"));
		enrollmentInfo.setSemesterCode(semSeqList.get(0).getSemesterCode());
		enrollmentInfo.setSessionStartDate(session.getAttribute("startDate").toString());
		enrollmentInfo.setSessionEndDate(session.getAttribute("endDate").toString());
		enrollmentInfo.setUserType(session.getAttribute("userId").toString());// used for user id
		PrestagingInfoGetter dateData = prestagingService
				.getRegistrationDueDate(enrollmentInfo);
		
		Boolean b = true;
		StringTokenizer studentDataTokens = new StringTokenizer(request.getParameter("oldStudentData"), ",");
		try {
			
			while(studentDataTokens.hasMoreTokens()){


				PrestagingInfoGetter dataRow = new PrestagingInfoGetter();

				studentDataTokens.nextToken();//serial number
				dataRow.setEnrollmentNo(studentDataTokens.nextToken());
				dataRow.setStudentFirstName(studentDataTokens.nextToken());
				dataRow.setStudentMiddleName(" ");
				dataRow.setStudentLastName(" ");
				dataRow.setFatherFirstName(studentDataTokens.nextToken());
				dataRow.setFatherMiddleName(" ");
				dataRow.setFatherLastName(" ");
				dataRow.setMotherFirstName("not available");
				dataRow.setMotherMiddleName(" ");
				dataRow.setMotherLastName(" ");
				String dob=studentDataTokens.nextToken();
				if(dob.equalsIgnoreCase("NA"))
				{
					dataRow.setDob("9999-09-09");
				}
				else
				{
					dataRow.setDob(dob);
				}
				

				dataRow.setCategory(studentDataTokens.nextToken());
				dataRow.setPrimaryEmail(studentDataTokens.nextToken());

				
				String genderCode=studentDataTokens.nextToken();
				if(genderCode.equalsIgnoreCase("NA"))
				{
					dataRow.setGender(" ");
				}
				else
				{
					dataRow.setGender(genderCode);
				}
				
				//dataRow.setAddressKey("PER");
				dataRow.setPerAddress(studentDataTokens.nextToken());
				dataRow.setPerCity(" ");
				dataRow.setPerState(" ");
				dataRow.setPerPinCode("282005");

			
				dataRow.setRollNoGroupCode(studentDataTokens.nextToken());
				dataRow.setLongField(" ");
				studentDataTokens.nextToken();//student hindi name
		
				dataRow.setUserId("STD");
				dataRow.setNickName(session.getAttribute("nickName").toString());
				
				
				
				

				
				dataRow.setUniversityId(session.getAttribute("universityId").toString());
				dataRow.setEntityId(request.getParameter("entityId"));
				dataRow.setProgramId(request.getParameter("programId"));
				dataRow.setBranchId(request.getParameter("branchId"));
				dataRow.setSpecializationId(request.getParameter("specializationId"));
				dataRow.setAttemptNo("1");
				
				if (semSeqList.size() > 0) {
					System.out.println(semSeqList.get(0).getSemesterCode()+"xxxxx");
						dataRow.setSemesterId(semSeqList.get(0).getSemesterCode());
				}


				dataRow.setRegistrationDueDate(dateData
						.getRegistrationDueDate());
				dataRow.setSemesterStartDate(dateData
						.getSemesterStartDate());
				dataRow.setSemesterEndDate(dateData
						.getSemesterEndDate());


				dataRow.setAdmissionMode("NEW");
						dataRow.setOfficePhone(" ");
						dataRow.setHomePhone(" ");
						dataRow.setOtherPhone(" ");
						dataRow.setFax(" ");
					
						inputInfo.add(dataRow);

		
		}
			
			

	}

		
			
			


		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		List<PrestagingInfoGetter>insertedEnrollmentInfo = prestagingService
		.setPrestagingDataOld(inputInfo);

return new ModelAndView("prestaging/insertInfo", "dataList",
		insertedEnrollmentInfo);
	}
	
	
	public ModelAndView updateStudentData(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		

		List<StudentInfoGetter> inputEnrollmentInfo = new ArrayList<StudentInfoGetter>();
		System.out.println(request.getParameter("students")+":");
		System.out.println(request.getParameter("fathers")+":");
		StringTokenizer studentDataTokens = new StringTokenizer(request.getParameter("students"), "$");
		StringTokenizer fatherDataTokens = new StringTokenizer(request.getParameter("fathers"), "$");
		try {
				while(studentDataTokens.hasMoreTokens()){
					
					StudentInfoGetter enrollmentInfo = new StudentInfoGetter();

					enrollmentInfo.setUniversityId(session.getAttribute("universityId").toString());
					enrollmentInfo.setOldEntityId(request.getParameter("entityId"));
					enrollmentInfo.setOldProgramId(request.getParameter("programId"));
					enrollmentInfo.setOldBranchId(request.getParameter("branchId"));
					enrollmentInfo.setOldSpecialization(request.getParameter("specializationId"));
					enrollmentInfo.setEntityId(request.getParameter("newentityId"));
					enrollmentInfo.setProgramId(request.getParameter("newprogramId"));
					enrollmentInfo.setBranchId(request.getParameter("newbranchId"));
					enrollmentInfo.setNewSpecialization(request.getParameter("newspecializationId"));
					enrollmentInfo.setSessionStartDate(session.getAttribute("startDate").toString());
					enrollmentInfo.setSessionEndDate(session.getAttribute("endDate").toString());
					enrollmentInfo.setStudentFirstName(studentDataTokens.nextToken());
					enrollmentInfo.setFatherFirstName(fatherDataTokens.nextToken());
					enrollmentInfo.setCreatorId(session.getAttribute("userName").toString());
					inputEnrollmentInfo.add(enrollmentInfo);
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}

		String count = prestagingService
				.updateTempData(inputEnrollmentInfo);

		return new ModelAndView("RegistrationForm/RegisterStudent",
	            "result", count);
	}
	

}
