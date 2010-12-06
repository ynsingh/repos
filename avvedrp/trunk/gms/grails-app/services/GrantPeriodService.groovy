
class GrantPeriodService
{
	
	/**
	 * Get all active grant periods.
	 */
	public List getAllGrantPeriods()
	{
		def grantPeriodInstanceList = GrantPeriod.findAll("from GrantPeriod GP where GP.activeYesNo='Y'")
		return grantPeriodInstanceList
	}
	
	/**
	 * Get grant period details by id.
	 */
	public GrantPeriod getGrantPeriodById(Integer grantPeriodId)
	{
		def grantPeriodInstance = GrantPeriod.get( grantPeriodId )
		return grantPeriodInstance
	}
	
	
	/**
	 * Delete grant period details.
	 */
	public Integer deleteGrantPeriod(def grantPeriodParams)
	{
		def grantPeriodDeletedId = null
		def grantAllocationSplitService = new GrantAllocationSplitService()
		/* Get the list of grant allocation split based on grant period*/
		def grantAllocationSplitInstanceList=grantAllocationSplitService.getGrantAllocationSplitBasedOnGrantPeriod(grantPeriodParams)
		/*Check if any grant allocation split exists for this grant period*/
		if (!grantAllocationSplitInstanceList)
		{
			/*Getting grant period based on id*/
			def grantPeriodInstance = getGrantPeriodById( new Integer(grantPeriodParams.id ))
			if(grantPeriodInstance) 
			{
	        	grantPeriodInstance.modifiedBy="admin"
	    		grantPeriodInstance.modifiedDate=new Date()
	        	/* setting the grant period as inactive */
	        	grantPeriodInstance.activeYesNo="N"
	        	grantPeriodInstance.properties = grantPeriodParams
	        	if(!grantPeriodInstance.hasErrors() && grantPeriodInstance.save()) 
	        	{
	        		grantPeriodDeletedId = grantPeriodInstance.id	
	        	}
			}
		}
		else
		{
			grantPeriodDeletedId=0  
		}
		return grantPeriodDeletedId
	}  
	
	/**
	 * Update grant period details.
	 */
	public GrantPeriod updateGrantPeriod(def grantPeriodParams)
	{
		/*Getting grant period details by id*/
		def grantPeriodInstance = getGrantPeriodById( new Integer(grantPeriodParams.id ))
        if(grantPeriodInstance) 
        {
        	grantPeriodInstance.modifiedBy="admin"
    		grantPeriodInstance.modifiedDate=new Date()
        	grantPeriodInstance.properties = grantPeriodParams
        	if(!grantPeriodInstance.hasErrors() && grantPeriodInstance.save())
	    	{
            	grantPeriodInstance.saveMode = "Updated"
            }
    	}
		return grantPeriodInstance
	}
	
	/**
	 * Save grant period details.
	 */
	public GrantPeriod saveGrantPeriod(def grantPeriodInstance)
	{
		grantPeriodInstance.createdBy="admin"
		grantPeriodInstance.modifiedBy="admin"
		/*Setting the grant period as active*/
		grantPeriodInstance.activeYesNo="Y" //12-11-2010
		if(grantPeriodInstance.save())
		{
    		grantPeriodInstance.saveMode = "Saved"
    	}
	    	
    	return grantPeriodInstance
	}
	
	/**
	 * Getting the list of all default grant periods.
	 */
	public List getDefaultGrantPeriod()
	{
		def chkDefaultGrantPeriodInstance=GrantPeriod.findAll("from GrantPeriod GP where GP.defaultYesNo='Y'")
		return chkDefaultGrantPeriodInstance
	}
	
	/**
	 * Getting grant period details based on the name
	 */
	public GrantPeriod getGrantPeriod(def grantPeriodInstance)
	{	
		def grantPeriodDuplicateInstance=GrantPeriod.find("from GrantPeriod GP where GP.name='" + grantPeriodInstance.name+"'")
		return grantPeriodDuplicateInstance
	}
}