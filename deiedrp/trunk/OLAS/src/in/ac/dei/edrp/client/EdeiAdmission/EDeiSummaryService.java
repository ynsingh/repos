/*
 * @(#) EDeiSummaryService.java
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

package in.ac.dei.edrp.client.EdeiAdmission;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @version 1.0 1 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */
@RemoteServiceRelativePath("edeiSummarySheet")
public interface EDeiSummaryService extends RemoteService {

	List<EDEIStudentBean> getUniversityCourse(String universityId,String pgId);
    
    List<EDEIStudentBean> getUniversityModules(String universityId,String pgId);
    
    List<EDEIStudentBean> Category(String universityId);
    
    String[][] getStateData()throws Exception;
    
    String[][] getCityData(String state)throws Exception;
    
    List<EDEIStudentBean> getCourseModule(List<String> selectedCourseId, String universityId,String programId);
    
    List<EDEIStudentBean> getApplicantAccountDetails(EDEIStudentBean inputBean);
    
    EDEIStudentBean insertEDEINEWSummarySheetDetails(EDEIStudentBean finalData) throws Exception;
    
    EDEIStudentBean generatePDF(EDEIStudentBean outBean, String docFolder);
    
    EDEIStudentBean sendMailConfirmation(EDEIStudentBean outBean) throws Exception;
    
    Integer getModuleCredit();
    
    List<EDEIStudentBean> checkstudentModules(String userEmail,String sessionStartDate, String sessionEndDate);
    
    List<EDEIStudentBean> getStudentCourses(String userEmail, String sessionStartDate,String sessionEndDate);
    
    String[][] getProgramNameUGData();
    
    String[][] getProgramNamePGData();
    
    String[][] getProgramNameOTData();
    
    List<EDEIStudentBean> getUniversityOnlineProgram(EDEIStudentBean input);
    
    List<EDEIStudentBean> getUniversityOnlineDomains(String uniNickName);
    
    List<EDEIStudentBean> getUniversityOnlineProgramModule(EDEIStudentBean input);
    
    List<EDEIStudentBean> getPersonalDetails(EDEIStudentBean inputBean);
    EDEIStudentBean insertEDEINEWSummarySheetDetailsForExistingProgram(EDEIStudentBean finalData) throws Exception;    
    EDEIStudentBean generatePDFForExistingProgram(EDEIStudentBean outBean, String docFolder);    
    EDEIStudentBean insertEDEINEWSummarySheetDetailsForNEWProgram(EDEIStudentBean finalData) throws Exception;    
}
