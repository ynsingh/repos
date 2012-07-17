
/*
 * @(#) ReadyPreStagingBean.java
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
package in.ac.dei.edrp.cms.domain.registration.prestaging;

public class ReadyPreStagingBean {

	private String entityId;
	private String programId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String process;
	private String activity;

	private String enrollmentNumber;
	private String rollNumber;
	private String studentId;
	private String admissionMode;

	private String processStatus;

	private String reasonCode;
	private String description;

	private String admissionNormalMode;
	private String admissionSwitchMode;

	private String programCourseKey;
	private String status;
	private String currentStatus;

	private int count;

	private String branchId;
	private String specializationId;

	private String modifierId;

	private String sessionStartDate;
	private String sessionEndDate;
	private String activityId;
	/*
	 * Added by Ashish
	 */
	private String studentName;
	private String fatherName;
	private String motherName;
	private String dateOfBirth;
	private String gender;
	private String category;
	

	/**
	 * Default Constructor
	 */
	public ReadyPreStagingBean() {
		super();
	}

	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String admissionMode) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.admissionMode = admissionMode;

	}

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param admissionNormalMode
	 * @param admissionSwitchMode
	 */
	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String admissionNormalMode,
			String admissionSwitchMode) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.admissionNormalMode = admissionNormalMode;
		this.admissionSwitchMode = admissionSwitchMode;
	}

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param enrollmentNumber
	 * @param rollNumber
	 * @param admissionMode
	 */
	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String enrollmentNumber, String rollNumber,
			String admissionMode, String modifierId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.admissionMode = admissionMode;
		this.modifierId = modifierId;

	}

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param enrollmentNumber
	 * @param rollNumber
	 * @param admissionMode
	 * @param processStatus
	 */
	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String enrollmentNumber, String rollNumber,
			String admissionMode, String processStatus, String modifierId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.enrollmentNumber = enrollmentNumber;
		this.admissionMode = admissionMode;
		this.rollNumber = rollNumber;
		this.processStatus = processStatus;
		this.modifierId = modifierId;
	}

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param enrollmentNumber
	 * @param rollNumber
	 * @param admissionMode
	 * @param processStatus
	 * @param reasonCode
	 * @param description
	 */
	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String enrollmentNumber, String rollNumber,
			String admissionMode, String processStatus, String reasonCode,
			String description, String modifierId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.admissionMode = admissionMode;
		this.processStatus = processStatus;
		this.reasonCode = reasonCode;
		this.description = description;
		this.modifierId = modifierId;

	}

	public ReadyPreStagingBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String rollNumber, String admissionMode,
			String processStatus, String reasonCode, String description,
			String modifierId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.rollNumber = rollNumber;
		this.admissionMode = admissionMode;
		this.processStatus = processStatus;
		this.reasonCode = reasonCode;
		this.description = description;
		this.modifierId = modifierId;

	}

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param branchId
	 * @param specializationId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param enrollmentNumber
	 * @param rollNumber
	 * @param admissionMode
	 * @param processStatus
	 * @param reasonCode
	 * @param description
	 */
	public ReadyPreStagingBean(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate,
			String enrollmentNumber, String rollNumber, String admissionMode,
			String processStatus, String modifierId, String currentStatus) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.admissionMode = admissionMode;
		this.processStatus = processStatus;
		this.modifierId = modifierId;
		this.currentStatus = currentStatus;

	}
	
	

	public ReadyPreStagingBean(String entityId2, String programId2,
			String semesterCode2, String semesterStartDate2,
			String semesterEndDate2, String enrollmentNumber2,
			String registrationRollNumber, String newMode, String modifierId,
			String studentName2, String fatherName2, String motherName2,
			String dateOfBirth2, String gender2, String category2) {
		
		this.entityId=entityId2;
		this.programId=programId2;
		this.semesterCode=semesterCode2;
		this.semesterStartDate = semesterStartDate2;
		this.semesterEndDate = semesterEndDate2;
		this.enrollmentNumber = enrollmentNumber2;
		this.rollNumber = registrationRollNumber;
		this.admissionMode = newMode;
		this.modifierId = modifierId;
		this.studentName = studentName2;
		this.fatherName = fatherName2;
		this.motherName = motherName2;
		this.dateOfBirth = dateOfBirth2;
		this.gender = gender2;
		this.category = category2;
		
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * 
	 * @param entityId
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * 
	 * @return programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * 
	 * @param programId
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * 
	 * @return semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}

	/**
	 * 
	 * @param semesterCode
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * 
	 * @return semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	/**
	 * 
	 * @param semesterStartDate
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	/**
	 * 
	 * @return semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}

	/**
	 * 
	 * @param semesterEndDate
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	/**
	 * 
	 * @return process id
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * 
	 * @param process
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * 
	 * @return activity id
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * 
	 * @param activity
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * 
	 * @return enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	/**
	 * 
	 * @param enrollmentNumber
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	/**
	 * 
	 * @return rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * 
	 * @param rollNumber
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * 
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * 
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * 
	 * @return admissionNormalMode
	 */
	public String getAdmissionNormalMode() {
		return admissionNormalMode;
	}

	/**
	 * 
	 * @return admissionMode
	 */
	public String getAdmissionMode() {
		return admissionMode;
	}

	/**
	 * 
	 * @param admissionMode
	 */
	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	/**
	 * 
	 * @return processStatus
	 */
	public String getProcessStatus() {
		return processStatus;
	}

	/**
	 * 
	 * @param processStatus
	 */
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	/**
	 * 
	 * @return reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * 
	 * @param reasonCode
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @param admissionNormalMode
	 */
	public void setAdmissionNormalMode(String admissionNormalMode) {
		this.admissionNormalMode = admissionNormalMode;
	}

	/**
	 * 
	 * @return admissionSwitchMode
	 */
	public String getAdmissionSwitchMode() {
		return admissionSwitchMode;
	}

	/**
	 * 
	 * @param admissionSwitchMode
	 */
	public void setAdmissionSwitchMode(String admissionSwitchMode) {
		this.admissionSwitchMode = admissionSwitchMode;
	}

	/**
	 * 
	 * @return programCourseKey
	 */
	public String getProgramCourseKey() {
		return programCourseKey;
	}

	/**
	 * 
	 * @param programCourseKey
	 */
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return currentStatus
	 */

	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * 
	 * @param currentStatus
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * 
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 
	 * @return branchId
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * 
	 * @param branchId
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * 
	 * @return specializationId
	 */
	public String getSpecializationId() {
		return specializationId;
	}

	/**
	 * 
	 * @param specializationId
	 */
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public String getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}
