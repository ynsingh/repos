/**
 * @(#) AddDropCourseBean.java
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
package in.ac.dei.edrp.cms.domain.adddropcourse;

import java.util.ArrayList;
import java.util.List;

/**
 * this is Server side bean class for add drop course
 * 
 * @version 1.0 12 July 2011
 * @author MOHD AMIR
 */
public class AddDropCourseBean {
	/** declaring private variables */
	private String courseCode;
	private String courseName;
	private String credits;
	private String minimumCredits;
	private String maximumCredits;
	private String courseType;
	private String courseGroup;
	private String courseClass;
	private String courseClassName;
	private String minimumLectureCredits;
	private String maximumLectureCredits;
	private String regNumber;
	private String enrollNumber;
	private String rollNumber;
	private String studentId;
	private String semesterStartDate;
	private String semesterEndDate;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterId;
	private String entityName;
	private String programName;
	private String branchName;
	private String specializationName;
	private String semesterName;
	private String name;
	private String courseTypeName;
	private String courseGroupName;
	private String userId;
	private String sessionStartDate;//Add by Devendra
	private String sessionEndDate;//Add by Devendra
	private String universityId;//Add by Devendra
	private List<String> courseCodeList;//Add by Devendra
	private String theoryCredits;
	private String practicalCredits;
	
	
	/**
	 * @return the theoryCredits
	 */
	public String getTheoryCredits() {
		return theoryCredits;
	}

	/**
	 * @param theoryCredits the theoryCredits to set
	 */
	public void setTheoryCredits(String theoryCredits) {
		this.theoryCredits = theoryCredits;
	}
	

	/**
	 * @return the practicalCredits
	 */
	public String getPracticalCredits() {
		return practicalCredits;
	}

	/**
	 * @param practicalCredits the practicalCredits to set
	 */
	public void setPracticalCredits(String practicalCredits) {
		this.practicalCredits = practicalCredits;
	}

	/**
	 * @return the courseCodeList
	 */
	public List<String> getCourseCodeList() {
		return courseCodeList;
	}

	/**
	 * @param courseCodeList the courseCodeList to set
	 */
	public void setCourseCodeList(List<String> courseCodeList) {
		this.courseCodeList = courseCodeList;
	}

	/**
	 * @return the universityId
	 */
	public String getUniversityId() {
		return universityId;
	}

	/**
	 * @param universityId the universityId to set
	 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

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

	// defining getter and setter method for variables
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

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getMinimumCredits() {
		return minimumCredits;
	}

	public void setMinimumCredits(String minimumCredits) {
		this.minimumCredits = minimumCredits;
	}

	public String getMaximumCredits() {
		return maximumCredits;
	}

	public void setMaximumCredits(String maximumCredits) {
		this.maximumCredits = maximumCredits;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(String courseGroup) {
		this.courseGroup = courseGroup;
	}

	public String getMinimumLectureCredits() {
		return minimumLectureCredits;
	}

	public void setMinimumLectureCredits(String minimumLectureCredits) {
		this.minimumLectureCredits = minimumLectureCredits;
	}

	public String getMaximumLectureCredits() {
		return maximumLectureCredits;
	}

	public void setMaximumLectureCredits(String maximumLectureCredits) {
		this.maximumLectureCredits = maximumLectureCredits;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseGroupName(String courseGroupName) {
		this.courseGroupName = courseGroupName;
	}

	public String getCourseGroupName() {
		return courseGroupName;
	}

	public void setEnrollNumber(String enrollNumber) {
		this.enrollNumber = enrollNumber;
	}

	public String getEnrollNumber() {
		return enrollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}

	public String getCourseClass() {
		return courseClass;
	}

	public void setCourseClassName(String courseClassName) {
		this.courseClassName = courseClassName;
	}

	public String getCourseClassName() {
		return courseClassName;
	}
}
