

class ProposalCategory {
	
	String name
	String remarks
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		name(nullable:false,blank:false)
		remarks(nullable:true)
		activeYesNo(nullable:false)
        createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
    }
 String saveMode;
	
	static transients = [ "saveMode" ]

}
