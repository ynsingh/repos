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
	private String addorg="Not Allow";
	private String editorg="Not Allow";
	private String addproject="Not Allow";
	private String editproject="Not Allow";
	private String addmember="Not Allow";
	private String editmember="Not Allow";
	private String assignproject="Not Allow";
	private String editauthority="Not Allow";
	private String assigntask="Not Allow";
	private String edittask="Not Allow";
	private String uploaddoc="Not Allow";
	private String downloaddoc="Not Allow";
	private String addrole="Not Allow";
	private String editrole="Not Allow";
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
