/*
 * @(#) AwardSheetController.java
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
package in.ac.dei.edrp.cms.controller.awardsheet;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.awardsheet.AwardSheetDao;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramMasterInfoGetter;
import in.ac.dei.edrp.cms.domain.programmaster.ProgramTermDetailsInfoGetter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * Controller for Award Blank Sheet
 * @author Manpreet Kaur
 * @date 20-03-2011
 * @version 1.0
 */
public class AwardSheetController extends MultiActionController {
    AwardSheetDao awardSheetDao;
 private static Logger logObj = Logger.getLogger(AwardSheetController.class);
    /**
     * Method to initialize controller
     * @param awardSheetDao object of AwardSheetDao interface
     */
    public void setAwardSheetDao(AwardSheetDao awardSheetDao) {
        this.awardSheetDao = awardSheetDao;
        
        
    }

    /**
     *  Method to get the list of courses of the instructor
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getCourseList(HttpServletRequest request,
        HttpServletResponse response) throws Exception { // method updated by ankit
        
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
		AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
//        inputObj.setEntityId(session.getAttribute("userId").toString().substring(1,9));
        inputObj.setCreatorId(session.getAttribute("userId").toString());
        inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
        
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
        inputObj.setDisplayType(request.getParameter("displayType"));

        List<AwardSheetInfoGetter> courseList = awardSheetDao.getCourseList(inputObj);
        return new ModelAndView("awardsheet/CourseList", "result", courseList);
    }

    /**
     *  Method to get list of evaluation components for given course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getEvaluationComponents(HttpServletRequest request,
        HttpServletResponse response) throws Exception { // no change in this method
        
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
        
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setCreatorId(session.getAttribute("userId").toString());

        List<AwardSheetInfoGetter> componentList = awardSheetDao.getEvaluationComponents(inputObj);

        return new ModelAndView("awardsheet/EvaluationComponentList", "result",
            componentList);
    }

    /**
     *  Method to get list of students for given course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getStudentList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
    	ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
    	inputObj.setEntityId(request.getParameter("entityId"));
    	inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setBranchcode(request.getParameter("branchCode"));
        inputObj.setSpecializationCode(request.getParameter("specCode"));
        inputObj.setSystemCode(request.getParameter("semesterCode"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setSemesterStartDate(request.getParameter("startDate"));
        inputObj.setSemesterEndDate(request.getParameter("endDate"));

        List<AwardSheetInfoGetter> studentList = awardSheetDao.getStudentList(inputObj);

        return new ModelAndView("awardsheet/StudentList", "result", studentList);
    }

    /**
     *  Method to get list of marks of students for given course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getStudentMarks(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        
		inputObj.setEntityId(request.getParameter("entityId"));
		inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setProgramId(request.getParameter("programId"));
        inputObj.setBranchcode(request.getParameter("branchCode"));
        inputObj.setSpecializationCode(request.getParameter("specCode"));
        inputObj.setSystemCode(request.getParameter("semesterCode"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartdate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setSystemValue(request.getParameter("buttonPressed"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
             
        List<AwardSheetInfoGetter> marksList = awardSheetDao.getStudentMarks(inputObj);

        return new ModelAndView("awardsheet/MarksList", "result", marksList);
    }

    /**
     *  Method to get list of marks of students for given course
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getRule(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
		inputObj.setSemesterStartDate(request.getParameter("semesterStartDate"));
		inputObj.setSemesterEndDate(request.getParameter("semesterEndDate"));
        inputObj.setUniversityId(session.getAttribute("universityId").toString());

        List<AwardSheetInfoGetter> rule = awardSheetDao.getStudentMarks(inputObj);

        return new ModelAndView("termdetails/SysTwoList", "result", rule);
    }

    /**
    *   Method for checking status of existing entry regarded given course
    * @param request
    * @param response
    * @return object of ModelAndView
    * @throws Exception
    */
    public ModelAndView getStatus(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
    	ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartdate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setEntityId(request.getParameter("entityId"));
        inputObj.setCreatorId(request.getParameter("employeeCode"));
        inputObj.setEmployeeCode(request.getParameter("employeeCode"));
        inputObj.setUniversityId(request.getParameter("entityId").substring(0, 4));
        inputObj.setDisplayType(request.getParameter("displayType"));
        String statusValue = awardSheetDao.checkStatus(inputObj);

        return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
    }

    /**
     *   Method for getting employee code
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getEmployeeCode(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
 
        List<AwardSheetInfoGetter> employeeCode = awardSheetDao.getEmployeeCode(session.getAttribute("userId").toString());

        return new ModelAndView("termdetails/SysTwoList", "result", employeeCode);
    }

    /**
     *   Method for getting list of pending requests
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getPendingRequestList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setCreatorId(request.getParameter("employeeCode"));
        inputObj.setDisplayType(request.getParameter("displayType"));
       

        List<ProgramTermDetailsInfoGetter> pendingList = awardSheetDao.getPendingList(inputObj);
        return new ModelAndView("awardsheet/PendingList", "result", pendingList);
    }
    
    /**
     *   Method for getting list of pending requests
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getApprovedRequestList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        ProgramMasterInfoGetter inputObj = new ProgramMasterInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setCreatorId(request.getParameter("employeeCode"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        
        List<ProgramTermDetailsInfoGetter> approvedList = awardSheetDao.getApprovedList(inputObj);
        return new ModelAndView("awardsheet/PendingList", "result", approvedList);
    }
    

    /**
     * Method for insert/update student marks
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView saveStudentMarks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		inputObj.setEntityId(request.getParameter("entityId"));
		inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setCreatorId(request.getParameter("employeeCode"));
        
        StringTokenizer data = new StringTokenizer(request.getParameter("data"), ";");
        String statusValue = null;
        try {
        	statusValue = awardSheetDao.saveStudentMarks(inputObj, data);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent","result", e.getMessage());
        }

        return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
    }

    /**
     * Method for submitting award sheet for approval
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView submitForApproval(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();

        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setEntityId(request.getParameter("entityId"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setEmployeeCode(request.getParameter("employeeCode"));
        inputObj.setStatus(request.getParameter("status"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setCreatorId((String) session.getAttribute("userId"));
        String result = null;

        try {
            result = awardSheetDao.submitForApproval(inputObj, "S");
        } catch (MyException e) {

            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }

    /**
     * Method for submitting award sheet for approval
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView withdrawRequest(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();

        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setEntityId(request.getParameter("entityId"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setEmployeeCode(request.getParameter("employeeCode"));
        inputObj.setStatus(request.getParameter("status"));
        inputObj.setUniversityId(request.getParameter("entityId").substring(0, 4));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setCreatorId((String) session.getAttribute("userId"));
        String result = null;

        try {
            result = awardSheetDao.withdrawRequest(inputObj);
        } catch (MyException e){
        	return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }

    /**
     * Method for approving award blank sheet
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView approveRequest(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();

        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setEntityId(request.getParameter("entity"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setEmployeeCode(request.getParameter("employeeCode"));
        inputObj.setStatus(request.getParameter("status"));
        inputObj.setPreviousStatus(request.getParameter("previousStatus"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setPreviousRequestSender(request.getParameter("requestSender"));       
        inputObj.setCreatorId((String)session.getAttribute("userId"));
        inputObj.setUniversityId((String)session.getAttribute("universityId"));
        
        String result = null;

        try {
            result = awardSheetDao.approveRequest(inputObj);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent","result", e.getMessage());
        }
        return new ModelAndView("RegistrationForm/RegisterStudent", "result", result);
    }

    /**
     * Method for approving award blank sheet
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView rejectRequest(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();

        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        inputObj.setEntityId(request.getParameter("entity"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setStartDate(request.getParameter("startDate"));
        inputObj.setEndDate(request.getParameter("endDate"));
        inputObj.setEmployeeCode(request.getParameter("employeeCode"));
        inputObj.setStatus(request.getParameter("status"));
        inputObj.setDisplayType(request.getParameter("displayType")); // added by ankit
        inputObj.setReason(request.getParameter("reason"));
        inputObj.setCreatorId((String)session.getAttribute("userId"));
        inputObj.setUniversityId(session.getAttribute("universityId").toString());
        String result = null;

        try {
            result = awardSheetDao.rejectRequest(inputObj);
        } catch (MyException e) {
            return new ModelAndView("RegistrationForm/RegisterStudent",
                "result", e.getMessage());
        }

        return new ModelAndView("RegistrationForm/RegisterStudent", "result",
            result);
    }
    
    public ModelAndView showApprovedRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
    	 String sep = System.getProperty("file.separator");
    	 String entity = request.getParameter("entityName");
    	 String program = request.getParameter("programName");
         String branch = request.getParameter("branchName");
         String specialization =request.getParameter("specializationName");
         String semester = request.getParameter("semesterName");
         String courseCode =request.getParameter("courseCode");

         String displayType= request.getParameter("displayType");
         String filePath = "AwardSheet";
        
         AwardSheetInfoGetter inputObj= new AwardSheetInfoGetter();
         inputObj.setEntityId(request.getParameter("entityId"));
         inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
         inputObj.setCourseCode(courseCode);
         
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
         inputObj.setDisplayType(displayType);
         
         AwardSheetInfoGetter empObject = awardSheetDao.getStarterEmployee(inputObj);
         
         filePath = filePath+sep+empObject.getEmployeeId();;
         
         filePath = filePath+sep+entity;
         filePath = filePath+sep+courseCode;
              
         if(displayType.equalsIgnoreCase("I")){
         	filePath = filePath+sep+"Internal";
         }else if(displayType.equalsIgnoreCase("E")){
         	filePath = filePath+sep+"External";
         }else if(displayType.equalsIgnoreCase("R")){
         	filePath = filePath+sep+"Remedial";
         }
         
         filePath=filePath+sep+entity+"_"+program+"_"+branch+"_"+specialization+"_"+semester+"_"+
         courseCode+" ("+displayType+")" + ".pdf";

     	 return new ModelAndView("RegistrationForm/RegisterStudent", "result",
                 filePath);	
    }
    
    /**
     * Method for getting the grade.
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getGrades(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
		inputObj.setUniversityId(universityId);
        List<AwardSheetInfoGetter>  result = awardSheetDao.getGrade(inputObj);
        return new ModelAndView("awardsheet/grade","result", result);
    }
    
    /**
     *   Method for getting list of rejected requests
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getRejectedRequestList(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        inputObj.setUniversityId(session.getAttribute("universityId").toString());
        inputObj.setRequestSender(request.getParameter("employeeCode"));
        inputObj.setDisplayType(request.getParameter("displayType"));
        inputObj.setStatus(request.getParameter("status"));
        
        //Change Done By Dheeraj For Allowing multiple Approvers on Same Course Code Only in Current Session
        inputObj.setSessionStartDate(session.getAttribute("startDate").toString());
        inputObj.setSessionEndDate(session.getAttribute("endDate").toString());
        
        List<AwardSheetInfoGetter> rejectedList = awardSheetDao.getRejectedList(inputObj);        
        return new ModelAndView("awardsheet/PendingList", "result", rejectedList);
    }
    
    /**
     * Method to check next approval exist.
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView isNextApprovalExist(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        inputObj.setEntityId(request.getParameter("entityId"));
        inputObj.setCourseCode(request.getParameter("courseCode"));
        inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
        
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
        inputObj.setDisplayType(request.getParameter("displayType"));
        
        String message = awardSheetDao.isNextApprovalExist(inputObj);        
        return new ModelAndView("associatecoursewithinstructor/Result", "message", message);
    }
    
    /**
     * Method to check External Award Blank Allowed.
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView IsExternalAwardAllowed(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        
		inputObj.setUniversityId(universityId);
		inputObj.setCourseCode(request.getParameter("courseCode"));
        
		String message = awardSheetDao.IsExternalAwardAllowed(inputObj);        
        return new ModelAndView("associatecoursewithinstructor/Result", "message", message);
    }
	
	/**
     *  Method to Set setter/getters for Generate Excel file 
     *  @author Devendra Singhal
     * @param request
     * @param response
     * @return object of ModelAndView containing String message
     * @throws Exception
     */
    public ModelAndView downloadTemplate(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String dataProvider= request.getParameter("dataProviderList");
		String column=request.getParameter("columnNameList");
		String CTGroupName=request.getParameter("CTGroupName");
		String fileName=request.getParameter("fileName");
		StringTokenizer columnToken=new StringTokenizer(column,",");
		StringTokenizer dataProviderToken=new StringTokenizer(dataProvider,"|");
		StringTokenizer CTTokens=new StringTokenizer(request.getParameter("CTList"),",");
        String message=generateExcel(columnToken,dataProviderToken,CTTokens,CTGroupName,fileName);
        return new ModelAndView("buildactivitymaster/Result","message", message);
    }
    
    /**
     *  Method to Set setter/getters for upload Generated Excel File
     *  @author Devendra Singhal
     * @param request
     * @param response
     * @return object of ModelAndView containing String message
     * @throws Exception
     */
    public ModelAndView uploadTemplate(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
    	 ProgramMasterInfoGetter studentoldmarks = new ProgramMasterInfoGetter();
        HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		String userId=(String) session.getAttribute("userId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		String sep=System.getProperty("file.separator");
		String filePath=this.getServletContext().getRealPath("/");
		inputObj.setUniversityId(universityId);
		//inputObj.setCreatorId(userId);
		 inputObj.setCreatorId(request.getParameter("employeeCode"));
		String data=request.getParameter("data");
		String fileName=request.getParameter("fileName");
		String fileName1=request.getParameter("fileName1");
		String cList=request.getParameter("CList");
		String display=request.getParameter("display");
		
		////////////////////////////////////////////////////////
		studentoldmarks.setEntityId(request.getParameter("entityId"));
		studentoldmarks.setUniversityId(session.getAttribute("universityId").toString());
		studentoldmarks.setProgramId(request.getParameter("programId"));
		studentoldmarks.setBranchcode(request.getParameter("branchCode"));
		studentoldmarks.setSpecializationCode(request.getParameter("specCode"));
		studentoldmarks.setSystemCode(request.getParameter("semesterCode"));
		studentoldmarks.setCourseCode(request.getParameter("courseCode"));
		studentoldmarks.setStartdate(request.getParameter("startDate"));
		studentoldmarks.setEndDate(request.getParameter("endDate"));
		studentoldmarks.setSystemValue(request.getParameter("buttonPressed"));
		studentoldmarks.setDisplayType(request.getParameter("displayType"));
		studentoldmarks.setProgramCourseKey(request.getParameter("programCourseKey"));
             
        List<AwardSheetInfoGetter> oldmarks = awardSheetDao.getStudentMarks(studentoldmarks);
        
        if (oldmarks==null){
        	AwardSheetInfoGetter emptyobject = new AwardSheetInfoGetter(); 
        	emptyobject.setRollNumber("notfound");
        	oldmarks =new ArrayList<AwardSheetInfoGetter>();
        	oldmarks.add(emptyobject);
        	
        }

		
		////////////////////////////////////////////////////////
		
		String message=awardSheetDao.uploadTemplate(filePath+sep+"Excel"+fileName, data, inputObj,cList,display,filePath+sep+"Excel"+sep+fileName,fileName1,oldmarks);
        return new ModelAndView("buildactivitymaster/Result","message",message);
    }
    
    /**
     * Method to get Approved status
     * @param request
     * @param response
     * @return object of ModelAndView
     * @throws Exception
     */
    public ModelAndView getAprStatus(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
    	HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
        
		inputObj.setUniversityId(universityId);
		inputObj.setCourseCode(request.getParameter("courseCode"));
		inputObj.setEntityId(request.getParameter("entityId"));
		inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
		inputObj.setSemesterStartDate(request.getParameter("startDate"));
		inputObj.setSemesterEndDate(request.getParameter("endDate"));
		inputObj.setDisplayType(request.getParameter("display"));
		inputObj.setApprovalOrder(request.getParameter("approvalOrder"));
		List<AwardSheetInfoGetter>list = awardSheetDao.getAprStatus(inputObj);  
		String message="";
		if(list.size()>0){
			message=list.get(0).getCode().toString();
		}
		System.out.println("message is inside appr status "+message);
        return new ModelAndView("associatecoursewithinstructor/Result", "message", message);
    }
    
    public ModelAndView getgradelimit(HttpServletRequest request,
            HttpServletResponse response) throws Exception { // method created by Arush
            
        	HttpSession session = request.getSession(true);
    		String universityId =(String) session.getAttribute("universityId");
    		if(universityId == null){
    			return new ModelAndView("general/SessionInactive","sessionInactive",true);
    		}
        	
    		AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
//            inputObj.setEntityId(session.getAttribute("userId").toString().substring(1,9));
            inputObj.setCreatorId(session.getAttribute("userId").toString());
            inputObj.setUniversityId(session.getAttribute("userId").toString().substring(1, 5));
         
    		inputObj.setCourseCode(request.getParameter("courseCode"));
    		inputObj.setDisplayType(request.getParameter("displayType")) ;
    		inputObj.setSessionStartDate(session.getAttribute("startDate").toString());
    		inputObj.setSessionEndDate(session.getAttribute("endDate").toString());

    		List<AwardSheetInfoGetter> gradelimit = awardSheetDao.getgradelimit(inputObj);
            return new ModelAndView("awardsheet/gradelimit", "result", gradelimit);
        }
    
    public ModelAndView getcourseAprStatus(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
        	HttpSession session = request.getSession(true);
    		String universityId =(String) session.getAttribute("universityId");
    		if(universityId == null){
    			return new ModelAndView("general/SessionInactive","sessionInactive",true);
    		}
            
    		inputObj.setUniversityId(universityId);
    		inputObj.setCourseCode(request.getParameter("courseCode"));
    		inputObj.setEntityId(request.getParameter("entityId"));
    		inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
    		inputObj.setSemesterStartDate(request.getParameter("startDate"));
    		inputObj.setSemesterEndDate(request.getParameter("endDate"));
    		inputObj.setDisplayType(request.getParameter("displayType"));
    		List<AwardSheetInfoGetter>courseapprstatus = awardSheetDao.getcourseAprStatus(inputObj); 
    		return new ModelAndView("awardsheet/courseapprstatus", "result", courseapprstatus);
    		
        }
    
    public ModelAndView getApprovalOrder(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        	AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
        	HttpSession session = request.getSession(true);
    		String universityId =(String) session.getAttribute("universityId");
    		if(universityId == null){
    			return new ModelAndView("general/SessionInactive","sessionInactive",true);
    		}
            
    		inputObj.setUniversityId(universityId);
    		inputObj.setCourseCode(request.getParameter("courseCode"));
    		inputObj.setProgramCourseKey(request.getParameter("programCourseKey"));
    		inputObj.setCreatorId((String) session.getAttribute("userId"));
    		inputObj.setDisplayType(request.getParameter("displayType"));
    		inputObj.setEntityId(request.getParameter("entityId"));
            
    		List<AwardSheetInfoGetter> list = awardSheetDao.getApprovalOrder(inputObj);  
    		
            return new ModelAndView("awardsheet/courseapprstatus", "result", list);
        }
    
    
    /**
     *  Method to Generate Excel file 
     *  @author Devendra Singhal
     * @param StringTokenizer columns 
     * @param StringTokenizer data 
     * @param StringTokenizer column CT
     * @param String Column Group Name
     * @param String File name
     *  
     */
    @SuppressWarnings("deprecation")
	public String generateExcel(StringTokenizer column,StringTokenizer provider,StringTokenizer CTList,String CTGroupName,String fileName){
    	
    
    	
    	String message="";
    	try {
    	String sep=System.getProperty("file.separator");
    	String filePath=this.getServletContext().getRealPath("/")+"Excel";
    	File file=new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		FileOutputStream fout=new FileOutputStream(filePath+sep+fileName+".xls");
		HSSFWorkbook template=new HSSFWorkbook();
		HSSFSheet sheet=template.createSheet("sheet1");
		HSSFRow headerRow=sheet.createRow((short)0);
		HSSFCellStyle cellStyle=template.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle ProviderCellStyle=template.createCellStyle();
		ProviderCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont cellFont=template.createFont();
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(cellFont);
		int index=1;
		int detailRowIndex=0;
		int count=column.countTokens();
		int countCT=CTList.countTokens();
		HSSFCell headerCell;
	
		
		cellStyle.setFont(cellFont);
		ArrayList<String>CTkn=new ArrayList<String>() ;
		int tokenIndex=0;
		while(CTList.hasMoreTokens()){
			CTkn.add(tokenIndex, CTList.nextToken());
			tokenIndex++;
		}	
	
		// if there is no component return with error.
		if(countCT==0){
			message="NoCT";
			return message;
		}
		else{
	
	
			while(column.hasMoreTokens()){
				String str=column.nextToken();
//				if(column.countTokens()==count-1){                // Commented out by Arush
//					headerCell=headerRow.createCell((short)(count-index));// Commented out by Arush
//					headerCell.setCellValue(str);// Commented out by Arush
//					sheet.autoSizeColumn((short)(column.countTokens()));// Commented out by Arush
//					headerCell.setCellStyle(cellStyle);	// Commented out by Arush
//				}
				// WRITE FIRST ROW HEADER ROLL NUMBER AND NAME 
				 if(column.countTokens()==0 || column.countTokens()==1){
					headerCell=headerRow.createCell((short)(count-index));
					headerCell.setCellValue(str);
					sheet.autoSizeColumn((short)column.countTokens());
					headerCell.setCellStyle(cellStyle);
				}
				index++;
			}
			// Write Group Name of components.
			
	
			
//			int idx=2;
//			int startIndex=2;
//			for(int c=0;c<CTGroupName.split("-").length;c++){
//				if(CTGroupName.split("-")[CTGroupName.split("-").length-(c+1)]!=null || !(CTGroupName.split("-")[CTGroupName.split("-").length-(c+1)].equals(""))){
//					int CTindex=0;
//					HSSFCell CTCell=headerRow.createCell((short)(idx));
//					CTCell.setCellValue(CTGroupName.split("-")[CTGroupName.split("-").length-(c+1)]);
//					CTCell.setCellStyle(cellStyle);
//					 idx++;
////					for(int jj=0;jj<CTkn.size();jj++){
////						String str=CTkn.get(jj);
////						String spl[]=str.split("\n");
////					//	if(spl[0].substring(0,2).equals(CTGroupName.split("-")[CTGroupName.split("-").length-(c+1)])){ // do not need to match Arush
////							 CTindex++;
////							 idx++;
////					//	}
////					}
//					Region rg=new Region(0, (short)(startIndex), 0, (short)(idx-1));
//					sheet.addMergedRegion(rg);
//					startIndex=idx;
//				}
//			}
		}
		
	
		HSSFRow CTRow=sheet.createRow((short)1);
		for(int jj=0;jj<CTkn.size();jj++){
			String str=CTkn.get(CTkn.size()-(jj+1));
			String spl[]=null;
			if(str!=null || str!=""){
				spl=str.split("\n");
			}
			if(spl.length>0){
				HSSFCell CTHeaderCell=CTRow.createCell((short)(jj+2));
				CTHeaderCell.setCellValue(spl[0]+" "+spl[1]);
				CTHeaderCell.setCellStyle(cellStyle);
				sheet.autoSizeColumn((short)(countCT+2));
				
			}
		}
		
		while(provider.hasMoreTokens()){
			String str=provider.nextToken();
			System.out.println("provider"+str);
			String spl[]=str.split(",");
			HSSFRow row=sheet.createRow((short)detailRowIndex+2);
			for(int i=0;i<spl.length;i++){
				if(spl[i]!=null || spl[i]!=""){
			//		String spl1[]=spl[i].split("-");
					
					String spl1[]=spl[i].split("#"); // for down loading  Grade 
					//if((!(spl1.length>1)) && spl1.length>0){
					
					if(spl1.length==1 && i<2) {
//						if(i>2){
//							HSSFCell providerCell;
//							providerCell=row.createCell((short)(i+countCT-1));
//							providerCell.setCellValue(spl[i]);
//							providerCell.setCellStyle(ProviderCellStyle);
//							sheet.autoSizeColumn((short)i);
//						}
//						else if(i<2){
						
							HSSFCell providerCell=row.createCell((short)i);
							providerCell.setCellValue(spl[i]);
							providerCell.setCellStyle(ProviderCellStyle);
							sheet.autoSizeColumn((short)i);
				//		}
//						else{
//							HSSFCell providerCell=row.createCell((short)i);
//							providerCell.setCellValue(spl1[0]);
//							providerCell.setCellStyle(ProviderCellStyle);
//							sheet.autoSizeColumn((short)i);
//						}
					}
					else if(spl1.length>0){
							for(int jj=0;jj<CTkn.size();jj++){
								String str1=CTkn.get(CTkn.size()-(jj+1));
								String splColm[]=null;
								if(str1!=null || str1!=""){
									splColm=str1.split("\n");
								}
								if(splColm.length>0){
									for(int j=0;j<spl1.length;j++){
										
										if (((spl1[j].split("/").length))>1){
											
										
										if(splColm[0].equals(spl1[j].split("/")[1])){
											HSSFCell providerCell=row.createCell((short)(jj+2));
											providerCell.setCellValue(spl1[j].split("/")[0]);
											providerCell.setCellStyle(ProviderCellStyle);
											sheet.autoSizeColumn((short)i);	
										}
										}
									}
									
								}
							
						}
					}
					else{
						HSSFCell providerCell=row.createCell((short)i);
						providerCell.setCellValue("");
						providerCell.setCellStyle(ProviderCellStyle);
						sheet.autoSizeColumn((short)i);	
					}
					
				}
			}
			detailRowIndex++;
		}
		template.write(fout);
		fout.close();
		message="success|"+filePath+sep+"Template.xls";
		} 
    	catch (FileNotFoundException e) {
    		message="FileError";
    		logObj.error("FileNotFound Exception in generateExcel : method "+e);
		}
    	catch (IOException e) {
    		message="IOError";
    		logObj.error("IO Exception in generateExcel : method "+e);
		}
		
		return message;
    	
    }
    
   
}
