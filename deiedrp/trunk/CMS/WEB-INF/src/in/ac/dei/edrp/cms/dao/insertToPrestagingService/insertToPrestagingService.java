/**
 * @(#) insertInPrestagingService.java
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
package in.ac.dei.edrp.cms.dao.insertToPrestagingService;

import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.util.List;
/**
 * this interface for  method of prestaging data transfer
 * 
 * @version 1.0 08 October 2012
 * @author Ashish Mohan
 */
public interface insertToPrestagingService {
	
	//check that whether entered enrollment number exist in student master and return details if exist
	StudentMasterInfoBean checkExistanceOfEnroll(StudentMasterInfoBean input);

	/* setting student data in prestaging table for fresh student */
	List<EnrollmentInfo> setPrestagingData(List<EnrollmentInfo> inputPrestagingData);
	
	/* setting student data in database */
	List<PrestagingInfoGetter> setPrestagingDataOld(List<PrestagingInfoGetter> studentDataList);
	
	/* getting registration due date */
	PrestagingInfoGetter getRegistrationDueDate(EnrollmentInfo inputBean);
	
	/* getting semester and sequence number */
	List<ConsolidatedChartBean> getSemesterAndSeqNo(ConsolidatedChartBean chartBean);

	List<StudentMasterInfoBean> getStudent(EnrollmentInfo enrollmentInfo);

	List<EnrollmentInfo> setTempData(List<EnrollmentInfo> inputEnrollmentInfo);

	String updateTempData(List<StudentInfoGetter> inputEnrollmentInfo);
	
	
}
