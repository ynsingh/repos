class AccountHeads {
	
	AccountHeads parent;
	String code;
	String name;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		parent(nullable: true)
		code(blank:false)
		name(blank:false)
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		
    }
	
	String saveMode;
	String accHeadPeriod;
	String accHeadCode;
	static transients = [ "saveMode","accHeadPeriod","accHeadCode" ]

}
