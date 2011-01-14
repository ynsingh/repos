

class FullProposal {
	
	PreProposal preProposal
	String universitySubmissionID
	String proposalStatus
	Integer preProposalLevel
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		universitySubmissionID(nullable:true)
		proposalStatus(nullable:true)
		preProposalLevel(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
		
    }
    String projectTitle;
	
	static transients = [ "projectTitle"]
}
