class PartyProposalForm {

	Party party;
	Notification notification;
	String name;
	String value;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	String formType;
	
	static constraints = {
		
		
		name(nullable:false,blank:false)
		value(nullable:false,blank:false)
		createdBy(nullable: true)
		createdDate(nullable: true)
		modifiedBy(nullable: true)
		modifiedDate(nullable: true)
		notification(nullable:true)
	    formType(nullable:false,blank:false)
		
    }
	
}
