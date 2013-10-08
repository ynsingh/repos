/**
 * @(#) EnrollmentInfo.java
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
package in.ac.dei.edrp.cms.domain.enrollment;

/**
 * this is Server side bean class for enrollemnt
 * 
 * @version 1.0 11 Mar 2011
 * @author MOHD AMIR
 */
public class EnrollmentInfo {
	/** declaring private variables */
	private String enrollmentNo;
	private String registrationNo;
	private String registrationStatus;
	private String studentId;
	private String studentName;
	private String fatherName;
	private String motherName;
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;
	private String primaryMail;
	private String secondaryMail;
	private String guardianName;
	private String gender;
	private String dob;
	private String nationality;
	private String religion;
	private String categoryCode;
	private String categoryName;
	private String entityCode;
	private String programCode;
	private String branchCode;
	private String specializationCode;
	private String entity;
	private String program;
	private String branch;
	private String specialization;
	private String semesterCode;
	private String addressKey;
	private String address;
	private String city;
	private String state;
	private String pinCode;
	private String exam;
	private String examCode;
	private String year;
	private String college;
	private String boardCode;
	private String board;
	private String officePhone;
	private String homePhone;
	private String otherPhone;
	private String userType;
	private Integer count;
	private String corAddress;
	private String corCity;
	private String corState;
	private String corPincode;
	private String admissionMode;
	private String fax;
	private String sessionStartDate;
	private String sessionEndDate;
	private String status;
	private String facRegNo;
	private String submitDate;
	private Integer totalMarks;
	private Integer marksObtained;
	private String universityId;
	private String nickName;
	private String seqNum;
	private String code;
	private String photoPath;
	private String hindiName;
	private String fatherHindiName;
	private String motherHindiName;
	private String cgpa;//Add by Devendra
	private String semesterStartDate;//Add by Devendra
	private String semesterEndDate;//Add by Devendra
	private String rollNumber;//Add by Devendara
	private String creatorId;
	private String rollNoGroupCode;//Add By Devendra
	private String longField;//Add By Devendra
	private int rangeFrom;
	private int rangeTo;
	private String format;
	private String type;
	private String userId;
	// defining getter and setter method for variables
	
	
	/**
	 * @return the cgpa
	 */
	public String getCgpa() {
		return cgpa;
	}

	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	/**
	 * @param semesterStartDate the semesterStartDate to set
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	/**
	 * @return the semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}

	/**
	 * @param semesterEndDate the semesterEndDate to set
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	/**
	 * @param cgpa the cgpa to set
	 */
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
	
	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
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

	public String getPrimaryMail() {
		return primaryMail;
	}

	public void setPrimaryMail(String primaryMail) {
		this.primaryMail = primaryMail;
	}

	public String getSecondaryMail() {
		return secondaryMail;
	}

	public void setSecondaryMail(String secondaryMail) {
		this.secondaryMail = secondaryMail;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getSpecializationCode() {
		return specializationCode;
	}

	public void setSpecializationCode(String specializationCode) {
		this.specializationCode = specializationCode;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public String getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getAdmissionMode() {
		return admissionMode;
	}

	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFacRegNo() {
		return facRegNo;
	}

	public void setFacRegNo(String facRegNo) {
		this.facRegNo = facRegNo;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Integer getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Integer marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setHindiName(String hindiName) {
		this.hindiName = hindiName;
	}

	public String getHindiName() {
		return hindiName;
	}

	public void setFatherHindiName(String fatherHindiName) {
		this.fatherHindiName = fatherHindiName;
	}

	public String getFatherHindiName() {
		return fatherHindiName;
	}

	public void setMotherHindiName(String motherHindiName) {
		this.motherHindiName = motherHindiName;
	}

	public String getMotherHindiName() {
		return motherHindiName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
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

	/**
	 * @return the rangeFrom
	 */
	public int getRangeFrom() {
		return rangeFrom;
	}

	/**
	 * @param rangeFrom the rangeFrom to set
	 */
	public void setRangeFrom(int rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	/**
	 * @return the rangeTo
	 */
	public int getRangeTo() {
		return rangeTo;
	}

	/**
	 * @param rangeTo the rangeTo to set
	 */
	public void setRangeTo(int rangeTo) {
		this.rangeTo = rangeTo;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
