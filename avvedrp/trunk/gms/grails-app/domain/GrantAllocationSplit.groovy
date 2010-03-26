class GrantAllocationSplit {
	Projects projects
    GrantAllocation grantAllocation
    AccountHeads accountHead
    GrantPeriod grantPeriod
	double amount
	String description
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate
	
	static constraints={
		grantAllocation(nullable:true)
		amount(nullable:false)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		}
    
    boolean isSaved
    double unAllocatedAmt
    static transients = [ "isSaved" ,"unAllocatedAmt"]

}
