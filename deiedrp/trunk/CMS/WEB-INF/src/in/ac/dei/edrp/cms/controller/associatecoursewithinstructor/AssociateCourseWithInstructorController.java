/**
 * @(#) AssociateCourseWithInstructorController.java
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

package in.ac.dei.edrp.cms.controller.associatecoursewithinstructor;


import in.ac.dei.edrp.cms.dao.associatecoursewithinstructor.AssociateCourseWithInstructorDAO;
import in.ac.dei.edrp.cms.domain.associatecoursewithinstructor.AssociateCourseWithInstructor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the Associate Course With Instructor information 
 * @author Ankit Jain
 * @date 02 March 2011
 * @version 1.0
 */
public class AssociateCourseWithInstructorController extends MultiActionController{

	private AssociateCourseWithInstructorDAO associateCourseWithInstructorDao;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param associateCourseWithInstructorDao
     */
	public void setAssociateCourseWithInstructorDAO(AssociateCourseWithInstructorDAO associateCourseWithInstructorDao) {
		this.associateCourseWithInstructorDao = associateCourseWithInstructorDao;
	}
	
	/**
     * Method to get the entity list.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView entityList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId(session.getAttribute("universityId").toString());
		List<AssociateCourseWithInstructor> entityList = associateCourseWithInstructorDao.getEntityList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/EntityList","entityList", entityList);
	}
	
	/**
     * Method to get the Program List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView programList(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setEntityId(request.getParameter("entityId"));
		List<AssociateCourseWithInstructor> programNameList = associateCourseWithInstructorDao.getProgramList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/ProgramList","programNameList", programNameList);
	}
	
	/**
     * Method to get the Branch List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView branchList(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId(universityId);
		associateCourseWithInstructor.setEntityId(request.getParameter("entityId"));
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		List<AssociateCourseWithInstructor> branchList = associateCourseWithInstructorDao.getBranchList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/branchList","branchList", branchList);
	}
	
	/**
     * Method to get the Specialization List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView specializationList(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId(universityId);
		associateCourseWithInstructor.setEntityId(request.getParameter("entityId"));
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		associateCourseWithInstructor.setBranchId(request.getParameter("branchId"));
		List<AssociateCourseWithInstructor> specializationList = associateCourseWithInstructorDao.getSpecializationList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/SpecializationList","specializationList", specializationList);
	}
	
	/**
     * Method to get the Semester List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView semesterList(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId(universityId);
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		List<AssociateCourseWithInstructor> semesterList = associateCourseWithInstructorDao.getSemesterList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/SemesterList","semesterList", semesterList);
	}
	
	/**
     * Method to get the Semester Dates.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView semesterDates(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId(session.getAttribute("universityId").toString());
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		associateCourseWithInstructor.setBranchId(request.getParameter("branchId"));
		associateCourseWithInstructor.setSpecializationId(request.getParameter("specializationId"));
		associateCourseWithInstructor.setSemesterCode(request.getParameter("semesterCode"));
		
		List<AssociateCourseWithInstructor> semesterDate = associateCourseWithInstructorDao.getSemesterDates(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/SemesterDate","semesterDate", semesterDate);
	}
	
	/**
     * Method to get the Course Instructor Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView courseInstructorDetails(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setEntityId(request.getParameter("selectedEntityId"));
		String selectedEntityId=request.getParameter("selectedEntityId");
		associateCourseWithInstructor.setUniversityId(selectedEntityId.substring(0, 4));
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		associateCourseWithInstructor.setBranchId(request.getParameter("branchId"));
		associateCourseWithInstructor.setSpecializationId(request.getParameter("specializationId"));
		associateCourseWithInstructor.setSemesterCode(request.getParameter("semesterCode"));
		associateCourseWithInstructor.setSemesterStartDate(request.getParameter("semesterStartDate"));
		associateCourseWithInstructor.setSemesterEndDate(request.getParameter("semesterEndDate"));
		List<AssociateCourseWithInstructor> courseInstructorDetails = associateCourseWithInstructorDao.getCourseInstructorDetails(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/CourseInstructorDetails","courseInstructorDetails", courseInstructorDetails);
	}
	
	/**
     * Method to get the Employee List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView employeeList(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setUniversityId("E"+request.getParameter("entityId").substring(0, 4)+"%");
		List<AssociateCourseWithInstructor> employeeList = associateCourseWithInstructorDao.getEmployeeList(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/EmployeeList","employeeList", employeeList);
	}
	
	/**
     * Method to assign the instructor to a course.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView AssignEmployee(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setCreatorId(session.getAttribute("userId").toString());
		String courseDataTokens =request.getParameter("selectedData");
		
		String message = associateCourseWithInstructorDao.assignEmployee(associateCourseWithInstructor, courseDataTokens);
		return new ModelAndView("associatecoursewithinstructor/Result","message", message);
	}
	
	/**
     * Method to get the Course Instructor Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView InstructorDetails(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setEntityId(request.getParameter("selectedEntityId"));
		String selectedEntityId=request.getParameter("selectedEntityId");
		associateCourseWithInstructor.setUniversityId(selectedEntityId.substring(0, 4));
		associateCourseWithInstructor.setProgramId(request.getParameter("programId"));
		associateCourseWithInstructor.setBranchId(request.getParameter("branchId"));
		associateCourseWithInstructor.setSpecializationId(request.getParameter("specializationId"));
		associateCourseWithInstructor.setSemesterCode(request.getParameter("semesterCode"));
		associateCourseWithInstructor.setSemesterStartDate(request.getParameter("semesterStartDate"));
		associateCourseWithInstructor.setSemesterEndDate(request.getParameter("semesterEndDate"));

		List<AssociateCourseWithInstructor> courseInstructorDetails = associateCourseWithInstructorDao.getInstructorDetails(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/CourseInstructorDetails","courseInstructorDetails", courseInstructorDetails);
	}
	
	/**
     * Method to Update the instructor details
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView UpdateInstructor(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		associateCourseWithInstructor.setEntityId(request.getParameter("selectedEntityId"));
		associateCourseWithInstructor.setProgramCourseKey(request.getParameter("programCourseKey"));
		associateCourseWithInstructor.setCourseCode(request.getParameter("courseCode"));
		associateCourseWithInstructor.setEmployeeCode(request.getParameter("employeeCode"));
		associateCourseWithInstructor.setSemesterStartDate(request.getParameter("semesterStartDate"));
		associateCourseWithInstructor.setSemesterEndDate(request.getParameter("semesterEndDate"));
		associateCourseWithInstructor.setStatus(request.getParameter("status"));
		associateCourseWithInstructor.setCreatorId(session.getAttribute("userId").toString());		
		
		String message = associateCourseWithInstructorDao.updateEmployee(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/Result","message", message);
	}
	
	/**
     * Method to delete the Course Instructor Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView deleteCourseInstructor(HttpServletRequest request,
			HttpServletResponse response) {
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String courseDataTokens =request.getParameter("selectedData");		
		String message = associateCourseWithInstructorDao.deleteCourseInstructor(associateCourseWithInstructor, courseDataTokens);
		return new ModelAndView("associatecoursewithinstructor/Result","message", message);
	}
	
	/**
     * Method to get the session details
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		
		
		associateCourseWithInstructor.setEntityId(request.getParameter("entityId"));
		associateCourseWithInstructor.setUniversityId(request.getParameter("entityId").substring(0, 4));
		List<AssociateCourseWithInstructor> sessionDetails = associateCourseWithInstructorDao.getSessionDetails(associateCourseWithInstructor);
		return new ModelAndView("activitymaster/SessionDetails","sessionDetails", sessionDetails);
	}
	
	/**
     * Method to BUild the Instructor Course Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView BuildInstructorCourse(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		AssociateCourseWithInstructor associateCourseWithInstructor= new AssociateCourseWithInstructor();
		associateCourseWithInstructor.setSessionStartDate(request.getParameter("sessionStartDate"));
		associateCourseWithInstructor.setSessionEndDate(request.getParameter("sessionEndDate"));
		associateCourseWithInstructor.setEntityId(request.getParameter("entityId"));
		associateCourseWithInstructor.setUniversityId(request.getParameter("entityId").substring(0, 4));
		associateCourseWithInstructor.setCreatorId(session.getAttribute("userId").toString());
		
		String message = associateCourseWithInstructorDao.buildInstructorCourse(associateCourseWithInstructor);
		return new ModelAndView("associatecoursewithinstructor/Result","message", message);
	}
}

