class Investigator
{
	String name;
	String Designation;
	Party party;
	PartyDepartment department;
	String address;
	String email;
	char activeYesNo;
	static constraints = 
	{
		
		department(nullable:true,blank:false)
		name(nullable:false,blank:false)
		email(email:true,blank:false,unique: true)
    }
}
