/*
 * @(#) FinalSemesterResultStatisticsCategoryWise.java
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
 * @author Nupur Dixit
 * @date 12 Nov 2011
 * @version 1.0
 */
public class FinalSemesterResultStatisticsCategoryWise {
	
	private String entityId;
	private String entityCode;
	private String entityName;
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
	
	private String sem;
	private String semAll;
	private String gender;
	private String value;
	private String total;
	private String category;
		
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

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getSem() {
		return sem;
	}

	public void setSemAll(String semAll) {
		this.semAll = semAll;
	}

	public String getSemAll() {
		return semAll;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotal() {
		return total;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}	
	
}
