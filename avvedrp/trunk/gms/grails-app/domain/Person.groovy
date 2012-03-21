



/**
 * User domain class.
 */
class Person {
	String username
	String password
	String userRealName
	String userSurName
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	char activeYesNo
	String userDesignation
	String phNumber
	
	static constraints = {
		username(blank: false)
		userRealName(blank: false)
		userSurName(blank: true,nullable:true)
		email email:true,blank:true
		password blank: false
		userDesignation(nullable:true)
		phNumber(nullable:true)
	}

	Set<Authority> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}
