package member;

/**
 * Object Class Customer
 */
public class MemberBean {
	
	//properties
	private String userid;
	private String permittedby;
	private String projectname;
	private String orgname;
	
	
	//constructors
	public MemberBean(){}
	public MemberBean(String userid, String permittedby, String projectname, String orgname){
		this.userid = userid;
		this.permittedby = permittedby;
		this.projectname = projectname;
		this.orgname = orgname;
		
	}

	//getter and setter methods
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPermittedby() {
		return permittedby;
	}
	public void setPermittedby(String permittedby) {
		this.permittedby = permittedby;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
}
