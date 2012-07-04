



class OpenID {

	String url

	static belongsTo = [user: Person]

	static constraints = {
		url unique: true
	}
}
