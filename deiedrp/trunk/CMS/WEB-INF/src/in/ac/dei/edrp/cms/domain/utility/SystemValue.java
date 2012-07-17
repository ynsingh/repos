/**
 * @(#) SystemValue.java
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
package in.ac.dei.edrp.cms.domain.utility;

public class SystemValue {

	String univeristyCode;
	String code;
	String value;
	String modifierId;


	/*
	 * Ashish
	 */
	String programCourseKey;
	String entityId;
	String semesterCode;
	String semesterStartDate;
	String semesterEndDate;
	String process;
	String processCode;
	String creatorId;

	//Devendra Starts

	private String sessionStartDate;
	private String sessionEndDate;
	private String status;
	private String previousSessionStartDate;
	private String previousSessionEndDate;
	private String studentId;
	private String regRollNo;
	private String program;
	private String branch;
	private String specialization;
	private String semester;
	private String attemptNo;
	private String admissionMode;


	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the regRollNo
	 */
	public String getRegRollNo() {
		return regRollNo;
	}
	/**
	 * @param regRollNo the regRollNo to set
	 */
	public void setRegRollNo(String regRollNo) {
		this.regRollNo = regRollNo;
	}
	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}
	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}
	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * @return the attemptNo
	 */
	public String getAttemptNo() {
		return attemptNo;
	}
	/**
	 * @param attemptNo the attemptNo to set
	 */
	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}
	/**
	 * @return the admissionMode
	 */
	public String getAdmissionMode() {
		return admissionMode;
	}
	/**
	 * @param admissionMode the admissionMode to set
	 */
	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}
	/**
	 * @return the previousSessionStartDate
	 */
	public String getPreviousSessionStartDate() {
		return previousSessionStartDate;
	}
	/**
	 * @param previousSessionStartDate the previousSessionStartDate to set
	 */
	public void setPreviousSessionStartDate(String previousSessionStartDate) {
		this.previousSessionStartDate = previousSessionStartDate;
	}
	/**
	 * @return the previousSessionEndDate
	 */
	public String getPreviousSessionEndDate() {
		return previousSessionEndDate;
	}
	/**
	 * @param previousSessionEndDate the previousSessionEndDate to set
	 */
	public void setPreviousSessionEndDate(String previousSessionEndDate) {
		this.previousSessionEndDate = previousSessionEndDate;
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

	//Devendra Ends



	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getUniveristyCode() {
		return univeristyCode;
	}
	public void setUniveristyCode(String univeristyCode) {
		this.univeristyCode = univeristyCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public SystemValue(String univeristyCode, String code) {
		super();
		this.univeristyCode = univeristyCode;
		this.code = code;
	}



	public String getProgramCourseKey() {
		return programCourseKey;
	}
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getSemesterCode() {
		return semesterCode;
	}
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getSemesterStartDate() {
		return semesterStartDate;
	}
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}
	public String getSemesterEndDate() {
		return semesterEndDate;
	}
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}
	public SystemValue(){
		super();
	}



}
