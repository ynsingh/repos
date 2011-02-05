

class ApprovalAuthority {
	
	Party party
	String name
	String email
	String approveLevel
	char approveMandatory
	char approveAll
	char viewAll
	char defaultYesNo
	String remarks
	char activeYesNo
	Integer totalMembers
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		name(nullable:false,blank:false)
		email(nullable:true)
		approveLevel(nullable:true)
		approveMandatory(nullable:false)
		approveAll(nullable:false)
		viewAll(nullable:false)
		remarks(nullable:true)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
    }
     String saveMode;
	
	static transients = [ "saveMode","totalMembers" ]

}
