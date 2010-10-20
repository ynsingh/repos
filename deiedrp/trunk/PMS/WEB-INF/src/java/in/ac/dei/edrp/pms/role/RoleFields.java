package in.ac.dei.edrp.pms.role;

public class RoleFields {

	private String roleid;
	private String rolename;
	private String roledescription;
	private String createdby;
	private String createdon;
	private String updatedon;
	private String authority;//to check whether the login user is Super Admin /User
	
	private String editauthority;
	
	private String roleauthority;
	
	public RoleFields(String roleid,String rolename, String roledescription,
			String createdby,String createdon,String updatedon,String authority,String editauthority) {
		this.roleid=roleid;
		this.rolename=rolename;
		this.roledescription=roledescription;
		this.createdby=createdby;
		this.createdon=createdon;
		this.updatedon=updatedon;
		this.authority=authority;
		this.editauthority=editauthority;//to hold authorities Allow/Not Allow
	}
	
	public RoleFields(String roleid,String rolename, String roledescription,String roleauthority) {
		this.roleid=roleid;
		this.rolename=rolename;
		this.roledescription=roledescription;
		this.roleauthority = roleauthority;
	}
	public RoleFields(String roleauthority)
	{
		this.roleauthority = roleauthority;
		
	}
	
	public String getRoleauthority() {
		return roleauthority;
	}

	public void setRoleauthority(String roleauthority) {
		this.roleauthority = roleauthority;
	}

	/**The method getauthority return the authority of login person */
	public String getauthority() {
		return authority;
	}
/**The method setauthority is used for setting authority of login person */
	public void setauthority(String authority) {
		this.authority = authority;
	}
	/**The method getRoleid return the role id */
	public String getRoleid() {
		return roleid;
	}
/**The method setRoleid is used for setting the role id */
	public void setRoleid(String roleid) {
		this.roleid = roleid;
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
	/**The method getCreatedby return the name of owner of the role*/
	public String getCreatedby() {
		return createdby;
	}
/**The method setCreatedby is used for setting the name of owner of the role */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	/**The method getCreatedon return the creation date of the role*/
	public String getCreatedon() {
		return createdon;
	}
/**The method setCreatedon is used for setting the creation date of the role */
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	/**The method getUpdatedon return the updation date of the role*/
	public String getUpdatedon() {
		return updatedon;
	}
/**The method setUpdatedon is used for setting the updation date of the role */
	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}
	
	
	/**The method getEditauthority return the edit authority permission  */
	public String getEditauthority() {
		return editauthority;
	}
/**The method setEditauthority is used for setting the edit authority permission */
	public void setEditauthority(String editauthority) {
		this.editauthority = editauthority;
	}

}
