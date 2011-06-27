

class ProposalApprovalDetailService {

    boolean transactional = true
    def approvalAuthorityDetailService
    def proposalApprovalAuthorityMapService
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
    def getProposalApprovalDetailsByUserAndProposalId(def UserId,def proposalId,def approvalAuthorityId,def proposalType)
    {
    	def proposalApprovalDetailUserInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.person.id="+UserId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId)
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
     * method to get proposalApprovalDetail using approvalAuthorityId ,proposalid,proposal type ans status
     */
    def getProposalApprovalDetailByProposalStatus(def approvalAuthorityId,def proposalId,def status,def proposalType)
    {
    	def proposalApprovalDetailByProposalStatus = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalStatus='"+status+"' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
    	return proposalApprovalDetailByProposalStatus
    }
    /*
     * 
     */
    def inactivateProposalApprovalDetailForNeedMoreInfo(def proposalId,proposalLevel,def proposalType)
    {
    	def proposalApprovalDetailInstance = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.approveOrder >="+(new Integer(proposalLevel))+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
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
     /*
      * method to get Preproposal proposalApprovalDetail using approvalAuthorityId,proposalid and proposal type
      */
     def getProposalApprovalDetailsByAuthorityAndProposalId(def approvalAuthorityId,def proposalId,def proposalType)
     {
     	def proposalApprovalDetailByApprovalAuthority = ProposalApprovalDetail.findAll("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.approvalAuthorityDetail.approvalAuthority.id="+approvalAuthorityId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+proposalId+" and PD.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalType+"'")
     	return proposalApprovalDetailByApprovalAuthority
     }
     /*
      * method to get proposalApprovalDetail using ProposalApproval
      */
     def getProposalApprovalDetailByProposalApproval(def proposalApprovalId)
     {
     	def proposalApprovalDetailInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PD where PD.activeYesNo='Y' and PD.proposalApproval.id="+proposalApprovalId)
     	return proposalApprovalDetailInstance
     }
     /*
      * method to validate proposalApprovalDetail data and change the status of proposal 
      */
      def validateProposalApprovalDetail(def proposalApprovalDetailInstance,def proposalType,def proposalLevel,def proposalCurStatus)
     {
    	  def proposalId=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId
    	  def proposalStatus=proposalCurStatus
    	  def proposalNewLevel=proposalLevel
    	  def proposalValues=[:]
    	  /*method to get all the proposalApprovalDetail of the preproposal using approvalAuthority*/
			def proposalApprovalDetailByApprovalAuthority=getProposalApprovalDetailsByAuthorityAndProposalId(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId,proposalType)
			/*method to get all the approvalAuthorityDetail of current approvalAuthority*/
			def approvalAuthorityInstance = approvalAuthorityDetailService.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id)
			/*method to get the minimum restart order of preProposal from proposalApprovalAuthorityMap*/
			def preProposalApprovalRestartMinOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalRestartMinOrder(proposalType,proposalId)
			/*method to get the maximum approval order of preProposal from proposalApprovalAuthorityMap*/
			def preProposalApprovalMaxOrder=proposalApprovalAuthorityMapService.getPreProposalApprovalMaxOrder(proposalType,proposalId)
			/*method to get all proposalApprovalDetail which status Approved*/
			def proposalApprovalDetailByProposalApprovedStatus =getProposalApprovalDetailByProposalStatus(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId,'Approved',proposalType)
			/*method to get all proposalApprovalDetail which status Rejected*/
			def proposalApprovalDetailByProposalRejectedStatus=getProposalApprovalDetailByProposalStatus(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId,'Rejected',proposalType)
			/*method to get all proposalApprovalDetail which status NeedMoreInfo*/
			def proposalApprovalDetailByProposalNeedMoreInfoStatus=getProposalApprovalDetailByProposalStatus(proposalApprovalDetailInstance.proposalApproval.approvalAuthorityDetail.approvalAuthority.id,proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId,'NeedMoreInfo',proposalType)
			/*assign total size of each proposalApprovalDetailsInstance*/
			def proposalApprovalDetailByProposalApprovedStatusSize=proposalApprovalDetailByProposalApprovedStatus.size()
			def proposalApprovalDetailByProposalRejectedStatusSize=proposalApprovalDetailByProposalRejectedStatus.size()
			def proposalApprovalDetailByProposalNeedMoreInfoStatusSize=proposalApprovalDetailByProposalNeedMoreInfoStatus.size()
			def approvalAuthorityDetailInstanceSize=approvalAuthorityInstance.size()
			/*validating proposal reviews and update proposal status and level*/
			if(proposalApprovalDetailByApprovalAuthority.size()==approvalAuthorityDetailInstanceSize)
			{
				if(approvalAuthorityInstance[0].approvalAuthority.approveAll=='Y')
	      		{
					if(proposalApprovalDetailByProposalApprovedStatusSize==approvalAuthorityDetailInstanceSize)
	      			{
						if(proposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
	      				{
							proposalNewLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
	      				}
	              		
	              		if(preProposalApprovalMaxOrder[0]==proposalNewLevel)
	      				{
	              			proposalStatus="Approved"
	                  	}
	              		
	      			}
					else if(proposalApprovalDetailByProposalRejectedStatusSize>proposalApprovalDetailByProposalNeedMoreInfoStatusSize)
					{
						if(proposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
	      				{
							proposalNewLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
	      				}
						proposalStatus="Rejected"
	                  		
					}
					else
					{
						proposalStatus="NeedMoreInfo"
	          			proposalNewLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
	              		
					}
	      		}
				else
				{
					if(proposalApprovalDetailByProposalApprovedStatusSize>proposalApprovalDetailByProposalRejectedStatusSize && proposalApprovalDetailByProposalApprovedStatusSize>proposalApprovalDetailByProposalNeedMoreInfoStatusSize)
					{
						if(proposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
	      				{
							proposalNewLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
	      				}
						if(preProposalApprovalMaxOrder[0]==proposalNewLevel)
	      				{
	              			proposalStatus="Approved"
	                  	}
	              		
					}
					else if(proposalApprovalDetailByProposalRejectedStatusSize>proposalApprovalDetailByProposalNeedMoreInfoStatusSize)
					{
						if(proposalLevel<proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder)
	      				{
							proposalNewLevel=proposalApprovalDetailInstance.proposalApproval.proposalApprovalAuthorityMap.approveOrder
	      				}
						proposalStatus="Rejected"
	                  	
					}
					else
					{
						proposalStatus="NeedMoreInfo"
						proposalNewLevel=new Integer(preProposalApprovalRestartMinOrder[0])-1
                  		
					}
				}
			}
    	  /*create a map with values proposal status and proposal level*/
			proposalValues = ['proposalStatus':proposalStatus,'proposalNewLevel':proposalNewLevel]
    	  
    	  return proposalValues
     }
     def saveProposalApprovalDetail(def proposalApprovalDetailInstance)
     {
    	 if(proposalApprovalDetailInstance.save())
    	 {
    		 
    	 }
    	 else
    	 {
    		 proposalApprovalDetailInstance=null
    	 }
    	 return proposalApprovalDetailInstance
     }
     def proposalApprovalDetailByProposalApproval(def proposalApprovalInstanceId)
     {
    	 def proposalApprovalDetailInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PAD where PAD.proposalApproval.id ="+proposalApprovalInstanceId)
 		 return proposalApprovalDetailInstance
     }
     def proposalApprovalDetailByProposalApprovalAuthorityMap(def proposalApprovalAuthorityMapInstance)
     {
    	 def proposalList = ProposalApprovalDetail.executeQuery(" from ProposalApprovalDetail PA where PA.proposalApproval.proposalApprovalAuthorityMap.proposalId ="+ proposalApprovalAuthorityMapInstance.proposalId +" and  PA.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+proposalApprovalAuthorityMapInstance.proposalType+"'") 
    	 return proposalList
     }
     /*
      * method to get proposal approval details by user id and proposal type
      */
      def getProposalApplDetailsByUserAndProposalType(def userId,def params)
     {
     	 def proposalApprovalDetailInstance= ProposalApprovalDetail.findAll("from ProposalApprovalDetail PA where PA.proposalApproval.approvalAuthorityDetail.person.id="+userId+"and PA.proposalApproval.proposalApprovalAuthorityMap.proposalType='"+params.ProposalType+"'")
     	 return proposalApprovalDetailInstance
     }
}
