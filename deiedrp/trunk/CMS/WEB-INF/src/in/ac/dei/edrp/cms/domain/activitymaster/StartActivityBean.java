package in.ac.dei.edrp.cms.domain.activitymaster;

public class StartActivityBean {

	private String universityId;
	private String entityId;
	private String entityName;
	private String programId;
	private String programName;
	private String branchId;
	private String branchName;
	private String specializationId;
	private String specializationName;
	private String semesterCode;
	private String semesterName;
	private String semesterStartDate;
	private String semesterEndDate;
	private String processId;
	private String processName;
	private String processStartDate;
	private String ProcessEndDate;
	private String processStatus;
	private String activityId;
	private String activityName;
	private String activityStartDate;
	private String activityEndDate;
	private int activitySequence;
	private String activityStatus;

	private String userId;

	private String sessionStartDate;
	private String sessionEndDate;
	private String universityStatus;

	private String programCourseKey;

	private String componentCode;
	private String componentDescription;
	private String groupCode;
	
	private int processCounter;

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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
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

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessStartDate() {
		return processStartDate;
	}

	public void setProcessStartDate(String processStartDate) {
		this.processStartDate = processStartDate;
	}

	public String getProcessEndDate() {
		return ProcessEndDate;
	}

	public void setProcessEndDate(String processEndDate) {
		ProcessEndDate = processEndDate;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(String activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public String getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public int getActivitySequence() {
		return activitySequence;
	}

	public void setActivitySequence(int activitySequence) {
		this.activitySequence = activitySequence;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUniversityStatus() {
		return universityStatus;
	}

	public void setUniversityStatus(String universityStatus) {
		this.universityStatus = universityStatus;
	}

	public String getProgramCourseKey() {
		return programCourseKey;
	}

	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	
	public int getProcessCounter() {
		return processCounter;
	}

	public void setProcessCounter(int processCounter) {
		this.processCounter = processCounter;
	}

	public StartActivityBean(String sessionStartDate, String sessionEndDate,
			String entityId, String entityName, String programId,
			String programName, String branchId, String branchName,
			String specializationId, String specializationName,
			String semesterCode, String semesterName, String semesterStartDate,
			String semesterEndDate, String processId, String processName,
			String processStartDate, String processEndDate, String processStatus) {
		super();

		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.entityId = entityId;
		this.entityName = entityName;
		this.programId = programId;
		this.programName = programName;
		this.branchId = branchId;
		this.branchName = branchName;
		this.specializationId = specializationId;
		this.specializationName = specializationName;
		this.semesterCode = semesterCode;
		this.semesterName = semesterName;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.processId = processId;
		this.processName = processName;
		this.processStartDate = processStartDate;
		this.ProcessEndDate = processEndDate;
		this.processStatus = processStatus;

	}

	public StartActivityBean(String sessionStartDate, String sessionEndDate,
			String entityId, String entityName, String programId,
			String programName, String branchId, String branchName,
			String specializationId, String specializationName,
			String semesterCode, String semesterName, String semesterStartDate,
			String semesterEndDate, String processId, String processName,
			String activityId, String activityName, String activityStartDate,
			String activityEndDate, int activitySequence, String activityStatus) {
		super();

		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;
		this.entityId = entityId;
		this.entityName = entityName;
		this.programId = programId;
		this.programName = programName;
		this.branchId = branchId;
		this.branchName = branchName;
		this.specializationId = specializationId;
		this.specializationName = specializationName;
		this.semesterCode = semesterCode;
		this.semesterName = semesterName;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.processId = processId;
		this.processName = processName;
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityStartDate = activityStartDate;
		this.activityEndDate = activityEndDate;
		this.activitySequence = activitySequence;
		this.activityStatus = activityStatus;
	}

	public StartActivityBean(String universityId, String entityId,
			String programCourseKey, String semesterStartDate,
			String semesterEndDate, String processId) {
		super();
		this.universityId = universityId;
		this.entityId = entityId;
		this.programCourseKey = programCourseKey;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.processId = processId;
	}

	public StartActivityBean(String programId, String branchId,
			String specializationId, String semesterCode) {
		super();
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
	}

	public StartActivityBean(String universityId, String entityId,
			String entityName, String programId, String programName,
			String branchId, String branchName, String specializationId,
			String specializationName, String semesterCode,
			String semesterName, String semesterStartDate,
			String semesterEndDate, String programCourseKey, String processId,
			String activityId, int activitySequence, String activityStatus,
			String userId) {
		super();

		this.universityId = universityId;
		this.entityId = entityId;
		this.entityName = entityName;
		this.programId = programId;
		this.programName = programName;
		this.branchId = branchId;
		this.branchName = branchName;
		this.specializationId = specializationId;
		this.specializationName = specializationName;
		this.semesterCode = semesterCode;
		this.semesterName = semesterName;

		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;

		this.programCourseKey = programCourseKey;

		this.processId = processId;

		this.activityId = activityId;

		this.activitySequence = activitySequence;
		this.activityStatus = activityStatus;

		this.userId = userId;
	}

	public StartActivityBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
