/*
 * @(#) CorrectionInRegistrationInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.correctioninregistration;

/**
 * This bean file is associated with
 * PostRegistrationCorrection
 * @author Rohit
 * @date 3 AUG 2011
 * @version 1.0
 */
public class CorrectionInRegistrationInfoGetter {
	
	private String universityId;
	private String userId;
	private String studentId;
	private String regNo;
	private String rollNo;
	private String enrollNo;
	private String dob;
	private String category;
	private String gender;
	private String studentfname;
	private String studentmname;
	private String studentlname;
	private String fatherfname;
	private String fathermname;
	private String fatherlname;
	private String entityName;
	private String programName;
	private String branchName;
	private String specializationName;
	private String semesterName;
	private String regStatus;
	private String reasoncode;
	private String description;
	private String admissionMode;
	private String categoryId;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterId;
	private String sequenceNo;
	private String oldEntity;
	private String oldProgram;
	private String oldBranch;
	private String oldSpecialization;
	private String oldSemester;
	private int count;
	
	public String getOldEntity() {
		return oldEntity;
	}
	public void setOldEntity(String oldEntity) {
		this.oldEntity = oldEntity;
	}
	public String getOldProgram() {
		return oldProgram;
	}
	public void setOldProgram(String oldProgram) {
		this.oldProgram = oldProgram;
	}
	public String getOldBranch() {
		return oldBranch;
	}
	public void setOldBranch(String oldBranch) {
		this.oldBranch = oldBranch;
	}
	public String getOldSpecialization() {
		return oldSpecialization;
	}
	public void setOldSpecialization(String oldSpecialization) {
		this.oldSpecialization = oldSpecialization;
	}
	public String getOldSemester() {
		return oldSemester;
	}
	public void setOldSemester(String oldSemester) {
		this.oldSemester = oldSemester;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudentfname() {
		return studentfname;
	}
	public void setStudentfname(String studentfname) {
		this.studentfname = studentfname;
	}
	public String getStudentmname() {
		return studentmname;
	}
	public void setStudentmname(String studentmname) {
		this.studentmname = studentmname;
	}
	public String getStudentlname() {
		return studentlname;
	}
	public void setStudentlname(String studentlname) {
		this.studentlname = studentlname;
	}
	public String getFatherfname() {
		return fatherfname;
	}
	public void setFatherfname(String fatherfname) {
		this.fatherfname = fatherfname;
	}
	public String getFathermname() {
		return fathermname;
	}
	public void setFathermname(String fathermname) {
		this.fathermname = fathermname;
	}
	public String getFatherlname() {
		return fatherlname;
	}
	public void setFatherlname(String fatherlname) {
		this.fatherlname = fatherlname;
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
	public String getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	public String getReasoncode() {
		return reasoncode;
	}
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdmissionMode() {
		return admissionMode;
	}
	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	public String getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getUniversityId() {
		return universityId;
	}
	

}
