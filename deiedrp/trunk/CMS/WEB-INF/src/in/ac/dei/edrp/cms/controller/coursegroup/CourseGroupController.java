/**
 * @(#) CourseGroupController.java
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
package in.ac.dei.edrp.cms.controller.coursegroup;

import in.ac.dei.edrp.cms.dao.coursegroup.CourseGroupService;
import in.ac.dei.edrp.cms.domain.coursegroup.CourseGroupBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Course Group
 * 
 * @version 1.0 19 FEB 2011
 * @author MOHD AMIR
 */
public class CourseGroupController extends MultiActionController {

	/** creating object of courseGroupService interface */
	private CourseGroupService courseGroupService;

	/** defining setter method for object of CourseGroupService interface */
	public void setCourseGroupService(CourseGroupService courseGroupService) {
		this.courseGroupService = courseGroupService;
	}

	/**
	 * This method get list of course group from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing course Group List
	 */
	public ModelAndView getGroupList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseGroupBean courseGroupBean = new CourseGroupBean();

		courseGroupBean.setUniversityId(session.getAttribute("universityId")
				.toString());
		courseGroupBean.setProgramId(request.getParameter("programId"));
		courseGroupBean.setBranchId(request.getParameter("branchId"));
		courseGroupBean.setSpecializationId(request
				.getParameter("specializationId"));
		courseGroupBean.setSemesterCode(request.getParameter("semesterId"));

		List<StudentNumbersInfoGetter> programCourseKeyInfo = courseGroupService
				.getProgramCourseKey(courseGroupBean);

		if (programCourseKeyInfo.size() > 0) {
			courseGroupBean.setProgramCourseKey(programCourseKeyInfo.get(0)
					.getProgramCourseKey());
		}

		List<CourseGroupBean> courseGroupList = courseGroupService
				.getCourseGroupList(courseGroupBean);

		return new ModelAndView("coursegroup/groupList", "courseGroupList",
				courseGroupList);
	}

	/**
	 * This method delete course group details from database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing number of records deleted
	 */
	public ModelAndView deleteGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {
		CourseGroupBean courseGroupBean = new CourseGroupBean();

		courseGroupBean.setProgramCourseKey(request
				.getParameter("programCourseKey"));
		try{
		int deleteCount = 0;
		StringTokenizer groupCodesTokenizer = new StringTokenizer(
				request.getParameter("courseGroupCodes"), "\\|");
		while (groupCodesTokenizer.hasMoreTokens()) {
			courseGroupBean.setCourseGroupCode(groupCodesTokenizer.nextToken());
			deleteCount += courseGroupService
					.deleteCourseGroupDetails(courseGroupBean);
		}
		return new ModelAndView("systemtabletwo/countInfo", "count",
				deleteCount);
		}
		catch(Exception e){
			return new ModelAndView("RegistrationForm/RegisterStudent",
            "result", "ErrorInDelete"+e);
			}
		
	}

	/**
	 * This method update course group details from database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing number of records updated
	 */
	public ModelAndView updateGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseGroupBean courseGroupBean = new CourseGroupBean();
		courseGroupBean.setUniversityId(session.getAttribute("universityId")
				.toString());
		courseGroupBean.setProgramCourseKey(request
				.getParameter("programCourseKey"));
		courseGroupBean.setCourseGroupCode(request
				.getParameter("courseGroupCode"));
		courseGroupBean.setMaxCredits(request.getParameter("maxCredits"));
		courseGroupBean.setMinCredits(request.getParameter("minCredits"));
		courseGroupBean.setElective(request.getParameter("elective"));
		courseGroupBean.setOrderInMarksheet(request.getParameter("orderInMarksheet"));
		courseGroupBean.setUserId(session.getAttribute("userId").toString());
		try{
		int updateCount = courseGroupService
				.updateCourseGroupDetails(courseGroupBean);

		return new ModelAndView("systemtabletwo/countInfo", "count",
				updateCount);
		}
		catch(Exception e){
			return new ModelAndView("RegistrationForm/RegisterStudent",
            "result", "ErrorInUpdate"+e);
			}
	}

	/**
	 * This method get course group details from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing course Group Details
	 */
	public ModelAndView getGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseGroupBean courseGroupBean = new CourseGroupBean();

		courseGroupBean.setUniversityId(session.getAttribute("universityId")
				.toString());
		courseGroupBean.setProgramId(request.getParameter("programId"));
		courseGroupBean.setBranchId(request.getParameter("branchId"));
		courseGroupBean.setSpecializationId(request
				.getParameter("specializationId"));
		
		courseGroupBean.setSemesterCode(request.getParameter("semesterId"));

		List<StudentNumbersInfoGetter> programCourseKeyInfo = courseGroupService
				.getProgramCourseKey(courseGroupBean);

		courseGroupBean.setProgramCourseKey(programCourseKeyInfo.get(0)
				.getProgramCourseKey());

		List<CourseGroupBean> courseGroupDetails = courseGroupService
				.getCourseGroupDetails(courseGroupBean);

		return new ModelAndView("coursegroup/courseGroupInfo",
				"courseGroupDetails", courseGroupDetails);
	}

	/**
	 * This method get no of duplicate record for given course group and program
	 * course key
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing no of record found
	 */
	public ModelAndView getDuplicateRecordCount(HttpServletRequest request,
			HttpServletResponse response) {
		CourseGroupBean courseGroupBean = new CourseGroupBean();

		courseGroupBean.setProgramId(request.getParameter("programId"));
		courseGroupBean.setBranchId(request.getParameter("branchId"));
		courseGroupBean.setSpecializationId(request
				.getParameter("specializationId"));
		courseGroupBean.setSemesterCode(request.getParameter("semesterId"));
		courseGroupBean.setCourseGroupCode(request
				.getParameter("courseGroupCode"));
		List<StudentNumbersInfoGetter> programCourseKeyInfo = courseGroupService
				.getProgramCourseKey(courseGroupBean);
		courseGroupBean.setProgramCourseKey(programCourseKeyInfo.get(0)
				.getProgramCourseKey());
		
		int count = courseGroupService
				.getDuplicateCourseGroupCount(courseGroupBean);

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method set course group details into database
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView setGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseGroupBean courseGroupBean = new CourseGroupBean();

		courseGroupBean.setProgramId(request.getParameter("programId"));
		courseGroupBean.setBranchId(request.getParameter("branchId"));
		courseGroupBean.setSpecializationId(request
				.getParameter("specializationId"));
		courseGroupBean.setSemesterCode(request.getParameter("semesterId"));
		courseGroupBean.setCourseGroupCode(request
				.getParameter("courseGroupCode"));
		courseGroupBean.setMaxCredits(request.getParameter("maxCredits"));
		courseGroupBean.setMinCredits(request.getParameter("minCredits"));
		courseGroupBean.setElective(request.getParameter("elective"));
		courseGroupBean.setOrderInMarksheet(request.getParameter("orderInMarksheet"));
		courseGroupBean.setUserId(session.getAttribute("userId").toString());

		List<StudentNumbersInfoGetter> programCourseKeyInfo = courseGroupService
				.getProgramCourseKey(courseGroupBean);

		courseGroupBean.setProgramCourseKey(programCourseKeyInfo.get(0)
				.getProgramCourseKey());
		
		try{
			courseGroupService.setCourseGroupDetails(courseGroupBean);
			return new ModelAndView("general/SessionInactive", "sessionInactive",
				false);
			}
		catch(Exception e){
			return new ModelAndView("RegistrationForm/RegisterStudent",
            "result", "ErrorInInsert"+e);
			}
		}
}