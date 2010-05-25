package in.ac.dei.edrp.pms.addorg_in_portal;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 19-03-2010
 * 
 * XDoclet definition:
 * @struts.form name="addorginportalform"
 */
public class AddOrgInPortalForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Generated fields
	 */

	/** emailid property */
	private String emailid;

	/** portalname property */
	private String portalname;

	/** role property */
	private String role;

	/** organisation property */
	private String organisation;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	/*public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the emailid.
	 * @return String
	 */
	public String getEmailid() {
		return emailid;
	}

	/** 
	 * Set the emailid.
	 * @param emailid The emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	/** 
	 * Returns the portalname.
	 * @return String
	 */
	public String getPortalname() {
		return portalname;
	}

	/** 
	 * Set the portalname.
	 * @param portalname The portalname to set
	 */
	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}

	/** 
	 * Returns the role.
	 * @return String
	 */
	public String getRole() {
		return role;
	}

	/** 
	 * Set the role.
	 * @param role The role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/** 
	 * Returns the organisation.
	 * @return String
	 */
	public String getOrganisation() {
		return organisation;
	}

	/** 
	 * Set the organisation.
	 * @param organisation The organisation to set
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}
