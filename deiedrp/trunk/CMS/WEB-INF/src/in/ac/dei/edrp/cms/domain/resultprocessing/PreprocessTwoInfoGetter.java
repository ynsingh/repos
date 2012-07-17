/*
 * @(#) PreprocessTwoInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.resultprocessing;

/**
 * Bean class for pre-process
 * @author mhrd edrp
 * @date 08-04-2011
 * @version 1.0
 */
public class PreprocessTwoInfoGetter {
	
	private String rollNumber;
	private String attemptNumber;
	private String courseCode;
	private String marks;
	private String maxAllowedAttempt;
	private String semesterCode;
	private String finalSemester;
	private int totalFail;
	private int totalPass;
	private String switchNumber;
	private String toProgram;
	private String toBranch;
	private String toSpecialization;
	private String fromProgram;
	private String fromBranch;
	private String fromSpecialization;
	private String status;
	private String programMode;
	private String programId;
	private String branchId;
	private String specializationId;
	private String entityId;
	private String programCourseKey;
	private String modifierId;
	private String startDate;
	private String endDate;
	
	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}
	/**
	 * @param rollNumber the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	/**
	 * @return the attemptNumber
	 */
	public String getAttemptNumber() {
		return attemptNumber;
	}
	/**
	 * @param attemptNumber the attemptNumber to set
	 */
	public void setAttemptNumber(String attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * @return the marks
	 */
	public String getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(String marks) {
		this.marks = marks;
	}
	/**
	 * @return the maxAllowedAttempt
	 */
	public String getMaxAllowedAttempt() {
		return maxAllowedAttempt;
	}
	/**
	 * @param maxAllowedAttempt the maxAllowedAttempt to set
	 */
	public void setMaxAllowedAttempt(String maxAllowedAttempt) {
		this.maxAllowedAttempt = maxAllowedAttempt;
	}
	/**
	 * @return the semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}
	/**
	 * @param semesterCode the semesterCode to set
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	/**
	 * @return the finalSemester
	 */
	public String getFinalSemester() {
		return finalSemester;
	}
	/**
	 * @param finalSemester the finalSemester to set
	 */
	public void setFinalSemester(String finalSemester) {
		this.finalSemester = finalSemester;
	}
	/**
	 * @return the totalFail
	 */
	public int getTotalFail() {
		return totalFail;
	}
	/**
	 * @param totalFail the totalFail to set
	 */
	public void setTotalFail(int totalFail) {
		this.totalFail = totalFail;
	}
	/**
	 * @return the totalPass
	 */
	public int getTotalPass() {
		return totalPass;
	}
	/**
	 * @param totalPass the totalPass to set
	 */
	public void setTotalPass(int totalPass) {
		this.totalPass = totalPass;
	}
	/**
	 * @return the switchNumber
	 */
	public String getSwitchNumber() {
		return switchNumber;
	}
	/**
	 * @param switchNumber the switchNumber to set
	 */
	public void setSwitchNumber(String switchNumber) {
		this.switchNumber = switchNumber;
	}
	/**
	 * @return the toProgram
	 */
	public String getToProgram() {
		return toProgram;
	}
	/**
	 * @param toProgram the toProgram to set
	 */
	public void setToProgram(String toProgram) {
		this.toProgram = toProgram;
	}
	/**
	 * @return the toBranch
	 */
	public String getToBranch() {
		return toBranch;
	}
	/**
	 * @param toBranch the toBranch to set
	 */
	public void setToBranch(String toBranch) {
		this.toBranch = toBranch;
	}
	/**
	 * @return the toSpecialization
	 */
	public String getToSpecialization() {
		return toSpecialization;
	}
	/**
	 * @param toSpecialization the toSpecialization to set
	 */
	public void setToSpecialization(String toSpecialization) {
		this.toSpecialization = toSpecialization;
	}
	/**
	 * @return the fromProgram
	 */
	public String getFromProgram() {
		return fromProgram;
	}
	/**
	 * @param fromProgram the fromProgram to set
	 */
	public void setFromProgram(String fromProgram) {
		this.fromProgram = fromProgram;
	}
	/**
	 * @return the fromBranch
	 */
	public String getFromBranch() {
		return fromBranch;
	}
	/**
	 * @param fromBranch the fromBranch to set
	 */
	public void setFromBranch(String fromBranch) {
		this.fromBranch = fromBranch;
	}
	/**
	 * @return the fromSpecialization
	 */
	public String getFromSpecialization() {
		return fromSpecialization;
	}
	/**
	 * @param fromSpecialization the fromSpecialization to set
	 */
	public void setFromSpecialization(String fromSpecialization) {
		this.fromSpecialization = fromSpecialization;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the programMode
	 */
	public String getProgramMode() {
		return programMode;
	}
	/**
	 * @param programMode the programMode to set
	 */
	public void setProgramMode(String programMode) {
		this.programMode = programMode;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the specializationId
	 */
	public String getSpecializationId() {
		return specializationId;
	}
	/**
	 * @param specializationId the specializationId to set
	 */
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
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
	 * @return the programCourseKey
	 */
	public String getProgramCourseKey() {
		return programCourseKey;
	}
	/**
	 * @param programCourseKey the programCourseKey to set
	 */
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}
	/**
	 * @return the modifierId
	 */
	public String getModifierId() {
		return modifierId;
	}
	/**
	 * @param modifierId the modifierId to set
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
	
	
	

}
