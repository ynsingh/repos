/*
 * @(#) InstAdminRegistrationInfo.java
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
package in.ac.dei.edrp.cms.domain.registrationForInstAndAdmin;

/**
 * This file consist of the methods used for setting up the institute admin registration 
 * setup.
 * 
 * @author Upasana 
 * @date 21	November 2012
 * @version 1.0
 */
public class InstAdminRegistrationInfo {

	private String userId;
	private String employeeFirstName;
	private String employeeLastName;
	private String designation;
	private String primaryEmailId;
	private String password;
	
	private String instituteName;
	private String instituteNickName;
	private String instituteType;
	private String instituteDomain;
	private String sessionStartDate;
	private String sessionEndDate;
	private String address;
	private String country;
	private String state;
	private String city;
	private String pinCode;
	private String officePhoneNumber;
	private String otherPhoneNumber;
	private String count;
	private String status;
	private String universityCode;
	private String componentId;
	private String componentDescription;
	private String groupCode;
	private String applicationId;
	
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
	 * @return the employeeFirstName
	 */
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	/**
	 * @param employeeFirstName the employeeFirstName to set
	 */
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	/**
	 * @return the employeeLastName
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	/**
	 * @param employeeLastName the employeeLastName to set
	 */
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the primaryEmailId
	 */
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}
	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return instituteName;
	}
	/**
	 * @param instituteName the instituteName to set
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	/**
	 * @return the instituteNickName
	 */
	public String getInstituteNickName() {
		return instituteNickName;
	}
	/**
	 * @param instituteNickName the instituteNickName to set
	 */
	public void setInstituteNickName(String instituteNickName) {
		this.instituteNickName = instituteNickName;
	}
	/**
	 * @return the instituteType
	 */
	public String getInstituteType() {
		return instituteType;
	}
	/**
	 * @param instituteType the instituteType to set
	 */
	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}
	/**
	 * @return the instituteDomain
	 */
	public String getInstituteDomain() {
		return instituteDomain;
	}
	/**
	 * @param instituteDomain the instituteDomain to set
	 */
	public void setInstituteDomain(String instituteDomain) {
		this.instituteDomain = instituteDomain;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	/**
	 * @return the officePhoneNumber
	 */
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}
	/**
	 * @param officePhoneNumber the officePhoneNumber to set
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}
	/**
	 * @return the otherPhoneNumber
	 */
	public String getOtherPhoneNumber() {
		return otherPhoneNumber;
	}
	/**
	 * @param otherPhoneNumber the otherPhoneNumber to set
	 */
	public void setOtherPhoneNumber(String otherPhoneNumber) {
		this.otherPhoneNumber = otherPhoneNumber;
	}
	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getUniversityCode() {
		return universityCode;
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
	 * @param componentDescription the componentDescription to set
	 */
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	
}
