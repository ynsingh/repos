class ExpenseRequestService {

	public List chkFundAvailableByAcctHead(def expenseRequestInstance)
	{
		def recAmtList=GrantReceipt.executeQuery("select sum(GR.amount) from GrantReceipt GR,GrantAllocation GA,GrantAllocationSplit GS where  GS.grantAllocation.id=GA.id and GR.grantAllocation.id="+expenseRequestInstance.grantAllocation.id+" and GR.grantAllocationSplit.id=GS.id and GS.accountHead.id="+expenseRequestInstance.accountHead.id)
				
		println"++++++++chkFundInstance.GrantReceipt.amount+++++++"+recAmtList[0]
		
			return recAmtList
	}
	
	public List getExpenseRequestByProjects(def projectId)
	{
		def expenseRequestInstanceList=ExpenseRequest.findAll("from ExpenseRequest ER where ER.grantAllocation.projects.id="+projectId)
		
		return expenseRequestInstanceList
	}
	
}

