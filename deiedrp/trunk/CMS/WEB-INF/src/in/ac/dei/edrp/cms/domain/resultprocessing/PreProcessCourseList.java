/**
 * @(#) PreProcessCourseList.java
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

public class PreProcessCourseList {

	private String universityId;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String courseCode;

	private int totalInternal;
	private int totalExternal;
	private int totalMarks;

	private String rollNumber;
	private String programCourseKey;

	private String semesterStartDate;
	private String semesterEndDate;

	private double totalTorPAggregate;
	private double totalAggregate;

	private String courseClassification;
	// private String courseClassification2;

	private double credits;

	private String internalGrade;
	private double internalGradeValue;
	private String externalGrade;
	private double externalGradeValue;

	private String courseStatus;

	private String userId;

	private String resultSystem;
	
	private String studentStatus;

	// private String finalMarksOrGrade;

	private double minPassGrade;
	private double minPassMarks;

	private double internalWeight;
	private double externalWeight;

	private String courseType;
	private double obtainedMarks;
	private double obtainedGrade;
	private double earnedCredits;
	private double sgpaPointSecured;
	private double cgpaPointSecured;

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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getTotalInternal() {
		return totalInternal;
	}

	public void setTotalInternal(int totalInternal) {
		this.totalInternal = totalInternal;
	}

	public int getTotalExternal() {
		return totalExternal;
	}

	public void setTotalExternal(int totalExternal) {
		this.totalExternal = totalExternal;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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

	public double getTtotalTorPAggregate() {
		return totalTorPAggregate;
	}

	public void settotalTorPAggregate(double totalTorPAggregate) {
		this.totalTorPAggregate = totalTorPAggregate;
	}

	public double getTotalAggregate() {
		return totalAggregate;
	}

	public void setTotalAggregate(double totalAggregate) {
		this.totalAggregate = totalAggregate;
	}

	public String getCourseClassification() {
		return courseClassification;
	}

	public void setCourseClassification(String courseClassification) {
		this.courseClassification = courseClassification;
	}

	// public String getCourseClassification2() {
	// return courseClassification2;
	// }
	//
	// public void setCourseClassification2(String courseClassification2) {
	// this.courseClassification2 = courseClassification2;
	// }

	public double getTotalTorPAggregate() {
		return totalTorPAggregate;
	}

	public void setTotalTorPAggregate(double totalTorPAggregate) {
		this.totalTorPAggregate = totalTorPAggregate;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getInternalGrade() {
		return internalGrade;
	}

	public void setInternalGrade(String internalGrade) {
		this.internalGrade = internalGrade;
	}

	public double getInternalGradeValue() {
		return internalGradeValue;
	}

	public void setInternalGradeValue(double internalGradeValue) {
		this.internalGradeValue = internalGradeValue;
	}

	public String getExternalGrade() {
		return externalGrade;
	}

	public void setExternalGrade(String externalGrade) {
		this.externalGrade = externalGrade;
	}

	public double getExternalGradeValue() {
		return externalGradeValue;
	}

	public void setExternalGradeValue(double externalGradeValue) {
		this.externalGradeValue = externalGradeValue;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResultSystem() {
		return resultSystem;
	}

	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}

	// public String getFinalMarksOrGrade() {
	// return finalMarksOrGrade;
	// }
	//
	// public void setFinalMarksOrGrade(String finalMarksOrGrade) {
	// this.finalMarksOrGrade = finalMarksOrGrade;
	// }

	public double getMinPassGrade() {
		return minPassGrade;
	}

	public void setMinPassGrade(double minPassGrade) {
		this.minPassGrade = minPassGrade;
	}

	public double getMinPassMarks() {
		return minPassMarks;
	}

	public void setMinPassMarks(double minPassMarks) {
		this.minPassMarks = minPassMarks;
	}

	public double getInternalWeight() {
		return internalWeight;
	}

	public void setInternalWeight(double internalWeight) {
		this.internalWeight = internalWeight;
	}

	public double getExternalWeight() {
		return externalWeight;
	}

	public void setExternalWeight(double externalWeight) {
		this.externalWeight = externalWeight;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public double getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public double getObtainedGrade() {
		return obtainedGrade;
	}

	public void setObtainedGrade(double obtainedGrade) {
		this.obtainedGrade = obtainedGrade;
	}

	public double getEarnedCredits() {
		return earnedCredits;
	}

	public void setEarnedCredits(double earnedCredits) {
		this.earnedCredits = earnedCredits;
	}

	public double getSgpaPointSecured() {
		return sgpaPointSecured;
	}

	public void setSgpaPointSecured(double sgpaPointSecured) {
		this.sgpaPointSecured = sgpaPointSecured;
	}

	public double getCgpaPointSecured() {
		return cgpaPointSecured;
	}

	public void setCgpaPointSecured(double cgpaPointSecured) {
		this.cgpaPointSecured = cgpaPointSecured;
	}
	
	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public PreProcessCourseList(String universtyId, String programCourseKey,
			String semesterStartDate, String semesterEndDate, String programId,
			String branchId, String specialzationId, String semesterCode,String entityId) {
		this.universityId = universtyId;
		this.programCourseKey = programCourseKey;
		// this.rollNumber=rollNumber;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specialzationId;
		this.semesterCode = semesterCode;
		this.entityId =entityId;

	}

	public PreProcessCourseList(String universityId, String programCourseKey,
			String semesterStartDate, String semesterEndDate,
			String rollNumber, String userId) {
		this.universityId = universityId;
		this.programCourseKey = programCourseKey;
		this.rollNumber = rollNumber;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.userId = userId;

	}

	public PreProcessCourseList() {
		super();
	}
}
