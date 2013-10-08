/*
 * @(#) AwardSheetInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.awardsheet;


/**
 * Bean class for getting and setting award sheet related data
 * @author Manpreet Kaur
 * @date 03-03-2011
 * @version 1.0
 */
public class AwardSheetInfoGetter {
    private String rollNumber;
    private String enrollmentNumber;
    private String evaluationId;
    private String marks;
    private String grades;
    private String passfail;
    private String studentName;
    private String group;
    private String rule;
    private String idType;
    private String displayType;
    private String courseCode;
    private String universityId;
    private String programId;
    private String maximumMarks;
    private String courseName;
    private String entityId;
    private String entityType;
    private String branchId;
    private String specializationId;
    private String semesterCode;
    private String startDate;
    private String endDate;
    private String programCourseKey;
    private String creatorId;
    private String modifierId;
    private String totalInternal;
    private String totalExternal;
    private String total;
    private String approvalOrder;
    private String requestSender;
    private String requestSenderdesignation;
    private String requestSendername;
    private String requestGetter;
    private String requestgettername;
    private String requestGetterdesignation;
     private String extra;

    private String employeeCode;
    private String status;
    private String previousStatus;
    private String entityName;
    private String entityTypeName;
    private String branchName;
    private String specializationName;
    private String semesterName;
    private String programName;
    private String attemptNumber;
    private String resultSystem;
    private String code;
    private String name;
    private String internalGrade;
    private String externalGrade;
    private String finalGrade;
    private String semesterStartDate;
    private String semesterEndDate;
    private String reason;
    private String employeeId;
    private String employeeName;
    private String emailId;
    private String previousRequestSender;
    private String gradelimit ;
    private String marksfrom ;
    private String marksto ;
    private String sessionStartDate;
    private String sessionEndDate;
    private String attendence;
	private String oldmarks ;

	private String inserttime ;
	private String modificationtime ;
	private String completiondate ;
	private String requestdate ;
	private String displaydate ;
	private String requestedmarks ;
	private String requesterremarks ;
	private String submitdates;
	private String issuestatus ;

	public String getIssuestatus() {
		return issuestatus;
	}

	public void setIssuestatus(String issuestatus) {
		this.issuestatus = issuestatus;
	}

	public String getSubmitdates() {
		return submitdates;
	}

	public void setSubmitdates(String submitdates) {
		this.submitdates = submitdates;
	}

	public String getRequestedmarks() {
		return requestedmarks;
	}

	public void setRequestedmarks(String requestedmarks) {
		this.requestedmarks = requestedmarks;
	}

	public String getRequesterremarks() {
		return requesterremarks;
	}

	public void setRequesterremarks(String requesterremarks) {
		this.requesterremarks = requesterremarks;
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

	public String getGradelimit() {
		return gradelimit;
	}

	public void setGradelimit(String gradelimit) {
		this.gradelimit = gradelimit;
	}

	public String getMarksfrom() {
		return marksfrom;
	}

	public void setMarksfrom(String marksfrom) {
		this.marksfrom = marksfrom;
	}

	public String getMarksto() {
		return marksto;
	}

	public void setMarksto(String marksto) {
		this.marksto = marksto;
	}

	/**
	 * @return the previousRequestSender
	 */
	public String getPreviousRequestSender() {
		return previousRequestSender;
	}

	/**
	 * @param previousRequestSender the previousRequestSender to set
	 */
	public void setPreviousRequestSender(String previousRequestSender) {
		this.previousRequestSender = previousRequestSender;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	/**
	 * @param semesterStartDate the semesterStartDate to set
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	/**
	 * @return the semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}

	/**
	 * @param semesterEndDate the semesterEndDate to set
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

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

    /**
     * @return the evaluationId
     */
    public String getEvaluationId() {
        return evaluationId;
    }

    /**
     * @param evaluationId the evaluationId to set
     */
    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
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
     * @return the grades
     */
    public String getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(String grades) {
        this.grades = grades;
    }

    /**
     * @return the passfail
     */
    public String getPassfail() {
        return passfail;
    }

    /**
     * @param passfail the passfail to set
     */
    public void setPassfail(String passfail) {
        this.passfail = passfail;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the rule
     */
    public String getRule() {
        return rule;
    }

    /**
     * @param rule the rule to set
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the displayType
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     * @param displayType the displayType to set
     */
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
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
     * @return the maximumMarks
     */
    public String getMaximumMarks() {
        return maximumMarks;
    }

    /**
     * @param maximumMarks the maximumMarks to set
     */
    public void setMaximumMarks(String maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
     * @return the entityType
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * @param entityType the entityType to set
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
     * @return the creatorId
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId the creatorId to set
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    /**
     * @return the totalInternal
     */
    public String getTotalInternal() {
        return totalInternal;
    }

    /**
     * @param totalInternal the totalInternal to set
     */
    public void setTotalInternal(String totalInternal) {
        this.totalInternal = totalInternal;
    }

    /**
     * @return the totalExternal
     */
    public String getTotalExternal() {
        return totalExternal;
    }

    /**
     * @param totalExternal the totalExternal to set
     */
    public void setTotalExternal(String totalExternal) {
        this.totalExternal = totalExternal;
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
     * @return the approvalOrder
     */
    public String getApprovalOrder() {
        return approvalOrder;
    }

    /**
     * @param approvalOrder the approvalOrder to set
     */
    public void setApprovalOrder(String approvalOrder) {
        this.approvalOrder = approvalOrder;
    }

    /**
     * @return the requestSender
     */
    public String getRequestSender() {
        return requestSender;
    }

    /**
     * @param requestSender the requestSender to set
     */
    public void setRequestSender(String requestSender) {
        this.requestSender = requestSender;
    }

    /**
     * @return the requestGetter
     */
    public String getRequestGetter() {
        return requestGetter;
    }

    /**
     * @param requestGetter the requestGetter to set
     */
    public void setRequestGetter(String requestGetter) {
        this.requestGetter = requestGetter;
    }

    /**
     * @return the employeeCode
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * @param employeeCode the employeeCode to set
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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
     * @return the previousStatus
     */
    public String getPreviousStatus() {
        return previousStatus;
    }

    /**
     * @param previousStatus the previousStatus to set
     */
    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
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
	 * @return the entityTypeName
	 */
	public String getEntityTypeName() {
		return entityTypeName;
	}

	/**
	 * @param entityTypeName the entityTypeName to set
	 */
	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the specializationName
	 */
	public String getSpecializationName() {
		return specializationName;
	}

	/**
	 * @param specializationName the specializationName to set
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return semesterName;
	}

	/**
	 * @param semesterName the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
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
	 * @return the resultSystem
	 */
	public String getResultSystem() {
		return resultSystem;
	}

	/**
	 * @param resultSystem the resultSystem to set
	 */
	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the internalGrade
	 */
	public String getInternalGrade() {
		return internalGrade;
	}

	/**
	 * @param internalGrade the internalGrade to set
	 */
	public void setInternalGrade(String internalGrade) {
		this.internalGrade = internalGrade;
	}

	/**
	 * @return the externalGrade
	 */
	public String getExternalGrade() {
		return externalGrade;
	}

	/**
	 * @param externalGrade the externalGrade to set
	 */
	public void setExternalGrade(String externalGrade) {
		this.externalGrade = externalGrade;
	}

	/**
	 * @return the finalGrade
	 */
	public String getFinalGrade() {
		return finalGrade;
	}

	/**
	 * @param finalGrade the finalGrade to set
	 */
	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}


	public String getOldmarks() {
		return oldmarks;
	}

	public void setOldmarks(String oldmarks) {
		this.oldmarks = oldmarks;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getModificationtime() {
		return modificationtime;
	}

	public void setModificationtime(String modificationtime) {
		this.modificationtime = modificationtime;
	}

	public String getCompletiondate() {
		return completiondate;
	}

	public void setCompletiondate(String completiondate) {
		this.completiondate = completiondate;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getRequestSenderdesignation() {
		return requestSenderdesignation;
	}

	public void setRequestSenderdesignation(String requestSenderdesignation) {
		this.requestSenderdesignation = requestSenderdesignation;
	}

	public String getRequestSendername() {
		return requestSendername;
	}

	public void setRequestSendername(String requestSendername) {
		this.requestSendername = requestSendername;
	}

	public String getRequestgettername() {
		return requestgettername;
	}

	public void setRequestgettername(String requestgettername) {
		this.requestgettername = requestgettername;
	}

	public String getRequestGetterdesignation() {
		return requestGetterdesignation;
	}

	public void setRequestGetterdesignation(String requestGetterdesignation) {
		this.requestGetterdesignation = requestGetterdesignation;
	}

	public String getDisplaydate() {
		return displaydate;
	}

	public void setDisplaydate(String displaydate) {
		this.displaydate = displaydate;
	}
    public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getExtra() {
		return extra;
	}
}

