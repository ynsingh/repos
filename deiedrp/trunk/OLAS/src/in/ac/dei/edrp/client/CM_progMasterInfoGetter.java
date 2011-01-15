package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_progMasterInfoGetter implements IsSerializable {
    String program_id;
    String program_code;
    String program_name;
    String program_type;
    String Program_mode;
    boolean branch;
    boolean specilization;
    String no_of_terms;
    String total_credits;
    String number_of_attempt_allowed;
    String max_number_of_fail_subjects;
    String fixed_duration;
    String UGorPG;
    String insert_time;
    String modification_time;
    String creator_id;
    String tencodes;
    String minimun_duration;
    String maximum_duration;
    String[] start_day_Month;
    String startdate;
    String oldstartdate;
    String[] branch_code;
    String[] Branch_Name;
    String branchname;
    String[] branch_specialization_code;
    String[][] branchSpec;
    String specialization_code;
    String[] specialization_name;
    String specname;
    String branchcode;
    String[] category1;
    String category;
    String category_code;
    String[] PercentageSeats1;
    String percentage_seats;
    boolean reservation;
    String university_id;
    String system_code;
    String system_value;
    String modifier_id;

    public String getProgram_type() {
        return program_type;
    }

    public void setProgram_type(String program_type) {
        this.program_type = program_type;
    }

    public String getNumber_of_attempt_allowed() {
        return number_of_attempt_allowed;
    }

    public void setNumber_of_attempt_allowed(String number_of_attempt_allowed) {
        this.number_of_attempt_allowed = number_of_attempt_allowed;
    }

    public String getTencodes() {
        return tencodes;
    }

    public void setTencodes(String tencodes) {
        this.tencodes = tencodes;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getSystem_code() {
        return system_code;
    }

    public void setSystem_code(String system_code) {
        this.system_code = system_code;
    }

    public String getSystem_value() {
        return system_value;
    }

    public void setSystem_value(String system_value) {
        this.system_value = system_value;
    }

    public String[] getBranch_specialization_code() {
        return branch_specialization_code;
    }

    public String getMinimun_duration() {
		return minimun_duration;
	}

	public void setMinimun_duration(String minimun_duration) {
		this.minimun_duration = minimun_duration;
	}

	public void setBranch_specialization_code(
        String[] branch_specialization_code) {
        this.branch_specialization_code = branch_specialization_code;
    }

//    public String getMinimum_duration() {
//        return minimun_duration;
//    }
//
//    public void setMinimum_duration(String minimum_duration) {
//        this.minimun_duration = minimum_duration;
//    }

    public String getMaximum_duration() {
        return maximum_duration;
    }

    public void setMaximum_duration(String maximum_duration) {
        this.maximum_duration = maximum_duration;
    }

    public String[] getStart_day_Month() {
        return start_day_Month;
    }

    public void setStart_day_Month(String[] start_day_Month) {
        this.start_day_Month = start_day_Month;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getProgram_code() {
        return program_code;
    }

    public void setProgram_code(String program_code) {
        this.program_code = program_code;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    //	public String getProgram_Type() {
    //		return program_type;
    //	}
    //	public void setProgram_Type(String program_Type) {
    //		this.program_type = program_Type;
    //	}
    public String getProgram_mode() {
        return Program_mode;
    }

    public void setProgram_mode(String program_mode) {
        Program_mode = program_mode;
    }

    public String getNo_of_terms() {
        return no_of_terms;
    }

    public void setNo_of_terms(String no_of_terms) {
        this.no_of_terms = no_of_terms;
    }

    public String getTotal_credits() {
        return total_credits;
    }

    public void setTotal_credits(String total_credits) {
        this.total_credits = total_credits;
    }

    //	public String getNumber_of_Attempt_allowed() {
    //		return Number_of_Attempt_allowed;
    //	}
    //	public void setNumber_of_Attempt_allowed(String number_of_Attempt_allowed) {
    //		Number_of_Attempt_allowed = number_of_Attempt_allowed;
    //	}
    //	public String getMax_Number_of_Fail_Subjects() {
    //		return Max_Number_of_Fail_Subjects;
    //	}
    //	public void setMax_Number_of_Fail_Subjects(String max_Number_of_Fail_Subjects) {
    //		Max_Number_of_Fail_Subjects = max_Number_of_Fail_Subjects;
    //	}
    //	public String getFixed_Duration() {
    //		return Fixed_Duration;
    //	}
    public String getMax_number_of_fail_subjects() {
        return max_number_of_fail_subjects;
    }

    public void setMax_number_of_fail_subjects(
        String max_number_of_fail_subjects) {
        this.max_number_of_fail_subjects = max_number_of_fail_subjects;
    }

    //	public void setFixed_Duration(String fixed_Duration) {
    //		Fixed_Duration = fixed_Duration;
    //	}
    public String getUGorPG() {
        return UGorPG;
    }

    public String getFixed_duration() {
		return fixed_duration;
	}

	public void setFixed_duration(String fixed_duration) {
		this.fixed_duration = fixed_duration;
	}

	public boolean isBranch() {
        return branch;
    }

    public void setBranch(boolean branch) {
        this.branch = branch;
    }

    public boolean isSpecilization() {
        return specilization;
    }

    public void setSpecilization(boolean specilization) {
        this.specilization = specilization;
    }

    public String[] getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String[] branch_code) {
        this.branch_code = branch_code;
    }

    public String getSpecialization_code() {
        return specialization_code;
    }

    public void setSpecialization_code(String specialization_code) {
        this.specialization_code = specialization_code;
    }

    public void setUGorPG(String gorPG) {
        UGorPG = gorPG;
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

    public String[] getBranch_Name() {
        return Branch_Name;
    }

    public void setBranch_Name(String[] branch_Name) {
        Branch_Name = branch_Name;
    }

    public String[] getSpecialization_name() {
        return specialization_name;
    }

    public void setSpecialization_name(String[] specialization_name) {
        this.specialization_name = specialization_name;
    }

    public String[] getCategory1() {
        return category1;
    }

    public void setCategory(String[] category1) {
        this.category1 = category1;
    }

    public String[] getPercentageSeats1() {
        return PercentageSeats1;
    }

    public void setPercentageSeats(String[] percentageSeats1) {
        PercentageSeats1 = percentageSeats1;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPercentage_seats() {
        return percentage_seats;
    }

    public void setPercentage_seats(String percentage_seats) {
        this.percentage_seats = percentage_seats;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String[][] getBranchSpec() {
        return branchSpec;
    }

    public void setBranchSpec(String[][] branchSpec) {
        this.branchSpec = branchSpec;
    }

    public String getOldstartdate() {
        return oldstartdate;
    }

    public void setOldstartdate(String oldstartdate) {
        this.oldstartdate = oldstartdate;
    }
}
