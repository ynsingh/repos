class GrantPeriod {
	
	
	String name;
	char activeYesNo;
	char defaultYesNo
	Date startDate;
	Date endDate;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		name(blank:false)
		defaultYesNo(nullable: false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
	String saveMode;
	static transients = [ "saveMode" ]

}
