/*
 * @(#) ProgramTermDetailsController.java
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
package in.ac.dei.edrp.cms.controller.programmaster;

import in.ac.dei.edrp.cms.dao.programmaster.ProgramTermDetailsDao;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Controller for populating and managing program term details
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public class ProgramTermDetailsController extends MultiActionController {
    ProgramTermDetailsDao programTermDetailsDao;

    /**
     * Method to initialize controller
     * @param programMasterDao object of EntityMasterDao interface
     */
    public void setProgramTermDetailsDao(
        ProgramTermDetailsDao programTermDetailsDao) {
        this.programTermDetailsDao = programTermDetailsDao;
    }

    /**
     *   Method for inserting details of a term into database
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView insertTermDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);
    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        ProgramTermDetailsInfoGetter inputObj = new ProgramTermDetailsInfoGetter();
        System.out.println(request.getParameter("programId"));
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setSemesterCode(request.getParameter("semCode"));
        inputObj.setSemesterStartDate(request.getParameter("startDate"));
        inputObj.setSemesterEndDate(request.getParameter("endDate"));
        inputObj.setSemesterSequence(request.getParameter("semSequence"));
        inputObj.setSemesterGroup(request.getParameter("semGroup"));
        inputObj.setFinalSemesterCode(request.getParameter("finalSem"));
        inputObj.setNumberOfTeachingDays(request.getParameter("teachingDays"));
        inputObj.setDurationInWeeks(request.getParameter("duration"));
        inputObj.setMinimumSgpa(request.getParameter("minSgpa"));
        inputObj.setMinimumCgpa(request.getParameter("minCgpa"));
        inputObj.setMaximumCredit(request.getParameter("maxCredit"));
        inputObj.setMinimumCredit(request.getParameter("minCredit"));
        inputObj.setMaximumLectureCredit(request.getParameter("maxLCredit"));
        inputObj.setMinimumLectureCredit(request.getParameter("minLCredit"));
        inputObj.setMaximumCreditSpecialcase(request.getParameter(
                "creditSplCase"));
        inputObj.setMaxSpecLectureCourse(request.getParameter("splLCourse"));
      
        
        inputObj.setCreatorId(session.getAttribute("userId").toString());

        String result = programTermDetailsDao.insertTermDetails(inputObj);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }

    /**
     *  Method to get list of semesters available in university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getSemesterList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);
        String userId = (String) session.getAttribute("userId");
        if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        userId = userId.toString().substring(1, 5);
        List<ProgramTermDetailsInfoGetter> semList = programTermDetailsDao.getSemesterList(userId);
        
        return new ModelAndView("termdetails/SysTwoList", "result", semList);
    }

    /**
     *  Method to get list of semester groups available in university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getSemesterGroupList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);
    	String userId = (String) session.getAttribute("userId");
    	  if(userId == null)
          {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
          }
       userId = userId.toString().substring(1, 5);

        List<ProgramTermDetailsInfoGetter> semgrpList = programTermDetailsDao.getSemesterGroupList(userId);
        return new ModelAndView("termdetails/SysTwoList", "result", semgrpList);
    }

    /**
     * Method for getting list of sequences for a program
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getProgSequenceList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	
        String progId = request.getParameter("programId");
        List<ProgramTermDetailsInfoGetter> resultList = programTermDetailsDao.getProgSequenceList(progId);

        return new ModelAndView("termdetails/SysTwoList", "result", resultList);
    }

    /**
     * Method for getting list of programs for manage
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getProgListForManage(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        String progId = session.getAttribute("userId").toString().substring(1, 5) + "%";
        List<ProgramTermDetailsInfoGetter> resultList = programTermDetailsDao.getProgListForManage(progId);

        return new ModelAndView("termdetails/SysTwoList", "result", resultList);
    }

    /**
     * Method for getting list of semesters existing in a program
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getProgramSemList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        String progId = request.getParameter("programId");
        List<ProgramTermDetailsInfoGetter> resultList = programTermDetailsDao.getProgramSemList(progId);

        return new ModelAndView("termdetails/SysTwoList", "result", resultList);
    }

    /**
     * Method for getting program term details for managing purpose
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getTermDetailForManage(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        ProgramTermDetailsInfoGetter inputObj = new ProgramTermDetailsInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setSemesterCode(request.getParameter("semCode"));

        List<ProgramTermDetailsInfoGetter> detailList = programTermDetailsDao.getTermDetailForManage(inputObj);

        return new ModelAndView("termdetails/FullDetails", "programList",
            detailList);
    }

    /**
     *   Method for update details of a term into database
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView updateTermDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        ProgramTermDetailsInfoGetter inputObj = new ProgramTermDetailsInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setSemesterCode(request.getParameter("semCode"));
        inputObj.setSemesterStartDate(request.getParameter("startDate"));
        inputObj.setSemesterEndDate(request.getParameter("endDate"));
        inputObj.setSemesterSequence(request.getParameter("semSequence"));
        inputObj.setSemesterGroup(request.getParameter("semGroup"));
        inputObj.setFinalSemesterCode(request.getParameter("finalSem"));
        inputObj.setNumberOfTeachingDays(request.getParameter("teachingDays"));
        inputObj.setDurationInWeeks(request.getParameter("duration"));
        inputObj.setMinimumSgpa(request.getParameter("minSgpa"));
        inputObj.setMinimumCgpa(request.getParameter("minCgpa"));
        inputObj.setMaximumCredit(request.getParameter("maxCredit"));
        inputObj.setMinimumCredit(request.getParameter("minCredit"));
        inputObj.setMaximumLectureCredit(request.getParameter("maxLCredit"));
        inputObj.setMinimumLectureCredit(request.getParameter("minLCredit"));
        inputObj.setMaximumCreditSpecialcase(request.getParameter(
                "creditSplCase"));
        inputObj.setMaxSpecLectureCourse(request.getParameter("splLCourse"));
        inputObj.setModifierId(session.getAttribute("userId").toString());
        inputObj.setSessionStartDate(session.getAttribute("startDate").toString());
        inputObj.setSessionEndDate(session.getAttribute("endDate").toString());

        String result = programTermDetailsDao.updateTermDetails(inputObj);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }

    /**
     * Method for update details of a term into database
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView deleteTermDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        ProgramTermDetailsInfoGetter inputObj = new ProgramTermDetailsInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setSemesterCode(request.getParameter("semCode"));

        String result = programTermDetailsDao.deleteTermDetails(inputObj);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }
}
