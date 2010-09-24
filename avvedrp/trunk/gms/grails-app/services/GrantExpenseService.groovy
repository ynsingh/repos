import java.text.SimpleDateFormat

class GrantExpenseService {
	
	/**
	 * Get grant expense by ID
	 */
	public GrantExpense getGrantExpenseById(Integer grantExpenseId){
		def grantExpenseInstance = GrantExpense.get( grantExpenseId )
		return grantExpenseInstance
	}
	
	/**
	 * Delete grant expense
	 */
	public Integer deleteGrantExpense(Integer grantExpenseId){
		Integer grantAllocationId = null
		def grantExpenseInstance = getGrantExpenseById( grantExpenseId )
		if(grantExpenseInstance) {
            grantExpenseInstance.delete()
           // grantAllocationId = grantExpenseInstance.grantAllocation.id
            grantAllocationId = 1;
		}
		else{
			grantAllocationId = -1
		}
		return grantAllocationId
	}
	
	/**
	 * Function to get grant expense by grant allocation and range of expense date.
	 */
	public GrantExpense[] getGrantExpenseByGrantAllocationAndExpenseDateRange(def grantAllocationInstance,def dateFrom,def dateTo){
		def grantExpenseInstanceList = GrantExpense.findAllByGrantAllocationAndDateOfExpenseBetween(grantAllocationInstance,dateFrom,dateTo)
		return grantExpenseInstanceList
	}
	
	
	/**
	 * Function to get grant expense by project and range of expense date.
	 */
	public GrantExpense[] getGrantExpenseByProjectsAndExpenseDateRange(def projectInstance,def dateFrom,def dateTo){
		def grantExpenseInstanceList = GrantExpense.findAllByProjectsAndDateOfExpenseBetween(projectInstance,dateFrom,dateTo)
		return grantExpenseInstanceList
	}
	
	/**
	 * Function to get grant expense by grant allocation and range of expense date.
	 */
	public GrantExpense[] getGrantExpenseByProjectsAndExpenseDateRange(def grantExpenseId,def projectInstance,def dateFrom,def dateTo){
		def grantExpenseInstanceList
		def sdf1 = new SimpleDateFormat('yyyy/MM/dd')
		if(grantExpenseId == null){
			String query = "from GrantExpense GE where GE.projects.id = "+projectInstance.id+" and DATE_FORMAT(GE.dateOfExpense,'%Y/%m/%d')  "+
				"between '"+sdf1.format(dateFrom)+"' and '"+sdf1.format(dateTo)+"'order by GE.dateOfExpense" 
			println "*****Query "+query
			grantExpenseInstanceList = GrantExpense.findAll(query)
		}
		else{
			def grantExpense = getGrantExpenseById(new Integer(grantExpenseId))
			grantExpenseInstanceList = GrantExpense.findAllByProjectsAndDateOfExpense(projectInstance,grantExpense.dateOfExpense)
		}
		return grantExpenseInstanceList
	}
	
	
	/**
	 * Function to get grant expense by grant allocation and range of expense date.
	 */
	public GrantExpense[] getGrantExpenseByGrantAllocationAndExpenseDateRange(def grantExpenseId,def grantAllocationInstance,def dateFrom,def dateTo){
		def grantExpenseInstanceList
		def sdf1 = new SimpleDateFormat('yyyy/MM/dd')
		if(!grantExpenseId){
			String query = "from GrantExpense GE where GE.grantAllocation.id = "+grantAllocationInstance.id+" and GE.dateOfExpense  "+
				"between '"+sdf1.format(dateFrom)+"' and '"+sdf1.format(dateTo)+"' order by GE.dateOfExpense" 
			println "*****Query "+query
			grantExpenseInstanceList = GrantExpense.findAll(query)
		}
		else{
			def grantExpense = getGrantExpenseById(new Integer(grantExpenseId))
			grantExpenseInstanceList = GrantExpense.findAllByGrantAllocationAndDateOfExpense(grantAllocationInstance,grantExpense.dateOfExpense)
		}
		return grantExpenseInstanceList
	}
	
	/**
	 * Function to get grant expense summary.
	 */
	public List getGrantExpenseSummary(grantAllocationInstance){
    	/* Get all heads allocated to grant */
    	
    	def grantExpenseSummaryList = new ArrayList()
    	def grantAllocationSplitInstanceList = GrantAllocationSplit.findAllByGrantAllocation(grantAllocationInstance)
    	
		for(int i=0;i<grantAllocationSplitInstanceList.size();i++){
    		GrantAllocationSplit grantAllocationSplitInstance = grantAllocationSplitInstanceList.get(i)
    		def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.grantAllocationSplit = ? ",[grantAllocationSplitInstance])
    		def balance = grantAllocationSplitInstance.amount
    		System.out.println(" Account head "+grantAllocationSplitInstance.accountHead.code+" SumAmount "+sumAmount[0])
    		if(sumAmount != null && sumAmount[0] != null)
    			balance = grantAllocationSplitInstance.amount-sumAmount[0]
    		System.out.println("Balance "+balance)
    		GrantExpense grantExpenseInstance = new GrantExpense()
    		grantExpenseInstance.grantAllocationSplit = grantAllocationSplitInstance
    		grantExpenseInstance.expenseAmount = grantAllocationSplitInstance.amount
    		grantExpenseInstance.balanceAmount = balance
    		   		
    		grantExpenseSummaryList.add(grantExpenseInstance)
    	}
    	return grantExpenseSummaryList
    	
    }

	
	/**
	 * Function to get grant expense summary.
	 */
	public List getGrantExpenseSummaryForAProject(projectInstance){
    	/* Get all heads allocated to grant */
    	
    	def grantExpenseSummaryList = new ArrayList()
    	def grantAllocationSplitInstanceList = GrantAllocationSplit.findAllByProjects(projectInstance)
    	
		for(int i=0;i<grantAllocationSplitInstanceList.size();i++){
    		GrantAllocationSplit grantAllocationSplitInstance = grantAllocationSplitInstanceList.get(i)
    		def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.grantAllocationSplit = ? ",[grantAllocationSplitInstance])
    		def balance = grantAllocationSplitInstance.amount
    		System.out.println(" Account head "+grantAllocationSplitInstance.accountHead.code+" SumAmount "+sumAmount[0])
    		if(sumAmount != null && sumAmount[0] != null)
    			balance = grantAllocationSplitInstance.amount-sumAmount[0]
    		System.out.println("Balance "+balance)
    		GrantExpense grantExpenseInstance = new GrantExpense()
    		grantExpenseInstance.grantAllocationSplit = grantAllocationSplitInstance
    		grantExpenseInstance.expenseAmount = grantAllocationSplitInstance.amount
    		grantExpenseInstance.balanceAmount = balance
    		   		
    		grantExpenseSummaryList.add(grantExpenseInstance)
    	}
    	return grantExpenseSummaryList
    	
    }
	/**
	 * Function to update grant expense
	 */
	public GrantExpense updateGrantExpense(def grantExpenseparams){
		def grantExpenseInstance = getGrantExpenseById(new Integer(grantExpenseparams.id))
        if(grantExpenseInstance) {
        	grantExpenseInstance.modifiedBy = "admin"
        	grantExpenseInstance.modifiedDate = new Date()
        	grantExpenseInstance.properties = grantExpenseparams
            if(!grantExpenseInstance.hasErrors() && grantExpenseInstance.save()) {
            	grantExpenseInstance.isSaved = true
            }
            else
            	grantExpenseInstance.isSaved = false
        }
		return grantExpenseInstance
	}
	
	/**
	 * Function to save grant expense
	 */
	public GrantExpense saveGrantExpense(def grantExpenseInstance){
		if(!grantExpenseInstance.hasErrors() && grantExpenseInstance.save()) {
			grantExpenseInstance.isSaved = true
		}
		else
			grantExpenseInstance.isSaved = false
			
		return grantExpenseInstance
	}
	
	/**
	 * Function to get grant expense summary.
	 */
	public List getGrantExpenseTotalForAProject(projectInstance){
    	/* Get all heads allocated to grant */
    	
    	def grantExpenseInstance = null
    	def grantExpenseSummaryList = new ArrayList()
    	def expenseAmount = 0.0
    	def allocatedAmount = 0.0
    	def balanceAmount = 0.0
    	def amtAllocatedList = GrantAllocationSplit.executeQuery("select AH.id,AH.code,sum(GAS.amount) "+
    			"as AmtAllocated from GrantAllocationSplit GAS,AccountHeads AH "+ 
    			"where AH.id = GAS.accountHead.id and GAS.projects.id="+projectInstance.id+" group by GAS.accountHead order by AH.code")
    	
		for(int i=0;i<amtAllocatedList.size();i++){
			expenseAmount = 0.0
	    	allocatedAmount = 0.0
	    	balanceAmount = 0.0
	    	
			grantExpenseInstance = new GrantExpense()
			grantExpenseInstance.accountHeadCode = amtAllocatedList[i][1]
			if(amtAllocatedList[i][2] != null)
				allocatedAmount = amtAllocatedList[i][2]
			    		
    		def sumExpAmountList = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from "+
    				"GrantExpense GE where GE.projects.id="+projectInstance.id+" and  GE.grantAllocationSplit.accountHead.id = ? ",[amtAllocatedList[i][0]])
    		if(sumExpAmountList.size() > 0){
    			if(sumExpAmountList[0] != null)
    				expenseAmount = sumExpAmountList[0]
    		}
			balanceAmount = allocatedAmount - expenseAmount
    		
    		grantExpenseInstance.expenseAmount = expenseAmount
    		grantExpenseInstance.balanceAmount = balanceAmount
    		   		
    		grantExpenseSummaryList.add(grantExpenseInstance)
    	}
    	return grantExpenseSummaryList
    	
    }
	/**
	 * Function to check whether expense is entered against headwise allocation.
	 */
	public GrantExpense[] getExpenseForGrantAllocationSplit(def grantAllocationSplitInstance){
		
		def grantExpenseInstance=[]
		grantExpenseInstance = GrantExpense.findAll(" from GrantExpense GE where GE.grantAllocationSplit.id = " + grantAllocationSplitInstance.id)
		println"*********grantAllocationSplitInstance***********"+grantExpenseInstance
		
	    return grantExpenseInstance    
		
	}	
}