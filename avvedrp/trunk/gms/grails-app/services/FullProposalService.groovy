

class FullProposalService {

    boolean transactional = true

    def serviceMethod() {

    }
    def getSubmittedFullProposalById(def fullProposalId)
    {
    	def fullProposalInstance= FullProposal.find("from FullProposal P where P.proposalStatus='Submitted' and P.id="+fullProposalId)
    	return fullProposalInstance
    }
}
