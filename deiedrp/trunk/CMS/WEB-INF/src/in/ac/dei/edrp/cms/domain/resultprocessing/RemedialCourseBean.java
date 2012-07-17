package in.ac.dei.edrp.cms.domain.resultprocessing;

import java.io.Serializable;

@SuppressWarnings("serial")
//public class RemedialCourseBean implements Serializable {
	public class RemedialCourseBean  {
	private String courseCode;
	private String studentStatus;
	private String courseClassification;
	private String courseType;
	private String resultSystem;
	
	// Changes By Arush
	//private String credits;
	//private String earnedCredits;
	//private String obtainedGrade;
	//private String totalMarks;
	
	private Double credits;
	private Double earnedCredits;
	private Double  obtainedGrade;
	private Double  totalMarks;
	
	// Changes end by Arush 
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getCourseClassification() {
		return courseClassification;
	}
	public void setCourseClassification(String courseClassification) {
		this.courseClassification = courseClassification;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getResultSystem() {
		return resultSystem;
	}
	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}
	public Double getCredits() {
		return credits;
	}
	public void setCredits(Double credits) {
		this.credits = credits;
	}
	public Double getEarnedCredits() {
		return earnedCredits;
	}
	public void setEarnedCredits(Double earnedCredits) {
		this.earnedCredits = earnedCredits;
	}
	public Double getObtainedGrade() {
		return obtainedGrade;
	}
	public void setObtainedGrade(Double obtainedGrade) {
		this.obtainedGrade = obtainedGrade;
	}
	public Double getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	//
//	public String getCredits() {
//		return credits;
//	}
//	public void setCredits(String credits) {
//		this.credits = credits;
//	}
//	public String getEarnedCredits() {
//		return earnedCredits;
//	}
//	public void setEarnedCredits(String earnedCredits) {
//		this.earnedCredits = earnedCredits;
//	}
//	public String getObtainedGrade() {
//		return obtainedGrade;
//	}
//	public void setObtainedGrade(String obtainedGrade) {
//		this.obtainedGrade = obtainedGrade;
//	}
//	public String getTotalMarks() {
//		return totalMarks;
//	}
//	public void setTotalMarks(String totalMarks) {
//		this.totalMarks = totalMarks;
//	}
}