
class Award {
	Proposal proposal;
	Projects projects;
	Date awardedDate;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
    static constraints = {
    	createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable:true)
    }
}
