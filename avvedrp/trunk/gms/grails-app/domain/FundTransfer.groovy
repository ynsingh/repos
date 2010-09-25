class FundTransfer 
{
	GrantAllocation  grantAllocation;
	double amount;
	Date dateOfTransfer;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
    static constraints = 
    {
    	createdDate(nullable: true)
		modifiedDate(nullable: true)
    }
    String amountCode;
    static transients = [ "amountCode" ]
}
