



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

	static constraints = {
		username blank: false, unique: true
		userRealName(blank: false)
		userSurName(blank: true,nullable:true)
		email email:true,blank:true
		password blank: false
	}

	Set<Authority> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}
