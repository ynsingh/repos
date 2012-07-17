/**
 * @(#) StudentMasterInfoBean.java
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
package in.ac.dei.edrp.cms.domain.studentmaster;

import java.util.HashMap;
import java.util.Map;

/**
 * this is Server side controller class for Student Master
 * 
 * @version 1.0 24 Mar 2011
 * @author MOHD AMIR
 */
public class StudentMasterInfoBean{
	/** declaring private variables */
	private String universityId;
	private String sequenceNumber;
	private String studentId;
	private String enrollmentNumber;
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String primaryEmailId;
	private String secondaryEmailId;
	private String dateOfBirth;
	private String categoryCode;
	private String gender;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;
	private String registeredInSession;
	private String status;
	private String userId;
	private String parentEntity;
	private String addressKey;
	private String addressType;
	private String userType;
	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private String pinCode;
	private String officePhone;
	private String homePhone;
	private String otherPhone;
	private String fax;
	private String hindiName;
	private String fatherHindiName;
	private String motherHindiName;
	private String path;
	private String religion;
	private String nationality;
	private String guardian;
	private String sessionStartDate;
	private String sessionEndDate;
	
	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getGuardian() {
		return guardian;
	}


	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}


	public String getHindiName() {
		return hindiName;
	}


	public void setHindiName(String hindiName) {
		this.hindiName = hindiName;
	}


	public String getFatherHindiName() {
		return fatherHindiName;
	}


	public void setFatherHindiName(String fatherHindiName) {
		this.fatherHindiName = fatherHindiName;
	}


	public String getMotherHindiName() {
		return motherHindiName;
	}


	public void setMotherHindiName(String motherHindiName) {
		this.motherHindiName = motherHindiName;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	private Map<String,StudentMasterInfoBean> studentAddress=new HashMap<String,StudentMasterInfoBean>();
	
	/**
	 * defining constructor
	 */
	public StudentMasterInfoBean(){
		
	}
	
	
	/**
	 * defining constructor
	 */
	public StudentMasterInfoBean(Map<String,StudentMasterInfoBean> studentAddress){
		this.studentAddress=studentAddress;
	}
	
	
	
	/**
	 * defining constructor
	 */
	public StudentMasterInfoBean(String universityId,
			String enrollmentNumber, String studentId,String studentFirstName,
			String studentMiddleName, String studentLastName,
			String primaryEmailId, String secondaryEmailId, String dateOfBirth,
			String categoryCode, String gender, String fatherFirstName,
			String fatherMiddleName, String fatherLastName,
			String motherFirstName, String motherMiddleName,
			String motherLastName, String registeredInSession, String status) {
		super();
		this.universityId = universityId;
		this.studentId = studentId;
		this.enrollmentNumber = enrollmentNumber;
		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.primaryEmailId = primaryEmailId;
		this.secondaryEmailId = secondaryEmailId;
		this.dateOfBirth = dateOfBirth;
		this.categoryCode = categoryCode;
		this.gender = gender;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.registeredInSession = registeredInSession;
		this.status = status;
	}


	/**
	 * defining constructor
	 */
	public StudentMasterInfoBean(String userType,String addressLineOne,String addressLineTwo,
			String city,String state,String pinCode,String officePhone,String homePhone,String otherPhone,
			String fax){
	this.userType=userType;
	this.addressLineOne=addressLineOne;
	this.addressLineTwo=addressLineTwo;
	this.city=city;
	this.state=state;
	this.pinCode=pinCode;
	this.officePhone=officePhone;
	this.homePhone=homePhone;
	this.otherPhone=otherPhone;
	this.fax=fax;
	}
	
	/**
	 * defining getter and setter for getting and setting values of private
	 * variables
	 */
	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
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

	
	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getRegisteredInSession() {
		return registeredInSession;
	}

	public void setRegisteredInSession(String registeredInSession) {
		this.registeredInSession = registeredInSession;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
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

	public Map<String, StudentMasterInfoBean> getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Map<String, StudentMasterInfoBean> studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getParentEntity() {
		return parentEntity;
	}


	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}


	public String getSequenceNumber() {
		return sequenceNumber;
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
}