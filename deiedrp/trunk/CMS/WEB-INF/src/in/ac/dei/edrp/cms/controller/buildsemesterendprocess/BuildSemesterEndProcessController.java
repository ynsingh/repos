/**
 * @(#) CgpaDivisionController.java
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
package in.ac.dei.edrp.cms.controller.buildsemesterendprocess;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.buildsemesterenprocess.BuildSemesterEndProcessDao;
import in.ac.dei.edrp.cms.domain.buildsemesterendprocess.BuildSemesterEndProcess;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * This controller is designed for setting & retrieving information for Build Semester End Process.
 * @author Devendra Singhal
 * @date Dec 02 2011
 * @version 1.0
 */
public class BuildSemesterEndProcessController extends MultiActionController{
	
	//Create variable of BuildSemesterEndProcessDao interface
	private BuildSemesterEndProcessDao buildSemesterEndProcessDao;
	/**
     * The setter method of the interface associated with this controller
     * @param buildActivityMaster
     */
	public void setBuildSemesterEndProcessDao(BuildSemesterEndProcessDao buildSemesterEndProcessDao) {
		this.buildSemesterEndProcessDao = buildSemesterEndProcessDao;
	}
	
	/**
     * Method to Clear Temporary Tables
     * @param request
     * @param response
     * @throws Exception
     * @return Model and View containing message
     */
	public ModelAndView clearTempTables(HttpServletRequest request,
		HttpServletResponse response)throws Exception {
		//create Object of BuildSemesterEndProcess bean
		BuildSemesterEndProcess buildSemesterEndProcess=new BuildSemesterEndProcess();
		//Create Session Object
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String startDateTemp[]=(request.getParameter("startDate")).split("/");
		String endDateTemp[]=(request.getParameter("endDate")).split("/");
		String startDate=startDateTemp[2]+"-"+startDateTemp[0]+"-"+startDateTemp[1];
		String endDate=endDateTemp[2]+"-"+endDateTemp[0]+"-"+endDateTemp[1];
		buildSemesterEndProcess.setUniversityId(universityId);
		buildSemesterEndProcess.setCreatorId((String)session.getAttribute("userId"));
		buildSemesterEndProcess.setNextSemStartDate(startDate);
		buildSemesterEndProcess.setNextSemEndDate(endDate);
		String message = buildSemesterEndProcessDao.clearTempTables(buildSemesterEndProcess);
		return new ModelAndView("buildactivitymaster/Result","message", message);
	}
	
	/**
     * Method to set & retrieve information to Build for REG/SEP
     * @param request
     * @param response
     * @throws Exception
     * @return Model and View containing String message
     */
	public ModelAndView readyForRegSep(HttpServletRequest request,
		HttpServletResponse response)throws Exception {
		//create Object of BuildSemesterEndProcess bean
		BuildSemesterEndProcess buildSemesterEndProcess=new BuildSemesterEndProcess();
		//Create Session Object
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String startDateTemp[]=(request.getParameter("startDate")).split("/");
		String endDateTemp[]=(request.getParameter("endDate")).split("/");
		String startDate=startDateTemp[2]+"-"+startDateTemp[0]+"-"+startDateTemp[1];
		String endDate=endDateTemp[2]+"-"+endDateTemp[0]+"-"+endDateTemp[1];
		buildSemesterEndProcess.setUniversityId(universityId);
		buildSemesterEndProcess.setCreatorId((String)session.getAttribute("userId"));
		buildSemesterEndProcess.setNextSemStartDate(startDate);
		buildSemesterEndProcess.setNextSemEndDate(endDate);
		String message="";
		if(request.getParameter("buildFor").toString().equals("REG")){
			message = buildSemesterEndProcessDao.readyForRegistration(buildSemesterEndProcess);
		}
		else if(request.getParameter("buildFor").toString().equals("SEP")){
			message = buildSemesterEndProcessDao.readyForSemesterEnd(buildSemesterEndProcess);
			System.out.println("inside Controller Sep "+message);
		}
		return new ModelAndView("buildactivitymaster/Result","message", message);
	}
}
