package in.ac.dei.edrp.pms.role;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 24-02-2010
 * 
 * XDoclet definition:
 * @struts.form name="editroleform"
 */
public class RoleForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Generated fields
	 */
	private String rolename2=null;
	private String oldrolename=null;
	private String roleid=null;
	private String rolename=null;
	private String roledescription=null;
	private String[] authorities;
	
	/*
	 * Generated Methods
	 */
	
/** 
* Method reset is used for resetting all the fields with default values.
* @param mapping
* @param request
*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
	public String getRolename2() {
	return rolename2;
}

public void setRolename2(String rolename2) {
	this.rolename2 = rolename2;
}

	/**The method getRoleid return the role id */
	public String getRoleid() {
		return roleid;
	}
/**The method setRoleid is used for setting the role id */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	/**The method getOldrolename return the old role name */
	public String getOldrolename() {
		return oldrolename;
	}
/**The method setOldrolename is used for setting the old role name */
	public void setOldrolename(String oldrolename) {
		this.oldrolename = oldrolename;
	}

	/**The method getRolename return the role name */
	public String getRolename() {
		return rolename;
	}
/**The method setRolename is used for setting the role name */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	/**The method getRoledescription return the role description */
	public String getRoledescription() {
		return roledescription;
	}
/**The method setRoledescription is used for setting the role description */
	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
	}

public String[] getAuthorities() {
	return authorities;
}

public void setAuthorities(String[] authorities) {
	this.authorities = authorities;
}

	
	
	

}
