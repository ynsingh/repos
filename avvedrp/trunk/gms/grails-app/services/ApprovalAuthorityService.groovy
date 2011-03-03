
class ApprovalAuthorityService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*
     * Get active Approval Authority
     */
     public List getActiveApprovalAuthority(String partyId)
    {
    	def approvalAuthorityList = ApprovalAuthority.findAll("from ApprovalAuthority A where A.activeYesNo = 'Y' and A.party="+partyId)
    	return approvalAuthorityList
    }
     
    
    /*
     * Save Approval Authority
     */
    public saveApprovalAuthority(params)
    {
    	def approvalAuthorityInstance = new ApprovalAuthority(params)
    	approvalAuthorityInstance.save(flush: true)
    	return approvalAuthorityInstance
    }
    /*
     * Get the details of the Approval Authority
     */
     public ApprovalAuthority getApprovalAuthorityById(Integer approvalAuthorityId)
  	{
  		def approvalAuthorityInstance = ApprovalAuthority.get( approvalAuthorityId)
  		return approvalAuthorityInstance
  	}
    /*
     * Update Approval Authority
     */
     public updateApprovalAuthority(def params, def approvalAuthorityInstance)
    {
    	if(approvalAuthorityInstance)
    	{
    		 approvalAuthorityInstance.properties = params
    	
    	    if (!approvalAuthorityInstance.hasErrors() && approvalAuthorityInstance.save(flush: true)) 
    	    {
    		 approvalAuthorityInstance.saveMode = "Updated"
    	    }
    	 return  approvalAuthorityInstance
        }
     return  approvalAuthorityInstance
    }
    
     public Integer deleteApprovalAuthority(def approvalAuthorityParams)
 	{  
 		def approvalAuthorityDeletedId = null
 		def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
 	
 		
 		def approvalAuthorityDetailList = approvalAuthorityDetailService.getApprovalAuthorityDetailBasedOnApprovalAuthority(approvalAuthorityParams)
 	
 		
 		if(!approvalAuthorityDetailList)
 		{	
 			
 			def approvalAuthorityInstance = getApprovalAuthorityById(new Integer(approvalAuthorityParams.id))
 			if(approvalAuthorityInstance)
 			{
 				
 				approvalAuthorityInstance.modifiedBy="admin"
 					approvalAuthorityInstance.modifiedDate=new Date()
 		    	
 					/* setting the account head as inactive */
 					approvalAuthorityInstance.activeYesNo="N"
 		    	
 					if(!approvalAuthorityInstance.hasErrors() && approvalAuthorityInstance.save()) 
 					{
 						approvalAuthorityDeletedId = approvalAuthorityInstance.id
 					}	 
 				   
 			}
 			return approvalAuthorityDeletedId
 		}
 		else
 		{	  
 			approvalAuthorityDeletedId = 0		
 			return approvalAuthorityDeletedId			
 	  	}
 	}
     /*
      * check Aupproval Authority exists
      */
     public checkDuplicateApprovalAuthority(def params)
   	{
   		 def chkApprovalAuthorityInstance = ApprovalAuthority.findAll("from  ApprovalAuthority A where A.name= '"+params.name+"' and A.activeYesNo = 'Y'")
   		 return chkApprovalAuthorityInstance
   	}
     
     public List chkDefaultAuthority(String partyId)
     {
    	 def chkDefaultAuthorityInstance = ApprovalAuthority.findAll("from ApprovalAuthority AA where AA.defaultYesNo = 'Y' and AA.party="+partyId)
    	 return chkDefaultAuthorityInstance
     }
     /*
      * Get default active Approval Authority
      */
      public List getDefaultActiveApprovalAuthority(def partyId)
     {
     	def approvalAuthorityList = ApprovalAuthority.findAll("from ApprovalAuthority A where A.activeYesNo = 'Y' and A.defaultYesNo = 'Y' and A.party="+partyId)
     	return approvalAuthorityList
     }
}
