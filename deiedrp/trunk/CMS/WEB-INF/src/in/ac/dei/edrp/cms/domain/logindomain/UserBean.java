package in.ac.dei.edrp.cms.domain.logindomain;

public class UserBean {

	private String userId;
	private String userName;
	private String city;
	private String qualification;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
	public UserBean() {
		super();
	}
	
	public UserBean(String userId, String userName, String city,
			String qualification) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.city = city;
		this.qualification = qualification;
	}
	
	
	
}
