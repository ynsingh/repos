/*
 * @(#) StudentVerificationDao.java
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

import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.studentregistration.CourseInfoGetter;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.sql.SQLException;
import java.util.List;


/**
 * This interface is being used
 * for Student registration form
 * @author Manpreet Kaur
 * @version 1.0
 * @date 20-01-2011
 */
public interface StudentVerificationDao {
    /**
    * Method for fetching list of students with their corresponding details based on
    * entity, program, branch, specialization,semester, semester start date, semester end date basis
    * @param programObject
    * @return object containing students detail list
    * @throws Exception
    */
    List<StudentInfoGetter> getStudentBasicDetails(
        StudentInfoGetter programObject) throws Exception;

    /**
     * Method for fetching component list with their verification status for a particular student
     * and for inserting verification components that were laterly added to system.
     * @param studentObject
     * @return object containing component list
     * @throws Exception
     */
    List<StudentInfoGetter> getComponentVerificationDetails(
        StudentInfoGetter studentObject) throws Exception;

    /**
     * Method for updating component verification status for student and
     * for setting verified status as "verified" if all components for student
     * are verified else "unverified".
     * @param studentObject
     * @throws Exception
     */
    void saveComponentStatus(StudentInfoGetter[] studentObject)
        throws Exception;

    /**
     * Method for fetching full details of student
     * @param inputObj containing student's key information
     * @return Object containing student's full details
     * @throws Exception
     */
    StudentInfoGetter getStudentFullDetails(StudentInfoGetter inputObj)
        throws Exception;

    /**
     * Method for getting list of courses opted by student
     * @param inputObj
     * @return
     * @throws Exception
     */
    List<CourseInfoGetter> getCourseList(StudentInfoGetter inputObj)
        throws Exception;
    /**
     * Method For getting personal details of enrolled student
     * @param enrollmentInfo 
     * @return List Containing personal details
     */
    List<EnrollmentInfo> getEnrollmentPersonalDetails(EnrollmentInfo enrollmentInfo);
    
    /**
     * Method for getting enrollment academic details
     * @param enrollmentInfo
     * @return List containing of academic details
     */
    List<EnrollmentInfo> getEnrollmentAcademicDetails(EnrollmentInfo enrollmentInfo);
    
    /**
     * Method for updating the status of the student
     * @param enrollmentInfo
     * @return
     * @throws SQLException 
     */
    int updateStudentStatus(EnrollmentInfo enrollmentInfo) throws SQLException;

	int updateRejectStatus(EnrollmentInfo enrollmentInfo);

	/**
     * Method For getting personal details of enrolled student for SWT case
     * @param enrollmentInfo 
     * @return List Containing personal details
     */
    List<EnrollmentInfo> getEnrollmentPersonalDetailsSWT(EnrollmentInfo enrollmentInfo);
	
	/**
     * Method for getting enrollment academic details of SWT student
     * @param enrollmentInfo
     * @return List containing of academic details
     */
	List<EnrollmentInfo> getEnrollmentAcademicDetailsSWT(EnrollmentInfo enrollmentInfo);

    
}
