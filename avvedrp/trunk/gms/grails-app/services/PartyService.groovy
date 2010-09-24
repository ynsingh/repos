
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
		//check fund allocation by party and grantagency
		def partyFromGrantAllocationInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.party="+partyInstance.id)
		def partyFromGrantAllocationGrantorInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.granter="+partyInstance.id)
		println "partyFromGrantAllocationInstance"+partyFromGrantAllocationInstance
        if((!partyFromGrantAllocationInstance)&& (!partyFromGrantAllocationGrantorInstance))
        {
		if(partyInstance) {
        	if(partyInstance.delete()){
        		partyDeletedId = partyInstance.id
        	}
        	else
        		partyDeletedId = -1
        }
		}
        else
        {
        	partyDeletedId = null
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
		def partyInstanceList =Party.findAll( "from Party P where P.partyType='GA' and P.activeYesNo='Y'"+subQuery ) 
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
	/**
	 * get departments of an institution
	 */
	public PartyDepartment[] getPartyDepartment(def partyId)
	{
		
		def partyDepartmentInstance =  PartyDepartment.findAll("from PartyDepartment P where P.party.id = " +partyId+ "and P.activeYesNo='Y'")
		println "=====partyDepartmentInstance====== " + partyDepartmentInstance 
		return partyDepartmentInstance
	}
	
}