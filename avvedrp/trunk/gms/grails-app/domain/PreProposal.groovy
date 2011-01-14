import java.lang.Integer

class PreProposal {
	
        Party party
        
		ProposalCategory proposalCategory
		String projectTitle
		Person person
		String remarks
		Date preProposalSavedDate
		Date preProposalCancelDate
		String preProposalStatus
		Integer preProposalLevel
		String preProposalForm
		char activeYesNo		String createdBy;		Date createdDate;		String modifiedBy;		Date modifiedDate;
		
		
		static constraints = 
		{
        	projectTitle(nullable:true)
        	remarks(nullable:true)
        	preProposalSavedDate(nullable:true)
        	preProposalCancelDate(nullable:true)
        	preProposalStatus(nullable:true)
        	preProposalLevel(nullable:false)
    		preProposalForm(nullable:false)
    		activeYesNo(nullable:false)    		createdDate(nullable: true)    		modifiedDate(nullable: true)    		createdBy(nullable: true)    		modifiedBy(nullable: true)    		
	    }
        String saveMode;		String proposalStatus;
    	
    	static transients = [ "saveMode","proposalStatus" ]

	}


   