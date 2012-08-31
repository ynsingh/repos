/*
 * @(#) SummarySheetBean.java
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
package in.ac.dei.edrp.client.summarysheet;

import java.io.Serializable;

import java.util.List;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class SummarySheetBean implements Serializable {
    private String sessionStartDate;
    private String sessionEndDate;
    private String registrationNumber;
    private String universityId;
    private String entityId;
    private String entityCode;
    private String facultyRegNo;
    private String programId;
    private String universityName;
    private String entityName;
    private String programName;
    private String studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String dob;
    private String gender;
    private String category;
    private String phoneNumber;
    private String otherPhone;
    private String ddNo;
    private String ddAmount;
    private String ddDate;
    private String bankName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    private String componentId;
    private String componentDescription;
    private String componentType;
    private String componentWeightage;
    private String weightageFlag;
    private String boardFlag;
    private String specialWeightageFlag;
    private String componentCriteriaFlag;
    private String ugPg;
    private String totalMarks;
    private String marksObtained;
    private String percentage;
    private String score;
    private String board;
    private Boolean weightage;
    private String ruleNumber;
    private String sequenceNumber;
    private String paperCode;
    private String paperDescription;
    private String grouping;
    private String docId;
    private String docName;
    private String docPath;
    private String userId;
    private String photoPath;
    private String formNumber;
    private String firstDegreeCode;
    private List<String> programList;
    private List<String> programNameList;
    private List<SummarySheetBean> paperGroupList;
    private List<SummarySheetBean> academicList;
    private List<SummarySheetBean> attachmentList;
    private List<SummarySheetBean> degreeList;
    private List<SummarySheetBean> cosCodeList;
    private List<String> entityIdList;
    private String cosCode;
    private String cosDescription;
    private String userEmailId;
    private Boolean staffWeightage;
    private String universityNickName;
    private String centerCode;
    private String centerDescription;
    private List<SummarySheetBean> centerCodeList;
    private String nationality;
    private String religion;
    private String guardian;
    private String facRegNumber;
    private List<String> registrationNumList;
    private String fileSeparator;
    private String minorityDesc;
    private String maritalStatusDesc;
    private String minority;
    private String maritalStatus;
    
    

    public List<String> getEntityIdList() {
		return entityIdList;
	}

	public void setEntityIdList(List<String> entityIdList) {
		this.entityIdList = entityIdList;
	}

	public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Boolean getWeightage() {
        return weightage;
    }

    public void setWeightage(Boolean weightage) {
        this.weightage = weightage;
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

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getFacultyRegNo() {
        return facultyRegNo;
    }

    public void setFacultyRegNo(String facultyRegNo) {
        this.facultyRegNo = facultyRegNo;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherFirstName() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherFirstName() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName = motherFirstName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getDdNo() {
        return ddNo;
    }

    public void setDdNo(String ddNo) {
        this.ddNo = ddNo;
    }

    public String getDdAmount() {
        return ddAmount;
    }

    public void setDdAmount(String ddAmount) {
        this.ddAmount = ddAmount;
    }

    public String getDdDate() {
        return ddDate;
    }

    public void setDdDate(String ddDate) {
        this.ddDate = ddDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentWeightage() {
        return componentWeightage;
    }

    public void setComponentWeightage(String componentWeightage) {
        this.componentWeightage = componentWeightage;
    }

    public String getWeightageFlag() {
        return weightageFlag;
    }

    public void setWeightageFlag(String weightageFlag) {
        this.weightageFlag = weightageFlag;
    }

    public String getBoardFlag() {
        return boardFlag;
    }

    public void setBoardFlag(String boardFlag) {
        this.boardFlag = boardFlag;
    }

    public String getSpecialWeightageFlag() {
        return specialWeightageFlag;
    }

    public void setSpecialWeightageFlag(String specialWeightageFlag) {
        this.specialWeightageFlag = specialWeightageFlag;
    }

    public String getComponentCriteriaFlag() {
        return componentCriteriaFlag;
    }

    public void setComponentCriteriaFlag(String componentCriteriaFlag) {
        this.componentCriteriaFlag = componentCriteriaFlag;
    }

    public String getUgPg() {
        return ugPg;
    }

    public void setUgPg(String ugPg) {
        this.ugPg = ugPg;
    }

    public String getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(String ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getPaperDescription() {
        return paperDescription;
    }

    public void setPaperDescription(String paperDescription) {
        this.paperDescription = paperDescription;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<String> getProgramList() {
        return programList;
    }

    public void setProgramList(List<String> programList) {
        this.programList = programList;
    }

    public List<SummarySheetBean> getPaperGroupList() {
        return paperGroupList;
    }

    public void setPaperGroupList(List<SummarySheetBean> paperGroupList) {
        this.paperGroupList = paperGroupList;
    }

    public List<SummarySheetBean> getAcademicList() {
        return academicList;
    }

    public void setAcademicList(List<SummarySheetBean> academicList) {
        this.academicList = academicList;
    }

    public List<SummarySheetBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<SummarySheetBean> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setFirstDegreeCode(String firstDegreeCode) {
        this.firstDegreeCode = firstDegreeCode;
    }

    public String getFirstDegreeCode() {
        return firstDegreeCode;
    }

	public void setProgramNameList(List<String> programNameList) {
		this.programNameList = programNameList;
	}

	public List<String> getProgramNameList() {
		return programNameList;
	}

	public void setDegreeList(List<SummarySheetBean> degreeList) {
		this.degreeList = degreeList;
	}

	public List<SummarySheetBean> getDegreeList() {
		return degreeList;
	}

	public String getCosCode() {
		return cosCode;
	}

	public void setCosCode(String cosCode) {
		this.cosCode = cosCode;
	}

	public String getCosDescription() {
		return cosDescription;
	}

	public void setCosDescription(String cosDescription) {
		this.cosDescription = cosDescription;
	}

	public List<SummarySheetBean> getCosCodeList() {
		return cosCodeList;
	}

	public void setCosCodeList(List<SummarySheetBean> cosCodeList) {
		this.cosCodeList = cosCodeList;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public Boolean getStaffWeightage() {
		return staffWeightage;
	}

	public void setStaffWeightage(Boolean staffWeightage) {
		this.staffWeightage = staffWeightage;
	}

	public String getUniversityNickName() {
		return universityNickName;
	}

	public void setUniversityNickName(String universityNickName) {
		this.universityNickName = universityNickName;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterDescription() {
		return centerDescription;
	}

	public void setCenterDescription(String centerDescription) {
		this.centerDescription = centerDescription;
	}

	public List<SummarySheetBean> getCenterCodeList() {
		return centerCodeList;
	}

	public void setCenterCodeList(List<SummarySheetBean> centerCodeList) {
		this.centerCodeList = centerCodeList;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public String getFacRegNumber() {
		return facRegNumber;
	}

	public void setFacRegNumber(String facRegNumber) {
		this.facRegNumber = facRegNumber;
	}

	public List<String> getRegistrationNumList() {
		return registrationNumList;
	}

	public void setRegistrationNumList(List<String> registrationNumList) {
		this.registrationNumList = registrationNumList;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	/**
	 * @return the minorityDesc
	 */
	public String getMinorityDesc() {
		return minorityDesc;
	}

	/**
	 * @param minorityDesc the minorityDesc to set
	 */
	public void setMinorityDesc(String minorityDesc) {
		this.minorityDesc = minorityDesc;
	}

	/**
	 * @return the maritalStatusDesc
	 */
	public String getMaritalStatusDesc() {
		return maritalStatusDesc;
	}

	/**
	 * @param maritalStatusDesc the maritalStatusDesc to set
	 */
	public void setMaritalStatusDesc(String maritalStatusDesc) {
		this.maritalStatusDesc = maritalStatusDesc;
	}

	/**
	 * @return the minority
	 */
	public String getMinority() {
		return minority;
	}

	/**
	 * @param minority the minority to set
	 */
	public void setMinority(String minority) {
		this.minority = minority;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	
}
