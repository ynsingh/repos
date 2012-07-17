/**
 * @(#) ManualProcessController.java
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
package in.ac.dei.edrp.cms.controller.manualprocess;

import in.ac.dei.edrp.cms.dao.manualprocess.ManualProcess;
import in.ac.dei.edrp.cms.domain.manualprocess.ManualProcessBean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Devendra Singhal
 * @date Sept/13/2011
 * @version 1.0
 *
 */
public class ManualProcessController extends MultiActionController{
	/** creating object of manualProcessDao interface */
	private ManualProcess manualProcessDao;

	/** defining setter method for object of manualProcessDao interface */
	public void setManualProcessDao(ManualProcess manualProcessDao) {
		this.manualProcessDao = manualProcessDao;
	}

	/**
	 * This method is used for getting OwnerEntity from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getOwnerEntity(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(true);
			String universityCode=(String)session.getAttribute("universityId");
			if(universityCode== null) {
				return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
			}
		 	ManualProcessBean input=new ManualProcessBean();
		 	input.setUniversityId(universityCode);
		 	List<ManualProcessBean>entityList =manualProcessDao.getOwnerEntity(input);
		 	 return new ModelAndView("ManualProcess/ProgramList",
		             "resultObject", entityList);
	}

	/**
	 * This method is used for getting Program Name,Program Id and Program Code from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getProgram(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(true);
			String universityCode=(String)session.getAttribute("universityId");
			if(universityCode== null) {
				return new ModelAndView("general/SessionInactive",
				"sessionInactive", true);
			}
	        ManualProcessBean input=new ManualProcessBean();
	        input.setUniversityId(universityCode);
	        input.setEntityId(request.getParameter("entityId"));
		 	List<ManualProcessBean>programList =manualProcessDao.getProgram(input);
		 	 return new ModelAndView("ManualProcess/ProgramList",
		             "resultObject", programList);
	}

	/**
	 * This method is used for getting branchId,and branch Name from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getBranch(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(true);
			String universityCode=(String)session.getAttribute("universityId");
			if(universityCode== null) {
				return new ModelAndView("general/SessionInactive",
						"sessionInactive", true);
			}
	        ManualProcessBean input=new ManualProcessBean();
	        input.setUniversityId(universityCode);
	        input.setProgramId(request.getParameter("programId"));
		 	List<ManualProcessBean>branchList =manualProcessDao.getBranch(input);
		 	 return new ModelAndView("ManualProcess/ProgramList",
		             "resultObject", branchList);
	}

	/**
	 * This method is used for getting branchId,and branch Name from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getSpecialization(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(true);
			String universityCode=(String)session.getAttribute("universityId");
			if(universityCode== null) {
				return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
			}
			ManualProcessBean input=new ManualProcessBean();
			input.setUniversityId(universityCode);
	        input.setProgramId(request.getParameter("programId"));
	        input.setBranchId(request.getParameter("branchId"));
		 	List<ManualProcessBean>specializationList =manualProcessDao.getSpecialization(input);
		 	 return new ModelAndView("ManualProcess/ProgramList",
		             "resultObject", specializationList);
	}

	/**
	 * This method is used for getting branchId,and branch Name from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getSemester(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(true);
			String universityCode=(String)session.getAttribute("universityId");
			if(universityCode== null) {
				return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
			}
			ManualProcessBean input=new ManualProcessBean();
			input.setUniversityId(universityCode);
	        input.setProgramId(request.getParameter("programId"));
	        input.setBranchId(request.getParameter("branchId"));
	        input.setSpecializationId(request.getParameter("specializationId"));
		 	List<ManualProcessBean>semesterList =manualProcessDao.getSemester(input);
		 	 return new ModelAndView("ManualProcess/ProgramList",
		             "resultObject", semesterList);
	}

	/**
	 * This method is used for getting Session StartDate from database
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return Model and View containing login user details details
	 */
	public ModelAndView getSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		String universityCode=(String)session.getAttribute("universityId");
		if(universityCode== null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
        ManualProcessBean input=new ManualProcessBean();
        input.setUniversityId(universityCode);
		List<ManualProcessBean>sessionList =manualProcessDao.getSessionStartDate(input);
		return new ModelAndView("ManualProcess/ProgramList",
	             "resultObject",sessionList);
	}
}
