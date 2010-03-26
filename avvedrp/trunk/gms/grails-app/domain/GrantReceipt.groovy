class GrantReceipt {
	
	Projects projects
	GrantAllocation grantAllocation
	GrantAllocationSplit grantAllocationSplit
	double amount
	Date dateOfReceipt
	String description
	String referenceId;
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate
	
	static constraints={
		grantAllocation(nullable:true)
		amount(nullable:false)
		dateOfReceipt(nullable:false)
		grantAllocationSplit(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
	}
	
	boolean isSaved
	double balanceAmt
    static transients = [ "isSaved","balanceAmt" ]

}
