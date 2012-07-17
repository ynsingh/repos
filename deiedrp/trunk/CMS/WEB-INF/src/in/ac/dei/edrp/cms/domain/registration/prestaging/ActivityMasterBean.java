/*
 * @(#) ActivityMasterBean.java
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
package in.ac.dei.edrp.cms.domain.registration.prestaging;

import java.util.List;

public class ActivityMasterBean {

	private String universityId;

	private String entityId;
	private String programId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private List<ProcessList> processList;
	private List<ActivityListOfProcess> activityList;

	private String branchId;
	private String specializationId;
	private String processName;
	private String processId;
	private String activityName;
	private String activityId;
	private String status;

	private String sessionStartDate;
	private String sessionEndDate;

	private String userId;
	private int processCount;

	public ActivityMasterBean(String processName, String activityName,
			String entityId, String programId, String branchId,
			String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate, String status) {
		super();
		this.processName = processName;
		this.activityName = activityName;
		this.entityId = entityId;
		this.programId = programId;

		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;

		this.status = status;
	}

	public ActivityMasterBean(String processName, String activityName,
			String entityId, String programId, String branchId,
			String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate) {
		super();
		this.processName = processName;
		this.activityName = activityName;
		this.entityId = entityId;
		this.programId = programId;

		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;

	}

	public ActivityMasterBean(String entityId, String programId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		// this.admissionNormalMode = admissionNormalMode;
		// this.admissionSwitchMode = admissionSwitchMode;
	}

	public ActivityMasterBean(String universityId, String processId,
			String activityId, String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate, String status,
			String userId, String sessionStartDate, String sessionEndDate,
			int processCount) {

		this.universityId = universityId;
		this.processId = processId;
		this.activityId = activityId;
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.status = status;
		this.userId = userId;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.processCount = processCount;
	}

	public ActivityMasterBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
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

	public List<ProcessList> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessList> processList) {
		this.processList = processList;
	}

	public List<ActivityListOfProcess> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityListOfProcess> activityList) {
		this.activityList = activityList;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getProcessCount() {
		return processCount;
	}

	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}

}
