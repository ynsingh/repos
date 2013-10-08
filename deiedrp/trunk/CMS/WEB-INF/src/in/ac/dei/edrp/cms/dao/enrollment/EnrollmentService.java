/**
 * @(#) EnrollmentService.java
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
package in.ac.dei.edrp.cms.dao.enrollment;

import java.util.List;

import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;

/**
 * The client interface for Enrollment
 * 
 * @version 1.0 11 MAR 2011
 * @author MOHD AMIR
 */
public interface EnrollmentService {
	/** getting Personal details from Database */
	public List<EnrollmentInfo> getPersonalDetails(EnrollmentInfo enrollmentInfo) throws Exception;

	/** getting academic details from Database */
	public List<EnrollmentInfo> getAcademicDetails(String registrationNo) throws Exception;

	/** getting contact details from Database */
	public List<EnrollmentInfo> getContactDetails(String userId) throws Exception;

	/** Inserting student info into Database */
	public String updateStudentDetails(EnrollmentInfo personalInfo,
			List<EnrollmentInfo> addressList, List<EnrollmentInfo> academicList) throws Exception;

	/** Uploading photos and setting photo path into Database */
	public Boolean uploadStudentPhotos(String filePath,String outputFolder) throws Exception;
	
	/**Method to get student final list from database
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	*/
	public List<EnrollmentInfo> getStudentFinalList(EnrollmentInfo enrollmentInfo) throws Exception;
	
	/**Method to get list  of those student whose sounds match to other students from database
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	*/
	public List<EnrollmentInfo> getStudentChackedList(EnrollmentInfo enrollmentInfo) throws Exception;
	
	/**Method to get list  roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return List<EnrollmentInfo>
	 *@author Devendra Singhal
	*/
	public List<EnrollmentInfo> getFormat(EnrollmentInfo enrollmentInfo);
	
	/**Method to Delete roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@param String Format
	 *@return String containing success/fail
	 *@author Devendra Singhal
	*/
	public String deleteFormat(EnrollmentInfo enrollmentInfo,String format);
	
	/**Method to Insert roll number/enrollment number format
	 *@param Object of EnrollmentInfo enrollmentInfo
	 *@return String containing success/fail
	 *@author Devendra Singhal
	*/
	public String insertFormat(EnrollmentInfo enrollmentInfo);
}