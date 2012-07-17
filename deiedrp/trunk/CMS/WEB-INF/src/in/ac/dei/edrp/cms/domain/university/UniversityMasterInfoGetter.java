/*
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
package in.ac.dei.edrp.cms.domain.university;


/**
 * This bean file is associated with
 * university master setup.
 * @author Ashish
 * @date 9 Feb 2011
 * @version 1.0
 */
public class UniversityMasterInfoGetter {
    private String userId;
    private String universityCode;
    private String universityName;
    private String sessionStartDate;
    private String sessionEndDate;
    private String currentStatus;
    private String universityAddress;
    private String universityCity;
    private String universityState;
    private String universityPincode;
    private String universityPhoneNumber;
    private String universityOtherPhoneNumber;
    private String universityFaxNumber;
    private String universityInsertTime;
    private String modificationTime;
    private String universityCreatorID;
    private String modifierID;
    private String nickName;
    private String flag;
    private String componentId;
    private String componentDescription;
    private String countryName;

    /**
     * default constructor
     */
    public UniversityMasterInfoGetter() {
    }
    
    

    public String getCountryName() {
		return countryName;
	}



	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
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

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public String getUniversityCity() {
        return universityCity;
    }

    public void setUniversityCity(String universityCity) {
        this.universityCity = universityCity;
    }

    public String getUniversityState() {
        return universityState;
    }

    public void setUniversityState(String universityState) {
        this.universityState = universityState;
    }

    public String getUniversityPincode() {
        return universityPincode;
    }

    public void setUniversityPincode(String universityPincode) {
        this.universityPincode = universityPincode;
    }

    public String getUniversityPhoneNumber() {
        return universityPhoneNumber;
    }

    public void setUniversityPhoneNumber(String universityPhoneNumber) {
        this.universityPhoneNumber = universityPhoneNumber;
    }

    public String getUniversityOtherPhoneNumber() {
        return universityOtherPhoneNumber;
    }

    public void setUniversityOtherPhoneNumber(String universityOtherPhoneNumber) {
        this.universityOtherPhoneNumber = universityOtherPhoneNumber;
    }

    public String getUniversityFaxNumber() {
        return universityFaxNumber;
    }

    public void setUniversityFaxNumber(String universityFaxNumber) {
        this.universityFaxNumber = universityFaxNumber;
    }

    public String getUniversityInsertTime() {
        return universityInsertTime;
    }

    public void setUniversityInsertTime(String universityInsertTime) {
        this.universityInsertTime = universityInsertTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getUniversityCreatorID() {
        return universityCreatorID;
    }

    public void setUniversityCreatorID(String universityCreatorID) {
        this.universityCreatorID = universityCreatorID;
    }

    public String getModifierID() {
        return modifierID;
    }

    public void setModifierID(String modifierID) {
        this.modifierID = modifierID;
    }
}
