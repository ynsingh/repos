package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SystemTableTwo implements IsSerializable
{
	private String university_id;
	private String component_id;
	private String component_description;
	private int active;
	
	
	public SystemTableTwo() {
		
	}


	public SystemTableTwo(String university_id, String component_id, String component_description,
			int active) {
		this.university_id=university_id;
		this.component_id = component_id;
		this.component_description = component_description;
		this.active = active;
	}


	public String getComponent_id() {
		return component_id;
	}


	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}


	public String getComponent_description() {
		return component_description;
	}


	public void setComponent_description(String component_description) {
		this.component_description = component_description;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public String getUniversity_id() {
		return university_id;
	}


	public void setUniversity_id(String university_id) {
		this.university_id = university_id;
	}
	
	

}
