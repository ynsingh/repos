/*
 * @(#) AdvanceParentProgramController.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.cms.controller.advanceparentprogram;

import in.ac.dei.edrp.cms.dao.advanceparentprogram.AdvanceParentProgramConnect;
import in.ac.dei.edrp.cms.domain.advanceparentprogram.AdvanceParentProgramInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This controller is designed for setting & retrieving information about
 * advance program setup.
 * 
 * @author Ashish
 * @date 17 Feb 2011
 * @version 1.0
 */
public class AdvanceParentProgramController extends MultiActionController {
	private AdvanceParentProgramConnect advanceParentProgramConnect;

	/**
	 * The setter method of the interface associated with this controller
	 * 
	 * @param advanceParentProgramConnect
	 */
	public void setAdvanceParentProgramConnect(
			AdvanceParentProgramConnect advanceParentProgramConnect) {
		this.advanceParentProgramConnect = advanceParentProgramConnect;
	}

	/**
	 * Method for getting the list of programs for the concerned university
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getProgramDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdvanceParentProgramInfoGetter input = new AdvanceParentProgramInfoGetter();
		HttpSession session = request.getSession(true);
		
		String userId = (String) session.getAttribute("userId");

		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

		List<AdvanceParentProgramInfoGetter> resultprogramDetails = advanceParentProgramConnect
				.getProgramList(input.getUserId());

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultprogramDetails);
	}

	/**
	 * Method for getting the list of courses for the concerned program
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getCoursesDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdvanceParentProgramInfoGetter input = new AdvanceParentProgramInfoGetter();
		HttpSession session = request.getSession(true);
		
		String userId = (String) session.getAttribute("userId");

		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

		input.setProgramId(request.getParameter("programId"));
		input.setAdvanceProgramId(request.getParameter("advanceProgramId"));

		List<AdvanceParentProgramInfoGetter> resultcourseDetails = advanceParentProgramConnect
				.getCoursesList(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultcourseDetails);
	}

	/**
	 * Method for inserting a record for the concerned program-advance
	 * program-course combination
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView setAdvanceProgramRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdvanceParentProgramInfoGetter input = new AdvanceParentProgramInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setProgramId(request.getParameter("programId"));
		input.setAdvanceProgramId(request.getParameter("advanceProgramId"));

		input.setCourseCode(request.getParameter("courseCode"));

		String resultinsertDetails = advanceParentProgramConnect
				.insertAdvanceProgramRecord(input);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultinsertDetails);
	}

	/**
	 * Method for getting the records for a concerned program for managing.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getadvanceProgramRecords(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdvanceParentProgramInfoGetter input = new AdvanceParentProgramInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setProgramId(request.getParameter("programId"));
		input.setAdvanceProgramId(request.getParameter("advanceProgramId"));

		List<AdvanceParentProgramInfoGetter> resultProgramRecords = advanceParentProgramConnect
				.getAdvanceProgramRecords(input);

		return new ModelAndView("AdvanceParentProgram/AdvanceParentProgram",
				"resultObject", resultProgramRecords);
	}

	/**
	 * Method for deleting records for the concerned program
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteRecords(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdvanceParentProgramInfoGetter input = new AdvanceParentProgramInfoGetter();

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setProgramId(request.getParameter("programId"));
		input.setAdvanceProgramId(request.getParameter("advanceProgramId"));

		StringTokenizer items = new StringTokenizer(request
				.getParameter("courseCode"), ",");

		String resultinsertDetails = advanceParentProgramConnect
				.deleteAdvanceProgramRecord(input.getUserId(), input
						.getProgramId(), input.getAdvanceProgramId(), items);

		return new ModelAndView("preProcessChecks/preProcessResultlist",
				"resultObject", resultinsertDetails);
	}
}
