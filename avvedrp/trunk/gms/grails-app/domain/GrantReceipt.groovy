class GrantReceipt {
	
	static auditable=true
	Projects projects
	GrantAllocation grantAllocation
	GrantAllocationSplit grantAllocationSplit
	double amount
	String modeOfPayment
	String ddNo
	Date ddDate
	String bankName
	String ddBranch
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
		modeOfPayment(nullable:false)
		ddNo(nullable:true)
	    ddDate(nullable:true)
	    bankName(nullable:true)
	    ddBranch(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
	}
	
	boolean isSaved
	double balanceAmt
    static transients = [ "isSaved","balanceAmt" ]

}
