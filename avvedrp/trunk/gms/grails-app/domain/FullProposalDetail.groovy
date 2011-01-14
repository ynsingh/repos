
class FullProposalDetail {
	
	FullProposal fullProposal
	String fileName
	String comments
	Date proposalSubmittedDate
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		fileName(nullable:false)
		comments(nullable:true)
		proposalSubmittedDate(nullable:false)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
		
}
}
