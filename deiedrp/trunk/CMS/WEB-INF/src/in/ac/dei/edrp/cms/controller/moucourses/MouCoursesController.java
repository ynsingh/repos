/*
 * @(#) MouCoursesController.java
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
package in.ac.dei.edrp.cms.controller.moucourses;

import in.ac.dei.edrp.cms.dao.moucourses.MouCoursesConnect;
import in.ac.dei.edrp.cms.domain.moucourses.MouCoursesInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about MOU University Courses setup.
 * @author Ashish
 * @date 11 Mar 2011
 * @version 1.0
 */
public class MouCoursesController extends MultiActionController {
    private MouCoursesConnect mouCoursesConnect;

    public void setMouCoursesConnect(MouCoursesConnect mouCoursesConnect) {
        this.mouCoursesConnect = mouCoursesConnect;
    }

    /**
     * Method for getting the list of Universities(MOU)
     * for the concerned university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getMouUniversityDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        MouCoursesInfoGetter input = new MouCoursesInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<MouCoursesInfoGetter> resultUniversityDetails = mouCoursesConnect.getUniversitiesList(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultUniversityDetails);
    }

    /**
     * Method for getting the list of courses which can be offered
     * as an MOU course(offered by the selected university(MOU)) in our university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getMouCoursesDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        MouCoursesInfoGetter input = new MouCoursesInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setMouUniversityId(request.getParameter("mouUniversityId"));

        List<MouCoursesInfoGetter> resultUniversityDetails = mouCoursesConnect.getMOUCoursesList(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultUniversityDetails);
    }

    /**
     * Method for setting up a record
     * for university-mou university-mou course combination
     * for the activity(insert/update) performed
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView setMouCoursesDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        MouCoursesInfoGetter input = new MouCoursesInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setMouUniversityId(request.getParameter("mouUniversityId"));
        input.setCourseCode(request.getParameter("mouCourse"));
        input.setCourseStatus(request.getParameter("courseStatus"));
        input.setActivity(request.getParameter("activity"));

        String resultMOUDetails = mouCoursesConnect.setMouCoursesDetails(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultMOUDetails);
    }

    /**
     * Method for getting the list of records
     * already defined for the university-mou_university combination
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView setSetRecordsDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        MouCoursesInfoGetter input = new MouCoursesInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setMouUniversityId(request.getParameter("mouUniversityId"));

        List<MouCoursesInfoGetter> resultUniversityDetails = mouCoursesConnect.getSetRecordsList(input);

        return new ModelAndView("MOUCourses/MOUCourses", "resultObject",
            resultUniversityDetails);
    }

    /**
     * Method for deleting the records already defined
     * for the concerned university-mou_university combination
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView deleteMOURecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        MouCoursesInfoGetter input = new MouCoursesInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setMouUniversityId(request.getParameter("mouUniversityId"));

        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "MOURecords"), ",");

        String resultMOUDetails = mouCoursesConnect.deleteMOURecords(input,
                items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultMOUDetails);
    }
}
