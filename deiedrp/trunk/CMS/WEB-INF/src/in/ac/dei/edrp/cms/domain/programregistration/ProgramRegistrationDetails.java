/*
 * @(#) ProgramRegistrationDetails.java
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
package in.ac.dei.edrp.cms.domain.programregistration;

import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;

/**
 * Creation date: 10-Feb-2011
 * The behavior of this class is as bean class.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ProgramRegistrationDetails {

	private String universityId;
	private String programCourseKey;
	private String registrationStartDate;
	private String registrationLastDate;
	private Integer addDropPeriod;
	private String semesterStartDate;
	private String semesterEndDate;
	private String sessionStartDate;
	private String sessionEndDate;
	private String sessionDate;
	private String status;
	private String programId;
	private String semesterCode;
	private String creatorId;
	private String modifierId;
	private ProgramMaster programMaster;
	private String entityId; 
	private String entityName;

	public ProgramRegistrationDetails(){}
	//Add By Devendra
	private String processCode;
	
	
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
	 * @return the programMaster
	 */
	public ProgramMaster getProgramMaster() {
		return programMaster;
	}

	/**
	 * @param programMaster the programMaster to set
	 */
	public void setProgramMaster(ProgramMaster programMaster) {
		this.programMaster = programMaster;
	}

	/**
	 * This is the parameterized constructor
	 * @param universityId
	 * @param programCourseKey
	 * @param registrationStartDate
	 * @param registrationLastDate
	 * @param addDropPeriod
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param creatorId
	 * @param status
	 * @param modifierId
	 */

	public ProgramRegistrationDetails(String universityId,
			String programCourseKey, String registrationStartDate,
			String registrationLastDate, int addDropPeriod,
			String semesterStartDate, String semesterEndDate,
			String sessionStartDate, String sessionEndDate, String creatorId,
			String status, String modifierId, String entityId) {

		this.universityId = universityId;
		this.programCourseKey = programCourseKey;
		this.registrationStartDate = registrationStartDate;
		this.registrationLastDate = registrationLastDate;
		this.addDropPeriod = addDropPeriod;
		this.semesterStartDate =semesterStartDate;
		this.semesterEndDate =semesterEndDate;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.creatorId = creatorId;
		this.status = status;
		this.modifierId = modifierId;
		this.entityId=entityId;

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
	 * @return the registrationStartDate
	 */
	public String getRegistrationStartDate() {
		return registrationStartDate;
	}
	/**
	 * @param registrationStartDate the registrationStartDate to set
	 */
	public void setRegistrationStartDate(String registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}
	/**
	 * @return the registrationLastDate
	 */
	public String getRegistrationLastDate() {
		return registrationLastDate;
	}
	/**
	 * @param registrationLastDate the registrationLastDate to set
	 */
	public void setRegistrationLastDate(String registrationLastDate) {
		this.registrationLastDate = registrationLastDate;
	}
	/**
	 * @return the addDropPeriod
	 */
	public Integer getAddDropPeriod() {
		return addDropPeriod;
	}
	/**
	 * @param addDropPeriod the addDropPeriod to set
	 */
	public void setAddDropPeriod(Integer addDropPeriod) {
		this.addDropPeriod = addDropPeriod;
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
	 * @return the sessionDate
	 */
	public String getSessionDate() {
		return sessionDate;
	}
	/**
	 * @param sessionDate the sessionDate to set
	 */
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
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


}
