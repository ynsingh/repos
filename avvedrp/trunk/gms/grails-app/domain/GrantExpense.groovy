class GrantExpense {
	
	Projects projects
	GrantAllocation grantAllocation
	GrantAllocationSplit grantAllocationSplit
	double expenseAmount
	Date dateOfExpense
	String description
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate
	
	double balanceAmount
	
	static constraints={
		grantAllocation(nullable:true)
		grantAllocationSplit(nullable:true)
		expenseAmount(nullable:false)
		dateOfExpense(nullable:false)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		
	}
	
	Date dateFrom
	Date dateTo
	boolean isSaved
	String accountHeadCode
	static transients = [ "balanceAmount","dateFrom","dateTo","isSaved","accountHeadCode" ]

}
