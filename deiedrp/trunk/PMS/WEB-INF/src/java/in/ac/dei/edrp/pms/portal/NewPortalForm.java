package in.ac.dei.edrp.pms.portal;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 17-02-2010
 * 
 * XDoclet definition:
 * @struts.form name="newportalform"
 */
public class NewPortalForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Generated fields
	 */
	private String portalname=null;
	private String portaldescription;
	/*
	 * Generated Methods
	 */
		
/** 
* Method reset is used for resetting all the fields with default values.
* @param mapping
* @param request
*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.portalname = null;
		this.portaldescription = null;
	}
	
	
/**The method getPortalname return the portal name */
	public String getPortalname() {
		return portalname;
	}
/**The method setPortalname is used for setting the portal name */
	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}
	
	/**The method getPortaldescription return the portal description */
	public String getPortaldescription() {
		return portaldescription;
	}
/**The method setPortaldescription is used for setting the portal description */
	public void setPortaldescription(String portaldescription) {
		this.portaldescription = portaldescription;
	}
}