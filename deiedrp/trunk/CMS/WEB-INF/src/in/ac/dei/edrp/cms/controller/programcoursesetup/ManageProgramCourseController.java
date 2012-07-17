/*
 * @(#) ManageProgramCourseController.java
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
package in.ac.dei.edrp.cms.controller.programcoursesetup;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programcoursesetup.ManageProgramCourseSetupDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 12-Jan-2011
 * The behavior of this class is as Cotroller.
 * This class is used for managing program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ManageProgramCourseController extends MultiActionController{

	static final Logger logger = Logger.getLogger(ManageProgramCourseController.class);
	
	private ManageProgramCourseSetupDAO manageprogramcoursesetupDAO;
	/** 
	 * Method setManageProgramCourseSetupDAO is used for setting the reference of a implemented class.
	 * @param manageprogramcoursesetupDAO 
	 * 
	 */
	public void setManageProgramCourseSetupDAO(ManageProgramCourseSetupDAO manageprogramcoursesetupDAO) {
	this.manageprogramcoursesetupDAO = manageprogramcoursesetupDAO;
	}
	/** 
	 * Method programCourseHeaderList is used for getting the program course header list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView programCourseHeaderList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<ProgramMaster> programCourseHeaderList = null;
		try{
			programCourseHeaderList = manageprogramcoursesetupDAO.getProgramCourseHeaderList(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/ProgramCourseHeader","programCourseHeaderList", programCourseHeaderList);
		}
	/** 
	 * Method programCourseDetails is used for getting the program course details of selected
	 * program header.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView programCourseDetails(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String programCourseKey = request.getParameter("programCourseKey");
		List<CourseDetails> programCourseDetails = null;
		try{
			programCourseDetails = manageprogramcoursesetupDAO.getProgramCourseDetails(programCourseKey);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/ProgramCourseDetails","programCourseDetails", programCourseDetails);
		}
	/** 
	 * Method programCourseCtgOptGrp is used for getting the category, group and option of
	 * program course
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView programCourseCtgOptGrp(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<CourseDetails> programCourseCtgOptGrp = null;
		try{
			programCourseCtgOptGrp = manageprogramcoursesetupDAO.getProgramCourseCtgOptGrp(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/ProgramCourseCtgOptGrp","programCourseCtgOptGrp", programCourseCtgOptGrp);
		}
	/** 
	 * Method updateCourseCtgOptGrp is used for updating the category, group, option 
	 * and availability of program course
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView updateCourseCtgOptGrp(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
	//	String message="fail";
		String availiable ;
		CourseDetails programCourseCtgOptGrp = new CourseDetails();
		programCourseCtgOptGrp.setProgramCourseKey(request.getParameter("program_course_key"));
		programCourseCtgOptGrp.setCourseCode(request.getParameter("course_code"));
		programCourseCtgOptGrp.setCourseCategory(request.getParameter("course_category"));
		programCourseCtgOptGrp.setCourseGroup(request.getParameter("course_group"));
		
		availiable = request.getParameter("course_availability");
//		availiable = (Boolean.valueOf(availiable)==true) ? "Y":"N";		
		programCourseCtgOptGrp.setCourseAvailability(availiable);
		
		programCourseCtgOptGrp.setModifierId((String)request.getSession().getAttribute("userId"));
	//	try{
		String message=manageprogramcoursesetupDAO.updateProgramCourseCtgOptGrp(programCourseCtgOptGrp);
//			message="success";
//		}catch(MyException e){
//			logger.error(e);
//		}
		return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
		}
		
	/** 
	 * Method deleteProgramCourse is used for deleting the selected program course
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView deleteProgramCourse(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String message="fail";
		String programCourseKey = request.getParameter("program_course_key");
		String courseCodes = request.getParameter("courseCodes");
		try{
			message=manageprogramcoursesetupDAO.deleteDesiredProgramCourse(programCourseKey, courseCodes);
			//message="success";
		}catch(MyException e){
			logger.error(e);
			message="deleteError"+e.getMessage();
		}
			return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
		}
	/** 
	 * Method changeStatusProgramHeader is used for changing the status of program course
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView changeStatusProgramHeader(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String message="fail";
		String programCourseKeys = request.getParameter("programcoursekeys");
		try{
			manageprogramcoursesetupDAO.changeStatusDesiredProgramCourseHeader(programCourseKeys,
					(String)request.getSession().getAttribute("userId"));
			message="success";
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
		}
	
}
