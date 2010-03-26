
class GrantPeriodService{
	
	/**
	 * Function to get all grant periods.
	 */
	public List getAllGrantPeriods(def grantPeriodParams){
		def grantPeriodInstanceList = GrantPeriod.list( grantPeriodParams )
		return grantPeriodInstanceList
	}
	
	/**
	 * Function to get grant period by id.
	 */
	public GrantPeriod getGrantPeriodById(Integer grantPeriodId){
		def grantPeriodInstance = GrantPeriod.get( grantPeriodId )
		return grantPeriodInstance
	}
	
	/**
	 * Function to delete grant period.
	 */
	public Integer deleteGrantPeriod(Integer grantPeriodId){
		def grantPeriodDeletedId = null
		
		def grantPeriodInstance = getGrantPeriodById( grantPeriodId )
        if(grantPeriodInstance) {
        	grantPeriodInstance.delete()
            grantPeriodDeletedId = grantPeriodInstance.id
        }
		return grantPeriodDeletedId
	}
	
	/**
	 * Function to update grant period.
	 */
	public GrantPeriod updateGrantPeriod(def grantPeriodParams){
		def grantPeriodInstance = getGrantPeriodById( new Integer(grantPeriodParams.id ))
        if(grantPeriodInstance) {
        	grantPeriodInstance.modifiedBy="admin"
    		grantPeriodInstance.modifiedDate=new Date()
        	grantPeriodInstance.properties = grantPeriodParams
	    	
            if(!grantPeriodInstance.hasErrors() && grantPeriodInstance.save()) {
            	grantPeriodInstance.saveMode = "Updated"
            }
    	   
        }
		return grantPeriodInstance
	}
	
	/**
	 * Function to save grant period.
	 */
	public GrantPeriod saveGrantPeriod(def grantPeriodInstance){
		
    	if(grantPeriodInstance.save()){
    		grantPeriodInstance.saveMode = "Saved"
    	}
	    	
    	return grantPeriodInstance
	}
	
}