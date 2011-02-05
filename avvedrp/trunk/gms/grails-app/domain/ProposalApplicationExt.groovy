class ProposalApplicationExt {
	ProposalApplication proposalApplication;
	String label;
	Integer orderNo;
	Integer page;
	String field;
	String value;
	char activeYesNo;
	String createdBy;
    Date createdDate;
    String modifiedBy;
    Date modifiedDate;
static constraints={
					orderNo(nullable:true)
					page(nullable:true)
					label(nullable:true)
					createdBy(nullable:true)
					createdDate(nullable:true)
					modifiedBy(nullable:true)
					modifiedDate(nullable:true)
    }

}
