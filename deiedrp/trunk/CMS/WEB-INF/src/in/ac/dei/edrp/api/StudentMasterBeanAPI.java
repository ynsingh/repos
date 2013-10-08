package in.ac.dei.edrp.api;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is Server side bean class for Student Master
 * 
 * @version 1.0 24 Mar 2011
 * @author MOHD AMIR
 */
public class StudentMasterBeanAPI extends StudentMarksSummaryBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9180126054164341028L;
	/** declaring private variables */
	private String universityId;
	private String universityName;
	private String sequenceNumber;
	private String studentId;
	private String enrollmentNumber;
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String primaryEmailId;
	private String secondaryEmailId;
	private String dateOfBirth;
	private String categoryCode;
	private String gender;
	private String fatherFirstName;
	private String fatherMiddleName;
	private String fatherLastName;
	private String motherFirstName;
	private String motherMiddleName;
	private String motherLastName;
	private String registeredInSession;
	private String status;
	private String userId;
	private String parentEntity;
	private String addressKey;
	private String addressType;
	private String userType;
	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private String pinCode;
	private String officePhone;
	private String homePhone;
	private String otherPhone;
	private String fax;
	private String hindiName;
	private String fatherHindiName;
	private String motherHindiName;
	private String path;
	private String religion;
	private String nationality;
	private String guardian;
	private String sessionStartDate;
	private String sessionEndDate;
	private String userEmailId;
	private String parentAddress1;
	private String parentAddress2;
	private String parentPhone1;
	private String parentPhone2;
	private String parentEmail1;
	private String parentEmail2;
	private String placeOfBirth;
	private String maritalStatus;
	private String website;
	private String UID;
	private String panNo;
	private String passportNo;
	private String country;
	private List<StudentMasterBeanAPI> semesterList;
	private List<StudentMasterBeanAPI> courseList;
	private String errorMsg;
	private String entityName;
	private String division;
	
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getUID() {
		return UID;
	}


	public void setUID(String uID) {
		UID = uID;
	}


	public String getPanNo() {
		return panNo;
	}


	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}


	public String getPassportNo() {
		return passportNo;
	}


	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getParentAddress1() {
		return parentAddress1;
	}


	public void setParentAddress1(String parentAddress1) {
		this.parentAddress1 = parentAddress1;
	}


	public String getParentAddress2() {
		return parentAddress2;
	}


	public void setParentAddress2(String parentAddress2) {
		this.parentAddress2 = parentAddress2;
	}


	public String getParentPhone1() {
		return parentPhone1;
	}


	public void setParentPhone1(String parentPhone1) {
		this.parentPhone1 = parentPhone1;
	}


	public String getParentPhone2() {
		return parentPhone2;
	}


	public void setParentPhone2(String parentPhone2) {
		this.parentPhone2 = parentPhone2;
	}


	public String getParentEmail1() {
		return parentEmail1;
	}


	public void setParentEmail1(String parentEmail1) {
		this.parentEmail1 = parentEmail1;
	}


	public String getParentEmail2() {
		return parentEmail2;
	}


	public void setParentEmail2(String parentEmail2) {
		this.parentEmail2 = parentEmail2;
	}


	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getGuardian() {
		return guardian;
	}


	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}


	public String getHindiName() {
		return hindiName;
	}


	public void setHindiName(String hindiName) {
		this.hindiName = hindiName;
	}


	public String getFatherHindiName() {
		return fatherHindiName;
	}


	public void setFatherHindiName(String fatherHindiName) {
		this.fatherHindiName = fatherHindiName;
	}


	public String getMotherHindiName() {
		return motherHindiName;
	}


	public void setMotherHindiName(String motherHindiName) {
		this.motherHindiName = motherHindiName;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	private Map<String,StudentMasterBeanAPI> studentAddress=new HashMap<String,StudentMasterBeanAPI>();
	
	/**
	 * defining constructor
	 */
	public StudentMasterBeanAPI(){
		
	}
	
	
	/**
	 * defining constructor
	 */
	public StudentMasterBeanAPI(Map<String,StudentMasterBeanAPI> studentAddress){
		this.studentAddress=studentAddress;
	}
	
	
	
	/**
	 * defining constructor
	 */
	public StudentMasterBeanAPI(String universityId,
			String enrollmentNumber, String studentId,String studentFirstName,
			String studentMiddleName, String studentLastName,
			String primaryEmailId, String secondaryEmailId, String dateOfBirth,
			String categoryCode, String gender, String fatherFirstName,
			String fatherMiddleName, String fatherLastName,
			String motherFirstName, String motherMiddleName,
			String motherLastName, String registeredInSession, String status) {
		super();
		this.universityId = universityId;
		this.studentId = studentId;
		this.enrollmentNumber = enrollmentNumber;
		this.studentFirstName = studentFirstName;
		this.studentMiddleName = studentMiddleName;
		this.studentLastName = studentLastName;
		this.primaryEmailId = primaryEmailId;
		this.secondaryEmailId = secondaryEmailId;
		this.dateOfBirth = dateOfBirth;
		this.categoryCode = categoryCode;
		this.gender = gender;
		this.fatherFirstName = fatherFirstName;
		this.fatherMiddleName = fatherMiddleName;
		this.fatherLastName = fatherLastName;
		this.motherFirstName = motherFirstName;
		this.motherMiddleName = motherMiddleName;
		this.motherLastName = motherLastName;
		this.registeredInSession = registeredInSession;
		this.status = status;
	}


	/**
	 * defining constructor
	 */
	public StudentMasterBeanAPI(String userType,String addressLineOne,String addressLineTwo,
			String city,String state,String pinCode,String officePhone,String homePhone,String otherPhone,
			String fax){
	this.userType=userType;
	this.addressLineOne=addressLineOne;
	this.addressLineTwo=addressLineTwo;
	this.city=city;
	this.state=state;
	this.pinCode=pinCode;
	this.officePhone=officePhone;
	this.homePhone=homePhone;
	this.otherPhone=otherPhone;
	this.fax=fax;
	}
	
	/**
	 * defining getter and setter for getting and setting values of private
	 * variables
	 */
	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
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

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherFirstName() {
		return motherFirstName;
	}

	
	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getRegisteredInSession() {
		return registeredInSession;
	}

	public void setRegisteredInSession(String registeredInSession) {
		this.registeredInSession = registeredInSession;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Map<String, StudentMasterBeanAPI> getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Map<String, StudentMasterBeanAPI> studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getParentEntity() {
		return parentEntity;
	}


	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}


	public String getSequenceNumber() {
		return sequenceNumber;
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


	/**
	 * @param userEmailId the userEmailId to set
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}


	/**
	 * @return the userEmailId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}


	public void setSemesterList(List<StudentMasterBeanAPI> semesterList) {
		this.semesterList = semesterList;
	}


	public List<StudentMasterBeanAPI> getSemesterList() {
		return semesterList;
	}


	public void setCourseList(List<StudentMasterBeanAPI> courseList) {
		this.courseList = courseList;
	}


	public List<StudentMasterBeanAPI> getCourseList() {
		return courseList;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	public String getEntityName() {
		return entityName;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getDivision() {
		return division;
	}


	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}


	public String getUniversityName() {
		return universityName;
	}

}
