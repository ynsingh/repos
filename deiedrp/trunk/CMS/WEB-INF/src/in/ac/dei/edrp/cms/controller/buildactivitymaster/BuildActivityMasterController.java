/**
 * @(#) BuildActivityMasterController.java
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

package in.ac.dei.edrp.cms.controller.buildactivitymaster;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.buildactivitymaster.BuildActivityMasterDAO;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;
import in.ac.dei.edrp.cms.domain.programgroup.ProgramGroup;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the Build activity master information
 * @author Ankit Jain
 * @date 14 Feb 2011
 * @version 1.0
 */
public class BuildActivityMasterController extends MultiActionController{

	private BuildActivityMasterDAO buildActivityMasterDao;


	/**
     * The setter method of the interface associated
     * with this controller
     * @param buildActivityMaster
     */
	public void setBuildActivityMasterDAO(BuildActivityMasterDAO buildActivityMasterDao) {
		this.buildActivityMasterDao = buildActivityMasterDao;
	}

	/**
     * Method will build activity master data for the next session.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getSessionDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setEntityId(session.getAttribute("userId").toString().substring(1, 9));
		activityMasterObject.setUniversityId(session.getAttribute("universityId").toString());
		List<ActivityMaster> sessionDetails = buildActivityMasterDao.getSessionDetails(activityMasterObject);
		System.out.println("inside BuildActivityMaster controller session is "+sessionDetails.get(0).getSessionStartDate()+sessionDetails.get(0).getSessionEndDate());
		return new ModelAndView("buildactivitymaster/SessionDetails","sessionDetails", sessionDetails);
	}

	/**
     * Method will build activity master data for the next session.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView setActivityForNewSession(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setEntityId(session.getAttribute("userId").toString().substring(1, 9));
		activityMasterObject.setUniversityId(session.getAttribute("universityId").toString());
		activityMasterObject.setCreatorId(session.getAttribute("userId").toString());
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));

		String message = buildActivityMasterDao.setActivityForNewSession(activityMasterObject);
		return new ModelAndView("buildactivitymaster/Result","message",message);
	}

	/**
     * Method will check whether activities builded already.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView BuildCheck(HttpServletRequest request,
	HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		String result = buildActivityMasterDao.checkBuildComplete(activityMasterObject);
		return new ModelAndView("buildactivitymaster/Result","message", result);
	}


	/**
     * Method for count the no of activity
     * @param request
     * @param response
     * @return
     */
	public ModelAndView getEntity(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		activityMasterObject.setUniversityId(universityId);
		List<ActivityMaster> entityList=buildActivityMasterDao.getEntity(activityMasterObject);
		return new ModelAndView("activitymaster/Entity","entityList", entityList);
	}

	/**
     * Method to get the Program List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView programList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		List<ActivityMaster> programNameList = buildActivityMasterDao.getProgramList(activityMasterObject);
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
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setProgramId(request.getParameter("programId"));
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		List<ActivityMaster> branchList = buildActivityMasterDao.getBranchList(activityMasterObject);
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
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setProgramId(request.getParameter("programId"));
		activityMasterObject.setBranchId(request.getParameter("branchId"));
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		List<ActivityMaster> specializationList = buildActivityMasterDao.getSpecializationList(activityMasterObject);
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
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setProgramId(request.getParameter("programId"));
		List<ActivityMaster> semesterList = buildActivityMasterDao.getSemesterList(activityMasterObject);
		return new ModelAndView("associatecoursewithinstructor/SemesterList","semesterList", semesterList);
	}

	/**
     * Method to get the Semester List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView buildAllPrograms(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setProgramId(request.getParameter("programId"));
		activityMasterObject.setBranchId(request.getParameter("branchId"));
		activityMasterObject.setSpecializationId(request.getParameter("specializationId"));
		activityMasterObject.setSemesterCode(request.getParameter("semesterCode"));
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		activityMasterObject.setCreatorId(session.getAttribute("userId").toString());
		String message = buildActivityMasterDao.buildAllPrograms(activityMasterObject);
		return new ModelAndView("activitymaster/SubmitFail","message", message);
	}

	/**
     * Method will build activity master data for the next session.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getCurrentSession(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0,4));
		List<ActivityMaster> sessionDetails = buildActivityMasterDao.getSessionDetails(activityMasterObject);
		return new ModelAndView("buildactivitymaster/SessionDetails","sessionDetails", sessionDetails);
	}

	/**
     * Method for fetch the programCourse Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView programCourseHeaderList(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setProgramId(request.getParameter("programId"));
		activityMasterObject.setBranchId(request.getParameter("branchId"));
		activityMasterObject.setSpecializationId(request.getParameter("specializationId"));
		activityMasterObject.setSemesterCode(request.getParameter("semesterCode"));
		List<ActivityMaster> programCourseHeaderList = buildActivityMasterDao.getProgramCourseHeaderList(activityMasterObject);
		return new ModelAndView("activitymaster/ProgramCourseHeader","programCourseHeaderList", programCourseHeaderList);
	}

	/**
     * Method to get the Semester List.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView buildSelectedPrograms(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		activityMasterObject.setProgramId(request.getParameter("programId"));
		activityMasterObject.setBranchId(request.getParameter("branchId"));
		activityMasterObject.setSpecializationId(request.getParameter("specializationId"));
		activityMasterObject.setSemesterCode(request.getParameter("semesterCode"));
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		activityMasterObject.setCreatorId(session.getAttribute("userId").toString());
		String selectedProgramSemesterTokens = request.getParameter("recordArrayColl");
		String message = buildActivityMasterDao.buildSelectedPrograms(activityMasterObject,selectedProgramSemesterTokens);
		return new ModelAndView("activitymaster/SubmitFail","message", message);
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
		ActivityMaster activityMasterObject = new ActivityMaster();
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		try{
			activityMasterObject.setUniversityId(universityId);
			message=buildActivityMasterDao.checkStatus(activityMasterObject);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("buildactivitymaster/Result","message", message);
		}
}
