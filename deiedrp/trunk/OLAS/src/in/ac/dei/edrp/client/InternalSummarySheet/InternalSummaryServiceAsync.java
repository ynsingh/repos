/*
 * @(#) InternalSummarySheetAsync.java
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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @version 1.0 28 MAY 2012
 * @author UPASANA KULSHRESTHA
 */
public interface InternalSummaryServiceAsync {
    void getProgramComponent(SummarySheetBean inputBean,
        AsyncCallback<List<SummarySheetBean>> callback);

    void getGroupWisePaperCode(SummarySheetBean inputBean,
        AsyncCallback<List<SummarySheetBean>> asyncCallback);

    void getProgramDocumentList(SummarySheetBean inputBean,
        AsyncCallback<List<SummarySheetBean>> asyncCallback);

    void getFormProgramList(SummarySheetBean inputBean,
        AsyncCallback<List<SummarySheetBean>> asyncCallback);

    void insertSummarySheetDetails(SummarySheetBean inputBean,
            AsyncCallback<SummarySheetBean> asyncCallback) ;
    
    void generatePDF(SummarySheetBean inputBean,String docPath,
                    AsyncCallback<SummarySheetBean> asyncCallback) ;
    
    void getProgramFirstDegreeList(SummarySheetBean inputBean,
                    AsyncCallback<List<SummarySheetBean>> asyncCallback) ;

	void getCosCodeDescription(SummarySheetBean inputBean,
			AsyncCallback<List<SummarySheetBean>> asyncCallback);

	void getApplicantsPrograms(String userEmail,AsyncCallback<List<ApplicantAccountBean>> asyncCallback);

	void getApplicantDetails(String userEmail,
			AsyncCallback<List<SummarySheetBean>> asyncCallback);

	void getExaminationCenter(SummarySheetBean inputBean,
			AsyncCallback<List<SummarySheetBean>> asyncCallback);

	void sendMailConfirmation(SummarySheetBean finalData,
			AsyncCallback<SummarySheetBean> asyncCallback);
}
