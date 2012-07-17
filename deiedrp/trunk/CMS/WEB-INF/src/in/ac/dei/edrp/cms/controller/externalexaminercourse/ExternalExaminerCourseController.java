/*
 * @(#) ExternalExaminerCourseController.java
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
package in.ac.dei.edrp.cms.controller.externalexaminercourse;

import in.ac.dei.edrp.cms.dao.externalexaminercourse.ExternalExaminerCourseConnect;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about courses-external examiners combination.
 * @author Ashish
 * @date 7 Mar 2011
 * @version 1.0
 * @author Ashish Mohan
 * @date 27 Dec 2011
 * @version 2.0
 */
public class ExternalExaminerCourseController extends MultiActionController {
    private ExternalExaminerCourseConnect externalExaminerCourseConnect;

    /**
        * The setter method of the interface associated
        * with this controller
        * @param externalExaminerCourseConnect
        */
    public void setExternalExaminerCourseConnect(
        ExternalExaminerCourseConnect externalExaminerCourseConnect) {
        this.externalExaminerCourseConnect = externalExaminerCourseConnect;
    }

    /**
    * Method for getting the list of courses for the concerned university
    * @param request
    * @param response
    * @return List
    * @throws Exception
    */
    public ModelAndView getCourseDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
        input.setEmployeeId(session.getAttribute("startDate").toString());//used to set session start date
		input.setDesignation(session.getAttribute("endDate").toString());//used to set session end date
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
	
        List<ExternalExaminerCourseInfoGetter> resultCourseDetails = externalExaminerCourseConnect.getCoursesList(input);
    
        Iterator<ExternalExaminerCourseInfoGetter> iterator = resultCourseDetails.iterator();
        while (iterator.hasNext()) {
		ExternalExaminerCourseInfoGetter externalExaminerCourseInfoGetter = (ExternalExaminerCourseInfoGetter) iterator.next();
			
			externalExaminerCourseInfoGetter.setDesignation(input.getDesignation());
			externalExaminerCourseInfoGetter.setEmployeeId(input.getEmployeeId());
			
		}

        return new ModelAndView("EmployeeRole/ApplicationUsers",
            "resultObject", resultCourseDetails);
    }

    /**
     * Method for getting the list of examiners for the concerned university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getExaminerDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setCourseCode(request.getParameter("courseCode"));

        List<ExternalExaminerCourseInfoGetter> resultExaminerDetails = externalExaminerCourseConnect.getExaminerList(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultExaminerDetails);
    }

    /**
     * Method for inserting the course-examiner combination
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView setDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		String sessionStartDate=(String) session.getAttribute("startDate");
		String sessionEndDate=(String)session.getAttribute("endDate");
		
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		
		input.setUserId(userId);
		input.setSessionStartDate(sessionStartDate);
		input.setSessionEndDate(sessionEndDate);
        input.setCourseCode(request.getParameter("courseCode"));
        input.setExternalExaminerId(request.getParameter("examinerCode"));
        input.setFirstDate(request.getParameter("firstDate"));
        input.setSecondDate(request.getParameter("secondDate"));
        input.setThirdDate(request.getParameter("thirdDate"));
        input.setTime(request.getParameter("time"));
        input.setSecondTime(request.getParameter("secondTime"));
        input.setThirdTime(request.getParameter("thirdTime"));
        String resultInsertSuccess = externalExaminerCourseConnect.insertExaminerCourse(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultInsertSuccess);
    }

    /**
     * Method for getting the records already defined for the selected course
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getCourseExaminerDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
        String sessionStartDate=(String) session.getAttribute("startDate");
		String sessionEndDate=(String)session.getAttribute("endDate");
	
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setSessionStartDate(sessionStartDate);
		input.setSessionEndDate(sessionEndDate);
        input.setCourseCode(request.getParameter("courseCode"));

        List<ExternalExaminerCourseInfoGetter> resultRecordsDetails = externalExaminerCourseConnect.getCourseExaminerList(input);

        return new ModelAndView("ExternalExaminerCourse/ExternalExaminerCourse",
            "resultObject", resultRecordsDetails);
    }

    /**
     * Method for deleting the records for the selected course
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView deleteExaminerReocrd(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
        String sessionStartDate=(String) session.getAttribute("startDate");
		String sessionEndDate=(String)session.getAttribute("endDate");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setSessionStartDate(sessionStartDate);
		input.setSessionEndDate(sessionEndDate);
        input.setCourseCode(request.getParameter("courseCode"));

        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "examinerCourseRecords"), ",");

        String resultDeleteDetails = externalExaminerCourseConnect.deleteExaminerRecord(input,
                items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultDeleteDetails);
    }
    
    /**
     * Method for updating the record for the selected course
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView updateExaminerReocrd(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
        String sessionStartDate=(String) session.getAttribute("startDate");
		String sessionEndDate=(String)session.getAttribute("endDate");
		
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
		input.setSessionStartDate(sessionStartDate);
		input.setSessionEndDate(sessionEndDate);
        input.setCourseCode(request.getParameter("courseCode"));
        input.setExternalExaminerId(request.getParameter("externalExaminerId"));
        input.setFirstDate(request.getParameter("firstDate"));
        input.setSecondDate(request.getParameter("secondDate"));
        input.setThirdDate(request.getParameter("thirdDate"));
        input.setTime(request.getParameter("time"));
        input.setSecondTime(request.getParameter("secondTime"));
        input.setThirdTime(request.getParameter("thirdTime"));

        String resultUpdateDetails = externalExaminerCourseConnect.updateExaminerRecord(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultUpdateDetails);
    }
}
