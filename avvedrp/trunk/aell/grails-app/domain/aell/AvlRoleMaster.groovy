package aell

class AvlRoleMaster {
	
	String  authority
	String status
	static mapping = {
		cache true
	    id column: "role_id"
		authority column: "role_name"
		version false
	    	
	 }
	    static constraints = {
		authority blank: false
		
	    }
}
