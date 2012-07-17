/*
 * @(#) Transcript.java
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
 * @date 5 Dec 2011
 * @version 1.0
 */
public class Transcript {
	
	private String enrollmentNumber;	
	private String rollNumber;
//	private String currentSemester;
//	private String semesterSequence;
	private String semesterCode;
	private String semesterName;
	private String finalSemesterCode;
	private String programStatus;
	private String programId;
	private String programName;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specialization;
	private String programCourseKey;
//	private String inSemester;
//	private String outSemester;
	private String division;
	private String switchNumber;
	private String sequenceNumber;
	private String semesterStartDate;
	private String semesterEndDate;
	private double theoryCgpa;
	private double practicalCgpa;
	private double cgpa;
	private double sgpa;
	private String status;
	private String statusName;
	private String courseCode;
	private String courseName;
	private String internalGrade;
	private String externalGrade;
	private double finalGradePoint;
	private String earnedCredit;
	private double credit;
	private String studentName;
	private String fatherName;
	private String dob;
	private String universityId;
	
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

	public double getSgpa() {
		return sgpa;
	}

	public void setSgpa(double sgpa) {
		this.sgpa = sgpa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getInternalGrade() {
		return internalGrade;
	}

	public void setInternalGrade(String internalGrade) {
		this.internalGrade = internalGrade;
	}

	public String getExternalGrade() {
		return externalGrade;
	}

	public void setExternalGrade(String externalGrade) {
		this.externalGrade = externalGrade;
	}

	public double getFinalGradePoint() {
		return finalGradePoint;
	}

	public void setFinalGradePoint(double finalGradePoint) {
		this.finalGradePoint = finalGradePoint;
	}

	public String getEarnedCredit() {
		return earnedCredit;
	}

	public void setEarnedCredit(String earnedCredit) {
		this.earnedCredit = earnedCredit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
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

	
	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	
	
	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getFinalSemesterCode() {
		return finalSemesterCode;
	}

	public void setFinalSemesterCode(String finalSemesterCode) {
		this.finalSemesterCode = finalSemesterCode;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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

	/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param semesterCode the semesterCode to set
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * @return the semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}

	/**
	 * @param semesterName the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return semesterName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param universityId the universityId to set
	 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	/**
	 * @return the universityId
	 */
	public String getUniversityId() {
		return universityId;
	}

	/**
	 * @param theoryCgpa the theoryCgpa to set
	 */
	public void setTheoryCgpa(double theoryCgpa) {
		this.theoryCgpa = theoryCgpa;
	}

	/**
	 * @return the theoryCgpa
	 */
	public double getTheoryCgpa() {
		return theoryCgpa;
	}

	/**
	 * @param practicalCgpa the practicalCgpa to set
	 */
	public void setPracticalCgpa(double practicalCgpa) {
		this.practicalCgpa = practicalCgpa;
	}

	/**
	 * @return the practicalCgpa
	 */
	public double getPracticalCgpa() {
		return practicalCgpa;
	}	
	
}
