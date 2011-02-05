class ExpenseRequestEntry {
	Projects projects
	Investigator investigator
	GrantAllocation grantAllocation
	GrantAllocationSplit grantAllocationSplit
	String expenseRequestCode
	String expenseDescription
	Date dateOfExpense
	double expenseAmount
	String purchaseOrderNo
	Date purchaseOrderDate
	String invoiceNo
	Date invoiceDate
	double invoiceAmount
	String status
	Integer level
	String remarks
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate

    static constraints = {
    
		grantAllocation(nullable: true)
		grantAllocationSplit(nullable: true)
		invoiceAmount(nullable: true)
		invoiceNo(nullable: true)
		modifiedBy(nullable: true)
		modifiedDate(nullable: true)	
    }
}
