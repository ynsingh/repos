class ProposalApplicationExt {
	ProposalApplication proposalApplication;
	String field;
	String value;
	String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;
static constraints={
	
					createdBy(nullable:true)
					createdDate(nullable:true)
					modifiedBy(nullable:true)
					modifiedDate(nullable:true)
    }

}
