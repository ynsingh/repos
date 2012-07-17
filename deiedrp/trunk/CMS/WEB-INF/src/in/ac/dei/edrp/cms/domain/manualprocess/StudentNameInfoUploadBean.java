package in.ac.dei.edrp.cms.domain.manualprocess;

import java.util.Date;

public class StudentNameInfoUploadBean {
	private String rollNumber;	
	private String enrollmentNumber;
	private String studentNameHindi;
	private String fatherNameEnglish;
	private String fatherNameHindi;
	private String motherNameEnglish;
	private String motherNameHindi;
	private Date dob;
	private String universityId;
	private String entityId;
	
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getFatherNameEnglish() {
		return fatherNameEnglish;
	}
	public void setFatherNameEnglish(String fatherNameEnglish) {
		this.fatherNameEnglish = fatherNameEnglish;
	}
	public String getFatherNameHindi() {
		return fatherNameHindi;
	}
	public void setFatherNameHindi(String fatherNameHindi) {
		this.fatherNameHindi = fatherNameHindi;
	}
	public String getMotherNameEnglish() {
		return motherNameEnglish;
	}
	public void setMotherNameEnglish(String motherNameEnglish) {
		this.motherNameEnglish = motherNameEnglish;
	}
	public String getMotherNameHindi() {
		return motherNameHindi;
	}
	public void setMotherNameHindi(String motherNameHindi) {
		this.motherNameHindi = motherNameHindi;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}	
	/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}
	/**
	 * @return the enrollmentNumber
	 */
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}
	/**
	 * @param universityId the universityId to set
	 */
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	/**
	 * @return the universityId
	 */
	public String getUniversityId() {
		return universityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param studentNameHindi the studentNameHindi to set
	 */
	public void setStudentNameHindi(String studentNameHindi) {
		this.studentNameHindi = studentNameHindi;
	}
	/**
	 * @return the studentNameHindi
	 */
	public String getStudentNameHindi() {
		return studentNameHindi;
	}
	

}
