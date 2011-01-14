

class PreProposalCoPI {
	
	PreProposal preProposal
	Investigator investigator
	String coPiCommitments
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		coPiCommitments(nullable:true)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
    }
    String saveMode;
	
	static transients = [ "saveMode" ]

}
