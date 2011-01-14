

class ProposalApprovalService {

    boolean transactional = true
    def preProposalService
    def fullProposalService
    def serviceMethod() {

    }
    def getProposalApprovalList(def userId)
    {
    	def proposalApprovalInstanceNewList = []
    	
    	def proposalApprovalInstanceList=getProposalApprovalByUserIdAndProposalType(userId,'PreProposal')
        for(proposalApprovalValue in proposalApprovalInstanceList)
        {
        	
    		
        	def preProposalValue=preProposalService.getSubmittedPreProposalById(proposalApprovalValue.proposalApprovalAuthorityMap.proposalId)
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
}
