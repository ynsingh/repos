class Utilization {
	Party grantee;
	Projects projects;
	Date uploadedDate;
	String attachmentPath;
	String archiveYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	static constraints ={
		attachmentPath(nullable:true)
		createdBy(nullable:true)
	    createdDate(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		archiveYesNo(nullable:true)
		}

}
