class ProjectType
{

	Integer id;
	String type;
	char activeYesNo;
	String createdBy
	Date createdDate
	String modifiedBy
	Date modifiedDate
	
	static constraints=
	{
		type(blank:false)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
	}
	
	String saveMode;
	static transients = [ "saveMode" ]
}
