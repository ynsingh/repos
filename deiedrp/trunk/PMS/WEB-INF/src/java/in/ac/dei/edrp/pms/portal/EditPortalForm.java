package in.ac.dei.edrp.pms.portal;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 17-02-2010
 * 
 * XDoclet definition:
 * @struts.form name="editportalform"
 */
public class EditPortalForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Generated fields
	 */
	private String portalid=null;
	private String portalname=null;
	private String oldportalname=null;
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
		// TODO Auto-generated method stub
		
	}
	/**The method getPortalid return the portal id */
	public String getPortalid() {
		return portalid;
	}
/**The method setPortalid is used for setting the portal id */
	public void setPortalid(String portalid) {
		this.portalid = portalid;
	}
	
/**The method getPortalname return the portal name */
	public String getPortalname() {
		return portalname;
	}
/**The method setPortalname is used for setting the portal name */
	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}
	/**The method getOldportalname return the old portal name */
	public String getOldportalname() {
		return oldportalname;
	}
/**The method setOldportalname is used for setting the old portal name */
	public void setOldportalname(String oldportalname) {
		this.oldportalname = oldportalname;
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