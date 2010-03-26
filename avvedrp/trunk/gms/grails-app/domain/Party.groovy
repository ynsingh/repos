class Party {
	
	String code;
	String nameOfTheInstitution;
	String address;
	String phone;
	String email;
	String partyType;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	
	static constraints = {
		
		code(nullable:false,blank:false)
		nameOfTheInstitution(nullable:false,blank:false)
		email(email:true,blank:true)
		partyType(nullable: true)
		createdDate(nullable: true)
		modifiedBy(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
	String saveMode;
	Boolean statusEmail
	static transients = [ "saveMode","statusEmail" ]
	
	//static transients = [ "statusEmail" ]
	

}
