
/*
 * @(#) MasterTransferBean.java
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

import java.util.*;

public class MasterTransferBean {

	private String univeristyId;

	private String enrollmentNumber;

	private String newEntity;
	private String newProgram;
	private String newBranch;
	private String newSpecialization;
	private String newSemester;
	private String semesterStartDate;
	private String semesterEndDate;
	private String oldEntity;
	private String oldProgram;
	private String oldBranch;
	private String oldSpecialization;
	private String oldSemester;

	private String rollNumber;
	private String studentId;
	private String oldRollNumber;

	private String admissionMode;
	private String statusInSemester;
	private String statusInOldSemester;

	private String registerDate;
	private String registrationStatus;
	private String registrationStartDate;

	private int switchNumber;

	private String status;

	private List<CourseList> courseList;

	private String userId;

	private String programCourseKey;
	private String oldProgramCourseKey;

	private String processId;
	private String activityId;

	private String registrationNumber;
	private String courseCode;
	private String options;
	private String courseStatus;
	private String modeOfEntry;
	private String programStatus;

	private String sessionStartDate;
	private String sessionEndDate;

	private int processCount;

	private String courseGroup;
	
	private double registeredCredit;
	private double registeredCreditExcludingAudit;
	private double registeredTheoryExcludingAudit;
	private double registeredPracticalExcludingAudit;
	private String courseName;			// Added By Dheeraj For Transferring Course Name IN student_course
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getModeOfEntry() {
		return modeOfEntry;
	}

	public void setModeOfEntry(String modeOfEntry) {
		this.modeOfEntry = modeOfEntry;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getUniveristyId() {
		return univeristyId;
	}

	public void setUniveristyId(String univeristyId) {
		this.univeristyId = univeristyId;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(String newEntity) {
		this.newEntity = newEntity;
	}

	public String getNewProgram() {
		return newProgram;
	}

	public void setNewProgram(String newProgram) {
		this.newProgram = newProgram;
	}

	public String getNewBranch() {
		return newBranch;
	}

	public void setNewBranch(String newBranch) {
		this.newBranch = newBranch;
	}

	public String getNewSpecialization() {
		return newSpecialization;
	}

	public void setNewSpecialization(String newSpecialization) {
		this.newSpecialization = newSpecialization;
	}

	public String getNewSemester() {
		return newSemester;
	}

	public void setNewSemester(String newSemester) {
		this.newSemester = newSemester;
	}

	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	public String getSemesterEndDate() {
		return semesterEndDate;
	}

	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	public String getOldEntity() {
		return oldEntity;
	}

	public void setOldEntity(String oldEntity) {
		this.oldEntity = oldEntity;
	}

	public String getOldProgram() {
		return oldProgram;
	}

	public void setOldProgram(String oldProgram) {
		this.oldProgram = oldProgram;
	}

	public String getOldBranch() {
		return oldBranch;
	}

	public void setOldBranch(String oldBranch) {
		this.oldBranch = oldBranch;
	}

	public String getOldSpecialization() {
		return oldSpecialization;
	}

	public void setOldSpecialization(String oldSpecialization) {
		this.oldSpecialization = oldSpecialization;
	}

	public String getOldSemester() {
		return oldSemester;
	}

	public void setOldSemester(String oldSemester) {
		this.oldSemester = oldSemester;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getOldRollNumber() {
		return oldRollNumber;
	}

	public void setOldRollNumber(String oldRollNumber) {
		this.oldRollNumber = oldRollNumber;
	}

	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	public String getStatusInSemester() {
		return statusInSemester;
	}

	public void setStatusInSemester(String statusInSemester) {
		this.statusInSemester = statusInSemester;
	}

	public String getStatusInOldSemester() {
		return statusInOldSemester;
	}

	public void setStatusInOldSemester(String statusInOldSemester) {
		this.statusInOldSemester = statusInOldSemester;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegistrationStartDate() {
		return registrationStartDate;
	}

	public void setRegistrationStartDate(String registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CourseList> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseList> courseList) {
		this.courseList = courseList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSwitchNumber() {
		return switchNumber;
	}

	public void setSwitchNumber(int switchNumber) {
		this.switchNumber = switchNumber;
	}

	private int sequenceNumber;

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	public String getOldProgramCourseKey() {
		return oldProgramCourseKey;
	}

	public void setOldProgramCourseKey(String oldProgramCourseKey) {
		this.oldProgramCourseKey = oldProgramCourseKey;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	public int getProcessCount() {
		return processCount;
	}

	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
	
	public String getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(String courseGroup) {
		this.courseGroup = courseGroup;
	}
	
	public double getRegisteredCredit() {
		return registeredCredit;
	}

	public void setRegisteredCredit(double registeredCredit) {
		this.registeredCredit = registeredCredit;
	}

	public double getRegisteredCreditExcludingAudit() {
		return registeredCreditExcludingAudit;
	}

	public void setRegisteredCreditExcludingAudit(
			double registeredCreditExcludingAudit) {
		this.registeredCreditExcludingAudit = registeredCreditExcludingAudit;
	}

	public double getRegisteredTheoryExcludingAudit() {
		return registeredTheoryExcludingAudit;
	}

	public void setRegisteredTheoryExcludingAudit(
			double registeredTheoryExcludingAudit) {
		this.registeredTheoryExcludingAudit = registeredTheoryExcludingAudit;
	}

	public double getRegisteredPracticalExcludingAudit() {
		return registeredPracticalExcludingAudit;
	}

	public void setRegisteredPracticalExcludingAudit(
			double registeredPracticalExcludingAudit) {
		this.registeredPracticalExcludingAudit = registeredPracticalExcludingAudit;
	}

	public MasterTransferBean(String univeristyId, String newEntity,
			String newProgram, String newBranch, String newSpecialization,
			String newSemester, String semesterStartDate,
			String semesterEndDate, String oldEntity, String oldProgram,
			String oldBranch, String oldSpecialization, String oldSemester,
			String enrollmentNumber, String rollNumber, String oldRollNumber,
			String studentId, String admissionMode, String statusInSemester,
			String modeOfEntry, String registrationStatus, int switchNumber,
			List<CourseList> courseList, String programCourseKey,
			String userId, String sessionStartDate, String sessionEndDate,
			String activityId, int processCount,double registeredCredit,double registeredCreditExcludingAudit,
			double registeredTheoryExcludingAudit,double registeredPracticalExcludingAudit
			) {
		super();
		this.univeristyId = univeristyId;

		this.newEntity = newEntity;
		this.newProgram = newProgram;
		this.newBranch = newBranch;
		this.newSpecialization = newSpecialization;
		this.newSemester = newSemester;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;

		this.oldEntity = oldEntity;
		this.oldProgram = oldProgram;
		this.oldBranch = oldBranch;
		this.oldSpecialization = oldSpecialization;
		this.oldSemester = oldSemester;

		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;

		this.oldRollNumber = oldRollNumber;
		this.studentId = studentId;

		this.admissionMode = admissionMode;
		this.statusInSemester = statusInSemester;

		this.modeOfEntry = modeOfEntry;
		this.registrationStatus = registrationStatus;

		this.switchNumber = switchNumber;
		this.courseList = courseList;
		this.programCourseKey = programCourseKey;
		this.userId = userId;

		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;

		this.activityId = activityId;

		this.processCount = processCount;
		
		this.registeredCredit=registeredCredit;
		this.registeredCreditExcludingAudit=registeredCreditExcludingAudit;
		this.registeredTheoryExcludingAudit=registeredTheoryExcludingAudit;
		this.registeredPracticalExcludingAudit=registeredPracticalExcludingAudit;

	}

	public MasterTransferBean(String univsersityId, String processId,
			String activityId, String newEntity, String newProgram,
			String newBranch, String newSpecialization, String newSemester,
			String semesterStartDate, String semesterEndDate, String userId,
			String sessionStartDate, String sessionEndDate,
			String programCourseKey, int processCount) {
		// TODO Auto-generated constructor stub
		this.univeristyId = univsersityId;
		this.processId = processId;
		this.activityId = activityId;
		this.newEntity = newEntity;
		this.newProgram = newProgram;
		this.newBranch = newBranch;
		this.newSpecialization = newSpecialization;
		this.newSemester = newSemester;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.userId = userId;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.programCourseKey = programCourseKey;
		this.processCount = processCount;
	}

	public MasterTransferBean() {
		super();
	}

}
