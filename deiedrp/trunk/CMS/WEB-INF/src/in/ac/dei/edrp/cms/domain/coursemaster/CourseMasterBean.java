/**
 * @(#) CourseMasterBean.java
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
package in.ac.dei.edrp.cms.domain.coursemaster;

public class CourseMasterBean
{
	/** declaring private variables **/
	private String universityCode;
	private String ownerEntityId;
	private String ownerEntityName;
	private String ownerProgramId;
	private String ownerProgramName;
	private String ownerBranchId;
	private String ownerBranchName;
	private String ownerSpecializationId;
	private String ownerSpecializationName;
	private String courseCode;
	private String courseName;
	private String courseTypeId;
	private String courseTypeName;
	private String courseClassificationId;
	private String courseClassificationName;
	private String courseGroupId;
	private String courseGroupName;
	private String units;
	private String credits;
	private String lectures;
	private String tutorials;
	private String practicals;
	private String marksEndSemester;
	private String marksContEval;
	private String marksTotal;
	private String sinceSession;
	private String resultSystem;
	private String userId;
	private Integer count;
	private String groupCode;
	private String componentId;
	private String componentDescription;
	private String dummyFlag;
	/*gradeLimit added By Mandeep*/
	private String gradeLimit;
	private String edeiStatus;
	
	/** defining getter and setter method for private variables **/
	public String getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
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
	
	public String getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}
	
	public String getCourseTypeName() {
		return courseTypeName;
	}
	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}
	
	public String getCourseClassificationId() {
		return courseClassificationId;
	}
	public void setCourseClassificationId(String courseClassificationId) {
		this.courseClassificationId = courseClassificationId;
	}
	
	public String getCourseClassificationName() {
		return courseClassificationName;
	}
	public void setCourseClassificationName(String courseClassificationName) {
		this.courseClassificationName = courseClassificationName;
	}
	
	public String getCourseGroupId() {
		return courseGroupId;
	}
	public void setCourseGroupId(String courseGroupId) {
		this.courseGroupId = courseGroupId;
	}
	
	public String getCourseGroupName() {
		return courseGroupName;
	}
	public void setCourseGroupName(String courseGroupName) {
		this.courseGroupName = courseGroupName;
	}
	
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	
	public String getLectures() {
		return lectures;
	}
	public void setLectures(String lectures) {
		this.lectures = lectures;
	}
	
	public String getTutorials() {
		return tutorials;
	}
	public void setTutorials(String tutorials) {
		this.tutorials = tutorials;
	}
	
	public String getPracticals() {
		return practicals;
	}
	public void setPracticals(String practicals) {
		this.practicals = practicals;
	}
	
	public String getMarksEndSemester() {
		return marksEndSemester;
	}
	public void setMarksEndSemester(String marksEndSemester) {
		this.marksEndSemester = marksEndSemester;
	}
	
	public String getMarksContEval() {
		return marksContEval;
	}
	public void setMarksContEval(String marksContEval) {
		this.marksContEval = marksContEval;
	}
	
	public String getMarksTotal() {
		return marksTotal;
	}
	public void setMarksTotal(String marksTotal) {
		this.marksTotal = marksTotal;
	}
	
	public String getSinceSession() {
		return sinceSession;
	}
	public void setSinceSession(String sinceSession) {
		this.sinceSession = sinceSession;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public void setOwnerEntityId(String ownerEntityId) {
		this.ownerEntityId = ownerEntityId;
	}
	public String getOwnerEntityId() {
		return ownerEntityId;
	}
	
	public void setOwnerEntityName(String ownerEntityName) {
		this.ownerEntityName = ownerEntityName;
	}
	public String getOwnerEntityName() {
		return ownerEntityName;
	}
	
	public void setOwnerProgramId(String ownerProgramId) {
		this.ownerProgramId = ownerProgramId;
	}
	public String getOwnerProgramId() {
		return ownerProgramId;
	}
	
	public void setOwnerSpecializationName(String ownerSpecializationName) {
		this.ownerSpecializationName = ownerSpecializationName;
	}
	public String getOwnerSpecializationName() {
		return ownerSpecializationName;
	}
	
	public void setOwnerBranchId(String ownerBranchId) {
		this.ownerBranchId = ownerBranchId;
	}
	public String getOwnerBranchId() {
		return ownerBranchId;
	}
	
	public void setOwnerBranchName(String ownerBranchName) {
		this.ownerBranchName = ownerBranchName;
	}
	public String getOwnerBranchName() {
		return ownerBranchName;
	}
	
	public void setOwnerSpecializationId(String ownerSpecializationId) {
		this.ownerSpecializationId = ownerSpecializationId;
	}
	public String getOwnerSpecializationId() {
		return ownerSpecializationId;
	}
	
	public void setOwnerProgramName(String ownerProgramName) {
		this.ownerProgramName = ownerProgramName;
	}
	public String getOwnerProgramName() {
		return ownerProgramName;
	}
	
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}
	public String getResultSystem() {
		return resultSystem;
	}
	public void setDummyFlag(String dummyFlag) {
		this.dummyFlag = dummyFlag;
	}
	public String getDummyFlag() {
		return dummyFlag;
	}
	public String getGradeLimit() {
		return gradeLimit;
	}
	public void setGradeLimit(String gradeLimit) {
		this.gradeLimit = gradeLimit;
	}
	 	
	/**
	 * @return the edeiStatus
	 */
	public String getEdeiStatus() {
		return edeiStatus;
	}
	/**
	 * @param edeiStatus the edeiStatus to set
	 */
	public void setEdeiStatus(String edeiStatus) {
		this.edeiStatus = edeiStatus;
	}
}