/**
 * @(#) AssociateCourseWithInstructorDAO.java
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

package in.ac.dei.edrp.cms.dao.associatecoursewithinstructor;

import in.ac.dei.edrp.cms.domain.associatecoursewithinstructor.AssociateCourseWithInstructor;

import java.util.List;

/**
 * This interface consist the list of methods used
 * in ActivityMasterDAOImpl file.
 * @author Ankit Jain
 * @date 02 March 2011
 * @version 1.0
 */
public interface AssociateCourseWithInstructorDAO {
	
	/**
     * Method to get the Entity List.
     * @return List of Entity
     */
	List<AssociateCourseWithInstructor> getEntityList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the Program List.
     * @return List of Programs
     */
	List<AssociateCourseWithInstructor> getProgramList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the Branch List.
     * @return List of Branch
     */
	List<AssociateCourseWithInstructor> getBranchList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the specialization List.
     * @return List of specialization list
     */
	List<AssociateCourseWithInstructor> getSpecializationList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the Semester List.
     * @return List of Semester List
     */
	List<AssociateCourseWithInstructor> getSemesterList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the Semester Dates
     * @return List of Semester Dates
     */
	List<AssociateCourseWithInstructor> getSemesterDates(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the Employee List.
     * @return List of Employee
     */
	List<AssociateCourseWithInstructor> getEmployeeList(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to to assign the employee to course.
     * @return List 
     */
	String assignEmployee(AssociateCourseWithInstructor associateCourseWithInstructor, String courseDataTokens);
	
	/**
     * Method to get course instructor details.
     * @return List of course instructor details.
     */
	List<AssociateCourseWithInstructor> getInstructorDetails(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to update the instructor details 
     * @return String
     */
	String updateEmployee(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to update the instructor details
     * @return String
     */
	String deleteCourseInstructor(AssociateCourseWithInstructor associateCourseWithInstructor, String courseDataTokens);
	
	/**
     * Method to get the process details
     * @param associateCourseWithInstructor AssociateCourseWithInstructor object
     * @return List of process
     */
	List<AssociateCourseWithInstructor> getCourseInstructorDetails(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to update the instructor details
     * @return String
     */
	String buildInstructorCourse(AssociateCourseWithInstructor associateCourseWithInstructor);
	
	/**
     * Method to get the process details
     * @param associateCourseWithInstructor AssociateCourseWithInstructor object
     * @return List of process
     */
	List<AssociateCourseWithInstructor> getSessionDetails(AssociateCourseWithInstructor associateCourseWithInstructor);
	
}
