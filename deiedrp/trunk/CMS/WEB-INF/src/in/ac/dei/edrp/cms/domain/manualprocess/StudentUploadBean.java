package in.ac.dei.edrp.cms.domain.manualprocess;
import java.util.List;


public class StudentUploadBean {
	
	private String universityId;
	private String entityId;
	private String programId;
	private String branchId;
	private String semesterSequence;
	private String semesterCode;
	private String specializationId;
	private String rollNumber;
	private String studentName;
	private String studentFirstName;
	private String studentLastName;
	private String studentId;
	private String gender;
	private String attemptNumber;
	private List<StudentUploadBean> courseList;
	private List<StudentUploadBean> remedialCourseList;
	private List<String> previousPercentage;
	private String CGPA;
	private String resultCode;
	private String gradeCode;
	private String ugPgCode;
	private String finalSemesterCode;
	private String yearOfRegistration;
	private String evenSemesterRemInd;
	private String category;
	private String enrollmentNumber;
	private String tenCodes;
	private String registrationDate;
	private String noOfRemedials;
	private String programCode; 
	private String programCourseKey;
	private String semesterStartDate;
	private String semesterEndDate;
	private String registrationDueDate;
	private String courseCode;
	private String originalCourseCode;
	private String courseStatus;
	private String studentStatus;
	private String creatorId;
	private String internalMarks;
	private String externalMarks;
	private String cummulativeMarks;
	private String courseGroup;
	private String internalGrades;
	private String externalGrades;
	private String cummulativeGrades;
	private String courseCategory;
	private String courseClassification;
	private String credits;
	private String earnedCredits;
	private String pointSecuredSgpa;
	private String pointSecuredCgpa;
	private String earnedCreditCgpa;
	private String sessionStartDate;
	private String sessionEndDate;
	private String division;
	private String statusSRSH;
	private String statusStudentProgram;
	private String currentSemSGPA;
	private String value;
	private ErrorLogBean errorLogBean;
    private String passedFromSession;
	private String passedToSession;
	private String registeredCredit;
	private String registeredTheoryCreditExcludingAudit;
	private String registeredPracticalCreditExcludingAudit;
	private String registrationCreditExcludingAudit;
	
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the courseGroup
	 */
	public String getCourseGroup() {
		return courseGroup;
	}
	/**
	 * @param courseGroup the courseGroup to set
	 */
	public void setCourseGroup(String courseGroup) {
		this.courseGroup = courseGroup;
	}
	public StudentUploadBean(){
		
	}
	/**
	 * Method for course List
	 * @param courseCode
	 * @param originalCourseCode
	 * @param courseStatus
	 * @param studentStatus
	 */
	public StudentUploadBean(String courseCode,String originalCourseCode,String courseStatus,String internalMarks,String externalMarks,String cummulativeMarks,
			String studentStatus,String courseGroup,String internalGrades,String externalGrades,String cummulativeGrades)
	{
		this.courseCode = courseCode;
		this.originalCourseCode = originalCourseCode;
		this.courseStatus = courseStatus;
		this.courseGroup = courseGroup; 
		this.internalMarks = internalMarks;
		this.externalMarks = externalMarks;
		this.cummulativeMarks = cummulativeMarks;
		this.studentStatus = studentStatus;
		this.internalGrades = internalGrades;
		this.externalGrades = externalGrades;
		this.cummulativeGrades = cummulativeGrades;
	}
	
	/**
	 * Method for remedial course List
	 * @param courseCode
	 * @param courseGroup
	 * @param internal grades
	 * @param external grades
	 * @param cumulative grades
	 */
	public StudentUploadBean(String courseCode,String courseGroup,String internalGrades,String externalGrades,String cummulativeGrades)
	{
		this.courseCode = courseCode;
		this.courseGroup = courseGroup; 
		this.internalGrades = internalGrades;
		this.externalGrades = externalGrades;
		this.cummulativeGrades = cummulativeGrades;
	}
	/**
	 * Method for students Courses Status 
	 * @param rollNumber
	 * @param programCode
	 * @param branchId
	 * @param semesterSequence
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param courseList
	 * @param studentStatus
	 * @param creatorId
	 * @param attemptNumber
	 */
	public StudentUploadBean(String universityId,String rollNumber,String programCode,String branchId,String specializationId,String semesterSequence,String semesterStartDate,String semesterEndDate,
			List<StudentUploadBean> courseList, List<StudentUploadBean> remedialCourseList, String studentStatus,String creatorId,String attemptNumber)
	{
		this.universityId = universityId;
		this.rollNumber = rollNumber;
		this.programCode = programCode;
		this.branchId = branchId;		
		this.specializationId = specializationId;
		this.semesterSequence = semesterSequence;
		this.semesterStartDate =semesterStartDate;
		this.semesterEndDate = semesterEndDate;		
		this.courseList = courseList;
		this.remedialCourseList = remedialCourseList;
		this.creatorId = creatorId;
		this.attemptNumber = attemptNumber;		
	}
	/**
	 * Method for wrapping student record
	 * @param entityId
	 * @param programCode
	 * @param branchId
	 * @param semesterSequence
	 * @param specializationId
	 * @param rollNumber
	 * @param studentName
	 * @param gender
	 * @param attemptNumber
	 * @param previousPercentage
	 * @param cummulativePercentage
	 * @param resultCode
	 * @param gradeCode
	 * @param ugPgCode
	 * @param finalSemesterCode
	 * @param yearOfRegistration
	 * @param evenSemesterRemInd
	 * @param category
	 * @param enrollmentNumber
	 * @param tenCodes
	 * @param semesterStartDate
	 * @param semesterEndDate
	 * @param registrationDueDate
	 * @param registrationDate
	 */
	public StudentUploadBean(String universityId, String entityId,String programId, String branchId,String semesterCode,String specializationId,String programCode,
			String programCourseKey,String rollNumber,String enrollmentNumber, String gender, String studentName,String attemptNumber,String currentSemSGPA,
			String CGPA, String resultCode,int noOfRemedials,String statusSRSH, String statusStudentProgram, String finalSemesterCode,
			String yearOfRegistration,String division,
			String category,String semesterStartDate,String semesterEndDate,String registrationDate,
			List<StudentUploadBean> courseList, List<StudentUploadBean> remedialCourseList, String creatorId,
			String sessionStartDate, String sessionEndDate, ErrorLogBean errorLogBean)
	{
		this.universityId = universityId;
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.semesterCode = semesterCode;
		this.specializationId = specializationId;
		this.programCode = programCode;
		this.programCourseKey = programCourseKey;
		this.rollNumber = rollNumber;
		this.studentName = studentName;
		this.gender = gender;
		this.attemptNumber = attemptNumber;	
		this.setCurrentSemSGPA(currentSemSGPA);
//		this.previousPercentage = previousPercentage;
		this.CGPA = CGPA;
		this.resultCode = resultCode;
		this.noOfRemedials = String.valueOf(noOfRemedials);
		this.setStatusSRSH(statusSRSH);
		this.setStatusStudentProgram(statusStudentProgram);
		this.finalSemesterCode = finalSemesterCode;
		this.yearOfRegistration = yearOfRegistration;
		this.division = division;
//		this.evenSemesterRemInd = evenSemesterRemInd;
		this.category = category;
		this.enrollmentNumber = enrollmentNumber;
//		this.tenCodes = tenCodes;		
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
//		this.registrationDueDate = registrationDueDate;
		this.registrationDate = registrationDate;
		this.courseList = courseList;
		this.remedialCourseList = remedialCourseList;
		this.creatorId = creatorId;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.setErrorLogBean(errorLogBean);
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
	 * @return the attemtNumber
	 */
	public String getAttemptNumber() {
		return attemptNumber;
	}

	/**
	 * @param attemtNumber the attemtNumber to set
	 */
	public void setAttemptNumber(String attemtNumber) {
		this.attemptNumber = attemtNumber;
	}
	

	/**
	 * @return the previousPercentage
	 */
	public List<String> getPreviousPercentage() {
		return previousPercentage;
	}

	/**
	 * @param previousPercentage the previousPercentage to set
	 */
	public void setPreviousPercentage(List<String> previousPercentage) {
		this.previousPercentage = previousPercentage;
	}


	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}

	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * @return the ugPgCode
	 */
	public String getUgPgCode() {
		return ugPgCode;
	}

	/**
	 * @param ugPgCode the ugPgCode to set
	 */
	public void setUgPgCode(String ugPgCode) {
		this.ugPgCode = ugPgCode;
	}

	/**
	 * @return the finalSemesterCode
	 */
	public String getFinalSemesterCode() {
		return finalSemesterCode;
	}

	/**
	 * @param finalSemesterCode the finalSemesterCode to set
	 */
	public void setFinalSemesterCode(String finalSemesterCode) {
		this.finalSemesterCode = finalSemesterCode;
	}

	/**
	 * @return the yearOfRegistration
	 */
	public String getYearOfRegistration() {
		return yearOfRegistration;
	}

	/**
	 * @param yearOfRegistration the yearOfRegistration to set
	 */
	public void setYearOfRegistration(String yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	/**
	 * @return the evenSemesterRemInd
	 */
	public String getEvenSemesterRemInd() {
		return evenSemesterRemInd;
	}

	/**
	 * @param evenSemesterRemInd the evenSemesterRemInd to set
	 */
	public void setEvenSemesterRemInd(String evenSemesterRemInd) {
		this.evenSemesterRemInd = evenSemesterRemInd;
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
	 * @return the tenCodes
	 */
	public String getTenCodes() {
		return tenCodes;
	}

	/**
	 * @param tenCodes the tenCodes to set
	 */
	public void setTenCodes(String tenCodes) {
		this.tenCodes = tenCodes;
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
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the noOfRemedials
	 */
	public String getNoOfRemedials() {
		return noOfRemedials;
	}

	/**
	 * @param noOfRemedials the noOfRemedials to set
	 */
	public void setNoOfRemedials(String noOfRemedials) {
		this.noOfRemedials = noOfRemedials;
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
	 * @return the registrationDueDate
	 */
	public String getRegistrationDueDate() {
		return registrationDueDate;
	}

	/**
	 * @param registrationDueDate the registrationDueDate to set
	 */
	public void setRegistrationDueDate(String registrationDueDate) {
		this.registrationDueDate = registrationDueDate;
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
	 * @return the originalCourseCode
	 */
	public String getOriginalCourseCode() {
		return originalCourseCode;
	}
	/**
	 * @param originalCourseCode the originalCourseCode to set
	 */
	public void setOriginalCourseCode(String originalCourseCode) {
		this.originalCourseCode = originalCourseCode;
	}
	/**
	 * @return the courseStatus
	 */
	public String getCourseStatus() {
		return courseStatus;
	}
	/**
	 * @param courseStatus the courseStatus to set
	 */
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	/**
	 * @return the studentStatus
	 */
	public String getStudentStatus() {
		return studentStatus;
	}
	/**
	 * @param studentStatus the studentStatus to set
	 */
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
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
	 * @return the courseList
	 */
	public List<StudentUploadBean> getCourseList() {
		return courseList;
	}
	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<StudentUploadBean> courseList) {
		this.courseList = courseList;
	}
	/**
	 * @return the internalMarks
	 */
	public String getInternalMarks() {
		return internalMarks;
	}
	/**
	 * @param internalMarks the internalMarks to set
	 */
	public void setInternalMarks(String internalMarks) {
		this.internalMarks = internalMarks;
	}
	/**
	 * @return the externalMarks
	 */
	public String getExternalMarks() {
		return externalMarks;
	}
	/**
	 * @param externalMarks the externalMarks to set
	 */
	public void setExternalMarks(String externalMarks) {
		this.externalMarks = externalMarks;
	}
	/**
	 * @return the cummulativeMarks
	 */
	public String getCummulativeMarks() {
		return cummulativeMarks;
	}
	/**
	 * @param cummulativeMarks the cummulativeMarks to set
	 */
	public void setCummulativeMarks(String cummulativeMarks) {
		this.cummulativeMarks = cummulativeMarks;
	}
	/**
	 * @return the internalGrades
	 */
	public String getInternalGrades() {
		return internalGrades;
	}
	/**
	 * @param internalGrades the internalGrades to set
	 */
	public void setInternalGrades(String internalGrades) {
		this.internalGrades = internalGrades;
	}
	/**
	 * @return the externalGrades
	 */
	public String getExternalGrades() {
		return externalGrades;
	}
	/**
	 * @param externalGrades the externalGrades to set
	 */
	public void setExternalGrades(String externalGrades) {
		this.externalGrades = externalGrades;
	}
	/**
	 * @return the cummulativeGrades
	 */
	public String getCummulativeGrades() {
		return cummulativeGrades;
	}
	/**
	 * @param cummulativeGrades the cummulativeGrades to set
	 */
	public void setCummulativeGrades(String cummulativeGrades) {
		this.cummulativeGrades = cummulativeGrades;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseClassification(String courseClassification) {
		this.courseClassification = courseClassification;
	}
	public String getCourseClassification() {
		return courseClassification;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public String getCredits() {
		return credits;
	}
	public void setEarnedCredits(String earnedCredits) {
		this.earnedCredits = earnedCredits;
	}
	public String getEarnedCredits() {
		return earnedCredits;
	}
	public void setPointSecuredSgpa(String pointSecuredSgpa) {
		this.pointSecuredSgpa = pointSecuredSgpa;
	}
	public String getPointSecuredSgpa() {
		return pointSecuredSgpa;
	}
	public void setPointSecuredCgpa(String pointSecuredCgpa) {
		this.pointSecuredCgpa = pointSecuredCgpa;
	}
	public String getPointSecuredCgpa() {
		return pointSecuredCgpa;
	}
	public void setEarnedCreditCgpa(String earnedCreditCgpa) {
		this.earnedCreditCgpa = earnedCreditCgpa;
	}
	public String getEarnedCreditCgpa() {
		return earnedCreditCgpa;
	}
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getSemesterCode() {
		return semesterCode;
	}
	public void setCGPA(String cGPA) {
		CGPA = cGPA;
	}
	public String getCGPA() {
		return CGPA;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivision() {
		return division;
	}
	public void setRemedialCourseList(List<StudentUploadBean> remedialCourseList) {
		this.remedialCourseList = remedialCourseList;
	}
	public List<StudentUploadBean> getRemedialCourseList() {
		return remedialCourseList;
	}
	public void setStatusSRSH(String statusSRSH) {
		this.statusSRSH = statusSRSH;
	}
	public String getStatusSRSH() {
		return statusSRSH;
	}
	public void setStatusStudentProgram(String statusStudentProgram) {
		this.statusStudentProgram = statusStudentProgram;
	}
	public String getStatusStudentProgram() {
		return statusStudentProgram;
	}
	public void setCurrentSemSGPA(String currentSemSGPA) {
		this.currentSemSGPA = currentSemSGPA;
	}
	public String getCurrentSemSGPA() {
		return currentSemSGPA;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setErrorLogBean(ErrorLogBean errorLogBean) {
		this.errorLogBean = errorLogBean;
	}
	public ErrorLogBean getErrorLogBean() {
		return errorLogBean;
	}
	public void setPassedFromSession(String passedFromSession) {
		this.passedFromSession = passedFromSession;
	}
	public String getPassedFromSession() {
		return passedFromSession;
	}
	public void setPassedToSession(String passedToSession) {
		this.passedToSession = passedToSession;
	}
	public String getPassedToSession() {
		return passedToSession;
	}
	public void setRegisteredCredit(String registeredCredit) {
		this.registeredCredit = registeredCredit;
	}
	public String getRegisteredCredit() {
		return registeredCredit;
	}
	public void setRegisteredTheoryCreditExcludingAudit(
			String registeredTheoryCreditExcludingAudit) {
		this.registeredTheoryCreditExcludingAudit = registeredTheoryCreditExcludingAudit;
	}
	public String getRegisteredTheoryCreditExcludingAudit() {
		return registeredTheoryCreditExcludingAudit;
	}
	public void setRegisteredPracticalCreditExcludingAudit(
			String registeredPracticalCreditExcludingAudit) {
		this.registeredPracticalCreditExcludingAudit = registeredPracticalCreditExcludingAudit;
	}
	public String getRegisteredPracticalCreditExcludingAudit() {
		return registeredPracticalCreditExcludingAudit;
	}
	public void setRegistrationCreditExcludingAudit(
			String registrationCreditExcludingAudit) {
		this.registrationCreditExcludingAudit = registrationCreditExcludingAudit;
	}
	public String getRegistrationCreditExcludingAudit() {
		return registrationCreditExcludingAudit;
	}	
}
