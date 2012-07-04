class UniversityMaster {
	
	String nameOfUniversity;
	String address;
	String phoneNo;
	String email;
	String code;
	char activeYesNo;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;
	static constraints ={
		createdBy(nullable:true)
	    createdDate(nullable:true)
		modifiedBy(nullable:true)
		modifiedDate(nullable:true)
		}

}
