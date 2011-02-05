class ExpenseRequestService {

	public List chkFundAvailableByAcctHead(def expenseRequestInstance)
	{
		def recAmtList=GrantReceipt.executeQuery("select sum(GR.amount) from GrantReceipt GR,GrantAllocation GA,GrantAllocationSplit GS where  GS.grantAllocation.id=GA.id and GR.grantAllocation.id="+expenseRequestInstance.grantAllocation.id+" and GR.grantAllocationSplit.id=GS.id and GS.accountHead.id="+expenseRequestInstance.accountHead.id)
		return recAmtList
	}
	
	public List getExpenseRequestByProjects(def projectId)
	{
		def expenseRequestInstanceList=ExpenseRequest.findAll("from ExpenseRequest ER where ER.grantAllocation.projects.id="+projectId)
		
		return expenseRequestInstanceList
	}
	/**
	 * Getting expenseRequestEntryInstanceList by Investigator id
	 */
	public getExpenseRequestEntryByInvestigator(def InvestigatorId)
	{
		def expenseRequestEntryInstanceList = ExpenseRequestEntry.findAll("from ExpenseRequestEntry ERE where ERE.investigator.id="+InvestigatorId)
		return expenseRequestEntryInstanceList
		
	}
	
	/*
	 * Getting ExpenseRequestEntry List by Project Id
	 */
	public getExpenseRequestEntryByProjectId(def ProjectId)
	{
		def expenseRequestEntryInstance = ExpenseRequestEntry.findAll("from ExpenseRequestEntry ERE where ERE.projects.id="+ProjectId)
		return expenseRequestEntryInstance
	}
	/*
	 * Getting ExpenseRequestEntry by Request Id
	 */
	public getExpenseRequestEntryById(def RequestId)
	{
		def expenseRequestEntryInstance = ExpenseRequestEntry.find("from ExpenseRequestEntry ERE where ERE.id="+RequestId)
		return expenseRequestEntryInstance
	}
	
	/*
	 * Getting ExpenseRequestEntry by Request Code
	 */
	 public getExpenseRequestEntryByRequestCode(def RequestCode)
	 {
		 def expenseRequestEntryInstance = ExpenseRequestEntry.find("from ExpenseRequestEntry ERE where ERE.expenseRequestCode ='"+RequestCode+"'")
		 return expenseRequestEntryInstance
	 }
	
	 /*
	 * Getting ExpenseRequestEntry Instance by Project Id
	 */
		public getRequestEntryByProjectId(def ProjectId)
		{
			def expenseRequestEntryInstance = ExpenseRequestEntry.find("from ExpenseRequestEntry ERE where ERE.projects.id="+ProjectId)
			return expenseRequestEntryInstance
		}
}

