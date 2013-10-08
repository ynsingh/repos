/*
 * @(#) ProgramCourseCreditController.java
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
package in.ac.dei.edrp.cms.controller.programCourseHeaderCredit;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.controller.programcoursesetup.ManageProgramCourseController;
import in.ac.dei.edrp.cms.dao.programCourseHeaderCredit.ProgramCourseCreditDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This class contains methods for program course header
 * @author Upasana Kulshrestha
 * @date 10-10-2012
 * @version 1.0
 */

public class ProgramCourseCreditController extends MultiActionController{
	
	static final Logger logger = Logger.getLogger(ManageProgramCourseController.class);
	
	private ProgramCourseCreditDAO programcoursecreditDAO;
	/** 
	 * Method setProgramCourseCreditDAO is used for setting the reference of a implemented class.
	 * @param programcoursecreditDAO 
	 * 
	 */
	public void setProgramCourseCreditDAO(ProgramCourseCreditDAO programcoursecreditDAO) {
		this.programcoursecreditDAO = programcoursecreditDAO;
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
			programCourseHeaderList = programcoursecreditDAO.getProgramCourseHeaderList(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseCredit/ProgramCourseHeaderCredit","programCourseHeaderList", programCourseHeaderList);
		}
	
	/** 
	 * Method selectProgramTermCredits is used for getting the credits from 
	 * program course term
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView selectProgramTermCredits(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		List<ProgramMaster> programTermCredits = null;
		
		String programCoursekey=request.getParameter("program_course_key");
		
		try{
			programTermCredits = programcoursecreditDAO.getProgramTermCredit(programCoursekey);
			System.out.println(programTermCredits.size());
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programCourseCredit/ProgramTermCredits","programTermCredits", programTermCredits);
		}
	
	/** 
	 * Method updateProgramCourseCredit is used for updating the credit 
	 * of program course header
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 * 
	 */
	public ModelAndView updateProgramCourseCredit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		ProgramMaster programCourseCredit = new ProgramMaster();
		programCourseCredit.setProgramCourseKey(request.getParameter("program_course_key"));
		programCourseCredit.setMaxCredit(request.getParameter("maxCredit"));
		programCourseCredit.setMinCredit(request.getParameter("minCredit"));
		programCourseCredit.setMaxLecCredit(request.getParameter("maxLecCredit"));
		programCourseCredit.setMinLecCredit(request.getParameter("minLecCredit"));
		
		programCourseCredit.setModifierId((String)request.getSession().getAttribute("userId"));
		String message=programcoursecreditDAO.updateProgramCourseCredit(programCourseCredit);
		return new ModelAndView("programCourseSetup/InsertionInfo","message", message);
	}
}
