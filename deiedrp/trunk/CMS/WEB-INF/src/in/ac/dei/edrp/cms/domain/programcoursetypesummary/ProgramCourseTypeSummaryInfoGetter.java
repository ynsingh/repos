/*
 * @(#) ProgramCourseTypeSummaryInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.programcoursetypesummary;


/**
 * This bean file is associated with
 * program course type summary setup.
 * @author Ashish
 * @date 12 Feb 2011
 * @version 1.0
 */
public class ProgramCourseTypeSummaryInfoGetter {
    private String userId;
    private String componentId;
    private String componentDescription;
    private String programId;
    private String programName;
    private String branchId;
    private String branchName;
    private String specializationId;
    private String specializationName;
    private String semesterCode;
    private String semesterName;
    private String courseTypeCode;
    private String courseTypeDescription;
    private String branchGroupCode;
    private String specialGroupCode;
    private String semesterGroupCode;
    private String programCourseKey;
    private String activityFlag;
    private String minCredits;
    private String maxCredits;
    private String creatorId;
    private String creationTime;
    private String modifierId;
    private String modificationTime;
    private String entityId;//Add by Devendra
    private String entityName;//Add by Devendra
    private String universityId;//Add by Devendra
    private String semesterSequence; // added by Nupur
    
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
     * default constructor
     */
    public ProgramCourseTypeSummaryInfoGetter() {
    }

    public String getActivityFlag() {
        return activityFlag;
    }

    public void setActivityFlag(String activityFlag) {
        this.activityFlag = activityFlag;
    }

    public String getProgramCourseKey() {
        return programCourseKey;
    }

    public void setProgramCourseKey(String programCourseKey) {
        this.programCourseKey = programCourseKey;
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

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getCourseTypeCode() {
        return courseTypeCode;
    }

    public void setCourseTypeCode(String courseTypeCode) {
        this.courseTypeCode = courseTypeCode;
    }

    public String getCourseTypeDescription() {
        return courseTypeDescription;
    }

    public void setCourseTypeDescription(String courseTypeDescription) {
        this.courseTypeDescription = courseTypeDescription;
    }

    public String getBranchGroupCode() {
        return branchGroupCode;
    }

    public void setBranchGroupCode(String branchGroupCode) {
        this.branchGroupCode = branchGroupCode;
    }

    public String getSpecialGroupCode() {
        return specialGroupCode;
    }

    public void setSpecialGroupCode(String specialGroupCode) {
        this.specialGroupCode = specialGroupCode;
    }

    public String getSemesterGroupCode() {
        return semesterGroupCode;
    }

    public void setSemesterGroupCode(String semesterGroupCode) {
        this.semesterGroupCode = semesterGroupCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

	public void setSemesterSequence(String semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	public String getSemesterSequence() {
		return semesterSequence;
	}
}
