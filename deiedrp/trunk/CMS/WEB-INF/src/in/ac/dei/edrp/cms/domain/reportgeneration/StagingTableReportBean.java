/*
 * @(#) StagingTableReportBean.java
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

package in.ac.dei.edrp.cms.domain.reportgeneration;

/**
 * This is the getter setter class 
 * @author Nupur Dixit
 * @date 27 Sep 2012
 * @version 1.0
 */
public class StagingTableReportBean {
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
	private String rollNumber;
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
	private String universityId;
	
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
	
}
