/**
 * @(#) PreProcessForResultBean.java
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

package in.ac.dei.edrp.cms.domain.resultprocessing;

public class PreProcessForResultBean {

	private String universityId;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String sessionStartDate;
	private String sessionEndDate;
	private int semesterSequence;
	private String programStatus;
	private String semesterStatus;
	private String courseStatus;

	private String programCourseKey;

	private String enrollmentNumber;
	private String rollNumber;

	private String courseCode;

	private String minPassMarks;

	private String studentProcessStatus;

	private double weightedPercentage;

	private double aggregateTheory;
	private double aggregatePractical;

	private double practicalCredit;
	private double theoryCredit;

	private String remarks;

	private int attemptNumber;

	private String userId;

	private double sgpa;

	private int numberOfRemedials;

	private double registeredCredit;
	
	private String resultSystem;

	private int count;

	private double registeredTheoryCreditExcludingAudit;
	private double registeredPracticalCreditExcludingAudit;
	private double sgpaTheoryPointSecured;
	private double sgpaPracticalPointSecured;
	private double cgpaTheoryPointSecured;
	private double cgpaPracticalPointSecured;
	private double earnedTheorycgpaCredit;
	private double earnedPracticalcgpaCredit;
	private double earnedTheoryAudCredit;
	private double earnedPracticalAudCredit;
	
	private double cgpa=0.0;
	
	private double theorycgpa=0.0;
	private double practicalcgpa=0.0;
	
	public double getTheorycgpa() {
		return theorycgpa;
	}

	public void setTheorycgpa(double theorycgpa) {
		this.theorycgpa = theorycgpa;
	}

	public double getPracticalcgpa() {
		return practicalcgpa;
	}

	public void setPracticalcgpa(double practicalcgpa) {
		this.practicalcgpa = practicalcgpa;
	}

	public int getSemesterSequence() {
		return semesterSequence;
	}

	public void setSemesterSequence(int semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	private double minPassGrade;

	// To get min passing marks and grade , we will this property
	private String code;
	
	private String activityId;
	private String processId;
	
	private String ProgramCompletionDate ;
	private String OutSemester ;

	public String getProgramCompletionDate() {
		return ProgramCompletionDate;
	}

	public void setProgramCompletionDate(String programCompletionDate) {
		ProgramCompletionDate = programCompletionDate;
	}

	public String getOutSemester() {
		return OutSemester;
	}

	public void setOutSemester(String outSemester) {
		OutSemester = outSemester;
	}

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

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
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

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getSemesterStatus() {
		return semesterStatus;
	}

	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getMinPassMarks() {
		return minPassMarks;
	}

	public void setMinPassMarks(String minPassMarks) {
		this.minPassMarks = minPassMarks;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStudentProcessStatus() {
		return studentProcessStatus;
	}

	public void setStudentProcessStatus(String studentProcessStatus) {
		this.studentProcessStatus = studentProcessStatus;
	}

	public double getWeightedPercentage() {
		return weightedPercentage;
	}

	public void setWeightedPercentage(double weightedPercentage) {
		this.weightedPercentage = weightedPercentage;
	}

	public double getAggregateTheory() {
		return aggregateTheory;
	}

	public void setAggregateTheory(double aggregateTheory) {
		this.aggregateTheory = aggregateTheory;
	}

	public double getAggregatePractical() {
		return aggregatePractical;
	}

	public void setAggregatePractical(double aggregatePractical) {
		this.aggregatePractical = aggregatePractical;
	}

	public double getPracticalCredit() {
		return practicalCredit;
	}

	public void setPracticalCredit(double practicalCredit) {
		this.practicalCredit = practicalCredit;
	}

	public double getTheoryCredit() {
		return theoryCredit;
	}

	public void setTheoryCredit(double theoryCredit) {
		this.theoryCredit = theoryCredit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getSgpa() {
		return sgpa;
	}

	public void setSgpa(double sgpa) {
		this.sgpa = sgpa;
	}

	public int getNumberOfRemedials() {
		return numberOfRemedials;
	}

	public void setNumberOfRemedials(int numberOfRemedials) {
		this.numberOfRemedials = numberOfRemedials;
	}

	public double getRegisteredCredit() {
		return registeredCredit;
	}

	public void setRegisteredCredit(double registeredCredit) {
		this.registeredCredit = registeredCredit;
	}

	

	public String getResultSystem() {
		return resultSystem;
	}

	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getRegisteredTheoryCreditExcludingAudit() {
		return registeredTheoryCreditExcludingAudit;
	}

	public void setRegisteredTheoryCreditExcludingAudit(
			double registeredTheoryCreditExcludingAudit) {
		this.registeredTheoryCreditExcludingAudit = registeredTheoryCreditExcludingAudit;
	}

	public double getRegisteredPracticalCreditExcludingAudit() {
		return registeredPracticalCreditExcludingAudit;
	}

	public void setRegisteredPracticalCreditExcludingAudit(
			double registeredPracticalCreditExcludingAudit) {
		this.registeredPracticalCreditExcludingAudit = registeredPracticalCreditExcludingAudit;
	}

	public double getSgpaTheoryPointSecured() {
		return sgpaTheoryPointSecured;
	}

	public void setSgpaTheoryPointSecured(double sgpaTheoryPointSecured) {
		this.sgpaTheoryPointSecured = sgpaTheoryPointSecured;
	}

	public double getSgpaPracticalPointSecured() {
		return sgpaPracticalPointSecured;
	}

	public void setSgpaPracticalPointSecured(double sgpaPracticalPointSecured) {
		this.sgpaPracticalPointSecured = sgpaPracticalPointSecured;
	}

	public double getCgpaTheoryPointSecured() {
		return cgpaTheoryPointSecured;
	}

	public void setCgpaTheoryPointSecured(double cgpaTheoryPointSecured) {
		this.cgpaTheoryPointSecured = cgpaTheoryPointSecured;
	}

	public double getCgpaPracticalPointSecured() {
		return cgpaPracticalPointSecured;
	}

	public void setCgpaPracticalPointSecured(double cgpaPracticalPointSecured) {
		this.cgpaPracticalPointSecured = cgpaPracticalPointSecured;
	}

	public double getEarnedTheorycgpaCredit() {
		return earnedTheorycgpaCredit;
	}

	public void setEarnedTheorycgpaCredit(double earnedTheorycgpaCredit) {
		this.earnedTheorycgpaCredit = earnedTheorycgpaCredit;
	}

	public double getEarnedPracticalcgpaCredit() {
		return earnedPracticalcgpaCredit;
	}

	public void setEarnedPracticalcgpaCredit(double earnedPracticalcgpaCredit) {
		this.earnedPracticalcgpaCredit = earnedPracticalcgpaCredit;
	}

	public double getEarnedTheoryAudCredit() {
		return earnedTheoryAudCredit;
	}

	public void setEarnedTheoryAudCredit(double earnedTheoryAudCredit) {
		this.earnedTheoryAudCredit = earnedTheoryAudCredit;
	}

	public double getEarnedPracticalAudCredit() {
		return earnedPracticalAudCredit;
	}

	public void setEarnedPracticalAudCredit(double earnedPracticalAudCredit) {
		this.earnedPracticalAudCredit = earnedPracticalAudCredit;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public PreProcessForResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreProcessForResultBean(String universityId, String entityId,
			String programId, String branchId, String specializationId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String sessionStartDate,
			String sessionEndDate, String programStatus, String semesterStatus,
			String programCourseKey,String activityId,String processId) {
		super();
		this.universityId = universityId;
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.programStatus = programStatus;
		this.semesterStatus = semesterStatus;
		this.programCourseKey = programCourseKey;
		this.activityId=activityId;
		this.processId=processId;
	}

	public void setMinPassGrade(double minPassGrade) {
		this.minPassGrade = minPassGrade;
	}

	public double getMinPassGrade() {
		return minPassGrade;
	}
	private String switchRule;
	private String switchType;
	private String modeOfEntry;
	private String programMarksFlag;
	private String consideringSemester;
	private String inSemester;
	private String preEntity;
	private String preProgram;
	private String preBranch;
	private String preSpecialization;
	private String preSemester;
	private String previousSemesterStartDate;
	private String previosSemesterEndDate;
	private String ruleFormula;

public String getSwitchRule() {
		return switchRule;
	}

	public void setSwitchRule(String switchRule) {
		this.switchRule = switchRule;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public String getModeOfEntry() {
		return modeOfEntry;
	}

	public void setModeOfEntry(String modeOfEntry) {
		this.modeOfEntry = modeOfEntry;
	}

	public String getProgramMarksFlag() {
		return programMarksFlag;
	}

	public void setProgramMarksFlag(String programMarksFlag) {
		this.programMarksFlag = programMarksFlag;
	}

	public String getConsideringSemester() {
		return consideringSemester;
	}

	public void setConsideringSemester(String consideringSemester) {
		this.consideringSemester = consideringSemester;
	}

	public String getInSemester() {
		return inSemester;
	}

	public void setInSemester(String inSemester) {
		this.inSemester = inSemester;
	}

	public String getPreProgram() {
		return preProgram;
	}

	public void setPreProgram(String preProgram) {
		this.preProgram = preProgram;
	}

	public String getPreBranch() {
		return preBranch;
	}

	public void setPreBranch(String preBranch) {
		this.preBranch = preBranch;
	}

	public String getPreSpecialization() {
		return preSpecialization;
	}

	public void setPreSpecialization(String preSpecialization) {
		this.preSpecialization = preSpecialization;
	}

	public String getPreSemester() {
		return preSemester;
	}

	public void setPreSemester(String preSemester) {
		this.preSemester = preSemester;
	}

	public String getPreviousSemesterStartDate() {
		return previousSemesterStartDate;
	}

	public void setPreviousSemesterStartDate(String previousSemesterStartDate) {
		this.previousSemesterStartDate = previousSemesterStartDate;
	}

	public String getPreviosSemesterEndDate() {
		return previosSemesterEndDate;
	}

	public void setPreviosSemesterEndDate(String previosSemesterEndDate) {
		this.previosSemesterEndDate = previosSemesterEndDate;
	}
	public String getRuleFormula() {
		return ruleFormula;
	}

	public void setRuleFormula(String ruleFormula) {
		this.ruleFormula = ruleFormula;
	}
	public String getPreEntity() {
		return preEntity;
	}

	public void setPreEntity(String preEntity) {
		this.preEntity = preEntity;
	}
}
