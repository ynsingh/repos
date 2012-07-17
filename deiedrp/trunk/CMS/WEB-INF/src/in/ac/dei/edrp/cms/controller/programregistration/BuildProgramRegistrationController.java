/*
 * @(#) BuildProgramRegistrationController.java
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
import in.ac.dei.edrp.cms.dao.programregistration.BuildProgramRegistrationDAO;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;
import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Creation date: 10-Feb-2011
 * The behavior of this class is as Cotroller.
 * This class is used for building program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class BuildProgramRegistrationController extends MultiActionController{

	static final Logger logger = Logger.getLogger(BuildProgramRegistrationController.class);

	ProgramRegistrationDetails sessionDate = null;
	private BuildProgramRegistrationDAO buildprogramRegistrationDAO;
	/**
	 * Method setBuildProgramRegistrationDAO is used for setting the reference of a implemented class.
	 * @param buildprogramRegistrationDAO
	 */
	public void setBuildProgramRegistrationDAO(BuildProgramRegistrationDAO buildprogramRegistrationDAO) {
	this.buildprogramRegistrationDAO = buildprogramRegistrationDAO;
	}
	/**
	 * Method for renewing all the registrations for new session.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */
	public ModelAndView systemBuildProgramRegistration(HttpServletRequest request,HttpServletResponse response)
	{

		HttpSession session = request.getSession(true);
	String universityId = (String) session.getAttribute("universityId");
	sessionDate = buildprogramRegistrationDAO.getSessionDate(universityId);

	sessionDate.setUniversityId(universityId);
	String acknowledge = buildprogramRegistrationDAO.systemBuildRegistration(sessionDate);
	return null;
	}

	/**
	 * Method loadSession is used for getting the current session of an university.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */

	public ModelAndView loadSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null)
		{
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}

		try{
			sessionDate = buildprogramRegistrationDAO.getSessionDate(universityId);
		}catch(MyException e){
			logger.error(e);
		}
			return new ModelAndView("programRegistration/SessionDate","sessionDate", sessionDate);
		}

	/**
	 * Method insertProgramReg is used for inserting the whole programs.
	 * @param request coming from client side
	 * @param response used for sending response
	 * @return ModelAndView the forward page and model data used in the forward page
	 */
		public ModelAndView insertProgramReg(HttpServletRequest request,
				HttpServletResponse response) throws ParseException {
			int message=-1;
			List<Integer> numberOfRecordsInserted =null;
			HttpSession session = request.getSession();

			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			int regPeriod = Integer.parseInt(request.getParameter("regperiod"));
			int addDropPeriod = Integer.parseInt(request.getParameter("adddropperiod"));
			String ssdate = sessionDate.getSessionStartDate();
			String sedate = sessionDate.getSessionEndDate();
			String userId =(String) session.getAttribute("userId");
			String flag=request.getParameter("flag");
			try{
				numberOfRecordsInserted = buildprogramRegistrationDAO.insertProgramRegistration(regPeriod, addDropPeriod,
						ssdate,sedate,universityId,userId,flag);
				if(numberOfRecordsInserted.get(2) > 0){
					message=0;
				}
				else{
					message=1;
				}
				numberOfRecordsInserted.add(message);

			}catch(MyException e){
				//changes By Mandeep
				logger.error(e);
				String errorMessage="insertError"+e.getMessage();
			return new ModelAndView("programRegistration/InsertionInfo","message",errorMessage);
			}
			return new ModelAndView("programRegistration/InsertionInfo","message", numberOfRecordsInserted);
			}

		/**
		 * Method programToRegister is used for getting program details to registered.
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 */
		public ModelAndView programToRegister(HttpServletRequest request,
				HttpServletResponse response) {

			HttpSession session = request.getSession(true);

			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			String sessioStartDate = sessionDate.getSessionStartDate();
			String sessionEndDate = sessionDate.getSessionEndDate();

			sessionDate.setUniversityId(universityId);
			List<ProgramRegistrationDetails> programDetails = null;
			try{
				programDetails = buildprogramRegistrationDAO.getProgramRegistrationDetails(sessionDate);
			}catch(MyException e){
				logger.error(e);
			}
				return new ModelAndView("programRegistration/ProgramToRegister","programDetails", programDetails);
			}

		/**
		 * Method insertProgram is used for inserting single program to registered.
		 * @param request coming from client side
		 * @param response used for sending response
		 * @return ModelAndView the forward page and model data used in the forward page
		 */
		public ModelAndView insertProgram(HttpServletRequest request,
				HttpServletResponse response) throws ParseException {
			HttpSession session = request.getSession();

			String universityId =(String) session.getAttribute("universityId");
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}
			int count=0;
			StringTokenizer entity =new StringTokenizer(request.getParameter("selectedEntityId"),"|");
			StringTokenizer prog =new StringTokenizer(request.getParameter("programCourseKey"),"|");
			StringTokenizer ssd =new StringTokenizer(request.getParameter("semesterStartDate"),"|");
			StringTokenizer sed =new StringTokenizer(request.getParameter("semesterEndDate"),"|");
			StringTokenizer regp =new StringTokenizer(request.getParameter("registrationPeriod"),"|");
			StringTokenizer adp =new StringTokenizer(request.getParameter("addDropPeriod"),"|");
			String userId =(String) session.getAttribute("userId");
			String sessionStartDate =(String)  session.getAttribute("startDate");
			String sessionEndDate =(String)  session.getAttribute("endDate");


			try{
			while(prog.hasMoreTokens()){

				int registrationPeriod = Integer.parseInt(regp.nextToken());
				int addDropPeriod = Integer.parseInt(adp.nextToken());

				ProgramRegistrationDetails programDetails = new ProgramRegistrationDetails();
				programDetails.setProgramCourseKey(prog.nextToken());
				programDetails.setSemesterStartDate(ssd.nextToken());
				programDetails.setSemesterEndDate(sed.nextToken());
				programDetails.setEntityId(entity.nextToken());

				buildprogramRegistrationDAO.insertSingleProgramRegistration(programDetails, registrationPeriod,
						addDropPeriod, sessionStartDate, sessionEndDate, universityId, userId);

				count++;
			}
			}catch(MyException e){
				//changes By Mandeep
				logger.error(e);
				String errorMessage="insertError"+e.getMessage();
				return new ModelAndView("programRegistration/InsertionInfo","message",errorMessage);
			}

			return new ModelAndView("programCourseSetup/InsertionInfo","message",count);
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
			ProgramRegistrationDetails programRegDetails = new ProgramRegistrationDetails();
			if(universityId == null)
			{
				return new ModelAndView("general/SessionInactive","sessionInactive",true);
			}

			try{
				programRegDetails.setUniversityId(universityId);
				message=buildprogramRegistrationDAO.checkStatus(programRegDetails);
			}catch(MyException e){
				logger.error(e);
			}
				return new ModelAndView("buildactivitymaster/Result","message", message);
			}
}
