
/*
 * @(#) PersonalDetailsBean.java
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

public class PersonalDetailsBean {

	private String universityId;
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
	private String primaryEmailId;
	private String secondaryEmailId;
	private String dateOfBirth;
	private String categoryCode;
	private String gender;
	private String userId;

	private String registeredInSession;

	private String permanentAddress;
	private String permanentCity;
	private String permanentState;
	private String permanentPinCode;
	private String correspondentAddress;
	private String correspondentCity;
	private String correspondentState;
	private String correspondentPinCode;
	private String extraPhone;
	private String homePhone;
	private String otherPhone;
	private String fax;
	

	private int count;

	//Enrolment Number 
	private String nameInHindi;
	private String fatherNameInHindi;
	private String motherNameInHindi;
	private String nationality;
	private String religion;
	private String photoPath;
	private String guardianName;
	
	private String sessionStartDate;
	private String sessionEndDate;
	
	
	
	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
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

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegisteredInSession() {
		return registeredInSession;
	}

	public void setRegisteredInSession(String registeredInSession) {
		this.registeredInSession = registeredInSession;
	}

	private String entityId;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	public String getNameInHindi() {
		return nameInHindi;
	}

	public void setNameInHindi(String nameInHindi) {
		this.nameInHindi = nameInHindi;
	}

	public String getFatherNameInHindi() {
		return fatherNameInHindi;
	}

	public void setFatherNameInHindi(String fatherNameInHindi) {
		this.fatherNameInHindi = fatherNameInHindi;
	}

	public String getMotherNameInHindi() {
		return motherNameInHindi;
	}

	public void setMotherNameInHindi(String motherNameInHindi) {
		this.motherNameInHindi = motherNameInHindi;
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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
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

	public PersonalDetailsBean(String universityId, String entityId,
			String enrollmentNumber, String studentId, String studentFirstName,
			String studentMiddleName, String studentLastName,
			String fatherFirstName, String fatherMiddleName,
			String fatherLastName, String motherFirstName,
			String motherMiddleName, String motherLastName,
			String primaryEmailId, String dateOfBirth, String categoryCode,
			String gender, String userId, String registeredInSession,
			String permanentAddress, String permanentCity,
			String permanentState, String permanentPinCode,
			String correspondentAddress, String correspondentCity,
			String correspondentState, String correspondentPinCode,
			String homePhone, String extraPhone, String otherPhone, String fax,
			String nameInHindi,String fatherNameInHindi,String motherNameInHindi,String photoPath,
			String guardianName,String religion,String nationality,
			String sessionStartDate,String sessionEndDate) {
		super();
		this.universityId = universityId;
		this.enrollmentNumber = enrollmentNumber;
		this.studentId = studentId;
		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.primaryEmailId = primaryEmailId;
		this.dateOfBirth = dateOfBirth;
		this.categoryCode = categoryCode;
		this.gender = gender;
		this.userId = userId;

		this.permanentAddress = permanentAddress;
		this.permanentCity = permanentCity;
		this.permanentState = permanentState;
		this.permanentPinCode = permanentPinCode;

		this.correspondentAddress = correspondentAddress;
		this.correspondentCity = correspondentCity;
		this.correspondentState = correspondentState;
		this.correspondentPinCode = correspondentPinCode;

		this.homePhone = homePhone;
		this.otherPhone = otherPhone;
		this.extraPhone = extraPhone;
		this.fax = fax;
		
		this.nameInHindi=nameInHindi;
		this.fatherNameInHindi=fatherNameInHindi;
		this.motherNameInHindi=motherNameInHindi;
		this.photoPath=photoPath;
		this.guardianName=guardianName;
		this.religion=religion;
		this.nationality=nationality;

		this.sessionStartDate=sessionStartDate;
		this.sessionEndDate=sessionEndDate;
	}

	public PersonalDetailsBean(String universityId, String enrollmentNumber,
			String userId) {
		super();
		this.universityId = universityId;
		this.enrollmentNumber = enrollmentNumber;
		this.userId = userId;
	}

	public PersonalDetailsBean() {
		super();
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

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(String permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}

	public String getCorrespondentAddress() {
		return correspondentAddress;
	}

	public void setCorrespondentAddress(String correspondentAddress) {
		this.correspondentAddress = correspondentAddress;
	}

	public String getCorrespondentCity() {
		return correspondentCity;
	}

	public void setCorrespondentCity(String correspondentCity) {
		this.correspondentCity = correspondentCity;
	}

	public String getCorrespondentState() {
		return correspondentState;
	}

	public void setCorrespondentState(String correspondentState) {
		this.correspondentState = correspondentState;
	}

	public String getCorrespondentPinCode() {
		return correspondentPinCode;
	}

	public void setCorrespondentPinCode(String correspondentPinCode) {
		this.correspondentPinCode = correspondentPinCode;
	}

	public String getExtraPhone() {
		return extraPhone;
	}

	public void setExtraPhone(String extraPhone) {
		this.extraPhone = extraPhone;
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

}
