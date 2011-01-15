package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CM_employeeInfoGetter implements IsSerializable{
	private String first_name;
	private String middle_name;
	private String last_name;
	private String entity_employee_id;
	private String location_name;
	private String location_id;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEntity_employee_id() {
		return entity_employee_id;
	}
	public void setEntity_employee_id(String entity_employee_id) {
		this.entity_employee_id = entity_employee_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
}
