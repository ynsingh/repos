

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
   /*
    * Method to check  duplicate data
    */
    public def checkDuplicateProposalApprovalAuthorityMap(def proposalApplicationInstance,def params)
   
   {   
	   println"proposalApplicationInstance++++"+proposalApplicationInstance
	                                              
   	 def proposalApprovalAuthorityMapData = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalId="+proposalApplicationInstance.proposal.id+" and PAM.proposalType='"+params.proposalType+"' and PAM.approvalAuthority="+params.approvalAuthority.id)
	    
   	 println"proposalApprovalAuthorityMapData---------------"+proposalApprovalAuthorityMapData
   	 return proposalApprovalAuthorityMapData
   }
   
    /*
     * Method to check  duplicate data in edit
     
     public List checkDuplicateEditProposalApprovalAuthorityMap(def params)
    
    {   
 	   println"params++++"+params
    	 def proposalApprovalAuthorityMapDetail = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where  PAM.approvalAuthority="+params.approvalAuthority.id)
    	 println"proposalApprovalAuthorityMapDetail"+proposalApprovalAuthorityMapDetail
    	 return proposalApprovalAuthorityMapDetail
    }
    */
/*
     * Save Proposal Approval Authority Map for Expense Request Entry
     */
    public saveProposalApprovalAuthorityMap(def proposalApprovalAuthorityMapInstanceList,def approvalAuthorityInstance,def userInstance,def expenseRequestEntryInstance)
    {
    	def proposalApprovalAuthorityMapInstance = new ProposalApprovalAuthorityMap()
    	def level = 0
    	if(proposalApprovalAuthorityMapInstanceList.size()>0)
    	{
    		for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
    		{
    			if (level<proposalApprovalAuthorityMapInstanceList[i].approveOrder)
    			{
    				level = proposalApprovalAuthorityMapInstanceList[i].approveOrder
    				proposalApprovalAuthorityMapInstance.approveOrder = level+1
    				expenseRequestEntryInstance.level = (expenseRequestEntryInstance.level)+1
    			}
    				
    		}
    		
    	}
    	else {
    		proposalApprovalAuthorityMapInstance.approveOrder = 1
    		expenseRequestEntryInstance.level = (expenseRequestEntryInstance.level)+1
    	}
    	
    	proposalApprovalAuthorityMapInstance.approvalAuthority= approvalAuthorityInstance
    	proposalApprovalAuthorityMapInstance.proposalId = expenseRequestEntryInstance.id
    	proposalApprovalAuthorityMapInstance.processRestartOrder = 1
    	proposalApprovalAuthorityMapInstance.proposalType = 'ExpenseRequest'
    	proposalApprovalAuthorityMapInstance.activeYesNo = 'Y'
		proposalApprovalAuthorityMapInstance.remarks = ""
    	
    	proposalApprovalAuthorityMapInstance.createdBy = userInstance.username
    	proposalApprovalAuthorityMapInstance.createdDate = new Date()
    	
    	if (proposalApprovalAuthorityMapInstance.save(flush: true)) 
    	{
    		return proposalApprovalAuthorityMapInstance
    	}
    }
    
    
    
    
    public checkApproveOrder(def proposalApprovalAuthorityMapInstance,def proposalApprovalAuthorityMapInstanceList)
    {
    	def order = proposalApprovalAuthorityMapInstance.approveOrder
    	for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
		{
    		if(order < proposalApprovalAuthorityMapInstanceList[i].approveOrder)
    		{
    			order = proposalApprovalAuthorityMapInstanceList[i].approveOrder
    		}
		}
    	return order
    }
    
   
    public getProposalApprovalAuthorityMapInstanceListByProposalId(def expenseRequestEntryId)
    {
    	def proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalId =" +expenseRequestEntryId+ " and PAM.proposalType = 'ExpenseRequest'")
    	return proposalApprovalAuthorityMapInstanceList
    }
	
    /*
     * Getting heighest approve order of an expense request
     */
    def getExpenseRequestMaxOrder(def expenseRequestEntryInstance)
    {
    	def expenseRequestMaxOrder = ProposalApprovalAuthorityMap.executeQuery("select MAX(PM.approveOrder) from ProposalApprovalAuthorityMap PM where PM.proposalType='ExpenseRequest' and PM.proposalId="+expenseRequestEntryInstance.id)
    	return expenseRequestMaxOrder
    }
    
    /*
     * Getting ProposalApprovalAuthorityMap By heighest approve order of an expense request
     */
    public getProposalApprovalAuthorityMapByApproveOrder(def expenseRequestEntryInstance,def expenseRequestMaxOrder)
    {
    	def proposalApprovalAuthorityMapInstance= ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PAM where PAM.proposalId =" +expenseRequestEntryInstance.id+ " and PAM.proposalType = 'ExpenseRequest' and PAM.approveOrder ="+expenseRequestMaxOrder)
    	return proposalApprovalAuthorityMapInstance
    }
    /*
     * Save Proposal Approval Authority Map for proposal application
     */
     public saveProposalApprovalAuthorityMapForProposalApplication(def approvalAuthorityInstance,def proposalId,def proposalType)
    {
    	 def proposalApprovalAuthorityMapInstance 
    	 def proposalApprovalAuthorityMapLastInstance =getProposalApprovalAuthorityMapByProposalIdAndApprovalAuthority(proposalId,approvalAuthorityInstance.id,proposalType)
    	
    	 if(proposalApprovalAuthorityMapLastInstance)
    	 {
    		 proposalApprovalAuthorityMapInstance=proposalApprovalAuthorityMapLastInstance
    	 }
    	 else
    	 {
    		 def proposalApprovalAuthorityMapOldInstance = getProposalApprovalAuthorityMapByProposalIdAndType(proposalId,proposalType)
    		if(proposalApprovalAuthorityMapOldInstance)
    			{
    				proposalApprovalAuthorityMapOldInstance.activeYesNo = 'N'
    				proposalApprovalAuthorityMapOldInstance.save()
    			}	 
    		proposalApprovalAuthorityMapInstance= new ProposalApprovalAuthorityMap()
    	 }
    	 proposalApprovalAuthorityMapInstance.approveOrder = 1
    	 proposalApprovalAuthorityMapInstance.approvalAuthority= approvalAuthorityInstance
     	proposalApprovalAuthorityMapInstance.proposalId = proposalId
     	proposalApprovalAuthorityMapInstance.processRestartOrder = 1
     	proposalApprovalAuthorityMapInstance.proposalType = proposalType
     	proposalApprovalAuthorityMapInstance.activeYesNo = 'Y'
 		proposalApprovalAuthorityMapInstance.remarks = ""
 		if(proposalApprovalAuthorityMapInstance.save())
 		{
 			
 		}
 		else
 		{
 			
 		}
 		return proposalApprovalAuthorityMapInstance
    }
    /*
     * method to get proposal approval authority map by user id
     */
     public getProposalApprovalAuthorityMapByReviewer(def userId,def proposalType)
     {
     	def proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = '"+proposalType+"' and PAM.approvalAuthority in (select AD.approvalAuthority from ApprovalAuthorityDetail AD where AD.activeYesNo = 'Y' and AD.person.id="+userId+")")
     	return proposalApprovalAuthorityMapInstanceList
     }
     /*
      * method to get proposal approval authority map by proposal id and type
      */
      public getProposalApprovalAuthorityMapByProposalIdAndType(def proposalId,def proposalType)
      {
      	def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = '"+proposalType+"' and PAM.proposalId="+proposalId+" and PAM.activeYesNo = 'Y'" )
      	return proposalApprovalAuthorityMapInstance
      }
      /*
       * method to get proposal approval authority map by proposalid,approvalauthority and type
       */
       public getProposalApprovalAuthorityMapByProposalIdAndApprovalAuthority(def proposalId,def approvalauthorityId,def proposalType)
       {
       	def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = '"+proposalType+"' and PAM.proposalId="+proposalId+" and PAM.approvalAuthority="+approvalauthorityId+" and PAM.activeYesNo = 'Y'")
       	return proposalApprovalAuthorityMapInstance
       }
       /*
        * method to get proposal approval authority map by proposalid,approvalauthority and type
        */
       public getProposalApprovalAuthorityMapById(def id)
       {
    	def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.get(id)
    	return proposalApprovalAuthorityMapInstance
       }
       /*
        * Get proposal approval authority map List by Approvalauthority
        */
       public getProposalApprovalAuthorityMapByApprovalauthority(def approvalAuthorityId)
       {
    	   def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.approvalAuthority.id =" +approvalAuthorityId+ " and  PAM.proposalType = 'ExpenseRequest'")
   		   return proposalApprovalAuthorityMapInstance
       }
       /*
        * Get proposal Approval Authority Map By Id and Expense Request
        */
       public getProposalAuthorityMapById(def proposalApprovalAuthorityMapId)
       {
    	   def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PAM where PAM.id =" + proposalApprovalAuthorityMapId + "and PAM.proposalType = 'ExpenseRequest'")
       	   return proposalApprovalAuthorityMapInstance
       }

       /*
        * Get proposal Type  and Proposal Id 
        */  
       public getProposalAuthorityMapByType(def proposalInstance)
       {
    	   def authorityInstance= ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = 'PreProposal' and PAM.proposalId ="+proposalInstance.id)
    	   authorityInstance
       }
       /*
        * Method to get Proposal Approval AuthorityMap Details
        */
       public getProposalAuthorityMapDetails(def proposalApplicationInstance,def params)
       {
    	   def proposalApprovalAuthorityMapList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PM where PM.activeYesNo='Y' and PM.proposalId="+proposalApplicationInstance.proposal.id+" and PM.proposalType='"+params.proposalType+"' and PM.approveOrder= "+params.approveOrder)
    	   return proposalApprovalAuthorityMapList
       }
       
       /*
        * Method to check duplicate Proposal Approval AuthorityMap Details
        */
       
       public getDuplicateProposalAuthorityMapDetails(def proposalApplicationInstance,def params)
       {
       
           def proposalApprovalAuthorityMapData = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalId="+proposalApplicationInstance.proposal.id+" and PAM.proposalType='"+params.proposalType+"' and PAM.approvalAuthority="+params.approvalAuthority.id)
           return proposalApprovalAuthorityMapData
       
       }
       /*
        * method to get proposal approval authority map by user id and type
        */
        public getProposalAppAuthorityMapByReviewer(def userId,def params)
        {
        	def proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.proposalType = '"+params.ProposalType+"' and PAM.approvalAuthority in (select AD.approvalAuthority from ApprovalAuthorityDetail AD where AD.activeYesNo = 'Y' and AD.person.id="+userId+")")
        	return proposalApprovalAuthorityMapInstanceList
        }
   	

       
       public getProposalApprovalAuthorityMapInstanceListByProposalIdAndType(def expenseRequestEntryId,def params)
       {
       	def proposalApprovalAuthorityMapDuplicateInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.approvalAuthority.id='"+params.approvalAuthority.id+"'and PAM.proposalId =" +expenseRequestEntryId+ " and PAM.proposalType = 'ExpenseRequest'")
       	return proposalApprovalAuthorityMapDuplicateInstance
       }
   	
       public getProposalApprovalAuthorityMapByApprovalauthorityId(def approvalAuthorityId)
       {
    	   def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PAM where PAM.approvalAuthority.id =" +approvalAuthorityId)
    	   return proposalApprovalAuthorityMapInstance
       }
}
