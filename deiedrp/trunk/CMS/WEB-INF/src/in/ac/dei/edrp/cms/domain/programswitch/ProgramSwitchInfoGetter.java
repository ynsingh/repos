/*
 * @(#) ProgramSwitchInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.programswitch;


/**
 * This bean file is associated with
 * Program Switch setup.
 * @author Ashish
 * @date 14 Mar 2011
 * @version 1.0
 */
public class ProgramSwitchInfoGetter {
    private String userId;
    private String flag;
    private String programId;
    private String programName;
    private String branchId;
    private String branchName;
    private String specializationId;
    private String specializationName;
    private String semesterCode;
    private String semesterName;
    private String oldProgramId;
    private String oldProgramName;
    private String oldBranchId;
    private String oldBranchName;
    private String oldSpecializationId;
    private String oldSpecializationName;
    private String oldSemesterCode;
    private String oldSemesterName;
    private String componentId;
    private String componentDescription;
    private String universityCode;
    private String activity;
    private String creatorId;
    private String creationTime;
    private String modifierId;
    private String modificationTime;
    private String switchRuleId;
    private String switchTypeId;
    private String oldSwitchRuleId;
    private String entityName;
    private String entityId;
    private String fromEntityId;
    private String fromEntityName;
    private String semesterSequence;
    
    

    /**
	 * @return the fromEntityId
	 */
	public String getFromEntityId() {
		return fromEntityId;
	}

	/**
	 * @param fromEntityId the fromEntityId to set
	 */
	public void setFromEntityId(String fromEntityId) {
		this.fromEntityId = fromEntityId;
	}

	/**
	 * @return the fromEntityName
	 */
	public String getFromEntityName() {
		return fromEntityName;
	}

	/**
	 * @param fromEntityName the fromEntityName to set
	 */
	public void setFromEntityName(String fromEntityName) {
		this.fromEntityName = fromEntityName;
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
         * @return the flag
         */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
    * @return the userId
    */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the oldProgramId
     */
    public String getOldProgramId() {
        return oldProgramId;
    }

    /**
     * @param oldProgramId the oldProgramId to set
     */
    public void setOldProgramId(String oldProgramId) {
        this.oldProgramId = oldProgramId;
    }

    /**
         * @return the oldProgramName
         */
    public String getOldProgramName() {
        return oldProgramName;
    }

    /**
     * @param oldProgramName the oldProgramName to set
     */
    public void setOldProgramName(String oldProgramName) {
        this.oldProgramName = oldProgramName;
    }

    /**
     * @return the oldBranchId
     */
    public String getOldBranchId() {
        return oldBranchId;
    }

    /**
     * @param oldBranchId the oldBranchId to set
     */
    public void setOldBranchId(String oldBranchId) {
        this.oldBranchId = oldBranchId;
    }

    /**
     * @return the oldBranchName
     */
    public String getOldBranchName() {
        return oldBranchName;
    }

    /**
     * @param oldBranchName the oldBranchName to set
     */
    public void setOldBranchName(String oldBranchName) {
        this.oldBranchName = oldBranchName;
    }

    /**
     * @return the oldSpecializationId
     */
    public String getOldSpecializationId() {
        return oldSpecializationId;
    }

    /**
     * @param oldSpecializationId the oldSpecializationId to set
     */
    public void setOldSpecializationId(String oldSpecializationId) {
        this.oldSpecializationId = oldSpecializationId;
    }

    /**
     * @return the oldSpecializationName
     */
    public String getOldSpecializationName() {
        return oldSpecializationName;
    }

    /**
     * @param oldSpecializationName the oldSpecializationName to set
     */
    public void setOldSpecializationName(String oldSpecializationName) {
        this.oldSpecializationName = oldSpecializationName;
    }

    /**
     * @return the oldSemesterCode
     */
    public String getOldSemesterCode() {
        return oldSemesterCode;
    }

    /**
     * @param oldSemesterCode the oldSemesterCode to set
     */
    public void setOldSemesterCode(String oldSemesterCode) {
        this.oldSemesterCode = oldSemesterCode;
    }

    /**
     * @return the oldSemesterName
     */
    public String getOldSemesterName() {
        return oldSemesterName;
    }

    /**
     * @param oldSemesterName the oldSemesterName to set
     */
    public void setOldSemesterName(String oldSemesterName) {
        this.oldSemesterName = oldSemesterName;
    }

    /**
    * @return the componentId
    */
    public String getComponentId() {
        return componentId;
    }

    /**
     * @param componentId the componentId to set
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    /**
     * @return the componentDescription
     */
    public String getComponentDescription() {
        return componentDescription;
    }

    /**
     * @param componentDescription the componentDescription to set
     */
    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    /**
     * @return the universityCode
     */
    public String getUniversityCode() {
        return universityCode;
    }

    /**
     * @param universityCode the universityCode to set
     */
    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
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
     * @return the creationTime
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
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
     * @return the modificationTime
     */
    public String getModificationTime() {
        return modificationTime;
    }

    /**
     * @param modificationTime the modificationTime to set
     */
    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    /**
     * @return the switchRuleId
     */
    public String getSwitchRuleId() {
        return switchRuleId;
    }

    /**
     * @param switchRuleId the switchRuleId to set
     */
    public void setSwitchRuleId(String switchRuleId) {
        this.switchRuleId = switchRuleId;
    }

    /**
     * @return the switchTypeId
     */
    public String getSwitchTypeId() {
        return switchTypeId;
    }

    /**
     * @param switchTypeId the switchTypeId to set
     */
    public void setSwitchTypeId(String switchTypeId) {
        this.switchTypeId = switchTypeId;
    }

    /**
     * @return the oldSwitchRuleId
     */
    public String getOldSwitchRuleId() {
        return oldSwitchRuleId;
    }

    /**
     * @param oldSwitchRuleId the oldSwitchRuleId to set
     */
    public void setOldSwitchRuleId(String oldSwitchRuleId) {
        this.oldSwitchRuleId = oldSwitchRuleId;
    }

	public void setSemesterSequence(String semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	public String getSemesterSequence() {
		return semesterSequence;
	}
}
