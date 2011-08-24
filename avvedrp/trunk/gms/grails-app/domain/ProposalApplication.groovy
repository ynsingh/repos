class ProposalApplication {
	Proposal proposal;
	Date applicationSubmitDate;
	String createdBy;
    Date createdDate;
    String modifiedBy;
    String controllerId;
    Date modifiedDate;
    String name;
    String designation;
    String organisation;
    String postalAddress;
    String city;
    String state;
    String phone;
    String fax;
    String email;
    String mobile;
    ProposalCategory proposalCategory;
    String projectTitle;
    
    String zipCode;
static constraints={
	
					controllerId(nullable:true)
					createdBy(nullable:true)
					createdDate(nullable:true)
					modifiedBy(nullable:true)
					modifiedDate(nullable:true)
					name(nullable:true)
					designation(nullable:true)
					organisation(nullable:true)
					postalAddress(nullable:true)
					city(nullable:true)
					state(nullable:true)
					phone(nullable:true)
					fax(nullable:true)
					email(nullable:true)
					mobile(nullable:true)
					proposalCategory(nullable:true)
					projectTitle(nullable:true)
					
					zipCode(nullable:true)
 }
	boolean saveAll
	char award
	static transients=["saveAll","award"]

}
