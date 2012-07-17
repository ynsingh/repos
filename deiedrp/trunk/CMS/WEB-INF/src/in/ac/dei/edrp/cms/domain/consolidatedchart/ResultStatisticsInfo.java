/*
 * @(#) ResultStatisticsInfo.java
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
package in.ac.dei.edrp.cms.domain.consolidatedchart;

/**
 * this is bean class for result statistics
 * 
 * @version 1.0 8 Sept 2011
 * @author MOHD AMIR
 */
public class ResultStatisticsInfo {
	/** declaring private variables */
	private String universityId;
	private String entityCode;
	private String entityId;
	private String entityName;
	private String programCode;
	private String programId;
	private String programName;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterId;
	private String semesterName;
	private String sessionStartDate;
	private String sessionEndDate;
	private String semesterStartDate;
	private String semesterEndDate;
	private String gender;
	private String enrolled;
	private String appeared;
	private String ufm;
	private String incomplete;
	private String passed;
	private String remedial;
	private String failedAt1;
	private String failedAt2;
	private String passPercentage;
	private String fstDic;
	private String fstDiv;
	private String scndDiv;
	private String programCourseKey;
	private String semSequenceNo;
	private String status;
	private String attemptNo;
	private String count;
	private String cgpaL;
	private String cgpaU;
	private Integer semesterWise;
	private String level;
	private String category;

	/** defining setter and getter method for private variable **/
	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
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

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	public String getAppeared() {
		return appeared;
	}

	public void setAppeared(String appeared) {
		this.appeared = appeared;
	}

	public String getUfm() {
		return ufm;
	}

	public void setUfm(String ufm) {
		this.ufm = ufm;
	}

	public String getIncomplete() {
		return incomplete;
	}

	public void setIncomplete(String incomplete) {
		this.incomplete = incomplete;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public String getRemedial() {
		return remedial;
	}

	public void setRemedial(String remedial) {
		this.remedial = remedial;
	}

	public String getFailedAt1() {
		return failedAt1;
	}

	public void setFailedAt1(String failedAt1) {
		this.failedAt1 = failedAt1;
	}

	public String getFailedAt2() {
		return failedAt2;
	}

	public void setFailedAt2(String failedAt2) {
		this.failedAt2 = failedAt2;
	}

	public String getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(String passPercentage) {
		this.passPercentage = passPercentage;
	}

	public String getFstDic() {
		return fstDic;
	}

	public void setFstDic(String fstDic) {
		this.fstDic = fstDic;
	}

	public String getFstDiv() {
		return fstDiv;
	}

	public void setFstDiv(String fstDiv) {
		this.fstDiv = fstDiv;
	}

	public String getScndDiv() {
		return scndDiv;
	}

	public void setScndDiv(String scndDiv) {
		this.scndDiv = scndDiv;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	public String getSemSequenceNo() {
		return semSequenceNo;
	}

	public void setSemSequenceNo(String semSequenceNo) {
		this.semSequenceNo = semSequenceNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttemptNo() {
		return attemptNo;
	}

	public void setAttemptNo(String attemptNo) {
		this.attemptNo = attemptNo;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCgpaL() {
		return cgpaL;
	}

	public void setCgpaL(String cgpaL) {
		this.cgpaL = cgpaL;
	}

	public String getCgpaU() {
		return cgpaU;
	}

	public void setCgpaU(String cgpaU) {
		this.cgpaU = cgpaU;
	}

	public void setSemesterWise(Integer semesterWise) {
		this.semesterWise = semesterWise;
	}

	public Integer getSemesterWise() {
		return semesterWise;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	/**
	 * @param semesterStartDate the semesterStartDate to set
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	/**
	 * @return the semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	/**
	 * @param semesterEndDate the semesterEndDate to set
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	/**
	 * @return the semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}
}