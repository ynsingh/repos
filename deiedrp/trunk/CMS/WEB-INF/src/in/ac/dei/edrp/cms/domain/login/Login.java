/**
 * @(#) Login.java
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
package in.ac.dei.edrp.cms.domain.login;

public class Login {
	/** declaring private variables */
	private String universityId;
	private String universityName;
    private String userName;
    private String userId;
	private String password;
	private String lastLogin;
	private String status;
	private String userGroupId;
	private String userGroupName;
	private String startDate;
	private String endDate;
	private String menuId;
	private String menuName;
	private String menuItemId;
	private String menuItemName;
	private String newPassword;
	private String nickName;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	private String phone;
	private String otherPhone;
	private String fax;
	private String application;
//updated for link
	private String sessionStartDate;
	private String enrollmentPeriod;
	private String regStartDate;
	private String enrollExtendDays;
	private String regDays;
	private String regExtDays;
	private int maxLogins;
	private int count;
	private String value;
	private String dummyFlagOne;
	private String componentCode;
	private String lastModifiedDate;
	private int attempt;
	private String sequence;
	private String oldPassword;
	
	public int getMaxLogins() {
		return maxLogins;
	}

	public void setMaxLogins(int maxLogins) {
		this.maxLogins = maxLogins;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	/** defining getter and setter for getting and setting values of private variables */
    public String getUniversityId() {
		return universityId;
	}
	
    public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	
    
    public String getUserName() {
		return userName;
	}
	
    public void setUserName(String userName) {
		this.userName = userName;
	}
	
    
    public String getUserId() {
		return userId;
	}
	
    public void setUserId(String userId) {
		this.userId = userId;
	}
	
    
    public String getPassword() {
		return password;
	}
	
    public void setPassword(String password) {
		this.password = password;
	}
	
    
    public String getLastLogin() {
		return lastLogin;
	}
	
    public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
    
    public String getStatus() {
		return status;
	}
	
    public void setStatus(String status) {
		this.status = status;
	}
	
    
    public String getUserGroupId() {
		return userGroupId;
	}
	
    public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
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
	 * @return the enrollmentPeriod
	 */
	public String getEnrollmentPeriod() {
		return enrollmentPeriod;
	}

	/**
	 * @param enrollmentPeriod the enrollmentPeriod to set
	 */
	public void setEnrollmentPeriod(String enrollmentPeriod) {
		this.enrollmentPeriod = enrollmentPeriod;
	}

	/**
	 * @return the regStartDate
	 */
	public String getRegStartDate() {
		return regStartDate;
	}

	/**
	 * @param regStartDate the regStartDate to set
	 */
	public void setRegStartDate(String regStartDate) {
		this.regStartDate = regStartDate;
	}

	/**
	 * @return the enrollExtendDays
	 */
	public String getEnrollExtendDays() {
		return enrollExtendDays;
	}

	/**
	 * @param enrollExtendDays the enrollExtendDays to set
	 */
	public void setEnrollExtendDays(String enrollExtendDays) {
		this.enrollExtendDays = enrollExtendDays;
	}

	/**
	 * @return the regDays
	 */
	public String getRegDays() {
		return regDays;
	}

	/**
	 * @param regDays the regDays to set
	 */
	public void setRegDays(String regDays) {
		this.regDays = regDays;
	}

	/**
	 * @return the regExtDays
	 */
	public String getRegExtDays() {
		return regExtDays;
	}

	/**
	 * @param regExtDays the regExtDays to set
	 */
	public void setRegExtDays(String regExtDays) {
		this.regExtDays = regExtDays;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the dummyFlagOne
	 */
	public String getDummyFlagOne() {
		return dummyFlagOne;
	}

	/**
	 * @param dummyFlagOne the dummyFlagOne to set
	 */
	public void setDummyFlagOne(String dummyFlagOne) {
		this.dummyFlagOne = dummyFlagOne;
	}

	/**
	 * @return the componentCode
	 */
	public String getComponentCode() {
		return componentCode;
	}

	/**
	 * @param componentCode the componentCode to set
	 */
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the attempt
	 */
	public int getAttempt() {
		return attempt;
	}

	/**
	 * @param attempt the attempt to set
	 */
	public void setAttempt(int attempt) {
		this.attempt = attempt;
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
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
