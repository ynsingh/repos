

class ProposalApprovalService {

    boolean transactional = true
    def preProposalService
    def fullProposalService
    def proposalApprovalAuthorityMapService
    def serviceMethod() {

    }
    def getProposalApprovalList(def proposalApprovalAuthorityMapInstance)
    {
    	def proposalApprovalInstanceNewList = []
    	//println "preProposalValue00000 "+userId
    	//def proposalApprovalInstanceList=getProposalApprovalByUserIdAndProposalType(userId,'PreProposal')
        //def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByReviewer(userId,'PreProposal')
    	println "proposalApprovalAuthorityMapInstance "+proposalApprovalAuthorityMapInstance
        for(proposalApprovalValue in proposalApprovalAuthorityMapInstance)
        {
        	
    		
        	def preProposalValue=preProposalService.getSubmittedPreProposalById(proposalApprovalValue.proposalId)
        	
        	def preProposalApprovalLevel
        	if(preProposalValue == null)
        	{
        		preProposalApprovalLevel = 0
        	}
        	else
        	{
        		preProposalApprovalLevel = preProposalValue.preProposalLevel
        	}
        	
        	if(preProposalValue != null)
        	{
        	if((new Integer(proposalApprovalValue.approveOrder))==(new Integer(preProposalApprovalLevel)+1))
        	{
        		  proposalApprovalInstanceNewList << preProposalValue
        		  preProposalValue.viewAll='Y'
        	}
        	else if(proposalApprovalValue.approvalAuthority.viewAll=='Y')
        	{
        		preProposalValue.viewAll='Y'
        		proposalApprovalInstanceNewList << preProposalValue
        	}
        	}
        }
    	return proposalApprovalInstanceNewList
    }
    /*
     * method to get Full ProposalApproval list
     */
    def getFullProposalApprovalList(def userId)
    {
    	def proposalApprovalInstanceNewList = []
    	
    	def proposalApprovalInstanceList=getProposalApprovalByUserIdAndProposalType(userId,'FullProposal')
    	for(proposalApprovalValue in proposalApprovalInstanceList)
        {
        	
        	
        	def preProposalValue=fullProposalService.getSubmittedFullProposalById(proposalApprovalValue.proposalApprovalAuthorityMap.proposalId)
        	
        	def preProposalApprovalLevel
        	if(preProposalValue == null)
        	{
        		preProposalApprovalLevel = 0
        	}
        	else
        	{
        		preProposalApprovalLevel = preProposalValue.preProposalLevel
        	}
        	
        	if(preProposalValue != null)
        	{
        	if((new Integer(proposalApprovalValue.proposalApprovalAuthorityMap.approveOrder))==(new Integer(preProposalApprovalLevel)+1))
        	{
        		proposalApprovalInstanceNewList << proposalApprovalValue
        	}
        	else if(proposalApprovalValue.proposalApprovalAuthorityMap.approvalAuthority.viewAll=='Y')
        	{
        		proposalApprovalValue.viewAll='Y'
        		proposalApprovalInstanceNewList << proposalApprovalValue
        	}
        	}
        }
    	return proposalApprovalInstanceNewList
    }
    def getProposalApprovalByUserIdAndProposalType(def userId,def proposalType)
    {
    	def proposalApprovalInstanceList=ProposalApproval.findAll("from ProposalApproval PA where PA.approvalAuthorityDetail.person.id="+userId+"and PA.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
    	return proposalApprovalInstanceList
    }
    
    
    def getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(def approvalAuthorityDetailInstanceList,def proposalApprovalAuthorityMapInstance)
    {
    	def proposalApprovalInstanceList = []
    	for(int j=0;j<approvalAuthorityDetailInstanceList.size();j++)
		{
	    	def proposalApprovalInstance = ProposalApproval.find("from ProposalApproval PA where PA.proposalApprovalAuthorityMap.id = "+proposalApprovalAuthorityMapInstance.id +"and PA.approvalAuthorityDetail.id = "+approvalAuthorityDetailInstanceList[j].id)
	    	if(proposalApprovalInstance)
	    	{
	    		proposalApprovalInstanceList.add(proposalApprovalInstance)
	    	}
		}
    	return proposalApprovalInstanceList
    }
    /*
     * method to get proposal approval by proposalApprovalAuthorityMap and approvalAuthorityDetails id
     */
    def getProposalApprovalByProposalApprovalAuthorityMapAndUser(def proposalApprovalAuthorityMapInstanceId,
    																def approvalAuthorityDetailId)
    {
    	def proposalApprovalInstance = ProposalApproval.find("from ProposalApproval PA where PA.proposalApprovalAuthorityMap.id ="+proposalApprovalAuthorityMapInstanceId+" and PA.approvalAuthorityDetail = "+approvalAuthorityDetailId)
	    return proposalApprovalInstance
    }
    /*
     * method to get proposal approval by proposalApprovalAuthorityMap and approvalAuthorityDetails id
     */
    def saveProposalApproval(def proposalApprovalInstance)
    {
    	if(proposalApprovalInstance.save())
    	{
    		
    	}
    	else
    	{
    		proposalApprovalInstance=null
    	}
    	return proposalApprovalInstance
    }
   /*
    * Get Proposal Approval By ProposalApprovalAuthorityMap
    */
    def getProposalApprovalByProposalApprovalAuthorityMap(def proposalApprovalAuthorityMapInstanceId)
   {
    	def proposalApprovalInstanceList = ProposalApproval.findAll("from ProposalApproval PA where PA.proposalApprovalAuthorityMap.id =" +proposalApprovalAuthorityMapInstanceId) 
		return proposalApprovalInstanceList
   }
   
    /*
     * Get Proposal Approval By ProposalApprovalAuthorityMap and approvalAuthority
     */
   def getProposalApprovalByAuthorityMapandauthorityId(def proposalApprovalAuthorityMapInstance,def approvalAuthorityDetailId)
   {
	   def proposalApprovalInstanceList = []
   	for(int j=0;j<proposalApprovalAuthorityMapInstance.size();j++)
		{
	    	def proposalApprovalInstance = ProposalApproval.find("from ProposalApproval PA where PA.proposalApprovalAuthorityMap.id ="+proposalApprovalAuthorityMapInstance[j].id +"and PA.approvalAuthorityDetail.id = "+approvalAuthorityDetailId)
	    	
	    	if(proposalApprovalInstance)
	    	{
	    		proposalApprovalInstanceList.add(proposalApprovalInstance)
	    	}
	    	
		}
   	return proposalApprovalInstanceList
	 
   }
}
