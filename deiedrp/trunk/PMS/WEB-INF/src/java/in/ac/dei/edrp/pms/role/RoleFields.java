package in.ac.dei.edrp.pms.role;

public class RoleFields {

	private String roleid;
	private String rolename;
	private String roledescription;
	private String createdby;
	private String createdon;
	private String updatedon;
	private String authority;//to check whether the login user is Super Admin /User
	private String addorg;
	private String editorg;
	private String addproject;
	private String editproject;
	private String addmember;
	private String editmember;
	private String assignproject;
	private String editauthority;
	private String assigntask;
	private String edittask;
	private String uploaddoc;
	private String downloaddoc;
	private String addrole;
	private String editrole;
	
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
	
	public RoleFields(String roleid,String rolename, String roledescription,String addorg, String editorg,
			String addproject, String editproject,String addmember, String editmember,
			String assignproject, String editauthority,String assigntask, String edittask,
			String uploaddoc, String downloaddoc,String addrole, String editrole) {
		this.roleid=roleid;
		this.rolename=rolename;
		this.roledescription=roledescription;
		this.addorg = addorg;
		this.editorg = editorg;
		this.addproject = addproject;
		this.editproject = editproject;
		this.addmember = addmember;
		this.editmember = editmember;
		this.assignproject = assignproject;
		this.editauthority = editauthority;
		this.assigntask = assigntask;
		this.edittask = edittask;
		this.uploaddoc = uploaddoc;
		this.downloaddoc = downloaddoc;
		this.addrole = addrole;
		this.editrole = editrole;
				
	}
	public RoleFields(String s[])
	{
		this.addorg = s[0];
		this.editorg = s[1];
		this.addproject = s[2];
		this.editproject = s[3];
		this.addmember = s[4];
		this.editmember = s[5];
		this.assignproject = s[6];
		this.editauthority = s[7];
		this.assigntask = s[8];
		this.edittask = s[9];
		this.uploaddoc = s[10];
		this.downloaddoc = s[11];
		this.addrole = s[12];
		this.editrole = s[13];
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
	/**The method getAddorg return the add organisation permission */
	public String getAddorg() {
		return addorg;
	}
/**The method setAddorg is used for setting the add organisation permission */
	public void setAddorg(String addorg) {
		this.addorg = addorg;
	}
	
	/**The method getEditorg return the edit organisation permission */
	public String getEditorg() {
		return editorg;
	}
/**The method setEditorg is used for setting the edit organisation permission */
	public void setEditorg(String editorg) {
		this.editorg = editorg;
	}
	
	/**The method getAddproject return the add project permission */
	public String getAddproject() {
		return addproject;
	}
/**The method setAddproject is used for setting the add project permission */
	public void setAddproject(String addproject) {
		this.addproject = addproject;
	}
	
	/**The method getEditproject return the edit project permission */
	public String getEditproject() {
		return editproject;
	}
/**The method setEditproject is used for setting the edit project permission */
	public void setEditproject(String editproject) {
		this.editproject = editproject;
	}
	
	/**The method getAddmember return the add member permission */
	public String getAddmember() {
		return addmember;
	}
/**The method setAddmember is used for setting the add member permission */
	public void setAddmember(String addmember) {
		this.addmember = addmember;
	}
	
	/**The method getEditmember return the edit member permission */
	public String getEditmember() {
		return editmember;
	}
/**The method setEditmember is used for setting the edit member permission */
	public void setEditmember(String editmember) {
		this.editmember = editmember;
	}
	
	/**The method getAssignproject return the assign project permission */
	public String getAssignproject() {
		return assignproject;
	}
/**The method setAssignproject is used for setting the assign project permission */
	public void setAssignproject(String assignproject) {
		this.assignproject = assignproject;
	}
	
	/**The method getEditauthority return the edit authority permission  */
	public String getEditauthority() {
		return editauthority;
	}
/**The method setEditauthority is used for setting the edit authority permission */
	public void setEditauthority(String editauthority) {
		this.editauthority = editauthority;
	}
	
	/**The method getAssigntask return the assign task permission */
	public String getAssigntask() {
		return assigntask;
	}
/**The method setAssigntask is used for setting the assign task permission */
	public void setAssigntask(String assigntask) {
		this.assigntask = assigntask;
	}
	/**The method getEdittask return the edit task permission  */
	public String getEdittask() {
		return edittask;
	}
/**The method setEdittask is used for setting the edit task permission */
	public void setEdittask(String edittask) {
		this.edittask = edittask;
	}
	
	/**The method getUploaddoc return the upload documents permission */
	public String getUploaddoc() {
		return uploaddoc;
	}
/**The method setUploaddoc is used for setting the upload documents permission */
	public void setUploaddoc(String uploaddoc) {
		this.uploaddoc = uploaddoc;
	}
	
	/**The method getDownloaddoc return the download documents permission  */
	public String getDownloaddoc() {
		return downloaddoc;
	}
/**The method setDownloaddoc is used for setting the download documents permission */
	public void setDownloaddoc(String downloaddoc) {
		this.downloaddoc = downloaddoc;
	}
	/**The method getAddrole return the add role permission */
	public String getAddrole() {
		return addrole;
	}
/**The method setAddrole is used for setting the add role permission */
	public void setAddrole(String addrole) {
		this.addrole = addrole;
	}
	
	/**The method getEditrole return the edit role permission */
	public String getEditrole() {
		return editrole;
	}
/**The method setEditrole is used for setting the edit role permission */
	public void setEditrole(String editrole) {
		this.editrole = editrole;
	}

}
