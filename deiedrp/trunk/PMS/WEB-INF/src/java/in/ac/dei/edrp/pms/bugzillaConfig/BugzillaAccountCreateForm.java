package in.ac.dei.edrp.pms.bugzillaConfig;

import org.apache.struts.validator.ValidatorForm;

public class BugzillaAccountCreateForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
