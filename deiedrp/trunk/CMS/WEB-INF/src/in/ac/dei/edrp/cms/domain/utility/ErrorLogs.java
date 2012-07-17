package in.ac.dei.edrp.cms.domain.utility;

public class ErrorLogs {

	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private int actualRecord;
	private int processRecord;
	private int failrecord;
	private int rejectedValue;
	private String processFlag;
	private int processCounter;

	private String studentId;
	private String enrollmentNumber;
	private String studentName;

	private String reasonCode;
	private String description;

	private String processId;
	private String activityId;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
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

	public int getActualRecord() {
		return actualRecord;
	}

	public void setActualRecord(int actualRecord) {
		this.actualRecord = actualRecord;
	}

	public int getProcessRecord() {
		return processRecord;
	}

	public void setProcessRecord(int processRecord) {
		this.processRecord = processRecord;
	}

	public int getFailrecord() {
		return failrecord;
	}

	public void setFailrecord(int failrecord) {
		this.failrecord = failrecord;
	}

	public int getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(int rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public int getProcessCounter() {
		return processCounter;
	}

	public void setProcessCounter(int processCounter) {
		this.processCounter = processCounter;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public ErrorLogs(String entityId, String programId, String branchId,
			String specializationId, String semesterCode,
			String semesterStartDate, String semesterEndDate, String processId,
			String activityId) {
		super();
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.processId = processId;
		this.activityId = activityId;

	}

	public ErrorLogs() {
		super();
	}
}
