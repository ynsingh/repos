package in.ac.dei.edrp.pms.bugzillaConfig;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/** 
 * MyEclipse Struts
 * Creation date: 30-Nov-2010
 * 
 * XDoclet definition:
 * @struts.form name="componentConfigFormIntoBugzilla"
 */
public class ComponentConfigFormIntoBugzilla extends ValidatorForm {
	/*
	 * Generated fields
	 */
	private static final long serialVersionUID = 1L;

	private String projectName;
	private String component;
	private String componentDescription;
	private String assignedTo;
	
	/*
	 * Generated Methods
	 */

	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
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