

class Asset 

{
	Projects projects;
	String assetName;
	String assetCode;
	String serialNo;
	String barCode;
	String modalName;
	String modalNo;
	String invoiceNo;
	String manufacturer;
	double cost;
	String storageLocation;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
   	Date modifiedDate;
	
    static constraints = 
	{

	invoiceNo(nullable:true)
	manufacturer(nullable:true)
	storageLocation(nullable:true)
	createdBy(nullable:true)
   	createdDate(nullable:true)
	modifiedBy(nullable:true)
	modifiedDate(nullable:true)
	}
}
