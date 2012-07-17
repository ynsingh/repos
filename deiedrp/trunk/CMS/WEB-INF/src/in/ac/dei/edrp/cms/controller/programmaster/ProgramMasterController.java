/*
 * @(#) ProgramMasterController.java
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


import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programmaster.ProgramMasterDao;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Controller for Program Master functionality
 * @author Manpreet Kaur
 * @date 22-02-2011
 * @version 1.0
 */
public class ProgramMasterController extends MultiActionController {
    ProgramMasterDao programMasterDao;

    /**
     * Method to initialize controller
     * @param programMasterDao object of EntityMasterDao interface
     */
    public void setProgramMasterDao(ProgramMasterDao programMasterDao) {
        this.programMasterDao = programMasterDao;
    }

    /**
     *  Method to get list of program modes available in university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodUniversityProgramMode(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> modeList = programMasterDao.methodUniversityProgramMode(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/ModeList", "modeList", modeList);
    }

    /**
     *  Method to get list of program types available in university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodUniversityProgramType(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> typeList = programMasterDao.methodUniversityProgramType(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/TypeList", "typeList", typeList);
    }

    /**
     * Method for getting list of branches that can be associated for programs under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodBranchList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> branchList = programMasterDao.methodBranchList(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/BranchList", "branchList",
            branchList);
    }

    /**
     * Method for getting list of branches that can be associated for programs under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodSpecList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> specList = programMasterDao.methodSpecList(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/BranchList", "branchList",
            specList);
    }

    /**
     * Method for getting list of branches that can be associated for programs under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodSemList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> semList = null;

        semList = programMasterDao.methodSemesterList(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/BranchList", "branchList",
            semList);
    }

    /**
     * Method for adding new program under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodAddProgDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        HttpSession session=request.getSession(true);
        System.out.println("inside the method");
    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	inputObject.setProgramCode(request.getParameter("program_code"));
        inputObject.setProgramName(request.getParameter("program_name").trim());
        inputObject.setProgramType(request.getParameter("program_type").trim());
        inputObject.setProgramMode(request.getParameter("program_mode").trim());
        inputObject.setProgramDescription(request.getParameter("program_description").trim());
        inputObject.setBranch(request.getParameter("branch_exists").trim());
        inputObject.setSpecilization(request.getParameter(
                "specialization_exists").trim());
        inputObject.setNumberOfTerms(request.getParameter("no_of_terms").trim());
        inputObject.setTotalCredits(request.getParameter("total_credits").trim());
        inputObject.setNumberOfAttemptAllowed(request.getParameter(
                "number_of_attempt_allowed").trim());
        inputObject.setMaxNumberOfFailSubjects(request.getParameter(
                "max_number_of_fail_subjects").trim());
        inputObject.setFixedDuration(request.getParameter("fixed_duration").trim());
        inputObject.setUgOrPg(request.getParameter("ug_pg").trim());
        inputObject.setTencodes(request.getParameter("ten_codes").trim());
        inputObject.setPrintAggregate(request.getParameter("print_aggregate").trim());
        inputObject.setDgpa(request.getParameter("dgpa").trim());
        inputObject.setMaxRegSemester(request.getParameter("max_reg_semester").trim());
        inputObject.setCreditRequired(request.getParameter("credit_required").trim());
        inputObject.setFixedOrVariableCredit(request.getParameter(
                "fixed_or_variable_credit").trim());
        inputObject.setCreatorId(session.getAttribute("userId").toString());
        inputObject.setBranchcode(request.getParameter("branch_list").trim());
        inputObject.setSpecializationCode(request.getParameter("spec_list").trim());
        inputObject.setMaximumDuration(request.getParameter("max_duration").trim());
        inputObject.setMinimunDuration(request.getParameter("min_duration").trim());
        inputObject.setStartdate(request.getParameter("startdate").trim());
        inputObject.setActiveSemester(request.getParameter("active_sem_list").trim());

        String result = "";

        try {
            result = programMasterDao.methodAddProgDetails(session.getAttribute("universityId").toString(), inputObject);

            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", result);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }
    }

    /**
     * Method for getting list of programs added under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodprogList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);
    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
    	System.out.println("in method program after session inactive");
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	List<ProgramMasterInfoGetter> progList = null;

        progList = programMasterDao.methodprogList(session.getAttribute("universityId").toString());

        return new ModelAndView("programmaster/BranchList", "branchList",
            progList);
    }

    /**
     * Method for getting program details for editing purpose
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodProgMasterDetailsForManage(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	String criteria = request.getParameter("criteria");
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setProgramCode(request.getParameter("programCode"));
               
        inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));

        List<ProgramMasterInfoGetter> progList = null;

        progList = programMasterDao.methodProgMasterDetailsForManage(session.getAttribute("userId").toString(),
                inputObj, criteria);

        return new ModelAndView("programmaster/BasicDetails", "programList",
            progList);
    }

    /**
     * Method for updating program details
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodUpdateProgDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("program_id"));
        inputObject.setProgramName(request.getParameter("program_name").trim());
        inputObject.setProgramType(request.getParameter("program_type").trim());
        inputObject.setProgramMode(request.getParameter("program_mode").trim());
        inputObject.setNumberOfTerms(request.getParameter("no_of_terms").trim());
        inputObject.setTotalCredits(request.getParameter("credit_required").trim());
        inputObject.setNumberOfAttemptAllowed(request.getParameter(
                "number_of_attempt_allowed").trim());
        inputObject.setMaxNumberOfFailSubjects(request.getParameter(
                "max_number_of_fail_subjects").trim());
        inputObject.setUgOrPg(request.getParameter("ug_pg").trim());
        inputObject.setTencodes(request.getParameter("ten_codes").trim());
        inputObject.setPrintAggregate(request.getParameter("print_aggregate").trim());
        inputObject.setDgpa(request.getParameter("dgpa").trim());
        inputObject.setMaxRegSemester(request.getParameter("max_reg_semester").trim());
        inputObject.setCreditRequired(request.getParameter("credit_required").trim());
        inputObject.setFixedOrVariableCredit(request.getParameter(
                "fixed_or_variable_credit").trim());
        inputObject.setProgramDescription(request.getParameter("programDescription").trim());

        programMasterDao.methodUpdateProgBasicDetails(session.getAttribute("userId").toString(), inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            null);
    }

    /**
     * Method for getting program's branch and specialization details for editing purpose
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodProgBranchSpecDetailsForManage(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	String criteria = request.getParameter("criteria");
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setProgramCode(request.getParameter("programCode"));
        inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));

        List<ProgramMasterInfoGetter> branchSpecList = programMasterDao.methodProgBranchSpecDetailsForManage(inputObj,
                criteria);

        return new ModelAndView("programmaster/BranchSpecList", "bsList",
            branchSpecList);
    }

    /**
     * Method for adding new branch and specialization under program
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodAddAnotherBranch(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("programId"));
        inputObject.setProgramCode(request.getParameter("programCode"));
        inputObject.setBranchcode(request.getParameter("branchCode"));
        inputObject.setSpecializationCode(request.getParameter("specCode"));
        inputObject.setActiveSemester(request.getParameter("activeSem"));
        inputObject.setCreatorId(session.getAttribute("userId").toString());

        String result = programMasterDao.methodAddAnotherBranch(inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }

    /**
     * Method for deleting a program under university
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodBranchDelete(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	
        ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("progId"));
        inputObject.setBranchcode(request.getParameter("branchId"));
        inputObject.setSpecializationCode(request.getParameter("specId"));
        try{
        String result = programMasterDao.methodBranchDelete(inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
}   catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }
    }

    /**
     * Method for getting program's duration details for editing purpose
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodProgDurationDetailsForManage(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setProgramCode(request.getParameter("programCode"));
        inputObj.setCreatorId(session.getAttribute("userId").toString());

        try {
            List<ProgramMasterInfoGetter> durationList = programMasterDao.methodProgDurationDetailsForManage(inputObj);

            return new ModelAndView("programmaster/DurationDetails",
                "durationList", durationList);
        } catch (Exception e) {
            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }
    }

    /**
     * Method for deleting program duration detail
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodProgDurationDelete(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("progId"));
        inputObject.setStartdate(request.getParameter("startDate"));
try{
        programMasterDao.methodProgDurationDelete(inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            null);
}   catch (MyException e) {
    return new ModelAndView("RegistrationForm/RegisterStudent",
        "result", e.getMessage());
}
    }

    /**
     * Method to update program duration details
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodUpdateProgDurationDetails(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("progId"));
        inputObject.setMinimunDuration(request.getParameter("min_duration"));
        inputObject.setMaximumDuration(request.getParameter("max_duration"));
        inputObject.setFixedDuration(request.getParameter("fixed_duration"));
        inputObject.setModifierId(session.getAttribute("userId").toString());

        programMasterDao.methodUpdateProgDurationDetails(inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            null);
    }

    /**
     * Method for add new start date for program
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodAddStartDate(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	ProgramMasterInfoGetter inputObject = new ProgramMasterInfoGetter();
        inputObject.setProgramId(request.getParameter("progId"));
        inputObject.setStartdate(request.getParameter("startDate"));
        inputObject.setMinimunDuration(request.getParameter("min_duration"));
        inputObject.setMaximumDuration(request.getParameter("max_duration"));
        inputObject.setCreatorId(session.getAttribute("userId").toString());
        programMasterDao.methodAddStartDate(inputObject);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            null);
    }

    /**
     * Method to delete program's all details
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView methodDeleteProg(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String result = "";
        HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userId");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
        
        try {
        	result = programMasterDao.methodDeleteProg(request.getParameter("progId"));

        	 return new ModelAndView("RegistrationForm/RegisterStudent", "result",
        	            result);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }
       
    }
}
