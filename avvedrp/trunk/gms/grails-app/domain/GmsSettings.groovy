class GmsSettings {
	String name;
	String value;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = {
    	createdDate(nullable: true)
    	createdBy(nullable: true)
    	modifiedBy(nullable:true)
		modifiedDate(nullable:true)
    }
}
