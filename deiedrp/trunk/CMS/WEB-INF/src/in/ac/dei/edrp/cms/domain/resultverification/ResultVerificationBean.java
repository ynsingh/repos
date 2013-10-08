/*
 * @(#) ResultVerificationBean.java
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

package in.ac.dei.edrp.cms.domain.resultverification;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the getter setter class 
 * @author Nupur Dixit
 * @date 27 Oct 2012
 * @version 1.0
 */
public class ResultVerificationBean {
	private String universityId;
	private String requestNo;
	private String company;
	private String receiveDate;
	private String processDate;
	private String processStatus;
	private String creatorId;
	private String modifierId;
	private String insertTime;
	private String modificationTime;
	private String extra;
	private String rollNumber;
	private String requester;
	private String compName;
	private String compAdd;
	private String refNo;
	private String refDate;
	private String requestType;
	private List<String> rollNoList = new ArrayList<String>();
	private List<String> olderRollNoList = new ArrayList<String>();
	private String requestCode;
	private String requestTypeCode;
	private String requestTypeName;
	
	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompAdd() {
		return compAdd;
	}

	public void setCompAdd(String compAdd) {
		this.compAdd = compAdd;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}

	private String sessionStartDate;
	private String sessionEndDate;
	private String entityId;
	private String programId;
	private String programCode;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String oldEntityId;
	private String oldProgramId;
	private String oldBranchId;
	private String oldSpecializationId;
	private String oldSemester;
	private String newEntityId;
	private String newProgramId;
	private String newBranchId;
	private String newSpecializationId;
	private String newSemester;
	private String oldClassName;
	private String newClassName;
	private String oldRollNumber;
	private String newRollNumber;
	private String enrollmentNumber;	
	
	private String motherName;
	private String processFlag;	
	private String semesterName;
	private String finalSemesterCode;
	private String programStatus;	
	private String programName;	
	private String branchName;
	private String specialization;
	private String programCourseKey;
	private String studentName;
	private String fatherName;
	
	
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}
	
	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	
	
	
	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getFinalSemesterCode() {
		return finalSemesterCode;
	}

	public void setFinalSemesterCode(String finalSemesterCode) {
		this.finalSemesterCode = finalSemesterCode;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

		/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}


	/**
	 * @param semesterCode the semesterCode to set
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * @return the semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}

	/**
	 * @param semesterName the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return semesterName;
	}


	/**
	 * @param universityId the universityId to set
	 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/**
	 * @return the universityId
	 */
	public String getUniversityId() {
		return universityId;
	}


	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public String getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public String getSessionEndDate() {
		return sessionEndDate;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramCode() {
		return programCode;
	}

	public String getOldEntityId() {
		return oldEntityId;
	}

	public void setOldEntityId(String oldEntityId) {
		this.oldEntityId = oldEntityId;
	}

	public String getOldProgramId() {
		return oldProgramId;
	}

	public void setOldProgramId(String oldProgramId) {
		this.oldProgramId = oldProgramId;
	}

	public String getOldBranchId() {
		return oldBranchId;
	}

	public void setOldBranchId(String oldBranchId) {
		this.oldBranchId = oldBranchId;
	}

	public String getOldSpecializationId() {
		return oldSpecializationId;
	}

	public void setOldSpecializationId(String oldSpecializationId) {
		this.oldSpecializationId = oldSpecializationId;
	}

	public String getOldSemester() {
		return oldSemester;
	}

	public void setOldSemester(String oldSemester) {
		this.oldSemester = oldSemester;
	}

	public String getNewEntityId() {
		return newEntityId;
	}

	public void setNewEntityId(String newEntityId) {
		this.newEntityId = newEntityId;
	}

	public String getNewProgramId() {
		return newProgramId;
	}

	public void setNewProgramId(String newProgramId) {
		this.newProgramId = newProgramId;
	}

	public String getNewBranchId() {
		return newBranchId;
	}

	public void setNewBranchId(String newBranchId) {
		this.newBranchId = newBranchId;
	}

	public String getNewSpecializationId() {
		return newSpecializationId;
	}

	public void setNewSpecializationId(String newSpecializationId) {
		this.newSpecializationId = newSpecializationId;
	}

	public String getNewSemester() {
		return newSemester;
	}

	public void setNewSemester(String newSemester) {
		this.newSemester = newSemester;
	}

	public String getOldClassName() {
		return oldClassName;
	}

	public void setOldClassName(String oldClassName) {
		this.oldClassName = oldClassName;
	}

	public String getNewClassName() {
		return newClassName;
	}

	public void setNewClassName(String newClassName) {
		this.newClassName = newClassName;
	}

	public String getOldRollNumber() {
		return oldRollNumber;
	}

	public void setOldRollNumber(String oldRollNumber) {
		this.oldRollNumber = oldRollNumber;
	}

	public String getNewRollNumber() {
		return newRollNumber;
	}

	public void setNewRollNumber(String newRollNumber) {
		this.newRollNumber = newRollNumber;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getExtra() {
		return extra;
	}

	public void setRollNoList(List<String> rollNoList) {
		this.rollNoList = rollNoList;
	}

	public List<String> getRollNoList() {
		return rollNoList;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setOlderRollNoList(List<String> olderRollNoList) {
		this.olderRollNoList = olderRollNoList;
	}

	public List<String> getOlderRollNoList() {
		return olderRollNoList;
	}

	public void setRequestTypeCode(String requestTypeCode) {
		this.requestTypeCode = requestTypeCode;
	}

	public String getRequestTypeCode() {
		return requestTypeCode;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}

	public String getRequestTypeName() {
		return requestTypeName;
	}	
	
}
