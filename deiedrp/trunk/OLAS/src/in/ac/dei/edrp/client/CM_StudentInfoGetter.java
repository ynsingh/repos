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
	
	
	public String getNewpapercode() {
		return newpapercode;
	}
	public void setNewpapercode(String newpapercode) {
		this.newpapercode = newpapercode;
	}

	private String papercode;
	private String newpapercode;
	private String firstDegreeCode;		
	private String componentID;
	private String weightageID;
	private String user_id;
	private String creator_id;
	private String modifier_id;
	public String getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}

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
	
	
	
    public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	public String getGrouping() {
		return grouping;
	}
    
    public String getModification_time() {
		return modification_time;
	}
	public void setModification_time(String modification_time) {
		this.modification_time = modification_time;
	}
	public String getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	
    
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

    public String getCreator_id() {
		return creator_id;
	}
    
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	
	public String getUGorPG() {
		return UGorPG;
	}
	public void setUGorPG(String gorPG) {
		UGorPG = gorPG;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	public String getForm_number() {
		return form_number;
	}
	public void setForm_number(String form_number) {
		this.form_number = form_number;
	}
	public String getComponentID() {
		return componentID;
	}
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}
	public String getWeightageID() {
		return weightageID;
	}
	public void setWeightageID(String weightageID) {
		this.weightageID = weightageID;
	}
	public String getFirstDegreeCode() {
		return firstDegreeCode;
	}
	public void setFirstDegreeCode(String firstDegreeCode) {
		this.firstDegreeCode = firstDegreeCode;
	}
	public String getPapercode() {
		return papercode;
	}
	public void setPapercode(String papercode) {
		this.papercode = papercode;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public CM_StudentInfoGetter() {
	}
	public String getuniversity_code() {
		return university_code;
	}

	public void setuniversity_code(String university_code) {
		this.university_code = university_code;

	}

	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	
	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;

	}
	public String getmiddle_name() {
		return middle_name;
	}

	public void setmiddle_name(String middle_name) {
		this.middle_name = middle_name;

	}
	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;

	}
	public String getfather_Fname() {
		return father_Fname;
	}

	public void setfather_Fname(String father_Fname) {
		this.father_Fname = father_Fname;

	}
	public String getfather_Mname() {
		return father_Mname;
	}

	public void setfather_Mname(String father_Mname) {
		this.father_Mname = father_Mname;

	}
	public String getfather_Lname() {
		return father_Lname;
	}

	public void setfather_Lname(String father_Lname) {
		this.father_Lname = father_Lname;

	}
	public String getmother_Fname() {
		return mother_Fname;
	}

	public void setmother_Fname(String mother_Fname) {
		this.mother_Fname = mother_Fname;

	}
	public String getmother_Mname() {
		return mother_Mname;
	}

	public void setmother_Mname(String mother_Mname) {
		this.mother_Mname = mother_Mname;

	}
	public String getmother_Lname() {
		return mother_Lname;
	}

	public void setmother_Lname(String mother_Lname) {
		this.mother_Lname = mother_Lname;

	}
	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;

	}
	public String getdate_of_birth() {
		return date_of_birth;
	}

	public void setdate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;

	}
	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;

	}
	


	public String getMarksPercentage() {
		return marksPercentage;
	}
	public void setMarksPercentage(String marksPercentage) {
		this.marksPercentage = marksPercentage;
	}
	

	public String getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}
	
	
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getComputedMarks() {
		return ComputedMarks;
	}

	public void setComputedMarks(String ComputedMarks) {
		this.ComputedMarks = ComputedMarks;
	}

//	
//	public String getPaperCode() {
//		return PaperCode;
//	}
//
//	public void setPaperCode(String PaperCode) {
//		this.PaperCode = PaperCode;
//	}
	
//	public String getFirstDegreeCode() {
//		return FirstDegreeCode;
//	}
//
//	public void setFirstDegreeCode(String FirstDegreeCode) {
//		this.FirstDegreeCode = FirstDegreeCode;
//	}
	
	
//	public String getWeightageID() {
//		return WeightageID;
//	}
//
//	public void setWeightageID(String WeightageID) {
//		this.WeightageID = WeightageID;
//
//	}
	
//	public String getUserId() {
//		return user_id;
//	}
//
//	public void setUserId(String user_id) {
//		this.user_id = user_id;
//
//	}
	
	
	public boolean getFlag() {
		return Flag;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setFlag(boolean Flag) {
		this.Flag = Flag;
	}
	public String getcos_value() {
		return cos_value;
	}

	public void setcos_value(String cos_value) {
		this.cos_value = cos_value;
	}
	public String getentity_id() {
		return entity_id;
	}

	public void setentity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	
	public String getboard_id() {
		return board_id;
	}

	public void setboard_id(String board_id) {
		this.board_id = board_id;
	}
	
	public String getboard_name() {
		return board_name;
	}

	public void setboard_name(String board_name) {
		this.board_name = board_name;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_code() {
		return category_code;
	}
	
	public void setEnrollmentNumber(String enrollment_number) {
		this.enrollment_number = enrollment_number;
	}
	public String getEnrollmentNumber() {
		return enrollment_number;
	}
	
	
}

