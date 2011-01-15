package in.ac.dei.edrp.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ReportInfoGetter implements IsSerializable {

	//getter and setter for variables used in (CM_entityInfo.xml)
	//Files using in GreetingServiceImpl
	
	private String university_id;
	public String getUniversity_id() {
		return university_id;
	}

	public void setUniversity_id(String university_id) {
		this.university_id = university_id;
	}

	private String entity_id;
	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	
	private String entity_type;
	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}


	private String entity_name;
	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	
	private String entity_address;
	public String getEntity_address() {
		return entity_address;
	}

	public void setEntity_address(String entity_address) {
		this.entity_address = entity_address;
	}

	

	
	private String entity_city;
	
	public String getEntity_city() {
		return entity_city;
	}

	public void setEntity_city(String entity_city) {
		this.entity_city = entity_city;
	}
	private String entity_state;
	public String getEntity_state() {
		return entity_state;
	}
	private String entity_phone;
	public void setEntity_state(String entity_state) {
		this.entity_state = entity_state;
	}

	public String getEntity_phone() {
		return entity_phone;
	}


	private String fax;
	public void setEntity_phone(String entity_phone) {
		this.entity_phone = entity_phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}


	private String user_id;
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	
	private String insert_time;
	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}



	private String modification_time;
	public String getModification_time() {
		return modification_time;
	}

	public void setModification_time(String modification_time) {
		this.modification_time = modification_time;
	}

	
	private String creator_id;
	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}


	private String modifier_id;
	public String getModifier_id() {
		return modifier_id;
	}

	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}


	private String parent_entity_id;
	public String getParent_entity_id() {
		return parent_entity_id;
	}

	public void setParent_entity_id(String parent_entity_id) {
		this.parent_entity_id = parent_entity_id;
	}
	
	private String parent_entity_name;
	
	public String getParent_entity_name() {
		return parent_entity_name;
	}

	public void setParent_entity_name(String parent_entity_name) {
		this.parent_entity_name = parent_entity_name;
	}
	
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	private String criteria;
	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	private String entity_description;
	public String getEntity_description() {
		return entity_description;
	}

	public void setEntity_description(String entity_description) {
		this.entity_description = entity_description;
	}
	
	private String program_name;
	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	
	private String offered_by;
	public String getOffered_by() {
		return offered_by;
	}

	public void setOffered_by(String offered_by) {
		this.offered_by = offered_by;
	}
	
	private String branch_name;
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	private String program_id;
	public String getProgram_id() {
		return program_id;
	}

	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}
	
	

	

	

	

	

	

	

	/*
	 * For update computed Marks properties are
	 */
	private String registration_number;
	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}
	
	private String component_id;
	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}
	
	private double marks_percentage;
	public double getMarks_percentage() {
		return marks_percentage;
	}

	public void setMarks_percentage(double marks_percentage) {
		this.marks_percentage = marks_percentage;
	}
	
	private String flag;
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	private String branch_code;
	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	
	private double computed_Marks;
	public double getComputed_Marks() {
		return computed_Marks;
	}

	public void setComputed_Marks(double computed_Marks) {
		this.computed_Marks = computed_Marks;
	}

	private double actual_computed_Marks;

	public double getActual_computed_Marks() {
		return actual_computed_Marks;
	}

	public void setActual_computed_Marks(double actual_computed_Marks) {
		this.actual_computed_Marks = actual_computed_Marks;
	}
	
	private double component_Weightage;
	public double getComponent_Weightage() {
		return component_Weightage;
	}

	public void setComponent_Weightage(double component_Weightage) {
		this.component_Weightage = component_Weightage;
	}
	
	private double weightage_percentage;
	public double getWeightage_percentage() {
		return weightage_percentage;
	}

	public void setWeightage_percentage(double weightage_percentage) {
		this.weightage_percentage = weightage_percentage;
	}
	
	private String weightage_id;
	public String getWeightage_id() {
		return weightage_id;
	}

	public void setWeightage_id(String weightage_id) {
		this.weightage_id = weightage_id;
	}
	
	private String weight_id;

	public String getWeight_id() {
		return weight_id;
	}

	public void setWeight_id(String weight_id) {
		this.weight_id = weight_id;
	}
	
	private double percentage;
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	private String board_id;
	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	
	private String weightage_flag;
	public String getWeightage_flag() {
		return weightage_flag;
	}

	public void setWeightage_flag(String weightage_flag) {
		this.weightage_flag = weightage_flag;
	}
	
	private String board_flag;
	public String getBoard_flag() {
		return board_flag;
	}

	public void setBoard_flag(String board_flag) {
		this.board_flag = board_flag;
	}
	
	private String special_weightage_flag;
	public String getSpecial_weightage_flag() {
		return special_weightage_flag;
	}

	public void setSpecial_weightage_flag(String special_weightage_flag) {
		this.special_weightage_flag = special_weightage_flag;
	}

	private double normalization_factor;

	public double getNormalization_factor() {
		return normalization_factor;
	}

	public void setNormalization_factor(double normalization_factor) {
		this.normalization_factor = normalization_factor;
	}
	
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	private String flag_status;
	public String getFlag_status() {
		return flag_status;
	}

	public void setFlag_status(String flag_status) {
		this.flag_status = flag_status;
	}
	
	private double component_eligiblity;
	public double getComponent_eligiblity() {
		return component_eligiblity;
	}

	public void setComponent_eligiblity(double component_eligiblity) {
		this.component_eligiblity = component_eligiblity;
	}
	
	private String cos_value;
	public String getCos_value() {
		return cos_value;
	}

	public void setCos_value(String cos_value) {
		this.cos_value = cos_value;
	}
	
	private String category;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	private String component_criteria_flag;
	public String getComponent_criteria_flag() {
		return component_criteria_flag;
	}

	public void setComponent_criteria_flag(String component_criteria_flag) {
		this.component_criteria_flag = component_criteria_flag;
	}
	
	private Date vardate;
	public Date getVardate() {
		return vardate;
	}

	public void setVardate(Date vardate) {
		this.vardate = vardate;
	}
	
	private int age_eligibility;
	public int getAge_eligibility() {
		return age_eligibility;
	}

	public void setAge_eligibility(int age_eligibility) {
		this.age_eligibility = age_eligibility;
	}
	
	private String compare_date;
	public String getCompare_date() {
		return compare_date;
	}

	public void setCompare_date(String compare_date) {
		this.compare_date = compare_date;
	}

	private int diff;
	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}
	
	private double sum_computed_marks;
	public double getSum_computed_marks() {
		return sum_computed_marks;
	}

	public void setSum_computed_marks(double sum_computed_marks) {
		this.sum_computed_marks = sum_computed_marks;
	}
	
	private double sum_actual_computed_marks;
	public double getSum_actual_computed_marks() {
		return sum_actual_computed_marks;
	}

	public void setSum_actual_computed_marks(double sum_actual_computed_marks) {
		this.sum_actual_computed_marks = sum_actual_computed_marks;
	}
	
	private String reason_code;
	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}
	
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReportInfoGetter(){
		//super();
	}
	private String comp;
	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	private String max_test_number;
	public String getMax_test_number() {
		return max_test_number;
	}

	public void setMax_test_number(String max_test_number) {
		this.max_test_number = max_test_number;
	}
	
	private String city;
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	private String test_number;
	public String getTest_number() {
		return test_number;
	}

	public void setTest_number(String test_number) {
		this.test_number = test_number;
	}
	
	private String called;
	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	private String first_name;
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	private String middle_name;
	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	private String last_name;
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String date_of_birth;
	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	private String gender;
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	private String student_id;
	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	private double cut_off_number;
	public double getCut_off_number() {
		return cut_off_number;
	}

	public void setCut_off_number(double cut_off_number) {
		this.cut_off_number = cut_off_number;
	}

	private String component_description;
	public String getComponent_description() {
		return component_description;
	}

	public void setComponent_description(String component_description) {
		this.component_description = component_description;
	}

	private String attendance;
	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
	private String attendance_flag;
	public String getAttendance_flag() {
		return attendance_flag;
	}

	public void setAttendance_flag(String attendance_flag) {
		this.attendance_flag = attendance_flag;
	}
	
	private int cos_seat;
	public int getCos_seat() {
		return cos_seat;
	}

	public void setCos_seat(int cos_seat) {
		this.cos_seat = cos_seat;
	}

	private String marks;
	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	private double total_marks;
	public double getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(double total_marks) {
		this.total_marks = total_marks;
	}
	
	private String start_date;
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	private String end_date;
	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	private String university_start_date;
	public String getUniversity_start_date() {
		return university_start_date;
	}

	public void setUniversity_start_date(String university_start_date) {
		this.university_start_date = university_start_date;
	}
	
	private String university_end_date;
	public String getUniversity_end_date() {
		return university_end_date;
	}

	public void setUniversity_end_date(String university_end_date) {
		this.university_end_date = university_end_date;
	}

	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String maincity;
	public String getMaincity() {
		return maincity;
	}

	public void setMaincity(String maincity) {
		this.maincity = maincity;
	}

	private String value;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ReportInfoGetter(String program_id, String entity_id,
			String branch_code,String registration_number,String cos_value,String comp, String reason_code,
			String message) {
		super();
		this.entity_id = entity_id;
		this.program_id = program_id;
		this.branch_code = branch_code;
		this.registration_number = registration_number;
		this.comp=comp;
		this.cos_value=cos_value;
		this.reason_code = reason_code;
		this.message = message;
	}
	

	
}
