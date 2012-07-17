/**
 * @(#) SystemTableTwoBean.java
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
package in.ac.dei.edrp.cms.domain.systemtabletwo;

/**
 * this is bean class for SystemTableTwo
 *
 * @version 1.0 14 FEB 2011
 * @author MOHD AMIR
 */
public class SystemTableTwoBean {
	
	/** declaring private variables */
	private String groupCode;
	private String componentCode;
	private String componentDescription;
	private Integer count;
	private String universityCode;
	private String userId;
	private String active;
	private String dummyFlag1;
	private String dummyFlag2;
	private String dummyFlag3;
	private String verificationRequired;
	
	/** defining getter and setter for getting and setting values of private variables */
	public String getGroupCode() {
		return groupCode;
	}
	
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	
	public String getComponentCode() {
		return componentCode;
	}
	
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	
	public String getComponentDescription() {
		return componentDescription;
	}
	
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	public String getUniversityCode() {
		return universityCode;
	}
	
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getActive() {
		return active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
	
	
	public String getDummyFlag1() {
		return dummyFlag1;
	}
	
	public void setDummyFlag1(String dummyFlag1) {
		this.dummyFlag1 = dummyFlag1;
	}
	
	
	public String getDummyFlag2() {
		return dummyFlag2;
	}
	
	public void setDummyFlag2(String dummyFlag2) {
		this.dummyFlag2 = dummyFlag2;
	}
	
	
	public String getDummyFlag3() {
		return dummyFlag3;
	}
	
	public void setDummyFlag3(String dummyFlag3) {
		this.dummyFlag3 = dummyFlag3;
	}
	
	
	public String getVerificationRequired() {
		return verificationRequired;
	}
	
	public void setVerificationRequired(String verificationRequired) {
		this.verificationRequired = verificationRequired;
	}
}
