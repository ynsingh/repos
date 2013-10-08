/*
 * @(#) ProgramMaster.java
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
package in.ac.dei.edrp.cms.domain.programcoursesetup;

/**
 * Creation date: 06-Jan-2011
 * The behavior of this class is as bean class.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ProgramMaster {

	private String programName;
	private String programCode;
	private String programId;
	private String branchName;
	private String branchId;
	private String semesterCode;
	private Integer semesterSequence;
	private String specializationName;
	private String specializationId;
	private String semesterStatus;
	private String programCourseKey;
	private String creatorId;
	private String modifierId;
	private String semesterName;
	private String sessionStartDate;
	private String sessionEndDate;
	private String universityId;
	private String entityId;
	private String maxCredit;
	private String minCredit;
	private String maxLecCredit;
	private String minLecCredit;
	
		
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
	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}
	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
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
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	 * @return the semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * @param semesterCode the semesterCode to set
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * @return the semesterSequence
	 */
	public Integer getSemesterSequence() {
		return semesterSequence;
	}
	/**
	 * @param semesterSequence the semesterSequence to set
	 */
	public void setSemesterSequence(Integer semesterSequence) {
		this.semesterSequence = semesterSequence;
	}
	/**
	 * @return the specializationName
	 */
	public String getSpecializationName() {
		return specializationName;
	}
	/**
	 * @param specializationName the specializationName to set
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
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
	 * @return the semesterStatus
	 */
	public String getSemesterStatus() {
		return semesterStatus;
	}
	/**
	 * @param semesterStatus the semesterStatus to set
	 */
	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}
	/**
	 * @return the programCourseKey
	 */
	public String getProgramCourseKey() {
		return programCourseKey;
	}
	/**
	 * @param programCourseKey the programCourseKey to set
	 */
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
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
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
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
	 * @return the sessionDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	/**
	 * @param sessionDate the sessionDate to set
	 */
	public void setSessionEndDate(String sessionDate) {
		this.sessionEndDate = sessionDate;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setMaxCredit(String maxCredit) {
		this.maxCredit = maxCredit;
	}
	public String getMaxCredit() {
		return maxCredit;
	}
	public void setMinCredit(String minCredit) {
		this.minCredit = minCredit;
	}
	public String getMinCredit() {
		return minCredit;
	}
	public void setMaxLecCredit(String maxLecCredit) {
		this.maxLecCredit = maxLecCredit;
	}
	public String getMaxLecCredit() {
		return maxLecCredit;
	}
	public void setMinLecCredit(String minLecCredit) {
		this.minLecCredit = minLecCredit;
	}
	public String getMinLecCredit() {
		return minLecCredit;
	}
	
	
}
