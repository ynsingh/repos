/*   StudentMarksSummaryBean.java
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
package in.ac.dei.edrp.api;
/**
 * This bean file is associated with
 * Student Information
 * @author Nupur Dixit
 * @date 10 Jan 2011
 * @version 1.0
 */
public class StudentMarksSummaryBean {
	private String userId;		
	private String semesterCode;			
	private String programCourseKey;	
	
	private String universityId;
	private String enrollmentNumber;
	private String rollNumber;
	private String programId;
	private String branchId;
	private String specializationId;
	private String programName;
	private String branchName;
	private String specialization;
	private String courseCode;
	private String courseName;
	private String semesterName;
	private String evaluationId;
	private String marks;
	private String grades;
	private String status;
	private String semesterStartDate;
	private String semesterEndDate;
	private String sessionStartDate;
	private String sessionEndDate;
	private String entityId;
	
	private String message;	
	private String totalInternal;
	private String totalExternal;
	private String totalMarks;
	private String internalGrade;
	private String externalGrade;
	private String finalGradePoint;
	private String approvalStatus;
	private String displayType;
	private String sgpa;
	private String cgpa;
	private String theorySgpa;
	private String practicalSgpa;
	private String theoryCgpa;
	private String practicalCgpa;
	
	private String courseStatus;
	private String studentStatus;
	private String attemptNumber;
	private String name;
	
	//added by ashish 
	private String markSavedDate;
	private String displayStartDate;
	private String displayEndDate;
	private String studentLeft;
	

	public String getMarkSavedDate() {
		return markSavedDate;
	}
	public void setMarkSavedDate(String markSavedDate) {
		this.markSavedDate = markSavedDate;
	}
	public String getDisplayStartDate() {
		return displayStartDate;
	}
	public void setDisplayStartDate(String displayStartDate) {
		this.displayStartDate = displayStartDate;
	}
	public String getDisplayEndDate() {
		return displayEndDate;
	}
	public void setDisplayEndDate(String displayEndDate) {
		this.displayEndDate = displayEndDate;
	}
	public String getStudentLeft() {
		return studentLeft;
	}
	public void setStudentLeft(String studentLeft) {
		this.studentLeft = studentLeft;
	}
	//end of adding
	
	
	
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}
	public String getSemesterStartDate() {
		return semesterStartDate;
	}
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}
	public String getSemesterEndDate() {
		return semesterEndDate;
	}
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	public String getProgramCourseKey() {
		return programCourseKey;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseName() {
		return courseName;
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
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	public String getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getGrades() {
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalInternal() {
		return totalInternal;
	}
	public void setTotalInternal(String totalInternal) {
		this.totalInternal = totalInternal;
	}
	public String getTotalExternal() {
		return totalExternal;
	}
	public void setTotalExternal(String totalExternal) {
		this.totalExternal = totalExternal;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
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
	public String getFinalGradePoint() {
		return finalGradePoint;
	}
	public void setFinalGradePoint(String finalGradePoint) {
		this.finalGradePoint = finalGradePoint;
	}
	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	/**
	 * @return the displayType
	 */
	public String getDisplayType() {
		return displayType;
	}
	public String getSgpa() {
		return sgpa;
	}
	public void setSgpa(String sgpa) {
		this.sgpa = sgpa;
	}
	public String getCgpa() {
		return cgpa;
	}
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
	public String getTheorySgpa() {
		return theorySgpa;
	}
	public void setTheorySgpa(String theorySgpa) {
		this.theorySgpa = theorySgpa;
	}
	public String getPracticalSgpa() {
		return practicalSgpa;
	}
	public void setPracticalSgpa(String practicalSgpa) {
		this.practicalSgpa = practicalSgpa;
	}
	public String getTheoryCgpa() {
		return theoryCgpa;
	}
	public void setTheoryCgpa(String theoryCgpa) {
		this.theoryCgpa = theoryCgpa;
	}
	public String getPracticalCgpa() {
		return practicalCgpa;
	}
	public void setPracticalCgpa(String practicalCgpa) {
		this.practicalCgpa = practicalCgpa;
	}
	/**
	 * @param courseStatus the courseStatus to set
	 */
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	/**
	 * @return the courseStatus
	 */
	public String getCourseStatus() {
		return courseStatus;
	}
	/**
	 * @param studentStatus the studentStatus to set
	 */
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	/**
	 * @return the studentStatus
	 */
	public String getStudentStatus() {
		return studentStatus;
	}
	/**
	 * @param attemptNumber the attemptNumber to set
	 */
	public void setAttemptNumber(String attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	/**
	 * @return the attemptNumber
	 */
	public String getAttemptNumber() {
		return attemptNumber;
	}
	/**
	 * @param sessionStartDate the sessionStartDate to set
	 */
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	/**
	 * @return the sessionStartDate
	 */
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	/**
	 * @param sessionEndDate the sessionEndDate to set
	 */
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	/**
	 * @return the sessionEndDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
