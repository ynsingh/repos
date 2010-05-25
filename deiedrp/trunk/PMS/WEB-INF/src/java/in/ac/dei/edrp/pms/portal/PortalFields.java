package in.ac.dei.edrp.pms.portal;

public class PortalFields {

	private String portalid;
	private String portalname;
	private String portaldescription;
	private String createdby;
	private String createdon;
	
	
	public PortalFields(String portalid,String portalname, String portaldescription,String createdby,String createdon) {
		this.portalid=portalid;
		this.portalname=portalname;
		this.portaldescription=portaldescription;
		this.createdby=createdby;
		this.createdon=createdon;
		
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
	
	/**The method getPortaldescription return the portal description */
	public String getPortaldescription() {
		return portaldescription;
	}
/**The method setPortaldescription is used for setting the portal description */
	public void setPortaldescription(String portaldescription) {
		this.portaldescription = portaldescription;
	}
	/**The method getCreatedby return the name of owner of the portal*/
	public String getCreatedby() {
		return createdby;
	}
/**The method setCreatedby is used for setting the name of owner of the portal */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	/**The method getCreatedon return the creation date of the portal*/
	public String getCreatedon() {
		return createdon;
	}
/**The method setCreatedon is used for setting the creation date of the portal */
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	
}
