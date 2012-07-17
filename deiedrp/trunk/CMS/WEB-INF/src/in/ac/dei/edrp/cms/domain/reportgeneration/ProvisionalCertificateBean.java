/*
 * @(#)ProvisionalCertificate.java
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
package in.ac.dei.edrp.cms.domain.reportgeneration;

/**
 * This Class contains the Getter Setter methods used to Generate Provisional Certificate
 * @author Devendra Singhal
 * @date 09 Dec 2011
 * @version 1.0
 */
public class ProvisionalCertificateBean {

	private String studentName;
	private String rollNo;
	private String entityId;
	private String entityName;
	private String sesstionStartDate;
	private String sessionEndDate;
	private String cumulative;
	private String theoryPercentage;
	private String practicalPercentage;
	private String division;
	private String theoryDivision;
	private String practicalDivision;
	private String bracnhId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String programId;
	private String programName;
	private String universityId;
	private String universityName;
	private String printAggregate;
	private String status;
	private String componentDescription;
	private String code;
	private String groupCode;
	
	
	/**
	 * @return the componentDescription
	 */
	public String getComponentDescription() {
		return componentDescription;
	}
	/**
	 * @param componentDescription the componentDescription to set
	 */
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
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
	 * @return the printAggregate
	 */
	public String getPrintAggregate() {
		return printAggregate;
	}
	/**
	 * @param printAggregate the printAggregate to set
	 */
	public void setPrintAggregate(String printAggregate) {
		this.printAggregate = printAggregate;
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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the rollNo
	 */
	public String getRollNo() {
		return rollNo;
	}
	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
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
	 * @return the sesstionStartDate
	 */
	public String getSesstionStartDate() {
		return sesstionStartDate;
	}
	/**
	 * @param sesstionStartDate the sesstionStartDate to set
	 */
	public void setSesstionStartDate(String sesstionStartDate) {
		this.sesstionStartDate = sesstionStartDate;
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
	 * @return the cumulative
	 */
	public String getCumulative() {
		return cumulative;
	}
	/**
	 * @param cumulative the cumulative to set
	 */
	public void setCumulative(String cumulative) {
		this.cumulative = cumulative;
	}
	/**
	 * @return the theoryPercentage
	 */
	public String getTheoryPercentage() {
		return theoryPercentage;
	}
	/**
	 * @param theoryPercentage the theoryPercentage to set
	 */
	public void setTheoryPercentage(String theoryPercentage) {
		this.theoryPercentage = theoryPercentage;
	}
	/**
	 * @return the practicalPercentage
	 */
	public String getPracticalPercentage() {
		return practicalPercentage;
	}
	/**
	 * @param practicalPercentage the practicalPercentage to set
	 */
	public void setPracticalPercentage(String practicalPercentage) {
		this.practicalPercentage = practicalPercentage;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return the theoryDivision
	 */
	public String getTheoryDivision() {
		return theoryDivision;
	}
	/**
	 * @param theoryDivision the theoryDivision to set
	 */
	public void setTheoryDivision(String theoryDivision) {
		this.theoryDivision = theoryDivision;
	}
	/**
	 * @return the practicalDivision
	 */
	public String getPracticalDivision() {
		return practicalDivision;
	}
	/**
	 * @param practicalDivision the practicalDivision to set
	 */
	public void setPracticalDivision(String practicalDivision) {
		this.practicalDivision = practicalDivision;
	}
	/**
	 * @return the bracnhId
	 */
	public String getBracnhId() {
		return bracnhId;
	}
	/**
	 * @param bracnhId the bracnhId to set
	 */
	public void setBracnhId(String bracnhId) {
		this.bracnhId = bracnhId;
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
	
}
