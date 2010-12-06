class GrantAllocation
{
	
	static auditable=true
	Party party;
	Party granter;
	Projects projects;
	String code;
	double amountAllocated;
	Date DateOfAllocation;
	String allocationType;
	String remarks;
	String sanctionOrderNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	double totAllAmount
	double balanceAmount
	static constraints = {
		
		createdDate(nullable: true)
		modifiedDate(nullable: true)
		granter(nullable:true)
		sanctionOrderNo(nullable: true)
    }
    
	boolean isSaved
	String grantCode;
    static transients = [ "totAllAmount","isSaved","grantCode","balanceAmount" ]

}
