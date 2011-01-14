
class PreProposalDetail {
	
	PreProposal preProposal
	String field
	String value
	Date preProposalSubmittedDate
	char activeYesNo
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = 
    {
		field(nullable:true)
		value(nullable:true)
		preProposalSubmittedDate(nullable:true)
		activeYesNo(nullable:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
    }
   String saveMode;
	
	static transients = [ "saveMode" ]
}
