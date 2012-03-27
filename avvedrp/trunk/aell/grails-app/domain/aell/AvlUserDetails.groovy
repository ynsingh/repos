package aell

class AvlUserDetails {
	AvlUserMaster avlUserMaster
	String firstName
	String lastName
	String age
	Long phoneNumber
	String profession
	String college
	String university
	String specialization
	Date dateOfRegistration
	String country
	String state
	String gender
	String news_letter_status
    static constraints = {
		avlUserMaster blank:false,unique: true
		firstName nullable:true, blank:true
		lastName nullable:true, blank:true
		age nullable:true, blank:true
		phoneNumber nullable:true, blank:true
		profession nullable:true, blank:true
		college nullable:true, blank:true
		university nullable:true, blank:true
		specialization nullable:true, blank:true
		dateOfRegistration nullable:true, blank:true
		country nullable:true, blank:true
		state nullable:true, blank:true
		gender nullable:true, blank:true
		news_letter_status nullable:true, blank:true	
		gender (inList:["M","F"])
		news_letter_status (inList:["Y","N"])
    }
	static mapping = {
	  avlUserMaster column: "user_id"	  
	  version false
	   }
}
