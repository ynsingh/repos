/**
 * @(#) SystemTableTwoController.java
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

package in.ac.dei.edrp.cms.dao.reportgeneration;

import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;

import java.util.List;

public interface ProgressCardDao {
	
	public List<ProgressCardInfo> progressCardDetails(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> progressCardMarksDetails(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> progressCardClass(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> totalMarksDetails(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> getProgramCourseKeys(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> studentSatisfyingPCK(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> semestersBetTwoSemesters(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo semesterIsGreater(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo switchRules(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo getPreviousCombination(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo getSinglePCK(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo semesterStartEndDate(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo previousSemesterMarks(ProgressCardInfo progressCardInfo); 
	public int insertIntoReportControlLog(ProgressCardInfo progressCardInfo);
	public int insertIntoErrorLog(String rollNumber,String programCourseKey,String reportType,String errorCode,String semesterStartDate,String semesterEndDate,String session);
	public int insertIntoPrintControlLog(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo cummulativeForFinalResultCard(ProgressCardInfo progressCardInfo);
	public ProgressCardInfo checkStatusOfAwradSheet(ProgressCardInfo progressCardInfo);
	public List<ProgressCardInfo> getPreviousProgramCourseKey(ProgressCardInfo progressCardInfo);	
}
