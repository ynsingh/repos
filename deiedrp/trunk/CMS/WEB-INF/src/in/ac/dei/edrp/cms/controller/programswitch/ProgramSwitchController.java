/*
 * @(#) ProgramSwitchController.java
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
package in.ac.dei.edrp.cms.controller.programswitch;

import in.ac.dei.edrp.cms.dao.programswitch.ProgramSwitchConnect;
import in.ac.dei.edrp.cms.domain.programswitch.ProgramSwitchInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This controller is designed for setting & retrieving
 * information about program Switch setup.
 * @author Ashish
 * @date 14 Mar 2011
 * @version 1.0
 */
public class ProgramSwitchController extends MultiActionController {
    private ProgramSwitchConnect programSwitchConnect;

    /**
     * @param programSwitchConnect the programSwitchConnect to set
     */
    public void setProgramSwitchConnect(
        ProgramSwitchConnect programSwitchConnect) {
        this.programSwitchConnect = programSwitchConnect;
    }

    /**
     * Method for getting the list of programs-combinations
     * for the concerned university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getInputRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<ProgramSwitchInfoGetter> resultInputDetails = programSwitchConnect.getInputRecords(input);

        return new ModelAndView("ProgramSwitch/ProgramSwitch", "resultObject",
            resultInputDetails);
    }
    
    /**
     * Method for getting the list of programs-combinations
     * for the concerned university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getOldSemestersDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
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

        List<ProgramSwitchInfoGetter> resultInputDetails = programSwitchConnect.getOldSemesters(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles", "resultObject",
            resultInputDetails);
    }

    /**
     * Method for getting the list Switch types
     * defined for the concerned university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getSwitchTypes(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<ProgramSwitchInfoGetter> resultSwitchTypeDetails = programSwitchConnect.getSwitchTypes(input);

        return new ModelAndView("UniversityRolesSetup/UniversityRoles",
            "resultObject", resultSwitchTypeDetails);
    }

    /**
     * Method for setting up a record
     * of the selected program combination
     * based on the activity(insert/update) for the university
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView setProgramSwitchDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();

        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);
        input.setSwitchRuleId(request.getParameter("switchRuleId"));
        input.setSwitchTypeId(request.getParameter("swtichType"));
        input.setFromEntityId(request.getParameter("entityId"));
        input.setOldProgramId(request.getParameter("oldProgramId"));
        input.setOldBranchId(request.getParameter("oldBranchId"));
        input.setOldSpecializationId(request.getParameter("oldSpecializationId"));
        input.setOldSemesterCode(request.getParameter("oldSemesterId"));
        input.setEntityId(request.getParameter("newEntityId"));
        input.setProgramId(request.getParameter("newProgramId"));
        input.setBranchId(request.getParameter("newBranchId"));
        input.setSpecializationId(request.getParameter("newSpecializationId"));
        input.setSemesterCode(request.getParameter("newSemesterId"));
        input.setActivity(request.getParameter("activity"));
        input.setOldSwitchRuleId(request.getParameter("oldSwitchRuleId"));

        String resultUpdateDetails = programSwitchConnect.setProgramSwitchDetails(input);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultUpdateDetails);
    }

    /**
     * Method for getting the list of
     * defined records set for the university
     * @param request
     * @param response
     * @return List
     * @throws Exception
     */
    public ModelAndView getSetRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
        HttpSession session = request.getSession(true);

        String userId = (String) session.getAttribute("userId");
		
		if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		input.setUserId(userId);

        List<ProgramSwitchInfoGetter> resultprogramDetails = programSwitchConnect.getSetRecords(input);

        return new ModelAndView("ProgramSwitch/ProgramSwitchRecords",
            "resultObject", resultprogramDetails);
    }

    /**
     * Method for deleting the selected records from program switch
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    public ModelAndView deleteProgramSwitchRecords(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StringTokenizer items = new StringTokenizer(request.getParameter(
                    "deleteRecords"), ",");

        String resultDeleteDetails = programSwitchConnect.deleteProgramSwitchRecords(items);

        return new ModelAndView("preProcessChecks/preProcessResultlist",
            "resultObject", resultDeleteDetails);
    }
    
    public ModelAndView getProgramCourseTypeDetails(HttpServletRequest request,
    					HttpServletResponse response)throws Exception{
    	
    	ProgramSwitchInfoGetter input = new ProgramSwitchInfoGetter();
        HttpSession session = request.getSession(true);
    	
    	 String userId = (String) session.getAttribute("userId");
 		
 		if(userId == null)
         {
         return new ModelAndView("general/SessionInactive","sessionInactive",true);
         }
 		
 		input.setUserId(userId);
 		input.setCreatorId(request.getParameter("counter"));
 		
 		
 		if(input.getCreatorId().equalsIgnoreCase("one")){
 			
 			input.setEntityId(request.getParameter("entityId"));
 			
 		}else if(input.getCreatorId().equalsIgnoreCase("two")){
 			
 			input.setEntityId(request.getParameter("entityId"));
 			input.setProgramId(request.getParameter("programId"));
 			
 		}else if(input.getCreatorId().equalsIgnoreCase("three")){
 			
 			input.setEntityId(request.getParameter("entityId"));
 			input.setProgramId(request.getParameter("programId"));
 			input.setBranchId(request.getParameter("branchId"));
 			
 		}

         List<ProgramSwitchInfoGetter> resultprogramDetails = programSwitchConnect.getRecords4Counter(input);

         return new ModelAndView("UniversityRolesSetup/UniversityRoles",
             "resultObject", resultprogramDetails);
    	
    	
    	
    	
    }
}
