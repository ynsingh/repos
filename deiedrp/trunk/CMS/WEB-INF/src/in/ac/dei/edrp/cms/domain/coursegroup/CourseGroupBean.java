/**
 * @(#) CourseGroupBean.java
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
package in.ac.dei.edrp.cms.domain.coursegroup;

/**
 * this is bean class for SystemTableTwo
 *
 * @version 1.0 14 FEB 2011
 * @author MOHD AMIR
 */
public class CourseGroupBean {
	
	/** declaring private variables */
	private String programCourseKey;
	private String programId;
	private String programName;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterCode;
	private String semesterName;
	private String courseGroupCode;
	private String courseGroupName;
	private String elective;
	private String userId;
	private String minCredits;
	private String maxCredits;
	private Integer count;
	private String entityId;
	private String parentEntity;
	private String universityId;
	private String orderInMarksheet;
	
	
	/** defining getter and setter for getting and setting values of private variables */
	
	public String getProgramCourseKey() {
		return programCourseKey;
	}
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
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
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getCourseGroupCode() {
		return courseGroupCode;
	}
	public void setCourseGroupCode(String courseGroupCode) {
		this.courseGroupCode = courseGroupCode;
	}
	public String getCourseGroupName() {
		return courseGroupName;
	}
	public void setCourseGroupName(String courseGroupName) {
		this.courseGroupName = courseGroupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMinCredits() {
		return minCredits;
	}
	public void setMinCredits(String minCredits) {
		this.minCredits = minCredits;
	}
	public String getMaxCredits() {
		return maxCredits;
	}
	public void setMaxCredits(String maxCredits) {
		this.maxCredits = maxCredits;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	public void setElective(String elective) {
		this.elective = elective;
	}
	public String getElective() {
		return elective;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}
	public String getParentEntity() {
		return parentEntity;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityId() {
		return universityId;
	}
	/**
	 * @param orderInMarksheet the orderInMarksheet to set
	 */
	public void setOrderInMarksheet(String orderInMarksheet) {
		this.orderInMarksheet = orderInMarksheet;
	}
	/**
	 * @return the orderInMarksheet
	 */
	public String getOrderInMarksheet() {
		return orderInMarksheet;
	}
}