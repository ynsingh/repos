package in.ac.dei.edrp.pms.bugzillaConfig;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/** 
 * MyEclipse Struts
 * Creation date: 30-Nov-2010
 * 
 * XDoclet definition:
 * @struts.form name="projectConfigFormIntoBugzilla"
 */
public class ProjectConfigFormIntoBugzilla extends ValidatorForm {
	/*
	 * Generated fields
	 */
	private static final long serialVersionUID = 1L;

	private String projectName;
	private String projectVersion;
	
	/*
	 * Generated Methods
	 */

	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	
}