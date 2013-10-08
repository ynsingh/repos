/**
 * @(#) PrestagingInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.prestaging;

/**
 * this is Server side bean class for prestaging
 * 
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 */
public class PrestagingInfoGetter {
	/** declaring private variables */
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;
	private String studentId;
	private String registrationNo;
	private String registrationDueDate;
	private String enrollmentNo;
	private String dob;
	private String category;
	private String gender;
	private String admissionMode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String primaryEmail;
	private String processStatus;
	private String perAddress;
	private String perCity;
	private String perState;
	private String perPinCode;
	private String corAddress;
	private String corCity;
	private String corState;
	private String corPinCode;
	private String officePhone;
	private String homePhone;
	private String otherPhone;
	private String fax;
	private String attemptNo;
	private String universityId;
	private String parentEntity;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterId;
	private String oldEntityId;
	private String oldProgramId;
	private String oldBranchId;
	private String oldSpecializationId;
	private String oldSemesterId;
	private String programName;
	private String branchName;
	private String specializationName;
	private String semesterName;
	private String userId;
	private String nickName;
	private String rollNoGroupCode;//Add By Devendra
	private String longField;//Add By Devendra

	// defining getter and setter method for variables
	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherFirstName() {
		return motherFirstName;
	}

	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
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

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}

	public String getPerCity() {
		return perCity;
	}

	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}

	public String getPerState() {
		return perState;
	}

	public void setPerState(String perState) {
		this.perState = perState;
	}

	public String getPerPinCode() {
		return perPinCode;
	}

	public void setPerPinCode(String perPinCode) {
		this.perPinCode = perPinCode;
	}

	public String getCorAddress() {
		return corAddress;
	}

	public void setCorAddress(String corAddress) {
		this.corAddress = corAddress;
	}

	public String getCorCity() {
		return corCity;
	}

	public void setCorCity(String corCity) {
		this.corCity = corCity;
	}

	public String getCorState() {
		return corState;
	}

	public void setCorState(String corState) {
		this.corState = corState;
	}

	public String getCorPinCode() {
		return corPinCode;
	}

	public void setCorPinCode(String corPinCode) {
		this.corPinCode = corPinCode;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAttemptNo() {
		return attemptNo;
	}

	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getParentEntity() {
		return parentEntity;
	}

	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

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

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
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

	public String getOldSemesterId() {
		return oldSemesterId;
	}

	public void setOldSemesterId(String oldSemesterId) {
		this.oldSemesterId = oldSemesterId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setRegistrationDueDate(String registrationDueDate) {
		this.registrationDueDate = registrationDueDate;
	}

	public String getRegistrationDueDate() {
		return registrationDueDate;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	/**
	 * @return the rollNoGroupCode
	 */
	public String getRollNoGroupCode() {
		return rollNoGroupCode;
	}

	/**
	 * @param rollNoGroupCode the rollNoGroupCode to set
	 */
	public void setRollNoGroupCode(String rollNoGroupCode) {
		this.rollNoGroupCode = rollNoGroupCode;
	}

	/**
	 * @return the longField
	 */
	public String getLongField() {
		return longField;
	}

	/**
	 * @param longField the longField to set
	 */
	public void setLongField(String longField) {
		this.longField = longField;
	}
}