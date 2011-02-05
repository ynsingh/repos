



/**
 * Authority domain class.
 */
class Authority {

	static hasMany = [people: Person]

	/** description */
	String description
	/** ROLE String */
	String authority
	
	char activeYesNo;

	static constraints = {
		authority(blank: false)
		description()
	}
}
