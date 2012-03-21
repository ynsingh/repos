class FundAdvance {
	
	GrantAllocation grantAllocation
	String fundAdvanceCode
	String advanceDescription
	Date dateOfAdvance
	double advanceAmount
	String modeOfPayment
	String ddNo
	Date ddDate
	String bankName
	String ddBranch
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate
	String status

    static constraints = {
	    ddNo(nullable:true)
	    ddDate(nullable:true)
	    bankName(nullable:true)
	    ddBranch(nullable:true)
	    modifiedBy(nullable:true)
	    modifiedDate(nullable:true)
	    status(nullable:true)
    
    }
	double balanceAmount;
	String grntAlln
    static transients = [ "balanceAmount" , "grntAlln" ]
}
