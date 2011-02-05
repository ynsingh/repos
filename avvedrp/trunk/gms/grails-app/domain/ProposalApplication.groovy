class ProposalApplication {
	Proposal proposal;
	Date applicationSubmitDate;
	String createdBy;
    Date createdDate;
    String modifiedBy;
    String controllerId;
    Date modifiedDate;
static constraints={
	
					controllerId(nullable:true)
					createdBy(nullable:true)
					createdDate(nullable:true)
					modifiedBy(nullable:true)
					modifiedDate(nullable:true)
 }
	boolean saveAll
	static transients=["saveAll"]

}
