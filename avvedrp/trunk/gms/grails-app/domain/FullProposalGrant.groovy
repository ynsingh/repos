

class FullProposalGrant {
	
	FullProposal fullProposal
	String grantAgency
	String grantName
	Date appliedDate
	String remarks
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		
		grantAgency(nullable:false)
		grantName(nullable:false)
		appliedDate(nullable:false)
		remarks(nullable:true)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
		
    }
}
