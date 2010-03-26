class ProjectTracking {
	
	Projects projects;
	String projectStatus;
	double percOfCompletion;
	Date dateOfTracking;
	String remarks;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		remarks(nullable: true)
    }
	
	String saveMode;
	static transients = [ "saveMode" ]

}
