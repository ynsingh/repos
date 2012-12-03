

class AssetTransfer 

{
	Asset asset
	String fromLocation
	String toLocation
	String fromDepartment
	String toDepartment
	String requestedBy
	String transferNo
	Date transferDate
	String createdBy;
    	Date createdDate;
    	String modifiedBy;
   	Date modifiedDate;




	
    static constraints = 
	{

	fromDepartment(nullable:true)
	toDepartment(nullable:true)
	createdBy(nullable:true)
   	createdDate(nullable:true)
    	modifiedBy(nullable:true)
    	modifiedDate(nullable:true)
   	 }
}
