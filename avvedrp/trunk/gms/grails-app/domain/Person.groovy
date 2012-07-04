



/**
 * User domain class.
 */
class Person {
	transient springSecurityService
	transient grailsApplication
	transient sessionFactory
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
	
	static hasMany = [openIds: OpenID]
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	private boolean isDirty(String fieldName) {
		def session = sessionFactory.currentSession
		def entry = findEntityEntry(session)
		if (!entry) {
			return false
		}

		Object[] values = entry.persister.getPropertyValues(this, session.entityMode)
		int[] dirtyProperties = entry.persister.findDirty(values, entry.loadedState, this, session)
		int fieldIndex = entry.persister.propertyNames.findIndexOf { fieldName == it }
		return fieldIndex in dirtyProperties
	}

	private findEntityEntry(session) {
		def entry = session.persistenceContext.getEntry(this)
		if (!entry) {
			return null
		}

		if (!entry.requiresDirtyCheck(this) && entry.loadedState) {
			return null
		}

		entry
	}

	Set<Authority> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
}
