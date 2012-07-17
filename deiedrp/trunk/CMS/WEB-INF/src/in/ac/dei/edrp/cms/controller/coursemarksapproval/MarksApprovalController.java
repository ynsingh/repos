/**
 * @(#) MarksApprovalController.java
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
package in.ac.dei.edrp.cms.controller.coursemarksapproval;

import in.ac.dei.edrp.cms.dao.coursemarksapproval.MarksApprovalService;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.coursemarksapproval.MarksApprovalInfo;
import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Marks Approval
 * 
 * @version 1.0 16 MAR 2011
 * @author MOHD AMIR
 */
public class MarksApprovalController extends MultiActionController {
	/** creating object of MarksApprovalService interface */
	private MarksApprovalService marksApprovalService;

	/** defining setter method for object of MarksApprovalService interface */
	public void setMarksApprovalService(
			MarksApprovalService marksApprovalService) {
		this.marksApprovalService = marksApprovalService;
	}

	/**
	 * This method get list of employee from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing employee List
	 */
	public ModelAndView getEmployeeList(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		MarksApprovalInfo input = new MarksApprovalInfo();
		input.setUniversityCode(session.getAttribute("universityId").toString());
		List<MarksApprovalInfo> details = marksApprovalService
				.getEmployeeList(input);		
		return new ModelAndView("marksApproval/employeeInfo", "details",
				details);
	}

	/**
	 * This method get list of course from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing course List
	 */
	public ModelAndView getCourseList(HttpServletRequest request,
			HttpServletResponse response) {
		MarksApprovalInfo input = new MarksApprovalInfo();

		input.setProgramCode(request.getParameter("program"));
		input.setBranchCode(request.getParameter("branch"));
		input.setSpecializationCode(request.getParameter("specialization"));
		input.setSemesterCode(request.getParameter("semester"));

		List<MarksApprovalInfo> details = marksApprovalService
				.getCourseList(input);

		return new ModelAndView("marksApproval/courseInfo", "details", details);
	}

	/**
	 * This method get no of duplicate record for given course,approval order
	 * and program course key
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing no of record found
	 */
	public ModelAndView getDuplicateRecordCount(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		MarksApprovalInfo input = new MarksApprovalInfo();
		input.setEntityCode(request.getParameter("entityId"));
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setEmployeeCode(request.getParameter("employeeCode"));
		input.setApprovalOrder(Integer.parseInt(request
				.getParameter("approvalOrder")));

		int count = marksApprovalService.getDuplicateCount(input);
		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method insert Approval details into database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView show that whether record inserted or not
	 */
	public ModelAndView setMarksApprovalDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		MarksApprovalInfo input = new MarksApprovalInfo();
		
		String data=request.getParameter("data");
		input.setProgramCourseKey(request.getParameter("programCourseKey"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setApprovalOrder(Integer.parseInt(request
				.getParameter("approvalOrder")));
		input.setSequenceNo(request.getParameter("sequence"));
		input.setUserId(session.getAttribute("userId").toString());
		input.setEvaluationId(request.getParameter("display"));
		String message = marksApprovalService.setApprovalDetails(input,data);
		return new ModelAndView("enrollment/info", "info", message);
	}

	/**
	 * This method update Approval details from database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing number of records updated
	 */
	public ModelAndView updateApprovalDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		MarksApprovalInfo marksApprovalInfo = new MarksApprovalInfo();

		marksApprovalInfo.setProgramCourseKey(request
				.getParameter("programCourseKey"));
		marksApprovalInfo.setCourseCode(request.getParameter("courseCode"));
		marksApprovalInfo.setEmployeeCode(request.getParameter("employeeCode"));
		marksApprovalInfo.setApprovalOrder(Integer.parseInt(request
				.getParameter("approvalOrder")));
		marksApprovalInfo.setUserId(session.getAttribute("userId").toString());

		int count = marksApprovalService
				.updateApprovalDetails(marksApprovalInfo);
		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method delete Approval details from database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing number of records deleted
	 */
	public ModelAndView deleteApprovalDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		StringTokenizer programCourseKeys = new StringTokenizer(
				request.getParameter("programCourseKey"), "|");
		StringTokenizer courseCodes = new StringTokenizer(
				request.getParameter("courseCode"), "|");
		StringTokenizer approvalOrders = new StringTokenizer(
				request.getParameter("approvalOrder"), "|");
		StringTokenizer entityToken= new StringTokenizer(
				request.getParameter("entity"), "|");
		StringTokenizer employeeToken = new StringTokenizer(
				request.getParameter("employee"), "|");
		StringTokenizer sequenceToken = new StringTokenizer(
				request.getParameter("sequenceNo"), "|");
		StringTokenizer displayToken = new StringTokenizer(
				request.getParameter("display"), "|");
		int count = 0;
		while (programCourseKeys.hasMoreTokens()) {
			MarksApprovalInfo marksApprovalInfo = new MarksApprovalInfo();

			marksApprovalInfo
					.setProgramCourseKey(programCourseKeys.nextToken());
			marksApprovalInfo.setCourseCode(courseCodes.nextToken());
			marksApprovalInfo.setApprovalOrder(Integer.parseInt(approvalOrders
					.nextToken()));
			marksApprovalInfo.setEmployeeCode(employeeToken.nextToken());
			marksApprovalInfo.setEntityCode(entityToken.nextToken());
			marksApprovalInfo.setEvaluationId(displayToken.nextToken());
			String  seqNo=sequenceToken.nextToken();
			if(seqNo.equals("null")){
				seqNo=null;
			}
			marksApprovalInfo.setSequenceNo(seqNo);			
			count += marksApprovalService
					.deleteApprovalDetails(marksApprovalInfo);		
		}

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method get Approval details from database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing Approval details
	 */
	public ModelAndView getMarksApprovalDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		MarksApprovalInfo input = new MarksApprovalInfo();
		input.setUniversityCode((String) session.getAttribute("universityId"));
		String entity=request.getParameter("entity");
		if(entity.equalsIgnoreCase("All")){
			input.setEntityCode(input.getUniversityCode()+"%");
		}
		else{
			input.setEntityCode(entity);
		}		
		List<MarksApprovalInfo> details = marksApprovalService
				.getApprovalDetails(input);

		return new ModelAndView("marksApproval/marksApprovalInfo", "details",
				details);
	}

	/**
	 * This method get program details from database
	 * @author Devendra Singhal
	 * @param request
	 * @param response
	 * @return ModelAndView containing Approval details
	 */
	public ModelAndView getProgramDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
		input.setUniversityId((String) session.getAttribute("universityId"));
		String entity=request.getParameter("entity");
		if(entity.equalsIgnoreCase("All")){
			input.setEntityId(input.getUniversityId()+"%");
		}
		else{
			input.setEntityId(entity);
		}				
		List<ProgramCourseTypeSummaryInfoGetter> details = marksApprovalService
		.getProgramDetails(input);		
		return new ModelAndView("ProgramCourseTypeSummary/programCourseTypeSummary", "resultObject",
				details);
	}
	
	/**
	 * This method get entity from database
	 * @author Devendra Singhal
	 * @param request
	 * @param response
	 * @return ModelAndView containing Approval details
	 */
	public ModelAndView getEntity(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
		input.setUniversityId((String) session.getAttribute("universityId"));
		List<ProgramCourseTypeSummaryInfoGetter> details = marksApprovalService
		.getEntity(input);
		return new ModelAndView("ProgramCourseTypeSummary/programCourseTypeSummary", "resultObject",
				details);
	}
	
	/**
	 * This method get CourseEmployee from database
	 * @author Devendra Singhal
	 * @param request
	 * @param response
	 * @return ModelAndView containing message
	 */
	public ModelAndView getCourseEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		
		MarksApprovalInfo input = new MarksApprovalInfo();
		//Program code setter is used to set session start date
		input.setProgramCode((String) session.getAttribute("startDate"));
		//Branch code setter is used to set session end date
		input.setBranchCode((String) session.getAttribute("endDate"));
		input.setEmployeeCode(request.getParameter("employeeCode"));
		input.setEntityCode(request.getParameter("entity"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setProgramCourseKey(request.getParameter("programCourseKey"));		
		input.setUniversityCode(universityId);
		String count=marksApprovalService.getCourseEmployee(input);		
		return new ModelAndView("enrollment/info", "info",count);
	}
	
	/**
	 * This method get program details from database
	 * @author Devendra Singhal
	 * @param request
	 * @param response
	 * @return ModelAndView containing Approval details
	 */
	public ModelAndView checkSequence(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		MarksApprovalInfo input = new MarksApprovalInfo();
		input.setEntityCode(request.getParameter("entity"));
		input.setCourseCode(request.getParameter("courseCode"));
		input.setProgramCourseKey(request.getParameter("programCourseKey"));	
		input.setApprovalOrder(Integer.parseInt(request.getParameter("approvalOrder")));
		input.setSequenceNo(request.getParameter("sequence"));	
		input.setEvaluationId(request.getParameter("displaay"));
		String message=marksApprovalService.checkSequence(input);		
		return new ModelAndView("enrollment/info", "info",message);
	}
	
	/**
	 * This method get Display type
	 * @author Devendra Singhal
	 * @param request
	 * @param response
	 * @return ModelAndView containing Display type details
	 */
	public ModelAndView getDisplayType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String universityId=(String) session.getAttribute("universityId");
		if (universityId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		MarksApprovalInfo input = new MarksApprovalInfo();
		input.setUniversityCode(universityId);
		List<AwardSheetInfoGetter> list=marksApprovalService.getDisplayType(input);		
		return new ModelAndView("awardsheet/EvaluationComponentList", "result",list);
	}
}