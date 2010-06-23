class GrantAllocationSplit {
	
	static auditable=true
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
    AccountHeads subAccHead
    AccountHeads accHead
    String accHeadPeriod
    static transients = [ "isSaved" ,"unAllocatedAmt","subAccHead","accHead","accHeadPeriod"]

}
