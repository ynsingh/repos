/*   StudentRemedialInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.studentremedial;
/**
 * This bean file is associated with
 * Student Remedial.
 * @author Rohit
 * @date 7 April 2011
 * @version 1.0
 */
public class StudentRemedialInfoGetter {
	private String userId;
	private String rollNo;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterId;
	private String universityCode;
	private String programName;
	private String branchName;
	private String specializationName;
	private String semesterName;
	private String programCourseKey;
	private String semesterStartDate;
	private String semesterEndDate;
	private String courseCode;
	private String courseStatus;
	private String courseName;
	private String attemptNumber;
	private String applied;
	private String appliedStartDate;
	private String appliedEndDate;
	private String appliedSemester;
	private String entityId;//add by devendra
	private String entityName;//add by devendra
	private String enrollmentNumber;//add by devendra
	private String nextSessionStartDate;//add by devendra
	private String nextSessionEndDate;//add by devendra
	private String sessionStartDate;//add by devendra
	private String sessionEndDate;//add by devendra
	
	/**
	 * @return the sessionStartDate
	 */
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	/**
	 * @param sessionStartDate the sessionStartDate to set
	 */
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	/**
	 * @return the sessionEndDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	/**
	 * @param sessionEndDate the sessionEndDate to set
	 */
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	/**
	 * @return the nextSessionStartDate
	 */
	public String getNextSessionStartDate() {
		return nextSessionStartDate;
	}
	/**
	 * @param nextSessionStartDate the nextSessionStartDate to set
	 */
	public void setNextSessionStartDate(String nextSessionStartDate) {
		this.nextSessionStartDate = nextSessionStartDate;
	}
	/**
	 * @return the nextSessionEndDate
	 */
	public String getNextSessionEndDate() {
		return nextSessionEndDate;
	}
	/**
	 * @param nextSessionEndDate the nextSessionEndDate to set
	 */
	public void setNextSessionEndDate(String nextSessionEndDate) {
		this.nextSessionEndDate = nextSessionEndDate;
	}
	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}
	/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
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
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getUniversityCode() {
		return universityCode;
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
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}
	public String getSpecializationName() {
		return specializationName;
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
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setAttemptNumber(String attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	public String getAttemptNumber() {
		return attemptNumber;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}
	public String getApplied() {
		return applied;
	}
	public void setAppliedStartDate(String appliedStartDate) {
		this.appliedStartDate = appliedStartDate;
	}
	public String getAppliedStartDate() {
		return appliedStartDate;
	}
	public void setAppliedEndDate(String appliedEndDate) {
		this.appliedEndDate = appliedEndDate;
	}
	public String getAppliedEndDate() {
		return appliedEndDate;
	}
	public void setAppliedSemester(String appliedSemester) {
		this.appliedSemester = appliedSemester;
	}
	public String getAppliedSemester() {
		return appliedSemester;
	}
}
