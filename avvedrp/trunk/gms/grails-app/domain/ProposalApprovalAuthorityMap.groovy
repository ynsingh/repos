

class ProposalApprovalAuthorityMap {
	
	String proposalType
	Integer proposalId
	ApprovalAuthority approvalAuthority
	Integer approveOrder
	Integer processRestartOrder
	String remarks
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	

    static constraints = 
    {
		proposalType(nullable:false)
		proposalId(nullable:false)
		approveOrder(nullable:false)
		processRestartOrder(nullable:false)
		remarks(nullable:true)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
    }
}
