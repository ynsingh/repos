package aell

class AvlUserLoginDetails
 {
	Integer userId
	String sessionId
	Date loginTime
	Integer loginDuration
	String loginIp
	String  logoutStatus 
	Integer sessionCount
	 static mapping = {
	   version false
	    		
	   }
	    static constraints = {
		loginDuration nullable: true
		sessionCount nullable:true
		 }
		
}
