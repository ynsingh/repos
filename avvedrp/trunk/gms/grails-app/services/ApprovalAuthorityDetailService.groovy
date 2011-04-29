

class ApprovalAuthorityDetailService {

    boolean transactional = true

    def serviceMethod() {

    }
    public saveApprovalAuthorityDetail(params)
    {
    	 def approvalAuthorityDetailInstance = new ApprovalAuthorityDetail(params)
    	 approvalAuthorityDetailInstance.save(flush: true)
    	 return approvalAuthorityDetailInstance
    }
    /*
     * Get the details of the Approval Authority Detail
     */
 	public ApprovalAuthorityDetail getApprovalAuthorityDetailById(Integer approvalAuthorityDetailId)
 	{
 		def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.get( approvalAuthorityDetailId)
 		return approvalAuthorityDetailInstance
 	}
 	/*
     * Update Approval Authority Detail
     */
     public updateApprovalAuthorityDetail(def params, def  approvalAuthorityDetailInstance)
    {
    	if(approvalAuthorityDetailInstance)
    	{
    		approvalAuthorityDetailInstance.properties = params
    	
    	    if (!approvalAuthorityDetailInstance.hasErrors() && approvalAuthorityDetailInstance.save(flush: true)) 
    	    {
    	    	approvalAuthorityDetailInstance.saveMode = "Updated"
    	    }
    	 return  approvalAuthorityDetailInstance
        }
     return  approvalAuthorityDetailInstance
    }
 	public getApprovalAuthorityDetailByApprovalAuthority(def ApprovalAuthorityDetailId)
 	{
 		def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority="+ApprovalAuthorityDetailId)
 		return approvalAuthorityDetailInstance
 	}
 	public List getApprovalAuthorityDetailBasedOnApprovalAuthority(def approvalAuthorityParams)
 	{
 		def approvalAuthorityDetailList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority="+approvalAuthorityParams.id)
 		return approvalAuthorityDetailList
 	}
 	
 	public def deleteApprovalAuthorityDetail(def approvalAuthorityDetailParams)
 	{  
 		def approvalAuthorityDetailDeletedId = null
 		def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
 	
 		
 		def proposalApprovalAuthorityMapList = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapBasedOnApprovalAuthorityDetail(approvalAuthorityDetailParams)
 	
 		
 		if(!proposalApprovalAuthorityMapList)
 		{	
 			
 			def approvalAuthorityDetailInstance = getApprovalAuthorityDetailById(new Integer(approvalAuthorityDetailParams.id))
 			if(approvalAuthorityDetailInstance)
 			{
 				
 				
 					approvalAuthorityDetailInstance.modifiedDate=new Date()
 		    	
 					/* setting the account head as inactive */
 					approvalAuthorityDetailInstance.activeYesNo="N"
 		    	
 					if(!approvalAuthorityDetailInstance.hasErrors() && approvalAuthorityDetailInstance.save()) 
 					{
 						approvalAuthorityDetailDeletedId = approvalAuthorityDetailInstance.id
 					}	 
 				   
 			}
 			return approvalAuthorityDetailDeletedId
 		}
 		else
 		{	  
 			approvalAuthorityDetailDeletedId = 0		
 			return approvalAuthorityDetailDeletedId			
 	  	}
 	}
 	/*
 	 * method to get approval authority details of default approval authority
 	 */
 	public List getDefaultApprovalAuthorityDetailsByParty(def party)
  	{
  		def approvalAuthorityDetailList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority.defaultYesNo='Y' and AD.approvalAuthority.party="+party)
  		return approvalAuthorityDetailList
  	}
 	/*
 	 * To get Approval Authority Detail list By Approval Authority
 	 */
 	public List getApprovalAuthorityDetailByApprovalAuthority(def approvalAuthorityId)
 	{
 		def approvalAuthorityDetailInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.approvalAuthority ="+approvalAuthorityId+" and AAD.activeYesNo = 'Y'")
    	return approvalAuthorityDetailInstanceList
	}
 	/*
 	 * method to get approval authority details by approval authority and user
 	 */
 	public getApprovalAuthorityDetailByApprovalAuthorityUser(def approvalAuthorityId,def userId)
 	{
 		def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.find("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority="+approvalAuthorityId+" and AD.person="+userId)
 		return approvalAuthorityDetailInstance
 	}
 	/*
 	 * Get ApprovalAuthorityDetail List By Person
 	 */
 	 public getApprovalAuthorityDetailInstanceListByPerson(def UserId)
 	 {
 		def approvalAuthorityDetailInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.person.id="+UserId+" and AAD.activeYesNo = 'Y'")
    	return approvalAuthorityDetailInstanceList
 	 }
}