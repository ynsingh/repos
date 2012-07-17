/**
 * @(#) MarksApprovalService.java
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
package in.ac.dei.edrp.cms.dao.coursemarksapproval;

import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;
import in.ac.dei.edrp.cms.domain.coursemarksapproval.MarksApprovalInfo;
import in.ac.dei.edrp.cms.domain.programcoursetypesummary.ProgramCourseTypeSummaryInfoGetter;

import java.util.List;

/**
 * The client interface for Marks Approval.
 *
 * @version 1.0 16 MAR 2011
 * @author MOHD AMIR
 */
public interface MarksApprovalService {
	/** getting course list from Database */
	List<MarksApprovalInfo> getCourseList(MarksApprovalInfo marksApprovalInfo);
    
	/** getting employee list from Database */
	List<MarksApprovalInfo> getEmployeeList(MarksApprovalInfo marksApprovalInfo);
    
	/** getting Approval Details from Database */
	List<MarksApprovalInfo> getApprovalDetails(MarksApprovalInfo marksApprovalInfo);

	/** deleting Approval Details from Database */
	int deleteApprovalDetails(MarksApprovalInfo marksApprovalInfo);

	/** updating Approval Details into Database */
	int updateApprovalDetails(MarksApprovalInfo marksApprovalInfo);

	/** getting duplicate record count from Database */
	int getDuplicateCount(MarksApprovalInfo marksApprovalInfo);

	/** inserting Approval Details into Database */
	String setApprovalDetails(MarksApprovalInfo marksApprovalInfo,String data);
	
	/** getting program details from database */
	List<ProgramCourseTypeSummaryInfoGetter> getProgramDetails(ProgramCourseTypeSummaryInfoGetter programCourseTypeSummaryInfoGetter);
	
	/** getting entity List from database */
	List<ProgramCourseTypeSummaryInfoGetter> getEntity(ProgramCourseTypeSummaryInfoGetter programCourseTypeSummaryInfoGetter);
	
	/** Getting message that is a employee attach to a particular course*/
	String getCourseEmployee(MarksApprovalInfo marksApprovalInfo);
	
	/** Method to check sequence no already exists for a particular course*/
	String checkSequence(MarksApprovalInfo marksApprovalInfo);
	
	/** Method to get Display Type*/
	List<AwardSheetInfoGetter> getDisplayType(MarksApprovalInfo marksApprovalInfo);
}