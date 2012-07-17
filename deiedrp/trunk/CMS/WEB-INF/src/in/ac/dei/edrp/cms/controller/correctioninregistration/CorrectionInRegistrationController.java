/**
 * @(#) CorrectionInRegistrationController.java
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
package in.ac.dei.edrp.cms.controller.correctioninregistration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.correctioninregistration.CorrectionInRegistrationConnect;
import in.ac.dei.edrp.cms.domain.correctioninregistration.CorrectionInRegistrationInfoGetter;
import in.ac.dei.edrp.cms.domain.updateprestaging.UpdatePrestagingInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class CorrectionInRegistrationController extends MultiActionController  {
	
	/**
	* this is Server side controller class for Update Post Registration Data
	* @version 1.0 3 AUG 2011
	* @author ROHIT
	*/
	
	/** creating object of PostRegistrationCorrection interface */
	private CorrectionInRegistrationConnect correctionInRegistrationConnect;

	/** defining setter method for object of PostRegistrationCorrection interface */
	public void setCorrectionInRegistrationConnect(
			CorrectionInRegistrationConnect correctionInRegistrationConnect) {
		this.correctionInRegistrationConnect = correctionInRegistrationConnect;
	}

	/**
	 * This method Fetch the error records and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing Error records
	 */
	public ModelAndView getGridRecords(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		CorrectionInRegistrationInfoGetter input = new CorrectionInRegistrationInfoGetter();

		 HttpSession session = request.getSession(true);
		 String userId = (String) session.getAttribute("userId");
		 String universityId = (String) session.getAttribute("universityId");	
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			
		 input.setEntityId(request.getParameter("entityId"));
		 input.setUniversityId(universityId);
		 if(request.getParameter("programId").equalsIgnoreCase("a")){
		 input.setProgramId("%");}
		 else{
			 input.setProgramId(request.getParameter("programId"));
		 }
		

		List<CorrectionInRegistrationInfoGetter> resultDetails = correctionInRegistrationConnect
				.getGridRecords(input);
		
		return new ModelAndView("CorrectionInRegistration/gridRecords",
				"resultObject",resultDetails);

	}

	/**
	  * This method is update record having incorrect sequence number
	  * @param request
	  * @param response
	  * @return ModelAndView containing Value success or failure
	  * @throws Exception
	  */
	 public ModelAndView checkRecord(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     CorrectionInRegistrationInfoGetter detail = new CorrectionInRegistrationInfoGetter();

	     HttpSession session = request.getSession(true);

	     String userId = (String) session.getAttribute("userId");
	     String universityId = (String) session.getAttribute("universityId");		
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			
			detail.setUniversityId(universityId);
			detail.setEnrollNo(request.getParameter("enrollNo"));
			

			String resultSetSuccess = correctionInRegistrationConnect.checkRecord(detail);
	     	
	     return new ModelAndView("preProcessChecks/preProcessResultlist",
	         "resultObject", resultSetSuccess);
	 }
	
	 
	 
		/**
		  * This method is update record having incorrect sequence number
		  * @param request
		  * @param response
		  * @return ModelAndView containing Value success or failure
		  * @throws Exception
		  */
		 public ModelAndView rejectRecords(HttpServletRequest request,
		     HttpServletResponse response) throws Exception {
		     CorrectionInRegistrationInfoGetter detail = new CorrectionInRegistrationInfoGetter();

		     HttpSession session = request.getSession(true);

		     String userId = (String) session.getAttribute("userId");
		     String universityId = (String) session.getAttribute("universityId");		
				if(userId == null)
		     {
		     return new ModelAndView("general/SessionInactive","sessionInactive",true);
		     }
				detail.setUserId(userId);
				detail.setEntityId(request.getParameter("entityId"));
				detail.setProgramId(request.getParameter("programId"));
				detail.setBranchId(request.getParameter("branchId"));
				detail.setSpecializationId(request.getParameter("specializationId"));
				detail.setRegNo(request.getParameter("registrationNo"));
				detail.setStudentId(request.getParameter("studentId"));
				detail.setReasoncode(request.getParameter("reasonCode"));
				detail.setUniversityId(universityId);
				
				String resultSetSuccess = correctionInRegistrationConnect.rejectRecords(detail);
		     	
		     return new ModelAndView("preProcessChecks/preProcessResultlist",
		         "resultObject", resultSetSuccess);
		 }
		
	/**
	  * This method is update record having incorrect sequence number
	  * @param request
	  * @param response
	  * @return ModelAndView containing Value success or failure
	  * @throws Exception
	  */
	 public ModelAndView updateSeqRecord(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     CorrectionInRegistrationInfoGetter detail = new CorrectionInRegistrationInfoGetter();

	     HttpSession session = request.getSession(true);

	     String userId = (String) session.getAttribute("userId");
	     String universityId = (String) session.getAttribute("universityId");		
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			detail.setUserId(userId);
			detail.setReasoncode(request.getParameter("reasoncode"));
			detail.setSequenceNo(request.getParameter("sequenceNo"));
			detail.setRegNo(request.getParameter("regNo"));
			detail.setRollNo(request.getParameter("rollNo"));
			detail.setProgramId(request.getParameter("programId"));
			detail.setUniversityId(universityId);

			String resultSetSuccess = correctionInRegistrationConnect.updateSeqDetails(detail);
	     	
	     return new ModelAndView("preProcessChecks/preProcessResultlist",
	         "resultObject", resultSetSuccess);
	 }

	 
	  /**
	  * This method is used for getting Details From Student master
	  * @param request
	  * @param response
	  * @return ModelAndView containing Student Details
	  * @throws Exception
	  */
	 public ModelAndView getSMDetails(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     CorrectionInRegistrationInfoGetter smdetail = new CorrectionInRegistrationInfoGetter();

	     HttpSession session = request.getSession(true);

	     String userId = (String) session.getAttribute("userId");
	     String universityId = (String) session.getAttribute("universityId");		
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			
		smdetail.setEnrollNo(request.getParameter("enrollNo"));
		smdetail.setUniversityId(universityId);
		
		List<CorrectionInRegistrationInfoGetter> smRecords = correctionInRegistrationConnect
			.getSMDetails(smdetail);
	
	return new ModelAndView("CorrectionInRegistration/SMRecords",
			"resultObject",smRecords);
	 }
	
	
	 /**
	  * This method is used for getting Details from temporary student master
	  * @param request
	  * @param response
	  * @return ModelAndView containing student Details from temporary student master
	  * @throws Exception
	  */
	 public ModelAndView getTSMDetails(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     CorrectionInRegistrationInfoGetter tsmdetail = new CorrectionInRegistrationInfoGetter();

	     HttpSession session = request.getSession(true);

	     String userId = (String) session.getAttribute("userId");
	     String universityId = (String) session.getAttribute("universityId");		
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
				
			tsmdetail.setStudentId(request.getParameter("studentId"));
			tsmdetail.setUniversityId(universityId);
			List<CorrectionInRegistrationInfoGetter> tsmRecords = correctionInRegistrationConnect
			.getTSMDetails(tsmdetail);
	
	return new ModelAndView("CorrectionInRegistration/SMRecords",
			"resultObject",tsmRecords);
	 }
	
	 /**
	  * This method is used for getting the Enrollment No.
	  * @param request
	  * @param response
	  * @return ModelAndView containing value of enrollment number
	  * @throws Exception
	  */
	 public ModelAndView getEnrollNo(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
					
			CorrectionInRegistrationInfoGetter input = new CorrectionInRegistrationInfoGetter();

			 HttpSession session = request.getSession(true);
			 String userId = (String) session.getAttribute("userId");
			 String universityId = (String) session.getAttribute("universityId");		
				if(userId == null)
		     {
		     return new ModelAndView("general/SessionInactive","sessionInactive",true);
		     }
			 
			 input.setStudentfname(request.getParameter("studentfname"));
			 input.setStudentmname(request.getParameter("studentmname"));
			 input.setStudentlname(request.getParameter("studentlname"));
			 input.setFatherfname(request.getParameter("fatherfname"));
			 input.setFathermname(request.getParameter("fathermname"));
			 input.setFatherlname(request.getParameter("fatherlname"));
			 input.setGender(request.getParameter("gender"));
			 input.setCategoryId(request.getParameter("categoryId"));
			 input.setDob(request.getParameter("dob"));
			 input.setUniversityId(universityId);
			
			List<CorrectionInRegistrationInfoGetter> EnrollNoAns = correctionInRegistrationConnect
					.getEnrollNo(input);
			
			return new ModelAndView("CorrectionInRegistration/enrol",
					"resultObject",EnrollNoAns);

		}
	 
	 /**
	  * This method is used for getting the Roll No.
	  * @param request
	  * @param response
	  * @return ModelAndView containing value of roll number
	  * @throws Exception
	  */
	 public ModelAndView getRollNo(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
					
			CorrectionInRegistrationInfoGetter input = new CorrectionInRegistrationInfoGetter();

			 HttpSession session = request.getSession(true);
			 String userId = (String) session.getAttribute("userId");
			 String universityId = (String) session.getAttribute("universityId");		
				if(userId == null)
		     {
		     return new ModelAndView("general/SessionInactive","sessionInactive",true);
		     }
				
			 input.setEnrollNo(request.getParameter("enrollNo"));
			 input.setOldEntity(request.getParameter("oldEntity"));
			 input.setOldProgram(request.getParameter("oldProgram"));
			 input.setOldBranch(request.getParameter("oldBranch"));
			 input.setOldSpecialization(request.getParameter("oldSpecialization"));
			 input.setOldSemester(request.getParameter("oldSemester"));
			 input.setUniversityId(universityId);
			 
			List<CorrectionInRegistrationInfoGetter> rollNoAns = correctionInRegistrationConnect
					.getRollNo(input);
			
			return new ModelAndView("CorrectionInRegistration/enrol",
					"resultObject",rollNoAns);

		}
	 
	 /**
	  * This method is used for setting correct Sequence number
	  * @param request
	  * @param response
	  * @return ModelAndView containing Value success or failure
	  * @throws Exception
	  */
	 public ModelAndView setNumbers(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     CorrectionInRegistrationInfoGetter detail = new CorrectionInRegistrationInfoGetter();

	     HttpSession session = request.getSession(true);

	     String userId = (String) session.getAttribute("userId");
	     String universityId = (String) session.getAttribute("universityId");		
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			detail.setUserId(userId);
			detail.setReasoncode(request.getParameter("reasoncode"));
			detail.setStudentId(request.getParameter("studentId"));
			detail.setEnrollNo(request.getParameter("enrollNo"));
			detail.setRollNo(request.getParameter("rollNo"));
			detail.setUniversityId(universityId);
			String resultSetSuccess = correctionInRegistrationConnect.setNumbers(detail);
	     	
	     return new ModelAndView("preProcessChecks/preProcessResultlist",
	         "resultObject", resultSetSuccess);
	 }
	
}
