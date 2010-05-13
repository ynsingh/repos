class Utilization {
	Party grantee;
	Projects projects;
	Date submittedDate;
	String archiveYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	static constraints ={
		createdBy(nullable:true)
	    createdDate(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		archiveYesNo(nullable:true)
		}

}
