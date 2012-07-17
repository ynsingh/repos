/*
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
package in.ac.dei.edrp.cms.controller.cgpadivision;

import in.ac.dei.edrp.cms.dao.cgpadivision.CgpaDivisionConnect;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information CGPA division setup.
 * @author Ashish
 * @date 8 Mar 2011
 * @version 1.0
 */
public class CgpaDivisionController extends MultiActionController {
    private CgpaDivisionConnect cgpaDivisionConnect;

    /**
    * The setter method of the interface associated
    * with this controller
    * @param cgpaDivisionConnect
    */
    public void setCgpaDivisionConnect(CgpaDivisionConnect cgpaDivisionConnect) {
        this.cgpaDivisionConnect = cgpaDivisionConnect;
    }

    /**
     * This method retrieves the list of divisions(active) defined for the university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getInputDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        CgpaDivisionInfoGetter input = new CgpaDivisionInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<CgpaDivisionInfoGetter> resultDivisions = cgpaDivisionConnect.getDivisions(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultDivisions);
    }

    /**
     * This method is used for setting up the details for the divisions
     * on the basis of activity(insert/update)
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView setDivisionDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        CgpaDivisionInfoGetter input = new CgpaDivisionInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setDivisionId(request.getParameter("divisionId"));
        input.setMinCGPA(request.getParameter("minCGPA"));
        input.setMaxCGPA(request.getParameter("maxCGPA"));
        input.setActivity(request.getParameter("activity"));

        String resultSetSuccess = cgpaDivisionConnect.setDivisionDetails(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultSetSuccess);
    }

    /**
     * This method retrieves the divisions already defined for the university.
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getSetData(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        CgpaDivisionInfoGetter input = new CgpaDivisionInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<CgpaDivisionInfoGetter> resultDivisions = cgpaDivisionConnect.getSetDivisionRecords(input);

        return new ModelAndView("CGPADivision/CGPADivision", "resultObject",
            resultDivisions);
    }

    /**
    * Method for deleting records(divisions)for the concerned university
    * @param request
    * @param response
    * @return String
    * @throws Exception
    */
    public ModelAndView deleteRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        CgpaDivisionInfoGetter input = new CgpaDivisionInfoGetter();        

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "divisionRecords"), ",");

        String resultDeleteDetails = cgpaDivisionConnect.deleteDivisionRecords(input,
                items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultDeleteDetails);
    }
}
