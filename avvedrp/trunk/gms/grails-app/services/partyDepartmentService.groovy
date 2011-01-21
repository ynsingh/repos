class PartyDepartmentService
{
	public List getActiveDepartment(def partyId)
	{
		def departmentList=PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyId+"and PD.activeYesNo='Y'")
		return departmentList
	}
	
	/*
	 * Function to get Department By ID
	 */
	 public def getPartyDepartmentById(def departmentId)
	{
		 def partyDepartmentInstance = PartyDepartment.get(departmentId)
		 return partyDepartmentInstance
	        
	}
	 /**
	 * Function to save Department.
	 */
	public saveDepartment(def partyDepartmentInstance){
		/* Check whether Institution with same name already exists.*/
	    if(checkDuplicateDepartment(partyDepartmentInstance) == 0){
           	if(partyDepartmentInstance.save()){
           		partyDepartmentInstance.saveMode = "Saved"
           	}
	    }
	    else
	    {
	    	partyDepartmentInstance.saveMode = "Duplicate"
	    	partyDepartmentInstance.id=checkDuplicateDepartment(partyDepartmentInstance)
	    }
	    	
    	return partyDepartmentInstance
	}
	
	/**
	 * Function to check whether Department exists or not.
	 */
	public Integer checkDuplicateDepartment(def partyDepartmentInstance){
    	def departmentId = 0
    	def chkDepartmentInstance = PartyDepartment.find("from PartyDepartment PD where PD.departmentCode= '"+partyDepartmentInstance.departmentCode+"' and PD.activeYesNo='Y' and PD.party="+partyDepartmentInstance.party.id)
    	if(chkDepartmentInstance)
    		departmentId = chkDepartmentInstance.id
    		
    	return departmentId
    }
	/**
	 * Function to update department.
	 */
	public updateDepartment(def departmentParams){
		def partyDepartmentInstance = getPartyDepartmentById( new Integer(departmentParams.id ))
        if(partyDepartmentInstance) {
        	partyDepartmentInstance.modifiedBy="admin"
        		partyDepartmentInstance.modifiedDate=new Date()
        	
        	/* Check whether Institution with same name already exists.*/
            Integer departmentId = checkDuplicateDepartment(departmentParams)
    	    if(departmentId.intValue() == 0 || departmentId.intValue() == partyDepartmentInstance.id.intValue()){
    	    	partyDepartmentInstance.properties = departmentParams
	            if(!partyDepartmentInstance.hasErrors() && partyDepartmentInstance.save()) {
	            	partyDepartmentInstance.saveMode = "Updated"
	            }
    	    }
    	    else
    	    	partyDepartmentInstance.saveMode = "Duplicate"
        }
		return partyDepartmentInstance
	}
	/*
	 * Function to delete department
	 */
	 public deleteDepartment(def partyDepartmentInstance)
	{
		if(partyDepartmentInstance)
		{
			partyDepartmentInstance.activeYesNo = "N"
			partyDepartmentInstance.save()
			return partyDepartmentInstance
		}
	}
		
}
