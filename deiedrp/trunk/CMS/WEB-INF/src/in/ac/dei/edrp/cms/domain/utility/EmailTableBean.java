package in.ac.dei.edrp.cms.domain.utility;

public class EmailTableBean {

	private String userId;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String userGroupId;
	private String userName;
	private String emailId;
	private String referTable;
	private boolean isReady;
	private String creatorId;

	public EmailTableBean(String userId, String entityId, String programId,
			String branchId, String specializationId, String semesterCode,
			String userGroupId, String userName, String emailId,
			String referTable, boolean isReady,String creatorId) {
		super();
		this.userId = userId;
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.userGroupId = userGroupId;
		this.userName = userName;
		this.emailId = emailId;
		this.referTable = referTable;
		this.isReady = isReady;
		this.creatorId=creatorId;
	}

	public EmailTableBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getReferTable() {
		return referTable;
	}

	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}

	public boolean getIsReady() {
		return isReady;
	}

	public void setIsReady(boolean isReady) {
		this.isReady = isReady;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

}
