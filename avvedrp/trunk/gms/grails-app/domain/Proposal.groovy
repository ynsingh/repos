class Proposal 
   {
	Notification notification;
	String code;
	Party party;
	String proposalDocumentationPath;
	Date proposalSubmitteddate;
	String description;
	String lockedYN;
	static constraints=
	   {
		code(nullable:false,blank:false)
		proposalDocumentationPath(nullable:true)
		lockedYN(nullable:true)
		description(nullable:true)
		}
		
	}
