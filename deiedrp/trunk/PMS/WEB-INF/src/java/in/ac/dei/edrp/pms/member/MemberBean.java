package in.ac.dei.edrp.pms.member;

/**
 * Object Class Customer
 */
public class MemberBean {
	
	//properties
	private String userid;
	private String portalname;
	private String orgname;
	private String rolename;
	private String valid_key;
	private String editPermission;
	
	//constructors
	public MemberBean(){}
	public MemberBean(String userid, String portalname, String orgname,
			String rolename,String valid_key,String editPermission){
		this.userid = userid;
		this.portalname = portalname;
		this.rolename = rolename;
		this.orgname = orgname;
		this.valid_key=valid_key;
		this.editPermission=editPermission;
	}

	//getter and setter methods
	/**The method getEditPermission return the edit authority permission  */
	public String getEditPermission() {
		return editPermission;
	}
/**The method setEditPermission is used for setting the edit authority permission */
	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}
	public String getValid_key() {
		return valid_key;
	}
	public void setValid_key(String valid_key) {
		this.valid_key = valid_key;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPortalname() {
		return portalname;
	}
	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
}
