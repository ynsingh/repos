class Proposal 
   {
	Notification notification;
	String code;
	Party party;
	String proposalDocumentationPath;
	Date proposalSubmitteddate;
	String description;
	String lockedYN;
	Integer proposalVersion;
	Person person;
	String proposalStatus;
	Integer proposalLevel;
	Proposal parent;
	String proposalType;
	static constraints=
	   {
		code(nullable:false,blank:false)
		notification(nullable:true)
		party(nullable:true)
		proposalDocumentationPath(nullable:true)
		description(nullable:true)
		lockedYN(nullable:true)
		description(nullable:true)
		proposalVersion(nullable:true)
		person(nullable:true)
		proposalStatus(nullable:true)
		proposalLevel(nullable:true)
		parent(nullable:true)
		proposalType(nullable:false,blank:false)
		}
	 String saveMode;
	 
	
	 static transients = [ "saveMode" ]

	}
