/*
 * @(#) ManageProgramRegistrationController.java
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
package in.ac.dei.edrp.cms.controller.programregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programregistration.ManageProgramRegistrationDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 23-Feb-2011
 * The behavior of this class is as Controller.
 * This class is used for managing program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ManageProgramRegistrationController extends MultiActionController{

	static final Logger logger = Logger.getLogger(ManageProgramRegistrationController.class);
	
	ProgramRegistrationDetails sessionDate = null;
	private ManageProgramRegistrationDAO manageprogramRegistrationDAO;
	/** 
	 * Method setManageProgramRegistrationDAO is used for setting the reference of a implemented class.
	 * @param manageprogramRegistrationDAO 
	 */
	public void setManageProgramRegistrationDAO(ManageProgramRegistrationDAO manageprogramRegistrationDAO) {
	this.manageprogramRegistrationDAO = manageprogramRegistrationDAO;
	}
	
	public ModelAndView getEntityList(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		ProgramRegistrationDetails registrationDetails=new ProgramRegistrationDetails();
		registrationDetails.setUniversityId(universityId);		
		List<ProgramRegistrationDetails> entityList=manageprogramRegistrationDAO.getEntityList(registrationDetails);
		
		return new ModelAndView("programRegistration/EntityList","entity", entityList);
	}
	
	/** 
	 * Method programList is used for getting the program list of an university.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */

	public ModelAndView programList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();		
		
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<ProgramMaster> programNameList = null;				
		try{
			sessionDate = manageprogramRegistrationDAO.getSessionDate(universityId);	
			sessionDate.setUniversityId(universityId);
			sessionDate.setEntityId(request.getParameter("selectedEntityId"));
			programNameList = manageprogramRegistrationDAO.getProgramNameList(sessionDate);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programRegistration/ProgramList","program", programNameList);
		}

		/** 
		 * Method branchList is used for getting the branch list of an program.
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 */

		public ModelAndView branchList(HttpServletRequest request,
				HttpServletResponse response) {
			
			HttpSession session = request.getSession();		
			
			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			
			List<ProgramMaster> branchNameList = null;
			ProgramMaster programMaster = new ProgramMaster();
			programMaster.setProgramId(request.getParameter("programId"));
			programMaster.setSessionStartDate(sessionDate.getSessionStartDate());
			programMaster.setSessionEndDate(sessionDate.getSessionEndDate());
			programMaster.setUniversityId(universityId);
			programMaster.setEntityId(request.getParameter("selectedEntityId"));
			try{
				branchNameList = manageprogramRegistrationDAO.getBranchNameList(programMaster);
			}catch(MyException e){
				logger.error(e);
			}
				return new ModelAndView("programRegistration/ProgramList","program", branchNameList);
			}
			
			/** 
			 * Method specializationList is used for getting the specialization list of an program.
			 * @param request coming from client side
			 * @param response used for sending response
			 * @return ModelAndView the forward page and model data used in the forward page
			 */

			public ModelAndView specializationList(HttpServletRequest request,
					HttpServletResponse response) {
				
				HttpSession session = request.getSession();		
				
				String universityId =(String) session.getAttribute("universityId");
				if(universityId == null)
				{
					return new ModelAndView("general/SessionInactive","sessionInactive",true);
				}
				
				List<ProgramMaster> specializationNameList = null;
				ProgramMaster programMaster = new ProgramMaster();
				programMaster.setProgramId(request.getParameter("programId"));
				programMaster.setBranchId(request.getParameter("branchId"));
				programMaster.setSessionStartDate(sessionDate.getSessionStartDate());
				programMaster.setSessionEndDate(sessionDate.getSessionEndDate());
				programMaster.setUniversityId(universityId);
				programMaster.setEntityId(request.getParameter("selectedEntityId"));
				try{
					specializationNameList = manageprogramRegistrationDAO.getSpecializationList(programMaster);
				}catch(MyException e){
					logger.error(e);
				}
					return new ModelAndView("programRegistration/ProgramList","program", specializationNameList);
				}
			
			/** 
			 * Method semesterList is used for getting the semester list of an program.
			 * @param request coming from client side
			 * @param response used for sending response
			 * @return ModelAndView the forward page and model data used in the forward page
			 */

			public ModelAndView semesterList(HttpServletRequest request,
					HttpServletResponse response) {
				
				HttpSession session = request.getSession();		
				
				String universityId =(String) session.getAttribute("universityId");
				if(universityId == null)
				{
					return new ModelAndView("general/SessionInactive","sessionInactive",true);
				}
				
				List<ProgramMaster> semesterList = null;
				ProgramMaster programMaster = new ProgramMaster();
				programMaster.setProgramId(request.getParameter("programId"));
				programMaster.setBranchId(request.getParameter("branchId"));
				programMaster.setSpecializationId(request.getParameter("specializationId"));	
				programMaster.setSessionStartDate(sessionDate.getSessionStartDate());
				programMaster.setSessionEndDate(sessionDate.getSessionEndDate());
				programMaster.setUniversityId(universityId);
				programMaster.setEntityId(request.getParameter("selectedEntityId"));
				try{
					semesterList = manageprogramRegistrationDAO.getSemesterList(programMaster);
				}catch(MyException e){
					logger.error(e);
				}
				return new ModelAndView("programCourseSetup/SemAndSpecial","semAndspecial", semesterList);
				}
			
			/** 
			 * Method programDetails is used for getting the program details which you want to change.
			 * @param request coming from client side
			 * @param response used for sending response
			 * @return ModelAndView the forward page and model data used in the forward page
			 */

			public ModelAndView programDetails(HttpServletRequest request,
					HttpServletResponse response) {
				
				HttpSession session = request.getSession();		
				
				String universityId =(String) session.getAttribute("universityId");
				if(universityId == null)
				{
					return new ModelAndView("general/SessionInactive","sessionInactive",true);
				}
				ProgramMaster programMaster = new ProgramMaster();
				programMaster.setProgramId(request.getParameter("programId"));
				programMaster.setBranchId(request.getParameter("branchId"));
				programMaster.setSpecializationId(request.getParameter("specializationId"));
				programMaster.setSemesterCode(request.getParameter("semesterCode"));
				programMaster.setSessionStartDate(sessionDate.getSessionStartDate());
				programMaster.setSessionEndDate(sessionDate.getSessionEndDate());
				programMaster.setEntityId(request.getParameter("selectedEntityId"));
				ProgramRegistrationDetails programDetails = null;
				try{
					programDetails = manageprogramRegistrationDAO.getProgramDetails(programMaster);
				}catch(MyException e){
					logger.error(e);
				}
				return new ModelAndView("programRegistration/ProgramDetails","programDetails", programDetails);
				}
			
			/** 
			 * Method updateProgramDetails is used for changing the program details.
			 * @param request coming from client side
			 * @param response used for sending response
			 * @return ModelAndView the forward page and model data used in the forward page
			 */

			public ModelAndView updateProgramDetails(HttpServletRequest request,
					HttpServletResponse response) {
				HttpSession session = request.getSession();				
				String userId =(String) session.getAttribute("userId");
				
				if(userId == null)
				{
					return new ModelAndView("general/SessionInactive","sessionInactive",true);
				}
				String message="fail";
				ProgramRegistrationDetails programDetails = new ProgramRegistrationDetails();
				programDetails.setProgramCourseKey(request.getParameter("programCourseKey"));
				programDetails.setRegistrationStartDate(request.getParameter("registrationStartDate"));
				programDetails.setRegistrationLastDate(request.getParameter("registrationLastDate"));
				programDetails.setAddDropPeriod(Integer.parseInt(request.getParameter("addDropPeriod")));
				programDetails.setStatus(request.getParameter("status"));
				programDetails.setModifierId(userId);
				programDetails.setSessionStartDate(sessionDate.getSessionStartDate());
				programDetails.setSessionEndDate(sessionDate.getSessionEndDate());
				programDetails.setEntityId(request.getParameter("selectedEntityId"));
				try{
					if(manageprogramRegistrationDAO.changeProgramDetails(programDetails)>0)
						message="success";
				}catch(MyException e){
					//changes By Mandeep
					logger.error(e);
					 message="updateError"+e.getMessage();
					return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
					
				}
				return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
				}

			/** 
			 * Method checkStatus is used for checking the status of program details.
			 * @param request coming from client side
			 * @param response used for sending response
			 * @return ModelAndView the forward page and model data used in the forward page
			 */

			public ModelAndView checkStatus(HttpServletRequest request,
					HttpServletResponse response) {
				HttpSession session = request.getSession();		
				
				String universityId =(String) session.getAttribute("universityId");
				if(universityId == null)
				{
					return new ModelAndView("general/SessionInactive","sessionInactive",true);
				}
				ProgramRegistrationDetails programDetails = new ProgramRegistrationDetails();
				programDetails.setProgramCourseKey(request.getParameter("programCourseKey"));
				ProgramRegistrationDetails status = null;
				try{
					status = manageprogramRegistrationDAO.viewStatus(programDetails);
				}catch(MyException e){
					logger.error(e);
				}
				return new ModelAndView("programRegistration/SessionDate","sessionDate", status);
				}
			
}
