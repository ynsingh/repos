/*
 * @(#)  ProgramSearchInfoGetter.java
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

package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This bean file is associated with Program Search
 * @author UPASANA KULSHRESTHA
 * @date 18 February 2012
 * @version 1.0
 */

public class ProgramSearchInfoGetter implements IsSerializable{
	
	/** declaring private variables */
	private String universityId;
	private String universityName;
	private String programId;
	private String programName;
	private String entityId;
	private String entityName;
	private String entityLocation;
	private String programDescription;
	private String entityCode;
	private String sessionStartDate;
	private String sessionEndDate;
	private String formNumber;
	private String formName;
	private String email;
	private String count;
	private String uniNickName;
	/** defining setter and getter method for private variable **/
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityLocation() {
		return entityLocation;
	}
	public void setEntityLocation(String entityLocation) {
		this.entityLocation = entityLocation;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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
	public String getFormNumber() {
		return formNumber;
	}
	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUniNickName() {
		return uniNickName;
	}
	public void setUniNickName(String uniNickName) {
		this.uniNickName = uniNickName;
	}

}
