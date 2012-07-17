/*
 * @(#) StudentRegistrationFormDao.java
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
package in.ac.dei.edrp.cms.dao.studentregistration;


import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;
import java.util.StringTokenizer;


/**
 *This inteface is being used for student registration process
 * @author Manpreet Kaur
 * @date 22-01-2011
 * @version 1.0
 */

//@RemoteServiceRelativePath("student")
public interface StudentRegistrationFormDao {
    /**
    * Method to populate student's details to show on registration form
    * @param registration_roll_number registration number of the applicant or roll number of existing student
    * @return student's details
    */
    List<StudentInfoGetter> getStudentDetails(String registration_roll_number)
        throws Exception;

    /**
     * Method to get core courses for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester, specialization
     * @return list of core/compulsary courses
     */
    List<CourseInfoGetter> getStudentCoreSubject(StudentInfoGetter inputObj,StringTokenizer courseGroup)
        throws Exception;

    /**
     * Method to get registration deadlines for specific program,branch,specialization semester basis
     * @param inputObj containing program, branch, semester, specialization
     * @return registration deadlines
     */
    List<StudentInfoGetter> getRegistrationDeadlines(StudentInfoGetter inputObj)
        throws Exception;

    /**
     * Method to get Elective courses for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester
     * @return list of elective courses with respective groups and minimum and maximum selections
     */
    List<CourseInfoGetter> getElectiveSubject(StudentInfoGetter inputObj)
        throws Exception;

    /**
     * Method to get minimum required number of courses of each type for specific program,branch,semester basis
     * @param inputObj containing program, branch, semester
     * @return list of courses and their respective minimum selections required
     * @throws Exception
     */
    List<CourseInfoGetter> getCourseTypeSummary(StudentInfoGetter inputObj)
        throws Exception;

    /**
     * Method for inserting student details into database
     * @param progDetails contains academic details of student
     * @param courseDetails contains list of courses selected by student
     * @return string
     * @throws Exception
     */
    String registerStudent(StudentInfoGetter progDetails,
        CourseInfoGetter[] courseDetails) throws Exception;

    /**
     * Method to get minimum and maximum required credits for specific program,semester basis
     * @param inputObj containing program, semester
     * @return list of courses and their respective minimum credits required
     * @throws Exception
     */
    public List<StudentInfoGetter> getCreditDetailsFromTermDetails(
        StudentInfoGetter inputObj) throws Exception;
    
    /**
     * Method to get Core program groups
     * @param inputObj
     * @return
     * @throws Exception
     */
    public List<CourseInfoGetter> getProgramGroup(StudentInfoGetter inputObj) throws Exception;
    
    /**
     * Method to get core program group rules
	  * @param inputObj
	  * @return List
	  */
	public List<CourseInfoGetter> getProgramGroupRule(StudentInfoGetter inputObj) throws Exception;

	 /**
	  * Method to get student's previously opted course groups
	  * @param inputObj
	  * @return List
	  */
		public List<CourseInfoGetter> getOldCourseGroup(StudentInfoGetter inputObj) throws Exception;
}
