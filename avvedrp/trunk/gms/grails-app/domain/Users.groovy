class Users {
	
	String userName;
	String userPassword;
	String name;
	String email;
	String phone;
	String role;
	char activeYesNo;
	Integer version;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	static constraints={
		createdDate(nullable:true)
		createdBy(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		}

}
