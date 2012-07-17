/*
 * @(#) StudentRegistrationController.java
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
package in.ac.dei.edrp.cms.controller.studentregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.studentregistration.StudentRegistrationFormDao;
import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * This controller is providing middle layer for the methods of
 * <code>StudentRegistrationFormRPC</code> DAO interface and
 * request coming from client side. This controller is being used
 * for Student registration process
 * @author Manpreet Kaur
 * @date 22-01-2011
 * @version 1.0
 */
public class StudentRegistrationController extends MultiActionController {
    private StudentRegistrationFormDao studentRegistrationFormRPC;

    /**
     * Method to initialize controller
     * @param studentConnect
     */
    public void setStudentRegistrationFormDao(
        StudentRegistrationFormDao studentConnect) {
        this.studentRegistrationFormRPC = studentConnect;
    }

    /**
     * Method to populate student's details to show on registration form
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getStudentDetails(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
	
    	HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userName");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	
    	
        String rollNumber = request.getParameter("roll_number");
        List<StudentInfoGetter> studentRecord=null;
        try{
         studentRecord = studentRegistrationFormRPC.getStudentDetails(rollNumber);
        }catch (MyException e) {
        	return new ModelAndView("RegistrationForm/RegisterStudent", "result",
                    e.getMessage());
        }       
        return new ModelAndView("RegistrationForm/StudentRecord",
            "studentRecord", studentRecord);
    
    }

    /**
     * Method to get core courses for specific program,branch,semester basis
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getStudentCoreSubject(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();

        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setEntityId(request.getParameter("entity_id"));
        StringTokenizer courseGroup=new StringTokenizer(request.getParameter("selectedGroup"),",");

        List<CourseInfoGetter> coreSubjects = studentRegistrationFormRPC.getStudentCoreSubject(inputObj,courseGroup);

//        return new ModelAndView("RegistrationForm/StudentCoreSubject",
//            "coreSubjects", coreSubjects);
        return new ModelAndView("RegistrationForm/ElectiveSubjects",
                "electiveSubjects", coreSubjects);
    }

    /**
     * Method to get registration deadlines for specific
     * program,branch,specialization semester basis
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getRegistrationDeadlines(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	
    	HttpSession session=request.getSession(true);

    	String univId = (String) session.getAttribute("universityId");
    	if(univId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
    	
    	String ssd = (String) session.getAttribute("startDate");
    	String sed = (String) session.getAttribute("endDate");
    	
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setEntityId(request.getParameter("entity_id"));
        inputObj.setSessionStartDate(ssd);
        inputObj.setSessionEndDate(sed);
        
        //add this parameter of registration due date on flex side

        //        		inputObj.setRegistrationStartDate(request.getParameter(""));
        List<StudentInfoGetter> deadlines=null;
        try{
        deadlines = studentRegistrationFormRPC.getRegistrationDeadlines(inputObj);
        }catch (MyException e) {
        	return new ModelAndView("RegistrationForm/RegisterStudent", "result",
                    e.getMessage());
        } 
        return new ModelAndView("RegistrationForm/RegistrationDeadlines",
            "deadlines", deadlines);
    }

    /**
     * Method to get Elective courses for specific program,branch,semester basis
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getElectiveSubject(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setEntityId(request.getParameter("entity_id"));

        List<CourseInfoGetter> electiveSubjects = studentRegistrationFormRPC.getElectiveSubject(inputObj);

        return new ModelAndView("RegistrationForm/ElectiveSubjects",
            "electiveSubjects", electiveSubjects);
    }

    /**
     * Method to get minimum required number of courses of each type for
     * specific program,branch,semester basis
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCourseTypeSummary(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setEntityId(request.getParameter("entity_id"));

        List<CourseInfoGetter> courseSummary = studentRegistrationFormRPC.getCourseTypeSummary(inputObj);

        return new ModelAndView("RegistrationForm/CourseTypeSummary",
            "courseSummary", courseSummary);
    }

    /**
     * Method for inserting student details into database
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
 
	public ModelAndView registerStudent(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
		
		
		
		HttpSession session=request.getSession(true);

    	String userId = (String) session.getAttribute("userName");
    	if(userId == null)
        {
        return new ModelAndView("general/SessionInactive","sessionInactive",true);
        }
		
		
		
        StudentInfoGetter inputObj = new StudentInfoGetter();

        inputObj.setStudentId(request.getParameter("student_id"));
        inputObj.setRollNumber(request.getParameter("roll_number"));
        inputObj.setEnrollmentNumber(request.getParameter("enrollment_number"));
        inputObj.setDateOfBirth(request.getParameter("date_of_birth"));
        inputObj.setCategory(request.getParameter("category"));
        inputObj.setGender(request.getParameter("gender"));
        inputObj.setStudentName(request.getParameter("student_name"));
//        inputObj.setFatherName(request.getParameter("father_name"));
//        inputObj.setMotherName(request.getParameter("mother_name"));
        inputObj.setEntityId(request.getParameter("entity_id"));
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setOldEntityId(request.getParameter("old_entity_id"));
        inputObj.setOldProgramId(request.getParameter("old_program_id"));
        inputObj.setOldBranchCode(request.getParameter("old_branch_code"));
        inputObj.setOldSpecialization(request.getParameter("old_specialization"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setOldSemesterCode(request.getParameter("old_semester_code"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setOldSpecialization(request.getParameter("old_specialization"));
        inputObj.setAdmissionMode(request.getParameter("admission_mode"));
        inputObj.setSessionStartDate(request.getParameter("session_start_date"));
        inputObj.setSessionEndDate(request.getParameter("session_end_date"));
        inputObj.setAttempt(request.getParameter("attempt"));
        inputObj.setFirstName(request.getParameter("first_name"));
        inputObj.setMiddleName(request.getParameter("middle_name"));
        inputObj.setLastName(request.getParameter("last_name"));
        inputObj.setPrimaryEmailId(request.getParameter("primary_email_id"));
        inputObj.setFatherFirstName(request.getParameter("fatherFirstName"));
        inputObj.setFatherMiddleName(request.getParameter("fatherMiddleName"));
        inputObj.setFatherLastName(request.getParameter("fatherLastName"));
        inputObj.setMotherFirstName(request.getParameter("motherFirstName"));
        inputObj.setMotherMiddleName(request.getParameter("motherMiddleName"));
        inputObj.setMotherLastName(request.getParameter("motherLastName"));
        inputObj.setRegCredit(request.getParameter("regCredit"));
        inputObj.setTheoryCredit(request.getParameter("theoryCredit"));
        inputObj.setPracCredit(request.getParameter("pracCredit"));
        inputObj.setCreditExcludeAudit(request.getParameter("creditExcludeAudit"));
        
        inputObj.setSequenceNumber(request.getParameter(
                    "sequence_number"));
        inputObj.setRegistrationNumber(request.getParameter(
                "registrationNumber"));
        inputObj.setRegRollNumber(request.getParameter("reg_roll_number"));

        if (request.getParameter("probable_semester").equalsIgnoreCase("")) {
        } else {
            inputObj.setProbableSemester(request.getParameter(
                    "probable_semester"));
            inputObj.setProbableSemesterStartDate(request.getParameter(
                    "probable_semester_start_date"));
            inputObj.setProbableSemesterEndDate(request.getParameter(
                    "probable_semester_end_date"));
            inputObj.setProbableAttemptNumber(request.getParameter(
                    "probable_attempt_number"));
            inputObj.setProbableRegisterDueDate(request.getParameter(
                    "probable_register_due_date"));
            inputObj.setStatusInSemester(request.getParameter(
                    "status_in_semester"));
        }

        String[] Courses = request.getParameter("course_list").trim()
                                  .split("\\|");
        String[] course_group = request.getParameter("course_group_list").trim()
        .split("\\|");
        
        // Added By Dheeraj For Getting Course Names From Request
        
        String[] course_name = request.getParameter("course_name_list").trim()
        .split("\\|");
        
        CourseInfoGetter[] courseDetails = new CourseInfoGetter[Courses.length];

        for (int i = 0; i < Courses.length; i++) {
            courseDetails[i] = new CourseInfoGetter();
            courseDetails[i].setCourseCode(Courses[i]);
            courseDetails[i].setCourseGroupCode(course_group[i]);
            courseDetails[i].setCourseName(course_name[i]);		// Course Name Added By Dheeraj to be inserted in temp_student_courses
        }

        
        @SuppressWarnings("unused")
		String result = null;
        
        try{
        result=studentRegistrationFormRPC.registerStudent(inputObj,
                courseDetails);
        }catch (MyException e) {
        	return new ModelAndView("RegistrationForm/RegisterStudent", "result",
                    e.getMessage());
        } 
        return new ModelAndView("general/SessionInactive","sessionInactive",false);
    }

    /**
     * Method to get minimum and maximum required credits for specific program,semester basis
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCreditDetailsFromTermDetails(
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));

        StudentInfoGetter returnObj = new StudentInfoGetter();
        List<StudentInfoGetter> creditDetails = studentRegistrationFormRPC.getCreditDetailsFromTermDetails(inputObj);

        for (StudentInfoGetter c : creditDetails) {
            returnObj = c;
        }

        return new ModelAndView("RegistrationForm/CreditDetailsFromTermDetails",
            "result", returnObj);
    }
    
    
    /**
     * Method to get compulsory course groups for specific program,branch,semester basis
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCompulsoryCourseGroup(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        inputObj.setEntityId(request.getParameter("entity_id"));

        List<CourseInfoGetter> group = studentRegistrationFormRPC.getProgramGroup(inputObj);

        return new ModelAndView("RegistrationForm/CourseGroup",
            "coreGroup", group);
    }
    
       
    
    /**
     * Method to get student's previously opted course groups for from program,branch,semester basis
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getOldCourseGroup(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("old_program"));
        inputObj.setBranchCode(request.getParameter("old_branch"));
        inputObj.setNewSpecialization(request.getParameter("old_specialization"));
        inputObj.setSemesterCode(request.getParameter("old_semester"));
        inputObj.setEntityId(request.getParameter("old_entity"));
        inputObj.setRollNumber(request.getParameter("roll"));

        List<CourseInfoGetter> group = studentRegistrationFormRPC.getOldCourseGroup(inputObj);

        return new ModelAndView("RegistrationForm/CourseGroup",
            "coreGroup", group);
    }
    
    /**
     * Method to get compulsory course groups rules for specific program,branch,semester basis
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView getCompulsoryGroupRule(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        StudentInfoGetter inputObj = new StudentInfoGetter();
        inputObj.setProgramId(request.getParameter("program_id"));
        inputObj.setBranchCode(request.getParameter("branch_code"));
        inputObj.setNewSpecialization(request.getParameter("new_specialization"));
        inputObj.setSemesterCode(request.getParameter("semester_code"));
        List<CourseInfoGetter> groupRule = studentRegistrationFormRPC.getProgramGroupRule(inputObj);

        return new ModelAndView("RegistrationForm/CourseGroupRule",
            "groupRule", groupRule);
    }
    
    
    
}
