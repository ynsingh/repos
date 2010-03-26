
class PartyService{
	
	/**
	 * Function to get all active main parties with party type null.
	 */
	public List getPartiesWithoutPartyType(String subQuery){
		 
		def partyInstanceList =Party.findAll( "from Party P where  P.partyType is NULL and P.activeYesNo='Y'"+subQuery )
		return partyInstanceList
	}
	
	
	
	
	/**
	 * Function to get party by id
	 */
	public Party getPartyById(Integer partyId){
		def partyInstance = Party.get( partyId )
		return partyInstance
	}
	
	/**
	 * Function to get sub parties
	 */
	public List getSubParties(Integer mainPartyId){
		def partyInstanceList=Party.findAll("from Party P where P.activeYesNo='Y' and P.parent.id="+mainPartyId)
		return partyInstanceList
	}
	
	/**
	 * Function to delete party.
	 */
	public Integer deleteParty(Integer partyId){
		def partyDeletedId = null
		def partyInstance = getPartyById( partyId )
        if(partyInstance) {
        	if(partyInstance.delete()){
        		partyDeletedId = partyInstance.id
        	}
        	else
        		partyDeletedId = -1
        }
		return partyDeletedId
	}
	
	/**
	 * Function to update party.
	 */
	public Party updateParty(def partyParams){
		def partyInstance = getPartyById( new Integer(partyParams.id ))
        if(partyInstance) {
        	partyInstance.modifiedBy="admin"
            partyInstance.modifiedDate=new Date()
        	
        	/* Check whether Institution with same name already exists.*/
            Integer partyId = checkDuplicateParty(partyParams)
    	    if(partyId.intValue() == 0 || partyId.intValue() == partyInstance.id.intValue()){
    	    	partyInstance.properties = partyParams
	            if(!partyInstance.hasErrors() && partyInstance.save()) {
	            	partyInstance.saveMode = "Updated"
	            }
    	    }
    	    else
    	    	partyInstance.saveMode = "Duplicate"
        }
		return partyInstance
	}
	
	/**
	 * Function to save party.
	 */
	public Party saveParty(def partyInstance){
		/* Check whether Institution with same name already exists.*/
	    if(checkDuplicateParty(partyInstance) == 0){
           	if(partyInstance.save()){
           		partyInstance.saveMode = "Saved"
           	}
	    }
	    else
	    {
	    	partyInstance.saveMode = "Duplicate"
	    	partyInstance.id=checkDuplicateParty(partyInstance)
	    }
	    	
    	return partyInstance
	}
	
	/**
	 * Function to check whether party exists or not.
	 */
	public Integer checkDuplicateParty(def partyInstance){
    	def partyId = 0
    	System.out.println("DuplicateInstitution__ "+partyInstance.code)
    	def chkPartyInstance = Party.find("from Party P where P.code= '"+partyInstance.code+"' and P.activeYesNo='Y' and P.partyType is NULL ")
    	if(chkPartyInstance)
    		partyId = chkPartyInstance.id
    		
    	return partyId
    }
	
	/**
	 * Function to get all active grant agencies.
	 */
	public List getActiveGrantAgency(String subQuery){
		def partyInstanceList =Party.findAll( "from Party P where P.partyType='GA' and P.activeYesNo='Y' "+subQuery ) 
		return partyInstanceList
	}
	
	/**
	 * Function to check whether grant agency exists or not.
	 */
	private Integer checkDuplicateGrantAgency(def partyInstance){
    	def grantAgencyId = 0
    	System.out.println("DuplicateGrantAgency__ "+partyInstance.code)
    	def chkPartyInstance = Party.find("from Party P where P.code= '"+partyInstance.code+"' and P.activeYesNo='Y' and P.partyType='GA'")
    	if(chkPartyInstance)
    		grantAgencyId = chkPartyInstance.id
    		
    	return grantAgencyId
    }
	
	/**
	 * Function to update grant agency.
	 */
	public Party updateGrantAgency(def partyParams){
		def partyInstance = getPartyById( new Integer(partyParams.id ))
        if(partyInstance) {
        	partyInstance.modifiedBy="admin"
            partyInstance.modifiedDate=new Date()
        	
        	/* Check whether Institution with same name already exists.*/
            Integer partyId = checkDuplicateGrantAgency(partyParams)
    	    if(partyId.intValue() == 0 || partyId.intValue() == partyInstance.id.intValue()){
    	    	partyInstance.properties = partyParams
	            if(!partyInstance.hasErrors() && partyInstance.save()) {
	            	partyInstance.saveMode = "Updated"
	            }
    	    }
    	    else
    	    	partyInstance.saveMode = "Duplicate"
        }
		return partyInstance
	}
	
	/**
	 * Function to save grant agency.
	 */
	public Party saveGrantAgency(def partyInstance){
		/* Check whether Institution with same name already exists.*/
	    if(checkDuplicateGrantAgency(partyInstance) == 0){
           	if(partyInstance.save()){
           		partyInstance.saveMode = "Saved"
           	}
	    }
	    else
	    	partyInstance.saveMode = "Duplicate"
	    	
    	return partyInstance
	}
	
}