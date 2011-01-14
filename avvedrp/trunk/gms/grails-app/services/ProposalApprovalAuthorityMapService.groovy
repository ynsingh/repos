

class ProposalApprovalAuthorityMapService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*
     * method to get lowest process restart order of a proposal
     */
    def getPreProposalApprovalRestartMinOrder(def proposalType,def proposalId)
    {
    	def preProposalApprovalRestartMinOrder = ProposalApprovalAuthorityMap.executeQuery("select MIN(PM.processRestartOrder) from ProposalApprovalAuthorityMap PM where PM.proposalType='"+proposalType+"' and PM.proposalId="+proposalId)
    	return preProposalApprovalRestartMinOrder
    }
    /*
     * method to get highest approve order of a proposal
     */
    def getPreProposalApprovalMaxOrder(def proposalType,def proposalId)
    {
    	def preProposalApprovalMaxOrder = ProposalApprovalAuthorityMap.executeQuery("select MAX(PM.approveOrder) from ProposalApprovalAuthorityMap PM where PM.proposalType='"+proposalType+"' and PM.proposalId="+proposalId)
    	return preProposalApprovalMaxOrder
    }
    public def getProposalApprovalAuthorityMapBasedOnApprovalAuthorityDetail(def approvalAuthorityDetailParams)
    {
    	def proposalApprovalAuthorityMapList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.approvalAuthority="+approvalAuthorityDetailParams.approvalAuthority.id)
    	return proposalApprovalAuthorityMapList
    }
}
