/**
 * @(#) CourseMasterController.java
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
package in.ac.dei.edrp.cms.controller.coursemaster;

import java.util.List;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.coursemaster.CourseMasterService;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * this is Server side controller class for Course Master
 * 
 * @version 1.0 21 FEB 2011
 * @author MOHD AMIR
 */
public class CourseMasterController extends MultiActionController {

	/** creating object of CourseMasterService interface */
	private CourseMasterService courseMasterService;

	/** defining setter method for object of CourseMasterService interface */
	public void setCourseMasterService(CourseMasterService courseMasterService) {
		this.courseMasterService = courseMasterService;
	}

	/**
	 * This method is used for getting list of component and mapping to jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing component list
	 */
	public ModelAndView getComponents(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setGroupCode(request.getParameter("groupCode"));

		List<UnivRoleInfoGetter> componentList = courseMasterService
				.getComponents(courseMasterBean);
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				componentList);
	}

	/**
	 * This method is used for getting list of Entities and mapping to jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Entities list
	 */
	public ModelAndView getEntities(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		List<CourseMasterBean> entityList = courseMasterService
				.getEntities(session.getAttribute("universityId").toString());
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				entityList);
	}

	/**
	 * This method is used for getting list of Programs and mapping to jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Programs list
	 */
	public ModelAndView getEntityPrograms(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		List<CourseMasterBean> programList = courseMasterService
				.getPrograms(request.getParameter("entityId"));
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				programList);
	}

	/**
	 * This method is used for getting list of Branch and mapping to jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Branch list
	 */
	public ModelAndView getProgramBranches(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean=new CourseMasterBean();
		
		courseMasterBean.setComponentId(request.getParameter("programId"));
		courseMasterBean.setUniversityCode(session.getAttribute("universityId").toString());
		
		List<CourseMasterBean> branchList = courseMasterService
				.getBranch(courseMasterBean);
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				branchList);
	}

	/**
	 * This method is used for getting list of Specializations and mapping to
	 * jsp
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Specializations list
	 */
	public ModelAndView getProgramSpecializations(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId").toString());
		courseMasterBean.setOwnerProgramId(request.getParameter("programId"));
		courseMasterBean.setOwnerBranchId(request.getParameter("branchId"));

		List<CourseMasterBean> specializationList = courseMasterService
				.getSpecialization(courseMasterBean);
		return new ModelAndView("coursemaster/componentInfo", "detailsList",
				specializationList);
	}

	/**
	 * This method is used for checking for duplicate Course and mapping result
	 * to jsp
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing duplicate record count
	 */
	public ModelAndView getDuplicateCourseCount(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();
		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setCourseCode(request.getParameter("courseCode"));

		int count = courseMasterService
				.getDuplicateCourseCount(courseMasterBean);
		return new ModelAndView("systemtabletwo/countInfo", "count", count);
	}

	/**
	 * This method is used for inserting Course details to database
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public ModelAndView setCourseDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
			
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setUserId(session.getAttribute("userId").toString());

		courseMasterBean.setOwnerEntityId(request.getParameter("entityId"));
		courseMasterBean.setOwnerProgramId(request.getParameter("programId"));
		courseMasterBean.setOwnerBranchId(request.getParameter("branchId"));
		courseMasterBean.setOwnerSpecializationId(request
				.getParameter("specializationId"));
		courseMasterBean.setCourseCode(request.getParameter("courseCode").trim());
		courseMasterBean.setCourseName(request.getParameter("courseName").trim());
		courseMasterBean.setCourseGroupId(request.getParameter("courseGroup"));
		courseMasterBean.setCourseClassificationId(request
				.getParameter("courseClassification"));
		courseMasterBean.setCourseTypeId(request.getParameter("courseType"));
		courseMasterBean.setLectures(request.getParameter("lectures"));
		courseMasterBean.setPracticals(request.getParameter("practicals"));
		courseMasterBean.setTutorials(request.getParameter("tutorials"));
		courseMasterBean.setCredits(request.getParameter("credits"));
		courseMasterBean.setUnits(request.getParameter("units"));
		courseMasterBean
				.setMarksContEval(request.getParameter("internalMarks"));
		courseMasterBean.setMarksEndSemester(request
				.getParameter("externalMarks"));
		courseMasterBean.setMarksTotal(request.getParameter("totalMarks"));
		courseMasterBean.setDummyFlag(request.getParameter("dummyFlag"));
		courseMasterBean.setSinceSession(request.getParameter("sinceSession"));
		courseMasterBean.setResultSystem(request.getParameter("resultSystem"));
		/*gradeLimit added By Mandeep*/
		courseMasterBean.setGradeLimit(request.getParameter("gradeLimit"));
		courseMasterBean.setEdeiStatus(request.getParameter("edeiStatus"));
		try{
		courseMasterService.setCourseDetails(courseMasterBean);
	
		return new ModelAndView("general/SessionInactive", "sessionInactive",
				false);
		}
		catch(Exception e){
            return new ModelAndView("RegistrationForm/RegisterStudent",
                    "result", e.getMessage());
		}

	}

	/**
	 * This method is used for getting Course Details and mapping to jsp file
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing Course Details
	 * @throws Exception 
	 */
	public ModelAndView getCourseDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setOwnerEntityId(request.getParameter("entityId"));
		courseMasterBean.setOwnerProgramId(request.getParameter("programId"));
		courseMasterBean.setOwnerBranchId(request.getParameter("branchId"));
		courseMasterBean.setOwnerSpecializationId(request
				.getParameter("specializationId"));
		courseMasterBean.setCourseCode(request.getParameter("courseCode"));

		List<CourseMasterBean> detailsList = courseMasterService
				.getCourseDetails(courseMasterBean);
		return new ModelAndView("coursemaster/courseInfo", "detailsList",
				detailsList);
	}

	/**
	 * This method is used for updating Course details from database
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing no of record updated
	 * @throws Exception 
	 */
	public ModelAndView updateCourseDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setUserId(session.getAttribute("userId").toString());

		courseMasterBean.setCourseCode(request.getParameter("courseCode"));
		courseMasterBean.setCourseName(request.getParameter("courseName"));
		courseMasterBean.setCourseGroupId(request.getParameter("courseGroup"));
		courseMasterBean.setCourseClassificationId(request
				.getParameter("courseClassification"));
		courseMasterBean.setCourseTypeId(request.getParameter("courseType"));
		courseMasterBean.setLectures(request.getParameter("lectures"));
		courseMasterBean.setPracticals(request.getParameter("practicals"));
		courseMasterBean.setTutorials(request.getParameter("tutorials"));
		courseMasterBean.setCredits(request.getParameter("credits"));
		courseMasterBean.setUnits(request.getParameter("units"));
		courseMasterBean
				.setMarksContEval(request.getParameter("internalMarks"));
		courseMasterBean.setMarksEndSemester(request
				.getParameter("externalMarks"));
		courseMasterBean.setMarksTotal(request.getParameter("totalMarks"));
		courseMasterBean.setDummyFlag(request.getParameter("dummyFlag"));
		courseMasterBean.setSinceSession(request.getParameter("sinceSession"));
		courseMasterBean.setResultSystem(request.getParameter("resultSystem"));
		/*gradeLimit added By Mandeep*/
		courseMasterBean.setGradeLimit(request.getParameter("gradeLimit"));
		courseMasterBean.setEdeiStatus(request.getParameter("edeiStatus"));
     try{
		int count = courseMasterService.updateCourseDetails(courseMasterBean);

		return new ModelAndView("systemtabletwo/countInfo", "count", count);
     }
     catch(Exception e){
         return new ModelAndView("RegistrationForm/RegisterStudent",
                 "result", e.getMessage());  	 
     }
	}

	/**
	 * This method is used for deleting course details from database
	 * 
	 * @param request
	 * @param response
	 * @return Model and View containing no of record deleted
	 * @throws Exception 
	 */
	public ModelAndView deleteCourseDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		CourseMasterBean courseMasterBean = new CourseMasterBean();

		courseMasterBean.setUniversityCode(session.getAttribute("universityId")
				.toString());
		courseMasterBean.setCourseCode(request.getParameter("courseCode"));
         try{
		int count = courseMasterService.deleteCourseDetails(courseMasterBean);
		return new ModelAndView("systemtabletwo/countInfo", "count", count);
         }
         catch(Exception e){
             return new ModelAndView("RegistrationForm/RegisterStudent",
                     "result", e.getMessage()); 
         }
	}
}
