package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_StudentInfoGetter implements IsSerializable {
    private String university_code;
    private String programId;
    private String program_name;
    private String registrationNumber;
    private String form_number;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String father_Fname;
    private String father_Mname;
    private String father_Lname;
    private String mother_Fname;
    private String mother_Mname;
    private String mother_Lname;
    private String gender;
    private String date_of_birth;
    private String category;
    private String category_code;
    private String marksPercentage;
    private String marksObtained;
    private String totalMarks;
    private String ComputedMarks;
    private String enrollment_number;
    private String specialization_id;
    private String specialization_name;
    private String papercode;
    private String newpapercode;
    private String firstDegreeCode;
    private String componentID;
    private String weightageID;
    private String user_id;
    private String creator_id;
    private String modifier_id;
    private String score;
    private String branch;
    private boolean Flag;
    private String cos_value;
    private String entity_id;
    private String board_id;
    private String board_name;
    private String UGorPG;
    private String startdate;
    private String enddate;
    private String insert_time;
    private String modification_time;
    private String grouping;
    private String subject_code;//Add by Devendra
    
    /**
	 * @return the subject_code
	 */
	public String getSubject_code() {
		return subject_code;
	}
	/**
	 * @param subject_code the subject_code to set
	 */
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	String addressLine1;
    String addressLine2;
    String city;
    String state;
    String pinCode;
    String specialWeightageFlag;
    String staffFlag;
    String student_id;
	/**
	 * @return the university_code
	 */
	public String getUniversity_code() {
		return university_code;
	}
	/**
	 * @param university_code the university_code to set
	 */
	public void setUniversity_code(String university_code) {
		this.university_code = university_code;
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
	 * @return the program_name
	 */
	public String getProgram_name() {
		return program_name;
	}
	/**
	 * @param program_name the program_name to set
	 */
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	/**
	 * @return the form_number
	 */
	public String getForm_number() {
		return form_number;
	}
	/**
	 * @param form_number the form_number to set
	 */
	public void setForm_number(String form_number) {
		this.form_number = form_number;
	}
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the middle_name
	 */
	public String getMiddle_name() {
		return middle_name;
	}
	/**
	 * @param middle_name the middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the father_Fname
	 */
	public String getFather_Fname() {
		return father_Fname;
	}
	/**
	 * @param father_Fname the father_Fname to set
	 */
	public void setFather_Fname(String father_Fname) {
		this.father_Fname = father_Fname;
	}
	/**
	 * @return the father_Mname
	 */
	public String getFather_Mname() {
		return father_Mname;
	}
	/**
	 * @param father_Mname the father_Mname to set
	 */
	public void setFather_Mname(String father_Mname) {
		this.father_Mname = father_Mname;
	}
	/**
	 * @return the father_Lname
	 */
	public String getFather_Lname() {
		return father_Lname;
	}
	/**
	 * @param father_Lname the father_Lname to set
	 */
	public void setFather_Lname(String father_Lname) {
		this.father_Lname = father_Lname;
	}
	/**
	 * @return the mother_Fname
	 */
	public String getMother_Fname() {
		return mother_Fname;
	}
	/**
	 * @param mother_Fname the mother_Fname to set
	 */
	public void setMother_Fname(String mother_Fname) {
		this.mother_Fname = mother_Fname;
	}
	/**
	 * @return the mother_Mname
	 */
	public String getMother_Mname() {
		return mother_Mname;
	}
	/**
	 * @param mother_Mname the mother_Mname to set
	 */
	public void setMother_Mname(String mother_Mname) {
		this.mother_Mname = mother_Mname;
	}
	/**
	 * @return the mother_Lname
	 */
	public String getMother_Lname() {
		return mother_Lname;
	}
	/**
	 * @param mother_Lname the mother_Lname to set
	 */
	public void setMother_Lname(String mother_Lname) {
		this.mother_Lname = mother_Lname;
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
	 * @return the date_of_birth
	 */
	public String getDate_of_birth() {
		return date_of_birth;
	}
	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
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
	 * @return the category_code
	 */
	public String getCategory_code() {
		return category_code;
	}
	/**
	 * @param category_code the category_code to set
	 */
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	/**
	 * @return the marksPercentage
	 */
	public String getMarksPercentage() {
		return marksPercentage;
	}
	/**
	 * @param marksPercentage the marksPercentage to set
	 */
	public void setMarksPercentage(String marksPercentage) {
		this.marksPercentage = marksPercentage;
	}
	/**
	 * @return the marksObtained
	 */
	public String getMarksObtained() {
		return marksObtained;
	}
	/**
	 * @param marksObtained the marksObtained to set
	 */
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	/**
	 * @return the totalMarks
	 */
	public String getTotalMarks() {
		return totalMarks;
	}
	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	/**
	 * @return the computedMarks
	 */
	public String getComputedMarks() {
		return ComputedMarks;
	}
	/**
	 * @param computedMarks the computedMarks to set
	 */
	public void setComputedMarks(String computedMarks) {
		ComputedMarks = computedMarks;
	}
	/**
	 * @return the enrollment_number
	 */
	public String getEnrollment_number() {
		return enrollment_number;
	}
	/**
	 * @param enrollment_number the enrollment_number to set
	 */
	public void setEnrollment_number(String enrollment_number) {
		this.enrollment_number = enrollment_number;
	}
	/**
	 * @return the specialization_id
	 */
	public String getSpecialization_id() {
		return specialization_id;
	}
	/**
	 * @param specialization_id the specialization_id to set
	 */
	public void setSpecialization_id(String specialization_id) {
		this.specialization_id = specialization_id;
	}
	/**
	 * @return the specialization_name
	 */
	public String getSpecialization_name() {
		return specialization_name;
	}
	/**
	 * @param specialization_name the specialization_name to set
	 */
	public void setSpecialization_name(String specialization_name) {
		this.specialization_name = specialization_name;
	}
	/**
	 * @return the papercode
	 */
	public String getPapercode() {
		return papercode;
	}
	/**
	 * @param papercode the papercode to set
	 */
	public void setPapercode(String papercode) {
		this.papercode = papercode;
	}
	/**
	 * @return the newpapercode
	 */
	public String getNewpapercode() {
		return newpapercode;
	}
	/**
	 * @param newpapercode the newpapercode to set
	 */
	public void setNewpapercode(String newpapercode) {
		this.newpapercode = newpapercode;
	}
	/**
	 * @return the firstDegreeCode
	 */
	public String getFirstDegreeCode() {
		return firstDegreeCode;
	}
	/**
	 * @param firstDegreeCode the firstDegreeCode to set
	 */
	public void setFirstDegreeCode(String firstDegreeCode) {
		this.firstDegreeCode = firstDegreeCode;
	}
	/**
	 * @return the componentID
	 */
	public String getComponentID() {
		return componentID;
	}
	/**
	 * @param componentID the componentID to set
	 */
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}
	/**
	 * @return the weightageID
	 */
	public String getWeightageID() {
		return weightageID;
	}
	/**
	 * @param weightageID the weightageID to set
	 */
	public void setWeightageID(String weightageID) {
		this.weightageID = weightageID;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the creator_id
	 */
	public String getCreator_id() {
		return creator_id;
	}
	/**
	 * @param creator_id the creator_id to set
	 */
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	/**
	 * @return the modifier_id
	 */
	public String getModifier_id() {
		return modifier_id;
	}
	/**
	 * @param modifier_id the modifier_id to set
	 */
	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return Flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		Flag = flag;
	}
	/**
	 * @return the cos_value
	 */
	public String getCos_value() {
		return cos_value;
	}
	/**
	 * @param cos_value the cos_value to set
	 */
	public void setCos_value(String cos_value) {
		this.cos_value = cos_value;
	}
	/**
	 * @return the entity_id
	 */
	public String getEntity_id() {
		return entity_id;
	}
	/**
	 * @param entity_id the entity_id to set
	 */
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	/**
	 * @return the board_id
	 */
	public String getBoard_id() {
		return board_id;
	}
	/**
	 * @param board_id the board_id to set
	 */
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	/**
	 * @return the board_name
	 */
	public String getBoard_name() {
		return board_name;
	}
	/**
	 * @param board_name the board_name to set
	 */
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	/**
	 * @return the uGorPG
	 */
	public String getUGorPG() {
		return UGorPG;
	}
	/**
	 * @param gorPG the uGorPG to set
	 */
	public void setUGorPG(String gorPG) {
		UGorPG = gorPG;
	}
	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}
	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	/**
	 * @return the enddate
	 */
	public String getEnddate() {
		return enddate;
	}
	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/**
	 * @return the insert_time
	 */
	public String getInsert_time() {
		return insert_time;
	}
	/**
	 * @param insert_time the insert_time to set
	 */
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	/**
	 * @return the modification_time
	 */
	public String getModification_time() {
		return modification_time;
	}
	/**
	 * @param modification_time the modification_time to set
	 */
	public void setModification_time(String modification_time) {
		this.modification_time = modification_time;
	}
	/**
	 * @return the grouping
	 */
	public String getGrouping() {
		return grouping;
	}
	/**
	 * @param grouping the grouping to set
	 */
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	/**
	 * @return the specialWeightageFlag
	 */
	public String getSpecialWeightageFlag() {
		return specialWeightageFlag;
	}
	/**
	 * @param specialWeightageFlag the specialWeightageFlag to set
	 */
	public void setSpecialWeightageFlag(String specialWeightageFlag) {
		this.specialWeightageFlag = specialWeightageFlag;
	}
	/**
	 * @return the staffFlag
	 */
	public String getStaffFlag() {
		return staffFlag;
	}
	/**
	 * @param staffFlag the staffFlag to set
	 */
	public void setStaffFlag(String staffFlag) {
		this.staffFlag = staffFlag;
	}
	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
    
    
    
    
    
    

    
}
