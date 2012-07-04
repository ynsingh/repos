class BudgetMasterService
{
	/*
	 * Method to get All Active Financial Year
	 */
	public List getfinancialYearList()
	{
		 def financialYearInstanceList = FinancialYear.findAll("from FinancialYear FY where FY.activeYesNo = 'Y'")			
		 return financialYearInstanceList
	}
    /*
     * Method to get Duplicate Financial Year with Period
     */
    public checkDuplicateFinancialYear(def params)
	{
		 def chkFinancialYearInstanceList= FinancialYear.findAll("from FinancialYear FY where FY.activeYesNo='Y' and FY.financialPeriod='" + params.financialPeriod+"'")
		 return chkFinancialYearInstanceList
	}
    /*
    * Method to get All Active Budget Master
    */
    public List getBudgetMasterList(def partyInstance)
	{
		 def budgetMasterInstanceList = BudgetMaster.findAll("from BudgetMaster BM where BM.activeYesNo = 'Y' and BM.party="+partyInstance.id)			
		 return budgetMasterInstanceList
	}
    /*
     * Method to check Duplicate Budget Master
     */
    public checkDuplicateBudgetMaster(def params)
	{
    	 def chkbudgetMasterInstanceList= BudgetMaster.find("from BudgetMaster BM where BM.activeYesNo='Y' and BM.financialYear='"+params.financialYear.id+"' and BM.title='"+params.title+"'")
		 return chkbudgetMasterInstanceList
	}
    
    /*
     * Method to get BudgetMaster with party
     */
    public getParty(def partyInstance)
	{
    	def budgetMasterInstanceList = BudgetMaster.findAll("from BudgetMaster BM where BM.activeYesNo = 'Y' and BM.party="+partyInstance.id)
    	return budgetMasterInstanceList
	}
    /*
     * Method to get All Budget Detail with budget Master
     */
    public List getBudgetDetailsList(def budgetMasterInstance)
	{
       def budgetDetailsInstanceList = BudgetDetails.findAll("from BudgetDetails BD where BD.activeYesNo = 'Y' and BD.budgetMaster.id="+budgetMasterInstance.id)
       return budgetDetailsInstanceList
	}
    /*
     * Method to check Duplicate Budget Details
     */
    public checkDuplicateBudgetDetails(def params)
	{
        def chkbudgetDetailsInstanceList= BudgetDetails.findAll("from BudgetDetails BD where BD.activeYesNo = 'Y'and  BD.budgetMaster='"+params.budgetMasterInstance.id+"' and BD.accountHeads= "+params.accountHead.id+" and BD.accountHeads="+params.subAccountHead)
        return chkbudgetDetailsInstanceList
	}
    /*
     * Method to Get all active BudgetModuleMap
     */
    public List getBudgetModuleMapList()
	{
    	 def budgetModuleMapInstanceList = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.activeYesNo = 'Y'")			
		 return budgetModuleMapInstanceList
	}
     /*
      * Method to get code Of  Proposal
      */
    public List getproposalList(def partyInstance)
	{
    	def moduleTypeList =Proposal.executeQuery("select code from Proposal P where (P.proposalType='Proposal' and P.party="+partyInstance.id+"and P.id  NOT IN (select proposal.id from Award A where A.activeYesNo='Y'))")
    	return moduleTypeList
	}
     /*
      * Method to get NotificationTitle 
      */
    public List getNotificationList(def partyInstance)
	{
        def moduleTypeList = Notification.executeQuery("select notificationTitle from Notification N where N.publishYesNo='N' and N.party="+partyInstance.id)
        return moduleTypeList
	}
    /*
     * Method to get saved FullProposal
     */
    public List getFullProposalList(def partyInstance)
	{
      def proposalList =  Proposal.findAll("from Proposal P where P.proposalType='FullProposal' and P.proposalStatus='Saved' and  P.party="+partyInstance.id)
      return proposalList
	}
  /*
    * Method to get code of Projects
    */
    public List getprojectsList(def partyInstance)
	{
		 def projectsList = Projects.executeQuery("select code from Projects P where (P.activeYesNo='Y' and P.id  NOT IN (select projects.id from ProjectTracking PT where PT.projectStatus='Closed') and P.id IN (select projects.id from GrantAllocation GA where GA.party="+partyInstance.id+"))")
		 return projectsList
	}
    /*
     * Method to get project Title
     */
    public List getprojectTitleList(def proposalId)
	{
		 def proposalApplicationInstance = ProposalApplication.executeQuery("select projectTitle from ProposalApplication PA where PA.proposal="+proposalId)
		 return proposalApplicationInstance
	}
    /*
     * method to get proposalcode from params
     */
    public proposalDetails(def params)
	{
       def proposalInstance = Proposal.find("from Proposal P where P.code='"+params.moduleTypeId+"'")
       return proposalInstance
	}
    /*
     * method to get notificationTitle from params
     */
    public notificationDetails(def params)
	{
    	def notificationInstance = Notification.find("from Notification N where N.notificationTitle='"+params.moduleTypeId+"'")
        return notificationInstance
	}
    
   /*
     * method to get Projectcode from params
     */
    public projectDetails(def params)
	{
    	def projectInstance = Projects.find("from Projects P where P.code='"+params.moduleTypeId+"'")
        return projectInstance
	}
    
    /*
     * method to get Proposal Id By budgetModuleMapInstance
     */  
    public getproposalIdbyBudgetModuleMapInstance(def budgetModuleMapInstance)
	{
       def proposalInstance = Proposal.find("from Proposal P where P.id='"+budgetModuleMapInstance+"'")
       return proposalInstance
	}
    /*
     * method to get ProjectId By budgetModuleMapInstance
     */ 
    public getprojectIdbyBudgetModuleMapInstance(def budgetModuleMapInstance)
	{
       def projectInstance = Projects.find("from Projects P where P.id='"+budgetModuleMapInstance+"'")
       return projectInstance
	}
    /*
     * method to get Fullproposal Id By budgetModuleMapInstance
     */
    
    public getfullProposalIdbyBudgetModuleMapInstance(def budgetModuleMapInstance)
	{
       def proposalInstance = Proposal.find("from Proposal P where P.id='"+budgetModuleMapInstance+"'")
       return proposalInstance
	}
    /*
     * method to get Notification Id By budgetModuleMapInstance
     */
    public getNotificationIdbyBudgetModuleMapInstance(def budgetModuleMapInstance)
	{
      def notificationInstance = Notification.find("from Notification N where N.id='"+budgetModuleMapInstance+"'")
      return notificationInstance
	}

	
	/*
	 * Get the list of all ModuleMAp  based on BudgetMAster
	 */ 
	public List getModuleMapBudgetMaster(def budgetMasterInstance)
	{
		def getModuleMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster="+budgetMasterInstance.id+" and BMM.activeYesNo='Y'")
		return getModuleMap
	}
	/*
	 * Method to check Duplicate Module Map
	 */
	
    public checkDuplicateBudgetModMap(def params,def budgetModuleMapInstance)
	{
	   def checkDuplicateModMap = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.budgetMaster='"+params.budgetMaster.id+"' and BMM.moduleType='"+params.moduleType+"' and BMM.moduleTypeId='"+budgetModuleMapInstance.moduleTypeId+"'")
	   return checkDuplicateModMap
	}
    /*
     * Method to get Party from budgetMaster
     */
    public getpartyId(def partyInstance)
	{
     	def budgetMasterInstanceList = BudgetMaster.findAll("from BudgetMaster BM where BM.party.id="+partyInstance)
     	return budgetMasterInstanceList
	}
    /*
     * Method to get BudgetMaster from BudgetModuleMap
     */
    public getbudgetModMapInstance(def budgetMasterInstance,def email)
	{
    	def budgetModMapInstance = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.activeYesNo = 'Y' and BMM.createdBy='"+email+"' and BMM.budgetMaster="+budgetMasterInstance)
    	return budgetModMapInstance
	}
    
    /*
     * Method to get BudgetMaster from BudgetModuleMap
     */
    public getbudgetModInstance(def budgetMasterInstance)
	{
    	def budgetModMapInstance = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.activeYesNo = 'Y' and BMM.budgetMaster="+budgetMasterInstance)
    	return budgetModMapInstance
	}
    /*
     * Method to get BudgetMaster Id from Budgetdetails
     */
    public getbudgetDetailsInstancebyBudgetMasterId(def budgetMasterInstance)
	{
    	def budgetMasterInDetails = BudgetDetails.find("from BudgetDetails BD where BD.budgetMaster.id="+budgetMasterInstance.id)
    	return budgetMasterInDetails
	}
    /*
     * Method to get Proposal id from Proposal Application
     */
    public proposalApplicationInstanceByProposalId(def proposalInstance)
	{
    	def proposalAppInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id="+proposalInstance.id)
    	return proposalAppInstance
	}
    /*
     * Method to get BudgetMaster Id
     */
    public budgetMasterInstanceByBudgetMasterId(def params)
	{
    	def budgetMasterInstance = BudgetMaster.find("from BudgetMaster BM where BM.id="+params.budgetMaster.id)
    	return budgetMasterInstance
	}
    /*
     * Method to check Duplicate BudgetDetails with AccountHeads
     */
    public chkDuplicateAccountHeadInstance(def budgetMasterInstance,def accountHeadInstance)
	{
    	def chkbudgetDetailsInstanceList= BudgetDetails.find("from BudgetDetails BD where BD.activeYesNo = 'Y' and  BD.budgetMaster.id='"+budgetMasterInstance.id+"' and BD.accountHeads.id= "+accountHeadInstance.id+"")
    	return chkbudgetDetailsInstanceList
	}
    /*
     * Method to check Duplicate BudgetDetails with Sub AccountHeads
     */
    public chkDuplicateSubAccountHeadInstance(def budgetMasterInstance,def subAccountHeadInstance)
	{
    	 def chkbudgetDetailsSubInstanceList= BudgetDetails.find("from BudgetDetails BD where BD.activeYesNo = 'Y' and  BD.budgetMaster.id='"+budgetMasterInstance.id+"' and BD.accountHeads.id= "+subAccountHeadInstance.id+"")
    	 return chkbudgetDetailsSubInstanceList
	}
   /*
    * Method to getBudgetMaster Id And AccountHead Id
    */
    public getMasterIdAndAccountHeadId(def params,def budgetDetailsInstance)
	{
    	def budgetCurrenthead = BudgetDetails.find("from BudgetDetails BD where BD.budgetMaster.id="+params.budgetMasterId+" and BD.accountHeads.id="+budgetDetailsInstance.accountHeads.id)
    	return budgetCurrenthead
	}
    /*
     * Method to get Current AcoountHead in Budget Details
     */
    public getAccountHeadIdInCurrentBudget(def budgetCurrenthead)
	{
    	def accountHeadsInstance=AccountHeads.find("from AccountHeads P where P.id="+budgetCurrenthead.accountHeads.id+" and P.activeYesNo='Y'")
    	return accountHeadsInstance
	}
    /*
     * Method to get Parent AccountHead ID
     */
    public getParentAccountHeadId(def accountHeadsInstance)
	{
    	def subAccountHeadsList = AccountHeads.findAll("from AccountHeads AH where AH.parent="+accountHeadsInstance.parent.id)
    	return subAccountHeadsList
	}
    /*
     * Method to getBudgetMaster Id from Budget Details
     */
    public getMasterIdFromBudgetDetails(def params)
	{
    	def budgetDetailsInMasterlist = BudgetDetails.find("from BudgetDetails BD where BD.budgetMaster='"+params.budgetMasterInstance.id+"'")
    	return budgetDetailsInMasterlist
	}
    /*
     * Method to getBudgetMaster Id from Budget Module Map
     */
    public getMasterIdFromBudgetModMap(def budgetDetailsInMasterlist)
	{
    	def budgetModMapInstance = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.activeYesNo = 'Y' and BMM.budgetMaster="+budgetDetailsInMasterlist.budgetMaster.id)
    	return budgetModMapInstance
	}
    /*
     * Method to get ModuleType Id
     */
    public getmodulTypeIdByModuleType(def budgetId,def moduleType)
	{
    	def budgetModMapInstance = BudgetModuleMap.findAll("from BudgetModuleMap BMM where BMM.moduleType='"+moduleType+"' and BMM.moduleTypeId="+budgetId)
    	return budgetModMapInstance
	}
   /*
    * method to get Budget Master ID
    */
    public getMasterIdByModuleMap(def budgetMasterId)
	{
    	def budgetMasterInstance = BudgetMaster.findAll("from BudgetMaster BM where BM.id="+budgetMasterId)
    	return budgetMasterInstance
	}
    /*
     * Method to get Budget details by master Id
     */
    public getBudgetDetailsByMaasterId(def masterId)
	{
    	def budgetDetailsInstanceList = BudgetDetails.findAll("from BudgetDetails BD where BD.activeYesNo = 'Y' and BD.budgetMaster.id="+masterId)
    	return budgetDetailsInstanceList
	}
    /*
     * Method to get Total Budget amount
     */
    public getTotalBudgetAmount(def budgetMasterInstance)
	{
    	def budgetMasterAmountInstance = BudgetMaster.executeQuery("select SUM(BM.totalBudgetAmount) from BudgetMaster BM where BM.id="+budgetMasterInstance.id+" group by BM.id")
    	return budgetMasterAmountInstance
	} 
    /*
     * Method to get Allocated Amount
     */
    public getAllocatedAmount(def budgetMasterInstance)
	{
    	def budgetDetailAmountInstance = BudgetDetails.executeQuery("select SUM(BD.allocatedAmount) from BudgetDetails BD where BD.budgetMaster.id="+budgetMasterInstance.id+" group by BD.budgetMaster.id")
    	return budgetDetailAmountInstance
	}
    /*
     * Method to get Current amount In AccountHead
     */
    public getBudgetCurrentAmount(def budgetDetailsInstance,def budgetMasterInstance)
	{
    	def budgetCurrentAmount = BudgetDetails.executeQuery("select BD.allocatedAmount from BudgetDetails BD where BD.budgetMaster.id="+budgetMasterInstance.id+" and BD.accountHeads= "+budgetDetailsInstance.accountHeads.id)
    	return budgetCurrentAmount
	}
   
    public getModuleMapByBudgetMasterId(def params)
	{
    	def getModuleMapInstance = BudgetModuleMap.find("from BudgetModuleMap BMM where BMM.activeYesNo='Y' and BMM.budgetMaster.id="+params.id)
    	return getModuleMapInstance
	}
}

