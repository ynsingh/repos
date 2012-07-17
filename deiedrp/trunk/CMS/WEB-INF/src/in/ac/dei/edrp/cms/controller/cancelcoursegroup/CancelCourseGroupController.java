/**
 * @(#) CancelCourseGroupController.java
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
package in.ac.dei.edrp.cms.controller.cancelcoursegroup;

import in.ac.dei.edrp.cms.dao.cancelcoursegroup.CancelCourseGroupService;
import in.ac.dei.edrp.cms.domain.adddropcourse.AddDropCourseBean;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the Cancel Course Group information
 * @author Devendra Singhal
 * @date 29 March 2012
 * @version 1.0
 */
public class CancelCourseGroupController extends MultiActionController{
	/** creating object of CancelCourseGroupService interface */
	private CancelCourseGroupService cancelCourseGroupService;

	/** defining setter method for object of interface */
	public void setCancelCourseGroupService(
			CancelCourseGroupService cancelCourseGroupService) {
		this.cancelCourseGroupService = cancelCourseGroupService;
	}
	
	/**
	 * This method get student course Group list for Cancellation from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course Group list
	 */
	public ModelAndView getCancelCourseGroup(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");		
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		input.setRollNumber(request.getParameter("rollNumber"));
		input.setSessionStartDate((String) session.getAttribute("startDate"));
		input.setSessionEndDate((String) session.getAttribute("endDate"));
		input.setUniversityId(universityId);				
		List<AddDropCourseBean> detailsList = cancelCourseGroupService.getStudentCourseGroupCancel(input);		
		return new ModelAndView("adddropcourse/componentInfo", "detailsList",
				detailsList);
	}
	
	/**
	 * This method get student course Code list  for Cancellation from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course Code list
	 */
	public ModelAndView getCancelCourseCode(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");		
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		input.setRollNumber(request.getParameter("rollNumber"));
		input.setSemesterStartDate(request.getParameter("startDate"));
		input.setSemesterEndDate(request.getParameter("endDate"));
		input.setProgramName(request.getParameter("program"));
		input.setCourseGroup(request.getParameter("group"));
		input.setUniversityId(universityId);			
		List<AddDropCourseBean> detailsList = cancelCourseGroupService.getStudentCourseCodeCancel(input);		
		return new ModelAndView("adddropcourse/componentInfo", "detailsList",
				detailsList);
	}
	
	/**
	 * This method get student course Group list  to Insert from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course Code list
	 */
	public ModelAndView getAddCourseGroup(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");		
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		input.setRollNumber(request.getParameter("rollNumber"));
		input.setSemesterStartDate(request.getParameter("startDate"));
		input.setSemesterEndDate(request.getParameter("endDate"));
		input.setProgramName(request.getParameter("program"));
		input.setUniversityId(universityId);			
		List<AddDropCourseBean> detailsList = cancelCourseGroupService.getStudentCourseGroupAdd(input);	
		return new ModelAndView("adddropcourse/componentInfo", "detailsList",
				detailsList);
	}
	
	/**
	 * This method get student course Code list  to Insert from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course Code list
	 */
	public ModelAndView getAddCourseCode(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");		
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		input.setRollNumber(request.getParameter("rollNumber"));
		input.setSemesterStartDate(request.getParameter("startDate"));
		input.setSemesterEndDate(request.getParameter("endDate"));
		input.setProgramName(request.getParameter("program"));
		input.setCourseGroup(request.getParameter("group"));		
		input.setUniversityId(universityId);			
		List<AddDropCourseBean> detailsList = cancelCourseGroupService.getStudentCourseCodeAdd(input);	
		return new ModelAndView("adddropcourse/componentInfo", "detailsList",
				detailsList);
	}
	
	/**
	 * This method get student course Code list  to Insert from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student course Code list
	 */
	public ModelAndView cancelCourseGroup(HttpServletRequest request,
			HttpServletResponse response) {
		AddDropCourseBean input=new AddDropCourseBean();
		HttpSession session = request.getSession(true);
		String universityId=(String) session.getAttribute("universityId");		
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		input.setRollNumber(request.getParameter("rollNum"));
		input.setSemesterStartDate(request.getParameter("startDate"));
		input.setSemesterEndDate(request.getParameter("endDate"));
		input.setProgramName(request.getParameter("program"));
		input.setEntityId(request.getParameter("entity"));
		input.setUserId((String) session.getAttribute("userId"));
		input.setSessionStartDate((String) session.getAttribute("startDate"));
		input.setSessionEndDate((String) session.getAttribute("endDate"));
		String theoryCredits=request.getParameter("totaoAddTheoryCredit");
		String practicalCredits=request.getParameter("totalAddPracticalCredit");
		String totalAddCredit=request.getParameter("totalAddCredit");
		String totalCancelCredit=request.getParameter("totalCancelCredit");
		String totalAddCreditExcludingAudit=request.getParameter("totalAddCreditExcludingAudit");
		String totalCancelCreditExcludingAudit=request.getParameter("totalCancelCreditExcludingAudit");
		String cancelTheoryCredit=request.getParameter("cancelTheoryCredit");
		String CancelPracticalCredit=request.getParameter("cancelPracticalCredit");
		String cancelGroup=request.getParameter("cancelCourseGroup");
		String addGroup=request.getParameter("addCourseGroup");
		StringTokenizer courseToken=new StringTokenizer(request.getParameter("addCourse"), "|");
		String flag=request.getParameter("flag");
		input.setUniversityId(universityId);		
		String message = cancelCourseGroupService.cancelCourse(input,theoryCredits,practicalCredits,totalAddCredit,cancelTheoryCredit,CancelPracticalCredit,totalCancelCredit,totalAddCreditExcludingAudit,
				totalCancelCreditExcludingAudit,cancelGroup,addGroup,courseToken,flag);			
		return new ModelAndView("buildactivitymaster/Result","message", message);
	}
}
