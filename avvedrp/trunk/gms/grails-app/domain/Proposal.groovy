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
		}
		
	}
