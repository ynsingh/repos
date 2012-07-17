/*
 * @(#) ActivityMaster.java
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

package in.ac.dei.edrp.cms.domain.programgroup;

/**
 * This is the getter setter class 
 * @author Ankit Jain
 * @date 26 April 2011
 * @version 1.0
 */
public class ProgramGroup {
	
	private String entityId;
	private String entityName;
	private String programCourseKey;
	private String universityId;
	private String semesterStartDate;
	private String semesterEndDate;
	private String processName;
	private String processCode;
	private String activityName;
	private String activityCode;
	private String activitySequence;
	private String programId;
	private String programName;
	private String programCode;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterCode;
	private String semesterName;
	private Integer semesterSequence;
	private String semesterStatus;
	private String creatorId;
	private String modifierId;
	private String status;
	private String size;
	private String processActivityStartDate;
	private String processActivityEndDate;
	private String courseCode;
	private String courseName;
	private String employeeId;
	private String employeeCode;
	private String employeeName;
	private String sessionStartDate;
	private String sessionEndDate;
	private String groupCode;
	private String groupDescription;
	private String subgroupCode;
	private String subgroupDescription;
	private String minimumSelection;
	private String maximumSelection;
	private String conditionalGroup;
	private String groupOrder;
	private String linkedMinimumSelection;
	private String linkedMaximumSelection;
	private String linkedGroup;
	private String linkedGroupCode;
	private String linkedGroupDescription;
	private String groupRule;
	private String groupRuleName;
	
	
	

	/**
	 * @return the groupRuleName
	 */
	public String getGroupRuleName() {
		return groupRuleName;
	}

	/**
	 * @param groupRuleName the groupRuleName to set
	 */
	public void setGroupRuleName(String groupRuleName) {
		this.groupRuleName = groupRuleName;
	}

	/**
	 * @return the groupRule
	 */
	public String getGroupRule() {
		return groupRule;
	}

	/**
	 * @param groupRule the groupRule to set
	 */
	public void setGroupRule(String groupRule) {
		this.groupRule = groupRule;
	}
	
	/**
	 * @return the linkedGroupCode
	 */
	public String getLinkedGroupCode() {
		return linkedGroupCode;
	}

	/**
	 * @param linkedGroupCode the linkedGroupCode to set
	 */
	public void setLinkedGroupCode(String linkedGroupCode) {
		this.linkedGroupCode = linkedGroupCode;
	}

	/**
	 * @return the linkedGroupDescription
	 */
	public String getLinkedGroupDescription() {
		return linkedGroupDescription;
	}

	/**
	 * @param linkedGroupDescription the linkedGroupDescription to set
	 */
	public void setLinkedGroupDescription(String linkedGroupDescription) {
		this.linkedGroupDescription = linkedGroupDescription;
	}

	/**
	 * @return the linkedMinimumSelection
	 */
	public String getLinkedMinimumSelection() {
		return linkedMinimumSelection;
	}

	/**
	 * @param linkedMinimumSelection the linkedMinimumSelection to set
	 */
	public void setLinkedMinimumSelection(String linkedMinimumSelection) {
		this.linkedMinimumSelection = linkedMinimumSelection;
	}

	/**
	 * @return the linkedMaximumSelection
	 */
	public String getLinkedMaximumSelection() {
		return linkedMaximumSelection;
	}

	/**
	 * @param linkedMaximumSelection the linkedMaximumSelection to set
	 */
	public void setLinkedMaximumSelection(String linkedMaximumSelection) {
		this.linkedMaximumSelection = linkedMaximumSelection;
	}

	/**
	 * @return the linkedGroup
	 */
	public String getLinkedGroup() {
		return linkedGroup;
	}

	/**
	 * @param linkedGroup the linkedGroup to set
	 */
	public void setLinkedGroup(String linkedGroup) {
		this.linkedGroup = linkedGroup;
	}

	/**
	 * @return the groupOrder
	 */
	public String getGroupOrder() {
		return groupOrder;
	}

	/**
	 * @param groupOrder the groupOrder to set
	 */
	public void setGroupOrder(String groupOrder) {
		this.groupOrder = groupOrder;
	}

	/**
	 * @return the conditionalGroup
	 */
	public String getConditionalGroup() {
		return conditionalGroup;
	}

	/**
	 * @param conditionalGroup the conditionalGroup to set
	 */
	public void setConditionalGroup(String conditionalGroup) {
		this.conditionalGroup = conditionalGroup;
	}

	/**
	 * @return the minimumSelection
	 */
	public String getMinimumSelection() {
		return minimumSelection;
	}

	/**
	 * @param minimumSelection the minimumSelection to set
	 */
	public void setMinimumSelection(String minimumSelection) {
		this.minimumSelection = minimumSelection;
	}

	/**
	 * @return the maximumSelection
	 */
	public String getMaximumSelection() {
		return maximumSelection;
	}

	/**
	 * @param maximumSelection the maximumSelection to set
	 */
	public void setMaximumSelection(String maximumSelection) {
		this.maximumSelection = maximumSelection;
	}

	/**
	 * @return the subGroupCode
	 */
	public String getSubgroupCode() {
		return subgroupCode;
	}

	/**
	 * @param subgroupCode the subGroupCode to set
	 */
	public void setSubgroupCode(String subgroupCode) {
		this.subgroupCode = subgroupCode;
	}

	/**
	 * @return the subgroupDescription
	 */
	public String getSubgroupDescription() {
		return subgroupDescription;
	}

	/**
	 * @param subgroupDescription the subgroupDescription to set
	 */
	public void setSubgroupDescription(String subgroupDescription) {
		this.subgroupDescription = subgroupDescription;
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
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @param groupDescription the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	
	public String getProcessActivityStartDate() {
		return processActivityStartDate;
	}

	public void setProcessActivityStartDate(String processActivityStartDate) {
		this.processActivityStartDate = processActivityStartDate;
	}

	public String getProcessActivityEndDate() {
		return processActivityEndDate;
	}

	public void setProcessActivityEndDate(String processActivityEndDate) {
		this.processActivityEndDate = processActivityEndDate;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public Integer getSemesterSequence() {
		return semesterSequence;
	}

	public void setSemesterSequence(Integer semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	public String getSemesterStatus() {
		return semesterStatus;
	}

	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
	public String getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getActivitySequence() {
		return activitySequence;
	}

	public void setActivitySequence(String activitySequence) {
		this.activitySequence = activitySequence;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
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


	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	
	
}
