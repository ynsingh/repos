/*
 * @(#) SummarySheetService.java
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

package in.ac.dei.edrp.client.RPCFiles;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountBean;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;

import java.util.List;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@RemoteServiceRelativePath("newSummarySheet")
public interface SummarySheetService extends RemoteService {
    List<SummarySheetBean> getProgramComponent(SummarySheetBean inputBean);

    List<SummarySheetBean> getGroupWisePaperCode(SummarySheetBean inputBean);

    List<SummarySheetBean> getProgramDocumentList(SummarySheetBean inputBean);

    List<SummarySheetBean> getFormProgramList(SummarySheetBean inputBean);

    SummarySheetBean insertSummarySheetDetails(SummarySheetBean inputBean)
        throws Exception;

    SummarySheetBean generatePDF(SummarySheetBean inputBean, String docPath);

    List<SummarySheetBean> getProgramFirstDegreeList(SummarySheetBean inputBean);
    
    List<SummarySheetBean> getCosCodeDescription(SummarySheetBean inputBean);
    
    List<ApplicantAccountBean> getApplicantsPrograms(String userEmail);
    
    List<SummarySheetBean> getApplicantDetails(String userEmail,String universityId);
    
    List<SummarySheetBean> getExaminationCenter(SummarySheetBean inputBean);
    
    SummarySheetBean sendMailConfirmation(SummarySheetBean finalData) throws Exception;
	
	List<SummarySheetBean> getStudentsForManage(SummarySheetBean inputBean);//Add by Devendra May 21 For Manage Summary Sheet
    String deleteStudents(List<SummarySheetBean> beenList);//Add by Devendra May 21
    List<SummarySheetBean> getStudentDataForEdit(SummarySheetBean inputBean);//Add by Devendra May 22
    List<SummarySheetBean> getAcademicDetailForEdit(SummarySheetBean inputBean);//Add by Devendra May 22
    List<SummarySheetBean> checkSpecialWeightage(SummarySheetBean inputBean);//Add by Devendra May 22
    List<SummarySheetBean> getEnteranceTestData(SummarySheetBean inputBean);//Add by Devendra May 22
    SummarySheetBean updateSummarySheet(SummarySheetBean inputBean)throws Exception;//Add by Devendra May23   
			
}
