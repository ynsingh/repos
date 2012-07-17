/*
 * @(#) ProgramCourseSetupController.java
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

import in.ac.dei.edrp.cms.common.utility.CodeGenerator;
import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programcoursesetup.ProgramCourseSetupDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 06-Jan-2011
 * The behavior of this class is as Cotroller.
 * This class is used for creating program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ProgramCourseSetupController extends MultiActionController{

	static final Logger logger = Logger.getLogger(ProgramCourseSetupController.class);
	
	private ProgramCourseSetupDAO programcoursesetupDAO;
	/** 
	 * Method setProgramCourseSetupDAO is used for setting the reference of a implemented class.
	 * @param programcoursesetupDAO 
	 * 
	 */
	public void setProgramCourseSetupDAO(ProgramCourseSetupDAO programcoursesetupDAO) {
	this.programcoursesetupDAO = programcoursesetupDAO;
	}
	/** 
	 * Method programList is used for getting the program list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView programList(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);		
		
		String universityId =(String) session.getAttribute("universityId");		
		if(universityId == null)
		{
			
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		List<ProgramMaster> programNameList = null;
		try{
			programNameList = programcoursesetupDAO.getProgramNameList(universityId);
			}catch(MyException e){
				logger.error(e);
			}
			return new ModelAndView("programCourseSetup/ProgramNameWithId","programName", programNameList);
		}
	/** 
	 * Method branchList is used for getting the branch list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */
	public ModelAndView branchList(HttpServletRequest request,
			HttpServletResponse response) {
			
		HttpSession session = request.getSession(true);		
		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgramMaster programMaster = new ProgramMaster();
		programMaster.setProgramId(request.getParameter("progId"));
		List<ProgramMaster> branchNameList = null;
		try{
			branchNameList = programcoursesetupDAO.getBranchNameList(programMaster);
		}
		catch(MyException e){
			logger.error(e);
		}
		return new ModelAndView("programCourseSetup/BranchNameWithId","branchNameList", branchNameList);
		}
	/** 
	 * Method semesterList is used for getting the semester list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView semesterList(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);				
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgramMaster programMaster = new ProgramMaster();
		programMaster.setProgramId(request.getParameter("progId"));		
		
		List<ProgramMaster> semesterListWithCode = null;
		try{
			semesterListWithCode = programcoursesetupDAO.getSemesterList(programMaster);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/SemAndSpecial","semAndspecial", semesterListWithCode);
		}
	/** 
	 * Method specializationList is used for getting the specialization list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView specializationList(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgramMaster programMaster = new ProgramMaster();
		programMaster.setProgramId(request.getParameter("progId"));
		programMaster.setBranchId(request.getParameter("branchId"));
		programMaster.setSemesterCode(request.getParameter("semCode"));
		HashSet<ProgramMaster> specializationList = null;
		try{
			specializationList = programcoursesetupDAO.getSpecializationList(programMaster);
		}catch(MyException e){
			logger.error(e);
		}
		return new ModelAndView("programCourseSetup/BranchNameWithId","branchNameList", specializationList);
		}
	/** 
	 * Method courseDetails is used for getting the course details.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView courseDetails(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		ProgramMaster programMaster = new ProgramMaster();
		programMaster.setUniversityId(universityId);
		programMaster.setProgramId(request.getParameter("progId"));
		programMaster.setBranchId(request.getParameter("branchId"));
		programMaster.setSemesterCode(request.getParameter("semCode"));
		programMaster.setSpecializationId(request.getParameter("specialId"));
		List<List<CourseDetails>> courseDetail = null;
		try{
			courseDetail = programcoursesetupDAO.getCourseList(programMaster);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseSetup/CourseDetails","coursedetail", courseDetail);
		}
	/** 
	 * Method existingCourseDetails is used for getting the existing course lists according to the program header.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
		public ModelAndView existingCourseDetails(HttpServletRequest request,
				HttpServletResponse response) {
			ProgramMaster programHeader = new ProgramMaster();
			programHeader.setProgramId(request.getParameter("progId"));
			programHeader.setBranchId(request.getParameter("branchId"));
			programHeader.setSemesterCode(request.getParameter("semCode"));
			programHeader.setSpecializationId(request.getParameter("specialId"));
			List<CourseDetails> programCourseDetails = null;
			try{
				programCourseDetails = programcoursesetupDAO.getExistingProgramCourseDetails(programHeader);
			}catch(MyException e){
				logger.error(e);
			}
				return new ModelAndView("programCourseSetup/ProgramCourseDetails","programCourseDetails", programCourseDetails);
			}
		/** 
		 * Method insertCourseData is used for inserting the program header and course details.
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 * 
		 */
	public ModelAndView insertCourseData(HttpServletRequest request,
			HttpServletResponse response) {
		
		String message="fail";
		HttpSession session = request.getSession();
		String creatorId = (String)session.getAttribute("userId");

		if(creatorId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		ProgramMaster programHeader = new ProgramMaster();
		programHeader.setProgramId(request.getParameter("progId"));
		programHeader.setBranchId(request.getParameter("branchId"));
		programHeader.setSemesterCode(request.getParameter("semCode"));
		programHeader.setSpecializationId(request.getParameter("specialId"));		
		try{
		/**
		 * programCourseKey holds the key of existing program header
		 */
			String programCourseKey = programcoursesetupDAO.checkProgramHeader(programHeader);
			if(programCourseKey == null){
			
			logger.info("program header does not exist");
			/**
			 * generatedProgramCourseKey holds the program course key that is newly created.
			 */
			String generatedProgramCourseKey="";
			String maximumInrementedValue = programcoursesetupDAO.gettingProgramCourseKey();
			if (maximumInrementedValue != null) {
				generatedProgramCourseKey = CodeGenerator.gettingCombineCode(request.getParameter("progId"), Long
						.parseLong(maximumInrementedValue) + 1, 5);
			} else
				generatedProgramCourseKey = CodeGenerator.gettingCombineCode(request.getParameter("progId"), 1, 5);
			programHeader.setProgramCourseKey(generatedProgramCourseKey);
			programHeader.setCreatorId(creatorId);
			programHeader.setModifierId(null);
			programHeader.setSemesterStatus("ACT");
		programcoursesetupDAO.insertProgramCourseHeader(programHeader);
		programcoursesetupDAO.insertProgramCourseDetail(request.getParameter("courseData"),generatedProgramCourseKey,creatorId);
		message="success";
		}
		else
		{
			logger.info("program header exist");
			programcoursesetupDAO.insertProgramCourseDetail(request.getParameter("courseData"),programCourseKey,creatorId);
			message="success";
		}
		}catch(MyException e){
			logger.error(e);
		}
		return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
		}
}
