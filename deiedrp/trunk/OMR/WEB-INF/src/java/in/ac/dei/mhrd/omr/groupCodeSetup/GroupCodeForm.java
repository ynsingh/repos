/*
 * @(#) GroupCodeForm.java
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



package in.ac.dei.mhrd.omr.groupCodeSetup;

import org.apache.struts.validator.ValidatorForm;

/**
 * Bean class for getting and setting group code setup related data
 * @author UPASANA KULSHRESTHA
 * @date 21-08-2011
 * @version 1.0
 */

public class GroupCodeForm extends ValidatorForm{
	
	/* fromDate property*/
	private String fromDate;
	
	/* toDate property*/
	private String toDate;
	
	/* testName property*/
	private String testName;
	
	/* groupCodeId property*/
	private String groupCodeId;
	
	/* groupNameId property*/
	private String groupNameId;
	
	/* addedGroupCode property*/
	private String addedGroupCode;
	
	/* addedGroupName property*/
	private String addedGroupName;
	
	/* chk checkbox property*/
	private String chk;
	

	/**
     * @return the FromDate
     */
	public String getFromDate() {
		return fromDate;
	}
	
	/**
     * @param fromDate the fromdate to set
     */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
     * @return the toDate
     */
	public String getToDate() {
		return toDate;
	}

	/**
     * @param toDate the todate to set
     */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/**
     * @return the testName
     */
	public String getTestName() {
		return testName;
	}
	
	/**
     * @param testName the testName to set
     */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	/**
     * @return the groupCodeId
     */
	public String getGroupCodeId() {
		return groupCodeId;
	}
	
	/**
     * @param groupCodeId the groupCodeId to set
     */
	public void setGroupCodeId(String groupCodeId) {
		this.groupCodeId = groupCodeId;
	}
	
	/**
     * @return the groupNameId
     */
	public String getGroupNameId() {
		return groupNameId;
	}
	
	/**
     * @param groupNameId the groupNameId to set
     */
	public void setGroupNameId(String groupNameId) {
		this.groupNameId = groupNameId;
	}
	
	/**
     * @return the addedGroupCode
     */
	public String getAddedGroupCode() {
		return addedGroupCode;
	}
	
	/**
     * @param addedGroupCode the addedGroupCode to set
     */
	public void setAddedGroupCode(String addedGroupCode) {
		this.addedGroupCode = addedGroupCode;
	}
	
	/**
     * @return the addedGroupName
     */
	public String getAddedGroupName() {
		return addedGroupName;
	}
	
	/**
     * @param addedGroupName the addedGroupName to set
     */
	public void setAddedGroupName(String addedGroupName) {
		this.addedGroupName = addedGroupName;
	}
	
	/**
     * @return the chk
     */
	public String getChk() {
		return chk;
	}
	
	/**
     * @param chk the chk to set
     */
	public void setChk(String chk) {
		this.chk = chk;
	}
		
}
