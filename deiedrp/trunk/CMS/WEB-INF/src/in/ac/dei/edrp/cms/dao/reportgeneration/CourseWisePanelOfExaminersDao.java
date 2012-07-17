/*
 * @(#) ExternalExaminarDetailDao.java
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
package in.ac.dei.edrp.cms.dao.reportgeneration;

import java.util.List;

import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;

/**
 * This interface consist the list of methods used
 * in CourseWisePanelOfExaminersImpl file.
 * @author Mandeep Sodhi
 * @date 8 Jan 2012
 * @version 1.0
 */
public interface CourseWisePanelOfExaminersDao {
	/**
     * Method to get the Course Name.
     * @return List of Course Name
     */
	List<ExternalExaminerCourseInfoGetter> getCourseName(ExternalExaminerCourseInfoGetter input);
	/**
     * Method to get the Examiner Details
     * @return List of Examiner LIst
     */
	List<ExternalExaminerCourseInfoGetter> getExaminerList(
			ExternalExaminerCourseInfoGetter input);
	/**
     * Method to get the  course details.
     * @return List of  Course Code
     */
	List<ExternalExaminerCourseInfoGetter> getcourseList(
			ExternalExaminerCourseInfoGetter input);
	/**
     * Method to get the program Id 
     * @return List of program Id
     */
	List<ExternalExaminerCourseInfoGetter> getProgramId(
			ExternalExaminerCourseInfoGetter input);
	/**
     * Method to get the Class details.
     * @return List of Class
     */
	List<ExternalExaminerCourseInfoGetter> getClassList(
			ExternalExaminerCourseInfoGetter input);
	
	/**
	 * Method used for the Delay In Component Marks Report
	 * @param input
	 * @return
	 */
	List<FinalSemesterResultStatistics> getDelayDetails(
			FinalSemesterResultStatistics input);
	
	/**
	 * Method used to Get Program Course Key
	 * @param input
	 * @return
	 */
	List<FinalSemesterResultStatistics> getProgramCourseKey(
			FinalSemesterResultStatistics input);
	
	/**
	 * Method used for the Delay In Component Marks Report Entity Wise
	 * @param input
	 * @return
	 */
	List<FinalSemesterResultStatistics> getDelayDetailEntityWise(
			FinalSemesterResultStatistics input);
	/**
	 * Method used to Get Program Course Key
	 * @param input
	 * @return
	 */
	List<FinalSemesterResultStatistics> getProgramCourseKeyEntityWise(
			FinalSemesterResultStatistics input);

	/**
	 * Method used to Get Detail Of Courses Whose Marks Released By Dean
	 * @param input
	 * @return
	 */	
	List<FinalSemesterResultStatistics> getcoursesMarksReleasedByDean(
			FinalSemesterResultStatistics input);
	
	
	List<FinalSemesterResultStatistics> getEntityProgramList(
			FinalSemesterResultStatistics input);
	List<FinalSemesterResultStatistics> getReportProgramList(
			FinalSemesterResultStatistics input);
}
