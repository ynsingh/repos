/*
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
package in.ac.dei.edrp.cms.domain.studentregistration;

import java.sql.Date;


/**
 * This file consist of different variables to be used during
 * number generation for students.
 * @author Ashish
 * @date 21 jan 2010
 * @version 1.0
 *
 */
public class StudentNumbersInfoGetter {
    private Date session;
    private String entityName;
    private String entityId;
    private String programName;
    private String programId;
    private String branchName;
    private String branchId;
    private String specializationId;
    private String specializationName;
    private String universityId;
    private String semesterCode;
    private String semesterName;
    private String enrollmentNumber;
    private String rollNumber;
    private String registrationNumber;
    private String studentId;
    private String counter;
    private String systemValue;
    private String statusFlag;
    private String code;
    private String year;
    private String verificationStatus;
    private String creatorId;
    private String modifierId;
    private String insertTime;
    private String modificationTime;
    private String process;
    private String activity;
    private String semesterStartDate;
    private String semesterEndDate;
    private String programCourseKey;
    private String entityCode;
    private String rollValue;

    public StudentNumbersInfoGetter() {
    }

	public StudentNumbersInfoGetter(String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate) {
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
	}
	public StudentNumbersInfoGetter(String programId, String CODE,
			String entityId, String year) {
		this.programId=programId;
		this.entityId=entityId;
		this.code=CODE;
		this.year=year;
	}
	public StudentNumbersInfoGetter(String entityId, String programId,
			String code, String year, String userId) {
		this.programId=programId;
		this.entityId=entityId;
		this.code=code;
		this.year=year;
		this.creatorId=userId;
	}



	public StudentNumbersInfoGetter(String entityId, String programId,
			String rollNumber, String year, String code, String userId) {
		
		this.entityId = entityId;
		this.programId = programId;
		this.systemValue = rollNumber;
		this.year = year;
		this.code = code;
		this.modifierId = userId;
		
	}

	public Date getSession() {
        return session;
    }

    public void setSession(Date session) {
        this.session = session;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
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

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public String getProgramCourseKey() {
        return programCourseKey;
    }

    public void setProgramCourseKey(String programCourseKey) {
        this.programCourseKey = programCourseKey;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

	public String getRollValue() {
		return rollValue;
	}

	public void setRollValue(String rollValue) {
		this.rollValue = rollValue;
	}
    
}
