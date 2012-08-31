package in.ac.dei.edrp.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_ProgramInfoGetter implements IsSerializable {
    private String university_code;
    private Date session_sdate;
    private Date session_edate;
    private String cal_basis;
    private String cal_basisDescription;
    private String logic;
    private String logicDescription;
    private String userID;
    private String userName;
    private String formID;
    private String formName;
    
    public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getCal_basis() {
		return cal_basis;
 	}
 
	public void setCal_basis(String cal_basis) {
		this.cal_basis = cal_basis;
	}

	public String getCal_basisDescription() {
		return cal_basisDescription;
	}

	public void setCal_basisDescription(String cal_basisDescription) {
		this.cal_basisDescription = cal_basisDescription;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getLogicDescription() {
		return logicDescription;
	}

	public void setLogicDescription(String logicDescription) {
		this.logicDescription = logicDescription;
	}

	public Date getSession_sdate() {
		return session_sdate; 
	}
   
	public void setSession_sdate(Date session_sdate) {
		this.session_sdate = session_sdate;
	}

	public Date getSession_edate() {
		return session_edate;
	}

	public void setSession_edate(Date session_edate) {
		this.session_edate = session_edate;
	}

	
    private String entity_program_id;
    private String program_id;
    private String program_name;
    private String Entity_program_TermID;
    private String course_code;
    private String Flag;
    private String Available;
    private String optional;
    private String credits;
    private String lectures;
    private String tutorials;
    private String practicals;
    private String course_type;
    private String units;
    private boolean status;
    private String term;
    private String paper_code;
    private String paper_description;
    private String first_degree_code;
    private String component_id;
    private String branch_name;
    private String branch;
    private String entity_name;
    private String entity_id;
    private String cos_seats;
    public String getCos_seats() {
		return cos_seats;
	}

	public void setCos_seats(String cos_seats) {
		this.cos_seats = cos_seats;
	}

	private String cos_value;
    private String age_limit;
    private String category;
    private String grouping;
    private String group_code;
    private String componentId;
    private String componentDescription;
    private String branchName;
    private String subject_code;//Add by Devendra
    private String subject_description;//Add by Devendra
    private String catTypeDescription;
    private String catTypeId;
    private String groupCode;
    
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

	/**
	 * @return the subject_description
	 */
	public String getSubject_description() {
		return subject_description;
	}

	/**
	 * @param subject_description the subject_description to set
	 */
	public void setSubject_description(String subject_description) {
		this.subject_description = subject_description;
	}

	/*
     * programInfoGetter
     */
    private String mode_name;
    private String program_mode;
    private String type_name;
    private String program_type;

    /*
     * programinfogetter
     */
    private String program_code;
    private Boolean specialization;
    private int no_of_terms;
    private float total_credits;
    private int Number_of_Attempt_allowed;
    private int max_Fail_Subjects;
    private String Fixed_Duration;
    private String UGorPG;
    private String entity_type;
    private int entity_program_term_id;
    private String registration_start_date;
    private String last_date;
    private String add_drop_period;
    private String insert_time;
    private String modification_time;
    private String entity_description;
    private String university_id;
    private String no_of_times;
    private String cos_group;
    private String no_of_seats;
    private int defaultseats;
    private String session_start_date;
    private String session_end_date;
    private String settings;
    private String description;
    private String rule_no;
    private String type;
    private float weightage;
    private String sequence;
    private String component;
    private int total_marks;
    private String branch_code;
    private float cut_off_number;
    private String no_of_times_active;
    private String cut_off_percentage_active;
    private String cut_off_number_active;
    private float cut_off_percentage;
    private float percentage_seats;
    private float component_criteria;
    private String board_flag;
    private String eligibility_flag;
    private String special_flag;
    private String weightage_flag;
    private String creator_id;
    private String modifier_id;
    private String value;
    private String specialization_id;
    private String specialization_name;
    private String branch_id;
    private String university_name;
    private String X_Factor;
    private String academicImpact;
    public String getAcademicImpact() {
		return academicImpact;
	}

	public void setAcademicImpact(String academicImpact) {
		this.academicImpact = academicImpact;
	}

	public String getX_Factor() {
		return X_Factor;
	}

	public void setX_Factor(String x_Factor) {
		X_Factor = x_Factor;
	}

	public CM_ProgramInfoGetter() {
    }

	public String getAttendanceImpact() {
		return attendanceImpact;
	}

	public void setAttendanceImpact(String attendanceImpact) {
		this.attendanceImpact = attendanceImpact;
	}

	public String getWeightagePercentage() {
		return weightagePercentage;
	}

	public void setWeightagePercentage(String weightagePercentage) {
		this.weightagePercentage = weightagePercentage;
	}

	private String attendanceImpact;
	private String weightagePercentage;
    /**
         * @return the university_name
         */
    public String getUniversity_name() {
        return university_name;
    }

    /**
     * @param university_name the university_name to set
     */
    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getUniversity_code() {
        return university_code;
    }

    public void setUniversity_code(String university_code) {
        this.university_code = university_code;
    }

    public String getEntity_program_id() {
        return entity_program_id;
    }

    public void setEntity_program_id(String entity_program_id) {
        this.entity_program_id = entity_program_id;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getEntity_program_TermID() {
        return Entity_program_TermID;
    }

    public void setEntity_program_TermID(String entity_program_TermID) {
        Entity_program_TermID = entity_program_TermID;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getLectures() {
        return lectures;
    }

    public void setLectures(String lectures) {
        this.lectures = lectures;
    }

    public String getTutorials() {
        return tutorials;
    }

    public void setTutorials(String tutorials) {
        this.tutorials = tutorials;
    }

    public String getPracticals() {
        return practicals;
    }

    public void setPracticals(String practicals) {
        this.practicals = practicals;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPaper_code() {
        return paper_code;
    }

    public void setPaper_code(String paper_code) {
        this.paper_code = paper_code;
    }

    public String getPaper_description() {
        return paper_description;
    }

    public void setPaper_description(String paper_description) {
        this.paper_description = paper_description;
    }

    public String getFirst_degree_code() {
        return first_degree_code;
    }

    public void setFirst_degree_code(String first_degree_code) {
        this.first_degree_code = first_degree_code;
    }

    public String getComponent_id() {
        return component_id;
    }

    public void setComponent_id(String component_id) {
        this.component_id = component_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getCos_value() {
        return cos_value;
    }

    public void setCos_value(String cos_value) {
        this.cos_value = cos_value;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }

    public String getProgram_mode() {
        return program_mode;
    }

    public void setProgram_mode(String program_mode) {
        this.program_mode = program_mode;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getProgram_type() {
        return program_type;
    }

    public void setProgram_type(String program_type) {
        this.program_type = program_type;
    }

    public String getProgram_code() {
        return program_code;
    }

    public void setProgram_code(String program_code) {
        this.program_code = program_code;
    }

    public Boolean getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Boolean specialization) {
        this.specialization = specialization;
    }

    public int getNo_of_terms() {
        return no_of_terms;
    }

    public void setNo_of_terms(int no_of_terms) {
        this.no_of_terms = no_of_terms;
    }

    public float getTotal_credits() {
        return total_credits;
    }

    public void setTotal_credits(float total_credits) {
        this.total_credits = total_credits;
    }

    public int getNumber_of_Attempt_allowed() {
        return Number_of_Attempt_allowed;
    }

    public void setNumber_of_Attempt_allowed(int number_of_Attempt_allowed) {
        Number_of_Attempt_allowed = number_of_Attempt_allowed;
    }

    public int getMax_Fail_Subjects() {
        return max_Fail_Subjects;
    }

    public void setMax_Fail_Subjects(int max_Fail_Subjects) {
        this.max_Fail_Subjects = max_Fail_Subjects;
    }

    public String getFixed_Duration() {
        return Fixed_Duration;
    }

    public void setFixed_Duration(String fixed_Duration) {
        Fixed_Duration = fixed_Duration;
    }

    public String getUGorPG() {
        return UGorPG;
    }

    public void setUGorPG(String gorPG) {
        UGorPG = gorPG;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public int getEntity_program_term_id() {
        return entity_program_term_id;
    }

    public void setEntity_program_term_id(int entity_program_term_id) {
        this.entity_program_term_id = entity_program_term_id;
    }

    public String getRegistration_start_date() {
        return registration_start_date;
    }

    public void setRegistration_start_date(String registration_start_date) {
        this.registration_start_date = registration_start_date;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getAdd_drop_period() {
        return add_drop_period;
    }

    public void setAdd_drop_period(String add_drop_period) {
        this.add_drop_period = add_drop_period;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getModification_time() {
        return modification_time;
    }

    public void setModification_time(String modification_time) {
        this.modification_time = modification_time;
    }

    public String getEntity_description() {
        return entity_description;
    }

    public void setEntity_description(String entity_description) {
        this.entity_description = entity_description;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getNo_of_times() {
        return no_of_times;
    }

    public void setNo_of_times(String no_of_times) {
        this.no_of_times = no_of_times;
    }

    public String getCos_group() {
        return cos_group;
    }

    public void setCos_group(String cos_group) {
        this.cos_group = cos_group;
    }

    public String getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(String no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    public int getDefaultseats() {
        return defaultseats;
    }

    public void setDefaultseats(int defaultseats) {
        this.defaultseats = defaultseats;
    }

    public String getSession_start_date() {
        return session_start_date;
    }

    public void setSession_start_date(String session_start_date) {
        this.session_start_date = session_start_date;
    }

    public String getSession_end_date() {
        return session_end_date;
    }

    public void setSession_end_date(String session_end_date) {
        this.session_end_date = session_end_date;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRule_no() {
        return rule_no;
    }

    public void setRule_no(String rule_no) {
        this.rule_no = rule_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeightage() {
        return weightage;
    }

    public void setWeightage(float weightage) {
        this.weightage = weightage;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public float getCut_off_number() {
        return cut_off_number;
    }

    public void setCut_off_number(float cut_off_number) {
        this.cut_off_number = cut_off_number;
    }

    public String getNo_of_times_active() {
        return no_of_times_active;
    }

    public void setNo_of_times_active(String no_of_times_active) {
        this.no_of_times_active = no_of_times_active;
    }

    public String getCut_off_percentage_active() {
        return cut_off_percentage_active;
    }

    public void setCut_off_percentage_active(String cut_off_percentage_active) {
        this.cut_off_percentage_active = cut_off_percentage_active;
    }

    public String getCut_off_number_active() {
        return cut_off_number_active;
    }

    public void setCut_off_number_active(String cut_off_number_active) {
        this.cut_off_number_active = cut_off_number_active;
    }

    public float getCut_off_percentage() {
        return cut_off_percentage;
    }

    public void setCut_off_percentage(float cut_off_percentage) {
        this.cut_off_percentage = cut_off_percentage;
    }

    public float getPercentage_seats() {
        return percentage_seats;
    }

    public void setPercentage_seats(float percentage_seats) {
        this.percentage_seats = percentage_seats;
    }

    public float getComponent_criteria() {
        return component_criteria;
    }

    public void setComponent_criteria(float component_criteria) {
        this.component_criteria = component_criteria;
    }

    public String getBoard_flag() {
        return board_flag;
    }

    public void setBoard_flag(String board_flag) {
        this.board_flag = board_flag;
    }

    public String getEligibility_flag() {
        return eligibility_flag;
    }

    public void setEligibility_flag(String eligibility_flag) {
        this.eligibility_flag = eligibility_flag;
    }

    public String getSpecial_flag() {
        return special_flag;
    }

    public void setSpecial_flag(String special_flag) {
        this.special_flag = special_flag;
    }

    public String getWeightage_flag() {
        return weightage_flag;
    }

    public void setWeightage_flag(String weightage_flag) {
        this.weightage_flag = weightage_flag;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(String modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSpecialization_id() {
        return specialization_id;
    }

    public void setSpecialization_id(String specialization_id) {
        this.specialization_id = specialization_id;
    }

    public String getSpecialization_name() {
        return specialization_name;
    }

    public void setSpecialization_name(String specialization_name) {
        this.specialization_name = specialization_name;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

	public String getCatTypeDescription() {
		return catTypeDescription;
	}

	public void setCatTypeDescription(String catTypeDescription) {
		this.catTypeDescription = catTypeDescription;
	}

	public String getCatTypeId() {
		return catTypeId;
	}

	public void setCatTypeId(String catTypeId) {
		this.catTypeId = catTypeId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
