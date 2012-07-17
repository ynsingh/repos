/*
 * @(#) ProgramCourseTypeSummaryController.java
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
package in.ac.dei.edrp.cms.controller.programcoursetypesummary;

import in.ac.dei.edrp.cms.dao.programcoursetypesummary.ProgramCourseTypeSummaryConnect;
import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about program course type summary setup.
 * @author Ashish
 * @date 12 Feb 2011
 * @version 1.0
 */
public class ProgramCourseTypeSummaryController extends MultiActionController {
    private ProgramCourseTypeSummaryConnect programCourseTypeSummaryConnect;

    /**
     * The setter method of the interface associated
     * with this controller
     * @param programCourseTypeSummaryConnect
     */
    public void setProgramCourseTypeSummaryConnect(
        ProgramCourseTypeSummaryConnect programCourseTypeSummaryConnect) {
        this.programCourseTypeSummaryConnect = programCourseTypeSummaryConnect;
    }

    /**
     * Method for getting the list of programs
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getProgramCourseTypeDetails(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramCourseTypeDetails(input.getUserId());

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for getting the list of branches associated
     * with the selected program
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getProgramBranchDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramBranchDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for getting the list of Specializations associated
     * with the selected program-branch
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getProgramSpecializationDetails(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramSpecializationDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for getting the list of semesters associated
     * with the selected program-branch-specialization
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getProgramSemesterDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));
        input.setSpecializationId(request.getParameter("specializationId"));

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramSemesterDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for getting the list of course types associated
     * with the selected program combination
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCourseTypeDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));
        input.setSpecializationId(request.getParameter("specializationId"));
        input.setSemesterCode(request.getParameter("semesterCode"));

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramTypeDetails(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for inserting a record for the selected program & course type
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView setProgramCoursetypeSummary(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));
        input.setSpecializationId(request.getParameter("specializationId"));
        input.setSemesterCode(request.getParameter("semesterCode"));
        input.setCourseTypeCode(request.getParameter("courseType"));
        input.setMinCredits(Integer.parseInt(request.getParameter("minCredits")));
        input.setMaxCredits(Integer.parseInt(request.getParameter("maxCredits")));
        input.setActivityFlag(request.getParameter("activityFlag"));

        String resultprogramCourseDetails = programCourseTypeSummaryConnect.setProgramCoursetypeSummary(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for retrieving the list of course types already added
     * for the concerned program combination
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getProgramCoursetypeSummary(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));
        input.setSpecializationId(request.getParameter("specializationId"));
        input.setSemesterCode(request.getParameter("semesterCode"));

        List<ProgramCourseTypeSummaryInfoGetter> resultprogramCourseDetails = programCourseTypeSummaryConnect.getProgramCoursetypeSummary(input);

        return new ModelAndView("ProgramCourseTypeSummary/programCourseType",
            "resultObject", resultprogramCourseDetails);
    }

    /**
     * Method for deleting records for selected program combination
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView deleteRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramCourseTypeSummaryInfoGetter input = new ProgramCourseTypeSummaryInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setProgramId(request.getParameter("programId"));
        input.setBranchId(request.getParameter("branchId"));
        input.setSpecializationId(request.getParameter("specializationId"));
        input.setSemesterCode(request.getParameter("semesterCode"));
        input.setCourseTypeCode(request.getParameter("courseType"));

        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "courseType"), ",");

        String resultprogramCourseDetails = programCourseTypeSummaryConnect.deleteProgramCoursetypeSummaryRecords(input,
                items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultprogramCourseDetails);
    }
}
