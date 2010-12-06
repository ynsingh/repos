class PartyDepartment {

	Party party;
	String departmentCode;
	String name;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		
		departmentCode(nullable:false,blank:false)
		name(nullable:false,blank:false)
		createdBy(nullable: true)
		createdDate(nullable: true)
		modifiedBy(nullable: true)
		modifiedDate(nullable: true)
		
    }
	String saveMode;
	static transients = [ "saveMode" ]
}
