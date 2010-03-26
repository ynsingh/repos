class GrantAllocationTracking {
	
	GrantAllocation grantAllocation;
	String grantAllocationStatus;
	Date dateOfTracking;
	String remarks;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	
	static constraints = {
		remarks(nullable: true)
    }
	
	String trackType;
	String saveMode;
	static transients = [ "trackType","saveMode" ]

}
