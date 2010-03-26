class Projects {
	
	Projects parent;
	ProjectType projectType;
	String code;
	String name;
	String principalInvestigatorName;
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
		
	}
	
	String saveMode;
	double totAllAmount; 
	static transients = [ "saveMode","totAllAmount" ]


}
