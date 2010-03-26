
class AccountHeadsService{
	
	/**
	 * Function to get all active account heads.
	 */
	public List getActiveAccountHeads(String subQuery){
		def accountHeadsInstanceList = AccountHeads.findAll( "from AccountHeads AH where AH.parent=NULL and AH.activeYesNo='Y' "+subQuery  ) 
		return accountHeadsInstanceList
	}
	
	/**
	 * Function to get all active main account heads.
	 */
	/*public List getActiveMainAccountHeads(String subQuery){
		def accountHeadsInstanceList =AccountHeads.findAll( "from AccountHeads AH where AH.parent=NULL and AH.activeYesNo='Y'"+subQuery )
		return accountHeadsInstanceList
	}
	*/
	/**
	 * Function to get sub account heads.
	 */
	public List getSubAccountHeads(Integer mainAccountHeadId){
		def accountHeadsInstanceList=AccountHeads.findAll("from AccountHeads P where P.parent.id="+mainAccountHeadId)
		return accountHeadsInstanceList
	}
	
	/**
	 * Function to get account head by id.
	 */
	public AccountHeads getAccountHeadsById(Integer accountHeadId){
		def accountHeadsInstance = AccountHeads.get( accountHeadId )
		return accountHeadsInstance
	}
	
	/**
	 * Function to delete account head.
	 */
	public Integer deleteAccountHeads(Integer accountHeadsId){
		def accountHeadsDeletedId = null
		
		def accountHeadsInstance = getAccountHeadsById( accountHeadsId )
        if(accountHeadsInstance) {
            accountHeadsInstance.delete()
            accountHeadsDeletedId = accountHeadsInstance.id
        }
		return accountHeadsDeletedId
	}
	
	/**
	 * Function to update account heads
	 */
	public AccountHeads updateAccountHeads(def accountHeadsParams){
		def accountHeadsInstance = getAccountHeadsById( new Integer(accountHeadsParams.id ))
        if(accountHeadsInstance) {
        	accountHeadsInstance.modifiedBy="admin"
        	accountHeadsInstance.modifiedDate=new Date()
        	                        
            /* Check whether party with same name already exists.*/
            Integer accountHeadId = checkDuplicateAccountHead(accountHeadsParams)
    	    if(accountHeadId.intValue() == 0 || accountHeadId.intValue() == accountHeadsInstance.id.intValue()){
    	    	println "No duplicates"
    	    	accountHeadsInstance.properties = accountHeadsParams
	            if(!accountHeadsInstance.hasErrors() && accountHeadsInstance.save()) {
	            	accountHeadsInstance.saveMode = "Updated"
	            }
    	    }
    	    else
    	    	accountHeadsInstance.saveMode = "Duplicate"
        }
		return accountHeadsInstance
	}
	
	/**
	 * Function to save account head.
	 */
	public AccountHeads saveAccountHeads(def accountHeadsInstance){
		/* Check whether party with same name already exists.*/
	    if(checkDuplicateAccountHead(accountHeadsInstance) == 0){
        	if(accountHeadsInstance.save()){
        		accountHeadsInstance.saveMode = "Saved"
        	}
	    }
	    else
	    	accountHeadsInstance.saveMode = "Duplicate"
	    	
    	return accountHeadsInstance
	}
	
	/**
	 * Function to check whether account head already exists or not.
	 */
	public Integer checkDuplicateAccountHead(def accountHeadsInstance){
    	def accountHeadId = 0
    	System.out.println("DuplicateAccountHead__ "+accountHeadsInstance.code)
    	def chkAccountHeadInstance = AccountHeads.find("from AccountHeads AH where AH.code= '"+accountHeadsInstance.code+"' and AH.activeYesNo='Y' ")
    	if(chkAccountHeadInstance)
    		accountHeadId = chkAccountHeadInstance.id
    		
    	return accountHeadId
    }
	            
	            
	
}