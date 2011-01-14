

class FullProposalDetailService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*
     * method to get FullProposalDetail By FullProposalId
     */
    def getFullProposalDetailByFullProposalId(def proposalId)
    {
    	def fullProposalDetailInstance = FullProposalDetail.find("from FullProposalDetail where fullProposal.id="+proposalId)
    	return fullProposalDetailInstance
    }
}
