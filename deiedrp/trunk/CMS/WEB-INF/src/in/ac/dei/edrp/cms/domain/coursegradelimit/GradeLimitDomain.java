package in.ac.dei.edrp.cms.domain.coursegradelimit;

import java.util.List;

/**
 * this is bean class for grade course limit
 *
 * @version 1.0 27 FEB 2012
 * @author Ashish Mohan
 */
public class GradeLimitDomain {
	
	/** declaring private variables **/
	private String ownerEntityId;
	private String ownerEntityName;
	private String ownerProgramId;
	private String ownerProgramName;
	private String ownerBranchId;
	private String ownerBranchName;
	private String ownerSpecializationId;
	private String ownerSpecializationName;
	private String courseCode;
	private String courseName;
	private String grade;
	private String marksTo;
	private String marksFrom;
	private String displayType;
	private String userId;
	private String internalActive;
	private String limitActive;
	private String universityCode;
	private String marksContEval;
	private String marksEndSemester;
	private String totalMarks;
	private String startDate;
	private String endDate;
	private String employeeId;
	private String lowerA;
	private String lowerAM;
	private String lowerB;
	private String lowerBM;
	private String lowerC;
	private String lowerCM;
	private String lowerD;
	private String lowerDM;
	private String lowerE;
	private String lowerEM;
	private String lowerF;
	private List<String> courseCodeList;
	
	public String getUniversityCode() {
		return universityCode;
	}
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}
	public String getOwnerEntityId() {
		return ownerEntityId;
	}
	public void setOwnerEntityId(String ownerEntityId) {
		this.ownerEntityId = ownerEntityId;
	}
	public String getOwnerEntityName() {
		return ownerEntityName;
	}
	public void setOwnerEntityName(String ownerEntityName) {
		this.ownerEntityName = ownerEntityName;
	}
	public String getOwnerProgramId() {
		return ownerProgramId;
	}
	public void setOwnerProgramId(String ownerProgramId) {
		this.ownerProgramId = ownerProgramId;
	}
	public String getOwnerProgramName() {
		return ownerProgramName;
	}
	public void setOwnerProgramName(String ownerProgramName) {
		this.ownerProgramName = ownerProgramName;
	}
	public String getOwnerBranchId() {
		return ownerBranchId;
	}
	public void setOwnerBranchId(String ownerBranchId) {
		this.ownerBranchId = ownerBranchId;
	}
	public String getOwnerBranchName() {
		return ownerBranchName;
	}
	public void setOwnerBranchName(String ownerBranchName) {
		this.ownerBranchName = ownerBranchName;
	}
	public String getOwnerSpecializationId() {
		return ownerSpecializationId;
	}
	public void setOwnerSpecializationId(String ownerSpecializationId) {
		this.ownerSpecializationId = ownerSpecializationId;
	}
	public String getOwnerSpecializationName() {
		return ownerSpecializationName;
	}
	public void setOwnerSpecializationName(String ownerSpecializationName) {
		this.ownerSpecializationName = ownerSpecializationName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMarksTo() {
		return marksTo;
	}
	public void setMarksTo(String marksTo) {
		this.marksTo = marksTo;
	}
	public String getMarksFrom() {
		return marksFrom;
	}
	public void setMarksFrom(String marksFrom) {
		this.marksFrom = marksFrom;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInternalActive() {
		return internalActive;
	}
	public void setInternalActive(String internalActive) {
		this.internalActive = internalActive;
	}
	public String getLimitActive() {
		return limitActive;
	}
	public void setLimitActive(String limitActive) {
		this.limitActive = limitActive;
	}
	public String getMarksContEval() {
		return marksContEval;
	}
	public void setMarksContEval(String marksContEval) {
		this.marksContEval = marksContEval;
	}
	public String getMarksEndSemester() {
		return marksEndSemester;
	}
	public void setMarksEndSemester(String marksEndSemester) {
		this.marksEndSemester = marksEndSemester;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getLowerA() {
		return lowerA;
	}
	public void setLowerA(String lowerA) {
		this.lowerA = lowerA;
	}
	public String getLowerAM() {
		return lowerAM;
	}
	public void setLowerAM(String lowerAM) {
		this.lowerAM = lowerAM;
	}
	public String getLowerB() {
		return lowerB;
	}
	public void setLowerB(String lowerB) {
		this.lowerB = lowerB;
	}
	public String getLowerBM() {
		return lowerBM;
	}
	public void setLowerBM(String lowerBM) {
		this.lowerBM = lowerBM;
	}
	public String getLowerC() {
		return lowerC;
	}
	public void setLowerC(String lowerC) {
		this.lowerC = lowerC;
	}
	public String getLowerCM() {
		return lowerCM;
	}
	public void setLowerCM(String lowerCM) {
		this.lowerCM = lowerCM;
	}
	public String getLowerD() {
		return lowerD;
	}
	public void setLowerD(String lowerD) {
		this.lowerD = lowerD;
	}
	public String getLowerDM() {
		return lowerDM;
	}
	public void setLowerDM(String lowerDM) {
		this.lowerDM = lowerDM;
	}
	public String getLowerE() {
		return lowerE;
	}
	public void setLowerE(String lowerE) {
		this.lowerE = lowerE;
	}
	public String getLowerEM() {
		return lowerEM;
	}
	public void setLowerEM(String lowerEM) {
		this.lowerEM = lowerEM;
	}
	public String getLowerF() {
		return lowerF;
	}
	public void setLowerF(String lowerF) {
		this.lowerF = lowerF;
	}
	public List<String> getCourseCodeList() {
		return courseCodeList;
	}
	public void setCourseCodeList(List<String> courseCodeList) {
		this.courseCodeList = courseCodeList;
	}
}
