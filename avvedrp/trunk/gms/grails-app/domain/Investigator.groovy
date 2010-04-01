class Investigator
{
	String name;
	String Designation;
	Party party;
	PartyDepartment department;
	String address;
	String email;
	static constraints = 
	{
		
		department(nullable:false,blank:false)
		name(nullable:false,blank:false,unique: true)
		email(email:true,blank:false,unique: true)
    }
}
