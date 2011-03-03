

class ProposalApprovalDetailService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*
     * method to get fullproposal proposalApprovalDetail using userid and proposalid
     */
    def getFullProposalApprovalDetailsByUserAndProposalId(def UserId,def proposalId,def approvalAuthorityId)
    {
    	def proposalApprovalDetailUserInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.person.id="+UserId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId)
    	return proposalApprovalDetailUserInstance
    }
    /*
     * method to get preproposal proposalApprovalDetail using userid and proposalid
     */
    def getPreProposalApprovalDetailsByUserAndProposalId(def UserId,def proposalId,def approvalAuthorityId)
    {
    	def proposalApprovalDetailUserInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.person.id="+UserId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId)
    	return proposalApprovalDetailUserInstance
    }
    /*
     * method to get fullproposal proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getFullProposalApprovalDetailsByAuthorityAndProposalId(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByApprovalAuthority = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal'")
    	return proposalApprovalDetailByApprovalAuthority
    }
    /*
     * method to get Preproposal proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getPreProposalApprovalDetailsByAuthorityAndProposalId(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByApprovalAuthority = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal'")
    	return proposalApprovalDetailByApprovalAuthority
    }
    /*
     * method to get approved Fullproposal proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getApprovedFullProposalApprovalDetailsByAuthorityAndProposalId(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByProposalStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='Approved' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal'")
    	return proposalApprovalDetailByProposalStatus
    }
    /*
     * method to get approved preproposal proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getApprovedPreProposalApprovalDetailsByAuthorityAndProposalId(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByProposalStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='Approved' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal'")
    	return proposalApprovalDetailByProposalStatus
    }
    /*
     * method to get rejected FullproposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getFullProposalApprovalDetailByProposalRejectedStatus(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByProposalRejectedStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='Rejected'and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal'")
    	return proposalApprovalDetailByProposalRejectedStatus
    }
    /*
     * method to get rejected Pre proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getPreProposalApprovalDetailByProposalRejectedStatus(def approvalAuthorityId,def proposalId)
    {
    	def proposalApprovalDetailByProposalRejectedStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='Rejected'and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal'")
    	return proposalApprovalDetailByProposalRejectedStatus
    }
    /*
     * method to get rejected Pre proposalApprovalDetail using approvalAuthorityId and proposalid
     */
    def getPreProposalApprovalDetailByProposalStatus(def approvalAuthorityId,def proposalId,def status,def proposalType)
    {
    	def proposalApprovalDetailByProposalStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='"+status+"' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
    	return proposalApprovalDetailByProposalStatus
    }
    /*
     * 
     */
    def inactivateProposalApprovalDetailForNeedMoreInfo(def proposalInstance)
    {
    	def proposalApprovalDetailInstance = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalInstance.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder >="+(new Integer(proposalInstance.preProposalLevel))+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='PreProposal'")
    	for(proposalApprovalDetailValue in proposalApprovalDetailInstance)
    	{
    		println proposalApprovalDetailValue
    		proposalApprovalDetailValue.activeYesNo='N'
    		proposalApprovalDetailValue.save()
    	}
    	return proposalApprovalDetailInstance
    }
    /*
     * 
     */
    def inactivateFullProposalApprovalDetailForNeedMoreInfo(def proposalInstance)
    {
    	def proposalApprovalDetailInstance = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalInstance.id+" and PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder >="+(new Integer(proposalInstance.preProposalLevel))+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='FullProposal'")
    	for(proposalApprovalDetailValue in proposalApprovalDetailInstance)
    	{
    		println proposalApprovalDetailValue
    		proposalApprovalDetailValue.activeYesNo='N'
    		proposalApprovalDetailValue.save()
    	}
    	return proposalApprovalDetailInstance
    }
    /*
     * method to get proposal approval details by user id and proposal type
     */
     def getProposalApprovalDetailsByUserAndProposalType(def userId,def proposalType)
    {
    	 def proposalApprovalDetailInstance= ProposalApprovalDetail.findAll("from ProposalApprovalDetail PA where PA.proposalApproval.approvalAuthorityDetail.person.id="+userId+"and PA.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
    	 return proposalApprovalDetailInstance
    }
}
