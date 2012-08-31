package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;
import java.util.List;
public class ReportInfoGetter
    implements IsSerializable
{

    private int countSpecialization;
    private String specializationCode;
    private String university_id;
    private String entity_id;
    private String entity_type;
    private String entity_name;
    private String entity_address;
    private String entity_city;
    private String entity_state;
    private String entity_phone;
    private String fax;
    private String user_id;
    private String insert_time;
    private String modification_time;
    private String creator_id;
    private String modifier_id;
    private String parent_entity_id;
    private String parent_entity_name;
    private String level;
    private String criteria;
    private String entity_description;
    private String program_name;
    private String offered_by;
    private String branch_name;
    private String program_id;
    private String registration_number;
    private String component_id;
    private double marks_percentage;
    private String flag;
    private String branch_code;
    private double computed_Marks;
    private double actual_computed_Marks;
    private double component_Weightage;
    private double weightage_percentage;
    private String weightage_id;
    private String weight_id;
    private double percentage;
    private String board_id;
    private String weightage_flag;
    private String board_flag;
    private String special_weightage_flag;
    private double normalization_factor;
    private String type;
    private int count;
    private String flag_status;
    private double component_eligiblity;
    private String cos_value;
    private String category;
    private String component_criteria_flag;
    private Date vardate;
    private int age_eligibility;
    private String compare_date;
    private int diff;
    private double sum_computed_marks;
    private double sum_actual_computed_marks;
    private String reason_code;
    private String message;
    private String comp;
    private String max_test_number;
    private String city;
    private String test_number;
    private String called;
    private String status;
    private String first_name;
    private String middle_name;
    private String last_name;
    public String date_of_birth;
    private String gender;
    private String student_id;
    private double cut_off_number;
    private String component_description;
    private String attendance;
    private String attendance_flag;
    private int cos_seat;
    private String marks;
    private double total_marks;
    private String start_date;
    private String end_date;
    private String university_start_date;
    private String university_end_date;
    private String description;
    private String maincity;
    private String value;
    private String code;
    private String categoryCode;
    private String fatherFirstName;
    private String fatherMiddleName;
    private String fatherLastName;
    private double marksObtained;
    private String formNumber;
    private String subject_code;//Add by Devendra
    private List<String>list;//Add By Devendra May 10
    private String groupCode;//Add By Devendra May 10
    private String groupMarks;//Add By Devendra May 10
	
	 private String gn_number;//Add by Devendra May 29
    private String bc_number;//Add by Devendra May 29
    private String sc_number;//Add by Devendra May 29
    private String st_number;//Add by Devendra May 29
    
	
	 /**
	 * @return the gn_number
	 */
	public String getGn_number() {
		return gn_number;
	}

	/**
	 * @param gn_number the gn_number to set
	 */
	public void setGn_number(String gn_number) {
		this.gn_number = gn_number;
	}

	/**
	 * @return the bc_number
	 */
	public String getBc_number() {
		return bc_number;
	}

	/**
	 * @param bc_number the bc_number to set
	 */
	public void setBc_number(String bc_number) {
		this.bc_number = bc_number;
	}

	/**
	 * @return the sc_number
	 */
	public String getSc_number() {
		return sc_number;
	}

	/**
	 * @param sc_number the sc_number to set
	 */
	public void setSc_number(String sc_number) {
		this.sc_number = sc_number;
	}

	/**
	 * @return the st_number
	 */
	public String getSt_number() {
		return st_number;
	}

	/**
	 * @param st_number the st_number to set
	 */
	public void setSt_number(String st_number) {
		this.st_number = st_number;
	}

	
	 /**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return the groupMarks
	 */
	public String getGroupMarks() {
		return groupMarks;
	}

	/**
	 * @param groupMarks the groupMarks to set
	 */
	public void setGroupMarks(String groupMarks) {
		this.groupMarks = groupMarks;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}
    
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

	public ReportInfoGetter()
    {
    }

    public ReportInfoGetter(String program_id, String entity_id, String subjectCode, String registration_number, String cos_value, String comp, String reason_code, 
            String message)
    {
        this.entity_id = entity_id;
        this.program_id = program_id;
         this.subject_code = subjectCode;//Changed by devendra May 26
        this.registration_number = registration_number;
        this.comp = comp;
        this.cos_value = cos_value;
        this.reason_code = reason_code;
        this.message = message;
    }

    public ReportInfoGetter(String program_id, String entity_id, String branch_code, String registration_number, String cos_value, String comp, String reason_code, 
            String message, String specialization_id)
    {
        this.entity_id = entity_id;
        this.program_id = program_id;
        this.branch_code = branch_code;
        this.registration_number = registration_number;
        this.comp = comp;
        this.cos_value = cos_value;
        this.reason_code = reason_code;
        this.message = message;
        specializationCode = specialization_id;
    }

    public String getCategoryCode()
    {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public String getSpecializationCode()
    {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode)
    {
        this.specializationCode = specializationCode;
    }

    public int getCountSpecialization()
    {
        return countSpecialization;
    }

    public void setCountSpecialization(int countSpecialization)
    {
        this.countSpecialization = countSpecialization;
    }

    public String getUniversity_id()
    {
        return university_id;
    }

    public void setUniversity_id(String university_id)
    {
        this.university_id = university_id;
    }

    public String getEntity_id()
    {
        return entity_id;
    }

    public void setEntity_id(String entity_id)
    {
        this.entity_id = entity_id;
    }

    public String getEntity_type()
    {
        return entity_type;
    }

    public void setEntity_type(String entity_type)
    {
        this.entity_type = entity_type;
    }

    public String getEntity_name()
    {
        return entity_name;
    }

    public void setEntity_name(String entity_name)
    {
        this.entity_name = entity_name;
    }

    public String getEntity_address()
    {
        return entity_address;
    }

    public void setEntity_address(String entity_address)
    {
        this.entity_address = entity_address;
    }

    public String getEntity_city()
    {
        return entity_city;
    }

    public void setEntity_city(String entity_city)
    {
        this.entity_city = entity_city;
    }

    public String getEntity_state()
    {
        return entity_state;
    }

    public void setEntity_state(String entity_state)
    {
        this.entity_state = entity_state;
    }

    public String getEntity_phone()
    {
        return entity_phone;
    }

    public void setEntity_phone(String entity_phone)
    {
        this.entity_phone = entity_phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getInsert_time()
    {
        return insert_time;
    }

    public void setInsert_time(String insert_time)
    {
        this.insert_time = insert_time;
    }

    public String getModification_time()
    {
        return modification_time;
    }

    public void setModification_time(String modification_time)
    {
        this.modification_time = modification_time;
    }

    public String getCreator_id()
    {
        return creator_id;
    }

    public void setCreator_id(String creator_id)
    {
        this.creator_id = creator_id;
    }

    public String getModifier_id()
    {
        return modifier_id;
    }

    public void setModifier_id(String modifier_id)
    {
        this.modifier_id = modifier_id;
    }

    public String getParent_entity_id()
    {
        return parent_entity_id;
    }

    public void setParent_entity_id(String parent_entity_id)
    {
        this.parent_entity_id = parent_entity_id;
    }

    public String getParent_entity_name()
    {
        return parent_entity_name;
    }

    public void setParent_entity_name(String parent_entity_name)
    {
        this.parent_entity_name = parent_entity_name;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getCriteria()
    {
        return criteria;
    }

    public void setCriteria(String criteria)
    {
        this.criteria = criteria;
    }

    public String getEntity_description()
    {
        return entity_description;
    }

    public void setEntity_description(String entity_description)
    {
        this.entity_description = entity_description;
    }

    public String getProgram_name()
    {
        return program_name;
    }

    public void setProgram_name(String program_name)
    {
        this.program_name = program_name;
    }

    public String getOffered_by()
    {
        return offered_by;
    }

    public void setOffered_by(String offered_by)
    {
        this.offered_by = offered_by;
    }

    public String getBranch_name()
    {
        return branch_name;
    }

    public void setBranch_name(String branch_name)
    {
        this.branch_name = branch_name;
    }

    public String getProgram_id()
    {
        return program_id;
    }

    public void setProgram_id(String program_id)
    {
        this.program_id = program_id;
    }

    public String getRegistration_number()
    {
        return registration_number;
    }

    public void setRegistration_number(String registration_number)
    {
        this.registration_number = registration_number;
    }

    public String getComponent_id()
    {
        return component_id;
    }

    public void setComponent_id(String component_id)
    {
        this.component_id = component_id;
    }

    public double getMarks_percentage()
    {
        return marks_percentage;
    }

    public void setMarks_percentage(double marks_percentage)
    {    	
        this.marks_percentage = marks_percentage;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getBranch_code()
    {
        return branch_code;
    }

    public void setBranch_code(String branch_code)
    {
        this.branch_code = branch_code;
    }

    public double getComputed_Marks()
    {
        return computed_Marks;
    }

    public void setComputed_Marks(double computed_Marks)
    {
        this.computed_Marks = computed_Marks;
    }

    public double getActual_computed_Marks()
    {
        return actual_computed_Marks;
    }

    public void setActual_computed_Marks(double actual_computed_Marks)
    {
        this.actual_computed_Marks = actual_computed_Marks;
    }

    public double getComponent_Weightage()
    {
        return component_Weightage;
    }

    public void setComponent_Weightage(double component_Weightage)
    {
        this.component_Weightage = component_Weightage;
    }

    public double getWeightage_percentage()
    {
        return weightage_percentage;
    }

    public void setWeightage_percentage(double weightage_percentage)
    {
        this.weightage_percentage = weightage_percentage;
    }

    public String getWeightage_id()
    {
        return weightage_id;
    }

    public void setWeightage_id(String weightage_id)
    {
        this.weightage_id = weightage_id;
    }

    public String getWeight_id()
    {
        return weight_id;
    }

    public void setWeight_id(String weight_id)
    {
        this.weight_id = weight_id;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public void setPercentage(double percentage)
    {
        this.percentage = percentage;
    }

    public String getBoard_id()
    {
        return board_id;
    }

    public void setBoard_id(String board_id)
    {
        this.board_id = board_id;
    }

    public String getWeightage_flag()
    {
        return weightage_flag;
    }

    public void setWeightage_flag(String weightage_flag)
    {
        this.weightage_flag = weightage_flag;
    }

    public String getBoard_flag()
    {
        return board_flag;
    }

    public void setBoard_flag(String board_flag)
    {
        this.board_flag = board_flag;
    }

    public String getSpecial_weightage_flag()
    {
        return special_weightage_flag;
    }

    public void setSpecial_weightage_flag(String special_weightage_flag)
    {
        this.special_weightage_flag = special_weightage_flag;
    }

    public double getNormalization_factor()
    {
        return normalization_factor;
    }

    public void setNormalization_factor(double normalization_factor)
    {
        this.normalization_factor = normalization_factor;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getFlag_status()
    {
        return flag_status;
    }

    public void setFlag_status(String flag_status)
    {
        this.flag_status = flag_status;
    }

    public double getComponent_eligiblity()
    {
        return component_eligiblity;
    }

    public void setComponent_eligiblity(double component_eligiblity)
    {
        this.component_eligiblity = component_eligiblity;
    }

    public String getCos_value()
    {
        return cos_value;
    }

    public void setCos_value(String cos_value)
    {
        this.cos_value = cos_value;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getComponent_criteria_flag()
    {
        return component_criteria_flag;
    }

    public void setComponent_criteria_flag(String component_criteria_flag)
    {
        this.component_criteria_flag = component_criteria_flag;
    }

    public Date getVardate()
    {
        return vardate;
    }

    public void setVardate(Date vardate)
    {
        this.vardate = vardate;
    }

    public int getAge_eligibility()
    {
        return age_eligibility;
    }

    public void setAge_eligibility(int age_eligibility)
    {
        this.age_eligibility = age_eligibility;
    }

    public String getCompare_date()
    {
        return compare_date;
    }

    public void setCompare_date(String compare_date)
    {
        this.compare_date = compare_date;
    }

    public int getDiff()
    {
        return diff;
    }

    public void setDiff(int diff)
    {
        this.diff = diff;
    }

    public double getSum_computed_marks()
    {
        return sum_computed_marks;
    }

    public void setSum_computed_marks(double sum_computed_marks)
    {
        this.sum_computed_marks = sum_computed_marks;
    }

    public double getSum_actual_computed_marks()
    {
        return sum_actual_computed_marks;
    }

    public void setSum_actual_computed_marks(double sum_actual_computed_marks)
    {
        this.sum_actual_computed_marks = sum_actual_computed_marks;
    }

    public String getReason_code()
    {
        return reason_code;
    }

    public void setReason_code(String reason_code)
    {
        this.reason_code = reason_code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getComp()
    {
        return comp;
    }

    public void setComp(String comp)
    {
        this.comp = comp;
    }

    public String getMax_test_number()
    {
        return max_test_number;
    }

    public void setMax_test_number(String max_test_number)
    {
        this.max_test_number = max_test_number;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getTest_number()
    {
        return test_number;
    }

    public void setTest_number(String test_number)
    {
        this.test_number = test_number;
    }

    public String getCalled()
    {
        return called;
    }

    public void setCalled(String called)
    {
        this.called = called;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getMiddle_name()
    {
        return middle_name;
    }

    public void setMiddle_name(String middle_name)
    {
        this.middle_name = middle_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getDate_of_birth()
    {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth)
    {
        this.date_of_birth = date_of_birth;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getStudent_id()
    {
        return student_id;
    }

    public void setStudent_id(String student_id)
    {
        this.student_id = student_id;
    }

    public double getCut_off_number()
    {
        return cut_off_number;
    }

    public void setCut_off_number(double cut_off_number)
    {
        this.cut_off_number = cut_off_number;
    }

    public String getComponent_description()
    {
        return component_description;
    }

    public void setComponent_description(String component_description)
    {
        this.component_description = component_description;
    }

    public String getAttendance()
    {
        return attendance;
    }

    public void setAttendance(String attendance)
    {
        this.attendance = attendance;
    }

    public String getAttendance_flag()
    {
        return attendance_flag;
    }

    public void setAttendance_flag(String attendance_flag)
    {
        this.attendance_flag = attendance_flag;
    }

    public int getCos_seat()
    {
        return cos_seat;
    }

    public void setCos_seat(int cos_seat)
    {
        this.cos_seat = cos_seat;
    }

    public String getMarks()
    {
        return marks;
    }

    public void setMarks(String marks)
    {
        this.marks = marks;
    }

    public double getTotal_marks()
    {
        return total_marks;
    }

    public void setTotal_marks(double total_marks)
    {
        this.total_marks = total_marks;
    }

    public String getStart_date()
    {
        return start_date;
    }

    public void setStart_date(String start_date)
    {
        this.start_date = start_date;
    }

    public String getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(String end_date)
    {
        this.end_date = end_date;
    }

    public String getUniversity_start_date()
    {
        return university_start_date;
    }

    public void setUniversity_start_date(String university_start_date)
    {
        this.university_start_date = university_start_date;
    }

    public String getUniversity_end_date()
    {
        return university_end_date;
    }

    public void setUniversity_end_date(String university_end_date)
    {
        this.university_end_date = university_end_date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getMaincity()
    {
        return maincity;
    }

    public void setMaincity(String maincity)
    {
        this.maincity = maincity;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getFatherFirstName()
    {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName)
    {
        this.fatherFirstName = fatherFirstName;
    }

    public String getFatherMiddleName()
    {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName)
    {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName()
    {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName)
    {
        this.fatherLastName = fatherLastName;
    }

    public double getMarksObtained()
    {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained)
    {
        this.marksObtained = marksObtained;
    }

    public String getFormNumber()
    {
        return formNumber;
    }

    public void setFormNumber(String formNumber)
    {
        this.formNumber = formNumber;
    }
}
