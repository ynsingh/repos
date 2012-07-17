/**
 * @(#) SystemTableTwoController.java
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
package in.ac.dei.edrp.cms.domain.reportgeneration;

import javax.print.PrintService;

public class ProgressCardInfo {
	
	
	private String entityId;
	private String entityName;
	private String universityCode;
	private String programId;
	private String programName;
	private String programCode;
	private String branchId;
	private String branchName;
	private String switchNumber;
	private String switchRuleFormula;
	private String sequenceNumber;
	private String firstSemesterId;
	private String finalSemesterCode;
	private String secondSemesterId;
	private String insertSemester;
	private String inSemester;
	private String outSemester;
	private String semesterSequence; 
	private String semesterGroup;
	private String specializationId;
	private String SpecializationName;
	private String semesterId;
	private String semesterName;
	private String semesterStartDate;
	private String semesterEndDate;
	private String programCourseKey;
	private String status; 
	private String enrollmentNumber;
	private String rollNumber;
	private String modeOfEntry;
	private String progressCardType;
	private String name;
	private String sessionStartDate;
	private String sessionEndDate;
	private String courseCode;
	private String studentStatus;
	private String courseName;
	private String totalMarksInternal;
	private String totalMarksExternal;
	private String totalMarks;
	private String courseClassification;
	private String studentInternal;
	private String studentTotalMarks;
	private String credits;
	private String totalCredit;
	private String studentExternal;
	private String rollNoForDetail;
	private String printType; 
	private String totalTheoryCredits;
	private String totalPracticalCredits;
	private String weightTheory;
	private String weightPractical;
	private String totalAggregate;
	private String remark;
	private String programStatus;
	private String ruleCodeOne;
	private String ruleCodeTwo;
	private String ruleCodeThree;
	private String attemptNumber;
	private String whatToPrint;
	private int noOfStudents;
	private int noOfPdfGenerated;
	private String pdfGenerated;
	private String reportType;
	private String session;
	private String errorCode;	
	private String fileName;
	private PrintService selectedService;	
	private String division;
	private String cummulativeTheoryCgpa;
	private String cummulativePracticalCgpa;
	private String cgpa;
	private String sgpa;
	private String theoryDivision;
	private String practicalDivision;
    private String genAttempt;
	
	
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
		return SpecializationName;
	}
	public void setSpecializationName(String specializationName) {
		SpecializationName = specializationName;
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
	public String getProgramCourseKey() {
		return programCourseKey;
	}
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTotalMarksInternal() {
		return totalMarksInternal;
	}
	public void setTotalMarksInternal(String totalMarksInternal) {
		this.totalMarksInternal = totalMarksInternal;
	}
	public String getTotalMarksExternal() {
		return totalMarksExternal;
	}
	public void setTotalMarksExternal(String totalMarksExternal) {
		this.totalMarksExternal = totalMarksExternal;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getCourseClassification() {
		return courseClassification;
	}
	public void setCourseClassification(String courseClassification) {
		this.courseClassification = courseClassification;
	}
	public String getStudentInternal() {
		return studentInternal;
	}
	public void setStudentInternal(String studentInternal) {
		this.studentInternal = studentInternal;
	}
	public String getStudentTotalMarks() {
		return studentTotalMarks;
	}
	public void setStudentTotalMarks(String studentTotalMarks) {
		this.studentTotalMarks = studentTotalMarks;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public String getStudentExternal() {
		return studentExternal;
	}
	public void setStudentExternal(String studentExternal) {
		this.studentExternal = studentExternal;
	}
	public String getRollNoForDetail() {
		return rollNoForDetail;
	}
	public void setRollNoForDetail(String rollNoForDetail) {
		this.rollNoForDetail = rollNoForDetail;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getTotalTheoryCredits() {
		return totalTheoryCredits;
	}
	public void setTotalTheoryCredits(String totalTheoryCredits) {
		this.totalTheoryCredits = totalTheoryCredits;
	}
	public String getTotalPracticalCredits() {
		return totalPracticalCredits;
	}
	public void setTotalPracticalCredits(String totalPracticalCredits) {
		this.totalPracticalCredits = totalPracticalCredits;
	}
	public String getWeightTheory() {
		return weightTheory;
	}
	public void setWeightTheory(String weightTheory) {
		this.weightTheory = weightTheory;
	}
	public String getWeightPractical() {
		return weightPractical;
	}
	public void setWeightPractical(String weightPractical) {
		this.weightPractical = weightPractical;
	}
	public String getTotalAggregate() {
		return totalAggregate;
	}
	public void setTotalAggregate(String totalAggregate) {
		this.totalAggregate = totalAggregate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getModeOfEntry() {
		return modeOfEntry;
	}
	public void setModeOfEntry(String modeOfEntry) {
		this.modeOfEntry = modeOfEntry;
	}
	public String getSwitchNumber() {
		return switchNumber;
	}
	public void setSwitchNumber(String switchNumber) {
		this.switchNumber = switchNumber;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getInSemester() {
		return inSemester;
	}
	public void setInSemester(String inSemester) {
		this.inSemester = inSemester;
	}
	public String getOutSemester() {
		return outSemester;
	}
	public void setOutSemester(String outSemester) {
		this.outSemester = outSemester;
	}
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	public String getSemesterGroup() {
		return semesterGroup;
	}
	public void setSemesterGroup(String semesterGroup) {
		this.semesterGroup = semesterGroup;
	}
	public String getRuleCodeOne() {
		return ruleCodeOne;
	}
	public void setRuleCodeOne(String ruleCodeOne) {
		this.ruleCodeOne = ruleCodeOne;
	}
	public String getRuleCodeTwo() {
		return ruleCodeTwo;
	}
	public void setRuleCodeTwo(String ruleCodeTwo) {
		this.ruleCodeTwo = ruleCodeTwo;
	}
	public String getRuleCodeThree() {
		return ruleCodeThree;
	}
	public void setRuleCodeThree(String ruleCodeThree) {
		this.ruleCodeThree = ruleCodeThree;
	}
	public String getFirstSemesterId() {
		return firstSemesterId;
	}
	public void setFirstSemesterId(String firstSemesterId) {
		this.firstSemesterId = firstSemesterId;
	}
	public String getSecondSemesterId() {
		return secondSemesterId;
	}
	public void setSecondSemesterId(String secondSemesterId) {
		this.secondSemesterId = secondSemesterId;
	}
	public String getAttemptNumber() {
		return attemptNumber;
	}
	public void setAttemptNumber(String attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	public String getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}
	public String getSwitchRuleFormula() {
		return switchRuleFormula;
	}
	public void setSwitchRuleFormula(String switchRuleFormula) {
		this.switchRuleFormula = switchRuleFormula;
	}
	public String getInsertSemester() {
		return insertSemester;
	}
	public void setInsertSemester(String insertSemester) {
		this.insertSemester = insertSemester;
	}
	public String getSemesterSequence() {
		return semesterSequence;
	}
	public void setSemesterSequence(String semesterSequence) {
		this.semesterSequence = semesterSequence;
	}
	public String getProgressCardType() {
		return progressCardType;
	}
	public void setProgressCardType(String progressCardType) {
		this.progressCardType = progressCardType;
	}
	public String getFinalSemesterCode() {
		return finalSemesterCode;
	}
	public void setFinalSemesterCode(String finalSemesterCode) {
		this.finalSemesterCode = finalSemesterCode;
	}
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	public String getWhatToPrint() {
		return whatToPrint;
	}
	public void setWhatToPrint(String whatToPrint) {
		this.whatToPrint = whatToPrint;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public int getNoOfPdfGenerated() {
		return noOfPdfGenerated;
	}
	public void setNoOfPdfGenerated(int noOfPdfGenerated) {
		this.noOfPdfGenerated = noOfPdfGenerated;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getPdfGenerated() {
		return pdfGenerated;
	}
	public void setPdfGenerated(String pdfGenerated) {
		this.pdfGenerated = pdfGenerated;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public PrintService getSelectedService() {
		return selectedService;
	}
	public void setSelectedService(PrintService selectedService) {
		this.selectedService = selectedService;
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
	 * @return the cummulativeTheoryCgpa
	 */
	public String getCummulativeTheoryCgpa() {
		return cummulativeTheoryCgpa;
	}
	/**
	 * @param cummulativeTheoryCgpa the cummulativeTheoryCgpa to set
	 */
	public void setCummulativeTheoryCgpa(String cummulativeTheoryCgpa) {
		this.cummulativeTheoryCgpa = cummulativeTheoryCgpa;
	}
	/**
	 * @return the cummulativePracticalCgpa
	 */
	public String getCummulativePracticalCgpa() {
		return cummulativePracticalCgpa;
	}
	/**
	 * @param cummulativePracticalCgpa the cummulativePracticalCgpa to set
	 */
	public void setCummulativePracticalCgpa(String cummulativePracticalCgpa) {
		this.cummulativePracticalCgpa = cummulativePracticalCgpa;
	}
	/**
	 * @return the cgpa
	 */
	public String getCgpa() {
		return cgpa;
	}
	/**
	 * @param cgpa the cgpa to set
	 */
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
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
	 * @param sgpa the sgpa to set
	 */
	public void setSgpa(String sgpa) {
		this.sgpa = sgpa;
	}
	/**
	 * @return the sgpa
	 */
	public String getSgpa() {
		return sgpa;
	}
	/**
	 * @param genAttempt the genAttempt to set
	 */
	public void setGenAttempt(String genAttempt) {
		this.genAttempt = genAttempt;
	}
	/**
	 * @return the genAttempt
	 */
	public String getGenAttempt() {
		return genAttempt;
	}
}



