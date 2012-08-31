/*
 * @(#) EDEIStudentBean.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.client.EdeiAdmission;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * @version 1.0 1 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

/**
 * @author Quantum1
 *
 */
@SuppressWarnings("serial")
public class EDEIStudentBean implements Serializable {


	private String studentId;
	private String universityId;
	private String universityNickName;
	private String sessionStartDate;
	/**
	 * @return the sequenceNo
	 */
	public String getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	private String sessionEndDate;
	
	private String coursesName;
	private String coursesId;
	private String moduleId;
	private String moduleName;
	private String moduleStartDate;
	private String moduleEndDate;
	
	private String componentId;
	private String componentDescription;
	private String docId;
	private String docName;
	private String docPath;
	private List<String> courseCodeList;
	private String sequence;
	
	private String firstName;
    private String middleName;
    private String lastName;
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String dob;
    private String gender;
    private String category;
	private String categoryCode;
    private String phoneNumber;
    private String otherPhone;
    private String nationality;
    private String religion;
    private String guardian;
    private String minorityDesc;
    private String maritalStatusDesc;
    private String minority;
    private String maritalStatus;
    
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    
    private String totalMarks;
    private String marksObtained;
    private String percentage;
    private List<EDEIStudentBean> academicDataList;
    
    private String progCourseKey;
    private String entityId;
    private List<EDEIStudentBean> courseModuleList;
    
    private String fileSeparator;
    private String registrationNumber;
    private String ugPg;
	private List<EDEIStudentBean> attachmentList;
	private String userId;
	private String cosCode;
	private String degreeDescription;
	private String markOrGrade;
	private Integer count;
	private String credit;
	private String semesterStartDate;
	private String semesterEndDate;
	
	private String domainCode;
	private String domainName;
	private String programType;
	private String programId;
	private String programName;
	
	private String enrollmentNumber;
	private String rollNumber;
	private String sequenceNo;
	private String branchId;
	private String specializationId;
	private String studentStatus;
	
	/**
	 * @return the studentStatus
	 */
	public String getStudentStatus() {
		return studentStatus;
	}
	/**
	 * @param studentStatus the studentStatus to set
	 */
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the specializationId
	 */
	public String getSpecializationId() {
		return specializationId;
	}
	/**
	 * @param specializationId the specializationId to set
	 */
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}
	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}
	/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
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
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	/**
	 * @param categoryCode the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * @return the coursesName
	 */
	public String getCoursesName() {
		return coursesName;
	}
	/**
	 * @param coursesName the coursesName to set
	 */
	public void setCoursesName(String coursesName) {
		this.coursesName = coursesName;
	}
	
	/**
	 * @return the universityNickName
	 */
	public String getUniversityNickName() {
		return universityNickName;
	}
	/**
	 * @param universityNickName the universityNickName to set
	 */
	public void setUniversityNickName(String universityNickName) {
		this.universityNickName = universityNickName;
	}
	/**
	 * @return the sessionStartDate
	 */
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	/**
	 * @param sessionStartDate the sessionStartDate to set
	 */
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	/**
	 * @return the sessionEndDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	/**
	 * @param sessionEndDate the sessionEndDate to set
	 */
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	/**
	 * @return the coursesId
	 */
	public String getCoursesId() {
		return coursesId;
	}
	/**
	 * @param coursesId the coursesId to set
	 */
	public void setCoursesId(String coursesId) {
		this.coursesId = coursesId;
	}
	/**
	 * @return the universityId
	 */
	public String getUniversityId() {
		return universityId;
	}
	/**
	 * @param universityId the universityId to set
	 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}
	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the moduleStartDate
	 */
	public String getModuleStartDate() {
		return moduleStartDate;
	}
	/**
	 * @param moduleStartDate the moduleStartDate to set
	 */
	public void setModuleStartDate(String moduleStartDate) {
		this.moduleStartDate = moduleStartDate;
	}
	/**
	 * @return the moduleEndDate
	 */
	public String getModuleEndDate() {
		return moduleEndDate;
	}
	/**
	 * @param moduleEndDate the moduleEndDate to set
	 */
	public void setModuleEndDate(String moduleEndDate) {
		this.moduleEndDate = moduleEndDate;
	}
	/**
	 * @return the componentId
	 */
	public String getComponentId() {
		return componentId;
	}
	/**
	 * @param componentId the componentId to set
	 */
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	/**
	 * @return the componentDescription
	 */
	public String getComponentDescription() {
		return componentDescription;
	}
	/**
	 * @param componentdescription the componentDescription to set
	 */
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	
	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * @return the courseCodeList
	 */
	public List<String> getCourseCodeList() {
		return courseCodeList;
	}
	/**
	 * @param courseCodeList the courseCodeList to set
	 */
	public void setCourseCodeList(List<String> courseCodeList) {
		this.courseCodeList = courseCodeList;
	}
	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the fatherFirstName
	 */
	public String getFatherFirstName() {
		return fatherFirstName;
	}
	/**
	 * @param fatherFirstName the fatherFirstName to set
	 */
	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}
	/**
	 * @return the fatherMiddleName
	 */
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}
	/**
	 * @param fatherMiddleName the fatherMiddleName to set
	 */
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}
	/**
	 * @return the fatherLastName
	 */
	public String getFatherLastName() {
		return fatherLastName;
	}
	/**
	 * @param fatherLastName the fatherLastName to set
	 */
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	/**
	 * @return the motherFirstName
	 */
	public String getMotherFirstName() {
		return motherFirstName;
	}
	/**
	 * @param motherFirstName the motherFirstName to set
	 */
	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}
	/**
	 * @return the motherMiddleName
	 */
	public String getMotherMiddleName() {
		return motherMiddleName;
	}
	/**
	 * @param motherMiddleName the motherMiddleName to set
	 */
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}
	/**
	 * @return the motherLastName
	 */
	public String getMotherLastName() {
		return motherLastName;
	}
	/**
	 * @param motherLastName the motherLastName to set
	 */
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	/**
	 * @return the primaryEmail
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	/**
	 * @return the secondaryEmail
	 */
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	/**
	 * @param secondaryEmail the secondaryEmail to set
	 */
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the otherPhone
	 */
	public String getOtherPhone() {
		return otherPhone;
	}
	/**
	 * @param otherPhone the otherPhone to set
	 */
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the guardian
	 */
	public String getGuardian() {
		return guardian;
	}
	/**
	 * @param guardian the guardian to set
	 */
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	/**
	 * @return the minorityDesc
	 */
	public String getMinorityDesc() {
		return minorityDesc;
	}
	/**
	 * @param minorityDesc the minorityDesc to set
	 */
	public void setMinorityDesc(String minorityDesc) {
		this.minorityDesc = minorityDesc;
	}
	/**
	 * @return the maritalStatusDesc
	 */
	public String getMaritalStatusDesc() {
		return maritalStatusDesc;
	}
	/**
	 * @param maritalStatusDesc the maritalStatusDesc to set
	 */
	public void setMaritalStatusDesc(String maritalStatusDesc) {
		this.maritalStatusDesc = maritalStatusDesc;
	}
	/**
	 * @return the minority
	 */
	public String getMinority() {
		return minority;
	}
	/**
	 * @param minority the minority to set
	 */
	public void setMinority(String minority) {
		this.minority = minority;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the totalMarks
	 */
	public String getTotalMarks() {
		return totalMarks;
	}
	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	/**
	 * @return the marksObtained
	 */
	public String getMarksObtained() {
		return marksObtained;
	}
	/**
	 * @param marksObtained the marksObtained to set
	 */
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	/**
	 * @return the academicDataList
	 */
	public List<EDEIStudentBean> getAcademicDataList() {
		return academicDataList;
	}
	/**
	 * @param academicDataList the academicDataList to set
	 */
	public void setAcademicDataList(List<EDEIStudentBean> academicDataList) {
		this.academicDataList = academicDataList;
	}
	/**
	 * @return the progCourseKey
	 */
	public String getProgCourseKey() {
		return progCourseKey;
	}
	/**
	 * @param progCourseKey the progCourseKey to set
	 */
	public void setProgCourseKey(String progCourseKey) {
		this.progCourseKey = progCourseKey;
	}
	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return the courseModuleList
	 */
	public List<EDEIStudentBean> getCourseModuleList() {
		return courseModuleList;
	}
	/**
	 * @param courseModuleList the courseModuleList to set
	 */
	public void setCourseModuleList(List<EDEIStudentBean> courseModuleList) {
		this.courseModuleList = courseModuleList;
	}
	/**
	 * @return the docPath
	 */
	public String getDocPath() {
		return docPath;
	}
	/**
	 * @param docPath the docPath to set
	 */
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	/**
	 * @return the fileSeparator
	 */
	public String getFileSeparator() {
		return fileSeparator;
	}
	/**
	 * @param fileSeparator the fileSeparator to set
	 */
	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}
	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	/**
	 * @return the ugPg
	 */
	public String getUgPg() {
		return ugPg;
	}
	/**
	 * @param ugPg the ugPg to set
	 */
	public void setUgPg(String ugPg) {
		this.ugPg = ugPg;
	}
	/**
	 * @return the attachmentList
	 */
	public List<EDEIStudentBean> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * @param attachmentList the attachmentList to set
	 */
	public void setAttachmentList(List<EDEIStudentBean> attachmentList) {
		this.attachmentList = attachmentList;
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
	/**
	 * @return the cosCode
	 */
	public String getCosCode() {
		return cosCode;
	}
	/**
	 * @param cosCode the cosCode to set
	 */
	public void setCosCode(String cosCode) {
		this.cosCode = cosCode;
	}
	/**
	 * @return the degreeDescription
	 */
	public String getDegreeDescription() {
		return degreeDescription;
	}
	/**
	 * @param degreeDescription the degreeDescription to set
	 */
	public void setDegreeDescription(String degreeDescription) {
		this.degreeDescription = degreeDescription;
	}
	/**
	 * @return the markOrGrade
	 */
	public String getMarkOrGrade() {
		return markOrGrade;
	}
	/**
	 * @param markOrGrade the markOrGrade to set
	 */
	public void setMarkOrGrade(String markOrGrade) {
		this.markOrGrade = markOrGrade;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;
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
	 * @return the domainCode
	 */
	public String getDomainCode() {
		return domainCode;
	}
	/**
	 * @param domainCode the domainCode to set
	 */
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	/**
	 * @return the programType
	 */
	public String getProgramType() {
		return programType;
	}
	/**
	 * @param programType the programType to set
	 */
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
