class Proposal 
   {
	Notification notification;
	String code;
	Party party;
	Party grantor;
	String proposalDocumentationPath;
	Date proposalSubmitteddate;
	String description;
	String lockedYN;
	static constraints=
	   {
		code(nullable:false,blank:false)
		notification(nullable:true)
		party(nullable:true)
		grantor(nullable:true)
		proposalDocumentationPath(nullable:true)
		description(nullable:true)
		lockedYN(nullable:true)
		description(nullable:true)
		}
		
	}
