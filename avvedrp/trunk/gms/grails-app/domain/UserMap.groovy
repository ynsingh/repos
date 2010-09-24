class UserMap {
	Person user
	Party party;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
	String saveMode;
	static transients = [ "saveMode" ]
	

}
