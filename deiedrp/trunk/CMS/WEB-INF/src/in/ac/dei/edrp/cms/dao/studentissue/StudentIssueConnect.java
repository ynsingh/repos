/**
 * @(#) StudentIssueConnect.java
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

package in.ac.dei.edrp.cms.dao.studentissue;

import in.ac.dei.edrp.cms.domain.studentissue.StudentIssueInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.util.List;
import java.util.StringTokenizer;

/**
* The client interface for UpdatePrestaging
* 
* @version 1.0 05 Sep 2011
* @author ROHIT SAXENA
*/

public interface StudentIssueConnect {

	/**
	* Method to populate Issue details of an student from student_issue table
	* @param input object of reference bean class
	* @return list contains Records containing issue details
	*/
	List<StudentIssueInfoGetter> getIssueDetails(StudentIssueInfoGetter input);
    
	/**
	* Method to delete issues
	* @param input object of reference bean class
	* @return list contains prestaging Error Records
	*/
//	String deleteIssueRecords(StudentIssueInfoGetter input,
//			StringTokenizer items);
//	
	/**
	* Method to populate penality codes
	* @param input object of reference bean class
	* @return list contains penality codes
	*/
	List<StudentIssueInfoGetter> getPenalityCodes(String userId);
	
	/**
	* Method to populate Course Codes
	* @param input object of reference bean class
	* @return list contains Course Codes
	*/
	List<StudentIssueInfoGetter> resultGetPenalityCourseCodes(
				StudentIssueInfoGetter input);
	
	/**
	* Method to Close an issue
	* @param input object of reference bean class
	* @return String of success or failure
	*/
	String updateIssue(StudentIssueInfoGetter input);
	
	/**
	* Method to populate Entity
	* @param input object of reference bean class
	* @return Object having Entity Name
	*/
	Object getEntity(StudentIssueInfoGetter input);

	/**
	* Method to populate programs
	* @param input object of reference bean class
	* @return list contains programs
	*/
	List<StudentIssueInfoGetter> getPrograms(StudentIssueInfoGetter input);

	/**
	* Method to populate Branches
	* @param input object of reference bean class
	* @return list contains branches
	*/
	List<StudentIssueInfoGetter> getBranch(StudentIssueInfoGetter input);

	/**
	* Method to populate Specializations
	* @param input object of reference bean class
	* @return list contains Specializations
	*/
	List<StudentIssueInfoGetter> getSpecialization(
				StudentIssueInfoGetter input);

	/**
	* Method to populate Semesters
	* @param input object of reference bean class
	* @return list contains Semesters
	*/
	List<StudentIssueInfoGetter> getSemester(StudentIssueInfoGetter input);

	/**
	* Method to populate SemesterDates
	* @param input object of reference bean class
	* @return list contains Semester Dates
	*/
	StudentIssueInfoGetter getSemesterDates(StudentIssueInfoGetter input);

	/**
	* Method to Open an Issue
	* @param input object of reference bean class
	* @return String of success or failure
	*/	
	String insertIssue(StudentIssueInfoGetter input);

	/**
	* Method to populate List of roll numbers
	* @param input object of reference bean class
	* @return list contains roll numbers
	*/	
	List<StudentIssueInfoGetter> getRollNo(StudentIssueInfoGetter input);

	/**
	* Method to populate Issue Codes
	* @param input object of reference bean class
	* @return list contains Issue codes
	*/	
	List<StudentIssueInfoGetter> getIssues(StudentIssueInfoGetter input);

	


		

}
