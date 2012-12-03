class ChecklistMap
{
	Projects projects;
	Checklist checklist;
	char compulsory;
	String remarks;
	char activeYesNo;
	String satisfied;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

	static constraints = {
		
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		createdBy(nullable: true)
		modifiedBy(nullable: true)
		remarks(nullable: true)
    }
}

