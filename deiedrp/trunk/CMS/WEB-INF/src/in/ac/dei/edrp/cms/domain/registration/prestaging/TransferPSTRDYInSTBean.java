
/*
 * @(#) TransferPSTRDYInSTBean.java
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

/**
 * @author Deepak
 * 
 */
public class TransferPSTRDYInSTBean {

	private String entityId;
	private String programId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String processStatus;

	private String admissionMode;

	private String rollNumber;
	private String enrollmentNumber;
	private String studentId;

	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;
	private String categoryCode;
	private String gender;
	private String dateOfBirth;
	private String primaryEmailId;
	private String statusInSemester;

	private String registerDueDate;
	private int attemptNumber;

	private String processedFlag;

	private String oldSemester;

	private String probableSemester;
	private String probableSemesterStartDate;
	private String probableSemesterEndDate;
	private String probableRegisterDueDate;
	private int probableAttemptNumber;

	private String oldEntity;
	private String oldProgram;
	private String newBranch;
	private String newSpecialization;
	private String oldBranch;
	private String oldSpecialization;

	private String oldRollNumber;

	private String creatorId;

	private String perAddress = "";
	private String perCity = "";
	private String perState = "";
	private String perPincode = "";
	private String corAddress = "";
	private String corCity = "";
	private String corState = "";
	private String corPincode = "";
	private String officePhone = "";
	private String extraPhone = "";
	private String otherPhone = "";
	private String fax = "";
	private int sequenceNumber;
    //****New fields added by Nupur*********
	private String rollNumberGroupCode;
	private String longField;
	//**************************************

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

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
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

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public String getStatusInSemester() {
		return statusInSemester;
	}

	public void setStatusInSemester(String statusInSemester) {
		this.statusInSemester = statusInSemester;
	}

	public String getRegisterDueDate() {
		return registerDueDate;
	}

	public void setRegisterDueDate(String registerDueDate) {
		this.registerDueDate = registerDueDate;
	}

	public int getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public String getProcessedFlag() {
		return processedFlag;
	}

	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}

	public String getOldSemester() {
		return oldSemester;
	}

	public void setOldSemester(String oldSemester) {
		this.oldSemester = oldSemester;
	}

	public String getProbableSemester() {
		return probableSemester;
	}

	public void setProbableSemester(String probableSemester) {
		this.probableSemester = probableSemester;
	}

	public String getProbableSemesterStartDate() {
		return probableSemesterStartDate;
	}

	public void setProbableSemesterStartDate(String probableSemesterStartDate) {
		this.probableSemesterStartDate = probableSemesterStartDate;
	}

	public String getProbableSemesterEndDate() {
		return probableSemesterEndDate;
	}

	public void setProbableSemesterEndDate(String probableSemesterEndDate) {
		this.probableSemesterEndDate = probableSemesterEndDate;
	}

	public String getProbableRegisterDueDate() {
		return probableRegisterDueDate;
	}

	public void setProbableRegisterDueDate(String probableRegisterDueDate) {
		this.probableRegisterDueDate = probableRegisterDueDate;
	}

	public int getProbableAttemptNumber() {
		return probableAttemptNumber;
	}

	public void setProbableAttemptNumber(int probableAttemptNumber) {
		this.probableAttemptNumber = probableAttemptNumber;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getOldRollNumber() {
		return oldRollNumber;
	}

	public void setOldRollNumber(String oldRollNumber) {
		this.oldRollNumber = oldRollNumber;
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

	public String getPerPincode() {
		return perPincode;
	}

	public void setPerPincode(String perPincode) {
		this.perPincode = perPincode;
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

	public String getCorPincode() {
		return corPincode;
	}

	public void setCorPincode(String corPincode) {
		this.corPincode = corPincode;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getExtraPhone() {
		return extraPhone;
	}

	public void setExtraPhone(String extraPhone) {
		this.extraPhone = extraPhone;
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
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public TransferPSTRDYInSTBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransferPSTRDYInSTBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String processStatus) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.processStatus = processStatus;

	}

	public TransferPSTRDYInSTBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String rollNumber, String processStatus) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.rollNumber = rollNumber;
		this.processStatus = processStatus;

	}

	public TransferPSTRDYInSTBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String newBranch, String newSpecialization,
			String oldEntity, String oldProgram, String oldBranch,
			String oldSpecialization, String admissionMode, String rollNumber,
			String enrollmentNumber, String studentId, String oldRollNumber,
			String studentFirstName, String studentMiddleName,
			String studentLastName, String fatherFirstName,
			String fatherMiddleName, String fatherLastName,
			String motherFirstName, String motherMiddleName,
			String motherLastName, String categoryCode, String gender,
			String dateOfBirth, String primaryEmailId, String statusInSemester,
			String registerDueDate, int attemptNumber, String oldSemester,
			String probableSemester, String probableSemesterStartDate,
			String probableSemesterEndDate, String probableRegisterDueDate,
			int probableAttemptNumber, String creatorId, String perAddress,
			String perCity, String perState, String perPincode,
			String corAddress, String corCity, String corState,
			String corPincode, String officePhone, String extraPhone,
			String otherPhone, String fax) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;

		this.newBranch = newBranch;
		this.newSpecialization = newSpecialization;

		this.oldEntity = oldEntity;
		this.oldProgram = oldProgram;
		this.oldBranch = oldBranch;
		this.oldSpecialization = oldSpecialization;

		this.admissionMode = admissionMode;
		this.rollNumber = rollNumber;
		this.enrollmentNumber = enrollmentNumber;
		this.studentId = studentId;

		this.oldRollNumber = oldRollNumber;

		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.categoryCode = categoryCode;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.primaryEmailId = primaryEmailId;
		this.statusInSemester = statusInSemester;
		this.registerDueDate = registerDueDate;
		this.attemptNumber = attemptNumber;

		this.oldSemester = oldSemester;
		this.probableSemester = probableSemester;
		this.probableSemesterStartDate = probableSemesterStartDate;
		this.probableSemesterEndDate = probableSemesterEndDate;
		this.probableRegisterDueDate = probableRegisterDueDate;
		this.probableAttemptNumber = probableAttemptNumber;
		this.creatorId = creatorId;

		this.perAddress = perAddress;
		this.perCity = perCity;
		this.perState = perState;
		this.perPincode = perPincode;
		this.corAddress = corAddress;
		this.corCity = corCity;
		this.corState = corState;
		this.corPincode = corPincode;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.extraPhone = extraPhone;
		this.fax = fax;
		
	}
	
public void setRollNumberGroupCode(String rollNumberGroupCode) {
		this.rollNumberGroupCode = rollNumberGroupCode;
	}

	public String getRollNumberGroupCode() {
		return rollNumberGroupCode;
	}

	public void setLongField(String longField) {
		this.longField = longField;
	}

	public String getLongField() {
		return longField;
	}
}
