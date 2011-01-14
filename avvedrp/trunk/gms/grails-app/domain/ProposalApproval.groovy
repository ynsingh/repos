

class ProposalApproval {
	
	ProposalApprovalAuthorityMap proposalApprovalAuthorityMap
	ApprovalAuthorityDetail approvalAuthorityDetail
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	char viewAll
    static constraints = {
    	createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
		
    }
	static transients = [ "viewAll" ]
}
