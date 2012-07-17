/*
 * @(#) CreateProgramOfferedByController.java
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
import in.ac.dei.edrp.cms.dao.programofferedby.CreateProgramOfferedByDAO;
import in.ac.dei.edrp.cms.domain.programofferedby.EntityDetails;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 31-Jan-2011
 * The behavior of this class is as Cotroller.
 * This class is used for creating program offered by.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class CreateProgramOfferedByController extends MultiActionController{

	static final Logger logger = Logger.getLogger(CreateProgramOfferedByController.class);
	
	private CreateProgramOfferedByDAO createprogramofferedbyDAO;
	/** 
	 * Method setCreateProgramOfferedByDAO is used for setting the reference of a implemented class.
	 * @param createprogramofferedbyDAO 
	 * 
	 */
	public void setCreateProgramOfferedByDAO(CreateProgramOfferedByDAO createprogramofferedbyDAO) {
	this.createprogramofferedbyDAO = createprogramofferedbyDAO;
	}
	/** 
	 * Method entityList is used for getting the entity list.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView entityList(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();		
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<EntityDetails> entityNameList = null;
		try{
			entityNameList = createprogramofferedbyDAO.getEntityNameList(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programOfferedby/EntityNameWithId","entityName", entityNameList);
		}
	
	/** 
	 * Method programBranchSpecializationList is used for getting all the list of program,
	 * branch and specialization of an university.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView programBranchSpecializationList(HttpServletRequest request,
			HttpServletResponse response) {
		String entityId = request.getParameter("entityId");
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
	
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
	
		EntityDetails entityDetails = new EntityDetails();
		entityDetails.setEntityId(entityId);
		entityDetails.setUniversityId(universityId);
		List<List<EntityDetails>> listWithId = null;
		try{
			listWithId = createprogramofferedbyDAO.getProgramBranchSpecializationList(entityDetails);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programOfferedby/ProgramBranchSpeclWithId","listWithId", listWithId);
		}
	
	
		/** 
		 * Method insertProgramOfferedBy is used for inserting the program offered by data
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 * 
		 */
		public ModelAndView insertProgramOfferedBy(HttpServletRequest request,
				HttpServletResponse response) {
			HttpSession session = request.getSession();
			String creatorId = (String)session.getAttribute("userId");
			if(creatorId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
		
			String message = null;
				message = createprogramofferedbyDAO.insertProgramOfferedBy(request.getParameter("entityData"), 
						request.getParameter("entityId"), creatorId);			
				return new ModelAndView("programCourseSetup/InsertionInfo","message", message);//new ModelAndView("programOfferedby/ProgramBranchSpeclWithId","listWithId", listWithId);
			}
		
		/** 
		 * Method existingEntityDetails is used for getting all the existing list of program,
		 * branch and specialization of an selected entity.
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 * 
		 */
		public ModelAndView existingEntityDetails(HttpServletRequest request,
				HttpServletResponse response) {
		
			HttpSession session = request.getSession(true);
			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			String entityId = request.getParameter("entityId");
			EntityDetails entityDetails = new EntityDetails();
			entityDetails.setEntityId(entityId);
			entityDetails.setUniversityId(universityId);
			List<EntityDetails> existingEntityDetail = null;
			try{
				existingEntityDetail = createprogramofferedbyDAO.existingEntityDetails(entityDetails);
			}catch(MyException e){
				logger.error(e);
			}
			return new ModelAndView("programOfferedby/ExistingEntityDetails","entityDetails", existingEntityDetail);
		}
}
