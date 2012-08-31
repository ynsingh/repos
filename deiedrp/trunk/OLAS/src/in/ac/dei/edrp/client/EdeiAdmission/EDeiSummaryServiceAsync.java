/*
 * @(#) EDeiSummaryServiceAsync.java
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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @version 1.0 1 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */
public interface EDeiSummaryServiceAsync {
	
    /**
     * To get the courses of the university for the given program
     * @param universityId
     * @param pgId
     * @param asyncCallback
     */
	void getUniversityCourse(String universityId,
			String pgId, AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get the module details of the university for the given program
	 * @param universityId
	 * @param pgId
	 * @param asyncCallback
	 */
	void getUniversityModules(String universityId,
			String pgId, AsyncCallback<List<EDEIStudentBean>> asyncCallback);
	
	/**
	 * To get the categories of the university from system_table_two
	 * @param universityId
	 * @param asyncCallback
	 */
	void Category(String universityId,
			AsyncCallback<List<EDEIStudentBean>> asyncCallback);
	
	/**
	 * Get the states of India from XML
	 * @param asyncCallback
	 */
	void getStateData(AsyncCallback<String[][]> asyncCallback);
	
	/**
	 * To get Cities of the selected state from XML
	 * @param state
	 * @param asyncCallback
	 */
	void getCityData(String state,AsyncCallback<String[][]> asyncCallback);

	/**
	 * To get the module of the selected course 
	 * @param selectedCourseId
	 * @param universityId
	 * @param programId
	 * @param asyncCallback
	 */
	void getCourseModule(List<String> selectedCourseId, String universityId,String programId,
			AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get the applicant details from applicant_account_info
	 * @param inputBean
	 * @param asyncCallback
	 */
	void getApplicantAccountDetails(EDEIStudentBean inputBean,
			AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To insert the summary sheet details in the respective tables
	 * @param finalData
	 * @param asyncCallback
	 */
	void insertEDEINEWSummarySheetDetails(EDEIStudentBean finalData,
			AsyncCallback<EDEIStudentBean> asyncCallback);

	/**
	 * To generate PDF of the applicant for the selected modules and courses
	 * @param outBean
	 * @param docFolder
	 * @param asyncCallback
	 */
	void generatePDF(EDEIStudentBean outBean, String docFolder,
			AsyncCallback<EDEIStudentBean> asyncCallback);

	/**
	 * Method to send mail confirmation
	 * @param outBean
	 * @param asyncCallback
	 */
	void sendMailConfirmation(EDEIStudentBean outBean,
			AsyncCallback<EDEIStudentBean> asyncCallback);

	/**
	 * To get credits limit for the module from system_values
	 * @param asyncCallback
	 */
	void getModuleCredit(AsyncCallback<Integer> asyncCallback);
	
	/**
	 * To get applicants applied modules
	 * @param userEmail
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param asyncCallback
	 */
	void checkstudentModules(String userEmail,String sessionStartDate, String sessionEndDate, AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get student applied Courses
	 * @param userEmail
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param asyncCallback
	 */
	void getStudentCourses(String userEmail, String sessionStartDate,String sessionEndDate,AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get university UG programs from XML
	 * @param asyncCallback
	 */
	void getProgramNameUGData(AsyncCallback<String[][]> asyncCallback);

	/**
	 * To get university PG programs from XML
	 * @param asyncCallback
	 */
	void getProgramNamePGData(AsyncCallback<String[][]> asyncCallback);

	/**
	 * To get university OT (other) programs from XML
	 * @param asyncCallback
	 */
	void getProgramNameOTData(AsyncCallback<String[][]> asyncCallback);

	/**
	 * To get online program list from database
	 * @param input
	 * @param asyncCallback
	 */
	void getUniversityOnlineProgram(EDEIStudentBean input,AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get trades of university
	 * @param uniNickName
	 * @param asyncCallback
	 */
	void getUniversityOnlineDomains(String uniNickName,AsyncCallback<List<EDEIStudentBean>> asyncCallback);

	/**
	 * To get modules of all online Programs
	 * @param input
	 * @param asyncCallback
	 */
	void getUniversityOnlineProgramModule(EDEIStudentBean input,AsyncCallback<List<EDEIStudentBean>> asyncCallback);
	
	/**
	 * To get the applicant details from student_master and addresses_master
	 * @param inputBean
	 * @param asyncCallback
	 */
	void getPersonalDetails(EDEIStudentBean inputBean,
			AsyncCallback<List<EDEIStudentBean>> asyncCallback);	
	
	/**
	 * To insert the summary sheet details in the respective tables for Existing program
	 * @param finalData
	 * @param asyncCallback
	 */
	void insertEDEINEWSummarySheetDetailsForExistingProgram(EDEIStudentBean finalData,
			AsyncCallback<EDEIStudentBean> asyncCallback);
	
	/**
	 * To generate PDF of the applicant for the selected modules and courses for Existing Program
	 * @param outBean
	 * @param docFolder
	 * @param asyncCallback
	 */
	void generatePDFForExistingProgram(EDEIStudentBean outBean, String docFolder,
			AsyncCallback<EDEIStudentBean> asyncCallback);
	
	/**
	 * To insert the summary sheet details in the respective tables for Existing program
	 * @param finalData
	 * @param asyncCallback
	 */
	void insertEDEINEWSummarySheetDetailsForNEWProgram(EDEIStudentBean finalData,
			AsyncCallback<EDEIStudentBean> asyncCallback);
}
