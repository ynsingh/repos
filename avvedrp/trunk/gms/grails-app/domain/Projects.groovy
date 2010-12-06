class Projects {
	
	Projects parent;
	ProjectType projectType;
	String code;
	String name;
	String projectDuration;
	Date projectStartDate
	Date projectEndDate
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints={
		parent(nullable:true)
		projectDuration(nullable:true)
		projectStartDate(nullable:true)
		projectEndDate(nullable:true)
		name(blank:false)
		code(blank:false)
		investigator(nullable:true)
		
	}
	
	String saveMode;
	double totAllAmount; 
	Investigator investigator;
	String status;
	static transients = [ "saveMode","totAllAmount","investigator","status" ]


}
