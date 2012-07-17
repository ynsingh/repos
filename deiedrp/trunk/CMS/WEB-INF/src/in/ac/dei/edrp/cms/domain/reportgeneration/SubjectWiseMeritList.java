/*
 * @(#) SubjectWiseMeritList.java
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

import java.util.List;

/**
 * This is the getter setter class 
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class SubjectWiseMeritList {
	
	private String entityId;
	private String programCourseKey;
	private String universityId;
	private String semesterStartDate;
	private String semesterEndDate;
	private String processName;
	private String processCode;
	private String activityName;
	private String activityCode;
	private String activitySequence;
	private String programId;
	private String programName;
	private String programCode;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterCode;
	private String semesterName;
	private Integer semesterSequence;
	private String semesterStatus;
	private String creatorId;
	private String modifierId;
	private String status;
	private String size;
	private String processActivityStartDate;
	private String processActivityEndDate;
	private String activityStatus;
	private String sessionStartDate;
	private String sessionEndDate;
	private String courseGroup;
	private String name;
	private String rollNumber;
	private String marks;
	private String gender;
	private String category;
	private String total;
	private String entityCode;
	
	private List<String> programCourseKeyList;
	
	private String programCourseKeyArray[];
	private String programCourseKey1;
	private String programCourseKey2;
	private String programCourseKey3;
	private String programCourseKey4;
	private String programCourseKey5;
	private String programCourseKey6;
	private String programCourseKey7;
	private String programCourseKey8;
	private String programCourseKey9;
	private String programCourseKey10;
	
	private String semester1;
	private String semester2;
	private String semester3;
	private String semester4;
	private String semester5;
	private String semester6;
	private String semester7;
	private String semester8;
	private String semester9;
	private String semester10;
	
	
	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the semester1
	 */
	public String getSemester1() {
		return semester1;
	}

	/**
	 * @param semester1 the semester1 to set
	 */
	public void setSemester1(String semester1) {
		this.semester1 = semester1;
	}

	/**
	 * @return the semester2
	 */
	public String getSemester2() {
		return semester2;
	}

	/**
	 * @param semester2 the semester2 to set
	 */
	public void setSemester2(String semester2) {
		this.semester2 = semester2;
	}

	/**
	 * @return the semester3
	 */
	public String getSemester3() {
		return semester3;
	}

	/**
	 * @param semester3 the semester3 to set
	 */
	public void setSemester3(String semester3) {
		this.semester3 = semester3;
	}

	/**
	 * @return the semester4
	 */
	public String getSemester4() {
		return semester4;
	}

	/**
	 * @param semester4 the semester4 to set
	 */
	public void setSemester4(String semester4) {
		this.semester4 = semester4;
	}

	/**
	 * @return the semester5
	 */
	public String getSemester5() {
		return semester5;
	}

	/**
	 * @param semester5 the semester5 to set
	 */
	public void setSemester5(String semester5) {
		this.semester5 = semester5;
	}

	/**
	 * @return the semester6
	 */
	public String getSemester6() {
		return semester6;
	}

	/**
	 * @param semester6 the semester6 to set
	 */
	public void setSemester6(String semester6) {
		this.semester6 = semester6;
	}

	/**
	 * @return the semester7
	 */
	public String getSemester7() {
		return semester7;
	}

	/**
	 * @param semester7 the semester7 to set
	 */
	public void setSemester7(String semester7) {
		this.semester7 = semester7;
	}

	/**
	 * @return the semester8
	 */
	public String getSemester8() {
		return semester8;
	}

	/**
	 * @param semester8 the semester8 to set
	 */
	public void setSemester8(String semester8) {
		this.semester8 = semester8;
	}

	/**
	 * @return the semester9
	 */
	public String getSemester9() {
		return semester9;
	}

	/**
	 * @param semester9 the semester9 to set
	 */
	public void setSemester9(String semester9) {
		this.semester9 = semester9;
	}

	/**
	 * @return the semester10
	 */
	public String getSemester10() {
		return semester10;
	}

	/**
	 * @param semester10 the semester10 to set
	 */
	public void setSemester10(String semester10) {
		this.semester10 = semester10;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the programCourseArray
	 */
	public String[] getProgramCourseKeyArray() {
		return programCourseKeyArray;
	}

	/**
	 * @param programCourseArray the programCourseArray to set
	 */
	public void setProgramCourseKeyArray(String[] programCourseKeyArray) {
		this.programCourseKeyArray = programCourseKeyArray;
	}

	/**
	 * @return the programCourseKeyList
	 */
	public List<String> getProgramCourseKeyList() {
		return programCourseKeyList;
	}

	/**
	 * @param programCourseKeyList the programCourseKeyList to set
	 */
	public void setProgramCourseKeyList(List<String> programCourseKeyList) {
		this.programCourseKeyList = programCourseKeyList;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	

	public String getProgramCourseKey1() {
		return programCourseKey1;
	}

	public void setProgramCourseKey1(String programCourseKey1) {
		this.programCourseKey1 = programCourseKey1;
	}

	public String getProgramCourseKey2() {
		return programCourseKey2;
	}

	public void setProgramCourseKey2(String programCourseKey2) {
		this.programCourseKey2 = programCourseKey2;
	}

	public String getProgramCourseKey3() {
		return programCourseKey3;
	}

	public void setProgramCourseKey3(String programCourseKey3) {
		this.programCourseKey3 = programCourseKey3;
	}

	public String getProgramCourseKey4() {
		return programCourseKey4;
	}

	public void setProgramCourseKey4(String programCourseKey4) {
		this.programCourseKey4 = programCourseKey4;
	}

	public String getProgramCourseKey5() {
		return programCourseKey5;
	}

	public void setProgramCourseKey5(String programCourseKey5) {
		this.programCourseKey5 = programCourseKey5;
	}

	public String getProgramCourseKey6() {
		return programCourseKey6;
	}

	public void setProgramCourseKey6(String programCourseKey6) {
		this.programCourseKey6 = programCourseKey6;
	}

	public String getProgramCourseKey7() {
		return programCourseKey7;
	}

	public void setProgramCourseKey7(String programCourseKey7) {
		this.programCourseKey7 = programCourseKey7;
	}

	public String getProgramCourseKey8() {
		return programCourseKey8;
	}

	public void setProgramCourseKey8(String programCourseKey8) {
		this.programCourseKey8 = programCourseKey8;
	}

	public String getProgramCourseKey9() {
		return programCourseKey9;
	}

	public void setProgramCourseKey9(String programCourseKey9) {
		this.programCourseKey9 = programCourseKey9;
	}

	public String getProgramCourseKey10() {
		return programCourseKey10;
	}

	public void setProgramCourseKey10(String programCourseKey10) {
		this.programCourseKey10 = programCourseKey10;
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

	public String getCourseGroup() {
		return courseGroup;
	}

	public void setCourseGroup(String courseGroup) {
		this.courseGroup = courseGroup;
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

	public String getProcessActivityStartDate() {
		return processActivityStartDate;
	}

	public void setProcessActivityStartDate(String processActivityStartDate) {
		this.processActivityStartDate = processActivityStartDate;
	}

	public String getProcessActivityEndDate() {
		return processActivityEndDate;
	}

	public void setProcessActivityEndDate(String processActivityEndDate) {
		this.processActivityEndDate = processActivityEndDate;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public Integer getSemesterSequence() {
		return semesterSequence;
	}

	public void setSemesterSequence(Integer semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	public String getSemesterStatus() {
		return semesterStatus;
	}

	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
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

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getActivitySequence() {
		return activitySequence;
	}

	public void setActivitySequence(String activitySequence) {
		this.activitySequence = activitySequence;
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


	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}	
	
}
