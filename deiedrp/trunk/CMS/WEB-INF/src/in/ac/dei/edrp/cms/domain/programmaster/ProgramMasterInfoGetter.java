/*
 * @(#) ProgramMasterInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.programmaster;

/**
 * Bean class for Program Master related data
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public class ProgramMasterInfoGetter {
	String entityId;
    String programId;
    String programCode;
    String programName;
    String programType;
    String ProgramMode;
    String branch;
    String specilization;
    String numberOfTerms;
    String totalCredits;
    String numberOfAttemptAllowed;
    String maxNumberOfFailSubjects;
    String fixedDuration;
    String ugOrPg;
    String insertTime;
    String modificationTime;
    String creatorId;
    String tencodes;
    String minimunDuration;
    String maximumDuration;
    String startdate;
    String oldstartdate;
    String branchname;
    String specializationCode;
    String specname;
    String branchcode;
    String category;
    String categoryCode;
    String percentageSeats;
    boolean reservation;
    String universityId;
    String systemCode;
    String systemValue;
    String modifierId;
    String printAggregate;
    String modeName;
    String typeName;
    String maxRegSemester;
    String dgpa;
    String creditRequired;
    String fixedOrVariableCredit;
    String activeSemester;
    String endDate;
    String courseCode;
    String programCourseKey;
    String programDescription;
    String displayType;
    
    String semesterStartDate; // added by ankit
    String semesterEndDate;	// added by ankit 
    String  domainName; // added by Mandeep
    String  domainCode; // added by Mandeep
    
    private String approvalOrder;
    private String requestGetter;
    private String employeeCode;
    private String requestSender;
    
    
    
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

	public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }



    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getProgramMode() {
        return ProgramMode;
    }

    public void setProgramMode(String programMode) {
        ProgramMode = programMode;
    }

    public String getNumberOfTerms() {
        return numberOfTerms;
    }

    public void setNumberOfTerms(String numberOfTerms) {
        this.numberOfTerms = numberOfTerms;
    }

    public String getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(String totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getNumberOfAttemptAllowed() {
        return numberOfAttemptAllowed;
    }

    public void setNumberOfAttemptAllowed(String numberOfAttemptAllowed) {
        this.numberOfAttemptAllowed = numberOfAttemptAllowed;
    }

    public String getMaxNumberOfFailSubjects() {
        return maxNumberOfFailSubjects;
    }

    public void setMaxNumberOfFailSubjects(String maxNumberOfFailSubjects) {
        this.maxNumberOfFailSubjects = maxNumberOfFailSubjects;
    }

    public String getFixedDuration() {
        return fixedDuration;
    }

    public void setFixedDuration(String fixedDuration) {
        this.fixedDuration = fixedDuration;
    }

    public String getUgOrPg() {
        return ugOrPg;
    }

    public void setUgOrPg(String ugOrPg) {
        this.ugOrPg = ugOrPg;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getTencodes() {
        return tencodes;
    }

    public void setTencodes(String tencodes) {
        this.tencodes = tencodes;
    }

    public String getMinimunDuration() {
        return minimunDuration;
    }

    public void setMinimunDuration(String minimunDuration) {
        this.minimunDuration = minimunDuration;
    }

    public String getMaximumDuration() {
        return maximumDuration;
    }

    public void setMaximumDuration(String maximumDuration) {
        this.maximumDuration = maximumDuration;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getOldstartdate() {
        return oldstartdate;
    }

    public void setOldstartdate(String oldstartdate) {
        this.oldstartdate = oldstartdate;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPercentageSeats() {
        return percentageSeats;
    }

    public void setPercentageSeats(String percentageSeats) {
        this.percentageSeats = percentageSeats;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getPrintAggregate() {
        return printAggregate;
    }

    public void setPrintAggregate(String printAggregate) {
        this.printAggregate = printAggregate;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaxRegSemester() {
        return maxRegSemester;
    }

    public void setMaxRegSemester(String maxRegSemester) {
        this.maxRegSemester = maxRegSemester;
    }

    public String getDgpa() {
        return dgpa;
    }

    public void setDgpa(String dgpa) {
        this.dgpa = dgpa;
    }

    public String getCreditRequired() {
        return creditRequired;
    }

    public void setCreditRequired(String creditRequired) {
        this.creditRequired = creditRequired;
    }

    public String getFixedOrVariableCredit() {
        return fixedOrVariableCredit;
    }

    public void setFixedOrVariableCredit(String fixedOrVariableCredit) {
        this.fixedOrVariableCredit = fixedOrVariableCredit;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSpecilization() {
        return specilization;
    }

    public void setSpecilization(String specilization) {
        this.specilization = specilization;
    }

    public String getActiveSemester() {
        return activeSemester;
    }

    public void setActiveSemester(String activeSemester) {
        this.activeSemester = activeSemester;
    }

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
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
	 * @return the programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}

	/**
	 * @param programDescription the programDescription to set
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the domainCode
	 */
	public String getDomainCode() {
		return domainCode;
	}

	/**
	 * @param domainCode the domainCode to set
	 */
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
}
