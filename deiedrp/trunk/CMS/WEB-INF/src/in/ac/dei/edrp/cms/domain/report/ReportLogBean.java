package in.ac.dei.edrp.cms.domain.report;

public class ReportLogBean {
	private String universityCode;
	private String programId;
	private String branchCode;
	private String specializationCode;
	private String entityId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String reportCode;
	private String reportGenerated;
	private String reportPrinted;
	private String sessionStartDate;
	private String sessionEndDate;
	private String semesterType;
	private String insertTime;
	private String creatorId;
	private String errorCode;
	private String studentRollNumber;
	private String session;
	private String insertDate;
	private String isGenerated;
	private String printStatus;
	
	public ReportLogBean(){
		System.out.println("inside default constructor of report log bean");
		this.universityCode=null;
		this.programId=null;
		this.branchCode=null;
		this.specializationCode=null;
		this.entityId=null;
		this.semesterCode=null;
		this.semesterStartDate=null;
		this.semesterEndDate=null;
		this.reportCode=null;;
		this.reportGenerated=null;
		this.reportPrinted=null;
		this.sessionStartDate=null;
		this.sessionEndDate=null;
		this.semesterType=null;
		this.errorCode=null;
		this.studentRollNumber=null;
		this.session=null;
		this.isGenerated=null;
		this.printStatus=null;
	}
	public ReportLogBean(String universityCode,String entityId,String programId, String branchCode, String specializationCode,
			String semesterCode,String semesterStartDate,String semesterEndDate,String reportCode,String reportGenerated,
			String reportPrinted, String sessionStartDate, String sessionEndDate, String semesterType,String creatorId) {
		this();
		System.out.println("this ka semester code "+this.semesterCode);
		System.out.println("inside constructor mein semester code is "+semesterCode);
		this.universityCode = universityCode;
		this.entityId = entityId;
		this.programId = programId;
		this.branchCode = branchCode;
		this.specializationCode = specializationCode;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.reportCode = reportCode;
		this.reportGenerated = reportGenerated;
		this.reportPrinted = reportPrinted;
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.semesterType = semesterType;
		this.creatorId = creatorId;
	}
	
	public ReportLogBean(String universityCode, String programId,
			String branchCode, String specializationCode, String entityId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String reportCode, String errorCode,
			String studentRollNumber, String session) {
		this();
		this.universityCode = universityCode;
		this.programId = programId;
		this.branchCode = branchCode;
		this.specializationCode = specializationCode;
		this.entityId = entityId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.reportCode = reportCode;
		this.errorCode = errorCode;
		this.studentRollNumber = studentRollNumber;
		this.session = session;
	}

	public ReportLogBean(String universityCode, String programId,
			String branchCode, String specializationCode, String entityId,
			String semesterCode, String semesterStartDate,
			String semesterEndDate, String reportCode, String errorCode,
			String studentRollNumber, String session, String printStatus) {
		this();
		this.universityCode = universityCode;
		this.programId = programId;
		this.branchCode = branchCode;
		this.specializationCode = specializationCode;
		this.entityId = entityId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.reportCode = reportCode;
		this.errorCode = errorCode;
		this.studentRollNumber = studentRollNumber;
		this.session = session;
		this.printStatus = printStatus;
	}

	public String getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getSpecializationCode() {
		return specializationCode;
	}
	public void setSpecializationCode(String specializationCode) {
		this.specializationCode = specializationCode;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getSemesterCode() {
		return semesterCode;
	}
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
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
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportGenerated() {
		return reportGenerated;
	}
	public void setReportGenerated(String reportGenerated) {
		this.reportGenerated = reportGenerated;
	}
	public String getReportPrinted() {
		return reportPrinted;
	}
	public void setReportPrinted(String reportPrinted) {
		this.reportPrinted = reportPrinted;
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
	public String getSemesterType() {
		return semesterType;
	}
	public void setSemesterType(String semesterType) {
		this.semesterType = semesterType;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getStudentRollNumber() {
		return studentRollNumber;
	}
	public void setStudentRollNumber(String studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @param isGenerated the isGenerated to set
	 */
	public void setIsGenerated(String isGenerated) {
		this.isGenerated = isGenerated;
	}
	/**
	 * @return the isGenerated
	 */
	public String getIsGenerated() {
		return isGenerated;
	}

	/**
	 * @param printStatus the printStatus to set
	 */
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	/**
	 * @return the printStatus
	 */
	public String getPrintStatus() {
		return printStatus;
	}	
}
