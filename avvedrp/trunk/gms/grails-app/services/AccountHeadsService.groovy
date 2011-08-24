
class AccountHeadsService{
	
	/**
	 * Get all active account heads.
	 */
	public List getActiveAccountHeads(String subQuery)
	{
		def accountHeadsInstanceList = AccountHeads.findAll( "from AccountHeads AH where AH.parent=NULL and AH.activeYesNo='Y'"+subQuery  ) 
		return accountHeadsInstanceList
	}
	
	/**
	 * Get sub account heads.
	 */
	public List getSubAccountHeads(Integer mainAccountHeadId)
	{
		def accountHeadsInstanceList=AccountHeads.findAll("from AccountHeads P where P.parent.id="+mainAccountHeadId+"and P.activeYesNo='Y'")
		for(int i=0;i<accountHeadsInstanceList.size();i++ )
        {
			accountHeadsInstanceList[i].accHeadCode=accountHeadsInstanceList[i].code+" -"+accountHeadsInstanceList[i].name
        }
		return accountHeadsInstanceList
	}
	
	/**
	 * Get account head details by id.
	 */
	public AccountHeads getAccountHeadsById(Integer accountHeadId)
	{
		def accountHeadsInstance = AccountHeads.get( accountHeadId )
		return accountHeadsInstance
	}
	
	/**
	 * Delete account head details.
	 */

	public Integer deleteAccountHeads(def accountHeadsParams)
	{  
		def accountHeadsDeletedId = null
		def grantAllocationSplitService = new GrantAllocationSplitService()
	
		/* Get the list of grant allocation split based on account head */
		def grantAllocationSplitInstanceList = grantAllocationSplitService.getGrantAllocationSplitBasedOnAccountHead(accountHeadsParams)
	
		/* Check if any grant allocation split exists for this account head */
		if(!grantAllocationSplitInstanceList)
		{	
			/*Getting account head based on id*/
			def accountHeadsInstance = getAccountHeadsById( new Integer(accountHeadsParams.id ))
			if(accountHeadsInstance)
			{
				/* Getting sub account heads of the selected account head */
				def chkAccountHeadInstance=AccountHeads.findAll("from AccountHeads AH where AH.parent= "+accountHeadsInstance.id + " AND AH.activeYesNo='Y'")
			
				/* check whether the account head had no child */
				if(chkAccountHeadInstance[0]==null) 
				{
					accountHeadsInstance.modifiedBy="admin"
					accountHeadsInstance.modifiedDate=new Date()
		    	
					/* setting the account head as inactive */
					accountHeadsInstance.activeYesNo="N"
		    	
					if(!accountHeadsInstance.hasErrors() && accountHeadsInstance.save()) 
					{
						accountHeadsDeletedId = accountHeadsInstance.id
					}	 
				}    
			}
			return accountHeadsDeletedId
		}
		else
		{	  
			accountHeadsDeletedId = 0		
			return accountHeadsDeletedId			
	  	}
	}

	
	/**
	 * Update account head details
	 */
	public AccountHeads updateAccountHeads(def accountHeadsParams)
    {
		/* Getting account head details by id */
		def accountHeadsInstance = getAccountHeadsById( new Integer(accountHeadsParams.id ))
        if(accountHeadsInstance) 
        {
        	accountHeadsInstance.modifiedBy="admin"
        	accountHeadsInstance.modifiedDate=new Date()
        	
        	/* Check whether account head with same name already exists.*/
            Integer accountHeadId = checkDuplicateAccountHead(accountHeadsParams)
    	    if(accountHeadId.intValue() == 0 || accountHeadId.intValue() == accountHeadsInstance.id.intValue())
    	    {
    	    	accountHeadsInstance.properties = accountHeadsParams
	            if(!accountHeadsInstance.hasErrors() && accountHeadsInstance.save()) 
	            {
	            	accountHeadsInstance.saveMode = "Updated"
	            }
    	    }
    	    else
    	    	accountHeadsInstance.saveMode = "Duplicate"
        }
		return accountHeadsInstance
	}
	
	/**
	 * Save account head details.
	 */
	public AccountHeads saveAccountHeads(def accountHeadsInstance)
	{
		accountHeadsInstance.createdBy="admin"
        accountHeadsInstance.createdDate = new Date();
        accountHeadsInstance.modifiedBy="admin"
        accountHeadsInstance.activeYesNo="Y" 
        
		/* Check whether account head  with same name already exists.*/
	    if(checkDuplicateAccountHead(accountHeadsInstance) == 0)
	    {
        	if(accountHeadsInstance.save())
        	{
        		accountHeadsInstance.saveMode = "Saved"
        	}
	    }
	    else
	    	accountHeadsInstance.saveMode = "Duplicate"
	    	
    	return accountHeadsInstance
	}
	
	/**
	 * Check whether account head already exists or not.
	 */
	public Integer checkDuplicateAccountHead(def accountHeadsInstance)
	{
    	def accountHeadId = 0
    	def chkAccountHeadInstance = AccountHeads.find("from AccountHeads AH where AH.code= '"+accountHeadsInstance.code+"' and AH.activeYesNo='Y' ")
    	if(chkAccountHeadInstance)
    	  accountHeadId = chkAccountHeadInstance.id
    		
    	return accountHeadId
    }
	/**
	 * Function for getting the parent of a subaccount head
	 */
	public AccountHeads getParentAccountHead(def accHeads)
	{
		def accountHeadsInstance = AccountHeads.find("from AccountHeads AH where AH.id= "+accHeads.parent.id)
		return accountHeadsInstance
	}        
	public List getAllAccountHeads()
	{
        def accountHeadList=AccountHeads.findAll("from AccountHeads AH where AH.parent.id is NULL and AH.activeYesNo='Y' order by AH.name")
        return accountHeadList
	}
}