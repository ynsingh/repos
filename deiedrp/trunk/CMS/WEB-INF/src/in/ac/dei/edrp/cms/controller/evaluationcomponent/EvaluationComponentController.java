/*
 * @(#) EvaluationComponentController.java
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
package in.ac.dei.edrp.cms.controller.evaluationcomponent;

import in.ac.dei.edrp.cms.dao.evaluationcomponent.EvaluationComponentConnect;
import in.ac.dei.edrp.cms.domain.evaluationcomponent.EvaluationComponentInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about the adding evaluation components.
 * @author Ashish
 * @date 1 Mar 2011
 * @version 1.0
 */
public class EvaluationComponentController extends MultiActionController {
    private EvaluationComponentConnect evaluationComponentConnect;

    /**
    * The setter method of the interface associated
    * with this controller
    * @param evaluationComponentConnect
    */
    public void setEvaluationComponentConnect(
        EvaluationComponentConnect evaluationComponentConnect) {
        this.evaluationComponentConnect = evaluationComponentConnect;
    }

    /**
     * Method for getting the different input fields required
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getInputDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EvaluationComponentInfoGetter input = new EvaluationComponentInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);	
        input.setEvaluationComponents(request.getParameter(
                "evaluationComponent"));

        List<EvaluationComponentInfoGetter> resultInputDetails = evaluationComponentConnect.getEvaluationComponentList(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultInputDetails);
    }

    /**
     * Method for getting inserting arecord for the selected evaluation component
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView setDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EvaluationComponentInfoGetter input = new EvaluationComponentInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setEvaluationId(request.getParameter("evaluationComponentId"));
        input.setTypeId(request.getParameter("typeId"));
        input.setDisplayId(request.getParameter("displayId"));
        input.setActivity(request.getParameter("activity"));

        String resultSetDetails = evaluationComponentConnect.setEvaluationDetails(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultSetDetails);
    }

    /**
     * Method for getting the set details w.r.t. the university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getSetDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EvaluationComponentInfoGetter input = new EvaluationComponentInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<EvaluationComponentInfoGetter> resultgetSetDetails = evaluationComponentConnect.getSetRecordsDetails(input);

        return new ModelAndView("EvaluationComponent/EvaluationComponentRecords",
            "resultObject", resultgetSetDetails);
    }

    /**
     * Method for deleting the selected records for the concerned university
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView deleteRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        EvaluationComponentInfoGetter input = new EvaluationComponentInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "evaluationRecords"), ",");

        String resultDetails = evaluationComponentConnect.deleteEvaluationRecords(input,
                items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultDetails);
    }
}
