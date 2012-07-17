/*
 * @(#) AwardBlankCorrectionDao.java
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

package in.ac.dei.edrp.cms.dao.correctionInAwardBlank;

import java.util.List;
import java.util.StringTokenizer;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;

/**
 * This interface contains method declarations for Award Blank Correction functionality
 * @author Dheeraj Singh
 * @date 09-APR-2012
 * @version 1.0
 */

public interface AwardBlankCorrectionDao {

	/**
     * Method for getting current status of given student
     * @param input
     * @return List of necessary details
     */
	public List<AwardSheetInfoGetter> getCurrentStatus(AwardSheetInfoGetter input);
	
	/**
     * Method for getting marks of given student
     * @param input
     * @return List of marks
     */
	public List<AwardSheetInfoGetter> getMarks(AwardSheetInfoGetter input);
	
	/**
     * Method for updating marks of student
     * @param input
     * @param data
     * @return String
     */
	public String saveStudentMarks(AwardSheetInfoGetter input, StringTokenizer data);
	
	/**
     * Method for getting courses of given program and entity
     * @param input
     * @return List of courses
     */
	public List<AwardSheetInfoGetter> getCourses(AwardSheetInfoGetter input);
	
	/**
     * Method for getting semester dates of given program
     * @param input
     * @return List of dates
     */
	public List<AwardSheetInfoGetter> getSemesterDates(AwardSheetInfoGetter input);
	
	/**
     * Method for getting details of employee who teaches the courses
     * @param input
     * @return List of employee details
     */
	public List<AwardSheetInfoGetter> getEmployeeDetail(AwardSheetInfoGetter input);
	
	/**
     * Method for getting components of courses
     * @param input
     * @return List of component List
     */
	public List<AwardSheetInfoGetter> getComponents(AwardSheetInfoGetter input);
	
	/**
     * Method for getting marks all students of particular course code
     * @param input
     * @return List of marks
     */
	public List<AwardSheetInfoGetter> getStudentMarks(AwardSheetInfoGetter input);
}
