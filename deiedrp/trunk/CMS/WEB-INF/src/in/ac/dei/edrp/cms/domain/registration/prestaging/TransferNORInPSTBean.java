
/*
 * @(#) TransferNORInPSTBean.java
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

public class TransferNORInPSTBean {

	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;

	private String programCourseKey;

	private String nextSemesterCode;
	private String nextSemesterStartDate;
	private String nextSemesterEndDate;
	private String nextRegisterDueDate;
	private String nextProgramCourseKey;

	private boolean checkSemesterGroup;

	private String registerDueDate;

	private int maxAttemptAllowed;

	private String programStatus;

	private int attemptNumber;

	private String studentId;
	private String enrollmentNumber;
	private String rollNumber;

	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;

	private String dateOfBirth;
	private String category;
	private String gender;
	private String primaryEmailId;

	private String admissionMode;
	private String creatorId;

	private String statusInSemester;
	private String processStatus;
	private String nextStatus;

	private String probableSemester;
	private String probableRegisterDueDate;
	private String probableSemesterStartDate;
	private String probableSemesterEndDate;
	private int probableAttemptNumber;

	private String sessionStartDate;
	private String sessionEndDate;

	private String activityId;

	/**
	 * 
	 * @return Entity id
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
	 * @return program id
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
	 * @return branch id
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
	 * @return specialization id
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

	/**
	 * 
	 * @return semestercode
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
	 * @return semester start date
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
	 * @return semester end date
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
	 * @return programCoursekey
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
	 * @return registration semester code
	 */
	public String getNextSemesterCode() {
		return nextSemesterCode;
	}

	/**
	 * 
	 * @param nextSemesterCode:registration
	 *            semester code
	 */
	public void setNextSemesterCode(String nextSemesterCode) {
		this.nextSemesterCode = nextSemesterCode;
	}

	/**
	 * 
	 * @return registration semester start date
	 */
	public String getNextSemesterStartDate() {
		return nextSemesterStartDate;
	}

	/**
	 * 
	 * @param nextSemesterStartDate:registration
	 *            semester start date
	 */
	public void setNextSemesterStartDate(String nextSemesterStartDate) {
		this.nextSemesterStartDate = nextSemesterStartDate;
	}

	/**
	 * 
	 * @return registration semester end date
	 */
	public String getNextSemesterEndDate() {
		return nextSemesterEndDate;
	}

	/**
	 * 
	 * @param nextSemesterEndDate:registration
	 *            semester end date
	 */
	public void setNextSemesterEndDate(String nextSemesterEndDate) {
		this.nextSemesterEndDate = nextSemesterEndDate;
	}

	/**
	 * 
	 * @return RegisterDueDate
	 */
	public String getNextRegisterDueDate() {
		return nextRegisterDueDate;
	}

	/**
	 * 
	 * @param nextRegisterDueDate
	 */
	public void setNextRegisterDueDate(String nextRegisterDueDate) {
		this.nextRegisterDueDate = nextRegisterDueDate;
	}

	/**
	 * 
	 * @return registration program course key
	 */
	public String getNextProgramCourseKey() {
		return nextProgramCourseKey;
	}

	/**
	 * 
	 * @param nextProgramCourseKey
	 */
	public void setNextProgramCourseKey(String nextProgramCourseKey) {
		this.nextProgramCourseKey = nextProgramCourseKey;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getCheckSemesterGroup() {
		return checkSemesterGroup;
	}

	/**
	 * 
	 * @param checkSemesterGroup
	 */
	public void setCheckSemesterGroup(boolean checkSemesterGroup) {
		this.checkSemesterGroup = checkSemesterGroup;
	}

	/**
	 * 
	 * @return
	 */
	public String getRegisterDueDate() {
		return registerDueDate;
	}

	/**
	 * 
	 * @param registerDueDate
	 */
	public void setRegisterDueDate(String registerDueDate) {
		this.registerDueDate = registerDueDate;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxAttemptAllowed() {
		return maxAttemptAllowed;
	}

	/**
	 * 
	 * @param maxAttemptAllowed
	 */
	public void setMaxAttemptAllowed(int maxAttemptAllowed) {
		this.maxAttemptAllowed = maxAttemptAllowed;
	}

	/**
	 * 
	 * @return programStatus
	 */
	public String getProgramStatus() {
		return programStatus;
	}

	/**
	 * 
	 * @param programStatus
	 */
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	/**
	 * 
	 * @return attempt number
	 */
	public int getAttemptNumber() {
		return attemptNumber;
	}

	/**
	 * 
	 * @param attemptNumber
	 */
	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	/**
	 * 
	 * @return student id
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
	 * @return enrollment number
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
	 * @return
	 */
	public String getStudentFirstName() {
		return studentFirstName;
	}

	/**
	 * 
	 * @param studentFirstName
	 */
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	/**
	 * 
	 * @param studentMiddleName
	 */
	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	/**
	 * 
	 * @return
	 */
	public String getStudentLastName() {
		return studentLastName;
	}

	/**
	 * 
	 * @param studentLastName
	 */
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	/**
	 * 
	 * @return
	 */
	public String getFatherFirstName() {
		return fatherFirstName;
	}

	/**
	 * 
	 * @param fatherFirstName
	 */
	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	/**
	 * 
	 * @param fatherMiddleName
	 */
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	/**
	 * 
	 * @return
	 */
	public String getFatherLastName() {
		return fatherLastName;
	}

	/**
	 * 
	 * @param fatherLastName
	 */
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	/**
	 * 
	 * @return
	 */
	public String getMotherFirstName() {
		return motherFirstName;
	}

	/**
	 * 
	 * @param motherFirstName
	 */
	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	/**
	 * 
	 * @param motherMiddleName
	 */
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	/**
	 * 
	 * @return
	 */
	public String getMotherLastName() {
		return motherLastName;
	}

	/**
	 * 
	 * @param motherLastName
	 */
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	/**
	 * 
	 * @return
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * 
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return
	 */
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	/**
	 * 
	 * @return
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
	 * @return
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * 
	 * @param creatorId
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 
	 * @param primaryEmailId
	 */
	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatusInSemester() {
		return statusInSemester;
	}

	/**
	 * 
	 * @param statusInSemester
	 */
	public void setStatusInSemester(String statusInSemester) {
		this.statusInSemester = statusInSemester;
	}

	/**
	 * 
	 * @return
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
	 * @return
	 */
	public String getProbableSemester() {
		return probableSemester;
	}

	/**
	 * 
	 * @param probableSemester
	 */
	public void setProbableSemester(String probableSemester) {
		this.probableSemester = probableSemester;
	}

	/**
	 * 
	 * @return
	 */
	public String getProbableRegisterDueDate() {
		return probableRegisterDueDate;
	}

	/**
	 * 
	 * @param probableRegisterDueDate
	 */
	public void setProbableRegisterDueDate(String probableRegisterDueDate) {
		this.probableRegisterDueDate = probableRegisterDueDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getProbableSemesterStartDate() {
		return probableSemesterStartDate;
	}

	/**
	 * 
	 * @param probableSemesterStartDate
	 */
	public void setProbableSemesterStartDate(String probableSemesterStartDate) {
		this.probableSemesterStartDate = probableSemesterStartDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getProbableSemesterEndDate() {
		return probableSemesterEndDate;
	}

	/**
	 * 
	 * @param probableSemesterEndDate
	 */
	public void setProbableSemesterEndDate(String probableSemesterEndDate) {
		this.probableSemesterEndDate = probableSemesterEndDate;
	}

	/**
	 * 
	 * @return
	 */
	public int getProbableAttemptNumber() {
		return probableAttemptNumber;
	}

	/**
	 * 
	 * @param probableAttemptNumber
	 */
	public void setProbableAttemptNumber(int probableAttemptNumber) {
		this.probableAttemptNumber = probableAttemptNumber;
	}

	public TransferNORInPSTBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(String nextStatus) {
		this.nextStatus = nextStatus;
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

	/**
	 * 
	 * @param entityId
	 * @param programId
	 * @param branchId
	 * @param specializationId
	 * @param semesterCode
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param programCourseKey
	 */
	public TransferNORInPSTBean(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
	}

	/**
	 * 
	 * @param programId
	 * @param branchId
	 * @param specializationId
	 * @param semesterCode
	 */
	public TransferNORInPSTBean(String programId, String branchId,
			String specializationId, String semesterCode) {
		super();
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
	}

	public TransferNORInPSTBean(String programCourseKey,
			String semesterStartDate, String semesterEndDate) {
		super();

		this.programCourseKey = programCourseKey;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
	}

	public TransferNORInPSTBean(String programId, String semesterCode) {
		super();
		this.programId = programId;
		this.semesterCode = semesterCode;
	}

	public TransferNORInPSTBean(String programId) {
		super();
		this.programId = programId;
	}

	public TransferNORInPSTBean(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate,
			String programCourseKey, String programStatus, String processStatus) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.programCourseKey = programCourseKey;
		this.programStatus = programStatus;
		this.processStatus = processStatus;

	}

	public TransferNORInPSTBean(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate,
			String sessionStartDate, String sessionEndDate,
			String programCourseKey, String studentId, String enrollmentNumber,
			String rollNumber, String studentFirstName,
			String studentMiddleName, String studentLastName,
			String fatherFirstName, String fatherMiddleName,
			String fatherLastName, String motherFirstName,
			String motherMiddleName, String motherLastName, String dateOfBirth,
			String category, String gender, String primaryEmailId,
			String statusInSemester, int attemptNumber,
			String nextProgramCourseKey, String nextSemesterCode,
			String nextSemesterStartDate, String nextSemesterEndDate,
			String registerDueDate, boolean checkSemesterGroup,
			int maxAttemptAllowed, String admissionMode, String nextStatus,
			String processStatus, String creatorId, String activityId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.programCourseKey = programCourseKey;

		this.studentId = studentId;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.dateOfBirth = dateOfBirth;
		this.category = category;
		this.gender = gender;
		this.primaryEmailId = primaryEmailId;

		this.statusInSemester = statusInSemester;
		this.attemptNumber = attemptNumber;

		this.nextProgramCourseKey = nextProgramCourseKey;
		this.nextSemesterCode = nextSemesterCode;
		this.nextSemesterStartDate = nextSemesterStartDate;
		this.nextSemesterEndDate = nextSemesterEndDate;

		this.registerDueDate = registerDueDate;

		this.checkSemesterGroup = checkSemesterGroup;
		this.registerDueDate = registerDueDate;
		this.maxAttemptAllowed = maxAttemptAllowed;

		this.admissionMode = admissionMode;
		this.nextStatus = nextStatus;
		this.processStatus = processStatus;

		this.creatorId = creatorId;
		this.activityId = activityId;
	}

	// For REM case:
	public TransferNORInPSTBean(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate,
			String sessionStartDate, String sessionEndDate,
			String programCourseKey, String studentId, String enrollmentNumber,
			String rollNumber, String studentFirstName,
			String studentMiddleName, String studentLastName,
			String fatherFirstName, String fatherMiddleName,
			String fatherLastName, String motherFirstName,
			String motherMiddleName, String motherLastName, String dateOfBirth,
			String category, String gender, String primaryEmailId,
			String statusInSemester, int attemptNumber,
			String nextProgramCourseKey, String nextSemesterCode,
			String nextSemesterStartDate, String nextSemesterEndDate,
			String registerDueDate, boolean checkSemesterGroup,
			int maxAttemptAllowed, String admissionMode, String nextStatus,
			String processStatus, String probableSemester,
			String probableRegisterDueDate, String probableSemesterStartDate,
			String probableSemesterEndDate, int probableAttemptNumber,
			String creatorId, String activityId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.programCourseKey = programCourseKey;

		this.studentId = studentId;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.dateOfBirth = dateOfBirth;
		this.category = category;
		this.gender = gender;
		this.primaryEmailId = primaryEmailId;

		this.statusInSemester = statusInSemester;
		this.attemptNumber = attemptNumber;

		this.nextProgramCourseKey = nextProgramCourseKey;
		this.nextSemesterCode = nextSemesterCode;
		this.nextSemesterStartDate = nextSemesterStartDate;
		this.nextSemesterEndDate = nextSemesterEndDate;

		this.registerDueDate = registerDueDate;

		this.checkSemesterGroup = checkSemesterGroup;
		this.registerDueDate = registerDueDate;
		this.maxAttemptAllowed = maxAttemptAllowed;

		this.admissionMode = admissionMode;
		this.nextStatus = nextStatus;
		this.processStatus = processStatus;

		this.probableSemester = probableSemester;
		this.probableRegisterDueDate = probableRegisterDueDate;
		this.probableSemesterStartDate = probableSemesterStartDate;
		this.probableSemesterEndDate = probableSemesterEndDate;
		this.probableAttemptNumber = probableAttemptNumber;

		this.creatorId = creatorId;
		this.activityId = activityId;
	}

}
