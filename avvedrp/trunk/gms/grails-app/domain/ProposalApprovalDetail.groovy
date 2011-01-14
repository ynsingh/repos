

class ProposalApprovalDetail {
	
	ProposalApproval proposalApproval
	double proposalDetailId
	String proposalStatus
	Date approvalDate
	String moreComments
	String remarks
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		proposalDetailId(nullable:true)
		proposalStatus(nullable:false)
		approvalDate(nullable:false)
		moreComments(nullable:true)
		remarks(nullable:true)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable:true)
    }
}
