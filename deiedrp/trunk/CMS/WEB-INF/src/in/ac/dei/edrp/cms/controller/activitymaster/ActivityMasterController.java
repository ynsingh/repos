/**
 * @(#) ActivityMasterController.java
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

package in.ac.dei.edrp.cms.controller.activitymaster;

import in.ac.dei.edrp.cms.dao.activitymaster.ActivityMasterDAO;
import in.ac.dei.edrp.cms.domain.activitymaster.ActivityMaster;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving
 * the activity master information 
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class ActivityMasterController extends MultiActionController{

	private ActivityMasterDAO activityMasterDao;
	
	/**
     * The setter method of the interface associated
     * with this controller
     * @param activityMaster
     */
	public void setActivityMasterDAO(ActivityMasterDAO activityMasterDao) {
		this.activityMasterDao = activityMasterDao;
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
		List<ActivityMaster> programCourseHeaderList = activityMasterDao.getProgramCourseHeaderList(activityMasterObject);
		return new ModelAndView("activitymaster/ProgramCourseHeader","programCourseHeaderList", programCourseHeaderList);
	
	}
	
	/**
     * Method for fetch the Semester start & end date Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView SemesterStartEndDate(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		activityMasterObject.setProgramCourseKey(request.getParameter("programCourseKey"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		List<ActivityMaster> semesterStartEndDate = activityMasterDao.getSemesterStartEndDate(activityMasterObject);
		return new ModelAndView("activitymaster/SemesterStartEndDate","semesterStartEndDate", semesterStartEndDate);
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
		
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0,4));
		List<ActivityMaster> sessionDetails = activityMasterDao.getSessionDetails(activityMasterObject);
		return new ModelAndView("activitymaster/SessionDetails","sessionDetails", sessionDetails);
	}
	
	/**
     * Method for fetch the process Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView processList(HttpServletRequest request,	HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject=new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		List<ActivityMaster> processList = activityMasterDao.getProcessList(activityMasterObject);
		return new ModelAndView("activitymaster/ProcessList","processList", processList);
	}
	
	/**
     * Method for fetch the activity Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject=new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		activityMasterObject.setProgramCourseKey(request.getParameter("programCourseKey"));
		activityMasterObject.setProcessCode(request.getParameter("processCode"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
	
		List<ActivityMaster> activityList = activityMasterDao.getActivityList(activityMasterObject);
		return new ModelAndView("activitymaster/ActivityList","activityList", activityList);
	}
	
	/**
     * Method for fetch the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView activityMasterDetails(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setUniversityId(universityId);
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate")); 
		List<ActivityMaster> activityMasterDetail = activityMasterDao.getActivityMasterDetials(activityMasterObject);
		return new ModelAndView("activitymaster/ActivityMasterDetails","activityMasterDetail", activityMasterDetail);
	}
	
	/**
     * Method for save the activity master Details.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView submitData(HttpServletRequest request,	HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setCreatorId(session.getAttribute("userId").toString());
		activityMasterObject.setProgramCourseKey(request.getParameter("programCourseKey"));
		activityMasterObject.setSemesterStartDate(request.getParameter("semesterStartDate"));
		activityMasterObject.setSemesterEndDate(request.getParameter("semesterEndDate"));
		activityMasterObject.setProcessCode(request.getParameter("process"));
		activityMasterObject.setActivityCode(request.getParameter("activity"));
		activityMasterObject.setActivitySequence(request.getParameter("activitySequence"));
		activityMasterObject.setStatus("ACT");
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		
		activityMasterDao.setActivityMasterData(activityMasterObject);
		String message="success";
		return new ModelAndView("activitymaster/SubmitSuccesful","message", message);
	}
	
	/**
     * Method for Update the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView updateActivityMasterData(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setModifierId(session.getAttribute("userId").toString());
		activityMasterObject.setProgramCourseKey(request.getParameter("programCourseKey"));
		activityMasterObject.setSemesterStartDate(request.getParameter("semesterStartDate"));
		activityMasterObject.setSemesterEndDate(request.getParameter("semesterEndDate"));
		activityMasterObject.setProcessCode(request.getParameter("processCode"));
		activityMasterObject.setActivityCode(request.getParameter("activityCode"));
		activityMasterObject.setActivitySequence(request.getParameter("activitySequence"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		
		String message= activityMasterDao.updateActivityMaster(activityMasterObject);
		
		return new ModelAndView("activitymaster/SubmitSuccesful","message", message);
	}
	
	/**
     * Method for delete the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView deleteActivityMasterDetails(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		String message="fail";
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		String activityDataTokens = request.getParameter("recordArrayColl");
		activityMasterDao.deleteActivityMasterDetails(activityMasterObject, activityDataTokens);
		message="success";
		return new ModelAndView("activitymaster/InsertionInfo","message", message);
	}
	
	/**
     * Method for delete the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView ExistingActivitySequence(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		activityMasterObject.setProgramCourseKey(request.getParameter("programCourseKey"));
		activityMasterObject.setProcessCode(request.getParameter("processCode"));
		activityMasterObject.setActivityCode(request.getParameter("activityCode"));
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		List<ActivityMaster> activitySequenceList=activityMasterDao.getExistingActivitySequence(activityMasterObject);
		return new ModelAndView("activitymaster/ActivitySequenceList","activitySequenceList", activitySequenceList);
	}
	
	/**
     * Method for delete the activity master Details.
     * @param request
     * @param response
     * @return
     */
	public ModelAndView ChangeActivityStatus(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		String message="fail";
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setEntityId(request.getParameter("selectedEntityId"));
		String activityDataTokens = request.getParameter("recordArrayColl");
		activityMasterObject.setSessionStartDate(request.getParameter("sessionStartDate"));
		activityMasterObject.setSessionEndDate(request.getParameter("sessionEndDate"));
		activityMasterDao.changeActivityStatus(activityMasterObject, activityDataTokens);
		message="success";
		return new ModelAndView("activitymaster/InsertionInfo","message", message);
	}
	
	/**
     * Method for count the no of activity
     * @param request
     * @param response
     * @return
     */
	public ModelAndView NoOfActivity(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		ActivityMaster activityMasterObject = new ActivityMaster();
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		activityMasterObject.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
//		activityMasterObject.setActivityCode(request.getParameter("activityCode"));
		String activityCount=activityMasterDao.getActivityCount(activityMasterObject);
		return new ModelAndView("activitymaster/NoOfActivity","activityCount", activityCount);
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
		List<ActivityMaster> entityList=activityMasterDao.getEntity(activityMasterObject);
		return new ModelAndView("activitymaster/Entity","entityList", entityList);
	}
}
