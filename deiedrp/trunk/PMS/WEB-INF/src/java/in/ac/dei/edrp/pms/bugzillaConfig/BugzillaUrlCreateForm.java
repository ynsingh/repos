package in.ac.dei.edrp.pms.bugzillaConfig;

import org.apache.struts.validator.ValidatorForm;

public class BugzillaUrlCreateForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
