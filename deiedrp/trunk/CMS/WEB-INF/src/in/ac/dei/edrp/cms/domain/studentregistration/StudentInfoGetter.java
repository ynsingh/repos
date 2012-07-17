/*
 * StudentInfoGetter.java
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

import java.util.Date;
import java.util.List;


/**
 * Bean for student information
 * @author Manpreet Kaur
 * @date 20-12-2010
 * @version 1.0
 */
public class StudentInfoGetter {
    private String universityCode;
    private String programName;
    private String registrationNumber;
    private String formNumber;
    private String studentName;
    private String fatherName;
    private String motherName;
    private String gender;
    private String genderDescription;
    private String dateOfBirth;
    private String category;
    private String categoryDescription;
    private String userId;
    private String creatorId;
    private String modifierId;
    private String branchName;
    private boolean flag;
    private String cosValue;
    private String entityName;
    private String insertTime;
    private String modificationTime;
    private String studentId;
    private String programId;
    private String branchCode;
    private String entityId;
    private String semester;
    private String semesterCode;
    private String attempt;
    private String enrollmentNumber;
    private String sequenceNumber;
    private Date registrationDate;
    private Date registrationStartDate;
    private Date registrationEndDate;
    private String rollNumber;
    private String oldRollNumber;
    private String oldProgramId;
    private String oldBranchCode;
    private String oldEntityId;
    private String oldProgramName;
    private String oldBranchName;
    private String oldEntityName;
    private String oldSemester;
    private String oldSemesterCode;
    private String sessionStartDate;
    private String sessionEndDate;
    private String admissionMode;
    private String processedFlag;
    private String oldSpecialization;
    private String oldSpecializationDescription;
    private String newSpecialization;
    private String newSpecializationDescription;
    private String regRollNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String primaryEmailId;
    private String programCourseKey;
    private String courseCode;
    private String status;
    private String componentCode;
    private String componentDescription;
    private String notes;
    private String verified;
    private String minimumLectureCredit;
    private String probableSemester;
    private String probableSemesterStartDate;
    private String probableSemesterEndDate;
    private String probableAttemptNumber;
    private String probableRegisterDueDate;
    private String statusInSemester;
    private String maximumLectureCredit;
    private String minimumCourses;
    private String maximumCourses;
    /* newly added */
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherFirstName;
    private String motherMiddleName;
    private String motherLastName;
    private String perAddress;
    private String perCity;
    private String perState;
    private String perPincode;
    private String corAddress;
    private String corCity;
    private String corState;
    private String corPincode;
    private String officePhone;
    private String extraPhone;
    private String otherPhone;
    private String fax;
    private String semesterGroup;
    /* ashish */
    private String specializationId;
    private String reason;
    private String description;
    private String studentFirstName;
    private String studentMiddleName;
    private String studentLastName;
    private String ruleCodeOne;
    private String fromSemesterStatus;
    private int processCounter;
    private String processFlag;
    private String processStartTime;
    private String processEndTime;
    private int actualRecords;
    private int recordsProcessed;
    private int recordsFailed;
    private String secondaryEmailId;
    private String universityId;
    private String branchId;
    private String creationTime;
    private String fromSemester;
    private String toSemester;
    private String inSemester;
    private String outSemester;
    private String currentSemester;
    private String registerDate;
    private String modeOfEntry;
    private String oldBranchId;
    private String oldSpecializationId;
    private String semesterStartDate;
    private String semesterEndDate;
    private int attemptNumber;
    private String registrationStatus;
    private String reasonCode;
    private String reasonDescription;
    private String switchNumber;
    private String switchedDate;
    private String errorCode;  
    private String processId;
    private String activityId;
	private String semesterStatus;
	
	/**
	 * @return the semesterStatus
	 */
	public String getSemesterStatus() {
		return semesterStatus;
	}

	/**
	 * @param semesterStatus the semesterStatus to set
	 */
	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}
    
    //Added
	
    private String courseGroupCode;
     private String insertNumber;
     
     private String regCredit;
     private String theoryCredit;
     private String pracCredit;
     private String creditExcludeAudit;
    //added by rohit
     private String falg;
     private String falg1;
     private String falg2;


	//added by ashish mohan
   	private String entityNumber;
      private String programCode;
      private String semesterSequence;
      private String courseName;
      private String aggregateTheory;
      private String aggregatePractical;
      private List<StudentInfoGetter> courseList; 
      private List<StudentInfoGetter> remedialList;
	private String printAggregate;
     private List<String> courseCodeList;
     


	public List<String> getCourseCodeList() {
		return courseCodeList;
	}

	public void setCourseCodeList(List<String> courseCodeList) {
		this.courseCodeList = courseCodeList;
	}

	public String getPrintAggregate() {
		return printAggregate;
	}

	public void setPrintAggregate(String printAggregate) {
		this.printAggregate = printAggregate;
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

	public String getEntityNumber() {
		return entityNumber;
	}

	public void setEntityNumber(String entityNumber) {
		this.entityNumber = entityNumber;
	}
	
	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	
	
	/**
	 * @return the semesterSequence
	 */
	public String getSemesterSequence() {
		return semesterSequence;
	}

	/**
	 * @param semesterSequence the semesterSequence to set
	 */
	public void setSemesterSequence(String semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	/**
	 * @return the aggregateTheory
	 */
	public String getAggregateTheory() {
		return aggregateTheory;
	}

	/**
	 * @param aggregateTheory the aggregateTheory to set
	 */
	public void setAggregateTheory(String aggregateTheory) {
		this.aggregateTheory = aggregateTheory;
	}

	/**
	 * @return the aggregatePractical
	 */
	public String getAggregatePractical() {
		return aggregatePractical;
	}
	

	/**
	 * @param aggregatePractical the aggregatePractical to set
	 */
	public void setAggregatePractical(String aggregatePractical) {
		this.aggregatePractical = aggregatePractical;
	}

	/**
	 * @return the courseList
	 */
	public List<StudentInfoGetter> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<StudentInfoGetter> courseList) {
		this.courseList = courseList;
	}

	/**
	 * @return the remedialList
	 */
	public List<StudentInfoGetter> getRemedialList() {
		return remedialList;
	}

	/**
	 * @param remedialList the remedialList to set
	 */
	public void setRemedialList(List<StudentInfoGetter> remedialList) {
		this.remedialList = remedialList;
	}

	public StudentInfoGetter(String entityNumber,String programCode,String branchId,String semesterSequence,String rollNumber,
			String studentName,String category,String enrollmentNumber,String gender,String attemptNumber,String specializationId,List<StudentInfoGetter> courseList,
			List<StudentInfoGetter> remedialList,String aggregateTheory,String aggregatePractical)
	{
		this.entityNumber = entityNumber;
		this.programCode = programCode;
		this.branchId = branchId;
		this.semesterSequence = semesterSequence;
		this.rollNumber = rollNumber;
		this.studentName = studentName;
		this.category = category;
		this.enrollmentNumber = enrollmentNumber;
		this.gender = gender;
		this.attemptNumber = Integer.parseInt(attemptNumber);
		this.specializationId = specializationId;
		this.courseList = courseList;
		this.remedialList = remedialList;
		this.aggregateTheory = aggregateTheory;
		this.aggregatePractical = aggregatePractical;		
	}
	
	public StudentInfoGetter(String programName,String programCode,String branchId,String rollNumber,
			String studentName,String enrollmentNumber,String attemptNumber,String specializationId,
		  String status,String semesterSequence,List<StudentInfoGetter> remedialList)
	{
		this.programName = programName;
		this.programCode = programCode;
		this.branchId = branchId;
		this.semesterSequence = semesterSequence;
		this.rollNumber = rollNumber;
		this.studentName = studentName;
		this.enrollmentNumber = enrollmentNumber;
		this.attemptNumber = Integer.parseInt(attemptNumber);
		this.specializationId = specializationId;
		this.remedialList = remedialList;
		this.status=status;
		
	}
	/*
	 * added ended
	 */
     
    /**
     * default Constructor
     */
    public StudentInfoGetter() {
    }

    /**
     *
     * @param entityId
     * @param programId
     * @param branchId
     * @param specializationId
     * @param semesterCode
     * @param semesterStartDate
     * @param semesterEndDate
     */
    public StudentInfoGetter(String entityId, String programId,
        String branchId, String specializationId, String semesterCode,
        String semesterStartDate, String semesterEndDate) {
        this.entityId = entityId;
        this.programId = programId;
        this.branchCode = branchId;
        this.specializationId = specializationId;
        this.semesterCode = semesterCode;
        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
    }

    /**
     *
     * @param programId
     * @param branchId
     * @param specializationId
     * @param oldProgramId
     * @param oldBranchCode
     * @param oldSpecialization
     * @param fromSemester
     * @param toSemester
     */
    public StudentInfoGetter(String programId, String branchId,
        String specializationId, String oldProgramId, String oldBranchCode,
        String oldSpecialization, String fromSemester, String toSemester) {
        this.programId = programId;
        this.branchCode = branchId;
        this.specializationId = specializationId;
        this.oldProgramId = oldProgramId;
        this.oldBranchCode = oldBranchCode;
        this.oldSpecialization = oldSpecialization;
        this.fromSemester = fromSemester;
        this.toSemester = toSemester;
    }

    /**
     *
     * @param studentId
     * @param firstName
     * @param middleName
     * @param lastName
     * @param fatherName
     * @param dateOfBirth
     * @param gender
     * @param category
     * @param enrollmentNumber
     */
    public StudentInfoGetter(String studentId, String firstName,
        String middleName, String lastName, String fatherName,
        String dateOfBirth, String gender, String category,
        String enrollmentNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.category = category;
        this.enrollmentNumber = enrollmentNumber;
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
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	/**
	 * @return the formNumber
	 */
	public String getFormNumber() {
		return formNumber;
	}
	/**
	 * @param formNumber the formNumber to set
	 */
	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
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
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
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
	 * @return the genderDescription
	 */
	public String getGenderDescription() {
		return genderDescription;
	}
	/**
	 * @param genderDescription the genderDescription to set
	 */
	public void setGenderDescription(String genderDescription) {
		this.genderDescription = genderDescription;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	 * @return the categoryDescription
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}
	/**
	 * @param categoryDescription the categoryDescription to set
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
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
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the cosValue
	 */
	public String getCosValue() {
		return cosValue;
	}
	/**
	 * @param cosValue the cosValue to set
	 */
	public void setCosValue(String cosValue) {
		this.cosValue = cosValue;
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
	 * @return the insertTime
	 */
	public String getInsertTime() {
		return insertTime;
	}
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
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
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
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
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	/**
	 * @return the registrationStartDate
	 */
	public Date getRegistrationStartDate() {
		return registrationStartDate;
	}
	/**
	 * @param registrationStartDate the registrationStartDate to set
	 */
	public void setRegistrationStartDate(Date registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}
	/**
	 * @return the registrationEndDate
	 */
	public Date getRegistrationEndDate() {
		return registrationEndDate;
	}
	/**
	 * @param registrationEndDate the registrationEndDate to set
	 */
	public void setRegistrationEndDate(Date registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
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
	 * @return the oldRollNumber
	 */
	public String getOldRollNumber() {
		return oldRollNumber;
	}
	/**
	 * @param oldRollNumber the oldRollNumber to set
	 */
	public void setOldRollNumber(String oldRollNumber) {
		this.oldRollNumber = oldRollNumber;
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
	 * @return the oldBranchCode
	 */
	public String getOldBranchCode() {
		return oldBranchCode;
	}
	/**
	 * @param oldBranchCode the oldBranchCode to set
	 */
	public void setOldBranchCode(String oldBranchCode) {
		this.oldBranchCode = oldBranchCode;
	}
	/**
	 * @return the oldEntityId
	 */
	public String getOldEntityId() {
		return oldEntityId;
	}
	/**
	 * @param oldEntityId the oldEntityId to set
	 */
	public void setOldEntityId(String oldEntityId) {
		this.oldEntityId = oldEntityId;
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
	 * @return the oldEntityName
	 */
	public String getOldEntityName() {
		return oldEntityName;
	}
	/**
	 * @param oldEntityName the oldEntityName to set
	 */
	public void setOldEntityName(String oldEntityName) {
		this.oldEntityName = oldEntityName;
	}
	/**
	 * @return the oldSemester
	 */
	public String getOldSemester() {
		return oldSemester;
	}
	/**
	 * @param oldSemester the oldSemester to set
	 */
	public void setOldSemester(String oldSemester) {
		this.oldSemester = oldSemester;
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
	 * @return the sessionStartDate
	 */
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	/**
	 * @param sessionStartDate the sessionStartDate to set
	 */
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	/**
	 * @return the sessionEndDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	/**
	 * @param sessionEndDate the sessionEndDate to set
	 */
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	/**
	 * @return the admissionMode
	 */
	public String getAdmissionMode() {
		return admissionMode;
	}
	/**
	 * @param admissionMode the admissionMode to set
	 */
	public void setAdmissionMode(String admissionMode) {
		this.admissionMode = admissionMode;
	}
	/**
	 * @return the processedFlag
	 */
	public String getProcessedFlag() {
		return processedFlag;
	}
	/**
	 * @param processedFlag the processedFlag to set
	 */
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}
	/**
	 * @return the oldSpecialization
	 */
	public String getOldSpecialization() {
		return oldSpecialization;
	}
	/**
	 * @param oldSpecialization the oldSpecialization to set
	 */
	public void setOldSpecialization(String oldSpecialization) {
		this.oldSpecialization = oldSpecialization;
	}
	/**
	 * @return the oldSpecializationDescription
	 */
	public String getOldSpecializationDescription() {
		return oldSpecializationDescription;
	}
	/**
	 * @param oldSpecializationDescription the oldSpecializationDescription to set
	 */
	public void setOldSpecializationDescription(String oldSpecializationDescription) {
		this.oldSpecializationDescription = oldSpecializationDescription;
	}
	/**
	 * @return the newSpecialization
	 */
	public String getNewSpecialization() {
		return newSpecialization;
	}
	/**
	 * @param newSpecialization the newSpecialization to set
	 */
	public void setNewSpecialization(String newSpecialization) {
		this.newSpecialization = newSpecialization;
	}
	/**
	 * @return the newSpecializationDescription
	 */
	public String getNewSpecializationDescription() {
		return newSpecializationDescription;
	}
	/**
	 * @param newSpecializationDescription the newSpecializationDescription to set
	 */
	public void setNewSpecializationDescription(String newSpecializationDescription) {
		this.newSpecializationDescription = newSpecializationDescription;
	}
	/**
	 * @return the regRollNumber
	 */
	public String getRegRollNumber() {
		return regRollNumber;
	}
	/**
	 * @param regRollNumber the regRollNumber to set
	 */
	public void setRegRollNumber(String regRollNumber) {
		this.regRollNumber = regRollNumber;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the primaryEmailId
	 */
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}
	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
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
	 * @return the componentCode
	 */
	public String getComponentCode() {
		return componentCode;
	}
	/**
	 * @param componentCode the componentCode to set
	 */
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
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
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the verified
	 */
	public String getVerified() {
		return verified;
	}
	/**
	 * @param verified the verified to set
	 */
	public void setVerified(String verified) {
		this.verified = verified;
	}
	/**
	 * @return the minimumLectureCredit
	 */
	public String getMinimumLectureCredit() {
		return minimumLectureCredit;
	}
	/**
	 * @param minimumLectureCredit the minimumLectureCredit to set
	 */
	public void setMinimumLectureCredit(String minimumLectureCredit) {
		this.minimumLectureCredit = minimumLectureCredit;
	}
	/**
	 * @return the probableSemester
	 */
	public String getProbableSemester() {
		return probableSemester;
	}
	/**
	 * @param probableSemester the probableSemester to set
	 */
	public void setProbableSemester(String probableSemester) {
		this.probableSemester = probableSemester;
	}
	/**
	 * @return the probableSemesterStartDate
	 */
	public String getProbableSemesterStartDate() {
		return probableSemesterStartDate;
	}
	/**
	 * @param probableSemesterStartDate the probableSemesterStartDate to set
	 */
	public void setProbableSemesterStartDate(String probableSemesterStartDate) {
		this.probableSemesterStartDate = probableSemesterStartDate;
	}
	/**
	 * @return the probableSemesterEndDate
	 */
	public String getProbableSemesterEndDate() {
		return probableSemesterEndDate;
	}
	/**
	 * @param probableSemesterEndDate the probableSemesterEndDate to set
	 */
	public void setProbableSemesterEndDate(String probableSemesterEndDate) {
		this.probableSemesterEndDate = probableSemesterEndDate;
	}
	
	/**
	 * @return the probableRegisterDueDate
	 */
	public String getProbableRegisterDueDate() {
		return probableRegisterDueDate;
	}
	/**
	 * @param probableRegisterDueDate the probableRegisterDueDate to set
	 */
	public void setProbableRegisterDueDate(String probableRegisterDueDate) {
		this.probableRegisterDueDate = probableRegisterDueDate;
	}
	/**
	 * @return the statusInSemester
	 */
	public String getStatusInSemester() {
		return statusInSemester;
	}
	/**
	 * @param statusInSemester the statusInSemester to set
	 */
	public void setStatusInSemester(String statusInSemester) {
		this.statusInSemester = statusInSemester;
	}
	/**
	 * @return the maximumLectureCredit
	 */
	public String getMaximumLectureCredit() {
		return maximumLectureCredit;
	}
	/**
	 * @param maximumLectureCredit the maximumLectureCredit to set
	 */
	public void setMaximumLectureCredit(String maximumLectureCredit) {
		this.maximumLectureCredit = maximumLectureCredit;
	}
	/**
	 * @return the minimumCourses
	 */
	public String getMinimumCourses() {
		return minimumCourses;
	}
	/**
	 * @param minimumCourses the minimumCourses to set
	 */
	public void setMinimumCourses(String minimumCourses) {
		this.minimumCourses = minimumCourses;
	}
	/**
	 * @return the maximumCourses
	 */
	public String getMaximumCourses() {
		return maximumCourses;
	}
	/**
	 * @param maximumCourses the maximumCourses to set
	 */
	public void setMaximumCourses(String maximumCourses) {
		this.maximumCourses = maximumCourses;
	}
	/**
	 * @return the fatherFirstName
	 */
	public String getFatherFirstName() {
		return fatherFirstName;
	}
	/**
	 * @param fatherFirstName the fatherFirstName to set
	 */
	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}
	/**
	 * @return the fatherMiddleName
	 */
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}
	/**
	 * @param fatherMiddleName the fatherMiddleName to set
	 */
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}
	/**
	 * @return the fatherLastName
	 */
	public String getFatherLastName() {
		return fatherLastName;
	}
	/**
	 * @param fatherLastName the fatherLastName to set
	 */
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	
	/**
	 * @return the motherMiddleName
	 */
	public String getMotherMiddleName() {
		return motherMiddleName;
	}
	/**
	 * @param motherMiddleName the motherMiddleName to set
	 */
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}
	/**
	 * @return the motherLastName
	 */
	public String getMotherLastName() {
		return motherLastName;
	}
	/**
	 * @param motherLastName the motherLastName to set
	 */
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	/**
	 * @return the motherFirstName
	 */
	public String getMotherFirstName() {
		return motherFirstName;
	}
	/**
	 * @param motherFirstName the motherFirstName to set
	 */
	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}
	/**
	 * @return the perAddress
	 */
	public String getPerAddress() {
		return perAddress;
	}
	/**
	 * @param perAddress the perAddress to set
	 */
	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}
	/**
	 * @return the perCity
	 */
	public String getPerCity() {
		return perCity;
	}
	/**
	 * @param perCity the perCity to set
	 */
	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}
	/**
	 * @return the perState
	 */
	public String getPerState() {
		return perState;
	}
	/**
	 * @param perState the perState to set
	 */
	public void setPerState(String perState) {
		this.perState = perState;
	}
	
	/**
	 * @return the corAddress
	 */
	public String getCorAddress() {
		return corAddress;
	}
	/**
	 * @param corAddress the corAddress to set
	 */
	public void setCorAddress(String corAddress) {
		this.corAddress = corAddress;
	}
	/**
	 * @return the corCity
	 */
	public String getCorCity() {
		return corCity;
	}
	/**
	 * @param corCity the corCity to set
	 */
	public void setCorCity(String corCity) {
		this.corCity = corCity;
	}
	/**
	 * @return the corState
	 */
	public String getCorState() {
		return corState;
	}
	/**
	 * @param corState the corState to set
	 */
	public void setCorState(String corState) {
		this.corState = corState;
	}
	
	/**
	 * @return the officePhone
	 */
	public String getOfficePhone() {
		return officePhone;
	}
	/**
	 * @param officePhone the officePhone to set
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	/**
	 * @return the extraPhone
	 */
	public String getExtraPhone() {
		return extraPhone;
	}
	/**
	 * @param extraPhone the extraPhone to set
	 */
	public void setExtraPhone(String extraPhone) {
		this.extraPhone = extraPhone;
	}
	/**
	 * @return the otherPhone
	 */
	public String getOtherPhone() {
		return otherPhone;
	}
	/**
	 * @param otherPhone the otherPhone to set
	 */
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the studentFirstName
	 */
	public String getStudentFirstName() {
		return studentFirstName;
	}

	/**
	 * @param studentFirstName the studentFirstName to set
	 */
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	/**
	 * @return the studentMiddleName
	 */
	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	/**
	 * @param studentMiddleName the studentMiddleName to set
	 */
	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	/**
	 * @return the studentLastName
	 */
	public String getStudentLastName() {
		return studentLastName;
	}

	/**
	 * @param studentLastName the studentLastName to set
	 */
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	/**
	 * @return the ruleCodeOne
	 */
	public String getRuleCodeOne() {
		return ruleCodeOne;
	}

	/**
	 * @param ruleCodeOne the ruleCodeOne to set
	 */
	public void setRuleCodeOne(String ruleCodeOne) {
		this.ruleCodeOne = ruleCodeOne;
	}

	/**
	 * @return the fromSemesterStatus
	 */
	public String getFromSemesterStatus() {
		return fromSemesterStatus;
	}

	/**
	 * @param fromSemesterStatus the fromSemesterStatus to set
	 */
	public void setFromSemesterStatus(String fromSemesterStatus) {
		this.fromSemesterStatus = fromSemesterStatus;
	}

	/**
	 * @return the processCounter
	 */
	public int getProcessCounter() {
		return processCounter;
	}

	/**
	 * @param processCounter the processCounter to set
	 */
	public void setProcessCounter(int processCounter) {
		this.processCounter = processCounter;
	}

	/**
	 * @return the processFlag
	 */
	public String getProcessFlag() {
		return processFlag;
	}

	/**
	 * @param processFlag the processFlag to set
	 */
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	/**
	 * @return the processStartTime
	 */
	public String getProcessStartTime() {
		return processStartTime;
	}

	/**
	 * @param processStartTime the processStartTime to set
	 */
	public void setProcessStartTime(String processStartTime) {
		this.processStartTime = processStartTime;
	}

	/**
	 * @return the processEndTime
	 */
	public String getProcessEndTime() {
		return processEndTime;
	}

	/**
	 * @param processEndTime the processEndTime to set
	 */
	public void setProcessEndTime(String processEndTime) {
		this.processEndTime = processEndTime;
	}

	/**
	 * @return the actualRecords
	 */
	public int getActualRecords() {
		return actualRecords;
	}

	/**
	 * @param actualRecords the actualRecords to set
	 */
	public void setActualRecords(int actualRecords) {
		this.actualRecords = actualRecords;
	}

	/**
	 * @return the recordsProcessed
	 */
	public int getRecordsProcessed() {
		return recordsProcessed;
	}

	/**
	 * @param recordsProcessed the recordsProcessed to set
	 */
	public void setRecordsProcessed(int recordsProcessed) {
		this.recordsProcessed = recordsProcessed;
	}

	/**
	 * @return the recordsFailed
	 */
	public int getRecordsFailed() {
		return recordsFailed;
	}

	/**
	 * @param recordsFailed the recordsFailed to set
	 */
	public void setRecordsFailed(int recordsFailed) {
		this.recordsFailed = recordsFailed;
	}

	/**
	 * @return the secondaryEmailId
	 */
	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	/**
	 * @param secondaryEmailId the secondaryEmailId to set
	 */
	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
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
	 * @return the fromSemester
	 */
	public String getFromSemester() {
		return fromSemester;
	}

	/**
	 * @param fromSemester the fromSemester to set
	 */
	public void setFromSemester(String fromSemester) {
		this.fromSemester = fromSemester;
	}

	/**
	 * @return the toSemester
	 */
	public String getToSemester() {
		return toSemester;
	}

	/**
	 * @param toSemester the toSemester to set
	 */
	public void setToSemester(String toSemester) {
		this.toSemester = toSemester;
	}

	/**
	 * @return the inSemester
	 */
	public String getInSemester() {
		return inSemester;
	}

	/**
	 * @param inSemester the inSemester to set
	 */
	public void setInSemester(String inSemester) {
		this.inSemester = inSemester;
	}

	/**
	 * @return the outSemester
	 */
	public String getOutSemester() {
		return outSemester;
	}

	/**
	 * @param outSemester the outSemester to set
	 */
	public void setOutSemester(String outSemester) {
		this.outSemester = outSemester;
	}

	/**
	 * @return the currentSemester
	 */
	public String getCurrentSemester() {
		return currentSemester;
	}

	/**
	 * @param currentSemester the currentSemester to set
	 */
	public void setCurrentSemester(String currentSemester) {
		this.currentSemester = currentSemester;
	}

	/**
	 * @return the registerDate
	 */
	public String getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the modeOfEntry
	 */
	public String getModeOfEntry() {
		return modeOfEntry;
	}

	/**
	 * @param modeOfEntry the modeOfEntry to set
	 */
	public void setModeOfEntry(String modeOfEntry) {
		this.modeOfEntry = modeOfEntry;
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
	 * @return the attemptNumber
	 */
	public int getAttemptNumber() {
		return attemptNumber;
	}

	/**
	 * @param attemptNumber the attemptNumber to set
	 */
	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	/**
	 * @return the registrationStatus
	 */
	public String getRegistrationStatus() {
		return registrationStatus;
	}

	/**
	 * @param registrationStatus the registrationStatus to set
	 */
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the reasonDescription
	 */
	public String getReasonDescription() {
		return reasonDescription;
	}

	/**
	 * @param reasonDescription the reasonDescription to set
	 */
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
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
	 * @return the switchedDate
	 */
	public String getSwitchedDate() {
		return switchedDate;
	}

	/**
	 * @param switchedDate the switchedDate to set
	 */
	public void setSwitchedDate(String switchedDate) {
		this.switchedDate = switchedDate;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the semesterGroup
	 */
	public String getSemesterGroup() {
		return semesterGroup;
	}

	/**
	 * @param semesterGroup the semesterGroup to set
	 */
	public void setSemesterGroup(String semesterGroup) {
		this.semesterGroup = semesterGroup;
	}

	/**
	 * @return the attempt
	 */
	public String getAttempt() {
		return attempt;
	}

	/**
	 * @param attempt the attempt to set
	 */
	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}

	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the probableAttemptNumber
	 */
	public String getProbableAttemptNumber() {
		return probableAttemptNumber;
	}

	/**
	 * @param probableAttemptNumber the probableAttemptNumber to set
	 */
	public void setProbableAttemptNumber(String probableAttemptNumber) {
		this.probableAttemptNumber = probableAttemptNumber;
	}

	/**
	 * @return the perPincode
	 */
	public String getPerPincode() {
		return perPincode;
	}

	/**
	 * @param perPincode the perPincode to set
	 */
	public void setPerPincode(String perPincode) {
		this.perPincode = perPincode;
	}

	/**
	 * @return the corPincode
	 */
	public String getCorPincode() {
		return corPincode;
	}

	/**
	 * @param corPincode the corPincode to set
	 */
	public void setCorPincode(String corPincode) {
		this.corPincode = corPincode;
	}

	public String getCourseGroupCode() {
		return courseGroupCode;
	}

	public void setCourseGroupCode(String courseGroupCode) {
		this.courseGroupCode = courseGroupCode;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the insertNumber
	 */
	public String getInsertNumber() {
		return insertNumber;
	}

	/**
	 * @param insertNumber the insertNumber to set
	 */
	public void setInsertNumber(String insertNumber) {
		this.insertNumber = insertNumber;
	}

	public void setRegCredit(String regCredit) {
		this.regCredit = regCredit;
	}

	public String getRegCredit() {
		return regCredit;
	}

	public void setTheoryCredit(String theoryCredit) {
		this.theoryCredit = theoryCredit;
	}

	public String getTheoryCredit() {
		return theoryCredit;
	}

	public void setPracCredit(String pracCredit) {
		this.pracCredit = pracCredit;
	}

	public String getPracCredit() {
		return pracCredit;
	}

	public void setCreditExcludeAudit(String creditExcludeAudit) {
		this.creditExcludeAudit = creditExcludeAudit;
	}

	public String getCreditExcludeAudit() {
		return creditExcludeAudit;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	public String getFalg() {
		return falg;
	}

	public void setFalg1(String falg1) {
		this.falg1 = falg1;
	}

	public String getFalg1() {
		return falg1;
	}

	public void setFalg2(String falg2) {
		this.falg2 = falg2;
	}

	public String getFalg2() {
		return falg2;
	}


	
   
}
