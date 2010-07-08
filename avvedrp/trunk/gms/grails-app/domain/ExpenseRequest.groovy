class ExpenseRequest  {
	
	static auditable=true
	GrantAllocation grantAllocation;
	AccountHeads accountHead;
	double requestedAmount;
	Date requestedDate;
	char fundAvailableYesNo


 static constraints=
 {
	 grantAllocation(nullable:false)
	 accountHead(nullable:false)
	 requestedAmount(nullable:false)
	 requestedDate(nullable:false)
 }
	String grantCode;
    static transients = [ "grantCode" ]
}