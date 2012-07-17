/*
 * @(#) FinalSemesterResultStatistics.java
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
 * This is the getter setter class 
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class FinalSemesterResultStatistics {
	
	private String entityId;
	private String entityCode;
	private String programCourseKey;
	private String universityId;
	private String semesterStartDate;
	private String semesterEndDate;
	private String programId;
	private String programName;
	private String programCode;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterCode;
	private String semesterName;
	private String sessionStartDate;
	private String sessionEndDate;
	
	private String sex;
	private String appeared;
	private String enrolled;
	private String passed;
	private String IDistinction;
	private String IDiv;
	private String IIDiv;
	private String pass;
	private String ufm;
	private String remedial;
	private String failedFirstAttempt;
	private String failedSecondAttempt;
	private String percent;
	private String incomplete;
	private String programCourseKey2;
	private String entityName;
	private String semesterWise;
	
	//Delay In Display Marks Report
	private String teacher;
	private String courseCode;
	private String rollNumber;
	private String evaluationId;
	private String compDisplaydays;
	private String actualDisplayDates;
	private String delayDays;
	private String reportCode;
	private String reportType;
	private String reportDescription;
	private String year;
	
	/**
	 * @return the programCourseKey2
	 */
	public String getProgramCourseKey2() {
		return programCourseKey2;
	}

	/**
	 * @param programCourseKey2 the programCourseKey2 to set
	 */
	public void setProgramCourseKey2(String programCourseKey2) {
		this.programCourseKey2 = programCourseKey2;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAppeared() {
		return appeared;
	}

	public void setAppeared(String appeared) {
		this.appeared = appeared;
	}

	public String getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	public String getPassed() {
		return passed;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}

	public String getIDistinction() {
		return IDistinction;
	}

	public void setIDistinction(String iDistinction) {
		IDistinction = iDistinction;
	}

	public String getIDiv() {
		return IDiv;
	}

	public void setIDiv(String iDiv) {
		IDiv = iDiv;
	}

	public String getIIDiv() {
		return IIDiv;
	}

	public void setIIDiv(String iIDiv) {
		IIDiv = iIDiv;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUfm() {
		return ufm;
	}

	public void setUfm(String ufm) {
		this.ufm = ufm;
	}

	public String getRemedial() {
		return remedial;
	}

	public void setRemedial(String remedial) {
		this.remedial = remedial;
	}

	public String getFailedFirstAttempt() {
		return failedFirstAttempt;
	}

	public void setFailedFirstAttempt(String failedFirstAttempt) {
		this.failedFirstAttempt = failedFirstAttempt;
	}

	public String getFailedSecondAttempt() {
		return failedSecondAttempt;
	}

	public void setFailedSecondAttempt(String failedSecondAttempt) {
		this.failedSecondAttempt = failedSecondAttempt;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getIncomplete() {
		return incomplete;
	}

	public void setIncomplete(String incomplete) {
		this.incomplete = incomplete;
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

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
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

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityName() {		
		return entityName;
	}

	/**
	 * @param semesterWise the semesterWise to set
	 */
	public void setSemesterWise(String semesterWise) {
		this.semesterWise = semesterWise;
	}

	/**
	 * @return the semesterWise
	 */
	public String getSemesterWise() {
		return semesterWise;
	}

	/**
	 * @return the teacher
	 */
	public String getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the evaluationId
	 */
	public String getEvaluationId() {
		return evaluationId;
	}

	/**
	 * @param evaluationId the evaluationId to set
	 */
	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	/**
	 * @return the compDisplaydays
	 */
	public String getCompDisplaydays() {
		return compDisplaydays;
	}

	/**
	 * @param compDisplaydays the compDisplaydays to set
	 */
	public void setCompDisplaydays(String compDisplaydays) {
		this.compDisplaydays = compDisplaydays;
	}

	/**
	 * @return the actualDisplayDates
	 */
	public String getActualDisplayDates() {
		return actualDisplayDates;
	}

	/**
	 * @param actualDisplayDates the actualDisplayDates to set
	 */
	public void setActualDisplayDates(String actualDisplayDates) {
		this.actualDisplayDates = actualDisplayDates;
	}

	/**
	 * @return the delayDays
	 */
	public String getDelayDays() {
		return delayDays;
	}

	/**
	 * @param delayDays the delayDays to set
	 */
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}

	/**
	 * @return the reportCode
	 */
	public String getReportCode() {
		return reportCode;
	}

	/**
	 * @param reportCode the reportCode to set
	 */
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the reportDescription
	 */
	public String getReportDescription() {
		return reportDescription;
	}

	/**
	 * @param reportDescription the reportDescription to set
	 */
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}	
	
}
