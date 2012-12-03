class Investigator
{
	String name;
	String userSurName
	String Designation;
	Party party;
	PartyDepartment department;
	String address;
	String email;
	char activeYesNo;
	String aadhaarNo
	static constraints = 
	{
		
		department(nullable:true)
		name(nullable:false,blank:false)
		userSurName(nullable:false,blank:false)
		email(email:true,blank:false,unique: true)
		aadhaarNo(nullable:true)
    }
	String fullName
    static transients = [ "fullName" ]
}
