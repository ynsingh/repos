/**
 * @(#) InstructorCourseBuildController.java
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

package in.ac.dei.edrp.cms.controller.buildinstructorcourse;


import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.buildinstructorcourse.InstructorCourseBuildDAO;
import in.ac.dei.edrp.cms.domain.buildinstructorcourse.InstructorCourseBuild;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * Build Instructor Course information 
 * @author Devendra Singhal
 * @date Nov 24 2011
 * @version 1.0
 */
public class InstructorCourseBuildController extends MultiActionController{
	
	/** create object of InstructorCourseBuildDAO */
	private InstructorCourseBuildDAO instructorCourseBuildDAO;
	
	/**
     * The setter method of the interface associated with this controller
     * @param object of InstructorCourseBuildDAO
     */
	public void setBuildInstructorCourseDao(InstructorCourseBuildDAO instructorCourseBuildDAO) {
		this.instructorCourseBuildDAO = instructorCourseBuildDAO;
	}
	
	/**
     * Method to get the session details
     * @param request
     * @param response
     * @return ModelAndView containing Session Detail
     */
	public ModelAndView getSession(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		InstructorCourseBuild instructorCourseBuild= new InstructorCourseBuild();
		instructorCourseBuild.setUniversityId(universityId);
		List<InstructorCourseBuild> sessionDetails = instructorCourseBuildDAO.getSessionDetails(instructorCourseBuild);
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
		
		InstructorCourseBuild instructorCourseBuild= new InstructorCourseBuild();
		instructorCourseBuild.setSessionStartDate(request.getParameter("sessionStartDate"));
		instructorCourseBuild.setSessionEndDate(request.getParameter("sessionEndDate"));
		instructorCourseBuild.setUniversityId(universityId);
		instructorCourseBuild.setCreatorId(session.getAttribute("userId").toString());
		
		String message = instructorCourseBuildDAO.buildInstructorCourse(instructorCourseBuild);
		return new ModelAndView("associatecoursewithinstructor/Result","message", message);
	}
	
	/** 
	 * Method checkStatus is used for check the previous and current process status for build.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */

	public ModelAndView checkStatus(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();				
		String universityId =(String) session.getAttribute("universityId");	
		String message="";
		InstructorCourseBuild instructorCourseBuild= new InstructorCourseBuild();
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		try{
			instructorCourseBuild.setUniversityId(universityId);
			message=instructorCourseBuildDAO.checkStatus(instructorCourseBuild);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("buildactivitymaster/Result","message", message);
		}
}

