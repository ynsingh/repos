class ProjectDepartmentMap {

	Projects projects;
	PartyDepartment partyDepartment;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	char activeYesNo;
	static constraints = {
		
		createdBy(nullable: true)
		createdDate(nullable: true)
		modifiedBy(nullable: true)
		modifiedDate(nullable: true)
		
    }
	String saveMode;
	static transients = [ "saveMode" ]
}
