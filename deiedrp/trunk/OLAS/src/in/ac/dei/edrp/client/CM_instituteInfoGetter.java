package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_instituteInfoGetter implements IsSerializable {
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String newSession;
    private String Status = "1";
    private String designation;
    private String departmentCode;
    private String designationID;

    public CM_instituteInfoGetter() {
    }

    public String getNewSession() {
        return newSession;
    }

    public void setNewSession(String newSession) {
        this.newSession = newSession;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

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

    public String getpincode() {
        return pincode;
    }

    public void setpincode(String pincode) {
        this.pincode = pincode;
    }

	public String getDesignationID() {
		return designationID;
	}

	public void setDesignationID(String designationID) {
		this.designationID = designationID;
	}
}
