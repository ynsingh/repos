

class FullProposalService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*
     * method to get submitted full proposal by id
     */
    def getSubmittedFullProposalById(def fullProposalId)
    {
    	def fullProposalInstance= FullProposal.find("from FullProposal P where P.proposalStatus='Submitted' and P.id="+fullProposalId)
    	return fullProposalInstance
    }
    /*
     * method to get full proposal by id
     */
    def getFullProposalById(def fullProposalId)
    {
    	def fullProposalInstance= FullProposal.get(fullProposalId)
    	return fullProposalInstance
    }
    /*
     * method to update FullProposal
     */
    def updateFullProposal(def fullProposalInstance)
    {
    	if(fullProposalInstance.save())
    	{
    		
    	}
    	else
    	{
    		fullProposalInstance=null
    	}
    	return fullProposalInstance
    }
    /*
     * method to get full proposal by proposalId
     */
    def getFullProposalByProposal(def proposalId)
    {
    	def fullProposalInstance= FullProposal.find("from FullProposal F where F.preProposal = "+proposalId)
    	return fullProposalInstance
    }
}
