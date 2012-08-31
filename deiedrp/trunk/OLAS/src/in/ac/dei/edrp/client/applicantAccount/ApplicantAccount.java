/*
 * @(#) ApplicantAccount.java
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


package in.ac.dei.edrp.client.applicantAccount;

import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@RemoteServiceRelativePath("ApplicantAccount")
public interface ApplicantAccount extends RemoteService{
	
	String insertAccountdetails(ApplicantAccountBean accountInfo);
    boolean performSignup(String userCaptcha);
    Integer checkEmailDuplicacy(ApplicantAccountBean accountInfo);
    Integer getLogInConfirmation(String userName, String password);
    List<ApplicantAccountBean> getUniversitiesToApply();
    Integer changeApplicantPassword(String userName,String userId, String oldPass,String newPass);
    String getUserNameForAccount(String userName);
    List<ApplicantAccountBean> getAppliedProgramList(String userEmailId);
    String sendEmailAccount(ApplicantAccountBean accountInfo);
    List<ApplicantAccountBean> getAccountDetails(String userEmailId);
    String updateAccountdetails(ApplicantAccountBean accountInfo);
    
    List<EDEIStudentBean> getAvailableModule(EDEIStudentBean bean);//Add by Devendra
    List<EDEIStudentBean> getExistingProgramDetail(EDEIStudentBean bean);//Add by Devendra

}
