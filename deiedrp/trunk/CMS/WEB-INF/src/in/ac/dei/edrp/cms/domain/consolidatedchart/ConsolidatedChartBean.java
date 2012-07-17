/*
 * @(#) ConsolidatedChartBean.java
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
package in.ac.dei.edrp.cms.domain.consolidatedchart;

/**
 * this is bean class for consolidated chart
 * 
 * @version 1.0 8 Sept 2011
 * @author MOHD AMIR
 */
public class ConsolidatedChartBean {
	/** declaring private variables */
	private String rollNo;
	private String enrollmentNo;
	private String name;
	private String category;
	private String gender;
	private String attemptNo;
	private String weighted;
	private String internal;
	private String external;
	private String totalMarks;
	private String internalGr;
	private String externalGr;
	private String finalGr;
	private String result;
	private String entity;
	private String program;
	private String branch;
	private String specialization;
	private String semester;
	private String entityCode;
	private String programCode;
	private String branchCode;
	private String specializationCode;
	private String semesterCode;
	private String sessionStartDate;
	private String sessionEndDate;
	private String courseCode;
	private String[] percentages;
	private String semSeqNo;
	private String sgpa;
	private String resultSystem;
	private String componentId;
	private String componentDescription;
	private String groupCode;

	/** defining setter and getter method for private variable **/
	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAttemptNo() {
		return attemptNo;
	}

	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}

	public String getWeighted() {
		return weighted;
	}

	public void setWeighted(String weighted) {
		this.weighted = weighted;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	public String getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getSpecializationCode() {
		return specializationCode;
	}

	public void setSpecializationCode(String specializationCode) {
		this.specializationCode = specializationCode;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
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

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setPercentages(String[] percentages) {
		this.percentages = percentages;
	}

	public String[] getPercentages() {
		return percentages;
	}

	public void setSemSeqNo(String semSeqNo) {
		this.semSeqNo = semSeqNo;
	}

	public String getSemSeqNo() {
		return semSeqNo;
	}

	public void setSgpa(String sgpa) {
		this.sgpa = sgpa;
	}

	public String getSgpa() {
		return sgpa;
	}

	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}

	public String getResultSystem() {
		return resultSystem;
	}

	public void setInternalGr(String internalGr) {
		this.internalGr = internalGr;
	}

	public String getInternalGr() {
		return internalGr;
	}

	public void setExternalGr(String externalGr) {
		this.externalGr = externalGr;
	}

	public String getExternalGr() {
		return externalGr;
	}

	public void setFinalGr(String finalGr) {
		this.finalGr = finalGr;
	}

	public String getFinalGr() {
		return finalGr;
	}
}