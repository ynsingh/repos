/**
 * @(#) BuildNextSession.java
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
package in.ac.dei.edrp.cms.domain.buildnextsession;

/**
 * This bean file is associated with Build Next Session Process
 * @author Devendra Singhal
 * @date Nov 17 2011
 * @version 1.0
 */
public class BuildNextSession {
	private String sessionStartDate;
	private String sessionEndDate;
	private String universityId;
	private String nextSessionStartDate;
	private String nextSessionEndDate;
	private String status;
	private String groupCode;
	private String creatorId;
	private String modifierId;
	private String processCode;
	private String universityName;
	private String universityAdd;
	private String universityCity;
	private String universityPin;
	private String universityPhone;
	private String universityOtherPh;
	private String universityFax;
	private String universityNickName;
	private String universityState;
	private String country;
	private String dummyFlag;
	
	/**
	 * @return the dummyFlag
	 */
	public String getDummyFlag() {
		return dummyFlag;
	}
	/**
	 * @param dummyFlag the dummyFlag to set
	 */
	public void setDummyFlag(String dummyFlag) {
		this.dummyFlag = dummyFlag;
	}
	/**
	 * @return the universityName
	 */
	public String getUniversityName() {
		return universityName;
	}
	/**
	 * @param universityName the universityName to set
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	/**
	 * @return the universityAdd
	 */
	public String getUniversityAdd() {
		return universityAdd;
	}
	/**
	 * @param universityAdd the universityAdd to set
	 */
	public void setUniversityAdd(String universityAdd) {
		this.universityAdd = universityAdd;
	}
	/**
	 * @return the universityCity
	 */
	public String getUniversityCity() {
		return universityCity;
	}
	/**
	 * @param universityCity the universityCity to set
	 */
	public void setUniversityCity(String universityCity) {
		this.universityCity = universityCity;
	}
	/**
	 * @return the universityPin
	 */
	public String getUniversityPin() {
		return universityPin;
	}
	/**
	 * @param universityPin the universityPin to set
	 */
	public void setUniversityPin(String universityPin) {
		this.universityPin = universityPin;
	}
	/**
	 * @return the universityPhone
	 */
	public String getUniversityPhone() {
		return universityPhone;
	}
	/**
	 * @param universityPhone the universityPhone to set
	 */
	public void setUniversityPhone(String universityPhone) {
		this.universityPhone = universityPhone;
	}
	/**
	 * @return the universityOtherPh
	 */
	public String getUniversityOtherPh() {
		return universityOtherPh;
	}
	/**
	 * @param universityOtherPh the universityOtherPh to set
	 */
	public void setUniversityOtherPh(String universityOtherPh) {
		this.universityOtherPh = universityOtherPh;
	}
	/**
	 * @return the universityFax
	 */
	public String getUniversityFax() {
		return universityFax;
	}
	/**
	 * @param universityFax the universityFax to set
	 */
	public void setUniversityFax(String universityFax) {
		this.universityFax = universityFax;
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
	 * @return the universityState
	 */
	public String getUniversityState() {
		return universityState;
	}
	/**
	 * @param universityState the universityState to set
	 */
	public void setUniversityState(String universityState) {
		this.universityState = universityState;
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
	 * @return the processCode
	 */
	public String getProcessCode() {
		return processCode;
	}
	/**
	 * @param processCode the processCode to set
	 */
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the modifierId
	 */
	public String getModifierId() {
		return modifierId;
	}
	/**
	 * @param modifierId the modifierId to set
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	/**
	 * @return the nextSessionStartDate
	 */
	public String getNextSessionStartDate() {
		return nextSessionStartDate;
	}
	/**
	 * @param nextSessionStartDate the nextSessionStartDate to set
	 */
	public void setNextSessionStartDate(String nextSessionStartDate) {
		this.nextSessionStartDate = nextSessionStartDate;
	}
	/**
	 * @return the nextSessionEndDate
	 */
	public String getNextSessionEndDate() {
		return nextSessionEndDate;
	}
	/**
	 * @param nextSessionEndDate the nextSessionEndDate to set
	 */
	public void setNextSessionEndDate(String nextSessionEndDate) {
		this.nextSessionEndDate = nextSessionEndDate;
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
	
	
	
	

}
