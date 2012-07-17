/*
 * @(#) ManageProgramOfferedByController.java
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
package in.ac.dei.edrp.cms.controller.programofferedby;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programofferedby.ManageProgramOfferedByDAO;
import in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 02-Feb-2011
 * The behavior of this class is as Cotroller.
 * This class is used for managing program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */

public class ManageProgramOfferedByController extends MultiActionController{
	
static final Logger logger = Logger.getLogger(ManageProgramOfferedByController.class);
	
	private ManageProgramOfferedByDAO manageprogramofferedbyDAO;
	/** 
	 * Method setManageProgramOfferedByDAO is used for setting the reference of a implemented class.
	 * @param manageprogramofferedbyDAO 
	 * 
	 */
	public void setManageProgramOfferedByDAO(ManageProgramOfferedByDAO manageprogramofferedbyDAO) {
	this.manageprogramofferedbyDAO = manageprogramofferedbyDAO;
	}
	/** 
	 * Method manageEntityList is used for getting the entity list to manage.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView manageEntityList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();		
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		List<EntityDetails> entityNameList = null;
		try{
			entityNameList = manageprogramofferedbyDAO.getEntityNameList(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programOfferedby/EntityNameWithId","entityName", entityNameList);
		}
	
	/** 
	 * Method getProgramList is used for getting the program list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView getProgramList(HttpServletRequest request,
			HttpServletResponse response) {
		String entityId = request.getParameter("entityId");
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<EntityDetails> programNameList = null;
		try{
			programNameList = manageprogramofferedbyDAO.getProgramNameList(entityId);
		}catch(MyException e){
			logger.error(e);
		}
		return new ModelAndView("programOfferedby/EntityNameWithId","entityName", programNameList);
		}

	/** 
	 * Method getDetailsOfEntity is used for getting the program list according entity and program.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView getDetailsOfEntity(HttpServletRequest request,
			HttpServletResponse response) {
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		EntityDetails entityDetails = new EntityDetails();
		entityDetails.setEntityId(entityId);
		entityDetails.setProgramId(programId);
		entityDetails.setUniversityId(universityId);
		List<List<EntityDetails>> programNameList = null;
		try{
			programNameList = manageprogramofferedbyDAO.getProgramListOfEntity(entityDetails);
		}catch(MyException e){
			logger.error(e);
		}
		return new ModelAndView("programOfferedby/EntityDetails","entityDetails", programNameList);
		}
	
	/** 
	 * Method updateProgramOfferedBy is used for updating the program offered by 
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView updateProgramOfferedBy(HttpServletRequest request,
			HttpServletResponse response) {
		String message="fail";
		HttpSession session  = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		EntityDetails entityDetails = new EntityDetails();
		entityDetails.setEntityId(request.getParameter("entityId"));
		entityDetails.setProgramId(request.getParameter("programId"));
		entityDetails.setBranchId(request.getParameter("branchId"));
		entityDetails.setSpecializationId(request.getParameter("specializationId"));
		entityDetails.setEmployeeCode(request.getParameter("employeeCode"));
		entityDetails.setNumberOfSeats(Integer.parseInt(request.getParameter("seats")));
		entityDetails.setModifierId((String)request.getSession().getAttribute("userId"));
//		try{
		String result=	manageprogramofferedbyDAO.updateEntityDetails(entityDetails);
			
//			message="success";
//		}catch(MyException e){
//			logger.error(e);
//		}
			return new ModelAndView("programCourseSetup/InsertionInfo","message", result);
		}
		
		/** 
		 * Method deleteProgramOfferedBy is used for removing the selected programs of an entity
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 * 
		 */
		public ModelAndView deleteProgramOfferedBy(HttpServletRequest request,
				HttpServletResponse response) {
			String message = null;
			HttpSession session = request.getSession(true);
			String universityId = (String) session.getAttribute("universityId");
			
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			
			String entityId = request.getParameter("entityId");
			String entityData = request.getParameter("entityData");
			try{
				message=manageprogramofferedbyDAO.deleteDesiredEntityDetails(entityId, entityData);				
			}catch(MyException e){
				System.out.println("exception "+e);
				logger.error(e);
			}
				return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
			}
}
