/*
 * @(#) BuildSemesterEndProcess.java
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
package in.ac.dei.edrp.cms.domain.buildsemesterendprocess;

/**
 * This bean file is associated with Build Semester End Processes
 * @author Devendra Singhal
 * @date Dec 02 2011
 * @version 1.0
 */
public class BuildSemesterEndProcess {
	private String userId;
	private String universityId;
	private String creatorId;
	private String modifierId;
	private String semesterStartDate;
	private String semesterEndDate;
	private String nextSemStartDate;
	private String nextSemEndDate;
	private String processCode;
	private String status;
	private String periodStartDate;
	private String periodEndDate;
	private String entityId;
	private String programCourse;
	private String periodDay;
	private String exceedDay;
	private String code;
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
	 * @return the programCourse
	 */
	public String getProgramCourse() {
		return programCourse;
	}
	/**
	 * @param programCourse the programCourse to set
	 */
	public void setProgramCourse(String programCourse) {
		this.programCourse = programCourse;
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
	 * @return the periodDay
	 */
	public String getPeriodDay() {
		return periodDay;
	}
	/**
	 * @param periodDay the periodDay to set
	 */
	public void setPeriodDay(String periodDay) {
		this.periodDay = periodDay;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the period day
	 */
	public String getPeriodday() {
		return periodDay;
	}
	/**
	 * @param periodday the period day to set
	 */
	public void setPeriodday(String periodday) {
		this.periodDay = periodday;
	}
	/**
	 * @return the exceedDay
	 */
	public String getExceedDay() {
		return exceedDay;
	}
	/**
	 * @param exceedDay the exceedDay to set
	 */
	public void setExceedDay(String exceedDay) {
		this.exceedDay = exceedDay;
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
	 * @return the semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}
	/**
	 * @param semesterStartDate the semesterStartDate to set
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}
	/**
	 * @return the semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}
	/**
	 * @param semesterEndDate the semesterEndDate to set
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}
	/**
	 * @return the nextSemStartDate
	 */
	public String getNextSemStartDate() {
		return nextSemStartDate;
	}
	/**
	 * @param nextSemStartDate the nextSemStartDate to set
	 */
	public void setNextSemStartDate(String nextSemStartDate) {
		this.nextSemStartDate = nextSemStartDate;
	}
	/**
	 * @return the nextSemEndDate
	 */
	public String getNextSemEndDate() {
		return nextSemEndDate;
	}
	/**
	 * @param nextSemEndDate the nextSemEndDate to set
	 */
	public void setNextSemEndDate(String nextSemEndDate) {
		this.nextSemEndDate = nextSemEndDate;
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
	 * @return the periodStartDate
	 */
	public String getPeriodStartDate() {
		return periodStartDate;
	}
	/**
	 * @param periodStartDate the periodStartDate to set
	 */
	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	/**
	 * @return the periodEndDate
	 */
	public String getPeriodEndDate() {
		return periodEndDate;
	}
	/**
	 * @param periodEndDate the periodEndDate to set
	 */
	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	
	
}
