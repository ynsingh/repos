/*
 * @(#) FormSearchInfoGetter.java
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

package in.ac.dei.edrp.client.InternalSummarySheet;

import java.io.Serializable;

/**
 * @version 1.0 28 MAY 2012
 * @author UPASANA KULSHRESTHA
 */
@SuppressWarnings("serial")
public class FormSearchInfoGetter implements Serializable  {
	private String formName;
	private String formNumber;
	private String offeredBy;
	private String entityOfferedBy;
	private String entityCode;
	private String universityId;
	private String sessionStartDate;
	private String sessionEndDate;
	private String uniNickName;
	private String entityId;
	private String entityName;
	private String userId;
	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return the formNumber
	 */
	public String getFormNumber() {
		return formNumber;
	}
	/**
	 * @param formNumber the formNumber to set
	 */
	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}
	/**
	 * @return the offeredBy
	 */
	public String getOfferedBy() {
		return offeredBy;
	}
	/**
	 * @param offeredBy the offeredBy to set
	 */
	public void setOfferedBy(String offeredBy) {
		this.offeredBy = offeredBy;
	}
	/**
	 * @return the entityOfferedBy
	 */
	public String getEntityOfferedBy() {
		return entityOfferedBy;
	}
	/**
	 * @param entityOfferedBy the entityOfferedBy to set
	 */
	public void setEntityOfferedBy(String entityOfferedBy) {
		this.entityOfferedBy = entityOfferedBy;
	}
	/**
	 * @return the entityCode
	 */
	public String getEntityCode() {
		return entityCode;
	}
	/**
	 * @param entityCode the entityCode to set
	 */
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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
	 * @return the uniNickName
	 */
	public String getUniNickName() {
		return uniNickName;
	}
	/**
	 * @param uniNickName the uniNickName to set
	 */
	public void setUniNickName(String uniNickName) {
		this.uniNickName = uniNickName;
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
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
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
