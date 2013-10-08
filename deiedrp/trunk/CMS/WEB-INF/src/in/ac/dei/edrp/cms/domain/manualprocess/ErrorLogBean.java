package in.ac.dei.edrp.cms.domain.manualprocess;


public class ErrorLogBean {
	private String programId;
	private String branchId;
	private String semesterCode;
	private String specializationId;
	private String session;
	private String rollNumber;
	private String errorDescription;
	private String resultFile;
	private String insertTime;
	private String creatorId;
	private String enrollmentNumber;
	
	public ErrorLogBean(){
		
	}
	/**
	 * Method for course List
	 * @param courseCode
	 * @param originalCourseCode
	 * @param courseStatus
	 * @param studentStatus
	 */
	public ErrorLogBean(String programId,String branchId,String semesterCode,String specializationId,String session,String rollNumber
			,String resultFile,String creatorId,String enrollmentNumber){
		super();
		this.programId = programId;
		this.branchId = branchId;
		this.semesterCode = semesterCode;
		this.specializationId = specializationId; 
		this.session = session;
		this.rollNumber = rollNumber;
//		this.errorDescription = errorDescription;
		this.resultFile = resultFile;
//		this.insertTime = insertTime;
		this.creatorId = creatorId;	
		this.enrollmentNumber = enrollmentNumber;
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

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getSemesterCode() {
		return semesterCode;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getSession() {
		return session;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getResultFile() {
		return resultFile;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}	
}
