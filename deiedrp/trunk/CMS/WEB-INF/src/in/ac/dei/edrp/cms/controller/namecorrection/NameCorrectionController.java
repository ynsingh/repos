/**
 * @(#) NameCorrectionController.java
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
package in.ac.dei.edrp.cms.controller.namecorrection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.namecorrection.NameCorrectionConnect;
import in.ac.dei.edrp.cms.domain.namecorrection.NameCorrectionInfoGetter;
import in.ac.dei.edrp.cms.domain.updateprestaging.UpdatePrestagingInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class NameCorrectionController extends MultiActionController {
	
	/**
	* this is Server side controller class for Correcting Records Having Name Check Error in Verification Status
	* @version 1.0  3 AUG 2011
	* @author ROHIT
	*/
	
	/** creating object of NameCorrection interface */
	private NameCorrectionConnect nameCorrectionConnect;

	/** defining setter method for object of NameCorrection interface */
	public void setNameCorrectionConnect(NameCorrectionConnect nameCorrectionConnect) {
		this.nameCorrectionConnect = nameCorrectionConnect;
	}

	/**
	 * This method Fetch the list of programs and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing program list
	 */
	public ModelAndView getProgramList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		NameCorrectionInfoGetter input = new NameCorrectionInfoGetter();

		 HttpSession session = request.getSession(true);
		 String universityId = (String) session.getAttribute("universityId");
		 
		 input.setUniversityId(universityId);
		List<NameCorrectionInfoGetter> resultDetails = nameCorrectionConnect
				.getProgramList(input);
		
		return new ModelAndView("NameCorrection/IdNameDetails",
				"resultObject", resultDetails);

	}
	
	/**
	 * This method Fetch the list of branchs and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing branchs list
	 */
	public ModelAndView getBranchList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		NameCorrectionInfoGetter input = new NameCorrectionInfoGetter();
		
		input.setProgramId(request.getParameter("programId"));

		 HttpSession session = request.getSession(true);
		 String universityId = (String) session.getAttribute("universityId");	
		 input.setUniversityId(universityId);
		List<NameCorrectionInfoGetter> resultDetails = nameCorrectionConnect
				.getBranchList(input);
		
		return new ModelAndView("NameCorrection/IdNameDetails",
				"resultObject", resultDetails);

	}
	
	/**
	 * This method Fetch the list of specialization and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing specialization list
	 */
	public ModelAndView getspecializationList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		NameCorrectionInfoGetter input = new NameCorrectionInfoGetter();
		
		input.setProgramId(request.getParameter("programId"));
		input.setBranchId(request.getParameter("branchId"));

		 HttpSession session = request.getSession(true);
		 String universityId = (String) session.getAttribute("universityId");
		 input.setUniversityId(universityId);
		List<NameCorrectionInfoGetter> resultDetails = nameCorrectionConnect
				.getspecializationList(input);
		
		return new ModelAndView("NameCorrection/IdNameDetails",
				"resultObject", resultDetails);

	}
	
	/**
	 * This method Fetch the records having error and map to a jsp
	 * @param request
	 * @param response
	 * @return ModelAndView containing Error records
	 */
	public ModelAndView getErrorRecords(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		NameCorrectionInfoGetter input = new NameCorrectionInfoGetter();

		 HttpSession session = request.getSession(true);
		 String universityId = (String) session.getAttribute("universityId");	
		 input.setUserId(session.getAttribute("userId").toString());
		 input.setUniversityId(universityId);
		input.setEntityId(input.getUserId().substring(1,9));
		input.setProgramId(request.getParameter("programId"));
		
		if(request.getParameter("branchId").equalsIgnoreCase("b")){
			 input.setBranchId("%");}
			 else{
				 input.setBranchId(request.getParameter("branchId"));
			 }
	
		if(request.getParameter("specializationId").equalsIgnoreCase("c")){
			 input.setSpecializationId("%");}
			 else{
				 input.setSpecializationId(request.getParameter("specializationId"));
			 }
		

		List<NameCorrectionInfoGetter> resultDetails = nameCorrectionConnect
				.getErrorRecords(input);
		
		return new ModelAndView("NameCorrection/ErrorRecords",
				"resultObject", resultDetails);

	}
	
	
	 /**
	  * This method is used for update the record
	  * @param request
	  * @param response
	  * @return String
	  * @throws Exception
	  */
	 public ModelAndView updateNames(HttpServletRequest request,
	     HttpServletResponse response) throws Exception {
	     NameCorrectionInfoGetter detail = new NameCorrectionInfoGetter();

	     HttpSession session = request.getSession(true);
	     String universityId = (String) session.getAttribute("universityId");	
	     String userId = (String) session.getAttribute("userId");
			
			if(userId == null)
	     {
	     return new ModelAndView("general/SessionInactive","sessionInactive",true);
	     }
			detail.setUniversityId(universityId);
			detail.setUserId(userId);
			detail.setRegistrationNo(request.getParameter("registrationNo"));
			detail.setStudentId(request.getParameter("studentId"));
			detail.setStudentfname(request.getParameter("studentfname"));
			detail.setStudentmname(request.getParameter("studentmname"));
			detail.setStudentlname(request.getParameter("studentlname"));
			detail.setFatherfname(request.getParameter("fatherfname"));
			detail.setFathermname(request.getParameter("fathermname"));
			detail.setFatherlname(request.getParameter("fatherlname"));
			detail.setMotherfname(request.getParameter("motherfname"));
			detail.setMothermname(request.getParameter("mothermname"));
			detail.setMotherlname(request.getParameter("motherlname"));
			

	     String resultSetSuccess = nameCorrectionConnect.updateNames(detail);
	     	
	     return new ModelAndView("preProcessChecks/preProcessResultlist",
	         "resultObject", resultSetSuccess);
	 }
}
