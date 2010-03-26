class GrantPeriod {
	
	
	String name;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		name(blank:false, unique:true)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
	String saveMode;
	static transients = [ "saveMode" ]

}
