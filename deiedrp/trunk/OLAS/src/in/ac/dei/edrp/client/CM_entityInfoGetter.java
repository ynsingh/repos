package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_entityInfoGetter implements IsSerializable {
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
    private String offeredby;
    private String branch_name;
    private String program_id;

    /*
     * EntityInfoGetter
     */
    private String address_line1;
    private String address_line2;
    private String city;
    private String state;
    private int pincode;
    private String value;
    private String code;
    private String enrollment_number;
    private String startdate;
    private String enddate;

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

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getParent_entity_name() {
        return parent_entity_name;
    }

    public void setParent_entity_name(String parent_entity_name) {
        this.parent_entity_name = parent_entity_name;
    }

    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getEntity_address() {
        return entity_address;
    }

    public void setEntity_address(String entity_address) {
        this.entity_address = entity_address;
    }

    public String getEntity_city() {
        return entity_city;
    }

    public void setEntity_city(String entity_city) {
        this.entity_city = entity_city;
    }

    public String getEntity_state() {
        return entity_state;
    }

    public void setEntity_state(String entity_state) {
        this.entity_state = entity_state;
    }

    public String getEntity_phone() {
        return entity_phone;
    }

    public void setEntity_phone(String entity_phone) {
        this.entity_phone = entity_phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getParent_entity_id() {
        return parent_entity_id;
    }

    public void setParent_entity_id(String parent_entity_id) {
        this.parent_entity_id = parent_entity_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEntity_description() {
        return entity_description;
    }

    public void setEntity_description(String entity_description) {
        this.entity_description = entity_description;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getOfferedby() {
        return offeredby;
    }

    public void setOfferedby(String offeredby) {
        this.offeredby = offeredby;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    /*
     * EntityInfoGetter
     */

    //	public String getaddress1() {
    //		return addressline1;
    //	}
    //
    //	public void setaddress1(String addressline1) {
    //		this.addressline1 = addressline1;
    //	}

    //	public String getaddress2() {
    //		return addressline2;
    //	}
    //
    //	public void setaddress2(String addressline2) {
    //		this.addressline2 = addressline2;
    //	}
    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public String getstate() {
        return state;
    }

    public void setstate(String state) {
        this.state = state;
    }

    public int getpincode() {
        return pincode;
    }

    public void setpincode(int pincode) {
        this.pincode = pincode;
    }

    public String getvalue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        this.code = code;
    }

    public String getEnrollment_number() {
        return enrollment_number;
    }

    public void setEnrollment_number(String enrollment_number) {
        this.enrollment_number = enrollment_number;
    }
}
