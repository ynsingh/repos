/*
 * @(#) InternalSummarySheetService.java
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

package in.ac.dei.edrp.client.InternalSummarySheet;

import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountBean;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @version 1.0 28 MAY 2012
 * @author UPASANA KULSHRESTHA
 */
@RemoteServiceRelativePath("internalSummarySheet")
public interface InternalSummaryService extends RemoteService {
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
    
    List<SummarySheetBean> getApplicantDetails(String userEmail);
    
    List<SummarySheetBean> getExaminationCenter(SummarySheetBean inputBean);
    
    SummarySheetBean sendMailConfirmation(SummarySheetBean finalData) throws Exception;
			
}
